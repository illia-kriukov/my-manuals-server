package se.lnu.agile.mymanuals.service.account.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Representative;
import se.lnu.agile.mymanuals.service.account.AccountService;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RepresentativeDao representativeDao;

    @Override
    public RepresentativeDto getRepresentative(String email) {
        RepresentativeDto representativeDto = new RepresentativeDto();
        Representative representative = representativeDao.findByEmail(email);
        if (representative!=null) {
            BeanUtils.copyProperties(representative, representativeDto);
            return representativeDto;
        }
        return null;
    }

}