package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.company.CompanyDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Representative;

/**
 * Created by ilyakruikov on 11/11/16.
 */
@Component
public class RepresentativeToRepresentativeDto implements Function<Representative, RepresentativeDto> {

    @Override
    public RepresentativeDto apply(Representative representative) {
        RepresentativeDto representativeDto = new RepresentativeDto();
        BeanUtils.copyProperties(representative, representativeDto);

        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(representative.getCompany(), companyDto);

        representativeDto.setCompany(companyDto);
        return representativeDto;
    }

}