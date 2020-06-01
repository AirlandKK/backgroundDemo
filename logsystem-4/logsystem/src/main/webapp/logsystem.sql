/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : logsystem

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-08-13 16:04:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ex_user
-- ----------------------------
DROP TABLE IF EXISTS `ex_user`;
CREATE TABLE `ex_user` (
  `user_id` varchar(500) NOT NULL COMMENT '用户id',
  `user_name` varchar(500) DEFAULT '' COMMENT '用户名',
  `user_password` varchar(500) DEFAULT NULL COMMENT '用户密码',
  `user_type` varchar(500) DEFAULT NULL COMMENT '用户类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `nick` varchar(500) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(500) DEFAULT NULL COMMENT '性别',
  `mail` varchar(500) DEFAULT NULL COMMENT '邮箱',
  `photo_url` varchar(500) DEFAULT NULL COMMENT '头像',
  `is_enable` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表 ';

-- ----------------------------
-- Records of ex_user
-- ----------------------------
INSERT INTO `ex_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '3', null, '234', null, null, null, '1');
INSERT INTO `ex_user` VALUES ('1487e809-bd9f-11e9-a5bc-e0d55e47da59', '2345', '202cb962ac59075b964b07152d234b70', null, '2019-08-13 15:51:19', null, null, null, null, '1');
INSERT INTO `ex_user` VALUES ('3bdcba2c-bd9f-11e9-a5bc-e0d55e47da59', '2346', '202cb962ac59075b964b07152d234b70', '2', '2019-08-13 15:52:25', '123', '男', '2801763423@qq.com', 'uploadimage/1565682856708曹力.png', '1');

-- ----------------------------
-- Table structure for ex_user_log
-- ----------------------------
DROP TABLE IF EXISTS `ex_user_log`;
CREATE TABLE `ex_user_log` (
  `id` varchar(500) NOT NULL,
  `content` blob DEFAULT NULL COMMENT '日志内容',
  `create_time` datetime DEFAULT NULL COMMENT '日志发表时间',
  `user_id` varchar(500) DEFAULT NULL COMMENT '用户id',
  `title` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_user_log
-- ----------------------------
INSERT INTO `ex_user_log` VALUES ('c0d3c9aa-bda0-11e9-a5bc-e0d55e47da59', 0x3C703E3334353334353C696D67207372633D22687474703A2F2F3137322E31362E302E3132383A383737382F75706C6F6164696D6167652F31353635363833333937323233E69BB9E58A9B2E706E6722207374796C653D226D61782D77696474683A313030253B223E3C2F703E, '2019-08-13 16:03:18', '3bdcba2c-bd9f-11e9-a5bc-e0d55e47da59', '345345');

-- ----------------------------
-- Table structure for ex_user_log_comment
-- ----------------------------
DROP TABLE IF EXISTS `ex_user_log_comment`;
CREATE TABLE `ex_user_log_comment` (
  `id` varchar(500) NOT NULL,
  `user_id` varchar(500) DEFAULT NULL COMMENT '用户id',
  `log_id` varchar(500) DEFAULT NULL COMMENT '日志id',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_user_log_comment
-- ----------------------------
INSERT INTO `ex_user_log_comment` VALUES ('c8e58af5-bda0-11e9-a5bc-e0d55e47da59', '3bdcba2c-bd9f-11e9-a5bc-e0d55e47da59', 'c0d3c9aa-bda0-11e9-a5bc-e0d55e47da59', '345345', '2019-08-13 16:03:31');

-- ----------------------------
-- Table structure for ex_user_reply
-- ----------------------------
DROP TABLE IF EXISTS `ex_user_reply`;
CREATE TABLE `ex_user_reply` (
  `id` varchar(500) NOT NULL,
  `user_id` varchar(500) DEFAULT NULL COMMENT '用户id',
  `comment_id` varchar(500) DEFAULT NULL COMMENT '评论id',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '回复内容',
  `create_time` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_user_reply
-- ----------------------------
