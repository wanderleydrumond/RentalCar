create table users
(
    id       int8        not null,
    name     varchar(50) not null,
    code     varchar(7)  not null comment 'Replaces the username. Auto generated',
    role     int2        not null,
    password varchar(50) not null,
    token    binary(16)      null,
    constraint users_pk
        primary key (id),
    constraint users_unique
        unique (code)
);