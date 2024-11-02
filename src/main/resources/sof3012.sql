create database PolyOE;
use PolyOE;

drop database if exists users
create table users(
    Id nvarchar(20) not null primary key,
    Password nvarchar(50) not null,
    Fullname nvarchar(50) not null,
    Email nvarchar(50) not null,
    Admin bit not null
);

drop database if exists favorites
create table favorites(
    Id bigint not null primary key,
    VideoId character(11) not null,
    UserId nvarchar(20) not null,
    LikeDate date not null
);

drop database if exists videos
create table videos(
    Id character(11) not null primary key,
    Title nvarchar(50) not null,
    Poster nvarchar(50),
    [Description] nvarchar(MAX),
    Active bit not null,
    Views int not null
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

INSERT INTO videos (Id, Title, Poster, [Description], Active, Views) VALUES
    ('VID001', 'Learning Java', 'poster1.jpg', 'Video về các khái niệm cơ bản trong Java', true, 1500),
    ('VID002', 'Introduction to SQL', 'poster2.jpg', 'Hướng dẫn cơ bản về SQL', true, 2300),
    ('VID003', 'HTML & CSS Basics', 'poster3.jpg', 'Khóa học HTML và CSS cho người mới bắt đầu', true, 1800),
    ('VID004', 'JavaScript Essentials', 'poster4.jpg', 'Video về JavaScript cho người mới bắt đầu', true, 2100),
    ('VID005', 'Understanding MVC', 'poster5.jpg', 'Video giới thiệu về mô hình MVC', true, 1200),
    ('VID006', 'Advanced Java', 'poster6.jpg', 'Khóa học về các khái niệm nâng cao trong Java', true, 1600),
    ('VID007', 'Docker Basics', 'poster7.jpg', 'Hướng dẫn cơ bản về Docker', true, 1100),
    ('VID008', 'Angular for Beginners', 'poster8.jpg', 'Giới thiệu Angular từ A đến Z', true, 900),
    ('VID009', 'Spring Boot Introduction', 'poster9.jpg', 'Học Spring Boot cơ bản', true, 800),
    ('VID010', 'Database Optimization', 'poster10.jpg', 'Tối ưu hóa cơ sở dữ liệu', true, 1400);

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
