DROP TABLE Tehtavakategoria(
tehtava_id integer references Tehtava(id),
kategoria_id integer references Kategoria(id)
);

DROP TABLE Tehtava(
id SERIAL PRIMARY KEY,
otsikko varchar(255) NOT NULL,
kuvaus varchar(255),
prioriteetti integer,
kayttaja_id integer REFERENCES Kayttaja(id)
);

DROP TABLE Kayttaja(
id SERIAL PRIMARY KEY,
nimi varchar(50) NOT NULL,
salasana varchar(50) NOT NULL
);



DROP TABLE Kategoria(
id SERIAL PRIMARY KEY,
otsikko varchar(255)
);











