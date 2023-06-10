CREATE TABLE cars
(
    id          BIGINT      AUTO_INCREMENT,
    segment     INT2        NOT NULL,
    daily_price DOUBLE      NOT NULL,
    brand       VARCHAR(13) NOT NULL,
    CONSTRAINT cars_pk
        PRIMARY KEY (id)
);