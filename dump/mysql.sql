CREATE DATABASE  IF NOT EXISTS `bitcoin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bitcoin`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bitcoin
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `marketdepth`
--

DROP TABLE IF EXISTS `marketdepth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marketdepth` (
  `timestamp` datetime NOT NULL,
  `currency` varchar(3) NOT NULL,
  `type` varchar(4) NOT NULL,
  `count` int(11) NOT NULL,
  `price` float NOT NULL,
  `quantity` float NOT NULL,
  PRIMARY KEY (`timestamp`,`currency`,`count`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='https://www.okcoin.com/api/depth.do?ok=1\nhttps://www.okcoin.com/api/depth.do?symbol=ltc_usd&ok=1';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketdepth`
--

LOCK TABLES `marketdepth` WRITE;
/*!40000 ALTER TABLE `marketdepth` DISABLE KEYS */;
/*!40000 ALTER TABLE `marketdepth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticker`
--

DROP TABLE IF EXISTS `ticker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticker` (
  `date` datetime NOT NULL,
  `currency` varchar(3) NOT NULL,
  `timestamp` bigint(21) NOT NULL,
  `buy` float NOT NULL,
  `sell` float NOT NULL,
  `high` float NOT NULL,
  `low` float NOT NULL,
  `last` float NOT NULL,
  `volume` float NOT NULL,
  PRIMARY KEY (`date`,`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='https://www.okcoin.com/api/ticker.do?ok=1\nhttps://www.okcoin.com/api/ticker.do?symbol=ltc_usd&ok=1';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticker`
--

LOCK TABLES `ticker` WRITE;
/*!40000 ALTER TABLE `ticker` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tradehistory`
--

DROP TABLE IF EXISTS `tradehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tradehistory` (
  `date` bigint(21) NOT NULL,
  `date_ms` bigint(21) NOT NULL,
  `tid` bigint(21) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `type` varchar(4) NOT NULL,
  `amount` float NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`date`,`date_ms`,`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='https://www.okcoin.com/api/trades.do?since=5000&ok=1\nhttps://www.okcoin.com/api/trades.do?symbol=ltc_usd&since=5000&ok=1';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tradehistory`
--

LOCK TABLES `tradehistory` WRITE;
/*!40000 ALTER TABLE `tradehistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `tradehistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-05 14:22:02
