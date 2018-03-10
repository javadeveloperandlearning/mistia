package pe.com.cablered.seguridad.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the usuarios_perfiles database table.
 * 
 */
@Entity
@Table(name="seguridad.usuarios_perfiles")
@NamedQuery(name="UsuarioPerfil.findAll", query="SELECT u FROM UsuarioPerfil u")
public class UsuarioPerfil extends ObjectBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioPerfilPK id;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="cod_esta"  ,nullable= false, updatable= false )
	EstadoRegistro estadoRegistro;

	@ManyToOne(optional= false)
	@JoinColumns({
	@JoinColumn(name="cod_usua", referencedColumnName = "cod_usua", insertable= false, updatable= false )
	})
	Usuario usuario;
	

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_modu", referencedColumnName="cod_modu", insertable = false, updatable = false ),
		@JoinColumn(name="cod_perf", referencedColumnName="cod_perf", insertable = false, updatable = false)
		})
	private Perfil perfil;

	public UsuarioPerfil() {
	}

	public UsuarioPerfil(UsuarioPerfilPK id) {
		this.id = id;
	}

	
	public UsuarioPerfilPK getId() {
		return this.id;
	}

	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		UsuarioPerfil other = (UsuarioPerfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	



}