package com.inen.inenapp.dto.attention;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String codRegistro;
    private String codTrabajador;
    private String codPersona;
    private List<Service> services;
}
