package org.example.mrpapplication.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "part_name", nullable = false, unique = true)
    private String partName;

    @Column(name = "stock_quantity", nullable = false)
    private Double stockQuantity;

}

