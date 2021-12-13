-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Library
-- -----------------------------------------------------

DROP DATABASE IF EXISTS `Library`;

-- -----------------------------------------------------
-- Schema Library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Library` DEFAULT CHARACTER SET utf8 ;
USE `Library` ;

-- -----------------------------------------------------
-- Table `Library`.`book_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`book_categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`books` (
  `isbn` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NULL,
  `publication_year` INT NULL,
  `category_id` INT NOT NULL,
  `language` VARCHAR(45) NULL,
  PRIMARY KEY (`isbn`),
  INDEX `fk_book_book_cetergory1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_book_cetergory1`
    FOREIGN KEY (`category_id`)
    REFERENCES `Library`.`book_categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`authors` (
  `author_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  PRIMARY KEY (`author_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`book_has_author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`book_has_author` (
  `author_id` INT NOT NULL,
  `isbn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`author_id`, `isbn`),
  INDEX `fk_book_has_author_author1_idx` (`author_id` ASC) VISIBLE,
  INDEX `fk_book_has_author_book_idx` (`isbn` ASC) VISIBLE,
  CONSTRAINT `fk_book_has_author_book`
    FOREIGN KEY (`isbn`)
    REFERENCES `Library`.`books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_author_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `Library`.`authors` (`author_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`users_roles` (
  `role_id` INT NOT NULL,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password_hash` VARCHAR(90) NULL,
  `user_role_id` INT NOT NULL,
  `deleted` TINYINT(1) NULL,
  INDEX `fk_user_user_role1_idx` (`user_role_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_user_role1`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `Library`.`users_roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`loans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`loans` (
  `loan_id` INT NOT NULL AUTO_INCREMENT,
  `issue_date` DATE NULL,
  `due_date` DATE NULL,
  `return_date` DATE NULL,
  `fine_amount` DECIMAL(10,2) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`loan_id`),
  INDEX `fk_loan_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_loan_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`copies_of_books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`copies_of_books` (
  `inventory_id` INT NOT NULL AUTO_INCREMENT,
  `book_isbn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`inventory_id`),
  INDEX `fk_book_item_book1_idx` (`book_isbn` ASC) VISIBLE,
  CONSTRAINT `fk_book_item_book1`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `Library`.`books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`loan_has_copy_of_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`loan_has_copy_of_book` (
  `copy_of_book_inventory_id` INT NOT NULL,
  `loan_id` INT NOT NULL,
  PRIMARY KEY (`copy_of_book_inventory_id`, `loan_id`),
  INDEX `fk_loan_has_book_item_book_item1_idx` (`copy_of_book_inventory_id` ASC) VISIBLE,
  INDEX `fk_loan_has_book_item_loan1_idx` (`loan_id` ASC) VISIBLE,
  CONSTRAINT `fk_loan_has_book_item_loan1`
    FOREIGN KEY (`loan_id`)
    REFERENCES `Library`.`loans` (`loan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_has_book_item_book_item1`
    FOREIGN KEY (`copy_of_book_inventory_id`)
    REFERENCES `Library`.`copies_of_books` (`inventory_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`reservation_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`reservation_status` (
  `reservation_status_id` INT NOT NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`reservation_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`reservations` (
  `reservation_id` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `isbn` VARCHAR(45) NOT NULL,
  `date` DATE NULL,
  `reservation_status_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `fk_user_has_book_book1_idx` (`isbn` ASC) VISIBLE,
  INDEX `fk_user_has_book_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_reservation_reservation_status1_idx` (`reservation_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_book_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_book_book1`
    FOREIGN KEY (`isbn`)
    REFERENCES `Library`.`books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_reservation_status1`
    FOREIGN KEY (`reservation_status_id`)
    REFERENCES `Library`.`reservation_status` (`reservation_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Library`.`fines_payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Library`.`fines_payments` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `date` DATE NULL,
  `amount` DECIMAL(10,2) NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_fines_payments_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_fines_payments_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;