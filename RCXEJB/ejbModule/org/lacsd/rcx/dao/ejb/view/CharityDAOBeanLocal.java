package org.lacsd.rcx.dao.ejb.view;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.CharityVO;

public interface CharityDAOBeanLocal {
	
	public CharityVO getCharitysList() throws LACSDException;
	
}
