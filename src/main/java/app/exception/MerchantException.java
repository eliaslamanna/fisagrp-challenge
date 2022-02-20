package app.exception;

import app.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantException extends Exception {
    private HttpStatus status;
    private ErrorDTO error;
}
