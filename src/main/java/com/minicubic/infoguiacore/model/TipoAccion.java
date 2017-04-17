package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author xergio
 * @version 1
 */
@Entity
@Table(name = "tipos_acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAccion.findAll", query = "SELECT t FROM TipoAccion t")
    , @NamedQuery(name = "TipoAccion.findById", query = "SELECT t FROM TipoAccion t WHERE t.id = :id")
    , @NamedQuery(name = "TipoAccion.findByDescripcion", query = "SELECT t FROM TipoAccion t WHERE t.descripcion = :descripcion")})
public class TipoAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAccion")
    private Collection<UsuarioAccion> usuarioAccionCollection;

    public TipoAccion() {
    }

    public TipoAccion(Integer id) {
        this.id = id;
    }

    public TipoAccion(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<UsuarioAccion> getUsuarioAccionCollection() {
        return usuarioAccionCollection;
    }

    public void setUsuarioAccionCollection(Collection<UsuarioAccion> usuarioAccionCollection) {
        this.usuarioAccionCollection = usuarioAccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAccion)) {
            return false;
        }
        TipoAccion other = (TipoAccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiacore.model.TipoAccion[ id=" + id + " ]";
    }

}
