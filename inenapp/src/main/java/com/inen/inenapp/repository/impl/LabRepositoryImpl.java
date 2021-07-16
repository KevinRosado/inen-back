package com.inen.inenapp.repository.impl;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LabRepositoryImpl implements LabRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer addSample(Sample sample) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("sampleAmount", Types.INTEGER),
                new SqlParameter("sampleType", Types.VARCHAR),
                new SqlParameter("sampleState", Types.INTEGER),
                new SqlOutParameter("sampleCode", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_sample(?,?,?,?)}");
                callableStatement.setInt(1, Integer.parseInt(sample.getSampleAmount()));
                callableStatement.setString(2, sample.getSampleType());
                callableStatement.setInt(3, Integer.parseInt(sample.getSampleState()));
                callableStatement.registerOutParameter(4, Types.INTEGER);
                return callableStatement;
            }
        }, parameters);
        Integer code = (Integer) t.get("sampleCode");
        return code;
    }

    @Override
    public void addSampleServices(Integer sampleCode, Integer orderCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("orderCode", Types.INTEGER),
                new SqlParameter("sampleCode", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_service_sample(?,?)}");
                callableStatement.setInt(1, orderCode);
                callableStatement.setInt(2, sampleCode);
                return callableStatement;
            }
        }, parameters);
    }

    @Override
    public List<SampleService> getSampleServices(String orderCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("orderCode", Types.INTEGER),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.get_sample_services(?,?)}");
                callableStatement.setInt(1, Integer.parseInt(orderCode));
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<SampleService> sampleServices = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    SampleService s = new SampleService();
                    s.setOrderCode(String.valueOf(p.get("cod_orden_servicio")));
                    s.setServiceName(String.valueOf(p.get("descripcion_servicio")));
                    s.setSampleCode(String.valueOf(p.get("muestraCode")));
                    return s;
                }).collect(Collectors.toList());
        return sampleServices;
    }

    @Override
    public List<MachinesLab> getMachinesLab(String areaCode) {
        
        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter("areaCode", Types.INTEGER),
            new SqlOutParameter("p_cursor", Types.REF_CURSOR));
    Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
            con.setAutoCommit(false);
            CallableStatement callableStatement = con.prepareCall("{call INEN.get_machines_by_area(?,?)}");
            callableStatement.setString(1,areaCode);
            callableStatement.registerOutParameter(2, Types.REF_CURSOR);
            return callableStatement;
        }
    }, parameters);
    List<MachinesLab> machinesLab = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
            .map(p -> {
                MachinesLab m = new MachinesLab();
                m.setMachineCode(String.valueOf(p.get("cod_maquina")));
                m.setMachineModel(String.valueOf(p.get("descripcion")));
                m.setDisponibility(Boolean.valueOf((boolean) p.get("en_actividad")));
                return m;
            }).collect(Collectors.toList());
    return machinesLab;
    }

    @Override
    public List<SimpleSample> getSamplebyOrder(String orderCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("orderCode", Types.INTEGER),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.get_samples_order(?,?)}");
                callableStatement.setInt(1, Integer.parseInt(orderCode));
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<SimpleSample> sample = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    SimpleSample s = new SimpleSample();
                    s.setSampleCode(String.valueOf(p.get("cod_muestra")));
                    s.setSampleType(String.valueOf(p.get("descripcion")));
                    return s;
                }).collect(Collectors.toList());
        return sample;
    }

    @Override
    public void addMachineOperation(String machineCode, String workerCode, String personCode, String sampleCode, String orderCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("machineCode", Types.VARCHAR),
                new SqlParameter("workerCode", Types.VARCHAR),
                new SqlParameter("personCode", Types.VARCHAR),
                new SqlParameter("sampleCode", Types.INTEGER),
                new SqlParameter("orderCode", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_machine_operation(?,?,?,?,?)}");
                callableStatement.setString(1, machineCode);
                callableStatement.setString(2, workerCode);
                callableStatement.setString(3, personCode);
                callableStatement.setInt(4, Integer.parseInt(sampleCode));
                callableStatement.setInt(5, Integer.parseInt(orderCode));
                return callableStatement;
            }
        }, parameters);
    }


    @Override
    public List<String> getSampleRelations(String sampleCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("sampleCode", Types.INTEGER),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.get_sample_relations(?,?)}");
                callableStatement.setInt(1, Integer.parseInt(sampleCode));
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<String> orderCodes = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    String s = String.valueOf(p.get("cod_orden_servicio"));
                    return s;
                }).collect(Collectors.toList());
        return orderCodes;
    }

    @Override
    public void updateSample(String sampleCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("sampleCode", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.update_sample(?)}");
                callableStatement.setInt(1, Integer.parseInt(sampleCode));
                return callableStatement;
            }
        }, parameters);
    }

    @Override
    public void updateMachine(MachinesLab machineCode) {
        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter("machineCode", Types.VARCHAR));
    Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
            con.setAutoCommit(false);
            CallableStatement callableStatement = con.prepareCall("{call INEN.update_machine(?)}");
            callableStatement.setString(1, machineCode.getMachineCode());
            return callableStatement;
        }
    }, parameters);
        
    }

    @Override
    public List<MachineData> getMachineOperations(String machineCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("machineCode", Types.VARCHAR),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.get_machine_operations(?,?)}");
                callableStatement.setString(1, machineCode);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<MachineData> operations = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    MachineData m = new MachineData();
                    m.setCodAnalisisMaquina(String.valueOf(p.get("cod_analisis_maquina")));
                    m.setMaxRef(String.valueOf(p.get("max_referencial")));
                    m.setResultado(String.valueOf(p.get("resultado")));
                    m.setMinRef(String.valueOf(p.get("min_referencial")));
                    return m;
                }).collect(Collectors.toList());
        return operations;
    }

    @Override
    public void addResult(MachineData result) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("score", Types.VARCHAR),
                new SqlParameter("machineCode", Types.VARCHAR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_results(?,?)}");
                callableStatement.setString(1, result.getResultado());
                callableStatement.setString(2, result.getCodAnalisisMaquina());
                return callableStatement;
            }
        }, parameters);
    }

}
