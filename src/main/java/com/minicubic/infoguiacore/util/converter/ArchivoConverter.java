package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.ArchivoCabDto;
import com.minicubic.infoguiacore.dto.ArchivoDetDto;
import com.minicubic.infoguiacore.model.ArchivoCab;
import com.minicubic.infoguiacore.model.ArchivoDet;
import com.minicubic.infoguiacore.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1 - 18/04/2017
 */
public class ArchivoConverter {
    
    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();
    
    /**
     * 
     * @param archivosCab
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ArchivoCabDto> getArchivosCabDto(List<ArchivoCab> archivosCab) 
            throws IllegalAccessException, InvocationTargetException {
        List<ArchivoCabDto> archivosCabDto = new ArrayList<>();
        
        for (ArchivoCab archivoCab : archivosCab) {
            archivosCabDto.add(getArchivoCabDto(archivoCab));
        }
        
        return archivosCabDto;
    }
    
    /**
     * 
     * @param archivosCabDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public List<ArchivoCab> getArchivosCab(List<ArchivoCabDto> archivosCabDto) 
            throws IllegalAccessException, InvocationTargetException {
        List<ArchivoCab> archivosCab = new ArrayList<>();
        
        for (ArchivoCabDto archivoCabDto : archivosCabDto) {
            archivosCab.add(getArchivoCab(archivoCabDto));
        }
        
        return archivosCab;
    }
 
    /**
     * 
     * @param archivoCabDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ArchivoCab getArchivoCab(ArchivoCabDto archivoCabDto) 
            throws IllegalAccessException, InvocationTargetException {
        ArchivoCab archivoCab = new ArchivoCab();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(archivoCab, archivoCabDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(archivoCabDto.getTipoArchivoDto()) )
            archivoCab.setTipoArchivo(new TipoArchivoConverter().getTipoArchivo(archivoCabDto.getTipoArchivoDto()));
        
        if ( !Util.isEmpty(archivoCabDto.getArchivosDetDto()) ) {
            if ( !archivoCabDto.getArchivosDetDto().isEmpty() )
                archivoCab.setArchivosDet(getArchivosDet(archivoCabDto.getArchivosDetDto(), archivoCab));
        }

        return archivoCab;
    }
    
    /**
     * 
     * @param archivoCab
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public ArchivoCabDto getArchivoCabDto(ArchivoCab archivoCab) 
            throws IllegalAccessException, InvocationTargetException {
        ArchivoCabDto archivoCabDto = new ArchivoCabDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(archivoCabDto, archivoCab);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        // Copiamos las propiedades compuestas
        if ( !Util.isEmpty(archivoCab.getTipoArchivo()) )
            archivoCabDto.setTipoArchivoDto(new TipoArchivoConverter().getTipoArchivoDto(archivoCab.getTipoArchivo()));
        
        if ( !Util.isEmpty(archivoCab.getArchivosDet()) ) {
            if ( !archivoCab.getArchivosDet().isEmpty() ) 
                archivoCabDto.setArchivosDetDto(getArchivosDetDto(archivoCab.getArchivosDet()));
        }
        
        return archivoCabDto;
    }
    
    /**
     * 
     * @param archivosDet
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    private List<ArchivoDetDto> getArchivosDetDto(List<ArchivoDet> archivosDet) 
            throws IllegalAccessException, InvocationTargetException {
        List<ArchivoDetDto> archivosDetDto = new ArrayList<>();
        
        for (ArchivoDet archivoDet : archivosDet) {
            archivosDetDto.add(getArchivoDetDto(archivoDet));
        }
        
        return archivosDetDto;
    }
    
    /**
     * 
     * @param archivosDetDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    private List<ArchivoDet> getArchivosDet(List<ArchivoDetDto> archivosDetDto, ArchivoCab archivoCab) 
            throws IllegalAccessException, InvocationTargetException {
        List<ArchivoDet> archivosDet = new ArrayList<>();
        
        for (ArchivoDetDto archivoDetDto : archivosDetDto) {
            archivosDet.add(getArchivoDet(archivoDetDto, archivoCab));
        }
        
        return archivosDet;
    }
    
    /**
     * 
     * @param archivoDetDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    private ArchivoDet getArchivoDet(ArchivoDetDto archivoDetDto, ArchivoCab archivoCab) 
            throws IllegalAccessException, InvocationTargetException {
        ArchivoDet archivoDet = new ArchivoDet();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(archivoDet, archivoDetDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        archivoDet.setArchivoCab(archivoCab);
        
        return archivoDet;
    }
    
    private ArchivoDetDto getArchivoDetDto(ArchivoDet archivoDet) 
            throws IllegalAccessException, InvocationTargetException {
        ArchivoDetDto archivoDetDto = new ArchivoDetDto();
        
        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(archivoDetDto, archivoDet);
        } catch (NoSuchMethodException ex) {
            // No importa
        }
        
        return archivoDetDto;
    }
}
