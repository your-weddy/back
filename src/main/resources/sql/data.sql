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
