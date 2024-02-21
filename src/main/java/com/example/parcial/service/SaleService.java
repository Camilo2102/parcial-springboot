package com.example.parcial.service;

import com.example.parcial.DTO.SaleDTO;
import com.example.parcial.exceptions.HttpException;
import com.example.parcial.models.CustomerModel;
import com.example.parcial.models.ProductModel;
import com.example.parcial.models.SaleModel;
import com.example.parcial.repository.CustomerRepository;
import com.example.parcial.repository.ProductRepository;
import com.example.parcial.repository.SaleRepository;
import com.example.parcial.responses.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public SaleModel registerSale(SaleDTO sale) throws HttpException {
        try {
            CustomerModel customer = customerService.getCustomer(sale.getCustomerId());
            List<ProductModel> productList = productService.getProductsByIds(sale.getProductList());
            productService.updateStocks(sale.getProductList());

            SaleModel saleToRegister = SaleModel.builder()
                    .date(new Date())
                    .customerModel(customer)
                    .products(productList)
                    .total(productService.calculateTotal(sale.getProductList()))
                    .build();
            return saleRepository.save(saleToRegister);
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not save the sale");
        }
    }

    public List<SaleModel> findSalesByCustomer(String customerId) throws HttpException {
        try {
            return saleRepository.findByCustomerModelId(customerId);
        }catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not get the sales");
        }
    }

}
