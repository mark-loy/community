create table tag
(
	id bigint auto_increment,
	tag_name varchar(36) not null,
	type char not null,
	gmt_create bigint,
	gmt_modified bigint,
	constraint tag_pk
		primary key (id)
);