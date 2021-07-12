package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.repository.AttentionRepository;
import com.inen.inenapp.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void addNewOrder(Order order) {
       Integer orderCode = attentionRepository.addNewOrder(order);
       for (com.inen.inenapp.dto.attention.Service s: order.getServices()){
           attentionRepository.addNewService(orderCode, s.getService_code(), order.getTipoPrecio(), s.getService_price());
       }
    }

    @Override
    public List<Attention> getLastAttentions(String clinicalCode) {
        return attentionRepository.getLastAttentions(clinicalCode);
    }

    @Override
    public void addNewAttention(MedicalAttention medicalAttention) {
        attentionRepository.addNewAttention(medicalAttention);
    }
}
