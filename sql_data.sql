INSERT INTO tour_types (name) VALUES ('vacation');
INSERT INTO tour_types (name) VALUES ('excursion');
INSERT INTO tour_types (name) VALUES ('shopping');


INSERT INTO hotel_types (name) VALUES ('business hotel');
INSERT INTO hotel_types (name) VALUES ('resort');
INSERT INTO hotel_types (name) VALUES ('apartments');
INSERT INTO hotel_types (name) VALUES ('lodge');
INSERT INTO hotel_types (name) VALUES ('guest houses');
INSERT INTO hotel_types (name) VALUES ('bed and breakfast');


INSERT INTO roles (name) VALUES ('CUSTOMER');
INSERT INTO roles (name) VALUES ('MANAGER');

INSERT INTO countries (name) VALUES ('USA');
INSERT INTO countries (name) VALUES ('Singapore');
INSERT INTO countries (name) VALUES ('Ukraine');
INSERT INTO countries (name) VALUES ('Sweden');
INSERT INTO countries (name) VALUES ('South Korea');
INSERT INTO countries (name) VALUES ('Canada');
INSERT INTO countries (name) VALUES ('Japan');
INSERT INTO countries (name) VALUES ('Australia');

INSERT INTO statuses (title) VALUES ('registred');
INSERT INTO statuses (title) VALUES ('payed');
INSERT INTO statuses (title) VALUES ('declined');

INSERT INTO tours (name, description, country, peoples, hotel_type_id, tour_type_id, price, discount, is_hot)
VALUES ('Test tour', 'Test description', 'Japan', 2, 1, 1, 20000, 0, true);