package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import daos.CampeonatoDAO;
import daos.ClubDAO;
import daos.JugadorDAO;
import daos.JugadoresTorneoDAO;
import daos.PartidoDAO;
import daos.TablaPosicionesDAO;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import vo.PartidoVO;

public class Partido {

	private Integer idPartido;
	private int nroFecha;
	private int nroZona;
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
	private String fase;

	public Partido(int nroFecha, int nroZona, Club clubLocal, Club clubVisitante,Date fechaPartido, Campeonato campeonato,String fase) {
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.campeonato = campeonato;
		this.clubLocal = clubLocal;
		this.clubVisitante = clubVisitante;
		this.golesLocal = 0;
		this.golesVisitante = 0;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = false;
		this.convalidaVisitante = false;
		this.campeonato = campeonato;
		this.fase = fase;
		}
			
		
	

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}



	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
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

	public void setConvalidaLocal(boolean estado, String accion) throws ClubException {
		
		
		if (accion.compareTo("actualizarTabla") == 0) {
			this.convalidaLocal = estado;
			if (estado == true) {
				TablaPosicionesDAO.getInstancia().modificarTabla(this.getClubLocal().getIdClub(),this);
			}
		} else if (accion.compareTo("modelo") == 0) {
			this.convalidaLocal = estado;
		}
		
	}

	public void setConvalidaVisitante(boolean estado, String accion) throws ClubException {
		
		
		if (accion.compareTo("actualizarTabla") == 0) {
			this.convalidaVisitante = estado;
			if (estado == true) {
				TablaPosicionesDAO.getInstancia().modificarTabla(this.getClubVisitante().getIdClub(),this);
			}
		} else if (accion.compareTo("modelo") == 0) {
			this.convalidaVisitante = estado;
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
		return new PartidoVO(this.idPartido,clubLocal.getIdClub(),clubVisitante.getIdClub(),this.nroFecha,this.nroZona,this.golesLocal,this.golesVisitante,this.fechaPartido,this.convalidaLocal,this.convalidaVisitante,this.getCampeonato().getIdCampeonato(),this.fase);
	}
	
	public void agregarGolJugador(Jugador auxJugador, int minuto, String tipo) {
		Gol nuevoGol = new Gol(auxJugador,this,minuto,tipo);
		auxJugador.agregarGol(nuevoGol);
		if (Integer.compare(this.getClubLocal().getIdClub(), auxJugador.getClub().getIdClub()) == 0) { 
			
			if (tipo.compareTo("a favor") == 0) {
				//El gol fue del local a favor
				this.setGolesLocal(this.getGolesLocal() + 1);
			} else if(tipo.compareTo("en contra") == 0) {
				//El gol fue del local pero en contra
				this.setGolesVisitante(this.getGolesVisitante() + 1);
			}
			
		} else if(Integer.compare(this.getClubVisitante().getIdClub(), auxJugador.getClub().getIdClub()) == 0) { 
			if (tipo.compareTo("a favor") == 0) {
				//El gol fue del visitante a favor
				this.setGolesVisitante(this.getGolesVisitante() + 1);
			} else if(tipo.compareTo("en contra") == 0) {
				//El gol fue del visitante pero en contra
				this.setGolesLocal(this.getGolesLocal() + 1);
			}
		}
		this.actualizar();
	}
	

	public void validarPartido(Club auxClub) throws ClubException{
		
		if (Integer.compare(this.getClubLocal().getIdClub(), auxClub.getIdClub()) == 0) {
			this.setConvalidaLocal(true,"actualizarTabla");
		} else {
			this.setConvalidaVisitante(true,"actualizarTabla");

		}
		
		

		
		this.actualizar();
	}




	public void agregarJugadorPartido(int idJugador, Club club) throws PartidoException, JugadorException {
		if(JugadoresTorneoDAO.getInstancia().existeJugador(idJugador, this.getCampeonato().getIdCampeonato())) {
        	Jugador jug = JugadorDAO.getInstancia().obtenerJugador(idJugador);
            if(jug.juegaPartido(this) != true && PartidoDAO.getInstancia().obtenerCantidadJugadoresPartidoEquipo(this.getIdPartido(), club.getIdClub())<17 && PartidoDAO.getInstancia().validoParaJugar(idJugador,this.getCampeonato().getIdCampeonato()) == true) {
            	Miembro miem = new Miembro(club,this,jug);
                miem.grabar();
            }else {
            	if (jug.juegaPartido(this) == true) {
            		throw new PartidoException("El jugador ya se encuentra inscripto a un partido en ese día");
            	}else if (PartidoDAO.getInstancia().obtenerCantidadJugadoresPartidoEquipo(this.getIdPartido(),club.getIdClub()) >= 17) {
            		throw new JugadorException("El jugador fue registrado una vez iniciado el torneo");
            	}else {
            		throw new JugadorException("El jugador no se encuentra habilitado para jugar el partido");
            	}
            }
        }else {
        	throw new JugadorException("El jugador no esta inscripto en este torneo");
        }
	}
}
