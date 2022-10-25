create table clientss
(
    id       uniqueidentifier   not null
        primary key,
    username varchar default 50 not null,
    email    varchar default 50 not null,
    password varchar default 60 not null
)
go

