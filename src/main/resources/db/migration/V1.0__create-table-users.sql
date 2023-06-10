CREATE TABLE users
(
    id       BIGINT      AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    code     VARCHAR(7)  NOT NULL COMMENT 'Replaces the username. Auto generated',
    role     INT2        NOT NULL,
    password VARCHAR(50),
    token    BINARY(16),
    CONSTRAINT users_pk
        PRIMARY KEY (id),
    CONSTRAINT users_unique
        UNIQUE (code)
);