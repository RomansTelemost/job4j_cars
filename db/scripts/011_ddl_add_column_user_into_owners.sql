DELETE FROM owners;
ALTER TABLE owners ADD COLUMN user_id int not null references auto_user(id);