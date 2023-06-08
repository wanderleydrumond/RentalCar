create table rents
(
    id        int8  not null,
    user_id   int8  not null,
    car_id    int8  not null,
    rent_at   date not null,
    return_at date not null,
    constraint rents_pk
        primary key (id),
    constraint rents_cars_id_fk
        foreign key (car_id) references cars (id),
    constraint rents_users_id_fk
        foreign key (user_id) references users (id)
);