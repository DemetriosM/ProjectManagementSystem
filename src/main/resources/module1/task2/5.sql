drop table if exists tmp;
create temporary table tmp
select companies.name as company, customers.name as customer, count(projects.name) as projects_count, sum(cost) as total_amount
from dev.companies, dev.customers, companies_has_projects, dev.projects
where companies.id = companies_id and projects.id = projects_id and customers.id = projects.customers_id
group by companies.name, customers.name
order by company, total_amount asc;

select * from tmp group by tmp.company;