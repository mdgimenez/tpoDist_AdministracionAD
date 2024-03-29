package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="unidades")
public class UnidadEntity {

	@Id
	@Column(name = "identificador")
	private int identificador;
	private String piso;
	private String numero;
	private String habitado;
	
	@ManyToOne
	@JoinColumn(name = "codigoEdificio")
	private EdificioEntity edificio;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="documento")
	List<DuenioEntity> duenios;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="documento")
	List<InquilinoEntity> inquilinos;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="identificador")
	List<ReclamoEntity> reclamos;
	
	public UnidadEntity() {}
			
	public UnidadEntity(int identificador, String piso, String numero, EdificioEntity edificio) {
		this.identificador = identificador;
		this.piso = piso;
		this.numero = numero;
		this.habitado = "N";
		this.edificio = edificio;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	public String getHabitado() {
		return habitado;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}
	
	public List<DuenioEntity> getDuenios() {
		return duenios;
	}
	
	public List<ReclamoEntity> getReclamos() {
		return reclamos;
	}
}
