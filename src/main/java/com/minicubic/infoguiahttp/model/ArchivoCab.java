package com.minicubic.infoguiahttp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.CascadeType;
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
 * @version 1 - 17/04/2017
 */
@Entity
@Table(name = "archivos_cab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchivoCab.findAll", query = "SELECT a FROM ArchivoCab a"), 
    @NamedQuery(name = "ArchivoCab.findById", query = "SELECT a FROM ArchivoCab a WHERE a.id = :id"), 
    @NamedQuery(name = "ArchivoCab.findByRef", query = "SELECT a FROM ArchivoCab a WHERE a.tablaRef = :tablaRef AND a.columnaRef = :columnaRef AND a.idRef = :idRef")})
@ToString
@EqualsAndHashCode
public class ArchivoCab implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "tabla_ref")
    @Getter
    @Setter
    private String tablaRef;

    @Column(name = "columna_ref")
    @Getter
    @Setter
    private String columnaRef;

    @Column(name = "id_ref")
    @Getter
    @Setter
    private String idRef;
    
    @JoinColumn(name = "id_tipo_archivo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private TipoArchivo tipoArchivo;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "archivoCab")
    private List<ArchivoDet> archivosDet;

    @XmlTransient
    public List<ArchivoDet> getArchivosDet() {
        return archivosDet;
    }
    public void setArchivosDet(List<ArchivoDet> archivosDet) {
        this.archivosDet = archivosDet;
    }
}
