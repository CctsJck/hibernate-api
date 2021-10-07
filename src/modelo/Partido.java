package modelo;

import java.util.Date;
import java.util.List;

import controlador.Controlador;
import daos.CampeonatoDAO;
import daos.PartidoDAO;
import daos.TablaPosicionesDAO;
import exceptions.ClubException;
import vo.PartidoVO;

public class Partido {

	private Integer idPartido;
	private int nroFecha;
	private int nroZona;
	//private int categoria;
	private Club clubLocal;
	private Club clubVisitante;
	private Integer golesLocal;
	private Integer golesVisitante;
	private Date fechaPartido;
	private boolean convalidaLocal;
	private boolean convalidaVisitante;
	private Campeonato campeonato;
	private List<Miembro> jugadoresLocales;
	private List<Miembro> jugadoresVisitantes;

	public Partido(int nroFecha, int nroZona, Club clubLocal, Club clubVisitante,Date fechaPartido, Campeonato campeonato) {
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		//this.categoria = categoria;
		this.clubLocal = clubLocal;
		this.clubVisitante = clubVisitante;
		this.golesLocal = 0;
		this.golesVisitante = 0;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = false;
		this.convalidaVisitante = false;
		this.campeonato = campeonato;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public int getNroFecha() {
		return nroFecha;
	}

	public int getNroZona() {
		return nroZona;
	}

	

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Club getClubLocal() {
		return clubLocal;
	}

	public Club getClubVisitante() {
		return clubVisitante;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public Date getFechaPartido() {
		return fechaPartido;
	}

	public boolean isConvalidaLocal() {
		return convalidaLocal;
	}

	public boolean isConvalidaVisitante() {
		return convalidaVisitante;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}
	public List<Miembro> getJugadoresLocales() throws ClubException {
		return PartidoDAO.getInstancia().getJugadoresPartido(this.idPartido, this.clubLocal.getIdClub());
	}
	public List<Miembro> getJugadoresVisitantes() throws ClubException {
		return PartidoDAO.getInstancia().getJugadoresPartido(this.idPartido, this.clubVisitante.getIdClub());
	}

	public void setNroFecha(int nroFecha) {
		this.nroFecha = nroFecha;
	}
	
	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public void setNroZona(int nroZona) {
		this.nroZona = nroZona;
	}


	public void setClubLocal(Club clubLocal) {
		this.clubLocal = clubLocal;
	}

	public void setClubVisitante(Club clubVisitante) {
		this.clubVisitante = clubVisitante;
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public void setConvalidaLocal(boolean estado) throws ClubException {
		this.convalidaLocal = estado;
		if (this.convalidaLocal = true) {
			TablaPosicionesDAO.getInstancia().modificarTabla(this.getClubLocal().getIdClub(),this);
		}
	}

	public void setConvalidaVisitante(boolean estado) throws ClubException {
		this.convalidaVisitante = estado;
		if (this.convalidaVisitante = true) {
			TablaPosicionesDAO.getInstancia().modificarTabla(this.getClubVisitante().getIdClub(),this);
		}


	}

	public void agregarJugadoresLocales(Miembro miembro) {
		this.jugadoresLocales.add(miembro);
	}

	public void agregarJugadoresVisitantes(Miembro miembro) {
		this.jugadoresVisitantes.add(miembro);
	}

	public void grabar() {
		PartidoDAO.getInstancia().grabar(this);
		
	}
	
	public void actualizar() {
		PartidoDAO.getInstancia().actualizar(this);
	}
	
	public PartidoVO toVO() {
		return new PartidoVO(this.idPartido,this.nroFecha,this.nroZona,this.golesLocal,this.golesVisitante,this.fechaPartido,this.convalidaLocal,this.convalidaVisitante);
	}
	
	public void agregarGolJugador(Jugador auxJugador, int minuto, String tipo) {
		Gol nuevoGol = new Gol(auxJugador,this,minuto,tipo);
		auxJugador.agregarGol(nuevoGol);
		if (this.getClubLocal().getIdClub() == auxJugador.getClub().getIdClub()) {
			if (tipo == "a favor") {
				//El gol fue del local a favor
				this.setGolesLocal(this.getGolesLocal() + 1);
			} else if(tipo == "en contra") {
				//El gol fue del local pero en contra
				this.setGolesVisitante(this.getGolesVisitante() + 1);
			}
			
		} else if(this.getClubVisitante().getIdClub() == auxJugador.getClub().getIdClub()) {
			if (tipo == "a favor") {
				//El gol fue del visitante a favor
				this.setGolesVisitante(this.getGolesVisitante() + 1);
			} else if(tipo == "en contra") {
				//El gol fue del visitante pero en contra
				this.setGolesLocal(this.getGolesLocal() + 1);
			}
		}
		this.actualizar();
	}
	
	public void agregarJugadorPartido(Club club, Jugador jugador) {
		if (PartidoDAO.getInstancia().obtenerCantidadJugadoresPartidoEquipo(this.idPartido, club.getIdClub())<17 && PartidoDAO.getInstancia().validoParaJugar(jugador.getIdJugador(), this.getCampeonato().getIdCampeonato()) && this.getCampeonato().getCategoria() >= jugador.getCategoria()) {
			Miembro miem = new Miembro(club,this,jugador);
			miem.grabar();
		}
	}

	public void validarPartido(Club auxClub) throws ClubException{
		if (this.getClubLocal().getIdClub() == auxClub.getIdClub()) {
			this.setConvalidaLocal(true);
		} else {
			this.setConvalidaVisitante(true);
		}
		this.actualizar();
	}
	
	
	
}
