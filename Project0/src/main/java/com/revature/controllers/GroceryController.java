package com.revature.controllers;

import com.revature.exception.ClientErrorException;
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
    public List<Grocery> getAllGroceries() {return gs.getAllGroceries();}

    //View item by id
    @GetMapping("/{id}")
    public Grocery getGrocery(@PathVariable int id) {return gs.getGroceryByID(id);}

    //Create new item
    @PostMapping(consumes = "application/json", produces ="application/json")
    public @ResponseBody ResponseEntity<Grocery> addGrocery(@RequestBody Grocery g) throws ClientErrorException {
        return ResponseEntity.status(200).body(gs.addGrocery(g));
    }

    //Update item
    @PutMapping("/update")
    public @ResponseBody ResponseEntity<Integer> updateGrocery(@RequestBody Grocery g) throws ClientErrorException {
        gs.updateGrocery(g);
        return ResponseEntity.status(200).body(1);
    }

    //Delete item by id
    @DeleteMapping("/{id}")
    public void deleteGrocery(@PathVariable int id) {
        gs.deleteGroceryByID(id);
    }
}
