

CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned auto_increment,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `username` varchar(32)  DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(32)  DEFAULT NULL COMMENT '真实名',
  `gender` tinyint(2) unsigned NULL COMMENT '性别，1：男，2：女',
  `mobile` varchar(32)  DEFAULT NULL COMMENT '手机号',
  `email` varchar(32)  DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64)  DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255)  DEFAULT NULL COMMENT '头像',
  `status` tinyint(2) unsigned default 1 NOT NULL COMMENT '状态，0：冻结，1：正常',
  `remark` varchar(32)  DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(2) unsigned default 0 not null COMMENT '软删除，0：正常，1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户表';


CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned auto_increment,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `role_name` varchar(32)  DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32)  DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(32)  DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(2) unsigned default 0 not null COMMENT '软删除，0：正常，1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统角色表';



CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned auto_increment,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `user_id` bigint(20)  DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20)  DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';



CREATE TABLE `sys_manage_permission` (
  `id` bigint(20) unsigned auto_increment,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `parent_id` bigint(20)  DEFAULT NULL COMMENT '父id',
  `menu_type` tinyint(4) DEFAULT NULL COMMENT '类型，1：一级菜单，2：二级菜单，3：三级菜单，4：按钮',
  `name` varchar(32)  DEFAULT NULL COMMENT '权限名称',
  `code` varchar(32)  DEFAULT NULL COMMENT '权限编码',
  `perm_path` varchar(32)  DEFAULT NULL COMMENT '前端菜单路径或者按钮权限编码',
  `icon` varchar(32)  DEFAULT NULL COMMENT '图标',
  `sort` int(10) unsigned default 0 NOT NULL COMMENT '排序',
  `remark` varchar(32)  DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(2) unsigned default 0 not null COMMENT '软删除，0：正常，1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='后台菜单权限表';



CREATE TABLE `sys_role_manage_permission` (
  `id` bigint(20) unsigned auto_increment,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `role_id` bigint(20)  DEFAULT NULL COMMENT '角色id',
  `manage_permission_id` bigint(20)  DEFAULT NULL COMMENT '后台权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色后台权限表';





