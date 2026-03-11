SET IDENTITY_INSERT person ON;
INSERT INTO person (id, first_name, last_name, full_name, address_line1, city, postcode, country, email, phone) 
VALUES (1, 'Sarah', 'Johnson', 'Sarah Johnson', '456 Oak Avenue', 'Manchester', 'M1 1AD', 'United Kingdom', 'sarah.johnson@email.com', '01612345678');
SET IDENTITY_INSERT person OFF;

INSERT INTO card (card_number, card_holder, expiry_date, security_code, card_type) 
VALUES ('5555444433332222', 'SARAH JOHNSON', '06/23', '789', 'CREDIT');

SET IDENTITY_INSERT licence ON;
INSERT INTO licence (id, licence_number, fee, valid_until, status, renewed, person_id) 
VALUES (1, '1234567890', 159, '2023-12-31', 'ACTIVE', 0, 1);
SET IDENTITY_INSERT licence OFF;

SET IDENTITY_INSERT fine ON;
INSERT INTO fine (id, amount, reason, status, licence_id) 
VALUES (1, 120.00, 'Unlicensed TV viewing', 'PENDING', 1);
SET IDENTITY_INSERT fine OFF;

SET IDENTITY_INSERT payment ON;
INSERT INTO payment (id, reference_number, amount, payment_method, status, person_id, fine_id, card_card_number) 
VALUES (1, 'PAY-2024-0001', 120.00, 'CREDIT_CARD', 'PENDING', 1, 1, '5555444433332222');
SET IDENTITY_INSERT payment OFF;

UPDATE person SET card_card_number = '5555444433332222' WHERE id = 1;
