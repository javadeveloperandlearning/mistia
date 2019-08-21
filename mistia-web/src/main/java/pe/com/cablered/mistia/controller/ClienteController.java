package pe.com.cablered.mistia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.controller.form.ClienteForm;
import pe.com.cablered.mistia.dao.DepartamentoDao;
import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.ClienteDireccion;
import pe.com.cablered.mistia.model.Departamento;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.ObjectBean;
import pe.com.cablered.mistia.model.Provincia;
import pe.com.cablered.mistia.model.Tipo;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.service.AbstractSevice;
import pe.com.cablered.mistia.service.ClienteService;
import pe.com.cablered.mistia.service.DepartamentoService;
import pe.com.cablered.mistia.service.DistritoService;
import pe.com.cablered.mistia.service.ProvinciaService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.TipoService;
import pe.com.cablered.mistia.util.ConstantBusiness;
import static pe.com.cablered.mistia.util.ConstantBusiness.*;

/**
 *
 * @author javadeveloper
 */
@ManagedBean(name = "clienteview")
@ViewScoped
public class ClienteController extends BaseController<Cliente> implements Serializable {

    private List<Cliente> clienteList;
    private List<Tipo> tipoList;

    private Integer codigoCliente;
    private int tipoDocu;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreRazonSocial;

    private Cliente cliente;

    private Integer codigoDepartamento;
    private Integer codigoProvincia;

    private List<Departamento> departamentoList;
    private List<Distrito> distritoList;
    private List<Provincia> provinciaList;
    private List<ClienteDireccion> clienteDireccionList;
    private boolean disabledNombres;
    private boolean disabledNombresFiltros;

    public boolean isDisabledNombresFiltros() {
        return disabledNombresFiltros;
    }

    public void setDisabledNombresFiltros(boolean disabledNombresFiltros) {
        this.disabledNombresFiltros = disabledNombresFiltros;
    }

    @Inject
    private ClienteService clienteService;

    @Inject
    private TipoService tipoService;

    @Inject
    private DepartamentoService departamentoService;

    @Inject
    private ProvinciaService provinciaService;

    @Inject
    private DistritoService distritoService;

    @Inject
    private FacesContext facesContext;

    final static Logger logger = Logger.getLogger(ClienteController.class);

    @PostConstruct
    public void init() {

        tipoList = tipoService.getTipoList(ConstantBusiness.COD_GRUPO_TIPO_DOCU_IDENT);
        cliente = new Cliente();
        setCodigoDepartamento(COD_DEPARTAMENTO_JUNIN);
        setDepartamentoList(departamentoService.getDepartamentoList());
        setProvinciaList(provinciaService.getProvinciaList(COD_DEPARTAMENTO_JUNIN));
        setDistritoList(distritoService.getDistritoList(COD_DEPARTAMENTO_HUANACAYO));
        limpiar();
        //clienteList = clienteService.clienteList();
    }

