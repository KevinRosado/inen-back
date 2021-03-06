package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.repository.LabRepository;
import com.inen.inenapp.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabRepository labRepository;

    @Override
    @Transactional
    public void addSample(Sample sample) {
        Integer sampleCode = labRepository.addSample(sample);
        for (String code: sample.getOrderCodes()) {
            labRepository.addSampleServices(sampleCode, Integer.parseInt(code));
        }
    }

    @Override
    public List<SampleService> getSampleServices(String orderCode) {
        return labRepository.getSampleServices(orderCode);
    }

    @Override
    public List<MachinesLab> getMachinesLab(String areaCode) {
        
        return labRepository.getMachinesLab(areaCode);
    }

    @Override
    public List<SimpleSample> getSamplebyOrder(String orderCode) {
        
        return labRepository.getSamplebyOrder(orderCode);
    }

    @Override
    @Transactional
    public void addMachineOperation(MachineOperation machine) {
        for (MachineWithSamples m: machine.getMachineRelation()) {
            for (SimpleSample sample: m.getCodesArray()) {
                List<String> orderCodes = labRepository.getSampleRelations(sample.getSampleCode());
                for (String orderCode: orderCodes) {
                    labRepository.addMachineOperation(m.getMachineCode(), machine.getWorkerCode(), machine.getPersonCode(), sample.getSampleCode(), orderCode);
                }
                labRepository.updateSample(sample.getSampleCode());
            }
        }
    }

    @Override
    public void updateMachine(MachinesLab machineCode) {
        labRepository.updateMachine(machineCode);
    }

    @Override
    @Transactional
    public List<MachineSimulation> setSimulation(String areaCode) {
        List<MachineSimulation> lista = new ArrayList<MachineSimulation>();
        List<MachinesLab> machines = labRepository.getMachinesLab(areaCode);
        for (MachinesLab  i : machines) {
            MachineSimulation machineSimul = new MachineSimulation();
            machineSimul.setMachinesLab(i);
           List<MachineData> data = labRepository.getMachineOperations(i.getMachineCode());
            machineSimul.setMachineData(data);
            lista.add(machineSimul);
        }
        return lista;
    }

    @Override
    @Transactional
    public void addResults(MachineSimulation results) {
        for (MachineData r: results.getMachineData()) {
            labRepository.addResult(r);
        }
        labRepository.updateMachine(results.getMachinesLab());
    }

}
