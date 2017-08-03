package com.dmitriy.hw.dao;

import com.dmitriy.hw.model.Company;
import com.dmitriy.hw.model.Project;

import java.util.List;

public interface CompanyDao extends BaseDao<Company> {
    List<Project> getCompanyProjects(Long companyId);
    boolean connectProject(Long companyId, Long projectId);
    boolean disconnectProject(Long companyId, Long projectId);
}
