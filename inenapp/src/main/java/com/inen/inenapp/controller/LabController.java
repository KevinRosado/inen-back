package com.inen.inenapp.controller;

import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/lab")
public class LabController {
    @Autowired
    private LabService labService;

    @PostMapping(value = "/addsample", consumes = "application/json")
    public void addSample(@RequestBody Sample sample){
        labService.addSample(sample);
    }
}
