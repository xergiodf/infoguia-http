package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.ClienteCategoriaDto;
import com.minicubic.infoguiacore.model.ClienteCategoria;
import com.minicubic.infoguiacore.model.ClienteCategoriaPK;
import com.minicubic.infoguiacore.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class ClienteCategoriaConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();

    /**
     *
     * @param clienteCategorias
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public List<ClienteCategoriaDto> getClienteCategoriasDto(List<ClienteCategoria> clienteCategorias)
            throws IllegalAccessException, InvocationTargetException {
        List<ClienteCategoriaDto> clienteCategoriasDto = new ArrayList<>();

        for (ClienteCategoria clienteCategoria : clienteCategorias) {
            clienteCategoriasDto.add(getClienteCategoriaDto(clienteCategoria));
        }

        return clienteCategoriasDto;
    }

    /**
     *
     * @param clienteCategoriasDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public List<ClienteCategoria> getClienteCategorias(List<ClienteCategoriaDto> clienteCategoriasDto)
            throws IllegalAccessException, InvocationTargetException {
        List<ClienteCategoria> clienteCategorias = new ArrayList<>();

        for (ClienteCategoriaDto clienteCategoriaDto : clienteCategoriasDto) {
            clienteCategorias.add(getClienteCategoria(clienteCategoriaDto));
        }

        return clienteCategorias;
    }

    /**
     *
     * @param clienteCategoriaDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public ClienteCategoria getClienteCategoria(ClienteCategoriaDto clienteCategoriaDto)
            throws IllegalAccessException, InvocationTargetException {
        ClienteCategoria clienteCategoria = new ClienteCategoria();

        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clienteCategoria, clienteCategoriaDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        // Copiamos las propiedades compuestas
        if (!Util.isEmpty(clienteCategoriaDto.getCategoriaDto())
                && !Util.isEmpty(clienteCategoriaDto.getClienteDto())) {

            // Categoria
            clienteCategoria.setCategoria(new CategoriaConverter()
                    .getCategoria(clienteCategoriaDto.getCategoriaDto()));

            // Cliente
            clienteCategoria.setCliente(new ClienteConverter()
                    .getCliente(clienteCategoriaDto.getClienteDto()));

            // PK
            ClienteCategoriaPK pk = new ClienteCategoriaPK(
                    clienteCategoria.getCliente().getId(), clienteCategoria.getCategoria().getId());
            
            clienteCategoria.setClienteCategoriaPK(pk);
        }

        return clienteCategoria;
    }

    /**
     *
     * @param clienteCategoria
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public ClienteCategoriaDto getClienteCategoriaDto(ClienteCategoria clienteCategoria)
            throws IllegalAccessException, InvocationTargetException {
        ClienteCategoriaDto clienteCategoriaDto = new ClienteCategoriaDto();

        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(clienteCategoriaDto, clienteCategoria);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if (!Util.isEmpty(clienteCategoria.getCategoria())
                && !Util.isEmpty(clienteCategoria.getCliente())) {

            // Categoria
            clienteCategoriaDto.setCategoriaDto(new CategoriaConverter()
                    .getCategoriaDto(clienteCategoria.getCategoria()));

            // Cliente
            clienteCategoriaDto.setClienteDto(new ClienteConverter()
                    .getClienteDto(clienteCategoria.getCliente()));
        }

        return clienteCategoriaDto;
    }
}
