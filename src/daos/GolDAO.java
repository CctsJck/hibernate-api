package daos;

import java.util.ArrayList;
import java.util.List;
import Entity.GolEntity;
import Entity.JugadorEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import modelo.Club;
import modelo.Gol;
import modelo.Jugador;
import modelo.Partido;
import sessionManager.SessionManager;

public class GolDAO {
	
private static GolDAO instancia;
	
	private GolDAO() {}
	
	public static GolDAO getInstancia() {
		if(instancia == null)
			instancia = new GolDAO();
		return instancia;
	}
	
	public List<Gol> obtenerGolesJugador(Integer idJugador) throws ClubException{
		List<Gol> resultado = new ArrayList<Gol>();
		@SuppressWarnings("unchecked")
		List<GolEntity> aux = SessionManager.getInstancia().getSession().createQuery("from FaltaEntity f where f.campeonato = "+idJugador).list();
		for (GolEntity e : aux) {
			resultado.add(toModelo(e)); 
		}
		return resultado;
	}
	
	public void agregarGolJugador(Gol golNuevo) {
		GolEntity aux = toEntity(golNuevo);
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().save(aux);
		SessionManager.getInstancia().getSession().getTransaction().commit();		
	}
	
	public Long obtenerCantidadGolesJugadorCampeonato(Integer idJugador, Integer idCampeonato){
		Long aux = (Long) SessionManager.getInstancia().getSession().createQuery("select count(*) from GolEntity g , PartidoEntity p where g.partido = p.idPartido and g.jugador = "+idJugador+" and p.campeonato = "+idCampeonato).uniqueResult();
		return aux;
	}
	
	Gol toModelo(GolEntity gol) throws ClubException {
		Club auxClub = ClubDAO.getInstancia().toModeloClub(gol.getJugador().getClub());
		Jugador auxJugador = new Jugador(gol.getJugador().getTipoDocumento(),gol.getJugador().getNumeroDocumento(),gol.getJugador().getNombre(),gol.getJugador().getApellido(),auxClub,gol.getJugador().getFechaNacimiento());
		Partido auxPartido = PartidoDAO.getInstancia().toModelo(gol.getPartido());
		
		return new Gol(auxJugador,auxPartido,gol.getMinuto(),gol.getTipo());
	}
	
	GolEntity toEntity(Gol gol) {

		JugadorEntity auxJugador = JugadorDAO.getInstancia().toEntity(gol.getJugador());
		PartidoEntity auxPartido = new PartidoEntity(gol.getPartido().getNroFecha(),gol.getPartido().getNroZona(),ClubDAO.getInstancia().toEntity(gol.getPartido().getClubLocal()),ClubDAO.getInstancia().toEntity(gol.getPartido().getClubVisitante()),gol.getPartido().getFechaPartido(),CampeonatoDAO.getInstancia().toEntity(gol.getPartido().getCampeonato()));
		auxPartido.setIdPartido(gol.getPartido().getIdPartido());
		
		GolEntity nuevoGol = new GolEntity(auxJugador,auxPartido,gol.getMinuto(),gol.getTipo());
		nuevoGol.setIdGol(gol.getIdGol());
		
		return nuevoGol;
	}

}
