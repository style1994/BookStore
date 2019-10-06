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
INSERT INTO `book` VALUES ('B00001','Servlet&JSP技術手冊','1234567890123','資訊','大山出版社','本',999,1,NULL),('B00002','Java王者歸來','1234567890123','資訊','深石數位科技','本',699,1,NULL),('B00003','C#從入門到精通','1234567890123','資訊','臺北市 : 文魁資訊出版','本',880,1,NULL),('B00004','新Java 2 : 550個應用技巧大全集','1234567890123','圖書','臺北縣 : 博碩文化','本',599,10,NULL),('B00005','SCJP Java 5.0專業認證手冊 ','1234567890123','圖書','臺北市 : 碁峰資訊','本',880,6,NULL),('B00006','JavaScript實用範例辭典 ','1234567890123','圖書','臺北市 : 文魁資訊出版','本',999,30,NULL),('B00007','Dreamweaver 8與JSP實戰演繹','1234567890123','圖書','臺北市 : 文魁資訊出版','本',520,10,NULL);
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
INSERT INTO `membership` VALUES ('M00001','胖虎','0921579741','1994-10-01','716-32 屏東縣枋山鄉新北一街五段779巷689弄403號35樓 ',NULL),('M00002','小夫','0980800194','1993-10-01','048-29 雲林縣元長鄉立華街585巷235號 ',NULL),('M00003','大熊','0988694298','1994-10-01','613-02 屏東縣潮州鎮石園街二段418巷220弄917號47樓 ',NULL),('M00004','靜香','0948253647','1994-01-10','492 臺東縣長濱鄉秀福路606巷509號75樓 ',NULL),('M00005','張大山','0932914342','1999-10-01','569-05 澎湖縣湖西鄉大學二十三路388號',NULL),('M00006','王大明','0908838322','1946-10-01','188 新竹市東區香賓街得月巷街七段632號80樓','abc@gmail.com'),('M00007','王大川','0941182979','1946-01-01','603 新竹縣五峰鄉六路七路一段18巷365號',NULL),('M00008','王建民','0929088806','1946-01-10','863-78 屏東縣琉球鄉大通一街82巷602號',NULL),('M00009','王大賢','0927552813','1994-10-01','965 新竹市東區河南東四路六段954號42樓','abc@gmail.com');
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

-- Dump completed on 2019-10-06 15:01:05
