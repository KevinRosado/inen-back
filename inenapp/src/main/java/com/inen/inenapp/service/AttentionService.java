package com.inen.inenapp.service;

import com.inen.inenapp.dto.attention.Patient;

public interface AttentionService {
    Patient getPatientDetails(String patientCode);
}
