package pe.com.cablered.mistia.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.CuadrillaDao;
import pe.com.cablered.mistia.dao.CuadrillasDetalleDao;
import pe.com.cablered.mistia.excepcion.BusinessException;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.CuadrillasDetalle;
import pe.com.cablered.mistia.model.ObjectBean;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.Vehiculo;

@Stateless
public class CuadrillaService extends AbstractSevice<Cuadrilla> {

    final static Logger logger = Logger.getLogger(CuadrillaService.class);

    @Inject
    private CuadrillaDao cuadrillaDao;

    @Inject
    private CuadrillasDetalleDao CuadrillasDetalleDao;

    @Inject
    private TecnicoService tecnicoService;

    @Override
    public Response registrar(Cuadrilla cuadrilla) {

        Response response = new Response(Response.OK, Response.MSG_OK);
        try {

            long numeroCuadrilla = cuadrillaDao.getMax() + 1;
            cuadrilla.setNumeroCuadrilla(numeroCuadrilla);
            validar(cuadrilla);

            List<CuadrillasDetalle> _list = cuadrilla.getCuadrillasDetalles();
            int nse = 1;
            for (CuadrillasDetalle d : _list) {
                d.setCuadrilla(cuadrilla);
                d.getId().setNumeroSecuencia(nse);
                d.getId().setNumeroCuadrilla(numeroCuadrilla);
                nse++;
            }

            cuadrillaDao.create(cuadrilla);

            //
        } catch (BusinessException e) {

            response = new Response(e.getCodigo(), e.getMessage());

        } catch (Exception e) {

            logger.info(e);
            logger.error(e);
            e.printStackTrace();

            response = new Response(Response.ERROR, Response.MSG_ERROR);

        }

        return response;
    }

    @Override
    public Response modificar(Cuadrilla cuadrilla) {

        Response response = new Response(Response.OK, Response.MSG_OK);
        try {
            validar(cuadrilla);
            List<CuadrillasDetalle> _list = cuadrilla.getCuadrillasDetalles();
            int nse = 1;
            for (CuadrillasDetalle d : _list) {
                logger.info("actualizando  :" + d.getId().toString());
            }
            cuadrillaDao.update(cuadrilla);
        } catch (BusinessException e) {
            response = new Response(e.getCodigo(), e.getMessage());
        } catch (Exception e) {
            logger.info(e);
            logger.error(e);
            e.printStackTrace();
            response = new Response(Response.ERROR, Response.MSG_ERROR);
        }

        return response;
    }

    @Override
    public Response eliminar(Cuadrilla cuadrilla) {

        Response response = new Response(Response.OK, Response.MSG_OK);

        try {

            Cuadrilla _cuadrilla = cuadrillaDao.find(cuadrilla.getNumeroCuadrilla());
            cuadrillaDao.remove(_cuadrilla);

        } catch (Exception e) {

            logger.info(e);
            logger.error(e);
            e.printStackTrace();
            response = new Response(Response.ERROR, Response.MSG_ERROR);
        }

        return response;
    }

    public List<Cuadrilla> getCuadrillaList(Date fechaProgramacion) {

        return cuadrillaDao.getCuadrillaList(fechaProgramacion);

    }

    public List<Cuadrilla> getCuadrillaList() {

        return cuadrillaDao.getCuadrillaList();
    }

    public List<Cuadrilla> getCuadrillaList(Date fechaIni, Date fechaFin, Vehiculo vehiculo, String criterio) {
        return cuadrillaDao.getCuadrillaList(fechaIni, fechaFin, vehiculo, criterio);

    }

    private void validar(Cuadrilla cuadrilla) throws BusinessException {
        Cuadrilla _cuadrilla = cuadrillaDao.getCuarilla(cuadrilla.getFechaProgramacion(), cuadrilla.getNombre());
        if (_cuadrilla != null) {
            throw new BusinessException(Response.ERROR, " El nombre de la cuadrilla ya se encuentra registrada para la fecha. Debe ingresar otra");
        }
    }

    public List<Cuadrilla> getCuadrillaLibresList(Date fecPrgn) {
        return cuadrillaDao.getCuadrillaLibresList(fecPrgn);
    }

    public List<Cuadrilla> getCuadrillasCombinadas(Date fecPrgn) {

        List<Cuadrilla> cuadrillaList = new ArrayList<>();

        List<Tecnico> tecnicoList = tecnicoService.getTecnicoList();
        long ncuadrilla = 1;
        for (Tecnico t1 : tecnicoList) {
            for (Tecnico t2 : tecnicoList) {
                
                if(!existGrupo(cuadrillaList, t1, t2) &&  !t1.equals(t2)){
                    Cuadrilla cuadrilla = new Cuadrilla(ncuadrilla, "Cuadrilla "+ncuadrilla);
                    cuadrilla.getCuadrillasDetalles().add(new CuadrillasDetalle(cuadrilla, t1));
                    cuadrilla.getCuadrillasDetalles().add(new CuadrillasDetalle(cuadrilla, t2));
                    cuadrillaList.add(cuadrilla);
                    ncuadrilla++;
                }
            }
        }

        return cuadrillaList;
    }

    private boolean existGrupo(List<Cuadrilla> cuadrillaList, Tecnico t1, Tecnico t2) {

        for (Cuadrilla c : cuadrillaList) {

            boolean has1 = false;
            boolean has2 = false;

            for (CuadrillasDetalle cd : c.getCuadrillasDetalles()) {

                if (cd.getTecnico().equals(t1)) {
                    has1 = true;
                }

                if (cd.getTecnico().equals(t2)) {
                    has2 = true;
                }
            }

            if (has1 && has2) {
                return true;
            }
        }

        return false;
    }

}

/*
 * 
 			//cuadrilla.setCuadrillasDetalles(new ArrayList<CuadrillasDetalle>());
			//_List<CuadrillasDetalle> list = cuadrilla.getCuadrillasDetalles();
			/*
			int nse = 1;
			for (CuadrillasDetalle d : _list) {
				d.setCuadrilla(cuadrilla);
				d.getId().setNumeroSecuencia(nse);
				d.getId().setNumeroCuadrilla(numeroCuadrilla);
				nse++;
				//logger.info("insertando :"+d.getId().toString());
				CuadrillasDetalleDao.create(d);
			}
			 
			
			
			
			/*int nse = 1;
			for (CuadrillasDetalle d : _list) {
				d.setCuadrilla(cuadrilla);
				d.getId().setNumeroSecuencia(nse);
				d.getId().setNumeroCuadrilla(numeroCuadrilla);
				nse++;
				logger.info("insertando :"+d.getId().toString());
				CuadrillasDetalleDao.create(d);
			}*/
 /*List<CuadrillasDetalle> list = cuadrilla.getCuadrillasDetalles();
			
			int nse = 1;
			for (CuadrillasDetalle d : list) {
				d.setCuadrilla(cuadrilla);
				d.getId().setNumeroSecuencia(nse);
				d.getId().setNumeroCuadrilla(numeroCuadrilla);
				CuadrillasDetalleDao.create(d);
			}*/
