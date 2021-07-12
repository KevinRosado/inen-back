package com.inen.inenapp.dto.attention;

import lombok.Data;

@Data
public class MedicalAttention {
    private String description;
    private String details;
    private String patientType;
    private String workerCode;
    private String personCode;
    private String medicalCode;
    private String clinicalCode;
    private String areaCode;
}
