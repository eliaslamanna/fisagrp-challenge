package app.exception.types;

import app.dto.ErrorDTO;
import app.exception.MerchantException;
import org.springframework.http.HttpStatus;

public class InvalidNoteEntryException extends MerchantException {
    public InvalidNoteEntryException() {
        super(HttpStatus.BAD_REQUEST, new ErrorDTO("Invalid note entry", "The note entry is invalid"));
    }
}
