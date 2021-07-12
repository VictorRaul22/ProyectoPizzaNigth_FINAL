/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import EJB.BoletaFacadeLocal;
import Modelo.Boleta;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManagedBoleta {
    @EJB
    BoletaFacadeLocal boletaFacade;
    private List<Boleta> listBoleta;
    private Boleta boleta;

    public List<Boleta> getListBoleta() {
        this.listBoleta=boletaFacade.findAll();
        return listBoleta;
    }

    public void setListBoleta(List<Boleta> listBoleta) {
        this.listBoleta = listBoleta;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }
    
    
}
