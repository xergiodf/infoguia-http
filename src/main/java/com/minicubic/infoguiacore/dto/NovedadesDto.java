package com.minicubic.infoguiacore.dto;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
