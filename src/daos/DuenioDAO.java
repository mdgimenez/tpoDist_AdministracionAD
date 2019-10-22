package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.DuenioEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;

public class DuenioDAO {

	private static DuenioDAO instancia;
	
	private DuenioDAO() { }
	
	public static DuenioDAO getInstancia() {
		if(instancia==null)
			instancia = new DuenioDAO();
		return instancia;
	}

	public List<Persona> getDueniosByUnidad(int id) throws PersonaException {
		List<Persona> resultado = new ArrayList<Persona>();

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<DuenioEntity> duenios = (List<DuenioEntity>) s.createQuery("from DuenioEntity de where de.unidad.id = ?")
					.setInteger(0, id)
					.list();
		s.getTransaction().commit();
		if(duenios != null) {
			for(DuenioEntity d : duenios)
				resultado.add(PersonaDAO.getInstancia().toNegocio(d.getPersona()));
			return resultado;		
		}
		else
			throw new PersonaException("No se pudo recuperar los duenios");
		
	}
	
	public List<Edificio> getEdificiosAsociadosByDocumento(String documento) throws PersonaException {
		List<Edificio> resultado = new ArrayList<Edificio>();
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			List<DuenioEntity> duenios = (List<DuenioEntity>) s.createQuery("from DuenioEntity d where d.persona = ?")
						.setString(0, documento)
						.list();
			s.getTransaction().commit();
			if(duenios != null) {
				for(DuenioEntity d : duenios)
					resultado.add(EdificioDAO.getInstancia().findByID(d.getUnidad().getEdificio().getCodigo()));
				return resultado;
			}
			else
				return resultado;
		}
		catch (Exception e) {
			throw new PersonaException("No se pudo recuperar los edificios con dicho dueño");
		}
	}
	
	public List<Unidad> getUnidadesAsociados(String documento) throws PersonaException {
		List<Unidad> resultado = new ArrayList<Unidad>();
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			List<DuenioEntity> duenios = (List<DuenioEntity>) s.createQuery("from DuenioEntity d where d.persona = ?")
						.setString(0, documento)
						.list();
			s.getTransaction().commit();
			if(duenios != null) {
				for(DuenioEntity d : duenios)
					resultado.add(UnidadDAO.getInstancia().findById(d.getUnidad().getIdentificador()));
				return resultado;
			}
			else
				return resultado;
		}
		catch (Exception e) {
			throw new PersonaException("No se pudo recuperar los edificios con dicho dueño");
		}
	}
	
}
