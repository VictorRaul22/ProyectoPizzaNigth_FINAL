/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.CategoriaFacadeLocal;
import EJB.ProductoFacadeLocal;
import Modelo.Categoria;
import Modelo.Producto;
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
public class ManagedProducto implements Serializable {
    @EJB
    private ProductoFacadeLocal productoFacade;
    @EJB
    private CategoriaFacadeLocal cateogoriaFacade;
    private Producto producto;
    private List<Producto> listProduc;
    private Categoria categoria;
    private String nombreArchivo;
    private String ruta = "C:\\Users\\VICTOR\\Documents\\CICLO7\\DESARROLLO WEB INTEGRADO\\java\\ProyectoPizzaNigth_FINAL\\web\\Administrador\\Producto\\imagenes";
    public ManagedProducto(){
        
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListProduc() {
        
        this.listProduc = productoFacade.findAll();
        for(int i=0;i< this.listProduc.size();i++){
            producto=this.listProduc.get(i);
            producto.setIdCategoria(this.cateogoriaFacade.find(this.listProduc.get(i).getIdCategoria().getIdCategoria()));
            this.listProduc.set(i, producto);
        }
        return listProduc;
    }

    public void setListProduc(List<Producto> listProduc) {
        this.listProduc = listProduc;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        this.categoria = new Categoria();
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
    public void guardar() {
        String codigo = this.productoFacade.codProduc().get(0).getIdProducto();
        int n = Integer.parseInt(codigo.substring(1)) + 1;
        int aux = 5 - (n + "").length();
        String cod = "";
        if (aux > 0) {
            char[] zero = new char[aux];
            Arrays.fill(zero, '0');
            cod = "P" + String.valueOf(zero) + n;
        } else {
            cod = "P" + n;
        }
        System.out.println(cod);
        this.producto.setIdProducto(cod);
        this.producto.setImagen(nombreArchivo);
        this.producto.setIdCategoria(categoria);
        this.productoFacade.create(producto);
    
    }

    public void eliminar(Producto p) {
        this.productoFacade.remove(p);
    }

    public void cargarData(Producto p) {

        this.categoria.setIdCategoria(p.getIdCategoria().getIdCategoria());
        this.producto = p;
    }

    public void modificar() {
        this.producto.setIdCategoria(categoria);
        this.productoFacade.edit(producto);
    }
    public void crearCategoria(){
      //   String codigo = this.cateogriFacade.codCateg().get(0).getIdCategoria();
        // System.out.println(codigo+"sssssss");
        System.out.println("ssssssssssssssssssss");
       /* int n = Integer.parseInt(codigo.substring(1)) + 1;
        int aux = 5 - (n + "").length();
        String cod = "";
        if (aux > 0) {
            char[] zero = new char[aux];
            Arrays.fill(zero, '0');
            cod = "cp" + String.valueOf(zero) + n;
        } else {
            cod = "cp" + n;
        }
        this.categoria.setIdCategoria(cod);
        this.cateogriFacade.create(categoria);*/
    }
    public Producto FindIdProduc(String id){
        return  productoFacade.find(id);
    }

}
