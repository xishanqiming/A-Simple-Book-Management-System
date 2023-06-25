-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: JDBCTest
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `admin_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contact` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'su','SuperAdministrator','879988942'),(1000,'weilinchang','WeilinChang','198789878'),(1001,'xishanqiming','XishanQiming','201043976'),(1002,'gregabbott','GregAbbott','987987987'),(1005,'danielliang','DanielLiang','188922998'),(1006,'smithdell','SmithDell','988546654');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bno` int NOT NULL AUTO_INCREMENT,
  `category` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(50) NOT NULL,
  `press` varchar(30) NOT NULL,
  `year` int NOT NULL,
  `author` varchar(30) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `total` int NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=200229 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (100111,'文学','红楼梦新编','人民文学出版社',2021,'曹雪芹',34.98,15,0),(100112,'文学','西游记新编','人民文学出版社',2022,'吴承恩',34.98,15,15),(100113,'文学','三国演义新编','人民文学出版社',2023,'罗贯中',34.98,15,14),(100967,'计算机','Java程序设计','北京大学出版社',2022,'翁恺',123.90,20,14),(100968,'历史','阿拉伯通史','新世界出版社',2018,'飞利浦西提',48.00,20,12),(100969,'计算机','C#程序设计','哈尔滨工业大学出版社',2020,'王莉',40.90,9,5),(100979,'History','Ancient Greece','CMU',2021,'Turning',20.30,21,21),(100980,'Computer Science','Computer Architecture','THU',2020,'Alexander',90.00,20,20),(100988,'Computer Science','Computer Architecture','PKU',2014,'Greg',72.00,25,20),(200222,'物理学','量子物理讲义','人民教育出版社',2018,'曹雪芹',86.00,5,5),(200223,'物理学','地球物理学','高等教育出版社',2014,'吴承恩',44.50,8,8),(200224,'物理学','数学物理方法','中国科学技术大学出版社',2008,'罗贯中',39.80,25,25),(200228,'物理学','量子物理讲义','人民教育出版社',2018,'曹雪芹',86.00,5,5);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `cno` int NOT NULL,
  `bno` int NOT NULL,
  `borrow_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `admin_id` int NOT NULL,
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`borrow_id`),
  KEY `cno` (`cno`),
  KEY `bno` (`bno`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `card` (`cno`),
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`bno`) REFERENCES `book` (`bno`),
  CONSTRAINT `borrow_ibfk_3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (98796,100967,'2023-04-01','2023-05-10',1000,1),(98800,100969,'2023-04-02','2023-04-20',1002,2),(98801,100980,'2023-04-16','2023-04-28',1000,3),(98803,100967,'2023-03-28',NULL,1000,4),(98802,100111,'2023-05-10',NULL,1000,5),(98800,100113,'2023-05-10',NULL,1000,6);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `cno` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `department` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` char(1) NOT NULL,
  PRIMARY KEY (`cno`),
  CONSTRAINT `card_chk_1` CHECK ((`type` in (_utf8mb4'T',_utf8mb4'G',_utf8mb4'U',_utf8mb4'O')))
) ENGINE=InnoDB AUTO_INCREMENT=98811 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (98796,'张三强','外国语学院','U'),(98797,'李四岭','农业生命环境学院','U'),(98798,'王五峰','计算机学院','T'),(98799,'朱六喜','计算机学院','G'),(98800,'延七宗','经济学院','O'),(98801,'许琳琳','管理学院','G'),(98802,'托尼布莱斯','物理学院','G'),(98803,'李丽','心理学院','T'),(98808,'乔拜登','国际关系学院','O');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 20:33:44
