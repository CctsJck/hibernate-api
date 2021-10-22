package daos;

import java.util.ArrayList;
import java.util.List;
import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import Entity.JugadorEntity;
import exceptions.ClubException;
import modelo.Campeonato;
import modelo.Club;
import modelo.Jugador;
import sessionManager.SessionManager;

public class ClubDAO {
	
	private static ClubDAO instancia;
	
	private ClubDAO() {}
	
	public static ClubDAO getInstancia() {
		if(instancia == null)
			instancia = new ClubDAO();
		return instancia;
	}
	
	
	
	public List<Club> obtenerClubes() {
		List<Club> clubes = new ArrayList<>();
		SessionManager.getInstancia().getSession().beginTransaction();
		@SuppressWarnings("unchecked")
		List<ClubEntity> aux = (List<ClubEntity>) SessionManager.getInstancia().getSession().createQuery("from ClubEntity").list();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		for(ClubEntity e : aux) {
			clubes.add(toModeloClub(e));
		}
        return clubes;
    }
	
	public Club obtenerClubPorID(Integer id) throws ClubException{
        ClubEntity aux= (ClubEntity) SessionManager.getInstancia().getSession().createQuery("from ClubEntity c where c.idClub = "+ id).uniqueResult();
        
        if(aux!=null) {
            return toModeloClub(aux);
        }
        throw new ClubException("No existe el club");
    }
	
	Club toModeloClub(ClubEntity c) {
		Club auxClub = new Club(c.getNombre(),c.getDireccion());
		auxClub.setIdClub(c.getIdClub());
		return auxClub;
	}

	public void modificarClub(Club club, int idClub, String nombre, String direccion) {
		SessionManager.getInstancia().getSession().beginTransaction();
		club.setDireccion(direccion);
		club.setNombre(nombre);
		club.setIdClub(idClub);
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().merge(toEntity(club));
		SessionManager.getInstancia().getSession().getTransaction().commit();
		
	}

	 ClubEntity toEntity(Club club) {
		 List<CampeonatoEntity> aux2= new ArrayList<CampeonatoEntity>();
		ClubEntity aux = new ClubEntity();
		aux.setDireccion(club.getDireccion());
		aux.setNombre(club.getNombre());
		aux.setIdClub(club.getIdClub());
		if (club.getParticipaciones() != null) {
			for (Campeonato campeonato : club.getParticipaciones()) {
				aux2.add((CampeonatoDAO.getInstancia().toEntityExcepcion(campeonato)));
			}
		aux.setParticipaciones(aux2);
		}
		return aux;
		
	}
	 
	ClubEntity toEntityExcepcion(Club club) {
		ClubEntity aux = new ClubEntity();
		aux.setDireccion(club.getDireccion());
		aux.setNombre(club.getNombre());
		aux.setIdClub(club.getIdClub());
		return aux;
	}
	 
	 public void grabar(Club club) {
	        SessionManager.getInstancia().getSession().beginTransaction();
	        
	        SessionManager.getInstancia().getSession().save(toEntity(club));
	        SessionManager.getInstancia().getSession().getTransaction().commit();
		}

		public void eliminarClub(Club club) {
			SessionManager.getInstancia().getSession().beginTransaction();
			SessionManager.getInstancia().getSession().flush();
			SessionManager.getInstancia().getSession().clear();
			SessionManager.getInstancia().getSession().delete(toEntity(club));
			SessionManager.getInstancia().getSession().getTransaction().commit();
			
		}
		
		public void actualizarClub(Club club) {
			SessionManager.getInstancia().getSession().beginTransaction();
			SessionManager.getInstancia().getSession().flush();
			SessionManager.getInstancia().getSession().clear();
			SessionManager.getInstancia().getSession().update(toEntity(club));
			SessionManager.getInstancia().getSession().getTransaction().commit();
		}
		
		public List<Jugador> obtenerJugadoresClub(int idClub) throws ClubException {
			List<Jugador> jugadores = new ArrayList<Jugador>();
			@SuppressWarnings("unchecked")
			List<JugadorEntity> auxJugadores = SessionManager.getInstancia().getSession().createQuery("from JugadorEntity j where j.club = "+idClub).list();
			if(auxJugadores != null) {
				for (JugadorEntity jugador : auxJugadores) {
					jugadores.add(JugadorDAO.getInstancia().toModelo(jugador));
				}
				return jugadores;
			}
			throw new ClubException("No existe el club");

			
		}
		
		public Club obtenerClubPorIdRepresentante(int idRepresentante) throws ClubException {
			ClubEntity auxClub = (ClubEntity) SessionManager.getInstancia().getSession().createQuery("select distinct c from ClubEntity c INNER JOIN c.responsables r where r.legajo="+idRepresentante).uniqueResult();
			System.out.println(auxClub);
			if(auxClub != null) {
				return toModeloClub(auxClub);
			}
			throw new ClubException("No existe el club");
			
		}
	
	
	
	
	
}
