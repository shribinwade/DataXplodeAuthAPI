package com.dataxplode.auth.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsWrapper {
    private Integer reviewId;
    private String  reviewResult;

    // Add any other required fields if necessary
}