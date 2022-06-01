/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost
 Source Database       : moyu_blog

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : utf-8

 Date: 05/11/2022 00:14:16 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `role` varchar(255) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png', '摸鱼大大', '摸鱼大大', '123456', '管理员'), ('2', 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png', '张三', '张三', '123456', '普通用户'), ('3049b0e639b3480aa8b09be13ade999d', null, '长沙惠子', '长沙惠子', '123456', '123456'), ('873dee0f09c9429daa62db3f20e5735e', null, '刘行', '刘行', '123456', '管理员'), ('ce22c97a7f68414ea685d40f4243aca2', null, '习近平', '习近平', '123456', '国家主席'), ('da3a485bd2354ab08e4f0c97484df647', null, '普京', '普京', '123456', '俄罗斯总统');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
