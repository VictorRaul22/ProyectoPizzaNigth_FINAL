/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.RolOpe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author VICTOR
 */
@Local
public interface RolOpeFacadeLocal {

    void create(RolOpe rolOpe);

    void edit(RolOpe rolOpe);

    void remove(RolOpe rolOpe);

    RolOpe find(Object id);

    List<RolOpe> findAll();

    List<RolOpe> findRange(int[] range);

    int count();
    
}
