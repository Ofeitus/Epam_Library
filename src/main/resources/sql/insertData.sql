-- Roles
INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES
    ('1', 'ADMIN'),
    ('2', 'MANAGER'),
    ('3', 'MEMBER');

-- Users
INSERT INTO `library`.`users` (user_id, registration_date, name, surname, phone_number, email, password_hash, role_id, deleted) VALUES
    ('0', '2021-06-01', 'Timothy', 'Tyushev', '375015552368', '2008655z@email.com', '9e1774b83c29e5886cfad800a09529700838f61203851bde75bf8bea4e3a0d81', '1', '0'),
    ('0', '2021-08-01', 'Nikita', 'Strachinski', '375015550100', 'strachinski@email.com', '598e7086cebecbf663325f771c0bf44382ea367fa0702a0c26c6978a1971ccb4', '2', '0'),
    ('0', '2021-09-01', 'Maxim', 'Melnikov', '', 'melnikov@email.com', '9e2939ae2775f6e406f052361b0cc679f6d83f742c9309a2dd781572c402f430', '3', '0'),
    ('0', '2021-10-01', 'Alex', 'Nevstruev', '375015550101', 'nevstruev@email.com', '1a95730e6577eab1f04dcbdc04d0154e01956458869a93b3d6e658b9648ff47e', '3', '0'),
    ('0', '2021-12-01', 'Kliment', 'Severin', '375015550102', 'severin@email.com', '6bd9ce43d5c204a559a81d33d4739199e0339de5cb3448470f6698f4ebf3716f', '3', '0'),
    ('0', '2021-12-01', 'Pavel', 'Soroka', '375015550103', 'soroka@email.com', 'b970d520b867ccd8b66c373f3d9dce395816e7c72e22b6325bb1563fa71d144b', '3', '0');

-- Book Categories
INSERT INTO `library`.`book_categories` (`category_id`, `category_name`) VALUES
    ('0', 'Художественная литература'),
    ('0', 'Учебное пособие');

-- Authors
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES
    ('0', 'Джоан', 'Роулинг'),
    ('0', 'Игорь', 'Мытько'),
    ('0', 'Андрей', 'Жвалевский'),
    ('0', 'Рэй', 'Брэдбери'),
    ('0', 'George', 'Orwell'),
    ('0', 'Агата', 'Кристи'),
    ('0', 'Марио', 'Пьюзо'),
    ('0', 'Джошуа', 'Блох');

-- Books
INSERT INTO `library`.`books` (`book_isbn`, `title`, `publication_year`, `category_id`, `language`, `key_words`) VALUES
    ('9785389074354', 'Гарри Поттер и философский камень', '2016', '1', 'Русский', 'Фентези, фантастика'),
    ('9785389077812', 'Гарри Поттер и Тайная комната', '2019', '1', 'Русский', 'Фентези'),
    ('9785389077881', 'Гарри Поттер и узник Азкабана', '2016', '1', 'Русский', 'Фентези'),
    ('9785389077898', 'Гарри Поттер и Кубок огня', '2019', '1', 'Русский', 'Фентези, фантастика'),
    ('9785389077904', 'Гарри Поттер и Орден Феникса', '2016', '1', 'Русский', 'Фентези'),
    ('9785389077911', 'Гарри Поттер и Принц-полукровка', '2016', '1', 'Русский', 'Фентези'),
    ('9785389077928', 'Гарри Поттер и Дары Смерти', '2015', '1', 'Русский', 'Фентези'),
    ('5941170688', 'Порри Гаттер и Каменный Философ', '2003', '1', 'Русский', ''),
    ('9785699923595', '451 градус по Фаренгейту', '2017', '1', 'Русский', ''),
    ('9785170801152', '1984', '2017', '1', 'English', ''),
    ('9785041034979', 'Десять негритят', '2021', '1', 'Русский', ''),
    ('9785041078713', 'Крестный отец', '2020', '1', 'Русский', ''),
    ('9785855823479', 'Java. Эффективное программирование', '2013', '2', 'Русский', 'Программирование');

