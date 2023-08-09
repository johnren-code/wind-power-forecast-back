/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50739 (5.7.39-log)
 Source Host           : localhost:3306
 Source Schema         : windpowerforecast

 Target Server Type    : MySQL
 Target Server Version : 50739 (5.7.39-log)
 File Encoding         : 65001

 Date: 09/08/2023 23:01:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` tinyint(1) NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone_number` bigint(20) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'rbl', 1, 18, 'rbl', 'rbl123.+', 111, 'binglinren@foxmail.com', './file/202307/-043276b6f0044a6596d6d84e36679e6d.jpg', '我非常热爱学习', 0);
INSERT INTO `user` VALUES (2, NULL, NULL, NULL, 'test', '123', NULL, '2020141461056@stu.scu.edu.cn', './file/202307/default.jpg', NULL, 0);
INSERT INTO `user` VALUES (3, NULL, NULL, NULL, 'b1ur', '123', NULL, 'uxdha16@163.com', './file/202307/default.jpg', NULL, 0);
INSERT INTO `user` VALUES (4, NULL, NULL, NULL, 'admin', '123', NULL, '1010459502@qq.com', './file/202307/default.jpg', NULL, 0);
INSERT INTO `user` VALUES (5, NULL, NULL, NULL, 'tttt', '123', NULL, 'llbbrren@gmail.com', './file/202307/default.jpg', NULL, 0);
INSERT INTO `user` VALUES (6, NULL, NULL, NULL, 'hb', '123456', NULL, '1298775154@qq.com', './file/202307/default.jpg', NULL, 0);

-- ----------------------------
-- Table structure for wind_farm_url
-- ----------------------------
DROP TABLE IF EXISTS `wind_farm_url`;
CREATE TABLE `wind_farm_url`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `farm_id` bigint(20) NOT NULL,
  `origin_file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT 1,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wind_farm_url
-- ----------------------------
INSERT INTO `wind_farm_url` VALUES (3, 13, './file/202307/-21fbe63408544979a4711a8147b263a0.csv', NULL, 1, 0);
INSERT INTO `wind_farm_url` VALUES (4, 14, './file/202307/-06fa9f42df1c4db2af5958cdb773a8d4.csv', NULL, 1, 0);
INSERT INTO `wind_farm_url` VALUES (5, 15, './file/202307/-bcc0de87becf446a9d609103eb33c6cd.csv', './static/KNN_IF_KNN-a133d24f3ac44cfe871a3646962cd590.csv', 1, 0);
INSERT INTO `wind_farm_url` VALUES (6, 16, './file/202307/-9011458215854cc490ce8787db5b9778.csv', NULL, 1, 0);
INSERT INTO `wind_farm_url` VALUES (11, 20, './file/202307/-9b54445862f44b9094b6f30a7d0b95ef.csv', './static/KNN_IF_KNN-9b54445862f44b9094b6f30a7d0b95ef.csv', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
