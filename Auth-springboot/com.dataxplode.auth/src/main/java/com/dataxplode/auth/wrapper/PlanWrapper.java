package com.dataxplode.auth.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanWrapper {
    private Long planId;
    private String name;
    private Integer searchLimit;
    private Double price;
    private String description;
    private String billingCycle;
    private LocalDateTime createdAt;
}
