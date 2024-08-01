package com.revature.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    /*
    * For this table, we will add the general account info of users.
    * Then the basket will link to the basket table to show what this user have in
    * the basket.
    * */

    // serial id of cust
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    // username of cust
    @Column
    @Getter
    @Setter
    private String name;

    // password of cust
    @Column
    @Getter
    @Setter
    private int password;

    @Column
    @Getter
    @Setter
    private boolean is_admin;

    @OneToMany
    private List<Grocery> groceryList;

}
