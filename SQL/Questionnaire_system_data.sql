/*
 Navicat MySQL Data Transfer

 Source Server         : new
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 8.133.176.244:3306
 Source Schema         : Questionnaire_system_data

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/12/2020 14:23:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '答案描述',
  `question_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '问题id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '答案表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for attendant
-- ----------------------------
DROP TABLE IF EXISTS `attendant`;
CREATE TABLE `attendant`  (
  `idno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`idno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for invest
-- ----------------------------
DROP TABLE IF EXISTS `invest`;
CREATE TABLE `invest`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '问卷标题',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '问卷状态',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问卷表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `idno` int NOT NULL AUTO_INCREMENT,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idno`) USING BTREE,
  UNIQUE INDEX `member_email_uindex`(`email`) USING BTREE,
  UNIQUE INDEX `member_tel_uindex`(`tel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '问题描述',
  `invest_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT ' 问卷号',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for statistic
-- ----------------------------
DROP TABLE IF EXISTS `statistic`;
CREATE TABLE `statistic`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `invest_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '问卷id',
  `question_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '问题id',
  `answer_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '回答id',
  `user_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 162 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问卷统计' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- View structure for invest_data
-- ----------------------------
DROP VIEW IF EXISTS `invest_data`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `invest_data` AS select `statistic`.`invest_id` AS `invest_id`,`statistic`.`question_id` AS `question_id`,`statistic`.`answer_id` AS `answer_id`,count(0) AS `number` from `statistic` group by `statistic`.`invest_id`,`statistic`.`question_id`,`statistic`.`answer_id`;

-- ----------------------------
-- View structure for invest_question_answer
-- ----------------------------
DROP VIEW IF EXISTS `invest_question_answer`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `invest_question_answer` AS select `i`.`id` AS `invest_id`,`i`.`title` AS `title`,`q`.`id` AS `question_id`,`q`.`content` AS `question_content`,`a`.`content` AS `answer_content` from ((`invest` `i` join `question` `q`) join `answer` `a`) where ((`i`.`id` = `q`.`invest_id`) and (`q`.`id` = `a`.`question_id`));

SET FOREIGN_KEY_CHECKS = 1;
