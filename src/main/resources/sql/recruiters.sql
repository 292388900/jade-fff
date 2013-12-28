-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 27, 2013 at 06:52 PM
-- Server version: 5.5.34
-- PHP Version: 5.3.10-1ubuntu3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `recruiters`
--

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE IF NOT EXISTS `bids` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vacancy_id` bigint(20) NOT NULL,
  `recruiter_id` bigint(20) NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vacancy_id` (`vacancy_id`),
  KEY `recruiter_id` (`recruiter_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `bids`
--

INSERT INTO `bids` (`id`, `vacancy_id`, `recruiter_id`, `message`) VALUES
(1, 1, 1, 'Условия такие условия, такие интересные условия'),
(2, 1, 2, 'Какие то другие условия.'),
(3, 1, 3, 'Условия Условия Условия Условия Условия Условия Условия Условия " +                 "Условия Условия Условия Условия Условия Условия Условия Условия Условия Условия'),
(4, 2, 1, 'Кто не согласен с условиями тот не прав. Условия такие хорошие'),
(5, 2, 2, 'Грех не отказаться от условий под дулом пистолета');

-- --------------------------------------------------------

--
-- Table structure for table `employers`
--

CREATE TABLE IF NOT EXISTS `employers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employers_1` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `employers`
--

INSERT INTO `employers` (`id`, `user_id`) VALUES
(1, 2),
(2, 5),
(3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `recruiters`
--

CREATE TABLE IF NOT EXISTS `recruiters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recruiters_1` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `recruiters`
--

INSERT INTO `recruiters` (`id`, `user_id`) VALUES
(1, 1),
(2, 3),
(3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `lastname`) VALUES
(1, 'recruiter', '123123', 'Павел', 'Петров'),
(2, 'employer', '123123', 'Артём', 'Иванов'),
(3, 'test1', 'test1', 'Иван', 'Петров'),
(4, 'test2', 'test2', 'Владимир', 'Потанин'),
(5, 'test3', 'test3', 'Алексей', 'Никонов'),
(6, 'test4', 'test4', 'Игорь', 'Голованов');

-- --------------------------------------------------------

--
-- Table structure for table `vacancies`
--

CREATE TABLE IF NOT EXISTS `vacancies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employer_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `salary` varchar(255) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiration_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `test_file` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employer_id` (`employer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `vacancies`
--

INSERT INTO `vacancies` (`id`, `employer_id`, `title`, `description`, `salary`, `creation_date`, `expiration_date`, `test_file`) VALUES
(1, 1, 'Лесоруб', 'Должен уметь рубить лес', '10тыс.-15тыс. руб.', '2013-12-27 10:27:56', '2014-01-08 17:00:00', '#'),
(2, 1, 'Сантехник', 'Не должен пить!', '20 000 руб', '2013-12-27 10:27:56', '2014-01-30 17:00:00', '#'),
(3, 1, 'Программист', 'Уметь программировать на С++', '100500 руб.', '2013-12-27 10:29:06', '2014-01-14 17:00:00', '#');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bids`
--
ALTER TABLE `bids`
  ADD CONSTRAINT `bids_ibfk_2` FOREIGN KEY (`recruiter_id`) REFERENCES `recruiters` (`id`),
  ADD CONSTRAINT `bids_ibfk_1` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancies` (`id`);

--
-- Constraints for table `employers`
--
ALTER TABLE `employers`
  ADD CONSTRAINT `fk_employers_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `recruiters`
--
ALTER TABLE `recruiters`
  ADD CONSTRAINT `fk_recruiters_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `vacancies`
--
ALTER TABLE `vacancies`
  ADD CONSTRAINT `vacancies_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employers` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;