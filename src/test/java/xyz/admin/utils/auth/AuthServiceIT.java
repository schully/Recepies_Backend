/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel GV
 */
public class AuthServiceIT {
    
    public AuthServiceIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUser method, of class AuthService.
     */
    @Test
    public void testCheckUser() throws Exception {
        System.out.println("checkUser");
        String authorization = "";
        AuthService instance = new AuthService();
        Response expResult = null;
        Response result = instance.checkUser(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postUser method, of class AuthService.
     */
    @Test
    public void testPostUser() throws Exception {
        System.out.println("postUser");
        String authorization = "";
        AuthService instance = new AuthService();
        Response expResult = null;
        Response result = instance.postUser(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
