ALTER TABLE person ADD active tinyint;
UPDATE person SET active = 1; -- actualiza todos los registros anteriores