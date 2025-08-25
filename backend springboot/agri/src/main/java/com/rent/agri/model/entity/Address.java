package com.rent.agri.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String landmark;

    @Column(nullable = false)
    private String village;

    @Column(nullable = false)
    private String pincode;

    @Column(nullable = false)
    private String mandal;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String state;
}