/*
package com.drools.first.controller;

import com.drools.first.model.Jewellery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.drools.first.service.Jewelservice;

@RestController
public class Controller {

	@Autowired
	private Jewelservice service;

	@GetMapping("/applyDiscount")
	public String applyDiscount() {
		// Create a Jewellery object with the appropriate jewel
		Jewellery jewellery = new Jewellery();
		jewellery.setJewel("Diamond"); // Set the jewel as per your scenario

		// Use the DroolsService to apply rules and calculate discount
		Jewelservice.applyDiscount(jewellery);

		return "Discount applied successfully!";
	}
}

*/
package com.drools.first.controller;

import com.drools.first.model.Jewellery;
import com.drools.first.model.Responsejew;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.drools.first.service.Jewelservice;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Jewelservice service;
private ObjectMapper objectMapper;
    @GetMapping("/applyDiscount")
    public String applyDiscount() {

        String defaultJewel = "Silver";
        double defaultWeight = 20.0;

        // Create a Jewellery object with the default values
        Jewellery jewellery = new Jewellery();
        Jewellery jewobj = new Jewellery();
        jewellery.setJewel(defaultJewel);
        jewellery.setWeight(defaultWeight);

        jewobj.setJewel(defaultJewel);
        jewobj.setWeight(defaultWeight);

        // Use the Jewelservice to apply rules and calculate discount
        //service.applyDiscount(jewellery,jewobj);

        return "Discount applied successfully!";
    }


    @PostMapping("/applyDiscount2")
    public List<Responsejew> applyDiscount2(@RequestBody String jewel) {

        return service.applyDiscount(jewel);
    }
}
