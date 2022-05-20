-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Erstellungszeit: 20. Mai 2022 um 12:34
-- Server-Version: 10.7.3-MariaDB-1:10.7.3+maria~focal
-- PHP-Version: 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `olat-2-0`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_absence_entity`
--

CREATE TABLE `olat_absence_entity` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `signed` bit(1) DEFAULT NULL,
  `olat_subject_entity_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `in_use` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_group_entity`
--

CREATE TABLE `olat_group_entity` (
  `id` bigint(20) NOT NULL,
  `file_system_prefix` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `in_use` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_person_entity`
--

CREATE TABLE `olat_person_entity` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `in_use` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `olat_person_entity`
--

INSERT INTO `olat_person_entity` (`id`, `email`, `firstname`, `lastname`, `in_use`) VALUES
(1, 'admin@olat.com', 'Olat', 'Administrator', b'1'),
(6, 'gatzka58@gmail.com', 'Peter', 'Rutschmann', b'0');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_subject_entity`
--

CREATE TABLE `olat_subject_entity` (
  `id` bigint(20) NOT NULL,
  `details` longtext DEFAULT NULL,
  `file_system_prefix` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `in_use` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `olat_subject_entity`
--

INSERT INTO `olat_subject_entity` (`id`, `details`, `file_system_prefix`, `name`, `in_use`) VALUES
(24, 'Serverdienste', 'modul_123', 'Modul 123', b'0'),
(25, 'Daten', 'modul_100', 'Modul 100', b'0'),
(26, 'Datenbank', 'modul_104', 'Modul 104', b'0'),
(27, 'CodeKompVerschl', 'modul_114', 'Modul 114', b'0'),
(28, 'Netzwerk', 'modul_117', 'Modul 117', b'0'),
(29, 'Programmieren', 'modul_403', 'Modul 403', b'0'),
(30, 'Objektbasiert', 'modul_404', 'Modul 404', b'0'),
(31, 'Projektauftrag', 'modul_431', 'Modul 431', b'0'),
(32, 'Modul 120', 'modul_120', 'Modul 120', b'0'),
(33, 'Modul 226A', 'modul_226a', 'Modul 226A', b'0'),
(34, 'OO Vererbung', 'modul_226b', 'Modul 226B', b'0'),
(35, 'Architektur', 'modul_326', 'Modul 326', b'0'),
(36, 'Modul 426', 'modul_426', 'Modul 426', b'0'),
(37, 'User instr.', 'modul_214', 'Modul 214', b'0'),
(38, 'DB anbinden', 'modul_151', 'Modul 151', b'0'),
(39, 'Multimedia', 'modul_152', 'Modul 152', b'0'),
(40, 'App Sicherheit', 'modul_183', 'Modul 183', b'0'),
(41, 'Algorithmen', 'modul_411', 'Modul 411', b'0'),
(42, 'Allgemeinbildung', 'allgemeinbildung', 'Allgemeinbildung', b'0'),
(43, 'Wirtschaft', 'wirtschaft', 'Wirtschaft', b'0'),
(44, 'Englisch', 'englisch', 'Englisch', b'0'),
(45, 'Mathe & NW', 'mathe_nw', 'Mathe & NW', b'0');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_timetable_entity`
--

