package org.example.mrpapplication.Repository;

import org.example.mrpapplication.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByPartName(String partName);
}

