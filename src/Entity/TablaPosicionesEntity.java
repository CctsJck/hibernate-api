package Entity;




import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;



@Entity
@Table(name = "tablaPosiciones")
public class TablaPosicionesEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "idClub")
	private ClubEntity club;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCampeonato")
	private CampeonatoEntity campeonato;
	
	public TablaPosicionesEntity() {}
	
	

	public TablaPosicionesEntity(ClubEntity club, CampeonatoEntity camp) {
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



	public void setIdClub(Integer idTabla) {
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

	public ClubEntity getClub() {
		return club;
	}

	public void setClub(ClubEntity club) {
		this.club = club;
	}

	public CampeonatoEntity getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(CampeonatoEntity campeonato) {
		this.campeonato = campeonato;
	}
	
	
	
	
	
	

}
