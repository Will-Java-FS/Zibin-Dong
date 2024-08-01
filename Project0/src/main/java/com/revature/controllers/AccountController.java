package com.revature.controllers;

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
    public ResponseEntity<Account> accountLogin(@RequestBody Account a) {
        Account user = accountSerivce.login(a);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.status(401).body(null);
    }

    //Create new account
    @PostMapping(consumes = "application/json", produces ="application/json")
    public ResponseEntity<Account> addAccount(@RequestBody Account a) {
        a = as.addAccount(a);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //Delete account by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable int id) {
        return as.deleteAccountByID(id);
    }

}
