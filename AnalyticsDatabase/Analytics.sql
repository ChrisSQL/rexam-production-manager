-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 14, 2014 at 02:03 PM
-- Server version: 5.6.21
-- PHP Version: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `rexam`
--

-- --------------------------------------------------------

--
-- Table structure for table `Analytics`
--

CREATE TABLE `Analytics` (
  `Name` text NOT NULL,
  `TimesOpened` int(11) NOT NULL,
  `TimeStamp` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Analytics`
--

INSERT INTO `Analytics` (`Name`, `TimesOpened`, `TimeStamp`) VALUES
('ShellPressProduction', 11, '2014-11-14 10:46:04'),
('BalancerMaintenance', 0, '2014-11-14 12:37:58'),
('EndCounts', 96, '2014-11-13 08:59:08'),
('LinerAndShellsEntry', 366, '2014-11-13 08:59:04'),
('LinerDefects', 177, '2014-11-12 08:24:09'),
('PlantSpoilage', 41, '2014-11-10 15:21:09'),
('ProductionWeeklyReport', 14, '2014-11-10 15:21:14'),
('SpoilageByDay', 65, '2014-11-10 15:21:01'),
('GUI', 418, '2014-11-14 08:12:32'),
('Program', 421, '2014-11-14 08:12:32'),
('BalancerProduction', 86, '2014-11-13 09:36:24'),
('BalancerMaintenance', 24, '2014-11-04 09:37:58'),
('EHSStatutoryChecks', 125, '2014-11-12 13:07:19'),
('LexanFingerTracking', 3, '2014-10-07 08:27:55'),
('LineBalance', 19, '2014-11-10 15:19:22'),
('LinerMaintenance', 12, '2014-11-05 11:47:49'),
('LinerProduction', 137, '2014-11-13 09:36:28'),
('LinerSpoilage', 58, '2014-11-12 08:09:56'),
('MachineOEE', 29, '2014-11-10 15:18:14'),
('ScoreInsert', 135, '2014-11-05 19:57:32'),
('ShellPressMaintenance', 100, '2014-11-12 09:41:42'),
('StolleDowntime', 16, '2014-11-10 15:18:50'),
('StolleMaintenance', 14, '2014-11-05 20:32:51'),
('StolleProduction', 117, '2014-11-13 09:36:31'),
('StolleSpoilage', 32, '2014-11-12 08:09:56'),
('TimingBelt', 11, '2014-11-05 20:31:16'),
('TransferBelt', 15, '2014-11-10 15:12:31'),
('LinerEntryMenu', 23, '2014-11-12 08:08:27'),
('OtherEntryMenu', 20, '2014-11-10 15:29:07'),
('ShellPressEntryMenu', 13, '2014-11-12 08:08:28'),
('StolleEntryMenu', 69, '2014-11-12 14:32:09'),
('ParEntry', 82, '2014-11-13 08:59:12'),
('EmployeeAddressList', 336, '2014-11-13 09:29:35'),
('LSSPMActivityEntry', 15, '2014-11-10 15:07:29'),
('LinerDataEntryScreen', 64, '2014-11-12 08:42:39'),
('LinerUsageEntryScreen', 27, '2014-11-10 15:04:05'),
('MeetingQualityIssues', 19, '2014-11-10 15:08:45'),
('OptimeDataEntryScreen', 141, '2014-11-13 09:29:27'),
('ProductionMeeting', 22, '2014-11-10 15:08:12'),
('StolleDataEntryScreen', 42, '2014-11-12 13:09:08'),
('EmployeeOvertimeSystem', 4076, '2014-11-12 08:07:45'),
('DefectsList', 135, '2014-11-10 15:29:10'),
('Help', 48, '2014-11-13 09:30:48'),
('ShellType', 57, '2014-11-10 15:06:47');