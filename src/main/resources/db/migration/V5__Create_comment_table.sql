create table comment
(
	id bigint auto_increment,
	content varchar(1024) not null,
	parent_id bigint not null,
	creator int not null,
	type int not null,
	like_count int default 0,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	constraint COMMENT_PK
		primary key (id)
);