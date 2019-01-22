-- 用户表
create table user
(
	ID int auto_increment
		primary key,
	NAME varchar(40) null,
	PASSWORD varchar(30) not null,
	SCORE int default '0' null,
	PHONE_NUMBER varchar(20) null,
	REAL_NAME varchar(40) null
)
;

-- 评论表
create table comment
(
	COMMENTATOR_ID int not null,
	RESPONDENT int not null,
	CONTENT varchar(255) null,
	SCORE int default '100' not null,
	primary key (COMMENTATOR_ID, RESPONDENT),
	constraint comment_user__fk1
		foreign key (RESPONDENT) references homework_nju.user (id)
)
;


-- 照片表
create table photo
(
	PHOTO_id int auto_increment
		primary key,
	PLAN_ID int not null,
	UPLOADER_ID int not null,
	PHOTO_PATH varchar(50) null,
	constraint photo_plan__fk
		foreign key (PLAN_ID) references homework_nju.plan (id),
	constraint photo_user__fk
		foreign key (UPLOADER_ID) references homework_nju.user (id)
)
;

-- 计划表
create table plan
(
	ID int auto_increment
		primary key,
	time date null,
	SITE varchar(50) not null,
	PEOPLE_NUMBER int default '1' null
)
;


-- 计划-用户表
create table plan_user
(
	PLAN_ID int not null,
	USER_ID int not null,
	ROLE bit default b'0' null,
	primary key (PLAN_ID, USER_ID),
	constraint PLAN_USER_user__fk
		foreign key (USER_ID) references homework_nju.user (id)
)
;






