--liquibase formatted sql



INSERT INTO Customers (CustomerID, Name, Phone, Email)
VALUES
    ('488352d9-ae2f-4599-85a0-ae938dc3db77', 'John Doe', '+4923332223233', 'john.doe@example.com'),
    ('a4735e01-6026-411b-94dc-793aae18a316', 'Jane Smith', '+4922233333323', 'jane.smith@example.com'),
    ('ce85f16f-fc17-4a85-addd-03a2b7712651', 'Alice Brown', '+4933333322223', 'alice.brown@example.com'),
    ('75280e71-8231-4cd6-8988-de0bdd08d8c7', 'Bob Johnson', '+49387934897394', 'bob.johnson@example.com');

INSERT INTO LoyaltyCards (CardID, CustomerID, QRCode, Balance)
VALUES
    ('1f4532d4-8c59-4bde-857a-8c7a1f4325b7', '488352d9-ae2f-4599-85a0-ae938dc3db77', 'QR123456', 100.00),
    ('ac32fd79-b25e-49fb-9338-cf14bbaf2f77', 'a4735e01-6026-411b-94dc-793aae18a316', 'QR654321', 50.00),
    ('3b9d89ee-44d9-4c99-94ee-94f3a2320a85', 'ce85f16f-fc17-4a85-addd-03a2b7712651', 'QR987654', 75.50),
    ('d9c1246d-5b3d-4e6c-8253-bde5c89a0fcb', '75280e71-8231-4cd6-8988-de0bdd08d8c7', 'QR567890', 200.00);

INSERT INTO Transactions (TransactionID, CardID, Amount, Reason)
VALUES
    ('6f4e8c8f-5d5b-4f9e-9374-1c9a2bdf8d1e', '1f4532d4-8c59-4bde-857a-8c7a1f4325b7', -20.00, 'Purchase at Store A'),
    ('bf4d3a9e-2294-4cd1-aee3-37f3c7e2eb34', 'ac32fd79-b25e-49fb-9338-cf14bbaf2f77', 10.00, 'Refund'),
    ('20b7a0c5-55f7-4e89-a83d-5f3df1e08fcb', '3b9d89ee-44d9-4c99-94ee-94f3a2320a85', -15.75, 'Purchase at Store B');

INSERT INTO Admins (AdminID, Username, Password, Role)
VALUES
    ('df0b124e-a8d6-4c3d-b3c8-89f1a8d7efbb', 'manager1', 'hashed_password_1', 'manager'),
    ('fa78b38e-2274-4e2c-b5c4-cf8b2d23a876', 'employee1', 'hashed_password_2', 'employee');