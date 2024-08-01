package com.revature.models;


import jakarta.persistence.*;

public class Account {
    /*
    * For this table, we will add the general account info of users.
    * Then the basket will link to the basket table to show what this user have in
    * the basket.
    * */

    // serial id of cust
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // username of cust
    @Column
    private String name;

    // password of cust
    @Column
    private int password;

    private boolean is_admin;

}
