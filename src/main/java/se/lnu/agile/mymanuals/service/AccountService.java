package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.CompanyDto;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    RepresentativeDto getRepresentative(String email);

    RepresentativeDto createRepresentative (String email, String password, String name, String companyEmail, String companyPassword);

}