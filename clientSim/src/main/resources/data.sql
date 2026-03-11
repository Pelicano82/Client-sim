-- Test Data for TV Licence Payment System

-- Insert test Person
INSERT INTO person (id, first_name, last_name, full_name, address_line1, city, postcode, country, email, phone) 
VALUES (1, 'John', 'Smith', 'John Smith', '123 Main Street', 'London', 'SW1A 1AA', 'United Kingdom', 'john.smith@email.com', '02012345678');

-- Insert test Card
INSERT INTO card (card_number, card_holder, expiry_date, security_code, card_type) 
VALUES ('4532123456789010', 'JOHN SMITH', '12/26', '123', 'CREDIT');

-- Update Person with Card reference
UPDATE person SET card_card_number = '4532123456789010' WHERE id = 1;

-- Insert test Licence
INSERT INTO licence (id, licence_number, fee, valid_until, status, renewed, person_id) 
VALUES (1, 'LTV-2024-001', 159.00, '2025-12-31', 'ACTIVE', false, 1);

-- Insert test Fines
INSERT INTO fine (id, amount, reason, status, licence_id) 
VALUES (1, 85.50, 'Late payment penalty', 'PENDING', 1);

INSERT INTO fine (id, amount, reason, status, licence_id) 
VALUES (2, 45.00, 'Non-compliance fee', 'PENDING', 1);

-- Insert test Payment (optional)
INSERT INTO payment (id, reference_number, amount, payment_method, status, payment_date, transaction_id, person_id, fine_id, card_card_number) 
VALUES (1, 'ABC12345DEF', 130.50, 'CREDIT_CARD', 'COMPLETED', '2024-03-10 10:30:00', 'TXN-20240310-001', 1, 1, '4532123456789010');
