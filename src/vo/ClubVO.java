package vo;

import java.io.Serializable;
import java.util.List;

import modelo.Campeonato;
import modelo.Jugador;
import modelo.Partido;
import modelo.Responsable;
import modelo.TablaPosiciones;

public class ClubVO implements Serializable{

	private static final long serialVersionUID = 3444595187312481932L;
	private Integer idClub;
	private String nombre;
	private String direcci�n;

	
	public ClubVO() {}

	public ClubVO(Integer idClub, String nombre, String direcci�n) {
		this.idClub = idClub;
		this.nombre = nombre;
		this.direcci�n = direcci�n;
		
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirecci�n() {
		return direcci�n;
	}

	public void setDirecci�n(String direcci�n) {
		this.direcci�n = direcci�n;
	}

}
