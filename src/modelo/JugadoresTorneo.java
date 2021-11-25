package modelo;

import java.util.ArrayList;
import java.util.Date;

import vo.JugadoresTorneoVO;
import vo.UsuarioVO;

public class JugadoresTorneo {
	
	private int idJugadorTorneo;
	private Jugador jugador;
	private Campeonato campeonato;
	private boolean estado;
	

	
	public JugadoresTorneo(Jugador jugador, Campeonato campeonato, boolean estado) {
		this.jugador = jugador;
		this.campeonato = campeonato;
		this.estado = estado;
	}
	public int getIdJugadorTorneo() {
		return idJugadorTorneo;
	}
	public void setIdJugadorTorneo(int idJugadorTorneo) {
		this.idJugadorTorneo = idJugadorTorneo;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Campeonato getCampeonato() {
		return campeonato;
	}
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public JugadoresTorneoVO toVO() {
		 return new JugadoresTorneoVO(this.getIdJugadorTorneo(), this.getJugador().getIdJugador(), this.getCampeonato().getIdCampeonato(),this.isEstado());
	}
	
}
