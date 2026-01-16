package org.motionstack.marketwave.advertisementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "store_page_configs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorePageConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", insertable = false, updatable = false)
    private Store store;

    @Column(length = 200)
    private String metaTitle;

    @Column(length = 500)
    private String metaDescription;

    @Column(length = 100)
    private String themeName;

    @Column(length = 50)
    private String themeVersion;

    @Column(length = 1000)
    private String colors;

    @Column(length = 500)
    private String buttons;

    @Column(length = 1000)
    private String typography;

    @Column(length = 500)
    private String layout;

    @Column(length = 1000)
    private String pages;

    @Column(length = 1000)
    private String sections;
}