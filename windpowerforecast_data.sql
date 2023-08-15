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

 Date: 14/08/2023 18:11:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(10) UNSIGNED NOT NULL COMMENT '父ID',
  `TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型',
  `item_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '显示名',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储值',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述说明',
  `extdata` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展JSON',
  `sort_id` smallint(6) NOT NULL DEFAULT 99 COMMENT '排序号',
  `is_editable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可改',
  `is_deletable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可删',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104004 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (100000, 0, 'GENDER', '性别', NULL, '', NULL, 99, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (100001, 100000, 'GENDER', '男', 'M', NULL, NULL, 1, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (100002, 100000, 'GENDER', '女', 'F', NULL, NULL, 2, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (100003, 100000, 'GENDER', '男、女', 'MF', NULL, NULL, 3, 1, 0, 0, '2020-09-18 10:50:58', '2020-09-21 09:39:35');
INSERT INTO `dictionary` VALUES (101000, 0, 'STATE', '运行状态', NULL, '', NULL, 99, 1, 0, 0, '2020-09-17 10:11:12', '2023-08-12 23:01:26');
INSERT INTO `dictionary` VALUES (101001, 101000, 'STATE', '正常', '1', NULL, NULL, 1, 1, 0, 0, '2020-09-17 10:11:12', '2023-08-12 23:02:54');
INSERT INTO `dictionary` VALUES (101002, 101000, 'STATE', '良好', '2', NULL, NULL, 2, 1, 0, 0, '2020-09-17 10:11:12', '2023-08-12 23:03:00');
INSERT INTO `dictionary` VALUES (101003, 101000, 'STATE', '异常', '3', NULL, NULL, 3, 1, 0, 0, '2020-09-17 10:46:52', '2023-08-12 23:03:00');
INSERT INTO `dictionary` VALUES (101004, 101000, 'STATE', '故障', '4', NULL, NULL, 4, 1, 0, 0, '2020-09-17 10:47:27', '2023-08-12 23:03:01');
INSERT INTO `dictionary` VALUES (102000, 0, 'BOUNDSTATUS', '绑定状态', NULL, NULL, NULL, 99, 1, 0, 0, '2020-09-23 10:34:55', '2020-09-23 10:34:55');
INSERT INTO `dictionary` VALUES (102001, 102000, 'BOUNDSTATUS', '已绑定', 'YBD', NULL, NULL, 1, 1, 0, 0, '2020-09-23 10:36:04', '2020-09-24 11:43:09');
INSERT INTO `dictionary` VALUES (102002, 102000, 'BOUNDSTATUS', '已解绑', 'YJB', NULL, NULL, 2, 1, 0, 0, '2020-09-23 10:36:04', '2020-09-24 11:43:14');
INSERT INTO `dictionary` VALUES (103000, 0, 'DIFFICULTY', '贫困程度', NULL, '', NULL, 99, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (103001, 103000, 'DIFFICULTY', '特殊困难', 'TSKN', NULL, NULL, 1, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (103002, 103000, 'DIFFICULTY', '一般困难', 'YBKN', NULL, NULL, 2, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (103003, 103000, 'DIFFICULTY', '不困难', 'BKN', NULL, NULL, 3, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (104000, 0, 'ROLE', '用户类型', NULL, NULL, NULL, 99, 1, 0, 0, '2023-02-25 16:07:55', '2023-02-25 16:07:55');
INSERT INTO `dictionary` VALUES (104001, 104000, 'ROLE', '超级管理员', '1', NULL, NULL, 99, 1, 0, 0, '2023-02-25 16:06:05', '2023-02-25 16:08:53');
INSERT INTO `dictionary` VALUES (104002, 104000, 'ROLE', '数据分析师', '2', NULL, NULL, 99, 1, 0, 0, '2023-02-25 16:06:48', '2023-08-12 23:00:48');
INSERT INTO `dictionary` VALUES (104003, 104000, 'ROLE', '风场运维师', '3', NULL, NULL, 99, 1, 0, 0, '2023-02-26 10:17:23', '2023-08-12 23:00:56');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `farm_id` bigint(20) NOT NULL,
  `state` int(10) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (2, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:33');
INSERT INTO `feedback` VALUES (3, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:36');
INSERT INTO `feedback` VALUES (4, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:37');
INSERT INTO `feedback` VALUES (5, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:37');
INSERT INTO `feedback` VALUES (6, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:38');
INSERT INTO `feedback` VALUES (7, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:38');
INSERT INTO `feedback` VALUES (8, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:38');
INSERT INTO `feedback` VALUES (9, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:38');
INSERT INTO `feedback` VALUES (11, 13, 3, '风机异常', './file/202308/-00c087c3ab244da585975d7b51d7bb02.csv', '2023-08-13 11:58:39');

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
  `role` int(11) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'rbl', 1, 18, 'rbl', 'rbl123.+', 111, 'binglinren@foxmail.com', './file/202307/-043276b6f0044a6596d6d84e36679e6d.jpg', '我非常热爱学习', 2, 0);
INSERT INTO `user` VALUES (2, NULL, NULL, NULL, 'test', '123', NULL, '2020141461056@stu.scu.edu.cn', './file/202308/-e9fa5e6ad01149ab865cd26c75548656.jpg', NULL, 2, 0);
INSERT INTO `user` VALUES (3, NULL, NULL, NULL, 'b1ur', '123', NULL, 'uxdha16@163.com', './file/202307/default.jpg', NULL, 2, 0);
INSERT INTO `user` VALUES (7, NULL, NULL, NULL, 'admin', 'admin', NULL, 'test', './file/202307/default.jpg', NULL, 1, 0);
INSERT INTO `user` VALUES (8, NULL, NULL, NULL, 'maintain', '123456', NULL, '1298775154@qq.com', './file/202307/default.jpg', NULL, 3, 0);

-- ----------------------------
-- Table structure for wind_farm_url
-- ----------------------------
DROP TABLE IF EXISTS `wind_farm_url`;
CREATE TABLE `wind_farm_url`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `farm_id` bigint(20) NOT NULL,
  `origin_file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `process_file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(11) NOT NULL DEFAULT 1,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wind_farm_url
-- ----------------------------
INSERT INTO `wind_farm_url` VALUES (3, 13, './file/202307/-21fbe63408544979a4711a8147b263a0.csv', NULL, '北京市/北京市/朝阳区', 'LYP-W018', 1, 0);
INSERT INTO `wind_farm_url` VALUES (4, 14, './file/202307/-06fa9f42df1c4db2af5958cdb773a8d4.csv', NULL, '四川省/成都市/武侯区', 'LYP-W017', 1, 0);
INSERT INTO `wind_farm_url` VALUES (5, 15, './file/202307/-bcc0de87becf446a9d609103eb33c6cd.csv', './static/KNN_IF_KNN-a133d24f3ac44cfe871a3646962cd590.csv', '湖北省/武汉市/江岸区', 'LYP-W012', 1, 0);
INSERT INTO `wind_farm_url` VALUES (6, 16, './file/202307/-9011458215854cc490ce8787db5b9778.csv', NULL, '福建省/厦门市/思明区', 'LYP-W029', 1, 0);
INSERT INTO `wind_farm_url` VALUES (11, 20, './file/202307/-9b54445862f44b9094b6f30a7d0b95ef.csv', './static/KNN_IF_KNN-9b54445862f44b9094b6f30a7d0b95ef.csv', '浙江省/温州市/鹿城区', 'LYP-W028', 1, 0);
INSERT INTO `wind_farm_url` VALUES (12, 21, './file/202308/-d5a526ec242c468ba1c24126442d18cd.csv', NULL, '河南省新郑市', '222', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
