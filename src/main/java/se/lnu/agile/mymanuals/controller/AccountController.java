package se.lnu.agile.mymanuals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.CompanySignUpDto;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.RepresentativeSignUpDto;

import javax.validation.Valid;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public interface AccountController {

    @RequestMapping(value = "/company", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createCompany(@RequestBody @Valid CompanySignUpDto companySignUpDto);

    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    RepresentativeDto getRepresentativeInfoByEmail(@RequestParam("email") String email);

    @RequestMapping(value = "/representative", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createRepresentative(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto);

    @RequestMapping(value = "/category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createCategory(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto);

}