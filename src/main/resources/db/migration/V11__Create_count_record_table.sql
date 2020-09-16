create table count_record
(
	id bigint auto_increment,
	user_id bigint not null comment '用户id',
	question_id bigint not null comment '问题id',
	view_check int default 0 not null comment '0未浏览，1已浏览',
	like_check int default 0 not null comment '0未点赞，1已点赞',
	gmt_create bigint not null,
	constraint count_record_pk
		primary key (id)
);

