package app.controller;

import app.dto.NotesDTO;
import app.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    private final IMerchantService merchantService;

    @Autowired
    public MerchantController(IMerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/notes")
    public ResponseEntity<NotesDTO> enterNotes(@RequestBody NotesDTO notes) {
        return new ResponseEntity<>(merchantService.enterNotes(notes), HttpStatus.OK);
    }
}
