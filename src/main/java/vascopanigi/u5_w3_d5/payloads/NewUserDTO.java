package vascopanigi.u5_w3_d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "First name is mandatory.")
        @Size(min = 3, max = 15, message = "Your name should have at least 3 characters and max 15")
        String name,
        @NotEmpty(message = "Last name is mandatory.")
        @Size(min = 3, max = 15, message = "Your last name should have at least 3 characters and max 15")
        String surname,
        @NotEmpty(message = "The email address is mandatory.")
        @Email(message = "The email address you inserted is not valid")
        String email,
        @NotEmpty(message = "Password is mandatory.")
        @Size(min = 7, message = "Your password should have at least 7 characters.")
        String password
) {
}
