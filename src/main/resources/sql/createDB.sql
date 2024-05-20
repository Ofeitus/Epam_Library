-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Subjects
-- -----------------------------------------------------

DROP DATABASE IF EXISTS `Subjects`;

-- -----------------------------------------------------
-- Schema Subjects
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Subjects` DEFAULT CHARACTER SET utf8 ;
USE `Subjects` ;

-- -----------------------------------------------------
-- Table `Subjects`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Subjects`.`users_roles` (
  `role_id` INT NOT NULL,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Subjects`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Subjects`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `registration_date` DATE NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `phone_number` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `password_hash` VARCHAR(90) NULL,
  `role_id` INT NOT NULL,
  `deleted` TINYINT NOT NULL,
  INDEX `fk_user_user_role1_idx` (`role_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `Subjects`.`users_roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Subjects`.`loan_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Subjects`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(145) NULL,
  `hours` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
