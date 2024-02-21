package com.example.parcial.controller;

import com.example.parcial.DTO.ProductDTO;
import com.example.parcial.DTO.SaleDTO;
import com.example.parcial.exceptions.HttpException;
import com.example.parcial.models.CustomerModel;
import com.example.parcial.models.SaleModel;
import com.example.parcial.responses.ResponseHandler;
import com.example.parcial.responses.StockResponse;
import com.example.parcial.service.ProductService;
import com.example.parcial.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    private final ProductService productService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> findByCustomer(@PathVariable String customerId) throws HttpException {
        List<SaleModel> sales = saleService.findSalesByCustomer(customerId);
        return ResponseHandler.generateResponse("Sales found by id " + customerId, HttpStatus.OK, sales);
    }

    @PostMapping("/registerSale")
    public ResponseEntity<?> createSale(@RequestBody SaleDTO saleModel) throws HttpException {
        SaleModel sale = saleService.registerSale(saleModel);

        return ResponseHandler.generateResponse("Sales created", HttpStatus.OK, sale);
    }

    @GetMapping("/stock/{productId}/{amount}")
    public ResponseEntity<?> findStockByProductId(@PathVariable String productId, @PathVariable BigDecimal amount) throws HttpException {
        StockResponse stock = productService.validateProductStock(productId, amount);
        return ResponseHandler.generateResponse("Stock found for product id " + productId, HttpStatus.OK, stock);
    }


}
