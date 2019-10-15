package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ReclamoEntity;
import exceptions.ImagenException;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Imagen;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;

public class ReclamoDAO {

	private static ReclamoDAO instancia;
	
	private ReclamoDAO() { }
	
	public static ReclamoDAO getInstancia() {
		if(instancia==null)
			instancia = new ReclamoDAO();
		return instancia;
	}
	
	public int obtenerUltimoId() throws ReclamoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		int id = (Integer) s.createQuery("select max(idReclamo) from ReclamoEntity")
			.uniqueResult();
		//s.beginTransaction();
		if(id != 0)
			return id;
		else
			throw new ReclamoException("No se pudo recuperar el ultimo id");
		
	}
	
	public ReclamoEntity saveReclamo(Reclamo r) throws ReclamoException, ImagenException {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			ReclamoEntity re = new ReclamoEntity();
			if(r.getUnidad() != null)
				re = new ReclamoEntity(PersonaDAO.getInstancia().toEntity(r.getPersona()), 
					EdificioDAO.getInstancia().toEntity(r.getEdificio()), 
					r.getPiso(), 
					r.getUbicacion(),
					r.getTitulo(),
					r.getDescripcion(), 
					null,	//Caso base de que no se tenga ninguna imagen 
					r.getEstado(), 
					UnidadDAO.getInstancia().toEntity(r.getUnidad()));
			else
				re = new ReclamoEntity(PersonaDAO.getInstancia().toEntity(r.getPersona()), 
						EdificioDAO.getInstancia().toEntity(r.getEdificio()), 
						r.getPiso(), 
						r.getUbicacion(),
						r.getTitulo(),
						r.getDescripcion(), 
						null,	//Caso base de que no se tenga ninguna imagen
						r.getEstado(), 
						null);
			s.beginTransaction();
			s.save(re);
			s.getTransaction().commit();
			return re;
			} catch (Exception e) {
				throw new ReclamoException("No se pudo guardar el Reclamo");
			}
	}
	
	public List<Reclamo> getAll() throws ReclamoException, ImagenException {
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ReclamoEntity> reclamos = s.createQuery("from ReclamoEntity").list();
		for(ReclamoEntity r : reclamos)
			resultado.add(toNegocio(r));
		//s.getTransaction().commit();
		return resultado;
	}
	
	public Reclamo findByID(int codigo) throws ReclamoException, ImagenException {
		Reclamo resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		ReclamoEntity reclamo = (ReclamoEntity) s.createQuery("from ReclamoEntity r where r.idReclamo = ?").setInteger(0, codigo).uniqueResult();
		if(reclamo != null)
			resultado = toVista(reclamo);
		return resultado;
	}
	
	public Reclamo findByIdentificador(int identificador) throws ReclamoException, ImagenException {
		Reclamo resultado = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		ReclamoEntity reclamo = (ReclamoEntity) s.createQuery("from ReclamoEntity r where r.identificador = ?").setInteger(0, identificador).uniqueResult();
		if(reclamo != null)
			resultado = toNegocio(reclamo);
		return resultado;
	}
	
	public List<Reclamo> getReclamosByDocumento(String documento) throws ReclamoException, ImagenException {
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ReclamoEntity> reclamos = s.createQuery("from ReclamoEntity r where r.persona = ?").setString(0, documento).list();
		for(ReclamoEntity r : reclamos)
			resultado.add(toNegocio(r));
		//s.getTransaction().commit();
		return resultado;
	}
	
	public List<Reclamo> getReclamosByEdificio(int edificio) throws ReclamoException, ImagenException {
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ReclamoEntity> reclamos = s.createQuery("from ReclamoEntity r where r.edificio = ?").setInteger(0, edificio).list();
		for(ReclamoEntity r : reclamos)
			resultado.add(toNegocio(r));
		//s.getTransaction().commit();
		return resultado;
	}
	
	Reclamo toNegocio(ReclamoEntity r) throws ReclamoException, ImagenException {
		try {
			if(r != null) {
				Persona persona = PersonaDAO.getInstancia().findByID(r.getPersona().getDocumento());
				Edificio edificio = EdificioDAO.getInstancia().findByID(r.getEdificio().getCodigo());
				Unidad unidad = null;
				if(r.getUnidad() != null)
					unidad = UnidadDAO.getInstancia().findById(r.getUnidad().getEdificio().getCodigo(), r.getUnidad().getPiso(), r.getUnidad().getNumero());
				Reclamo reclamo = new Reclamo(r.getId(), persona, edificio, r.getPiso(), r.getUbicacion(), r.getTitulo(), r.getDescripcion(), unidad, r.getFecha());
				reclamo.setEstado(r.getEstado());
				//List<Imagen> imagenes = ImagenDAO.getInstancia().getImagenes(r.getId());
				//if(imagenes.size() > 0)
				//	reclamo.setImagenes(imagenes);
				return reclamo;
			}
			else
				throw new ReclamoException("No se pudo realizar la recuperación");
		} catch (Exception e) {
			throw new ReclamoException("No se pudo realizar la recuperación");
		}
	}
	
	Reclamo toVista(ReclamoEntity r) throws ReclamoException, ImagenException {
		try {
			if(r != null) {
				Persona persona = PersonaDAO.getInstancia().findByID(r.getPersona().getDocumento());
				Edificio edificio = EdificioDAO.getInstancia().findByID(r.getEdificio().getCodigo());
				Unidad unidad = null;
				if(r.getUnidad() != null)
					unidad = UnidadDAO.getInstancia().findById(r.getUnidad().getEdificio().getCodigo(), r.getUnidad().getPiso(), r.getUnidad().getNumero());
				Reclamo reclamo = new Reclamo(r.getId(), persona, edificio, r.getPiso(), r.getUbicacion(), r.getTitulo(), r.getDescripcion(), unidad, r.getFecha());
				reclamo.setEstado(r.getEstado());
				List<Imagen> imagenes = ImagenDAO.getInstancia().getImagenes(r.getId());
				if(imagenes.size() > 0)
					reclamo.setImagenes(imagenes);
				return reclamo;
			}
			else
				throw new ReclamoException("No se pudo realizar la recuperación");
		} catch (Exception e) {
			throw new ReclamoException("No se pudo realizar la recuperación");
		}
	}

	public void updateReclamoEstado(int id, String estado) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		s.createQuery("update ReclamoEntity set estado=? where idReclamo=?").setString(0, estado).setInteger(1, id).executeUpdate();
		s.getTransaction().commit();
	}
}
