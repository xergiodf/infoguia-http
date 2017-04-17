/*

 */
package com.minicubic.infoguiacore.enums;

/**
 *
 * @author xergio
 * @version 1 - 18/04/2017
 */
public enum TableReference {

    PERFIL_USUARIO("usuario_perfiles", "id"),
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
