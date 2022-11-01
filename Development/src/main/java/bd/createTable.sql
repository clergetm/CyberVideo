DROP TABLE IF EXISTS Films;
DROP TABLE IF EXISTS QRCodes;
DROP TABLE IF EXISTS BluRays;
DROP TABLE IF EXISTS Actors;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS FilmsActors;
DROP TABLE IF EXISTS FilmsCategories;
DROP TABLE IF EXISTS SupportFilms;
DROP TABLE IF EXISTS Rentals;
DROP TABLE IF EXISTS SupportCards;
DROP TABLE IF EXISTS SubscriberCards;
DROP TABLE IF EXISTS Subscribers;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS HistoricCreditCards;
DROP TABLE IF EXISTS Logins;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS OwnedCards;
DROP TABLE IF EXISTS RestrictedCategories;


CREATE TABLE Films{
    filmID INTEGER(10),
    title VARCHAR(50),
    synopsis BLOB,
    directorFirstName VARCHAR(30),
    directorLastName VARCHAR(30),
    restrictedAge CHAR(3) CHECK (restrictedAge IN ('M10', 'M12', 'M16', 'M18', 'ALL') ),
    CONSTRAINT filmID_pk PRIMARY KEY (filmID)
};

CREATE TABLE QRCodes{
    QRCodeID INTEGER(10),
    filmID INTEGER(10),
    link VARCHAR(255),
    CONSTRAINT QRCodeID_unique UNIQUE (QRCodeID),
    CONSTRAINT QRCodeID_pk PRIMARY KEY (QRCodeID),
    CONSTRAINT filmID_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID)
};

CREATE TABLE BluRays{
    blurayID INTEGER(10),
    filmID INTEGER(10),
    price FLOAT(3),
    state VARCHAR(9) CHECK (state IN ('AVAILABLE' ,'RENTED', 'DAMAGED', 'STOLEN') ),
    CONSTRAINT blurayID_pk PRIMARY KEY (blurayID),
    CONSTRAINT filmID_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID)
};

CREATE TABLE Actors{
    actorID INTEGER(10),
    actorFirstName VARCHAR(30),
    actorLastName VARCHAR(30),
    CONSTRAINT actorID_pk PRIMARY KEY (actorID)
};

CREATE TABLE Categories{
    categorieID INTEGER(10),
    catName VARCHAR(25),
    CONSTRAINT catName_unique UNIQUE (catName),
    CONSTRAINT categorieID_pk PRIMARY KEY (categorieID)
};


CREATE TABLE FilmsCategories{
    filmID INTEGER(10),
    categorieID INTEGER(10),
    CONSTRAINT filmID_categorieID_pk PRIMARY KEY (filmID,categorieID),
    CONSTRAINT filmID_FilmsCategories_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID),
    CONSTRAINT categorieID_fk FOREIGN KEY (categorieID) REFERENCES Categories(categorieID)
};


CREATE TABLE FilmsActors{
    filmID INTEGER(10),
    actorID INTEGER(10),
    CONSTRAINT filmID_actorID_pk PRIMARY KEY (filmID,actorID),
    CONSTRAINT filmID_FilmsActors_fk FOREIGN KEY (filmID) REFERENCES Films(FilmID),
    CONSTRAINT actorID_fk FOREIGN KEY (actorID) REFERENCES Actors(actorID)
};


CREATE TABLE SupportFilms{
    supportFilmID INTEGER(10),
    filmID INTEGER(10),
    typeFilm CHAR(2) CHECK (typeFilm IN ('QR', 'BR')),
    CONSTRAINT supportFilmID_pk PRIMARY KEY (supportFilmID),
    CONSTRAINT filmID_support_fk FOREIGN KEY (filmID) REFERENCES Films(filmID)
}

