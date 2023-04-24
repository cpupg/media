create table MEDIA.AUTHOR
(
  ID            CHARACTER VARYING(19) not null
    primary key,
  USER_ID       CHARACTER VARYING(90),
  USERNAME      CHARACTER VARYING(90) not null,
  SITE_ID       CHARACTER VARYING(19) not null,
  HOMEPAGE      CHARACTER VARYING(90),
  CREATE_TIME   TIMESTAMP             not null,
  UPDATE_TIME   TIMESTAMP,
  DELETE_STATUS INTEGER default 0     not null
);

comment on table MEDIA.AUTHOR is '创作人员';

comment on column MEDIA.AUTHOR.ID is 'ID';

comment on column MEDIA.AUTHOR.USER_ID is '用户在站点注册时的id';

comment on column MEDIA.AUTHOR.USERNAME is '用户名';

comment on column MEDIA.AUTHOR.SITE_ID is '注册站点ID';

comment on column MEDIA.AUTHOR.HOMEPAGE is '主页';

comment on column MEDIA.AUTHOR.CREATE_TIME is '创建时间';

comment on column MEDIA.AUTHOR.UPDATE_TIME is '更新时间';

comment on column MEDIA.AUTHOR.DELETE_STATUS is '删除状态0未删除1删除';

create index MEDIA.AUTHOR_1
  on MEDIA.AUTHOR (USER_ID);

create index MEDIA.AUTHOR_2
  on MEDIA.AUTHOR (USERNAME);

create index MEDIA.AUTHOR_3
  on MEDIA.AUTHOR (SITE_ID);

create index MEDIA.AUTHOR_4
  on MEDIA.AUTHOR (CREATE_TIME);

create index MEDIA.AUTHOR_5
  on MEDIA.AUTHOR (UPDATE_TIME);

create table MEDIA.RESOURCE
(
  ID            CHARACTER VARYING(19)  not null
    primary key,
  FILENAME      CHARACTER VARYING(90)  not null,
  DIR           CHARACTER VARYING(900) not null,
  AUTHOR_ID     CHARACTER VARYING(19)  not null,
  ALBUM_ID      CHARACTER VARYING(19),
  CREATE_TIME   TIMESTAMP              not null,
  UPDATE_TIME   TIMESTAMP,
  SAVE_TIME     TIMESTAMP,
  DELETE_STATUS INTEGER default 0      not null
);

comment on table MEDIA.RESOURCE is '资源';

comment on column MEDIA.RESOURCE.ID is 'ID';

comment on column MEDIA.RESOURCE.FILENAME is '文件名';

comment on column MEDIA.RESOURCE.DIR is '资源目录';

comment on column MEDIA.RESOURCE.AUTHOR_ID is '作者id';

comment on column MEDIA.RESOURCE.ALBUM_ID is '专辑id';

comment on column MEDIA.RESOURCE.CREATE_TIME is '创建时间';

comment on column MEDIA.RESOURCE.UPDATE_TIME is '更新时间';

comment on column MEDIA.RESOURCE.SAVE_TIME is '保存时间';

comment on column MEDIA.RESOURCE.DELETE_STATUS is '删除状态0未删除1删除';

create index MEDIA.RESOURCE_1
  on MEDIA.RESOURCE (CREATE_TIME desc);

create index MEDIA.RESOURCE_2
  on MEDIA.RESOURCE (UPDATE_TIME desc);

create index MEDIA.RESOURCE_3
  on MEDIA.RESOURCE (FILENAME);

create table MEDIA.SITE
(
  ID            CHARACTER VARYING(19) not null
    primary key,
  SITE_NAME     CHARACTER VARYING(90) not null,
  URL           CHARACTER VARYING(90) not null,
  CREATE_TIME   TIMESTAMP             not null,
  UPDATE_TIME   TIMESTAMP,
  DELETE_STATUS INTEGER default 0     not null
);

comment on table MEDIA.SITE is '站点';

comment on column MEDIA.SITE.ID is 'ID';

comment on column MEDIA.SITE.SITE_NAME is '网站名称';

comment on column MEDIA.SITE.URL is '网站地址';

comment on column MEDIA.SITE.CREATE_TIME is '创建时间';

comment on column MEDIA.SITE.UPDATE_TIME is '更细时间';

comment on column MEDIA.SITE.DELETE_STATUS is '删除状态0未删除1删除';

create index MEDIA.SITE_1
  on MEDIA.SITE (SITE_NAME);

create index MEDIA.SITE_2
  on MEDIA.SITE (CREATE_TIME);

create index MEDIA.SITE_3
  on MEDIA.SITE (UPDATE_TIME);

