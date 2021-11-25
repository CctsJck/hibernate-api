package vo;

import modelo.Campeonato;
import modelo.Jugador;

public class JugadoresTorneoVO {
	
	private int idJugadorTorneo;
	private int idJugador;
	private int idCampeonato;
	private boolean estado;
	
	public JugadoresTorneoVO(int idJugadorTorneo, int idJugador, int idCampeonato, boolean estado) {
		this.idJugadorTorneo = idJugadorTorneo;
		this.idJugador = idJugador;
		this.idCampeonato = idCampeonato;
		this.estado = estado;
	}
	
	public JugadoresTorneoVO() {}
	
	public int getIdJugadorTorneo() {
		return idJugadorTorneo;
	}
	public void setIdJugadorTorneo(int idJugadorTorneo) {
		this.idJugadorTorneo = idJugadorTorneo;
	}
	public int getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	public int getIdCampeonato() {
		return idCampeonato;
	}
	public void setIdCampeonato(int idCampeonato) {
		this.idCampeonato = idCampeonato;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
