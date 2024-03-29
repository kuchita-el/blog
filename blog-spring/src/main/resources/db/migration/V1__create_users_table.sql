create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);

create table authorities
(
    username  varchar(50) not null references users (username),
    authority varchar(50) not null,
    unique (username, authority)
);
