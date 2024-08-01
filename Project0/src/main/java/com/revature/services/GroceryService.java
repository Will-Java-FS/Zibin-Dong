package com.revature.services;

import com.revature.models.Grocery;
import com.revature.repositories.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GroceryService {
    GroceryRepo groceryRepo;

    @Autowired
    public GroceryService(GroceryRepo groceryRepo)
    {
        this.groceryRepo = groceryRepo;
    }

    public Grocery addGrocery(Grocery grocery)
    {
        return groceryRepo.save(grocery);
    }

    public List<Grocery> getAllGrocery()
    {
        return groceryRepo.findAll();
    }

    public Grocery getGroceryByID(int ID)
    {
        Optional<Grocery> optionalGrocery = groceryRepo.findById(ID);
        if(optionalGrocery.isPresent())
        {
            return optionalGrocery.get();
        }
        return null;
    }

    public void updateGrocery(Grocery grocery)
    {
        Optional<Grocery> optionalGrocery = groceryRepo.findById(grocery.getId());
        if(optionalGrocery.isPresent())
        {
            Grocery temp = optionalGrocery.get();
            temp = grocery;
            groceryRepo.save(temp);
        }
    }

    public void deleteGroceryByID(int ID)
    {
        groceryRepo.deleteById(ID);
    }
}
