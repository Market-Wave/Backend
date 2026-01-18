package org.motionstack.marketwave.advertisementservice.config;

import org.motionstack.marketwave.advertisementservice.model.*;
import org.motionstack.marketwave.advertisementservice.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@Profile("with-dataloader") // Only active when this profile is enabled
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            VehicleBrandRepository brandRepository,
            VehicleModelRepository modelRepository,
            BodyTypeRepository bodyTypeRepository,
            AdCategoryRepository categoryRepository,
            StoreRepository storeRepository,
            StorePageConfigRepository storePageConfigRepository,
            StoreMediaRepository storeMediaRepository,
            VehicleAdRepository adRepository,
            VehicleMediaRepository vehicleMediaRepository,
            VehicleAdLikeRepository adLikeRepository
    ) {
        return args -> {
            // Clear existing data for clean initialization
            System.out.println("Loading sample data...");

            // 1. Load Vehicle Brands
            List<VehicleBrand> brands = new ArrayList<>();
            String[] brandNames = {"Toyota", "Honda", "BMW", "Mercedes-Benz", "Audi",
                                   "Ford", "Nissan", "Hyundai", "Volkswagen", "Tesla"};
            for (String name : brandNames) {
                VehicleBrand brand = new VehicleBrand();
                brand.setName(name);
                brands.add(brand);
            }
            brands = brandRepository.saveAll(brands);
            System.out.println("✓ Loaded " + brands.size() + " vehicle brands");

            // 2. Load Vehicle Models
            List<VehicleModel> models = new ArrayList<>();

            // Toyota models
            String[][] toyotaModels = {{"Corolla"}, {"Camry"}, {"RAV4"}, {"Prius"}, {"Highlander"}};
            for (String[] model : toyotaModels) {
                VehicleModel vm = new VehicleModel();
                vm.setBrandId(brands.get(0).getId());
                vm.setName(model[0]);
                models.add(vm);
            }

            // Honda models
            String[][] hondaModels = {{"Civic"}, {"Accord"}, {"CR-V"}, {"Pilot"}, {"Fit"}};
            for (String[] model : hondaModels) {
                VehicleModel vm = new VehicleModel();
                vm.setBrandId(brands.get(1).getId());
                vm.setName(model[0]);
                models.add(vm);
            }

            // BMW models
            String[][] bmwModels = {{"3 Series"}, {"5 Series"}, {"X5"}, {"X3"}, {"7 Series"}};
            for (String[] model : bmwModels) {
                VehicleModel vm = new VehicleModel();
                vm.setBrandId(brands.get(2).getId());
                vm.setName(model[0]);
                models.add(vm);
            }

            // Mercedes models
            String[][] mercedesModels = {{"C-Class"}, {"E-Class"}, {"GLC"}, {"S-Class"}, {"A-Class"}};
            for (String[] model : mercedesModels) {
                VehicleModel vm = new VehicleModel();
                vm.setBrandId(brands.get(3).getId());
                vm.setName(model[0]);
                models.add(vm);
            }

            // Tesla models
            String[][] teslaModels = {{"Model 3"}, {"Model S"}, {"Model X"}, {"Model Y"}};
            for (String[] model : teslaModels) {
                VehicleModel vm = new VehicleModel();
                vm.setBrandId(brands.get(9).getId());
                vm.setName(model[0]);
                models.add(vm);
            }

            models = modelRepository.saveAll(models);
            System.out.println("✓ Loaded " + models.size() + " vehicle models");

            // 3. Load Body Types
            List<BodyType> bodyTypes = new ArrayList<>();
            String[] bodyTypeNames = {"Sedan", "SUV", "Hatchback", "Coupe", "Convertible",
                                      "Wagon", "Pickup Truck", "Van", "Minivan", "Sports Car"};
            for (String name : bodyTypeNames) {
                BodyType bodyType = new BodyType();
                bodyType.setName(name);
                bodyTypes.add(bodyType);
            }
            bodyTypes = bodyTypeRepository.saveAll(bodyTypes);
            System.out.println("✓ Loaded " + bodyTypes.size() + " body types");

            // 4. Load Ad Categories
            List<AdCategory> categories = new ArrayList<>();
            String[] categoryNames = {"New Cars", "Used Cars", "Certified Pre-Owned",
                                      "Classic Cars", "Electric Vehicles", "Luxury Cars"};
            for (String name : categoryNames) {
                AdCategory category = new AdCategory();
                category.setName(name);
                categories.add(category);
            }
            categories = categoryRepository.saveAll(categories);
            System.out.println("✓ Loaded " + categories.size() + " ad categories");

            // 5. Create Sample Stores
            List<Store> stores = new ArrayList<>();

            // Sample owner IDs
            UUID owner1 = UUID.randomUUID();
            UUID owner2 = UUID.randomUUID();
            UUID owner3 = UUID.randomUUID();

            // Store 1: Premium Auto Gallery
            Store store1 = new Store();
            store1.setOwnerId(owner1);
            store1.setName("Premium Auto Gallery");
            store1.setSlug("premium-auto-gallery");
            store1.setDescription("Your trusted destination for luxury and premium vehicles. We offer the finest selection of high-end cars.");
            store1.setCountry("US");
            store1.setCity("Los Angeles");
            store1.setLatitude(new BigDecimal("34.052235"));
            store1.setLongitude(new BigDecimal("-118.243683"));
            store1.setAddress("123 Sunset Boulevard, Los Angeles, CA");
            store1.setStatus("ACTIVE");
            stores.add(store1);

            // Store 2: City Motors
            Store store2 = new Store();
            store2.setOwnerId(owner2);
            store2.setName("City Motors");
            store2.setSlug("city-motors");
            store2.setDescription("Affordable and reliable vehicles for everyday use. Family-owned business since 1995.");
            store2.setCountry("US");
            store2.setCity("New York");
            store2.setLatitude(new BigDecimal("40.712776"));
            store2.setLongitude(new BigDecimal("-74.005974"));
            store2.setAddress("456 Broadway, New York, NY");
            store2.setStatus("ACTIVE");
            stores.add(store2);

            // Store 3: Electric Dreams Auto
            Store store3 = new Store();
            store3.setOwnerId(owner3);
            store3.setName("Electric Dreams Auto");
            store3.setSlug("electric-dreams-auto");
            store3.setDescription("Specializing in electric and hybrid vehicles. Join the green revolution!");
            store3.setCountry("US");
            store3.setCity("San Francisco");
            store3.setLatitude(new BigDecimal("37.774929"));
            store3.setLongitude(new BigDecimal("-122.419418"));
            store3.setAddress("789 Market Street, San Francisco, CA");
            store3.setStatus("ACTIVE");
            stores.add(store3);

            stores = storeRepository.saveAll(stores);
            System.out.println("✓ Loaded " + stores.size() + " stores");

            // 6. Create Store Page Configs
            List<StorePageConfig> storeConfigs = new ArrayList<>();

            for (Store store : stores) {
                StorePageConfig config = new StorePageConfig();
                config.setStoreId(store.getId());
                config.setStore(store); // Set the Store relationship properly
                config.setMetaTitle(store.getName() + " - Quality Vehicles");
                config.setMetaDescription(store.getDescription());
                config.setThemeName("modern-auto");
                config.setThemeVersion("1.0.0");
                config.setColors("{\"primary\": \"#1a73e8\", \"secondary\": \"#34a853\", \"accent\": \"#fbbc04\"}");
                config.setButtons("{\"radius\": \"8px\", \"style\": \"solid\"}");
                config.setTypography("{\"headingFont\": \"Roboto\", \"bodyFont\": \"Open Sans\"}");
                config.setLayout("{\"header\": \"sticky\", \"footer\": \"default\"}");
                config.setPages("{\"home\": true, \"inventory\": true, \"about\": true, \"contact\": true}");
                config.setSections("[{\"type\": \"hero\", \"enabled\": true}, {\"type\": \"featured\", \"enabled\": true}]");
                storeConfigs.add(config);
            }

            storeConfigs = storePageConfigRepository.saveAll(storeConfigs);
            System.out.println("✓ Loaded " + storeConfigs.size() + " store page configs");

            // 7. Create Store Media
            List<StoreMedia> storeMediaList = new ArrayList<>();

            for (int i = 0; i < stores.size(); i++) {
                Store store = stores.get(i);

                // Logo
                StoreMedia logo = new StoreMedia();
                logo.setStoreId(store.getId());
                logo.setUrl("https://example.com/stores/" + store.getSlug() + "/logo.png");
                logo.setMediaType("IMAGE");
                logo.setMediaView("logo");
                logo.setSortOrder(1);
                storeMediaList.add(logo);

                // Banner
                StoreMedia banner = new StoreMedia();
                banner.setStoreId(store.getId());
                banner.setUrl("https://example.com/stores/" + store.getSlug() + "/banner.jpg");
                banner.setMediaType("IMAGE");
                banner.setMediaView("banner");
                banner.setSortOrder(2);
                storeMediaList.add(banner);

                // Gallery images
                for (int j = 1; j <= 3; j++) {
                    StoreMedia gallery = new StoreMedia();
                    gallery.setStoreId(store.getId());
                    gallery.setUrl("https://example.com/stores/" + store.getSlug() + "/gallery-" + j + ".jpg");
                    gallery.setMediaType("IMAGE");
                    gallery.setMediaView("gallery-" + j);
                    gallery.setSortOrder(2 + j);
                    storeMediaList.add(gallery);
                }
            }

            storeMediaList = storeMediaRepository.saveAll(storeMediaList);
            System.out.println("✓ Loaded " + storeMediaList.size() + " store media items");

            // 8. Create Vehicle Ads
            List<VehicleAd> ads = new ArrayList<>();

            // Ad 1: Toyota Camry
            VehicleAd ad1 = new VehicleAd();
            ad1.setOwnerId(owner1);
            ad1.setStoreId(stores.get(0).getId());
            ad1.setTitle("2023 Toyota Camry XLE - Low Mileage, Excellent Condition");
            ad1.setDescription("Beautiful 2023 Toyota Camry XLE with only 15,000 miles. One owner, never been in an accident. Features include leather seats, sunroof, navigation system, and advanced safety features.");
            ad1.setBrandId(brands.get(0).getId());
            ad1.setModelId(models.get(1).getId());
            ad1.setBodyTypeId(bodyTypes.get(0).getId());
            ad1.setCategoryId(categories.get(1).getId());
            ad1.setYear(2023);
            ad1.setMileage(15000);
            ad1.setFuelType("GASOLINE");
            ad1.setTransmission("AUTOMATIC");
            ad1.setCondition("Excellent");
            ad1.setPrice(new BigDecimal("28500.00"));
            ad1.setCurrency("USD");
            ad1.setCountry("US");
            ad1.setCity("Los Angeles");
            ad1.setLatitude(new BigDecimal("34.052235"));
            ad1.setLongitude(new BigDecimal("-118.243683"));
            ad1.setAddress("123 Sunset Boulevard, Los Angeles, CA");
            ad1.setStatus("ACTIVE");
            ad1.setIsBiddingEnabled(false);
            ad1.setIsPreorderEnabled(true);
            ads.add(ad1);

            // Ad 2: BMW X5
            VehicleAd ad2 = new VehicleAd();
            ad2.setOwnerId(owner1);
            ad2.setStoreId(stores.get(0).getId());
            ad2.setTitle("2022 BMW X5 M Sport - Luxury SUV");
            ad2.setDescription("Stunning BMW X5 with M Sport package. Loaded with premium features including panoramic roof, Harman Kardon sound system, adaptive cruise control, and much more.");
            ad2.setBrandId(brands.get(2).getId());
            ad2.setModelId(models.get(12).getId());
            ad2.setBodyTypeId(bodyTypes.get(1).getId());
            ad2.setCategoryId(categories.get(5).getId());
            ad2.setYear(2022);
            ad2.setMileage(22000);
            ad2.setFuelType("GASOLINE");
            ad2.setTransmission("AUTOMATIC");
            ad2.setCondition("Excellent");
            ad2.setPrice(new BigDecimal("65000.00"));
            ad2.setCurrency("USD");
            ad2.setCountry("US");
            ad2.setCity("Los Angeles");
            ad2.setLatitude(new BigDecimal("34.052235"));
            ad2.setLongitude(new BigDecimal("-118.243683"));
            ad2.setAddress("123 Sunset Boulevard, Los Angeles, CA");
            ad2.setStatus("ACTIVE");
            ad2.setIsBiddingEnabled(true);
            ad2.setIsPreorderEnabled(false);
            ads.add(ad2);

            // Ad 3: Honda Civic
            VehicleAd ad3 = new VehicleAd();
            ad3.setOwnerId(owner2);
            ad3.setStoreId(stores.get(1).getId());
            ad3.setTitle("2021 Honda Civic Sport - Great Fuel Economy");
            ad3.setDescription("Reliable and fuel-efficient Honda Civic Sport. Perfect for daily commuting. Well maintained with full service history.");
            ad3.setBrandId(brands.get(1).getId());
            ad3.setModelId(models.get(5).getId());
            ad3.setBodyTypeId(bodyTypes.get(0).getId());
            ad3.setCategoryId(categories.get(1).getId());
            ad3.setYear(2021);
            ad3.setMileage(35000);
            ad3.setFuelType("GASOLINE");
            ad3.setTransmission("MANUAL");
            ad3.setCondition("Good");
            ad3.setPrice(new BigDecimal("22000.00"));
            ad3.setCurrency("USD");
            ad3.setCountry("US");
            ad3.setCity("New York");
            ad3.setLatitude(new BigDecimal("40.712776"));
            ad3.setLongitude(new BigDecimal("-74.005974"));
            ad3.setAddress("456 Broadway, New York, NY");
            ad3.setStatus("ACTIVE");
            ad3.setIsBiddingEnabled(false);
            ad3.setIsPreorderEnabled(false);
            ads.add(ad3);

            // Ad 4: Tesla Model 3
            VehicleAd ad4 = new VehicleAd();
            ad4.setOwnerId(owner3);
            ad4.setStoreId(stores.get(2).getId());
            ad4.setTitle("2023 Tesla Model 3 Long Range - Autopilot");
            ad4.setDescription("Brand new Tesla Model 3 Long Range with Autopilot. Electric performance meets cutting-edge technology. Zero emissions, incredible acceleration.");
            ad4.setBrandId(brands.get(9).getId());
            ad4.setModelId(models.get(20).getId());
            ad4.setBodyTypeId(bodyTypes.get(0).getId());
            ad4.setCategoryId(categories.get(4).getId());
            ad4.setYear(2023);
            ad4.setMileage(5000);
            ad4.setFuelType("ELECTRIC");
            ad4.setTransmission("AUTOMATIC");
            ad4.setCondition("Excellent");
            ad4.setPrice(new BigDecimal("48000.00"));
            ad4.setCurrency("USD");
            ad4.setCountry("US");
            ad4.setCity("San Francisco");
            ad4.setLatitude(new BigDecimal("37.774929"));
            ad4.setLongitude(new BigDecimal("-122.419418"));
            ad4.setAddress("789 Market Street, San Francisco, CA");
            ad4.setStatus("ACTIVE");
            ad4.setIsBiddingEnabled(false);
            ad4.setIsPreorderEnabled(true);
            ads.add(ad4);

            // Ad 5: Mercedes E-Class
            VehicleAd ad5 = new VehicleAd();
            ad5.setOwnerId(owner1);
            ad5.setStoreId(stores.get(0).getId());
            ad5.setTitle("2022 Mercedes-Benz E-Class E300 - Premium Luxury");
            ad5.setDescription("Elegant Mercedes E-Class with premium package. Immaculate condition with all maintenance records. Features advanced driver assistance and luxurious interior.");
            ad5.setBrandId(brands.get(3).getId());
            ad5.setModelId(models.get(16).getId());
            ad5.setBodyTypeId(bodyTypes.get(0).getId());
            ad5.setCategoryId(categories.get(5).getId());
            ad5.setYear(2022);
            ad5.setMileage(18000);
            ad5.setFuelType("GASOLINE");
            ad5.setTransmission("AUTOMATIC");
            ad5.setCondition("Excellent");
            ad5.setPrice(new BigDecimal("58000.00"));
            ad5.setCurrency("USD");
            ad5.setCountry("US");
            ad5.setCity("Los Angeles");
            ad5.setLatitude(new BigDecimal("34.052235"));
            ad5.setLongitude(new BigDecimal("-118.243683"));
            ad5.setAddress("123 Sunset Boulevard, Los Angeles, CA");
            ad5.setStatus("ACTIVE");
            ad5.setIsBiddingEnabled(false);
            ad5.setIsPreorderEnabled(false);
            ads.add(ad5);

            ads = adRepository.saveAll(ads);
            System.out.println("✓ Loaded " + ads.size() + " vehicle ads");

            // 9. Create Vehicle Media
            List<VehicleMedia> vehicleMediaList = new ArrayList<>();

            for (VehicleAd ad : ads) {
                String adSlug = ad.getTitle().toLowerCase().replaceAll("[^a-z0-9]+", "-");

                // Front view
                VehicleMedia front = new VehicleMedia();
                front.setAdId(ad.getId());
                front.setUrl("https://example.com/vehicles/" + adSlug + "/front.jpg");
                front.setMediaType("IMAGE");
                front.setMediaView("front");
                front.setSortOrder(1);
                vehicleMediaList.add(front);

                // Back view
                VehicleMedia back = new VehicleMedia();
                back.setAdId(ad.getId());
                back.setUrl("https://example.com/vehicles/" + adSlug + "/back.jpg");
                back.setMediaType("IMAGE");
                back.setMediaView("back");
                back.setSortOrder(2);
                vehicleMediaList.add(back);

                // Side views
                VehicleMedia left = new VehicleMedia();
                left.setAdId(ad.getId());
                left.setUrl("https://example.com/vehicles/" + adSlug + "/left.jpg");
                left.setMediaType("IMAGE");
                left.setMediaView("left");
                left.setSortOrder(3);
                vehicleMediaList.add(left);

                VehicleMedia right = new VehicleMedia();
                right.setAdId(ad.getId());
                right.setUrl("https://example.com/vehicles/" + adSlug + "/right.jpg");
                right.setMediaType("IMAGE");
                right.setMediaView("right");
                right.setSortOrder(4);
                vehicleMediaList.add(right);

                // Interior views
                VehicleMedia interiorFront = new VehicleMedia();
                interiorFront.setAdId(ad.getId());
                interiorFront.setUrl("https://example.com/vehicles/" + adSlug + "/interior-front.jpg");
                interiorFront.setMediaType("IMAGE");
                interiorFront.setMediaView("interior-front");
                interiorFront.setSortOrder(5);
                vehicleMediaList.add(interiorFront);
            }

            vehicleMediaList = vehicleMediaRepository.saveAll(vehicleMediaList);
            System.out.println("✓ Loaded " + vehicleMediaList.size() + " vehicle media items");

            // 10. Create Vehicle Ad Likes
            List<VehicleAdLike> likes = new ArrayList<>();

            // Some sample user IDs who liked the ads
            UUID user1 = UUID.randomUUID();
            UUID user2 = UUID.randomUUID();
            UUID user3 = UUID.randomUUID();

            // User 1 likes ad 1 and ad 4
            VehicleAdLike like1 = new VehicleAdLike();
            like1.setUserId(user1);
            like1.setAdId(ads.get(0).getId());
            likes.add(like1);

            VehicleAdLike like2 = new VehicleAdLike();
            like2.setUserId(user1);
            like2.setAdId(ads.get(3).getId());
            likes.add(like2);

            // User 2 likes ad 2 and ad 5
            VehicleAdLike like3 = new VehicleAdLike();
            like3.setUserId(user2);
            like3.setAdId(ads.get(1).getId());
            likes.add(like3);

            VehicleAdLike like4 = new VehicleAdLike();
            like4.setUserId(user2);
            like4.setAdId(ads.get(4).getId());
            likes.add(like4);

            // User 3 likes ad 3
            VehicleAdLike like5 = new VehicleAdLike();
            like5.setUserId(user3);
            like5.setAdId(ads.get(2).getId());
            likes.add(like5);

            likes = adLikeRepository.saveAll(likes);
            System.out.println("✓ Loaded " + likes.size() + " vehicle ad likes");

            System.out.println("\n========================================");
            System.out.println("✓ Sample data loaded successfully!");
            System.out.println("========================================");
            System.out.println("Summary:");
            System.out.println("  - " + brands.size() + " Vehicle Brands");
            System.out.println("  - " + models.size() + " Vehicle Models");
            System.out.println("  - " + bodyTypes.size() + " Body Types");
            System.out.println("  - " + categories.size() + " Ad Categories");
            System.out.println("  - " + stores.size() + " Stores");
            System.out.println("  - " + storeConfigs.size() + " Store Configs");
            System.out.println("  - " + storeMediaList.size() + " Store Media");
            System.out.println("  - " + ads.size() + " Vehicle Ads");
            System.out.println("  - " + vehicleMediaList.size() + " Vehicle Media");
            System.out.println("  - " + likes.size() + " Ad Likes");
            System.out.println("========================================");
            System.out.println("\nH2 Console is available at: http://localhost:8080/h2-console");
            System.out.println("JDBC URL: jdbc:h2:mem:advertisementdb");
            System.out.println("Username: sa");
            System.out.println("Password: (leave empty)");
            System.out.println("========================================\n");
        };
    }
}