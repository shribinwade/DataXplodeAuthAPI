package com.dataxplode.auth.Models.platformsModel;

import lombok.Data;

import javax.persistence.*;

@NamedQuery(name = "platform.findByPlatformName", query = "select u from Platform u where u.platformName=:platformName")
@NamedQuery(name = "Platform.getAllPlatforms", query = "select new com.dataxplode.auth.wrapper.PlatformWrapper(p.platformId,p.platformName) from Platform p")

@Entity
@Data
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "platform_name", nullable = false)
    private String platformName;

    // Getters and Setters
}
