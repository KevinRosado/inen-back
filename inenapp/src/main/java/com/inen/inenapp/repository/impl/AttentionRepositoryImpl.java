package com.inen.inenapp.repository.impl;

import com.inen.inenapp.dto.attention.*;
import com.inen.inenapp.repository.AttentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AttentionRepositoryImpl implements AttentionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Patient getPatientDetails(String patientCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("patientCode", Types.VARCHAR),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.patient_details(?,?)}");
                callableStatement.setString(1, patientCode);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<Patient> patients = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    Patient pt = new Patient();
                    pt.setClinical_code((String)p.get("cod_historia_clinica"));
                    pt.setBlood_type((String)p.get("grupo_sangre"));
                    pt.setMaternal_surname((String)p.get("ape_mat"));
                    pt.setPaternal_surname((String)p.get("ape_pat"));
                    pt.setPatient_name((String)p.get("nombres"));
                    pt.setInsurance_code((String) p.get("cod_tipo_seguro"));
                    pt.setInsurance_name((String) p.get("seguro"));
                    return pt;
                }).collect(Collectors.toList());
        return patients.get(0);
    }

    @Override
    public List<Service> getByPriceCode(String priceCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("priceCode", Types.VARCHAR),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.servicebypricecode(?,?)}");
                callableStatement.setString(1, priceCode);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<Service> services = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    Service s = new Service();
                    s.setService_code((String)p.get("cod_servicio"));
                    s.setService_name((String)p.get("descripcion_servicio"));
                    s.setService_price((BigDecimal) p.get("monto"));
                    return s;
                }).collect(Collectors.toList());
        return services;
    }

    @Override
    public Integer addNewOrder(Order order) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("attentionCode", Types.INTEGER),
                new SqlParameter("workerCode", Types.VARCHAR),
                new SqlParameter("personCode", Types.VARCHAR),
                new SqlOutParameter("orderCode", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.create_order(?,?,?,?)}");
                callableStatement.setInt(1, Integer.parseInt(order.getCodRegistro()));
                callableStatement.setString(2, order.getCodTrabajador());
                callableStatement.setString(3, order.getCodPersona());
                callableStatement.registerOutParameter(4, Types.INTEGER);
                return callableStatement;
            }
        }, parameters);
        Integer code = (Integer) t.get("orderCode");
        return code;
    }

    @Override
    public List<Attention> getLastAttentions(String clinicalCode) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("clinicalCode", Types.VARCHAR),
                new SqlOutParameter("p_cursor", Types.REF_CURSOR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                con.setAutoCommit(false);
                CallableStatement callableStatement = con.prepareCall("{call INEN.last_attentions(?,?)}");
                callableStatement.setString(1, clinicalCode);
                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
                return callableStatement;
            }
        }, parameters);
        List<Attention> attentions = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
                .map(p -> {
                    Attention a = new Attention();
                    a.setCodRegistro(String.valueOf(p.get("cod_registro")));
                    a.setHoraRegistro(String.valueOf(p.get("hora_registro")));
                    a.setDescripcion(String.valueOf(p.get("descripcion")));
                    return a;
                }).collect(Collectors.toList());
        return attentions;
    }

    @Override
    public void addNewAttention(MedicalAttention medicalAttention) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("p_description", Types.VARCHAR),
                new SqlParameter("p_details", Types.VARCHAR),
                new SqlParameter("p_patientType", Types.VARCHAR),
                new SqlParameter("p_workerCode", Types.VARCHAR),
                new SqlParameter("p_personCode", Types.VARCHAR),
                new SqlParameter("p_medicalCode", Types.VARCHAR),
                new SqlParameter("p_clinicalCode", Types.VARCHAR),
                new SqlParameter("p_areaCode", Types.VARCHAR),
                new SqlOutParameter("p_cursor", Types.BOOLEAN));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.create_attention(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, medicalAttention.getDescription());
                callableStatement.setString(2, medicalAttention.getDetails());
                callableStatement.setString(3, medicalAttention.getPatientType());
                callableStatement.setString(4, medicalAttention.getWorkerCode());
                callableStatement.setString(5, medicalAttention.getPersonCode());
                callableStatement.setString(6, medicalAttention.getMedicalCode());
                callableStatement.setString(7, medicalAttention.getClinicalCode());
                callableStatement.setString(8, medicalAttention.getAreaCode());
                callableStatement.registerOutParameter(9, Types.BOOLEAN);
                return callableStatement;
            }
        }, parameters);
    }

    @Override
    public void addNewService(Integer orderCode, String serviceCode, String priceType, BigDecimal price) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("orderCode", Types.VARCHAR),
                new SqlParameter("serviceCode", Types.VARCHAR),
                new SqlParameter("priceType", Types.VARCHAR),
                new SqlParameter("price", Types.NUMERIC));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_service(?,?,?,?)}");
                callableStatement.setInt(1, orderCode);
                callableStatement.setString(2, serviceCode);
                callableStatement.setString(3, priceType);
                callableStatement.setBigDecimal(4, price);
                return callableStatement;
            }
        }, parameters);
    }

    @Override
    public void addNewPerson(ClinicalHistory clinicalHistory) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("personCode", Types.VARCHAR),
                new SqlParameter("apePat", Types.VARCHAR),
                new SqlParameter("apeMat", Types.VARCHAR),
                new SqlParameter("names", Types.VARCHAR),
                new SqlParameter("birthday", Types.VARCHAR),
                new SqlParameter("civilState", Types.VARCHAR),
                new SqlParameter("gender", Types.VARCHAR),
                new SqlParameter("religion", Types.VARCHAR),
                new SqlParameter("livingPlace", Types.VARCHAR),
                new SqlParameter("bloodType", Types.VARCHAR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_person(?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, clinicalHistory.getPersonCode());
                callableStatement.setString(2, clinicalHistory.getPaternalSurname());
                callableStatement.setString(3, clinicalHistory.getMaternalSurname());
                callableStatement.setString(4, clinicalHistory.getPatientName());
                callableStatement.setDate(5, Date.valueOf(clinicalHistory.getBirthday()));
                callableStatement.setString(6, clinicalHistory.getCivilState());
                callableStatement.setString(7, clinicalHistory.getGender());
                callableStatement.setString(8, clinicalHistory.getReligion());
                callableStatement.setString(9, clinicalHistory.getLivingPlace());
                callableStatement.setString(10, clinicalHistory.getBloodType());
                return callableStatement;
            }
        }, parameters);
    }

    @Override
    public void addNewHistory(ClinicalHistory clinicalHistory) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("insuranceCode", Types.VARCHAR),
                new SqlParameter("phoneNumber", Types.VARCHAR),
                new SqlParameter("email", Types.VARCHAR),
                new SqlParameter("personCode", Types.VARCHAR),
                new SqlParameter("patientType", Types.VARCHAR));
        Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement callableStatement = con.prepareCall("{call INEN.add_clinic_historial(?,?,?,?,?)}");
                callableStatement.setString(1, clinicalHistory.getInsuranceCode());
                callableStatement.setString(2, clinicalHistory.getPhoneNumber());
                callableStatement.setString(3, clinicalHistory.getEmail());
                callableStatement.setString(4, clinicalHistory.getPersonCode());
                callableStatement.setString(5, clinicalHistory.getPatientType());
                return callableStatement;
            }
        }, parameters);
    }
}
