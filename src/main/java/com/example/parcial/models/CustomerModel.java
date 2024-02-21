package com.example.parcial.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerModel extends AuditModel{
    @Column(length = 80)
    private String name;
    @Column(length = 50)
    private String address;
    @Column(length = 10)
    private String phone;
}
