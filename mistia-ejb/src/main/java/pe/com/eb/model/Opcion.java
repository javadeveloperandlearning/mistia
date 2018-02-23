package pe.com.eb.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the opciones database table.
 * 
 */
@Entity
@Table(name="seguridad.opciones")
@NamedQuery(name="Opcion.findAll", query="SELECT o FROM Opcion o")
public class Opcion extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OpcionPK id;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="cod_esta"  ,nullable= false, updatable= false )
	EstadoRegistro estadoRegistro;

	@Column(name="des_opci")
	private String desOpci;

	@Column(name="url_opci")
	private String urlOpci;
	
	@Column(name="tipo_opci")
	private Integer tipoOpci;

	//bi-directional many-to-many association to Modulo
	@ManyToMany(mappedBy="opciones")
	private List<Modulo> modulos;

	//bi-directional many-to-one association to PerfilOpcion
	@OneToMany(mappedBy="opcion")
	private List<PerfilOpcion> perfilesOpciones;
	
	
	@Transient
	private String componente;

	public Opcion() {
	}
	
	

	public Opcion(String desOpci, String urlOpci, Integer tipoOpci) {
		super();
		this.desOpci = desOpci;
		this.urlOpci = urlOpci;
		this.tipoOpci = tipoOpci;
	}

	public Opcion(String desOpci, String urlOpci, Integer tipoOpci, String componente) {
		super();
		this.desOpci = desOpci;
		this.urlOpci = urlOpci;
		this.tipoOpci = tipoOpci;
		this.componente =  componente;
	}


	public OpcionPK getId() {
		return this.id;
	}

	public void setId(OpcionPK id) {
		this.id = id;
	}


	public String getDesOpci() {
		return this.desOpci;
	}

	public void setDesOpci(String desOpci) {
		this.desOpci = desOpci;
	}

	public String getUrlOpci() {
		return this.urlOpci;
	}

	public void setUrlOpci(String urlOpci) {
		this.urlOpci = urlOpci;
	}
	
	
	
	public Integer getTipoOpci() {
		return tipoOpci;
	}

	public void setTipoOpci(Integer tipoOpci) {
		this.tipoOpci = tipoOpci;
	}
	
	
	

	public String getComponente() {
		return componente;
	}



	public void setComponente(String componente) {
		this.componente = componente;
	}



	public List<Modulo> getModulos() {
		return this.modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public List<PerfilOpcion> getPerfilesOpciones() {
		return this.perfilesOpciones;
	}

	public void setPerfilesOpciones(List<PerfilOpcion> perfilesOpciones) {
		this.perfilesOpciones = perfilesOpciones;
	}

	public PerfilOpcion addPerfilesOpcione(PerfilOpcion perfilesOpcione) {
		getPerfilesOpciones().add(perfilesOpcione);
		perfilesOpcione.setOpcion(this);

		return perfilesOpcione;
	}

	public PerfilOpcion removePerfilesOpcione(PerfilOpcion perfilesOpcione) {
		getPerfilesOpciones().remove(perfilesOpcione);
		perfilesOpcione.setOpcion(null);

		return perfilesOpcione;
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
		Opcion other = (Opcion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}