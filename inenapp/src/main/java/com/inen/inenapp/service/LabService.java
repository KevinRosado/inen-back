package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.MachinesLab;
import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.dto.attention.SampleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LabService {
    void addSample(Sample sample);
    List<SampleService> getSampleServices (String orderCode);
    List<MachinesLab> getMachinesLab(String areaCode);
}
