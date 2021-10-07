package vo;

import java.io.Serializable;

import modelo.Jugador;
import modelo.Partido;

public class GolVO implements Serializable{

	private static final long serialVersionUID = -8255886969629226693L;
	private Integer idGol;
	private int minuto;
	private String tipo;
	
	public GolVO() {}

	public GolVO(Integer idGol, int minuto, String tipo) {
		this.idGol = idGol;
		this.minuto = minuto;
		this.tipo = tipo;
	}

	public Integer getIdGol() {
		return idGol;
	}

	public void setIdGol(Integer idGol) {
		this.idGol = idGol;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
}
