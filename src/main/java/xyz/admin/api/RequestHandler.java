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
    private RequestFacade requestefacaaa = new RequestFacade();

    @GET
    @Path("/hello/{name}")
    @Produces("application/json")
    public Response hello(@PathParam("name") String name) {
        JSONObject obj = new JSONObject();

        obj.put("message", String.format("Hello, %s", name));

        return Response.ok(obj.toString()).build();
    }

    @POST
    @Path("/hello/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response postRecipe(String bodyJson) {
        JSONObject body = new JSONObject(bodyJson);
        int createdRecipeId = 1337;
        try {
            String name = body.getString("name");
            String descriptions = body.getString("description");
            String instructions = body.getString("instructions");

            //  insertRecipeIntroDb(bane, descriptionm instrucitons)
            createdRecipeId = requestefacaaa.insertRecipe(name, descriptions, instructions);

        } catch (JSONException ex) {
            ex.printStackTrace();
            //  return response status 400
            return Response.status(400).build();
        }


        JSONObject output = new JSONObject();

        output.put("recipe_id", createdRecipeId);

        return Response.ok(output.toString()).build();
    }
}
