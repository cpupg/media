create table ALBUM
(
  ID CHARACTER VARYING(19) not null
    primary key,
  ALBUM_NAME CHARACTER VARYING(90) not null,
  AUTHOR_ID CHARACTER VARYING(19) not null,
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP
);

comment on table ALBUM is '专辑';

comment on column ALBUM.ID is 'ID';

comment on column ALBUM.ALBUM_NAME is '专辑';

comment on column ALBUM.AUTHOR_ID is '专辑作者';

comment on column ALBUM.CREATE_TIME is '创建时间';

comment on column ALBUM.UPDATE_TIME is '更新时间';

create table AUTHOR
(
  ID CHARACTER VARYING(19) not null
    primary key,
  USER_ID CHARACTER VARYING(90),
  USERNAME CHARACTER VARYING(90) not null,
  SITE_ID CHARACTER VARYING(19) not null,
  HOMEPAGE CHARACTER VARYING(90),
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP,
  DELETE_STATUS INTEGER default 0 not null
);

comment on table AUTHOR is '创作人员';

comment on column AUTHOR.ID is 'ID';

comment on column AUTHOR.USER_ID is '用户在站点注册时的id';

comment on column AUTHOR.USERNAME is '用户名';

comment on column AUTHOR.SITE_ID is '注册站点ID';

comment on column AUTHOR.HOMEPAGE is '主页';

comment on column AUTHOR.CREATE_TIME is '创建时间';

comment on column AUTHOR.UPDATE_TIME is '更新时间';

comment on column AUTHOR.DELETE_STATUS is '删除状态0未删除1删除';

create index AUTHOR_1
  on AUTHOR (USER_ID);

create index AUTHOR_2
  on AUTHOR (USERNAME);

create index AUTHOR_3
  on AUTHOR (SITE_ID);

create index AUTHOR_4
  on AUTHOR (CREATE_TIME);

create index AUTHOR_5
  on AUTHOR (UPDATE_TIME);

create table RESOURCE
(
  ID CHARACTER VARYING(19) not null
    primary key,
  FILENAME CHARACTER VARYING(90) not null,
  DIR CHARACTER VARYING(900) not null,
  AUTHOR_ID CHARACTER VARYING(19) not null,
  ALBUM_ID CHARACTER VARYING(19),
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP,
  SAVE_TIME TIMESTAMP,
  DELETE_STATUS INTEGER default 0 not null
);

comment on table RESOURCE is '资源';

comment on column RESOURCE.ID is 'ID';

comment on column RESOURCE.FILENAME is '文件名';

comment on column RESOURCE.DIR is '资源目录';

comment on column RESOURCE.AUTHOR_ID is '作者id';

comment on column RESOURCE.ALBUM_ID is '专辑id';

comment on column RESOURCE.CREATE_TIME is '创建时间';

comment on column RESOURCE.UPDATE_TIME is '更新时间';

comment on column RESOURCE.SAVE_TIME is '保存时间';

comment on column RESOURCE.DELETE_STATUS is '删除状态0未删除1删除';

create index RESOURCE_1
  on RESOURCE (CREATE_TIME desc);

create index RESOURCE_2
  on RESOURCE (UPDATE_TIME desc);

create index RESOURCE_3
  on RESOURCE (FILENAME);

create table RESOURCE_ALBUM
(
  ID CHARACTER VARYING(19) not null
    primary key,
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP,
  RESOURCE_ID CHARACTER VARYING(19) not null,
  ALBUM_ID CHARACTER VARYING(19) not null
);

comment on table RESOURCE_ALBUM is '资源所属专辑';

comment on column RESOURCE_ALBUM.ID is 'ID';

comment on column RESOURCE_ALBUM.CREATE_TIME is '创建时间';

comment on column RESOURCE_ALBUM.UPDATE_TIME is '更新时间';

comment on column RESOURCE_ALBUM.RESOURCE_ID is '资源ID';

comment on column RESOURCE_ALBUM.ALBUM_ID is '专辑ID';

create table RESOURCE_TYPE
(
  ID CHARACTER VARYING(19) not null
    primary key,
  RESOURCE_ID CHARACTER VARYING(19) not null,
  TYPE_ID CHARACTER VARYING(19) not null,
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP
);

comment on table RESOURCE_TYPE is '资源和类型关联';

comment on column RESOURCE_TYPE.ID is 'ID';

comment on column RESOURCE_TYPE.RESOURCE_ID is '资源id';

comment on column RESOURCE_TYPE.TYPE_ID is '类型id';

comment on column RESOURCE_TYPE.CREATE_TIME is '创建时间';

comment on column RESOURCE_TYPE.UPDATE_TIME is '更新时间';

create table RESOURCE_TYPE_MAP
(
  ID CHARACTER VARYING(19) not null
    primary key,
  PARENT_ID CHARACTER VARYING(19),
  NAME CHARACTER VARYING(90) not null,
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP
);

comment on table RESOURCE_TYPE_MAP is '资源类型映射';

comment on column RESOURCE_TYPE_MAP.ID is '主键';

comment on column RESOURCE_TYPE_MAP.PARENT_ID is '父类型';

comment on column RESOURCE_TYPE_MAP.NAME is '名称';

comment on column RESOURCE_TYPE_MAP.CREATE_TIME is '创建时间';

comment on column RESOURCE_TYPE_MAP.UPDATE_TIME is '更新时间';

create index RESOURCE_TYPE_MAP_1
  on RESOURCE_TYPE_MAP (CREATE_TIME desc);

create table SITE
(
  ID CHARACTER VARYING(19) not null
    primary key,
  SITE_NAME CHARACTER VARYING(90) not null,
  URL CHARACTER VARYING(90) not null,
  CREATE_TIME TIMESTAMP not null,
  UPDATE_TIME TIMESTAMP,
  DELETE_STATUS INTEGER default 0 not null
);

comment on table SITE is '站点';

comment on column SITE.ID is 'ID';

comment on column SITE.SITE_NAME is '网站名称';

comment on column SITE.URL is '网站地址';

comment on column SITE.CREATE_TIME is '创建时间';

comment on column SITE.UPDATE_TIME is '更细时间';

comment on column SITE.DELETE_STATUS is '删除状态0未删除1删除';

create index SITE_1
  on SITE (SITE_NAME);

create index SITE_2
  on SITE (CREATE_TIME);

create index SITE_3
  on SITE (UPDATE_TIME);

