package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.dto.attention.SampleService;

import java.util.List;

public interface LabRepository {
    Integer addSample(Sample sample);
    void addSampleServices(Integer sampleCode, Integer orderCode);
    List<SampleService> getSampleServices (String orderCode);
}
