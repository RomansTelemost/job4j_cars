CREATE TABLE engine (
    id serial primary key,
    name varchar(255) not null
);

create table car (
    id serial primary key,
    name varchar(255) not null,
    engine_id int not null references engine(id)
);

create table owners(
    id serial primary key,
    name varchar(255) not null
);

create table history_owner(
    id serial primary key,
    created timestamp not null,
    owner_id int not null references owners(id),
    car_id int not null references car(id),
    UNIQUE (owner_id, car_id)
);