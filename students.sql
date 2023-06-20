-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 13, 2023 lúc 02:58 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `testschool`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `studentid` int(11) NOT NULL,
  `studentname` varchar(50) DEFAULT NULL,
  `studentdob` date DEFAULT NULL,
  `studentaddress` varchar(100) DEFAULT NULL,
  `studentnumber` varchar(20) DEFAULT NULL,
  `sessionyear` varchar(20) DEFAULT NULL,
  `studentstatus` varchar(20) DEFAULT NULL,
  `studentactivate` bit(1) DEFAULT NULL,
  `studentgender` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`studentid`, `studentname`, `studentdob`, `studentaddress`, `studentnumber`, `sessionyear`, `studentstatus`, `studentactivate`, `studentgender`) VALUES
(2000003, 'Truong Ngoc Man', '2008-09-20', 'Thu Duc city', '0123456879', NULL, 'Is studying', b'0', 'Male'),
(2000005, 'Truong Du Hi', '2008-12-16', 'HCM city', '0123456879', NULL, 'Is studying', b'1', 'Male'),
(2000006, 'Ngoc Anh Thuong', '2007-04-30', 'HCM city', '0123456879', NULL, 'Is studying', b'1', 'Male'),
(2000008, 'Cuc Tinh Y', '2007-07-08', 'Thu Duc city', '0123456879', NULL, 'Is studying', b'0', 'Male'),
(2000009, 'Tinh Man', '2007-08-22', 'Thu Duc city', '0123456879', NULL, 'Is studying', b'0', 'Female'),
(2000013, 'Hoang Duoc Su', '2006-11-22', 'HCM city', '0123456879', NULL, 'Is studying', b'1', 'Male'),
(2000015, 'Gia Hue Tan', '2006-03-29', 'Thu Duc city', '0123456879', NULL, 'Is studying', b'0', 'Male'),
(2000044, 'Duong Ngoc Anh', '2006-03-09', NULL, NULL, NULL, 'Is studying', b'0', 'Male'),
(2000048, 'Bieu Hoang Duong', '2023-05-02', NULL, NULL, NULL, 'Is studying', b'0', 'Male'),
(2000071, 'Nhật Duy', '2007-01-02', NULL, NULL, NULL, 'Is studying', b'0', 'Male'),
(2000072, 'Dinh Duong Ngoc Lan', '2004-07-23', NULL, NULL, NULL, 'Is studying', b'1', 'Female'),
(2000073, 'Pham Bang Bang', '2006-10-11', NULL, NULL, NULL, 'Graduated', b'1', 'Female'),
(2000074, 'Chau Kiet Luan', '2003-06-04', NULL, NULL, NULL, 'Is studying', b'1', 'Male'),
(2000075, 'Au Duong Chan Hoa', '2001-10-05', NULL, NULL, NULL, 'Graduated', b'1', 'Male'),
(2000076, 'Manh My Ky', '2006-08-17', NULL, NULL, NULL, 'Drop', b'1', 'Female'),
(2000077, 'Doan Uc Quyen', '2009-06-04', NULL, NULL, NULL, 'Drop', b'1', 'Female'),
(2000078, 'Lai My Van', '2005-01-12', NULL, NULL, NULL, 'Is studying', b'0', 'Female');

--
-- Bẫy `students`
--
DELIMITER $$
CREATE TRIGGER `trg_scores_student_add` AFTER INSERT ON `students` FOR EACH ROW INSERT INTO scores(studentid,subjectid,midscore,finalscore,averagescore) VALUES
((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'B01',0.0,0.0,0.0),
((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'C01',0.0,0.0,0.0),
((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'E01',0.0,0.0,0.0),
((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'P01',0.0,0.0,0.0),
((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'M01',0.0,0.0,0.0),
((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'L01',0.0,0.0,0.0)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_user_student_add` AFTER INSERT ON `students` FOR EACH ROW INSERT INTO Users(studentid,usertype,username,userpassword) values ((SELECT studentid FROM Students order by studentid DESC LIMIT 1),'STUDENT',(SELECT studentid FROM Students order by studentid DESC LIMIT 1),'12345678')
$$
DELIMITER ;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentid`),
  ADD KEY `sessionyear` (`sessionyear`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `students`
--
ALTER TABLE `students`
  MODIFY `studentid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2000080;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`sessionyear`) REFERENCES `sessionyear` (`sessionyear`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
