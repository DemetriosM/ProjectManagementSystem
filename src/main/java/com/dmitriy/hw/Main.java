package com.dmitriy.hw;

import com.dmitriy.hw.dao.*;
import com.dmitriy.hw.dao.impl.jdbc.module2.*;
import com.dmitriy.hw.ui.View;
import com.dmitriy.hw.ui.ViewApi;
import com.dmitriy.hw.ui.ViewHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        CompanyDao companyDAO = new CompanyDaoImpl();
        CustomerDao customerDAO = new CustomerDaoImpl();
        DeveloperDao developerDAO = new DeveloperDaoImpl();
        ProjectDao projectDAO = new ProjectDaoImpl();
        SkillDao skillDAO = new SkillDaoImpl();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            ViewApi api = new View(companyDAO, customerDAO, developerDAO, projectDAO, skillDAO, bufferedReader);
            ViewHelper consoleHelper = new ViewHelper(api, bufferedReader);
            consoleHelper.chooseMainOperations();
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода данных!");
        }
    }

}
