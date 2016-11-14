package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.RepresentativeSignUpDto;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    RepresentativeDto getRepresentative(String email);

    void createRepresentative(RepresentativeSignUpDto representativeSignUpDto);

}