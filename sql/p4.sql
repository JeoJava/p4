/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : p4

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-06-23 07:26:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for leave_msg
-- ----------------------------
DROP TABLE IF EXISTS `leave_msg`;
CREATE TABLE `leave_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `mid` int(11) NOT NULL COMMENT '药品编号',
  `msg` varchar(255) NOT NULL COMMENT '留言',
  PRIMARY KEY (`id`),
  KEY `luid` (`uid`),
  KEY `lmid` (`mid`),
  CONSTRAINT `lmid` FOREIGN KEY (`mid`) REFERENCES `medicine` (`id`) ON DELETE CASCADE,
  CONSTRAINT `luid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `type` varchar(255) NOT NULL COMMENT '类别',
  `specifications` varchar(255) NOT NULL COMMENT '规格',
  `production_time` date NOT NULL COMMENT '生产日期',
  `shelf_life` int(11) NOT NULL COMMENT '保质期 以天为单位',
  `remain_amount` int(11) NOT NULL COMMENT '剩余库存',
  `input_price` double NOT NULL COMMENT '进价',
  `output_price` double NOT NULL COMMENT '售价',
  `src` varchar(255) NOT NULL COMMENT '药品图片链接',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=200028 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_record
-- ----------------------------
DROP TABLE IF EXISTS `order_record`;
CREATE TABLE `order_record` (
  `oid` int(11) NOT NULL COMMENT '订单编号 用来分辨哪些药品是属于哪些订单的',
  `mid` int(11) NOT NULL COMMENT '药品编号',
  `bought_amount` int(11) NOT NULL COMMENT '购买数量',
  PRIMARY KEY (`oid`,`mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `medicine` (`id`) ON DELETE CASCADE,
  CONSTRAINT `oid` FOREIGN KEY (`oid`) REFERENCES `user_order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `phone_no` varchar(11) NOT NULL COMMENT '电话号码, 唯一, 登录账号',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL,
  `sex` varchar(10) NOT NULL DEFAULT '未知' COMMENT '用户性别',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型, 0代表普通用户, 1+代表各类管理员',
  `register_time` date NOT NULL COMMENT '注册时间, 默认为当前时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNo` (`phone_no`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2017013 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `state` int(11) NOT NULL COMMENT '订单状态 0未付款 1已付款 2已取消 ',
  `order_time` date DEFAULT NULL COMMENT '订购时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1000042 DEFAULT CHARSET=utf8;
