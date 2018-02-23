package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the red_neuronal_detalle database table.
 * 
 */
@Embeddable
public class RedNeuronalDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Integer codigorn;

	@Column(name="numero_peso")
	private Integer numeroPeso;

	public RedNeuronalDetallePK() {
		super();
	}

	
	public RedNeuronalDetallePK(Integer codigorn, Integer numeroPeso) {
		super();
		this.codigorn = codigorn;
		this.numeroPeso = numeroPeso;
	}




	public Integer getCodigorn() {
		return this.codigorn;
	}
	public void setCodigorn(Integer codigorn) {
		this.codigorn = codigorn;
	}
	public Integer getNumeroPeso() {
		return this.numeroPeso;
	}
	public void setNumeroPeso(Integer numeroPeso) {
		this.numeroPeso = numeroPeso;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RedNeuronalDetallePK)) {
			return false;
		}
		RedNeuronalDetallePK castOther = (RedNeuronalDetallePK)other;
		return 
			this.codigorn.equals(castOther.codigorn)
			&& this.numeroPeso.equals(castOther.numeroPeso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigorn.hashCode();
		hash = hash * prime + this.numeroPeso.hashCode();
		
		return hash;
	}
}