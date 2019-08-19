package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import static pe.com.cablered.mistia.dao.CuadrillaDao.logger;

import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.Cuadrilla;

import static pe.com.cablered.mistia.util.ConstantBusiness.*;

@ApplicationScoped
public class ClienteDao extends CrudDao<Cliente> {

    final static Logger logger = Logger.getLogger(ClienteDao.class);

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Cliente> clienteList() {

        List<Cliente> list = Collections.EMPTY_LIST;
        try {
            String sql = "Select c from Cliente c  ";
            System.out.println(sql);
            TypedQuery<Cliente> query = getEntityManager().createQuery(sql, Cliente.class);
            list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<Cliente> clienteList(int tipoDocu, Integer codigoCliente, String nombres, String apellidoPaterno, String apellidoMaterno, String nombreRazonSocial) {
        
        
        List<Cliente> list = Collections.EMPTY_LIST;

        try {

            apellidoPaterno = (apellidoPaterno != null && apellidoPaterno.trim().equals("") ? null : apellidoPaterno);
            apellidoMaterno = (apellidoMaterno != null && apellidoMaterno.trim().equals("") ? null : apellidoMaterno);
            nombreRazonSocial = (nombreRazonSocial != null && nombreRazonSocial.trim().equals("") ? null : nombreRazonSocial);
            
            logger.info(" tipoDocu :  " + tipoDocu);
            logger.info(" codigoCliente :  " + codigoCliente);
            logger.info(" nombres :  " + nombres);
            logger.info(" apellidoPaterno :  " + apellidoPaterno);
            logger.info(" apellidoMaterno :  " + apellidoMaterno);
            logger.info(" nombreRazonSocial :  " + nombreRazonSocial);
                       

            String sql = "Select c "
                    + "from Cliente c  where "
                    //+ " (nombres =:pnombres or :pnombres is null)"
                   + "   (codigoCliente = :pcodigocliente or :pcodigocliente is  null )"
                   + " and  (upper(apellidoPaterno) like :papellidopaterno or :papellidopaterno is null ) "
                   + " and (upper(apellidoMaterno) like :papellidomaterno or :papellidomaterno is null) "
                   + " and (upper(nombreRazonSocial) like :pnombrerazonsocial or :pnombrerazonsocial is  null )";;
                        
            
            /*switch(tipoDocu){
                case TIPO_DOCU_DNI:
                    sql = sql+" and tipoDocumento  = "+ TIPO_DOCU_DNI;
                    break;                   
                    
                case TIPO_DOCU_CAR_EXT:
                    sql = sql+" and tipoDocumento  = "+ TIPO_DOCU_CAR_EXT;
                    break;
                    
                case TIPO_DOCU_RUC:
                    sql = sql+" and numeroRuc is not null";
                    break;                    
            }*/

            sql =  sql+" order by codigoCliente";
            
            
            TypedQuery<Cliente> query = getEntityManager().createQuery(sql, Cliente.class);
            query.setParameter("pcodigocliente", codigoCliente);
            //query.setParameter("pnombres", nombres);
            query.setParameter("papellidopaterno",   (apellidoPaterno==null?null: "%"+apellidoPaterno.trim().toUpperCase()+"%") );
            query.setParameter("papellidomaterno",   (apellidoMaterno==null?null: "%"+apellidoMaterno.trim().toUpperCase()+"%") );
            query.setParameter("pnombrerazonsocial", (nombreRazonSocial==null?null: "%"+nombreRazonSocial.trim().toUpperCase()+"%") );

            list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public int getMax() {
        int max = 0;
        try {

            String sql = "Select max(c.codigoCliente) From Cliente c";
            TypedQuery<Integer> query = getEntityManager().createQuery(sql, Integer.class);
            max = query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
            logger.error(e);
            max =  0;
        }
        return max;
    }   

}
