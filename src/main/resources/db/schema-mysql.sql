-- 删除user表若存在
DROP TABLE IF EXISTS `user`;
-- 创建user表
CREATE TABLE `user`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`          varchar(30) collate utf8mb4_0900_bin DEFAULT NULL COMMENT '姓名',
    `age`           tinyint(3)                           DEFAULT NULL COMMENT '年龄',
    `gender`        tinyint(1)                           DEFAULT NULL COMMENT '性别 1:男 0:女',
    `email`         varchar(50)                          DEFAULT NULL COMMENT '邮箱',
    'user_status'   tinyint(1)                           DEFAULT 1 COMMENT '用户状态 1:正常 2:禁用 3:删除',
    ‘password_type’ tinyint(1)                           DEFAULT 3 COMMENT '密码类型 1:交易密码 2:查询密码 3:登录密码',
    `created_by`    bigint(20) COMMENT '创建人',
    `created_time`  datetime                             default current_timestamp COMMENT '创建时间',
    `modified_by`   bigint(20) COMMENT '修改人',
    `modified_time` datetime on update current_timestamp COMMENT '修改时间',
    `deleted`       tinyint(1)                           DEFAULT 0 not null COMMENT '删除状态 0:未删除 1:已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 comment '用户信息表';

-- 删除role表若存在
DROP TABLE IF EXISTS `role`;
-- 创建role表
CREATE TABLE `role`
(
    `id`            bigint(20)                           NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_name`     varchar(30) collate utf8mb4_0900_bin NOT NULL COMMENT '角色名',
    `role_desc`     varchar(150)                         NOT NULL COMMENT '角色描述',
    `created_by`    bigint(20) COMMENT '创建人',
    `created_time`  datetime default current_timestamp COMMENT '创建时间',
    `modified_by`   bigint(20) COMMENT '修改人',
    `modified_time` datetime on update current_timestamp COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 comment '角色信息表';