package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.InquilinoEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;

public class InquilinoDAO {

	private static InquilinoDAO instancia;
	
	private InquilinoDAO() { }
	
	public static InquilinoDAO getInstancia() {
		if(instancia==null)
			instancia = new InquilinoDAO();
		return instancia;
	}

	public List<Persona> getInquilinosByUnidad(int id) throws PersonaException {
		List<Persona> resultado = new ArrayList<Persona>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<InquilinoEntity> inquilinos = (List<InquilinoEntity>) s.createQuery("from InquilinoEntity ie where ie.unidad.id = ?")
					.setInteger(0, id)
					.list();
		s.getTransaction().commit();
		if(inquilinos != null) {
			for(InquilinoEntity ie : inquilinos)
				resultado.add(PersonaDAO.getInstancia().toNegocio(ie.getPersona()));
			return resultado;		
		}
		else
			throw new PersonaException("No se pudo recuperar los inquilinos");
		
	}
	
	public List<Edificio> getEdificiosAsociadosByDocumento(String documento) throws PersonaException {
		List<Edificio> resultado = new ArrayList<Edificio>();
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			List<InquilinoEntity> inquilinos = (List<InquilinoEntity>) s.createQuery("from InquilinoEntity d where d.persona = ?")
						.setString(0, documento)
						.list();
			s.getTransaction().commit();
			if(inquilinos != null) {
				for(InquilinoEntity ie : inquilinos)
					resultado.add(EdificioDAO.getInstancia().findByID(ie.getUnidad().getEdificio().getCodigo()));
				return resultado;
		}
		else
			return resultado;
		}
		catch (Exception e) {
			throw new PersonaException("No se pudo recuperar las edificios con dicho inquilino");
		}
	}
	
	public List<Unidad> getUnidadesAsociados(String documento) throws PersonaException {
		List<Unidad> resultado = new ArrayList<Unidad>();
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			List<InquilinoEntity> inquilinos = (List<InquilinoEntity>) s.createQuery("from InquilinoEntity d where d.persona = ?")
						.setString(0, documento)
						.list();
			s.getTransaction().commit();
			if(inquilinos != null) {
				for(InquilinoEntity ie : inquilinos)
					resultado.add(UnidadDAO.getInstancia().findById(ie.getUnidad().getIdentificador()));
				return resultado;
		}
		else
			return resultado;
		}
		catch (Exception e) {
			throw new PersonaException("No se pudo recuperar las edificios con dicho inquilino");
		}
	}
}
