package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.Campeonato;
import modelo.Jugador;

@Entity
@Table(name="listaJugadoresTorneo")
public class JugadoresTorneoEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idJugadorTorneo;
	
	@ManyToOne
	@JoinColumn(name="idJugador")
	private JugadorEntity jugador;
	
	@ManyToOne
	@JoinColumn(name="idCampeonato")
	private CampeonatoEntity campeonato;
	private boolean estado;
	
	
	public JugadoresTorneoEntity(JugadorEntity jugador, CampeonatoEntity campeonato, boolean estado) {
		this.jugador = jugador;
		this.campeonato = campeonato;
		this.estado = estado;
	}
	
	public JugadoresTorneoEntity() {}

	public int getIdJugadorTorneo() {
		return idJugadorTorneo;
	}

	public void setIdJugadorTorneo(int idJugadorTorneo) {
		this.idJugadorTorneo = idJugadorTorneo;
	}

	public JugadorEntity getJugador() {
		return jugador;
	}

	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}

	public CampeonatoEntity getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(CampeonatoEntity campeonato) {
		this.campeonato = campeonato;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
