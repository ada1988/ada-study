/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50626
Source Host           : 127.0.0.1:3306
Source Database       : order_db

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-04-17 18:27:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail`;
CREATE TABLE `tbl_order_detail` (
  `id` bigint(20) NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_no` bigint(20) DEFAULT NULL,
  `product_sku_no` bigint(20) DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `product_num` decimal(12,2) DEFAULT NULL,
  `product_unit` varchar(128) DEFAULT NULL COMMENT '小时，平米，个，台',
  `price` decimal(12,2) DEFAULT NULL,
  `increment_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `actual_pay_amount` decimal(12,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_order_detail_0`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail_0`;
CREATE TABLE `tbl_order_detail_0` (
  `id` bigint(20) NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_no` bigint(20) DEFAULT NULL,
  `product_sku_no` bigint(20) DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `product_num` decimal(12,2) DEFAULT NULL,
  `product_unit` varchar(128) DEFAULT NULL COMMENT '小时，平米，个，台',
  `price` decimal(12,2) DEFAULT NULL,
  `increment_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `actual_pay_amount` decimal(12,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_order_detail_0
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_order_detail_1`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail_1`;
CREATE TABLE `tbl_order_detail_1` (
  `id` bigint(20) NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_no` bigint(20) DEFAULT NULL,
  `product_sku_no` bigint(20) DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `product_num` decimal(12,2) DEFAULT NULL,
  `product_unit` varchar(128) DEFAULT NULL COMMENT '小时，平米，个，台',
  `price` decimal(12,2) DEFAULT NULL,
  `increment_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `actual_pay_amount` decimal(12,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_order_detail_1
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_order_detail_2`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail_2`;
CREATE TABLE `tbl_order_detail_2` (
  `id` bigint(20) NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_no` bigint(20) DEFAULT NULL,
  `product_sku_no` bigint(20) DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `product_num` decimal(12,2) DEFAULT NULL,
  `product_unit` varchar(128) DEFAULT NULL COMMENT '小时，平米，个，台',
  `price` decimal(12,2) DEFAULT NULL,
  `increment_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `actual_pay_amount` decimal(12,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_order_detail_2
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_order_detail_3`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail_3`;
CREATE TABLE `tbl_order_detail_3` (
  `id` bigint(20) NOT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_no` bigint(20) DEFAULT NULL,
  `product_sku_no` bigint(20) DEFAULT NULL,
  `product_name` varchar(128) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  `product_num` decimal(12,2) DEFAULT NULL,
  `product_unit` varchar(128) DEFAULT NULL COMMENT '小时，平米，个，台',
  `price` decimal(12,2) DEFAULT NULL,
  `increment_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `actual_pay_amount` decimal(12,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_order_detail_3
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_form`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_form`;
CREATE TABLE `t_order_form` (
  `id` int(11) NOT NULL COMMENT '自增',
  `order_no` varchar(64) DEFAULT NULL COMMENT ' 订单编号',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `user_address` varchar(128) DEFAULT NULL COMMENT '用户地址',
  `lon_and_lat` varchar(128) DEFAULT NULL COMMENT '地址经纬度',
  `multiple_payment` int(11) DEFAULT NULL COMMENT '0否1是',
  `balance_amount` decimal(12,2) DEFAULT NULL COMMENT '余款',
  `order_status` int(11) DEFAULT NULL COMMENT '1竞单2待服务3取消4超时5服务完成6结束',
  `pay_status` int(11) DEFAULT NULL COMMENT '0待支付1已支付',
  `product_type` varchar(128) DEFAULT NULL COMMENT '产品大类',
  `product_sub_typte` varchar(128) DEFAULT NULL COMMENT '产品小类',
  `link_user` varchar(32) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `user_service_require` varchar(128) DEFAULT NULL COMMENT '用户服务要求',
  `canel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `end_time` datetime DEFAULT NULL COMMENT '预计结束时间',
  `order_amount` decimal(12,2) DEFAULT NULL COMMENT '订单金额',
  `increment_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额+订单金额',
  `discount_amount` decimal(12,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支付方式',
  `actual_pay_amount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `settlement_flag` int(11) DEFAULT NULL COMMENT '0未结算1已结算',
  `source_code` varchar(32) DEFAULT NULL COMMENT '对应字典',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '来源用户id',
  `dispatch_flag` int(11) DEFAULT NULL COMMENT '0系统派单1人工派单',
  `order_flag` int(11) DEFAULT NULL COMMENT '0客户下单1代下单',
  `is_delete` int(11) DEFAULT NULL COMMENT '0否1是',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单记录表';

