package org.motionstack.marketwave.advertisementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "vehicle_ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID ownerId;

    private Long storeId;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(length = 2000)
    private String description;

    private Long brandId;
    private Long modelId;
    private Long bodyTypeId;
    private Long categoryId;

    @Column(name = "`year`", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String condition;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @Column(length = 500)
    private String address;

    @Column(nullable = false)
    private String status;

    private Boolean isBiddingEnabled;
    private Boolean isPreorderEnabled;
}