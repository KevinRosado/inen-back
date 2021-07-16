package com.inen.inenapp.controller;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/sampleservices", produces = "application/json")
    public List<SampleService> getSampleServices (@RequestParam String orderCode){
        return labService.getSampleServices(orderCode);
    }
    @GetMapping(value = "/machinesarea", produces ="application/json")
    public List<MachinesLab> getMachinesLab(@RequestParam String areaCode){
        return labService.getMachinesLab(areaCode);
    }
    @GetMapping(value = "/samplesorder", produces ="application/json")
    public List<SimpleSample> getSamplebyOrder(@RequestParam String orderCode){
        return labService.getSamplebyOrder(orderCode);
    }

    @PostMapping(value = "/addoperation", consumes = "application/json")
    public void addMachineOperation(@RequestBody MachineOperation machine){
        labService.addMachineOperation(machine);
    }
    @PostMapping(value = "/updatemachine",consumes = "application/json")
    public void updateMachine(@RequestBody MachinesLab machineCode){
        labService.updateMachine(machineCode);
    }
    @GetMapping(value="/simulationinfo",consumes = "application/json")
    public List<MachineSimulation> setSimulation(@RequestParam String areaCode){
        return labService.setSimulation(areaCode);
    }
}

