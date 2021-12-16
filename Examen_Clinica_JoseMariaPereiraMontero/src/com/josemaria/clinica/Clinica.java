/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josemaria.clinica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mrnov
 */
public class Clinica implements Serializable{
    String cif;
    String nombre;
    String tf;
    ArrayList <Paciente> P;
    ArrayList <Sanitario> S;

    public Clinica() {
        this.P= new ArrayList<Paciente>();
        this.S= new ArrayList<Sanitario>();
    }

    public Clinica(String cif, String nombre, String tf) {
        this.cif = cif;
        this.nombre = nombre;
        this.tf = tf;
        this.P= new ArrayList<Paciente>();
        this.S= new ArrayList<Sanitario>();
    }

    
    
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public ArrayList<Paciente> getLP (){
        return P;
    }

    public void setLP(ArrayList<Paciente> P) {
        this.P = P;
    }
    
    public ArrayList<Sanitario> getLS (){
        return S;
    }

    public void setLS(ArrayList<Sanitario> S) {
        this.S = S;
    }
}
