-- 删除user表若存在
DROP TABLE IF EXISTS `user`;
-- 创建user表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` tinyint(3) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `created_by` bigint(20) COMMENT '创建人',
  `created_time` datetime COMMENT '创建时间',
  `modified_by` bigint(20) COMMENT '修改人',
  `modified_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 comment '用户信息表';

-- 删除role表若存在
DROP TABLE IF EXISTS `role`;
-- 创建role表
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名',
  `role_desc` varchar(150) NOT NULL COMMENT '角色描述',
  `created_by` bigint(20) COMMENT '创建人',
  `created_time` datetime COMMENT '创建时间',
  `modified_by` bigint(20) COMMENT '修改人',
  `modified_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 comment '角色信息表';