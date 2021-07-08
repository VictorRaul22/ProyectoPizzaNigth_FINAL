/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VICTOR
 */
@Entity
@Table(name = "tarjeta_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaCredito.findAll", query = "SELECT t FROM TarjetaCredito t")
    , @NamedQuery(name = "TarjetaCredito.findByIdTarjeta", query = "SELECT t FROM TarjetaCredito t WHERE t.idTarjeta = :idTarjeta")
    , @NamedQuery(name = "TarjetaCredito.findByNumTarjeta", query = "SELECT t FROM TarjetaCredito t WHERE t.numTarjeta = :numTarjeta")})
public class TarjetaCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_tarjeta")
    private String idTarjeta;
    @Column(name = "num_tarjeta")
    private Integer numTarjeta;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Usuarios idUser;

    public TarjetaCredito() {
    }

    public TarjetaCredito(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Integer getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(Integer numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarjeta != null ? idTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaCredito)) {
            return false;
        }
        TarjetaCredito other = (TarjetaCredito) object;
        if ((this.idTarjeta == null && other.idTarjeta != null) || (this.idTarjeta != null && !this.idTarjeta.equals(other.idTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.TarjetaCredito[ idTarjeta=" + idTarjeta + " ]";
    }
    
}
