DROP TABLE IF EXISTS `wiki` CASCADE;

CREATE TABLE `wiki`
(
    `id`    bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` varchar(255)       NOT NULL
);

CREATE TABLE IF NOT EXISTS `users` (
    email VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);