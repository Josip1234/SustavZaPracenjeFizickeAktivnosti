-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2018 at 11:49 PM
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
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `biking`
--

INSERT INTO `biking` (`id`, `vrijemeAktivnosti`, `brzinaUkm`, `udaljenost`, `email`, `datum`) VALUES
(1, '0:47', 0, 18.443267355293663, 'jbosnjak3@gmail.com', '2018-03-05'),
(2, '0:59', 0, 18.443267355293663, 'jbosnjak3@gmail.com', '2018-03-05'),
(3, '0:59', 0, 18.443267355293663, 'jbosnjak3@gmail.com', '2018-03-05');

-- --------------------------------------------------------

--
-- Table structure for table `bikingstatistika`
--

CREATE TABLE `bikingstatistika` (
  `id` int(11) NOT NULL,
  `korisnikBike` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `prosjecnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnoVrijemeAktivnosti` double NOT NULL,
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
  `udaljenost` double NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `running`
--

INSERT INTO `running` (`id`, `vrijemeAktivnosti`, `brzinaUkm`, `udaljenost`, `email`, `datum`) VALUES
(1, '0:31', 0, 0.010933943352007515, 'jbosnjak3@gmail.com', '2018-03-05');

-- --------------------------------------------------------

--
-- Table structure for table `runningstatistika`
--

CREATE TABLE `runningstatistika` (
  `id` int(11) NOT NULL,
  `korisnikRun` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `prosjecnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnoVrijemeAktivnosti` double NOT NULL,
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
(1, 12, '1:75', 20, 12.56, 'jbosnjak3@gmail.com', '2018-03-02 00:21:15'),
(2, 0, '0:01', 0, 0, 'jbosnjak3@gmail.com', '2018-03-01 23:33:57'),
(3, 1.5602117239441733, '0:47', 0, 0, 'jbosnjak3@gmail.com', '2018-03-02 09:18:44'),
(4, 0, '0:00', 0, 0, 'jbosnjak3@gmail.com', '2018-03-02 09:20:36'),
(5, 0, '1:39', 0, 0, 'jbosnjak3@gmail.com', NULL),
(6, 0, '2:02', 0, 0, 'jbosnjak3@gmail.com', NULL),
(7, 0, '0:00', 0, 0, 'jbosnjak3@gmail.com', '2018-03-02 16:27:02'),
(8, 0, '0:00', 0, 0, 'jbosnjak3@gmail.com', '2018-03-02 16:27:24'),
(9, 4600.179347937722, '0:00', 0, 0, 'he@gmail.com ', '2018-03-04 19:32:48'),
(10, 0, '0:20', 0, 0, 'jbosnjak3@gmail.com', NULL),
(11, 0.010933943352007515, '0:58', 0, 0, 'jbosnjak3@gmail.com', '2018-03-05 15:31:59'),
(12, 0, '0:00', 0, 0, 'jbosnjak3@gmail.com', '2018-03-05 22:37:56'),
(13, 0, '0:00', 0, 0, 'jbosnjak3@gmail.com', '2018-03-05 22:40:23'),
(14, 0, '0:00', 0, 0, 'jbosnjak3@gmail.com', '2018-03-05 22:40:24');

-- --------------------------------------------------------

--
-- Table structure for table `walkingstatistika`
--

CREATE TABLE `walkingstatistika` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `prosjecnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` int(11) NOT NULL,
  `period` datetime NOT NULL,
  `ukupanBrojKoraka` int(11) NOT NULL,
  `prosjecanBrojKoraka` double NOT NULL
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `bikingstatistika`
--
ALTER TABLE `bikingstatistika`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
