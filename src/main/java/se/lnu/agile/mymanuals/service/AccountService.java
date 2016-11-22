package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.*;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    void createCompany(CompanyCreateDto dto);

    RepresentativeDto getRepresentative(String email);

    void createRepresentative(RepresentativeSignUpDto dto);

}