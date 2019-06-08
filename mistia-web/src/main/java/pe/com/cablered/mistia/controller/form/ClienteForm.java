/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.controller.form;

import java.util.List;
import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.Departamento;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Provincia;

/**
 *
 * @author javadeveloper
 */
public class ClienteForm  extends Cliente{
    
   private Integer codigoDepartamento;
   private Integer codigoProvincia;
   private Integer codigoDisrito;
    
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    private List<Departamento> departamentoList;
    private List<Distrito> distritoList;
    private List<Provincia> provinciaList;   
    
    public Cliente toCliente(){
        
        /*
            private Integer codigoCliente;
            private String apellidos;
            private Integer codigoDistrito;
            private String direccion;
            private String estacionCreacion;
            private String estacionModificacion;
            private Timestamp fechaCreacion;
            private Timestamp fechaModificacion;
            private String nombres;
            private String telefono;
            private String usuarioCreacion;
            private String usuarioModificacion;
            private Integer tipoDocumento;
            private String documentoIdentidad;
            private String nombreRazonSocial;
            private String telefonoMovil;
            private Integer sexo;
            private String email
            private String numeroRuc;
        */
        Cliente cliente =   new Cliente();
        cliente.setCodigoCliente(this.getCodigoCliente());
        cliente.setNombres(this.getNombres());
        cliente.setCodigoDistrito(codigoDisrito);
        
        return cliente;
    } 
    
 
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public String getApellidos() {
        return apellidoPaterno+" "+ apellidoMaterno;
    }

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Integer getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Integer codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public Integer getCodigoDisrito() {
        return codigoDisrito;
    }

    public void setCodigoDisrito(Integer codigoDisrito) {
        this.codigoDisrito = codigoDisrito;
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }



    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public List<Distrito> getDistritoList() {
        return distritoList;
    }

    public void setDistritoList(List<Distrito> distritoList) {
        this.distritoList = distritoList;
    }
    
    
    
    
    
   
}
