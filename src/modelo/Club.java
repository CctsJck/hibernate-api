package modelo;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import daos.ClubDAO;
import daos.JugadorDAO;
import daos.PartidoDAO;
import daos.RepresentanteDAO;
import daos.TablaPosicionesDAO;
import exceptions.ClubException;
import vo.ClubVO;
import vo.JugadorVO;

public class Club implements Comparable<Club>{

	private Integer idClub;
	private String nombre;
	private String direccion;
	private List<Responsable> responsables;
	private List<Jugador> jugadores;
	private List<Partido> partidosLocal;
	private List<Partido> partidosVisitante;
	private List<Campeonato> participaciones;
	private List<TablaPosiciones> tablaPosiciones;
	
	
	public Club(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		jugadores = new ArrayList<Jugador>();
		responsables = new ArrayList<Responsable>();
		participaciones = new ArrayList<Campeonato>();
		this.tablaPosiciones = new ArrayList<TablaPosiciones>();
		partidosVisitante = new ArrayList<Partido>();
		partidosLocal = new ArrayList<Partido>();
		
	}
	
	
    public List<Partido> getPartidosLocal(Integer idClub) throws ClubException {
        return PartidoDAO.getInstancia().obtenerPartidoLocal(idClub);
    }

    public List<Partido> getPartidosVisitante(Integer idClub) throws ClubException {
        return PartidoDAO.getInstancia().obtenerPartidoVisitante(idClub);
    }

	public void setPartidosLocal(List<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	

	public void setPartidosVisitante(List<Partido> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}

	public List<Campeonato> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<Campeonato> participaciones) {
		this.participaciones = participaciones;
	}

	public List<TablaPosiciones> getTablaPosiciones() {
		return TablaPosicionesDAO.getInstancia().getTablaPosiciones(this.idClub);
	}

	public void setTablaPosiciones(List<TablaPosiciones> tablaPosiciones) {
		this.tablaPosiciones = tablaPosiciones;
	}

	public void setResponsables(List<Responsable> responsables) {
		this.responsables = responsables;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public void asignarResponsable(Responsable responsable) {
		responsables.add(responsable);
	}
	
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarJugador(Jugador jugador) {
		jugador.grabar();
	}

	public Integer getIdClub() {
		return idClub;
	}
	public void setIdClub(Integer idClub) {
		this.idClub = idClub;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public List<Responsable> getResponsables() {
		return RepresentanteDAO.getInstancia().getResponsablesClub(this.idClub);
	}

	public List<Jugador> getJugadores() throws ClubException {
		return ClubDAO.getInstancia().obtenerJugadoresClub(this.idClub);
	}
	
	

	@Override
	public int compareTo(Club o) {
		return this.getIdClub().compareTo(o.getIdClub());
	}
	
	public boolean participa(Campeonato campeonato) {
		return participaciones.contains(campeonato);
	}
	
	public void participar(Campeonato campeonato) {
		participaciones.add(campeonato);
		ClubDAO.getInstancia().actualizarClub(this);
	}
	
	public void agregarJugadoresToListaLocal(Jugador jugador, Partido partido) {
		partido.agregarJugadoresLocales(new Miembro(this, partido, jugador));
	}
	
	public void agregarJugadoresToListaVisitante(Jugador jugador, Partido partido) {
		partido.agregarJugadoresVisitantes(new Miembro(this, partido, jugador));
	}

	public void modificar(int idClub, String nombre, String direccion) {
		
		ClubDAO.getInstancia().modificarClub(this,idClub,nombre,direccion);
	}
	
	public void grabar() {
		ClubDAO.getInstancia().grabar(this);
	}

	public void borrar() {
		
		ClubDAO.getInstancia().eliminarClub(this);
		
	}
	
	public void actualizar() {
		ClubDAO.getInstancia().actualizarClub(this);
	}
	
	public ClubVO toVO() {
		return new ClubVO(this.idClub, this.nombre, this.direccion);
	}
	
	public void darBajaJugador(Jugador jugador) {
		jugador.setEliminado("eliminado");
		JugadorDAO.getInstancia().eliminarJugador(jugador);
		jugador.borrar();
	}
}
