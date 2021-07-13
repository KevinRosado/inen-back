package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

public interface AttentionRepository {
    Patient getPatientDetails(String patientCode);
    List<Service> getByPriceCode(String priceCode);
    Integer addNewOrder(Order order);
    List<Attention> getLastAttentions(String clinicalCode);
    void addNewAttention(MedicalAttention medicalAttention);
    void addNewService(Integer orderCode, String serviceCode, String priceType, BigDecimal price);
    void addNewPerson(ClinicalHistory clinicalHistory);
    void addNewHistory(ClinicalHistory clinicalHistory);
}
