/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;
import pe.com.cablered.mistia.model.TipoSolicitud;

/**
 *
 * @author javadeveloper
 */
public class TipoSolicitudSort1 implements Comparator<TipoSolicitud> {

    @Override
    public int compare(TipoSolicitud o1, TipoSolicitud o2) {
        
        if(o1==null || o2 == null){
            return 0;
        }
        if(o1.getPrioridad()==null || o2.getPrioridad()==null){
            return 0;
        }
        
        int i = 0;
        i =  o1.getPrioridad().compareTo(o2.getPrioridad());
        if(i!=0){
            return i;
        }
       
        return 0;
    }
    
}
