package com.dataxplode.auth.wrapper;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class userSearchDataWrapper {
    private Long userId;
    private SearchData searchData;


    // Custom constructor for JPQL
    public userSearchDataWrapper(Long userId,
                                 String country,
                                 String platform,
                                 Long competitiorsAnalysisCount,
                                 Long competitiveStratergyCount,
                                 Long distributorSearchCount,
                                 Long keywordQueryCount,
                                 Long marketSearchCount,
                                 Long productSearchCount
                             ) {
        this.userId = userId;
        this.searchData = new SearchData(
                country,
                platform,
                competitiorsAnalysisCount,
                competitiveStratergyCount,
                distributorSearchCount,
                keywordQueryCount,
                marketSearchCount,
                productSearchCount
        );
    }

}
