package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
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
    public RepresentativeDto createRepresentative(String email, String password, String name, String companyEmail, String companyPassword) {
        Representative representative = new Representative();
        representative.setEmail(email);
        representative.setPassword(password);
        representative.setName(name);
        representative.setCompany(companyDao.findByEmail(companyEmail));
        try {
            return representativeConverter.apply(representativeDao.save(representative));
        } catch (Exception e) {
            return null;
        }
    }

}