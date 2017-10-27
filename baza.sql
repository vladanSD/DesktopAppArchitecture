-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2017 at 10:57 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `baza`
--

-- --------------------------------------------------------

--
-- Table structure for table `koordinatori`
--

CREATE TABLE `koordinatori` (
  `koordinatorID` int(11) NOT NULL,
  `imePrezime` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `korisnickaLozinka` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `koordinatori`
--

INSERT INTO `koordinatori` (`koordinatorID`, `imePrezime`, `korisnickoIme`, `korisnickaLozinka`) VALUES
(1, 'Vladan Jovanovic', 'vlada', 'vlada');

-- --------------------------------------------------------

--
-- Table structure for table `radnesmene`
--

CREATE TABLE `radnesmene` (
  `radneSmeneID` int(11) NOT NULL,
  `datumRadneSmene` date NOT NULL,
  `vremeRadneSmene` int(11) NOT NULL,
  `zaposleniRb` int(11) NOT NULL,
  `koordinatorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `radnesmene`
--

INSERT INTO `radnesmene` (`radneSmeneID`, `datumRadneSmene`, `vremeRadneSmene`, `zaposleniRb`, `koordinatorID`) VALUES
(1, '2017-06-01', 3, 2, 1),
(2, '2017-07-01', 3, 2, 1),
(3, '2017-08-01', 3, 2, 1),
(4, '2017-06-01', 1, 3, 1),
(5, '2017-07-01', 1, 3, 1),
(6, '2017-08-01', 1, 3, 1),
(7, '2017-06-01', 1, 4, 1),
(8, '2017-07-01', 1, 4, 1),
(9, '2017-08-01', 1, 4, 1),
(10, '2017-06-01', 2, 5, 1),
(11, '2017-07-01', 3, 5, 1),
(12, '2017-08-01', 3, 5, 1),
(13, '2017-06-01', 2, 1, 1),
(14, '2017-07-01', 2, 1, 1),
(15, '2017-08-01', 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `stavkeugovora`
--

CREATE TABLE `stavkeugovora` (
  `ugovoriID` int(11) NOT NULL,
  `rbStavke` int(11) NOT NULL,
  `datumOd` date NOT NULL,
  `datumDo` date NOT NULL,
  `cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stavkeugovora`
--

INSERT INTO `stavkeugovora` (`ugovoriID`, `rbStavke`, `datumOd`, `datumDo`, `cena`) VALUES
(101, 1, '2017-05-29', '2017-06-29', 65000),
(102, 1, '2017-05-29', '2017-07-29', 180000),
(105, 1, '2017-05-29', '2017-06-04', 15000),
(105, 2, '2017-05-29', '2017-05-29', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `ugovori`
--

CREATE TABLE `ugovori` (
  `ugovoriID` int(11) NOT NULL,
  `nazivFirme` varchar(50) NOT NULL,
  `pib` varchar(50) NOT NULL,
  `opis` varchar(50) NOT NULL,
  `napomene` varchar(50) NOT NULL,
  `uslugeID` int(11) NOT NULL,
  `koordinatorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ugovori`
--

INSERT INTO `ugovori` (`ugovoriID`, `nazivFirme`, `pib`, `opis`, `napomene`, `uslugeID`, `koordinatorID`) VALUES
(101, 'Maximo Portal Doo', '111', 'IT Usluge', 'saradnik', 1, 1),
(102, 'Yamm', '10222', 'Turisticka Organizacija', 'novi korisnik', 2, 1),
(105, 'firma', '123', 'It kompanija', 'napomene', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usluge`
--

CREATE TABLE `usluge` (
  `uslugeID` int(11) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `sifraUsluge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usluge`
--

INSERT INTO `usluge` (`uslugeID`, `naziv`, `sifraUsluge`) VALUES
(1, 'Fizicko obezbedjenje', 1003),
(2, 'Tehnicko obezbedjenje', 1002);

-- --------------------------------------------------------

--
-- Table structure for table `zaposleni`
--

CREATE TABLE `zaposleni` (
  `zaposleniRb` int(11) NOT NULL,
  `imePrezimeZ` varchar(50) NOT NULL,
  `strucnaSprema` varchar(50) NOT NULL,
  `radniStaz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zaposleni`
--

INSERT INTO `zaposleni` (`zaposleniRb`, `imePrezimeZ`, `strucnaSprema`, `radniStaz`) VALUES
(1, 'Stojan Radosavljevic', 'sss', 15),
(2, 'Ivan Peric', 'vss', 12),
(3, 'Miroslav Mirosavljevic', 'vss', 24),
(4, 'Nenad Ostojic', 'sss', 7),
(5, 'Rade Tomic', 'vss', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `koordinatori`
--
ALTER TABLE `koordinatori`
  ADD PRIMARY KEY (`koordinatorID`);

--
-- Indexes for table `radnesmene`
--
ALTER TABLE `radnesmene`
  ADD PRIMARY KEY (`radneSmeneID`),
  ADD KEY `zaposleniRb` (`zaposleniRb`),
  ADD KEY `koordinatorID` (`koordinatorID`);

--
-- Indexes for table `stavkeugovora`
--
ALTER TABLE `stavkeugovora`
  ADD PRIMARY KEY (`ugovoriID`,`rbStavke`);

--
-- Indexes for table `ugovori`
--
ALTER TABLE `ugovori`
  ADD PRIMARY KEY (`ugovoriID`),
  ADD KEY `ugovori_ibfk_1` (`uslugeID`),
  ADD KEY `koordinatorID` (`koordinatorID`);

--
-- Indexes for table `usluge`
--
ALTER TABLE `usluge`
  ADD PRIMARY KEY (`uslugeID`);

--
-- Indexes for table `zaposleni`
--
ALTER TABLE `zaposleni`
  ADD PRIMARY KEY (`zaposleniRb`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `koordinatori`
--
ALTER TABLE `koordinatori`
  MODIFY `koordinatorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `radnesmene`
--
ALTER TABLE `radnesmene`
  MODIFY `radneSmeneID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `usluge`
--
ALTER TABLE `usluge`
  MODIFY `uslugeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `zaposleni`
--
ALTER TABLE `zaposleni`
  MODIFY `zaposleniRb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `radnesmene`
--
ALTER TABLE `radnesmene`
  ADD CONSTRAINT `radnesmene_ibfk_1` FOREIGN KEY (`zaposleniRb`) REFERENCES `zaposleni` (`zaposleniRb`) ON UPDATE CASCADE,
  ADD CONSTRAINT `radnesmene_ibfk_2` FOREIGN KEY (`koordinatorID`) REFERENCES `koordinatori` (`koordinatorID`) ON UPDATE CASCADE;

--
-- Constraints for table `stavkeugovora`
--
ALTER TABLE `stavkeugovora`
  ADD CONSTRAINT `stavkeugovora_ibfk_1` FOREIGN KEY (`ugovoriID`) REFERENCES `ugovori` (`ugovoriID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ugovori`
--
ALTER TABLE `ugovori`
  ADD CONSTRAINT `ugovori_ibfk_1` FOREIGN KEY (`uslugeID`) REFERENCES `usluge` (`uslugeID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ugovori_ibfk_2` FOREIGN KEY (`koordinatorID`) REFERENCES `koordinatori` (`koordinatorID`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
