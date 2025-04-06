DROP TABLE IF EXISTS `items-db`.`item`;

CREATE TABLE `items-db`.`item` (
       `id` INT NOT NULL AUTO_INCREMENT,
       `name` VARCHAR(128) NOT NULL,
       PRIMARY KEY (`id`));