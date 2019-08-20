package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the tecnico_competencia_detalle database table.
 *
 */
@Entity
@Table(name = "tecnico_competencia_detalle")
@NamedQuery(name = "TecnicoCompetenciaDetalle.findAll", query = "SELECT t FROM TecnicoCompetenciaDetalle t")
public class TecnicoCompetenciaDetalle extends ObjectBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TecnicoCompetenciaDetallePK id;

    @Column(name = "grado_competencia")
    private BigDecimal gradoCompetencia;

    //bi-directional many-to-one association to Competencia
    @ManyToOne
    @JoinColumn(name = "codigo_competencia", insertable = false, updatable = false)
    private Competencia competencia;

    //bi-directional many-to-one association to Tecnico
    @ManyToOne
    @JoinColumn(name = "codigo_tecnico", insertable = false, updatable = false)
    private Tecnico tecnico;

    public TecnicoCompetenciaDetalle() {
    }

    public TecnicoCompetenciaDetallePK getId() {
        return this.id;
    }

    public void setId(TecnicoCompetenciaDetallePK id) {
        this.id = id;
    }

    public BigDecimal getGradoCompetencia() {
        return this.gradoCompetencia;
    }

    public void setGradoCompetencia(BigDecimal gradoCompetencia) {
        this.gradoCompetencia = gradoCompetencia;
    }

    public Competencia getCompetencia() {
        return this.competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }

    public Tecnico getTecnico() {
        return this.tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public String toString() {
        return "TecnicoCompetenciaDetalle{" + "id=" + id + ", gradoCompetencia=" + gradoCompetencia + ", competencia=" + competencia + ", tecnico=" + tecnico + '}';
    }

}
