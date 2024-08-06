package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    @Query("From Account WHERE name = :nameVar")
    public Account searchAccountByName(@Param("nameVar") String name);
}
