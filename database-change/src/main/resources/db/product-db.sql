/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : product-db

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-06-20 11:08:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `db_product`
-- ----------------------------
DROP TABLE IF EXISTS `db_product`;
CREATE TABLE `db_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `isup` tinyint(1) DEFAULT '1' COMMENT '是否上线，0：下线，1：上线',
  `status` smallint(2) NOT NULL DEFAULT '10' COMMENT '产品状态 10-预售 20-在售 30-售罄 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='产品表';

-- ----------------------------
-- Records of db_product
-- ----------------------------
INSERT INTO `db_product` VALUES ('1', '产品名称1', '1', '20');
INSERT INTO `db_product` VALUES ('2', '产品名称2', '1', '20');
INSERT INTO `db_product` VALUES ('3', '产品名称3', '1', '20');

-- ----------------------------
-- Table structure for `db_product_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `db_product_statistics`;
CREATE TABLE `db_product_statistics` (
  `pid` int(11) NOT NULL,
  `scale_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '产品总额度',
  `sale_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '销售额度',
  `update_time` int(11) NOT NULL DEFAULT '0' COMMENT '最后更新时间',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号码',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品库存';

-- ----------------------------
-- Records of db_product_statistics
-- ----------------------------
INSERT INTO `db_product_statistics` VALUES ('1', '10.0000', '5.0000', '0', '0');
INSERT INTO `db_product_statistics` VALUES ('2', '20.0000', '1.0000', '0', '0');
INSERT INTO `db_product_statistics` VALUES ('3', '30.0000', '2.0000', '0', '0');
