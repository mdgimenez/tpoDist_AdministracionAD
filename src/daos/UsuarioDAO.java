package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UsuarioEntity;
import exceptions.UsuarioException;
import hibernate.HibernateUtil;
import modelo.Persona;
import modelo.Usuario;

public class UsuarioDAO {
	
	private static UsuarioDAO instancia;
	
	private UsuarioDAO() { }
	
	public static UsuarioDAO getInstancia() {
		if(instancia==null)
			instancia = new UsuarioDAO();
		return instancia;
	}
	
	public int obtenerUltimoId() throws UsuarioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		int id = (Integer) s.createQuery("select max(idUser) from UsuarioEntity").uniqueResult();
		if(id != 0)
			return id;
		else
			throw new UsuarioException("No se pudo recuperar el ultimo id");
	}
	
	public void saveUsuario(Usuario u) throws UsuarioException {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			UsuarioEntity ue = new UsuarioEntity(PersonaDAO.getInstancia().toEntity(u.getPersona()), 
					u.getContrasena());
			s.beginTransaction();
			s.save(ue);
			s.getTransaction().commit();
		} catch (Exception e) {
			throw new UsuarioException("No se pudo guardar el Usuario");
		}
	}
	
	public Usuario getUsuarioByID(int codigo) throws UsuarioException {
		try {
			Usuario resultado = null;
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.idUser = ?").setInteger(0, codigo).uniqueResult();
			if(usuario != null)
				resultado = toNegocio(usuario);
			return resultado;
		}
		catch (Exception e) {
			throw new UsuarioException("No se pudo obtener el Usuario");
		}
	}
	
	public Usuario getUsuarioByDocumento(String documento) throws UsuarioException {
		try {
			Usuario resultado = null;
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.persona = ?").setString(0, documento).uniqueResult();
			if(usuario != null)
				resultado = toNegocio(usuario);
			return resultado;
		}
		catch (Exception e) {
			return null;
			//throw new UsuarioException("No se pudo obtener el Usuario");
		}
	}
	
	public boolean getUsuarioAuth(String documento, String contrasena) throws UsuarioException {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			UsuarioEntity usuario = (UsuarioEntity) s.createQuery("from UsuarioEntity u where u.persona = ? and u.contrasena = ?")
					.setString(0, documento)
					.setString(1, contrasena)
					.uniqueResult();
			if(usuario != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new UsuarioException("No se pudo autenticar el Usuario");
		}
	}

	private Usuario toNegocio(UsuarioEntity u) throws UsuarioException {
		try {
			if(u != null) {
				Persona persona = PersonaDAO.getInstancia().findByID(u.getPersona().getDocumento());
				Usuario usuario = null;
				if(persona != null)
					usuario = new Usuario(u.getIdUser(), persona, u.getContrasena());
				return usuario;
			}
		} catch (Exception e) {
			throw new UsuarioException("No se pudo realizar la recuperación");
		}
		return null;
	}

}
