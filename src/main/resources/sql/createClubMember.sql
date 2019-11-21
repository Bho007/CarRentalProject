CREATE TABLE ClubMember (
    cellphone BIGINT PRIMARY KEY REFERENCES Customer(cellphone) ON DELETE CASCADE,
    points INT,
    fees INT
)