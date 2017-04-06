-- MySQL dump 10.13  Distrib 5.6.27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: baylandtag
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu0.14.04.1

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
-- Table structure for table `abgeordneter`
--

DROP TABLE IF EXISTS `abgeordneter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abgeordneter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Vorname` varchar(45) NOT NULL,
  `Titel` varchar(45) DEFAULT NULL,
  `Beruf` varchar(100) DEFAULT NULL,
  `Bild` mediumblob,
  `geb_am` date DEFAULT NULL,
  `geb_in` varchar(100) DEFAULT NULL,
  `ges_am` date DEFAULT NULL,
  `ges_in` varchar(100) DEFAULT NULL,
  `f_id` int(11) DEFAULT NULL,
  `k_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Staatsregierung_Familienstand_idx` (`f_id`),
  KEY `fk_Staatsregierung_Konfession1_idx` (`k_id`),
  KEY `Name` (`Name`,`Vorname`),
  KEY `Beruf` (`Beruf`),
  KEY `geb_in` (`geb_in`),
  KEY `ges_in` (`ges_in`),
  KEY `Title` (`Titel`),
  CONSTRAINT `fk_Staatsregierung_Familienstand` FOREIGN KEY (`f_id`) REFERENCES `familienstand` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Staatsregierung_Konfession1` FOREIGN KEY (`k_id`) REFERENCES `konfession` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abgeordneter`
--

