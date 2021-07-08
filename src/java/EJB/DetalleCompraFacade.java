/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.DetalleCompra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VICTOR
 */
@Stateless
public class DetalleCompraFacade extends AbstractFacade<DetalleCompra> implements DetalleCompraFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPizzaNigth_FINALPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCompraFacade() {
        super(DetalleCompra.class);
    }
    
}
