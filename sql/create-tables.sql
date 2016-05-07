CREATE TABLE Kayttaja(
id SERIAL PRIMARY KEY,
nimi varchar(50) NOT NULL,
salasana varchar(50) NOT NULL,
admin boolean
);

CREATE TABLE Tehtava(
id SERIAL PRIMARY KEY,
otsikko varchar(255) NOT NULL,
kuvaus varchar(255),
prioriteetti integer,
suoritettu boolean,
kayttaja_id integer REFERENCES Kayttaja(id) ON DELETE CASCADE
);

CREATE TABLE Kategoria(
id SERIAL PRIMARY KEY,
otsikko varchar(255),
kayttaja_id integer REFERENCES Kayttaja(id) ON DELETE CASCADE
);

CREATE TABLE Tehtavakategoria(
tehtava_id integer references Tehtava(id) ON DELETE CASCADE, 
kategoria_id integer references Kategoria(id) ON DELETE CASCADE
);











