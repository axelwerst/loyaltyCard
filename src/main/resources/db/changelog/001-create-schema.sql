--chenset alexandr.honcharenko:1
CREATE TABLE Customers (
                           CustomerID UUID PRIMARY KEY,
                           Name VARCHAR(100) NOT NULL,
                           Phone VARCHAR(20) UNIQUE NOT NULL,
                           Email VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE LoyaltyCards (
                              CardID UUID PRIMARY KEY,
                              CustomerID UUID NOT NULL,
                              QRCode VARCHAR(255) UNIQUE NOT NULL,
                              Balance NUMERIC(10, 2) DEFAULT 0.0,
                              FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
CREATE TABLE Transactions (
                              TransactionID UUID PRIMARY KEY,
                              CardID UUID NOT NULL,
                              Amount NUMERIC(10, 2) NOT NULL,
                              Reason VARCHAR(255),
                              Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (CardID) REFERENCES LoyaltyCards(CardID)
);
CREATE TABLE Admins (
                        AdminID UUID PRIMARY KEY,
                        Username VARCHAR(50) UNIQUE NOT NULL,
                        Password VARCHAR(255) NOT NULL,
                        Role VARCHAR(20) NOT NULL CHECK (Role IN ('manager', 'employee'))
);