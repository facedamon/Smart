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
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SmartScheduler','__TASK_CLASS_NAME__1','DEFAULT',NULL,'com.facedamon.smart.quartz.support.ScheduleJob','0','0','0','0','¬\í\0sr\0org.quartz.JobDataMap°è¿©°\Ë\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap\è\Ãû\Å](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\æ.­(v\n\Î\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0__TASK_PROPERTIES__sr\0%com.facedamon.smart.quartz.domain.Job\0\0\0\0\0\0\0\0L\0cronExpressiont\0Ljava/lang/String;L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\nmethodNameq\0~\0	L\0methodParamsq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0*com.facedamon.smart.common.base.BaseEntity0eÔ­\Ä\Zvy\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.DatehjKYt\0\0xpw\0\0b,\Þ)\àxpt\0\0pppt\00/10 * * * * ?t\0ç³»ç»é»è®¤ï¼æ åï¼sr\0java.lang.Long;\äÌ#\ß\0J\0valuexr\0java.lang.Number¬\à\0\0xp\0\0\0\0\0\0\0t\0ryTaskt\0\nryNoParamst\0\0t\01t\01x\0'),('SmartScheduler','__TASK_CLASS_NAME__2','DEFAULT',NULL,'com.facedamon.smart.quartz.support.ScheduleJob','0','0','0','0','¬\í\0sr\0org.quartz.JobDataMap°è¿©°\Ë\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap\è\Ãû\Å](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\æ.­(v\n\Î\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0__TASK_PROPERTIES__sr\0%com.facedamon.smart.quartz.domain.Job\0\0\0\0\0\0\0\0L\0cronExpressiont\0Ljava/lang/String;L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\nmethodNameq\0~\0	L\0methodParamsq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0*com.facedamon.smart.common.base.BaseEntity0eÔ­\Ä\Zvy\0L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.DatehjKYt\0\0xpw\0\0b,\Þ)\àxpt\0\0pppt\00/20 * * * * ?t\0ç³»ç»é»è®¤ï¼æåï¼sr\0java.lang.Long;\äÌ#\ß\0J\0valuexr\0java.lang.Number¬\à\0\0xp\0\0\0\0\0\0\0t\0ryTaskt\0ryParamst\0ryt\01t\01x\0');
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
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('SmartScheduler','Mac.local1575340215418',1575366476741,15000);
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
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'åæ°ID',
  `config_name` varchar(100) DEFAULT '' COMMENT 'åæ°å',
  `config_key` varchar(100) DEFAULT '' COMMENT 'åæ°é®',
  `config_value` varchar(100) DEFAULT '' COMMENT 'åæ°å¼',
  `config_type` char(1) DEFAULT 'N' COMMENT 'ç³»ç»åç½®(Y:æ¯,N:å¦)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='åæ°éç½®è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'ç¨æ·ç®¡ç-è´¦å·åå§å¯ç ','sys.user.initPassword','123456','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','åå§åå¯ç  123456');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'é¨é¨ID',
  `parent_id` int(11) DEFAULT '0' COMMENT 'ç¶é¨é¨ID',
  `ancestors` varchar(50) DEFAULT '' COMMENT 'æ å½¢åè¡¨',
  `dept_name` varchar(30) DEFAULT '' COMMENT 'é¨é¨åç§°',
  `order_num` int(4) DEFAULT '0' COMMENT 'æ¾ç¤ºé¡ºåº',
  `leader` varchar(20) DEFAULT '' COMMENT 'è´è´£äºº',
  `phone` varchar(11) DEFAULT '' COMMENT 'èç³»çµè¯',
  `email` varchar(50) DEFAULT '' COMMENT 'é®ç®±',
  `status` char(1) DEFAULT '0' COMMENT 'é¨é¨ç¶æ(0:æ­£å¸¸,1:åç¨)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='é¨é¨è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','Smart',0,'facedamon','15888888888','facedamon@163.com','1','admin','2018-03-16 11:33:00','admin','2019-12-03 17:47:56'),(101,100,'0,100','æé½æ»å¬å¸',1,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','admin','2019-12-03 17:46:10'),(102,100,'0,100','éåºåå¬å¸',2,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','admin','2019-12-03 17:46:33'),(103,101,'0,100,101','ç åé¨é¨',1,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','admin','2019-12-03 16:09:56'),(104,101,'0,100,101','å¸åºé¨é¨',2,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(105,101,'0,100,101','æµè¯é¨é¨',3,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(106,101,'0,100,101','è´¢å¡é¨é¨',4,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(107,101,'0,100,101','è¿ç»´é¨é¨',5,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(108,102,'0,100,102','å¸åºé¨é¨',1,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(109,102,'0,100,102','è´¢å¡é¨é¨',2,'facedamon','15888888888','facedamon@163.com','0','admin','2018-03-16 11:33:00','facedamon','2018-03-16 11:33:00'),(111,103,'0,100,101,103','test',1,'1','18588888888','facedamo@163.con','0','admin','2019-12-03 16:32:53','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` int(1) NOT NULL AUTO_INCREMENT COMMENT 'å­å¸ç¼ç ',
  `dict_sort` int(4) DEFAULT '0' COMMENT 'å­å¸æåº',
  `dict_label` varchar(100) DEFAULT '' COMMENT 'å­å¸æ ç­¾',
  `dict_value` varchar(100) DEFAULT '' COMMENT 'å­å¸å¼',
  `dict_type` varchar(100) DEFAULT '' COMMENT 'å­å¸ç±»å',
  `css_class` varchar(500) DEFAULT '' COMMENT 'æ ·å¼å±æ§',
  `list_class` varchar(500) DEFAULT '' COMMENT 'è¡¨æ ¼åæ¾æ ·å¼',
  `is_default` char(1) DEFAULT 'N' COMMENT 'æ¯å¦é»è®¤(Y:æ¯ï¼N:å¦)',
  `status` char(1) DEFAULT '0' COMMENT 'ç¶æ(0:æ­£å¸¸,1:åç¨)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='å­å¸æ°æ®è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'ç·','0','sys_user_sex','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ§å«ç·'),(2,2,'å¥³','1','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ§å«å¥³'),(3,3,'çç²','2','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','admin','2018-11-18 22:04:22','æ§å«æªç¥'),(4,1,'æ¾ç¤º','0','sys_show_hide','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ¾ç¤ºèå'),(5,2,'éè','1','sys_show_hide','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','éèèå'),(6,1,'æ­£å¸¸','0','sys_normal_disable','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ­£å¸¸ç¶æ'),(7,2,'åç¨','1','sys_normal_disable','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','åç¨ç¶æ'),(8,1,'æ­£å¸¸','0','sys_job_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ­£å¸¸ç¶æ'),(9,2,'æå','1','sys_job_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','åç¨ç¶æ'),(10,1,'æ¯','Y','sys_yes_no','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»é»è®¤æ¯'),(11,2,'å¦','N','sys_yes_no','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»é»è®¤å¦'),(12,1,'éç¥','1','sys_notice_type','','warning','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','éç¥'),(13,2,'å¬å','2','sys_notice_type','','success','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å¬å'),(14,1,'æ­£å¸¸','0','sys_notice_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ­£å¸¸ç¶æ'),(15,2,'å³é­','1','sys_notice_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å³é­ç¶æ'),(16,1,'æ°å¢','1','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ°å¢æä½'),(17,2,'ä¿®æ¹','2','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ä¿®æ¹æä½'),(18,3,'å é¤','3','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å é¤æä½'),(19,4,'ææ','4','sys_oper_type','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æææä½'),(20,5,'å¯¼åº','5','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å¯¼åºæä½'),(21,6,'å¯¼å¥','6','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å¯¼å¥æä½'),(22,7,'å¼ºé','7','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å¼ºéæä½'),(23,8,'çæä»£ç ','8','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','çææä½'),(24,8,'æ¸ç©ºæ°æ®','9','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ¸ç©ºæä½'),(25,1,'æå','0','sys_common_status','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ­£å¸¸ç¶æ'),(26,2,'å¤±è´¥','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','åç¨ç¶æ'),(28,1,'ç³»ç»ç¨æ·','00','sys_user_type','','primary','Y','0','admin','2019-12-03 14:17:31','',NULL,''),(29,2,'ä¸å¡ç¨æ·','01','sys_user_type','','primary','Y','0','admin','2019-12-03 14:18:00','',NULL,'');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'å­å¸ID',
  `dict_name` varchar(100) DEFAULT '' COMMENT 'å­å¸åç§°',
  `dict_type` varchar(100) DEFAULT '' COMMENT 'å­å¸ç±»å',
  `status` char(1) DEFAULT '0' COMMENT 'ç¶æ(0:æ­£å¸¸,1:åç¨)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='å­å¸ç±»åè¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'ç¨æ·æ§å«','sys_user_sex','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç¨æ·æ§å«åè¡¨'),(2,'èåç¶æ','sys_show_hide','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','èåç¶æåè¡¨'),(3,'ç³»ç»å¼å³','sys_normal_disable','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»å¼å³åè¡¨'),(4,'ä»»å¡ç¶æ','sys_job_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ä»»å¡ç¶æåè¡¨'),(5,'ç³»ç»æ¯å¦','sys_yes_no','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»æ¯å¦åè¡¨'),(6,'éç¥ç±»å','sys_notice_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','éç¥ç±»ååè¡¨'),(7,'éç¥ç¶æ','sys_notice_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','éç¥ç¶æåè¡¨'),(8,'æä½ç±»å','sys_oper_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æä½ç±»ååè¡¨'),(9,'ç³»ç»ç¶æ','sys_common_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç»å½ç¶æåè¡¨'),(11,'ç¨æ·ç±»å','sys_user_type','0','admin','2019-12-03 14:16:41','',NULL,'ç¨æ·ç±»å');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä»»å¡ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT 'ä»»å¡åç§°',
  `job_group` varchar(64) NOT NULL DEFAULT '' COMMENT 'ä»»å¡ç»å',
  `method_name` varchar(500) DEFAULT '' COMMENT 'ä»»å¡æ¹æ³',
  `method_params` varchar(500) DEFAULT '' COMMENT 'ä»»å¡åæ°',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'æ¶é´è¡¨è¾¾å¼',
  `misfire_policy` varchar(20) DEFAULT '0' COMMENT 'ä»»å¡æ§è¡éè¯¯ç­ç¥(0:é»è®¤,1:ç»§ç»­,2:ç­å¾,2:æ¾å¼)',
  `status` char(1) DEFAULT '0' COMMENT 'ç¶æ(0:æ­£å¸¸,1:æå)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`job_id`,`job_group`,`job_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='å®æ¶ä»»å¡è°åº¦è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'ryTask','ç³»ç»é»è®¤ï¼æ åï¼','ryNoParams','','0/10 * * * * ?','0','1','admin','2018-03-16 11:33:00','admin','2019-03-15 09:58:17',''),(2,'ryTask','ç³»ç»é»è®¤ï¼æåï¼','ryParams','ry','0/20 * * * * ?','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ä»»å¡æ¥å¿ID',
  `job_name` varchar(64) NOT NULL COMMENT 'ä»»å¡åç§°',
  `job_group` varchar(64) NOT NULL COMMENT 'ä»»å¡ç»å',
  `method_name` varchar(500) DEFAULT '' COMMENT 'ä»»å¡æ¹æ³',
  `method_params` varchar(500) DEFAULT '' COMMENT 'ä»»å¡åæ°',
  `job_message` varchar(500) DEFAULT '' COMMENT 'æ¥å¿ä¿¡æ¯',
  `status` char(1) DEFAULT '0' COMMENT 'æ§è¡ç¶æ(0:æ­£å¸¸,1:å¤±è´¥)',
  `exception_info` text COMMENT 'å¼å¸¸ä¿¡æ¯',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='å®æ¶ä»»å¡è°åº¦æ¥å¿è¡¨';
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
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'è®¿é®ID',
  `login_name` varchar(50) DEFAULT '' COMMENT 'ç»å½è´¦å·',
  `ipaddr` varchar(50) DEFAULT '' COMMENT 'ç»å½å°å',
  `login_location` varchar(255) DEFAULT '' COMMENT 'ç»å½å°ç¹',
  `browser` varchar(50) DEFAULT '' COMMENT 'æµè§å¨ç±»å',
  `os` varchar(50) DEFAULT '' COMMENT 'æä½ç³»ç»ç±»å',
  `status` char(1) DEFAULT '0' COMMENT 'ç»å½ç¶æ(0:æ­£å¸¸ï¼1:å¤±è´¥)',
  `msg` varchar(255) DEFAULT '' COMMENT 'æç¤ºæ¶æ¯',
  `login_time` datetime DEFAULT NULL COMMENT 'ç»å½æ¶é´',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='ç³»ç»è®¿é®è®°å½è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (1,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','ç»å½æå','2019-12-03 13:54:55'),(2,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','ç»å½æå','2019-12-03 16:09:16'),(3,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','éåºæå','2019-12-03 16:09:26'),(4,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','ç»å½æå','2019-12-03 16:09:30'),(5,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','ç»å½æå','2019-12-03 17:45:13'),(6,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','éåºæå','2019-12-03 17:45:17'),(7,'admin','127.0.0.1',NULL,'Safari','Mac OS X','0','ç»å½æå','2019-12-03 17:45:21');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'èåID',
  `menu_name` varchar(50) NOT NULL COMMENT 'èååç§°',
  `parent_id` int(11) DEFAULT '0' COMMENT 'ä¸çº§èåID',
  `order_num` int(4) DEFAULT '0' COMMENT 'æ¾ç¤ºé¡ºåº',
  `url` varchar(200) DEFAULT '#' COMMENT 'è¯·æ±URL',
  `menu_type` char(1) DEFAULT '' COMMENT 'èåç±»å(M:ç®å½,C:èå,F:æé®)',
  `visible` char(1) DEFAULT '0' COMMENT 'èåç¶æ(0ï¼æ¾ç¤º,1:éè)',
  `perms` varchar(100) DEFAULT '' COMMENT 'æéæ è¯',
  `icon` varchar(100) DEFAULT '#' COMMENT 'èåå¾æ ',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1095 DEFAULT CHARSET=utf8 COMMENT='èåä¿¡æ¯è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'ç³»ç»ç®¡ç',0,1,'#','M','0','','fa fa-gear','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»ç®¡çç®å½'),(2,'ç³»ç»çæ§',0,2,'#','M','0','','fa fa-video-camera','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»çæ§ç®å½'),(3,'ç³»ç»å·¥å·',0,3,'#','M','0','','fa fa-navicon','admin','2018-03-16 11:33:00','admin','2019-09-09 17:09:58','ç³»ç»å·¥å·ç®å½'),(100,'ç¨æ·ç®¡ç',1,1,'/system/user','C','0','system:user:view','#','admin','2018-03-16 11:33:00','admin','2019-05-26 15:59:47','ç¨æ·ç®¡çèå'),(101,'è§è²ç®¡ç',1,2,'/system/role','C','0','system:role:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','è§è²ç®¡çèå'),(102,'èåç®¡ç',1,3,'/system/menu','C','0','system:menu:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','èåç®¡çèå'),(103,'é¨é¨ç®¡ç',1,4,'/system/dept','C','0','system:dept:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','é¨é¨ç®¡çèå'),(104,'å²ä½ç®¡ç',1,5,'/system/post','C','0','system:post:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å²ä½ç®¡çèå'),(105,'å­å¸ç®¡ç',1,6,'/system/dict','C','0','system:dict:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å­å¸ç®¡çèå'),(107,'éç¥å¬å',1,8,'/system/notice','C','0','system:notice:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','éç¥å¬åèå'),(108,'æ¥å¿ç®¡ç',1,9,'#','M','0','','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æ¥å¿ç®¡çèå'),(109,'å¨çº¿ç¨æ·',2,1,'/monitor/online','C','0','monitor:online:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å¨çº¿ç¨æ·èå'),(110,'å®æ¶ä»»å¡',2,2,'/monitor/job','C','0','monitor:job:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','å®æ¶ä»»å¡èå'),(111,'æ°æ®çæ§',2,3,'/monitor/datasource','C','0','monitor:data:view','#','admin','2018-03-16 11:33:00','admin','2019-03-01 14:21:58','æ°æ®çæ§èå'),(112,'è¡¨åæå»º',3,1,'/tool/build','C','0','tool:build:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','è¡¨åæå»ºèå'),(113,'ä»£ç çæ',3,2,'/tool/gen','C','0','tool:gen:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ä»£ç çæèå'),(114,'ç³»ç»æ¥å£',3,3,'/tool/swagger','C','0','tool:swagger:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç³»ç»æ¥å£èå'),(500,'æä½æ¥å¿',108,1,'/monitor/operlog','C','0','monitor:operlog:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','æä½æ¥å¿èå'),(501,'ç»å½æ¥å¿',108,2,'/monitor/logininfor','C','0','monitor:logininfor:view','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç»å½æ¥å¿èå'),(1000,'ç¨æ·æ¥è¯¢',100,1,'#','F','0','system:user:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1001,'ç¨æ·æ°å¢',100,2,'#','F','0','system:user:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1002,'ç¨æ·ä¿®æ¹',100,3,'#','F','0','system:user:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1003,'ç¨æ·å é¤',100,4,'#','F','0','system:user:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1004,'ç¨æ·å¯¼åº',100,5,'#','F','0','system:user:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1005,'éç½®å¯ç ',100,5,'#','F','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1006,'è§è²æ¥è¯¢',101,1,'#','F','0','system:role:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1007,'è§è²æ°å¢',101,2,'#','F','0','system:role:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1008,'è§è²ä¿®æ¹',101,3,'#','F','0','system:role:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1009,'è§è²å é¤',101,4,'#','F','0','system:role:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1010,'è§è²å¯¼åº',101,4,'#','F','0','system:role:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1011,'èåæ¥è¯¢',102,1,'#','F','0','system:menu:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1012,'èåæ°å¢',102,2,'#','F','0','system:menu:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1013,'èåä¿®æ¹',102,3,'#','F','0','system:menu:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1014,'èåå é¤',102,4,'#','F','0','system:menu:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1015,'é¨é¨æ¥è¯¢',103,1,'#','F','0','system:dept:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1016,'é¨é¨æ°å¢',103,2,'#','F','0','system:dept:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1017,'é¨é¨ä¿®æ¹',103,3,'#','F','0','system:dept:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1018,'é¨é¨å é¤',103,4,'#','F','0','system:dept:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1019,'å²ä½æ¥è¯¢',104,1,'#','F','0','system:post:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1020,'å²ä½æ°å¢',104,2,'#','F','0','system:post:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1021,'å²ä½ä¿®æ¹',104,3,'#','F','0','system:post:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1022,'å²ä½å é¤',104,4,'#','F','0','system:post:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1023,'å²ä½å¯¼åº',104,4,'#','F','0','system:post:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1024,'å­å¸æ¥è¯¢',105,1,'#','F','0','system:dict:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1025,'å­å¸æ°å¢',105,2,'#','F','0','system:dict:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1026,'å­å¸ä¿®æ¹',105,3,'#','F','0','system:dict:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1027,'å­å¸å é¤',105,4,'#','F','0','system:dict:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1028,'å­å¸å¯¼åº',105,4,'#','F','0','system:dict:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1034,'å¬åæ¥è¯¢',107,1,'#','F','0','system:notice:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1035,'å¬åæ°å¢',107,2,'#','F','0','system:notice:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1036,'å¬åä¿®æ¹',107,3,'#','F','0','system:notice:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1037,'å¬åå é¤',107,4,'#','F','0','system:notice:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1038,'æä½æ¥è¯¢',500,1,'#','F','0','monitor:operlog:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1039,'æä½å é¤',500,2,'#','F','0','monitor:operlog:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1040,'è¯¦ç»ä¿¡æ¯',500,3,'#','F','0','monitor:operlog:detail','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1041,'æ¥å¿å¯¼åº',500,3,'#','F','0','monitor:operlog:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1042,'ç»å½æ¥è¯¢',501,1,'#','F','0','monitor:logininfor:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1043,'ç»å½å é¤',501,2,'#','F','0','monitor:logininfor:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1044,'æ¥å¿å¯¼åº',501,2,'#','F','0','monitor:logininfor:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1045,'å¨çº¿æ¥è¯¢',109,1,'#','F','0','monitor:online:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1046,'æ¹éå¼ºé',109,2,'#','F','0','monitor:online:batchForceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1047,'åæ¡å¼ºé',109,3,'#','F','0','monitor:online:forceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1048,'ä»»å¡æ¥è¯¢',110,1,'#','F','0','monitor:job:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1049,'ä»»å¡æ°å¢',110,2,'#','F','0','monitor:job:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1050,'ä»»å¡ä¿®æ¹',110,3,'#','F','0','monitor:job:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1051,'ä»»å¡å é¤',110,4,'#','F','0','monitor:job:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1052,'ç¶æä¿®æ¹',110,5,'#','F','0','monitor:job:changeStatus','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1053,'ä»»å¡å¯¼åº',110,5,'#','F','0','monitor:job:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1054,'çææ¥è¯¢',113,1,'#','F','0','tool:gen:list','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1055,'çæä»£ç ',113,2,'#','F','0','tool:gen:code','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00',''),(1065,'test',100,7,'#','F','0','system:user:test','#','mylusing','2018-11-18 18:16:53','',NULL,''),(1094,'åæ°ç®¡ç',1,7,'system/config','C','0','system:config:view','#','admin','2019-12-02 15:35:13','admin','2019-12-02 15:41:24','');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'éç¥ID',
  `notice_title` varchar(50) NOT NULL COMMENT 'å¬åæ é¢',
  `notice_type` char(2) NOT NULL COMMENT 'å¬åç±»å(1:éç¥,2:å¬å)',
  `notice_content` varchar(500) NOT NULL COMMENT 'å¬ååå®¹',
  `status` char(1) DEFAULT '0' COMMENT 'å¬åç¶æ(0:æ­£å¸¸,1:å³é­)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='éç¥å¬åè¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'æ¸©é¦¨æéï¼2019-07-01 æ°çæ¬åå¸','2','æ°çæ¬åå®¹<div><table class=\"table table-bordered\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>','0','admin','2018-03-16 11:33:00','admin','2019-12-03 10:49:24','ç®¡çå'),(2,'ç»´æ¤éç¥ï¼2018-07-01 ç³»ç»åæ¨ç»´æ¤','1','ç»´æ¤åå®¹','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','ç®¡çå');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'æ¥å¿ID',
  `model` varchar(50) DEFAULT '' COMMENT 'æ¨¡åæ é¢',
  `business_type` int(2) DEFAULT '0' COMMENT 'ä¸å¡ç±»å(0:å¶å®,1:æ°å¢,2:ä¿®æ¹ï¼3:å é¤)',
  `method` varchar(100) DEFAULT '' COMMENT 'æ¹æ³åç§°',
  `operator_type` int(1) DEFAULT '0' COMMENT 'æä½ç±»å(0:å¶å®,1:åå°ç¨æ·,2:ææºç«¯ç¨æ·)',
  `oper_name` varchar(50) DEFAULT '' COMMENT 'æä½äººå',
  `dept_name` varchar(50) DEFAULT '' COMMENT 'é¨é¨åç§°',
  `oper_url` varchar(255) DEFAULT '' COMMENT 'è¯·æ±URL',
  `oper_ip` varchar(30) DEFAULT '' COMMENT 'è¯·æ±ä¸»æºå°å',
  `oper_location` varchar(255) DEFAULT '' COMMENT 'æä½å°ç¹',
  `oper_param` varchar(255) DEFAULT '' COMMENT 'è¯·æ±åæ°',
  `status` int(1) DEFAULT '0' COMMENT 'æä½ç¶æ(0:æ­£å¸¸,1:å¼å¸¸)',
  `error_msg` varchar(2000) DEFAULT '' COMMENT 'éè¯¯ä¿¡æ¯',
  `oper_time` datetime DEFAULT NULL COMMENT 'æä½æ¶é´',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8 COMMENT='æä½æ¥å¿è®°å½è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (1,'æ¸ç©ºæ¥å¿',9,'com.facedamon.smart.web.controller.monitor.OperLogController.clean()',1,'admin','ç åé¨é¨','/monitor/operlog/clean','127.0.0.1',NULL,'{}',0,NULL,'2019-05-26 16:01:19'),(2,'æ¸ç©ºç»éæ¥å¿',9,'com.facedamon.smart.web.controller.monitor.LoginInfoController.clean()',1,'admin','ç åé¨é¨','/monitor/logininfor/clean','127.0.0.1',NULL,'{}',0,NULL,'2019-05-26 16:02:02'),(3,'æ°å¢å­å¸ç±»å',1,'com.facedamon.smart.web.controller.system.DictTypeController.add()',1,'admin','ç åé¨é¨','/system/dict/add','127.0.0.1',NULL,'{\"dictName\":[\"ä¸å¡ç±»å\"],\"dictType\":[\"busi_type\"],\"status\":[\"0\"],\"remark\":[\"ä¸å¡ç±»å\"]}',0,NULL,'2019-05-26 16:04:21'),(4,'æ°å¢å­å¸æ°æ®',1,'com.facedamon.smart.web.controller.system.DictDataController.add()',1,'admin','ç åé¨é¨','/system/dict/data/add','127.0.0.1',NULL,'{\"dictLabel\":[\"Python\"],\"dictValue\":[\"1\"],\"dictType\":[\"busi_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"info\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-05-26 16:05:15'),(5,'ç¨æ·æ¨¡å',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','ç åé¨é¨','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"4\"],\"deptId\":[\"103\"],\"userName\":[\"tanhong\"],\"email\":[\"9803433@qq.com\"],\"phonenumber\":[\"13100000000\"],\"sex\":[\"1\"],\"status\":[\"0\"],\"roleIds\":[\"5\"],\"postIds\":[\"4\"]}',0,NULL,'2019-05-26 16:05:46'),(6,'å¯¼åºå­å¸æ°æ®',5,'com.facedamon.smart.web.controller.system.DictDataController.export()',1,'admin','ç åé¨é¨','/system/dict/data/export','127.0.0.1',NULL,'{\"dictType\":[\"busi_type\"],\"dictLabel\":[\"\"],\"status\":[\"\"]}',0,NULL,'2019-05-26 16:06:16'),(7,'æ´æ°å­å¸æ°æ®',2,'com.facedamon.smart.web.controller.system.DictDataController.edit()',1,'admin','ç åé¨é¨','/system/dict/data/edit','127.0.0.1',NULL,'{\"dictCode\":[\"27\"],\"dictLabel\":[\"Python\"],\"dictValue\":[\"1\"],\"dictType\":[\"busi_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"default\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-05-26 16:06:56'),(8,'å¸ç§åæ°',1,'com.facedamon.smart.web.controller.param.CurrencyController.addSave()',1,'admin','ç åé¨é¨','/param/currency/add','127.0.0.1',NULL,'{\"currencyName\":[\"äººæ°å¸\"],\"currencySign\":[\"CNY\"],\"status\":[\"0\"],\"currencyFlag\":[\"on\"],\"remark\":[\"\"]}',1,'\n### Error updating database.  Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column \'currency_flag\' at row 1\n### The error may involve com.facedamon.smart.param.mapper.CurrencyMapper.insertCurrency-Inline\n### The error occurred while setting parameters\n### SQL: insert into smart_param_currency          ( currency_name,                          currency_sign,                          status,                          currency_flag )           values ( ?,                          ?,                          ?,                          ? )\n### Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column \'currency_flag\' at row 1\n; ]; Data truncation: Data too long for column \'currency_flag\' at row 1; nested exception is com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column \'currency_flag\' at row 1','2019-05-26 16:10:50'),(9,'ä¿®æ¹æ°æ®æé',2,'com.facedamon.smart.web.controller.system.RoleController.rule()',1,'admin','ç åé¨é¨','/system/role/rule','127.0.0.1',NULL,'{\"roleId\":[\"4\"],\"roleName\":[\"æ¯è¡å¤´å¯¸ç®¡çå\"],\"roleKey\":[\"zhihang\"],\"dataScope\":[\"2\"],\"deptIds\":[\"100,101,103\"]}',0,NULL,'2019-05-26 17:56:51'),(10,'ç¨æ·æ¨¡å',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','ç åé¨é¨','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"3\"],\"deptId\":[\"107\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@qq.com\"],\"phonenumber\":[\"17600603969\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"4\"],\"postIds\":[\"4\"]}',0,NULL,'2019-05-26 17:57:09'),(11,'å¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.forceLogout()',1,'admin','ç åé¨é¨','/monitor/online/forceLogout','127.0.0.1',NULL,'{\"sessionId\":[\"f4ce6581-3515-48d5-a643-03a038902fe9\"]}',0,NULL,'2019-07-10 16:59:41'),(12,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1062','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:48:40'),(13,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1066','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:51:40'),(14,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1067','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:51:45'),(15,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1066','127.0.0.1',NULL,'{}',0,NULL,'2019-09-09 14:51:49'),(16,'æ¹éä»£ç çæ',8,'com.facedamon.smart.web.controller.tool.GeneratorController.genCode()',1,'admin','ç åé¨é¨','/tool/gen/batchGenCode','127.0.0.1',NULL,'{\"tableNames\":[\"Model_User_Info,Model_Table_Info,Model_Field_Table_Quote,Model_Field_Code_Value_Info,Model_Base_Word_Info,Model_Base_Word_Field_Quote,Model_Field_Info\"]}',0,NULL,'2019-09-09 15:36:50'),(17,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"æ¨¡åç®¡ç\"],\"url\":[\"\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-database\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-09 17:09:17'),(18,'æ´æ°èå',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','ç åé¨é¨','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"3\"],\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"ç³»ç»å·¥å·\"],\"url\":[\"#\"],\"perms\":[\"\"],\"orderNum\":[\"3\"],\"icon\":[\"fa fa-navicon\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-09 17:09:58'),(19,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"åºè¯ç®¡ç\"],\"url\":[\"model/baseWordInfo\"],\"perms\":[\"model:baseWordInfo\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-09 17:13:19'),(20,'æ´æ°èå',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','ç åé¨é¨','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1069\"],\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"åºè¯ç®¡ç\"],\"url\":[\"model/baseWordInfo\"],\"perms\":[\"model:baseWordInfo:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-09 17:13:46'),(21,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"åºè¯æ¥è¯¢\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:10:24'),(22,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"åºè¯æ°å¢\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:10:54'),(23,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"åºè¯ç¼è¾\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:11:25'),(24,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1069\"],\"menuType\":[\"F\"],\"menuName\":[\"åºè¯å é¤\"],\"url\":[\"\"],\"perms\":[\"model:baseWordInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 11:11:44'),(25,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1065','127.0.0.1',NULL,'{}',0,NULL,'2019-09-10 11:11:56'),(26,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"å­æ®µç®¡ç\"],\"url\":[\"model/fieldInfo\"],\"perms\":[\"model:fieldInfo:view\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:02:03'),(27,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"å­æ®µæ¥è¯¢\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:02:52'),(28,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"å­æ®µæ°å¢\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:04:40'),(29,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"å­æ®µç¼è¾\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:05:09'),(30,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1074\"],\"menuType\":[\"F\"],\"menuName\":[\"å­æ®µå é¤\"],\"url\":[\"\"],\"perms\":[\"model:fieldInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:05:41'),(31,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"æ¨¡åè¡¨ç®¡ç\"],\"url\":[\"model/tableInfo\"],\"perms\":[\"model:tableInfo:view\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 14:59:56'),(32,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åè¡¨æ¥è¯¢\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:00:56'),(33,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åè¡¨æ°å¢\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:01:22'),(34,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åè¡¨ç¼è¾\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:01:49'),(35,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1079\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åè¡¨å é¤\"],\"url\":[\"\"],\"perms\":[\"model:tableInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:03:05'),(36,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"æ¨¡åç¨æ·\"],\"url\":[\"model/userInfo\"],\"perms\":[\"model:userInfo:view\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:14:43'),(37,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åç¨æ·æ¥è¯¢\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:15:50'),(38,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åç¨æ·æ°å¢\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:16:28'),(39,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åç¨æ·ç¼è¾\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:16:52'),(40,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1084\"],\"menuType\":[\"F\"],\"menuName\":[\"æ¨¡åç¨æ·å é¤\"],\"url\":[\"\"],\"perms\":[\"model:userInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:17:20'),(41,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"ç å¼è¡¨\"],\"url\":[\"model/fieldCodeValueInfo\"],\"perms\":[\"model:fieldCodeValueInfo\"],\"orderNum\":[\"5\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:30:40'),(42,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"ç å¼æ¥è¯¢\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:view\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:31:09'),(43,'æ´æ°èå',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin',NULL,'/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1089\"],\"parentId\":[\"1068\"],\"menuType\":[\"C\"],\"menuName\":[\"ç å¼è¡¨\"],\"url\":[\"model/fieldCodeValueInfo\"],\"perms\":[\"model:fieldCodeValueInfo:view\"],\"orderNum\":[\"5\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-10 15:31:22'),(44,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"ç å¼æ°å¢\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:add\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:32:06'),(45,'æ´æ°èå',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin',NULL,'/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1090\"],\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"ç å¼æ¥è¯¢\"],\"url\":[\"#\"],\"perms\":[\"model:fieldCodeValueInfo:list\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-09-10 15:32:24'),(46,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"ç å¼ç¼è¾\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:edit\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:32:50'),(47,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin',NULL,'/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1089\"],\"menuType\":[\"F\"],\"menuName\":[\"ç å¼å é¤\"],\"url\":[\"\"],\"perms\":[\"model:fieldCodeValueInfo:remove\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-09-10 15:33:34'),(48,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 10:35:33'),(49,'å é¤ç¨æ·',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','ç åé¨é¨','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"3\"]}',0,NULL,'2019-12-02 10:35:51'),(50,'å é¤ç¨æ·',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','ç åé¨é¨','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 10:35:54'),(51,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 10:36:01'),(52,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 10:36:04'),(53,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 10:36:07'),(54,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 10:36:10'),(55,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1089','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:44:37'),(56,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1090','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:44:46'),(57,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1070','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:01'),(58,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1071','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:11'),(59,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1072','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:19'),(60,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1073','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:26'),(61,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1069','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:33'),(62,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1075','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:40'),(63,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1076','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:48'),(64,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1077','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:47:58'),(65,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1078','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:03'),(66,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1074','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:06'),(67,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1080','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:10'),(68,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1081','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:16'),(69,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1082','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:20'),(70,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1083','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:24'),(71,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1079','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:28'),(72,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1085','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:34'),(73,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1086','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:41'),(74,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1087','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:46'),(75,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1088','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:53'),(76,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1084','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:48:57'),(77,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1091','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:05'),(78,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1092','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:12'),(79,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1093','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:18'),(80,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1089','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:23'),(81,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1068','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:26'),(82,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1029','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 10:49:53'),(83,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 11:44:02'),(84,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"3\"]}',0,NULL,'2019-12-02 14:35:40'),(85,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:35:43'),(86,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 14:35:47'),(87,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 14:35:50'),(88,'å é¤ç¨æ·',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','ç åé¨é¨','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"4,3\"]}',0,NULL,'2019-12-02 14:36:01'),(89,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:36:06'),(90,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-02 14:36:10'),(91,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 14:36:14'),(92,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:36:25'),(93,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:37:07'),(94,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-02 14:39:39'),(95,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1029','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:39:56'),(96,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1029','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:11'),(97,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1030','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:17'),(98,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1031','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:21'),(99,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1032','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:28'),(100,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/1033','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:43:36'),(101,'ç¨æ·æ¨¡å',1,'com.facedamon.smart.web.controller.system.UserController.add()',1,'admin','ç åé¨é¨','/system/user/add','127.0.0.1',NULL,'{\"deptId\":[\"104\"],\"loginName\":[\"damon\"],\"userName\":[\"facedamon\"],\"password\":[\"damon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:45:39'),(102,'æ°å¢è§è²',1,'com.facedamon.smart.web.controller.system.RoleController.add()',1,'admin','ç åé¨é¨','/system/role/add','127.0.0.1',NULL,'{\"roleName\":[\"damon\"],\"roleKey\":[\"Damon\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"\"],\"menuIds\":[\"1,106\"]}',0,NULL,'2019-12-02 14:46:39'),(103,'ç¨æ·æ¨¡å',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','ç åé¨é¨','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"5\"],\"deptId\":[\"104\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"6\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:47:19'),(104,'ç¨æ·æ¨¡å',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','ç åé¨é¨','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"5\"],\"deptId\":[\"104\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:47:28'),(105,'ç¨æ·æ¨¡å',2,'com.facedamon.smart.web.controller.system.UserController.edit()',1,'admin','ç åé¨é¨','/system/user/edit','127.0.0.1',NULL,'{\"userId\":[\"5\"],\"deptId\":[\"104\"],\"userName\":[\"facedamon\"],\"email\":[\"facedamon@11.com\"],\"phonenumber\":[\"18511111111\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"6\"],\"postIds\":[\"3\"]}',0,NULL,'2019-12-02 14:47:34'),(106,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:47:55'),(107,'å é¤ç¨æ·',3,'com.facedamon.smart.web.controller.system.UserController.remove()',1,'admin','ç åé¨é¨','/system/user/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 14:48:55'),(108,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:52:18'),(109,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin','ç åé¨é¨','/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"6\"]}',0,NULL,'2019-12-02 14:55:54'),(110,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin','ç åé¨é¨','/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 14:55:59'),(111,'æ°å¢è§è²',1,'com.facedamon.smart.web.controller.system.RoleController.add()',1,'admin',NULL,'/system/role/add','127.0.0.1',NULL,'{\"roleName\":[\"damon\"],\"roleKey\":[\"Damon\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"\"],\"menuIds\":[\"1,106\"]}',0,NULL,'2019-12-02 15:11:28'),(112,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin',NULL,'/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 15:11:41'),(113,'å é¤è§è²',3,'com.facedamon.smart.web.controller.system.RoleController.remove()',1,'admin',NULL,'/system/role/remove','127.0.0.1',NULL,'{\"ids\":[\"7\"]}',0,NULL,'2019-12-02 15:11:47'),(114,'å é¤èå',3,'com.facedamon.smart.web.controller.system.MenuController.remove()',1,'admin',NULL,'/system/menu/remove/106','127.0.0.1',NULL,'{}',0,NULL,'2019-12-02 15:11:52'),(115,'å é¤å­å¸ç±»å',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin',NULL,'/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"10\"]}',0,NULL,'2019-12-02 15:12:38'),(116,'æ´æ°å¬å',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','ç åé¨é¨','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"æ¸©é¦¨æéï¼2019-07-01 æ°çæ¬åå¸\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"æ°çæ¬åå®¹<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-02 15:15:42'),(117,'æ´æ°å¬å',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','ç åé¨é¨','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"æ¸©é¦¨æéï¼2019-07-01 æ°çæ¬åå¸\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"æ°çæ¬åå®¹<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-02 15:15:52'),(118,'æ´æ°å¬å',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','ç åé¨é¨','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"æ¸©é¦¨æéï¼2019-07-01 æ°çæ¬åå¸\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"æ°çæ¬åå®¹<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-02 15:16:18'),(119,'å é¤å­å¸ç±»å',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin','ç åé¨é¨','/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"5\"]}',0,NULL,'2019-12-02 15:25:35'),(120,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.MenuController.add()',1,'admin','ç åé¨é¨','/system/menu/add','127.0.0.1',NULL,'{\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"åæ°ç®¡ç\"],\"url\":[\"system.config\"],\"perms\":[\"system:config\"],\"orderNum\":[\"7\"],\"icon\":[\"\"],\"visable\":[\"0\"]}',0,NULL,'2019-12-02 15:35:13'),(121,'æ´æ°èå',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','ç åé¨é¨','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1094\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"åæ°ç®¡ç\"],\"url\":[\"system/config\"],\"perms\":[\"system:config\"],\"orderNum\":[\"7\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-12-02 15:41:09'),(122,'æ´æ°èå',2,'com.facedamon.smart.web.controller.system.MenuController.edit()',1,'admin','ç åé¨é¨','/system/menu/edit','127.0.0.1',NULL,'{\"menuId\":[\"1094\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"åæ°ç®¡ç\"],\"url\":[\"system/config\"],\"perms\":[\"system:config:view\"],\"orderNum\":[\"7\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}',0,NULL,'2019-12-02 15:41:24'),(123,'æ´æ°å¬å',2,'com.facedamon.smart.web.controller.system.NoticeController.edit()',1,'admin','ç åé¨é¨','/system/notice/edit','127.0.0.1',NULL,'{\"noticeId\":[\"1\"],\"noticeTitle\":[\"æ¸©é¦¨æéï¼2019-07-01 æ°çæ¬åå¸\"],\"noticeType\":[\"2\"],\"noticeContent\":[\"æ°çæ¬åå®¹<div><table class=\\\"table table-bordered\\\"><tbody><tr><td>uuid</td><td>name</td></tr><tr><td>start</td><td>Damon</td></tr></tbody></table><br></div>\"],\"sta',0,NULL,'2019-12-03 10:49:24'),(124,'æ¸ç©ºç»éæ¥å¿',9,'com.facedamon.smart.web.controller.monitor.LoginInfoController.clean()',1,'admin','ç åé¨é¨','/monitor/logininfor/clean','127.0.0.1',NULL,'{}',0,NULL,'2019-12-03 10:49:54'),(125,'æ¹éå¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.batchForceLogout()',1,'admin','ç åé¨é¨','/monitor/online/batchForceLogout','127.0.0.1',NULL,'{\"ids[]\":[\"1c07afcd-fc08-4b9c-ae73-2e8d802b32ff\",\"31d5984e-bde3-40d7-b36f-2bc83d82cc15\",\"ad273042-ee3b-4025-a445-0a8edef90ff4\",\"150c7875-f6ca-4c9f-b4a2-aded8d3394c0\",\"befd4133-d122-4620-ab92-37c440fdb32a\",\"083d007e-e958-4562-821b-b43e76d0a6c2\",\"d39dc973-3',0,NULL,'2019-12-03 10:50:45'),(126,'æ¹éå¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.batchForceLogout()',1,'admin','ç åé¨é¨','/monitor/online/batchForceLogout','127.0.0.1',NULL,'{\"ids[]\":[\"31d5984e-bde3-40d7-b36f-2bc83d82cc15\",\"ad273042-ee3b-4025-a445-0a8edef90ff4\",\"150c7875-f6ca-4c9f-b4a2-aded8d3394c0\",\"befd4133-d122-4620-ab92-37c440fdb32a\",\"083d007e-e958-4562-821b-b43e76d0a6c2\",\"d39dc973-3d6d-4242-87be-03198bdfa86a\",\"f56d2460-7',0,NULL,'2019-12-03 10:50:54'),(127,'æ¹éå¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.batchForceLogout()',1,'admin','ç åé¨é¨','/monitor/online/batchForceLogout','127.0.0.1',NULL,'{\"ids[]\":[\"ad273042-ee3b-4025-a445-0a8edef90ff4\",\"150c7875-f6ca-4c9f-b4a2-aded8d3394c0\",\"befd4133-d122-4620-ab92-37c440fdb32a\",\"083d007e-e958-4562-821b-b43e76d0a6c2\",\"d39dc973-3d6d-4242-87be-03198bdfa86a\",\"f56d2460-7848-4eb4-9c86-61e4f36dc947\"]}',0,NULL,'2019-12-03 10:50:59'),(128,'æ¹éå¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.batchForceLogout()',1,'admin','ç åé¨é¨','/monitor/online/batchForceLogout','127.0.0.1',NULL,'{\"ids[]\":[\"150c7875-f6ca-4c9f-b4a2-aded8d3394c0\",\"befd4133-d122-4620-ab92-37c440fdb32a\",\"083d007e-e958-4562-821b-b43e76d0a6c2\",\"d39dc973-3d6d-4242-87be-03198bdfa86a\",\"f56d2460-7848-4eb4-9c86-61e4f36dc947\"]}',0,NULL,'2019-12-03 10:51:06'),(129,'å¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.forceLogout()',1,'admin','ç åé¨é¨','/monitor/online/forceLogout','127.0.0.1',NULL,'{\"sessionId\":[\"f56d2460-7848-4eb4-9c86-61e4f36dc947\"]}',0,NULL,'2019-12-03 10:51:11'),(130,'å¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.forceLogout()',1,'admin','ç åé¨é¨','/monitor/online/forceLogout','127.0.0.1',NULL,'{\"sessionId\":[\"d39dc973-3d6d-4242-87be-03198bdfa86a\"]}',0,NULL,'2019-12-03 10:51:12'),(131,'å¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.forceLogout()',1,'admin','ç åé¨é¨','/monitor/online/forceLogout','127.0.0.1',NULL,'{\"sessionId\":[\"083d007e-e958-4562-821b-b43e76d0a6c2\"]}',0,NULL,'2019-12-03 10:51:14'),(132,'å¼ºéç¨æ·',7,'com.facedamon.smart.web.controller.monitor.UserOnlineController.forceLogout()',1,'admin','ç åé¨é¨','/monitor/online/forceLogout','127.0.0.1',NULL,'{\"sessionId\":[\"befd4133-d122-4620-ab92-37c440fdb32a\"]}',0,NULL,'2019-12-03 10:51:16'),(133,'å é¤å­å¸ç±»å',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin','ç åé¨é¨','/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"10\"]}',0,NULL,'2019-12-03 14:05:24'),(134,'å é¤å­å¸ç±»å',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin','ç åé¨é¨','/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"10\"]}',0,NULL,'2019-12-03 14:06:01'),(135,'å é¤å­å¸æ°æ®',3,'com.facedamon.smart.web.controller.system.DictDataController.remove()',1,'admin','ç åé¨é¨','/system/dict/data/remove','127.0.0.1',NULL,'{\"ids\":[\"27\"]}',0,NULL,'2019-12-03 14:07:10'),(136,'å é¤å­å¸ç±»å',3,'com.facedamon.smart.web.controller.system.DictTypeController.remove()',1,'admin','ç åé¨é¨','/system/dict/remove','127.0.0.1',NULL,'{\"ids\":[\"10\"]}',0,NULL,'2019-12-03 14:07:16'),(137,'æ°å¢å­å¸ç±»å',1,'com.facedamon.smart.web.controller.system.DictTypeController.add()',1,'admin','ç åé¨é¨','/system/dict/add','127.0.0.1',NULL,'{\"dictName\":[\"ç¨æ·ç±»å\"],\"dictType\":[\"sys_user_type\"],\"status\":[\"0\"],\"remark\":[\"ç¨æ·ç±»å\"]}',0,NULL,'2019-12-03 14:16:41'),(138,'æ°å¢å­å¸æ°æ®',1,'com.facedamon.smart.web.controller.system.DictDataController.add()',1,'admin','ç åé¨é¨','/system/dict/data/add','127.0.0.1',NULL,'{\"dictLabel\":[\"ç³»ç»ç¨æ·\"],\"dictValue\":[\"00\"],\"dictType\":[\"sys_user_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"primary\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-12-03 14:17:31'),(139,'æ°å¢å­å¸æ°æ®',1,'com.facedamon.smart.web.controller.system.DictDataController.add()',1,'admin','ç åé¨é¨','/system/dict/data/add','127.0.0.1',NULL,'{\"dictLabel\":[\"ä¸å¡ç¨æ·\"],\"dictValue\":[\"01\"],\"dictType\":[\"sys_user_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"primary\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-12-03 14:18:00'),(140,'æ°å¢åæ°',1,'com.facedamon.smart.web.controller.system.ConfigController.add()',1,'admin','ç åé¨é¨','/system/config/add','127.0.0.1',NULL,'{\"configName\":[\"æµè¯\"],\"configKey\":[\"test\"],\"configValue\":[\"1\"],\"configType\":[\"Y\"]}',0,NULL,'2019-12-03 14:51:06'),(141,'å é¤åæ°',3,'com.facedamon.smart.web.controller.system.ConfigController.remove()',1,'admin','ç åé¨é¨','/system/config/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-03 14:51:14'),(142,'å é¤å²ä½',3,'com.facedamon.smart.web.controller.system.PostController.remove()',1,'admin','ç åé¨é¨','/system/post/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-03 15:03:23'),(143,'å é¤å²ä½',3,'com.facedamon.smart.web.controller.system.PostController.remove()',1,'admin','ç åé¨é¨','/system/post/remove','127.0.0.1',NULL,'{\"ids\":[\"3\"]}',0,NULL,'2019-12-03 15:03:32'),(144,'å é¤å²ä½',3,'com.facedamon.smart.web.controller.system.PostController.remove()',1,'admin','ç åé¨é¨','/system/post/remove','127.0.0.1',NULL,'{\"ids\":[\"4\"]}',0,NULL,'2019-12-03 15:03:34'),(145,'å é¤å²ä½',3,'com.facedamon.smart.web.controller.system.PostController.remove()',1,'admin','ç åé¨é¨','/system/post/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-03 15:03:37'),(146,'å é¤å²ä½',3,'com.facedamon.smart.web.controller.system.PostController.remove()',1,'admin','ç åé¨é¨','/system/post/remove','127.0.0.1',NULL,'{\"ids\":[\"2\"]}',0,NULL,'2019-12-03 15:05:38'),(147,'æ´æ°å²ä½',2,'com.facedamon.smart.web.controller.system.PostController.edit()',1,'admin','ç åé¨é¨','/system/post/edit','127.0.0.1',NULL,'{\"postId\":[\"1\"],\"postName\":[\"CEO\"],\"postCode\":[\"æ»è£\"],\"postSort\":[\"1\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-12-03 15:06:09'),(148,'æ´æ°å²ä½',2,'com.facedamon.smart.web.controller.system.PostController.edit()',1,'admin','ç åé¨é¨','/system/post/edit','127.0.0.1',NULL,'{\"postId\":[\"1\"],\"postName\":[\"æ»è£\"],\"postCode\":[\"CEO\"],\"postSort\":[\"1\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-12-03 15:06:24'),(149,'æ°å¢å²ä½',1,'com.facedamon.smart.web.controller.system.PostController.add()',1,'admin','ç åé¨é¨','/system/post/add','127.0.0.1',NULL,'{\"postName\":[\"æç»\"],\"postCode\":[\"Teacher\"],\"postSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"\"]}',0,NULL,'2019-12-03 15:07:15'),(153,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 15:10:51'),(154,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"102\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"éåºåå¬å¸\"],\"orderNum\":[\"2\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 15:11:43'),(155,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 15:29:23'),(156,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"103\"],\"parentId\":[\"101\"],\"parentName\":[\"æé½æ»å¬å¸\"],\"deptName\":[\"ç åé¨é¨\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:09:56'),(158,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:11:28'),(159,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 16:24:26'),(160,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:24:34'),(161,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:24:45'),(162,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 16:24:50'),(163,'å é¤é¨é¨',3,'com.facedamon.smart.web.controller.system.DeptController.remove()',1,'admin','ç åé¨é¨','/system/dept/remove/110','127.0.0.1',NULL,'{}',0,NULL,'2019-12-03 16:27:15'),(164,'æ°å¢é¨é¨',1,'com.facedamon.smart.web.controller.system.DeptController.add()',1,'admin','ç åé¨é¨','/system/dept/add','127.0.0.1',NULL,'{\"parentId\":[\"103\"],\"deptName\":[\"test\"],\"orderNum\":[\"1\"],\"leader\":[\"1\"],\"phone\":[\"18588888888\"],\"email\":[\"facedamo@163.con\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:32:53'),(165,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:43:04'),(166,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 16:43:26'),(167,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 16:45:07'),(168,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 16:45:13'),(169,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 17:45:30'),(170,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"æé½æ»å¬å¸\"],\"orderNum\":[\"1\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 17:46:10'),(171,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"102\"],\"parentId\":[\"100\"],\"parentName\":[\"Smart\"],\"deptName\":[\"éåºåå¬å¸\"],\"orderNum\":[\"2\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"0\"]}',0,NULL,'2019-12-03 17:46:33'),(172,'ä¿®æ¹é¨é¨',2,'com.facedamon.smart.web.controller.system.DeptController.edit()',1,'admin','ç åé¨é¨','/system/dept/edit','127.0.0.1',NULL,'{\"deptId\":[\"100\"],\"parentId\":[\"0\"],\"parentName\":[\"æ ç¶èç¹\"],\"deptName\":[\"Smart\"],\"orderNum\":[\"0\"],\"leader\":[\"facedamon\"],\"phone\":[\"15888888888\"],\"email\":[\"facedamon@163.com\"],\"status\":[\"1\"]}',0,NULL,'2019-12-03 17:47:56');
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'å²ä½ID',
  `post_code` varchar(64) NOT NULL COMMENT 'å²ä½ç¼ç ',
  `post_name` varchar(50) NOT NULL COMMENT 'å²ä½åç§°',
  `post_sort` int(4) NOT NULL COMMENT 'æ¾ç¤ºé¡ºåº',
  `status` char(1) NOT NULL COMMENT 'å²ä½ç¶æ(0:æ­£å¸¸,1:åç¨)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='å²ä½ä¿¡æ¯è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'CEO','æ»è£',1,'0','admin','2018-03-16 11:33:00','admin','2019-12-03 15:06:24',''),(5,'Teacher','æç»',2,'0','admin','2019-12-03 15:07:15','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'å²ä½ID',
  `role_name` varchar(30) NOT NULL COMMENT 'è§è²åç§°',
  `role_key` varchar(100) NOT NULL COMMENT 'è§è²æéå­ç¬¦',
  `role_sort` int(4) NOT NULL COMMENT 'æ¾ç¤ºé¡ºåº',
  `data_scope` char(1) DEFAULT '1' COMMENT 'æ°æ®æé(1:å¨é¨æ°æ®æé,2:èªå®ä¹æ°æ®æé)',
  `status` char(1) NOT NULL COMMENT 'è§è²ç¶æ(0:æ­£å¸¸,1:åç¨)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='è§è²ä¿¡æ¯è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ç®¡çå','admin',1,'1','0','admin','2018-03-16 11:33:00','admin','2018-11-19 20:04:12','ç®¡çå');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_dept` (
  `role_id` int(11) NOT NULL COMMENT 'è§è²ID',
  `dept_id` int(11) NOT NULL COMMENT 'é¨é¨ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='è§è²é¨é¨å³èè¡¨';
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
  `role_id` int(11) NOT NULL COMMENT 'è§è²ID',
  `menu_id` int(11) NOT NULL COMMENT 'èåID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='è§è²èåå³èè¡¨';
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
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ç¨æ·ID',
  `dept_id` int(11) DEFAULT NULL COMMENT 'é¨é¨ID',
  `login_name` varchar(30) NOT NULL COMMENT 'ç»éè´¦å·',
  `user_name` varchar(30) NOT NULL COMMENT 'ç¨æ·æµç§°',
  `user_type` varchar(2) DEFAULT '00' COMMENT 'ç¨æ·ç±»å(00:ç³»ç»ç¨æ·)',
  `email` varchar(50) DEFAULT '' COMMENT 'é®ç®±',
  `phonenumber` varchar(11) DEFAULT '' COMMENT 'èç³»çµè¯',
  `sex` char(1) DEFAULT '0' COMMENT 'æ§å«(0:mail,1:femail,2:noone)',
  `avatar` varchar(100) DEFAULT '' COMMENT 'å¾åè·¯å¾',
  `password` varchar(50) DEFAULT '' COMMENT 'å¯ç ',
  `salt` varchar(20) DEFAULT '' COMMENT 'çå å¯',
  `status` char(1) DEFAULT '0' COMMENT 'è´¦å·ç¶æ(0:æ­£å¸¸ï¼1:åç¨)',
  `login_ip` varchar(20) DEFAULT '' COMMENT 'æåç»å½IP',
  `login_date` datetime DEFAULT NULL COMMENT 'æåç»å½æ¶é´',
  `create_by` varchar(64) DEFAULT '' COMMENT 'åå»ºè',
  `create_time` datetime DEFAULT NULL COMMENT 'åå»ºæ¶é´',
  `update_by` varchar(64) DEFAULT '' COMMENT 'æ´æ°è',
  `update_time` datetime DEFAULT NULL COMMENT 'æ´æ°æ¶é´',
  `remark` varchar(500) DEFAULT '' COMMENT 'å¤æ³¨',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='ç¨æ·ä¿¡æ¯è¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','admin','00','facedamon@163.com','15888888888','0','','8d8bc547e87209063b530e2b0f15f4fd','2741e9','0','127.0.0.1','2019-12-03 17:45:21','admin','2018-03-16 11:33:00','ry','2019-12-03 17:45:21','ç®¡çå');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_online`
--

DROP TABLE IF EXISTS `sys_user_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT 'ç¨æ·ä¼è¯ID',
  `login_name` varchar(50) DEFAULT '' COMMENT 'ç»å½è´¦æ·',
  `dept_name` varchar(50) DEFAULT '' COMMENT 'é¨é¨åç§°',
  `ipaddr` varchar(50) DEFAULT '' COMMENT 'IPå°å',
  `login_location` varchar(255) DEFAULT '' COMMENT 'ç»å½å°ç¹',
  `browser` varchar(50) DEFAULT '' COMMENT 'æµè§å¨ç±»å',
  `os` varchar(50) DEFAULT '' COMMENT 'æä½ç³»ç»ç±»å',
  `status` varchar(10) DEFAULT '0' COMMENT 'å¨çº¿ç¶æ(on_line,off_line)',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'sessionåå»ºæ¶é´',
  `last_access_time` datetime DEFAULT NULL COMMENT 'seesionæåè®¿é®æ¶é´',
  `expire_time` int(5) DEFAULT '5' COMMENT 'è¶æ¶æ¶é¿/min',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='å¨çº¿ç¨æ·è®°å½';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_online`
--

LOCK TABLES `sys_user_online` WRITE;
/*!40000 ALTER TABLE `sys_user_online` DISABLE KEYS */;
INSERT INTO `sys_user_online` VALUES ('076de7d9-7e46-49c9-ad4e-7f3700797a86','admin','ç åé¨é¨','127.0.0.1',NULL,'Safari','Mac OS X','ON_LINE','2019-12-03 17:45:18','2019-12-03 17:47:31',1800000);
/*!40000 ALTER TABLE `sys_user_online` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_post` (
  `user_id` int(11) NOT NULL COMMENT 'ç¨æ·ID',
  `post_id` int(11) NOT NULL COMMENT 'å²ä½ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ç¨æ·é¨é¨å³èè¡¨';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT 'ç¨æ·ID',
  `role_id` int(11) NOT NULL COMMENT 'è§è²ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ç¨æ·è§è²å³èè¡¨';
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

-- Dump completed on 2019-12-03 17:49:42
