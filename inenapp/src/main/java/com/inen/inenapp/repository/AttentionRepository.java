package com.inen.inenapp.repository;

import com.inen.inenapp.dto.attention.Patient;

public interface AttentionRepository {
    Patient getPatientDetails(String patientCode);
}
