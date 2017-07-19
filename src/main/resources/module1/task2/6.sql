select projects.name, projects.cost, avg(developers.salary)
from dev.projects, dev.developers, dev.developers_has_projects
where projects.id = projects_id and developers.id = developers_id
group by projects.id
order by cost asc limit 1;