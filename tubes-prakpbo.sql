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

-- Dumping data for table tubes-prakpbo.admin: ~1 rows (approximately)
REPLACE INTO `admin` (`id_admin`, `nama_admin`, `email`, `password`, `role`) VALUES
	(1, 'Admin Utama', 'admin@fluxbin.com', 'admin123', 'Admin');

-- Dumping data for table tubes-prakpbo.jenis_sampah: ~3 rows (approximately)
REPLACE INTO `jenis_sampah` (`id_jenis`, `nama_jenis`) VALUES
	(2, 'Sampah Anorganik'),
	(3, 'Sampah B3'),
	(1, 'Sampah Organik');

-- Dumping data for table tubes-prakpbo.keluarga: ~4 rows (approximately)
REPLACE INTO `keluarga` (`no_kk`, `nama_kepala`, `jumlah_anggota`, `alamat`, `no_telp`, `tanggal_lahir`, `email`, `password`, `role`) VALUES
	('327300100001', 'Budi Santoso', 4, 'Jl. Mawar No. 1 RT 01 RW 02', '081234567890', NULL, 'budi@gmail.com', 'budi123', 'Keluarga'),
	('327300100002', 'Siti Aminah', 3, 'Jl. Melati No. 2 RT 01 RW 02', '081234567891', NULL, 'siti@gmail.com', 'siti123', 'Keluarga'),
	('327300100003', 'Mila Siti Nabila', 3, 'Jl. Cendana No. 12 RT 03 RW 05', '081234567892', NULL, 'mila@gmail.com', 'mila123', 'Keluarga'),
	('327300100004', 'Ahmad Fauzi', 5, 'Jl. Kenanga No. 5 RT 02 RW 03', '081234567893', NULL, 'ahmad@gmail.com', 'ahmad123', 'Keluarga');

-- Dumping data for table tubes-prakpbo.pegawai: ~1 rows (approximately)
REPLACE INTO `pegawai` (`id_pegawai`, `nama_pegawai`, `password`, `role`) VALUES
	(1, 'Petugas Lapangan', 'petugas123', 'Pegawai');

-- Dumping data for table tubes-prakpbo.sampah_anorganik: ~1 rows (approximately)
REPLACE INTO `sampah_anorganik` (`id_jenis`) VALUES
	(2);

-- Dumping data for table tubes-prakpbo.sampah_b3: ~1 rows (approximately)
REPLACE INTO `sampah_b3` (`id_jenis`) VALUES
	(3);

-- Dumping data for table tubes-prakpbo.sampah_masuk: ~5 rows (approximately)
REPLACE INTO `sampah_masuk` (`no_sampah`, `kategori`, `jumlah_masuk`, `no_kk`, `tanggal_masuk`) VALUES
	(1, 1, 6, '327300100001', '2026-05-29 19:34:07'),
	(2, 2, 3, '327300100001', '2026-05-29 19:34:07'),
	(3, 1, 4, '327300100003', '2026-05-29 19:34:07'),
	(4, 2, 3, '327300100003', '2026-05-29 19:34:07'),
	(5, 3, 1, '327300100003', '2026-05-29 19:34:07');

-- Dumping data for table tubes-prakpbo.sampah_organik: ~1 rows (approximately)
REPLACE INTO `sampah_organik` (`id_jenis`) VALUES
	(1);

-- Dumping data for table tubes-prakpbo.transaksi_sampah: ~3 rows (approximately)
REPLACE INTO `transaksi_sampah` (`no_transaksi`, `no_kk`, `tanggal_bayar`, `status`, `created_at`) VALUES
	('TRX/20260501/001', '327300100001', NULL, 'sudah_bayar', '2026-05-29 19:34:07'),
	('TRX/20260501/002', '327300100002', NULL, 'belum_bayar', '2026-05-29 19:34:07'),
	('TRX/20260501/003', '327300100003', NULL, 'belum_bayar', '2026-05-29 19:34:07');

-- Dumping data for table tubes-prakpbo.users: ~1 rows (approximately)
REPLACE INTO `users` (`dtype`, `id`, `alamat`, `anggota`, `email`, `kk`, `nama`, `password`) VALUES
	('User', 1, 'Padalarang', 8, 'mila@gmail.com', '327645382739172', 'Mila Siti Nabila', 'poiuyt');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
