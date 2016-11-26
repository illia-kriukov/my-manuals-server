package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.company.CompanyDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeInfoDto;
import se.lnu.agile.mymanuals.model.Representative;

/**
 * Created by ilyakruikov on 11/11/16.
 */
@Component
public class RepresentativeToRepresentativeInfoDto implements Function<Representative, RepresentativeInfoDto> {

    @Override
    public RepresentativeInfoDto apply(Representative representative) {
        RepresentativeInfoDto representativeInfoDto = new RepresentativeInfoDto();
        BeanUtils.copyProperties(representative, representativeInfoDto);

        CompanyInfoDto companyInfoDto = new CompanyInfoDto();
        BeanUtils.copyProperties(representative.getCompany(), companyInfoDto);

        representativeInfoDto.setCompany(companyInfoDto);
        return representativeInfoDto;
    }

}