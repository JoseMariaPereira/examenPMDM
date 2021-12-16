/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josemaria.clinica;


/**
 *
 * @author mrnov
 */
public class PacienteEsporadico extends Paciente{
    private String especialidad;

    public PacienteEsporadico() {
    }

    public PacienteEsporadico(String especialidad) {
        this.especialidad = especialidad;
    }
     
    public PacienteEsporadico(String especialidad,String dni, String nombre, String apellidos, String telefono, boolean estado) {
        super(dni, nombre, apellidos,telefono, estado);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
