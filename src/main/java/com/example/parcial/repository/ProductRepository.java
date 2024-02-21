package com.example.parcial.repository;

import com.example.parcial.models.CustomerModel;
import com.example.parcial.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {
}
