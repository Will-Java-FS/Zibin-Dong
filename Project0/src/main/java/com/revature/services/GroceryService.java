package com.revature.services;

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

    public Grocery addGrocery(Grocery grocery)
    {
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

    public Grocery updateGrocery(Grocery grocery)
    {
        Optional<Grocery> optionalGrocery = groceryRepo.findById(grocery.getId());
        if(optionalGrocery.isPresent())
        {
            Grocery temp = optionalGrocery.get();
            temp = grocery;
            groceryRepo.save(temp);
            return temp;
        }
        return null;
    }

    public void deleteGroceryByID(int ID)
    {
        groceryRepo.deleteById(ID);
    }
}
