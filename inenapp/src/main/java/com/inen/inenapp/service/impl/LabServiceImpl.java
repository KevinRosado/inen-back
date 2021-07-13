package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.repository.LabRepository;
import com.inen.inenapp.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
