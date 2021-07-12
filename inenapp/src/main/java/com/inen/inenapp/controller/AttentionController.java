package com.inen.inenapp.controller;

import com.inen.inenapp.dto.attention.Order;
import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.dto.attention.Service;
import com.inen.inenapp.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/attention")
public class AttentionController {
    @Autowired
    private AttentionService attentionService;

    @GetMapping(value = "/patdetails", produces = "application/json")
    public Patient getPatientDetails(@RequestParam String patientCode){
        return attentionService.getPatientDetails(patientCode);
    }

    @GetMapping(value = "/services", produces = "application/json")
    public List<Service> getByPriceCode(@RequestParam String priceCode){
        return attentionService.getByPriceCode(priceCode);
    }

    @PostMapping(value = "/neworder", consumes = "application/json")
    public Integer addNewOrder(@RequestBody Order order){
        return attentionService.addNewOrder(order);
    }
}
