/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pe.com.cablered.mistia.dao.PlanTrabajoDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDetalleDao;

/**
 *
 * @author javadeveloper
 */

@Stateless
@LocalBean
public class PlanTrabajoService {
    
    @Inject
    PlanTrabajoDao planTrabajoDao;
    
    @Inject
    PlanTrabajoDetalleDao planTrabajoDetalleDao;
    
    
    
    
    
    
    
    
    
    
}
