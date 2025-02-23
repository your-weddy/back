DROP TABLE IF EXISTS `wiki` CASCADE;

CREATE TABLE `wiki`
(
    `id`    bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` varchar(255)       NOT NULL
);

CREATE TABLE `member`
(
    `id`                bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `email`             varchar(255),
    `name`              varchar(255),
    `profile_image_url` varchar(255) COMMENT 'link to user profile image',
    `oauth_id`           varchar(255),
    `created_at`        timestamp,
    `updated_at`        timestamp,
    `is_deleted`        bool
);

CREATE TABLE `checklist`
(
    `id`         bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `member_id`  bigint             NOT NULL,
    `d_day`      integer,
    `created_at` timestamp,
    `updated_at` timestamp,
    `is_deleted` bool
);

CREATE TABLE `large_category_item`
(
    `id`           bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `checklist_id` bigint             NOT NULL,
    `title`        varchar(255),
    `created_at`   timestamp,
    `updated_at`   timestamp,
    `is_deleted`   bool
);

CREATE TABLE `small_category_item`
(
    `id`                     bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `large_category_item_id` bigint             NOT NULL,
    `title`                  varchar(255),
    `due_date`               date COMMENT 'this item should be done before this date',
    `assignee_id`            bigint,
    `body`                   text COMMENT 'store rich text in HTML format',
    `status_id`              bigint,
    `amount`                 bigint,
    `sequence`               bigint,
    `created_at`             timestamp,
    `updated_at`             timestamp,
    `is_deleted`             bool
);

CREATE TABLE `small_category_item_status`
(
    `id`   bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255)
);

CREATE TABLE `small_category_item_assignee`
(
    `id`   bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255)
);

ALTER TABLE `checklist`
    ADD FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `large_category_item`
    ADD FOREIGN KEY (`checklist_id`) REFERENCES `checklist` (`id`);

ALTER TABLE `small_category_item`
    ADD FOREIGN KEY (`large_category_item_id`) REFERENCES `large_category_item` (`id`);

ALTER TABLE `small_category_item`
    ADD FOREIGN KEY (`status_id`) REFERENCES `small_category_item_status` (`id`);

ALTER TABLE `small_category_item`
    ADD FOREIGN KEY (`assignee_id`) REFERENCES `small_category_item_assignee` (`id`);
