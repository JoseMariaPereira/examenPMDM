/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josemaria.clinica;

import java.util.ArrayList;

/**
 *
 * @author mrnov
 */
public class PacienteHabitual extends Paciente{
    double descuento;
    private ArrayList <Consulta> revisiones;

    public PacienteHabitual() {
        this.revisiones = new ArrayList <Consulta> ();
    }

    public PacienteHabitual(ArrayList<Consulta> revisiones) {
        this.revisiones = revisiones;
    }

    public PacienteHabitual(ArrayList<Consulta> revisiones, String dni, String nombre,String apellidos,String telefono, float precio, boolean estado) {
        super(dni, nombre, apellidos,telefono, estado);
        descuento = precio;
        this.revisiones = revisiones;
    }

    public PacienteHabitual(String dni, String nombre,String apellidos, String telefono, boolean estado) {
        super(dni, nombre, apellidos,telefono, estado);
        this.revisiones = new ArrayList <Consulta> ();
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    
    public ArrayList <Consulta> getRevisiones() {
        return revisiones;
    }

    public void setRevisiones(ArrayList <Consulta> revisiones) {
        this.revisiones = revisiones;
    }  
}
