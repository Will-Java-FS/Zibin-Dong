package com.revature.controllers;

import com.revature.exception.ClientErrorException;
import com.revature.exception.DuplicateNameException;
import com.revature.exception.UnAuthorizedException;
import com.revature.models.Account;
import com.revature.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    AccountService as;

    @Autowired
    public AccountController(AccountService as) {this.as = as;}

    //Login to account
    @PostMapping
    public ResponseEntity<Account> accountLogin(@RequestBody Account a) throws UnAuthorizedException{
        a = as.login(a);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //Create new account
    @PostMapping(consumes = "application/json", produces ="application/json")
    public ResponseEntity<Account> addAccount(@RequestBody Account a) throws ClientErrorException, DuplicateNameException {
        a = as.createAccount(a);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
