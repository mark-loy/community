create table user_tag
(
	id bigint auto_increment comment '主键列',
	tag_name varchar(36) not null comment '标签名',
	type int default 2 comment '标签类型',
	user_id bigint comment '用户id',
	gmt_create bigint,
	gmt_modified bigint,
	constraint user_tag_pk
		primary key (id)
);

