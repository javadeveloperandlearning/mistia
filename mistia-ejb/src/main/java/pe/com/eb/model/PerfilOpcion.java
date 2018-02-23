package pe.com.eb.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the perfiles_opciones database table.
 * 
 */
@Entity
@Table(name="seguridad.perfiles_opciones")
@NamedQuery(name="PerfilOpcion.findAll", query="SELECT p FROM PerfilOpcion p")
public class PerfilOpcion extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PerfilOpcionPK id;

	@ManyToOne 
	@JoinColumn(name="cod_esta"  ,nullable= false, updatable= false )
	EstadoRegistro estadoRegistro;



	//bi-directional many-to-one association to Opcion
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_modu", referencedColumnName="cod_modu",insertable = false, updatable = false),
		@JoinColumn(name="cod_opci", referencedColumnName="cod_opci",insertable = false, updatable = false)
		})
	private Opcion opcion;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_modu", referencedColumnName="cod_modu",insertable = false, updatable = false),
		@JoinColumn(name="cod_perf", referencedColumnName="cod_perf",insertable = false, updatable = false)
		})
	private Perfil perfil;

	public PerfilOpcion() {
	}

	public PerfilOpcionPK getId() {
		return this.id;
	}

	public void setId(PerfilOpcionPK id) {
		this.id = id;
	}


	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfile(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PerfilOpcion other = (PerfilOpcion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}