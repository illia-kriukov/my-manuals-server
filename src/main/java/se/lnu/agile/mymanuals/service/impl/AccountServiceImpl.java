package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.CategoryToCategoryDto;
import se.lnu.agile.mymanuals.converter.CompanyToCompanyDto;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CategoryDao;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.CategorySignUpDto;
import se.lnu.agile.mymanuals.dto.CompanySignUpDto;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.RepresentativeSignUpDto;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.model.Category;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Representative;
import se.lnu.agile.mymanuals.service.AccountService;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private RepresentativeDao representativeDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CompanyToCompanyDto companyConverter;

    @Autowired
    private RepresentativeToRepresentativeDto representativeConverter;

    @Autowired
    private CategoryToCategoryDto categoryConverter;

    @Override
    public void createCompany(CompanySignUpDto dto) {
        if (validateCompanySignUp(dto.getEmail())) {
            Company company = new Company(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getDescription());
            companyConverter.apply(companyDao.save(company));
        }
    }

    @Override
    public RepresentativeDto getRepresentative(String email) {
        Representative representative = representativeDao.findByEmail(email);
        return representative != null ? representativeConverter.apply(representative) : null;
    }

    @Override
    public void createRepresentative(RepresentativeSignUpDto dto) {
        if (validateRepresentativeSignUp(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getCompanyEmail(),
                dto.getCompanyPassword())) {
            Representative representative =
                    new Representative(dto.getEmail(), dto.getPassword(), dto.getName(),
                            companyDao.findByEmail(dto.getCompanyEmail()));
            representativeConverter.apply(representativeDao.save(representative));
        }
    }

    @Override
    public void createCategory(CategorySignUpDto dto) {
        if (validateCategorySignUp(dto.getName())){
            Category category = new Category(dto.getName());
            categoryConverter.apply(categoryDao.save(category));
        }
    }

    /**
     * Perform validation of the companies's data at Sign-Up.
     *
     * Checks:
     * -> Company email doesn't exists in company table
     */
    private boolean validateCompanySignUp(String email){
        if (companyDao.findByEmail(email) != null) {
            String msg = "Failed to create company '%s'. The company with such email already exists.";
            throw new RegistrationException(String.format(msg, email));
        }
        return true;
    }

    /**
     * Perform validation of the representative's data at Sign-Up.
     *
     * Checks:
     * -> Company email and representative email are not the same
     * -> Representative email doesn't exists in representative table
     * -> Representative email doesn't exists in company table
     * -> Given companyEmail and companyPassword are correct according to company table in the DB
     */
    private boolean validateRepresentativeSignUp(String email, String password, String name, String companyEmail, String companyPassword){
        if (email.equals(companyEmail)) {
            String msg = "Failed to create representative '%s'. The representative email and the company email are the same.";
            throw new RegistrationException(String.format(msg, email));
        } else if (representativeDao.findByEmail(email) != null) {
            String msg = "Failed to create representative '%s'. The representative with such email already exists.";
            throw new RegistrationException(String.format(msg, email));
        } else if (companyDao.findByEmail(email) != null) {
            String msg = "Failed to create representative '%s'. The company with such email already exists.";
            throw new RegistrationException(String.format(msg, email));
        }

        Company company = companyDao.findByEmail(companyEmail);

        if (company == null) {
            String msg = "Failed to create representative '%s'. The company with such email does not exists.";
            throw new RegistrationException(String.format(msg, email));
        } else if (!company.getPassword().equals(companyPassword)) {
            String msg = "Failed to create representative '%s'. The company password is incorrect.";
            throw new RegistrationException(String.format(msg, email));
        }

        return true;
    }

    /**
     * Perform validation of the category's data at Sign-Up.
     *
     * Checks:
     * -> Category name doesn't exists in category table
     */
    private boolean validateCategorySignUp(String name){
        if (categoryDao.findByName(name) != null) {
            String msg = "Failed to create category '%s'. A category with such a name already exists.";
            throw new RegistrationException(String.format(msg, name));
        }
        return true;
    }

}