-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2018 at 09:29 PM
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
  `udaljenost` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `biking`
--

INSERT INTO `biking` (`id`, `vrijemeAktivnosti`, `brzinaUkm`, `udaljenost`, `email`, `datum`) VALUES
(1, '0:44', 14, 1.7732136200063726, 'jbosnjak3@gmail.com', '2018-03-16 09:47:58'),
(2, '0:53', 14, 1.7732136200063726, 'jbosnjak3@gmail.com', '2018-03-16 09:48:06');

-- --------------------------------------------------------

--
-- Table structure for table `bikingstatistika`
--

CREATE TABLE `bikingstatistika` (
  `id` int(11) NOT NULL,
  `korisnikBike` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` double NOT NULL,
  `period` datetime NOT NULL
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
(2, '45699988885', 'marko', 'markovic', 'm', '1997-01-30', 'mmarkovic32@gmail.com', '12345678'),
(4, '48975588966', 'Jesse', 'Pinkman', 'm', '1979-07-13', 'methaddict@gmail.com', 'sciencebitch'),
(7, '14588897456', 'Josip', 'Bošnjak', 'm', '1992-11-05', 'jbosnjak3@gmail.com', 'test123456'),
(8, '45556987777', 'marko', 'markovic', 'm', '1992-05-05', 'mmarkovic@gmail.com', 'rggwegwgwgfe');

-- --------------------------------------------------------

--
-- Table structure for table `running`
--

CREATE TABLE `running` (
  `id` int(11) NOT NULL,
  `vrijemeAktivnosti` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `brzinaUkm` double NOT NULL,
  `udaljenost` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `runningstatistika`
--

CREATE TABLE `runningstatistika` (
  `id` int(11) NOT NULL,
  `korisnikRun` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` double NOT NULL,
  `period` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `summarystatistika`
--

CREATE TABLE `summarystatistika` (
  `id` int(11) NOT NULL,
  `korisnikSum` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `ukupnaPrijedjenaUdaljenost` double NOT NULL,
  `prosjecnaBrzina` double NOT NULL
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
  `brzinaUkm` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `walking`
--

INSERT INTO `walking` (`id`, `udaljenost`, `vrijemeAktivnosti`, `koraci`, `brzinaUkm`, `email`, `datum`) VALUES
(10, 14.825276927842276, '0:24', 1, 0.000011867816945118697, 'jbosnjak3@gmail.com', '2018-03-13 08:17:19'),
(11, 517.6331906485199, '3:23', 11, 3.118272232822409, 'jbosnjak3@gmail.com', '2018-03-13 10:34:19'),
(12, 3558.2374673010036, '1:34', 1, 0, 'jbosnjak3@gmail.com', '2018-03-13 17:34:02'),
(13, 3558.2374673010036, '1:22', 1, 0, 'jbosnjak3@gmail.com', '2018-03-13 17:33:50'),
(14, 3558.2374673010036, '1:49', 1, 0, 'jbosnjak3@gmail.com', '2018-03-13 17:34:16'),
(15, 0, '0:28', 7, 0, 'jbosnjak3@gmail.com', '2018-03-14 16:44:51'),
(16, 0, '0:05', 1, 0, 'jbosnjak3@gmail.com', '2018-03-14 16:55:24'),
(17, 0, '0:06', 1, 0, 'jbosnjak3@gmail.com', '2018-03-14 16:55:25'),
(18, 0, '0:00', 1, 0, 'jbosnjak3@gmail.com', '2018-03-14 17:35:29'),
(19, 92.07574082783286, '0:47', 1, 0, 'jbosnjak3@gmail.com', '2018-03-14 23:04:51'),
(20, 92.07574082783286, '0:51', 1, 0, 'jbosnjak3@gmail.com', '2018-03-14 23:04:55'),
(21, 111.19492664455873, '0:44', 1, 15, 'jbosnjak3@gmail.com', '2018-03-15 00:18:10'),
(22, 111.19492664455873, '1:01', 1, 15, 'jbosnjak3@gmail.com', '2018-03-15 00:18:28'),
(23, 0, '0:12', 1, 7, 'jbosnjak3@gmail.com', '2018-03-15 10:10:08'),
(24, 0, '0:00', 1, 0, 'jbosnjak3@gmail.com', '2018-03-15 10:11:03'),
(25, 106.39270311141873, '1:24', 1, 8, 'jbosnjak3@gmail.com', '2018-03-15 15:19:36'),
(26, 106.39270311141873, '1:45', 6, 8, 'jbosnjak3@gmail.com', '2018-03-15 15:19:56'),
(27, 106.39270311141873, '1:48', 6, 8, 'jbosnjak3@gmail.com', '2018-03-15 15:19:59');

-- --------------------------------------------------------

--
-- Table structure for table `walkingstatistika`
--

CREATE TABLE `walkingstatistika` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` int(11) NOT NULL,
  `period` datetime NOT NULL,
  `ukupanBrojKoraka` int(11) NOT NULL
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
-- Indexes for table `bikingstatistika`
--
ALTER TABLE `bikingstatistika`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bikeKorisnik` (`korisnikBike`);

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
-- Indexes for table `runningstatistika`
--
ALTER TABLE `runningstatistika`
  ADD PRIMARY KEY (`id`),
  ADD KEY `korisnik` (`korisnikRun`);

--
-- Indexes for table `summarystatistika`
--
ALTER TABLE `summarystatistika`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sumKorisnik` (`korisnikSum`);

--
-- Indexes for table `walking`
--
ALTER TABLE `walking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`);

--
-- Indexes for table `walkingstatistika`
--
ALTER TABLE `walkingstatistika`
  ADD PRIMARY KEY (`id`),
  ADD KEY `korisnik` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `biking`
--
ALTER TABLE `biking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `bikingstatistika`
--
ALTER TABLE `bikingstatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `running`
--
ALTER TABLE `running`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `runningstatistika`
--
ALTER TABLE `runningstatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `summarystatistika`
--
ALTER TABLE `summarystatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `walking`
--
ALTER TABLE `walking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `walkingstatistika`
--
ALTER TABLE `walkingstatistika`
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
-- Constraints for table `bikingstatistika`
--
ALTER TABLE `bikingstatistika`
  ADD CONSTRAINT `korisnikBike` FOREIGN KEY (`korisnikBike`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `running`
--
ALTER TABLE `running`
  ADD CONSTRAINT `email` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `runningstatistika`
--
ALTER TABLE `runningstatistika`
  ADD CONSTRAINT `runKorisnik` FOREIGN KEY (`korisnikRun`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `summarystatistika`
--
ALTER TABLE `summarystatistika`
  ADD CONSTRAINT `korisnikSum` FOREIGN KEY (`korisnikSum`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `walking`
--
ALTER TABLE `walking`
  ADD CONSTRAINT `walking_ibfk_1` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `walkingstatistika`
--
ALTER TABLE `walkingstatistika`
  ADD CONSTRAINT `korisnik` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
