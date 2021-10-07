package daos;

import java.util.ArrayList;
import java.util.List;



import Entity.ClubEntity;
import Entity.RepresentanteEntity;
import exceptions.ClubException;
import exceptions.ResponsableException;
import modelo.Club;
import modelo.Responsable;
import sessionManager.SessionManager;

public class RepresentanteDAO {
	private static RepresentanteDAO instancia;
	
	private RepresentanteDAO() {}
	
	public static RepresentanteDAO getInstancia() {
		if(instancia == null) {
			instancia = new RepresentanteDAO();
		}
		return instancia;
	}
	public Responsable obtenerRepresentanteporID(int idRepresentante) throws ResponsableException{
		SessionManager.getInstancia().getSession().beginTransaction();
		RepresentanteEntity aux = (RepresentanteEntity) SessionManager.getInstancia().getSession().createQuery("from RepresentanteEntity e where e.legajo="+idRepresentante+"and e.eliminadoR='noEliminado'").uniqueResult();
		SessionManager.getInstancia().getSession().getTransaction().commit();
		if(aux != null) {
			return toModelo(aux);
		}
		throw new ResponsableException("No existe el responsable");
	}
	
	public List<Responsable> getResponsablesClub(int idClub){
		List<Responsable> respModelo = new ArrayList<Responsable>();
		@SuppressWarnings("unchecked")
		List<RepresentanteEntity> resp = (List<RepresentanteEntity>) SessionManager.getInstancia().getSession().createQuery("from RepresentanteEntity r where r.club = "+idClub).list();
		for (RepresentanteEntity r : resp) {
			respModelo.add(toModelo(r));
		}
		return respModelo;
	}
	
	public void grabar(Responsable responsable) {
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().save(toEntity(responsable));
		SessionManager.getInstancia().getSession().getTransaction().commit();
	}
	
	public void modificarRepresentante(Responsable representante,int idRepresentante,String nombre,int DNI,String tipoDocumento,int idClub,String eliminado) throws ClubException {
		SessionManager.getInstancia().getSession().beginTransaction();
		representante.setLegajo(idRepresentante);
		representante.setDNI(DNI);
		representante.setTipoDocumento(tipoDocumento);
		representante.setNombre(nombre);
		representante.setEliminado(eliminado);
		
		if (representante.getClub().getIdClub() != idClub) {
			Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);

			representante.setClub(auxClub);
		}
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().merge(toEntity(representante));
		SessionManager.getInstancia().getSession().getTransaction().commit();
	}
	
	public void eliminar(Responsable resp) {
		SessionManager.getInstancia().getSession().beginTransaction();
		SessionManager.getInstancia().getSession().flush();
		SessionManager.getInstancia().getSession().clear();
		SessionManager.getInstancia().getSession().delete(toEntity(resp));
		SessionManager.getInstancia().getSession().getTransaction().commit();
	}
	
	RepresentanteEntity toEntity(Responsable representante) {
		ClubEntity auxClub = new ClubEntity(representante.getClub().getNombre(),representante.getClub().getDireccion());
		auxClub.setIdClub(representante.getClub().getIdClub());
		RepresentanteEntity auxRepresentante = new RepresentanteEntity(representante.getTipoDocumento(),representante.getDNI(),representante.getNombre(),auxClub);
		auxRepresentante.setLegajo(representante.getLegajo());
		auxRepresentante.setEliminado(representante.getEliminado());
		return auxRepresentante;
	}
	
	Responsable toModelo(RepresentanteEntity entity) {
		Responsable aux = new Responsable(entity.getTipoDocumento(),entity.getDNI(),entity.getNombre(),ClubDAO.getInstancia().toModeloClub(entity.getClub()));
		aux.setLegajo(entity.getLegajo());
		aux.setEliminado("noEliminado");
		return aux;
	}

}
