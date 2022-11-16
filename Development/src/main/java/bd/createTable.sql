
DROP TABLE OwnedCards;
DROP TABLE Administrators;
DROP TABLE HistoricCreditCards;
DROP TABLE Subscribers;
DROP TABLE Logins;
DROP TABLE RestrictedCategories;
DROP TABLE CreditCards;
DROP TABLE SubscriberCards;
DROP TABLE Rentals;
DROP TABLE SupportCards;
DROP TABLE SupportFilms;
DROP TABLE FilmsActors;
DROP TABLE FilmsCategories;
DROP TABLE Categories;
DROP TABLE Actors;
DROP TABLE BluRays;
DROP TABLE QRCodes;
DROP TABLE Films;

CREATE TABLE Films(
    filmID INTEGER,
    title VARCHAR(50),
    synopsis CLOB,
    directorFirstName VARCHAR(30),
    directorLastName VARCHAR(30),
    restrictedAge CHAR(3) CHECK (restrictedAge IN ('M10', 'M12', 'M16', 'M18', 'ALL') ),
    CONSTRAINT filmID_pk PRIMARY KEY (filmID)
);

CREATE TABLE QRCodes(
    QRCodeID INTEGER,
    filmID INTEGER,
    link VARCHAR(255),
    CONSTRAINT QRCodeID_pk PRIMARY KEY (QRCodeID),
    CONSTRAINT filmID_QRcodes_fk FOREIGN KEY (filmID) REFERENCES Films(filmID)
);

CREATE TABLE BluRays(
    blurayID INTEGER,
    filmID INTEGER,
    price FLOAT(3),
    state VARCHAR(9) CHECK (state IN ('AVAILABLE' ,'RENTED', 'DAMAGED', 'STOLEN') ),
    CONSTRAINT blurayID_pk PRIMARY KEY (blurayID),
    CONSTRAINT filmID_BluRays_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID)
);

CREATE TABLE Actors(
    actorID INTEGER,
    actorFirstName VARCHAR(30),
    actorLastName VARCHAR(30),
    CONSTRAINT actorID_pk PRIMARY KEY (actorID)
);

CREATE TABLE Categories(
    categorieID INTEGER,
    catName VARCHAR(25),
    CONSTRAINT catName_unique UNIQUE (catName),
    CONSTRAINT categorieID_pk PRIMARY KEY (categorieID)
);


CREATE TABLE FilmsCategories(
    filmID INTEGER,
    categorieID INTEGER,
    CONSTRAINT filmID_categorieID_pk PRIMARY KEY (filmID,categorieID),
    CONSTRAINT filmID_FilmsCategories_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID),
    CONSTRAINT categorieID_FilmsCategories_fk FOREIGN KEY (categorieID) REFERENCES Categories(categorieID)
);


CREATE TABLE FilmsActors(
    filmID INTEGER,
    actorID INTEGER,
    CONSTRAINT filmID_actorID_pk PRIMARY KEY (filmID,actorID),
    CONSTRAINT filmID_FilmsActors_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID),
    CONSTRAINT actorID_fk FOREIGN KEY (actorID) REFERENCES Actors(actorID)
);


CREATE TABLE SupportFilms(
    supportFilmID INTEGER,
    filmID INTEGER,
    typeFilm CHAR(2) CHECK (typeFilm IN ('QR', 'BR')),
    CONSTRAINT supportFilmID_pk PRIMARY KEY (supportFilmID),
    CONSTRAINT filmID_support_fk FOREIGN KEY (filmID) REFERENCES Films(filmID)
);

CREATE TABLE SupportCards(
    supportCardID INTEGER,
    typeCard CHAR(2) CHECK (typeCard IN ('CC','SC')),
    CONSTRAINT supportCardID_pk PRIMARY KEY (supportCardID)
);

CREATE TABLE Rentals(
    rentalsID INTEGER,
    supportCardID INTEGER,
    supportFilmID INTEGER,
    beginDate DATE,
    endDate DATE,
    CONSTRAINT rentalsID_pk PRIMARY KEY (rentalsID),
    CONSTRAINT supportCardID_Rentals_fk FOREIGN KEY (supportCardID) REFERENCES SupportCards(supportCardID),
    CONSTRAINT supportFIlmsID_Rentals_fk FOREIGN KEY (supportFilmID) REFERENCES SupportFilms(supportFilmID)
);

CREATE TABLE SubscriberCards (
    subCardID INTEGER,
    supportCardID INTEGER,
    limitWeek INTEGER,
    restrictedAge CHAR(3) CHECK (restrictedAge IN ('M10', 'M12', 'M16', 'M18', 'ALL') ),
    balance FLOAT(3),
    CONSTRAINT subCardID_pk PRIMARY KEY (subCardID),
    CONSTRAINT supportCardID_SubscribersCards_fk FOREIGN KEY (supportCardID) REFERENCES SupportCards(supportCardID)
);

CREATE TABLE CreditCards (
    creditID INTEGER,
    supportCardID INTEGER,
    creditCardNum INTEGER,
    CONSTRAINT creditCardNum_unique UNIQUE (creditCardNum),
    CONSTRAINT creditID_pk PRIMARY KEY (creditID)
);

CREATE TABLE RestrictedCategories(
    categorieID INTEGER,
    subCardID INTEGER,
    CONSTRAINT categorieID_subCardID_pk PRIMARY KEY (categorieID,subCardID),
    CONSTRAINT categorieID_RestrictedCategories_fk FOREIGN KEY (categorieID) REFERENCES Categories(categorieID),
    CONSTRAINT subCardID_RestrictedCategories_fk FOREIGN KEY (subCardID) REFERENCES SubscriberCards(subCardID)
);

CREATE TABLE Logins(
    loginID VARCHAR(50),
    hPassword VARCHAR(250),
    CONSTRAINT loginID_pk PRIMARY KEY (loginID)
);

CREATE TABLE Subscribers (
    subID INTEGER,
    loginID VARCHAR(50),
    creditID INTEGER,
    subFirstName VARCHAR(30),
    subLastName VARCHAR(30),
    birthDate DATE,
    CONSTRAINT subID_pk PRIMARY KEY (subID),
    CONSTRAINT loginID_Subscribers_fk FOREIGN KEY (loginID) REFERENCES Logins(loginID),
    CONSTRAINT creditID_fk FOREIGN KEY (creditID) REFERENCES CreditCards(creditID)
);

CREATE TABLE HistoricCreditCards(
    historicID INTEGER,
    creditID INTEGER,
    actionDate DATE,
    action VARCHAR(15) CHECK( action IN ('STARTRENTAL', 'ENDRENTAL', 'PAYFULLPRICE', 'REFUND') ),
    amount INTEGER,
    CONSTRAINT historicID_pk PRIMARY KEY (historicID)
);

CREATE TABLE Administrators(
    adminID INTEGER,
    loginID VARCHAR(50),
    CONSTRAINT adminID_pk PRIMARY KEY (adminID),
    CONSTRAINT loginID_Administrators_fk FOREIGN KEY (loginID) REFERENCES Logins(loginID) 
);

CREATE TABLE OwnedCards(
    subID INTEGER,
    subCardID INTEGER,
    CONSTRAINT subID_subCardID_pk PRIMARY KEY (subID,subCardID),
    CONSTRAINT subID_fk FOREIGN KEY (subID) REFERENCES Subscribers(subID),
    CONSTRAINT subCardID_OwnedCards_fk FOREIGN KEY (subCardID) REFERENCES SubscriberCards(subCardID)
);