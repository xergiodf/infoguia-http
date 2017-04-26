package com.minicubic.infoguiacore.enums;

/**
 *
 * @author xergio
 * @version 1
 */
public enum TipoArchivo {
    
    PERFIL_USUARIO(1), PORTADA_SUCURSAL_CLIENTE(2), GALERIA_PERFIL_CLIENTE(3),
    IMAGEN_PUBLICACION(4);
    
    private final Integer id;
    
    private TipoArchivo(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
}
