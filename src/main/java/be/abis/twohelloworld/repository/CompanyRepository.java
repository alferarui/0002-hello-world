package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.model.Company;

import java.util.List;

public interface CompanyRepository {
    List<Company> getAll();
    Company getCompanyById(String companyId);
    List<Company> getCompaniesMatching(String contentMatch);
    Company createCompany(Company c);
    Company updateCompany(Company c);
    Company deleteCompany(Company c);
}
