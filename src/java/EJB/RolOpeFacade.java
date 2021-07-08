/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.RolOpe;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VICTOR
 */
@Stateless
public class RolOpeFacade extends AbstractFacade<RolOpe> implements RolOpeFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPizzaNigth_FINALPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolOpeFacade() {
        super(RolOpe.class);
    }
    
}
