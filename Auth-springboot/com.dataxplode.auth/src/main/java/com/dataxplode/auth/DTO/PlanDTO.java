package com.dataxplode.auth.DTO;

import com.dataxplode.auth.Models.planModel.BillingCycle;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanDTO {
    private Long planId;
    private String planname;
    private Integer searchLimit;
    private double planprice;
    private String description;
    private BillingCycle billingCycle;
    private LocalDateTime createdAt;

}
