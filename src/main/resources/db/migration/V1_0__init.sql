create table message
(
    message_id uuid not null,
    image      uuid,
    status     smallint,
    text       varchar(1000),
    primary key (message_id)
)