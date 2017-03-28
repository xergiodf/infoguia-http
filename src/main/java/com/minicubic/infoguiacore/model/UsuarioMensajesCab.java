/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "usuario_mensajes_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioMensajesCab.findAll", query = "SELECT u FROM UsuarioMensajesCab u"),
    @NamedQuery(name = "UsuarioMensajesCab.findById", query = "SELECT u FROM UsuarioMensajesCab u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioMensajesCab.findByFechaMensaje", query = "SELECT u FROM UsuarioMensajesCab u WHERE u.fechaMensaje = :fechaMensaje"),
    @NamedQuery(name = "UsuarioMensajesCab.findByNroOrden", query = "SELECT u FROM UsuarioMensajesCab u WHERE u.nroOrden = :nroOrden")})
public class UsuarioMensajesCab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_mensaje")
    private String fechaMensaje;
    @Basic(optional = false)
    @Column(name = "nro_orden")
    private int nroOrden;
    @Basic(optional = false)
    @Lob
    @Column(name = "mensaje")
    private String mensaje;
    @JoinColumn(name = "id_usuario_emisor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuarioEmisor;
    @JoinColumn(name = "id_usuario_receptor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuarioReceptor;

    public UsuarioMensajesCab() {
    }

    public UsuarioMensajesCab(Integer id) {
        this.id = id;
    }

    public UsuarioMensajesCab(Integer id, String fechaMensaje, int nroOrden, String mensaje) {
        this.id = id;
        this.fechaMensaje = fechaMensaje;
        this.nroOrden = nroOrden;
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaMensaje() {
        return fechaMensaje;
    }

    public void setFechaMensaje(String fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(int nroOrden) {
        this.nroOrden = nroOrden;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getIdUsuarioEmisor() {
        return idUsuarioEmisor;
    }

    public void setIdUsuarioEmisor(Usuario idUsuarioEmisor) {
        this.idUsuarioEmisor = idUsuarioEmisor;
    }

    public Usuario getIdUsuarioReceptor() {
        return idUsuarioReceptor;
    }

    public void setIdUsuarioReceptor(Usuario idUsuarioReceptor) {
        this.idUsuarioReceptor = idUsuarioReceptor;
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
        if (!(object instanceof UsuarioMensajesCab)) {
            return false;
        }
        UsuarioMensajesCab other = (UsuarioMensajesCab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.UsuarioMensajesCab[ id=" + id + " ]";
    }
    
}
