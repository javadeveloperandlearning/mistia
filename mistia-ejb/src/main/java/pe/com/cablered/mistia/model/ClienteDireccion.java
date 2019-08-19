/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author javadeveloper
 */
@Entity
@Table(name = "clientes_direcciones")
public class ClienteDireccion   extends ObjectBean implements Serializable {

    @EmbeddedId
    private ClienteDireccionPK id;

    /*@ManyToOne
    @JoinColumn(name = "codigo_cliente", insertable = false, updatable = false)*/
    @Transient
    private Cliente cliente;
    
    

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "referencia")
    private String referencia;

    
    @ManyToOne
    @JoinColumn(name = "codigo_distrito", updatable = true, insertable = true)
    private Distrito distrito;

    public ClienteDireccion() {
    }

    
    public ClienteDireccion(Integer codigoCliente, Integer numeroDireccion ) {    
        this.id =  new ClienteDireccionPK(codigoCliente, numeroDireccion);
    }
    
    
    public ClienteDireccionPK getId() {
        return id;
    }

    public void setId(ClienteDireccionPK id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClienteDireccion other = (ClienteDireccion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
