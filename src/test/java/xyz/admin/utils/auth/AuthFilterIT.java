/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import javax.ws.rs.container.ContainerRequestContext;
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
public class AuthFilterIT {
    
    public AuthFilterIT() {
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
     * Test of filter method, of class AuthFilter.
     */
    @Test
    public void testFilter() throws Exception {
        System.out.println("filter");
        ContainerRequestContext requestContext = null;
        AuthFilter instance = new AuthFilter();
        instance.filter(requestContext);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
