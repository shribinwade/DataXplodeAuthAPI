package com.dataxplode.auth.Models.ReviewsModel;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reviews")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Type(type = "json")
    @Column(name = "review_result", columnDefinition = "JSON", nullable = false)
    private String reviewResult;

    // Getters and Setters
}