--chenset alexandr.honcharenko:1
CREATE TABLE Customers (
                           Id UUID PRIMARY KEY,
                           Name VARCHAR(100) NOT NULL,
                           Phone VARCHAR(20) UNIQUE NOT NULL,
                           Email VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE LoyaltyCards (
                              Id UUID PRIMARY KEY,
                              CustomerID UUID UNIQUE NOT NULL,
                              QRCode VARCHAR(255) UNIQUE NOT NULL,
                              Balance NUMERIC(10, 2) DEFAULT 0.0,
                              FOREIGN KEY (CustomerID) REFERENCES Customers(Id)
);
CREATE TABLE Transactions (
                              Id UUID PRIMARY KEY,
                              CardID UUID NOT NULL,
                              Amount NUMERIC(10, 2) NOT NULL,
                              Reason VARCHAR(255),
                              Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (CardID) REFERENCES LoyaltyCards(Id)
);
CREATE TABLE Admins (
                        Id UUID PRIMARY KEY,
                        Name VARCHAR(100) NOT NULL,
                        Email VARCHAR(100) UNIQUE NOT NULL,
                        Password VARCHAR(255) NOT NULL
);