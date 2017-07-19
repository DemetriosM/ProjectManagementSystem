select surname as java_developer, salary
from dev.developers, dev.developers_has_skills, dev.skills
where skills.lang like 'Java' and skills_id = skills.id and developers_id = developers.id;

select sum(salary) as java_total_salary
from dev.developers, dev.developers_has_skills, dev.skills
where dev.skills.lang like 'Java' and skills_id = skills.id and developers_id = developers.id
group by skills.id;