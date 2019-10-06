package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagenes")
public class ImagenEntity {

	@Id
	private int numero;
	
	private String binary;
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "idReclamo")
	private ReclamoEntity reclamo;
	
	public ImagenEntity() {	}
	
	public ImagenEntity(String binary, String tipo, int idReclamo) {
		this.binary = binary;
		this.tipo = tipo;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getBinary() {
		return binary;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public ReclamoEntity getReclamo() {
		return reclamo;
	}
	
	
}
