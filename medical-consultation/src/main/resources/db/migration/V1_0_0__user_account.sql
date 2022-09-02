create sequence hibernate_sequence
    minvalue 1
    start with 1
    increment by 1
    cache 5;

create table user_account
(
    id         bigserial primary key,
    first_name varchar not null,
    last_name  varchar not null,
    email      varchar not null,
    password   varchar not null,
    role       varchar not null
);

create table phone
(
    id              bigserial primary key,
    number          varchar not null,
    user_account_id bigint,
    foreign key (user_account_id) references user_account (id)
);

create table doctor
(
    id              bigserial primary key,
    position        varchar,
    user_account_id bigint,
    foreign key (user_account_id) references user_account (id)
);

create table patient
(
    id              bigserial primary key,
    user_account_id bigint,
    foreign key (user_account_id) references user_account (id)
);
