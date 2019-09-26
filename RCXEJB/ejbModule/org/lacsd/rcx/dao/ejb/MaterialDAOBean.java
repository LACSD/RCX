package org.lacsd.rcx.dao.ejb;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.lacsd.rcx.dao.MaterialDAO;
import org.lacsd.rcx.dao.ejb.view.MaterialDAOBeanLocal;

/**
 * Session Bean implementation class PipeDAOBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(MaterialDAOBeanLocal.class)
@LocalBean
public class MaterialDAOBean extends MaterialDAO implements MaterialDAOBeanLocal {
	
	
	public MaterialDAOBean(){
		super();
	}
}
