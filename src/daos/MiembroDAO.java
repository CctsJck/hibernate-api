package daos;



import Entity.ClubEntity;
import Entity.JugadorEntity;
import Entity.MiembroEntity;
import Entity.PartidoEntity;
import exceptions.ClubException;
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
		MiembroEntity aux = (MiembroEntity) SessionManager.getInstancia().getSession().createQuery("from MiembroEntity m where m.idJugador = "+idJugador).uniqueResult();
		return toModelo(aux);
		
	}
	
	
	public void grabar(Miembro miembroNuevo) {
        MiembroEntity MiembroNuevo= toEntity(miembroNuevo);
        SessionManager.getInstancia().getSession().beginTransaction();
        SessionManager.getInstancia().getSession().save(MiembroNuevo);
        SessionManager.getInstancia().getSession().getTransaction().commit();
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
