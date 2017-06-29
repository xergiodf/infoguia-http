package com.minicubic.infoguiahttp.util;

import com.minicubic.infoguiahttp.dto.UsuarioDto;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;

/**
 *
 * @author xergio
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());

    @Inject
    @LoggedIn
    Event<Integer> loggedInEvent;

    @LoggedIn
    @Inject
    private UsuarioDto usuarioLogueado;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        try {

            // Check if the HTTP Authorization header is present and formatted correctly 
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                throw new NotAuthorizedException("401");
            }

            // Extract the token from the HTTP Authorization header
            String token = authHeader.substring("Bearer".length()).trim();

            if (!Util.verifyToken(token)) {

                throw new NotAuthorizedException("401");
            } else {

                loggedInEvent.fire(Integer.valueOf(Util.getClaims(token).getSubject()));
            }

            ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
            Method method = methodInvoker.getMethod();

            // Accesible para todos
            if (!method.isAnnotationPresent(PermitAll.class)) {
                // Denegado para todos
                if (method.isAnnotationPresent(DenyAll.class)) {
                    requestContext.abortWith(ACCESS_FORBIDDEN);
                }

                // Verificamos los roles
                if (method.isAnnotationPresent(RolesAllowed.class)) {
                    RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                    Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

                    //Is user valid?
                    if (!isUserAllowed(rolesSet)) {
                        requestContext.abortWith(ACCESS_DENIED);
                    }
                }
            }
        } catch (Exception e) {
            throw new NotAuthorizedException(e.getMessage());
        }
    }

    private boolean isUserAllowed(final Set<String> rolesSet) {
        boolean isAllowed = false;

        if (rolesSet.contains(usuarioLogueado.getTipoUsuarioDto().getId().toString())) {
            isAllowed = true;
        }

        return isAllowed;
    }
}
