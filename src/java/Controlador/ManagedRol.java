/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.RolesFacadeLocal;
import Modelo.Roles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManagedRol{
    @EJB
    RolesFacadeLocal rolesFacade;
    Roles roles;
    List<Roles> lisRoles;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public List<Roles> getLisRoles() {
        this.lisRoles=rolesFacade.findAll();
        return lisRoles;
    }

    public void setLisRoles(List<Roles> lisRoles) {
        this.lisRoles = lisRoles;
    }
    @PostConstruct
    public void init(){
        this.roles=new Roles();
    }
    
    public void cargar(){
        this.rolesFacade.create(roles);
    }
    public void eliminar(Roles r){
        this.rolesFacade.remove(r);
    }
    public void CargarData(Roles r){
        this.roles=r;
    }
    public void modificar(){
        this.rolesFacade.edit(roles);
    }
}
