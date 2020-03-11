package org.lacsd.rcx.common;

import org.lacsd.common.authentication.UserProfile;
import org.lacsd.common.values.EmployeeVO;

public class MockLACSDUser implements UserProfile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private volatile EmployeeVO employeeVO;

	@Override
	public boolean isBypassSecurity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmployeeVO getEmployeeVO() {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployeeID("306969");
		employeeVO.setUserName("christophersimmons");
		employeeVO.setFirstName("Christopher");
		employeeVO.setLastName("Simmons");
		setEmployeeVO(employeeVO);
		
		return this.employeeVO;
	}

	@Override
	public String getHttpSessionID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIPAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLoggedIN() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
		
	}

	@Override
	public void setHttpSessionID(String httpSessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIPAddress(String iPAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoggedIN(boolean loggedIN) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserID(String userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBypassSecurity(boolean isBypassSecurity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastLogin(String lastLogin) {
		// TODO Auto-generated method stub
		
	}

}
