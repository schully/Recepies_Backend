/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import javax.ws.rs.core.Response;
import jersey.repackaged.com.google.common.base.CharMatcher;
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
        String authorization = "Basic Z3RiZW46NDQ0NA=="; //Basic  username:password
        AuthService instance = new AuthService();
        Response result = instance.checkUser(authorization);
        System.out.println("result: " + result);
        assertEquals(result.getStatus(), 200);
    }

    @Test
    public void testCheckUserInvalid() throws Exception {
        System.out.println("checkUserInvalid");
        //String authorization = "Basic Z3RiZW46NDQ0NA=="; //Basic  username:password
        String authorization = "Basic Z3RiZW46NTU1NQ=="; //Basic  username:password
        AuthService instance = new AuthService();
        Response result = instance.checkUser(authorization);

        assertEquals(result.getStatus(), 401);
    }


    @Test
    public void testPostUserInvalid() throws Exception {
        System.out.println("postUserInvalid");
        String authorization = "Basic Z3RiZW46NTU1NQ==";
        AuthService instance = new AuthService();
        Response result = instance.postUser(authorization);
        assertEquals(result.getStatus(), 400);
    }

}
