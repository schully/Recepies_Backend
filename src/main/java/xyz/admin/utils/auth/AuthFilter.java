/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Daniel GV
 */
@Provider
public class AuthFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getMethod().equals("POST")) {
            return;
        }
        String authHeader = requestContext.getHeaderString("authorization");
        if (authHeader == null) {
            authHeader = "";
        }
        if(authHeader.length() > 0) {
            try {
                Credentials credentials = CredentialFacade.createCredentials(authHeader);
                if(CredentialFacade.verify(credentials.getUsername(), credentials.getPassword())){
                    return;
                }
            } catch (Exception e) {
                System.out.println("AuthFilterError: "+e.getMessage());
            }
        }
        Response unAuth = Response.status(Response.Status.UNAUTHORIZED).entity("wrong credentials").build();
        
        requestContext.abortWith(unAuth);
    }
    
}
