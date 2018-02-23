package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vehiculo_modelo database table.
 * 
 */
@Entity
@Table(name="vehiculo_modelo")
@XmlRootElement
@NamedQuery(name="VehiculoModelo.findAll", query="SELECT v FROM VehiculoModelo v")
public class VehiculoModelo extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_modelo")
	private Integer codigoModelo;

	private String descripcion;

	public VehiculoModelo() {
	}
	
	public VehiculoModelo(Integer codigoModelo, String descripcion) {
		super();
		this.codigoModelo = codigoModelo;
		this.descripcion = descripcion;
	}

	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="vehiculoModelo")
	private List<Vehiculo> vehiculos;


	public Integer getCodigoModelo() {
		return this.codigoModelo;
	}

	public void setCodigoModelo(Integer codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@XmlTransient
	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.setVehiculoModelo(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setVehiculoModelo(null);

		return vehiculo;
	}

}