alter table dev.developers add column salary int not null after name;
update dev.developers set salary = 1500 where id = 1;
update dev.developers set salary = 1000 where id = 2;
update dev.developers set salary = 2300 where id = 3;
update dev.developers set salary = 800 where id = 4;
update dev.developers set salary = 1700 where id = 5;
update dev.developers set salary = 900 where id = 6;
select * from dev.developers;