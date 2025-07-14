-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2025 at 12:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `bikingstatistika`
--

CREATE TABLE `bikingstatistika` (
  `id` int(11) NOT NULL,
  `korisnikBike` varchar(255) NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` double NOT NULL,
  `period` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `bikingstatistika`
--

INSERT INTO `bikingstatistika` (`id`, `korisnikBike`, `ukupnaUdaljenost`, `ukupnoVrijemeAktivnosti`, `prosjecnaBrzinaUkm`, `period`) VALUES
(1, 'jbosnjak3@gmail.com', 7.096578215918581, 34979, 11, '2018-03-17 08:15:23'),
(2, 'jbosnjak3@gmail.com', 7.096578215918581, 34979, 11, '2018-03-17 08:15:23'),
(3, 'jbosnjak3@gmail.com', 7.096578215918581, 34979, 11, '2018-03-17 08:15:23'),
(4, 'jbosnjak3@gmail.com', 7.096578215918581, 34979, 11, '2018-03-17 08:15:23'),
(5, 'jbosnjak3@gmail.com', 7.096578215918581, 34979, 11, '2018-03-17 08:15:23'),
(6, 'jbosnjak3@gmail.com', 8.869614514564777, 90363, 8, '2018-03-18 09:11:36');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `OIB` varchar(11) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `spol` varchar(1) NOT NULL,
  `datumr` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `sifra` varchar(255) NOT NULL
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
-- Table structure for table `runningstatistika`
--

CREATE TABLE `runningstatistika` (
  `id` int(11) NOT NULL,
  `korisnikRun` varchar(255) NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` double NOT NULL,
  `period` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `runningstatistika`
--

INSERT INTO `runningstatistika` (`id`, `korisnikRun`, `ukupnaUdaljenost`, `ukupnoVrijemeAktivnosti`, `prosjecnaBrzinaUkm`, `period`) VALUES
(1, 'jbosnjak3@gmail.com', 3.550150988612203, 32949, 6, '2018-03-17 08:14:45'),
(2, 'jbosnjak3@gmail.com', 3.550150988612203, 32949, 6, '2018-03-17 08:14:45'),
(3, 'jbosnjak3@gmail.com', 3.550150988612203, 32949, 6, '2018-03-17 08:14:45'),
(4, 'jbosnjak3@gmail.com', 3.550150988612203, 32949, 6, '2018-03-17 08:14:45'),
(5, 'jbosnjak3@gmail.com', 0, 3501, 0, '2018-03-17 20:12:59'),
(6, 'jbosnjak3@gmail.com', 3.549973667250756, 41423, 13.5, '2018-03-18 09:09:58');

-- --------------------------------------------------------

--
-- Table structure for table `summarystatistika`
--

CREATE TABLE `summarystatistika` (
  `id` int(11) NOT NULL,
  `korisnikSum` varchar(255) NOT NULL,
  `ukupanBrojKoraka` int(11) NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `ukupnaPrijedjenaUdaljenost` double NOT NULL,
  `prosjecnaBrzina` double NOT NULL,
  `datum` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `summarystatistika`
--

INSERT INTO `summarystatistika` (`id`, `korisnikSum`, `ukupanBrojKoraka`, `ukupnoVrijemeAktivnosti`, `ukupnaPrijedjenaUdaljenost`, `prosjecnaBrzina`, `datum`) VALUES
(1, 'jbosnjak3@gmail.com', 3, 123821, 12.423489251775166, 6.4444444444444455, '2018-03-17 13:24:09'),
(4, 'jbosnjak3@gmail.com', 3, 123821, 12.423489251775166, 6.4444444444444455, '2018-03-17 15:17:11'),
(5, 'jbosnjak3@gmail.com', 1, 87022, 10.646729204530784, 5.666666666666667, '2018-03-17 15:19:18'),
(6, 'jbosnjak3@gmail.com', 1, 87022, 10.646729204530784, 5.666666666666667, '2018-03-17 20:07:25'),
(7, 'jbosnjak3@gmail.com', 1, 74798, 10.646729204530784, 5.666666666666667, '2018-03-17 20:08:46'),
(9, 'jbosnjak3@gmail.com', 1, 33755, 0, 0, '2018-03-17 20:12:12'),
(10, 'jbosnjak3@gmail.com', 4, 14497, 0, 0, '2018-03-17 20:18:45'),
(11, 'jbosnjak3@gmail.com', 4, 14497, 0, 0, '2018-03-17 20:18:57'),
(12, 'jbosnjak3@gmail.com', 4, 14497, 0, 0, '2018-03-17 20:19:10'),
(13, 'jbosnjak3@gmail.com', 1, 5979, 0, 0, '2018-03-17 20:20:18'),
(14, 'jbosnjak3@gmail.com', 3, 193551, 14.196348229059916, 8.5, '2018-03-18 09:11:37');

-- --------------------------------------------------------

--
-- Table structure for table `walkingstatistika`
--

CREATE TABLE `walkingstatistika` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ukupnaUdaljenost` double NOT NULL,
  `ukupnoVrijemeAktivnosti` double NOT NULL,
  `prosjecnaBrzinaUkm` int(11) NOT NULL,
  `period` datetime NOT NULL,
  `ukupanBrojKoraka` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `walkingstatistika`
--

INSERT INTO `walkingstatistika` (`id`, `email`, `ukupnaUdaljenost`, `ukupnoVrijemeAktivnosti`, `prosjecnaBrzinaUkm`, `period`, `ukupanBrojKoraka`) VALUES
(1, 'jbosnjak3@gmail.com', 1.7767600472443834, 55893, 2, '2018-03-17 08:14:10', 3),
(2, 'jbosnjak3@gmail.com', 0, 19094, 0, '2018-03-17 15:19:16', 1),
(3, 'jbosnjak3@gmail.com', 0, 19094, 0, '2018-03-17 15:19:16', 1),
(4, 'jbosnjak3@gmail.com', 0, 6870, 0, '2018-03-17 20:08:45', 1),
(5, 'jbosnjak3@gmail.com', 0, 33755, 0, '2018-03-17 20:12:00', 1),
(6, 'jbosnjak3@gmail.com', 0, 33755, 0, '2018-03-17 20:12:00', 1),
(7, 'jbosnjak3@gmail.com', 0, 4161, 0, '2018-03-17 20:17:31', 1),
(8, 'jbosnjak3@gmail.com', 0, 14497, 0, '2018-03-17 20:18:44', 4),
(9, 'jbosnjak3@gmail.com', 0, 14497, 0, '2018-03-17 20:18:44', 4),
(10, 'jbosnjak3@gmail.com', 0, 14497, 0, '2018-03-17 20:18:44', 4),
(11, 'jbosnjak3@gmail.com', 0, 9027, 0, '2018-03-17 20:20:06', 7),
(12, 'jbosnjak3@gmail.com', 0, 5979, 0, '2018-03-17 20:20:17', 1),
(13, 'jbosnjak3@gmail.com', 1.7767600472443834, 61765, 4, '2018-03-18 09:09:14', 3);

-- --------------------------------------------------------

--
-- Table structure for table `weight_daily_stats`
--

CREATE TABLE `weight_daily_stats` (
  `id` int(11) NOT NULL,
  `date_time` datetime NOT NULL DEFAULT current_timestamp(),
  `weight` float NOT NULL,
  `difference` double NOT NULL,
  `trend` enum('growing','falling','neutral') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `weight_daily_stats`
--

INSERT INTO `weight_daily_stats` (`id`, `date_time`, `weight`, `difference`, `trend`) VALUES
(1, '2025-07-04 21:02:00', 91.3, 0, 'neutral'),
(2, '2025-07-05 08:35:00', 90.7, 0.6, 'falling'),
(3, '2025-07-05 15:39:00', 91, 0.3, 'growing'),
(4, '2025-07-05 23:04:00', 91, 0, 'neutral'),
(5, '2025-07-06 07:56:00', 90.1, 0.9, 'falling'),
(6, '2025-07-06 09:54:00', 89.8, 0.3, 'falling'),
(7, '2025-07-06 19:21:22', 91.3, 1.5, 'growing'),
(8, '2025-07-07 06:48:55', 91.2, 0.1, 'falling'),
(9, '2025-07-08 07:35:10', 91.1, 0.1, 'falling'),
(10, '2025-07-08 09:36:31', 90.5, 0.6, 'falling'),
(11, '2025-07-08 13:31:02', 91.3, 0.8, 'growing'),
(12, '2025-07-09 07:39:39', 91.6, 0.3, 'growing'),
(13, '2025-07-09 21:56:01', 92.4, 0.8, 'growing'),
(14, '2025-07-10 12:51:05', 91.4, 1, 'falling'),
(15, '2025-07-11 09:35:19', 90.8, 0.6, 'falling'),
(16, '2025-07-12 13:20:48', 90.9, 0.1, 'growing'),
(17, '2025-07-12 19:58:10', 91.3, 0.4, 'growing'),
(18, '2025-07-13 07:55:56', 90.6, 0.7, 'falling'),
(19, '2025-07-14 09:51:03', 90, 0.6, 'falling'),
(20, '2025-07-14 12:55:50', 90.8, 0.8, 'growing');

--
-- Indexes for dumped tables
--

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
-- Indexes for table `walkingstatistika`
--
ALTER TABLE `walkingstatistika`
  ADD PRIMARY KEY (`id`),
  ADD KEY `korisnik` (`email`);

--
-- Indexes for table `weight_daily_stats`
--
ALTER TABLE `weight_daily_stats`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bikingstatistika`
--
ALTER TABLE `bikingstatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `runningstatistika`
--
ALTER TABLE `runningstatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `summarystatistika`
--
ALTER TABLE `summarystatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `walkingstatistika`
--
ALTER TABLE `walkingstatistika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `weight_daily_stats`
--
ALTER TABLE `weight_daily_stats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bikingstatistika`
--
ALTER TABLE `bikingstatistika`
  ADD CONSTRAINT `korisnikBike` FOREIGN KEY (`korisnikBike`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Constraints for table `walkingstatistika`
--
ALTER TABLE `walkingstatistika`
  ADD CONSTRAINT `korisnik` FOREIGN KEY (`email`) REFERENCES `registration` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
