/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author javadeveloper
 */
@Embeddable
public class ClienteDireccionPK implements Serializable {

    @Column(name = "codigo_cliente", insertable = false, updatable = false)
    Integer codigoCliente;

    @Column(name = "numero_direccion", insertable = false, updatable = false)
    Integer numeroDireccion;

    public ClienteDireccionPK() {
    }

    public ClienteDireccionPK(Integer codigoCliente, Integer numeroDireccion) {
        this.codigoCliente = codigoCliente;
        this.numeroDireccion = numeroDireccion;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getNumeroDireccion() {
        return numeroDireccion;
    }

    public void setNumeroDireccion(Integer numeroDireccion) {
        this.numeroDireccion = numeroDireccion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.codigoCliente);
        hash = 19 * hash + Objects.hashCode(this.numeroDireccion);
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
        final ClienteDireccionPK other = (ClienteDireccionPK) obj;
        if (!Objects.equals(this.codigoCliente, other.codigoCliente)) {
            return false;
        }
        if (!Objects.equals(this.numeroDireccion, other.numeroDireccion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClienteDireccionPK{" + "codigoCliente=" + codigoCliente + ", numeroDireccion=" + numeroDireccion + '}';
    }

}
