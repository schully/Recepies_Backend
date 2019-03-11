/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import xyz.admin.entities.Category;
import xyz.admin.sessionbeans.CategoryFacade;

/**
 *
 * @author Daniel GV
 */

@Named(value = "Category")
@SessionScoped
public class CategoryBean implements Serializable{

    @EJB
    CategoryFacade categoryFacade;

    public List<Category> getCategories() {
        List<Category> cats = categoryFacade.findAll();
        return cats;
    }
}
