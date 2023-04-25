alter table media.SITE
add column delete_status int not null default 0;
comment on column media.SITE.delete_status is '删除状态0未删除1删除';

alter table media.AUTHOR
add column delete_status int not null default 0;
comment on column media.AUTHOR.delete_status is '删除状态0未删除1删除';

alter table media.RESOURCE
add column delete_status int not null default 0;
comment on column media.RESOURCE.delete_status is '删除状态0未删除1删除';
