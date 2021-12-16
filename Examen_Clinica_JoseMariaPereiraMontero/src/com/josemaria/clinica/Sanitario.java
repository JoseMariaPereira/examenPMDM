package com.josemaria.clinica;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

public class Sanitario implements Serializable{
    String dni, nombre, telefono;
    int trienios;
    ArrayList<Paciente> p;

    public Sanitario(/*String dni, String nombre, String telefono, float sueldoBase, float retencion,*/ int trienios) {
        //super(dni, nombre, telefono, sueldoBase, retencion);
        this.trienios = trienios;
        p = new ArrayList();
    }
    
    public Sanitario() {
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

    public String getTelefono() {
        return telefono;
    }

//    @Override
//    public float calculoSalario() {
//        return (sueldoBase + trienios) - ((sueldoBase + trienios)*retencion);
//    }
    public void setTelefono(String telefono) {	
        this.telefono = telefono;
    }

    public int getTrienios() {
        return trienios;
    }

    public void setTrienios(int trienios) {
        this.trienios = trienios;
    }

    public ArrayList<Paciente> getPacientes() {
        return p;
    }

    public void setPacientes(ArrayList<Paciente> p) {
        this.p = p;
    }
    
}