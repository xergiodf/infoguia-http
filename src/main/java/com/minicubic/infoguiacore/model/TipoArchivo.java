package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author xergio
 * @version 1 - 18/04/2017
 */
@Entity
@Table(name = "tipos_archivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoArchivo.findAll", query = "SELECT t FROM TipoArchivo t"), 
    @NamedQuery(name = "TipoArchivo.findById", query = "SELECT t FROM TipoArchivo t WHERE t.id = :id"), 
    @NamedQuery(name = "TipoArchivo.findByDescripcion", query = "SELECT t FROM TipoArchivo t WHERE t.descripcion = :descripcion"), 
    @NamedQuery(name = "TipoArchivo.findByAuditUsuario", query = "SELECT t FROM TipoArchivo t WHERE t.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "TipoArchivo.findByAuditFechaInsert", query = "SELECT t FROM TipoArchivo t WHERE t.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "TipoArchivo.findByAuditFechaUpdate", query = "SELECT t FROM TipoArchivo t WHERE t.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class TipoArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "descripcion")
    @Getter
    @Setter
    private String descripcion;

    @Column(name = "audit_usuario")
    @Getter
    @Setter
    private String auditUsuario;

    @Column(name = "audit_fecha_insert", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaInsert;
    
    @Column(name = "audit_fecha_update", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaUpdate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoArchivo")
    private Collection<ArchivoCab> archivoCabCollection;

    @XmlTransient
    public Collection<ArchivoCab> getArchivoCabCollection() {
        return archivoCabCollection;
    }

    public void setArchivoCabCollection(Collection<ArchivoCab> archivoCabCollection) {
        this.archivoCabCollection = archivoCabCollection;
    }
}
