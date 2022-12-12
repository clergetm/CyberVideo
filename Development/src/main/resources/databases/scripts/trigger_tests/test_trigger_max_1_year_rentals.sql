INSERT INTO SupportCards VALUES(1,'CC');
INSERT INTO Films VALUES(1,'Les dents de la mer',1975,'Histoire de requins...','Steven','Spielberg','M16');
INSERT INTO SupportFilms VALUES(1,1,'BR');
INSERT INTO Rentals VALUES(1,1,1,TO_DATE('01/05/2022','DD/MM/YYYY'),TO_DATE('01/06/2022','DD/MM/YYYY'));
INSERT INTO Rentals VALUES(2,1,1,TO_DATE('11/08/2022','DD/MM/YYYY'),TO_DATE('11/09/2022','DD/MM/YYYY'));
UPDATE Rentals SET beginDate = TO_DATE('01/05/2021','DD/MM/YYYY') WHERE rentalsID = 1;