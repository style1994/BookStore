-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `userId` varchar(25) NOT NULL,
  `passwd` varchar(25) NOT NULL,
  `token` varchar(25) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('root','1234','1'),('user','1234','2');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `b_no` varchar(10) NOT NULL,
  `b_name` varchar(30) NOT NULL,
  `b_isbn` varchar(13) NOT NULL,
  `b_catno` varchar(10) DEFAULT NULL,
  `b_pub` varchar(30) DEFAULT NULL,
  `b_unit` varchar(10) DEFAULT '本',
  `b_price` int(11) NOT NULL,
  `b_qty` int(11) NOT NULL,
  `b_pos` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`b_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('B00001','Servlet&JSP技術手冊','1234567890123','資訊','大山出版社','本',999,1,NULL),('B00002','Java王者歸來','1234567890123','資訊','深石數位科技','本',699,1,NULL),('B00003','C#從入門到精通','1234567890123','資訊','資訊','本',999,1,NULL),('B00004','等一個人咖啡','1234567890123','小說',NULL,'本',999,10,NULL),('B00005','殺手','1234567890123','小說','大山','本',100,6,NULL),('B00006','阿甘正傳','1234567890123','搞笑類','好運','本',999,30,'日本');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_order`
--

DROP TABLE IF EXISTS `book_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_order` (
  `o_no` varchar(10) NOT NULL,
  `o_date` date NOT NULL,
  `o_m_no` varchar(10) NOT NULL,
  PRIMARY KEY (`o_no`),
  KEY `book_order_ibfk_1_idx` (`o_m_no`),
  CONSTRAINT `book_order_ibfk_1` FOREIGN KEY (`o_m_no`) REFERENCES `membership` (`m_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_order`
--

LOCK TABLES `book_order` WRITE;
/*!40000 ALTER TABLE `book_order` DISABLE KEYS */;
INSERT INTO `book_order` VALUES ('od00001','1994-01-20','M00001'),('od00002','2019-09-16','M00002');
/*!40000 ALTER TABLE `book_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `book_order_view`
--

DROP TABLE IF EXISTS `book_order_view`;
/*!50001 DROP VIEW IF EXISTS `book_order_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `book_order_view` AS SELECT 
 1 AS `o_no`,
 1 AS `o_date`,
 1 AS `o_m_no`,
 1 AS `m_name`,
 1 AS `m_tel`,
 1 AS `m_addr`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `book_orderdetail`
--

DROP TABLE IF EXISTS `book_orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_orderdetail` (
  `od_no` varchar(10) NOT NULL,
  `od_b_no` varchar(10) NOT NULL,
  `od_qty` int(11) NOT NULL,
  `od_price` int(11) NOT NULL,
  PRIMARY KEY (`od_no`,`od_b_no`),
  KEY `od_b_no` (`od_b_no`),
  CONSTRAINT `book_orderdetail_ibfk_1` FOREIGN KEY (`od_no`) REFERENCES `book_order` (`o_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_orderdetail_ibfk_2` FOREIGN KEY (`od_b_no`) REFERENCES `book` (`b_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_orderdetail`
--

LOCK TABLES `book_orderdetail` WRITE;
/*!40000 ALTER TABLE `book_orderdetail` DISABLE KEYS */;
INSERT INTO `book_orderdetail` VALUES ('od00001','B00005',1,999),('od00001','B00006',10,99),('od00002','B00001',7,800),('od00002','B00002',20,550);
/*!40000 ALTER TABLE `book_orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `book_orderdt_view`
--

DROP TABLE IF EXISTS `book_orderdt_view`;
/*!50001 DROP VIEW IF EXISTS `book_orderdt_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `book_orderdt_view` AS SELECT 
 1 AS `od_no`,
 1 AS `od_b_no`,
 1 AS `b_name`,
 1 AS `b_isbn`,
 1 AS `b_catno`,
 1 AS `b_unit`,
 1 AS `od_qty`,
 1 AS `od_price`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership` (
  `m_no` varchar(10) NOT NULL,
  `m_name` varchar(25) NOT NULL,
  `m_tel` varchar(15) NOT NULL,
  `m_bir` date DEFAULT NULL,
  `m_addr` varchar(50) DEFAULT NULL,
  `m_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`m_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` VALUES ('M00001','胖虎','7777777777','1994-10-01','日本',NULL),('M00002','小夫','6666666666','1993-10-01','日本',NULL),('M00003','大熊','5555555555','1994-10-01','日本',NULL),('M00004','靜香','0000000000','1994-01-10','日本',NULL),('M00005','張大山','5555555555','1999-10-01','加拿大',NULL),('M00006','王大明','0976666666','1946-10-01','美國','abc@gmail.com'),('M00007','王大川','0958123456','1946-01-01','台灣',NULL),('M00008','王建民','0958777777','1946-01-10','台灣',NULL);
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `book_order_view`
--

/*!50001 DROP VIEW IF EXISTS `book_order_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `book_order_view` AS select `book_order`.`o_no` AS `o_no`,`book_order`.`o_date` AS `o_date`,`book_order`.`o_m_no` AS `o_m_no`,`membership`.`m_name` AS `m_name`,`membership`.`m_tel` AS `m_tel`,`membership`.`m_addr` AS `m_addr` from (`book_order` join `membership` on((`book_order`.`o_m_no` = `membership`.`m_no`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `book_orderdt_view`
--

/*!50001 DROP VIEW IF EXISTS `book_orderdt_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `book_orderdt_view` AS select `od`.`od_no` AS `od_no`,`od`.`od_b_no` AS `od_b_no`,`b`.`b_name` AS `b_name`,`b`.`b_isbn` AS `b_isbn`,`b`.`b_catno` AS `b_catno`,`b`.`b_unit` AS `b_unit`,`od`.`od_qty` AS `od_qty`,`od`.`od_price` AS `od_price` from ((`book_order` `o` join `book_orderdetail` `od`) join `book` `b`) where ((`b`.`b_no` = `od`.`od_b_no`) and (`od`.`od_no` = `o`.`o_no`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-04 16:46:32
