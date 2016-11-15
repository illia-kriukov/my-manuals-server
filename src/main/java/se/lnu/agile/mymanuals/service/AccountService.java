package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.CompanySignUpDto;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.RepresentativeSignUpDto;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    void createCompany(CompanySignUpDto dto);

    RepresentativeDto getRepresentative(String email);

    void createRepresentative(RepresentativeSignUpDto dto);

}