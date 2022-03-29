/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 29/03/2022 17:36:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='商品信息';

-- ----------------------------
-- Records of pms_product
-- ----------------------------
BEGIN;
INSERT INTO `pms_product` VALUES (1, 10001, '白酒', 23.50, '江小白', '好喝');
COMMIT;

-- ----------------------------
-- Table structure for pms_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute`;
CREATE TABLE `pms_product_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '属性的类型；0->规格；1->参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='商品属性参数表';

-- ----------------------------
-- Records of pms_product_attribute
-- ----------------------------
BEGIN;
INSERT INTO `pms_product_attribute` VALUES (1, '容量', 0);
COMMIT;

-- ----------------------------
-- Table structure for pms_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute_value`;
CREATE TABLE `pms_product_attribute_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_attribute_id` bigint(20) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL COMMENT '手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8 COMMENT='存储产品参数信息的表';

-- ----------------------------
-- Records of pms_product_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `pms_product_attribute_value` VALUES (1, 1, 1, '150ml');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
