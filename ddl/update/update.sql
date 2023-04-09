alter table media.SITE
add column valid varchar(2) not null default '1';
comment on column media.SITE.valid is '有效性0无效1有效';

alter table media.AUTHOR
add column valid varchar(2) not null default '1';
comment on column media.AUTHOR.valid is '有效性0无效1有效';

alter table media.RESOURCE
add column valid varchar(2) not null default '1';
comment on column media.RESOURCE.valid is '有效性0无效1有效';