DELETE FROM auto_post;
ALTER TABLE auto_post ADD COLUMN car_id int not null references car(id);