package com.inen.inenapp.dto.attention;

import lombok.Data;

import java.util.List;

@Data
public class Sample {
    private String sampleType;
    private String sampleState;
    private String sampleAmount;
    private List<String> orderCodes;
}
