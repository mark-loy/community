create table `user` (
    id bigint auto_increment,
    account_id varchar(100) not null,
    login_name varchar(50) not null,
    token char(36) not null,
    gmt_create bigint,
    gmt_modify bigint,
    constraint user_pk
        primary key (id)
)