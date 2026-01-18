package org.motionstack.marketwave.advertisementservice.dto;

import org.motionstack.marketwave.advertisementservice.model.*;
import org.motionstack.marketwave.advertisementservice.repository.VehicleBrandRepository;
import org.motionstack.marketwave.advertisementservice.repository.VehicleModelRepository;
import org.motionstack.marketwave.advertisementservice.repository.BodyTypeRepository;
import org.motionstack.marketwave.advertisementservice.repository.AdCategoryRepository;
import org.motionstack.marketwave.advertisementservice.repository.StoreRepository;
import org.springframework.stereotype.Component;

/**
 * Utility class for mapping between entities and DTOs.
 */
@Component
public class DtoMapper {

    private final VehicleBrandRepository vehicleBrandRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final BodyTypeRepository bodyTypeRepository;
    private final AdCategoryRepository adCategoryRepository;
    private final StoreRepository storeRepository;

    public DtoMapper(VehicleBrandRepository vehicleBrandRepository,
                    VehicleModelRepository vehicleModelRepository,
                    BodyTypeRepository bodyTypeRepository,
                    AdCategoryRepository adCategoryRepository,
                    StoreRepository storeRepository) {
        this.vehicleBrandRepository = vehicleBrandRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.bodyTypeRepository = bodyTypeRepository;
        this.adCategoryRepository = adCategoryRepository;
        this.storeRepository = storeRepository;
    }

    // VehicleAd mappings
    public VehicleAd toEntity(VehicleAdRequest request) {
        VehicleAd entity = new VehicleAd();
        entity.setOwnerId(request.getOwnerId());
        entity.setStoreId(request.getStoreId());
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setBrandId(request.getBrandId());
        entity.setModelId(request.getModelId());
        entity.setBodyTypeId(request.getBodyTypeId());
        entity.setCategoryId(request.getCategoryId());
        entity.setYear(request.getYear());
        entity.setMileage(request.getMileage());
        entity.setFuelType(request.getFuelType());
        entity.setTransmission(request.getTransmission());
        entity.setCondition(request.getCondition());
        entity.setPrice(request.getPrice());
        entity.setCurrency(request.getCurrency());
        entity.setCountry(request.getCountry());
        entity.setCity(request.getCity());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setAddress(request.getAddress());
        entity.setStatus(request.getStatus());
        entity.setIsBiddingEnabled(request.getIsBiddingEnabled());
        entity.setIsPreorderEnabled(request.getIsPreorderEnabled());
        return entity;
    }

