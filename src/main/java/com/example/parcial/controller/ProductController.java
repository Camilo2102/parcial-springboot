package com.example.parcial.controller;

import com.example.parcial.DTO.ProductDTO;
import com.example.parcial.exceptions.HttpException;
import com.example.parcial.models.ProductModel;
import com.example.parcial.responses.ResponseHandler;
import com.example.parcial.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getProductList() throws HttpException {
        List<ProductModel> customers = productService.getAllProducts   ();
        return ResponseHandler.generateResponse("Products found", HttpStatus.OK, customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) throws HttpException {
        ProductModel customer = productService.getProduct(id);
        return ResponseHandler.generateResponse("Product found", HttpStatus.OK, customer);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductModel product) throws HttpException {
        ProductModel productCreated = productService.createProduct(product);
        return ResponseHandler.generateResponse("Product created", HttpStatus.ACCEPTED, productCreated);
    }

    @PutMapping("/stock")
    public ResponseEntity<?> updateStock(@RequestBody ProductDTO productDTO) throws HttpException {
        ProductModel productModel = productService.updateStock(productDTO.getProductId(), productDTO.getAmount());
        return ResponseHandler.generateResponse("Stock updated", HttpStatus.ACCEPTED, productModel);
    }
}
