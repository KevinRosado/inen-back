package com.inen.inenapp.dto.attention;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String codRegistro;
    private String codTrabajador;
    private String codPersona;
    private String tipoPrecio;
    private List<Service> services;
}
