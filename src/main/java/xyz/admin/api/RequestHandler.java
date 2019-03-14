/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.api;

import java.util.List;
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
import xyz.admin.entities.Comment;
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
    @Path("/{recipeid}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@PathParam("recipeid") Integer id) {
        List<Comment> comments = requestFacade.getComments(id);

        JSONArray json = new JSONArray();

        for (Comment c : comments) {
            JSONObject cObj = new JSONObject();
            cObj.put("text", c.getText());
            json.put(cObj);
        }

        return Response.ok(json.toString()).build();
    }

    @GET
    @Path("/recipes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipes() {
        List<Recipe> recipes = requestFacade.getRecipes();

        JSONArray json = new JSONArray();

        for (Recipe r : recipes) {
            JSONObject rObj = new JSONObject();
            rObj.put("name", r.getName());
            rObj.put("id", r.getId());
            rObj.put("imageUrl", r.getPicture());
            json.put(rObj);
        }

        return Response.ok(json.toString()).build();
    }

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
        obj.put("picture", recipe.getPicture());
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
        obj.put("category", recipe.getCategory().getName());

        return Response.ok(obj.toString()).build();
    }

    @POST
    @Path("/{recipeId}/addcomment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postComment(String bodyJson, @PathParam("recipeId") int recipeId, @Context ContainerRequestContext request) {
        JSONObject body = new JSONObject(bodyJson);
        int userId = (int) request.getProperty("id");
        String text;

        try {
            text = body.getString("text");
            requestFacade.postComment(userId, recipeId, text);
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }

        JSONObject output = new JSONObject();
        JSONObject comment = new JSONObject();

        comment.put("user", userId);
        comment.put("recipe", recipeId);
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
        String name, description, instructions, category, quantity;
        try {
            name = body.getString("name");
            description = body.getString("description");
            instructions = body.getString("instructions");
            category = body.getString("category");
            createdRecipeId = requestFacade.insertRecipe(
                    ctx.getProperty("username").toString(),
                    name,
                    category,
                    description,
                    instructions
            );

        } catch (JSONException ex) {
            ex.printStackTrace();
            //  return response status 400
            return Response.status(400).build();
        }

        JSONObject output = new JSONObject();
        JSONObject ingredient = new JSONObject();
        JSONObject recipe = new JSONObject();

//        ingredient.put("quantity", quantity);
        recipe.put("id", createdRecipeId);
        recipe.put("name", name);
        recipe.put("description", description);
        recipe.put("instructions", instructions);
        recipe.put("category", category);

        output.put("recipe", recipe);

        return Response.ok(output.toString()).build();
    }
}
