package com.dataxplode.auth.wrapper;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SearchData {
    private String country;
    private String platform;
    private Long competitiorsAnalysisCount;
    private Long competitiveStratergyCount;
    private Long distributorSearchCount;
    private Long keywordQueryCount;
    private Long marketSearchCount;
    private Long productSearchCount;
}
