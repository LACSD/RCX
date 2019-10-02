package org.lacsd.rcx.dao.ejb;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.lacsd.rcx.dao.VendorDAO;
import org.lacsd.rcx.dao.ejb.view.VendorDAOBeanLocal;

/**
 * Session Bean implementation class VendorDAOBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(VendorDAOBeanLocal.class)
@LocalBean
public class VendorDAOBean extends VendorDAO implements VendorDAOBeanLocal {
       
    /**
     * @see VendorDAO#VendorDAO()
     */
    public VendorDAOBean() {
        super();
        // TODO Auto-generated constructor stub
    }

}
