package daos;

import org.hibernate.Session;

import Entity.UsuarioEntity;
import exceptions.UsuarioException;
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
	
	public Integer obtenerIdUsuario() throws UsuarioException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UsuarioEntity aux = (UsuarioEntity)  session.createQuery("from UsuarioEntity order by idUsuario desc").setMaxResults(1).uniqueResult();
		session.close();
		if (aux != null)
			return aux.getIdUsuario();
		throw new UsuarioException("No existen usuarios");
		}
	
	public Usuario getUsuarioByIdAndPassword(int idUsuario, String password) throws UsuarioException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UsuarioEntity userEntity = (UsuarioEntity) session.createQuery("from UsuarioEntity u where u.idUsuario="+idUsuario+" AND u.contraseña="+password).uniqueResult();
        session.close();
        if (userEntity != null) {
            return userEntity.toModelo();
        }

        throw new UsuarioException("No existe el usuario");
    }
	public boolean existeUsuario(int idUsuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UsuarioEntity aux = (UsuarioEntity) session.createQuery("from UsuarioEntity u where u.idUsuario= "+idUsuario);
		session.close();
		if (aux != null) {
			return true;
		} else {
			return false;
		}
	}
		

}
