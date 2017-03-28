package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "cliente_publicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientePublicacion.findAll", query = "SELECT c FROM ClientePublicacion c"),
    @NamedQuery(name = "ClientePublicacion.findById", query = "SELECT c FROM ClientePublicacion c WHERE c.id = :id"),
    @NamedQuery(name = "ClientePublicacion.findByTitulo", query = "SELECT c FROM ClientePublicacion c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "ClientePublicacion.findByDescripcionCorta", query = "SELECT c FROM ClientePublicacion c WHERE c.descripcionCorta = :descripcionCorta"),
    @NamedQuery(name = "ClientePublicacion.findByFechaCreacion", query = "SELECT c FROM ClientePublicacion c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ClientePublicacion.findByFechaDesde", query = "SELECT c FROM ClientePublicacion c WHERE c.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "ClientePublicacion.findByFechaHasta", query = "SELECT c FROM ClientePublicacion c WHERE c.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "ClientePublicacion.findByTipoPublicacion", query = "SELECT c FROM ClientePublicacion c WHERE c.tipoPublicacion.id = :tipoPublicacionId"),
    @NamedQuery(name = "ClientePublicacion.findByCliente", query = "SELECT c FROM ClientePublicacion c WHERE c.cliente.id = :clienteId")
})
public class ClientePublicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "descripcion_corta")
    private String descripcionCorta;
    
    @Column(name = "dir_imagen")
    private String dirImagen;
    
    @Column(name = "boton_accion")
    private String botonAccion;
    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    @Basic(optional = false)
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;
    
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;
    
    @JoinColumn(name = "tipo_publicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPublicacion tipoPublicacion;
    
    @JoinColumn(name = "id_estado_publicacion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoPublicacion estadoPublicacion;

    public ClientePublicacion() {
    }

    public ClientePublicacion(Integer id) {
        this.id = id;
    }

    public ClientePublicacion(Integer id, Date fechaDesde) {
        this.id = id;
        this.fechaDesde = fechaDesde;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDirImagen() {
        return dirImagen;
    }

    public void setDirImagen(String dirImagen) {
        this.dirImagen = dirImagen;
    }

    public String getBotonAccion() {
        return botonAccion;
    }

    public void setBotonAccion(String botonAccion) {
        this.botonAccion = botonAccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public EstadoPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
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
        if (!(object instanceof ClientePublicacion)) {
            return false;
        }
        ClientePublicacion other = (ClientePublicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.ClientePublicacion[ id=" + id + " ]";
    }
    
}
