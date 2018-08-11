CREATE TABLE HOTEL(
   ID int not null,
   NAME varchar(100),
   PRIMARY KEY(ID)
);

CREATE TABLE ROOM_STATUS(
   ID int not null,
   STATUS varchar(50),
   PRIMARY KEY(ID)
);

CREATE TABLE ROOM_TYPE(
   ID int not null,
   TYPE varchar(50),
   PRIMARY KEY(ID)
);

CREATE TABLE ROOM(
   ID int not null,
   NAME varchar(100),
   STATUS int,
   TYPE int,
   PRIMARY KEY(ID),
   FOREIGN KEY (STATUS) REFERENCES ROOM_STATUS(ID),
   FOREIGN KEY (TYPE) REFERENCES ROOM_TYPE(ID)
);