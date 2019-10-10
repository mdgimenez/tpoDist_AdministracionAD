package test;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.ImagenException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import exceptions.UsuarioException;
import modelo.Imagen;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;
import views.UsuarioView;

public class Test {

	public static void main(String[] args) throws EdificioException, UnidadException, PersonaException, ReclamoException, ImagenException {
		
		/*List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		System.out.println("Edificios " + edificios.size());
		
		List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(1);
		System.out.println("\nUnidades por edificio " + unidades.size());
		
		List<PersonaView> p1 = Controlador.getInstancia().habitantesPorEdificio(1);
		System.out.println("\nHabitantes por Edificio " +  p1.size());
		
		List<PersonaView> p2 = Controlador.getInstancia().dueniosPorEdificio(1);
		System.out.println("\nDuenios por Edificio " +  p2.size());
				
		List<PersonaView> p3 = Controlador.getInstancia().inquilinosPorEdificio(1);		
		System.out.println("\nInquilinos por Edificio " +  p3.size());

		List<PersonaView> p4 = Controlador.getInstancia().habilitadosPorEdificio(1);		
		System.out.println("\nHabilitados por Edificio " +  p4.size());
		
		List<PersonaView> pu = Controlador.getInstancia().dueniosPorUnidad(1, "1", "1");
		System.out.println("\nDuenios por unidad " + pu.size());

		List<PersonaView> iu = Controlador.getInstancia().inquilinosPorUnidad(1, "1", "1");
		System.out.println("\nInquilinos por unidad " + iu.size());

		List<ReclamoView> rv = Controlador.getInstancia().getReclamos();
		System.out.println("\nCantidad de reclamos registrados " + rv.size());*/
	
		/*RenderedImage imagen;
		File f = new File("B:\\Viaje a Brasil 2017\\IMG_20170115_105248624.jpg");
		try {
			imagen = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imagen, "jpg", baos );
			byte[] bytes = baos.toByteArray();
			Imagen i = new Imagen(-1, bytes, "jpg", 16);
			List<Imagen> imagenes = new ArrayList<Imagen>();
			imagenes.add(i);
			int id = Controlador.getInstancia().crearReclamo(new ReclamoView(-1,"CPA3449614", 1, "10", "Pasillo", "Mancha de humedad", 0, "Revision", imagenes));
			System.out.println("Id reclamo: " + id);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		/*Controlador.getInstancia().actualizarEstadoReclamo(13, "En progreso");
		
		System.out.println(Controlador.getInstancia().buscarReclamo(id).getEstado());
		
		List<ReclamoView> rv = Controlador.getInstancia().getReclamosByEdificio(6);*/
		
		/*List<ReclamoView> rv = Controlador.getInstancia().getReclamosByDocumento("DNI92920447");
		System.out.println(rv.size());*/
		
		/*List<ReclamoView> rv = Controlador.getInstancia().getReclamos();*/
		
		try {
			Controlador.getInstancia().registrarUsuario(new UsuarioView(-1, "CPA3449614", "password"));
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		
		/*List<ReclamoView> resultado = Controlador.getInstancia().buscarReclamosAsociados("DNI30012288");
		for(ReclamoView rv: resultado)
		{
			System.out.println(rv.getId());
		}*/
	}

}
