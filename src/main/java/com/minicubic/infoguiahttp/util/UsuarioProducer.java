package com.minicubic.infoguiahttp.util;

import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.services.UsuarioService;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author xergio
 */
@RequestScoped
public class UsuarioProducer {
    
    @Produces
    @RequestScoped
    @LoggedIn
    private UsuarioDto usuario;
    
    @Inject
    private UsuarioService service;
    
    public void handleLoggedInEvent(@Observes @LoggedIn Long id) {
        this.usuario = getUsuario(id);
    }
    
    private UsuarioDto getUsuario(Long id) {
        return service.getUsuario(id);
    }
}
