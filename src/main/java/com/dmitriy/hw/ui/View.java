package com.dmitriy.hw.ui;

import com.dmitriy.hw.dao.*;
import com.dmitriy.hw.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class View implements ViewApi {
    private CompanyDao companyDao;
    private CustomerDao customerDao;
    private DeveloperDao developerDao;
    private ProjectDao projectDao;
    private SkillDao skillDao;
    private BufferedReader br;

    public View(CompanyDao companyDao, CustomerDao customerDao, DeveloperDao developerDao,
                       ProjectDao projectDao, SkillDao skillDao, BufferedReader br) {
        this.companyDao = companyDao;
        this.customerDao = customerDao;
        this.developerDao = developerDao;
        this.projectDao = projectDao;
        this.skillDao = skillDao;
        this.br = br;
    }

    public void readAll(int tableNumber) {
        try {
            switch (tableNumber) {

                case 1:
                    List<Customer> customersList = customerDao.getAll();
                    System.out.println("Список заказчиков в системе:");
                    customersList.forEach(System.out::println);
                    break;

                case 2:
                    List<Company> companyList = companyDao.getAll();
                    System.out.println("Список компаний в системе:");
                    companyList.forEach(System.out::println);
                    break;

                case 3:
                    List<Project> projectList = projectDao.getAll();
                    System.out.println("Список проектов в системе:");
                    projectList.forEach(System.out::println);
                    break;

                case 4:
                    List<Developer> developerList = developerDao.getAll();
                    System.out.println("Список разработчиков в системе:");
                    developerList.forEach(System.out::println);
                    break;

                case 5:
                    List<Skill> skillsList = skillDao.getAll();
                    System.out.println("Список навыков в системе:");
                    skillsList.forEach(System.out::println);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }

    public void addOperations(int tableNumber) {
        try {

            switch (tableNumber) {

                case 1:
                    System.out.println("Для добавления нового заказчика введите его название:");
                    String customerName = br.readLine();
                    System.out.println("и город:");
                    String customerCity = br.readLine();
                    Customer customer = customerDao.create(new Customer(customerName, customerCity));
                    if (customer != null) System.out.printf("\n%s успешно сохранен!\n", customer.getName());
                    break;

                case 2:
                    System.out.println("Для добавления новой компании введите ее название:");
                    String companyName = br.readLine();
                    System.out.println("и город:");
                    String companyCity = br.readLine();
                    Company company = companyDao.create(new Company(companyName, companyCity));
                    if (company != null) System.out.printf("\n%s успешно сохранен!\n", company.getName());
                    break;

                case 3:
                    System.out.println("Для добавления нового проекта выберите номер заказчика проекта:");
                    List<Customer> customerList = customerDao.getAll();
                    int i = 1;
                    for (Customer customerFromList : customerList) {
                        System.out.printf("%d. %s\n", i++, customerFromList.getName());
                    }
                    int customerIndex = Integer.parseInt(br.readLine());
                    Customer customerForProject = customerList.get(customerIndex - 1);

                    System.out.println("Введите название нового проекта:");
                    String projectName = br.readLine();
                    System.out.println("Введите стоимость нового проекта:");
                    int projectCost = Integer.parseInt(br.readLine());

                    Project projectSave = projectDao.create(new Project(projectName, customerForProject.getId(), projectCost));
                    System.out.printf("\n%s успешно сохранен!\n", projectSave.getName());
                    break;


                case 4:
                    System.out.println("Для добавления нового разработчика выберите номер компании из списка:");
                    List<Company> companyList = companyDao.getAll();
                    i = 1;
                    for (Company company1 : companyList) {
                        System.out.println(i++ + ". " + company1.getName());
                    }
                    int companyIndex = Integer.parseInt(br.readLine());
                    Company devCompany = companyList.get(companyIndex - 1);

                    System.out.println("Для добавления нового разработчика введите его имя:");
                    String name = br.readLine();
                    System.out.println("Введите фамилию:");
                    String surname = br.readLine();
                    System.out.println("Введите зарплату разработчика за месяц:");
                    int salary = Integer.parseInt(br.readLine());

                    Developer developer = developerDao.create(new Developer(surname, name, salary, devCompany.getId()));
                    System.out.printf("\n%s %s успешно сохранен!\n", developer.getName(), developer.getSurname());
                    break;


                case 5:
                    System.out.println("Для добавления нового навыка введите его название:");
                    String skillName = br.readLine();
                    Skill skills = skillDao.create(new Skill(skillName));
                    System.out.printf("\n%s успешно сохранен!\n", skills.getLang());
                    break;


                case 0:
                    break;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }

    @Override
    public void deleteOperations(int tableNamber) {
        try {
            switch (tableNamber) {

                case 1:
                    System.out.println("Выберите номер заказчика из списка для удаления записи по нему");
                    List<Customer> customerList = customerDao.getAll();
                    int i = 1;
                    for (Customer customer : customerList) {
                        System.out.println(i++ + ". " + customer);
                    }
                    int customerIndex = Integer.parseInt(br.readLine());
                    Customer customer = customerList.get(customerIndex - 1);
                    List<Project> customerProjectList = projectDao.getAll()
                            .stream().filter( p -> p.getId().equals(customer.getId())).collect(Collectors.toList());
                    System.out.println("Вы уверены что хотите удалить заказчика \"" + customer + "\"?");
                    System.out.println("Также вместе с ним будут удалены проекты и данные связанные с ними:");
                    customerProjectList.stream()
                            .map(Project::getName)
                            .forEach((x) -> System.out.println("Проект " + x));
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            for (Project project: customerProjectList) {
                                projectDao.delete(project.getId());
                            }
                            customerDao.delete(customer.getId());
                            System.out.println("Заказчик и все данные связанные с ним удалены!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 2:
                    System.out.println("Выберите номер компании из списка для удаления записи");
                    List<Company> companyList = companyDao.getAll();
                    i = 1;
                    for (Company company : companyList) {
                        System.out.println(i++ + ". " + company);
                    }
                    int companyIndex = Integer.parseInt(br.readLine());
                    Company company = companyList.get(companyIndex - 1);
                    System.out.println("Вы уверены что хотите удалить компанию \"" + company + "\"?");
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            companyDao.delete(company.getId());
                            System.out.println("Компания и все данные связанные с ней удалены!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 3:
                    System.out.println("Выберите номер проекта из списка для удаления записи по нему");
                    List<Project> projectList = projectDao.getAll();
                    i = 1;
                    for (Project project : projectList) {
                        System.out.println(i++ + ". " + project.getName());
                    }
                    int projectIndex = Integer.parseInt(br.readLine());
                    Project project = projectList.get(projectIndex - 1);
                    System.out.println("Вы уверены что хотите удалить проект \"" + project.getName() + "\"?");
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            projectDao.delete(project.getId());
                            System.out.println("Проект и все данные связанные с ним удалены!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 4:
                    System.out.println("Выберите номер разработчика из списка для удаления записи по нему");
                    List<Developer> developerList = developerDao.getAll();
                    i = 1;
                    for (Developer developer : developerList) {
                        System.out.println(i++ + ". " + developer.getName() + " " + developer.getSurname());
                    }
                    int developerIndex = Integer.parseInt(br.readLine());
                    Developer developer = developerList.get(developerIndex - 1);
                    System.out.println("Вы уверены что хотите удалить разработчика \"" + developer.getName() + " " + developer.getSurname() + "\"?" + developer.getId());
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            developerDao.delete(developer.getId());
                            System.out.println("Разработчик удален!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 5:
                    System.out.println("Выберите номер навыка из списка для удаления записи по нему");
                    List<Skill> skillsList = skillDao.getAll();
                    i = 1;
                    for (Skill skills : skillsList) {
                        System.out.println(i++ + ". " + skills);
                    }
                    int skillIndex = Integer.parseInt(br.readLine());
                    Skill skills = skillsList.get(skillIndex - 1);
                    System.out.println("Вы уверены что хотите удалить \"" + skills + "\"?");
                    System.out.println("\nДля подтверждения выбора введите 1 или 0 для отмены и возврата в предидущее меню:");
                    System.out.println("Сделайте Ваш выбор:");
                    while (true) {
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            skillDao.delete(skills.getId());
                            System.out.println("Навык удален!");
                            break;
                        } else if (choice == 0) {
                            break;
                        }
                        System.out.println("Команда введена не верно, повторите попытку:");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Не верный номер операции! Повторите попытку! \nДля выхода нажмите \"0\"");
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
        } catch (NoSuchElementException | NumberFormatException n) {
            System.out.println("Команда введена неверно! Повторите выбор!" + " \nДля выхода нажмите \"0\"");
        }
    }

}
