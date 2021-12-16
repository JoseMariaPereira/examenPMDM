/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josemaria.clinica;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author mrnov
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Paciente implements Serializable{
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean estado;//activo o no

    private ArrayList<Historial> h;
    
    public Paciente() {
        this.h = new ArrayList<Historial>();
    }

    public Paciente(String dni, String nombre, String apellidos, String telefono,boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.estado = estado;
        
        this.h = new ArrayList<Historial>();
    }

    public ArrayList<Historial> getH() {
        return h;
    }

    public void setH(ArrayList<Historial> h) {
        this.h = h;
    }
 
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
     
}
