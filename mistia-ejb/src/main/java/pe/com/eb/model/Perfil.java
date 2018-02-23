package pe.com.eb.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the perfiles database table.
 * 
 */
@Entity
@Table(name="seguridad.perfiles")
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil extends ObjectBean implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PerfilPK id;

	@ManyToOne
	@JoinColumn(name="cod_esta"  ,referencedColumnName = "cod_esta"  ,insertable = true, updatable= true )
	EstadoRegistro estadoRegistro;

	@Column(name="des_perf")
	private String desPerf;
	
	@ManyToOne 
	@JoinColumn(name="cod_modu", referencedColumnName = "cod_modu"  ,insertable = false, updatable= false )
	Modulo modulo;

	//bi-directional many-to-one association to PerfilOpcion
	@OneToMany(mappedBy="perfil")
	private List<PerfilOpcion> perfilesOpciones;

	//bi-directional many-to-one association to UsuarioPerfil
	@OneToMany(mappedBy="perfil")
	private List<UsuarioPerfil> usuariosPerfiles;

	public Perfil() {
		
		
	}	
	
	

	public Perfil(PerfilPK id) {
		super();
		this.id = id;
	}
	
	public Perfil(Integer codModu, Integer codPerf) {
		this.id = new PerfilPK(codModu, codPerf);
	}

	public PerfilPK getId() {
		return this.id;
	}

	public void setId(PerfilPK id) {
		this.id = id;
	}
	

	

	public String getDesPerf() {
		return this.desPerf;
	}

	public void setDesPerf(String desPerf) {
		this.desPerf = desPerf;
	}

	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	public EstadoRegistro getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(EstadoRegistro estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public List<PerfilOpcion> getPerfilesOpciones() {
		return this.perfilesOpciones;
	}

	public void setPerfilesOpciones(List<PerfilOpcion> perfilesOpciones) {
		this.perfilesOpciones = perfilesOpciones;
	}

	public PerfilOpcion addPerfilesOpcione(PerfilOpcion perfilesOpcione) {
		getPerfilesOpciones().add(perfilesOpcione);
		perfilesOpcione.setPerfile(this);

		return perfilesOpcione;
	}

	public PerfilOpcion removePerfilesOpcione(PerfilOpcion perfilesOpcione) {
		getPerfilesOpciones().remove(perfilesOpcione);
		perfilesOpcione.setPerfile(null);

		return perfilesOpcione;
	}

	public List<UsuarioPerfil> getUsuariosPerfiles() {
		return this.usuariosPerfiles;
	}

	public void setUsuariosPerfiles(List<UsuarioPerfil> usuariosPerfiles) {
		this.usuariosPerfiles = usuariosPerfiles;
	}

	public UsuarioPerfil addUsuariosPerfile(UsuarioPerfil usuariosPerfile) {
		getUsuariosPerfiles().add(usuariosPerfile);
		usuariosPerfile.setPerfil(this);

		return usuariosPerfile;
	}

	public UsuarioPerfil removeUsuariosPerfile(UsuarioPerfil usuariosPerfile) {
		getUsuariosPerfiles().remove(usuariosPerfile);
		usuariosPerfile.setPerfil(null);

		return usuariosPerfile;
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Perfil clone(){
		
		try {
			return (Perfil)super.clone(); 
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this;
		}
	}
	

}