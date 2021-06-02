DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '资源名称',
  `res_url` varchar(255) default NULL COMMENT '资源url',
  `parent_id` int(11) default NULL COMMENT '父资源',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `resources` VALUES (1, '系统设置', '/system', 0);
INSERT INTO `resources` VALUES (2, '用户管理', '/usersPage', 1);
INSERT INTO `resources` VALUES (3, '角色管理', '/rolesPage', 1);
INSERT INTO `resources` VALUES (4, '资源管理', '/resourcesPage', 1);
INSERT INTO `resources` VALUES (5, '添加用户', '/users/add', 2);
INSERT INTO `resources` VALUES (6, '删除用户', '/users/delete', 2);
INSERT INTO `resources` VALUES (7, '添加角色', '/roles/add', 3);
INSERT INTO `resources` VALUES (8, '删除角色', '/roles/delete', 3);
INSERT INTO `resources` VALUES (9, '添加资源', '/resources/add', 4);
INSERT INTO `resources` VALUES (10, '删除资源', '/resources/delete', 4);
INSERT INTO `resources` VALUES (11, '分配角色', '/users/saveUserRoles', 2);
INSERT INTO `resources` VALUES (12, '分配权限', '/roles/saveRoleResources', 3);



DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role` VALUES (1, '超级管理员');
INSERT INTO `role` VALUES (2, '管理员');



DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `role_id` int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY  (`role_id`,`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role_resources` VALUES (1, 2);
INSERT INTO `role_resources` VALUES (1, 3);
INSERT INTO `role_resources` VALUES (1, 4);
INSERT INTO `role_resources` VALUES (1, 5);
INSERT INTO `role_resources` VALUES (1, 6);
INSERT INTO `role_resources` VALUES (1, 7);
INSERT INTO `role_resources` VALUES (1, 8);
INSERT INTO `role_resources` VALUES (1, 9);
INSERT INTO `role_resources` VALUES (1, 10);
INSERT INTO `role_resources` VALUES (1, 11);
INSERT INTO `role_resources` VALUES (1, 12);
INSERT INTO `role_resources` VALUES (2, 2);
INSERT INTO `role_resources` VALUES (2, 3);
INSERT INTO `role_resources` VALUES (2, 4);
INSERT INTO `role_resources` VALUES (2, 9);
INSERT INTO `role_resources` VALUES (3, 2);
INSERT INTO `role_resources` VALUES (3, 3);
INSERT INTO `role_resources` VALUES (3, 4);
INSERT INTO `role_resources` VALUES (3, 5);
INSERT INTO `role_resources` VALUES (3, 7);
INSERT INTO `role_resources` VALUES (3, 8);
INSERT INTO `role_resources` VALUES (3, 9);
INSERT INTO `role_resources` VALUES (3, 10);



DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(33) default NULL,
  `password` varchar(33) default NULL,
  `status` int(10) default 0 COMMENT '用户状态,0:启用，1:禁用，2:锁定',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1, 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', 0);
INSERT INTO `user` VALUES (2, 'user1', '90e66e36e3135a91d298177d4389851e', 0);




DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) default NULL,
  `role_id` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);
