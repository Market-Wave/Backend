-- Insert dummy data for City Motors store and related entities

-- Insert brands
INSERT INTO vehicle_brands (name) VALUES ('Toyota');
INSERT INTO vehicle_brands (name) VALUES ('Honda');
INSERT INTO vehicle_brands (name) VALUES ('Ford');
INSERT INTO vehicle_brands (name) VALUES ('Chevrolet');
INSERT INTO vehicle_brands (name) VALUES ('BMW');

-- Insert models
INSERT INTO vehicle_models (brand_id, name) VALUES (1, 'Corolla');
INSERT INTO vehicle_models (brand_id, name) VALUES (1, 'Camry');
INSERT INTO vehicle_models (brand_id, name) VALUES (1, 'RAV4');
INSERT INTO vehicle_models (brand_id, name) VALUES (2, 'Civic');
INSERT INTO vehicle_models (brand_id, name) VALUES (2, 'Accord');
INSERT INTO vehicle_models (brand_id, name) VALUES (2, 'CR-V');
INSERT INTO vehicle_models (brand_id, name) VALUES (3, 'F-150');
INSERT INTO vehicle_models (brand_id, name) VALUES (3, 'Explorer');
INSERT INTO vehicle_models (brand_id, name) VALUES (3, 'Mustang');
INSERT INTO vehicle_models (brand_id, name) VALUES (4, 'Silverado');
INSERT INTO vehicle_models (brand_id, name) VALUES (4, 'Equinox');
INSERT INTO vehicle_models (brand_id, name) VALUES (5, '3 Series');
INSERT INTO vehicle_models (brand_id, name) VALUES (5, '5 Series');
INSERT INTO vehicle_models (brand_id, name) VALUES (5, 'X3');

-- Insert body types
INSERT INTO body_types (name) VALUES ('Sedan');
INSERT INTO body_types (name) VALUES ('SUV');
INSERT INTO body_types (name) VALUES ('Truck');
INSERT INTO body_types (name) VALUES ('Hatchback');
INSERT INTO body_types (name) VALUES ('Coupe');
INSERT INTO body_types (name) VALUES ('Convertible');

-- Insert categories
INSERT INTO ad_categories (name) VALUES ('Cars');
INSERT INTO ad_categories (name) VALUES ('SUVs');
INSERT INTO ad_categories (name) VALUES ('Trucks');
INSERT INTO ad_categories (name) VALUES ('Luxury');
INSERT INTO ad_categories (name) VALUES ('Economy');

-- Insert City Motors store
INSERT INTO stores (owner_id, name, slug, description, country, city, latitude, longitude, address, status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'City Motors', 'city-motors',
'Affordable and reliable vehicles for everyday use. Family-owned business since 1995.',
'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE');

-- Insert vehicle ads for City Motors
INSERT INTO vehicle_ads (owner_id, store_id, title, description, brand_id, model_id, body_type_id, category_id, vehicle_year, mileage, fuel_type, transmission, condition, price, currency, country, city, latitude, longitude, address, status)
VALUES
('550e8400-e29b-41d4-a716-446655440000', 1, '2020 Toyota Corolla LE - Excellent Condition', 'Well-maintained sedan with low mileage. Single owner, clean CarFax. Perfect for daily commuting.', 1, 1, 1, 1, 2020, 35000, 'Gasoline', 'Automatic', 'Excellent', 18500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2019 Honda Civic EX - Low Mileage', 'Sporty and fuel-efficient compact car. Features include backup camera, Bluetooth, and alloy wheels.', 2, 4, 1, 1, 2019, 28000, 'Gasoline', 'Manual', 'Good', 16800.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2021 Toyota RAV4 Hybrid - One Owner', 'Fuel-efficient SUV perfect for city and highway driving. Loaded with safety features and modern tech.', 1, 3, 2, 2, 2021, 22000, 'Hybrid', 'Automatic', 'Excellent', 28500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2018 Ford F-150 XL - Work Truck', 'Reliable pickup truck with plenty of towing capacity. Great for work or recreational use.', 3, 7, 3, 3, 2018, 45000, 'Gasoline', 'Automatic', 'Good', 26500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2022 Chevrolet Equinox LT', 'Modern crossover SUV with advanced safety features. Comfortable seating for 5 passengers.', 4, 11, 2, 2, 2022, 15000, 'Gasoline', 'Automatic', 'Excellent', 24500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2019 BMW 3 Series 330i', 'Luxury sedan with premium features. Smooth ride and excellent performance. Low mileage for age.', 5, 12, 1, 4, 2019, 32000, 'Gasoline', 'Automatic', 'Excellent', 32500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2020 Honda CR-V EX', 'Reliable family SUV with spacious interior. Perfect for daily driving and weekend adventures.', 2, 6, 2, 2, 2020, 38000, 'Gasoline', 'Automatic', 'Excellent', 22800.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2017 Ford Mustang GT', 'Iconic American muscle car. Powerful V8 engine with manual transmission. Collector quality.', 3, 9, 5, 1, 2017, 55000, 'Gasoline', 'Manual', 'Good', 29500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE');

