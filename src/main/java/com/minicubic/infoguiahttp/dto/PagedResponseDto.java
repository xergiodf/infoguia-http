package com.minicubic.infoguiahttp.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sedf
 */
public class PagedResponseDto<T> {
    
    @Getter
    @Setter
    private Integer page;
    
    @Getter
    @Setter
    private Integer next;
    
    @Getter
    @Setter
    private Integer previous;
    
    @Getter
    @Setter
    private Integer total;
    
    @Getter
    @Setter
    private Integer current;
    
    @Getter
    @Setter
    private List<T> result;
}
