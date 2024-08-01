package com.revature.models;
import java.util.List;

public class Cart {
    /*
    * For this table, the first column will be the customer id, the rest columns will
    * be the name of grocery. For each row, we will fill in the quantity for each grocery.
    *  */


    // the serial id of a basket
    private int id;

    // the cust_id this basket belongs to
    private int cust_id;

    // the list to groceries that user had
    private List<Grocery> cart;

}
