/*
 Navicat Premium Data Transfer

 Source Server         : smartsearch
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : smartsearch.c7suc62aqtqi.ca-central-1.rds.amazonaws.com:3306
 Source Schema         : smartsearch

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 30/03/2024 16:01:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `keyword` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` varchar(45) COLLATE utf8mb3_bin NOT NULL,
  `user_id` varchar(45) COLLATE utf8mb3_bin DEFAULT NULL,
  `history_id` varchar(45) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `history_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
