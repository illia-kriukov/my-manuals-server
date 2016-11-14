package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.RepresentativeSignUpDto;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Representative;
import se.lnu.agile.mymanuals.service.AccountService;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RepresentativeDao representativeDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private RepresentativeToRepresentativeDto representativeConverter;

    @Override
    public RepresentativeDto getRepresentative(String email) {
        Representative representative = representativeDao.findByEmail(email);
        return representative != null ? representativeConverter.apply(representative) : null;
    }

    @Override
    public void createRepresentative(RepresentativeSignUpDto dto) {
        if (validateRepresentativeSignUp(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getCompanyEmail(), dto.getCompanyPassword())) {
            Representative representative =
                    new Representative(dto.getEmail(), dto.getPassword(), dto.getName(), companyDao.findByEmail(dto.getCompanyEmail()));
            representativeConverter.apply(representativeDao.save(representative));
        }
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
            String msg = "Failed to create representative '" + email + "'. The representative email and the company email are the same.";
            throw new RegistrationException(msg);
        } else if (getRepresentative(email) != null){
            String msg = "Failed to create representative '" + email + "'. The representative with such email already exists.";
            throw new RegistrationException(msg);
        } else if (companyDao.findByEmail(email) != null){
            String msg = "Failed to create representative '" + email + "'. The company with such email already exists.";
            throw new RegistrationException(msg);
        }

        Company company = companyDao.findByEmail(companyEmail);

        if (company == null) {
            throw new RegistrationException("Failed to create representative '" + email + "'. The company with such email does not exists.");
        } else if (!company.getPassword().equals(companyPassword)) {
            throw new RegistrationException("Failed to create representative '" + email + "'. The company password is incorrect.");
        }

        return true;
    }

}