package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import Entity.FaltaEntity;
import Entity.JugadorEntity;
import Entity.MiembroEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import exceptions.PartidoException;
import hibernate.HibernateUtil;
import modelo.Club;
import modelo.Falta;
import modelo.Jugador;
import modelo.Miembro;
import modelo.Partido;
import sessionManager.SessionManager;

public class PartidoDAO {
	
	private static PartidoDAO instancia;
	
	private PartidoDAO() {}
	
	public static PartidoDAO getInstancia() {
		if(instancia == null)
			instancia = new PartidoDAO();
		return instancia;
	}
	
	public Partido obtenerPartido(Integer idPartido) throws PartidoException, ClubException{
		SessionManager.getInstancia().getSession().beginTransaction();
		PartidoEntity aux = (PartidoEntity) SessionManager.getInstancia().getSession().createQuery("from PartidoEntity p where p.idPartido = "+idPartido).uniqueResult();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		if (aux != null) {
			return toModelo(aux);
		}
		throw new PartidoException("El partido no existe");
	}
	
	Partido toModelo(PartidoEntity e) throws ClubException {
		Partido partido = new Partido(e.getNroFecha(),e.getNroZona(),ClubDAO.getInstancia().toModeloClub(e.getClubLocal()),ClubDAO.getInstancia().toModeloClub(e.getClubVisitante()) ,e.getFechaPartido(),CampeonatoDAO.getInstancia().toCampeonatoModelo(e.getCampeonato()));
		partido.setIdPartido(e.getIdPartido());
		partido.setGolesLocal(e.getGolesLocal());
		partido.setGolesVisitante(e.getGolesVisitante());
		
		partido.setConvalidaLocal(e.isConvalidaLocal());
		partido.setConvalidaVisitante(e.isConvalidaVisitante());
		
		return partido;
		
	}

	public void grabar(Partido partido) {
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().save(toEntity(partido));
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		
	}
	
	public void actualizar(Partido partido) {
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().update(toEntity(partido));
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().getTransaction().commit();
	}

	PartidoEntity toEntity(Partido partido) {
		PartidoEntity aux = new PartidoEntity(partido.getNroFecha(),partido.getNroZona(),ClubDAO.getInstancia().toEntity(partido.getClubLocal()),ClubDAO.getInstancia().toEntity(partido.getClubVisitante()),partido.getFechaPartido(),CampeonatoDAO.getInstancia().toEntity(partido.getCampeonato()));
		aux.setIdPartido(partido.getIdPartido());
		aux.setGolesLocal(partido.getGolesLocal());
		aux.setGolesVisitante(partido.getGolesVisitante());
		if (partido.isConvalidaLocal()) {
			aux.setConvalidaLocal();
		} 
		if (partido.isConvalidaVisitante()) {
			
			aux.setConvalidaVisitante();
		}
		return aux;
	}
	public List<Miembro> getJugadoresPartido(Integer idPartido, Integer idClub) throws ClubException {
		List<Miembro> miembros  = new ArrayList<Miembro>();
		SessionManager.getInstancia().getSession().beginTransaction();
		List<MiembroEntity> aux = (List<MiembroEntity>) SessionManager.getInstancia().getSession().createQuery("from MiembroEntity m where m.club ="+idClub+" and m.partido="+idPartido).list();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		for(MiembroEntity m : aux)
			miembros.add(MiembroDAO.getInstancia().toModelo(m));
		return miembros;
		
	}
	public Long obtenerCantidadJugadoresPartidoEquipo(Integer idPartido, Integer idClub) {
		SessionManager.getInstancia().getSession().beginTransaction();
		Long cantidad =  (Long) SessionManager.getInstancia().getSession().createQuery("select count(*) from MiembroEntity m where m.club ="+idClub+" and m.partido="+idPartido).uniqueResult();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		return cantidad;
		
	}
	
