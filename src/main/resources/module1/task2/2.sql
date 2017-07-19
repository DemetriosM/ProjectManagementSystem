select projects.name as project, surname as developer, salary
from dev.developers_has_projects, dev.projects, dev.developers
where projects.id = projects_id and developers.id = developers_id;

select projects.name as project, sum(salary) as total_salary
from dev.projects, dev.developers, dev.developers_has_projects
where developers_has_projects.developers_id = developers.id and developers_has_projects.projects_id = projects.id
group by dev.projects.id
order by max(salary) desc limit 1;