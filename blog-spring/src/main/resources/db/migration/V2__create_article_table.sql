create table article(
    id varchar(255) primary key ,
    headline varchar(255) not null,
    body text not null,
    posted_at timestamp without time zone not null,
    created_at timestamp without time zone not null,
    updated_at timestamp without time zone not null,
    version integer not null
)