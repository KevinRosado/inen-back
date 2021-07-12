package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AttentionRepository {
    Patient getPatientDetails(String patientCode);
    List<Service> getByPriceCode(String priceCode);
    Integer addNewOrder(Order order);
    List<Attention> getLastAttentions(String clinicalCode);
    AttentionResponse addNewAttention(MedicalAttention medicalAttention);
}
