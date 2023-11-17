package com.taskREST.rest_api.db;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "regions")
public class Regions {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "region_name", nullable = false)
    private String region_name;
    @Column(name = "cut_region_name", nullable = false)
    private String cut_region_name;
    public Regions(Long id, String regionName, String cutRegionName) {
        this.id = id;
        this.region_name = regionName;
        this.cut_region_name = cutRegionName;
    }
    public Regions() {
    }
}
