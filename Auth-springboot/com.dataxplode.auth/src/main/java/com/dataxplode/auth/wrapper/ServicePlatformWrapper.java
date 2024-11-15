package com.dataxplode.auth.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicePlatformWrapper {

    private Integer serviceId;
    private String serviceName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updated_at;
}

