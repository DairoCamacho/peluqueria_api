create table person(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`id`)
);