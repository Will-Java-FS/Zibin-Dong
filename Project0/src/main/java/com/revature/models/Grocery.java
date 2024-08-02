package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "groceries")
public class Grocery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "int CHECK (quantity>=0)")
    private int quantity;

    @Column(columnDefinition = "double CHECK (price>0)")
    private double price;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "fk_owner")
    @JsonBackReference
    private Account owner;
}
