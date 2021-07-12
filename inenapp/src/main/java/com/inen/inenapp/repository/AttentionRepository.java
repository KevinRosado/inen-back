package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.Order;
import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.dto.attention.Service;

import java.util.List;

public interface AttentionRepository {
    Patient getPatientDetails(String patientCode);
    List<Service> getByPriceCode(String priceCode);
    Integer addNewOrder(Order order);
}
