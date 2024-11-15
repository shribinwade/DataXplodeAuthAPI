package com.dataxplode.auth.Models.platformsModel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private int platformId;

    @Column(name = "platform_name", nullable = false)
    private String platformName;

    // Getters and Setters
}
