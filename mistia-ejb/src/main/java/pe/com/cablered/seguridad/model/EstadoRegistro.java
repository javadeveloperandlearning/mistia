package pe.com.cablered.seguridad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "general.estados_registros")
public class EstadoRegistro extends ObjectBean {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cod_esta")
	private Integer codEsta;
	@Column(name = "des_esta")
	private String desEsta;
	@Column(name = "ind_acti")
	private Integer indActi;
	

	public Integer getCodEsta() {
		return codEsta;
	}

	public void setCodEsta(Integer codEsta) {
		this.codEsta = codEsta;
	}

	public String getDesEsta() {
		return desEsta;
	}

	public void setDesEsta(String desEsta) {
		this.desEsta = desEsta;
	}

	public Integer getIndActi() {
		return indActi;
	}

	public void setIndActi(Integer indActi) {
		this.indActi = indActi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEsta == null) ? 0 : codEsta.hashCode());
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
		EstadoRegistro other = (EstadoRegistro) obj;
		if (codEsta == null) {
			if (other.codEsta != null)
				return false;
		} else if (!codEsta.equals(other.codEsta))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {

		return desEsta;
	}
	

}
