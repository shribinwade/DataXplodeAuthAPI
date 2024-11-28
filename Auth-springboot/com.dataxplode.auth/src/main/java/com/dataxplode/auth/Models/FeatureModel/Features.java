package com.dataxplode.auth.Models.FeatureModel;

import com.dataxplode.auth.Models.FeatureContentModel.FeatureContentModel;
import com.dataxplode.auth.Models.PlanFeatureCommonModel.PlanFeatureTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedQuery(name="Features.findByFeature_name", query = "select u from Features u where u.featureName=:feature_name")

@Entity
@Table(name = "Feature")
@Data
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long featureId;

    @Column(nullable = false)
    private String featureName;

    @Column(nullable = false)
    private String featureDescription;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "feature")
    private List<FeatureContentModel> content;

    @OneToMany(mappedBy = "feature")
    @JsonBackReference
    private List<PlanFeatureTable> plans;


}
