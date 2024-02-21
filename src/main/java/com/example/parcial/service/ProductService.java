package com.example.parcial.service;

import com.example.parcial.DTO.ProductDTO;
import com.example.parcial.exceptions.HttpException;
import com.example.parcial.models.ProductModel;
import com.example.parcial.repository.ProductRepository;
import com.example.parcial.responses.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() throws HttpException {
        try {
            return productRepository.findAll();
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not get the products");
        }
    }

    public ProductModel getProduct(String id) throws HttpException {
        return this.getProductById(id);
    }

    public BigDecimal calculateTotal(List<ProductDTO> productList){
        return productList.stream()
                .map(ProductDTO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ProductModel> getProductsByIds(List<ProductDTO> products) throws HttpException {
        List<String> productIds = products.stream()
                .map(ProductDTO::getProductId)
                .collect(Collectors.toList());

        List<ProductModel> foundProductsList = productRepository.findAllById(productIds);

        for (ProductModel productFound : foundProductsList) {
            ProductDTO productF = products.stream()
                    .filter(product -> product.getProductId().equals(productFound.getId()))
                    .findFirst()
                    .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Product " + productFound.getId() + " not found in DTOs"));

            if (productF.getAmount().compareTo(productFound.getStock()) > 0) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Product " + productF.getProductId() + " without enough stock");
            }
        }

        if(productIds.size() != foundProductsList.size()){
            throw new HttpException(HttpStatus.NOT_FOUND, "Some products not found");
        }

        return foundProductsList;
    }

    public void updateStocks(List<ProductDTO> products) throws HttpException {
        for (ProductDTO product : products) {
            this.updateStock(product.getProductId(), product.getAmount());
        }
    }

    public ProductModel createProduct(ProductModel product) throws HttpException {
        try {
            return this.productRepository.save(product);
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not get the customers");
        }
    }

    private ProductModel getProductById(String id) throws HttpException {
        Optional<ProductModel> productFound = productRepository.findById(id);

        if(productFound.isEmpty()) throw new HttpException(HttpStatus.NOT_FOUND, "Product not found");

        return productFound.get();
    }

    public ProductModel updateStock(String productId, BigDecimal stock) throws HttpException {
        ProductModel product = getProductById(productId);

        product.setStock(stock);

        return productRepository.save(product);
    }

    public StockResponse validateProductStock(String id, BigDecimal amount) throws HttpException {
        ProductModel product = getProductById(id);

        boolean validAmount = product.getStock().compareTo(amount) >= 0;

        return StockResponse.builder()
                .stock(product.getStock())
                .productId(product.getId())
                .status(validAmount)
                .requiered(amount)
                .build();


    }
}
