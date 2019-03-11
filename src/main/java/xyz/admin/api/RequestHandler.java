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
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import xyz.admin.entities.Recipe;
import xyz.admin.entities.RecipeHasIngredient;

/**
 *
 * @author Daniel GV
 */
@Path("")
public class RequestHandler {

    @EJB
    private RequestFacade requestFacade;

    @GET
    @Path("/recipe/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("id") Integer id) {

        Recipe recipe = requestFacade.getRecipe(id);

        if (recipe == null) {
            System.out.println("SUXZ  B YU");
            return Response.status(400).build();
        }

        JSONObject obj = new JSONObject();

        obj.put("name", recipe.getName());
        obj.put("author", recipe.getUser().getUsername());
        obj.put("description", recipe.getDescription());
        obj.put("instructions", recipe.getInstructions());

        JSONArray ingredients = new JSONArray();
        for (RecipeHasIngredient ingredient : recipe.getRecipeHasIngredientCollection()) {
            JSONObject ingredientObj = new JSONObject();
            ingredientObj.put("name", ingredient.getIngredient1().getName());
            ingredientObj.put("quantity", ingredient.getQuantity());
            ingredients.put(ingredientObj);
        }
        obj.put("ingredients", ingredients);
        obj.put("category", recipe.getName());

        return Response.ok(obj.toString()).build();
    }

    @POST
    @Path("/{recipeId}/addcomment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postComment(String bodyJson, @PathParam("recipeId") String recipeId) {
        JSONObject body = new JSONObject(bodyJson);
        int userId;
        int recipeID;
        String text;
        try {
            userId = body.getInt("user");
            recipeID = body.getInt("recipe");
            text = body.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }

        JSONObject output = new JSONObject();
        JSONObject comment = new JSONObject();

        comment.put("user", userId);
        comment.put("recipe", recipeID);
        comment.put("text", text);

        output.put("comment", comment);

        return Response.ok(output.toString()).build();
    }

    @POST
    @Path("/addrecipe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postRecipe(String bodyJson, @Context ContainerRequestContext ctx) {
        JSONObject body = new JSONObject(bodyJson);
        int createdRecipeId = 1337;
        String name, description, instructions, category;
        try {
            name = body.getString("name");
            description = body.getString("description");
            instructions = body.getString("instructions");
            category = body.getString("category");
            //  insertRecipeIntroDb(bane, descriptionm instrucitons)
            createdRecipeId = requestFacade.insertRecipe(
                    ctx.getProperty("username").toString(), category, name, description, instructions
            );

        } catch (JSONException ex) {
            ex.printStackTrace();
            //  return response status 400
            return Response.status(400).build();
        }

        JSONObject output = new JSONObject();
        JSONObject recipe = new JSONObject();
        recipe.put("id", createdRecipeId);
        recipe.put("name", name);
        recipe.put("description", description);
        recipe.put("instructions", instructions);
        recipe.put("category", category);

        output.put("recipe", recipe);

        return Response.ok(output.toString()).build();
    }
}
