package se.lnu.agile.mymanuals.controller.impl;

import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Representative;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public interface AccountController {

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    RepresentativeDto getRepresentativeInfoInfoByEmail(@RequestParam("email") String email);

}