package com.bhattaraibikash.erepair;

import com.bhattaraibikash.erepair.bll.RequestBLL;
import com.bhattaraibikash.erepair.models.Request;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ProRequestTest {
    @Test
    public void testProRequest(){
        Request request = new Request("Ramesh Thapa", "ramesh@test.com", "Koteshwor, Kathmandu", "9849940000", "Skill1, Skill2");
        RequestBLL requestBLL = new RequestBLL();
        boolean result = requestBLL.sendRequest(request);
        assertEquals(true, result);
    }
}
