package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.GrupoCategoriaDto;
import com.minicubic.infoguiacore.model.GrupoCategoria;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1 - 07/04/2017
 */
public class GrupoCategoriaConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param grupoCategorias
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<GrupoCategoriaDto> getGrupoCategoriasDto(List<GrupoCategoria> grupoCategorias) 
            throws IllegalAccessException, InvocationTargetException {
        List<GrupoCategoriaDto> grupoCategoriasDto = new ArrayList<>();
        
        for (GrupoCategoria grupoCategoria : grupoCategorias) {
            grupoCategoriasDto.add(getGrupoCategoriaDto(grupoCategoria));
        }
        
        return grupoCategoriasDto;
    }
    
    /**
     * 
     * @param grupoCategoriasDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<GrupoCategoria> getGrupoCategorias(List<GrupoCategoriaDto> grupoCategoriasDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<GrupoCategoria> grupoCategorias = new ArrayList<>();
        
        for (GrupoCategoriaDto grupoCategoriaDto : grupoCategoriasDto) {
            grupoCategorias.add(getGrupoCategoria(grupoCategoriaDto));
        }
        
        return grupoCategorias;
    }
 
    /**
     * 
     * @param grupoCategoriaDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public GrupoCategoria getGrupoCategoria(GrupoCategoriaDto grupoCategoriaDto) 
            throws IllegalAccessException, InvocationTargetException {
        GrupoCategoria grupoCategoria = new GrupoCategoria();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(grupoCategoria, grupoCategoriaDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        return grupoCategoria;
    }
    
    /**
     * 
     * @param grupoCategoria
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public GrupoCategoriaDto getGrupoCategoriaDto(GrupoCategoria grupoCategoria) 
            throws IllegalAccessException, InvocationTargetException {
        GrupoCategoriaDto grupoCategoriaDto = new GrupoCategoriaDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(grupoCategoriaDto, grupoCategoria);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return grupoCategoriaDto;
    }
}
