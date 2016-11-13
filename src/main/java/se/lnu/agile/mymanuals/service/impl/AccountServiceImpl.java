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
        if( EvaluateRepresentativeSignIn (email, password, name, companyEmail, companyPassword)){
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

    private boolean EvaluateRepresentativeSignIn( String email,String password, String name,String cemail, String cpassword){
        // Check that company email and representative email are not the same
        if(email == cemail){  return false; }

        // Representative email already exists in representative table
        if(getRepresentative(email) != null){  return false; }

        // Representative email already exists in company table
        if(companyDao.findByEmail(email) != null){  return false; }

        // Check that given company_email and company_password are correct
        Company company = companyDao.findByEmail(cemail);
        if( company == null ){ return false;}  //Company_email doesn't exist in database
        if( !company.getPassword().equals(cpassword) ){ return false; } // Wrong company_password


        // Check name
        if( !CheckName(name) ){ return false;}

        // Check that password is longer that 6 characters
        if( password.length()<6) { return false; }

        // Check email format
        if( !CheckEmailFormat(email) ){ return false;}

        // Evaluation passed successfully
        return true;
    }

    private boolean CheckEmailFormat(String email){
        String EMAIL_PATTERN =   "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean CheckName(String name){
        String[] temp = name.split(" ");
        // Check that name is at least two words (name-surname)
        if(temp.length<2) { return false;}
        // Check that words start with Upper case letter
        for(int i=0; i<temp.length; i++){
            char firstLetter = temp[i].charAt(0);
            if(firstLetter<'A' || firstLetter>'Z'){ return false;}
        }
        return true;
    }
}