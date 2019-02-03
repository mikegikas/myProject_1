CREATE DATABASE `myproject`;
USE `myproject`;

CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(30) NOT NULL,
    `fname` VARCHAR(30),
    `lname` VARCHAR(30),
    `role` INT NOT NULL,
    PRIMARY KEY (`id`, `username`)
);

CREATE TABLE `messages` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `sender` VARCHAR(30) NOT NULL,
    `receiver` VARCHAR(30) NOT NULL,
    `date` VARCHAR(30) NOT NULL,
    `data` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`, `sender`),
    FOREIGN KEY (`sender`) REFERENCES `users` (`username`)
);

CREATE TABLE `messages_inbox` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `messages_id` INT NOT NULL,
    `sender_id` VARCHAR(30) NOT NULL,
    `data` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`messages_id`) REFERENCES `messages` (`id`)
);

CREATE TABLE `messages_sent` ( 
	`id` INT NOT NULL AUTO_INCREMENT,
    `messages_id` INT NOT NULL,
    `receiver_id` VARCHAR(30) NOT NULL,
    `data` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
	FOREIGN KEY (`messages_id`) REFERENCES `messages` (`id`)
);

