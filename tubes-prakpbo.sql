-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for tubes-prakpbo
CREATE DATABASE IF NOT EXISTS `tubes-prakpbo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tubes-prakpbo`;

-- Dumping structure for table tubes-prakpbo.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` bigint NOT NULL AUTO_INCREMENT,
  `nama_admin` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.admin: ~1 rows (approximately)
REPLACE INTO `admin` (`id_admin`, `nama_admin`, `email`, `password`, `role`) VALUES
	(1, 'Admin Utama', 'admin@fluxbin.com', 'admin123', 'Admin');

-- Dumping structure for table tubes-prakpbo.jenis_sampah
CREATE TABLE IF NOT EXISTS `jenis_sampah` (
  `id_jenis` int NOT NULL AUTO_INCREMENT,
  `nama_jenis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_jenis`),
  UNIQUE KEY `nama_jenis` (`nama_jenis`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.jenis_sampah: ~3 rows (approximately)
REPLACE INTO `jenis_sampah` (`id_jenis`, `nama_jenis`) VALUES
	(2, 'Sampah Anorganik'),
	(3, 'Sampah B3'),
	(1, 'Sampah Organik');

-- Dumping structure for table tubes-prakpbo.keluarga
CREATE TABLE IF NOT EXISTS `keluarga` (
  `no_kk` varchar(50) NOT NULL,
  `nama_kepala` varchar(255) DEFAULT NULL,
  `jumlah_anggota` int DEFAULT '1',
  `alamat` varchar(255) DEFAULT NULL,
  `no_telp` varchar(255) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_kk`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.keluarga: ~4 rows (approximately)
REPLACE INTO `keluarga` (`no_kk`, `nama_kepala`, `jumlah_anggota`, `alamat`, `no_telp`, `tanggal_lahir`, `email`, `password`, `role`) VALUES
	('327300100001', 'Budi Santoso', 4, 'Jl. Mawar No. 1 RT 01 RW 02', '081234567890', NULL, 'budi@gmail.com', 'budi123', 'Keluarga'),
	('327300100002', 'Siti Aminah', 3, 'Jl. Melati No. 2 RT 01 RW 02', '081234567891', NULL, 'siti@gmail.com', 'siti123', 'Keluarga'),
	('327300100003', 'Mila Siti Nabila', 3, 'Jl. Cendana No. 12 RT 03 RW 05', '081234567892', NULL, 'mila@gmail.com', 'mila123', 'Keluarga'),
	('327300100004', 'Ahmad Fauzi', 5, 'Jl. Kenanga No. 5 RT 02 RW 03', '081234567893', NULL, 'ahmad@gmail.com', 'ahmad123', 'Keluarga');