    public void setObject(ObjectBean object) {
        this.object = object;
        this.cliente = (Cliente) object;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public void editar(Cliente cliente) {
        logger.info("metodo :  editar ");
        super.editar(cliente);

        List<ClienteDireccion> clienteDireccionList = clienteService.getClienteDireccionList(cliente);
        cliente.setClienteDireccions(clienteDireccionList);

    }

    /*
    public void guardar(){
        Response response  =  clienteService.registrar((Cliente)cliente);
    }*/
    public void limpiar() {
        tipoDocu = TODOS_VALUE;
        codigoCliente = null;
        nombres = null;
        apellidoPaterno = null;
        apellidoMaterno = null;
        nombreRazonSocial = null;
        clienteList = Collections.EMPTY_LIST;
        cliente.setClienteDireccions(new ArrayList<>());

    }

    public void actualizarDistritos() {

        List<Distrito> distritoList = distritoService.getDistritoList(getCodigoProvincia());
        for (Distrito distrito : distritoList) {
            logger.info(distrito.toString());
        }
        setDistritoList(distritoList);
    }

    public void salir() {
    }

    @Override
    public void mostrar() {
        try {

            clienteList = clienteService.clienteList(tipoDocu, codigoCliente, nombres, apellidoPaterno, apellidoMaterno, nombreRazonSocial);
            setList(clienteList);
        } catch (Exception e) {
            clienteList = Collections.EMPTY_LIST;
        }
    }

    @Override
    public void nuevo() {
        super.nuevo();
        
        this.cliente = new Cliente();
        
        
        this.cliente.setClienteDireccions(new ArrayList<>());
        setCodigoDepartamento(COD_DEPARTAMENTO_JUNIN);
        setDepartamentoList(departamentoService.getDepartamentoList());
        setProvinciaList(provinciaService.getProvinciaList(COD_DEPARTAMENTO_JUNIN));
        setDistritoList(distritoService.getDistritoList(COD_DEPARTAMENTO_HUANACAYO));
        this.cliente.setTipoDocumento(new Tipo(TIPO_DOCU_DNI));
        this.cliente.setSexo(SM);

        setObject(cliente);

    }

    @Override
    public void grabar() {

        this.cliente.setTipoDocumento(new Tipo(this.cliente.getCodigoTipoDocumento()));
        if (action.equals(NUEVO)) {
            cliente.setCodigoCliente(clienteService.getMax());
        } else {
            Response response = clienteService.actualizarDireccionesList(cliente);
        }

        setObject(cliente);
        super.grabar();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarDireccion() {

        logger.info(" metodo :  agregarDireccion  ");
        logger.info(" cliente.getDireccion(): ." + cliente.getDireccion() + ".");
        if (cliente.getDireccion() == null || (cliente.getDireccion() != null && cliente.getDireccion().trim().equals(""))) {
            mostrarMensaje(new Response(Response.ERROR, "Debe ingresar una dirección Válida"));
            return;
        }

        Integer numeroDIreccion = cliente.getClienteDireccions().size() + 1;
        ClienteDireccion clienteDireccion = new ClienteDireccion(0, numeroDIreccion);
        clienteDireccion.setDireccion(cliente.getDireccion());
        clienteDireccion.setReferencia(cliente.getReferencia());
        clienteDireccion.setDistrito(distritoService.getDistrito(cliente.getCodigoDistrito()));
        cliente.getClienteDireccions().add(clienteDireccion);
        cliente.setDireccion("");
        cliente.setReferencia("");

    }

    public void eliminarDireccion(ClienteDireccion clienteDireccion) {
        logger.info(" metodo :  eliminarDireccion ");
        try {
            int idx = cliente.getClienteDireccions().indexOf(clienteDireccion);
            cliente.getClienteDireccions().remove(idx);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void activaNombres() {
        logger.info("metodo : activaNombres");
        if (cliente.getCodigoTipoDocumento() != null && cliente.getCodigoTipoDocumento() == TIPO_DOCU_RUC) {
            disabledNombres = true;
        } else {
            disabledNombres = false;
        }
    }

    public void activaNombresFiltros() {
        logger.info("metodo : activaNombresFiltros");
        
        if (getTipoDocu() == TIPO_DOCU_RUC) {
            
            disabledNombresFiltros = true;
            
            this.nombres = null;
            this.apellidoPaterno = null;
            this.apellidoMaterno = null;
            
        } else {
            disabledNombresFiltros = false;
        }
    }

    public int getTipoDocu() {
        return tipoDocu;
    }

    public void setTipoDocu(int tipoDocu) {
        this.tipoDocu = tipoDocu;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    protected FacesContext getFacesContext() {
        return facesContext;
    }

    @Override
    protected AbstractSevice getService() {
        return clienteService;
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

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public List<Distrito> getDistritoList() {
        return distritoList;
    }

    public void setDistritoList(List<Distrito> distritoList) {
        this.distritoList = distritoList;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public DepartamentoService getDepartamentoService() {
        return departamentoService;
    }

    public void setDepartamentoService(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    public DistritoService getDistritoService() {
        return distritoService;
    }

    public void setDistritoService(DistritoService distritoService) {
        this.distritoService = distritoService;
    }

    public List<ClienteDireccion> getClienteDireccionList() {
        return clienteDireccionList;
    }

    public void setClienteDireccionList(List<ClienteDireccion> clienteDireccionList) {
        this.clienteDireccionList = clienteDireccionList;
    }

    public boolean isDisabledNombres() {
        return disabledNombres;
    }

    public void setDisabledNombres(boolean disabledNombres) {
        this.disabledNombres = disabledNombres;
    }

}
