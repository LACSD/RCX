package org.lacsd.rcx.dao.ejb.view;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.VendorVO;

public interface VendorDAOBeanLocal {
	/**
	 * Local Bean that declares getVendList()
	 * @return
	 * @throws LACSDException
	 */
	public VendorVO getVendList() throws LACSDException;
}