-- Insert private seller vehicle ads (person ads)
INSERT INTO vehicle_ads (owner_id, title, description, brand_id, model_id, body_type_id, category_id, vehicle_year, mileage, fuel_type, transmission, condition, price, currency, country, city, latitude, longitude, address, status)
VALUES
('660e8400-e29b-41d4-a716-446655440001', '2016 Nissan Altima 2.5 SV - Private Seller', 'Well-maintained sedan from private owner. Great condition, single owner, all maintenance records available. Perfect family car.', 2, 5, 1, 1, 2016, 85000, 'Gasoline', 'Automatic', 'Good', 12500.00, 'USD', 'US', 'Los Angeles', 34.0522, -118.2437, '123 Main St, Los Angeles, CA', 'ACTIVE'),

('770e8400-e29b-41d4-a716-446655440002', '2020 Jeep Grand Cherokee Limited - Private Sale', 'Beautiful SUV in excellent condition. Private seller, garage kept, no accidents. Loaded with features including navigation and leather seats.', 1, 3, 2, 2, 2020, 42000, 'Gasoline', 'Automatic', 'Excellent', 32000.00, 'USD', 'US', 'Chicago', 41.8781, -87.6298, '456 Oak Avenue, Chicago, IL', 'ACTIVE'),

('880e8400-e29b-41d4-a716-446655440003', '2015 Chevrolet Malibu LT - Moving Sale', 'Reliable sedan from private owner who is moving out of state. Recent oil changes, new tires, and timing belt replaced. Great value!', 4, 10, 1, 1, 2015, 72000, 'Gasoline', 'Automatic', 'Good', 9800.00, 'USD', 'US', 'Houston', 29.7604, -95.3698, '789 Pine Street, Houston, TX', 'ACTIVE'),

('990e8400-e29b-41d4-a716-446655440004', '2018 Volkswagen Jetta GLI - Private Owner', 'Sporty compact sedan with manual transmission. Private seller, well maintained, recent service. Perfect for enthusiasts.', 5, 13, 1, 1, 2018, 65000, 'Gasoline', 'Manual', 'Excellent', 15800.00, 'USD', 'US', 'Phoenix', 33.4484, -112.0740, '321 Elm Drive, Phoenix, AZ', 'ACTIVE'),

('aa0e8400-e29b-41d4-a716-446655440005', '2019 Subaru Outback Limited - Family Owned', 'Spacious wagon in great condition. Private seller, one owner, all-wheel drive, perfect for families or outdoor enthusiasts.', 1, 2, 4, 2, 2019, 58000, 'Gasoline', 'Automatic', 'Excellent', 26800.00, 'USD', 'US', 'Seattle', 47.6062, -122.3321, '654 Maple Lane, Seattle, WA', 'ACTIVE');

-- Insert store media for City Motors
INSERT INTO store_media (store_id, url, media_type, media_view, sort_order)
VALUES
(1, 'https://images.unsplash.com/photo-1549317661-bd32c8ce0db2?w=1200', 'IMAGE', 'LOGO', 0),
(1, 'https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=1200', 'IMAGE', 'BANNER', 1),
(1, 'https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=1200', 'IMAGE', 'STOREFRONT', 2);

