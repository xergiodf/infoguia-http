package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @version 1
 */
@Entity
@Table(name = "usuario_perfiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioPerfil.findAll", query = "SELECT u FROM UsuarioPerfil u"), 
    @NamedQuery(name = "UsuarioPerfil.findById", query = "SELECT u FROM UsuarioPerfil u WHERE u.id = :id"), 
    @NamedQuery(name = "UsuarioPerfil.findByNombres", query = "SELECT u FROM UsuarioPerfil u WHERE u.nombres = :nombres"), 
    @NamedQuery(name = "UsuarioPerfil.findByApellidos", query = "SELECT u FROM UsuarioPerfil u WHERE u.apellidos = :apellidos"), 
    @NamedQuery(name = "UsuarioPerfil.findByFechaNacimiento", query = "SELECT u FROM UsuarioPerfil u WHERE u.fechaNacimiento = :fechaNacimiento"), 
    @NamedQuery(name = "UsuarioPerfil.findByOcupacion", query = "SELECT u FROM UsuarioPerfil u WHERE u.ocupacion = :ocupacion"), 
    @NamedQuery(name = "UsuarioPerfil.findByCiudad", query = "SELECT u FROM UsuarioPerfil u WHERE u.ciudad = :ciudad"), 
    @NamedQuery(name = "UsuarioPerfil.findByAuditUsuario", query = "SELECT u FROM UsuarioPerfil u WHERE u.auditUsuario = :auditUsuario"), 
    @NamedQuery(name = "UsuarioPerfil.findByAuditFechaInsert", query = "SELECT u FROM UsuarioPerfil u WHERE u.auditFechaInsert = :auditFechaInsert"), 
    @NamedQuery(name = "UsuarioPerfil.findByAuditFechaUpdate", query = "SELECT u FROM UsuarioPerfil u WHERE u.auditFechaUpdate = :auditFechaUpdate")})
@ToString
@EqualsAndHashCode
public class UsuarioPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @Column(name = "nombres")
    @Getter
    @Setter
    private String nombres;
    
    @Column(name = "apellidos")
    @Getter
    @Setter
    private String apellidos;
    
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date fechaNacimiento;
    
    @Column(name = "ocupacion")
    @Getter
    @Setter
    private String ocupacion;
    
    @Column(name = "ciudad")
    @Getter
    @Setter
    private Integer ciudad;

    @Column(name = "audit_usuario")
    @Getter
    @Setter
    private String auditUsuario;

    @Column(name = "audit_fecha_insert")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaInsert;
    
    @Column(name = "audit_fecha_update")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date auditFechaUpdate;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Usuario usuario;
}
