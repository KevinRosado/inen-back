

package com.inen.inenapp.dto.login;
import lombok.Data;

@Data
public class LoginResponse{
    private String userName;
    private String userLastName;
    private String userCode;
    private String codPersona;
    private String codMarcaje;
    private String codSistema;
    private String jwt;
    private String rol;
    
}
