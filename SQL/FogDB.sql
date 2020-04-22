-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema fogdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fogdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fogdb` ;

-- -----------------------------------------------------
-- Table `fogdb`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`cities` (
  `zipcode` INT(11) NOT NULL,
  `cityname` VARCHAR(45) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`measurement_units`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`measurement_units` (
  `measurement_units_id` INT(11) NOT NULL AUTO_INCREMENT,
  `units` INT(11) NOT NULL,
  `c_width` TINYINT(4) NOT NULL,
  `c_length` TINYINT(4) NOT NULL,
  `ts_width` TINYINT(4) NOT NULL,
  `ts_length` TINYINT(4) NOT NULL,
  PRIMARY KEY (`measurement_units_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`user_proposition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`user_proposition` (
  `user_proposition_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `adress` VARCHAR(45) NOT NULL,
  `zipcode` INT(11) NOT NULL,
  `phone` INT(11) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `comments` TEXT NOT NULL,
  PRIMARY KEY (`user_proposition_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`orders` (
  `orders_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_proposition_id` INT(11) NOT NULL,
  `order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(45) NOT NULL DEFAULT 'Forespørgsel',
  `oc_width` INT(11) NOT NULL,
  `oc_length` INT(11) NOT NULL,
  `ots_width` INT(11) NULL DEFAULT NULL,
  `ots_length` INT(11) NULL DEFAULT NULL,
  `roof_type` VARCHAR(45) NOT NULL,
  `roof_material` VARCHAR(90) NOT NULL,
  `pitch` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orders_id`),
  INDEX `fk_userProp_orders_idx` (`user_proposition_id` ASC) VISIBLE,
  CONSTRAINT `fk_userProp_orders`
    FOREIGN KEY (`user_proposition_id`)
    REFERENCES `fogdb`.`user_proposition` (`user_proposition_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`rafter_spacing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`rafter_spacing` (
  `rafter_spacing_id` INT(11) NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NOT NULL,
  `beam_dimension` VARCHAR(45) NOT NULL,
  `beam_spacing` DECIMAL(10,2) NOT NULL,
  `rafter_length` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`rafter_spacing_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`roof`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`roof` (
  `roof_id` INT(11) NOT NULL AUTO_INCREMENT,
  `roof_type` VARCHAR(45) NOT NULL,
  `roof_category` VARCHAR(45) NOT NULL,
  `roof_material` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`roof_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`roof_pitch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`roof_pitch` (
  `roof_pitch_id` INT(11) NOT NULL AUTO_INCREMENT,
  `pitch` INT(11) NOT NULL,
  `factor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`roof_pitch_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fogdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogdb`.`users` (
  `users_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(20) NOT NULL DEFAULT 'costumer',
  PRIMARY KEY (`users_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
