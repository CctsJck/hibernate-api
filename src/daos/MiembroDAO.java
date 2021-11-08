package daos;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Entity.ClubEntity;
import Entity.JugadorEntity;
import Entity.MiembroEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
import hibernate.HibernateUtil;
import modelo.Miembro;

import sessionManager.SessionManager;

public class MiembroDAO {
	private static MiembroDAO instancia;
	
	private MiembroDAO() {}
	
	public static MiembroDAO getInstancia() {
		if(instancia == null)
			instancia = new MiembroDAO();
		return instancia;
	}
	
	public Miembro getMiembro(Integer idJugador) throws ClubException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		MiembroEntity aux = (MiembroEntity) session.createQuery("from MiembroEntity m where m.idJugador = "+idJugador).uniqueResult();
		session.close();
		return toModelo(aux);
		
	}
	
	
	public void grabar(Miembro miembroNuevo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        MiembroEntity MiembroNuevo= toEntity(miembroNuevo);
        session.beginTransaction();
        session.save(MiembroNuevo);
        session.getTransaction().commit();
        session.close();
    }
	
	public Long obtenerPartidosJugados(int idJugador,int idCampeonato) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long aux = (Long) session.createQuery("SELECT count(*) FROM MiembroEntity m INNER JOIN m.partido p INNER JOIN p.campeonato c WHERE idJugador = "+idJugador+" AND c.idCampeonato ="+idCampeonato).uniqueResult();
        session.close();
        return aux;
    }
	
	MiembroEntity toEntity(Miembro m) {
		ClubEntity auxClub = ClubDAO.getInstancia().toEntity(m.getClub());
		PartidoEntity auxPartido = PartidoDAO.getInstancia().toEntity(m.getPartido());
		JugadorEntity auxJugador = JugadorDAO.getInstancia().toEntity(m.getJugador());
		
		MiembroEntity auxM = new MiembroEntity(auxClub,auxPartido,auxJugador);
		auxM.setIdLista(m.getIdLista());

		
		return auxM;
		
	}
	public List<Miembro> obtenerJugadoresPartido(int idPartido) throws ClubException{
		List<Miembro> miembros = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<MiembroEntity> aux  = (List<MiembroEntity>) session.createQuery("from MiembroEntity where idPartido="+idPartido + " ORDER BY jugador").list();
		for(MiembroEntity e : aux) {
			miembros.add(toModelo(e));
		}
		return miembros;	
	}
	
	
	Miembro toModelo(MiembroEntity m) throws ClubException {
		Miembro miembroNuevo = new Miembro(ClubDAO.getInstancia().toModeloClub(m.getClub()),PartidoDAO.getInstancia().toModelo(m.getPartido()),JugadorDAO.getInstancia().toModelo(m.getJugador()));
		miembroNuevo.setIdLista(m.getIdLista());
		return miembroNuevo;
		
	}
	
}
