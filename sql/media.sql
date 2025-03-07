CREATE TABLE MEDIA.AUTHOR
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  USER_ID CHARACTER VARYING(90),
  USERNAME CHARACTER VARYING(90) NOT NULL,
  SITE_ID CHARACTER VARYING(19) NOT NULL,
  HOMEPAGE CHARACTER VARYING(512),
  CREATE_TIME TIMESTAMP NOT NULL,
  UPDATE_TIME TIMESTAMP,
  DELETE_STATUS INTEGER DEFAULT 0 NOT NULL
);
COMMENT ON TABLE MEDIA.AUTHOR IS '创作人员';
COMMENT ON COLUMN MEDIA.AUTHOR.ID IS 'ID';
COMMENT ON COLUMN MEDIA.AUTHOR.USER_ID IS '用户在站点注册时的ID';
COMMENT ON COLUMN MEDIA.AUTHOR.USERNAME IS '用户名';
COMMENT ON COLUMN MEDIA.AUTHOR.SITE_ID IS '注册站点ID';
COMMENT ON COLUMN MEDIA.AUTHOR.HOMEPAGE IS '主页';
COMMENT ON COLUMN MEDIA.AUTHOR.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.AUTHOR.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.AUTHOR.DELETE_STATUS IS '删除状态0未删除1删除';
CREATE INDEX MEDIA.AUTHOR_1
  ON MEDIA.AUTHOR (USER_ID);
CREATE INDEX MEDIA.AUTHOR_2
  ON MEDIA.AUTHOR (USERNAME);
CREATE INDEX MEDIA.AUTHOR_3
  ON MEDIA.AUTHOR (SITE_ID);
CREATE INDEX MEDIA.AUTHOR_4
  ON MEDIA.AUTHOR (CREATE_TIME);
CREATE INDEX MEDIA.AUTHOR_5
  ON MEDIA.AUTHOR (UPDATE_TIME);

CREATE TABLE MEDIA.DIRECTORY
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  DIR_CODE INTEGER NOT NULL,
  PARENT_CODE INTEGER NOT NULL,
  NAME CHARACTER VARYING(500) DEFAULT '' NOT NULL,
  PATH CHARACTER VARYING(1000) DEFAULT '' NOT NULL,
  CODE_LIST CHARACTER VARYING(100) DEFAULT '' NOT NULL,
  LEVEL TINYINT DEFAULT '0' NOT NULL,
  DELETE_STATUS TINYINT DEFAULT '0' NOT NULL,
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME TIMESTAMP
);
COMMENT ON TABLE MEDIA.DIRECTORY IS '资源目录';
COMMENT ON COLUMN MEDIA.DIRECTORY.ID IS '主键ID';
COMMENT ON COLUMN MEDIA.DIRECTORY.DIR_CODE IS '目录代码';
COMMENT ON COLUMN MEDIA.DIRECTORY.PARENT_CODE IS '父目录代码';
COMMENT ON COLUMN MEDIA.DIRECTORY.NAME IS '目录名';
COMMENT ON COLUMN MEDIA.DIRECTORY.PATH IS '全路径';
COMMENT ON COLUMN MEDIA.DIRECTORY.CODE_LIST IS '全路径对用的目录代码清单';
COMMENT ON COLUMN MEDIA.DIRECTORY.LEVEL IS '目录层级';
COMMENT ON COLUMN MEDIA.DIRECTORY.DELETE_STATUS IS '删除状态0未删除1删除';
COMMENT ON COLUMN MEDIA.DIRECTORY.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.DIRECTORY.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.DIRECTORY.DELETE_TIME IS '删除时间';
CREATE UNIQUE INDEX MEDIA.MEDIA_DIRECTORY_1
  ON MEDIA.DIRECTORY (DIR_CODE);
CREATE INDEX MEDIA.MEDIA_DIRECTORY_2
  ON MEDIA.DIRECTORY (CODE_LIST);

CREATE TABLE MEDIA.DIR_REPO
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  DIR_CODE INTEGER NOT NULL,
  NAME CHARACTER VARYING(32) DEFAULT '' NOT NULL,
  PATH CHARACTER VARYING(500) DEFAULT '' NOT NULL,
  DELETE_STATUS TINYINT DEFAULT '0' NOT NULL,
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME TIMESTAMP
);
COMMENT ON TABLE MEDIA.DIR_REPO IS '仓库表';
COMMENT ON COLUMN MEDIA.DIR_REPO.ID IS '主键ID';
COMMENT ON COLUMN MEDIA.DIR_REPO.DIR_CODE IS '目录代码';
COMMENT ON COLUMN MEDIA.DIR_REPO.NAME IS '名称（不是目录名）';
COMMENT ON COLUMN MEDIA.DIR_REPO.PATH IS 'PATH';
COMMENT ON COLUMN MEDIA.DIR_REPO.DELETE_STATUS IS 'DELETE_STATUS';
COMMENT ON COLUMN MEDIA.DIR_REPO.CREATE_TIME IS 'CREATE_TIME';
COMMENT ON COLUMN MEDIA.DIR_REPO.UPDATE_TIME IS 'UPDATE_TIME';
COMMENT ON COLUMN MEDIA.DIR_REPO.DELETE_TIME IS 'DELETE_TIME';
CREATE UNIQUE INDEX MEDIA.DIR_REPO_1
  ON MEDIA.DIR_REPO (DIR_CODE);

