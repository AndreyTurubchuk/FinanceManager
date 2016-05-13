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
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `idTransaction` int(11) NOT NULL AUTO_INCREMENT,
  `descriptions` varchar(45) NOT NULL COMMENT 'Текстовое описание транзакции',
  `summ` int(32) NOT NULL COMMENT 'Сумма проведения операции',
  `data` date NOT NULL COMMENT 'Дата проведения операции',
  `typeOfOperation` int(2) NOT NULL COMMENT 'Тип операции. 1 - пополнение денежных средств, 0 - снятие денежных средств',
  `idAccount` int(11) NOT NULL COMMENT 'Номер счета, к которому привязана транзакция. Берется из сравочника "Счета" (Accounts)',
  PRIMARY KEY (`idTransaction`),
  UNIQUE KEY `idTransaction_UNIQUE` (`idTransaction`),
  KEY `fk_transactions_idx` (`idAccount`),
  CONSTRAINT `fk_transactions` FOREIGN KEY (`idAccount`) REFERENCES `accounts` (`idaccounts`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='Транзакции (Список операций по счету)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (4,'Открытие вклада',1000,'2014-01-20',1,22),(8,'Открытие вклада',500,'2014-01-20',1,23),(9,'Открытие вклада',3000,'2015-03-23',1,26),(10,'Открытие вклада',100,'2013-07-15',1,29),(13,'Пополнение вклада',200,'2014-03-20',1,25),(14,'Пополнение вклада',100,'2014-04-15',1,22),(15,'Списание вклада',500,'2015-01-20',0,23),(16,'Списание вклада',200,'2015-09-23',0,26),(17,'Списание',300,'2015-09-23',1,26),(18,'Списание',300,'2015-09-23',1,26),(19,'Списание',300,'2015-09-23',1,26),(93,'мороженое',500,'2016-03-12',1,95),(94,'аванс',500,'2016-03-13',1,96),(95,'акции',300,'2016-03-14',1,96),(98,'сок',200,'2016-03-19',1,95),(99,'хлеб',300,'2016-03-19',1,95),(100,'молоко',400,'2016-03-19',0,95),(101,'зарплата',500,'2016-03-19',1,96);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-13 17:43:31
