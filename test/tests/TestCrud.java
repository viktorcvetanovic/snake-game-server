/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import cruds.CrudBestscores;
import cruds.CrudSkin;
import cruds.CrudUser;
import entity.Bestscores;
import entity.Skins;
import entity.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vikto
 */
public class TestCrud {

    public TestCrud() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetAllUsers() {
        List<User> list = CrudUser.getAllUsers();
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetSkin() {
        Skins s = CrudSkin.getSkin(1);
        assertTrue(s != null);
    }

    @Test
    public void testGetAllBestscores() {
        List<Bestscores> list = CrudBestscores.getAllBestScores();
        assertTrue(list.size() > 0);
    }
//
//    @Test
//    public void testAddUserAndGetUser() {
//        CrudUser.addUser(new User(99, "test", "test", "test", "test", 2, null, null));
//        List<User> list = CrudUser.getAllUsers();
//        assertEquals(list.get(list.size() - 1).getUsername(), "test");
//    }

//    @Test
//    public void testDeleteUser() {
//        CrudUser.deleteUser(new User(99));
//        assertEquals(CrudUser.getUser(99), null);
//    }
}
