package se.lnu.agile.mymanuals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ilyakruikov on 11/11/16.
 */
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public interface AccountController {

    @RequestMapping(value = "/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createCompany(@RequestBody @Valid CompanySignUpDto companySignUpDto);

    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    RepresentativeDto getRepresentativeInfoByEmail(@RequestParam("email") String email);

    @RequestMapping(value = "/representative", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createRepresentative(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto);

}