create table notification
(
	id bigint auto_increment ,
	type int not null comment '通知类型（1.评论通知，2.问题通知）',
	parent_id bigint not null comment '通知的所属id（评论/问题id）',
	notifier_id bigint not null comment '通知发送者',
	receiver_id bigint not null comment '通知接收者',
	status int not null comment '通知状态（1.未读，2.已读）',
	gmt_create bigint not null comment '创建时间',
	constraint notification_pk
		primary key (id)
);
