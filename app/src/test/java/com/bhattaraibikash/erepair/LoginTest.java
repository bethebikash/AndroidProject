package com.bhattaraibikash.erepair;

import com.bhattaraibikash.erepair.bll.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class LoginTest {
    @Test
    public void testLogin(){
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("username", "Admin123");
        assertEquals(true, result);
    }
}
