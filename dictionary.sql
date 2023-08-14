/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50739 (5.7.39-log)
 Source Host           : localhost:3306
 Source Schema         : springboot_demo

 Target Server Type    : MySQL
 Target Server Version : 50739 (5.7.39-log)
 File Encoding         : 65001

 Date: 11/08/2023 18:51:34
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
) ENGINE = InnoDB AUTO_INCREMENT = 104004 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (100000, 0, 'GENDER', '性别', NULL, '', NULL, 99, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (100001, 100000, 'GENDER', '男', 'M', NULL, NULL, 1, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (100002, 100000, 'GENDER', '女', 'F', NULL, NULL, 2, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (100003, 100000, 'GENDER', '男、女', 'MF', NULL, NULL, 3, 1, 0, 0, '2020-09-18 10:50:58', '2020-09-21 09:39:35');
INSERT INTO `dictionary` VALUES (101000, 0, 'STATUS', '审核状态', NULL, '', NULL, 99, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (101001, 101000, 'STATUS', '通过', 'TG', NULL, NULL, 1, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (101002, 101000, 'STATUS', '不通过', 'BTG', NULL, NULL, 2, 1, 0, 0, '2020-09-17 10:11:12', '2020-09-17 10:11:12');
INSERT INTO `dictionary` VALUES (101003, 101000, 'STATUS', '待审核', 'DSH', NULL, NULL, 3, 1, 0, 0, '2020-09-17 10:46:52', '2020-09-17 10:46:52');
INSERT INTO `dictionary` VALUES (101004, 101000, 'STATUS', '已取消', 'YQX', NULL, NULL, 4, 1, 0, 0, '2020-09-17 10:47:27', '2020-09-17 10:47:27');
INSERT INTO `dictionary` VALUES (101005, 101000, 'STATUS', '待提交', 'DTJ', NULL, NULL, 5, 1, 0, 0, '2020-09-18 17:50:46', '2020-09-18 17:50:46');
INSERT INTO `dictionary` VALUES (102000, 0, 'BOUNDSTATUS', '绑定状态', NULL, NULL, NULL, 99, 1, 0, 0, '2020-09-23 10:34:55', '2020-09-23 10:34:55');
INSERT INTO `dictionary` VALUES (102001, 102000, 'BOUNDSTATUS', '已绑定', 'YBD', NULL, NULL, 1, 1, 0, 0, '2020-09-23 10:36:04', '2020-09-24 11:43:09');
INSERT INTO `dictionary` VALUES (102002, 102000, 'BOUNDSTATUS', '已解绑', 'YJB', NULL, NULL, 2, 1, 0, 0, '2020-09-23 10:36:04', '2020-09-24 11:43:14');
INSERT INTO `dictionary` VALUES (103000, 0, 'DIFFICULTY', '贫困程度', NULL, '', NULL, 99, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (103001, 103000, 'DIFFICULTY', '特殊困难', 'TSKN', NULL, NULL, 1, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (103002, 103000, 'DIFFICULTY', '一般困难', 'YBKN', NULL, NULL, 2, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (103003, 103000, 'DIFFICULTY', '不困难', 'BKN', NULL, NULL, 3, 1, 0, 0, '2020-09-24 10:57:53', '2020-09-24 10:57:53');
INSERT INTO `dictionary` VALUES (104000, 0, 'ROLE', '用户类型', NULL, NULL, NULL, 99, 1, 0, 0, '2023-02-25 16:07:55', '2023-02-25 16:07:55');
INSERT INTO `dictionary` VALUES (104001, 104000, 'ROLE', '超级管理员', '1', NULL, NULL, 99, 1, 0, 0, '2023-02-25 16:06:05', '2023-02-25 16:08:53');
INSERT INTO `dictionary` VALUES (104002, 104000, 'ROLE', '普通用户', '2', NULL, NULL, 99, 1, 0, 0, '2023-02-25 16:06:48', '2023-02-25 17:53:59');
INSERT INTO `dictionary` VALUES (104003, 104000, 'ROLE', '学生', '3', NULL, NULL, 99, 1, 0, 0, '2023-02-26 10:17:23', '2023-02-26 10:17:23');

SET FOREIGN_KEY_CHECKS = 1;