-- Book has Author
INSERT INTO `library`.`book_has_author` (book_isbn, author_id) VALUES
    ('9785389074354', '1'),
    ('9785389077812', '1'),
    ('9785389077881', '1'),
    ('9785389077898', '1'),
    ('9785389077904', '1'),
    ('9785389077911', '1'),
    ('9785389077928', '1'),
    ('5941170688', '2'),
    ('5941170688', '3'),
    ('9785699923595', '4'),
    ('9785170801152', '5'),
    ('9785041034979', '6'),
    ('9785041078713', '7'),
    ('9785855823479', '8');

-- Copy of book statuses
INSERT INTO `library`.`copy_of_book_status` (copy_of_book_status_id, copy_of_book_status_value) VALUES
    ('1', 'AVAILABLE'),
    ('2', 'READING_ROOM'),
    ('3', 'RESERVED'),
    ('4', 'LOANED'),
    ('5', 'WRITTEN_OFF');

-- Copies of Books
INSERT INTO `library`.`copies_of_books` (inventory_id, receipt_date, price, book_isbn, copy_of_book_status_id) VALUES
    ('0', CURDATE(), '10.3', '9785389074354', '4'),
    ('0', CURDATE(), '20.5', '9785389077812', '1'),
    ('0', CURDATE(), '10.7', '9785389077881', '1'),
    ('0', CURDATE(), '10.5', '9785389077881', '1'),
    ('0', CURDATE(), '12.5', '9785389077898', '1'),
    ('0', CURDATE(), '40.4', '9785389077904', '4'),
    ('0', CURDATE(), '10.5', '9785389077911', '1'),
    ('0', CURDATE(), '4.5', '9785389077928', '3'),
    ('0', CURDATE(), '10.5', '5941170688', '4'),
    ('0', CURDATE(), '10.5', '9785699923595', '1'),
    ('0', CURDATE(), '30.5', '9785170801152', '1'),
    ('0', CURDATE(), '10.1', '9785041034979', '3'),
    ('0', CURDATE(), '8.5', '9785041078713', '1'),
    ('0', CURDATE(), '28.5', '9785855823479', '1'),
    ('0', CURDATE(), '28.5', '9785855823479', '1');

-- Loan statuses
INSERT INTO `library`.`loan_status` (loan_status_id, loan_status_value) VALUES
    ('1', 'ISSUED'),
    ('2', 'RETURNED'),
    ('3', 'FINED'),
    ('4', 'PAID');

-- Loans
INSERT INTO `library`.`loans` (loan_id, issue_date, due_date, return_date, fine_amount, user_id, inventory_id, loan_status_id) VALUES
    ('0', DATE_ADD(CURDATE(), INTERVAL -1 DAY), DATE_ADD(CURDATE(), INTERVAL 30-1 DAY), null, null, '3', '6', '1'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -26 DAY), DATE_ADD(CURDATE(), INTERVAL 30-26 DAY), null, null, '3', '9', '1'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -40 DAY), DATE_ADD(CURDATE(), INTERVAL 30-40 DAY), null, null, '3', '1', '1'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -50 DAY), DATE_ADD(CURDATE(), INTERVAL 30-50 DAY), DATE_ADD(CURDATE(), INTERVAL 40-50 DAY), '2', '3', '5', '3'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -60 DAY), DATE_ADD(CURDATE(), INTERVAL 30-60 DAY), DATE_ADD(CURDATE(), INTERVAL 35-60 DAY), '1', '3', '3', '4'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -100 DAY), DATE_ADD(CURDATE(), INTERVAL 30-100 DAY), DATE_ADD(CURDATE(), INTERVAL 10-100 DAY), null, '3', '10', '2');

-- Reservation statuses
INSERT INTO `library`.`reservation_status` (reservation_status_id, reservations_status_value) VALUES
    ('1', 'RESERVED'),
    ('2', 'READY_TO_ISSUE'),
    ('3', 'ISSUED');

-- Reservations
INSERT INTO `library`.`reservations` (reservation_id, date, user_id, inventory_id, reservation_status_id) VALUES
    ('0', DATE_ADD(CURDATE(), INTERVAL -1 DAY), '3', '8', '1'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -6 DAY), '3', '12', '2'),
    ('0', DATE_ADD(CURDATE(), INTERVAL -20 DAY), '3', '1', '3');