CREATE TABLE MEDIA.RESOURCE
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  FILENAME CHARACTER VARYING(90) NOT NULL,
  AUTHOR_ID CHARACTER VARYING(19) NOT NULL,
  ALBUM_ID CHARACTER VARYING(19),
  CREATE_TIME TIMESTAMP NOT NULL,
  UPDATE_TIME TIMESTAMP,
  SAVE_TIME TIMESTAMP NOT NULL,
  DELETE_STATUS INTEGER DEFAULT 0 NOT NULL,
  DIR_CODE INTEGER DEFAULT '0' NOT NULL,
  COVER_ID VARCHAR(19) NOT NULL DEFAULT ''
);
COMMENT ON TABLE MEDIA.RESOURCE IS '资源';
COMMENT ON COLUMN MEDIA.RESOURCE.ID IS 'ID';
COMMENT ON COLUMN MEDIA.RESOURCE.FILENAME IS '文件名';
COMMENT ON COLUMN MEDIA.RESOURCE.AUTHOR_ID IS '作者ID';
COMMENT ON COLUMN MEDIA.RESOURCE.ALBUM_ID IS '专辑ID';
COMMENT ON COLUMN MEDIA.RESOURCE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.RESOURCE.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.RESOURCE.SAVE_TIME IS '保存时间';
COMMENT ON COLUMN MEDIA.RESOURCE.DELETE_STATUS IS '删除状态0未删除1删除';
COMMENT ON COLUMN MEDIA.RESOURCE.COVER_ID IS '封面文件ID';
CREATE INDEX MEDIA.RESOURCE_1
  ON MEDIA.RESOURCE (CREATE_TIME DESC);
CREATE INDEX MEDIA.RESOURCE_2
  ON MEDIA.RESOURCE (UPDATE_TIME DESC);
CREATE INDEX MEDIA.RESOURCE_3
  ON MEDIA.RESOURCE (FILENAME);

CREATE TABLE MEDIA.RESOURCE_BACKUP
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  FILENAME CHARACTER VARYING(90) NOT NULL,
  AUTHOR_ID CHARACTER VARYING(19) NOT NULL,
  ALBUM_ID CHARACTER VARYING(19),
  CREATE_TIME TIMESTAMP NOT NULL,
  UPDATE_TIME TIMESTAMP,
  SAVE_TIME TIMESTAMP NOT NULL,
  DELETE_STATUS INTEGER DEFAULT 0 NOT NULL,
  DIR_CODE INTEGER DEFAULT '0' NOT NULL
);
COMMENT ON TABLE MEDIA.RESOURCE_BACKUP IS '资源';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.ID IS 'ID';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.FILENAME IS '文件名';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.AUTHOR_ID IS '作者ID';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.ALBUM_ID IS '专辑ID';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.SAVE_TIME IS '保存时间';
COMMENT ON COLUMN MEDIA.RESOURCE_BACKUP.DELETE_STATUS IS '删除状态0未删除1删除';
CREATE INDEX MEDIA."RESOURCE_BACKUP_1"
  ON MEDIA.RESOURCE_BACKUP (CREATE_TIME DESC);
CREATE INDEX MEDIA."RESOURCE_BACKUP_2"
  ON MEDIA.RESOURCE_BACKUP (UPDATE_TIME DESC);
CREATE INDEX MEDIA."RESOURCE_BACKUP_3"
  ON MEDIA.RESOURCE_BACKUP (FILENAME);

CREATE TABLE MEDIA.RESOURCE_DIR_CODE_TEMP
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  DIR_CODE INTEGER
);

CREATE TABLE MEDIA.SITE
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  SITE_NAME CHARACTER VARYING(90) NOT NULL,
  URL CHARACTER VARYING(90) NOT NULL,
  CREATE_TIME TIMESTAMP NOT NULL,
  UPDATE_TIME TIMESTAMP,
  DELETE_STATUS INTEGER DEFAULT 0 NOT NULL
);
COMMENT ON TABLE MEDIA.SITE IS '站点';
COMMENT ON COLUMN MEDIA.SITE.ID IS 'ID';
COMMENT ON COLUMN MEDIA.SITE.SITE_NAME IS '网站名称';
COMMENT ON COLUMN MEDIA.SITE.URL IS '网站地址';
COMMENT ON COLUMN MEDIA.SITE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.SITE.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.SITE.DELETE_STATUS IS '删除状态0未删除1删除';
CREATE INDEX MEDIA.SITE_1
  ON MEDIA.SITE (SITE_NAME);
