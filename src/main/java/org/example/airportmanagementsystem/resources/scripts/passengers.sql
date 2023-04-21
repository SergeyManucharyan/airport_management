create table public.passengers
(
    id      serial  primary key,
    name    varchar(50),
    phone   varchar(50),
    country varchar(50),
    city    varchar(50)
);