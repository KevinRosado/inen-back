package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.dto.attention.Service;

import java.util.List;

public interface AttentionService {
    Patient getPatientDetails(String patientCode);
    List<Service> getByPriceCode(String priceCode);
}
