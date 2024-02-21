package com.example.parcial.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleModel extends AuditModel {
    private Date date;
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerModel customerModel;

    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> products;

}
