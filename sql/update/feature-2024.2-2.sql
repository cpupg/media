create table media.file_upload
(
  id varchar(19) not null primary key,
  original_filename varchar(64) not null default '',
  filename varchar(32) not null default '',
  business_code varchar(19) not null default '0',
  business_type tinyint not null default 0,
  upload_status tinyint not null default 0,
  delete_status tinyint not null default 0,
  upload_time timestamp not null default current_timestamp,
  delete_time timestamp
);

create index media.file_upload_1 on media.file_upload (business_code);

comment on table media.file_upload is '文件上传';
comment on column media.file_upload.id is '主键标识';
comment on column media.file_upload.original_filename is '原始文件名';
comment on column media.file_upload.filename is '保存时的文件名';
comment on column media.file_upload.business_code is '业务代码，用来关联业务';
comment on column media.file_upload.business_type is '业务类型，用来区分业务';
comment on column media.file_upload.upload_status is '上传状态1开始上传2上传完成';
comment on column media.file_upload.delete_status is '删除状态';
comment on column media.file_upload.upload_time is '上传时间';
comment on column media.file_upload.delete_time is '删除时间';
