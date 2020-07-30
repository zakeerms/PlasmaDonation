-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 10, 2020 at 04:30 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";
CREATE TABLE `posts` (
  `id` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT current_timestamp(),
  `message` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `number` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `posts` (`id`, `message`, `url`, `number`) VALUES
('1', 't', 'https://plasma-donation.000webhostapp.com/uploads/Daiper_Camera.jpg', '8309991236'),
('2020-07-10 15:56:28', 'Need urgent help', 'https://plasma-donation.000webhostapp.com/uploads/test.jpg', '2142361664');



CREATE TABLE `user_table` (
  `id` int(100) NOT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `city` text COLLATE utf8_unicode_ci NOT NULL,
  `blood_group` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL,
  `number` text COLLATE utf8_unicode_ci NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT INTO `user_table` (`id`, `name`, `city`, `blood_group`, `password`, `number`, `timestamp`) VALUES
(1, 'Dhvani', 'Portland', 'A+', 'DhvaniDhvani', '9713128282', '2020-07-03 22:35:04'),
(2, 'priyanka', 'houston', 'AB+', 'priya225', '9794223153', '2020-07-04 18:25:11'),
(3, 'Zakir', 'Irving', 'O-', 'lascolinas', '2142361664', '2020-07-05 22:15:37'),
(4, 'shivpriya', 'Dallas', 'A-', '12345', '2142361994', '2020-07-09 21:10:50'),
--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
