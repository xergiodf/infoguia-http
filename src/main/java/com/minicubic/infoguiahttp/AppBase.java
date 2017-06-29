package com.minicubic.infoguiahttp;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author xergio
 * @version 1
 */
@ApplicationPath("api")
public class AppBase extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.minicubic.infoguiahttp.ArchivoRest.class);
        resources.add(com.minicubic.infoguiahttp.AuthenticationRest.class);
        resources.add(com.minicubic.infoguiahttp.CategoriaRest.class);
        resources.add(com.minicubic.infoguiahttp.CiudadRest.class);
        resources.add(com.minicubic.infoguiahttp.ClienteCategoriaRest.class);
        resources.add(com.minicubic.infoguiahttp.ClienteRest.class);
        resources.add(com.minicubic.infoguiahttp.DepartamentoRest.class);
        resources.add(com.minicubic.infoguiahttp.EstadoPublicacionRest.class);
        resources.add(com.minicubic.infoguiahttp.EstadoUsuarioRest.class);
        resources.add(com.minicubic.infoguiahttp.PublicacionRest.class);
        resources.add(com.minicubic.infoguiahttp.SearchRest.class);
        resources.add(com.minicubic.infoguiahttp.SucursalRest.class);
        resources.add(com.minicubic.infoguiahttp.TipoHorarioRest.class);
        resources.add(com.minicubic.infoguiahttp.TipoPublicacionRest.class);
        resources.add(com.minicubic.infoguiahttp.TipoUsuarioRest.class);
        resources.add(com.minicubic.infoguiahttp.UsuarioPerfilRest.class);
        resources.add(com.minicubic.infoguiahttp.UsuarioRest.class);
        resources.add(com.minicubic.infoguiahttp.ValoracionRest.class);
        resources.add(com.minicubic.infoguiahttp.util.SecurityFilter.class);
        resources.add(com.minicubic.infoguiahttp.util.UtilFilter.class);
    }
}
