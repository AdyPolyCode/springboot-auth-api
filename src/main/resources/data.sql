insert into room (id, type, capacity) values (1, 'biology', 25);
insert into room (id, type, capacity) values (2, 'chemistry', 25);
insert into room (id, type, capacity) values (3, 'physics', 25);

insert  into student (id, first_name, last_name, age, phone_number, created_at, updated_at) values (1, 'Alfred', 'Sed', 19, 123456789, '2022-03-24', '2022-03-24');
insert  into student (id, first_name, last_name, age, phone_number, created_at, updated_at) values (2, 'Martha', 'Pinto', 20, 123456788, '2022-03-24', '2022-03-24');
insert  into student (id, first_name, last_name, age, phone_number, created_at, updated_at) values (3, 'Jonny', 'Storm', 18, 123456787, '2022-03-24', '2022-03-24');

insert  into teacher (id, first_name, last_name, age, phone_number, created_at, updated_at) values (1, 'Bob', 'Mayer', 56, 123456786, '2022-03-24', '2022-03-24');
insert  into teacher (id, first_name, last_name, age, phone_number, created_at, updated_at) values (2, 'Katy', 'Stronghold', 50, 123456785, '2022-03-24', '2022-03-24');
insert  into teacher (id, first_name, last_name, age, phone_number, created_at, updated_at) values (3, 'Jonathan', 'Bold', 48, 123456784, '2022-03-24', '2022-03-24');


insert into subject (id, type, description, length_in_minutes, room_id, teacher_id) values (1, 'biology', 'Biology is a natural science discipline that studies living things', 45, 1, 1);
insert into subject (id, type, description, length_in_minutes, room_id, teacher_id) values (2, 'chemistry', 'Chemistry is the study of substances—that is, elements and compounds', 45, 2, 2);
insert into subject (id, type, description, length_in_minutes, room_id, teacher_id) values (3, 'physics', 'Physics is the branch of science that deals with the structure of matter and how the fundamental constituents of the universe interact', 45, 3, 3);

insert into student_subject (student_id, subject_id) values (1, 1);
insert into student_subject (student_id, subject_id) values (1, 2);

insert into student_subject (student_id, subject_id) values (2, 2);
insert into student_subject (student_id, subject_id) values (2, 3);

insert into student_subject (student_id, subject_id) values (3, 1);
insert into student_subject (student_id, subject_id) values (3, 2);
insert into student_subject (student_id, subject_id) values (3, 3);