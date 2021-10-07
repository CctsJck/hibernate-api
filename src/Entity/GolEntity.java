package Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;




@Entity
@Table(name="goles")
public class GolEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idGol;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idJugador")
	private JugadorEntity jugador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idPartido")
	private PartidoEntity partido;
	
	private int minuto;
	@Column(name="sentido")
	private String tipo;
	
	public GolEntity() {}
		
	public GolEntity(JugadorEntity jugador, PartidoEntity partido, int minuto, String tipo) {
		
		this.jugador = jugador;
		this.partido = partido;
		this.minuto = minuto;
		this.tipo = tipo;
	}

	public Integer getIdGol() {
		return idGol;
	}

	public void setIdGol(Integer idGol) {
		this.idGol = idGol;
	}
	
	public JugadorEntity getJugador() {
		return jugador;
	}

	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}

	public PartidoEntity getPartido() {
		return partido;
	}

	public void setPartido(PartidoEntity partido) {
		this.partido = partido;
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
