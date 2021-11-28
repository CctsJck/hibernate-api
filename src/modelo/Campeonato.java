package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import Entity.FaltaEntity;
import Entity.PartidoEntity;
import Entity.TablaPosicionesEntity;
import controlador.Controlador;
import daos.CampeonatoDAO;
import daos.ClubDAO;
import daos.FaltaDAO;
import daos.GolDAO;
import daos.JugadorDAO;
import daos.JugadoresTorneoDAO;
import daos.MiembroDAO;
import daos.PartidoDAO;
import daos.TablaPosicionesDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import vo.CampeonatoVO;
import vo.ClubVO;
import vo.FaltaVO;
import vo.JugadorVO;
import vo.PartidoVO;
import vo.TablaPosicionesVO;

public class Campeonato implements Comparable<Campeonato>{
	
	private Integer idCampeonato;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private String eliminadoC;
	private List<Club> inscriptos;
	private List<Falta> faltas;
	private List<Partido> partidos;
	private TablaPosiciones tablaPosiciones;
	private String tipo;
	private int categoria;
	private List<JugadoresTorneo> jugadoresHabilitados;
	




	public Campeonato(String descripcion, Date fechaInicio, Date fechaFin , String tipo,int categoria) {
		this.idCampeonato = null;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = "inactivo";
		this.inscriptos = new ArrayList<Club>();
		this.faltas = new ArrayList<Falta>();
		this.partidos = new ArrayList<Partido>();
		this.tipo = tipo;
		int auxCategoria = categoria;
		if (categoria > 100) {
			if(Integer.compare(auxCategoria, 2000) == 1 || Integer.compare(auxCategoria,2000) == 0) {
	        	this.categoria = auxCategoria - 2000;
	        }else {
	        	this.categoria = auxCategoria - 1900;
	        	}
		}else {
			this.categoria = categoria;
		}
        
	}



	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public Integer getIdCampeonato() {
		return idCampeonato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}


	@Override
	public int compareTo(Campeonato o) {
		return this.getIdCampeonato().compareTo(o.getIdCampeonato());
	}
	
	public void inscribirClub(Club club) throws ClubException, CampeonatoException {
        List<Club> inscriptos = this.inscriptos;
        boolean var = inscriptos.contains(club);
        if (var == false) {
            inscriptos.add(club);
            CampeonatoDAO.getInstancia().actualizar(this);
			//Generamos la tabla con los valores por defecto al club que se inscribio
			TablaPosicionesDAO.getInstancia().crearTablaPosiciones(club.getIdClub(), this.getIdCampeonato());
        }
    }

	public List<Falta> getFaltas() throws ClubException {
		return FaltaDAO.getInstancia().obtenerFaltasCampeonato(this.getIdCampeonato());
	}

	public void setFaltas(List<Falta> faltas) {
		this.faltas = faltas;
	}

	public List<Partido> getPartidos() throws ClubException {
		return PartidoDAO.getInstancia().obtenerPartidoDeCampeonato(this.getIdCampeonato());
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}


	public void setTablaPosiciones(TablaPosiciones tablaPosiciones) {
		this.tablaPosiciones = tablaPosiciones;
	}
	
	public void agregarClub(Club club) {
		this.inscriptos.add(club);
	}

	public List<Club> getInscriptos() {
		return this.inscriptos;
	}

