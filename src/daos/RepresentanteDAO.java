package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Entity.ClubEntity;
import Entity.RepresentanteEntity;
import exceptions.ClubException;
import exceptions.ResponsableException;
import hibernate.HibernateUtil;
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		RepresentanteEntity aux = (RepresentanteEntity) session.createQuery("from RepresentanteEntity e where e.legajo="+idRepresentante).uniqueResult();
		//session.close();

		System.out.println(aux.getNombre());
		if(aux != null) {
			Responsable resp = toModelo(aux);
			session.close();
			return resp;
		}
		throw new ResponsableException("No existe el responsable");
	}
	
	public List<Responsable> getResponsablesClub(int idClub){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Responsable> respModelo = new ArrayList<Responsable>();
		@SuppressWarnings("unchecked")
		List<RepresentanteEntity> resp = (List<RepresentanteEntity>) session.createQuery("from RepresentanteEntity r where r.club = "+idClub).list();
		session.close();
		for (RepresentanteEntity r : resp) {
			respModelo.add(toModelo(r));
		}
		return respModelo;
	}
	
	public void grabar(Responsable responsable) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(toEntity(responsable));
		session.getTransaction().commit();
		session.close();
	}
	
	public void modificarRepresentante(Responsable representante,int idRepresentante,String nombre,int DNI,String tipoDocumento,int idClub,String eliminado) throws ClubException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		representante.setLegajo(idRepresentante);
		representante.setDNI(DNI);
		representante.setTipoDocumento(tipoDocumento);
		representante.setNombre(nombre);
		representante.setEliminado(eliminado);
		
		if (representante.getClub().getIdClub() != idClub) {
			Club auxClub = ClubDAO.getInstancia().obtenerClubPorID(idClub);

			representante.setClub(auxClub);
		}
		session.flush();
		session.clear();
		session.merge(toEntity(representante));
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Responsable> obtenerRepresentantes() throws ResponsableException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Responsable> respModelo = new ArrayList<Responsable>();
		List<RepresentanteEntity> resp = (List<RepresentanteEntity>) session.createQuery("from RepresentanteEntity r ORDER BY r.legajo").list();
		if (resp != null) {
			for (RepresentanteEntity r : resp) {
				respModelo.add(toModelo(r));
			}
			session.close();
			return respModelo;
		}
		throw new ResponsableException("No se encontraron los representantes");
		
		
		
	}
	
	public void eliminar(Responsable resp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.flush();
		session.clear();
		session.delete(toEntity(resp));
		session.getTransaction().commit();
		session.close();
	}
	
	RepresentanteEntity toEntity(Responsable representante) {
		ClubEntity auxClub = new ClubEntity(representante.getClub().getNombre(),representante.getClub().getDireccion());
		auxClub.setIdClub(representante.getClub().getIdClub());
		RepresentanteEntity auxRepresentante = new RepresentanteEntity(representante.getTipoDocumento(),representante.getDNI(),representante.getNombre(),auxClub,representante.getIdUsuario());
		auxRepresentante.setLegajo(representante.getLegajo());
		auxRepresentante.setEliminado(representante.getEliminado());
		return auxRepresentante;
	}
	
	Responsable toModelo(RepresentanteEntity entity) {
		Responsable aux = new Responsable(entity.getTipoDocumento(),entity.getDNI(),entity.getNombre(),ClubDAO.getInstancia().toModeloClub(entity.getClub()),entity.getIdUsuario());
		aux.setLegajo(entity.getLegajo());
		aux.setEliminado(entity.getEliminado());
		return aux;
	}
	
	public Responsable getRepresentanteByIdUsuario(int idUsuario) throws ResponsableException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RepresentanteEntity repreEntity = (RepresentanteEntity) session.createQuery("from RepresentanteEntity r where r.idUsuario="+idUsuario).uniqueResult();
        if (repreEntity != null) {
            Responsable repreModelo = toModelo(repreEntity);
            session.close();
            return repreModelo;
        }

        throw new ResponsableException("No existe el representante");
    }
	
	public boolean existeRepresentanteDNI(int documento) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 RepresentanteEntity aux = (RepresentanteEntity) session.createQuery("from RepresentanteEntity r where r.DNI= "+documento).uniqueResult();
		 session.close();
		 if (aux != null) {
			 return true;
		 }else {
			 return false;
		 }
	}
	
	

}
