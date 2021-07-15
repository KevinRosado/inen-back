package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.MachinesLab;
import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.dto.attention.SampleService;
import com.inen.inenapp.dto.attention.SimpleSample;

import java.util.List;

public interface LabRepository {
    Integer addSample(Sample sample);
    void addSampleServices(Integer sampleCode, Integer orderCode);
    List<SampleService> getSampleServices (String orderCode);
    List<MachinesLab> getMachinesLab(String areaCode);
    List<SimpleSample> getSamplebyOrder (String orderCode);
}
