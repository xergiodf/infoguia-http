/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @version 1 - 07/04/2017
 */
@Entity
@Table(name = "grupo_categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoCategoria.findAll", query = "SELECT g FROM GrupoCategoria g"), 
    @NamedQuery(name = "GrupoCategoria.findById", query = "SELECT g FROM GrupoCategoria g WHERE g.id = :id"), 
    @NamedQuery(name = "GrupoCategoria.findByDescripcion", query = "SELECT g FROM GrupoCategoria g WHERE g.descripcion = :descripcion"), 
    @NamedQuery(name = "GrupoCategoria.findByAuditUsuario", query = "SELECT g FROM GrupoCategoria g WHERE g.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "GrupoCategoria.findByAuditFechaInsert", query = "SELECT g FROM GrupoCategoria g WHERE g.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "GrupoCategoria.findByAuditFechaUpdate", query = "SELECT g FROM GrupoCategoria g WHERE g.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class GrupoCategoria implements Serializable {
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoCategoria")
    private Collection<Categoria> categoriaCollection;

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }    
}