CREATE TABLE `olat_timetable_entity` (
  `id` bigint(20) NOT NULL,
  `weekday` varchar(255) NOT NULL,
  `olat_group_entity_id` bigint(20) NOT NULL,
  `in_use` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_timetable_entity_olat_subject_entities`
--

CREATE TABLE `olat_timetable_entity_olat_subject_entities` (
  `olat_timetable_entity_id` bigint(20) NOT NULL,
  `olat_subject_entities_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_user_entity`
--

CREATE TABLE `olat_user_entity` (
  `id` bigint(20) NOT NULL,
  `file_system_prefix` varchar(255) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `profile_picture_url` varchar(255) DEFAULT NULL,
  `registered` bit(1) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `olat_person_entity_id` bigint(20) NOT NULL,
  `in_use` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `olat_user_entity`
--

INSERT INTO `olat_user_entity` (`id`, `file_system_prefix`, `hashed_password`, `profile_picture_url`, `registered`, `username`, `olat_person_entity_id`, `in_use`) VALUES
(1, 'admin', '$2a$10$ExREI0vux3bJNuj/8vndlO792kmhwYq1nnFiHytl86P14t4cUOKsq', 'admin', NULL, 'admin', 1, NULL),
(10, 'peter_rutschmann', '$2a$10$RbRqwrBuHMm5ti.ffJwYMu4gseym/Tr8jPe03l0K9npkLcLFyiGZq', 'zzearl628a2gnlp', b'0', 'peter.rutschmann', 6, b'0');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_user_entity_olat_group_entities`
--

CREATE TABLE `olat_user_entity_olat_group_entities` (
  `olat_user_entity_id` bigint(20) NOT NULL,
  `olat_group_entities_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `olat_user_entity_roles`
--

CREATE TABLE `olat_user_entity_roles` (
  `olat_user_entity_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `olat_user_entity_roles`
--

INSERT INTO `olat_user_entity_roles` (`olat_user_entity_id`, `roles`) VALUES
(1, 'ADMIN'),
(10, 'STUDENT');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `olat_absence_entity`
--
ALTER TABLE `olat_absence_entity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5hj2u2wnsc4ryfosuhfi1nxku` (`olat_subject_entity_id`),
  ADD KEY `FK6thxagne2h1u5jlcbrek2spc9` (`student_id`);

--
-- Indizes für die Tabelle `olat_group_entity`
--
ALTER TABLE `olat_group_entity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fp4o34ui9pef19wgyveusr5qk` (`file_system_prefix`),
  ADD UNIQUE KEY `UK_j70c4mxyllpn7baye0k8gb4lt` (`name`),
  ADD KEY `FKlk295ogna36woloqrdkrs3glt` (`teacher_id`);

--
-- Indizes für die Tabelle `olat_person_entity`
--
ALTER TABLE `olat_person_entity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_k86adf01ih5wul7il1qbrpurw` (`email`);

--
-- Indizes für die Tabelle `olat_subject_entity`
--
ALTER TABLE `olat_subject_entity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1rebli0qeuo79l8var44da6xp` (`file_system_prefix`),
  ADD UNIQUE KEY `UK_cm6k0dkxrf9vhr6biedkv55to` (`name`);

--
-- Indizes für die Tabelle `olat_timetable_entity`
--
ALTER TABLE `olat_timetable_entity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nlnah8sw6expluh8mc77xkoa7` (`olat_group_entity_id`);

--
-- Indizes für die Tabelle `olat_timetable_entity_olat_subject_entities`
--
ALTER TABLE `olat_timetable_entity_olat_subject_entities`
  ADD KEY `FKjtggcow65191hevo98cwy755h` (`olat_subject_entities_id`),
  ADD KEY `FKkm218wujs7aojf5pixhb0qviy` (`olat_timetable_entity_id`);

--
-- Indizes für die Tabelle `olat_user_entity`
--
ALTER TABLE `olat_user_entity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_81eykh1s3tc7hb8xcfdb5k9yx` (`olat_person_entity_id`),
  ADD UNIQUE KEY `UK_d5oc3v0cn8jtp5wxulxbvu7je` (`file_system_prefix`),
  ADD UNIQUE KEY `UK_qugy02eauniv6fxrtt53ggtio` (`username`);

--
-- Indizes für die Tabelle `olat_user_entity_olat_group_entities`
--
ALTER TABLE `olat_user_entity_olat_group_entities`
  ADD KEY `FKgxrrvai5g0un9qh4np8owgbul` (`olat_group_entities_id`),
  ADD KEY `FKpsg8oj2m14kal7rcig90how3r` (`olat_user_entity_id`);

--
-- Indizes für die Tabelle `olat_user_entity_roles`
--
ALTER TABLE `olat_user_entity_roles`
  ADD KEY `FK3t6592l6l4naes621tsljclbu` (`olat_user_entity_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `olat_absence_entity`
--
ALTER TABLE `olat_absence_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `olat_group_entity`
--
ALTER TABLE `olat_group_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `olat_person_entity`
--
ALTER TABLE `olat_person_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `olat_subject_entity`
--
ALTER TABLE `olat_subject_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT für Tabelle `olat_timetable_entity`
--
ALTER TABLE `olat_timetable_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `olat_user_entity`
--
ALTER TABLE `olat_user_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `olat_absence_entity`
--
ALTER TABLE `olat_absence_entity`
  ADD CONSTRAINT `FK5hj2u2wnsc4ryfosuhfi1nxku` FOREIGN KEY (`olat_subject_entity_id`) REFERENCES `olat_subject_entity` (`id`),
  ADD CONSTRAINT `FK6thxagne2h1u5jlcbrek2spc9` FOREIGN KEY (`student_id`) REFERENCES `olat_person_entity` (`id`);

--
-- Constraints der Tabelle `olat_group_entity`
--
ALTER TABLE `olat_group_entity`
  ADD CONSTRAINT `FKlk295ogna36woloqrdkrs3glt` FOREIGN KEY (`teacher_id`) REFERENCES `olat_user_entity` (`id`);

--
-- Constraints der Tabelle `olat_timetable_entity`
--
ALTER TABLE `olat_timetable_entity`
  ADD CONSTRAINT `FK3wuc63gi4rhguxamlr5dpgvh1` FOREIGN KEY (`olat_group_entity_id`) REFERENCES `olat_group_entity` (`id`);

--
-- Constraints der Tabelle `olat_timetable_entity_olat_subject_entities`
--
ALTER TABLE `olat_timetable_entity_olat_subject_entities`
  ADD CONSTRAINT `FKjtggcow65191hevo98cwy755h` FOREIGN KEY (`olat_subject_entities_id`) REFERENCES `olat_subject_entity` (`id`),
  ADD CONSTRAINT `FKkm218wujs7aojf5pixhb0qviy` FOREIGN KEY (`olat_timetable_entity_id`) REFERENCES `olat_timetable_entity` (`id`);

--
-- Constraints der Tabelle `olat_user_entity`
--
ALTER TABLE `olat_user_entity`
  ADD CONSTRAINT `FKto24l8k7ot9oyo6etf71uqsov` FOREIGN KEY (`olat_person_entity_id`) REFERENCES `olat_person_entity` (`id`);

--
-- Constraints der Tabelle `olat_user_entity_olat_group_entities`
--
ALTER TABLE `olat_user_entity_olat_group_entities`
  ADD CONSTRAINT `FKgxrrvai5g0un9qh4np8owgbul` FOREIGN KEY (`olat_group_entities_id`) REFERENCES `olat_group_entity` (`id`),
  ADD CONSTRAINT `FKpsg8oj2m14kal7rcig90how3r` FOREIGN KEY (`olat_user_entity_id`) REFERENCES `olat_user_entity` (`id`);

--
-- Constraints der Tabelle `olat_user_entity_roles`
--
ALTER TABLE `olat_user_entity_roles`
  ADD CONSTRAINT `FK3t6592l6l4naes621tsljclbu` FOREIGN KEY (`olat_user_entity_id`) REFERENCES `olat_user_entity` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
