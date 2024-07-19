DELETE FROM file;
ALTER TABLE file ADD CONSTRAINT path_unique unique(path);
ALTER TABLE file ALTER COLUMN path set not null;