package com.dataxplode.auth.Models.pincodeModel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pincode")
@Data
public class Pincode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pincode_id")
    private int pincodeId;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    // Getters and Setters
}
