insert into user_details (birth_date, name)
values (current_date(), 'phanquiduc'),
       (current_date(), 'skythien'),
       (current_date(), 'smiley');

insert into post(description, user_id)
values ('This is fun!', 1),
       ('This is fun as hell!', 1),
       ('This is not fun at all!', 2),
       ('You should delete this!', 2),
       ('This look like sh*t!', 3);