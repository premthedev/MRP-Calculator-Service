package org.example.mrpapplication.Controller;

import org.example.mrpapplication.Service.MRPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mrp")
public class MRPController {

    @Autowired
    private MRPService mrpService;

    @GetMapping("/calculate")
    public List<Map<String, Object>> calculateMRP(@RequestParam String itemName, @RequestParam int quantity) {
        return mrpService.calculateMRP(itemName, quantity);
    }
}