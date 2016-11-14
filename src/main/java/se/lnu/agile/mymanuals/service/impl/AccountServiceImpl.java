package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.CompanyDto;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Representative;
import se.lnu.agile.mymanuals.service.AccountService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if( verifyRepresentativeSignIn(email, password, name, companyEmail, companyPassword)){
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
        return null;
    }

    /**
     * Perform verification of the representative's data at Sign-Up
     * Checks:
     * -> Company email and representative email are not the same
     * -> Representative email already exists in representative table
     * -> Representative email already exists in company table
     * -> Given companyEmail and companyPassword are correct according to company table in the DB
     * -> Representative's name format (at least two words starting with UpperCase)
     * -> Password is longer that 6 characters
     * -> Representative's Email format
     */
    private boolean verifyRepresentativeSignIn( String email,String password, String name,String companyEmail, String companyPassword){
        if(email.equals(companyEmail)){  return false; }
        if(getRepresentative(email) != null){  return false; }
        if(companyDao.findByEmail(email) != null){  return false; }
        Company company = companyDao.findByEmail(companyEmail);
        if( company == null ){ return false;}
        if( !company.getPassword().equals(companyPassword) ){ return false; }
        if (!checkName(name) || password.length() < 6 || !checkEmailFormat(email)) { return false; }
        return true;
    }

    private boolean checkEmailFormat(String email){
        String EMAIL_PATTERN =   "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkName(String name){
        String[] temp = name.split(" ");
        if(temp.length<2) { return false;}
        for(int i=0; i<temp.length; i++){
            char firstLetter = temp[i].charAt(0);
            if(firstLetter<'A' || firstLetter>'Z'){ return false;}
        }
        return true;
    }
}