package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tecnicos database table.
 * 
 */
@Entity
@Table(name="tecnicos")
@NamedQuery(name="Tecnico.findAll", query="SELECT t FROM Tecnico t")
public class Tecnico extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tecnico")
	private Integer codigoTecnico;

	private String apellidos;

	private String direccion;

	private String dni;

	private String email;



	private String nombres;

	private String telefono;



	//bi-directional many-to-one association to CuadrillasDetalle
	@OneToMany(mappedBy="tecnico")
	private List<CuadrillasDetalle> cuadrillasDetalles;

	//bi-directional many-to-one association to TecnicoCompetenciaDetalle
	@OneToMany(mappedBy="tecnico")
	private List<TecnicoCompetenciaDetalle> tecnicoCompetenciaDetalles;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="codigo_cargo")
	private Cargo cargo;

	//bi-directional many-to-one association to Distrito
	@ManyToOne
	@JoinColumn(name="codigo_distrito")
	private Distrito distrito;

	public Tecnico() {
	}
	
	
	
	
	
	

	public Integer getCodigoTecnico() {
		return this.codigoTecnico;
	}

	public void setCodigoTecnico(Integer codigoTecnico) {
		this.codigoTecnico = codigoTecnico;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public List<CuadrillasDetalle> getCuadrillasDetalles() {
		return this.cuadrillasDetalles;
	}

	public void setCuadrillasDetalles(List<CuadrillasDetalle> cuadrillasDetalles) {
		this.cuadrillasDetalles = cuadrillasDetalles;
	}

	public CuadrillasDetalle addCuadrillasDetalle(CuadrillasDetalle cuadrillasDetalle) {
		getCuadrillasDetalles().add(cuadrillasDetalle);
		cuadrillasDetalle.setTecnico(this);

		return cuadrillasDetalle;
	}

	public CuadrillasDetalle removeCuadrillasDetalle(CuadrillasDetalle cuadrillasDetalle) {
		getCuadrillasDetalles().remove(cuadrillasDetalle);
		cuadrillasDetalle.setTecnico(null);

		return cuadrillasDetalle;
	}

	public List<TecnicoCompetenciaDetalle> getTecnicoCompetenciaDetalles() {
		return this.tecnicoCompetenciaDetalles;
	}

	public void setTecnicoCompetenciaDetalles(List<TecnicoCompetenciaDetalle> tecnicoCompetenciaDetalles) {
		this.tecnicoCompetenciaDetalles = tecnicoCompetenciaDetalles;
	}

	public TecnicoCompetenciaDetalle addTecnicoCompetenciaDetalle(TecnicoCompetenciaDetalle tecnicoCompetenciaDetalle) {
		getTecnicoCompetenciaDetalles().add(tecnicoCompetenciaDetalle);
		tecnicoCompetenciaDetalle.setTecnico(this);

		return tecnicoCompetenciaDetalle;
	}

	public TecnicoCompetenciaDetalle removeTecnicoCompetenciaDetalle(TecnicoCompetenciaDetalle tecnicoCompetenciaDetalle) {
		getTecnicoCompetenciaDetalles().remove(tecnicoCompetenciaDetalle);
		tecnicoCompetenciaDetalle.setTecnico(null);

		return tecnicoCompetenciaDetalle;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTecnico == null) ? 0 : codigoTecnico.hashCode());
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
		Tecnico other = (Tecnico) obj;
		if (codigoTecnico == null) {
			if (other.codigoTecnico != null)
				return false;
		} else if (!codigoTecnico.equals(other.codigoTecnico))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	

}