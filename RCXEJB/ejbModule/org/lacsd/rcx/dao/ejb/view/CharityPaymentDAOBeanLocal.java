package org.lacsd.rcx.dao.ejb.view;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.CharityPaymentVO;

public interface CharityPaymentDAOBeanLocal {
	
	public CharityPaymentVO getCharityPaymentsList() throws LACSDException;
	
}
