package com.dataxplode.auth.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSearchHistoryDTO {
    private Long id;
    private String keywordQuery;
    private String competitiveStratergyQuery;
    private String marketSearchQuery;
    private LocalDate createdAt;
    private String countryName;
    private String featureName;
    private String platformName;


}
