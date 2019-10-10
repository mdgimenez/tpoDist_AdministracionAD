package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="imagenes")
public class ImagenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	
	private byte[] binary;
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "idReclamo")
	private ReclamoEntity reclamo;
	
	public ImagenEntity() {	}
	
	public ImagenEntity(byte[] binary, String tipo, ReclamoEntity reclamo) {
		this.reclamo = reclamo;
		this.binary = binary;
		this.tipo = tipo;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public byte[] getBinary() {
		return binary;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public ReclamoEntity getReclamo() {
		return reclamo;
	}
	
}
