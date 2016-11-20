package se.lnu.agile.mymanuals.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import se.lnu.agile.mymanuals.controller.AccountController;
import se.lnu.agile.mymanuals.dto.*;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.service.AccountService;
import se.lnu.agile.mymanuals.error.ValidationError;
import se.lnu.agile.mymanuals.error.ValidationErrorBuilder;

import javax.validation.Valid;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/company", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCompany(@RequestBody @Valid CompanySignUpDto companySignUpDto) {
        accountService.createCompany(companySignUpDto);
    }

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    public RepresentativeDto getRepresentativeInfoByEmail(@RequestParam("email") String email) {
        return accountService.getRepresentative(email);
    }

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createRepresentative(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto) {
        accountService.createRepresentative(representativeSignUpDto);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(RegistrationException e) {
        return ValidationErrorBuilder.fromException(e);
    }

}