package com.dataxplode.auth.Models.PlanFeatureCommonModel;

import com.dataxplode.auth.Models.FeatureModel.Features;
import com.dataxplode.auth.Models.planModel.Plan;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.apache.fontbox.ttf.table.common.FeatureTable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Plan_Feature_table")
@Data
public class PlanFeatureTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonBackReference
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    @JsonBackReference
    private Features feature;

    @Column(nullable = false)
    private LocalDate createdAt;
}