CREATE INDEX MEDIA.SITE_2
  ON MEDIA.SITE (CREATE_TIME);
CREATE INDEX MEDIA.SITE_3
  ON MEDIA.SITE (UPDATE_TIME);

CREATE TABLE MEDIA.TAG
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  NAME CHARACTER VARYING(10) DEFAULT '' NOT NULL,
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DELETE_TIME TIMESTAMP
);
COMMENT ON TABLE MEDIA.TAG IS '标签';
COMMENT ON COLUMN MEDIA.TAG.ID IS '主键';
COMMENT ON COLUMN MEDIA.TAG.NAME IS '名称';
COMMENT ON COLUMN MEDIA.TAG.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.TAG.DELETE_TIME IS '删除时间';
CREATE INDEX MEDIA.MEDIA_TAG_1
  ON MEDIA.TAG (NAME);

CREATE TABLE MEDIA.TAG_REFERENCE
(
  ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  RESOURCE_ID CHARACTER VARYING(19) DEFAULT '' NOT NULL,
  TAG_ID CHARACTER VARYING(19) DEFAULT '' NOT NULL,
  REFERENCE_TYPE TINYINT DEFAULT 0 NOT NULL,
  REFER_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  UN_REFER_TIME TIMESTAMP
);
COMMENT ON TABLE MEDIA.TAG_REFERENCE IS '标签引用';
COMMENT ON COLUMN MEDIA.TAG_REFERENCE.ID IS '主键';
COMMENT ON COLUMN MEDIA.TAG_REFERENCE.RESOURCE_ID IS '资源ID';
COMMENT ON COLUMN MEDIA.TAG_REFERENCE.TAG_ID IS '标签ID';
COMMENT ON COLUMN MEDIA.TAG_REFERENCE.REFERENCE_TYPE IS '引用类型1:资源';
COMMENT ON COLUMN MEDIA.TAG_REFERENCE.REFER_TIME IS '引用时间';
COMMENT ON COLUMN MEDIA.TAG_REFERENCE.UN_REFER_TIME IS '取消引用的时间';
CREATE INDEX MEDIA.MEDIA_TAG_REFERENCE_1
  ON MEDIA.TAG_REFERENCE (RESOURCE_ID);
CREATE INDEX MEDIA.MEDIA_TAG_REFERENCE_2
  ON MEDIA.TAG_REFERENCE (TAG_ID);

CREATE TABLE MEDIA.TEMP
(
  ID TINYINT
);
CREATE INDEX MEDIA."TEMP.T"
  ON MEDIA.TEMP (ID);


-- DROP TABLE MEDIA.ALBUM;

CREATE TABLE MEDIA.ALBUM
(
  ID VARCHAR(19) PRIMARY KEY NOT NULL,
  NAME VARCHAR(32) NOT NULL DEFAULT '',
  DELETE_STATUS TINYINT NOT NULL DEFAULT 0,
  CREATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME TIMESTAMP,
  COVER_ID VARCHAR(19) NOT NULL DEFAULT ''
);

COMMENT ON TABLE MEDIA.ALBUM IS '专辑';
COMMENT ON COLUMN MEDIA.ALBUM.ID IS '主键';
COMMENT ON COLUMN MEDIA.ALBUM.NAME IS '专辑名称';
COMMENT ON COLUMN MEDIA.ALBUM.DELETE_STATUS IS '删除状态';
COMMENT ON COLUMN MEDIA.ALBUM.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.ALBUM.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.ALBUM.DELETE_TIME IS '删除时间';
COMMENT ON COLUMN MEDIA.ALBUM.COVER_ID IS '封面文件ID';

CREATE TABLE MEDIA.ALBUM_RESOURCE
(
  ID VARCHAR(19) PRIMARY KEY NOT NULL,
  ALBUM_ID VARCHAR(19) NOT NULL DEFAULT '',
  RESOURCE_ID VARCHAR(19) NOT NULL DEFAULT '',
  DELETE_STATUS TINYINT NOT NULL DEFAULT 0,
  CREATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME TIMESTAMP
);

COMMENT ON TABLE MEDIA.ALBUM_RESOURCE IS '资源专辑';
COMMENT ON COLUMN MEDIA.ALBUM_RESOURCE.ID IS '主键';
COMMENT ON COLUMN MEDIA.ALBUM_RESOURCE.DELETE_STATUS IS '删除状态';
COMMENT ON COLUMN MEDIA.ALBUM_RESOURCE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN MEDIA.ALBUM_RESOURCE.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN MEDIA.ALBUM_RESOURCE.DELETE_TIME IS '删除时间';


