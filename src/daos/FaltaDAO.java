package daos;

import java.util.ArrayList;
import java.util.List;
import Entity.CampeonatoEntity;
import Entity.FaltaEntity;
import Entity.JugadorEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import modelo.Campeonato;
import modelo.Club;
import modelo.Falta;
import modelo.Jugador;
import modelo.Partido;
import sessionManager.SessionManager;

public class FaltaDAO {
	
	private static FaltaDAO instancia;
	
	private FaltaDAO() {}
	
	public static FaltaDAO getInstancia() {
		if(instancia == null)
			instancia = new FaltaDAO();
		return instancia;
	}
	
	public List<Falta> obtenerFaltasCampeonato(Integer idCampeonato) throws ClubException{
		List<Falta> resultado = new ArrayList<Falta>();
		@SuppressWarnings("unchecked")
		List<FaltaEntity> aux = SessionManager.getInstancia().getSession().createQuery("from FaltaEntity f where f.campeonato = "+idCampeonato).list();
		for (FaltaEntity e : aux) {
			resultado.add(toModelo(e)); 
		}
		return resultado;
	}
	
	
	
	

	
	public List<Falta> obtenerFaltasJugadorX(Integer idJugador) throws ClubException {
		List<Falta> resultado = new ArrayList<Falta>();
		SessionManager.getInstancia().getSession().beginTransaction();
		@SuppressWarnings("unchecked")
		List<FaltaEntity> aux = (List<FaltaEntity>) SessionManager.getInstancia().getSession().createQuery("from FaltaEntity f where f.jugador ="+ idJugador).list();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		if(aux.size() != 0)
			for(FaltaEntity e : aux)
				resultado.add(toModelo(e));
		return resultado;
	}
	
	public Long obtenerCantidadFaltasJugadorX(Integer idJugador , Integer idCampeonato) {
		SessionManager.getInstancia().getSession().beginTransaction();
		Long aux = (Long) SessionManager.getInstancia().getSession().createQuery("select count(*) from FaltaEntity f where f.jugador ="+ idJugador +" and f.campeonato="+idCampeonato).uniqueResult();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		return aux;
	}
	
	public void agregarFaltaJugador(Falta faltaNuevo) {
		FaltaEntity aux = toEntity(faltaNuevo);
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().save(aux);
		SessionManager.getInstancia().getSession().getTransaction().commit();
		
		
	}
	
	FaltaEntity toEntity(Falta falta) {
		JugadorEntity auxJugador = JugadorDAO.getInstancia().toEntity(falta.getJugador());
		PartidoEntity auxPartido = PartidoDAO.getInstancia().toEntity(falta.getPartido());
		CampeonatoEntity auxCampeonato = CampeonatoDAO.getInstancia().toEntity(falta.getCampeonato());
		FaltaEntity aux = new FaltaEntity(auxJugador,auxPartido,auxCampeonato,falta.getMinuto(),falta.getTipo());
		aux.setIdFalta(falta.getIdFalta());
		
		return aux;
	} 
	
	Falta toModelo(FaltaEntity falta) throws ClubException {
		Club auxClub = ClubDAO.getInstancia().toModeloClub(falta.getJugador().getClub());
		Jugador auxJugador = new Jugador(falta.getJugador().getTipoDocumento(),falta.getJugador().getNumeroDocumento(),falta.getJugador().getNombre(),falta.getJugador().getApellido(),auxClub,falta.getJugador().getFechaNacimiento());
		Partido auxPartido = PartidoDAO.getInstancia().toModelo(falta.getPartido());
		Campeonato auxCampeonato =	CampeonatoDAO.getInstancia().toCampeonatoModelo(falta.getCampeonato());

		Falta aux = new Falta(auxJugador, auxPartido, auxCampeonato,falta.getMinuto(),falta.getTipo());
		
		return aux;
		
	}
	
	
}
