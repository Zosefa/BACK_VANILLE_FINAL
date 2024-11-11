CREATE TABLE personne(
  id_personne SERIAL primary key ,
  nom VARCHAR,
  tel VARCHAR
);
CREATE TABLE utilisateur(
  id_utilisateur SERIAL PRIMARY KEY ,
  id_personne INT REFERENCES personne(id_personne),
  pwsd VARCHAR
);

CREATE TABLE vanilla(
  id_vanilla SERIAL PRIMARY KEY ,
  nom VARCHAR,
  email VARCHAR,
  tel VARCHAR,
  dirigeant VARCHAR,
  dascription VARCHAR
);
CREATE TABLE type(
  id_type SERIAL PRIMARY KEY ,
  nom VARCHAR
);
CREATE TABLE vanille(
  id_vanille SERIAL PRIMARY KEY,
  nom VARCHAR,
  description VARCHAR,
  prix float,
  id_type INT REFERENCES type(id_type),
  image VARCHAR
);
CREATE TABLE type_divers(
    id_type_divers SERIAL PRIMARY KEY ,
    nom VARCHAR
);
CREATE TABLE divers(
    id_divers SERIAL PRIMARY KEY ,
    nom VARCHAR,
    description VARCHAR,
    prix float,
    id_type_divers INT REFERENCES type_divers(id_type_divers),
    image VARCHAR
);
CREATE TABLE disponible(
    id_disponible SERIAL PRIMARY KEY ,
    idVanille INT REFERENCES vanille(id_vanille),
    debut date,
    fin date,
    affichage boolean
);
CREATE TABLE solde(
  id_solde SERIAL PRIMARY KEY,
  id_disponible INT REFERENCES disponible(id_disponible),
  remise double precision,
  debut date,
  fin date,
  affichage boolean
);
