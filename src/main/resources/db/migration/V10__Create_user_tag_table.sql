create table user_tag
(
	id bigint auto_increment,
	tag_name varchar(16) not null,
	type int default 2,
	user_id bigint,
	gmt_create bigint,
	gmt_modified bigint,
	constraint USER_TAG_PK
		primary key (id)
);

comment on table user_tag is '用户自定义标签表';

comment on column user_tag.id is '主键列';

comment on column user_tag.tag_name is '标签名';

comment on column user_tag.type is '标签类型';

comment on column user_tag.user_id is '用户id';

