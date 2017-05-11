package com.minicubic.infoguiacore.enums;

/**
 *
 * @author xergio
 * @version 1
 */
public enum TipoArchivo {
    
    USUARIO_PERFIL(1, "/usuarioPerfiles/"), 
    CLIENTE_SUCURSAL(2, "/clienteSucursales/"), 
    GALERIA_PERFIL_CLIENTE(3, ""),
    CLIENTE_PUBLICACION(4, "/clientePublicaciones/");
    
    private final Integer id;
    private final String uploadDir;
    
    private TipoArchivo(Integer id, String uploadDir) {
        this.id = id;
        this.uploadDir = uploadDir;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getUploadDir() {
        return this.uploadDir;
    }
}