LOCK TABLES `abgeordneter` WRITE;
/*!40000 ALTER TABLE `abgeordneter` DISABLE KEYS */;
/*!40000 ALTER TABLE `abgeordneter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familienstand`
--

DROP TABLE IF EXISTS `familienstand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familienstand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Bezeichnung` (`Bezeichnung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familienstand`
--

LOCK TABLES `familienstand` WRITE;
/*!40000 ALTER TABLE `familienstand` DISABLE KEYS */;
/*!40000 ALTER TABLE `familienstand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `konfession`
--

DROP TABLE IF EXISTS `konfession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `konfession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Bezeichnung` (`Bezeichnung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `konfession`
--

LOCK TABLES `konfession` WRITE;
/*!40000 ALTER TABLE `konfession` DISABLE KEYS */;
/*!40000 ALTER TABLE `konfession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kreis`
--

DROP TABLE IF EXISTS `kreis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kreis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` char(1) NOT NULL,
  `Bezeichnung` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Bezeichnung` (`Bezeichnung`),
  KEY `Type` (`Type`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kreis`
--

LOCK TABLES `kreis` WRITE;
/*!40000 ALTER TABLE `kreis` DISABLE KEYS */;
/*!40000 ALTER TABLE `kreis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden`
--

DROP TABLE IF EXISTS `orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orden` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Bezeichnung` (`Bezeichnung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parfkt`
--

DROP TABLE IF EXISTS `parfkt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parfkt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Bezeichnung` (`Bezeichnung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parfkt`
--

LOCK TABLES `parfkt` WRITE;
/*!40000 ALTER TABLE `parfkt` DISABLE KEYS */;
/*!40000 ALTER TABLE `parfkt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partei`
--

DROP TABLE IF EXISTS `partei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partei` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Bezeichnung` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partei`
--

LOCK TABLES `partei` WRITE;
/*!40000 ALTER TABLE `partei` DISABLE KEYS */;
/*!40000 ALTER TABLE `partei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staatsregierung`
--

DROP TABLE IF EXISTS `staatsregierung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staatsregierung` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Bezeichnung` (`Bezeichnung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staatsregierung`
--

LOCK TABLES `staatsregierung` WRITE;
/*!40000 ALTER TABLE `staatsregierung` DISABLE KEYS */;
/*!40000 ALTER TABLE `staatsregierung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zt_fkt_abg`
--

DROP TABLE IF EXISTS `zt_fkt_abg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zt_fkt_abg` (
  `pa_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  `Typ` varchar(45) NOT NULL,
  `von` date NOT NULL,
  `bis` date NOT NULL,
  PRIMARY KEY (`pa_id`,`a_id`,`Typ`,`von`,`bis`),
  KEY `fk_ParFkt_has_Abgeordneter_Abgeordneter1_idx` (`a_id`),
  KEY `fk_ParFkt_has_Abgeordneter_ParFkt1_idx` (`pa_id`),
  CONSTRAINT `fk_ParFkt_has_Abgeordneter_Abgeordneter1` FOREIGN KEY (`a_id`) REFERENCES `abgeordneter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ParFkt_has_Abgeordneter_ParFkt1` FOREIGN KEY (`pa_id`) REFERENCES `parfkt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zt_fkt_abg`
--

LOCK TABLES `zt_fkt_abg` WRITE;
/*!40000 ALTER TABLE `zt_fkt_abg` DISABLE KEYS */;
/*!40000 ALTER TABLE `zt_fkt_abg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zt_kreis_abg`
--

DROP TABLE IF EXISTS `zt_kreis_abg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zt_kreis_abg` (
  `k_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  `von` date NOT NULL,
  `bis` date NOT NULL,
  PRIMARY KEY (`k_id`,`a_id`,`von`,`bis`),
  KEY `fk_Kreis_has_Staatsregierung_Staatsregierung1_idx` (`a_id`),
  KEY `fk_Kreis_has_Staatsregierung_Kreis1_idx` (`k_id`),
  CONSTRAINT `fk_Kreis_has_Staatsregierung_Kreis1` FOREIGN KEY (`k_id`) REFERENCES `kreis` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kreis_has_Staatsregierung_Staatsregierung1` FOREIGN KEY (`a_id`) REFERENCES `abgeordneter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zt_kreis_abg`
--

LOCK TABLES `zt_kreis_abg` WRITE;
/*!40000 ALTER TABLE `zt_kreis_abg` DISABLE KEYS */;
/*!40000 ALTER TABLE `zt_kreis_abg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zt_ord_abg`
--

DROP TABLE IF EXISTS `zt_ord_abg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zt_ord_abg` (
  `o_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`o_id`,`a_id`),
  KEY `fk_orden_has_abgeordneter_abgeordneter1_idx` (`a_id`),
  KEY `fk_orden_has_abgeordneter_orden1_idx` (`o_id`),
  CONSTRAINT `fk_orden_has_abgeordneter_abgeordneter1` FOREIGN KEY (`a_id`) REFERENCES `abgeordneter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orden_has_abgeordneter_orden1` FOREIGN KEY (`o_id`) REFERENCES `orden` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zt_ord_abg`
--

LOCK TABLES `zt_ord_abg` WRITE;
/*!40000 ALTER TABLE `zt_ord_abg` DISABLE KEYS */;
/*!40000 ALTER TABLE `zt_ord_abg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zt_partei_abg`
--

DROP TABLE IF EXISTS `zt_partei_abg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zt_partei_abg` (
  `a_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `von` date NOT NULL,
  `bis` date NOT NULL,
  PRIMARY KEY (`a_id`,`p_id`,`von`,`bis`),
  KEY `fk_Abgeordneter_has_Partei_Partei1_idx` (`p_id`),
  KEY `fk_Abgeordneter_has_Partei_Abgeordneter1_idx` (`a_id`),
  CONSTRAINT `fk_Abgeordneter_has_Partei_Abgeordneter1` FOREIGN KEY (`a_id`) REFERENCES `abgeordneter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Abgeordneter_has_Partei_Partei1` FOREIGN KEY (`p_id`) REFERENCES `partei` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zt_partei_abg`
--

LOCK TABLES `zt_partei_abg` WRITE;
/*!40000 ALTER TABLE `zt_partei_abg` DISABLE KEYS */;
/*!40000 ALTER TABLE `zt_partei_abg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zt_reg_abg`
--

DROP TABLE IF EXISTS `zt_reg_abg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zt_reg_abg` (
  `s_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  `von` date NOT NULL,
  `bis` date NOT NULL,
  PRIMARY KEY (`s_id`,`a_id`,`von`,`bis`),
  KEY `fk_Staatsregierung_has_Abgeordneter_Abgeordneter1_idx` (`a_id`),
  KEY `fk_Staatsregierung_has_Abgeordneter_Staatsregierung1_idx` (`s_id`),
  CONSTRAINT `fk_Staatsregierung_has_Abgeordneter_Abgeordneter1` FOREIGN KEY (`a_id`) REFERENCES `abgeordneter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Staatsregierung_has_Abgeordneter_Staatsregierung1` FOREIGN KEY (`s_id`) REFERENCES `staatsregierung` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zt_reg_abg`
--

LOCK TABLES `zt_reg_abg` WRITE;
/*!40000 ALTER TABLE `zt_reg_abg` DISABLE KEYS */;
/*!40000 ALTER TABLE `zt_reg_abg` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-11 14:59:51
