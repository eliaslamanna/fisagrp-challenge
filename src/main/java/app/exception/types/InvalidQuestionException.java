package app.exception.types;

import app.dto.ErrorDTO;
import app.exception.MerchantException;
import org.springframework.http.HttpStatus;

public class InvalidQuestionException extends MerchantException {
    public InvalidQuestionException() {
        super(HttpStatus.BAD_REQUEST, new ErrorDTO("Invalid question", "The question cannot be answered with the current information."));
    }
}
