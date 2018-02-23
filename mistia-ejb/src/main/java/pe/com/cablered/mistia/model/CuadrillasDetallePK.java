package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the cuadrillas_detalle database table.
 * 
 */
@Embeddable
public class CuadrillasDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_cuadrilla", insertable=false, updatable=false)
	private Long numeroCuadrilla;

	@Column(name="numero_secuencia")
	private Integer numeroSecuencia;

	public CuadrillasDetallePK() {
	}
	
	
	
	
	
	public CuadrillasDetallePK(long numeroCuadrilla, Integer numeroSecuencia) {
		super();
		this.numeroCuadrilla = numeroCuadrilla;
		this.numeroSecuencia = numeroSecuencia;
	}





	public Long getNumeroCuadrilla() {
		return this.numeroCuadrilla;
	}
	public void setNumeroCuadrilla(Long numeroCuadrilla) {
		this.numeroCuadrilla = numeroCuadrilla;
	}
	public Integer getNumeroSecuencia() {
		return this.numeroSecuencia;
	}
	public void setNumeroSecuencia(Integer numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroCuadrilla == null) ? 0 : numeroCuadrilla.hashCode());
		result = prime * result + ((numeroSecuencia == null) ? 0 : numeroSecuencia.hashCode());
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
		CuadrillasDetallePK other = (CuadrillasDetallePK) obj;
		if (numeroCuadrilla == null) {
			if (other.numeroCuadrilla != null)
				return false;
		} else if (!numeroCuadrilla.equals(other.numeroCuadrilla))
			return false;
		if (numeroSecuencia == null) {
			if (other.numeroSecuencia != null)
				return false;
		} else if (!numeroSecuencia.equals(other.numeroSecuencia))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "CuadrillasDetallePK [numeroCuadrilla=" + numeroCuadrilla + ", numeroSecuencia=" + numeroSecuencia + "]";
	}
	
	
	
	
}