package com.dataxplode.auth.wrapper.ServicesWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Wrapper class for ProductSearchData
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDataWrapper {
    private Integer productSearchId;
    private String searchQuery;
    private String searchResults;
    private String createdAt;
}
