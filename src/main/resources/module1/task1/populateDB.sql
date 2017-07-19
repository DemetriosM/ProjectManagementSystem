
INSERT INTO `companies` (`id`,`name`,`city`) VALUES (1,'EPAM','Kiev');
INSERT INTO `companies` (`id`,`name`,`city`) VALUES (2,'SoftServe','Kiev');
INSERT INTO `companies` (`id`,`name`,`city`) VALUES (3,'Nix Solutions Ltd','Kharkiv');

INSERT INTO `developers` (`id`,`surname`,`name`,`companies_id`) VALUES (1,'Petrov','Sergey',1);
INSERT INTO `developers` (`id`,`surname`,`name`,`companies_id`) VALUES (2,'Voronkov','Oleg',1);
INSERT INTO `developers` (`id`,`surname`,`name`,`companies_id`) VALUES (3,'Drozd','Irina',2);
INSERT INTO `developers` (`id`,`surname`,`name`,`companies_id`) VALUES (4,'Mangust','Ivan',2);
INSERT INTO `developers` (`id`,`surname`,`name`,`companies_id`) VALUES (5,'Kalinichenko','Olga',3);
INSERT INTO `developers` (`id`,`surname`,`name`,`companies_id`) VALUES (6,'Kravchenko','Vadim',3);

INSERT INTO `skills` (`id`,`lang`) VALUES (1,'Java');
INSERT INTO `skills` (`id`,`lang`) VALUES (2,'C++');
INSERT INTO `skills` (`id`,`lang`) VALUES (3,'C#');
INSERT INTO `skills` (`id`,`lang`) VALUES (4,'HTML');
INSERT INTO `skills` (`id`,`lang`) VALUES (5,'CSS');
INSERT INTO `skills` (`id`,`lang`) VALUES (6,'JavaScript');

INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (1,1);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (1,3);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (2,1);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (3,4);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (3,5);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (3,6);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (4,2);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (5,1);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (5,4);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (5,5);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (6,2);
INSERT INTO `developers_has_skills` (`developers_id`,`skills_id`) VALUES (6,6);

INSERT INTO `customers` (`id`,`name`,`city`) VALUES (1,'Roshen','Kiev');
INSERT INTO `customers` (`id`,`name`,`city`) VALUES (2,'HTZ','Kharkiv');

INSERT INTO `projects` (`id`,`name`,`customers_id`) VALUES (1,'BookingSystem',1);
INSERT INTO `projects` (`id`,`name`,`customers_id`) VALUES (2,'SiteRoshen',1);
INSERT INTO `projects` (`id`,`name`,`customers_id`) VALUES (3,'ManageSystem',2);
INSERT INTO `projects` (`id`,`name`,`customers_id`) VALUES (4,'SiteHTZ',2);

INSERT INTO `companies_has_projects` (`companies_id`,`projects_id`) VALUES (1,1);
INSERT INTO `companies_has_projects` (`companies_id`,`projects_id`) VALUES (1,4);
INSERT INTO `companies_has_projects` (`companies_id`,`projects_id`) VALUES (2,3);
INSERT INTO `companies_has_projects` (`companies_id`,`projects_id`) VALUES (3,2);
INSERT INTO `companies_has_projects` (`companies_id`,`projects_id`) VALUES (1,2);

INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (1,1);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (2,1);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (1,2);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (2,2);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (3,3);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (4,3);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (5,4);
INSERT INTO `developers_has_projects` (`developers_id`,`projects_id`) VALUES (6,4);
