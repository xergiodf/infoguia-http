package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Usuario.findByUsernameAndPassword", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Usuario.findByFechaExpiracion", query = "SELECT u FROM Usuario u WHERE u.fechaExpiracion = :fechaExpiracion"),
    @NamedQuery(name = "Usuario.findByTokenCambioPass", query = "SELECT u FROM Usuario u WHERE u.tokenCambioPass = :tokenCambioPass"),
    @NamedQuery(name = "Usuario.findByTokenConfirmacion", query = "SELECT u FROM Usuario u WHERE u.tokenConfirmacion = :tokenConfirmacion")})
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<SucursalValoracionDet> sucursalValoracionDetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioEmisor", fetch = FetchType.LAZY)
    private List<UsuarioMensajesCab> usuarioMensajesCabList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioReceptor", fetch = FetchType.LAZY)
    private List<UsuarioMensajesCab> usuarioMensajesCabList1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;
    
    @Column(name = "token_cambio_pass")
    private String tokenCambioPass;
    
    @Column(name = "token_confirmacion")
    private String tokenConfirmacion;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    @JoinColumn(name = "tipo_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;
    
    @JoinColumn(name = "id_usuario_estados", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoUsuario usuarioEstado;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(Long id, String username, String password, Date fechaRegistro) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTokenCambioPass() {
        return tokenCambioPass;
    }

    public void setTokenCambioPass(String tokenCambioPass) {
        this.tokenCambioPass = tokenCambioPass;
    }

    public String getTokenConfirmacion() {
        return tokenConfirmacion;
    }

    public void setTokenConfirmacion(String tokenConfirmacion) {
        this.tokenConfirmacion = tokenConfirmacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.cliente = Cliente;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public EstadoUsuario getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(EstadoUsuario usuarioEstados) {
        this.usuarioEstado = usuarioEstados;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.Usuario[ id=" + id + " ]";
    }

    @XmlTransient
    public List<SucursalValoracionDet> getSucursalValoracionDetList() {
        return sucursalValoracionDetList;
    }

    public void setSucursalValoracionDetList(List<SucursalValoracionDet> sucursalValoracionDetList) {
        this.sucursalValoracionDetList = sucursalValoracionDetList;
    }

    @XmlTransient
    public List<UsuarioMensajesCab> getUsuarioMensajesCabList() {
        return usuarioMensajesCabList;
    }

    public void setUsuarioMensajesCabList(List<UsuarioMensajesCab> usuarioMensajesCabList) {
        this.usuarioMensajesCabList = usuarioMensajesCabList;
    }

    @XmlTransient
    public List<UsuarioMensajesCab> getUsuarioMensajesCabList1() {
        return usuarioMensajesCabList1;
    }

    public void setUsuarioMensajesCabList1(List<UsuarioMensajesCab> usuarioMensajesCabList1) {
        this.usuarioMensajesCabList1 = usuarioMensajesCabList1;
    }
    
}
