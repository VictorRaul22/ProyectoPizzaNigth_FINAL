/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import EJB.CategoriaFacade;
import EJB.ProductoFacadeLocal;
import Modelo.Categoria;
import Modelo.Producto;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class ManagedProducto {
    @EJB
    ProductoFacadeLocal productoFacade;
    private Producto producto;
    private List<Producto> listProduc;
    private Categoria categoria;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListProduc() {
        this.listProduc=productoFacade.findAll();
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
    public void init(){
        this.producto=new Producto();
        this.categoria=new Categoria();
    }
    public void guardar(){
        this.producto.setIdCategoria(categoria);
        this.productoFacade.create(producto);
    }
    public void eliminar(Producto p){
        this.productoFacade.remove(p);
    }
    public void cargarData(Producto p){
        this.categoria.setIdCategoria(p.getIdCategoria().getIdCategoria());
        this.producto=p;
    }
    public void modeificar(){
        this.producto.setIdCategoria(categoria);
        this.productoFacade.edit(producto);
    }
    
    
    
}
