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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Daniel GV
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({xyz.admin.utils.auth.CredentialsIT.class, xyz.admin.utils.auth.AuthServiceIT.class, xyz.admin.utils.auth.AuthFilterIT.class, xyz.admin.utils.auth.CredentialFacadeIT.class})
public class AuthITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
