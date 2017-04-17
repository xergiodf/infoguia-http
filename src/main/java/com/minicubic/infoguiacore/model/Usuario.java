package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Collection;
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
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUsernameAndPassword", 
            query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password AND u.estadoUsuario.id = :estadoUsuarioId"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.finfByUsernameAndTokenConfirmacion", 
            query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.tokenConfirmacion = :tokenConfirmacion AND u.estadoUsuario.id = :estadoUsuarioId")
})
@ToString
@EqualsAndHashCode
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;
    
    @Column(name = "email")
    @Getter
    @Setter
    private String email;
    
    @Column(name = "username")
    @Getter
    @Setter
    private String username;
    
    @Column(name = "password")
    @Getter
    @Setter
    private String password;
    
    @JoinColumn(name = "id_estado_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private EstadoUsuario estadoUsuario;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaRegistro;
    
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date fechaExpiracion;
    
    @Column(name = "token_cambio_pass")
    @Getter
    @Setter
    private String tokenCambioPass;
    
    @Column(name = "token_confirmacion")
    @Getter
    @Setter
    private String tokenConfirmacion;
    
    @Column(name = "ultima_conexion")
    @Getter
    @Setter
    private String ultimaConexion;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Cliente cliente;
    
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private TipoUsuario tipoUsuario;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<SucursalValoracionDet> sucursalValoracionDets;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<UsuarioPerfil> usuarioPerfiles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEmisor", fetch = FetchType.LAZY)
    private Collection<UsuarioMensajeCab> usuarioMensajeCabsEmisor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioReceptor", fetch = FetchType.LAZY)
    private Collection<UsuarioMensajeCab> usuarioMensajesCabsReceptor;

    @XmlTransient
    public Collection<SucursalValoracionDet> getSucursalValoracionDets() {
        return sucursalValoracionDets;
    }
    public void setSucursalValoracionDets(Collection<SucursalValoracionDet> sucursalValoracionDets) {
        this.sucursalValoracionDets = sucursalValoracionDets;
    }

    @XmlTransient
    public Collection<UsuarioMensajeCab> getUsuarioMensajesCabsEmisor() {
        return usuarioMensajeCabsEmisor;
    }
    public void setUsuarioMensajesCabsEmisor(Collection<UsuarioMensajeCab> usuarioMensajesCabsEmisor) {
        this.usuarioMensajeCabsEmisor = usuarioMensajesCabsEmisor;
    }

    @XmlTransient
    public Collection<UsuarioMensajeCab> getUsuarioMensajesCabsReceptor() {
        return usuarioMensajesCabsReceptor;
    }
    public void setUsuarioMensajesCabsReceptor(Collection<UsuarioMensajeCab> usuarioMensajesCabsReceptor) {
        this.usuarioMensajesCabsReceptor = usuarioMensajesCabsReceptor;
    }
    
    @XmlTransient
    public Collection<UsuarioPerfil> getUsuarioPerfiles() {
        return usuarioPerfiles;
    }
    public void setUsuarioPerfiles(Collection<UsuarioPerfil> usuarioPerfiles) {
        this.usuarioPerfiles = usuarioPerfiles;
    }
}