-- DROP TABLE MEDIA.FILE_UPLOAD;
CREATE TABLE MEDIA.FILE_UPLOAD
(
  ID VARCHAR(19) NOT NULL PRIMARY KEY,
  ORIGINAL_FILENAME VARCHAR(64) NOT NULL DEFAULT '',
  FILENAME VARCHAR(32) NOT NULL DEFAULT '',
  BUSINESS_CODE VARCHAR(19) NOT NULL DEFAULT '0',
  BUSINESS_TYPE TINYINT NOT NULL DEFAULT 0,
  EXTENSION VARCHAR(8) NOT NULL DEFAULT '',
  UPLOAD_STATUS TINYINT NOT NULL DEFAULT 0,
  DELETE_STATUS TINYINT NOT NULL DEFAULT 0,
  UPLOAD_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  DELETE_TIME TIMESTAMP
);

CREATE INDEX MEDIA.FILE_UPLOAD_1 ON MEDIA.FILE_UPLOAD (BUSINESS_CODE);

COMMENT ON TABLE MEDIA.FILE_UPLOAD IS '文件上传';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.ID IS '主键标识';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.ORIGINAL_FILENAME IS '原始文件名';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.FILENAME IS '保存时的文件名';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.BUSINESS_CODE IS '业务代码，用来关联业务';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.BUSINESS_TYPE IS '业务类型，用来区分业务';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.EXTENSION IS '文件扩展名';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.UPLOAD_STATUS IS '上传状态1开始上传2上传完成';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.DELETE_STATUS IS '删除状态';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.UPLOAD_TIME IS '上传时间';
COMMENT ON COLUMN MEDIA.FILE_UPLOAD.DELETE_TIME IS '删除时间';


CREATE TABLE MEDIA.RATE
(
  RATE_ID CHARACTER VARYING(19) NOT NULL
    PRIMARY KEY,
  RESOURCE_ID CHARACTER VARYING(19) NOT NULL,
  RATE TINYINT DEFAULT 0 NOT NULL,
  DELETE_STATUS INTEGER DEFAULT 0 NOT NULL,
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIME NOT NULL,
  UPDATE_TIME TIMESTAMP
);

COMMENT ON TABLE RATE IS '评分';
COMMENT ON COLUMN RATE.RESOURCE_ID IS '资源标识';
COMMENT ON COLUMN RATE.RATE IS '评分';
COMMENT ON COLUMN RATE.DELETE_STATUS IS '删除状态';
COMMENT ON COLUMN RATE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN RATE.UPDATE_TIME IS '更新时间';

CREATE TABLE MEDIA.COLLECT
(
  COLLECT_ID VARCHAR(19) NOT NULL PRIMARY KEY,
  COLLECT_NAME VARCHAR(32) NOT NULL DEFAULT '',
  DELETE_STATUS TINYINT NOT NULL DEFAULT 0,
  CREATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIME(),
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME timestamp
);

comment on table media.COLLECT is '收藏';
comment on column media.COLLECT.COLLECT_ID is '收藏id';
comment on column media.COLLECT.COLLECT_NAME is '收藏名称';
comment on column media.COLLECT.DELETE_STATUS is '删除状态';
comment on column media.COLLECT.CREATE_TIME is '创建时间';
comment on column media.COLLECT.UPDATE_TIME is '更新时间';
comment on column media.COLLECT.DELETE_TIME is '删除时间';

CREATE TABLE MEDIA.RESOURCE_COLLECT
(
  RESOURCE_COLLECT_ID VARCHAR(19) NOT NULL PRIMARY KEY,
  RESOURCE_ID VARCHAR(19) NOT NULL,
  COLLECT_ID VARCHAR(19) NOT NULL,
  DELETE_STATUS INTEGER DEFAULT 0 NOT NULL,
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIME NOT NULL,
  UPDATE_TIME TIMESTAMP,
  DELETE_TIME timestamp
);

comment on table MEDIA.RESOURCE_COLLECT is '资源关联的收藏';
comment on column media.RESOURCE_COLLECT.RESOURCE_COLLECT_ID is '关联id';
comment on column media.RESOURCE_COLLECT.RESOURCE_ID is '资源主键id';
comment on column media.RESOURCE_COLLECT.COLLECT_ID is '收藏id';
comment on column media.RESOURCE_COLLECT.DELETE_STATUS is '删除状态';
comment on column media.RESOURCE_COLLECT.CREATE_TIME is '创建时间';
comment on column media.RESOURCE_COLLECT.UPDATE_TIME is '更新时间';
comment on column media.RESOURCE_COLLECT.DELETE_TIME is '删除时间';
