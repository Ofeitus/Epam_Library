-- Roles
INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES ('2', 'MANAGER');
INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES ('3', 'MEMBER');

-- Users
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Timothy', 'Tyushev', '2008655z@gmail.com', '9e1774b83c29e5886cfad800a09529700838f61203851bde75bf8bea4e3a0d81', '1', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Nikita', 'Strachinski', 'strachinski@email.com', '598e7086cebecbf663325f771c0bf44382ea367fa0702a0c26c6978a1971ccb4', '2', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Maxim', 'Melnikov', 'melnikov@email.com', '9e2939ae2775f6e406f052361b0cc679f6d83f742c9309a2dd781572c402f430', '3', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Alex', 'Nevstruev', 'nevstruev@email.com', '1a95730e6577eab1f04dcbdc04d0154e01956458869a93b3d6e658b9648ff47e', '3', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Kliment', 'Severin', 'severin@email.com', '6bd9ce43d5c204a559a81d33d4739199e0339de5cb3448470f6698f4ebf3716f', '3', '0');

-- Book Categories
INSERT INTO `library`.`book_categories` (`category_id`, `name`) VALUES ('0', 'Фентези');
INSERT INTO `library`.`book_categories` (`category_id`, `name`) VALUES ('0', 'Роман-антиутопия');
INSERT INTO `library`.`book_categories` (`category_id`, `name`) VALUES ('0', 'Детектив');

-- Authors
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'Джоан', 'Роулинг');
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'Игорь', 'Мытько');
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'Андрей', 'Жвалевский');
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'Рэй', 'Брэдбери');
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'George', 'Orwell');
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'Агата', 'Кристи');
INSERT INTO `library`.`authors` (`author_id`, `name`, `surname`) VALUES ('0', 'Марио', 'Пьюзо');

-- Books
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785389074354', 'Гарри Поттер и философский камень', '2016', '1', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('5941170688', 'Порри Гаттер и Каменный Философ', '2003', '1', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785699923595', '451 градус по Фаренгейту', '2017', '2', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785170801152', '1984', '2017', '2', 'English');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785041034979', 'Десять негритят', '2021', '3', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785041078713', 'Крестный отец', '2020', '3', 'Русский');

-- Book has Author
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('1', '9785389074354');
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('2', '5941170688');
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('3', '5941170688');
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('4', '9785699923595');
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('5', '9785170801152');
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('6', '9785041034979');
INSERT INTO `library`.`book_has_author` (author_id, isbn) VALUES ('7', '9785041078713');