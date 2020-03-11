package org.lacsd.rcx.dao.ejb;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.lacsd.rcx.dao.SiteOperationsDAO;
import org.lacsd.rcx.dao.ejb.view.SiteOperationsDAOBeanLocal;

/**
 * Session Bean implementation class PipeDAOBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(SiteOperationsDAOBeanLocal.class)
@LocalBean
public class SiteOperationsDAOBean extends SiteOperationsDAO implements SiteOperationsDAOBeanLocal {
	
	
	public SiteOperationsDAOBean(){
		super();
	}
}
