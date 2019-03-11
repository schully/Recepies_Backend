/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import xyz.admin.entities.Comment;
import xyz.admin.sessionbeans.CommentFacade;

/**
 *
 * @author Daniel GV
 */

@Named(value = "Comment")
@SessionScoped
public class CommentBean implements Serializable{
    @EJB
    CommentFacade commentFacade;
    
    /**
     * Deletes a comment, very dangerous, much GDPR.
     * @param commentId Id of comment to be deleted
     */
    public void deleteComment(Comment comment) {
        if (comment == null) {
            return;
        }
        
        commentFacade.remove(comment);
    }
}
