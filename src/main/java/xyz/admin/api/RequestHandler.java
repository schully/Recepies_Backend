/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Daniel GV
 */
@Path("")
public class RequestHandler {
    private RequestFacade requestFacade = new RequestFacade();

    @GET
    @Path("/recipe/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("name") String name) {
        JSONObject obj = new JSONObject();

        obj.put("message", String.format("Hello, %s", name));

        return Response.ok(obj.toString()).build();
    }

    @POST
    @Path("/addrecipe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postRecipe(String bodyJson) {
        JSONObject body = new JSONObject(bodyJson);
        int createdRecipeId = 1337;
        String name, descriptio, instructions;
        try {
            name = body.getString("name");
            descriptio = body.getString("description");
            instructions = body.getString("instructions");

            //  insertRecipeIntroDb(bane, descriptionm instrucitons)
            createdRecipeId = requestFacade.insertRecipe(name, descriptio, instructions);

        } catch (JSONException ex) {
            ex.printStackTrace();
            //  return response status 400
            return Response.status(400).build();
        }


        JSONObject output = new JSONObject();
        JSONObject recipe = new JSONObject();
        recipe.put("id", createdRecipeId);
        recipe.put("name", name);
        recipe.put("description", descriptio);
        recipe.put("instructions", instructions);
        
        output.put("recipe", recipe);

        return Response.ok(output.toString()).build();
    }
}
