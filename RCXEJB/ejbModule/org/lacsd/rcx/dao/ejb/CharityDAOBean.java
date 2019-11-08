package org.lacsd.rcx.dao.ejb;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.lacsd.rcx.dao.CharityDAO;
import org.lacsd.rcx.dao.ejb.view.CharityDAOBeanLocal;

/**
 * Session Bean implementation class PipeDAOBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(CharityDAOBeanLocal.class)
@LocalBean
public class CharityDAOBean extends CharityDAO implements CharityDAOBeanLocal {
	
	
	public CharityDAOBean(){
		super();
	}
}
