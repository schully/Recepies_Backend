/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel GV
 */
@Path("user")
public class AuthService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUser(@HeaderParam("Authorization") String authorization) {
        
    }
}
