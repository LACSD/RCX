package org.lacsd.rcx.dao.ejb.view;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.rcx.values.RateVO;

public interface RateDAOBeanLocal {
	/**
	 * Local Bean that declares getRateList()
	 * @return
	 * @throws LACSDException
	 */
	public RateVO getRateList(RateVO rateVO) throws LACSDException;
}
