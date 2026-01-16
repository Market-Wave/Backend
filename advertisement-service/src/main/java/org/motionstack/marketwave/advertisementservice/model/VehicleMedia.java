package org.motionstack.marketwave.advertisementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vehicle_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long adId;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(nullable = false)
    private String mediaType;

    @Column(nullable = false)
    private String mediaView;

    @Column(nullable = false)
    private Integer sortOrder;
}