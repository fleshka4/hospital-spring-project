CREATE SCHEMA HOSPITAL

CREATE TABLE HOSPITAL.Diagnosis
(
    ID   SERIAL NOT NULL PRIMARY KEY UNIQUE,
    Name VARCHAR(50)
);

CREATE TABLE HOSPITAL.Wards
(
    ID       SERIAL NOT NULL PRIMARY KEY UNIQUE,
    Name     VARCHAR(50),
    MaxCount INTEGER
);

CREATE TABLE HOSPITAL.People
(
    ID          SERIAL NOT NULL PRIMARY KEY UNIQUE,
    FirstName   VARCHAR(20),
    LastName    VARCHAR(20),
    MiddleName  VARCHAR(20),
    DiagnosisID INTEGER,
    WardID      INTEGER,
    FOREIGN KEY (DiagnosisID) REFERENCES Diagnosis (ID),
    FOREIGN KEY (WardID) REFERENCES Wards (ID)
);

-- DROP TABLE Users;

CREATE TABLE HOSPITAL.Users
(
    ID SERIAL NOT NULL PRIMARY KEY UNIQUE,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR,
    user_role_id INTEGER
);

CREATE TABLE HOSPITAL.user_roles
(
    ID SERIAL NOT NULL PRIMARY KEY UNIQUE,
    user_role VARCHAR(255) UNIQUE
);
