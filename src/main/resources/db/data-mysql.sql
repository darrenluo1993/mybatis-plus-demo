TRUNCATE TABLE `user`;
INSERT INTO `user` (id, name, age, email, created_by)
VALUES (1, 'root', 18, 'root@baomidou.com', 1),
       (2, 'Jone', 18, 'test1@baomidou.com', 1),
       (3, 'Jack', 20, 'test2@baomidou.com', 1),
       (4, 'Tom', 28, 'test3@baomidou.com', 1),
       (5, 'Sandy', 21, 'test4@baomidou.com', 1),
       (6, 'Billie', 24, 'test5@baomidou.com', 1);
TRUNCATE TABLE `role`;
INSERT INTO `role` (id, role_name, role_desc, created_by)
VALUES (1, 'admin', '管理员', 1),
       (2, 'user', '用户', 1);