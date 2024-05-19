-- Roles
INSERT INTO `Subjects`.`users_roles` (`role_id`, `role_name`) VALUES
    ('1', 'ADMIN');

-- Users
INSERT INTO `Subjects`.`users` (registration_date, name, surname, phone_number, email, password_hash, role_id, deleted) VALUES
    ('2024-05-01', 'admin', '', '375015552368', 'test@email.com', '9e1774b83c29e5886cfad800a09529700838f61203851bde75bf8bea4e3a0d81', '1', '0');

-- Subjects
INSERT INTO `Subjects`.`subject` (name, hours) VALUES
    ('Математика', 150),
    ('Физика', 50),
    ('Языки программирования', 100),
    ('Структуры и алгоритмы обработки данных', 70);
