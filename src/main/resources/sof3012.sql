create database PolyOE_Lab;
use PolyOE_Lab;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    Id NVARCHAR(20) NOT NULL PRIMARY KEY,
    Password NVARCHAR(50) NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    Admin BIT NOT NULL
);

DROP TABLE IF EXISTS videos;
CREATE TABLE videos (
    Id CHARACTER(11) NOT NULL PRIMARY KEY,
    Title NVARCHAR(50) NOT NULL,
    Poster NVARCHAR(50),
    Description NVARCHAR(255),
    Active BIT NOT NULL,
    Views INT NOT NULL
);

DROP TABLE IF EXISTS favorites;
CREATE TABLE favorites (
    Id BIGINT NOT NULL PRIMARY KEY,
    VideoId CHARACTER(11) NOT NULL,
    UserId NVARCHAR(20) NOT NULL,
    LikeDate DATE NOT NULL,
    FOREIGN KEY (VideoId) REFERENCES videos(Id) ON DELETE CASCADE,
    FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS share;
CREATE TABLE share (
   Id BIGINT NOT NULL PRIMARY KEY,
   VideoId CHARACTER(11) NOT NULL,
   UserId NVARCHAR(20) NOT NULL,
   Emails NVARCHAR(20),
   ShareDate DATE NOT NULL,
   FOREIGN KEY (VideoId) REFERENCES videos(Id) ON DELETE CASCADE,
   FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE
);

INSERT INTO users (Id, Password, Fullname, Email, Admin) VALUES
    ('U01', 'password01', 'Nguyen Van A', 'vana@fpt.edu.vn', false),
    ('U02', 'password02', 'Tran Thi B', 'tranb@fpt.edu.vn', false),
    ('U03', 'password03', 'Le Van C', 'vanc@gmail.com', true),
    ('U04', 'password04', 'Pham Thi D', 'phamd@fpt.edu.vn', false),
    ('U05', 'password05', 'Hoang Van E', 'hoange@fpt.edu.vn', false),
    ('U06', 'password06', 'Vu Thi F', 'vutf@yahoo.com', true),
    ('U07', 'password07', 'Ngo Van G', 'gngo@fpt.edu.vn', false),
    ('U08', 'password08', 'Dao Thi H', 'daoh@outlook.com', false),
    ('U09', 'password09', 'Nguyen Van I', 'vanin@fpt.edu.vn', false),
    ('U10', 'password10', 'Bui Thi J', 'buij@fpt.edu.vn', false),
    ('U11', 'password11', 'Dang Van K', 'dangvk@yahoo.com', false),
    ('U12', 'password12', 'Phan Thi L', 'phanl@fpt.edu.vn', true),
    ('U13', 'password13', 'Ho Thi M', 'hohm@fpt.edu.vn', false),
    ('U14', 'password14', 'Do Van N', 'don@fpt.edu.vn', false),
    ('U15', 'password15', 'Chu Thi O', 'chuto@gmail.com', true);

INSERT INTO videos (Id, Title, Poster, Description, Active, Views) VALUES
    ('VID001', 'Learning Java', 'poster1.jpg', 'Video về các khái niệm cơ bản trong Java', 1, 1500),
    ('VID002', 'Introduction to SQL', 'poster2.jpg', 'Hướng dẫn cơ bản về SQL', 1, 2300),
    ('VID003', 'HTML & CSS Basics', 'poster3.jpg', 'Khóa học HTML và CSS cho người mới bắt đầu', 1, 1800),
    ('VID004', 'JavaScript Essentials', 'poster4.jpg', 'Video về JavaScript cho người mới bắt đầu', 1, 2100),
    ('VID005', 'Understanding MVC', 'poster5.jpg', 'Video giới thiệu về mô hình MVC', 1, 1200),
    ('VID006', 'Advanced Java', 'poster6.jpg', 'Khóa học về các khái niệm nâng cao trong Java', 1, 1600),
    ('VID007', 'Docker Basics', 'poster7.jpg', 'Hướng dẫn cơ bản về Docker', 1, 1100),
    ('VID008', 'Angular for Beginners', 'poster8.jpg', 'Giới thiệu Angular từ A đến Z', 1, 900),
    ('VID009', 'Spring Boot Introduction', 'poster9.jpg', 'Học Spring Boot cơ bản', 1, 800),
    ('VID010', 'Database Optimization', 'poster10.jpg', 'Tối ưu hóa cơ sở dữ liệu', 1, 1400);

INSERT INTO favorites (Id, VideoId, UserId, LikeDate) VALUES
    (1, 'VID001', 'U01', '2024-01-15'),
    (2, 'VID002', 'U01', '2024-02-10'),
    (3, 'VID003', 'U02', '2024-03-05'),
    (4, 'VID004', 'U03', '2024-04-12'),
    (5, 'VID005', 'U03', '2024-05-08'),
    (6, 'VID006', 'U04', '2024-06-20'),
    (7, 'VID007', 'U05', '2024-07-14'),
    (8, 'VID008', 'U06', '2024-08-22'),
    (9, 'VID009', 'U07', '2024-09-17'),
    (10, 'VID010', 'U08', '2024-10-25'),
    (11, 'VID001', 'U09', '2024-11-01'),
    (12, 'VID002', 'U10', '2024-11-01'),
    (13, 'VID003', 'U11', '2024-11-01'),
    (14, 'VID004', 'U12', '2024-11-01'),
    (15, 'VID005', 'U13', '2024-11-01');

INSERT INTO share (Id, VideoId, UserId, Emails, ShareDate) VALUES
    (1, 'VID001', 'U01', 'vana@fpt.edu.vn', '2024-01-20'),
    (2, 'VID002', 'U02', 'tranb@fpt.edu.vn', '2024-02-15'),
    (3, 'VID003', 'U03', 'vanc@gmail.com', '2024-03-10'),
    (4, 'VID004', 'U04', 'phamd@fpt.edu.vn', '2024-04-05'),
    (5, 'VID005', 'U05', 'hoange@fpt.edu.vn', '2024-05-01'),
    (6, 'VID006', 'U06', 'vutf@yahoo.com', '2024-06-18'),
    (7, 'VID007', 'U07', 'gngo@fpt.edu.vn', '2024-07-12'),
    (8, 'VID008', 'U08', 'daoh@outlook.com', '2024-08-24'),
    (9, 'VID009', 'U09', 'vanin@fpt.edu.vn', '2024-09-14'),
    (10, 'VID010', 'U10', 'buij@fpt.edu.vn', '2024-10-10'),
    (11, 'VID001', 'U11', 'dangvk@yahoo.com', '2024-11-01'),
    (12, 'VID002', 'U12', 'phanl@fpt.edu.vn', '2024-11-02'),
    (13, 'VID003', 'U13', 'hohm@fpt.edu.vn', '2024-11-03'),
    (14, 'VID004', 'U14', 'don@fpt.edu.vn', '2024-11-04'),
    (15, 'VID005', 'U15', 'chuto@gmail.com', '2024-11-05');
