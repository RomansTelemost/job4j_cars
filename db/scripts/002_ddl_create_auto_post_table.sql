create table auto_post
(
id serial primary key,
description varchar,
created timestamp not null,
auto_user_id int references auto_post(id) not null
);