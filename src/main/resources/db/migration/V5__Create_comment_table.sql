create table comment
(
	id bigint auto_increment,
	content varchar(1024) not null,
	parent_id bigint not null,
	creator bigint not null,
	type int not null,
	like_count int default 0,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	constraint comment_pk
		primary key (id)
);