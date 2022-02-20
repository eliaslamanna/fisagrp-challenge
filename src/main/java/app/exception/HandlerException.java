package app.exception;

import app.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(MerchantException.class)
    public ResponseEntity<ErrorDTO> merchantException(MerchantException e) {
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

}
