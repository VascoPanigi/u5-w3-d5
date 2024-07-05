package vascopanigi.u5_w3_d5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vascopanigi.u5_w3_d5.enitites.User;
import vascopanigi.u5_w3_d5.exceptions.UnauthorizedException;
import vascopanigi.u5_w3_d5.payloads.UserLoginDTO;
import vascopanigi.u5_w3_d5.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndCreateToken(UserLoginDTO userLoginDTO){
        User user = this.userService.findByEmail(userLoginDTO.email());
        if(bcrypt.matches(userLoginDTO.password(), user.getPassword())){
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Login failed! Try again.");
        }
    }
}
