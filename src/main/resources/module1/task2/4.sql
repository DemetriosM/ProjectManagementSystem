alter table dev.projects add column cost int not null;
update dev.projects set cost = 6000 where projects.id = 1;
update dev.projects set cost = 2000 where projects.id = 2;
update dev.projects set cost = 4000 where projects.id = 3;
update dev.projects set cost = 5000 where projects.id = 4;
select * from dev.projects;