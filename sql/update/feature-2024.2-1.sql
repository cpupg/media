drop table media.album;

create table media.album
(
  id varchar(19) primary key not null,
  name varchar(32) not null default '',
  delete_status tinyint not null default 0,
  create_time timestamp not null default current_timestamp(),
  update_time timestamp,
  delete_time timestamp
);

comment on table media.album is '专辑';
comment on column media.album.id is '主键';
comment on column media.album.name is '专辑名称';
comment on column media.album.delete_status is '删除状态';
comment on column media.album.create_time is '创建时间';
comment on column media.album.update_time is '更新时间';
comment on column media.album.delete_time is '删除时间';
