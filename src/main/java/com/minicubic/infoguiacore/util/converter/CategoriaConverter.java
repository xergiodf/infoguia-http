package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.CategoriaDto;
import com.minicubic.infoguiacore.model.Categoria;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class CategoriaConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param categorias
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<CategoriaDto> getCategoriasDto(List<Categoria> categorias) 
            throws IllegalAccessException, InvocationTargetException {
        List<CategoriaDto> categoriasDto = new ArrayList<>();
        
        for (Categoria categoria : categorias) {
            categoriasDto.add(getCategoriaDto(categoria));
        }
        
        return categoriasDto;
    }
    
    /**
     * 
     * @param categoriasDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<Categoria> getCategorias(List<CategoriaDto> categoriasDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<Categoria> categorias = new ArrayList<>();
        
        for (CategoriaDto categoriaDto : categoriasDto) {
            categorias.add(getCategoria(categoriaDto));
        }
        
        return categorias;
    }
 
    /**
     * 
     * @param categoriaDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public Categoria getCategoria(CategoriaDto categoriaDto) 
            throws IllegalAccessException, InvocationTargetException {
        Categoria categoria = new Categoria();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(categoria, categoriaDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        return categoria;
    }
    
    /**
     * 
     * @param categoria
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public CategoriaDto getCategoriaDto(Categoria categoria) 
            throws IllegalAccessException, InvocationTargetException {
        CategoriaDto categoriaDto = new CategoriaDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(categoriaDto, categoria);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return categoriaDto;
    }
}
