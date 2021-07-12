/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.BoletaFacadeLocal;
import EJB.DetalleCompraFacadeLocal;
import EJB.ProductoFacade;
import EJB.ProductoFacadeLocal;
import Modelo.Boleta;
import Modelo.DetalleCompra;
import Modelo.Producto;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManagedPedido implements Serializable{
    @EJB
    private DetalleCompraFacadeLocal detalleFacabde;
    
    @EJB
    private BoletaFacadeLocal boletaFacade;
    
    @EJB
    private ProductoFacadeLocal productoFacade;
            
    private DetalleCompra detalleCompra;
    private Boleta boleta;
    private Producto producto;
    
    private List<DetalleCompra> listDetalle;
    private List<Boleta> lisBoleta;

    public DetalleCompra getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(DetalleCompra detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<DetalleCompra> getListDetalle() {
        this.listDetalle=detalleFacabde.findAll();
//        for(int i=0;i< this.listDetalle.size();i++){
//            detalleCompra=this.listDetalle.get(i);
//            detalleCompra.setIdProducto(this.productoFacade.find(this.listDetalle.get(i).getIdProducto().getIdProducto()));
//            this.listDetalle.set(i, detalleCompra);
//        }
        return listDetalle;
    }

    public void setListDetalle(List<DetalleCompra> listDetalle) {
        this.listDetalle = listDetalle;
    }
    public List<Boleta> getLisBoleta() {
        this.lisBoleta=boletaFacade.findAll();
        return lisBoleta;
    }

    public void setLisBoleta(List<Boleta> lisBoleta) {
        this.lisBoleta = lisBoleta;
    }
    @PostConstruct
    public void init(){
        this.boleta=new Boleta();
        this.producto=new Producto();
        this.detalleCompra=new DetalleCompra();
    }
    public void guardar(){
        String codigo = this.detalleFacabde.codDetalle().get(0).getIdDetalle();
        int n = Integer.parseInt(codigo.substring(2)) + 1;
        int aux = 5 - (n + "").length();
        String cod = "";
        if (aux > 0) {
            char[] zero = new char[aux];
            Arrays.fill(zero, '0');
            cod = "DB" + String.valueOf(zero) + n;
        } else {
            cod = "DB" + n;
        }
        this.detalleCompra.setIdDetalle(cod);
        this.detalleCompra.setIdBoleta(boleta);
        this.detalleCompra.setIdProducto(producto);
        this.detalleFacabde.create(detalleCompra);
    }
    public void eliminar(DetalleCompra d){
        this.detalleFacabde.remove(d);
    }
    public void cargarData(DetalleCompra d){
        this.boleta.setIdBoleta(d.getIdBoleta().getIdBoleta());
        this.producto.setIdProducto(d.getIdProducto().getIdProducto());
        this.detalleCompra=d;
    }
    public void modificar(){
        DecimalFormatSymbols dfs=new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df=new DecimalFormat("#.##",dfs);
        double precio= Double.parseDouble(df.format(this.productoFacade.find(producto.getIdProducto()).getPrecio()));
        this.detalleCompra.setPrecioSubTotal(Double.parseDouble(df.format(detalleCompra.getCantidad()*precio)));

        this.detalleCompra.setIdBoleta(boleta);
        this.detalleCompra.setIdProducto(producto);
        this.detalleFacabde.edit(detalleCompra);
        init();
    }


    
    
    
    
    
}
