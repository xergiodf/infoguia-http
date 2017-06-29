/*

 */
package com.minicubic.infoguiahttp.enums;

/**
 *
 * @author xergio
 * @version 1 - 18/04/2017
 */
public enum TableReference {

    USUARIO_PERFIL("usuario_perfiles", "id"),
    CLIENTE_SUCURSAL("cliente_sucursales", "id"),
    CLIENTE_PUBLICACION("cliente_publicaciones", "id"),
    CLIENTE("clientes", "id");

    private final String tableName;
    private final String idColumnName;

    private TableReference(String tableName, String idColumnName) {
        this.tableName = tableName;
        this.idColumnName = idColumnName;
    }

    public String getTableName() {
        return this.tableName;
    }
    
    public String getIdColumnName() {
        return this.idColumnName;
    }
}
