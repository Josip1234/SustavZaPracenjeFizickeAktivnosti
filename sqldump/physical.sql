-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 06, 2018 at 10:34 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `physical`
--

-- --------------------------------------------------------

--
-- Table structure for table `activities`
--

CREATE TABLE `activities` (
  `id` int(11) NOT NULL,
  `OIB` varchar(11) COLLATE utf8_croatian_ci NOT NULL,
  `naziv_aktivnosti` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `vrijeme_izvođenja` time NOT NULL,
  `datum_izvođenja` date NOT NULL,
  `broj_koraka` int(11) DEFAULT NULL,
  `broj_trbušnjaka` int(11) DEFAULT NULL,
  `broj_sklekova` int(11) DEFAULT NULL,
  `kilometraža` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ibm`
--

CREATE TABLE `ibm` (
  `id` int(11) NOT NULL,
  `OIB` varchar(11) COLLATE utf8_croatian_ci NOT NULL,
  `IBM` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mapiranje`
--

CREATE TABLE `mapiranje` (
  `id` int(11) NOT NULL,
  `OIB` varchar(11) COLLATE utf8_croatian_ci NOT NULL,
  `naziv_rute` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `latituda` double NOT NULL,
  `longituda` double NOT NULL,
  `grad` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `adresa` varchar(255) COLLATE utf8_croatian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `OIB` varchar(11) COLLATE utf8_croatian_ci NOT NULL,
  `ime` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `spol` varchar(1) COLLATE utf8_croatian_ci NOT NULL,
  `datumr` date NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `sifra` varchar(255) COLLATE utf8_croatian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci ROW_FORMAT=COMPACT;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`id`, `OIB`, `ime`, `prezime`, `spol`, `datumr`, `email`, `sifra`) VALUES
(1, '86052601428', 'Josip', 'Bošnjak', 'm', '1992-11-05', 'jbosnjak3@gmail.com', 'test'),
(2, '14588899666', 'Marko', 'Marković', 'm', '2017-09-30', 'mmarkovic@gmail.com', 'egeggheghdhehe'),
(3, '12344444444', 'Bo??njak', 'bo??njak', 'm', '2017-12-12', 'aaa@gg.aa', 'sggsÄ?Ä‡Ä‡'),
(7, '1234444444', 'Bošnjak', 'bošnjak', 'm', '2017-12-12', 'aaahh@gg.aa', 'šššš'),
(9, '14555555555', 'Josip', 'BoÅ¡njak', 'm', '2018-02-09', 'bbosnjak@gg.gg', 'FEWFWFWEFFWFW'),
(11, '14777777777', 'fefwefwf', 'wfgrrrgrggrÄ?Å¡', 'm', '2018-02-16', 'fefwef@gg.geg', 'fefwffeewfeff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `OIB` (`OIB`),
  ADD KEY `OIB_2` (`OIB`),
  ADD KEY `OIB_3` (`OIB`);

--
-- Indexes for table `ibm`
--
ALTER TABLE `ibm`
  ADD PRIMARY KEY (`id`),
  ADD KEY `OIB` (`OIB`);

--
-- Indexes for table `mapiranje`
--
ALTER TABLE `mapiranje`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `naziv_rute` (`naziv_rute`),
  ADD KEY `OIB` (`OIB`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sifra` (`sifra`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `OIB` (`OIB`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activities`
--
ALTER TABLE `activities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ibm`
--
ALTER TABLE `ibm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mapiranje`
--
ALTER TABLE `mapiranje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activities`
--
ALTER TABLE `activities`
  ADD CONSTRAINT `oibk` FOREIGN KEY (`OIB`) REFERENCES `registration` (`OIB`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ibm`
--
ALTER TABLE `ibm`
  ADD CONSTRAINT `oibkeyx` FOREIGN KEY (`OIB`) REFERENCES `registration` (`OIB`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mapiranje`
--
ALTER TABLE `mapiranje`
  ADD CONSTRAINT `oib` FOREIGN KEY (`OIB`) REFERENCES `registration` (`OIB`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
