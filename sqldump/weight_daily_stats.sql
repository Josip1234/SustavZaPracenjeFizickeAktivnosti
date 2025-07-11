-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2025 at 09:42 AM
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
(15, '2025-07-11 09:35:19', 90.8, 0.6, 'falling');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `weight_daily_stats`
--
ALTER TABLE `weight_daily_stats`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `weight_daily_stats`
--
ALTER TABLE `weight_daily_stats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
