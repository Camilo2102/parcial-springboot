package com.example.parcial.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class StockResponse {
    private String productId;
    private boolean status;
    private BigDecimal stock;
    private BigDecimal requiered;
}
