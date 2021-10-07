package modelo;

import java.util.List;

import daos.TablaPosicionesDAO;
import vo.TablaPosicionesVO;

public class TablaPosiciones {
	
	private Integer idTabla;
	private int cantidadJugados;
	private int cantidadganados;
	private int cantidadempatados;
	private int cantidadperdidos;
	private int golesFavor;
	private int golesContra;
	private int diferenciaGoles;
	private int puntos;
	private float promedio;
	
	private Club club;
	private Campeonato campeonato;
	
	
	
	
	public TablaPosiciones(Club club, Campeonato camp) {
		
		this.campeonato = camp;
		this.club = club;
		this.cantidadJugados = 0;
		this.cantidadganados = 0;
		this.cantidadempatados = 0;
		this.cantidadperdidos = 0;
		this.golesFavor = 0;
		this.golesContra = 0;
		this.diferenciaGoles = 0;
		this.puntos = 0;
		this.promedio = 0;
		
	}
	
	
	
	public Integer getIdTabla() {
		return idTabla;
	}



	public void setIdTabla(Integer idTabla) {
		this.idTabla = idTabla;
	}



	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
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
	public void grabar() {
		TablaPosicionesDAO.getInstancia().grabar(this);
	}
	
	public void actualizar() {
		TablaPosicionesDAO.getInstancia().actualizar(this);
	}
	
	public TablaPosicionesVO toVO() {
		return new TablaPosicionesVO(this.idTabla,this.cantidadJugados,this.cantidadganados,this.cantidadempatados,this.cantidadperdidos,this.golesFavor,this.golesContra,this.diferenciaGoles,this.puntos,this.promedio);
	}
	
	

}
