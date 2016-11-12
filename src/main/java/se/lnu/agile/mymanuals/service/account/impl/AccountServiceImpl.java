package se.lnu.agile.mymanuals.service.account.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.RepresentativeDtoToRepresentative;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.CompanyDto;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Representative;
import se.lnu.agile.mymanuals.service.account.AccountService;

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
    public RepresentativeDto addRepresentative(long id, String email, String password, String name, String companyEmail) {
        Representative representative= new Representative();
        representative.setId(id);
        representative.setEmail(email);
        representative.setPassword(password);
        representative.setName(name);
        Company company=companyDao.findByEmail(companyEmail);
        representative.setCompany(company);
        try {
            return representativeConverter.apply(representativeDao.save(representative));
        }catch (Exception e) {
            return null;
        }




            }


    }

