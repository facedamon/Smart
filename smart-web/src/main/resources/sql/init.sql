-- MySQL dump 10.13  Distrib 5.7.22, for osx10.12 (x86_64)
--
-- Host: localhost    Database: smart
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CALENDARS` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SmartScheduler','__TASK_CLASS_NAME__1','DEFAULT','0/10 * * * * ?','Asia/Shanghai'),('SmartScheduler','__TASK_CLASS_NAME__2','DEFAULT','0/20 * * * * ?','Asia/Shanghai');
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_FIRED_TRIGGERS`
--

LOCK TABLES `QRTZ_FIRED_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SmartScheduler','__TASK_CLASS_NAME__1','DEFAULT',NULL,'com.facedamon.smart.quartz.support.ScheduleJob','0','0','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0__TASK_PROPERTIES__sr\0%com.facedamon.smart.quartz.domain.Job\0\0\0\0\0\0\0\0L\0cronExpressiont\0Ljava/lang/String;L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\nmethodNameq\0~\0	L\0methodParamsq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0*com.facedamon.smart.common.base.BaseEntity0eԭ\�\Zvy\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0b,\�)\�xpt\0\0pppt\00/10 * * * * ?t\0系统默认（无参）sr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0\0\0\0\0t\0ryTaskt\0\nryNoParamst\0\0t\01t\01x\0'),('SmartScheduler','__TASK_CLASS_NAME__2','DEFAULT',NULL,'com.facedamon.smart.quartz.support.ScheduleJob','0','0','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0__TASK_PROPERTIES__sr\0%com.facedamon.smart.quartz.domain.Job\0\0\0\0\0\0\0\0L\0cronExpressiont\0Ljava/lang/String;L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\nmethodNameq\0~\0	L\0methodParamsq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0*com.facedamon.smart.common.base.BaseEntity0eԭ\�\Zvy\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0b,\�)\�xpt\0\0pppt\00/20 * * * * ?t\0系统默认（有参）sr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0\0\0\0\0t\0ryTaskt\0ryParamst\0ryt\01t\01x\0');
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_LOCKS` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
INSERT INTO `QRTZ_LOCKS` VALUES ('SmartScheduler','STATE_ACCESS'),('SmartScheduler','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('SmartScheduler','Mac.local1575272807240',1575272930314,15000);
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_TRIGGERS` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `QRTZ_JOB_DETAILS` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

LOCK TABLES `QRTZ_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SmartScheduler','__TASK_CLASS_NAME__1','DEFAULT','__TASK_CLASS_NAME__1','DEFAULT',NULL,1550112880000,-1,5,'PAUSED','CRON',1550112878000,0,NULL,-1,''),('SmartScheduler','__TASK_CLASS_NAME__2','DEFAULT','__TASK_CLASS_NAME__2','DEFAULT',NULL,1550112880000,-1,5,'PAUSED','CRON',1550112878000,0,NULL,-1,'');
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数ID',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键',
  `config_value` varchar(100) DEFAULT '' COMMENT '参数值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置(Y:是,N:否)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','初始化密码 123456');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` int(11) DEFAULT '0' COMMENT '父部门ID',
  `ancestors` varchar(50) DEFAULT '' COMMENT '树形列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态(0:正常,1:停用)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','Smart',0,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(101,100,'0,100','成都总公司',1,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(102,100,'0,100','重庆分公司',2,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(103,101,'0,100,101','研发部门',1,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(104,101,'0,100,101','市场部门',2,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(105,101,'0,100,101','测试部门',3,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(106,101,'0,100,101','财务部门',4,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(107,101,'0,100,101','运维部门',5,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(108,102,'0,100,102','市场部门',1,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(109,102,'0,100,102','财务部门',2,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(110,108,'0,100,102,108','ceshi',1,'ss','17600603967','facedamon@163.com','0','admin','2018-11-15 18:44:17','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` int(1) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(500) DEFAULT '' COMMENT '样式属性',
  `list_class` varchar(500) DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认(Y:是，N:否)',
  `status` char(1) DEFAULT '0' COMMENT '状态(0:正常,1:停用)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','性别女'),(3,3,'畜牲','2','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','admin','2018-11-18 22:04:22','性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(10,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统默认是'),(11,2,'否','N','sys_yes_no','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统默认否'),(12,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知'),(13,2,'公告','2','sys_notice_type','','success','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','公告'),(14,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(15,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','关闭状态'),(16,1,'新增','1','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','新增操作'),(17,2,'修改','2','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','修改操作'),(18,3,'删除','3','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','删除操作'),(19,4,'授权','4','sys_oper_type','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','授权操作'),(20,5,'导出','5','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','导出操作'),(21,6,'导入','6','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','导入操作'),(22,7,'强退','7','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','强退操作'),(23,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','生成操作'),(24,8,'清空数据','9','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','清空操作'),(25,1,'成功','0','sys_common_status','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','正常状态'),(26,2,'失败','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','停用状态'),(27,1,'Python','1','busi_type','','default','Y','0','admin','2019-05-26 16:05:15','admin','2019-05-26 16:06:56','');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态(0:正常,1:停用)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','任务状态列表'),(5,'系统是否','sys_yes_no','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统是否列表'),(6,'通知类型','sys_notice_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知类型列表'),(7,'通知状态','sys_notice_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知状态列表'),(8,'操作类型','sys_oper_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','操作类型列表'),(9,'系统状态','sys_common_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','登录状态列表'),(10,'业务类型','busi_type','0','admin','2019-05-26 16:04:21','',NULL,'业务类型');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(500) DEFAULT '' COMMENT '任务参数',
  `cron_expression` varchar(255) DEFAULT '' COMMENT '时间表达式',
  `misfire_policy` varchar(20) DEFAULT '0' COMMENT '任务执行错误策略(0:默认,1:继续,2:等待,2:放弃)',
  `status` char(1) DEFAULT '0' COMMENT '状态(0:正常,1:暂停)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`job_id`,`job_group`,`job_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'ryTask','系统默认（无参）','ryNoParams','','0/10 * * * * ?','0','1','admin','2018-03-16 11:33:00','admin','2019-03-15 09:58:17',''),(2,'ryTask','系统默认（有参）','ryParams','ry','0/20 * * * * ?','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(500) DEFAULT '' COMMENT '任务参数',
  `job_message` varchar(500) DEFAULT '' COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态(0:正常,1:失败)',
  `exception_info` text COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_logininfor` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统类型',
  `status` char(1) DEFAULT '0' COMMENT '登录状态(0:正常，1:失败)',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='系统访问记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (1,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-05-26 17:38:47'),(2,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-05-26 17:57:16'),(3,'facedamon','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-05-26 17:57:22'),(4,'facedamon','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-05-26 18:06:55'),(5,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-07-10 16:58:55'),(6,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-07-10 17:10:24'),(7,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-07-10 17:11:43'),(8,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-09 14:47:17'),(9,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-09-09 14:47:22'),(10,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-09 14:47:28'),(11,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-09 14:57:40'),(12,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-09 17:07:49'),(13,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-09 19:06:05'),(14,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-09-09 19:08:26'),(15,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-10 11:07:27'),(16,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-10 14:00:18'),(17,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-09-10 14:41:27'),(18,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 10:35:13'),(19,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 10:35:19'),(20,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 10:35:22'),(21,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 11:43:52'),(22,'admin','127.0.0.1',NULL,'Safari','Mac OS X','1','密码输入错误{0}次','2019-12-02 14:35:15'),(23,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 14:35:19'),(24,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 14:45:53'),(25,'damon','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 14:45:57'),(26,'damon','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 14:46:02'),(27,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 14:46:05'),(28,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 14:48:30'),(29,'damon','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 14:48:34'),(30,'damon','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 14:48:40'),(31,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 14:48:43'),(32,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 15:13:36'),(33,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 15:13:39'),(34,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 15:38:14'),(35,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 15:38:33'),(36,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','登录成功','2019-12-02 15:38:36'),(37,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','退出成功','2019-12-02 15:38:54'),(38,'admin','127.0.0.1',NULL,'Chrome','Mac OS X','0','登录成功','2019-12-02 15:40:22');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '上级菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求URL',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型(M:目录,C:菜单,F:按钮)',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态(0：显示,1:隐藏)',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1095 DEFAULT CHARSET=utf8 COMMENT='菜单信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'#','M','0','','fa fa-gear','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统管理目录'),(2,'系统监控',0,2,'#','M','0','','fa fa-video-camera','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统监控目录'),(3,'系统工具',0,3,'#','M','0','','fa fa-navicon','admin','2018-03-16 11:33:00','admin','2019-09-09 17:09:58','系统工具目录'),(100,'用户管理',1,1,'/system/user','C','0','system:user:view','#','admin','2018-03-16 11:33:00','admin','2019-05-26 15:59:47','用户管理菜单'),(101,'角色管理',1,2,'/system/role','C','0','system:role:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','角色管理菜单'),(102,'菜单管理',1,3,'/system/menu','C','0','system:menu:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','菜单管理菜单'),(103,'部门管理',1,4,'/system/dept','C','0','system:dept:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','部门管理菜单'),(104,'岗位管理',1,5,'/system/post','C','0','system:post:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','岗位管理菜单'),(105,'字典管理',1,6,'/system/dict','C','0','system:dict:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','字典管理菜单'),(107,'通知公告',1,8,'/system/notice','C','0','system:notice:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','通知公告菜单'),(108,'日志管理',1,9,'#','M','0','','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','日志管理菜单'),(109,'在线用户',2,1,'/monitor/online','C','0','monitor:online:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','在线用户菜单'),(110,'定时任务',2,2,'/monitor/job','C','0','monitor:job:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','定时任务菜单'),(111,'数据监控',2,3,'/monitor/datasource','C','0','monitor:data:view','#','admin','2018-03-16 11:33:00','admin','2019-03-01 14:21:58','数据监控菜单'),(112,'表单构建',3,1,'/tool/build','C','0','tool:build:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','表单构建菜单'),(113,'代码生成',3,2,'/tool/gen','C','0','tool:gen:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','代码生成菜单'),(114,'系统接口',3,3,'/tool/swagger','C','0','tool:swagger:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','系统接口菜单'),(500,'操作日志',108,1,'/monitor/operlog','C','0','monitor:operlog:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','操作日志菜单'),(501,'登录日志',108,2,'/monitor/logininfor','C','0','monitor:logininfor:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','登录日志菜单'),(1000,'用户查询',100,1,'#','F','0','system:user:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1001,'用户新增',100,2,'#','F','0','system:user:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1002,'用户修改',100,3,'#','F','0','system:user:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1003,'用户删除',100,4,'#','F','0','system:user:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1004,'用户导出',100,5,'#','F','0','system:user:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1005,'重置密码',100,5,'#','F','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1006,'角色查询',101,1,'#','F','0','system:role:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1007,'角色新增',101,2,'#','F','0','system:role:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1008,'角色修改',101,3,'#','F','0','system:role:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1009,'角色删除',101,4,'#','F','0','system:role:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1010,'角色导出',101,4,'#','F','0','system:role:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1011,'菜单查询',102,1,'#','F','0','system:menu:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1012,'菜单新增',102,2,'#','F','0','system:menu:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1013,'菜单修改',102,3,'#','F','0','system:menu:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1014,'菜单删除',102,4,'#','F','0','system:menu:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1015,'部门查询',103,1,'#','F','0','system:dept:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1016,'部门新增',103,2,'#','F','0','system:dept:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1017,'部门修改',103,3,'#','F','0','system:dept:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1018,'部门删除',103,4,'#','F','0','system:dept:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1019,'岗位查询',104,1,'#','F','0','system:post:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1020,'岗位新增',104,2,'#','F','0','system:post:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1021,'岗位修改',104,3,'#','F','0','system:post:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1022,'岗位删除',104,4,'#','F','0','system:post:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1023,'岗位导出',104,4,'#','F','0','system:post:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1024,'字典查询',105,1,'#','F','0','system:dict:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1025,'字典新增',105,2,'#','F','0','system:dict:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1026,'字典修改',105,3,'#','F','0','system:dict:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1027,'字典删除',105,4,'#','F','0','system:dict:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1028,'字典导出',105,4,'#','F','0','system:dict:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1034,'公告查询',107,1,'#','F','0','system:notice:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1035,'公告新增',107,2,'#','F','0','system:notice:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1036,'公告修改',107,3,'#','F','0','system:notice:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1037,'公告删除',107,4,'#','F','0','system:notice:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1038,'操作查询',500,1,'#','F','0','monitor:operlog:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1039,'操作删除',500,2,'#','F','0','monitor:operlog:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1040,'详细信息',500,3,'#','F','0','monitor:operlog:detail','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1041,'日志导出',500,3,'#','F','0','monitor:operlog:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1042,'登录查询',501,1,'#','F','0','monitor:logininfor:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1043,'登录删除',501,2,'#','F','0','monitor:logininfor:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1044,'日志导出',501,2,'#','F','0','monitor:logininfor:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1045,'在线查询',109,1,'#','F','0','monitor:online:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1046,'批量强退',109,2,'#','F','0','monitor:online:batchForceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1047,'单条强退',109,3,'#','F','0','monitor:online:forceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1048,'任务查询',110,1,'#','F','0','monitor:job:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1049,'任务新增',110,2,'#','F','0','monitor:job:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1050,'任务修改',110,3,'#','F','0','monitor:job:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1051,'任务删除',110,4,'#','F','0','monitor:job:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1052,'状态修改',110,5,'#','F','0','monitor:job:changeStatus','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1053,'任务导出',110,5,'#','F','0','monitor:job:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1054,'生成查询',113,1,'#','F','0','tool:gen:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1055,'生成代码',113,2,'#','F','0','tool:gen:code','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1065,'test',100,7,'#','F','0','system:user:test','#','mylusing','2018-11-18 18:16:53','',NULL,''),(1094,'参数管理',1,7,'system/config','C','0','system:config:view','#','admin','2019-12-02 15:35:13','admin','2019-12-02 15:41:24','');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(2) NOT NULL COMMENT '公告类型(1:通知,2:公告)',
  `notice_content` varchar(500) NOT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态(0:正常,1:关闭)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2019-07-01 新版本发布','2','新版本内容<div><table class=\"table table-bordered\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>','0','admin','2018-03-16 11:33:00','admin','2019-12-02 15:16:18','管理员'),(2,'维护通知：2018-07-01 系统凌晨维护','1','维护内容','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `model` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型(0:其它,1:新增,2:修改，3:删除)',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类型(0:其它,1:后台用户,2:手机端用户)',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(30) DEFAULT '' COMMENT '请求主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(255) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态(0:正常,1:异常)',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误信息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='操作日志记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (1,'清空日志',9,'com.facedamon.smart.web.controller.monitor.OperLogController.clean()',1,'admin','研发部门','/monitor/operlog/clean','127.0.0.1',NULL,'{}',0,NULL,'2019-05-26 16:01:19'),(2,'清空登陆日志',9,'com.facedamon.smart.web.controller.monitor.LoginInfoController.clean()',1,'admin','研发部门','/monitor/logininfor/clean','127.0.0.1',NULL,'{}',0,NULL,'2019-05-26 16:02:02'),(3,'新增字典类型',1,'com.facedamon.smart.web.controller.system.DictTypeController.add()',1,'admin','研发部门','/system/dict/add','127.0.0.1',NULL,'{\"dictName\":[\"业务类型\"],\"dictType\":[\"busi_type\"],\"status\":[\"0\"],\"remark\":[\"业务类型\"]}',0,NULL,'2019-05-26 16:04:21'),(4,'新增字典数据',1,'com.facedamon.smart.web.controller.system.DictDataController.add()',1,'admin','研发部门','/system/dict/data/add','127.0.0.1',NULL,'{\"dictLabel\":[\"Python\"],\"dictValue\":[\"1\"],\"dictType\":[\"busi_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"info\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-05-26 16:05:15'),(5,'用户模块',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','研发部门','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"4\"],\"deptId\":[\"103\"],\"userName\":[\"tanhong\"],\"email\":[\"9803433@qq.com\"],\"phonenumber\":[\"13100000000\"],\"sex\":[\"1\"],\"status\":[\"0\"],\"roleIds\":[\"5\"],\"postIds\":[\"4\"]}',0,NULL,'2019-05-26 16:05:46'),(6,'导出字典数据',5,'com.facedamon.smart.web.controller.system.DictDataController.export()',1,'admin','研发部门','/system/dict/data/export','127.0.0.1',NULL,'{\"dictType\":[\"busi_type\"],\"dictLabel\":[\"\"],\"status\":[\"\"]}',0,NULL,'2019-05-26 16:06:16'),(7,'更新字典数据',2,'com.facedamon.smart.web.controller.system.DictDataController.edit()',1,'admin','研发部门','/system/dict/data/edit','127.0.0.1',NULL,'{\"dictCode\":[\"27\"],\"dictLabel\":[\"Python\"],\"dictValue\":[\"1\"],\"dictType\":[\"busi_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"default\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-05-26 16:06:56'),(8,'币种参数',1,'com.facedamon.smart.web.controller.param.CurrencyController.addSave()',1,'admin','研发部门','/param/currency/add','127.0.0.1',NULL,'{\"currencyName\":[\"人民币\"],\"currencySign\":[\"CNY\"],\"status\":[\"0\"],\"currencyFlag\":[\"on\"],\"remark\":[\"\"]}',1,'\n### Error updating database.  Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column \'currency_flag\' at row 1\n### The error may involve com.facedamon.smart.param.mapper.CurrencyMapper.insertCurrency-Inline\n### The error occurred while setting parameters\n### SQL: insert into smart_param_currency          ( currency_name,                          currency_sign,                          status,                          currency_flag )           values ( ?,                          ?,                          ?,                          ? )\n### Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column \'currency_flag\' at row 1\n; ]; Data truncation: Data too long for column \'currency_flag\' at row 1; nested exception is com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column \'currency_flag\' at row 1','2019-05-26 16:10:50'),(9,'修改数据权限',2,'com.facedamon.smart.web.controller.system.RoleController.rule()',1,'admin','研发部门','/system/role/rule','127.0.0.1',NULL,'{\"roleId\":[\"4\"],\"roleName\":[\"支行头寸管理员\"],\"roleKey\":[\"zhihang\"],\"dataScope\":[\"2\"],\"deptIds\":[\"100,101,103\"]}',0,NULL,'2019-05-26 17:56:51'),(10,'用户模块',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','研发部门','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"3\"],\"deptId\":[\"107\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@qq.com\"],\"phonenumber\":[\"17600603969\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"4\"],\"postIds\":[\"4\"]}',0,NULL,'2019-05-26 17:57:09'),(11,'强退用户',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.forceLogout()',1,'admin','研发部门','/monitor/online/forceLogout','127.0.0.1',NULL,'{\"sessionId\":[\"f4ce6581-3515-48d5-a643-03a038902fe9\"]}',0,NULL,'2019-07-10 16:59:41'),(12,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1062','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:48:40'),(13,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1066','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:51:40'),(14,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1067','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:51:45'),(15,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1066','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:51:49'),(16,'批量代码生成',8,'com.facedamon.smart.web.controller.tool.GeneratorController.genCode()',1,'admin','研发部门','/tool/gen/batchGenCode','127.0.0.1',NULL,'{\"tableNames\":[\"Model_User_Info,Model_Table_Info,Model_Field_Table_Quote,Model_Field_Code_Value_Info,Model_Base_Word_Info,Model_Base_Word_Field_Quote,Model_Field_Info\"]}',0,NULL,'2019-09-09 15:36:50'),(17,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"模型管理\"],\"url\":[\"\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-database\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-09 17:09:17'),(18,'更新菜单',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','研发部门','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"3\"],\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"系统工具\"],\"url\":[\"#\"],\"perms\":[\"\"],\"orderNum\":[\"3\"],\"icon\":[\"fa fa-navicon\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-09 17:09:58'),(19,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"基词管理\"],\"url\":[\"model/baseWordInfo\"],\"perms\":[\"model:baseWordInfo\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-09 17:13:19'),(20,'更新菜单',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','研发部门','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1069\"],\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"基词管理\"],\"url\":[\"model/baseWordInfo\"],\"perms\":[\"model:baseWordInfo:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-09 17:13:46'),(21,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"基词查询\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:10:24'),(22,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"基词新增\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:10:54'),(23,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"基词编辑\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:11:25'),(24,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"基词删除\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:11:44'),(25,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1065','127.0.0.1',NULL,'{}',0,NULL,'2019-09-10 11:11:56'),(26,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"字段管理\"],\"url\":[\"model/fieldInfo\"],\"perms\":[\"model:fieldInfo:view\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:02:03'),(27,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"字段查询\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:02:52'),(28,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"字段新增\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:04:40'),(29,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"字段编辑\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:05:09'),(30,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"字段删除\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:05:41'),(31,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"模型表管理\"],\"url\":[\"model/tableInfo\"],\"perms\":[\"model:tableInfo:view\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:59:56'),(32,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"模型表查询\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:00:56'),(33,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"模型表新增\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:01:22'),(34,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"模型表编辑\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:01:49'),(35,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"模型表删除\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:03:05'),(36,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"模型用户\"],\"url\":[\"model/userInfo\"],\"perms\":[\"model:userInfo:view\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:14:43'),(37,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"模型用户查询\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:15:50'),(38,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"模型用户新增\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:16:28'),(39,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"模型用户编辑\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:16:52'),(40,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"模型用户删除\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:17:20'),(41,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"码值表\"],\"url\":[\"model/fieldCodeValueInfo\"],\"perms\":[\"model:fieldCodeValueInfo\"],\"orderNum\":[\"5\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:30:40'),(42,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"码值查询\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:view\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:31:09'),(43,'更新菜单',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin',NULL,'/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1089\"],\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"码值表\"],\"url\":[\"model/fieldCodeValueInfo\"],\"perms\":[\"model:fieldCodeValueInfo:view\"],\"orderNum\":[\"5\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-10 15:31:22'),(44,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"码值新增\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:32:06'),(45,'更新菜单',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin',NULL,'/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1090\"],\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"码值查询\"],\"url\":[\"#\"],\"perms\":[\"model:fieldCodeValueInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-10 15:32:24'),(46,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"码值编辑\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:32:50'),(47,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"码值删除\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:33:34'),(48,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 10:35:33'),(49,'删除用户',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','研发部门','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"3\"]}',0,NULL,'2019-12-02 10:35:51'),(50,'删除用户',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','研发部门','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 10:35:54'),(51,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 10:36:01'),(52,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 10:36:04'),(53,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 10:36:07'),(54,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 10:36:10'),(55,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1089','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:44:37'),(56,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1090','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:44:46'),(57,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1070','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:01'),(58,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1071','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:11'),(59,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1072','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:19'),(60,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1073','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:26'),(61,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1069','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:33'),(62,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1075','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:40'),(63,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1076','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:48'),(64,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1077','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:58'),(65,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1078','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:03'),(66,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1074','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:06'),(67,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1080','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:10'),(68,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1081','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:16'),(69,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1082','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:20'),(70,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1083','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:24'),(71,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1079','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:28'),(72,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1085','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:34'),(73,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1086','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:41'),(74,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1087','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:46'),(75,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1088','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:53'),(76,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1084','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:57'),(77,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1091','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:05'),(78,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1092','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:12'),(79,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1093','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:18'),(80,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1089','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:23'),(81,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1068','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:26'),(82,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1029','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:53'),(83,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 11:44:02'),(84,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"3\"]}',0,NULL,'2019-12-02 14:35:40'),(85,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:35:43'),(86,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 14:35:47'),(87,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 14:35:50'),(88,'删除用户',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','研发部门','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"4,3\"]}',0,NULL,'2019-12-02 14:36:01'),(89,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:36:06'),(90,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 14:36:10'),(91,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 14:36:14'),(92,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:36:25'),(93,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:37:07'),(94,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:39:39'),(95,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1029','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:39:56'),(96,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1029','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:11'),(97,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1030','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:17'),(98,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1031','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:21'),(99,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1032','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:28'),(100,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/1033','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:36'),(101,'用户模块',1,'com.facedamon.smart.web.controller.system.UserController.add()',1,'admin','研发部门','/system/user/add','127.0.0.1',NULL,'{\"deptId\":[\"104\"],\"loginName\":[\"damon\"],\"userName\":[\"facedamon\"],\"password\":[\"damon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:45:39'),(102,'新增角色',1,'com.facedamon.smart.web.controller.system.RoleController.add()',1,'admin','研发部门','/system/role/add','127.0.0.1',NULL,'{\"roleName\":[\"damon\"],\"roleKey\":[\"Damon\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"\"],\"menuIds\":[\"1,106\"]}',0,NULL,'2019-12-02 14:46:39'),(103,'用户模块',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','研发部门','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"5\"],\"deptId\":[\"104\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"6\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:47:19'),(104,'用户模块',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','研发部门','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"5\"],\"deptId\":[\"104\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:47:28'),(105,'用户模块',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','研发部门','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"5\"],\"deptId\":[\"104\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"6\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:47:34'),(106,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:47:55'),(107,'删除用户',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','研发部门','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 14:48:55'),(108,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:52:18'),(109,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','研发部门','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"6\"]}',0,NULL,'2019-12-02 14:55:54'),(110,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','研发部门','/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:55:59'),(111,'新增角色',1,'com.facedamon.smart.web.controller.system.RoleController.add()',1,'admin',NULL,'/system/role/add','127.0.0.1',NULL,'{\"roleName\":[\"damon\"],\"roleKey\":[\"Damon\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"\"],\"menuIds\":[\"1,106\"]}',0,NULL,'2019-12-02 15:11:28'),(112,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin',NULL,'/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 15:11:41'),(113,'删除角色',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin',NULL,'/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"7\"]}',0,NULL,'2019-12-02 15:11:47'),(114,'删除菜单',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin',NULL,'/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 15:11:52'),(115,'删除字典类型',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin',NULL,'/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"10\"]}',0,NULL,'2019-12-02 15:12:38'),(116,'更新公告',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','研发部门','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"温馨提醒：2019-07-01 新版本发布\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"新版本内容<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-02 15:15:42'),(117,'更新公告',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','研发部门','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"温馨提醒：2019-07-01 新版本发布\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"新版本内容<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-02 15:15:52'),(118,'更新公告',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','研发部门','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"温馨提醒：2019-07-01 新版本发布\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"新版本内容<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-02 15:16:18'),(119,'删除字典类型',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin','研发部门','/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 15:25:35'),(120,'新增部门',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','研发部门','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"参数管理\"],\"url\":[\"system.config\"],\"perms\":[\"system:config\"],\"orderNum\":[\"7\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-12-02 15:35:13'),(121,'更新菜单',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','研发部门','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1094\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"参数管理\"],\"url\":[\"system/config\"],\"perms\":[\"system:config\"],\"orderNum\":[\"7\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-12-02 15:41:09'),(122,'更新菜单',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','研发部门','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1094\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"参数管理\"],\"url\":[\"system/config\"],\"perms\":[\"system:config:view\"],\"orderNum\":[\"7\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-12-02 15:41:24');
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '岗位状态(0:正常,1:停用)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(2,'se','项目经理',2,'0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(3,'hr','人力资源',3,'0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(4,'user','普通员工',4,'0','admin','2018-03-16 11:33:00','admin','2018-11-18 15:44:17','cesh');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据权限(1:全部数据权限,2:自定义数据权限)',
  `status` char(1) NOT NULL COMMENT '角色状态(0:正常,1:停用)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'管理员','admin',1,'1','0','admin','2018-03-16 11:33:00','admin','2018-11-19 20:04:12','管理员');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_dept` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100),(2,101),(2,105),(4,100),(4,101),(4,103),(5,100),(5,101),(5,102),(5,103),(5,104),(5,105),(5,106),(5,107),(5,108),(5,109);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登陆账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型(00:系统用户)',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '联系电话',
  `sex` char(1) DEFAULT '0' COMMENT '性别(0:mail,1:femail,2:noone)',
  `avatar` varchar(100) DEFAULT '' COMMENT '图像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '账号状态(0:正常，1:停用)',
  `login_ip` varchar(20) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','admin','00','facedamon@163.com','15888888888','0','','8d8bc547e87209063b530e2b0f15f4fd','2741e9','0','127.0.0.1','2019-12-02 15:40:23','admin','2018-03-16 11:33:00','ry','2019-12-02 15:40:22','管理员');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_online`
