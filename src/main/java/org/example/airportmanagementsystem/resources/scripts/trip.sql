create table public.trip
(
    trip_id    integer not null
        primary key,
    company_id integer
        references public.companies,
    airplane   varchar(10),
    town_from  varchar(20),
    town_to    varchar(20),
    time_out   timestamp,
    time_in    timestamp
);