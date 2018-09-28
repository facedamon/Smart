
/*部门表*/

drop table if exists sys_dept;
create table sys_dept(
  dept_id int(11) not null auto_increment primary key comment '部门ID',
  parent_id int(11) default 0 comment '父部门ID',
  ancestors varchar(50) default '' comment '树形列表',
  dept_name varchar(30) default '' comment '部门名称',
  order_num int(4) default 0 comment '显示顺序',
  leader varchar(20) default '' comment '负责人',
  phone varchar(11) default '' comment '联系电话',
  email varchar(50) default '' comment '邮箱',
  status char(1) default '0' comment '部门状态(0:正常,1:停用)',
  del_flag char(1) default '0' comment '删除标志(0:存在，1：删除)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间'
) engine=innodb auto_increment=200 default charset=utf8 comment='部门表';

/*用户信息表*/
drop table if exists sys_user;
create table sys_user(
 user_id int(11) not null auto_increment primary key comment '用户ID',
 dept_id int(11) default null comment '部门ID',
 login_name varchar(30) not null comment '登陆账号',
 user_name varchar(30) not null comment '用户昵称',
 user_type varchar(2) default '00' comment '用户类型(00:系统用户)',
 email varchar(50) default '' comment '邮箱',
 phonenumber varchar(11) default '' comment '联系电话',
 sex char(1) default '0' comment '性别(0:mail,1:femail,2:noone)',
 avatar varchar(100) default '' comment '图像路径',
 password varchar(50) default '' comment '密码',
 salt varchar(20) default '' comment '盐加密',
 status char(1) default '0' comment '账号状态(0:正常，1:停用)',
 del_flag char(1) default '0' comment '删除标志(0:存在，2：删除)',
 login_ip varchar(20) default '' comment '最后登录IP',
 login_date datetime  comment '最后登录时间',
 create_by varchar(64) default '' comment '创建者',
 create_time datetime comment '创建时间',
 update_by varchar(64) default '' comment '更新者',
 update_time datetime comment '更新时间',
 remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=200 default charset=utf8 comment='用户信息表';

/*岗位信息表*/
drop table if exists sys_post;
create table sys_post(
  post_id int(11) not null auto_increment primary key comment '岗位ID',
  post_code varchar(64) not null comment '岗位编码',
  post_name varchar(50) not null comment '岗位名称',
  post_sort int(4) not null comment '显示顺序',
  status char(1) not null comment '岗位状态(0:正常,1:停用)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=100 default charset=utf8 comment='岗位信息表';

/*角色信息表*/
drop table if exists sys_role;
create table sys_role(
  role_id int(11) not null auto_increment primary key comment '岗位ID',
  role_name varchar(30) not null comment '角色名称',
  role_key varchar(100) not null comment '角色权限字符',
  role_sort int(4) not null comment '显示顺序',
  data_scope char(1)  default '1' comment '数据权限(1:全部数据权限,2:自定义数据权限)',
  status char(1) not null comment '角色状态(0:正常,1:停用)',
  del_flag char(1) default '0' comment '删除标志(0:删除,2:存在)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=100 default charset=utf8 comment='角色信息表';

/*菜单信息表*/
drop table if exists sys_menu;
create table sys_menu(
  menu_id int(11) not null auto_increment primary key comment '菜单ID',
  menu_name varchar(50) not null comment '菜单名称',
  parent_id int(11) default 0 comment '上级菜单ID',
  order_num int(4) default 0 comment '显示顺序',
  url varchar(200) default '#' comment '请求URL',
  menu_type char(1) default '' comment '菜单类型(M:目录,C:菜单,F:按钮)',
  visible char(1) default 0 comment '菜单状态(0：显示,1:隐藏)',
  perms varchar(100) default '' comment '权限标识',
  icon varchar(100) default '#' comment '菜单图标',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=200 default charset=utf8 comment='菜单信息表';

/*用户、角色关联表 N->1*/
drop table if exists sys_user_role;
create table sys_user_role(
  user_id int(11) not null comment '用户ID',
  role_id int(11) not null comment '角色ID',
  primary key(user_id,role_id)
)engine=innodb default charset=utf8 comment='用户角色关联表';

/*角色、菜单关联表 1->N*/
drop table if exists sys_role_menu;
create table sys_role_menu(
  role_id int(11) not null comment '角色ID',
  menu_id int(11) not null comment '菜单ID',
  primary key(role_id,menu_id)
)engine=innodb default charset=utf8 comment='角色菜单关联表';

/*角色、部门关联表 1->N*/
drop table if exists sys_role_dept;
create table sys_role_dept(
  role_id int(11) not null comment '角色ID',
  dept_id int(11) not null comment '部门ID',
  primary key(role_id,dept_id)
)engine=innodb default charset=utf8 comment='角色部门关联表';

/*用户、岗位关联表 1->N*/
drop table if exists sys_user_post;
create table sys_user_post(
  user_id int(11) not null comment '用户ID',
  post_id int(11) not null comment '岗位ID',
  primary key(user_id,post_id)
)engine=innodb default charset=utf8 comment='用户部门关联表';

/*操作日志记录表*/
drop table if exists sys_oper_log;
create table sys_oper_log(
  oper_id int(11) not null auto_increment primary key comment '日志ID',
  title varchar(50) default '' comment '模块标题',
  business_type int(2) default 0 comment '业务类型(0:其它,1:新增,2:修改，3:删除)',
  method varchar(100) default '' comment '方法名称',
  operator_type int(1) default 0 comment '操作类型(0:其它,1:后台用户,2:手机端用户)',
  oper_name varchar(50) default '' comment '操作人员',
  dept_name varchar(50) default '' comment '部门名称',
  oper_url varchar(255) default '' comment '请求URL',
  oper_ip varchar(30) default '' comment '请求主机地址',
  oper_location varchar(255) default '' comment '操作地点',
  oper_param varchar(255) default '' comment '请求参数',
  status int(1) default 0 comment '操作状态(0:正常,1:异常)',
  error_msg varchar(2000) default '' comment '错误信息',
  oper_time datetime comment '操作时间'
)engine=innodb auto_increment=100 default charset=utf8 comment='操作日志记录表';

/*字典类型表*/
drop table if exists sys_dict_type;
create table sys_dict_type(
  dict_id int(11) not null auto_increment primary key comment '字典ID',
  dict_name varchar(100) default '' comment '字典名称',
  dict_type varchar(100) unique default '' comment'字典类型',
  status char(1) default '0' comment '状态(0:正常,1:停用)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=100 default charset=utf8 comment='字典类型表';

/*数据字典表*/
drop table if exists sys_dict_data;
create table sys_dict_data(
  dict_code int(1) not null primary key auto_increment comment '字典编码',
  dict_sort int(4) default 0 comment '字典排序',
  dict_label varchar(100) default '' comment '字典标签',
  dict_value varchar(100) default '' comment '字典值',
  dict_type  varchar(100) default '' comment '字典类型',
  css_class varchar(500) default '' comment '样式属性',
  list_class varchar(500) default '' comment '表格回显样式',
  is_default char(1) default 'N' comment '是否默认(Y:是，N:否)',
  status char(1) default '0' comment '状态(0:正常,1:停用)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=100 default charset=utf8 comment='字典数据表';

/*参数配置表*/
drop table if exists sys_config;
create table sys_config(
  config_id int(5) not null auto_increment primary key comment '参数ID',
  config_name varchar(100) default '' comment '参数名',
  config_key varchar(100) default '' comment '参数键',
  config_value varchar(100) default '' comment '参数值',
  config_type char(1) default 'N' comment '系统内置(Y:是,N:否)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=100 default charset=utf8 comment='参数配置表';

/*系统访问记录*/
drop table if exists sys_logininfor;
create table sys_logininfor(
  info_id int(11) not null auto_increment primary key comment '访问ID',
  login_name varchar(50) default '' comment '登录账号',
  ipaddr varchar(50) default '' comment '登录地址',
  login_location varchar(255) default '' comment '登录地点',
  browser varchar(50) default '' comment '浏览器类型',
  os varchar(50) default '' comment '操作系统类型',
  status char(1) default '0' comment '登录状态(0:正常，1:失败)',
  msg varchar(255) default '' comment '提示消息',
  login_time datetime comment '登录时间'
)engine=innodb auto_increment=100 default charset=utf8 comment='系统访问记录表';

/*在线用户记录*/
drop table if exists sys_user_online;
create table sys_user_online(
  sessionId varchar(50) default '' primary key comment '用户会话ID',
  login_name varchar(50) default '' comment '登录账户',
  dept_name varchar(50) default '' comment '部门名称',
  ipaddr varchar(50) default '' comment 'IP地址',
  login_location varchar(255) default '' comment '登录地点',
  browser varchar(50) default '' comment '浏览器类型',
  os varchar(50) default '' comment '操作系统类型',
  status varchar(10) default '0' comment '在线状态(on_line,off_line)',
  start_timestamp datetime comment 'session创建时间',
  last_access_time datetime comment 'seesion最后访问时间',
  expire_time int(5) default 5 comment '超时时长/min'
)engine=innodb auto_increment=100 default charset=utf8 comment='在线用户记录';

/*通知公告表*/
drop table if exists sys_notice;
create table sys_notice(
  notice_id int(4) not null auto_increment primary key comment '通知ID',
  notice_tile varchar(50) not null comment '公告标题',
  notice_type char(2) not null comment '公告类型(1:通知,2:公告)',
  notice_content varchar(500) not null comment '公告内容',
  status char(1) default '0' comment '公告状态(0:正常,1:关闭)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注'
)engine=innodb auto_increment=100 default charset=utf8 comment='通知公告表';