CREATE TABLE IF NOT EXISTS pokemon (
    id                          BIGSERIAL NOT NULL,
    pokedex_id                  INT NOT NULL,
    name                        VARCHAR(100) NOT NULL,
    species_id                  INT,
    height                      INT NOT NULL,
    weight                      INT NOT NULL,
    base_experience             INT NOT NULL,
    order_number                INT NOT NULL,
    is_default                  BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (pokedex_id, order_number)
);