CREATE TABLE Rentals{
    rentalsID INTEGER(10),
    supportCardID INTEGER(10),
    supportFilmID INTEGER(10),
    beginDate DATE,
    endDate DATE,
    CONSTRAINT rentalsID_pk PRIMARY KEY (rentalsID),
    CONSTRAINT supportCardID_Rentals_fk FOREIGN KEY (supportCardID) REFERENCES SupportCards(supportCardID),
    CONSTRAINT supportFIlmsID_Rentals_fk FOREIGN KEY (supportFilmID) REFERENCES SupportFilms(supportFilmID)
};

CREATE TABLE SupportCards{
    supportCardID INTEGER(10),
    typeCard CHAR(2) CHECK (typeCard IN ('CC','SC')),
    CONSTRAINT supportCardID_pk PRIMARY KEY (supportCardID)
};

CREATE TABLE SubscriberCards {
    subCardID INTEGER(10),
    supportCardID INTEGER(10),
    limitWeek INTEGER(2),
    restrictedAge CHAR(3) CHECK (restrictedAge IN ('M10', 'M12', 'M16', 'M18', 'ALL') ),
    balance FLOAT(3),
    CONSTRAINT subCardID_pk PRIMARY KEY (subCardID),
    CONSTRAINT supportCardID_SubscribersCards_fk FOREIGN KEY (supportCardID) REFERENCES SupportCards(supportCardID)
};

CREATE TABLE CreditCards {
    creditID INTEGER(10),
    supportCardID INTEGER(10),
    creditCardNum INTEGER(16),
    CONSTRAINT creditCardNum_unique UNIQUE (creditCardNum),
    CONSTRAINT creditID_pk PRIMARY KEY (creditID)
};

CREATE TABLE RestrictedCategories{
    categorieID INTEGER(10),
    subCardID INTEGER(10),
    CONSTRAINT categorieID_subCardID_pk PRIMARY KEY (categorieID,subCardID),
    CONSTRAINT categorieID_fk FOREIGN KEY (categorieID) REFERENCES Categories(categorieID),
    CONSTRAINT subCardID_fk FOREIGN KEY (subCardID) REFERENCES SubscriberCards(subCardID)
};

CREATE TABLE Subscribers {
    subID VARCHAR(10),
    loginID VARCHAR(50),
    creditID INTEGER(10),
    subFirstName VARCHAR(30),
    subLastName VARCHAR(30),
    birthDate DATE,
    CONSTRAINT subID_pk PRIMARY KEY (subID),
    CONSTRAINT loginID_Subscribers_fk FOREIGN KEY (loginID) REFERENCES Logins(loginID),
    CONSTRAINT creditID_fk FOREIGN KEY (creditID) REFERENCES CreditCards(creditID)
};

CREATE TABLE HistoricCreditCards{
    historicID VARCHAR(100),
    creditID INTEGER(10),
    actionDate DATE,
    action VARCHAR(15) CHECK( action IN ('STARTRENTAL', 'ENDRENTAL', 'PAYFULLPRICE', 'REFUND') ),
    amount INTEGER(3),
    CONSTRAINT historicID_pk PRIMARY KEY (historicID)
};

CREATE TABLE Logins{
    loginID VARCHAR(50),
    hPassword VARCHAR(250),
    CONSTRAINT loginID_pk PRIMARY KEY (loginID)
};

CREATE TABLE Administrators{
    adminID INTEGER(10),
    loginID VARCHAR(50),
    CONSTRAINT adminID_pk PRIMARY KEY (adminID),
    CONSTRAINT loginID_Administrators_fk FOREIGN KEY (loginID) REFERENCES Logins(loginID) 
}

CREATE TABLE OwnedCards{
    subID INTEGER(10),
    subCardID INTEGER(10),
    CONSTRAINT subID_subCardID_pk PRIMARY KEY (subID,subCardID),
    CONSTRAINT subID_fk FOREIGN KEY (subID) REFERENCES Subscribers(subID),
    CONSTRAINT subCardID_fk FOREIGN KEY (subCardID) REFERENCES SubscriberCards(subCardID)
};