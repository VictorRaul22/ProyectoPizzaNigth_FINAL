/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.CategoriaFacadeLocal;
import Modelo.Categoria;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManagedCategoria implements Serializable{
    @EJB
    private CategoriaFacadeLocal categoriaFacade;
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
        String codigo = this.categoriaFacade.codCateg().get(0).getIdCategoria();
        int n = Integer.parseInt(codigo.substring(2)) + 1;
        int aux = 5 - (n + "").length();
        String cod;
        if (aux > 0) {
            char[] zero = new char[aux];
            Arrays.fill(zero, '0');
            cod = "CP" + String.valueOf(zero) + n;
        } else {
            cod = "CP" + n;
        }
        this.categoria.setIdCategoria(cod);
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
        init();
    }
    public void vaciarDato(){
        this.categoria=new Categoria();
    }
}
