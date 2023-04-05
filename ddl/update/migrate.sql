insert into media.media
select *
from public.media;

insert into media.author
select *
from public.author;

insert into media.site
select *
from public.site;