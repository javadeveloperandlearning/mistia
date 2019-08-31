package pe.com.cablered.mistia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.ContratoServicio;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Poste;
import pe.com.cablered.mistia.model.Provincia;
import pe.com.cablered.mistia.model.Servicio;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;
import pe.com.cablered.mistia.model.Tipo;
import pe.com.cablered.mistia.model.TipoSolicitud;
import pe.com.cablered.mistia.service.ClienteService;
import pe.com.cablered.mistia.service.ContratoServicioService;
import pe.com.cablered.mistia.service.DistritoService;
import pe.com.cablered.mistia.service.HorarioAtencionDetalleService;
import pe.com.cablered.mistia.service.ProvinciaService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.ServicioService;
import pe.com.cablered.mistia.service.SolicitudServicioService;
import pe.com.cablered.mistia.service.TipoService;
import pe.com.cablered.mistia.service.TipoSolicitudService;
import pe.com.cablered.mistia.util.ConstantBusiness;

import static pe.com.cablered.mistia.util.ConstantBusiness.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import pe.com.cablered.mistia.model.ClienteDireccion;
import pe.com.cablered.mistia.model.SolicitudServicioDetalle;

@ManagedBean(name = "soliservgene")
@SessionScoped
public class SolicitudServicioGenerarController extends AbastractSolicitudServicioBean implements Serializable {

    private List<Map> horarioList;
    private List<Provincia> provinciaList;
    private List<Distrito> distritoList;
    private List<Tipo> tipoDomicilioList;
    private List<Tipo> tipoPaqueteList;
    private List<Tipo> tipoVelocidadInternetList;
    private List<Map> mpcontratoList;
    private List<Map> cantTelevisorList;
    private List<TipoSolicitud> tipoSolicitudList;
    private List<ContratoServicio> contratoServicioList;
    private List<SolicitudServicioHorarioAtencion> solicitudHorarioList;

    private List<SolicitudServicioDetalle> solicitudServicioDetalleList;
    private List<Servicio> servicioList;
    private String numeroContrato;
    private Integer codigoProvincia;
    private Integer codigoDisrito;
    private Integer codigoTipoDomicilio;
    private Integer codigoTipoPaquete;
    private Integer codigoTipoVeloInternet;
    private Integer codigoTipoSolicitud;
    private Integer codigoCantTele;
    private Integer codigoCliente;
    private String nombreCliente;
    private String nombreServicio;
    private String direccion;
    private String nroDomicilio;
    private String dptoIntDomicilio;
    private String referencia;
    private String urbanizacion;
    private Double tarifaMensual;
    private Double tarifaSolicitud;
    private Integer cantServicio;
    private SolicitudServicio solicitudServicio;
    private String direccionSelected;
    private String posicionCobertura;

    private int accion;

    private boolean disabled;
    private boolean disabled2;
    private boolean checktodos;

    private String labelActualizar;

    final static Logger logger = Logger.getLogger(SolicitudServicioGenerarController.class);

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Inject
    private SolicitudServicioService solicitudServicioService;

    @Inject
    private DistritoService distritoService;

    @Inject
    private TipoSolicitudService tipoSolicitudService;

    @Inject
    private ProvinciaService provinciaService;

    @Inject
    private TipoService tipoService;

    @Inject
    private ContratoServicioService contratoServicioService;

    @Inject
    private ClienteService clienteService;

    @Inject
    private HorarioAtencionDetalleService horarioAtencionDetalleService;

    @Inject
    private ServicioService servicioService;

    @Inject
    private FacesContext facesContext;
    
    

    @PostConstruct
    public void init() {

        limpiar();

    }

    public void cargarDirecciones() {

        this.codigoCliente = getCodigoCliente(nombreCliente);
        this.clienteDireccionList = clienteService.getClienteDireccionList(cliente);
        direccionSelected = "";
    }

