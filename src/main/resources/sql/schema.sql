DROP TABLE IF EXISTS `wiki` CASCADE;

CREATE TABLE `wiki`
(
    `id`    bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` varchar(255)       NOT NULL
);
