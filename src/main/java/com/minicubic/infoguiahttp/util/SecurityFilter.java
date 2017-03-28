package com.minicubic.infoguiahttp.util;

import com.minicubic.infoguiacore.util.Util;
import com.minicubic.infoguiahttp.annotations.LoggedIn;
import com.minicubic.infoguiahttp.annotations.Secured;
import java.io.IOException;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;

/**
 *
 * @author xergio
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    @Inject
    @LoggedIn
    Event<Long> loggedInEvent;
    
    @Inject
    UsuarioProducer producer;
    
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

                loggedInEvent.fire(Long.valueOf(Util.getClaims(token).getSubject()));
            }
        } catch (Exception e) {
            throw new NotAuthorizedException(e.getMessage());
        }
    }
}
