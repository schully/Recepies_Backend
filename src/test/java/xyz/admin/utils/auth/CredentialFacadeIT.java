/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

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
public class CredentialFacadeIT {
    
    public CredentialFacadeIT() {
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
     * Test of createCredentials method, of class CredentialFacade.
     */
    @Test
    public void testCreateCredentials() {
        System.out.println("createCredentials");
        String basicAuth = "";
        Credentials expResult = null;
        Credentials result = CredentialFacade.createCredentials(basicAuth);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class CredentialFacade.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Credentials credentials = null;
        boolean expResult = false;
        boolean result = CredentialFacade.save(credentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class CredentialFacade.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        String username = "";
        Credentials expResult = null;
        Credentials result = CredentialFacade.get(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verify method, of class CredentialFacade.
     */
    @Test
    public void testVerify() throws Exception {
        System.out.println("verify");
        String username = "";
        String password = "";
        boolean expResult = false;
        boolean result = CredentialFacade.verify(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
