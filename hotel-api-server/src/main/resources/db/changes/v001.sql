CREATE TABLE HOTEL(
    ID int not null,
    NAME varchar(100),
    CATEGORY int,
    PRIMARY KEY(ID)
);

CREATE TABLE ROOM_TYPE(
    ID int not null,
    TYPE varchar(50),
    ID_HOTEL int,
    PRIMARY KEY(ID),
    FOREIGN KEY(ID_HOTEL) REFERENCES HOTEL(ID)
);

CREATE TABLE ROOM(
    ID int not null,
    NAME varchar(100),
    STATUS varchar(50),
    TYPE int,
    ID_HOTEL int,
    PRIMARY KEY(ID),
    FOREIGN KEY(TYPE) REFERENCES ROOM_TYPE(ID),
    FOREIGN KEY(ID_HOTEL) REFERENCES HOTEL(ID)
);

CREATE TABLE ROOM_TYPE_TARIFF(
    ID int not null,
    NUMBER_GUESTS int,
    TARIFF NUMERIC(2),
    ID_ROOM_TYPE int,
    PRIMARY KEY(ID),
    FOREIGN KEY(ID_ROOM_TYPE) REFERENCES ROOM_TYPE(ID)
);