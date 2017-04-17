package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author xergio
 * @version 1 - 17/04/2017
 */
@Entity
@Table(name = "archivos_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchivoDet.findAll", query = "SELECT a FROM ArchivoDet a"), 
    @NamedQuery(name = "ArchivoDet.findById", query = "SELECT a FROM ArchivoDet a WHERE a.id = :id"), 
    @NamedQuery(name = "ArchivoDet.findByMimeType", query = "SELECT a FROM ArchivoDet a WHERE a.mimeType = :mimeType"), 
    @NamedQuery(name = "ArchivoDet.findByNombre", query = "SELECT a FROM ArchivoDet a WHERE a.nombre = :nombre"), 
    @NamedQuery(name = "ArchivoDet.findByUbicacion", query = "SELECT a FROM ArchivoDet a WHERE a.ubicacion = :ubicacion"), 
    @NamedQuery(name = "ArchivoDet.findByUrl", query = "SELECT a FROM ArchivoDet a WHERE a.url = :url"), 
    @NamedQuery(name = "ArchivoDet.findByAuditUsuario", query = "SELECT a FROM ArchivoDet a WHERE a.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "ArchivoDet.findByAuditFechaInsert", query = "SELECT a FROM ArchivoDet a WHERE a.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "ArchivoDet.findByAuditFechaUpdate", query = "SELECT a FROM ArchivoDet a WHERE a.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class ArchivoDet implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @JoinColumn(name = "id_archivo_cab", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private ArchivoCab archivoCab;
    
    @Column(name = "mime_type")
    @Getter
    @Setter
    private String mimeType;
    
    @Column(name = "nombre")
    @Getter
    @Setter
    private String nombre;
    
    @Column(name = "ubicacion")
    @Getter
    @Setter
    private String ubicacion;

    @Column(name = "URL")
    @Getter
    @Setter
    private String url;

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
}
