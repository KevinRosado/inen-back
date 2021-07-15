package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.attention.MachinesLab;
import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.dto.attention.SampleService;
import com.inen.inenapp.repository.LabRepository;
import com.inen.inenapp.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabRepository labRepository;

    @Override
    @Transactional
    public void addSample(Sample sample) {
        Integer sampleCode = labRepository.addSample(sample);
        for (String code: sample.getOrderCodes()) {
            labRepository.addSampleServices(sampleCode, Integer.parseInt(code));
        }
    }

    @Override
    public List<SampleService> getSampleServices(String orderCode) {
        return labRepository.getSampleServices(orderCode);
    }

    @Override
    public List<MachinesLab> getMachinesLab(String areaCode) {
        
        return labRepository.getMachinesLab(areaCode);
    }
    
}
