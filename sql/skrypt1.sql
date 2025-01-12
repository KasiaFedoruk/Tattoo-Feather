CREATE SCHEMA IF NOT EXISTS `tattoo_project`;

USE `tattoo_project`;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `appointments`;
DROP TABLE IF EXISTS `artists`;
DROP TABLE IF EXISTS `availability`;
DROP TABLE IF EXISTS `contact`;
DROP TABLE IF EXISTS `portfolio`;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,  
  `phone_number` INT NOT NULL
);

INSERT INTO `users` (`username`, `password`, `phone_number`) 
VALUES 
('admin', '$2a$12$Lc64FE0qaw5IAlqybvXjquPqBV4BtRSn096V2ht8SWJPb08VnTN1m', '333222111'),
('test', '$2a$12$Lc64FE0qaw5IAlqybvXjquPqBV4BtRSn096V2ht8SWJPb08VnTN1m', '111222333');

CREATE TABLE `artists` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `photo_URL` VARCHAR(255),
    `description` VARCHAR(255)
);

CREATE TABLE `contact` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `phone_number` VARCHAR(255) NOT NULL,
    `message` VARCHAR(255) NOT NULL
);

CREATE TABLE `appointments`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `artist_id` INT NOT NULL,
    `date` VARCHAR(50) NOT NULL,
    `time` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users(id),
    FOREIGN KEY (`artist_id`) REFERENCES artists(id)
);



CREATE TABLE `availability` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `date` VARCHAR(50) NOT NULL,
    `time` VARCHAR(50) NOT NULL,
    `id_artist` INT,
    FOREIGN KEY (`id_artist`) REFERENCES artists(id) ON DELETE CASCADE
);



CREATE TABLE `portfolio` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `photo_URL` VARCHAR(255) NOT NULL,
    `artist_id` INT,
    FOREIGN KEY (`artist_id`) REFERENCES artists(id) ON DELETE CASCADE
);