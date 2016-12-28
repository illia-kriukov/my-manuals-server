package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.company.CompanyDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.model.Company;

/**
 * Created by ilyakruikov on 12/26/16.
 */
@Component
public class CompanyToCompanyInfoDto implements Function<Company, CompanyInfoDto> {

    @Override
    public CompanyInfoDto apply(Company company) {
        CompanyInfoDto companyDto = new CompanyInfoDto();
        BeanUtils.copyProperties(company, companyDto);
        return companyDto;
    }

}