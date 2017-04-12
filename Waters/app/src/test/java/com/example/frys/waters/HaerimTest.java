package com.example.frys.waters;

import com.example.frys.waters.controllers.Registration;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HaerimTest {
    private final Registration r = new Registration();

    @Test
    public void isValidEmaailTest() throws Exception {
        String email = "hello@gmail.com";
        Assert.assertEquals(true, r.isValidEmail(email));
    }

    @Test
    public void isValidEmailTest2() throws Exception {
        String email = "hello192";
        Assert.assertEquals(false, r.isValidEmail(email));
    }

    @Test
    public void isValidEmailTest3() throws Exception {
        String email = "joon1013.com";
        Assert.assertEquals(false, r.isValidEmail(email));
    }

    @Test
    public void isValidEmailTest4() throws Exception {
        String email = "hello@.org";
        Assert.assertEquals(false, r.isValidEmail(email));
    }

    @Test
    public void isValidEmailTest5() throws Exception {
        String email = "";
        Assert.assertEquals(false, r.isValidEmail(email));
    }
}