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
    CONSTRAINT QRCodeID_pk PRIMARY KEY (QRCodeID)
};

CREATE TABLE BluRays{
    blurayID INTEGER(10),
    filmID INTEGER(10),
    price FLOAT(3),
    state VARCHAR(9) CHECK (state IN ('AVAILABLE' ,'RENTED', 'DAMAGED', 'STOLEN') ),
    CONSTRAINT blurayID_pk PRIMARY KEY (blurayID)
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
    CONSTRAINT filmID_categorieID_pk PRIMARY KEY (filmID,categorieID)
};


CREATE TABLE FilmsActors{
    filmID INTEGER(10),
    actorID INTEGER(10),
    CONSTRAINT filmID_actorID_pk PRIMARY KEY (filmID,actorID)
};


CREATE TABLE SupportFilms{
    supportFilmID INTEGER(10),
    filmID INTEGER(10),
    typeFilm CHAR(2) CHECK (typeFilm IN ('QR', 'BR')),
    CONSTRAINT supportFilmID_pk PRIMARY KEY (supportFilmID)
}

CREATE TABLE Rentals{
    rentalsID INTEGER(10),
    supportCardID INTEGER(10),
    supportFilmID INTEGER(10),
    beginDate DATE,
    endDate DATE,
    CONSTRAINT rentalsID_pk PRIMARY KEY (rentalsID)
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
    CONSTRAINT subCardID_pk PRIMARY KEY (subCardID)
};

CREATE TABLE RestrictedCategories{
    categorieID INTEGER(10),
    subCardID INTEGER(10),
    CONSTRAINT categorieID_subCardID_pk PRIMARY KEY (categorieID,subCardID)
};

CREATE TABLE Subscribers {
    subID VARCHAR(10),
    loginID VARCHAR(50),
    creditID INTEGER(10),
    subFirstName VARCHAR(30),
    subLastName VARCHAR(30),
    birthDate DATE,
    CONSTRAINT subID_pk PRIMARY KEY (subID)
};

CREATE TABLE CreditCards {
    creditID INTEGER(10),
    supportCardID INTEGER(10),
    creditCardNum INTEGER(16),
    CONSTRAINT creditCardNum_unique UNIQUE (creditCardNum),
    CONSTRAINT creditID_pk PRIMARY KEY (creditID)
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
    CONSTRAINT adminID_pk PRIMARY KEY (adminID)
}

CREATE TABLE OwnedCards{
    subID INTEGER(10),
    subCardID INTEGER(10),
    CONSTRAINT subID_subCardID_pk PRIMARY KEY (subID,subCardID)
};