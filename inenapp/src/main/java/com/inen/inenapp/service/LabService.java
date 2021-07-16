package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LabService {
    void addSample(Sample sample);
    List<SampleService> getSampleServices (String orderCode);
    List<MachinesLab> getMachinesLab(String areaCode);
    List<SimpleSample> getSamplebyOrder(String orderCode);
    void addMachineOperation(MachineOperation machine);
    void updateMachine(MachinesLab machineCode);
    List<MachineSimulation> setSimulation(String areaCode);
    void addResults(MachineSimulation results);
}
