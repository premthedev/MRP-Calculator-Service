package org.example.mrpapplication.Service;

import org.example.mrpapplication.Model.Bom;
import org.example.mrpapplication.Model.Inventory;
import org.example.mrpapplication.Repository.BOMRepository;
import org.example.mrpapplication.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// MRPService.java
@Service
public class MRPService {
    @Autowired
    private BOMRepository bomRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Map<String, Object>> calculateMRP(String itemName, int quantity) {
        List<Map<String, Object>> result = new ArrayList<>();
        calculateMRPRecursive(itemName, quantity, 1, result);
        return result;
    }

    private void calculateMRPRecursive(String itemName, double parentQty, int level, List<Map<String, Object>> result) {
        List<Bom> bomList = bomRepository.findByParentItem(itemName);

        for (Bom bom : bomList) {
            String part = bom.getChildItem();
            // Calculate required quantity based on parent quantity and BOM ratio
            double requiredQty = bom.getQuantity() * parentQty;

            // Get inventory
            Inventory inventory = inventoryRepository.findByPartName(part).orElse(new Inventory());
            double onHandInventory = inventory.getStockQuantity() != null ? inventory.getStockQuantity() : 0;

            // Calculate net requirement
            double toBeProcured = Math.max(0, requiredQty - onHandInventory);

            // Create result map
            Map<String, Object> partInfo = new HashMap<>();
            partInfo.put("part", part);
            partInfo.put("level", level);
            partInfo.put("requiredQty", requiredQty);
            partInfo.put("onHandInventory", onHandInventory);
            partInfo.put("toBeProcured", toBeProcured);

            List<Bom> subComponents = bomRepository.findByParentItem(part);
            if (!subComponents.isEmpty()) {
                List<Map<String, Object>> components = new ArrayList<>();
                calculateMRPRecursive(part, requiredQty, level + 1, components);
                partInfo.put("components", components);
            }

            result.add(partInfo);
        }
    }
}