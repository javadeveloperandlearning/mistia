/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.service;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pe.com.cablered.mistia.dao.DepartamentoDao;
import pe.com.cablered.mistia.model.Departamento;

/**
 *
 * @author javadeveloper
 */
@Stateless
@LocalBean
public class DepartamentoService {
    
    
    @Inject
    DepartamentoDao departamentoDao;
    
    
     public List<Departamento> getDepartamentoList(){
         return departamentoDao.getDepartamentoList();
     }
   
    
}
