package br.com.case_ebanx.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class EventRequest {

    private String type;
    private String origin;
    private String destination;
    private BigDecimal amount;
}