    public VehicleAdResponse toResponse(VehicleAd entity) {
        VehicleAdResponse response = new VehicleAdResponse();
        response.setId(entity.getId());
        response.setOwnerId(entity.getOwnerId());
        response.setStoreId(entity.getStoreId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setBrandId(entity.getBrandId());
        response.setModelId(entity.getModelId());
        response.setBodyTypeId(entity.getBodyTypeId());
        response.setCategoryId(entity.getCategoryId());
        
        // Fetch and set nested objects
        if (entity.getBrandId() != null) {
            vehicleBrandRepository.findById(entity.getBrandId())
                .ifPresent(brand -> response.setBrand(toResponse(brand)));
        }
        if (entity.getModelId() != null) {
            vehicleModelRepository.findById(entity.getModelId())
                .ifPresent(model -> response.setModel(toResponse(model)));
        }
        if (entity.getBodyTypeId() != null) {
            bodyTypeRepository.findById(entity.getBodyTypeId())
                .ifPresent(bodyType -> response.setBodyType(toResponse(bodyType)));
        }
        if (entity.getCategoryId() != null) {
            adCategoryRepository.findById(entity.getCategoryId())
                .ifPresent(category -> response.setCategory(toResponse(category)));
        }
        if (entity.getStoreId() != null) {
            storeRepository.findById(entity.getStoreId())
                .ifPresent(store -> response.setStore(toResponse(store)));
        }
        
        response.setYear(entity.getYear());
        response.setMileage(entity.getMileage());
        response.setFuelType(entity.getFuelType());
        response.setTransmission(entity.getTransmission());
        response.setCondition(entity.getCondition());
        response.setPrice(entity.getPrice());
        response.setCurrency(entity.getCurrency());
        response.setCountry(entity.getCountry());
        response.setCity(entity.getCity());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        response.setAddress(entity.getAddress());
        response.setStatus(entity.getStatus());
        response.setIsBiddingEnabled(entity.getIsBiddingEnabled());
        response.setIsPreorderEnabled(entity.getIsPreorderEnabled());
        return response;
    }

    // VehicleBrand mappings
    public VehicleBrand toEntity(VehicleBrandRequest request) {
        VehicleBrand entity = new VehicleBrand();
        entity.setName(request.getName());
        return entity;
    }

    public VehicleBrandResponse toResponse(VehicleBrand entity) {
        VehicleBrandResponse response = new VehicleBrandResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }

    // VehicleModel mappings
    public VehicleModel toEntity(VehicleModelRequest request) {
        VehicleModel entity = new VehicleModel();
        entity.setBrandId(request.getBrandId());
        entity.setName(request.getName());
        return entity;
    }

    public VehicleModelResponse toResponse(VehicleModel entity) {
        VehicleModelResponse response = new VehicleModelResponse();
        response.setId(entity.getId());
        response.setBrandId(entity.getBrandId());
        response.setName(entity.getName());
        return response;
    }

    // BodyType mappings
    public BodyType toEntity(BodyTypeRequest request) {
        BodyType entity = new BodyType();
        entity.setName(request.getName());
        return entity;
    }

    public BodyTypeResponse toResponse(BodyType entity) {
        BodyTypeResponse response = new BodyTypeResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }

    // AdCategory mappings
    public AdCategory toEntity(AdCategoryRequest request) {
        AdCategory entity = new AdCategory();
        entity.setName(request.getName());
        return entity;
    }

    public AdCategoryResponse toResponse(AdCategory entity) {
        AdCategoryResponse response = new AdCategoryResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }

    // Store mappings
    public Store toEntity(StoreRequest request) {
        Store entity = new Store();
        entity.setOwnerId(request.getOwnerId());
        entity.setName(request.getName());
        entity.setSlug(request.getSlug());
        entity.setDescription(request.getDescription());
        entity.setCountry(request.getCountry());
        entity.setCity(request.getCity());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setAddress(request.getAddress());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public StoreResponse toResponse(Store entity) {
        StoreResponse response = new StoreResponse();
        response.setId(entity.getId());
        response.setOwnerId(entity.getOwnerId());
        response.setName(entity.getName());
        response.setSlug(entity.getSlug());
        response.setDescription(entity.getDescription());
        response.setCountry(entity.getCountry());
        response.setCity(entity.getCity());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        response.setAddress(entity.getAddress());
        response.setStatus(entity.getStatus());
        return response;
    }

    // VehicleMedia mappings
    public VehicleMedia toEntity(VehicleMediaRequest request) {
        VehicleMedia entity = new VehicleMedia();
        entity.setAdId(request.getAdId());
        entity.setUrl(request.getUrl());
        entity.setMediaType(request.getMediaType());
        entity.setMediaView(request.getMediaView());
        entity.setSortOrder(request.getSortOrder());
        return entity;
    }

    public VehicleMediaResponse toResponse(VehicleMedia entity) {
        VehicleMediaResponse response = new VehicleMediaResponse();
        response.setId(entity.getId());
        response.setAdId(entity.getAdId());
        response.setUrl(entity.getUrl());
        response.setMediaType(entity.getMediaType());
        response.setMediaView(entity.getMediaView());
        response.setSortOrder(entity.getSortOrder());
        return response;
    }

    // StoreMedia mappings
    public StoreMedia toEntity(StoreMediaRequest request) {
        StoreMedia entity = new StoreMedia();
        entity.setStoreId(request.getStoreId());
        entity.setUrl(request.getUrl());
        entity.setMediaType(request.getMediaType());
        entity.setMediaView(request.getMediaView());
        entity.setSortOrder(request.getSortOrder());
        return entity;
    }

    public StoreMediaResponse toResponse(StoreMedia entity) {
        StoreMediaResponse response = new StoreMediaResponse();
        response.setId(entity.getId());
        response.setStoreId(entity.getStoreId());
        response.setUrl(entity.getUrl());
        response.setMediaType(entity.getMediaType());
        response.setMediaView(entity.getMediaView());
        response.setSortOrder(entity.getSortOrder());
        return response;
    }

    // StorePageConfig mappings
    public StorePageConfig toEntity(StorePageConfigRequest request) {
        StorePageConfig entity = new StorePageConfig();
        entity.setStoreId(request.getStoreId());
        entity.setMetaTitle(request.getMetaTitle());
        entity.setMetaDescription(request.getMetaDescription());
        entity.setThemeName(request.getThemeName());
        entity.setThemeVersion(request.getThemeVersion());
        entity.setColors(request.getColors());
        entity.setButtons(request.getButtons());
        entity.setTypography(request.getTypography());
        entity.setLayout(request.getLayout());
        entity.setPages(request.getPages());
        entity.setSections(request.getSections());
        return entity;
    }

    public StorePageConfigResponse toResponse(StorePageConfig entity) {
        StorePageConfigResponse response = new StorePageConfigResponse();
        response.setId(entity.getId());
        response.setStoreId(entity.getStoreId());
        response.setMetaTitle(entity.getMetaTitle());
        response.setMetaDescription(entity.getMetaDescription());
        response.setThemeName(entity.getThemeName());
        response.setThemeVersion(entity.getThemeVersion());
        response.setColors(entity.getColors());
        response.setButtons(entity.getButtons());
        response.setTypography(entity.getTypography());
        response.setLayout(entity.getLayout());
        response.setPages(entity.getPages());
        response.setSections(entity.getSections());
        return response;
    }
}
