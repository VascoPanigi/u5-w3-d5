package vascopanigi.u5_w3_d5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vascopanigi.u5_w3_d5.exceptions.BadRequestException;
import vascopanigi.u5_w3_d5.payloads.NewUserDTO;
import vascopanigi.u5_w3_d5.payloads.NewUserResponseDTO;
import vascopanigi.u5_w3_d5.payloads.UserLoginDTO;
import vascopanigi.u5_w3_d5.payloads.UserLoginResponseDTO;
import vascopanigi.u5_w3_d5.services.AuthService;
import vascopanigi.u5_w3_d5.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(authService.authenticateUserAndCreateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewUserResponseDTO(this.userService.save(body).getId());
    }
}
