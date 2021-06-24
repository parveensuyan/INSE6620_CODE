CREATE DATABASE  IF NOT EXISTS `nursemanagement` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `nursemanagement`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: nursemanagement
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `add_preference`
--

DROP TABLE IF EXISTS `add_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_preference` (
  `prefer_id` int(11) NOT NULL AUTO_INCREMENT,
  `nurse_id` int(4) DEFAULT NULL,
  `preference` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prefer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `add_preference_day`
--

DROP TABLE IF EXISTS `add_preference_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_preference_day` (
  `prefer_day_id` int(11) NOT NULL AUTO_INCREMENT,
  `nurse_id` int(4) DEFAULT NULL,
  `prefer_id` int(4) DEFAULT NULL,
  `preference_day` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prefer_day_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `add_schedule`
--

DROP TABLE IF EXISTS `add_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `hospital_id` int(4) DEFAULT NULL,
  `schedule_day_night` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `add_schedule_day`
--

DROP TABLE IF EXISTS `add_schedule_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_schedule_day` (
  `schedule_day_id` int(11) NOT NULL AUTO_INCREMENT,
  `nurse_id` int(4) DEFAULT NULL,
  `schedule_id` int(4) DEFAULT NULL,
  `schedule_day` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`schedule_day_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency` (
  `agency_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `PASSWRD` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postalcode` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `agencycode` varchar(255) DEFAULT NULL,
  `certified` varchar(255) DEFAULT NULL,
  `CONFIRMPASSWRD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`agency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nurse_id` int(4) DEFAULT NULL,
  `prefer_id` int(4) DEFAULT NULL,
  `hospital_id` int(4) DEFAULT NULL,
  `preference_day` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `hospital_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `PASSWRD` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postalcode` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `certified` varchar(255) DEFAULT NULL,
  `CONFIRMPASSWRD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nurses`
--

DROP TABLE IF EXISTS `nurses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurses` (
  `nurse_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `PASSWRD` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postalcode` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `CONFIRMPASSWRD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nurse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'nursemanagement'
--

--
-- Dumping routines for database 'nursemanagement'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-23 21:54:32
