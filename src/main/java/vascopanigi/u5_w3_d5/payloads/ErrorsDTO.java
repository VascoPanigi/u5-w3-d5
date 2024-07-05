package vascopanigi.u5_w3_d5.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String errorMessage, LocalDateTime errorTime) {
}
