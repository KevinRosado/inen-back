package com.inen.inenapp.dto.attention;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Service {
    private String service_code;
    private String service_name;
    private BigDecimal service_price;
}
