package se.lnu.agile.mymanuals.controller.impl;

/**
 * Created by ilyakruikov on 11/10/16.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.lnu.agile.mymanuals.controller.AccountController;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.service.AccountService;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    public RepresentativeDto getRepresentativeInfoByEmail(@RequestParam("email") String email) {
        return accountService.getRepresentative(email);
    }

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.POST)
    public ResponseEntity<String> createRepresentative(@RequestParam("email") String email, @RequestParam("password") String password,
                                                    @RequestParam("name") String name, @RequestParam("companyEmail") String companyEmail,
                                                    @RequestParam("companyPassword") String companyPassword) {
        RepresentativeDto representativeDto = accountService.createRepresentative(email, password, name, companyEmail, companyPassword);
        return representativeDto != null ? ResponseEntity.status(HttpStatus.CREATED).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}