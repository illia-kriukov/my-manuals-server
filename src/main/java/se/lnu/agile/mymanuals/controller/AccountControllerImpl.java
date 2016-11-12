package se.lnu.agile.mymanuals.controller;

/**
 * Created by ilyakruikov on 11/10/16.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.lnu.agile.mymanuals.controller.impl.AccountController;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Representative;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.service.account.AccountService;
import se.lnu.agile.mymanuals.service.account.impl.AccountServiceImpl;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    public RepresentativeDto getRepresentativeInfoInfoByEmail(@RequestParam("email") String email) {
        return accountService.getRepresentative(email);
    }

    @Override
    public ResponseEntity<String> addRepresentative(@RequestParam("id") long id,
                                                    @RequestParam("email") String email, @RequestParam("password") String password,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("companyEmail") String companyEmail) {
        RepresentativeDto representativeDto=accountService.addRepresentative(id,email,password,name,companyEmail);
        if(representativeDto!=null) return ResponseEntity.status(HttpStatus.CREATED).body(null);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


    }

}