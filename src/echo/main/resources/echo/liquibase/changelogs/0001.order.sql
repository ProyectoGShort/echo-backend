-- changeset jdelacasapalomino:create-basic-tables

CREATE TABLE orders (
    id CHAR(36) PRIMARY KEY,
    source VARCHAR(30) NOT NULL,
    sourceUrl TEXT NOT NULL,
    mediaCount INTEGER NOT NULL,
    author VARCHAR(255) NOT NULL
);