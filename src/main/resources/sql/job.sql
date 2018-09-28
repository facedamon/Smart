/*定时任务调度表*/
drop table if exists sys_job;
create table sys_job(
  job_id int(11) not null auto_increment comment '任务ID',
  job_name varchar(64) default '' comment '任务名称',
  job_group varchar(64) default '' comment '任务组名',
  method_name varchar(500) default '' comment '任务方法',
  method_params varchar(500) default '' comment '任务参数',
  cron_expression varchar(255) default '' comment '时间表达式',
  misfire_policy varchar(20) default '0' comment '任务执行错误策略(0:默认,1:继续,2:等待,2:放弃)',
  status char(1) default '0' comment '状态(0:正常,1:暂停)',
  create_by varchar(64) default '' comment '创建者',
  create_time datetime comment '创建时间',
  update_by varchar(64) default '' comment '更新者',
  update_time datetime comment '更新时间',
  remark varchar(500) default '' comment '备注',
  primary key(job_id,job_group,job_name)
)engine=innodb auto_increment=100 default charset=utf8 comment='定时任务调度表';


/*定时任务调度日志表*/
drop table if exists sys_job_log;
create table sys_job_log(
  job_log_id int(11) not null primary key auto_increment comment '任务日志ID',
  job_name varchar(64) not null comment '任务名称',
  job_group varchar(64) not null comment '任务组名',
  method_name varchar(500) default '' comment '任务方法',
  method_params varchar(500) default '' comment '任务参数',
  job_message varchar(500) default '' comment '日志信息',
  status char(1) default '0' comment '执行状态(0:正常,1:失败)',
  exception_info text comment '异常信息',
  create_time datetime comment '创建时间'
)engine=innodb auto_increment=100 default charset=utf8 comment='定时任务调度日志表';

--------------------------------------