		public boolean validoParaJugar(int idJugador, int idCampeonato) {
			//Acumulacion de amarillas = 2 AMARILLAS
			String tarjeta = "amarilla";
			Long cantidadAmarillas = (Long) SessionManager.getInstancia().getSession().createQuery("SELECT count(*) FROM FaltaEntity f, PartidoEntity p WHERE p.idPartido = f.partido AND f.campeonato = "+idCampeonato+" AND f.jugador = "+idJugador+" AND f.tipo = 'amarilla'").uniqueResult();
			Integer fechaUltimaAmarilla = (Integer) SessionManager.getInstancia().getSession().createQuery("SELECT p.nroFecha FROM FaltaEntity f, PartidoEntity p WHERE p.idPartido = f.partido AND f.campeonato = "+idCampeonato+" AND f.jugador = "+idJugador+" AND f.tipo = 'amarilla' ORDER BY nroFecha DESC").setMaxResults(1).uniqueResult();
			
			Integer fechaUltimaRoja = (Integer) SessionManager.getInstancia().getSession().createQuery("SELECT p.nroFecha FROM FaltaEntity f, PartidoEntity p WHERE p.idPartido = f.partido AND f.campeonato = "+idCampeonato+" AND f.jugador = "+idJugador+" AND f.tipo = 'roja' ORDER BY nroFecha DESC").setMaxResults(1).uniqueResult();
			
			if (cantidadAmarillas == null || cantidadAmarillas == 0) {
				return true;
			} else if (fechaUltimaRoja == null || fechaUltimaRoja == 0) {
				return true;
			
			}
			else if (cantidadAmarillas % 2 != 0 && fechaUltimaAmarilla < this.obtenerUltimaFechaCampeonato(idCampeonato) && fechaUltimaRoja < this.obtenerUltimaFechaCampeonato(idCampeonato)) {
				return true;
			}
			return false;
			
			
		}
		
		public Integer obtenerUltimaFechaCampeonato(int idCampeonato) {
			Integer ultimaFecha = (Integer) SessionManager.getInstancia().getSession().createQuery("SELECT max(nroFecha) as nroFecha FROM PartidoEntity p WHERE p.campeonato ="+idCampeonato).uniqueResult();
			return ultimaFecha;
		}
		@SuppressWarnings("unchecked")
        public List<Partido> obtenerPartidoLocal(int idClub) throws ClubException{
            List<Partido> partidos = new ArrayList<Partido>();
            List<PartidoEntity>  auxPartidos = (List<PartidoEntity>)SessionManager.getInstancia().getSession().createQuery("FROM PartidoEntity p where p.idClubLocal ="+ idClub);
            for(PartidoEntity p : auxPartidos) {
                partidos.add(PartidoDAO.getInstancia().toModelo(p));
            }
            return partidos;
        }
		@SuppressWarnings("unchecked")
        public List<Partido> obtenerPartidoVisitante(int idClub) throws ClubException{
            List<Partido> partidos = new ArrayList<Partido>();
            List<PartidoEntity>  auxPartidos = (List<PartidoEntity>)SessionManager.getInstancia().getSession().createQuery("FROM PartidoEntity p where p.idClubVisitante ="+ idClub);
            for(PartidoEntity p : auxPartidos) {
                partidos.add(PartidoDAO.getInstancia().toModelo(p));
            }
            return partidos;
        }
        @SuppressWarnings("unchecked")
        public List<Partido> obtenerPartidoDeCampeonato(int idCampeonato) throws ClubException{
            List<Partido> partidosCampeonato = new ArrayList<Partido>();
            List<PartidoEntity> auxPartidos = SessionManager.getInstancia().getSession().createQuery("FROM PartidoEntity p WHERE p.campeonato = "+idCampeonato).list(); 
            for (PartidoEntity partido : auxPartidos) {
                partidosCampeonato.add(PartidoDAO.getInstancia().toModelo(partido));
            }
            return partidosCampeonato;
        }

}
