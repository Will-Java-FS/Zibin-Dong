package com.revature.services;

import com.revature.exception.ClientErrorException;
import com.revature.exception.UnAuthorizedException;
import com.revature.models.Account;
import com.revature.models.Grocery;
import com.revature.repositories.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {
    GroceryRepo groceryRepo;

    @Autowired
    public GroceryService(GroceryRepo groceryRepo)
    {
        this.groceryRepo = groceryRepo;
    }

    public Grocery addGrocery(Grocery grocery) throws ClientErrorException, UnAuthorizedException {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();

        if(loggedInUser == null) throw new UnAuthorizedException();
        if(grocery.getName() == null) throw new ClientErrorException();
        if(grocery.getPrice().compareTo(new BigDecimal("0.0")) <= 0) throw new ClientErrorException();
        if(grocery.getQuantity() < 1) throw new ClientErrorException();

        grocery.setOwner(loggedInUser);
        loggedInUser.getGroceryList().add(grocery);
        return groceryRepo.save(grocery);
    }

    public List<Grocery> getAllGroceries() {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();

        if (loggedInUser.is_admin()) return groceryRepo.findAll();
        return loggedInUser.getGroceryList();
    }

    public Grocery getGroceryByID(int ID) {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();
        Optional<Grocery> optionalGrocery = groceryRepo.findById(ID);

        if (optionalGrocery.isPresent()
                && (optionalGrocery.get().getOwner().equals(loggedInUser)
                || loggedInUser.is_admin())) {
            return optionalGrocery.get();
        } else {
            return null;
        }
    }

    public void updateGrocery(Grocery grocery) throws ClientErrorException {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();

        if(loggedInUser == null) throw new ClientErrorException();
        if(grocery.getName() == null) throw new ClientErrorException();
        if(grocery.getPrice().compareTo(new BigDecimal("0.0")) > 0) throw new ClientErrorException();
        if(grocery.getQuantity() < 1) throw new ClientErrorException();

        Optional<Grocery> optionalGrocery = groceryRepo.findById(grocery.getId());
        if(optionalGrocery.isPresent()) {
            Grocery temp = optionalGrocery.get();
            temp = grocery;
            if (temp.getOwner() == loggedInUser || loggedInUser.is_admin()) groceryRepo.save(temp);
        } else {
            throw new ClientErrorException();
        }
    }

    public void deleteGroceryByID(int ID) {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();
        Optional<Grocery> optionalGrocery = groceryRepo.findById(ID);

        if (optionalGrocery.isPresent()) {
            if (optionalGrocery.get().getOwner().equals(loggedInUser) || loggedInUser.is_admin()) {
                groceryRepo.deleteById(ID);
            }
        }
    }

}
