package com.josemaria.menu;

import java.util.ArrayList;

import com.josemaria.clinica.Consulta;

public class ContenedorRevision {
	private ArrayList<Consulta> revisiones;
	
	public ContenedorRevision() {}
	
	public ContenedorRevision(ArrayList<Consulta> revisiones) {
		super();
		this.revisiones = revisiones;
	}



	public ArrayList<Consulta> getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(ArrayList<Consulta> revisiones) {
		this.revisiones = revisiones;
	}
}
