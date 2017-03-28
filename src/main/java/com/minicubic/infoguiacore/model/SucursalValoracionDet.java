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
@Table(name = "sucursal_valoracion_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalValoracionDet.findAll", query = "SELECT s FROM SucursalValoracionDet s"),
    @NamedQuery(name = "SucursalValoracionDet.findById", query = "SELECT s FROM SucursalValoracionDet s WHERE s.id = :id"),
    @NamedQuery(name = "SucursalValoracionDet.findByPuntaje", query = "SELECT s FROM SucursalValoracionDet s WHERE s.puntaje = :puntaje")})
public class SucursalValoracionDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "puntaje")
    private float puntaje;
    @Lob
    @Column(name = "mensaje")
    private String mensaje;
    @JoinColumn(name = "id_cabecera", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalValoracionCab idCabecera;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public SucursalValoracionDet() {
    }

    public SucursalValoracionDet(Integer id) {
        this.id = id;
    }

    public SucursalValoracionDet(Integer id, float puntaje) {
        this.id = id;
        this.puntaje = puntaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public SucursalValoracionCab getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(SucursalValoracionCab idCabecera) {
        this.idCabecera = idCabecera;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof SucursalValoracionDet)) {
            return false;
        }
        SucursalValoracionDet other = (SucursalValoracionDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.SucursalValoracionDet[ id=" + id + " ]";
    }
    
}
