CREATE SCHEMA `credit_card_db` ;

CREATE TABLE `credit_card_db`.`credit_cards` (
  `credit_card_number` INT NOT NULL,
  `date_time_added` DATETIME NOT NULL,
  `country` CHAR(2) NOT NULL,
  PRIMARY KEY (`credit_card_number`));

CREATE TABLE `credit_card_db`.`credit_cards_queue` (
  `credit_card_number` INT NOT NULL,
  `date_time_added` INT NOT NULL,
  PRIMARY KEY (`idcredit_cards_queue`));

CREATE TABLE `credit_card_db`.`countries` (
  `country_code` VARCHAR(2) NOT NULL,
  `country_name` VARCHAR(45) NOT NULL,
  `banned` TINYINT(1) NOT NULL);