	public void setInscriptos(List<Club> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public void grabar() {
		CampeonatoDAO.getInstancia().grabar(this);
		
	}
	
	public void actualizar() {
		CampeonatoDAO.getInstancia().actualizar(this);
	}
	
	public void setIdCampeonato(Integer id) {
		this.idCampeonato = id;
	}
	
	public List<Jugador> getJugadores() throws CampeonatoException, ClubException {
		return CampeonatoDAO.getInstancia().getJugadoresCampeonato(this.idCampeonato);
	}

	public String getEliminado() {
		return eliminadoC;
	}

	public void setEliminado(String eliminado) {
		this.eliminadoC = eliminado;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void crearPartidos(int nroFecha, int nroZona, Club clubLocal, Club clubVisitante,Date fechaPartido,String fase) throws CampeonatoException {
		//if (this.getEstado().compareTo("inactivo") == 0) {
		if (!this.getFechaInicio().after(fechaPartido) && !this.getFechaFin().before(fechaPartido)) {
			Partido partido = new Partido(nroFecha, nroZona, clubLocal, clubVisitante,fechaPartido, this,fase);			
			partido.grabar();
		}else {
			throw new CampeonatoException("La fecha del partido no esta dentro de las fechas del campeonato");
		}
		//}
		
		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public CampeonatoVO toVO() {
		return new CampeonatoVO(this.idCampeonato, this.descripcion, this.fechaInicio, this.fechaFin, this.estado, this.eliminadoC, this.tipo, this.categoria);
	}
	
	public String[][] getEstaditicaJugadoresCampeonato() throws CampeonatoException, ClubException{
		List<Jugador> jugadoresCampeonato = this.getJugadores();
		String [][] datos = new String[jugadoresCampeonato.size()][4];
		int cont = 0;
		for(Jugador auxJugador: jugadoresCampeonato) {
			Long faltas = FaltaDAO.getInstancia().obtenerCantidadFaltasJugadorX(auxJugador.getIdJugador(), idCampeonato);
			Long goles = GolDAO.getInstancia().obtenerCantidadGolesJugadorCampeonato(auxJugador.getIdJugador(), idCampeonato);
			datos[cont][0] = auxJugador.getNombre();
			datos[cont][1] = auxJugador.getClub().getNombre();
			datos[cont][2] = Long.toString(faltas);
			datos[cont][3] = Long.toString(goles);
			cont +=1;
		}
		return datos;
	}
	
	public String[] getEstaditicaJugadorCampeonato(int idJugador) throws CampeonatoException, ClubException, JugadorException{
		Jugador jugadorCampeonato = JugadorDAO.getInstancia().obtenerJugador(idJugador);
		String [] datos = new String[7];
		Long faltas = FaltaDAO.getInstancia().obtenerCantidadFaltasJugadorX(idJugador, idCampeonato);
		Long goles = GolDAO.getInstancia().obtenerCantidadGolesJugadorCampeonato(idJugador, idCampeonato);
		Long partidosJugados = MiembroDAO.getInstancia().obtenerPartidosJugados(idJugador,this.getIdCampeonato());
		Long amarillas = JugadorDAO.getInstancia().obtenerAmarillas(idJugador,idCampeonato);
		Long rojas = JugadorDAO.getInstancia().obtenerRojas(idJugador,idCampeonato);
		datos[0] = jugadorCampeonato.getNombre();
		datos[1] = jugadorCampeonato.getClub().getNombre();
		datos[2] = Long.toString(partidosJugados);
		datos[3] = Long.toString(goles);
		datos[4] = Long.toString(faltas);
		datos[5] = Long.toString(amarillas);
		datos[6] = Long.toString(rojas);
		return datos;
    }
	
	public void crearPartidosGrupos() throws ClubException, CampeonatoException {
		Date fecha = this.getFechaInicio();
		Date fechaFin = this.getFechaFin();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar2.setTime(fechaFin);
		List<Integer> equipos = new ArrayList<Integer>();
		
		List<Club> clubesInscriptos = this.getInscriptos();
		//Agrego los idClubes a un array
		for (Club club : clubesInscriptos) {
			equipos.add(club.getIdClub());
		}
		int N = equipos.size();
		int fechaVuelta = N;
		
		int[][] matriz1,matriz2,jornadas,jornadas2;
		
		matriz1 = new int[N-1][N/2];
		matriz2 = new int[N-1][N/2];
		jornadas = new int[N-1][N/2]; //primera vuelta
		jornadas2 = new int[N-1][N/2]; //segunda vuelta
		
		//Obtener cada cuanto se juegan los partidos, depende de la diferencia entre fechaFin y fechaInicio, ese valor dividirlo por la cantidad de fechas (jornadas.length + jornadas2.length)
		long miliSecondForDate1 = calendar.getTimeInMillis();
		long miliSecondForDate2 = calendar2.getTimeInMillis();
		long diffInMilis = miliSecondForDate2 - miliSecondForDate1;
		int diffInDays = (int) (diffInMilis / (24 * 60 * 60 * 1000));
		int incremento = ((diffInDays) / (jornadas.length + jornadas2.length));
		//Fechas de los partidos de vuelta
		Date fechaPartidoVuelta = this.getFechaInicio();
		calendar2.setTime(fechaPartidoVuelta);
		calendar2.add(Calendar.DAY_OF_YEAR, (jornadas2.length * incremento));
		fechaPartidoVuelta = calendar2.getTime();
		int cont = 0;
		int cont2 = N-2;
		//Relleno la matriz con los partidos
		for(int i=0;i<N-1;i++){
			for(int j=0;j<N/2;j++){
				//matriz1
				matriz1[i][j] = equipos.get(cont);
				cont++;
				if(cont==(N-1)) cont=0;
				
				//matriz2
				if(j==0) matriz2[i][j] = N;
				else {
					matriz2[i][j] = equipos.get(cont2);
					cont2--;
					if(cont2==-1) cont2 = N-2;
				}				
				//Elaboro la matriz final de enfrentamientos por jornada (primera vuelta)
				if(j==0){
					if(i%2==0) {
						//jornadas[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
						this.crearPartidos(i+1, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]) , fecha,null);
					}
					else {
						//jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
						this.crearPartidos(i+1, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]) , fecha,null);

					}
				}
				else {
					//jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
					this.crearPartidos(i+1, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]) , fecha,null);
					
				}
				
				
				//segunda vuelta - al reves que la primera
				if(j==0){
					if(i%2==0) {
						//jornadas2[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
						this.crearPartidos(fechaVuelta, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]) , fechaPartidoVuelta,null);

					}
					else {
						//jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
						this.crearPartidos(fechaVuelta, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]) , fechaPartidoVuelta,null);

					}
				}
				else {
					//jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
					this.crearPartidos(fechaVuelta, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]) , fechaPartidoVuelta,null);

				}
				
			}
			calendar.add(Calendar.DAY_OF_YEAR, incremento);
			fecha = calendar.getTime();
			
			calendar2.add(Calendar.DAY_OF_YEAR, incremento);
			fechaPartidoVuelta = calendar2.getTime();
			
			fechaVuelta+=1;
		}
	
	
	}
	
	public List<JugadoresTorneo> getJugadoresHabilitados() {
		return jugadoresHabilitados;
	}



	public void setJugadoresHabilitados(List<JugadoresTorneo> jugadoresHabilitados) {
		this.jugadoresHabilitados = jugadoresHabilitados;
	}



	public void agregarJugadorTorneo(Jugador auxJugador) {
		JugadoresTorneo jugadorTorneo = new JugadoresTorneo (auxJugador,this,true);
		JugadoresTorneoDAO.getInstancia().save(jugadorTorneo);
	}
	
	
	public void agregarJugadorCampeonato(Jugador auxJugador) throws CampeonatoException, JugadorException {
		Integer categoriaTorneo = this.getCategoria();
		Integer categoriaJugador = auxJugador.getCategoria();
				if (categoriaTorneo >  21) {
					categoriaTorneo = categoriaTorneo + 1900;
					
				}else {
					categoriaTorneo = categoriaTorneo + 2000;
				}
				if (categoriaJugador > 21) {
					categoriaJugador = categoriaJugador + 1900;
					
				}else {
					categoriaJugador = categoriaJugador + 2000;
					
				}
				if (Integer.compare(categoriaTorneo, categoriaJugador) == -1 || Integer.compare(categoriaTorneo, categoriaJugador) == 0) {
					if (verificacionFechaFichaje(this.getFechaInicio(), auxJugador.getFichaje())!= true) {
						JugadoresTorneo aux = new JugadoresTorneo(auxJugador,this,true);
	                    JugadoresTorneoDAO.getInstancia().save(aux);
					}else {
						throw new JugadorException("El jugador fue fichado una vez comenzado el torneo");
					}
	
				}else {
					throw new JugadorException("El jugador no esta habilitado para jugar en un torneo de esta categoria");
				}
		
	}
	
	public TablaPosicionesVO[][] crearTablaGrupos(){
		List<Partido> auxPartidos = PartidoDAO.getInstancia().obtenerPartidosPorFase(idCampeonato);
		Set<Integer> zonas = new HashSet<>();
		Set<Integer> idClubesZona = new HashSet<>();
		for(Partido p: auxPartidos) {
			if (!zonas.contains(p.getNroZona())) {
				zonas.add(p.getNroZona());
			}
		}
		List<Integer> auxZonas = new ArrayList<>(zonas);
		int cantidadGrupos = auxZonas.size();
		int cantEquiposZona;
		List<Partido> auxPartidosZona = PartidoDAO.getInstancia().obtenerPartidosPorFaseYZona(idCampeonato,auxZonas.get(0));
		for (Partido par: auxPartidosZona) {
			if(!idClubesZona.contains(par.getClubLocal().getIdClub())){
				idClubesZona.add(par.getClubLocal().getIdClub());
			}
		}
		List<Club> clubesZona = new ArrayList<>();
		List<Integer> idClubes = new ArrayList<>(idClubesZona);
		for (Integer i: idClubes) {
			clubesZona.add(ClubDAO.getInstancia().obtenerClubPorID(i));
		}
		cantEquiposZona = idClubes.size();
		TablaPosiciones tabla[][]= new TablaPosiciones[cantidadGrupos][cantEquiposZona];
		TablaPosicionesVO tablaVO[][] =  new TablaPosicionesVO[cantidadGrupos][cantEquiposZona];
		for (Integer nroZona: auxZonas) {
			 auxPartidosZona = PartidoDAO.getInstancia().obtenerPartidosPorFaseYZona(idCampeonato,nroZona);
			for (Partido par: auxPartidosZona) {
				if(!idClubesZona.contains(par.getClubLocal().getIdClub())){
					idClubesZona.add(par.getClubLocal().getIdClub());
				}
			}
			
			clubesZona = new ArrayList<>();
			idClubes = new ArrayList<>(idClubesZona);
			for (Integer i: idClubes) {
				clubesZona.add(ClubDAO.getInstancia().obtenerClubPorID(i));
			}
			idClubesZona = new HashSet<>();
			for (int x=0;x<cantEquiposZona;x++) {
				TablaPosiciones t = new TablaPosiciones(clubesZona.get(x),this);
				tabla[nroZona-1][x] = t;
			}
			
			for (Partido p: auxPartidosZona) {
				
				for (int z=0;z<cantEquiposZona;z++) {
					if (Integer.compare(tabla[nroZona-1][z].getClub().getIdClub(), p.getClubLocal().getIdClub()) == 0) {
						tabla[nroZona-1][z] = modificarTablaGrupos(tabla[nroZona-1][z],p,p.getClubLocal());
					}
					if (Integer.compare(tabla[nroZona-1][z].getClub().getIdClub(), p.getClubVisitante().getIdClub()) == 0) {
						tabla[nroZona-1][z] = modificarTablaGrupos(tabla[nroZona-1][z],p,p.getClubVisitante());
					}
				}
			}
		
		}
		for (int c = 1;c<cantidadGrupos+1;c++) {
			for (int k =0;k<cantEquiposZona;k++) {
				tablaVO[c-1][k] = tabla[c-1][k].toVO();
			}
		}
		TablaPosicionesVO aux;
        for (int c =1;c<cantidadGrupos+1;c++) {
            Arrays.sort(tablaVO[c-1],Comparator.comparingInt(TablaPosicionesVO::getPuntos).reversed());
        }
        return tablaVO;
	}
	
	private boolean verificacionFechaFichaje(Date fechaInicio,Date fechaJugador) throws CampeonatoException {
		boolean resultado = false;
		if (fechaInicio.compareTo(fechaJugador) < 0) {
			
			resultado = true;
		}
		
		return resultado;
	}
	
	private TablaPosiciones modificarTablaGrupos(TablaPosiciones tabla,Partido partido,Club club) throws ClubException {
		tabla.setCantidadJugados(tabla.getCantidadJugados() + 1);
		if (Integer.compare(partido.getGolesLocal(), partido.getGolesVisitante())  == 0 ) {
			//Empataron el partido
			tabla.setCantidadempatados(tabla.getCantidadempatados() + 1);
			tabla.setPuntos(tabla.getPuntos() + 1);
		} else if (Integer.compare(club.getIdClub(), partido.getClubLocal().getIdClub()) == 0) {
			//El id que me pasan es el del club local
			if (Integer.compare(partido.getGolesLocal() , partido.getGolesVisitante()) == 1) {
				//El partido lo gano el equipo local
				tabla.setCantidadganados(tabla.getCantidadganados() + 1);
				
				//Actualizo los puntos
				tabla.setPuntos(tabla.getPuntos() + 3);
			} else if (Integer.compare(partido.getGolesLocal() , partido.getGolesVisitante()) == -1 ) {
				//El partido lo perdio el local
				tabla.setCantidadperdidos(tabla.getCantidadperdidos() + 1);
			}
			//Goles a favor del club local
			tabla.setGolesFavor(tabla.getGolesFavor() + partido.getGolesLocal());
			
			//Goles en contra del club local
			tabla.setGolesContra(tabla.getGolesContra() + partido.getGolesVisitante());
			 
			//Actualizo la diferencia
			tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());

				
		}  else if (Integer.compare(club.getIdClub(), partido.getClubVisitante().getIdClub()) == 0)  {
			//El id que me pasan es el del club visitante
			if (Integer.compare(partido.getGolesVisitante(), partido.getGolesLocal()) == 1 ) {
				//Gano el visitante
				tabla.setCantidadganados(tabla.getCantidadganados() + 1);
				tabla.setPuntos(tabla.getPuntos() + 3);
			} else if (Integer.compare(partido.getGolesVisitante(), partido.getGolesLocal()) == -1 ) {
				//Perdio el visitante
				tabla.setCantidadperdidos(tabla.getCantidadperdidos() + 1);
			}
			
			//Goles a favor del club visitante
			tabla.setGolesFavor(tabla.getGolesFavor() + partido.getGolesVisitante());
			
			//Goles en contra del club visitante
			tabla.setGolesContra(tabla.getGolesContra() + partido.getGolesLocal());
			
			//Actualizo la diferencia
			tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());
		}
		
		//Actualizo el promedio
		tabla.setPromedio((float) tabla.getPuntos() / (float) tabla.getCantidadJugados()); 
		
		
		return tabla;
	}
	

	
	
	
}
