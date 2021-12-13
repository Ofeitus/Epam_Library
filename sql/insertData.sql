INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES ('2', 'MANAGER');
INSERT INTO `library`.`users_roles` (`role_id`, `role_name`) VALUES ('3', 'MEMBER');

INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Pavel', 'Soroka', 'sorokaPavel@email.com', '9e1774b83c29e5886cfad800a09529700838f61203851bde75bf8bea4e3a0d81', '1', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Nikita', 'Strachinski', 'sorokaPavel1@email.com', '598e7086cebecbf663325f771c0bf44382ea367fa0702a0c26c6978a1971ccb4', '2', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Maxim', 'Melnikov', 'sorokaPavel2@email.com', '9e2939ae2775f6e406f052361b0cc679f6d83f742c9309a2dd781572c402f430', '3', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Alex', '', 'sorokaPavel3@email.com', '1a95730e6577eab1f04dcbdc04d0154e01956458869a93b3d6e658b9648ff47e', '3', '0');
INSERT INTO `library`.`users` (user_id, name, surname, email, password_hash, user_role_id, deleted) VALUES ('0', 'Pavel4', 'Soroka4', 'sorokaPavel4@email.com', '6bd9ce43d5c204a559a81d33d4739199e0339de5cb3448470f6698f4ebf3716f', '3', '0');

INSERT INTO `library`.`book_categories` (`category_id`, `name`) VALUES ('0', 'Фентези');
INSERT INTO `library`.`book_categories` (`category_id`, `name`) VALUES ('0', 'Роман-антиутопия');
INSERT INTO `library`.`book_categories` (`category_id`, `name`) VALUES ('0', 'Детектив');

INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785389074354', 'Гарри Поттер и философский камень', '2016', '1', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785699923595', '451 градус по Фаренгейту', '2017', '2', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785170801152', '1984', '2017', '2', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785041034979', 'Десять негритят', '2021', '3', 'Русский');
INSERT INTO `library`.`books` (`isbn`, `title`, `publication_year`, `category_id`, `language`) VALUES ('9785041078713', 'Крестный отец', '2020', '3', 'Русский');