-- Dumping structure for table tubes-prakpbo.pegawai
CREATE TABLE IF NOT EXISTS `pegawai` (
  `id_pegawai` bigint NOT NULL AUTO_INCREMENT,
  `nama_pegawai` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_pegawai`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.pegawai: ~1 rows (approximately)
REPLACE INTO `pegawai` (`id_pegawai`, `nama_pegawai`, `password`, `role`) VALUES
	(1, 'Petugas Lapangan', 'petugas123', 'Pegawai');

-- Dumping structure for table tubes-prakpbo.sampah_anorganik
CREATE TABLE IF NOT EXISTS `sampah_anorganik` (
  `id_jenis` int NOT NULL,
  PRIMARY KEY (`id_jenis`),
  CONSTRAINT `sampah_anorganik_ibfk_1` FOREIGN KEY (`id_jenis`) REFERENCES `jenis_sampah` (`id_jenis`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.sampah_anorganik: ~1 rows (approximately)
REPLACE INTO `sampah_anorganik` (`id_jenis`) VALUES
	(2);

-- Dumping structure for table tubes-prakpbo.sampah_b3
CREATE TABLE IF NOT EXISTS `sampah_b3` (
  `id_jenis` int NOT NULL,
  PRIMARY KEY (`id_jenis`),
  CONSTRAINT `sampah_b3_ibfk_1` FOREIGN KEY (`id_jenis`) REFERENCES `jenis_sampah` (`id_jenis`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.sampah_b3: ~1 rows (approximately)
REPLACE INTO `sampah_b3` (`id_jenis`) VALUES
	(3);

-- Dumping structure for table tubes-prakpbo.sampah_masuk
CREATE TABLE IF NOT EXISTS `sampah_masuk` (
  `no_sampah` int NOT NULL AUTO_INCREMENT,
  `kategori` int NOT NULL,
  `jumlah_masuk` int DEFAULT NULL,
  `no_kk` varchar(20) NOT NULL,
  `tanggal_masuk` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`no_sampah`),
  KEY `kategori` (`kategori`),
  KEY `no_kk` (`no_kk`),
  CONSTRAINT `sampah_masuk_ibfk_1` FOREIGN KEY (`kategori`) REFERENCES `jenis_sampah` (`id_jenis`) ON DELETE RESTRICT,
  CONSTRAINT `sampah_masuk_ibfk_2` FOREIGN KEY (`no_kk`) REFERENCES `keluarga` (`no_kk`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.sampah_masuk: ~9 rows (approximately)
REPLACE INTO `sampah_masuk` (`no_sampah`, `kategori`, `jumlah_masuk`, `no_kk`, `tanggal_masuk`) VALUES
	(1, 1, 6, '327300100001', '2026-05-29 19:34:07'),
	(2, 2, 3, '327300100001', '2026-05-29 19:34:07'),
	(3, 1, 4, '327300100003', '2026-05-29 19:34:07'),
	(4, 2, 3, '327300100003', '2026-05-29 19:34:07'),
	(5, 3, 1, '327300100003', '2026-05-29 19:34:07'),
	(6, 1, 6, '327300100002', '2026-05-29 16:29:46'),
	(7, 3, 6, '327300100001', '2026-05-29 23:40:12'),
	(8, 2, 2, '327300100002', '2026-05-30 12:21:49'),
	(9, 3, 3, '327300100004', '2026-06-04 11:56:44');

-- Dumping structure for table tubes-prakpbo.sampah_organik
CREATE TABLE IF NOT EXISTS `sampah_organik` (
  `id_jenis` int NOT NULL,
  PRIMARY KEY (`id_jenis`),
  CONSTRAINT `sampah_organik_ibfk_1` FOREIGN KEY (`id_jenis`) REFERENCES `jenis_sampah` (`id_jenis`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.sampah_organik: ~1 rows (approximately)
REPLACE INTO `sampah_organik` (`id_jenis`) VALUES
	(1);

-- Dumping structure for table tubes-prakpbo.transaksi_sampah
CREATE TABLE IF NOT EXISTS `transaksi_sampah` (
  `no_transaksi` varchar(255) NOT NULL,
  `no_kk` varchar(20) NOT NULL,
  `tanggal_bayar` datetime DEFAULT NULL,
  `status` enum('belum_bayar','sudah_bayar','diberhentikan') DEFAULT 'belum_bayar',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`no_transaksi`),
  KEY `no_kk` (`no_kk`),
  CONSTRAINT `transaksi_sampah_ibfk_1` FOREIGN KEY (`no_kk`) REFERENCES `keluarga` (`no_kk`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.transaksi_sampah: ~3 rows (approximately)
REPLACE INTO `transaksi_sampah` (`no_transaksi`, `no_kk`, `tanggal_bayar`, `status`, `created_at`) VALUES
	('TRX/20260501/001', '327300100001', '2026-05-30 12:57:32', 'sudah_bayar', '2026-05-29 19:34:07'),
	('TRX/20260501/002', '327300100002', '2026-05-30 12:57:33', 'belum_bayar', '2026-05-29 19:34:07'),
	('TRX/20260501/003', '327300100003', '2026-05-30 12:57:34', 'belum_bayar', '2026-05-29 19:34:07');

-- Dumping structure for table tubes-prakpbo.users
CREATE TABLE IF NOT EXISTS `users` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alamat` varchar(255) DEFAULT NULL,
  `anggota` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `kk` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `users_chk_1` CHECK ((`dtype` in (_utf8mb4'User',_utf8mb4'Admin',_utf8mb4'Pegawai')))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tubes-prakpbo.users: ~1 rows (approximately)
REPLACE INTO `users` (`dtype`, `id`, `alamat`, `anggota`, `email`, `kk`, `nama`, `password`) VALUES
	('User', 1, 'Padalarang', 8, 'mila@gmail.com', '327645382739172', 'Mila Siti Nabila', 'poiuyt');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
