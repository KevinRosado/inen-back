package com.inen.inenapp.dto.attention;

import lombok.Data;

import java.util.List;

@Data
public class MachineOperation {
    private String workerCode;
    private String personCode;
    private List<MachineWithSamples> machineRelation;
}
