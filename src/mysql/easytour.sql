/*
Navicat MySQL Data Transfer

Source Server         : AndroidTest
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : easytour

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-01-03 22:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `now_city` varchar(255) NOT NULL,
  `now_address` varchar(255) NOT NULL,
  `after_address` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `money` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usr` (`userid`),
  CONSTRAINT `usr` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES ('1', 'hjh123', 'Suzhou', 'Pingyuanlu', 'LinAnZafu', '2', '55');
INSERT INTO `delivery` VALUES ('2', 'hjh123', 'ZheJiang', 'LinAn', 'JiYang', '5', '120');
INSERT INTO `delivery` VALUES ('3', 'hjh123', 'ZheJiang', 'HangZhou', 'JiYang', '5', '120');
INSERT INTO `delivery` VALUES ('20', 'hjh123', '阿拉善盟', '酒店', '机场', '1', '15');
INSERT INTO `delivery` VALUES ('21', 'hjh123', '鞍山', '酒店', '酒店', '11', '165');
INSERT INTO `delivery` VALUES ('22', 'hjh123', '保定', '酒店', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('23', 'hjh123', '鞍山', '酒店', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('24', 'hjh123', '阿拉善盟', '酒店', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('25', 'hjh123', '阿拉善盟', '酒店', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('26', 'hjh123', '保定', '酒店', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('27', 'fsdf', '包头', '酒店', '酒店', '2', '30');
INSERT INTO `delivery` VALUES ('28', 'hjh123', '本溪', '酒店', '酒店', '2', '30');
INSERT INTO `delivery` VALUES ('29', 'hjh123', '长沙', '机场', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('30', 'hjh123', '阿拉善盟', '酒店', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('31', 'hjh123', '白山', '机场', '酒店', '1', '15');
INSERT INTO `delivery` VALUES ('32', 'hjh123', '鞍山', '酒店', '机场', '2', '30');

-- ----------------------------
-- Table structure for tipbuffer
-- ----------------------------
DROP TABLE IF EXISTS `tipbuffer`;
CREATE TABLE `tipbuffer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipid` int(11) NOT NULL,
  `myfrom` varchar(255) DEFAULT NULL,
  `myto` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tipbuffer
-- ----------------------------
INSERT INTO `tipbuffer` VALUES ('11', '17', 'hjh123', 'xqy123', 'test');
INSERT INTO `tipbuffer` VALUES ('12', '17', 'xqy123', 'xqy123', '123');
INSERT INTO `tipbuffer` VALUES ('14', '17', 'hjh123', 'xqy123', '123');

-- ----------------------------
-- Table structure for tips
-- ----------------------------
DROP TABLE IF EXISTS `tips`;
CREATE TABLE `tips` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) NOT NULL,
  `wherewant` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `data` varchar(255) NOT NULL,
  `zan` int(11) NOT NULL DEFAULT '0',
  `pinglun` int(255) NOT NULL DEFAULT '0',
  `userid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersid` (`userid`),
  KEY `usersname` (`author`),
  CONSTRAINT `usersid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `usersname` FOREIGN KEY (`author`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tips
-- ----------------------------
INSERT INTO `tips` VALUES ('15', 'hjh', '长沙', '2018-12-20 00:00:00', '长沙的小吃特别好吃，风景也很美丽，推荐去XXX', '5', '2', 'hjh123');
INSERT INTO `tips` VALUES ('16', 'hjh', '张家界', '2016-05-01 00:00:00', '这是一个测试', '0', '0', 'hjh123');
INSERT INTO `tips` VALUES ('17', 'newer', 'Hunan', '2018-11-20 21:09:20', 'HuNan is beautiful', '0', '0', 'xqy123');
INSERT INTO `tips` VALUES ('18', 'hjh', '', '2019-00-03 20:18:13', '', '0', '0', 'hjh123');

-- ----------------------------
-- Table structure for tipspinglun
-- ----------------------------
DROP TABLE IF EXISTS `tipspinglun`;
CREATE TABLE `tipspinglun` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipid` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `userid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipsid` (`tipid`),
  CONSTRAINT `tipsid` FOREIGN KEY (`tipid`) REFERENCES `tips` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tipspinglun
-- ----------------------------
INSERT INTO `tipspinglun` VALUES ('1', '15', '真的吗我一直很想去长沙', 'hjh123', 'hjh', '2018-12-20 00:00:00', null);
INSERT INTO `tipspinglun` VALUES ('2', '15', '我也是！', 'hjh123', 'hjh', '2018-12-20 00:00:00', null);
INSERT INTO `tipspinglun` VALUES ('3', '15', 'test', 'hjh123', 'hjh', '2018-11-20 22:03:08', 'newer');
INSERT INTO `tipspinglun` VALUES ('4', '16', 'test', 'hjh123', 'hjh', '2018-11-20 14:12:22', null);
INSERT INTO `tipspinglun` VALUES ('5', '17', '这是一个测试', 'hjh123', 'hjh', '2018-11-20 14:12:38', 'newer');
INSERT INTO `tipspinglun` VALUES ('22', '17', '123', 'hjh123', 'hjh', '2018-11-21 07:10:53', 'newer');
INSERT INTO `tipspinglun` VALUES ('23', '15', '123', 'xqy123', 'newer', '2018-11-21 07:11:08', 'hjh');
INSERT INTO `tipspinglun` VALUES ('37', '16', 'test', 'xqy123', 'newer', '2018-11-22 00:48:05', 'hjh');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mysignature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1213', '1213121', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('121312312', '1231231', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('1321', '2131231', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('1eew', 'FDGDDSG', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('21321', 'ffsdaf', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('2ds21', 'fewqfqewf', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('3123', '21312', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('5', '5', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('dgds', 'gfdsg', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('dsfsdf', 'dsfsdfs', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('dsfvhxcjv', 'bhjdbgdfg', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('euwfghj', 'gdfsgdg', 'newer', 'man', null, null, null);
INSERT INTO `user` VALUES ('fdgdf', 'gfddsfg', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('fdsbgefdgdfg', 'gfhf', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('fsdf', 'hig', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('hjh', '46456', 'newer', 'man', null, null, null);
INSERT INTO `user` VALUES ('hjh12', 'hjh1213', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('hjh123', 'hjh123', 'hjh', 'man', null, '15306578895', 'This is a Test');
INSERT INTO `user` VALUES ('hjh1ewqe', '1213131', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('hjhfsda', 'sffsfs', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('hjhhjh898', 'hyp123', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('i1', 'i1', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('t1', 't1', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('test1', 'test1', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('test2', 'test2', 'newer', 'woman', null, null, null);
INSERT INTO `user` VALUES ('test3', 'test3', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('test4', 'test4', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('test5', 'test5', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('test6', 'test5', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('test7', '123', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('ts1', 'ts1', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('ts12', 'ts12', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('uih12312', 'fwe34', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('xcx', '465465', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('xqy', 'wgwekyrewg', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('xqy123', 'xqy123', 'newer', null, null, null, null);
INSERT INTO `user` VALUES ('yu123', 'yu123', 'newer', null, null, null, null);

-- ----------------------------
-- Table structure for userplan
-- ----------------------------
DROP TABLE IF EXISTS `userplan`;
CREATE TABLE `userplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `dest` varchar(255) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `choice_luggage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userplan
-- ----------------------------
INSERT INTO `userplan` VALUES ('22', 'hjh123', '北京市北京市东城区', '0005-05-05 00:00:00', '0005-05-05 00:00:00', '3-5|');
INSERT INTO `userplan` VALUES ('23', 'hjh123', '辽宁省沈阳市沈河区', '2016-01-05 00:00:00', '2016-01-16 00:00:00', '3-2|');
INSERT INTO `userplan` VALUES ('24', 'hjh123', '北京市北京市东城区', '2014-01-01 00:00:00', '2014-01-05 00:00:00', '3-8|');
INSERT INTO `userplan` VALUES ('25', 'hjh123', '北京市北京市丰台区', '2018-12-05 00:00:00', '2018-12-07 00:00:00', '3-5|');
INSERT INTO `userplan` VALUES ('26', 'hjh123', '北京市北京市东城区', '2018-12-07 00:00:00', '2018-12-15 00:00:00', '3-6|3-2|');
INSERT INTO `userplan` VALUES ('27', 'hjh123', '辽宁省沈阳市沈河区', '2018-12-05 00:00:00', '2018-12-07 00:00:00', '3-12|4-2|');
INSERT INTO `userplan` VALUES ('28', 'fsdf', '北京市北京市东城区', '2018-12-19 00:00:00', '2018-12-28 00:00:00', '2-2|3-3|');
INSERT INTO `userplan` VALUES ('29', 'hjh123', '北京市北京市东城区', '2018-12-04 00:00:00', '2018-12-07 00:00:00', '2-5|4-2|');
INSERT INTO `userplan` VALUES ('31', 'hjh123', '北京市北京市东城区', '2018-12-05 00:00:00', '2018-12-14 00:00:00', '1-5|1-2|2-2|3-5|3-8|3-2|4-2|');
INSERT INTO `userplan` VALUES ('32', 'hjh123', '北京市北京市东城区', '2018-12-06 00:00:00', '2018-12-15 00:00:00', '3-11|');
INSERT INTO `userplan` VALUES ('33', 'hjh123', '北京市北京市东城区', '2018-12-12 00:00:00', '2018-12-14 00:00:00', '3-3|4-2|');
INSERT INTO `userplan` VALUES ('34', 'hjh123', '北京市北京市东城区', '2018-12-13 00:00:00', '2018-12-21 00:00:00', '3-11|');
INSERT INTO `userplan` VALUES ('35', 'hjh123', '北京市北京市东城区', '2018-12-13 00:00:00', '2018-12-28 00:00:00', '3-11|');
INSERT INTO `userplan` VALUES ('36', 'hjh123', '湖北省荆州市洪湖市', '2018-12-05 00:00:00', '2018-12-21 00:00:00', '3-6|3-2|');
INSERT INTO `userplan` VALUES ('37', 'hjh123', '北京市北京市东城区', '2018-12-03 00:00:00', '2018-12-20 00:00:00', '3-5|3-9|4-2|');
INSERT INTO `userplan` VALUES ('38', 'hjh123', '北京市北京市东城区', '2019-01-24 00:00:00', '2019-01-26 00:00:00', '2-4|3-1|3-8|3-12|4-2|');
