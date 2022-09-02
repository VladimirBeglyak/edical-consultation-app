create table doctor_schedule
(
    id        bigserial primary key,
    create_at timestamp,
    doctor_id bigint,
    foreign key (doctor_id) references doctor (id)
);

create table patient_schedule
(
    id          bigserial primary key,
    create_at   timestamp,
    description text,
    patient_id  bigint,
    foreign key (patient_id) references patient (id)
);
