package com.josemaria.clinica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author mrnov
 */
public class Consulta implements Serializable{
    private LocalDate fechaConsulta;
    private LocalDate fechaRevision;
    private Sanitario facultativo;

    public Consulta() {
    }

    public Consulta(LocalDate fechaConsulta, LocalDate fechaRevision, Sanitario sanitario) {
        this.fechaConsulta = fechaConsulta;
        this.fechaRevision = fechaRevision;
        this.facultativo = sanitario;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public LocalDate getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDate fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Sanitario getMedico() {
        return facultativo;
    }

    public void setMedico(Sanitario IdFacultativo) {
        this.facultativo = IdFacultativo;
    }   
}
