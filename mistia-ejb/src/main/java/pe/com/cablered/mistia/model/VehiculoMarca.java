package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vehiculo_marca database table.
 * 
 */
@Entity
@Table(name="vehiculo_marca")
@XmlRootElement
@NamedQuery(name="VehiculoMarca.findAll", query="SELECT v FROM VehiculoMarca v")
public class VehiculoMarca extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_marca")
	private Integer codigoMarca;

	private String descripcion;



	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="vehiculoMarca", fetch= FetchType.EAGER)
	private List<Vehiculo> vehiculos;

	public VehiculoMarca() {
	}

	
	
	public VehiculoMarca(Integer codigoMarca, String descripcion) {
		super();
		this.codigoMarca = codigoMarca;
		this.descripcion = descripcion;
	}



	public Integer getCodigoMarca() {
		return this.codigoMarca;
	}

	public void setCodigoMarca(Integer codigoMarca) {
		this.codigoMarca = codigoMarca;
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
		vehiculo.setVehiculoMarca(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setVehiculoMarca(null);

		return vehiculo;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoMarca == null) ? 0 : codigoMarca.hashCode());
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
		VehiculoMarca other = (VehiculoMarca) obj;
		if (codigoMarca == null) {
			if (other.codigoMarca != null)
				return false;
		} else if (!codigoMarca.equals(other.codigoMarca))
			return false;
		return true;
	}



	@Override
	public String toString() {
	
		return descripcion;
	}

}