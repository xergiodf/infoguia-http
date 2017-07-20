package com.minicubic.infoguiahttp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "tipos_acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAccion.findAll", query = "SELECT t FROM TipoAccion t")
    , @NamedQuery(name = "TipoAccion.findById", query = "SELECT t FROM TipoAccion t WHERE t.id = :id")
    , @NamedQuery(name = "TipoAccion.findByDescripcion", query = "SELECT t FROM TipoAccion t WHERE t.descripcion = :descripcion")})
@ToString
@EqualsAndHashCode
public class TipoAccion implements Serializable {
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
}
