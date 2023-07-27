-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: xdb
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `x_menu`
--

DROP TABLE IF EXISTS `x_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `x_menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `component` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `redirect` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `is_leaf` varchar(1) DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `x_menu`
--

LOCK TABLES `x_menu` WRITE;
/*!40000 ALTER TABLE `x_menu` DISABLE KEYS */;
INSERT INTO `x_menu` VALUES (1,'Layout','/sys','/sys/user','sysManage','系统管理','userManage',0,'N',0),(2,'sys/user','user',NULL,'userList','用户列表','user',1,'Y',0),(3,'sys/role','role',NULL,'roleList','角色列表','roleManage',1,'Y',0),(4,'Layout','/test','/test/test1','test','功能测试','form',0,'N',0),(5,'test/test1','test1','','test1','测试点一','form',4,'Y',0),(6,'test/test2','test2','','test2','测试点二','form',4,'Y',0),(7,'test/test3','test3','','test3','测试点三','form',4,'Y',0);
/*!40000 ALTER TABLE `x_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `x_role`
--

DROP TABLE IF EXISTS `x_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `x_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `x_role`
--

LOCK TABLES `x_role` WRITE;
/*!40000 ALTER TABLE `x_role` DISABLE KEYS */;
INSERT INTO `x_role` VALUES (1,'admin','超级管理员'),(2,'hr','人事专员'),(3,'normal','普通员工');
/*!40000 ALTER TABLE `x_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `x_role_menu`
--

DROP TABLE IF EXISTS `x_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `x_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `menu_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `x_role_menu`
--

LOCK TABLES `x_role_menu` WRITE;
/*!40000 ALTER TABLE `x_role_menu` DISABLE KEYS */;
INSERT INTO `x_role_menu` VALUES (5,4,2),(6,4,6),(7,4,1),(8,4,4),(14,5,2),(16,5,1),(17,5,4),(25,1,1),(26,1,2),(27,1,3),(28,1,4),(29,1,5),(30,1,6),(31,1,7);
/*!40000 ALTER TABLE `x_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `x_user`
--

DROP TABLE IF EXISTS `x_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `x_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `x_user`
--

LOCK TABLES `x_user` WRITE;
/*!40000 ALTER TABLE `x_user` DISABLE KEYS */;
INSERT INTO `x_user` VALUES (1,'admin','$2a$10$GekOG2Z27V/Hx3/MAErvt.aZOW1EVRb3jx4rh2Hxpf0tIGggS/Oy6','super@aliyun.com','18788887777',1,'http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0),(2,'zhangsan','123456','zhangsann@gmail.com','15842312344',1,'http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0),(3,'lisi','123456','lisi@gmail.com','18844343444',1,'http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0),(4,'wangwu','123456','wangwu@gmail.com','15944644555',1,'http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0),(5,'zhaoliu','123456','zhaoliu@@gmail.com','16412454212',1,'http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',1),(6,'张三','$2a$10$GekOG2Z27V/Hx3/MAErvt.aZOW1EVRb3jx4rh2Hxpf0tIGggS/Oy6','23845452228@qq.com','12345211545',1,NULL,0);
/*!40000 ALTER TABLE `x_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `x_user_role`
--

DROP TABLE IF EXISTS `x_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `x_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `x_user_role`
--

LOCK TABLES `x_user_role` WRITE;
/*!40000 ALTER TABLE `x_user_role` DISABLE KEYS */;
INSERT INTO `x_user_role` VALUES (1,1,1),(2,1,3),(6,0,0);
/*!40000 ALTER TABLE `x_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-27 14:51:49
