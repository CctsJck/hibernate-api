package daos;




import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Entity.TablaPosicionesEntity;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.TablaPosicionesException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Club;
import modelo.Partido;
import modelo.TablaPosiciones;
import sessionManager.SessionManager;

public class TablaPosicionesDAO {
	private static TablaPosicionesDAO instancia;
	
	private TablaPosicionesDAO() {}
	
	public static TablaPosicionesDAO getInstancia() {
		if(instancia == null)
			instancia = new TablaPosicionesDAO();
		return instancia;
	}
	
	public TablaPosiciones obtenerTablaCampeonatoClub(Integer idCampeonato,int idClub){
		Session session = HibernateUtil.getSessionFactory().openSession();
		TablaPosicionesEntity aux = (TablaPosicionesEntity) session.createQuery("from TablaPosicionesEntity t where t.campeonato ="+idCampeonato+" and t.club = "+idClub).uniqueResult();
		session.close();
		return toModelo(aux);
	}
	
	public List<TablaPosiciones> obtenerTablaCampeonato(Integer idCampeonato) throws TablaPosicionesException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<TablaPosiciones> tablas = new ArrayList<TablaPosiciones>();
		List<TablaPosicionesEntity> aux = (List<TablaPosicionesEntity>) session.createQuery("from TablaPosicionesEntity t where t.campeonato ="+idCampeonato+" ORDER BY t.promedio").list();
		
		
		if (aux != null) {
			for (TablaPosicionesEntity tabla : aux) {
				tablas.add(toModelo(tabla));
			}
			session.close();
			return tablas; 
		}
		throw new TablaPosicionesException("No existen las tablas");
		
		
		
	}
	
	TablaPosiciones toModelo(TablaPosicionesEntity t) {
		 TablaPosiciones nuevaTabla = new TablaPosiciones(ClubDAO.getInstancia().toModeloClub(t.getClub()),CampeonatoDAO.getInstancia().toCampeonatoModelo(t.getCampeonato()));
		 nuevaTabla.setIdTabla(t.getIdTabla());
		 nuevaTabla.setCantidadJugados(t.getCantidadJugados());
		 nuevaTabla.setCantidadempatados(t.getCantidadempatados());
		 nuevaTabla.setCantidadganados(t.getCantidadganados());
		 nuevaTabla.setCantidadperdidos(t.getCantidadperdidos());
		 nuevaTabla.setGolesFavor(t.getGolesFavor());
		 nuevaTabla.setGolesContra(t.getGolesContra());
		 nuevaTabla.setDiferenciaGoles(t.getDiferenciaGoles());
		 nuevaTabla.setPuntos(t.getPuntos());
		 nuevaTabla.setPromedio(t.getPromedio());
		 return nuevaTabla;
	}
	
	TablaPosicionesEntity toEntity(TablaPosiciones t) {
		TablaPosicionesEntity nuevaTabla = new TablaPosicionesEntity(ClubDAO.getInstancia().toEntity(t.getClub()),CampeonatoDAO.getInstancia().toEntity(t.getCampeonato()));
		
		nuevaTabla.setIdTabla(t.getIdTabla());
		nuevaTabla.setCantidadJugados(t.getCantidadJugados());
		nuevaTabla.setCantidadempatados(t.getCantidadempatados());
		nuevaTabla.setCantidadganados(t.getCantidadganados());
		nuevaTabla.setCantidadperdidos(t.getCantidadperdidos());
		nuevaTabla.setGolesFavor(t.getGolesFavor());
		nuevaTabla.setGolesContra(t.getGolesContra());
		nuevaTabla.setDiferenciaGoles(t.getDiferenciaGoles());
		nuevaTabla.setPuntos(t.getPuntos());
		nuevaTabla.setPromedio(t.getPromedio());
		return nuevaTabla;
	}
	
	public void crearTablaPosiciones(int idClub, int idCampeonato) throws ClubException, CampeonatoException {
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		Campeonato auxCamp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		if (auxClub != null && auxCamp != null) {
			TablaPosiciones nuevaTabla = new TablaPosiciones(auxClub,auxCamp);
			nuevaTabla.grabar();
		}
	}
	
	public void modificarTabla(int idClub,Partido partido) throws ClubException {
		Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);
		//Partido auxPartido = PartidoDAO.getInstancia().obtenerPartido(partido.getIdPartido());
		if (auxClub != null && partido != null) {
			TablaPosiciones tabla = TablaPosicionesDAO.getInstancia().obtenerTablaCampeonatoClub(partido.getCampeonato().getIdCampeonato(),auxClub.getIdClub());
			if (tabla != null) {
				tabla.setCantidadJugados(tabla.getCantidadJugados() + 1);
				if (idClub == partido.getClubLocal().getIdClub()) {
					//El id que me pasan es el del club local
					if (partido.getGolesLocal() > partido.getGolesVisitante()) {
						//El partido lo gano el equipo local
						tabla.setCantidadganados(tabla.getCantidadganados() + 1);
						
						//Actualizo los puntos
						tabla.setPuntos(tabla.getPuntos() + 3);
					} else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
						//El partido lo perdio el local
						tabla.setCantidadperdidos(tabla.getCantidadperdidos() + 1);
					}
					//Goles a favor del club local
					tabla.setGolesFavor(tabla.getGolesFavor() + partido.getGolesLocal());
					
					//Goles en contra del club local
					tabla.setGolesContra(tabla.getGolesContra() + partido.getGolesVisitante());
					
					//Actualizo la diferencia
					tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());

						
				} else if (partido.getGolesLocal() == partido.getGolesVisitante()) {
					//Empataron el partido
					tabla.setCantidadempatados(tabla.getCantidadempatados() + 1);
					tabla.setPuntos(tabla.getPuntos() + 1);
				} else if (idClub == partido.getClubVisitante().getIdClub())  {
					//El id que me pasan es el del club visitante
					if (partido.getGolesVisitante() > partido.getGolesLocal()) {
						//Gano el visitante
						tabla.setCantidadganados(tabla.getCantidadganados() + 1);
						tabla.setPuntos(tabla.getPuntos() + 3);
					} else if (partido.getGolesVisitante() < partido.getGolesLocal()) {
						//Perdio el visitante
						tabla.setCantidadperdidos(tabla.getCantidadperdidos() + 1);
					}
					
					//Goles a favor del club visitante
					tabla.setGolesFavor(tabla.getGolesFavor() + partido.getGolesVisitante());
					
					//Goles en contra del club visitante
					tabla.setGolesContra(tabla.getGolesContra() + partido.getGolesLocal());
					
					//Actualizo la diferencia
					tabla.setDiferenciaGoles(tabla.getGolesFavor() - tabla.getGolesContra());
				}
				
				//Actualizo el promedio
				tabla.setPromedio(tabla.getPuntos() / tabla.getCantidadJugados()); 
				
				//Actualizamos en la BD
				tabla.actualizar();
				
			}
		}
	}
	
	public void grabar(TablaPosiciones tabla) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(toEntity(tabla));
		session.getTransaction().commit();
	}
	
	public void actualizar(TablaPosiciones tabla) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.update(toEntity(tabla));
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<TablaPosiciones> getTablaPosiciones(int idClub) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        List<TablaPosiciones> tablas = new ArrayList<TablaPosiciones>();
        List<TablaPosicionesEntity> auxTablas = session.createQuery("FROM TablaPosicionesEntity t WHERE t.club = "+idClub).list();
        session.close();
        for (TablaPosicionesEntity tabla : auxTablas) {
            tablas.add(toModelo(tabla));
        }

        return tablas;
    }

}
