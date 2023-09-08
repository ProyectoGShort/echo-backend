-- changeset jdelacasapalomino:create-basic-tables

CREATE TABLE orders (
    id CHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    source VARCHAR(30) NOT NULL,
    source_url TEXT NOT NULL,
    media_count INTEGER NOT NULL,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE medias (
    id CHAR(36) PRIMARY KEY,
    position INTEGER NOT NULL,
    source VARCHAR(30) NOT NULL,
    order_id VARCHAR(36) NOT NULL,
    order_source_url TEXT NOT NULL,
    status VARCHAR(30) NOT NULL,
    title VARCHAR(255) NULL,
    author VARCHAR(255) NULL,
    media_source_url TEXT NULL,

    UNIQUE (position, order_id)
);