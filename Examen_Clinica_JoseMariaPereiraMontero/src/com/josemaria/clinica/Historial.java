package com.josemaria.clinica;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.time.*;


public class Historial implements Serializable{


    LocalDate fechaConsulta;


    LocalDateTime hora;


    String tipoTrabajo;


    double importe;
	
    Sanitario sanitario;

    public Historial(){}

    public Historial(LocalDate fechaConsulta, LocalDateTime hora, String tipoTrabajo, double importe) {
        this.fechaConsulta = fechaConsulta;
        this.hora = hora;
        this.tipoTrabajo = tipoTrabajo;
        this.importe = importe;

        this.sanitario = null;
    }

    public LocalDate getFecha() {
        return fechaConsulta;
    }

    public void setFecha(LocalDate fechaConsulta) {
            this.fechaConsulta = fechaConsulta;
    }

    public LocalDateTime getHora() {
            return hora;
    }

    public void setHora(LocalDateTime hora) {
            this.hora = hora;
    }

    public String getTipoTrabajo() {
            return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
            this.tipoTrabajo = tipoTrabajo;
    }

    public double getImporte() {
            return importe;
    }

    public void setImporte(double importe) {
            this.importe = importe;
    }
}