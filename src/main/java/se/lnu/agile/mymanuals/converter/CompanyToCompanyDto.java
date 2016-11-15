package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.CompanyDto;
import se.lnu.agile.mymanuals.model.Company;

/**
 * Created by Lo.Gas_2 on 12/11/2016.
 */
@Component
public class CompanyToCompanyDto implements Function<Company, CompanyDto> {

    @Override
    public CompanyDto apply(Company company) {
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        return companyDto;
    }

}