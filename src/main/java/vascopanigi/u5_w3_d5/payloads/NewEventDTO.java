package vascopanigi.u5_w3_d5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEventDTO(
                          @NotEmpty(message = "The description is mandatory")
                          String description,
                          @NotEmpty(message = "The location is mandatory")
                          String location,
//                          @NotNull(message = "The date is mandatory")
//                            LocalDate date,
                          int capacity)
{
}
