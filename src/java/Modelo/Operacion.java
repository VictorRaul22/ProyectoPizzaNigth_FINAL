/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VICTOR
 */
@Entity
@Table(name = "operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacion.findAll", query = "SELECT o FROM Operacion o")
    , @NamedQuery(name = "Operacion.findByIdOpe", query = "SELECT o FROM Operacion o WHERE o.idOpe = :idOpe")
    , @NamedQuery(name = "Operacion.findByNombre", query = "SELECT o FROM Operacion o WHERE o.nombre = :nombre")})
public class Operacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_ope")
    private String idOpe;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_mod", referencedColumnName = "id_mod")
    @ManyToOne
    private Modulo idMod;
    @OneToMany(mappedBy = "idOpe")
    private Collection<RolOpe> rolOpeCollection;

    public Operacion() {
    }

    public Operacion(String idOpe) {
        this.idOpe = idOpe;
    }

    public String getIdOpe() {
        return idOpe;
    }

    public void setIdOpe(String idOpe) {
        this.idOpe = idOpe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modulo getIdMod() {
        return idMod;
    }

    public void setIdMod(Modulo idMod) {
        this.idMod = idMod;
    }

    @XmlTransient
    public Collection<RolOpe> getRolOpeCollection() {
        return rolOpeCollection;
    }

    public void setRolOpeCollection(Collection<RolOpe> rolOpeCollection) {
        this.rolOpeCollection = rolOpeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpe != null ? idOpe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.idOpe == null && other.idOpe != null) || (this.idOpe != null && !this.idOpe.equals(other.idOpe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Operacion[ idOpe=" + idOpe + " ]";
    }
    
}
