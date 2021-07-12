package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.repository.AttentionRepository;
import com.inen.inenapp.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionRepository attentionRepository;

    @Override
    public Patient getPatientDetails(String patientCode) {
        return attentionRepository.getPatientDetails(patientCode);
    }

    @Override
    public List<com.inen.inenapp.dto.attention.Service> getByPriceCode(String priceCode) {
        return attentionRepository.getByPriceCode(priceCode);
    }

    @Override
    public Integer addNewOrder(Order order) {
        return attentionRepository.addNewOrder(order);
    }

    @Override
    public List<Attention> getLastAttentions(String clinicalCode) {
        return attentionRepository.getLastAttentions(clinicalCode);
    }

    @Override
    public AttentionResponse addNewAttention(MedicalAttention medicalAttention) {
        return attentionRepository.addNewAttention(medicalAttention);
    }
}
