package com.minicubic.infoguiahttp.model;

import java.io.Serializable; 
import java.util.Date; 
import java.util.List; 
import javax.persistence.CascadeType; 
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.FetchType; 
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

@Entity 
@Table(name = "tipos_horarios") 
@XmlRootElement 
@NamedQueries({ 
    @NamedQuery(name = "TipoHorario.findAll", query = "SELECT t FROM TipoHorario t"), 
    @NamedQuery(name = "TipoHorario.findById", query = "SELECT t FROM TipoHorario t WHERE t.id = :id"), 
    @NamedQuery(name = "TipoHorario.findByDescripcion", query = "SELECT t FROM TipoHorario t WHERE t.descripcion = :descripcion")}) 
@ToString 
@EqualsAndHashCode 
public class TipoHorario implements Serializable { 
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
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoHorario", fetch = FetchType.LAZY) 
    private List<SucursalHorarioCab> sucursalHorariosCabs; 
 
    @XmlTransient 
    public List<SucursalHorarioCab> getSucursalHorariosCabList() { 
        return sucursalHorariosCabs; 
    } 
    public void setSucursalHorariosCabList(List<SucursalHorarioCab> sucursalHorariosCabList) { 
        this.sucursalHorariosCabs = sucursalHorariosCabList; 
    } 
} 
