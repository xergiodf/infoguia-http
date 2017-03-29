package com.minicubic.infoguiacore.model;

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
 * @version 1
 */
@Entity
@Table(name = "cliente_sucursales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteSucursal.findAll", query = "SELECT c FROM ClienteSucursal c"),
    @NamedQuery(name = "ClienteSucursal.findById", query = "SELECT c FROM ClienteSucursal c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteSucursal.findByNombreSucursal", query = "SELECT c FROM ClienteSucursal c WHERE c.nombreSucursal = :nombreSucursal"),
    @NamedQuery(name = "ClienteSucursal.findByDireccionFisica", query = "SELECT c FROM ClienteSucursal c WHERE c.direccionFisica = :direccionFisica"),
    @NamedQuery(name = "ClienteSucursal.findByCoordenadas", query = "SELECT c FROM ClienteSucursal c WHERE c.coordenadas = :coordenadas"),
    @NamedQuery(name = "ClienteSucursal.findByCliente", query = "SELECT c FROM ClienteSucursal c WHERE c.cliente.id = :clienteId")})
@ToString
@EqualsAndHashCode
public class ClienteSucursal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @Column(name = "nombre_sucursal")
    @Getter
    @Setter
    private String nombreSucursal;
    
    @Column(name = "direccion_fisica")
    @Getter
    @Setter
    private String direccionFisica;
    
    @Column(name = "coordenadas")
    @Getter
    @Setter
    private String coordenadas;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Cliente cliente;
    
    @Column(name = "telefonos")
    @Getter
    @Setter
    private String telefonos;
    
    @Column(name = "emails")
    @Getter
    @Setter
    private String emails;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalValoracionCab> sucursalValoracionCabs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalHorarioCab> sucursalHorariosCabs;

    @XmlTransient
    public List<SucursalValoracionCab> getSucursalValoracionCabs() {
        return sucursalValoracionCabs;
    }
    public void setSucursalValoracionCabs(List<SucursalValoracionCab> sucursalValoracionCabs) {
        this.sucursalValoracionCabs = sucursalValoracionCabs;
    }

    @XmlTransient
    public List<SucursalHorarioCab> getSucursalHorariosCabs() {
        return sucursalHorariosCabs;
    }
    public void setSucursalHorariosCabs(List<SucursalHorarioCab> sucursalHorariosCabs) {
        this.sucursalHorariosCabs = sucursalHorariosCabs;
    }
}
