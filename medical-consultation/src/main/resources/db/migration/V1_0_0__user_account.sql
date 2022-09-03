create sequence hibernate_sequence
    minvalue 1
    start with 1
    increment by 1
    cache 5;

create table user_account
(
    type       varchar(32),
    id         bigserial primary key,
    email      varchar not null,
    password   varchar not null,
    name       varchar not null,
    phone      varchar not null,
    image      varchar,
    specialty  varchar,
    passport   varchar,
    birth_date date,
    role       varchar not null
);

insert into user_account (type, email, password, name, phone, image, role)
values ('UserAccount',
        'admin@gmail.com',
        '{bcrypt}$2a$12$DI0qPSqi8PUhGzvOUTa/6.ZU75LbYmZubv1ZmlrIopeKATnr9/xFO',
        'Ivan Ivanov',
        '+375336790382',
        'https://cdn.vectorstock.com/i/1000x1000/18/05/businessman-or-programmer-avatar-profile-userpic-vector-7471805.webp',
        'ADMIN');


-- create sequence hibernate_sequence
--     minvalue 1
--     start with 1
--     increment by 1
--     cache 5;
--
-- create table user_account
-- (
--     id       bigserial primary key,
--     role     varchar(64) not null,
--     email    varchar     not null,
--     password varchar     not null
-- );
--
-- create table personal_data
-- (
--     id              bigserial primary key,
--     name            varchar       not null,
--     phone           varchar       not null,
--     image           varchar,
--     passport        varchar,
--     birth_date      date,
--     user_account_id bigint unique not null references user_account (id)
-- );
--
-- insert into user_account ()
-- values ('admin@gmail.com',
--         '{bcrypt}$2a$12$DI0qPSqi8PUhGzvOUTa/6.ZU75LbYmZubv1ZmlrIopeKATnr9/xFO',
--         'ADMIN');
--
-- create table doctor
-- (
--     id               bigserial primary key,
--     document         varchar       not null,
--     specialty        varchar       not null,
--     user_account_id  bigint unique not null references user_account (id),
--     personal_data_id bigint unique not null references personal_data (id)
-- );
--
-- create table patient
-- (
--     id               bigserial primary key,
--     user_account_id  bigint unique not null references user_account (id),
--     personal_data_id bigint unique not null references personal_data (id)
-- );
--
-- insert into personal_data(name, phone, image, user_account_id)
-- values ('Ivan Ivanov', '+375336790382',
--         'https://cdn.vectorstock.com/i/1000x1000/18/05/businessman-or-programmer-avatar-profile-userpic-vector-7471805.webp',
--         1);
--
