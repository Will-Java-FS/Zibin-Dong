package com.revature.services;

import com.revature.exception.ClientErrorException;
import com.revature.models.Grocery;
import com.revature.repositories.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Grocery addGrocery(Grocery grocery) throws ClientErrorException
    {
        if(grocery.getName() == null)
        {
            throw new ClientErrorException();
        }

        if(grocery.getPrice() <= 0)
        {
            throw new ClientErrorException();
        }

        if(grocery.getQuantity() < 1)
        {
            throw new ClientErrorException();
        }
        return groceryRepo.save(grocery);
    }

    public List<Grocery> getAllGroceries()
    {
        return groceryRepo.findAll();
    }

    public Grocery getGroceryByID(int ID)
    {
        Optional<Grocery> optionalGrocery = groceryRepo.findById(ID);
        return optionalGrocery.orElse(null);
    }

    public void updateGrocery(Grocery grocery) throws ClientErrorException
    {
        if(grocery.getName() == null)
        {
            throw new ClientErrorException();
        }

        if(grocery.getPrice() <= 0)
        {
            throw new ClientErrorException();
        }

        if(grocery.getQuantity() < 1)
        {
            throw new ClientErrorException();
        }

        Optional<Grocery> optionalGrocery = groceryRepo.findById(grocery.getId());
        if(optionalGrocery.isPresent())
        {
            Grocery temp = optionalGrocery.get();
            temp = grocery;
            groceryRepo.save(temp);
        }
        else
        {
            throw new ClientErrorException();
        }
    }

    public void deleteGroceryByID(int ID)
    {
        groceryRepo.deleteById(ID);
    }
}
