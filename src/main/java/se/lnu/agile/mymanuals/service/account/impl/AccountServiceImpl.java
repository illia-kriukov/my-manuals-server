package se.lnu.agile.mymanuals.service.account.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
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
    private RepresentativeToRepresentativeDto representativeConverter;

    @Override
    public RepresentativeDto getRepresentative(String email) {
        Representative representative = representativeDao.findByEmail(email);
        return representative != null ? representativeConverter.apply(representative) : null;
    }

}