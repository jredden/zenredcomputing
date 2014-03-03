DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `User_id` int unsigned NOT NULL auto_increment
  ,`User_status` ENUM('candidate1', 'candidate2', 'registered')
  ,`firstName` varchar(255) NOT NULL default ''
  ,`lastName` varchar(255) NOT NULL default ''
  ,`emailAddress` varchar(255) NOT NULL default ''
  ,`password` varchar(255)  NOT NULL default ''
  ,`Datestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ,KEY `User_idKey` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subjects` (
`Subjects_id` int unsigned NOT NULL auto_increment
,`Subjects_code` int unsigned NOT NULL
,`Subjects_name` varchar(255) NOT NULL default ''
,`Datestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,KEY `Subjects_idKey` (`Subjects_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Posts` (
`Posts_id` int unsigned NOT NULL auto_increment
,`Content` text
,`Datestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,KEY `Posts_idKey` (`Posts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `UserToSubjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserToSubjects` (
`UserToSubjects_id` int unsigned NOT NULL auto_increment
,`Subjects_id` int unsigned NOT NULL
,`User_id` int unsigned NOT NULL
,`Datestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,KEY `UsersToSubjects_idKey` (`UserToSubjects_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `UserToPosts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserToPosts` (
`UserToPosts_id` int unsigned NOT NULL auto_increment
,`User_id` int unsigned NOT NULL
,`Posts_id` int unsigned NOT NULL
,`Datestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,KEY `UsersToPosts_idKey` (`UserToPosts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `SubjectsToPosts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectsToPosts` (
`SubjectsToPosts_id` int unsigned NOT NULL auto_increment
,`Subjects_id` int unsigned NOT NULL
,`Posts_id` int unsigned NOT NULL
,`Datestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,KEY `SubjectsToPosts_idKey` (`SubjectsToPosts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

