/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import static pe.com.cablered.mistia.dao.DistritoDao.logger;
import pe.com.cablered.mistia.model.Departamento;
import pe.com.cablered.mistia.model.Distrito;

/**
 *
 * @author javadeveloper
 */

@ApplicationScoped
public class DepartamentoDao extends CrudDao<Departamento> {
    
    	final static Logger logger = Logger.getLogger(DepartamentoDao.class);
	
	@Inject
	private EntityManager em;
	
        
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
    
        public List<Departamento> getDepartamentoList(){
		
		logger.info(" metodo : getDepartamentoList");
		List<Departamento> departamentoList =  Collections.EMPTY_LIST;
		
		try{
			String sql =  "Select new Departamento(d.codigoDepartamento, d.descripcion) from Departamento d";
			TypedQuery<Departamento> query =  getEntityManager().createQuery(sql, Departamento.class);	
			departamentoList =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return departamentoList;
			
	}
        
        
        
        
        
}
