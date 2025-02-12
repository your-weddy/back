INSERT INTO `wiki` (`title`)
VALUES ('위키 제목');

insert into `member` (`email`, `name`, `profile_image_url`, `created_at`, `updated_at`, `is_deleted`)
values ('x@a.com', 'tester1', 'url', current_timestamp, null, 0);
insert into `member` (`email`, `name`, `profile_image_url`, `created_at`, `updated_at`, `is_deleted`)
values ('y@a.com', 'tester2', 'url', current_timestamp, null, 0);

insert into `checklist` (`member_id`, `d_day`, `created_at`, `updated_at`, `is_deleted`)
values (1, 100, current_timestamp, null, 0);
insert into `checklist` (`member_id`, `d_day`, `created_at`, `updated_at`, `is_deleted`)
values (2, null, current_timestamp, null, 0);

insert into `large_category_item` (`id`, `checklist_id`, `title`, `created_at`, `updated_at`, `is_deleted`)
values (1, 1, 'test', current_timestamp, null, 0);

INSERT INTO `small_category_item_assignee` (`id`,`name`)
VALUES (1, '나');
INSERT INTO `small_category_item_assignee` (`id`,`name`)
VALUES (2, '짝궁');

INSERT INTO `small_category_item_status` (`id`,`name`)
VALUES (1, '시작전');
INSERT INTO `small_category_item_status` (`id`,`name`)
VALUES (2, '진행중');
INSERT INTO `small_category_item_status` (`id`,`name`)
VALUES (3, '완료');

INSERT INTO `small_category_item`
(`id`, `large_category_item_id`, `title`, `due_date`, `assignee_id`, `body`, `status_id`, `amount`, `created_at`, `updated_at`, `is_deleted`)
VALUES
(1, 1, 'Book a venue', '2025-06-01', 1, '<p>Find and book a wedding venue.</p>', 1, 5000000, NOW(), NOW(), FALSE),
(2, 1, 'Visit venue options', '2025-05-15', 1, '<p>Visit 3-5 venues and compare.</p>', 2, 0, NOW(), NOW(), FALSE),
(3, 1, 'Choose wedding dress', '2025-05-20', 1, '<p>Try on different styles and decide.</p>', 1, 2000000, NOW(), NOW(), FALSE),
(4, 1, 'Finalize guest list', '2025-05-10', 2, '<p>Confirm attendees and send invites.</p>', 2, 0, NOW(), NOW(), FALSE);
