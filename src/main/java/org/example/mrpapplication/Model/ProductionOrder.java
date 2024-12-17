package org.example.mrpapplication.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "production_orders")
@Data
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "required_qty", nullable = false)
    private Double requiredQty;

    // Getters and Setters
}

