package org.lacsd.rcx.dao.ejb.view;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.MaterialVO;

public interface MaterialDAOBeanLocal {
	
	public MaterialVO getMaterialsList() throws LACSDException;
	
}
