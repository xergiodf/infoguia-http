package com.minicubic.infoguiahttp.enums;

import lombok.Getter;

/**
 *
 * @author sedf
 */
public enum TipoAccion {
    LISTA_DESEO(1),
    FAVORITO(2);
    
    @Getter
    private final Integer id;
    
    private TipoAccion(Integer id) {
        this.id = id;
    }
}
