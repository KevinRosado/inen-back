package com.inen.inenapp.repository.impl;

import com.inen.inenapp.dto.login.LoginRequest;
import com.inen.inenapp.dto.login.LoginResponse;
import com.inen.inenapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.net.ConnectException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean registerUser() {
        List<SqlParameter> parameters = new ArrayList<>();
        parameters.add(new SqlParameter("", Types.VARCHAR));
        parameters.add(new SqlParameter("", Types.VARCHAR));
        parameters.add(new SqlParameter("", Types.VARCHAR));
        parameters.add(new SqlParameter("", Types.VARCHAR));

        try{
            Connection cn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement statement = cn.prepareCall("call LOGIN_USER(?,?)");
           

        }catch(SQLException throwables){

        }
    
        
        return null;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        List<SqlParameter> parameters = new ArrayList<>();
        parameters.add(new SqlParameter("", Types.VARCHAR));
        parameters.add(new SqlParameter("", Types.VARCHAR));
        LoginResponse loginResponse = new LoginResponse();
        try {
            Connection cn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement statement = cn.prepareCall("call LOGIN_USER(?,?)");
            statement.setString(1, loginRequest.getUserCode());
            statement.setString(2, loginRequest.getUserPassword());
            statement.execute();

            ResultSet rs = statement.getResultSet();
            if (rs != null) {
                loginResponse.setUserName(rs.getString("nombres"));
                loginResponse.setUserLastName(rs.getString("ape_pat"));
                loginResponse.setUserCode(rs.getString("cod_trabajor"));
                loginResponse.setCodMarcaje(rs.getString("cod_marcaje"));
                loginResponse.setCodSistema(rs.getString("cod_sistema"));
                loginResponse.setRol(rs.getString("cod_rol"));
                loginResponse.setJwt("XD");
            }else{
                
            }

        } catch (SQLException throwables) {

        }

        return loginResponse;
    }

}
