package vascopanigi.u5_w3_d5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vascopanigi.u5_w3_d5.enitites.User;
import vascopanigi.u5_w3_d5.exceptions.BadRequestException;
import vascopanigi.u5_w3_d5.payloads.NewUserDTO;
import vascopanigi.u5_w3_d5.payloads.NewUserResponseDTO;
import vascopanigi.u5_w3_d5.services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy){
        return this.userService.getAllUsers(pageNum, pageSize, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO save(@RequestBody @Validated NewUserDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewUserResponseDTO(this.userService.save(body).getId());
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedEmployee){
        return currentAuthenticatedEmployee;
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedEmployee){
        this.userService.findByIdAndDelete(currentAuthenticatedEmployee.getId());
    }

    @GetMapping("/{employeeId}")
        public User findById(@PathVariable UUID employeeId) {
        return this.userService.findById(employeeId);
    }
}
