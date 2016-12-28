package se.lnu.agile.mymanuals.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import se.lnu.agile.mymanuals.controller.AccountController;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.company.CompanyCreateDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerInfoDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerSignUpDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeInfoDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeSignUpDto;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.service.AccountService;
import se.lnu.agile.mymanuals.error.ValidationError;
import se.lnu.agile.mymanuals.error.ValidationErrorBuilder;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/account/authorities", method = RequestMethod.GET)
    public GrantedAuthority getUserAuthorities(Authentication authentication) {
        return ((List<GrantedAuthority>) authentication.getAuthorities()).get(0);
    }

    @Override
    @RequestMapping(value = "/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
        accountService.createCompany(companyCreateDto);
    }

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createRepresentative(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto) {
        accountService.createRepresentative(representativeSignUpDto);
    }

    @Override
    public RepresentativeInfoDto getRepresentativeInfo(@AuthenticationPrincipal Principal principal) {
        return accountService.getRepresentativeInfo(principal.getName());
    }

    @Override
    @RequestMapping(value = "/consumer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createConsumer(@RequestBody @Valid ConsumerSignUpDto consumerSignUpDto) {
        accountService.createConsumer(consumerSignUpDto);
    }

    @Override
    public ConsumerInfoDto getConsumerInfo(@AuthenticationPrincipal Principal principal) {
        return accountService.getConsumerInfo(principal.getName());
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