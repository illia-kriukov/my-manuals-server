package se.lnu.agile.mymanuals.controller;

/**
 * Created by ilyakruikov on 11/10/16.
 */
import org.springframework.beans.factory.annotation.Autowired;
import se.lnu.agile.mymanuals.controller.impl.AccountController;
import se.lnu.agile.mymanuals.dto.RepresentativeDto;
import se.lnu.agile.mymanuals.model.Representative;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.service.account.AccountService;
import se.lnu.agile.mymanuals.service.account.impl.AccountServiceImpl;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService = new AccountServiceImpl();

    @Override
    @RequestMapping(value = "/representative", method = RequestMethod.GET)
    public RepresentativeDto getRepresentativeInfoInfoByEmail(@RequestParam("email") String email) {
        return accountService.getRepresentative(email);
    }

}