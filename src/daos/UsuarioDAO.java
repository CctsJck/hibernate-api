package daos;

import org.hibernate.Session;

import Entity.UsuarioEntity;
import hibernate.HibernateUtil;
import modelo.Usuario;

public class UsuarioDAO {
	
	private static UsuarioDAO instancia;
	
	private UsuarioDAO() {}
	
	public static UsuarioDAO getInstancia() {
		if(instancia == null)
			instancia = new UsuarioDAO();
		return instancia;
	}
	
	public void grabar(Usuario usuarioNuevo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UsuarioEntity aux = usuarioNuevo.toEntity();
		session.save(aux);
		session.close();
	}
	
	public Integer obtenerIdUsuario() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UsuarioEntity aux = (UsuarioEntity)  session.createQuery("from UsuarioEntity order by idUsuario desc").setMaxResults(1).uniqueResult();
		session.close();
		return aux.getIdUsuario();
	}

}
