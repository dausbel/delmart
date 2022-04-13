drop table if exists phones CASCADE;

drop table if exists users CASCADE;

create table users
(
    id            bigint       not null,
    city_code     varchar(255) not null,
    country_code  varchar(255) not null,
    creation_time date         not null,
    email         varchar(255) not null,
    last_login    date,
    name          varchar(255) not null,
    password      varchar(255) not null,
    token         varchar(255) not null,
    update_time   date,
    primary key (id)
);

create table phones
(
    id      bigint generated by default as identity,
    phone   varchar(255),
    user_id bigint not null,
    primary key (id)
);

alter table phones
    add constraint FKmg6d77tgqfen7n1g763nvsqe3 foreign key (user_id) references users
