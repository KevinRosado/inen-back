package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.Attention;
import com.inen.inenapp.dto.attention.Order;
import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.dto.attention.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AttentionService {
    Patient getPatientDetails(String patientCode);
    List<Service> getByPriceCode(String priceCode);
    Integer addNewOrder(Order order);
    List<Attention> getLastAttentions(String clinicalCode);
}
