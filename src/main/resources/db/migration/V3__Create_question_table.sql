create table question
(
	id int auto_increment,
	title varchar(50) not null,
	description text not null,
	gmt_create bigint,
	gmt_modify bigint,
	creator int not null,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
	constraint QUESTION_PK
		primary key (id)
);

