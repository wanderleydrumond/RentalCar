CREATE TABLE rents
(
    id        BIGINT     AUTO_INCREMENT,
    user_id   BIGINT     NOT NULL,
    car_id    BIGINT     NOT NULL,
    rent_at   TIMESTAMP  NOT NULL,
    return_at TIMESTAMP  DEFAULT NULL,
    CONSTRAINT rents_pk
        PRIMARY KEY (id),
    CONSTRAINT rents_cars_id_fk
        FOREIGN KEY (car_id) references cars (id),
    CONSTRAINT rents_users_id_fk
        FOREIGN KEY (user_id) references users (id)
);