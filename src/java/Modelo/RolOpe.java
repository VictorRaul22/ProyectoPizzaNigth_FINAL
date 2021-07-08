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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VICTOR
 */
@Entity
@Table(name = "rol_ope")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolOpe.findAll", query = "SELECT r FROM RolOpe r")
    , @NamedQuery(name = "RolOpe.findByIdRolOp", query = "SELECT r FROM RolOpe r WHERE r.idRolOp = :idRolOp")})
public class RolOpe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol_op")
    private Integer idRolOp;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Roles idRol;
    @JoinColumn(name = "id_ope", referencedColumnName = "id_ope")
    @ManyToOne
    private Operacion idOpe;

    public RolOpe() {
    }

    public RolOpe(Integer idRolOp) {
        this.idRolOp = idRolOp;
    }

    public Integer getIdRolOp() {
        return idRolOp;
    }

    public void setIdRolOp(Integer idRolOp) {
        this.idRolOp = idRolOp;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    public Operacion getIdOpe() {
        return idOpe;
    }

    public void setIdOpe(Operacion idOpe) {
        this.idOpe = idOpe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolOp != null ? idRolOp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolOpe)) {
            return false;
        }
        RolOpe other = (RolOpe) object;
        if ((this.idRolOp == null && other.idRolOp != null) || (this.idRolOp != null && !this.idRolOp.equals(other.idRolOp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.RolOpe[ idRolOp=" + idRolOp + " ]";
    }
    
}
