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

('550e8400-e29b-41d4-a716-446655440000', 1, '2019 Honda Civic EX - Low Mileage', 'Sporty and fuel-efficient compact car. Features include backup camera, Bluetooth, and alloy wheels.', 2, 4, 1, 1, 2019, 28000, 'Gasoline', 'Manual', 'Very Good', 16800.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2021 Toyota RAV4 Hybrid - One Owner', 'Fuel-efficient SUV perfect for city and highway driving. Loaded with safety features and modern tech.', 1, 3, 2, 2, 2021, 22000, 'Hybrid', 'Automatic', 'Excellent', 28500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2018 Ford F-150 XL - Work Truck', 'Reliable pickup truck with plenty of towing capacity. Great for work or recreational use.', 3, 7, 3, 3, 2018, 45000, 'Gasoline', 'Automatic', 'Good', 26500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2022 Chevrolet Equinox LT', 'Modern crossover SUV with advanced safety features. Comfortable seating for 5 passengers.', 4, 11, 2, 2, 2022, 15000, 'Gasoline', 'Automatic', 'Excellent', 24500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2019 BMW 3 Series 330i', 'Luxury sedan with premium features. Smooth ride and excellent performance. Low mileage for age.', 5, 12, 1, 4, 2019, 32000, 'Gasoline', 'Automatic', 'Very Good', 32500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2020 Honda CR-V EX', 'Reliable family SUV with spacious interior. Perfect for daily driving and weekend adventures.', 2, 6, 2, 2, 2020, 38000, 'Gasoline', 'Automatic', 'Excellent', 22800.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE'),

('550e8400-e29b-41d4-a716-446655440000', 1, '2017 Ford Mustang GT', 'Iconic American muscle car. Powerful V8 engine with manual transmission. Collector quality.', 3, 9, 5, 1, 2017, 55000, 'Gasoline', 'Manual', 'Good', 29500.00, 'USD', 'US', 'New York', 40.7128, -74.0060, '456 Broadway, New York, NY', 'ACTIVE');