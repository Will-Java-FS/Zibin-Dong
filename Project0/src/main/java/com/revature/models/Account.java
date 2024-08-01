package com.revature.models;


public class Account {
    /*
    * For this table, we will add the general account info of users.
    * Then the basket will link to the basket table to show what this user have in
    * the basket.
    * */

    // serial id of cust
    private int id;

    // username of cust
    private String username;

    // password of cust
    private int password;

    // the groceries that user had. It will link to the specific row of basket table
    private Basket basket;


}
