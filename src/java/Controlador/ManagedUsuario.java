/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.UsuariosFacadeLocal;
import Modelo.Roles;
import Modelo.Usuarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class ManagedUsuario {
    @EJB
    UsuariosFacadeLocal usuarioFacade;
    private Usuarios usuario;
    private Roles rol;
    private List<Usuarios> listUsuario;

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Usuarios> getListUsuario() {
        this.listUsuario=usuarioFacade.findAll();
        return listUsuario;
    }

    public void setListUsuario(List<Usuarios> listUsuario) {
        this.listUsuario = listUsuario;
    }
    @PostConstruct
    public void init(){
        this.usuario=new Usuarios();
        this.rol=new Roles();
    }
    public void guardar(){
        this.usuario.setIdRol(rol);
        this.usuarioFacade.create(usuario);
    }
     public void eliminar(Usuarios u){
        this.usuarioFacade.remove(u);
    }
    public void cargarData(Usuarios u){
        this.rol.setIdRol(u.getIdRol().getIdRol());
        this.usuario=u;
    }
    public void modificar(){
        this.usuario.setIdRol(rol);
        this.usuarioFacade.edit(usuario);
    }
}
