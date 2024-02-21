package com.example.parcial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductModel extends AuditModel {
    private BigDecimal stock;
    private String name;
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<SaleModel> sales;
}
