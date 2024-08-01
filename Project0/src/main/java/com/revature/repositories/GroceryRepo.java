package com.revature.repositories;
import com.revature.models.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepo extends JpaRepository<Grocery, Integer> {

}
