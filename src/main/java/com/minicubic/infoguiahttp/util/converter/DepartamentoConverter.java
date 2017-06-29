package com.minicubic.infoguiahttp.util.converter;

import com.minicubic.infoguiahttp.dto.DepartamentoDto;
import com.minicubic.infoguiahttp.model.Departamento;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1 - 11.05.2017
 */
public class DepartamentoConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param departamentos
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<DepartamentoDto> getDepartamentosDto(List<Departamento> departamentos) 
            throws IllegalAccessException, InvocationTargetException {
        List<DepartamentoDto> departamentosDto = new ArrayList<>();
        
        for (Departamento departamento : departamentos) {
            departamentosDto.add(getDepartamentoDto(departamento));
        }
        
        return departamentosDto;
    }
    
    /**
     * 
     * @param departamentosDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<Departamento> getDepartamentos(List<DepartamentoDto> departamentosDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<Departamento> departamentos = new ArrayList<>();
        
        for (DepartamentoDto departamentoDto : departamentosDto) {
            departamentos.add(getDepartamento(departamentoDto));
        }
        
        return departamentos;
    }
 
    /**
     * 
     * @param departamentoDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public Departamento getDepartamento(DepartamentoDto departamentoDto) 
            throws IllegalAccessException, InvocationTargetException {
        Departamento departamento = new Departamento();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(departamento, departamentoDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        return departamento;
    }
    
    /**
     * 
     * @param departamento
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public DepartamentoDto getDepartamentoDto(Departamento departamento) 
            throws IllegalAccessException, InvocationTargetException {
        DepartamentoDto departamentoDto = new DepartamentoDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(departamentoDto, departamento);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        return departamentoDto;
    }
}
