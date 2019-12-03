package org.lacsd.rcx.dao.ejb;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.lacsd.rcx.dao.RateDAO;
import org.lacsd.rcx.dao.ejb.view.RateDAOBeanLocal;

/**
 * Session Bean implementation class RateDAOBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(RateDAOBeanLocal.class)
@LocalBean
public class RateDAOBean extends RateDAO implements RateDAOBeanLocal {
       
    /**
     * @see RateDAO#RateDAO()
     */
    public RateDAOBean() {
        super();
        // TODO Auto-generated constructor stub
    }

}
