package com.minicubic.infoguiahttp.rest;

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
        resources.add(com.minicubic.infoguiahttp.rest.AuthenticationRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.CategoriaRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.ClienteCategoriaRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.ClienteRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.EstadoPublicacionRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.EstadoUsuarioRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.PublicacionRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.SucursalRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.TipoPublicacionRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.TipoUsuarioRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.UploadRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.UsuarioPerfilRest.class);
        resources.add(com.minicubic.infoguiahttp.rest.UsuarioRest.class);
        resources.add(com.minicubic.infoguiahttp.util.SecurityFilter.class);
        resources.add(com.minicubic.infoguiahttp.util.UtilFilter.class);
    }
}
