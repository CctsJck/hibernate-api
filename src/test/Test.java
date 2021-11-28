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
import exceptions.ResponsableException;
import exceptions.UsuarioException;
import sessionManager.SessionManager;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException, ClubException, CampeonatoException, PartidoException, JugadorException, UsuarioException, ResponsableException {
		
		SimpleDateFormat fecha = new SimpleDateFormat("MM-dd-yyyy");
		 
		//CREACION DE CAMPEONATOS
		//fijarse cuando ejecutamos el test si el campeonato es por Puntos o por Zona
		Controlador.getInstancia().crearCampeonato("Copa Libertadores", fecha.parse("8-20-2021"), fecha.parse("11-20-2021"), "Zona",90);
		Controlador.getInstancia().crearCampeonato("La Liga", fecha.parse("8-20-2021"), fecha.parse("11-20-2021"), "Puntos",90);
		
		
		//CREACCION DE CLUBES
		Controlador.getInstancia().crearClub("Boca Juniors", "Brandsen");
		Controlador.getInstancia().crearClub("River Plate", "Av. Pres. Figueroa Alcorta"); 
		Controlador.getInstancia().crearClub("Racing Club", "Diego A. Milito, Avellaneda"); 
		Controlador.getInstancia().crearClub("Club Atlético San Lorenzo de Almagro", "Av. Perito Moreno 2145"); 
		
		//CREACION DE REPRESENTANTES
		Controlador.getInstancia().crearRepresentante("DNI", 34968472, "Sebastian Bataglia", 1);
		Controlador.getInstancia().crearRepresentante("DNI", 29483556, "Marcelo Gallardo", 2);
		Controlador.getInstancia().crearRepresentante("DNI", 21938517, "Fernando Gago", 3);
		Controlador.getInstancia().crearRepresentante("DNI", 22705398, "Paolo Montero",4);
		
		
		//CREACION DE JUGADORES DE BOCA
		Controlador.getInstancia().agregarJugador("DNI", 39968123, "Aguston", "Rossi", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968122, "Carlos", "Izquierdos", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-1999"));
		Controlador.getInstancia().agregarJugador("DNI", 32968121, "Marcos", "Rojo", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968123, "Frank", "Fabra", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 36968128, "Agustin", "Sandez", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 35968129, "Jorman", "Campuzano", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968125, "Juan Edgardo", "Ramirez", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 35968126, "Esteban", "Rolon", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 31968121, "Luis", "Vazquez", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 40596812, "Nicolas", "Orsini", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 34196812, "Cristian", "Pavon", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		
		//CREACION DE JUGADORES DE RIVER
		Controlador.getInstancia().agregarJugador("DNI", 42968478, "Julian", "Alvarez", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 30968477, "Leonardo", "Ponzio", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 40968476, "Enzo", "Perez", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968490, "David", "Martinez",2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 41966474, "Bruno", "Zuculini", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 36958473, "Franco", "Armani", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 31968472, "Jonathan", "Maidana", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968475, "Javier", "Pinola", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968476, "Milton", "Casco", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968473, "Alex", "Vigo", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968471, "Federico", "Girotii", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		
		//CREACION DE JUGADORES DE RACING CLUB
		Controlador.getInstancia().agregarJugador("DNI", 12312312, "Gabriel", "Arias", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 25189821, "Ivan Alexis", "Pillud", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 12314613, "Leonardo German", "Sigali", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 54122333, "Nery Andres", "Dominguez",3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 42323335, "Fernando Andres", "Prado Avelino", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 21312314, "Leonel Ariel", "Miranda", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 35421234, "Carlos Jonas", "Alcaraz", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 22231456, "Lisandro", "Lopez", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33333333, "Marcelo Javier", "Correa", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 23456213, "Enzo Nahuel", "Copetti", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 41568492, "Ignacio Alberto", "Piatti", 3,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		
		//CREACION DE JUGADORES DE SAN LORENZO
		Controlador.getInstancia().agregarJugador("DNI", 22222223, "Sebastian", "Torrico", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 44444444, "Marcelo", "Herrera", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 40468476, "Alejandro", "Donatti", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 35678090, "Cristian", "Zapata",4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 41966001, "Gabriel", "Rojas", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33959852, "Yeison", " Gordillo", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 42136872, "Néstor", "Ortigoza", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 43968271, "Nicolás", "Fernández Mercau", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 40000001, "Nicolas", "Fernández", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 30068473, "Franco", "Di Santo", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33953171, "Alexander", "Diaz", 4,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		
		//AGREGAR CLUB A CAMPEONATO 1
		Controlador.getInstancia().asignarParticipaciones(1, 1);
		Controlador.getInstancia().asignarParticipaciones(2, 1);
		Controlador.getInstancia().asignarParticipaciones(3, 1);
		Controlador.getInstancia().asignarParticipaciones(4, 1);
		
		//AGREGAR CLUB A CAMPEONATO 2
		Controlador.getInstancia().asignarParticipaciones(1, 2);
		Controlador.getInstancia().asignarParticipaciones(2, 2);

		
		//INSCRIBIR JUGADORES A CAMPEOANTO 1
		Controlador.getInstancia().agregarJugadorCampeonato(1, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(2, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(3, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(4, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(5, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(6, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(7, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(8, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(9, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(10, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(11, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(12, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(13, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(14, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(15, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(16, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(17, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(18, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(19, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(20, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(21, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(22, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(23, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(24, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(25, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(26, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(27, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(28, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(29, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(30, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(31, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(32, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(33, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(34, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(35, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(36, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(37, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(38, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(39, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(40, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(41, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(42, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(43, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(44, 1);
		
		//INSCRIBIR JUGADORES A CAMPEOANTO 2
		Controlador.getInstancia().agregarJugadorCampeonato(1, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(2, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(3, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(4, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(5, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(6, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(7, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(8, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(9, 2);
		Controlador.getInstancia().agregarJugadorCampeonato(10,2);
		Controlador.getInstancia().agregarJugadorCampeonato(11,2);
		Controlador.getInstancia().agregarJugadorCampeonato(12,2);
		Controlador.getInstancia().agregarJugadorCampeonato(13,2);
		Controlador.getInstancia().agregarJugadorCampeonato(14,2);
		Controlador.getInstancia().agregarJugadorCampeonato(15,2);
		Controlador.getInstancia().agregarJugadorCampeonato(16,2);
		Controlador.getInstancia().agregarJugadorCampeonato(17,2);
		Controlador.getInstancia().agregarJugadorCampeonato(18,2);
		Controlador.getInstancia().agregarJugadorCampeonato(19,2);
		Controlador.getInstancia().agregarJugadorCampeonato(20,2);
		Controlador.getInstancia().agregarJugadorCampeonato(21,2);
		Controlador.getInstancia().agregarJugadorCampeonato(22,2);


		
		//CREACION DE PARTIDOS (EN CASO DE QUE EL TORNEO SEA POR PUNTOS NO DEBO CREAR LOS PARTIDOS)
		Controlador.getInstancia().crearPartido(1, 1, 1, 2, fecha.parse("8-21-2021"), 1,"grupos");
		Controlador.getInstancia().crearPartido(2, 1, 2, 1, fecha.parse("8-22-2021"), 1,"grupos");
		Controlador.getInstancia().crearPartido(1, 2, 3, 4, fecha.parse("8-23-2021"), 1,"grupos");
		Controlador.getInstancia().crearPartido(2, 2, 4, 3, fecha.parse("8-24-2021"), 1,"grupos");
		Controlador.getInstancia().crearPartido(2, 2, 2, 3, fecha.parse("8-25-2021"), 1,"Finales");
		Controlador.getInstancia().crearPartido(2, 2, 3, 2, fecha.parse("8-26-2021"), 1,"Finales");
		
		//ACTIVACION DE CAMPEONATO 1
		Controlador.getInstancia().activarCampeonato(1);
		
		//ACTIVACION DE CAMPEONATO 2
		Controlador.getInstancia().activarCampeonato(2);
		
		//AGREGAR JUGADORES DE BOCA A PARTIDO 1
		Controlador.getInstancia().agregarJugadorPartido(1, 1, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 2, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 3, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 4, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 5, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 6, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 7, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 8, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 9, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 10, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 11, 1);
		
		//AGREGAR JUGADORES DE BOCA A PARTIDO 2
		Controlador.getInstancia().agregarJugadorPartido(2, 1, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 2, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 3, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 4, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 5, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 6, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 7, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 8, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 9, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 10, 1);
		Controlador.getInstancia().agregarJugadorPartido(2, 11, 1);
		
		//AGREGAR JUGADORES DE RIVER A PARTIDO 1
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
		
		//AGREGAR JUGADORES DE RIVER A PARTIDO 2
		Controlador.getInstancia().agregarJugadorPartido(2, 12, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 13, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 14, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 15, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 16, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 17, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 18, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 19, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 20, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 21, 2);
		Controlador.getInstancia().agregarJugadorPartido(2, 22, 2);
		
		//AGREGAR JUGADORES DE RACING CLUB A PARTIDO 3
		Controlador.getInstancia().agregarJugadorPartido(3, 23, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 24, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 25, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 26, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 27, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 28, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 29, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 30, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 31, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 32, 3);
		Controlador.getInstancia().agregarJugadorPartido(3, 33, 3);
		
		//AGREGAR JUGADORES DE RACING CLUB A PARTIDO 4
		Controlador.getInstancia().agregarJugadorPartido(4, 23, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 24, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 25, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 26, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 27, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 28, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 29, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 30, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 31, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 32, 3);
		Controlador.getInstancia().agregarJugadorPartido(4, 33, 3);
		
		//AGREGAR JUGADORES DE SAN LORENZO A PARTIDO 3
		Controlador.getInstancia().agregarJugadorPartido(3, 34, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 35, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 36, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 37, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 38, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 39, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 40, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 41, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 42, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 43, 4);
		Controlador.getInstancia().agregarJugadorPartido(3, 44, 4);
		
		//AGREGAR JUGADORES DE SAN LORENZO A PARTIDO 4
		Controlador.getInstancia().agregarJugadorPartido(4, 34, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 35, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 36, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 37, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 38, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 39, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 40, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 41, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 42, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 43, 4);
		Controlador.getInstancia().agregarJugadorPartido(4, 44, 4);
		
		//AGREGAR JUGADORES DE RIVER PARTIDO 5
		Controlador.getInstancia().agregarJugadorPartido(5, 12, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 13, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 14, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 15, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 16, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 17, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 18, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 19, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 20, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 21, 2);
		Controlador.getInstancia().agregarJugadorPartido(5, 22, 2);
		
		//AGREGAR JUGADORES DE RIVER PARTIDO 6
		Controlador.getInstancia().agregarJugadorPartido(6, 12, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 13, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 14, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 15, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 16, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 17, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 18, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 19, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 20, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 21, 2);
		Controlador.getInstancia().agregarJugadorPartido(6, 22, 2);
		
		//AGREGAR JUGADORES DE RACING CLUB A PARTIDO 5
		Controlador.getInstancia().agregarJugadorPartido(5, 23, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 24, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 25, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 26, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 27, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 28, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 29, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 30, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 31, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 32, 3);
		Controlador.getInstancia().agregarJugadorPartido(5, 33, 3);
		
		//AGREGAR JUGADORES DE RACING CLUB A PARTIDO 6
		Controlador.getInstancia().agregarJugadorPartido(6, 23, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 24, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 25, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 26, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 27, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 28, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 29, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 30, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 31, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 32, 3);
		Controlador.getInstancia().agregarJugadorPartido(6, 33, 3);
		
		//AGREGAR GOLES A PARTIDO 1
		Controlador.getInstancia().agregarGolJugador(12, 1, 90, "a favor");
		Controlador.getInstancia().agregarGolJugador(22, 1, 31, "a favor");
		
		//AGREGAR GOLES A PARTIDO 2
		
		//AGREGAR GOLES A PARTIDO 3
		Controlador.getInstancia().agregarGolJugador(32, 3, 22, "a favor");
		Controlador.getInstancia().agregarGolJugador(42, 4, 63, "a favor");
		
		//AGREGAR GOLES A PARTIDO 4
		Controlador.getInstancia().agregarGolJugador(30, 4, 44, "a favor");
		
		//AGREGAR GOLES A PARTIDO 5
		Controlador.getInstancia().agregarGolJugador(12, 5, 21, "a favor");
		
		//AGREGAR GOLES A PARTIDO 6
		Controlador.getInstancia().agregarGolJugador(12, 6, 33, "a favor");
		Controlador.getInstancia().agregarGolJugador(22, 6, 66, "a favor");
		
		//AGREGAR GOLES A PARTIDO 7
		Controlador.getInstancia().agregarGolJugador(1, 7, 12, "a favor");
		
		//AGREGAR GOLES A PARTIDO 8

		
		//AGREGAR FALTAS A JUGADORES PARTIDO 1
		Controlador.getInstancia().agregarFaltaJugador(3, 1, 1, 33,"Amarilla");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 2
		Controlador.getInstancia().agregarFaltaJugador(1, 2, 1, 40,"Roja");
		Controlador.getInstancia().agregarFaltaJugador(3, 2, 1, 76,"Amarilla");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 3
		Controlador.getInstancia().agregarFaltaJugador(27, 3, 1, 11,"Amarilla");
		Controlador.getInstancia().agregarFaltaJugador(39, 3, 1, 38,"Amarilla");
		Controlador.getInstancia().agregarFaltaJugador(30, 3, 1, 80,"Amarilla");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 4
		Controlador.getInstancia().agregarFaltaJugador(32, 3, 1, 22,"Amarilla");
		Controlador.getInstancia().agregarFaltaJugador(43, 3, 1, 51,"Amarilla");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 5
		Controlador.getInstancia().agregarFaltaJugador(15, 5, 1, 22,"Amarilla");
		Controlador.getInstancia().agregarFaltaJugador(25, 5, 1, 22,"Amarilla");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 6
		Controlador.getInstancia().agregarFaltaJugador(17, 6, 1, 22,"Amarilla");
		Controlador.getInstancia().agregarFaltaJugador(32, 6, 1, 22,"Roja");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 7
		Controlador.getInstancia().agregarFaltaJugador(3, 7, 2, 33,"Amarilla");
		Controlador.getInstancia().agregarFaltaJugador(12, 7, 2, 24,"Amarilla");
		
		//AGREGAR FALTAS A JUGADORES PARTIDO 8
		Controlador.getInstancia().agregarFaltaJugador(1, 8, 2, 11,"Roja");
		
		
		//VALIDAR PARTIDOS
		Controlador.getInstancia().validarPartido(1, 1);
		Controlador.getInstancia().validarPartido(2, 1);
		Controlador.getInstancia().validarPartido(1, 2);
		Controlador.getInstancia().validarPartido(2, 2);
		Controlador.getInstancia().validarPartido(3, 3);
		Controlador.getInstancia().validarPartido(4, 3);
		Controlador.getInstancia().validarPartido(3, 4);
		Controlador.getInstancia().validarPartido(4, 4);
		Controlador.getInstancia().validarPartido(2, 5);
		Controlador.getInstancia().validarPartido(3, 5);
		Controlador.getInstancia().validarPartido(2, 6);
		Controlador.getInstancia().validarPartido(3, 6);
		Controlador.getInstancia().validarPartido(1, 7);
		Controlador.getInstancia().validarPartido(2, 7);
		Controlador.getInstancia().validarPartido(1, 8);
		Controlador.getInstancia().validarPartido(2, 8);
		
		System.out.println();
		System.out.println("Se finalizo la carga con exito");

		 
		/*//fijarse cuando ejecutamos el test si el campeonato es por Puntos o por Zona
		
		Controlador.getInstancia().crearClub("Boca jrs", "Lalslals");
		Controlador.getInstancia().crearClub("River Plate", "Av. Pres. Figueroa Alcorta");*/
		/* Controlador.getInstancia().crearClub("Racing","Avellaneda");
        Controlador.getInstancia().crearClub("Independiente", "Avellaneda");
        Controlador.getInstancia().crearClub("DYJ","Varela");
        Controlador.getInstancia().crearClub("All Boys", "Floresta");
		
		Controlador.getInstancia().crearRepresentante("DNI", 34968472, "Sebastian Bataglia", 1);
		Controlador.getInstancia().crearRepresentante("DNI", 29483556, "Marcelo Gallardo", 2);
		
	

        Controlador.getInstancia().crearPartido(1, 1, 1, 2, fecha.parse("20-08-2000"), 1,"grupos");
        Controlador.getInstancia().crearPartido(2, 1, 1, 5, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1, 1, 6, fecha.parse("20-08-2000"), 1, "grupos");

        Controlador.getInstancia().crearPartido(2, 1, 2, 1, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1, 2, 5, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1, 2, 6, fecha.parse("20-08-2000"), 1, "grupos");

        Controlador.getInstancia().crearPartido(2, 1, 5, 1, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1, 5, 2, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1, 5, 6, fecha.parse("20-08-2000"), 1, "grupos");

        Controlador.getInstancia().crearPartido(2, 1, 6, 1, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1 ,6, 2, fecha.parse("20-08-2000"), 1, "grupos");
        Controlador.getInstancia().crearPartido(2, 1, 6, 5, fecha.parse("20-08-2000"), 1, "grupos");
        
        Controlador.getInstancia().crearTablasGrupos(1);*/
		
		//Controlador.getInstancia().crearPartido(1, 1, 1, 2, fecha.parse("20-08-2000"), 1,null);
		//Controlador.getInstancia().crearPartido(2, 1,, 1, 2, fecha.parse("20-08-2000"), 1, null);

		
		/*Controlador.getInstancia().agregarJugador("DNI", 39968123, "Aguston", "Rossi", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968122, "Carlos", "Izquierdos", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-1999"));
		Controlador.getInstancia().agregarJugador("DNI", 32968121, "Marcos", "Rojo", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968123, "Frank", "Fabra", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 36968128, "Agustin", "Sandez", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 35968129, "Jorman", "Campuzano", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968125, "Juan Edgardo", "Ramirez", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 35968126, "Esteban", "Rolon", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 31968121, "Luis", "Vazquez", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 40596812, "Nicolas", "Orsini", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 34196812, "Cristian", "Pavon", 1, fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		
		Controlador.getInstancia().agregarJugador("DNI", 42968478, "Julian", "Alvarez", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 30968477, "Leonardo", "Ponzio", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 40968476, "Enzo", "Perez", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968490, "David", "Martinez",2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 41966474, "Bruno", "Zuculini", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 36958473, "Franco", "Armani", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 31968472, "Jonathan", "Maidana", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968475, "Javier", "Pinola", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968476, "Milton", "Casco", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968473, "Alex", "Vigo", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		Controlador.getInstancia().agregarJugador("DNI", 33968471, "Federico", "Girotii", 2,fecha.parse("20-08-2001"),fecha.parse("20-08-2001"));
		
		
		Controlador.getInstancia().asignarParticipaciones(1, 1);
		Controlador.getInstancia().asignarParticipaciones(2, 1);
		
		Controlador.getInstancia().activarCampeonato(1);
		
		Controlador.getInstancia().agregarJugadorCampeonato(1, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(2, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(3, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(4, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(5, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(6, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(7, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(8, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(9, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(10, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(11, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(12, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(13, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(14, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(15, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(16, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(17, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(18, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(19, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(20, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(21, 1);
		Controlador.getInstancia().agregarJugadorCampeonato(22, 1);
		
		
		Controlador.getInstancia().agregarJugadorPartido(1, 1, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 2, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 3, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 4, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 5, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 6, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 7, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 8, 1);
		Controlador.getInstancia().agregarJugadorPartido(1, 9, 1);
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
		Controlador.getInstancia().validarPartido(1, 4);
		
		//Controlador.getInstancia().getEstaditicaJugadoresCampeonato(1);
		
		
		//Descomentar si desea modificar o eliminar
		
		//Controlador.getInstancia().modificarClub(2, "Club Atletico River Plate", "Av Figueroa Alcorta");
		//Controlador.getInstancia().modificarJugador(1, "DNI", 44123123, "Agustin", "Rossi", 1,new java.sql.Date(2000,12,5));
		//Controlador.getInstancia().modificarRepresentante(1, "Miguel Angel Russo", 29968472, "DNI", 1);
		
		//Controlador.getInstancia().eliminarCampeonato(1);
		//Controlador.getInstancia().eliminarJugador(1);
		//Controlador.getInstancia().eliminarRepresentante(1);*/

	}

}
