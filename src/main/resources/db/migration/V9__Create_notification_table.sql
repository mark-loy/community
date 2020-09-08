create table notification
(
	id bigint auto_increment,
	type int not null,
	parent_id bigint not null,
	notifier_id bigint not null,
	receiver_id bigint not null,
	status int not null,
	gmt_create bigint not null,
	constraint NOTIFICATION_PK
		primary key (id)
);

comment on column notification.type is '通知类型（1.评论通知，2.问题通知）';

comment on column notification.parent_id is '通知的所属id（评论/问题id）';

comment on column notification.notifier_id is '通知发送者';

comment on column notification.receiver_id is '通知接收者';

comment on column notification.status is '通知状态（1.未读，2.已读）';

comment on column notification.gmt_create is '创建时间';

