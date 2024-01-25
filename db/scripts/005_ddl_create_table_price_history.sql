CREATE TABLE PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   auto_post_id int not null references auto_post(id),
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);