--

DROP TABLE IF EXISTS `sys_user_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账户',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT 'IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统类型',
  `status` varchar(10) DEFAULT '0' COMMENT '在线状态(on_line,off_line)',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'seesion最后访问时间',
  `expire_time` int(5) DEFAULT '5' COMMENT '超时时长/min',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_online`
--

LOCK TABLES `sys_user_online` WRITE;
/*!40000 ALTER TABLE `sys_user_online` DISABLE KEYS */;
INSERT INTO `sys_user_online` VALUES ('083d007e-e958-4562-821b-b43e76d0a6c2','admin','研发部门','127.0.0.1',NULL,'Safari','Mac OS X','ON_LINE','2019-12-02 14:48:41','2019-12-02 14:55:45',1800000),('150c7875-f6ca-4c9f-b4a2-aded8d3394c0','admin','研发部门','127.0.0.1',NULL,'Chrome','Mac OS X','ON_LINE','2019-12-02 15:40:11','2019-12-02 15:42:48',1800000),('befd4133-d122-4620-ab92-37c440fdb32a','admin','研发部门','127.0.0.1',NULL,'Safari','Mac OS X','ON_LINE','2019-12-02 15:13:36','2019-12-02 15:34:37',1800000),('d39dc973-3d6d-4242-87be-03198bdfa86a','admin','研发部门','127.0.0.1',NULL,'Safari','Mac OS X','ON_LINE','2019-12-02 11:43:47','2019-12-02 11:43:54',1800000),('f56d2460-7848-4eb4-9c86-61e4f36dc947','admin','研发部门','127.0.0.1',NULL,'Safari','Mac OS X','ON_LINE','2019-12-02 10:35:20','2019-12-02 10:49:06',1800000);
/*!40000 ALTER TABLE `sys_user_online` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_post` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-02 15:55:04
