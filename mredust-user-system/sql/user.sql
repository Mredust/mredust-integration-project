/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : 127.0.0.1:3306
 Source Schema         : mredust-integration-project

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 04/04/2025 00:38:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `sex` tinyint(4) NOT NULL DEFAULT 2 COMMENT '用户性别（0-女 1-男 2-保密）',
  `role` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户角色（0-普通用户 1-管理员）',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（0-否 1-是）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'acbd988c6cc18576a364047bd2fdcf70', '管理员', 2, 1, 'https://raw.githubusercontent.com/Mredust/images/main/file/6a354be99f4589a9dc6f8740e17d505.png', '2025-03-31 16:17:50', '2025-04-01 20:40:15', 0);
INSERT INTO `user` VALUES (3, 'user645702', 'ce2dc006c5003b13a773aaf038029d12', '用户r645702', 2, 0, 'https://raw.githubusercontent.com/Mredust/images/main/file/6a354be99f4589a9dc6f8740e17d505.png', '2025-03-31 23:22:35', '2025-04-01 20:40:17', 0);
INSERT INTO `user` VALUES (4, 'user0dffbf', 'ce2dc006c5003b13a773aaf038029d12', '用户0dffbf', 0, 0, 'https://raw.githubusercontent.com/Mredust/images/main/file/af95641a043da18c3d78f41452251d5.png', '2025-03-31 23:23:21', '2025-04-01 21:25:45', 0);
INSERT INTO `user` VALUES (5, 'user1', 'ce2dc006c5003b13a773aaf038029d12', '用户1', 2, 0, 'https://raw.githubusercontent.com/Mredust/images/main/file/6a354be99f4589a9dc6f8740e17d505.png', '2025-04-01 19:36:51', '2025-04-01 20:40:18', 0);
INSERT INTO `user` VALUES (6, 'user2', 'ce2dc006c5003b13a773aaf038029d12', '用户6d8f31', 1, 0, 'https://raw.githubusercontent.com/Mredust/images/main/file/6a354be99f4589a9dc6f8740e17d505.png', '2025-04-01 20:11:42', '2025-04-01 22:08:04', 0);
INSERT INTO `user` VALUES (7, 'userdb1963', 'ce2dc006c5003b13a773aaf038029d12', '用户1bd3c2', 2, 0, NULL, '2025-04-03 23:31:24', '2025-04-03 23:31:24', 0);
INSERT INTO `user` VALUES (8, 'user348d3b', 'ce2dc006c5003b13a773aaf038029d12', '用户5b43e0', 2, 0, NULL, '2025-04-04 00:36:31', '2025-04-04 00:36:31', 0);

SET FOREIGN_KEY_CHECKS = 1;
