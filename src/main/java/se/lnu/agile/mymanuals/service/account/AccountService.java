package se.lnu.agile.mymanuals.service.account;

import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Representative;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    RepresentativeDto getRepresentative(String email);

}