package test;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import sessionManager.SessionManager;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException, ClubException, CampeonatoException, PartidoException, JugadorException {
		//SessionManager.getInstancia().openSession();
		SimpleDateFormat fecha = new SimpleDateFormat("dd-mm-yyyy");
		
		Controlador.getInstancia().crearCampeonato("Copa Libertadores", fecha.parse("20-08-2000"), fecha.parse("20-08-2001"), "Puntos",2001);
		
		
		Controlador.getInstancia().crearClub("Boca Juniors", "Brandsen");
		Controlador.getInstancia().crearClub("River Plate", "Av. Pres. Figueroa Alcorta");
		
		Controlador.getInstancia().crearRepresentante("DNI", 34968472, "Sebastian Bataglia", 1);
		Controlador.getInstancia().crearRepresentante("DNI", 29483556, "Marcelo Gallardo", 2);
		
		Controlador.getInstancia().crearPartido(1, 1, 02, 1, 2, fecha.parse("20-08-2000"), 1);
		
		
		Controlador.getInstancia().agregarJugador("DNI", 39968123, "Aguston", "Rossi", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968122, "Carlos", "Izquierdos", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 32968121, "Marcos", "Rojo", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968123, "Frank", "Fabra", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 36968128, "Agustin", "Sandez", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 35968129, "Jorman", "Campuzano", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968125, "Juan Edgardo", "Ramirez", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 35968126, "Esteban", "Rolon", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 31968121, "Luis", "Vazquez", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 40596812, "Nicolas", "Orsini", 1, new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 34196812, "Cristian", "Pavon", 1, new java.sql.Date(2000,12,5));
		
		Controlador.getInstancia().agregarJugador("DNI", 42968478, "Julian", "Alvarez", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 30968477, "Leonardo", "Ponzio", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 40968476, "Enzo", "Perez", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968475, "David", "Martinez", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 41966474, "Bruno", "Zuculini", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 36958473, "Franco", "Armani", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 31968472, "Jonathan", "Maidana", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968475, "Javier", "Pinola", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968476, "Milton", "Casco", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968473, "Alex", "Vigo", 2,new java.sql.Date(2000,12,5));
		Controlador.getInstancia().agregarJugador("DNI", 33968471, "Federico", "Girotii", 2,new java.sql.Date(2000,12,5));
		
		
		Controlador.getInstancia().asignarParticipaciones(1, 1);
		Controlador.getInstancia().asignarParticipaciones(2, 1);
		
		Controlador.getInstancia().finalizarCargaEquiposTorneo(1);
		
		Controlador.getInstancia().agregarJugadorPartido(1, 1, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 2, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 3, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 4, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 5, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 6, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 7, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 8, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 8, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 10, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 11, 1);
		
		Controlador.getInstancia().agregarJugadorPartido(1, 12, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 13, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 14, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 15, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 16, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 17, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 18, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 19, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 20, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 21, 2);
		Controlador.getInstancia().agregarJugadorPartido(1, 22, 2);
		
		Controlador.getInstancia().agregarGolJugador(12, 1, 90, "a favor");
		Controlador.getInstancia().agregarGolJugador(22, 1, 31, "a favor");
		
		Controlador.getInstancia().agregarFaltaJugador(1, 1, 1, 40,"Roja");
		
		Controlador.getInstancia().validarPartido(1, 1);
		Controlador.getInstancia().validarPartido(2, 1);
		
		Controlador.getInstancia().getEstaditicaJugadoresCampeonato(1);
		System.out.println("hola");
		
		//Descomentar si desea modificar o eliminar
		
		//Controlador.getInstancia().modificarClub(2, "Club Atletico River Plate", "Av Figueroa Alcorta");
		//Controlador.getInstancia().modificarJugador(1, "DNI", 44123123, "Agustin", "Rossi", 1,new java.sql.Date(2000,12,5));
		//Controlador.getInstancia().modificarRepresentante(1, "Miguel Angel Russo", 29968472, "DNI", 1);
		
		//Controlador.getInstancia().eliminarCampeonato(1);
		//Controlador.getInstancia().eliminarJugador(1);
		//Controlador.getInstancia().eliminarRepresentante(1);

		
		//SessionManager.getInstancia().closeSession();
		System.out.println("Probando VSCode");
		
		

	}

}
