package com.example.parcial.repository;

import com.example.parcial.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, String> {
    List<SaleModel> findByCustomerModelId(String customerModel_id);
}
