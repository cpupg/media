drop table media.DIRECTORY;
create table MEDIA.DIRECTORY
(
  ID CHARACTER VARYING(19) not null
    primary key,
  DIR_CODE INTEGER not null,
  PARENT_CODE INTEGER not null,
  NAME CHARACTER VARYING(500) default '' not null,
  PATH VARCHAR(1000) default '' not null,
  CODE_LIST CHARACTER VARYING(100) default '' not null,
  LEVEL TINYINT default '0' not null,
  DELETE_STATUS TINYINT default '0' not null,
  CREATE_TIME TIMESTAMP default CURRENT_TIMESTAMP,
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME TIMESTAMP
);

comment on table MEDIA.DIRECTORY is '资源目录';

comment on column MEDIA.DIRECTORY.ID is '主键id';

comment on column MEDIA.DIRECTORY.DIR_CODE is '目录代码';

comment on column MEDIA.DIRECTORY.PARENT_CODE is '父目录代码';

comment on column MEDIA.DIRECTORY.NAME is '目录名';

comment on column MEDIA.DIRECTORY.PATH is '全路径';

comment on column MEDIA.DIRECTORY.CODE_LIST is '全路径对用的目录代码清单';

comment on column MEDIA.DIRECTORY.LEVEL is '目录层级';

comment on column MEDIA.DIRECTORY.DELETE_STATUS is '删除状态0未删除1删除';

comment on column MEDIA.DIRECTORY.CREATE_TIME is '创建时间';

comment on column MEDIA.DIRECTORY.UPDATE_TIME is '更新时间';

comment on column MEDIA.DIRECTORY.DELETE_TIME is '删除时间';

create unique index MEDIA.MEDIA_DIRECTORY_1
  on MEDIA.DIRECTORY (DIR_CODE);

create index MEDIA.MEDIA_DIRECTORY_2
  on MEDIA.DIRECTORY (CODE_LIST);

create table MEDIA.DIR_REPO
(
  ID CHARACTER VARYING(19) not null
    primary key,
  DIR_CODE INTEGER not null,
  NAME CHARACTER VARYING(32) default '' not null,
  PATH CHARACTER VARYING(500) default '' not null,
  DELETE_STATUS TINYINT default '0' not null,
  CREATE_TIME TIMESTAMP default CURRENT_TIMESTAMP,
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME TIMESTAMP
);

comment on table MEDIA.DIR_REPO is '仓库表';

comment on column MEDIA.DIR_REPO.ID is '主键id';

comment on column MEDIA.DIR_REPO.DIR_CODE is '目录代码';

comment on column MEDIA.DIR_REPO.NAME is '名称（不是目录名）';

comment on column MEDIA.DIR_REPO.PATH is '全路径';

comment on column MEDIA.DIR_REPO.DELETE_STATUS is '删除状态';

comment on column MEDIA.DIR_REPO.CREATE_TIME is '创建时间';

comment on column MEDIA.DIR_REPO.UPDATE_TIME is '更新时间';

comment on column MEDIA.DIR_REPO.DELETE_TIME is '删除时间';

create unique index MEDIA.DIR_REPO_1
  on MEDIA.DIR_REPO (DIR_CODE);

alter table media.RESOURCE
add column dir_code integer not null default '0';

update media.RESOURCE
set SAVE_TIME = CREATE_TIME
where SAVE_TIME is null;

alter table media.RESOURCE
alter column SAVE_TIME set not null;
