package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import exceptions.ImagenException;
import hibernate.HibernateUtil;
import modelo.Imagen;

public class ImagenDAO {

	private static ImagenDAO instancia;
	
	private ImagenDAO() { }
	
	public static ImagenDAO getInstancia() {
		if(instancia==null)
			instancia = new ImagenDAO();
		return instancia;
	}
	
	public int obtenerUltimoId() throws ImagenException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.getCurrentSession();
		int id = (Integer) s.createQuery("select max(numero) from ReclamoEntity")
			.uniqueResult();
		s.beginTransaction();
		if(id != 0)
			return id;
		else
			throw new ImagenException("No se pudo recuperar el ultimo id");
		
	}
	
	public List<Imagen> getImagenes(int idReclamo) throws ImagenException {
		try {
			List<Imagen> resultado = new ArrayList<Imagen>();
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ImagenEntity> imagenes = s.createQuery("from ImagenEntity i where i.reclamo = ?").setInteger(0, idReclamo).list();
			for(ImagenEntity i : imagenes)
				resultado.add(toNegocio(i));
			s.getTransaction().commit();
			return resultado;
		} catch (Exception e) {
			throw new ImagenException("No se pudo recuperar las imagenes");
		}
	}
	
	Imagen toNegocio(ImagenEntity i) throws ImagenException {
		if(i != null) {
			Imagen imagen = new Imagen(i.getNumero(), i.getBinary(), i.getTipo(), i.getReclamo().getId());
			return imagen;
		}
		else
			throw new ImagenException("No se pudo recuperar las imagenes");
	}

	public void saveImagen(ReclamoEntity reclamo, List<Imagen> imagenes) throws ImagenException {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.getCurrentSession();
			s.beginTransaction();
			ImagenEntity imagen = new ImagenEntity();
			for(Imagen i : imagenes) {
				imagen = new ImagenEntity(i.getBinary(), i.getTipo(), reclamo);
				s.beginTransaction();
				s.save(imagen);
			}
			s.getTransaction().commit();
		} catch (Exception e) {
			throw new ImagenException("No se pudo guardar las imagenes");
		}
	}
}
