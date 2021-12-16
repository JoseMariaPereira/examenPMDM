package com.josemaria.menu;

import java.util.ArrayList;
import com.josemaria.clinica.Historial;

public class ContenedorHistorial {

	private ArrayList<Historial> historial;
	
	public ContenedorHistorial() {}
	
	public ContenedorHistorial(ArrayList<Historial> historial) {
		super();
		this.historial = historial;
	}



	public ArrayList<Historial> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Historial> historial) {
		this.historial = historial;
	}
	
	
}
