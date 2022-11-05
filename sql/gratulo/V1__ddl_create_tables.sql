create table account (
    id serial primary key,
    login varchar(128) not null unique,
    password varchar(128) not null,
    phone varchar(128),
    tg_tag varchar(128),
    tg_group varchar (128)
);

create table event_category (
    id serial primary key,
    name varchar(512)
);

create table event (
    id serial primary key,
    account_id integer,
    ev_date timestamp,
    person varchar(512),
    ev_category_id integer,
    foreign key (account_id) references account(id),
    foreign key (ev_category_id) references event_category(id),
);