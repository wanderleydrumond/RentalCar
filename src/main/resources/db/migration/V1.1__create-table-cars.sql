create table cars
(
    id          int8        not null,
    segment     int2        not null,
    daily_price double      not null,
    brand       varchar(13) not null,
    constraint cars_pk
        primary key (id)
);