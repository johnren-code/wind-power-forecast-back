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

 Date: 16/07/2023 19:38:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wind_farm
-- ----------------------------
DROP TABLE IF EXISTS `wind_farm`;
CREATE TABLE `wind_farm`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `farm_id` bigint(20) NOT NULL,
  `datetime` datetime NOT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wind_speed_pred` float NULL DEFAULT NULL,
  `wind_direction` int(11) NOT NULL,
  `temperature` float NOT NULL,
  `humidity` int(11) NOT NULL,
  `pressure` int(11) NOT NULL,
  `prepower` int(11) NULL DEFAULT NULL,
  `wind_speed_real` float NULL DEFAULT NULL,
  `round_ws` float NOT NULL,
  `round_power` int(11) NULL DEFAULT NULL,
  `yd15` int(11) NULL DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT 1,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 988283 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