-- ----------------------------
-- Records of t_order_form
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_form_0`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_form_0`;
CREATE TABLE `t_order_form_0` (
  `id` int(11) NOT NULL COMMENT '自增',
  `order_no` varchar(64) DEFAULT NULL COMMENT ' 订单编号',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `user_address` varchar(128) DEFAULT NULL COMMENT '用户地址',
  `lon_and_lat` varchar(128) DEFAULT NULL COMMENT '地址经纬度',
  `multiple_payment` int(11) DEFAULT NULL COMMENT '0否1是',
  `balance_amount` decimal(12,2) DEFAULT NULL COMMENT '余款',
  `order_status` int(11) DEFAULT NULL COMMENT '1竞单2待服务3取消4超时5服务完成6结束',
  `pay_status` int(11) DEFAULT NULL COMMENT '0待支付1已支付',
  `product_type` varchar(128) DEFAULT NULL COMMENT '产品大类',
  `product_sub_typte` varchar(128) DEFAULT NULL COMMENT '产品小类',
  `link_user` varchar(32) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `user_service_require` varchar(128) DEFAULT NULL COMMENT '用户服务要求',
  `canel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `end_time` datetime DEFAULT NULL COMMENT '预计结束时间',
  `order_amount` decimal(12,2) DEFAULT NULL COMMENT '订单金额',
  `increment_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额+订单金额',
  `discount_amount` decimal(12,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支付方式',
  `actual_pay_amount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `settlement_flag` int(11) DEFAULT NULL COMMENT '0未结算1已结算',
  `source_code` varchar(32) DEFAULT NULL COMMENT '对应字典',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '来源用户id',
  `dispatch_flag` int(11) DEFAULT NULL COMMENT '0系统派单1人工派单',
  `order_flag` int(11) DEFAULT NULL COMMENT '0客户下单1代下单',
  `is_delete` int(11) DEFAULT NULL COMMENT '0否1是',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单记录表';

-- ----------------------------
-- Records of t_order_form_0
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_form_1`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_form_1`;
CREATE TABLE `t_order_form_1` (
  `id` int(11) NOT NULL COMMENT '自增',
  `order_no` varchar(64) DEFAULT NULL COMMENT ' 订单编号',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `user_address` varchar(128) DEFAULT NULL COMMENT '用户地址',
  `lon_and_lat` varchar(128) DEFAULT NULL COMMENT '地址经纬度',
  `multiple_payment` int(11) DEFAULT NULL COMMENT '0否1是',
  `balance_amount` decimal(12,2) DEFAULT NULL COMMENT '余款',
  `order_status` int(11) DEFAULT NULL COMMENT '1竞单2待服务3取消4超时5服务完成6结束',
  `pay_status` int(11) DEFAULT NULL COMMENT '0待支付1已支付',
  `product_type` varchar(128) DEFAULT NULL COMMENT '产品大类',
  `product_sub_typte` varchar(128) DEFAULT NULL COMMENT '产品小类',
  `link_user` varchar(32) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `user_service_require` varchar(128) DEFAULT NULL COMMENT '用户服务要求',
  `canel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `end_time` datetime DEFAULT NULL COMMENT '预计结束时间',
  `order_amount` decimal(12,2) DEFAULT NULL COMMENT '订单金额',
  `increment_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额+订单金额',
  `discount_amount` decimal(12,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支付方式',
  `actual_pay_amount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `settlement_flag` int(11) DEFAULT NULL COMMENT '0未结算1已结算',
  `source_code` varchar(32) DEFAULT NULL COMMENT '对应字典',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '来源用户id',
  `dispatch_flag` int(11) DEFAULT NULL COMMENT '0系统派单1人工派单',
  `order_flag` int(11) DEFAULT NULL COMMENT '0客户下单1代下单',
  `is_delete` int(11) DEFAULT NULL COMMENT '0否1是',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单记录表';

-- ----------------------------
-- Records of t_order_form_1
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_form_2`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_form_2`;
CREATE TABLE `t_order_form_2` (
  `id` int(11) NOT NULL COMMENT '自增',
  `order_no` varchar(64) DEFAULT NULL COMMENT ' 订单编号',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `user_address` varchar(128) DEFAULT NULL COMMENT '用户地址',
  `lon_and_lat` varchar(128) DEFAULT NULL COMMENT '地址经纬度',
  `multiple_payment` int(11) DEFAULT NULL COMMENT '0否1是',
  `balance_amount` decimal(12,2) DEFAULT NULL COMMENT '余款',
  `order_status` int(11) DEFAULT NULL COMMENT '1竞单2待服务3取消4超时5服务完成6结束',
  `pay_status` int(11) DEFAULT NULL COMMENT '0待支付1已支付',
  `product_type` varchar(128) DEFAULT NULL COMMENT '产品大类',
  `product_sub_typte` varchar(128) DEFAULT NULL COMMENT '产品小类',
  `link_user` varchar(32) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `user_service_require` varchar(128) DEFAULT NULL COMMENT '用户服务要求',
  `canel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `end_time` datetime DEFAULT NULL COMMENT '预计结束时间',
  `order_amount` decimal(12,2) DEFAULT NULL COMMENT '订单金额',
  `increment_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额+订单金额',
  `discount_amount` decimal(12,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支付方式',
  `actual_pay_amount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `settlement_flag` int(11) DEFAULT NULL COMMENT '0未结算1已结算',
  `source_code` varchar(32) DEFAULT NULL COMMENT '对应字典',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '来源用户id',
  `dispatch_flag` int(11) DEFAULT NULL COMMENT '0系统派单1人工派单',
  `order_flag` int(11) DEFAULT NULL COMMENT '0客户下单1代下单',
  `is_delete` int(11) DEFAULT NULL COMMENT '0否1是',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单记录表';

-- ----------------------------
-- Records of t_order_form_2
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_form_3`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_form_3`;
CREATE TABLE `t_order_form_3` (
  `id` int(11) NOT NULL COMMENT '自增',
  `order_no` varchar(64) DEFAULT NULL COMMENT ' 订单编号',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `user_address` varchar(128) DEFAULT NULL COMMENT '用户地址',
  `lon_and_lat` varchar(128) DEFAULT NULL COMMENT '地址经纬度',
  `multiple_payment` int(11) DEFAULT NULL COMMENT '0否1是',
  `balance_amount` decimal(12,2) DEFAULT NULL COMMENT '余款',
  `order_status` int(11) DEFAULT NULL COMMENT '1竞单2待服务3取消4超时5服务完成6结束',
  `pay_status` int(11) DEFAULT NULL COMMENT '0待支付1已支付',
  `product_type` varchar(128) DEFAULT NULL COMMENT '产品大类',
  `product_sub_typte` varchar(128) DEFAULT NULL COMMENT '产品小类',
  `link_user` varchar(32) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `user_service_require` varchar(128) DEFAULT NULL COMMENT '用户服务要求',
  `canel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `end_time` datetime DEFAULT NULL COMMENT '预计结束时间',
  `order_amount` decimal(12,2) DEFAULT NULL COMMENT '订单金额',
  `increment_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '增值服务金额+订单金额',
  `discount_amount` decimal(12,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支付方式',
  `actual_pay_amount` decimal(12,2) DEFAULT NULL COMMENT '实际支付金额',
  `settlement_flag` int(11) DEFAULT NULL COMMENT '0未结算1已结算',
  `source_code` varchar(32) DEFAULT NULL COMMENT '对应字典',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '来源用户id',
  `dispatch_flag` int(11) DEFAULT NULL COMMENT '0系统派单1人工派单',
  `order_flag` int(11) DEFAULT NULL COMMENT '0客户下单1代下单',
  `is_delete` int(11) DEFAULT NULL COMMENT '0否1是',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单记录表';

-- ----------------------------
-- Records of t_order_form_3
-- ----------------------------
