package com.inen.inenapp.dto.attention;

import java.util.List;

import lombok.Data;

@Data
public class MachineSimulation {
    private List<MachineData> machineData;
    private MachinesLab machinesLab;
}
