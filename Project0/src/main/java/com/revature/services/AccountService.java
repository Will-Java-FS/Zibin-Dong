package com.revature.services;

import com.revature.exception.ClientErrorException;
import com.revature.exception.DuplicateNameException;
import com.revature.exception.UnAuthorizedException;
import com.revature.models.Account;
import com.revature.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepo accountRepo;
    public AccountService(AccountRepo accountRepo)
    {
        this.accountRepo = accountRepo;
    }

    public Account createAccount(Account account) throws ClientErrorException, DuplicateNameException
    {
        if(account.getName().isEmpty())
        {
            throw new ClientErrorException();
        }
        if(account.getPassword().length() < 4)
        {
            throw new ClientErrorException();
        }

        if(accountRepo.searchAccountByName(account.getName()) != null)
        {
            throw new DuplicateNameException();
        }
        return accountRepo.save(account);
    }

    public Account login(Account account) throws UnAuthorizedException
    {
        if(accountRepo.searchAccountByName(account.getName()) == null)
        {
            throw new UnAuthorizedException();
        }
        if(!accountRepo.searchAccountByName(account.getName()).getPassword().equals(account.getPassword()))
        {
            throw new UnAuthorizedException();
        }
        return accountRepo.searchAccountByName(account.getName());
    }
}
