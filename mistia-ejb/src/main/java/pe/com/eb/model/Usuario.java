package pe.com.eb.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="seguridad.usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends ObjectBean implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional= false)
	@Column(name="cod_usua")
	private String codUsua;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="fec_cadu")
	private Date fec_cadu;
	
	@Column(name="fec_cadu_clave")
	private Date  fec_cadu_clave ;
	
	@Column(name="ind_camb_clave")
	private Integer ind_camb_clave;
	
	@Column(name="num_cone")
	private Integer num_cone;
	
	@Transient
	private String claveConfirmacion;
	
	
	//@ManyToOne (fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="cod_esta"  ,nullable= false, updatable= false )
	EstadoRegistro estadoRegistro;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<UsuarioPerfil> usuarioPerfilList;


	public Usuario() {
	}
	
	public Usuario(  String codUsua) {
		
		this.codUsua = codUsua;
	}

	public String getCodUsua() {
		return this.codUsua;
	}

	public void setCodUsua(String codUsua) {
		this.codUsua = codUsua;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public EstadoRegistro getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(EstadoRegistro estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}


	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Date getFec_cadu() {
		return fec_cadu;
	}

	public void setFec_cadu(Date fec_cadu) {
		this.fec_cadu = fec_cadu;
	}

	public Date getFec_cadu_clave() {
		return fec_cadu_clave;
	}

	public void setFec_cadu_clave(Date fec_cadu_clave) {
		this.fec_cadu_clave = fec_cadu_clave;
	}

	public Integer getInd_camb_clave() {
		return ind_camb_clave;
	}

	public void setInd_camb_clave(Integer ind_camb_clave) {
		this.ind_camb_clave = ind_camb_clave;
	}

	public Integer getNum_cone() {
		return num_cone;
	}

	public void setNum_cone(Integer num_cone) {
		this.num_cone = num_cone;
	}

	public List<UsuarioPerfil> getUsuarioPerfilList() {
		return usuarioPerfilList;
	}

	public void setUsuarioPerfilList(List<UsuarioPerfil> usuarioPerfilList) {
		this.usuarioPerfilList = usuarioPerfilList;
	}

	

	public String getClaveConfirmacion() {
		return claveConfirmacion;
	}

	public void setClaveConfirmacion(String claveConfirmacion) {
		this.claveConfirmacion = claveConfirmacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUsua == null) ? 0 : codUsua.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codUsua == null) {
			if (other.codUsua != null)
				return false;
		} else if (!codUsua.equals(other.codUsua))
			return false;
		return true;
	}


	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return this;
		}
	}


}