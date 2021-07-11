package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.repository.AttentionRepository;
import com.inen.inenapp.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionRepository attentionRepository;

    @Override
    public Patient getPatientDetails(String patientCode) {
        return attentionRepository.getPatientDetails(patientCode);
    }
}