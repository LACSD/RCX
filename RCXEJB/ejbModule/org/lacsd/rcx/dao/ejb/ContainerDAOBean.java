package org.lacsd.rcx.dao.ejb;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.lacsd.rcx.dao.ContainerDAO;
import org.lacsd.rcx.dao.ejb.view.ContainerDAOBeanLocal;

/**
 * Session Bean implementation class ContainerDAOBean
 */
@Stateless
@Local(ContainerDAOBeanLocal.class)
@LocalBean
public class ContainerDAOBean extends ContainerDAO implements ContainerDAOBeanLocal {
       
    /**
     * @see ContainerDAO#ContainerDAO()
     */
    public ContainerDAOBean() {
        super();
        // TODO Auto-generated constructor stub
    }

}
