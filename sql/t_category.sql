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

 Date: 05/11/2022 00:16:57 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
--  Records of `t_category`
-- ----------------------------
BEGIN;
INSERT INTO `t_category` VALUES ('1', '分类一'), ('2', '分类二'), ('2ad568e4f5f44131ad6c8fa55f4f0e63', '9'), ('4c8f0ac812024c04ba447f313405663f', '分类四'), ('61f594724c1e4c399243b19a6399ad4e', '8'), ('8e3607022a3b42499d2e1921e0289132', '7'), ('a5ff4f5e96984604af8d07ea61f57ada', '6'), ('eb8e2df26ec7401b9b2a9eaae095f41c', '分类五'), ('f88ef56e2c2c43cebf71803ea72c1a12', '分类三');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
