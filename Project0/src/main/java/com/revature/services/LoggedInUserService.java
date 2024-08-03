package com.revature.services;

import com.revature.models.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUserService {
    private static LoggedInUserService singleton = null;

    @Getter
    @Setter
    private Account loggedInUser;

    private LoggedInUserService() {
        loggedInUser = null;
    }

    public static synchronized LoggedInUserService getInstance()
    {
        if (singleton == null) singleton = new LoggedInUserService();
        return singleton;
    }
}
