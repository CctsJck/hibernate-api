package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Entity.FaltaEntity;
import Entity.PartidoEntity;
import Entity.TablaPosicionesEntity;
import controlador.Controlador;
import daos.CampeonatoDAO;
import daos.ClubDAO;
import daos.FaltaDAO;
import daos.GolDAO;
import daos.JugadorDAO;
import daos.PartidoDAO;
import daos.TablaPosicionesDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import vo.CampeonatoVO;
import vo.ClubVO;
import vo.FaltaVO;
import vo.JugadorVO;
import vo.PartidoVO;

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
		this.categoria = categoria;
		
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
	
	public List<Jugador> getJugadores() throws CampeonatoException {
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
	
	public void crearPartidos(int nroFecha, int nroZona, Club clubLocal, Club clubVisitante,Date fechaPartido) {
		
		//CampeonatoDAO.getInstancia().crearPartidos(this.inscriptos,this.inscriptos,this);
		if (this.getEstado() == "inactivo") {
			Partido partido = new Partido(nroFecha, nroZona, clubLocal, clubVisitante,fechaPartido, this);
			partido.grabar();
		}
		
		
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
	
	public String[][] getEstaditicaJugadoresCampeonato() throws CampeonatoException{
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
	
	public void crearPartidosGrupos() throws ClubException {
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
		System.out.println(jornadas2.length * incremento);
		calendar2.add(Calendar.DAY_OF_YEAR, (jornadas2.length * incremento));
		fechaPartidoVuelta = calendar2.getTime();
		 
		System.out.println(fechaPartidoVuelta);
		
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
						this.crearPartidos(i+1, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]) , fecha);
					}
					else {
						//jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
						this.crearPartidos(i+1, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]) , fecha);

					}
				}
				else {
					//jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
					this.crearPartidos(i+1, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]) , fecha);
					
				}
				
				
				//segunda vuelta - al reves que la primera
				if(j==0){
					if(i%2==0) {
						//jornadas2[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
						this.crearPartidos(fechaVuelta, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]) , fechaPartidoVuelta);

					}
					else {
						//jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
						this.crearPartidos(fechaVuelta, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]) , fechaPartidoVuelta);

					}
				}
				else {
					//jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
					this.crearPartidos(fechaVuelta, 0, ClubDAO.getInstancia().obtenerClubPorID(matriz2[i][j]),ClubDAO.getInstancia().obtenerClubPorID(matriz1[i][j]) , fechaPartidoVuelta);

				}
				
			}
			calendar.add(Calendar.DAY_OF_YEAR, incremento);
			fecha = calendar.getTime();
			
			calendar2.add(Calendar.DAY_OF_YEAR, incremento);
			fechaPartidoVuelta = calendar2.getTime();
			
			fechaVuelta+=1;
		}
	
	
	}

	
	
	
}
