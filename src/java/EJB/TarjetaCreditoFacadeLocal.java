/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.TarjetaCredito;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author VICTOR
 */
@Local
public interface TarjetaCreditoFacadeLocal {

    void create(TarjetaCredito tarjetaCredito);

    void edit(TarjetaCredito tarjetaCredito);

    void remove(TarjetaCredito tarjetaCredito);

    TarjetaCredito find(Object id);

    List<TarjetaCredito> findAll();

    List<TarjetaCredito> findRange(int[] range);

    int count();
    
}
