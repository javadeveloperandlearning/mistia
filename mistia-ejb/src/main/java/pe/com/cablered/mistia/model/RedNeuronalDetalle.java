package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the red_neuronal_detalle database table.
 * 
 */
@Entity
@Table(name="red_neuronal_detalle")
@NamedQuery(name="RedNeuronalDetalle.findAll", query="SELECT r FROM RedNeuronalDetalle r")
public class RedNeuronalDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RedNeuronalDetallePK id;

	@Column(name="numero_conexion")
	private Integer numeroConexion;

	@Column(name="numero_neurona")
	private Integer numeroNeurona;

	@Column(name="valor_peso")
	private BigDecimal valorPeso;

	//bi-directional many-to-one association to RedNeuronal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigorn", insertable = false, updatable= false)
	private RedNeuronal redNeuronal;

	public RedNeuronalDetalle() {
	}

	public RedNeuronalDetallePK getId() {
		return this.id;
	}

	public void setId(RedNeuronalDetallePK id) {
		this.id = id;
	}

	public Integer getNumeroConexion() {
		return this.numeroConexion;
	}

	public void setNumeroConexion(Integer numeroConexion) {
		this.numeroConexion = numeroConexion;
	}

	public Integer getNumeroNeurona() {
		return this.numeroNeurona;
	}

	public void setNumeroNeurona(Integer numeroNeurona) {
		this.numeroNeurona = numeroNeurona;
	}

	public BigDecimal getValorPeso() {
		return this.valorPeso;
	}

	public void setValorPeso(BigDecimal valorPeso) {
		this.valorPeso = valorPeso;
	}

	public RedNeuronal getRedNeuronal() {
		return this.redNeuronal;
	}

	public void setRedNeuronal(RedNeuronal redNeuronal) {
		this.redNeuronal = redNeuronal;
	}

}