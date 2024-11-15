package com.dataxplode.auth.wrapper.ServicesWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitiveStrategyDataWrapper {
    private Integer competitiveStrategyId;
    private String searchQuery;
    private String searchResults;
    private String createdAt;
}
