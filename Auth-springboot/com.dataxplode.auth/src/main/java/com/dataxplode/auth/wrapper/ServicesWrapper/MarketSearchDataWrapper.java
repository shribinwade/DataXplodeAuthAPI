package com.dataxplode.auth.wrapper.ServicesWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketSearchDataWrapper {
    private Integer marketSearchId;
    private String searchQuery;
    private String searchResults;
    private String createdAt;
}