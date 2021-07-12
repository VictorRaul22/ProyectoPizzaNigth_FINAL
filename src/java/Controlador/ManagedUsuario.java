/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.RolesFacadeLocal;
import EJB.UsuariosFacadeLocal;
import Modelo.Roles;
import Modelo.Usuarios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
@ManagedBean
@SessionScoped
public class ManagedUsuario{
    @EJB
    UsuariosFacadeLocal usuarioFacade;
    
    @EJB
    RolesFacadeLocal rolesFacade;
    
    private Usuarios usuario;
    private Roles rol;
    private List<Usuarios> listUsuario;
    private String nombreArchivo;
    private String ruta = "C:\\Users\\VICTOR\\Documents\\CICLO7\\DESARROLLO WEB INTEGRADO\\java\\ProyectoPizzaNigth_FINAL\\web\\Administrador\\Usuario\\imagenes";

    
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
        for(int i=0;i< this.listUsuario.size();i++){
            usuario=this.listUsuario.get(i);
            usuario.setIdRol(this.rolesFacade.find(this.listUsuario.get(i).getIdRol().getIdRol()));
            this.listUsuario.set(i, usuario);
        }
        
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
    public void cargarCopiarImagen(FileUploadEvent event) {
        byte[] bytes = new byte[1024];
        int read = 0;
        try {
            UploadedFile uploadFile = event.getFile();
            nombreArchivo = uploadFile.getFileName();
            File file = new File(ruta, nombreArchivo);
            OutputStream out = new FileOutputStream(file);
            InputStream in = uploadFile.getInputstream();

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + "ssssssssssssssss");
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "sssssssssssssssss");
        }
    }
    public void guardar(){
        String codigo = this.usuarioFacade.codUser().get(0).getIdUser();
        int n = Integer.parseInt(codigo.substring(1)) + 1;
        int aux = 5 - (n + "").length();
        String cod;
        if (aux > 0) {
            char[] zero = new char[aux];
            Arrays.fill(zero, '0');
            cod = "U" + String.valueOf(zero) + n;
        } else {
            cod = "U" + n;
        }
        System.out.println(cod);
        this.usuario.setIdUser(cod);
        this.usuario.setIdRol(rol);
        this.usuario.setFotoPerfil(nombreArchivo);
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
        //System.out.println("1:"+usuario.getIdRol());
       
        this.usuarioFacade.edit(usuario);
        //System.out.println("2:"+usuario.getIdRol());
    }
    
}
