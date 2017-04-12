package com.example.frys.waters;

import com.example.frys.waters.controllers.WaterPurityReportActivity;
import com.example.frys.waters.model.WaterPurityReport;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joon1 on 2017-04-10.
 */
public class JoonTest {
    private WaterPurityReportActivity r = new WaterPurityReportActivity();
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