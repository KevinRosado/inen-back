package com.inen.inenapp.controller;

import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
