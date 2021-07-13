/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.UsuariosFacadeLocal;
import Modelo.Usuarios;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean
@SessionScoped
public class platillaController implements Serializable{
    @EJB
     UsuariosFacadeLocal us;
    
    private Usuarios usuario;
    
    public Usuarios getUsuario() {
       this.usuario=(Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    @PostConstruct
    public void init(){
        this.usuario=new Usuarios();
    }
//    public void verificarSesion(){
//        Usuarios u=null;
//        try{
//            u =(Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//            //this.usuario=this.us.find(usuario.getIdUser());
//            if(u==null){
//            //   FacesContext.getCurrentInstance().getExternalContext().redirect("./SINPERMISOS.xhtml");
//            }
//            
//      
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        setUsuario(u);
//    }
    public void GetionTablas(){
        Usuarios us=(Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        int i=0;
        try{
            
         if( us==null || us.getIdRol().getIdRol().equals("R002") ){
             System.out.println( "HOLA MUNDO");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../SINPERMISOS.xhtml");
            
            }   
        }catch(Exception e){
            
        }
        
    }


    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
