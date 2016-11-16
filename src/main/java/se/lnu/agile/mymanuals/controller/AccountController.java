package se.lnu.agile.mymanuals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.dto.RepresentativeSignUpDto;

import javax.validation.Valid;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public interface AccountController {
    @CrossOrigin
    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    RepresentativeDto getRepresentativeInfoByEmail(@RequestParam("email") String email);

    @CrossOrigin
    @RequestMapping(value = "/representative", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createRepresentative(@RequestBody @Valid RepresentativeSignUpDto representativeSignUpDto);

}