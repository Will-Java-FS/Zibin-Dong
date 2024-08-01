package com.revature.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


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
    private int id;

    // username of cust
    @Column
    private String name;

    // password of cust
    @Column
    private String password;

    @Column
    private boolean is_admin;

    @OneToMany
    private List<Grocery> groceryList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId() && getPassword() == account.getPassword() && isIs_admin() == account.isIs_admin() && Objects.equals(getName(), account.getName()) && Objects.equals(groceryList, account.groceryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), isIs_admin(), groceryList);
    }

    @Override
    public String toString() {
        return "Account{" +
                "groceryList=" + groceryList +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password=" + password +
                ", is_admin=" + is_admin +
                '}';
    }
}
