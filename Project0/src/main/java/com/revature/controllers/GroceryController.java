package com.revature.controllers;

import com.revature.exception.ClientErrorException;
import com.revature.exception.NotFoundException;
import com.revature.exception.UnAuthorizedException;
import com.revature.models.Grocery;
import com.revature.services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groceries")
@CrossOrigin
public class GroceryController {
    GroceryService gs;

    @Autowired
    public GroceryController(GroceryService gs) {this.gs = gs;}

    //View all items
    @GetMapping
    public List<Grocery> getAllGroceries() throws UnAuthorizedException {return gs.getAllGroceries();}

    //View item by id
    @GetMapping("/{id}")
    public Grocery getGrocery(@PathVariable int id) {return gs.getGroceryByID(id);}

    //Create new item
    @PostMapping
    public @ResponseBody ResponseEntity<Grocery> addGrocery(@RequestBody Grocery g) throws ClientErrorException, UnAuthorizedException {
        return ResponseEntity.status(200).body(gs.addGrocery(g));
    }

    //Update item
    @PutMapping("/update/{id}")
    public @ResponseBody ResponseEntity<Integer> updateGrocery(@PathVariable int id, @RequestBody Grocery g) throws ClientErrorException, NotFoundException {
        gs.updateGrocery(id, g);
        return ResponseEntity.status(200).body(1);
    }

    //Delete item by id
    @DeleteMapping("/{id}")
    public void deleteGrocery(@PathVariable int id) {
        gs.deleteGroceryByID(id);
    }
}
