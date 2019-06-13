/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.ws.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.util.ConstantBusiness;

/**
 *
 * @author javadeveloper
 */
public class RequestUtil {

    public static Map toMap(PlanTrabajo planTrabajo) {

        Map<String, Object> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantBusiness.FORMAT_DATE);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(planTrabajo.getFechaProgramacion().getTime());
        String fechaProgramacion = sdf.format(cal.getTime());
        map.put("numeroPlanTrabajo", planTrabajo.getNumeroPlanTrabajo());
        map.put("fechaProgramacion", fechaProgramacion);
        
        Cuadrilla cuadrilla =  planTrabajo.getCuadrilla();
        if(cuadrilla!=null){
             Map<String, Object> mapCuadrilla =  new HashMap();             
             mapCuadrilla.put("numeroCuadrilla", cuadrilla.getNumeroCuadrilla());
             mapCuadrilla.put("fechaProgramacion", fechaProgramacion);
             mapCuadrilla.put("nombre", cuadrilla.getNombre());
             mapCuadrilla.put("gradoAsignacion", cuadrilla.getGradoAsignacion());
             map.put("cuadrilla",mapCuadrilla);
        }
        
        return map;
        
    }
    
    
    
    public static PlanTrabajoDetalle toPlanTrabajo(Map<String, String> map){
        
        
        PlanTrabajoDetalle  detalle =   new PlanTrabajoDetalle();

        if(map.get("numeroPlanTrabajo")!=null){
              detalle.setNumeroPlanTrabajo( Long.parseLong(map.get("numeroPlanTrabajo"),10));
        }
        if(map.get("numeroSecuencial")!=null){
            detalle.setNumeroSecuencial(Integer.parseInt(map.get("numeroSecuencial"),10));
        }
        
        if(map.get("observacion")!=null){
            detalle.setObservacion(map.get("observacion"));
            
        }
        
        if(map.get("indAtnd")!=null){
            detalle.setObservacion(map.get("observacion"));
        }
        
        /*map.put("codMotivo", null);
        map.put("horaInicio", null);
        map.put("horaFin", null);
        map.put("horaInicioAtencion", null);
        map.put("horaFinAtencion", null);*/
        
        
        //map.put("gradoPrioridad", null);
        //map.put("solicitudServicio", null);
       
        
        
       // map.put("actualizado", null);
       // map.put("codigoEstado", null);
        
        
        return null;
    }

}
