package modelo;

import vo.FaltaVO;

public class Falta {

	private Integer idFalta;
	private Jugador jugador;
	private Partido partido;
	private Campeonato campeonato;
	private int minuto;
	private String tipo;
	
	public Falta(Jugador jugador, Partido partido, Campeonato campeonato, int minuto, String tipo) {
		this.idFalta = null;
		this.jugador = jugador;
		this.partido = partido;
		this.campeonato = campeonato;
		this.minuto = minuto;
		this.tipo = tipo;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Partido getPartido() {
		return partido;
	}

	public int getMinuto() {
		return minuto;
	}

	public String getTipo() {
		return tipo;
	}

	public Integer getIdFalta() {
		return idFalta;
	}

	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	public FaltaVO toVO() {
		return new FaltaVO (this.idFalta,this.minuto,this.tipo);
	}
	
	
}
