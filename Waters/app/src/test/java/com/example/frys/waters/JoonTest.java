package com.example.frys.waters;

import com.example.frys.waters.controllers.WaterPurityReportActivity;
import junit.framework.Assert;
import org.junit.Test;

public class JoonTest {
    private final WaterPurityReportActivity r = new WaterPurityReportActivity();
    @Test
    public void isValidPPMTest() throws Exception {
        String ppm = "0.0";
        Assert.assertEquals(true, r.isValidPPM(ppm));
    }

    @Test
    public void isValidPPMTest2() throws Exception {
        String ppm = "4#";
        Assert.assertEquals(false, r.isValidPPM(ppm));
    }

    @Test
    public void isValidPPMTest3() throws Exception {
        String ppm = "4000";
        Assert.assertEquals(true, r.isValidPPM(ppm));
    }

    @Test
    public void isValidPPMTest4() throws Exception {
        String ppm = "";
        Assert.assertEquals(false, r.isValidPPM(ppm));
    }

    @Test
    public void isValidPPMTest5() throws Exception {
        String ppm = "hello";
        Assert.assertEquals(false, r.isValidPPM(ppm));
    }

}