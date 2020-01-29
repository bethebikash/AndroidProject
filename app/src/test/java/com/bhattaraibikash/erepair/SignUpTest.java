package com.bhattaraibikash.erepair;

import com.bhattaraibikash.erepair.bll.SignUpBLL;
import com.bhattaraibikash.erepair.models.User;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SignUpTest {
    @Test
    public void testSignUp(){
        User user = new User("Bikash Bhattarai", "bikash@test.com", "Koteshwor, Kathmandu", "9849940443", "bikash123","admin123");
        SignUpBLL signUpBLL = new SignUpBLL();
        boolean result = signUpBLL.checkRegister(user);
        assertEquals(true, result);
    }
}
