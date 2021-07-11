package com.inen.inenapp.repository.impl;

import com.inen.inenapp.dto.attention.Patient;
import com.inen.inenapp.repository.AttentionRepository;
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
                    pt.setHealth_code((String) p.get("seguro"));
                    return pt;
                }).collect(Collectors.toList());
        return patients.get(0);
    }
}
