package com.inen.inenapp.dto.attention;

import lombok.Data;

@Data
public class Patient {
    private String clinical_code;
    private String paternal_surname;
    private String maternal_surname;
    private String patient_name;
    private String health_code;
    private String blood_type;
}
