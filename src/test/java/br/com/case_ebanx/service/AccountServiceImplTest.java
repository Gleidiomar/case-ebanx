package br.com.case_ebanx.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import br.com.case_ebanx.dto.EventRequest;
import br.com.case_ebanx.service.impl.AccountServiceImpl;

class AccountServiceImplTest {

    private AccountServiceImpl service;

    @BeforeEach
    void setup() {
        service = new AccountServiceImpl();
    }
    
    @Test
    void shouldResetState() {
        EventRequest deposit = new EventRequest();
        deposit.setType("deposit");
        deposit.setDestination("100");
        deposit.setAmount(BigDecimal.valueOf(10));

        service.handleEvent(deposit);

        service.reset();

        ResponseEntity<?> response = service.getBalance("100");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void shouldDepositAndCreateAccount() {
        EventRequest req = new EventRequest();
        req.setType("deposit");
        req.setDestination("100");
        req.setAmount(BigDecimal.valueOf(10));

        ResponseEntity<?> response = service.handleEvent(req);

        assertEquals(201, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        Map<?, ?> destination = (Map<?, ?>) body.get("destination");

        assertEquals("100", destination.get("id"));
        assertTrue(new BigDecimal("10")
                .compareTo((BigDecimal) destination.get("balance")) == 0);
    }

    @Test
    void shouldWithdrawSuccessfully() {
        // primeiro deposita
        EventRequest deposit = new EventRequest();
        deposit.setType("deposit");
        deposit.setDestination("100");
        deposit.setAmount(BigDecimal.valueOf(10));
        service.handleEvent(deposit);

        // depois saca
        EventRequest withdraw = new EventRequest();
        withdraw.setType("withdraw");
        withdraw.setOrigin("100");
        withdraw.setAmount(BigDecimal.valueOf(5));

        ResponseEntity<?> response = service.handleEvent(withdraw);

        assertEquals(201, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        Map<?, ?> origin = (Map<?, ?>) body.get("origin");

        assertEquals(new BigDecimal("5"), origin.get("balance"));
    }

    @Test
    void shouldFailWithdrawWhenInsufficientBalance() {
        EventRequest withdraw = new EventRequest();
        withdraw.setType("withdraw");
        withdraw.setOrigin("100");
        withdraw.setAmount(BigDecimal.valueOf(10));

        ResponseEntity<?> response = service.handleEvent(withdraw);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(0, response.getBody());
    }

    @Test
    void shouldTransferSuccessfully() {
        // cria conta origem
        EventRequest deposit = new EventRequest();
        deposit.setType("deposit");
        deposit.setDestination("100");
        deposit.setAmount(BigDecimal.valueOf(20));
        service.handleEvent(deposit);

        // transfere
        EventRequest transfer = new EventRequest();
        transfer.setType("transfer");
        transfer.setOrigin("100");
        transfer.setDestination("200");
        transfer.setAmount(BigDecimal.valueOf(10));

        ResponseEntity<?> response = service.handleEvent(transfer);

        assertEquals(201, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();

        Map<?, ?> origin = (Map<?, ?>) body.get("origin");
        Map<?, ?> destination = (Map<?, ?>) body.get("destination");

        assertEquals(new BigDecimal("10"), origin.get("balance"));
        assertEquals(new BigDecimal("10"), destination.get("balance"));
    }

    @Test
    void shouldFailTransferWhenNoBalance() {
        EventRequest transfer = new EventRequest();
        transfer.setType("transfer");
        transfer.setOrigin("100");
        transfer.setDestination("200");
        transfer.setAmount(BigDecimal.valueOf(10));

        ResponseEntity<?> response = service.handleEvent(transfer);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(0, response.getBody());
    }

    @Test
    void shouldReturn404WhenAccountNotFound() {
        ResponseEntity<?> response = service.getBalance("999");

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(0, response.getBody());
    }

    @Test
    void shouldReturnBalanceWhenAccountExists() {
        EventRequest deposit = new EventRequest();
        deposit.setType("deposit");
        deposit.setDestination("100");
        deposit.setAmount(BigDecimal.valueOf(50));
        service.handleEvent(deposit);

        ResponseEntity<?> response = service.getBalance("100");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(new BigDecimal("50"), response.getBody());
    }
}