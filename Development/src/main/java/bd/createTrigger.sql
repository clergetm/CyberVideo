CREATE OR REPLACE TRIGGER max_5_cards_subscriber
BEFORE INSERT ON OwnedCards
FOR EACH ROW
DECLARE 
    nbCards INTEGER;
BEGIN
    SELECT COUNT(*) INTO nbCards 
    FROM OwnedCards 
    WHERE subID = :new.subID;

    IF (nbCards >= 5) THEN 
        RAISE_APPLICATION_ERROR(-20001,'A subscriber cannot have more than 5 subscriber cards');
    END IF;
END;






