package com.revature.services;

import com.revature.exception.ClientErrorException;
import com.revature.exception.NotFoundException;
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
                && (optionalGrocery.get().getOwner().getId() == loggedInUser.getId()
                || loggedInUser.is_admin())) {
            return optionalGrocery.get();
        } else {
            return null;
        }
    }

    public void updateGrocery(int id, Grocery grocery) throws ClientErrorException, NotFoundException {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();

        if(loggedInUser == null) throw new ClientErrorException();

        Optional<Grocery> optionalGrocery = groceryRepo.findById(id);
        if(optionalGrocery.isPresent()) {
            Grocery temp = optionalGrocery.get();
            if (temp.getOwner().getId() == loggedInUser.getId() || loggedInUser.is_admin()) {
                if(grocery.getName() != null) temp.setName(grocery.getName());
                if(grocery.getPrice() != null) {
                    if (grocery.getPrice().compareTo(new BigDecimal("0.0")) > 0) throw new ClientErrorException();
                    else temp.setPrice(grocery.getPrice());
                }
                if(grocery.getQuantity() != null) {
                    if(grocery.getQuantity() < 1) throw new ClientErrorException();
                    else temp.setQuantity(grocery.getQuantity());
                }
                groceryRepo.save(temp);
            }
        } else {
            throw new NotFoundException();
        }
    }

    public void deleteGroceryByID(int ID) {
        Account loggedInUser = LoggedInUserService.getInstance().getLoggedInUser();
        Optional<Grocery> optionalGrocery = groceryRepo.findById(ID);

        if (optionalGrocery.isPresent()) {
            Account owner = optionalGrocery.get().getOwner();
            if (owner.getId() == loggedInUser.getId() || loggedInUser.is_admin()) {
                owner.getGroceryList().remove(optionalGrocery.get());
                groceryRepo.deleteById(ID);
            }
        }
    }

}
