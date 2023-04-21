create table public.pass_in_trip
(
    trip_id integer not null
        constraint pass_in_trip_trip__fk
            references public.trip,
    psg_id integer
        constraint pass_in_trip_passengers__fk
            references public.passengers,
    date            timestamp,
    place           varchar(5),
    pass_in_trip_id serial primary key
);
