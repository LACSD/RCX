package org.lacsd.rcx.common.action;

import org.lacsd.common.exceptions.LACSDException;
import org.lacsd.common.struts2.action.LACSDGenericAction;


public abstract class RCXGenericAction extends LACSDGenericAction {



	@Override
	protected String executeApplicationGlobal() throws LACSDException, Throwable {
		// TODO Auto-generated method stub
		return executeWithResult();
	}

	@Override
	protected void initErrControl() {
		// TODO Auto-generated method stub
		
	}

}
