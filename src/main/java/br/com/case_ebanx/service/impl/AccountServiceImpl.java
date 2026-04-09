package br.com.case_ebanx.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.case_ebanx.domain.Account;
import br.com.case_ebanx.dto.EventRequest;
import br.com.case_ebanx.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final Map<String, Account> accounts = new ConcurrentHashMap<>();
	
	public void reset() {
	    accounts.clear();
	}

    public ResponseEntity<?> getBalance(String accountId) {
        Account account = accounts.get(accountId);

        if (account == null) {
            return ResponseEntity.status(404).body(0);
        }

        return ResponseEntity.ok(account.getBalance());
    }

    public ResponseEntity<?> handleEvent(EventRequest request) {
    	
    	if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
    		return ResponseEntity.status(404).body(0);
    	}

    	if (request.getType() == null) {
    		return ResponseEntity.status(404).body(0);
    	}

    	if ("deposit".equals(request.getType())) {
    	    return deposit(request);
    	} else if ("withdraw".equals(request.getType())) {
    	    return withdraw(request);
    	} else if ("transfer".equals(request.getType())) {
    	    return transfer(request);
    	} else {
    	    return ResponseEntity.status(404).body(0);
    	}
    }

    private ResponseEntity<?> deposit(EventRequest req) {
        Account account = accounts.computeIfAbsent(req.getDestination(), Account::new);
        account.deposit(req.getAmount());

        Map<String, Object> destinationMap = new HashMap<>();
        destinationMap.put("id", account.getId());
        destinationMap.put("balance", account.getBalance().stripTrailingZeros());

        Map<String, Object> response = new HashMap<>();
        response.put("destination", destinationMap);

        return ResponseEntity.status(201).body(response);
    }

    private ResponseEntity<?> withdraw(EventRequest req) {
        Account account = accounts.get(req.getOrigin());

        if (account == null) {
            return ResponseEntity.status(404).body(0);
        }

        if (account.getBalance().compareTo(req.getAmount()) < 0) {
            return ResponseEntity.status(404).body(0);
        }

        account.withdraw(req.getAmount());

        Map<String, Object> originMap = new HashMap<>();
        originMap.put("id", account.getId());
        originMap.put("balance", account.getBalance().stripTrailingZeros());

        Map<String, Object> response = new HashMap<>();
        response.put("origin", originMap);

        return ResponseEntity.status(201).body(response);
    }

    private ResponseEntity<?> transfer(EventRequest req) {

        synchronized (this) {
            Account origin = accounts.get(req.getOrigin());

            if (origin == null) {
                return ResponseEntity.status(404).body(0);
            }

            if (origin.getBalance().compareTo(req.getAmount()) < 0) {
                return ResponseEntity.status(404).body(0);
            }

            Account destination = accounts.computeIfAbsent(req.getDestination(), Account::new);

            origin.withdraw(req.getAmount());
            destination.deposit(req.getAmount());

            Map<String, Object> originMap = new HashMap<>();
            originMap.put("id", origin.getId());
            originMap.put("balance", origin.getBalance().stripTrailingZeros());

            Map<String, Object> destinationMap = new HashMap<>();
            destinationMap.put("id", destination.getId());
            destinationMap.put("balance", destination.getBalance().stripTrailingZeros());

            Map<String, Object> response = new HashMap<>();
            response.put("origin", originMap);
            response.put("destination", destinationMap);

            return ResponseEntity.status(201).body(response);
        }
    }

}