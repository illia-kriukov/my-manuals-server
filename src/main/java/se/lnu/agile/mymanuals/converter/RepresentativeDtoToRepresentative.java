package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Representative;

/**
 * Created by ToMeg on 2016-11-11.
 */
@Component
public class RepresentativeDtoToRepresentative implements Function<RepresentativeDto, Representative> {

    @Override
    public Representative apply (RepresentativeDto representativeDto) {
        Representative representative = new Representative();
        BeanUtils.copyProperties(representativeDto, representative);
        Company company = new Company();
        BeanUtils.copyProperties(representativeDto.getCompany(), company);
        representative.setCompany(company);
        return representative;
    }
}
