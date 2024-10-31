create database PolyOE;
use PolyOE;

create table users(
Id nvarchar(20) not null primary key,
Password nvarchar(50) not null,
Fullname nvarchar(50) not null,
Email nvarchar(50) not null,
Admin bit not null
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

-- create table favorites(
-- Id bigint not null primary key,
-- VideoId character(11) not null,
-- UserId nvarchar(20) not null,
-- LikeDate date not null
--
-- );
--
-- create table videos(
-- Id character(11) not null primary key,
-- Title nvarchar(50) not null,
-- Poster nvarchar(50),
-- [Description] nvarchar(MAX),
-- Active bit not null,
-- Views int not null
-- );