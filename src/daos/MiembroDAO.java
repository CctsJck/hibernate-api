package daos;



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
    }
	
	MiembroEntity toEntity(Miembro m) {
		ClubEntity auxClub = ClubDAO.getInstancia().toEntity(m.getClub());
		PartidoEntity auxPartido = PartidoDAO.getInstancia().toEntity(m.getPartido());
		JugadorEntity auxJugador = JugadorDAO.getInstancia().toEntity(m.getJugador());
		
		MiembroEntity auxM = new MiembroEntity(auxClub,auxPartido,auxJugador);
		auxM.setIdLista(m.getIdLista());

		
		return auxM;
		
	}
	
	
	Miembro toModelo(MiembroEntity m) throws ClubException {
		Miembro miembroNuevo = new Miembro(ClubDAO.getInstancia().toModeloClub(m.getClub()),PartidoDAO.getInstancia().toModelo(m.getPartido()),JugadorDAO.getInstancia().toModelo(m.getJugador()));
		miembroNuevo.setIdLista(m.getIdLista());
		return miembroNuevo;
		
	}
	
}
