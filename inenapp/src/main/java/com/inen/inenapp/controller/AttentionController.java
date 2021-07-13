package com.inen.inenapp.controller;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public void addNewOrder(@RequestBody Order order){
        attentionService.addNewOrder(order);
    }

    @GetMapping(value ="/latest", produces = "application/json")
    public List<Attention> getLastAttentions(@RequestParam String clinicalCode){
        return attentionService.getLastAttentions(clinicalCode);
    }

    @PostMapping(value = "/newattention", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addNewAttention(@RequestBody MedicalAttention medicalAttention){
        attentionService.addNewAttention(medicalAttention);
    }

    @PostMapping(value = "/addpatient", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addNewPatient(@RequestBody ClinicalHistory clinicalHistory){
        attentionService.addNewPatient(clinicalHistory);
    }
}
