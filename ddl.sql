-- DDL
DROP DATABASE IF EXISTS web_magazine;
CREATE DATABASE web_magazine;
USE web_magazine;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS magazine;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS banner;
DROP TABLE IF EXISTS like_list;
DROP TABLE IF EXISTS pointlist;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS mycoupon;

CREATE TABLE `m_user` (
	`user_id`	VARCHAR(50)	NOT NULL,
	`user_pwd`	VARCHAR(2000)	NULL,
	`user_name`	VARCHAR(50)	NULL,
	`user_gender`	VARCHAR(20)	NULL,
	`user_phone_no`	VARCHAR(100)	NULL,
	`user_birth`	DATE	NULL,
	`user_regdate`	DATE	NULL,
	`user_email`	VARCHAR(100)	NULL,
	`user_type`	INT	NULL,
	`user_use`	BOOLEAN	NULL,
	`user_deldate`	DATE	NULL,
    `user_refresh_token` VARCHAR(2000)	NULL
);
ALTER TABLE `m_user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (`user_id`);

CREATE TABLE `category` (
	`cate_no`	INT	NOT NULL,
	`cate_info`	VARCHAR(100)	NULL,
	`cate_headno`	INT	NOT NULL
);

ALTER TABLE `category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (`cate_no`);
ALTER TABLE category ADD CONSTRAINT FOREIGN KEY (cate_headno) REFERENCES category (cate_no);

CREATE TABLE `magazine` (
	`magazine_no`	INT	NOT NULL,
	`magazine_name`	VARCHAR(100)	NULL,
	`magazine_company`	VARCHAR(100)	NULL,
	`magazine_regdate`	DATE	NULL,
	`magazine_cover`	VARCHAR(100)	NULL,
	`magazine_content`	TEXT	NULL,
	`cate_no`	INT	NOT NULL,
	`magazine_hit`	INT	NULL
);

ALTER TABLE `magazine` ADD CONSTRAINT `PK_MAGAZINE` PRIMARY KEY (`magazine_no`);
ALTER TABLE magazine MODIFY magazine_no INT AUTO_INCREMENT;
ALTER TABLE magazine AUTO_INCREMENT = 1;
ALTER TABLE magazine ADD CONSTRAINT FOREIGN KEY (cate_no) REFERENCES category (cate_no);


CREATE TABLE `banner` (
	`banner_no`	INT	NULL,
	`banner_content`	TEXT	NULL,
	`banner_hit`	INT	NULL,
	`banner_regdate`	DATE	NULL
);
ALTER TABLE `banner` ADD CONSTRAINT `PK_BANNER` PRIMARY KEY (`banner_no`);
ALTER TABLE banner MODIFY banner_no INT AUTO_INCREMENT;
ALTER TABLE banner AUTO_INCREMENT = 1;

CREATE TABLE `like_list` (
	`like_no`	INT	NOT NULL,
	`magazine_no`	INT	NOT NULL,
	`user_id`	VARCHAR(50)	NOT NULL
);
ALTER TABLE `like_list` ADD CONSTRAINT `PK_LIKE_LIST` PRIMARY KEY (`like_no`);
ALTER TABLE like_list MODIFY like_no INT AUTO_INCREMENT;
ALTER TABLE like_list AUTO_INCREMENT = 1;
ALTER TABLE like_list ADD CONSTRAINT FOREIGN KEY (magazine_no) REFERENCES magazine (magazine_no);
ALTER TABLE like_list ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES m_user (user_id);

CREATE TABLE `subscription` (
	`sub_no`	INT	NOT NULL,
	`sub_name`	VARCHAR(100)	NULL,
	`sub_period`	INT	NULL,
	`sub_price`	INT	NULL
);
ALTER TABLE `subscription` ADD CONSTRAINT `PK_SUBSCRIPTION` PRIMARY KEY (`sub_no`);
ALTER TABLE subscription MODIFY sub_no INT AUTO_INCREMENT;
ALTER TABLE subscription AUTO_INCREMENT = 1;

CREATE TABLE `subscribe_detail` (
	`sub_detail_no`	INT	NOT NULL,
	`sub_no`	INT	NOT NULL,
	`user_id`	VARCHAR(50)	NOT NULL,
	`sub_detail_regdate`	DATE	NULL,
	`sub_detail_avail`	BOOLEAN	NULL
);

ALTER TABLE `subscribe_detail` ADD CONSTRAINT `PK_SUBSCRIBE_DETAIL` PRIMARY KEY (`sub_detail_no`);
ALTER TABLE subscribe_detail MODIFY sub_detail_no INT AUTO_INCREMENT;
ALTER TABLE subscribe_detail AUTO_INCREMENT = 1;
ALTER TABLE subscribe_detail ADD CONSTRAINT FOREIGN KEY (sub_no) REFERENCES subscription (sub_no);

CREATE TABLE `notice` (
	`notice_no`	INT	NOT NULL,
	`notice_content`	TEXT	NULL,
	`notice_regdate`	DATE	NULL,
	`notice_hit`	INT	NULL,
	`user_id`	VARCHAR(50)	NOT NULL
);

ALTER TABLE `notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (`notice_no`);
ALTER TABLE notice MODIFY notice_no INT AUTO_INCREMENT;
ALTER TABLE notice AUTO_INCREMENT = 1;
ALTER TABLE notice ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES m_user (user_id);

CREATE TABLE `comment` (
	`comment_no`	INT	NOT NULL,
	`comment_content`	VARCHAR(500)	NULL,
	`comment_regdate`	DATE	NULL,
	`user_id`	VARCHAR(50)	NOT NULL,
	`magazine_no`	INT	NOT NULL
);
ALTER TABLE `comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (`comment_no`);
ALTER TABLE comment MODIFY comment_no INT AUTO_INCREMENT;
ALTER TABLE comment AUTO_INCREMENT = 1;
ALTER TABLE comment ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES m_user (user_id);
ALTER TABLE comment ADD CONSTRAINT FOREIGN KEY (magazine_no) REFERENCES magazine (magazine_no);
