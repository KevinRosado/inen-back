package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AttentionService {
    Patient getPatientDetails(String patientCode);
    List<Service> getByPriceCode(String priceCode);
    Integer addNewOrder(Order order);
    List<Attention> getLastAttentions(String clinicalCode);
    void addNewAttention(MedicalAttention medicalAttention);
}
