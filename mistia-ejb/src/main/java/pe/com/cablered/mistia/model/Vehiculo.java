package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vehiculos database table.
 * 
 */
@Entity
@Table(name="vehiculos")
@XmlRootElement
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="placa_vehiculo")
	private String placaVehiculo;
	
	
	@Column(name="descripcion")
	private String descripcion;

	//bi-directional many-to-one association to Cuadrilla
	@OneToMany(mappedBy="vehiculo",cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Cuadrilla> cuadrillas;

	//bi-directional many-to-one association to VehiculoMarca
	@ManyToOne
	@JoinColumn(name="codigo_marca")
	private VehiculoMarca vehiculoMarca;

	//bi-directional many-to-one association to VehiculoModelo
	@ManyToOne
	@JoinColumn(name="codigo_modelo")
	private VehiculoModelo vehiculoModelo;
	


	public Vehiculo() {
	}

	
	
	public Vehiculo(String placaVehiculo, String descripcion) {
		super();
		this.placaVehiculo = placaVehiculo;
		this.descripcion = descripcion;
	}



	public Vehiculo(String placaVehiculo) {
		super();
		this.placaVehiculo = placaVehiculo;
	}



	public String getPlacaVehiculo() {
		return this.placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}



	@XmlTransient
	public List<Cuadrilla> getCuadrillas() {
		return this.cuadrillas;
	}

	public void setCuadrillas(List<Cuadrilla> cuadrillas) {
		this.cuadrillas = cuadrillas;
	}

	public Cuadrilla addCuadrilla(Cuadrilla cuadrilla) {
		getCuadrillas().add(cuadrilla);
		cuadrilla.setVehiculo(this);

		return cuadrilla;
	}

	public Cuadrilla removeCuadrilla(Cuadrilla cuadrilla) {
		getCuadrillas().remove(cuadrilla);
		cuadrilla.setVehiculo(null);

		return cuadrilla;
	}

	public VehiculoMarca getVehiculoMarca() {
		return this.vehiculoMarca;
	}

	public void setVehiculoMarca(VehiculoMarca vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}

	public VehiculoModelo getVehiculoModelo() {
		return this.vehiculoModelo;
	}

	public void setVehiculoModelo(VehiculoModelo vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((placaVehiculo == null) ? 0 : placaVehiculo.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (placaVehiculo == null) {
			if (other.placaVehiculo != null)
				return false;
		} else if (!placaVehiculo.equals(other.placaVehiculo))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return this.descripcion;
	}
	
	
	
	
	
}