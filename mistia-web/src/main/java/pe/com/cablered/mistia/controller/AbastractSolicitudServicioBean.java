package pe.com.cablered.mistia.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.ClienteDireccion;

public class AbastractSolicitudServicioBean {

    final static Logger logger = Logger.getLogger(AbastractSolicitudServicioBean.class);

    protected Cliente cliente;
    protected ClienteDireccion clienteDireccion;
    protected List<Cliente> clienteList;
    protected List<ClienteDireccion> clienteDireccionList;

    public List<String> completeText(String query) {
        return buscarCliente(query);
    }

    private List<String> buscarCliente(String criterio) {

        logger.info(" metodo :  buscarCliente ##### ");
        logger.info(" citerio :" + criterio);
        logger.info(" cant :  " + clienteList.size());
        List<String> filtro = new ArrayList();

        for (Cliente cliente : clienteList) {

            if (cliente.getTipoDocumento().getCodigoTipo() == 11) {

                String nombre = cliente.getNombreRazonSocial();
                if (nombre.toUpperCase().contains(criterio.trim().toUpperCase())) {
                    filtro.add(nombre);
                }
            } else {
                String nombre = cliente.getApellidos() + " " + cliente.getNombres();
                if (nombre.toUpperCase().contains(criterio.trim().toUpperCase())) {
                    filtro.add(cliente.getApellidos() + " " + cliente.getNombres());
                }

            }

        }
        return filtro;
    }

    public List<String> completeDireccionesText(String query) {
        return buscarDireccion(query);
    }

    private List<String> buscarDireccion(String criterio) {
        logger.info(" metodo :  buscarDireccion ");
        logger.info(" citerio :" + criterio);
        List<String> filtro = new ArrayList();
        if (clienteDireccionList != null) {
            for (ClienteDireccion cd : clienteDireccionList) {
                String nombre = cd.getDireccion();
                if (nombre.toUpperCase().contains(criterio.trim().toUpperCase())) {
                    filtro.add(cd.getDireccion());
                    clienteDireccion = cd;
                }
            }
        }
        return filtro;
    }

    protected Integer getCodigoCliente(String nombreCliente) {
        
        logger.info(" metodo :  getCodigoCliente " + nombreCliente);
        Integer codigoClient = null;
        if (nombreCliente != null) {
            for (Cliente cliente : clienteList) {

                if (cliente.getTipoDocumento().getCodigoTipo() == 11) {
                    String nomclie = cliente.getNombreRazonSocial().trim();
                    if (nomclie.trim().equals(nombreCliente.trim())) {
                        codigoClient = cliente.getCodigoCliente();
                        this.cliente = cliente;
                        break;
                    }

                } else {
                    String nomclie = cliente.getApellidos().trim() + " " + cliente.getNombres().trim();
                    if (nomclie.trim().equals(nombreCliente.trim())) {
                        codigoClient = cliente.getCodigoCliente();
                        this.cliente = cliente;
                        break;
                    }
                }

            }
        }

        return codigoClient;
    }

    protected void cargarDirecciones() {
    }

}
