package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xergio
 * @version 1
 */
@Entity
@Table(name = "usuario_acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioAccion.findAll", query = "SELECT u FROM UsuarioAccion u")
    , @NamedQuery(name = "UsuarioAccion.findById", query = "SELECT u FROM UsuarioAccion u WHERE u.usuarioAccionPK.id = :id")
    , @NamedQuery(name = "UsuarioAccion.findByIdTipoAccion", query = "SELECT u FROM UsuarioAccion u WHERE u.usuarioAccionPK.idTipoAccion = :idTipoAccion")
    , @NamedQuery(name = "UsuarioAccion.findByPalabraClave", query = "SELECT u FROM UsuarioAccion u WHERE u.palabraClave = :palabraClave")
    , @NamedQuery(name = "UsuarioAccion.findByTablaRef", query = "SELECT u FROM UsuarioAccion u WHERE u.tablaRef = :tablaRef")
    , @NamedQuery(name = "UsuarioAccion.findByColRef", query = "SELECT u FROM UsuarioAccion u WHERE u.colRef = :colRef")
    , @NamedQuery(name = "UsuarioAccion.findByIdRef", query = "SELECT u FROM UsuarioAccion u WHERE u.idRef = :idRef")})
public class UsuarioAccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioAccionPK usuarioAccionPK;
    @Size(max = 200)
    @Column(name = "palabra_clave")
    private String palabraClave;
    @Size(max = 45)
    @Column(name = "tabla_ref")
    private String tablaRef;
    @Size(max = 45)
    @Column(name = "col_ref")
    private String colRef;
    @Size(max = 45)
    @Column(name = "id_ref")
    private String idRef;
    @JoinColumn(name = "id_tipo_accion", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoAccion tipoAccion;

    public UsuarioAccion() {
    }

    public UsuarioAccion(UsuarioAccionPK usuarioAccionPK) {
        this.usuarioAccionPK = usuarioAccionPK;
    }

    public UsuarioAccion(int id, int idTipoAccion) {
        this.usuarioAccionPK = new UsuarioAccionPK(id, idTipoAccion);
    }

    public UsuarioAccionPK getUsuarioAccionPK() {
        return usuarioAccionPK;
    }

    public void setUsuarioAccionPK(UsuarioAccionPK usuarioAccionPK) {
        this.usuarioAccionPK = usuarioAccionPK;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public String getTablaRef() {
        return tablaRef;
    }

    public void setTablaRef(String tablaRef) {
        this.tablaRef = tablaRef;
    }

    public String getColRef() {
        return colRef;
    }

    public void setColRef(String colRef) {
        this.colRef = colRef;
    }

    public String getIdRef() {
        return idRef;
    }

    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    public TipoAccion getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(TipoAccion tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioAccionPK != null ? usuarioAccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioAccion)) {
            return false;
        }
        UsuarioAccion other = (UsuarioAccion) object;
        if ((this.usuarioAccionPK == null && other.usuarioAccionPK != null) || (this.usuarioAccionPK != null && !this.usuarioAccionPK.equals(other.usuarioAccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiacore.model.UsuarioAccion[ usuarioAccionPK=" + usuarioAccionPK + " ]";
    }

}
