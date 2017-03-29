package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author xergio
 * @version 1
 */
@Embeddable
public class ClienteCategoriaPK implements Serializable {

    @Column(name = "id_cliente")
    private long idCliente;

    @Column(name = "id_categoria")
    private int idCategoria;

    public ClienteCategoriaPK() {
    }

    public ClienteCategoriaPK(long idCliente, int idCategoria) {
        this.idCliente = idCliente;
        this.idCategoria = idCategoria;
    }

    public long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (int) idCategoria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteCategoriaPK)) {
            return false;
        }
        ClienteCategoriaPK other = (ClienteCategoriaPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiacore.model.ClienteCategoriaPK[ idCliente=" + idCliente + ", idCategoria=" + idCategoria + " ]";
    }

}
