package com.dataxplode.auth.wrapper.ServicesWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitorAnalysisDataWrapper {
    private Integer competitorAnalysisId;
    private String searchQuery;
    private String brand;
    private String features;
    private String searchResults;
    private String createdAt;
}
