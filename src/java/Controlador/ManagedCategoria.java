/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.CategoriaFacadeLocal;
import Modelo.Categoria;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManagedCategoria {
    @EJB
    CategoriaFacadeLocal categoriaFacade;
    private List<Categoria> listaCategoria;
    private Categoria categoria;

    public List<Categoria> getListaCategoria() {
        this.listaCategoria=categoriaFacade.findAll();
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    @PostConstruct
    public void init(){
        this.categoria=new Categoria();
    }
    public void  guardar(){
        
       categoria.setIdCategoria("C0009");
        this.categoriaFacade.create(categoria);
    }
    public void elimianr(Categoria c){
        this.categoriaFacade.remove(c);
    }
    public void cargarData(Categoria c){
       
        this.categoria=c;
    }
    public void modificar(){
        this.categoriaFacade.edit(categoria);
    } 
}
