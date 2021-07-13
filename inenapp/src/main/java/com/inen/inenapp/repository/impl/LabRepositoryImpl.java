package com.inen.inenapp.repository.impl;

import com.inen.inenapp.dto.attention.Sample;
import com.inen.inenapp.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
                callableStatement.setInt(1, sampleCode);
                callableStatement.setInt(2, orderCode);
                return callableStatement;
            }
        }, parameters);
    }
}