-- Insert vehicle media for the ads
-- 2020 Toyota Corolla
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(1, 'https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=1200', 'IMAGE', 'EXTERIOR', 0),
(1, 'https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=1200', 'IMAGE', 'INTERIOR', 1),
(1, 'https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=1200', 'IMAGE', 'DASHBOARD', 2);

-- 2019 Honda Civic
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(2, 'https://images.unsplash.com/photo-1590362891991-f776e747a588?w=1200', 'IMAGE', 'EXTERIOR', 0),
(2, 'https://images.unsplash.com/photo-1590362891991-f776e747a588?w=1200', 'IMAGE', 'SIDE_VIEW', 1),
(2, 'https://images.unsplash.com/photo-1590362891991-f776e747a588?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2021 Toyota RAV4
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(3, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'EXTERIOR', 0),
(3, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'REAR_VIEW', 1),
(3, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2018 Ford F-150
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(4, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'EXTERIOR', 0),
(4, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'BED_VIEW', 1),
(4, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2022 Chevrolet Equinox
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(5, 'https://images.unsplash.com/photo-1619405399517-d7fce0f13302?w=1200', 'IMAGE', 'EXTERIOR', 0),
(5, 'https://images.unsplash.com/photo-1619405399517-d7fce0f13302?w=1200', 'IMAGE', 'SIDE_VIEW', 1),
(5, 'https://images.unsplash.com/photo-1619405399517-d7fce0f13302?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2019 BMW 3 Series
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(6, 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=1200', 'IMAGE', 'EXTERIOR', 0),
(6, 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=1200', 'IMAGE', 'INTERIOR', 1),
(6, 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=1200', 'IMAGE', 'DASHBOARD', 2);

-- 2020 Honda CR-V
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(7, 'https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?w=1200', 'IMAGE', 'EXTERIOR', 0),
(7, 'https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?w=1200', 'IMAGE', 'REAR_VIEW', 1),
(7, 'https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2017 Ford Mustang
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(8, 'https://images.unsplash.com/photo-1584345604476-8ec5f09e8b85?w=1200', 'IMAGE', 'EXTERIOR', 0),
(8, 'https://images.unsplash.com/photo-1584345604476-8ec5f09e8b85?w=1200', 'IMAGE', 'SIDE_VIEW', 1),
(8, 'https://images.unsplash.com/photo-1584345604476-8ec5f09e8b85?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2016 Nissan Altima (Private Seller)
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(9, 'https://images.unsplash.com/photo-1549399735-cef2e2c3f638?w=1200', 'IMAGE', 'EXTERIOR', 0),
(9, 'https://images.unsplash.com/photo-1549399735-cef2e2c3f638?w=1200', 'IMAGE', 'INTERIOR', 1),
(9, 'https://images.unsplash.com/photo-1549399735-cef2e2c3f638?w=1200', 'IMAGE', 'DASHBOARD', 2);

-- 2020 Jeep Grand Cherokee (Private Seller)
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(10, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'EXTERIOR', 0),
(10, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'INTERIOR', 1),
(10, 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=1200', 'IMAGE', 'REAR_VIEW', 2);

-- 2015 Chevrolet Malibu (Private Seller)
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(11, 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=1200', 'IMAGE', 'EXTERIOR', 0),
(11, 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=1200', 'IMAGE', 'SIDE_VIEW', 1),
(11, 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=1200', 'IMAGE', 'INTERIOR', 2);

-- 2018 Volkswagen Jetta GLI (Private Seller)
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(12, 'https://images.unsplash.com/photo-1558618047-3c8c76ca7d13?w=1200', 'IMAGE', 'EXTERIOR', 0),
(12, 'https://images.unsplash.com/photo-1558618047-3c8c76ca7d13?w=1200', 'IMAGE', 'INTERIOR', 1),
(12, 'https://images.unsplash.com/photo-1558618047-3c8c76ca7d13?w=1200', 'IMAGE', 'ENGINE', 2);

-- 2019 Subaru Outback (Private Seller)
INSERT INTO vehicle_media (ad_id, url, media_type, media_view, sort_order)
VALUES
(13, 'https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=1200', 'IMAGE', 'EXTERIOR', 0),
(13, 'https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=1200', 'IMAGE', 'REAR_VIEW', 1),
(13, 'https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=1200', 'IMAGE', 'INTERIOR', 2);
