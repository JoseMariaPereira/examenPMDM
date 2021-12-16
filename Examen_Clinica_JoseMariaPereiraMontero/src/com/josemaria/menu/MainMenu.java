package com.josemaria.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.josemaria.clinica.Clinica;
import com.josemaria.clinica.Consulta;
import com.josemaria.clinica.Historial;
import com.josemaria.clinica.Paciente;
import com.josemaria.clinica.PacienteEsporadico;
import com.josemaria.clinica.PacienteHabitual;
import com.josemaria.clinica.Sanitario;
import com.thoughtworks.xstream.XStream;

/* Clase que contiene el menu principal y el método de entrada al proyecto
 * */

public class MainMenu {
	
	/*Atributos*/
	
	private static String fileName = "FConsorcioClinicas.save";

	private final String FILE_H = "FicheroXMLHistorial_";
	private final String FILE_CR = "FicheroXMLConsultaRevision_";
	private File file;
	private Clinica clinica;
	
	
	/*Entrada de aplicacion*/
	
	public static void main(String[] args) {
		try {
			if (args.length != 0) {
				new MainMenu(args[0]).MenuStartUp();
			} else {
				new MainMenu(MainMenu.fileName).MenuStartUp();
			}
		} catch(IOException | ClassNotFoundException fnfe) {
			System.out.println("Fichero no encontrado, creando uno nuevo...");
			try {
				new MainMenu().MenuStartUp();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/*Construcotres*/
	
	/**
	 * Creacion de un fichero de clinicas para iniciar el menu
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public MainMenu() throws FileNotFoundException, IOException {
		file = new File(MainMenu.fileName);
		clinica = new Clinica("00000001", "ClinicaPrivada.S.A.", "100100100");
		
		ArrayList<Sanitario> sanitarios = new ArrayList<>(Arrays.asList(new Sanitario(0)));
		//Lista de pacientes
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>(
				Arrays.asList(
						new PacienteEsporadico("Nin idea", "22222222A", "Pepito", "Grillo", "321654987", false),
						new PacienteEsporadico("Sifilitico", "11111111A", "Dave", "Casalderrey", "123abc", true),
						new PacienteEsporadico("Sifilitico", "44444444A", "Sheyla", "Venade", "321654", true),
						new PacienteHabitual(
								new ArrayList<>(
										Arrays.asList(
												new Consulta(LocalDate.now(), LocalDate.now(), sanitarios.get(0))
												)
										), 
								"44664656J", "Jose", "Pereira", "123654789", 50, true),
						new PacienteHabitual(
								new ArrayList<>(
										Arrays.asList(
												new Consulta(LocalDate.of(2021, 11, 5), LocalDate.now(), sanitarios.get(0)),
												new Consulta(LocalDate.of(2020, 10, 12), LocalDate.of(2020, 10, 18), sanitarios.get(0))
												)
										), 
								"33333333A", "Cristina", "Eyo", "123654789", 100, false)
						)
				);

		ArrayList<Historial> h1 = new ArrayList<>(Arrays.asList(new Historial(LocalDate.now(), LocalDateTime.now(), "Vasectomia lumbar", 1500)));
		ArrayList<Historial> h2 = new ArrayList<>(Arrays.asList(new Historial(LocalDate.now(), LocalDateTime.now(), "Vasectomia lumbar doble", 1500)));
		ArrayList<Historial> h3 = new ArrayList<>(Arrays.asList(new Historial(LocalDate.of(2021, 11, 5), LocalDateTime.now(), "Estudio pedagogico mental", 1500),
				new Historial(LocalDate.now(), LocalDateTime.now(), "Pedanteria", 500)));
		ArrayList<Historial> h4 = new ArrayList<>(Arrays.asList(new Historial(LocalDate.of(2021, 11, 5), LocalDateTime.now(), "Estripios", 1500),
						new Historial(LocalDate.of(2020, 10, 12), LocalDateTime.now(), "fecha lanzamiento de arcane", 500)));

		pacientes.get(0).setH(h1);
		pacientes.get(1).setH(h1);
		pacientes.get(2).setH(h2);
		pacientes.get(3).setH(h3);
		pacientes.get(4).setH(h4);
		
		clinica.setLS(sanitarios);
		clinica.setLP(pacientes);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(clinica);
		oos.flush();
		oos.close();
	}
	
	/**
	 * Constructor con una clinica por fichero
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public MainMenu(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		file = new File(filename);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		clinica = (Clinica) ois.readObject();
		ois.close();
	}
	
	
	/*Métodos*/
	
	/**
	 * Inicia el menu principal del programa
	 */
	public void MenuStartUp() {
		Scanner scan = new Scanner(System.in);
		String opcion;
		
		do {
			System.out.println("Bienvenido al gestor de clinicas de la clinica: " + clinica.getNombre() + "!!\n");
			
			System.out.println("Menu:"
					+ "\n\t1.- Consulta de historial de un paciente."
					+ "\n\t2.- Consulta de revisiones de un paciente habitual."
					+ "\n\t3.- Guardar datos de paciente."
					+ "\n\t4.- Salir y guardar.");
			System.out.print("Selecciona una opcion: ");
			opcion = scan.nextLine();
			
			switch (opcion) {
				case "1":
					ConsultaHistorial(scan);
					break;
				case "2":
					ConsutlaRevision(scan);
					break;
				case "3":
					GuardarHistorial(scan);
					break;
				case "4": 
				case "Salir": 
				case "salir":
					opcion = "salir";
					if (SaveClinic(scan)) {
						opcion = "volver a intentarlo";
					}
					break;
				default:
					System.out.println("No existe la opcion \"" + opcion + "\" vuelve a intentarlo!");
					break;
			}
		
		} while(!opcion.equalsIgnoreCase("salir"));
		
		System.out.println("Hasta luego!");
		scan.close();
	}
	
	/*HISTORIAL*/
	
	/**
	 * Consulta de historial
	 * @param scan
	 */
	private void ConsultaHistorial(Scanner scan) {
		Paciente paciente = null;
		String dni;
		LocalDate f1, f2;
		ArrayList<Historial> historial;
		System.out.print("Introduce el dni del cliente que quieras buscar: ");
		dni = scan.nextLine();

		for (Paciente p : clinica.getLP()) {
			if (p.getDni().equalsIgnoreCase(dni) && p.isEstado()) {
				paciente = p;
				break;
			}
		}
		
		if (paciente != null) {
			f1 = escribirFecha(scan, "Busca entre dos fechas, introduce la primera (dd/mm/aaaa): " );
			f2 = escribirFecha(scan, "Introduce la segunda (dd/mm/aaaa): ");
			
			//miramos el orden de las fechas y reordenamos
			if (f1.isAfter(f2)) {
				LocalDate aux = f1;
				f1 = f2;
				f2 = aux;
			}
			
			historial = GetHistorial(paciente, f1, f2);
			
			if (historial.size() != 0) {
				int contador = 0;
				for (Historial h : historial) {
					contador++;
					System.out.println("\nConsulta " + contador + ":");
					System.out.println("Trabajo: " + h.getTipoTrabajo() 
							+ "\nfecha: " + h.getFecha());					
				}
			} else {
				System.out.println("Historial no encontrado...");
			}
			
			System.out.println("Quieres darlo de baja? (s para si)");
			
			if (scan.nextLine().equalsIgnoreCase("s")) {
				paciente.setEstado(false);
			}
			
		} else {
			System.out.println("No existe paciente con el dni: " + dni);
		}
	}
	
	/**
	 * Saca una lista de historial dependiendo de dos fechas y del paciente
	 * @param paciente
	 * @param from
	 * @param to
	 * @return
	 */
	private ArrayList<Historial> GetHistorial(Paciente paciente, LocalDate from, LocalDate to) {
		ArrayList<Historial> hs = new ArrayList<>();
		for (Historial h : paciente.getH()) {
			if (h.getFecha().isAfter(from) && h.getFecha().isBefore(to) || h.getFecha().isEqual(to) || h.getFecha().isEqual(from)) {
				hs.add(h);
			}
		}
		return hs;
	}
	
	/**
	 * Escritura de una fecha
	 * @param scan
	 * @return
	 */
	private LocalDate escribirFecha(Scanner scan, String texto) {
		LocalDate f;
		do {
			try {
				System.out.print(texto);
				String fecha = scan.nextLine();
				f = LocalDate.of(Integer.valueOf(fecha.split("/")[2]), Integer.valueOf(fecha.split("/")[1]), Integer.valueOf(fecha.split("/")[0]));
				return f;
			} catch (Exception e) {
				System.out.println("Formato no correcto... vuelve a intentarlo!");
			}
		} while (!false);//tecnicamente no es un while true
	}
	
	/*CONSULTA*/
	
	/**
	 * Consulta de historial
	 * @param scan
	 */
	private void ConsutlaRevision(Scanner scan) {
		PacienteHabitual paciente = null;
		String dni;
		LocalDate f1, f2;
		ArrayList<Consulta> consultas;
		System.out.print("Introduce el dni del cliente que quieras buscar: ");
		dni = scan.nextLine();

		for (Paciente p : clinica.getLP()) {
			if (p.getDni().equalsIgnoreCase(dni) && p.isEstado() && paciente instanceof PacienteHabitual) {
				paciente = (PacienteHabitual) p;
				break;
			}
		}
		
		if (paciente != null) {
			f1 = escribirFecha(scan, "Busca entre dos fechas, introduce la primera (dd/mm/aaaa): " );
			f2 = escribirFecha(scan, "Introduce la segunda (dd/mm/aaaa): ");
			
			//miramos el orden de las fechas y reordenamos
			if (f1.isAfter(f2)) {
				LocalDate aux = f1;
				f1 = f2;
				f2 = aux;
			}
			
			consultas = GetConsultas(paciente, f1, f2);
			
			if (consultas.size() != 0) {
				int contador = 0;
				for (Consulta c : consultas) {
					contador++;
					System.out.println("\nConsulta " + contador + ":");
					System.out.println("Consulta: " + c.getFechaConsulta() 
							+ "\nRevision: " + c.getFechaRevision() );					
				}
			} else {
				System.out.println("Consultas no encontrado...");
			}
			
			System.out.println("Quieres crear una nueva consulta para este paciente? (s para si)");
			
			if (scan.nextLine().equalsIgnoreCase("s")) {
				Consulta consulta = CrearConsulta(scan);
				paciente.getRevisiones().add(consulta);
				System.out.println("Consulta añadida con exito");
			}
			
		} else {
			System.out.println("No existe paciente con el dni: " + dni);
		}
	}
	
	/**
	 * Deveulve una consulta nueva
	 * @param scan
	 * @return
	 */
	private Consulta CrearConsulta(Scanner scan) {
		LocalDate fecha = escribirFecha(scan, "Introduce la fecha de la consulta(dd/mm/aaaa): ");
		return new Consulta(fecha, fecha.plusDays(21), clinica.getLS().get(0));
	}
	
	/**
	 * Saca una lista de historial dependiendo de dos fechas y del paciente
	 * @param paciente
	 * @param from
	 * @param to
	 * @return
	 */
	private ArrayList<Consulta> GetConsultas(PacienteHabitual paciente, LocalDate from, LocalDate to) {
		ArrayList<Consulta> cs = new ArrayList<>();
		for (Consulta c : paciente.getRevisiones()) {
			if (c.getFechaConsulta().isAfter(from) && c.getFechaConsulta().isBefore(to) || c.getFechaConsulta().isEqual(to) || c.getFechaConsulta().isEqual(from)) {
				cs.add(c);
			}
		}
		return cs;
	}
	
	/*Exportar a XML*/
	
	/**
	 * Guarada el historial de un paciente seleccionado, si es habitual tambien sus revisiones
	 * @param scan
	 */
	private void GuardarHistorial(Scanner scan) {
		Paciente paciente = null;
		String dni;
		System.out.print("Introduce el dni del cliente que quieras buscar: ");
		dni = scan.nextLine();

		for (Paciente p : clinica.getLP()) {
			if (p.getDni().equalsIgnoreCase(dni)) {
				paciente = p;
				break;
			}
		}
		
		if (paciente != null) {
			if (paciente instanceof PacienteHabitual) {
				ImprimirRevisiones((PacienteHabitual)paciente);
			}
			ImprimirHistorial(paciente);
		} else {
			System.out.println("No existe paciente con el dni: " + dni);
		}
	}
	
	/**
	 * Guardado de revisiones de un paciente
	 * @param paciente
	 */
	private void ImprimirRevisiones(PacienteHabitual paciente) {
		ContenedorRevision cr = new ContenedorRevision(paciente.getRevisiones());
		try{
            XStream xstream = new XStream();
            //cambiar de nombre a las etiquetas XML
            xstream.alias("Revision", ContenedorRevision.class);
            xstream.alias("Consulta", Consulta.class);
            //quitar etiqueta lista (atributo de la clase ListaAlumno
            xstream.addImplicitCollection(ContenedorRevision.class, "revisiones");
            //Insertar los objetos en el XML
            xstream.toXML(cr, new FileOutputStream(FILE_CR + paciente.getDni() + ".xml"));
            System.out.println("Creado el fichero xml con las revisiones");
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	/**
	 * Guardado del historial de un paciente
	 * @param paciente
	 */
	private void ImprimirHistorial(Paciente paciente) {
		ContenedorHistorial ch = new ContenedorHistorial(paciente.getH());
		try{
            XStream xstream = new XStream();
            //cambiar de nombre a las etiquetas XML
            xstream.alias("Historial", ContenedorHistorial.class);
            xstream.alias("Historial", Historial.class);
            //quitar etiqueta lista (atributo de la clase ListaAlumno
            xstream.addImplicitCollection(ContenedorHistorial.class, "historial");
            //Insertar los objetos en el XML
            xstream.toXML(ch, new FileOutputStream(FILE_H + paciente.getDni() + ".xml"));
            System.out.println("Creado el fichero xml con el historial");
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	
	/*Guardar clinica*/
	
	/**
	 * Guarda la clinica en un fichero
	 * @param scan
	 * @return
	 */
	private boolean SaveClinic(Scanner scan){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(clinica);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.out.println("Algo ha salido mal con el guardado, salir igualmente (s para si)?");
			if (!scan.nextLine().equalsIgnoreCase("s")) {
				return true;
			}
		}
		System.out.println("Guardado con exito");
		return false;
	}
	
}
