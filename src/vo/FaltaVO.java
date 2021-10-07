package vo;

import java.io.Serializable;

import modelo.Campeonato;
import modelo.Jugador;
import modelo.Partido;

public class FaltaVO implements Serializable{

	private static final long serialVersionUID = 528901363089226405L;
	private Integer idFalta;
	private int minuto;
	private String tipo;
	
	public FaltaVO() {}

	public FaltaVO(int idFalta, int minuto, String tipo) {
		this.idFalta = idFalta;
		this.minuto = minuto;
		this.tipo = tipo;
	}

	public Integer getIdFalta() {
		return idFalta;
	}

	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
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
