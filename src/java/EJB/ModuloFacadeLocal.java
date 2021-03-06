/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Modulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author VICTOR
 */
@Local
public interface ModuloFacadeLocal {

    void create(Modulo modulo);

    void edit(Modulo modulo);

    void remove(Modulo modulo);

    Modulo find(Object id);

    List<Modulo> findAll();

    List<Modulo> findRange(int[] range);

    int count();
    
}
