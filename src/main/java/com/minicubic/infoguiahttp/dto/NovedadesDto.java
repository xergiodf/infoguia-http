package com.minicubic.infoguiahttp.dto;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@EqualsAndHashCode
public class NovedadesDto {
   
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String titulo;
    @Getter
    @Setter
    private String descripconCorta;
    @Getter
    @Setter
    private String dirImagen;
    @Getter
    @Setter
    private Long idCliente;
    @Getter
    @Setter
    private String nombreCompleto;
    @Getter
    @Setter
    private String tipoPublicacion;

    public NovedadesDto(Integer id, String titulo, String descripconCorta, String dirImagen, Long idCliente, String nombreCompleto, String tipoPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripconCorta = descripconCorta;
        this.dirImagen = dirImagen;
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.tipoPublicacion = tipoPublicacion;
    }
    
    
    
}
