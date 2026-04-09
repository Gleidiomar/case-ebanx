package br.com.case_ebanx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.com.case_ebanx.dto.EventRequest;
import br.com.case_ebanx.service.AccountService;

@RestController
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }
    
    @PostMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public void reset() {
        service.reset();
    }

    @GetMapping("/balance")
    public ResponseEntity<?> balance(@RequestParam("account_id") String id) {
        return service.getBalance(id);
    }

    @PostMapping("/event")
    public ResponseEntity<?> event(@RequestBody EventRequest request) {
        return service.handleEvent(request);
    }
}
