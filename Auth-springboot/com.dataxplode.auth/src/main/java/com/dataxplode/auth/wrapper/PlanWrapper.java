package com.dataxplode.auth.wrapper;

import com.dataxplode.auth.Models.planModel.BillingCycle;
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
    private BillingCycle billingCycle =BillingCycle.FREE;
    private LocalDateTime createdAt;
}
