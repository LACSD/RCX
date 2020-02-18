package org.lacsd.rcx.dao.ejb;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.lacsd.rcx.dao.CharityPaymentDAO;
import org.lacsd.rcx.dao.ejb.view.CharityPaymentDAOBeanLocal;

/**
 * Session Bean implementation class PipeDAOBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(CharityPaymentDAOBeanLocal.class)
@LocalBean
public class CharityPaymentDAOBean extends CharityPaymentDAO implements CharityPaymentDAOBeanLocal {
	
	
	public CharityPaymentDAOBean(){
		super();
	}
}
