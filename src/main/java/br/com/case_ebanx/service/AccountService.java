package br.com.case_ebanx.service;

import org.springframework.http.ResponseEntity;

import br.com.case_ebanx.dto.EventRequest;

public interface AccountService {

	ResponseEntity<?> getBalance(String id);

	ResponseEntity<?> handleEvent(EventRequest request);

	void reset();
	
}
