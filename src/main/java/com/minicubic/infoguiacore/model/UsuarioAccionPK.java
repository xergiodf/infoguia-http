package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xergio
 * @version 1
 */
@Embeddable
public class UsuarioAccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_accion")
    private int idTipoAccion;

    public UsuarioAccionPK() {
    }

    public UsuarioAccionPK(int id, int idTipoAccion) {
        this.id = id;
        this.idTipoAccion = idTipoAccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoAccion() {
        return idTipoAccion;
    }

    public void setIdTipoAccion(int idTipoAccion) {
        this.idTipoAccion = idTipoAccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idTipoAccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioAccionPK)) {
            return false;
        }
        UsuarioAccionPK other = (UsuarioAccionPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idTipoAccion != other.idTipoAccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiacore.model.UsuarioAccionPK[ id=" + id + ", idTipoAccion=" + idTipoAccion + " ]";
    }

}
