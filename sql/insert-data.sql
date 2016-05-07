INSERT INTO Kayttaja (nimi, salasana) values ('test', 'test');
INSERT INTO Kayttaja (nimi, salasana) values ('test2', 'test2');

INSERT INTO Kategoria (otsikko, kayttaja_id) VALUES ('testikategoria', 1);
INSERT INTO Kategoria (otsikko, kayttaja_id) VALUES ('testikategoria2', 2);

INSERT INTO Tehtava (otsikko, kuvaus, prioriteetti, kayttaja_id) VALUES
('testitehtava', 'testailua', 1, 1);

INSERT INTO Tehtava (otsikko, kuvaus, prioriteetti, kayttaja_id) VALUES
('testitehtava2', 'testailua', 4, 2);

INSERT INTO Tehtavakategoria (tehtava_id, kategoria_id) VALUES (1,1);
INSERT INTO Tehtavakategoria (tehtava_id, kategoria_id) VALUES (1,2);
INSERT INTO Tehtavakategoria (tehtava_id, kategoria_id) VALUES (2,2);


