package com.minicubic.infoguiahttp.model;

import java.io.Serializable;
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
@Table(name = "usuario_acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioAccion.findAll", query = "SELECT u FROM UsuarioAccion u"), 
    @NamedQuery(name = "UsuarioAccion.findById", query = "SELECT u FROM UsuarioAccion u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioAccion.findByAllParams", 
            query =     "SELECT u "
                    +   "FROM UsuarioAccion u "
                    +   "WHERE u.usuario.id = :idUsuario "
                    +   "AND u.tipoAccion.id = :idTipoAccion "
                    +   "AND u.tablaRef = :tablaRef "
                    +   "AND u.colRef = :colRef "
                    +   "AND u.idRef = :idRef "),
    @NamedQuery(name = "UsuarioAccion.findByUsuarioTipoAccion", 
            query =     "SELECT u "
                    +   "FROM UsuarioAccion u "
                    +   "WHERE u.usuario.id = :idUsuario "
                    +   "AND u.tipoAccion.id = :idTipoAccion ")
})
@ToString
@EqualsAndHashCode
public class UsuarioAccion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Usuario usuario;
    
    @JoinColumn(name = "id_tipo_accion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private TipoAccion tipoAccion;

    @Column(name = "palabra_clave")
    @Getter
    @Setter
    private String palabraClave;
    
    @Column(name = "tabla_ref")
    @Getter
    @Setter
    private String tablaRef;
    
    @Column(name = "col_ref")
    @Getter
    @Setter
    private String colRef;
    
    @Column(name = "id_ref")
    @Getter
    @Setter
    private String idRef;
}
