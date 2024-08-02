package com.revature.controllers;

import com.revature.exception.ClientErrorException;
import com.revature.models.Grocery;
import com.revature.services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Grocery> addGrocery(@RequestBody Grocery g) throws ClientErrorException
    {
        g = gs.addGrocery(g);
        return new ResponseEntity<>(g, HttpStatus.OK);
    }

    //Update item
    @PutMapping("/{id}")
    public ResponseEntity<Grocery> updateGrocery(@PathVariable int id, @RequestBody Grocery g) throws ClientErrorException
    {
        g.setId(id);
        Grocery gOld = gs.getGroceryByID(id);
        if (gOld.getId() == id) {
            g = gs.updateGrocery(g);
            return new ResponseEntity<>(g, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    //Delete item by id
    @DeleteMapping("/{id}")
    public void deleteGrocery(@PathVariable int id) {
        gs.deleteGroceryByID(id);
    }

}
