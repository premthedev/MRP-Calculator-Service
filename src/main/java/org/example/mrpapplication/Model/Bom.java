package org.example.mrpapplication.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bom")
@Data
public class Bom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_item", nullable = false)
    private String parentItem;

    @Column(name = "child_item", nullable = false)
    private String childItem;

    @Column(name = "quantity", nullable = false)
    private Double quantity;
}
