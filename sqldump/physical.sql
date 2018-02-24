-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2018 at 05:46 PM
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
-- Table structure for table `biking`
--

CREATE TABLE `biking` (
  `id` int(11) NOT NULL,
  `vrijemeAktivnosti` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `brzinaUkm` double NOT NULL,
  `lokacija` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `udaljenost` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` date NOT NULL
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
(2, '45699988885', 'marko', 'markovic', 'm', '1997-01-30', 'mmarkovic32@gmail.com', '12345678'),
(3, '14888896654', 'Walter', 'White', 'm', '1958-02-06', 'he@gmail.com ', 'test'),
(4, '48975588966', 'Jesse', 'Pinkman', 'm', '1979-07-13', 'methaddict@gmail.com', 'sciencebitch');

-- --------------------------------------------------------

--
-- Table structure for table `running`
--

CREATE TABLE `running` (
  `id` int(11) NOT NULL,
  `vrijemeAktivnosti` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `brzinaUkm` double NOT NULL,
  `lokacija` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `udaljenost` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `walking`
--

CREATE TABLE `walking` (
  `id` int(11) NOT NULL,
  `udaljenost` double NOT NULL,
  `vrijemeAktivnosti` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `koraci` int(11) NOT NULL,
  `adresa` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `brzinaUkm` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `biking`
--
ALTER TABLE `biking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `OIB` (`OIB`);

--
-- Indexes for table `running`
--
ALTER TABLE `running`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`);

--
-- Indexes for table `walking`
--
ALTER TABLE `walking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `biking`
--
ALTER TABLE `biking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `running`
--
ALTER TABLE `running`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `walking`
--
ALTER TABLE `walking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `biking`
--
ALTER TABLE `biking`
  ADD CONSTRAINT `biking_ibfk_1` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `running`
--
ALTER TABLE `running`
  ADD CONSTRAINT `email` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `walking`
--
ALTER TABLE `walking`
  ADD CONSTRAINT `walking_ibfk_1` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
