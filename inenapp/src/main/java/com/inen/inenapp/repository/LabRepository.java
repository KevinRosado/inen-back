package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.*;

import java.util.List;

public interface LabRepository {
    Integer addSample(Sample sample);
    void addSampleServices(Integer sampleCode, Integer orderCode);
    List<SampleService> getSampleServices (String orderCode);
    List<MachinesLab> getMachinesLab(String areaCode);
    List<SimpleSample> getSamplebyOrder (String orderCode);
    void addMachineOperation(String machineCode, String workerCode, String personCode, String sampleCode, String orderCode);
    List<String> getSampleRelations(String sampleCode);
    void updateSample(String sampleCode);
}
