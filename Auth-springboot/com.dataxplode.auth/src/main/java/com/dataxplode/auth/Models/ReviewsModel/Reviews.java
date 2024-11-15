package com.dataxplode.auth.Models.ReviewsModel;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "review_result", columnDefinition = "JSON", nullable = false)
    private String reviewResult;

    // Getters and Setters
}