package daos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Club;
import modelo.Jugador;
import modelo.Partido;
import sessionManager.SessionManager;
import vo.JugadorVO;

public class CampeonatoDAO {
	
	private static CampeonatoDAO instancia;
	
	private CampeonatoDAO() {}
	
	public static CampeonatoDAO getInstancia() {
		if(instancia == null)
			instancia = new CampeonatoDAO();
		return instancia;
	}
	

	CampeonatoEntity toEntity(Campeonato campeonato) {
		List<ClubEntity> aux2 = new ArrayList<ClubEntity>();
		CampeonatoEntity aux = new CampeonatoEntity(campeonato.getDescripcion(),campeonato.getFechaInicio(),campeonato.getFechaFin(), campeonato.getTipo(),campeonato.getCategoria());
		aux.setIdCampeonato(campeonato.getIdCampeonato());
		aux.setEliminado(campeonato.getEliminado());
		aux.setEstado(campeonato.getEstado());
		
		if (campeonato.getInscriptos() != null) {
			for (Club club : campeonato.getInscriptos()) {
				aux2.add((ClubDAO.getInstancia().toEntityExcepcion(club)));
				
			}
			aux.setInscriptos(aux2);
		}
		return aux;
	}
	
	CampeonatoEntity toEntityExcepcion(Campeonato campeonato) {
		CampeonatoEntity aux = new CampeonatoEntity(campeonato.getDescripcion(),campeonato.getFechaInicio(),campeonato.getFechaFin(), campeonato.getTipo(),campeonato.getCategoria());
		aux.setIdCampeonato(campeonato.getIdCampeonato());
		return aux;
	}
	
	
	
	Campeonato toCampeonatoModelo (CampeonatoEntity entity) {
		Campeonato campeonato = new Campeonato(entity.getDescripcion(),entity.getFechaInicio(),entity.getFechaFin(),entity.getTipo(),entity.getCategoria());
		campeonato.setIdCampeonato(entity.getIdCampeonato());
		campeonato.setEliminado("noEliminado");
		campeonato.setEstado(entity.getEstado());
		for(ClubEntity x: entity.getInscriptos()) {
			campeonato.agregarClub(ClubDAO.getInstancia().toModeloClub(x));
		}
		return campeonato;
	}
	



	public void grabar(Campeonato campeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(toEntity(campeonato));
		session.getTransaction().commit();
		session.close();
	}
	
	public Campeonato obtenerCampeonatoPorID(Integer id) throws CampeonatoException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        CampeonatoEntity aux = (CampeonatoEntity) session.createQuery("from CampeonatoEntity c where c.eliminadoC = 'noEliminado' and c.idCampeonato = "+ id).uniqueResult();
        session.getTransaction().commit();
        session.close();
        if(aux!=null) {
            return toCampeonatoModelo(aux);
        }
        throw new CampeonatoException("No existe el campeonato");
    }
	
	public void actualizar(Campeonato c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CampeonatoEntity aux = toEntity(c);
		session.flush();
		session.clear();
		session.update(aux);
		session.getTransaction().commit();
		session.close();
	}
	
	
	public List<Jugador> getJugadoresCampeonato(Integer idCampeonato) throws CampeonatoException, ClubException {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Campeonato camp = CampeonatoDAO.getInstancia().obtenerCampeonatoPorID(idCampeonato);
		session.close();
		List<Club> clubes = camp.getInscriptos();
		for(Club auxClub : clubes ) {
			List<Jugador> auxjugadores = auxClub.getJugadores();
			for(Jugador player : auxjugadores) {
				jugadores.add(player);
			}
		}
		return jugadores;
	}

	public void crearPartidos(List<Club> inscriptos, List<Club> inscriptos2,Campeonato campeonato) {
		int nroFecha = 1;
		int nroZona = 0;
		int cont = 0;
		Date fecha = campeonato.getFechaInicio();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		for( int fila = 0; fila< inscriptos.size(); fila++) {
			for (int columna = 0 ; columna< inscriptos2.size(); columna++) {
				if(inscriptos.get(fila).getIdClub() != inscriptos2.get(columna).getIdClub() ) {
					//Integer ultimaFecha = PartidoDAO.getInstancia().obtenerUltimaFechaCampeonato(campeonato.getIdCampeonato());
					/*if(ultimaFecha == null || fila == 0) {
						nroFecha = 1;
					}else {
						calendar.add(Calendar.DAY_OF_YEAR, 5);
						fecha = calendar.getTime();
						nroFecha = ultimaFecha+1;
						
					}
					*/
					if(campeonato.getTipo() == "Puntos") {
						nroZona = 0;
					} else {
						//Para grupos pares con un maximo de 4 por grupo
						if (cont > 4) {
							nroZona = nroZona + 1;
							cont = 0;
						} else {
							cont+=1;
						}
					}
					Partido partido = new Partido(nroFecha,nroZona,inscriptos.get(fila),inscriptos.get(columna),fecha, campeonato,null);
					partido.grabar();
					nroFecha+=1;
				}
			}
			nroFecha = 1;
			fecha = campeonato.getFechaInicio();
		}
		
	}

	public List<Campeonato> getCampeonatos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Campeonato> campeonatos = new ArrayList<Campeonato>();
        List<CampeonatoEntity> auxCampeonatos = session.createQuery("FROM CampeonatoEntity p").list(); 
        session.close();
        for (CampeonatoEntity camp : auxCampeonatos) {
            campeonatos.add(CampeonatoDAO.getInstancia().toCampeonatoModelo(camp));
        }
        return campeonatos;
	}
	
	public boolean existeCampeonato(int idCampeonato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CampeonatoEntity aux = (CampeonatoEntity) session.createQuery("from CampeonatoEntity c where c.idCampeonato= "+idCampeonato).uniqueResult();
		session.close();
		if (aux != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<Campeonato> getCampeonatosPorIdJugador(int idJugador){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Campeonato> campeonatos = new ArrayList<Campeonato>();
		List<CampeonatoEntity> auxCampeonatos = (List<CampeonatoEntity>) session.createQuery("SELECT DISTINCT c FROM CampeonatoEntity c INNER JOIN c.partidos p INNER JOIN p.jugadoresLocales jl INNER JOIN p.jugadoresVisitantes jv WHERE jl.jugador ="+idJugador+" OR jv.jugador ="+idJugador).list();
		session.close();
		for (CampeonatoEntity camp : auxCampeonatos) {
            campeonatos.add(CampeonatoDAO.getInstancia().toCampeonatoModelo(camp));
        }
		return campeonatos;
	}
	
	public Campeonato getCampeonatoById(int idCampeonato) throws CampeonatoException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CampeonatoEntity campEntity = (CampeonatoEntity) session.createQuery("FROM CampeonatoEntity c WHERE c.idCampeonato = "+idCampeonato).uniqueResult();
		if (campEntity != null) {
			Campeonato camp = this.toCampeonatoModelo(campEntity);
			session.close();
			return camp;
		}
		throw new CampeonatoException("No existe un campeonato con id"+idCampeonato);

	}
	

}
