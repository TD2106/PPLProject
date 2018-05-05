-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitalreview
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `comment_content` varchar(300) NOT NULL,
  `comment_date` date NOT NULL,
  `comment_time` time NOT NULL,
  `is_enable` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_patient1_idx` (`patient_id`),
  KEY `fk_comment_doctor1_idx` (`doctor_id`),
  CONSTRAINT `fk_comment_doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `degree` varchar(100) NOT NULL,
  `accept_insurance` int(11) NOT NULL,
  `office_hour` varchar(45) NOT NULL,
  `hospital_id` int(11) NOT NULL,
  `general_specialty_id` int(11) NOT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `fk_doctor_hospital1_idx` (`hospital_id`),
  KEY `fk_doctor_general_specialty1_idx` (`general_specialty_id`),
  CONSTRAINT `fk_doctor_general_specialty1` FOREIGN KEY (`general_specialty_id`) REFERENCES `general_specialty` (`general_specialty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_hospital1` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `HospitalReview`.`doctor_BEFORE_DELETE` BEFORE DELETE ON `doctor` FOR EACH ROW
BEGIN
	DELETE FROM doctor_rating WHERE old.doctor_id = doctor_rating.doctor_id;
    DELETE FROM comment WHERE old.doctor_id = comment.doctor_id;
    DELETE FROM doctor_language WHERE old.doctor_id = doctor_language.doctor_id;
    DELETE FROM favorite_doctor WHERE old.doctor_id = favorite_doctor.doctor_id;
    DELETE FROM doctor_specific_specialty WHERE old.doctor_id = doctor_specific_specialty.doctor_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `doctor_language`
--

DROP TABLE IF EXISTS `doctor_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_language` (
  `doctor_id` int(11) NOT NULL,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`doctor_id`,`language`),
  KEY `fk_doctor_language_doctor1_idx` (`doctor_id`),
  CONSTRAINT `fk_doctor_language_doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_language`
--

LOCK TABLES `doctor_language` WRITE;
/*!40000 ALTER TABLE `doctor_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_rating`
--

DROP TABLE IF EXISTS `doctor_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_rating` (
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `is_enable` int(11) NOT NULL,
  PRIMARY KEY (`patient_id`,`doctor_id`),
  KEY `fk_doctor_rating_patient1_idx` (`patient_id`),
  KEY `fk_doctor_rating_doctor1_idx` (`doctor_id`),
  CONSTRAINT `fk_doctor_rating_doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_rating_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_rating`
--

LOCK TABLES `doctor_rating` WRITE;
/*!40000 ALTER TABLE `doctor_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_specific_specialty`
--

DROP TABLE IF EXISTS `doctor_specific_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_specific_specialty` (
  `specific_specialty_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`specific_specialty_id`,`doctor_id`),
  KEY `fk_doctor_specific_specialty_specific_specialty1_idx` (`specific_specialty_id`),
  KEY `fk_doctor_specific_specialty_doctor1_idx` (`doctor_id`),
  CONSTRAINT `fk_doctor_specific_specialty_doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_specific_specialty_specific_specialty1` FOREIGN KEY (`specific_specialty_id`) REFERENCES `specific_specialty` (`specific_specialty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_specific_specialty`
--

LOCK TABLES `doctor_specific_specialty` WRITE;
/*!40000 ALTER TABLE `doctor_specific_specialty` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_specific_specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_doctor`
--

DROP TABLE IF EXISTS `favorite_doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_doctor` (
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`patient_id`,`doctor_id`),
  KEY `fk_favorite_doctor_doctor1_idx` (`doctor_id`),
  CONSTRAINT `fk_favorite_doctor_doctor1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_doctor_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_doctor`
--

LOCK TABLES `favorite_doctor` WRITE;
/*!40000 ALTER TABLE `favorite_doctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_hospital`
--

DROP TABLE IF EXISTS `favorite_hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_hospital` (
  `patient_id` int(11) NOT NULL,
  `hospital_id` int(11) NOT NULL,
  PRIMARY KEY (`patient_id`,`hospital_id`),
  KEY `fk_favorite_hospital_patient1_idx` (`patient_id`),
  KEY `fk_favorite_hospital_hospital1_idx` (`hospital_id`),
  CONSTRAINT `fk_favorite_hospital_hospital1` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_hospital_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_hospital`
--

LOCK TABLES `favorite_hospital` WRITE;
/*!40000 ALTER TABLE `favorite_hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_specialty`
--

DROP TABLE IF EXISTS `general_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_specialty` (
  `general_specialty_id` int(11) NOT NULL AUTO_INCREMENT,
  `general_specialty` varchar(100) NOT NULL,
  PRIMARY KEY (`general_specialty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_specialty`
--

LOCK TABLES `general_specialty` WRITE;
/*!40000 ALTER TABLE `general_specialty` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_specialty` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `HospitalReview`.`general_specialty_BEFORE_DELETE` BEFORE DELETE ON `general_specialty` FOR EACH ROW
BEGIN
	UPDATE doctor SET doctor.general_specialty_id = 0 WHERE doctor.general_specialty_id = old.general_specialty_id;
    DELETE FROM specific_special_ty WHERE specific_specialty.general_specialty_id = old.general_specialty_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital` (
  `hospital_id` int(11) NOT NULL,
  `hospital_name` varchar(200) NOT NULL,
  `address` varchar(300) NOT NULL,
  `website` varchar(300) NOT NULL,
  `hospital_admin_name` varchar(100) NOT NULL,
  `hospital_admin_email` varchar(100) NOT NULL,
  `is_activated` int(11) NOT NULL,
  PRIMARY KEY (`hospital_id`),
  KEY `fk_Hospital_User1_idx` (`hospital_id`),
  CONSTRAINT `fk_Hospital_User1` FOREIGN KEY (`hospital_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (2,'CR','NCT','cr.com','test1','test1@gmail.com',0);
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `HospitalReview`.`hospital_BEFORE_DELETE` BEFORE DELETE ON `hospital` FOR EACH ROW
BEGIN
	DELETE FROM doctor WHERE doctor.hospital_id = old.hospital_id;
    DELETE FROM favorite_hospital WHERE favorite_hospital.hospital_id = old.hospital_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `address` varchar(300) NOT NULL,
  `is_activated` int(11) NOT NULL,
  PRIMARY KEY (`patient_id`),
  KEY `fk_Patient_User_idx` (`patient_id`),
  CONSTRAINT `fk_Patient_User` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'H','L','Male','HCMC',0);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `HospitalReview`.`patient_BEFORE_DELETE` BEFORE DELETE ON `patient` FOR EACH ROW
BEGIN
	DELETE FROM comment WHERE old.patient_id = comment.patient_id;
    DELETE FROM doctor_rating WHERE old.patient_id = doctor_rating.patient_id;
    DELETE FROM favorite_hospital WHERE old.patient_id = favorite_hospital.patient_id;
    DELETE FROM favorite_doctor WHERE old.patient_id = favorite_doctor.patient_id;
    DELETE FROM patient_language WHERE old.patient_id = patient_language.patient_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `patient_language`
--

DROP TABLE IF EXISTS `patient_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_language` (
  `patient_id` int(11) NOT NULL,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`patient_id`,`language`),
  KEY `fk_PatientLanguage_Patient1_idx` (`patient_id`),
  CONSTRAINT `fk_PatientLanguage_Patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_language`
--

LOCK TABLES `patient_language` WRITE;
/*!40000 ALTER TABLE `patient_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specific_specialty`
--

DROP TABLE IF EXISTS `specific_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specific_specialty` (
  `specific_specialty_id` int(11) NOT NULL AUTO_INCREMENT,
  `specific_specialty` varchar(100) NOT NULL,
  `general_specialty_id` int(11) NOT NULL,
  PRIMARY KEY (`specific_specialty_id`),
  KEY `fk_specific_specialty_general_specialty1_idx` (`general_specialty_id`),
  CONSTRAINT `fk_specific_specialty_general_specialty1` FOREIGN KEY (`general_specialty_id`) REFERENCES `general_specialty` (`general_specialty_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specific_specialty`
--

LOCK TABLES `specific_specialty` WRITE;
/*!40000 ALTER TABLE `specific_specialty` DISABLE KEYS */;
/*!40000 ALTER TABLE `specific_specialty` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `HospitalReview`.`specific_specialty_BEFORE_DELETE` BEFORE DELETE ON `specific_specialty` FOR EACH ROW
BEGIN
	 DELETE FROM doctor_specific_specialty WHERE specific_specialty.specific_specialty_id = doctor_specific_specialty.specific_specialty_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(300) NOT NULL,
  `user_type` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test@gmail.com','123','patient'),(2,'cr@gmail.com','1234','hospital');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `HospitalReview`.`user_BEFORE_DELETE` BEFORE DELETE ON `user` FOR EACH ROW
BEGIN
	DELETE FROM hospital WHERE old.user_id = hospital.hospital_id;
    DELETE FROM patient WHERE old.user_id = patient.patient_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'hospitalreview'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_comment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `add_comment`(IN patient_id INT,IN doctor_id INT,IN content varchar(300))
BEGIN
	INSERT INTO comment(patient_id,doctor_id,comment_content,comment_date,comment_time,is_enable)
    VALUES (patient_id,doctor_id,content,CURDATE(),TIME(NOW()),1);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_doctor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `add_doctor`(IN first_name VARCHAR(45), IN last_name VARCHAR(45), IN degree VARCHAR(100),
								IN accept_insurance INT, IN office_hour VARCHAR(45), IN hospital_id INT, IN general_specialty_id INT)
BEGIN
	INSERT INTO doctor(first_name,last_name,degree,accept_insurance,office_hour,hospital_id,general_specialty_id) 
    VALUES(first_name,last_name,degree,accept_insurance,office_hour,hospital_id,general_specialty_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_hospital` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `add_hospital`(IN email varchar(100), IN password varchar(300),
				IN hospital_name varchar(200),IN address varchar(300),IN website varchar(300),
                IN hospital_admin_name varchar(100),IN hospital_admin_email varchar(100))
BEGIN
	INSERT INTO user(email,password,user_type) values (email,password,'hospital');
    SET @id = last_insert_id();
    INSERT INTO hospital (hospital_id,hospital_name,address,website,hospital_admin_name,hospital_admin_email,is_activated)
    VALUES (@id,hospital_name,address,website,hospital_admin_name,hospital_admin_email,0);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_patient` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `add_patient`(IN email varchar(100), IN password varchar(300),
				IN first_name varchar(45),IN last_name varchar(45),IN gender varchar(45),
                IN address varchar(300))
BEGIN
	INSERT INTO user(email,password,user_type) values (email,password,'patient');
    SET @id = last_insert_id();
    INSERT INTO patient (patient_id,first_name,last_name,gender,address,is_activated)
    VALUES (@id,first_name,last_name,gender,address,0);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_doctor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `update_doctor`(IN doctor_id INT, IN first_name VARCHAR(45), IN last_name VARCHAR(45), IN degree VARCHAR(100),
								IN accept_insurance INT, IN office_hour VARCHAR(45), IN hospital_id INT, IN general_specialty_id INT)
BEGIN
	UPDATE doctor SET doctor.first_name = first_name, doctor.last_name = last_name, doctor.degree = degree,
						doctor.accept_insurance = accept_insurance, doctor.office_hour = office_hour,
						doctor.hospital_id = hospital_id, doctor.general_specialty_id = general_specialty_id 
	WHERE doctor.doctor_id = doctor_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_hospital` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `update_hospital`(IN hospital_id INT, IN email varchar(100), IN password varchar(300),
				IN hospital_name varchar(200),IN address varchar(300),IN website varchar(300),
                IN hospital_admin_name varchar(100),IN hospital_admin_email varchar(100))
BEGIN
	UPDATE user SET user.email = email, user.password = password WHERE user.user_id = hospital_id;
    UPDATE hospital SET hospital.hospital_name = hospital_name, hospital.address = address, 
    hospital.website = website, hospital.hospital_admin_name = hospital_admin_name, 
    hospital.hospital_admin_email = hospital_admin_email WHERE hospital.hospital_id = hospital_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_patient` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `update_patient`(IN patient_id INT, IN email varchar(100), IN password varchar(300),
				IN first_name varchar(45),IN last_name varchar(45),IN gender varchar(45),
                IN address varchar(300))
BEGIN
	UPDATE user SET user.email = email, user.password = password WHERE user.user_id = patient_id;
    UPDATE patient SET patient.first_name = first_name, patient.last_name = last_name, 
    patient.gender = gender, patient.address = address WHERE patient.patient_id = patient_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-26 13:30:39
