package vo;

import java.io.Serializable;

import modelo.Campeonato;
import modelo.Club;

public class TablaPosicionesVO implements Serializable{

	private static final long serialVersionUID = 2055235611122534426L;
	private Integer idTabla;
	private int idClub;
	private int cantidadJugados;
	private int cantidadganados;
	private int cantidadempatados;
	private int cantidadperdidos;
	private int golesFavor;
	private int golesContra;
	private int diferenciaGoles;
	private int puntos;
	private float promedio;

	
	public TablaPosicionesVO() {}

	public TablaPosicionesVO(Integer idTabla,int idClub, int cantidadJugados, int cantidadganados, int cantidadempatados,
			int cantidadperdidos, int golesFavor, int golesContra, int diferenciaGoles, int puntos, float promedio) {
		this.idTabla = idTabla;
		this.idClub = idClub;
		this.cantidadJugados = cantidadJugados;
		this.cantidadganados = cantidadganados;
		this.cantidadempatados = cantidadempatados;
		this.cantidadperdidos = cantidadperdidos;
		this.golesFavor = golesFavor; 
		this.golesContra = golesContra;
		this.diferenciaGoles = diferenciaGoles;
		this.puntos = puntos;
		this.promedio = promedio;

	}

	public int getIdClub() {
		return idClub;
	}

	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}

	public Integer getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(Integer idTabla) {
		this.idTabla = idTabla;
	}

	public int getCantidadJugados() {
		return cantidadJugados;
	}

	public void setCantidadJugados(int cantidadJugados) {
		this.cantidadJugados = cantidadJugados;
	}

	public int getCantidadganados() {
		return cantidadganados;
	}

	public void setCantidadganados(int cantidadganados) {
		this.cantidadganados = cantidadganados;
	}

	public int getCantidadempatados() {
		return cantidadempatados;
	}

	public void setCantidadempatados(int cantidadempatados) {
		this.cantidadempatados = cantidadempatados;
	}

	public int getCantidadperdidos() {
		return cantidadperdidos;
	}

	public void setCantidadperdidos(int cantidadperdidos) {
		this.cantidadperdidos = cantidadperdidos;
	}

	public int getGolesFavor() {
		return golesFavor;
	}

	public void setGolesFavor(int golesFavor) {
		this.golesFavor = golesFavor;
	}

	public int getGolesContra() {
		return golesContra;
	}

	public void setGolesContra(int golesContra) {
		this.golesContra = golesContra;
	}

	public int getDiferenciaGoles() {
		return diferenciaGoles;
	}

	public void setDiferenciaGoles(int diferenciaGoles) {
		this.diferenciaGoles = diferenciaGoles;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public float getPromedio() {
		return promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}


	
	
	
	
}
