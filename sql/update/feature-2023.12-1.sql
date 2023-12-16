alter table media.resource
drop column dir;

create table media.tag
(
  id varchar(19) not null primary key,
  name varchar(10) not null default '',
  create_time timestamp not null default current_timestamp(),
  delete_time timestamp
);

create index media.media_tag_1 on media.tag (name);

comment on table media.tag is '标签';
comment on column media.tag.id is '主键';
comment on column media.tag.name is '名称';
comment on column media.tag.create_time is '创建时间';
comment on column media.tag.delete_time is '删除时间';

create table media.tag_reference
(
  id varchar(19) not null primary key,
  resource_id varchar(19) not null default '',
  tag_id varchar(19) not null default '',
  reference_type tinyint not null default 0,
  refer_time timestamp not null default current_timestamp(),
  un_refer_time timestamp
);

create index media_tag_reference_1 on media.tag_reference (resource_id);
create index media_tag_reference_2 on media.tag_reference (tag_id);

comment on table media.tag_reference is '标签引用';
comment on column media.tag_reference.id is '主键';
comment on column media.tag_reference.resource_id is '资源id';
comment on column media.tag_reference.tag_id is '标签id';
comment on column media.tag_reference.reference_type is '引用类型1:资源';
comment on column media.tag_reference.refer_time is '引用时间';
comment on column media.tag_reference.un_refer_time is '取消引用的时间';
