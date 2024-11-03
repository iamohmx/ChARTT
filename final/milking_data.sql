-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 03, 2024 at 01:25 PM
-- Server version: 5.7.39
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `final`
--

-- --------------------------------------------------------

--
-- Table structure for table `milking_data`
--

CREATE TABLE `milking_data` (
  `id` bigint(20) NOT NULL,
  `milk_kg` float DEFAULT NULL,
  `cow_id` int(11) DEFAULT NULL,
  `dmy` date NOT NULL,
  `coop_id` int(11) DEFAULT NULL,
  `farm_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `milking_data`
--

INSERT INTO `milking_data` (`id`, `milk_kg`, `cow_id`, `dmy`, `coop_id`, `farm_id`) VALUES
(3, 15, 1, '2024-11-03', 1, 1),
(4, 20, 2, '2024-11-03', 2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `milking_data`
--
ALTER TABLE `milking_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhn05e4j6b0mivtsl61uibaece` (`cow_id`),
  ADD KEY `FKovabu4wme5v39mo3u9rk9s755` (`coop_id`),
  ADD KEY `FKbcni4qrlvk2hexcv3ybalt6tp` (`farm_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `milking_data`
--
ALTER TABLE `milking_data`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `milking_data`
--
ALTER TABLE `milking_data`
  ADD CONSTRAINT `FKbcni4qrlvk2hexcv3ybalt6tp` FOREIGN KEY (`farm_id`) REFERENCES `farms` (`id`),
  ADD CONSTRAINT `FKhn05e4j6b0mivtsl61uibaece` FOREIGN KEY (`cow_id`) REFERENCES `cows` (`id`),
  ADD CONSTRAINT `FKovabu4wme5v39mo3u9rk9s755` FOREIGN KEY (`coop_id`) REFERENCES `coops` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
