INSERT INTO product (name, description, price, category, image_url) VALUES
('Samsung TV 55"', 'Smart LED TV', 45000, 'Electronics', 'tv.jpg'),
('Sony Headphones', 'Noise cancelling', 8000, 'Electronics', 'headphones.jpg'),
('Nike Shoes', 'Running shoes', 5000, 'Footwear', 'shoes.jpg');

INSERT INTO store (name, address, city, pincode, phone, latitude, longitude, opening_time, closing_time) VALUES
('Reliance Digital', 'Main Road', 'Tiruppur', '641601', '9999999991', 11.1085, 77.3411, '09:00', '21:00'),
('Croma Store', 'Cross Cut Road', 'Coimbatore', '641001', '9999999992', 11.0168, 76.9558, '10:00', '21:00'),
('Vi Mobile Store', 'Anna Salai', 'Chennai', '600002', '9999999993', 13.0827, 80.2707, '09:30', '20:00');

INSERT INTO store_inventory (product_id, store_id, stock_quantity, status) VALUES
(1, 1, 10, 'IN_STOCK'),
(1, 2, 2, 'LOW_STOCK'),
(2, 1, 0, 'OUT_OF_STOCK'),
(2, 3, 5, 'IN_STOCK'),
(3, 2, 8, 'IN_STOCK');