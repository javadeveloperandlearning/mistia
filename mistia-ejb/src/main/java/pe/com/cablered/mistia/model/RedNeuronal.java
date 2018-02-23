package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the red_neuronal database table.
 * 
 */
@Entity
@Table(name="red_neuronal")
@NamedQuery(name="RedNeuronal.findAll", query="SELECT r FROM RedNeuronal r")
public class RedNeuronal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer codigorn;

	private String descripcion;

	@Column(name="numero_capas_ocultas")
	private Integer numeroCapasOcultas;

	@Column(name="numero_entradas")
	private Integer numeroEntradas;

	@Column(name="numero_salidas")
	private Integer numeroSalidas;

	//bi-directional many-to-one association to RedNeuronalDetalle
	@OneToMany(mappedBy="redNeuronal")
	private List<RedNeuronalDetalle> redNeuronalDetalles;

	public RedNeuronal() {
	}

	public Integer getCodigorn() {
		return this.codigorn;
	}

	public void setCodigorn(Integer codigorn) {
		this.codigorn = codigorn;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumeroCapasOcultas() {
		return this.numeroCapasOcultas;
	}

	public void setNumeroCapasOcultas(Integer numeroCapasOcultas) {
		this.numeroCapasOcultas = numeroCapasOcultas;
	}

	public Integer getNumeroEntradas() {
		return this.numeroEntradas;
	}

	public void setNumeroEntradas(Integer numeroEntradas) {
		this.numeroEntradas = numeroEntradas;
	}

	public Integer getNumeroSalidas() {
		return this.numeroSalidas;
	}

	public void setNumeroSalidas(Integer numeroSalidas) {
		this.numeroSalidas = numeroSalidas;
	}

	public List<RedNeuronalDetalle> getRedNeuronalDetalles() {
		return this.redNeuronalDetalles;
	}

	public void setRedNeuronalDetalles(List<RedNeuronalDetalle> redNeuronalDetalles) {
		this.redNeuronalDetalles = redNeuronalDetalles;
	}

	public RedNeuronalDetalle addRedNeuronalDetalle(RedNeuronalDetalle redNeuronalDetalle) {
		getRedNeuronalDetalles().add(redNeuronalDetalle);
		redNeuronalDetalle.setRedNeuronal(this);

		return redNeuronalDetalle;
	}

	public RedNeuronalDetalle removeRedNeuronalDetalle(RedNeuronalDetalle redNeuronalDetalle) {
		getRedNeuronalDetalles().remove(redNeuronalDetalle);
		redNeuronalDetalle.setRedNeuronal(null);

		return redNeuronalDetalle;
	}

}