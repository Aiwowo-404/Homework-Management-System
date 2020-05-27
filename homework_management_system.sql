/*
 Navicat Premium Data Transfer

 Source Server         : axw
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-ev3lbs2q.cd.tencentcdb.com:10136
 Source Schema         : homework_management_system

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 27/05/2020 20:17:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (0, '走进科学');
INSERT INTO `course` VALUES (1, '美好大自然');
INSERT INTO `course` VALUES (2, 'java');
INSERT INTO `course` VALUES (3, 'c++');
INSERT INTO `course` VALUES (4, '人工智能');
INSERT INTO `course` VALUES (5, '好奇的外星人');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `studentid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseid` int(11) NULL DEFAULT NULL,
  `answer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('0000001', '练习1', '怎么做人', NULL, 1, '17301173', 1, '做梦');
INSERT INTO `homework` VALUES ('0000002', '练习2', '怎么变态', NULL, 0, '17301173', 1, NULL);
INSERT INTO `homework` VALUES ('0000003', '练习3', '怎么变成傻子', NULL, 1, '17301173', 1, 'holyshit');
INSERT INTO `homework` VALUES ('1590581136707', '艾显威', 'asdas', NULL, 0, '17301173', 2, NULL);
INSERT INTO `homework` VALUES ('1590581136729', '艾显威', 'asdas', NULL, 0, '17301177', 2, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('17301173', '袁涛', 'axw112630');
INSERT INTO `student` VALUES ('17301177', '张泽光', '111');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `studentid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseid` int(11) NULL DEFAULT NULL,
  INDEX `student_course_course_id_fk`(`courseid`) USING BTREE,
  INDEX `student_course_student_id_fk`(`studentid`) USING BTREE,
  CONSTRAINT `student_course_course_id_fk` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_course_student_id_fk` FOREIGN KEY (`studentid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('17301173', 0);
INSERT INTO `student_course` VALUES ('17301173', 1);
INSERT INTO `student_course` VALUES ('17301173', 2);
INSERT INTO `student_course` VALUES ('17301173', 3);
INSERT INTO `student_course` VALUES ('17301173', 4);
INSERT INTO `student_course` VALUES ('17301173', 5);
INSERT INTO `student_course` VALUES ('17301177', 2);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('123456', '黑铁', 'axw');

-- ----------------------------
-- Table structure for teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course`  (
  `teacherid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseid` int(11) NULL DEFAULT NULL,
  INDEX `teacher_course_course_id_fk`(`courseid`) USING BTREE,
  INDEX `teacher_course_teacher_id_fk`(`teacherid`) USING BTREE,
  CONSTRAINT `teacher_course_course_id_fk` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_course_teacher_id_fk` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_course
-- ----------------------------
INSERT INTO `teacher_course` VALUES ('123456', 2);

SET FOREIGN_KEY_CHECKS = 1;
