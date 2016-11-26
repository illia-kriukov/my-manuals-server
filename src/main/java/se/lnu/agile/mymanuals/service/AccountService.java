package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.company.CompanyCreateDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerInfoDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerSignUpDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeInfoDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeSignUpDto;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    void createCompany(CompanyCreateDto dto);

    void createRepresentative(RepresentativeSignUpDto dto);

    RepresentativeInfoDto getRepresentativeInfo(String email);

    void createConsumer(ConsumerSignUpDto dto);

    ConsumerInfoDto getConsumerInfo(String email);

}