package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import Entity.CampeonatoEntity;
import Entity.ClubEntity;
import Entity.FaltaEntity;
import Entity.GolEntity;
import Entity.JugadorEntity;
import Entity.MiembroEntity;
import Entity.PartidoEntity;
import Entity.RepresentanteEntity;
import Entity.TablaPosicionesEntity;
import Entity.UsuarioEntity;


public class HibernateUtil
{
	/* Crea la sesion con la BD */
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 
        	 config.addAnnotatedClass(CampeonatoEntity.class);
        	 config.addAnnotatedClass(TablaPosicionesEntity.class);
        	 config.addAnnotatedClass(GolEntity.class);
        	 config.addAnnotatedClass(PartidoEntity.class);
        	 config.addAnnotatedClass(ClubEntity.class);
        	 config.addAnnotatedClass(JugadorEntity.class);
        	 config.addAnnotatedClass(FaltaEntity.class);
        	 config.addAnnotatedClass(MiembroEntity.class);
        	 config.addAnnotatedClass(RepresentanteEntity.class);
        	 config.addAnnotatedClass(UsuarioEntity.class);

        	 

      
             sessionFactory = config.buildSessionFactory(); 
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}