package com.inen.inenapp.dto.attention;

import lombok.Data;

import java.util.List;

@Data
public class MachineWithSamples {
    private String machineCode;
    private String machineModel;
    private List<SimpleSample> codesArray;
}
