package com.dataxplode.auth.wrapper.ServicesWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Wrapper class for DistributorSearchData
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributorSearchDataWrapper {
    private Integer distributorSearchId;
    private String searchQuery;
    private String searchResults;
    private String createdAt;
}
