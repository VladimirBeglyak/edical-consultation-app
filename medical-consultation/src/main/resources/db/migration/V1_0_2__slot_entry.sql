create table slot_entry
(
    id          bigserial primary key,
    start_time  timestamp,
    status_type varchar(32),
    doctor_id   bigint,
    patient_id  bigint,
    foreign key (doctor_id) references user_account (id),
    foreign key (patient_id) references user_account (id)
);
