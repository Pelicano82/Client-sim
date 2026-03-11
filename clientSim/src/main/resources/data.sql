-- Test Data for TV Licence Payment System - Azure SQL Server Compatible

SET IDENTITY_INSERT person ON;
INSERT INTO person (id, first_name, last_name, full_name, address_line1, city, postcode, country, email, phone, card_card_number) 
VALUES (1, 'John', 'Smith', 'John Smith', '123 Main Street', 'London', 'SW1A 1AA', 'United Kingdom', 'john.smith@email.com', '02012345678', '4532123456789010');
SET IDENTITY_INSERT person OFF;

-- Insert test Cards
INSERT INTO card (card_number, card_holder, expiry_date, security_code, card_type) 
VALUES ('4532123456789010', 'JOHN SMITH', '12/26', '123', 'CREDIT');

INSERT INTO card (card_number, card_holder, expiry_date, security_code, card_type) 
VALUES ('5425233010103010', 'JOHN SMITH', '08/27', '456', 'DEBIT');

SET IDENTITY_INSERT licence ON;
INSERT INTO licence (id, licence_number, fee, valid_until, status, renewed, person_id) 
VALUES (1, 'LTV-2024-001', 159.00, '2025-12-31', 'ACTIVE', 0, 1);
SET IDENTITY_INSERT licence OFF;

SET IDENTITY_INSERT fine ON;
INSERT INTO fine (id, amount, reason, status, licence_id) 
VALUES (1, 85.50, 'Late payment penalty', 'PENDING', 1);

INSERT INTO fine (id, amount, reason, status, licence_id) 
VALUES (2, 45.00, 'Non-compliance fee', 'PENDING', 1);
SET IDENTITY_INSERT fine OFF;

SET IDENTITY_INSERT payment ON;

-- Payment 1: Completed payment for late payment penalty
INSERT INTO payment (id, reference_number, amount, payment_method, status, payment_date, transaction_id, person_id, fine_id, card_card_number) 
VALUES (1, 'PAY-2024-001-001', 85.50, 'CREDIT_CARD', 'SUCCESS', '2024-03-10 10:30:00', 'TXN-20240310-001', 1, 1, '4532123456789010');

-- Payment 2: Completed payment for non-compliance fee
INSERT INTO payment (id, reference_number, amount, payment_method, status, payment_date, transaction_id, person_id, fine_id, card_card_number) 
VALUES (2, 'PAY-2024-002-001', 45.00, 'DEBIT_CARD', 'SUCCESS', '2024-03-11 14:15:30', 'TXN-20240311-002', 1, 2, '5425233010103010');

SET IDENTITY_INSERT payment OFF;
