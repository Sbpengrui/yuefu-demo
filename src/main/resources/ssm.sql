/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-10-09 08:51:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `remote` int(2) NOT NULL COMMENT '是否静态 1 是 2 否',
  `url` varchar(255) NOT NULL COMMENT '链接地址',
  `icon` varchar(255) DEFAULT NULL,
  `enable` int(2) DEFAULT '1' COMMENT '是否启用 1 启用 0 不启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'Language', '1', '0', '0', '', null, '1');
INSERT INTO `menu` VALUES ('2', '用户列表', '2', '1', '1', '/demo/index.html', 'icon-users', '1');
INSERT INTO `menu` VALUES ('3', 'list', '2', '1', '1', '/demo/list.html', 'icon-users', '1');
INSERT INTO `menu` VALUES ('4', 'java', '1', '0', '0', '', null, '1');
INSERT INTO `menu` VALUES ('5', 'C#', '1', '0', '0', '', null, '1');
INSERT INTO `menu` VALUES ('6', 'Ruby', '1', '0', '0', '', null, '1');
INSERT INTO `menu` VALUES ('7', 'Fortran', '1', '0', '0', '', null, '1');
INSERT INTO `menu` VALUES ('8', '菜单管理', '1', '0', '0', '', null, '1');
INSERT INTO `menu` VALUES ('9', '菜单树', '2', '8', '1', '/demo/menu/menuTree.html', 'icon-menu-rightarrow', '1');
INSERT INTO `menu` VALUES ('10', 'treegrid', '2', '8', '1', '/demo/menu/menuTreeGrid.html', 'icon-menu-rightarrow', '1');
INSERT INTO `menu` VALUES ('11', 'xxx', '3', '10', '0', '', null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '25');
INSERT INTO `user` VALUES ('2', 'sss', 'sss', '25');
INSERT INTO `user` VALUES ('3', 'sss1', 'sss', '25');
INSERT INTO `user` VALUES ('4', 'sss2', 'sss', '25');
INSERT INTO `user` VALUES ('5', 'sss3', 'sss', '25');
INSERT INTO `user` VALUES ('6', 'sss4', 'sss', '25');
INSERT INTO `user` VALUES ('7', 'sss5', 'sss', '25');
INSERT INTO `user` VALUES ('8', 'sss6', 'sss', '25');
INSERT INTO `user` VALUES ('9', 'sss7', 'sss', '25');
INSERT INTO `user` VALUES ('10', 'sss8', 'sss', '25');
INSERT INTO `user` VALUES ('11', 'sss9', 'sss', '25');
INSERT INTO `user` VALUES ('12', 'sss10', 'sss', '25');
INSERT INTO `user` VALUES ('13', 'sss11', 'sss', '25');
INSERT INTO `user` VALUES ('14', 'sss12', 'sss', '25');
INSERT INTO `user` VALUES ('15', 'sss13', 'sss', '25');
INSERT INTO `user` VALUES ('16', 'sss14', 'sss', '25');
INSERT INTO `user` VALUES ('17', 'sss15', 'sss', '25');
INSERT INTO `user` VALUES ('18', 'sss16', 'sss', '25');
INSERT INTO `user` VALUES ('19', 'sss17', 'sss', '25');
INSERT INTO `user` VALUES ('20', 'sss18', 'sss', '25');
INSERT INTO `user` VALUES ('21', 'sss19', 'sss', '25');

-- ----------------------------
-- Table structure for user_details
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_details
-- ----------------------------
INSERT INTO `user_details` VALUES ('8', '8', 'fname8', 'lname8', '1238', '444677@qq.com');
INSERT INTO `user_details` VALUES ('9', '9', 'fname9', 'lname9', '1239', '444677@qq.com');
INSERT INTO `user_details` VALUES ('10', '10', 'fname10', 'lname10', '12310', '444677@qq.com');
INSERT INTO `user_details` VALUES ('11', '11', 'fname11', 'lname11', '12311##', '444677@qq.com');
INSERT INTO `user_details` VALUES ('12', '12', 'fname12', 'lname12', '12312', '444677@qq.com');
INSERT INTO `user_details` VALUES ('13', '13', 'fname13', 'lname13', '12313', '444677@qq.com');
INSERT INTO `user_details` VALUES ('14', '14', 'fname14', 'lname14', '12314', '444677@qq.com');
INSERT INTO `user_details` VALUES ('15', '15', 'fname15', 'lname15', '12315', '444677@qq.com');
INSERT INTO `user_details` VALUES ('16', '16', 'fname16', 'lname16', '12316', '444677@qq.com');
INSERT INTO `user_details` VALUES ('17', '17', 'fname17', 'lname17', '12317', '444677@qq.com');
INSERT INTO `user_details` VALUES ('18', '18', 'fname18', 'lname18', '12318', '444677@qq.com');
INSERT INTO `user_details` VALUES ('19', '19', 'fname19', 'lname19', '12319', '444677@qq.com');
INSERT INTO `user_details` VALUES ('20', '20', 'fname20', 'lname20', '12320', '444677@qq.com');
INSERT INTO `user_details` VALUES ('21', '21', 'fname21', 'lname21', '12321', '444677@qq.com');
INSERT INTO `user_details` VALUES ('22', '6', 'fnamexxx', 'lnamexx', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('23', '15', 'fname', 'xxxxxxx', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('28', '7', '1222222222', 'xxxxxxx2133333', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('29', '6', '1222222222', 'xxxxxxx2133333', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('30', '6', '1222222222', 'xxxxxxx2133333', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('31', '6', '1222222222', 'xxxxxxx2133333', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('32', '12', '1222222222', 'xxxxxxx', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('33', '5', 'fname', 'xxxxxxx2133333', '32222222222222', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('34', '8', 'fname', 'lname', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('35', '4', 'fname', 'lname', '123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('36', '16', '1222222222', 'xxxxxxx', '32222222222222', '444677@');
INSERT INTO `user_details` VALUES ('37', '9', 'fname', 'lname', '12323213123', '444677839@qq.com');
INSERT INTO `user_details` VALUES ('38', '21', '1222222222', 'lname', '123444444444', '444677@qq.com');
