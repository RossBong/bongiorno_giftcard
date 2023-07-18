-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 12, 2023 alle 18:07
-- Versione del server: 10.4.20-MariaDB
-- Versione PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bongiorno_giftcard_db`
--
CREATE DATABASE IF NOT EXISTS `bongiorno_giftcard_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bongiorno_giftcard_db`;

-- --------------------------------------------------------

--
-- Struttura della tabella `carte`
--

CREATE TABLE `carte` (
  `id_carta` int(11) NOT NULL,
  `credito` float NOT NULL,
  `stato` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `carte`
--

INSERT INTO `carte` (`id_carta`, `credito`, `stato`) VALUES
(1, 80, 'attiva'),
(2, 10, 'attiva'),
(3, 120, 'attiva'),
(4, 30, 'non attiva'),
(5, 65, 'attiva'),
(6, 40, 'non attiva');

-- --------------------------------------------------------

--
-- Struttura della tabella `transazioni`
--

CREATE TABLE `transazioni` (
  `id_transazione` int(11) NOT NULL,
  `cod_utente` int(11) NOT NULL,
  `data` date NOT NULL,
  `importo` float NOT NULL,
  `cod_carta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `transazioni`
--

INSERT INTO `transazioni` (`id_transazione`, `cod_utente`, `data`, `importo`, `cod_carta`) VALUES
(58, 1, '2023-07-12', 140, 3),
(59, 1, '2023-07-12', 50, 4),
(60, 1, '2023-07-12', 70, 5),
(61, 2, '2023-07-12', -25, 1),
(62, 2, '2023-07-12', -15, 4),
(63, 2, '2023-07-12', -20.8, 3),
(64, 3, '2023-07-12', -5, 1),
(65, 3, '2023-07-12', -9.2, 3),
(70, 3, '2023-07-12', -10, 1),
(71, 3, '2023-07-12', -15, 4),
(72, 3, '2023-07-12', -25, 5),
(73, 3, '2023-07-12', 10, 5),
(74, 1, '2023-07-12', 10, 1),
(75, 1, '2023-07-12', 10, 1);

--
-- Trigger `transazioni`
--
DELIMITER $$
CREATE TRIGGER `updatecarta` AFTER INSERT ON `transazioni` FOR EACH ROW UPDATE carte
    SET carte.credito = carte.credito + new.importo 
    WHERE carte.id_carta=new.cod_carta
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `email`, `name`, `password`, `ruolo`) VALUES
(1, 'admin@giftcard.it', 'Rosario Bongiorno', '$2a$10$Q3Zytycheh52Q.8tEJGS1OIF9nKnjc39wJ0aV7oclU4Vo9dt46jfa', 'ADMIN'),
(2, 'negoziante1@giftcard.it', 'Giuseppe Rossi', '$2a$12$C7rcn.8ia6/Go1O8ELMtEe1A0PRAwI2INmvqZLVPKjrK2XMmdHIVa', 'NEGOZIANTE'),
(3, 'negoziante2@giftcard.it', 'Giovanni Alaimo', '$2a$12$Ww7vUuw7Ry.EgkdbXeEKUu6zbdL0gT/ghx7Ag5kyUdKba1XODxcJC', 'NEGOZIANTE');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `carte`
--
ALTER TABLE `carte`
  ADD PRIMARY KEY (`id_carta`);

--
-- Indici per le tabelle `transazioni`
--
ALTER TABLE `transazioni`
  ADD PRIMARY KEY (`id_transazione`),
  ADD KEY `FKd2wubcem3kg0lolceqbb1jroy` (`cod_carta`),
  ADD KEY `FKc5vv6jk6f5qad99axbbh580fm` (`cod_utente`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9b90mk1nolf3ou90p42a93tjo` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `carte`
--
ALTER TABLE `carte`
  MODIFY `id_carta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `transazioni`
--
ALTER TABLE `transazioni`
  MODIFY `id_transazione` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `transazioni`
--
ALTER TABLE `transazioni`
  ADD CONSTRAINT `FKc5vv6jk6f5qad99axbbh580fm` FOREIGN KEY (`cod_utente`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `FKd2wubcem3kg0lolceqbb1jroy` FOREIGN KEY (`cod_carta`) REFERENCES `carte` (`id_carta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
