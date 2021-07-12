package com.inen.inenapp.repository.impl;

import com.inen.inenapp.dto.login.LoginRequest;
import com.inen.inenapp.dto.login.LoginResponse;
import com.inen.inenapp.repository.UserRepository;

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

        List<SqlParameter> parameters = Arrays.asList(
            new SqlParameter("usercode", Types.VARCHAR),
            new SqlParameter("userpassword", Types.VARCHAR),
            new SqlOutParameter("p_cursor", Types.REF_CURSOR));

    Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator(){
        @Override
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
            con.setAutoCommit(false);
            CallableStatement callableStatement = con.prepareCall("{call INEN.login_user(?,?,?)}");
            callableStatement.setString(1, loginRequest.getUserCode());
            callableStatement.setString(2, loginRequest.getUserPassword());
            callableStatement.registerOutParameter(3, Types.REF_CURSOR);
            return callableStatement;
        }
    }, parameters);
    List<LoginResponse> response = ((ArrayList<LinkedCaseInsensitiveMap>) t.get("p_cursor")).stream()
            .map(p -> {
                LoginResponse user = new LoginResponse();
                user.setCodPersona((String) p.get("cod_persona"));
                user.setUserCode((String)p.get("cod_trabajador"));
                user.setUserLastName((String)p.get("ape_pat"));
                user.setUserName((String)p.get("nombres"));
                user.setCodMarcaje((String)p.get("cod_marcaje"));
                user.setCodSistema((String)p.get("cod_sistema"));
                user.setRol("Caja");
                user.setJwt("jwt");
                return user;
            }).collect(Collectors.toList());

    return response.get(0);
    }

}
