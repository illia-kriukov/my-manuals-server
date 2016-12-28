package se.lnu.agile.mymanuals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.company.CompanyCreateDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerInfoDto;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerSignUpDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeInfoDto;
import se.lnu.agile.mymanuals.dto.representative.RepresentativeSignUpDto;

import javax.validation.Valid;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ilyakruikov on 11/11/16.
 */
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public interface AccountController {

    @RequestMapping(value = "/account/current/authorities", method = RequestMethod.GET)
    GrantedAuthority getUserAuthorities(Authentication authentication);

    @RequestMapping(value = "/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createCompany(@RequestBody @Valid CompanyCreateDto companyCreateDto);

    @RequestMapping(value = "/representative", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createRepresentative(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto);

    @RequestMapping(value = "/account/current/representative", method = RequestMethod.GET)
    RepresentativeInfoDto getRepresentativeInfo(@AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "/consumer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createConsumer(@RequestBody @Valid ConsumerSignUpDto consumerSignUpDto);

    @RequestMapping(value = "account/current/consumer", method = RequestMethod.GET)
    ConsumerInfoDto getConsumerInfo(@AuthenticationPrincipal Principal principal);

}