    private void mostrarEditar(SolicitudServicio solicitudServicio) {

        try {

            this.solicitudServicio = solicitudServicio;
            cliente = solicitudServicio.getCliente();
            nombreCliente = cliente.getApellidos() + " " + cliente.getNombres();
            codigoDisrito = solicitudServicio.getDistrito().getCodigoDistrito();

            codigoTipoSolicitud = solicitudServicio.getTipoSolicitud().getCodigoTipoSolicitud();
            codigoCliente = getCodigoCliente(nombreCliente);
            direccion = solicitudServicio.getDireccion();

            codigoTipoDomicilio = solicitudServicio.getCodigoTipoDomicilio();

            solicitudHorarioList = solicitudServicioService.getListSolicitudServicioHorarioAtencion(solicitudServicio.getNumeroSolicitud());

            for (SolicitudServicioHorarioAtencion sha : solicitudHorarioList) {

                for (Map map : horarioList) {

                    Map<String, Boolean> checkdias1 = (Map) map.get("checkdias1");
                    String horainicio = (String) map.get("horainicio");
                    String horafin = (String) map.get("horafin");

                    if (sha.getHoraInicio().equals(horainicio) && sha.getHoraFin().equals(horafin)) {

                        Set<String> dias = checkdias1.keySet();
                        for (String dia : dias) {
                            Integer _dia = Integer.parseInt(dia, 10);
                            if (_dia == sha.getNumeroDia()) {
                                checkdias1.put(dia, true);
                            }
                        }
                    }

                }

            }

            solicitudServicioDetalleList = solicitudServicioService.getListSolicitudServicioDetalle(solicitudServicio.getNumeroSolicitud());

            clienteDireccionList = clienteService.getClienteDireccionList(solicitudServicio.getCliente());

            this.direccionSelected = solicitudServicio.getDireccion();
            logger.info(" solicitudServicio.getDistrito().getProvincia().getCodigoProvincia() :  " + solicitudServicio.getDistrito().getProvincia().getCodigoProvincia());

            codigoProvincia = solicitudServicio.getDistrito().getProvincia().getCodigoProvincia();

            distritoList = distritoService.getDistritoList(codigoProvincia);

            codigoDisrito = solicitudServicio.getDistrito().getCodigoDistrito();

            referencia = solicitudServicio.getReferencia();

            accion = ACCION_EDITA;
            labelActualizar = "Guardar";

            calcularCosto();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void actualizarContratoList() {

        mpcontratoList = new ArrayList<>();
        if (codigoTipoSolicitud != null && codigoTipoSolicitud == TIPO_SOLICITUD_INSTALACION) {

            this.codigoCliente = getCodigoCliente(nombreCliente);
            Map<String, Object> mp = new HashMap();
            mp.put("numeroContrato", "Nuevo");
            mpcontratoList.add(mp);
            setDisabled(true);

            nroDomicilio = "";
            dptoIntDomicilio = "";
            referencia = "";
            urbanizacion = "";
            // tipo docimicilio
            setDisabled2(false);
            setDisabled(true);

        } else {

            this.codigoCliente = getCodigoCliente(nombreCliente);
            this.contratoServicioList = contratoServicioService.getContratoServicioList(codigoCliente);

            if (contratoServicioList != null && contratoServicioList.size() > 1) {
                Map<String, Object> mp = new HashMap();
                mp.put("numeroContrato", DES_SELECCIONE);
                mpcontratoList.add(mp);
            } else if (contratoServicioList != null && contratoServicioList.size() == 1) {

                this.numeroContrato = contratoServicioList.get(0).getNumeroContrato() + "";
                cargardetallecontrato();
            }

            for (ContratoServicio c : contratoServicioList) {
                Map<String, Object> mp = new HashMap();
                mp.put("numeroContrato", c.getNumeroContrato() + "");
                mpcontratoList.add(mp);
            }
            setDisabled(false);
            setDisabled2(true);

        }

    }

    public void cargardetallecontrato() {
        Long _numeroContrato = null;
        try {
            _numeroContrato = Long.parseLong(this.numeroContrato);
        } catch (Exception e) {
        }
        if (this.numeroContrato == null) {
            return;
        }

        int idx = contratoServicioList.indexOf(new ContratoServicio(_numeroContrato));

        if (idx != -1) {
            ContratoServicio c = contratoServicioList.get(idx);

            this.provinciaList = new ArrayList<>();
            this.provinciaList.add(c.getDistrito().getProvincia());

            this.distritoList = new ArrayList<>();
            this.distritoList.add(c.getDistrito());
            this.direccion = c.getDireccion();
            this.codigoTipoDomicilio = c.getCodigoTipoDomicilio();
            this.nroDomicilio = c.getNroDomicilio();
            this.dptoIntDomicilio = c.getDptoIntDomicilio();
            this.referencia = c.getReferencia();
            this.urbanizacion = c.getUrbanizacion();

        }
    }

    public void actualizarDireccionFisica() {

        logger.info(" metodo :  actualizarDireccionFisica");
        logger.info(" nombreCliente :  " + nombreCliente);
        logger.info(" ##### direccionSelected : " + direccionSelected);
        clienteDireccion = buscarDireccion(direccionSelected);

        if (cliente != null && clienteDireccion != null) {

            /*
            direccion = cliente.getDireccion();
            codigoDisrito = cliente.getCodigoDistrito();
             */
            direccion = clienteDireccion.getDireccion();
            referencia = clienteDireccion.getReferencia();
            codigoDisrito = clienteDireccion.getDistrito().getCodigoDistrito();

            logger.info(" distrito  : " + codigoDisrito);
            logger.info(" direccion : " + direccion);

            Distrito distrito = distritoService.getDistrito(codigoDisrito);
            if (distrito != null) {

                logger.info(distrito.toString());
                Provincia provincia = distrito.getProvincia();
                codigoProvincia = provincia.getCodigoProvincia();
                logger.info(" codigoProvincia :" + codigoProvincia);
                logger.info(" añadiendo consulta ....");
                provinciaList = provinciaService.getProvinciaList(COD_DEPA_HUANCAYO);
                this.distritoList.clear();
                this.distritoList.addAll(distritoService.getDistritoList(codigoProvincia));

            }

        }

    }

    private ClienteDireccion buscarDireccion(String direccion) {

        ClienteDireccion clienteDireccion = null;

        for (ClienteDireccion cd : this.clienteDireccionList) {
            if (cd.getDireccion().equals(direccion)) {
                clienteDireccion = cd;
                break;
            }
        }

        return clienteDireccion;

    }

    public void cargarTipoSolicitServicio() {

        if (nombreCliente != null || nombreCliente != null && nombreCliente.trim().equals("")) {
            this.codigoTipoSolicitud = CODIGO_SELECCIONE;
            this.tipoSolicitudList = tipoSolicitudService.getTipoSolicitudList();

        } else {
            this.tipoSolicitudList.clear();

        }

    }

    public void cargarDistritos() {
        if (codigoProvincia != null) {
            this.distritoList = new ArrayList<>();
            this.distritoList.add(new Distrito(CODIGO_SELECCIONE, DES_SELECCIONE));
            this.distritoList.addAll(distritoService.getDistritoList(codigoProvincia));
        }
    }

    public boolean isCheckHorario(Integer dia, Integer nse) {
        logger.info(" metodo : checkedHorario ");
        boolean ischecked = false;
        logger.info(" checkHorario  dia :" + dia + " rango:" + nse);
        logger.info(" horarioList :" + horarioList.size());
        for (Map mp : horarioList) {
            Integer _nse = (Integer) mp.get("nse");
            if (_nse != null && _nse.equals(nse)) {
                logger.info(mp.toString());
                if (mp.get("checkdias") != null) {
                    LinkedHashMap<Integer, Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
                    Set<Integer> keys = checkdias.keySet();
                    for (Integer _dia : keys) {
                        Boolean check = checkdias.get(_dia);
                        if (check != null && check == true && dia == _dia) {
                            ischecked = true;
                            return true;

                        } else if (check != null && check == false && dia == _dia) {
                            ischecked = false;
                            return false;

                        }
                    }
                }
            }
        }
        return ischecked;
    }

    public boolean existChecksHorario() {
        logger.info(" metodo : existChecksHorario ");
        boolean existe = false;
        for (Map mp : horarioList) {
            if (mp.get("checkdias") != null) {
                LinkedHashMap<Integer, Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
                Set<Integer> keys = checkdias.keySet();
                for (Integer _dia : keys) {
                    Boolean check = checkdias.get(_dia);
                    if (check != null) {
                        existe = true;
                        break;
                    }
                }
            }
        }
        return existe;
    }

    public void setCheckHorario(Integer dia, Integer nse) {
        logger.info(" checkHorario  dia :" + dia + " rango:" + nse);
        Map mphorario = null;
        for (Map mp : horarioList) {
            Integer _nse = (Integer) mp.get("nse");
            if (_nse != null && _nse.equals(nse)) {
                logger.info(mp.toString());
                if (mp.get("checkdias") != null) {
                    LinkedHashMap<Integer, Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
                    Set<Integer> keys = checkdias.keySet();
                    for (Integer _dia : keys) {
                        Boolean check = checkdias.get(_dia);
                        if (check != null && check == true && dia == _dia) {
                            logger.info(" chekando true");
                            checkdias.put(_dia, false);
                            break;
                        } else if (check != null && check == false && dia == _dia) {
                            logger.info(" chekando true");
                            checkdias.put(_dia, true);
                            break;
                        }
                    }
                }
            }
        }

        logger.info(" PLOT HORARIO LIST ");
        for (Map mp : horarioList) {
            logger.info(mp.toString());
        }

        logger.info(" ### RESUMEN ### ");
        for (Map mp : horarioList) {
            if (mp.get("checkdias") != null) {
                LinkedHashMap<Integer, Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
                Set<Integer> keys = checkdias.keySet();
                for (Integer _dia : keys) {
                    Boolean check = checkdias.get(_dia);
                    if (check != null && check) {
                        logger.info(" dia: " + _dia + " rango: " + mp.get("rango") + " , " + check);
                    }
                }
            }
        }

        if (solicitudHorarioList != null) {
        }
    }

    public void checkear(Integer dia, Integer nse) {
        logger.info(" checkHorario  dia :" + dia + " rango:" + nse);
        Map mphorario = null;
        for (Map mp : horarioList) {
            Integer _nse = (Integer) mp.get("nse");
            if (_nse != null && _nse.equals(nse)) {
                logger.info(mp.toString());
                if (mp.get("checkdias") != null) {
                    LinkedHashMap<Integer, Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
                    Set<Integer> keys = checkdias.keySet();
                    for (Integer _dia : keys) {
                        Boolean check = checkdias.get(_dia);
                        if (check != null && check == true && dia == _dia) {
                            logger.info(" chekando true");
                            checkdias.put(_dia, false);
                            break;
                        } else if (check != null && check == false && dia == _dia) {
                            logger.info(" chekando true");
                            checkdias.put(_dia, true);
                            break;
                        }
                    }
                }
            }
        }

        logger.info(" PLOT HORARIO LIST ");
        for (Map mp : horarioList) {
            logger.info(mp.toString());
        }

        logger.info(" ### RESUMEN ### ");
        for (Map mp : horarioList) {
            if (mp.get("checkdias") != null) {
                LinkedHashMap<Integer, Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
                Set<Integer> keys = checkdias.keySet();
                for (Integer _dia : keys) {
                    Boolean check = checkdias.get(_dia);
                    if (check != null && check) {
                        logger.info(" dia: " + _dia + " rango: " + mp.get("rango") + " , " + check);
                    }
                }
            }
        }

        if (solicitudHorarioList != null) {
        }
    }

    public void checkearTodos() {

        logger.info("metodo : checkearTodos " + checktodos);
        for (Map mp : horarioList) {
            Map<String, Boolean> checkdias1 = (Map<String, Boolean>) mp.get("checkdias1");
            for (int i = 1; i <= 7; i++) {
                checkdias1.put(i + "", checktodos);
            }
        }

    }

    
    public void addListaServicios() {

        try {
            logger.info(" metodo : addListaServicios ");

            Servicio servicio1 = servicioService.getServicioPorPaquete(codigoTipoPaquete, codigoCantTele);
            Servicio servicio2 = servicioService.getServicioPorVelocidad(codigoTipoVeloInternet);

            servicioList = new ArrayList<>();
            if (servicio1 != null) {
                servicioList.add(servicio1);
            }

            if (servicio2 != null) {
                servicioList.add(servicio2);
            }

            tarifaMensual = 0.0;
            if (tipoSolicitudList != null) {
                tarifaSolicitud = tipoSolicitudList.get(codigoTipoSolicitud).getTarifa();
            }
            if (servicioList != null) {
                for (Servicio s : servicioList) {
                    tarifaMensual = tarifaMensual + s.getTarifa();
                    logger.info(" s :_" + s.getDescripcion() + " velocida " + s.getVeloInterMbps() + "  cant tele : " + s.getCantidadTelevisor());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void registrar() {
        logger.info(" metodo :  registrar");
        logger.info(" codigoCliente : " + codigoCliente);
        logger.info(" codigoTipoSolicitud : " + codigoTipoSolicitud);

        try {

            FacesMessage msg = new FacesMessage("");

            if (codigoCliente == null) {
                msg = new FacesMessage("Debe seleccionar un cliente");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
                return;
            }

            if (codigoTipoSolicitud != null && codigoTipoSolicitud == CODIGO_SELECCIONE) {
                msg = new FacesMessage("Debe seleccionar un tipo de solicitud de servicio");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
                return;
            }

            if (codigoProvincia != null && codigoProvincia == CODIGO_SELECCIONE) {
                msg = new FacesMessage("Debe seleccionar una provincia");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
            }

            if (codigoDisrito != null && codigoDisrito == CODIGO_SELECCIONE) {
                msg = new FacesMessage("Debe seleccionar una provincia");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
            }

            if (codigoTipoDomicilio != null && codigoTipoDomicilio == CODIGO_SELECCIONE) {
                msg = new FacesMessage("Debe seleccionar un tipo de domicilio");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
            }

            if (direccion == null || (direccion != null && direccion.trim().equals(""))) {
                msg = new FacesMessage("Debe seleccionar un cliente");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
                return;
            }

            if (!existChecksHorario()) {
                msg = new FacesMessage("Debe especificar el horario de antención");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
                return;
            }

            if (solicitudServicioDetalleList != null && solicitudServicioDetalleList.isEmpty()) {
                msg = new FacesMessage("Debe seleccionar por lo menos un servicio");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
                return;
            }

            //if (codigoTipoSolicitud != null && codigoTipoSolicitud == TIPO_SOLICITUD_INSTALACION) {
            Cliente cliente = null;
            if (codigoCliente != null) {
                int idx = clienteList.indexOf(new Cliente(codigoCliente));
                logger.info(" idx :" + idx);
                if (idx != -1) {
                    cliente = clienteList.get(idx);
                }
            }

            if (cliente == null) {
                logger.info("se debe registrar un cliente ");
                return;
            } else {
                logger.info("cliente seleccionado : " + cliente.getApellidos());

            }

          
            Map<String, Object> mpsession = facesContext.getExternalContext().getSessionMap();

            solicitudServicio.setCliente(cliente);
            solicitudServicio.setTipoSolicitud(new TipoSolicitud(codigoTipoSolicitud));
            solicitudServicio.setDistrito(new Distrito(codigoDisrito));

            Poste poste = (Poste) mpsession.get("posteCobertura"); // poste mas cercano obtenido de la cpbertura del servicio
            Map<String, Double> mpPuntoServicio = (Map<String, Double>) (Poste) mpsession.get("mpPuntoServicio"); // poste mas cercano obtenido de la cpbertura del servicio

            if (mpPuntoServicio != null) {

                Double latitud = mpPuntoServicio.get("latitud");
                Double longitud = mpPuntoServicio.get("longitud");
                solicitudServicio.setPoste(poste);
                solicitudServicio.setLatitud(latitud);
                solicitudServicio.setLongitud(longitud);

            } else {

                Poste poste1 = new Poste(70);
                solicitudServicio.setPoste(poste1);

            }
            solicitudServicio.setTarifaAtencion(tarifaSolicitud);
            solicitudServicio.setFechaSolicitud(new Date());
            solicitudServicio.setFechaAtencion(new Date());
            solicitudServicio.setDireccion(direccion);
            solicitudServicio.setCodigoTipoDomicilio(codigoTipoDomicilio);
            solicitudServicio.setNroDomicilio(nroDomicilio);
            solicitudServicio.setDptoIntDomicilio(dptoIntDomicilio);
            solicitudServicio.setReferencia(referencia);
            solicitudServicio.setUrbanizacion(urbanizacion);

            List<SolicitudServicioHorarioAtencion> soliHoraList = new ArrayList<>();

            for (Map mp : horarioList) {
                if (mp.get("checkdias1") != null) {
                    LinkedHashMap<String, Boolean> checkdias = (LinkedHashMap<String, Boolean>) mp.get("checkdias1");
                    Set<String> keys = checkdias.keySet();

                    for (String _dia : keys) {
                        Boolean check = checkdias.get(_dia);

                        if (check != null && check) {

                            logger.info(" dia: " + _dia + " rango: " + mp.get("rango") + " , " + check);
                            String horaInicio = (String) mp.get("horainicio");
                            String horaFin = (String) mp.get("horafin");
                            soliHoraList.add(new SolicitudServicioHorarioAtencion(Integer.parseInt(_dia, 10), horaInicio, horaFin));

                        }
                    }
                }
            }

            if (soliHoraList.isEmpty()) {
                msg = new FacesMessage("Debe seleccionar por los menos un día para el horario de atención");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, msg);
                return;
            }

            solicitudServicio.setSolicitudServicioHorarioAtencionList(soliHoraList);
            solicitudServicio.setSolicitudServicioDetalleList(solicitudServicioDetalleList);
            Response res2 = null;

            if (accion == ACCION_NUEVO) {

                res2 = solicitudServicioService.insertar(solicitudServicio);

            } else {

                res2 = solicitudServicioService.actualizar(solicitudServicio);

            }

            if (res2.getCodigo() == Response.OK) {

                msg = new FacesMessage("La solicitud de servicio se registró exitosamente");
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                facesContext.addMessage(null, msg);

                salir();
            }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void salir() {
        try {
            ExternalContext ec = facesContext.getExternalContext();
            ec.redirect(ec.getRequestContextPath() + ConstansView.SOLICITUD_SERVICIO_CONSULTA_VIEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void limpiar() {

        logger.info(" metodo :  limpiar ");
        //this.horarioList =   solicitudServicioService.getHorarioAtencionList();
        this.horarioList = horarioAtencionDetalleService.getHorarioAtencionList(1);

        this.solicitudHorarioList = new ArrayList<>();
        //this.provinciaList = provinciaService.getProvinciaList(COD_DEPA_HUANCAYO);
        this.provinciaList = Collections.EMPTY_LIST;
        this.distritoList = Collections.EMPTY_LIST;

        this.tipoPaqueteList = tipoService.getTipoList(COD_GRUPO_TIPO_PAQ_CABLE);
        this.tipoVelocidadInternetList = tipoService.getTipoList(COD_GRUPO_TIPO_VELO_INTER);
        this.cantTelevisorList = solicitudServicioService.getCantidadTelevisoresList();
        this.mpcontratoList = Collections.EMPTY_LIST;
        this.tipoSolicitudList = Collections.EMPTY_LIST;
        this.accion = ACCION_NUEVO;
        labelActualizar = "Registrar";
        this.clienteList = clienteService.clienteList();
        servicioList = servicioService.getServicioList();

        numeroContrato = null;
        codigoProvincia = null;
        codigoDisrito = null;
        //codigoTipoDomicilio= null;
        codigoTipoPaquete = null;
        codigoTipoVeloInternet = null;
        codigoTipoSolicitud = null;
        codigoCantTele = null;
        codigoCliente = null;
        nombreCliente = null;
        direccion = null;
        disabled = false;
        disabled2 = false;

        // provincias
        provinciaList = new ArrayList<>();
        provinciaList.add(new Provincia(CODIGO_SELECCIONE, DES_SELECCIONE));
        this.provinciaList.addAll(provinciaService.getProvinciaList(COD_DEPA_HUANCAYO));
        // distritos

        distritoList = new ArrayList<>();
        distritoList.add(new Distrito(CODIGO_SELECCIONE, DES_SELECCIONE));
        solicitudServicio = new SolicitudServicio();

        solicitudServicioDetalleList = new ArrayList<>();
        cantServicio = 1;

        this.codigoTipoSolicitud = CODIGO_SELECCIONE;
        this.tipoSolicitudList = tipoSolicitudService.getTipoSolicitudList();

        this.tipoDomicilioList = new ArrayList<>();
        this.tipoDomicilioList.add(new Tipo(CODIGO_SELECCIONE, DES_SELECCIONE));
        this.tipoDomicilioList.addAll(tipoService.getTipoList(COD_GRUPO_TIPO_DOMICILIO));

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        Long numerosolicitudedit = (Long) session.getAttribute("numerosolicitudedit");

        this.tarifaSolicitud = 0.0;
        this.tarifaMensual = 0.0;

        if (numerosolicitudedit != null) {
            SolicitudServicio solicitudServicio = solicitudServicioService.getSolicitudServicio1(numerosolicitudedit);
            mostrarEditar(solicitudServicio);
        }
        logger.info("numerosolicitudedit :" + numerosolicitudedit);

        session.setAttribute("mpPuntoServicio", null);
        posicionCobertura = null;

    }

    public List<String> completeServicioText(String query) {
        return buscarServicio(query);
    }

    private List<String> buscarServicio(String criterio) {

        logger.info("metodo : buscarServicio " + criterio);
        List<String> filtro = new ArrayList();
        for (Servicio servicio : servicioList) {
            String nombre = servicio.getDescripcion();
            if (nombre.toUpperCase().contains(criterio.trim().toUpperCase())) {
                filtro.add(servicio.getDescripcion());

            }
        }

        logger.info(" filtro " + filtro.size());
        return filtro;
    }

    public void agregarServicio() {

        logger.info(" metodo : agregarServicio");

        boolean existe = false;
        for (SolicitudServicioDetalle detalle : solicitudServicioDetalleList) {
            if (nombreServicio != null) {
                if (detalle.getServicio().getDescripcion().trim().equals(nombreServicio.trim())) {
                    existe = true;
                    logger.info(" el " + nombreServicio + " existe");
                    break;
                }
            }
        }

        if (existe) {

            FacesMessage msg = new FacesMessage("El servicio ya fue seleccionado");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, msg);
            return;

        }

        Servicio _servicio = null;
        for (Servicio servicio : servicioList) {
            if (nombreServicio != null) {
                if (servicio.getDescripcion().trim().contains(nombreServicio.trim())) {
                    _servicio = servicio;
                    break;
                }
            }
        }

        if (_servicio != null) {
            for (SolicitudServicioDetalle detalle : solicitudServicioDetalleList) {
                if (detalle.getServicio().getTipoServicio().equals(_servicio.getTipoServicio())) {

                    String desTipoServicio = "";
                    if (_servicio.getTipoServicio() == COD_TIPO_SERV_INTERNET) {
                        desTipoServicio = DES_TIPO_SERV_INTERNET;
                    } else if (_servicio.getTipoServicio() == COD_TIPO_SERV_CABLE) {
                        desTipoServicio = DES_TIPO_SERV_CABLE;
                    }
                    String mensaje = "Solo puede seleccionar un solo tipo de servicio de :" + DES_TIPO_SERV_CABLE;
                    FacesMessage msg = new FacesMessage(mensaje);
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    facesContext.addMessage(null, msg);
                    return;
                }
            }
        }

        for (Servicio servicio : servicioList) {
            if (nombreServicio != null) {
                if (servicio.getDescripcion().trim().contains(nombreServicio.trim())) {
                    SolicitudServicioDetalle detalle = new SolicitudServicioDetalle(0, servicio.getCodigoServicio());
                    detalle.setServicio(servicio);
                    detalle.setCantidad(1);
                    detalle.setCantTelevisor(servicio.getCantidadTelevisor());
                    detalle.setTarifa(servicio.getTarifa());
                    logger.info(" tarifa del servicio :" + servicio.getTarifa());
                    logger.info(servicio.toString());
                    solicitudServicioDetalleList.add(detalle);
                }
            }
        }

        calcularCosto();
    }

    public void actualizarTelevisor(SolicitudServicioDetalle detalle) {
        if (detalle.getCantTelevisor() != null && detalle.getCantTelevisor() > 0) {
            for (SolicitudServicioDetalle _detalle : solicitudServicioDetalleList) {
                if (_detalle.getServicio().getCodigoServicio() == detalle.getServicio().getCodigoServicio()) {
                    _detalle.setCantTelevisor(detalle.getCantTelevisor());
                    _detalle.setCantidad(detalle.getCantTelevisor());
                    calcularCosto();
                    break;
                }
            }

        }
    }

    public void eliminarServicio(Servicio servicio) {
        logger.info(" metodo : eliminarServicio");
        SolicitudServicioDetalle _detalle = null;
        for (SolicitudServicioDetalle detalle : solicitudServicioDetalleList) {
            if (detalle.getServicio().equals(servicio)) {
                _detalle = detalle;
            }
        }
        if (_detalle != null) {
            solicitudServicioDetalleList.remove(_detalle);
            calcularCosto();
        }
    }

    private void calcularCosto() {
        logger.info("metodo :  calcularCostoss");
        double total = 0.0;
        if (solicitudServicioDetalleList != null && !solicitudServicioDetalleList.isEmpty()) {
            for (SolicitudServicioDetalle detalle : solicitudServicioDetalleList) {

                total = total + (detalle.getTarifa() * detalle.getCantidad());
            }
        }
        logger.info(" total  :" + total);
        this.tarifaMensual = total;
        this.tarifaSolicitud = total;

        logger.info("tarifaMensual   :" + this.tarifaMensual);
        logger.info("tarifaSolicitud :" + this.tarifaSolicitud);
    }

    public List<Tipo> getTipoPaqueteList() {
        return tipoPaqueteList;
    }

    public void setTipoPaqueteList(List<Tipo> tipoPaqueteList) {
        this.tipoPaqueteList = tipoPaqueteList;
    }

    public List<Tipo> getTipoVelocidadInternetList() {
        return tipoVelocidadInternetList;
    }

    public void setTipoVelocidadInternetList(List<Tipo> tipoVelocidadInternetList) {
        this.tipoVelocidadInternetList = tipoVelocidadInternetList;
    }

    public List<Map> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Map> horarioList) {
        this.horarioList = horarioList;
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

    public List<Tipo> getTipoDomicilioList() {
        return tipoDomicilioList;
    }

    public void setTipoDomicilioList(List<Tipo> tipoDomicilioList) {
        this.tipoDomicilioList = tipoDomicilioList;
    }

    public List<Map> getMpcontratoList() {
        return mpcontratoList;
    }

    public void setMpcontratoList(List<Map> mpcontratoList) {
        this.mpcontratoList = mpcontratoList;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
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

    public Integer getCodigoTipoDomicilio() {
        return codigoTipoDomicilio;
    }

    public void setCodigoTipoDomicilio(Integer codigoTipoDomicilio) {
        this.codigoTipoDomicilio = codigoTipoDomicilio;
    }

    public Integer getCodigoTipoPaquete() {
        return codigoTipoPaquete;
    }

    public void setCodigoTipoPaquete(Integer codigoTipoPaquete) {
        this.codigoTipoPaquete = codigoTipoPaquete;
    }

    public Integer getCodigoTipoVeloInternet() {
        return codigoTipoVeloInternet;
    }

    public void setCodigoTipoVeloInternet(Integer codigoTipoVeloInternet) {
        this.codigoTipoVeloInternet = codigoTipoVeloInternet;
    }

    public List<Map> getCantTelevisorList() {
        return cantTelevisorList;
    }

    public void setCantTelevisorList(List<Map> cantTelevisorList) {
        this.cantTelevisorList = cantTelevisorList;
    }

    public Integer getCodigoTipoSolicitud() {
        return codigoTipoSolicitud;
    }

    public void setCodigoTipoSolicitud(Integer codigoTipoSolicitud) {
        this.codigoTipoSolicitud = codigoTipoSolicitud;
    }

    public List<TipoSolicitud> getTipoSolicitudList() {
        return tipoSolicitudList;
    }

    public void setTipoSolicitudList(List<TipoSolicitud> tipoSolicitudList) {
        this.tipoSolicitudList = tipoSolicitudList;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<ContratoServicio> getContratoServicioList() {
        return contratoServicioList;
    }

    public void setContratoServicioList(List<ContratoServicio> contratoServicioList) {
        this.contratoServicioList = contratoServicioList;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCodigoCantTele() {
        return codigoCantTele;
    }

    public void setCodigoCantTele(Integer codigoCantTele) {
        this.codigoCantTele = codigoCantTele;
    }

    public String getNroDomicilio() {
        return nroDomicilio;
    }

    public void setNroDomicilio(String nroDomicilio) {
        this.nroDomicilio = nroDomicilio;
    }

    public String getDptoIntDomicilio() {
        return dptoIntDomicilio;
    }

    public void setDptoIntDomicilio(String dptoIntDomicilio) {
        this.dptoIntDomicilio = dptoIntDomicilio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public boolean isDisabled2() {
        return disabled2;
    }

    public void setDisabled2(boolean disabled2) {
        this.disabled2 = disabled2;
    }

    public Double getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(Double tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public Double getTarifaSolicitud() {
        return tarifaSolicitud;
    }

    public void setTarifaSolicitud(Double tarifaSolicitud) {
        this.tarifaSolicitud = tarifaSolicitud;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public List<SolicitudServicioDetalle> getSolicitudServicioDetalleList() {
        return solicitudServicioDetalleList;
    }

    public void setSolicitudServicioDetalleList(List<SolicitudServicioDetalle> solicitudServicioDetalleList) {
        this.solicitudServicioDetalleList = solicitudServicioDetalleList;
    }

    public void sendCobertura() {
        logger.info("metodo: generar");
        try {

            ExternalContext ec = facesContext.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ec.getRequest();
            ec.redirect(ec.getRequestContextPath() + ConstansView.COBERTURA_TECNICA);
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public Integer getCantServicio() {
        return cantServicio;
    }

    public void setCantServicio(Integer cantServicio) {
        this.cantServicio = cantServicio;
    }

    public SolicitudServicio getSolicitudServicio() {
        return solicitudServicio;
    }

    public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
        this.solicitudServicio = solicitudServicio;
    }

    private boolean booleanFlag = true;

    public boolean isBooleanFlag() {
        return booleanFlag;
    }

    public void setBooleanFlag(boolean value) {
        this.booleanFlag = value;
    }

    public String getDireccionSelected() {
        return direccionSelected;
    }

    public void setDireccionSelected(String direccionSelected) {
        this.direccionSelected = direccionSelected;
    }

    public boolean isChecktodos() {
        return checktodos;
    }

    public void setChecktodos(boolean checktodos) {
        this.checktodos = checktodos;
    }

    public String getLabelActualizar() {
        return labelActualizar;
    }

    public void setLabelActualizar(String labelActualizar) {
        this.labelActualizar = labelActualizar;
    }

    public String getPosicionCobertura() {

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Map<String, Double> mpPuntoServicio = (Map<String, Double>) session.getAttribute("mpPuntoServicio");
        if (mpPuntoServicio != null) {
            Double latitud = mpPuntoServicio.get("latitud");
            Double longitud = mpPuntoServicio.get("longitud");
            posicionCobertura = "(latitud, longitud)" + latitud + ", " + longitud;
        } else {
            posicionCobertura = null;
        }

        return posicionCobertura;
    }

    public void setPosicionCobertura(String posicionCobertura) {

        this.posicionCobertura = posicionCobertura;
    }

}
