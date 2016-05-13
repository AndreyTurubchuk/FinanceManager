-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `idaccounts` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL COMMENT 'Описание счета',
  `balance` int(11) NOT NULL DEFAULT '0',
  `idusers` int(11) NOT NULL,
  `idaccountsdatabase` int(11) NOT NULL,
  PRIMARY KEY (`idaccounts`),
  UNIQUE KEY `idaccounts_UNIQUE` (`idaccounts`),
  UNIQUE KEY `idaccountsdatabase_UNIQUE` (`idaccountsdatabase`),
  KEY `fk_users_idx` (`idusers`),
  KEY `fk_accountsdatabase_idx` (`idaccountsdatabase`),
  CONSTRAINT `fk_accountsdatabase` FOREIGN KEY (`idaccountsdatabase`) REFERENCES `accountsdatabase` (`idaccountsDatabase`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users` FOREIGN KEY (`idusers`) REFERENCES `users` (`idusers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='Счета';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (22,'RUB',0,10,1),(23,'EVRO',0,10,2),(24,'DOLLAR',0,10,3),(25,'RUB',0,11,4),(26,'EVRO',0,11,5),(27,'RUB',0,13,6),(29,'DOLLAR',0,15,7),(30,'RUB',0,11,8),(31,'EVRO',0,15,9),(93,'авто',200,45,47),(95,'uan',8000,45,49),(96,'расходы',1742,45,50),(99,'rub',600,47,53),(107,'уек',400,48,57);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-13 17:43:29
