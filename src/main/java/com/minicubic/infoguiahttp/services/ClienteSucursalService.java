package com.minicubic.infoguiahttp.services;

import com.minicubic.infoguiahttp.dao.ClienteCategoriaDao;
import com.minicubic.infoguiahttp.dao.ClienteSucursalDao;
import com.minicubic.infoguiahttp.dto.CategoriaDto;
import com.minicubic.infoguiahttp.dto.ClienteCategoriaDto;
import com.minicubic.infoguiahttp.dto.ClienteSucursalDto;
import com.minicubic.infoguiahttp.dto.PagedResponseDto;
import com.minicubic.infoguiahttp.dto.SearchRequestDto;
import com.minicubic.infoguiahttp.dto.SucursalCategoriaDto;
import com.minicubic.infoguiahttp.util.Constants;
import com.minicubic.infoguiahttp.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 *
 * @author xergio
 * @version 1
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClienteSucursalService {

    @Inject
    private ClienteSucursalDao dao;

    @Inject
    private ClienteCategoriaDao clienteCategoriaDao;

    /**
     *
     * @param id
     * @return
     */
    public ClienteSucursalDto getClienteSucursal(Integer id) {
        return dao.getClienteSucursal(id);
    }

    /**
     *
     * @return
     */
    public List<ClienteSucursalDto> getClienteSucursales() {
        return dao.getClienteSucursales();
    }

    /**
     *
     * @param clienteId
     * @return
     */
    public List<ClienteSucursalDto> getClienteSucursalesByCliente(Long clienteId) {
        return dao.getClienteSucursalesByCliente(clienteId);
    }

    /**
     *
     * @param params
     * @return
     */
//    public List<SucursalCategoriaDto> getClienteSucursalesByParams(String params) {
//        SucursalCategoriaDto sucursalCategoriaDto = null;
//        List<ClienteCategoriaDto> clienteCategoriasDto = null;
//        List<SucursalCategoriaDto> sucursalCategoriasDto = new ArrayList<>();
//        
//        List<ClienteSucursalDto> clienteSucursalesDto = dao.getClienteSucursalesByParams(params);
//
//        for (ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto) {
//            sucursalCategoriaDto = new SucursalCategoriaDto();
//            sucursalCategoriaDto.setCategoriasDto(new ArrayList<CategoriaDto>());
//            
//            sucursalCategoriaDto.setSucursalDto(clienteSucursalDto);
//            
//            clienteCategoriasDto = clienteCategoriaDao.getClienteCategoriasByCliente(clienteSucursalDto.getClienteDto().getId());
//            for (ClienteCategoriaDto clienteCategoriaDto : clienteCategoriasDto)
//                sucursalCategoriaDto.getCategoriasDto().add(clienteCategoriaDto.getCategoriaDto());
//
//            sucursalCategoriasDto.add(sucursalCategoriaDto);
//        }
//        
//        return sucursalCategoriasDto;
//    }
    
    public PagedResponseDto getClienteSucursalesByParams(SearchRequestDto params) {
        SucursalCategoriaDto sucursalCategoriaDto = null;
        List<ClienteCategoriaDto> clienteCategoriasDto = null;
        List<SucursalCategoriaDto> sucursalCategoriasDto = new ArrayList<>();
        PagedResponseDto<SucursalCategoriaDto> response = new PagedResponseDto<>();
        
        List<ClienteSucursalDto> clienteSucursalesDto = dao.getClienteSucursalesByParams(params);
        Integer total = dao.getCantidadClienteSucursalesByParams(params);
        Integer page = Util.isEmpty(params.getPage()) ? 0 : params.getPage();

        for (ClienteSucursalDto clienteSucursalDto : clienteSucursalesDto) {
            sucursalCategoriaDto = new SucursalCategoriaDto();
            sucursalCategoriaDto.setCategoriasDto(new ArrayList<CategoriaDto>());
            
            sucursalCategoriaDto.setSucursalDto(clienteSucursalDto);
            
            clienteCategoriasDto = clienteCategoriaDao.getClienteCategoriasByCliente(clienteSucursalDto.getClienteDto().getId());
            for (ClienteCategoriaDto clienteCategoriaDto : clienteCategoriasDto)
                sucursalCategoriaDto.getCategoriasDto().add(clienteCategoriaDto.getCategoriaDto());

            sucursalCategoriasDto.add(sucursalCategoriaDto);
        }
        
        response.setPage(page);
        response.setNext(page + 1);
        response.setPrevious(page - 1);
        response.setCurrent(Constants.SEARCH_ROWS_PER_PAGE * page + Constants.SEARCH_ROWS_PER_PAGE);
        response.setTotal(total);
        response.setResult(sucursalCategoriasDto);
        
        return response;
    }

    /**
     *
     * @param clienteSucursalDto
     * @return
     */
    public ClienteSucursalDto saveClienteSucursal(ClienteSucursalDto clienteSucursalDto) {
        return dao.saveClienteSucursal(clienteSucursalDto);
    }

    /**
     *
     * @param id
     */
    public void deleteClienteSucursal(Integer id) {
        dao.deleteClienteSucursal(id);
    }
}
