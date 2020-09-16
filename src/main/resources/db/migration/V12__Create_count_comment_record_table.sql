create table count_comment_record
(
	id bigint auto_increment,
	user_id bigint not null comment '用户id',
	question_id bigint not null comment '问题id',
	comment_id bigint not null comment '评论id',
	comment_like_check int default 0 not null comment '0评论未点赞；1评论已点赞',
	gmt_create bigint null,
	constraint count_comment_record_pk
		primary key (id)
);

