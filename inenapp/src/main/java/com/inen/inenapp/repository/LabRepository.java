package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.Sample;

public interface LabRepository {
    Integer addSample(Sample sample);
    void addSampleServices(Integer sampleCode, Integer orderCode);
}
