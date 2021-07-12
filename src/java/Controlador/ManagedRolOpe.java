/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.ModuloFacadeLocal;
import EJB.OperacionFacade;
import EJB.OperacionFacadeLocal;
import EJB.RolOpeFacadeLocal;
import EJB.RolesFacadeLocal;
import Modelo.Modulo;
import Modelo.Operacion;
import Modelo.RolOpe;
import Modelo.Roles;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped

public class ManagedRolOpe implements Serializable{

    @EJB
    RolOpeFacadeLocal rolOpeFacade;
    @EJB
    OperacionFacadeLocal operacionFacade;
    @EJB
    RolesFacadeLocal rolesFacade;
    @EJB
    ModuloFacadeLocal moduloFacade;

    RolOpe rolope;
    Operacion Operaciones;
    Roles rol;
    Modulo modulo;
    private int view;
    private List<RolOpe> lisRolOpe;
    private List<Operacion> lisOperacion;
    private List<Modulo> lisModulo;
    private List<Roles>lisRoles;
    
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public RolOpe getRolope() {
        return rolope;
    }

    public void setRolope(RolOpe rolope) {
        this.rolope = rolope;
    }

    public Operacion getOperaciones() {
        return Operaciones;
    }

    public void setOperaciones(Operacion Operaciones) {
        this.Operaciones = Operaciones;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    

    public List<RolOpe> getLisRolOpe() {
        this.lisRolOpe = rolOpeFacade.findAll();
        for (int i = 0; i < this.lisRolOpe.size(); i++) {
            rolope = this.lisRolOpe.get(i);

            rolope.setIdRol(this.rolesFacade.find(this.lisRolOpe.get(i).getIdRol().getIdRol()));
            rolope.setIdOpe(this.operacionFacade.find(this.lisRolOpe.get(i).getIdOpe().getIdOpe()));
            Modulo m = this.moduloFacade.find(rolope.getIdOpe().getIdMod().getIdMod());
            rolope.getIdOpe().setIdMod(m);
            this.lisRolOpe.set(i, rolope);
        }
        return lisRolOpe;
    }

    public void setLisRolOpe(List<RolOpe> lisRolOpe) {
        this.lisRolOpe = lisRolOpe;
    }

    public List<Operacion> getLisOperacion() {
       // this.lisOperacion = operacionFacade.findAll();
        return lisOperacion;
    }

    public void setLisOperacion(List<Operacion> lisOperacion) {
        this.lisOperacion = lisOperacion;
    }

    public List<Modulo> getLisModulo() {
        this.lisModulo=moduloFacade.findAll();
        return lisModulo;
    }

    public void setLisModulo(List<Modulo> lisModulo) {
        this.lisModulo = lisModulo;
    }
    public List<Roles> getLisRoles() {
        this.lisRoles=rolesFacade.findAll();
        return lisRoles;
    }

    public void setLisRoles(List<Roles> lisRoles) {
        this.lisRoles = lisRoles;
    }
    @PostConstruct
    public void init() {
        this.rol = new Roles();
        this.Operaciones = new Operacion();
        this.rolope = new RolOpe();
        this.modulo=new Modulo();
        this.view=0;
    }

    public void guardar() {
        Operaciones.setIdMod(modulo);
        this.rolope.setIdOpe(Operaciones);
        this.rolope.setIdRol(rol);
        this.rolOpeFacade.create(rolope);
        
        
    }

    public void eliminar(RolOpe op) {
        this.rolOpeFacade.remove(op);
    }

    public void cargarData(RolOpe op) {
        this.Operaciones.setIdOpe(op.getIdOpe().getIdOpe());
        this.rol.setIdRol(op.getIdRol().getIdRol());
        this.modulo.setIdMod(op.getIdOpe().getIdMod().getIdMod());
        this.rolope = op;
    }

    public void modificar() {
        Operaciones.setIdMod(modulo);
        this.rolope.setIdOpe(Operaciones);
        this.rolope.setIdRol(rol);
        this.rolOpeFacade.edit(rolope);
        init();
    }
    
    public void listarOperacionModulo(){
       this.view=1;
        setLisOperacion(this.operacionFacade.listOperacion(this.modulo));
    }


    

}
