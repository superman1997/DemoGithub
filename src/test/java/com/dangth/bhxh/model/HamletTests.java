package com.dangth.bhxh.model;

import com.dangth.bhxh.model.location.Hamlet;
import org.junit.Assert;
import org.junit.Test;

public class HamletTests {
    @Test
    public void testGetSetIdCommune() {
        Hamlet hamlet = new Hamlet();
        String expectedResult = "H112";
        hamlet.setIdcommune("H112");
        String actual = hamlet.getIdcommune();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetId() {
        Hamlet hamlet = new Hamlet();
        String expectedResult = "H112";
        hamlet.setIdhamlet("H112");
        String actual = hamlet.getIdhamlet();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetHamlet() {
        Hamlet hamlet = new Hamlet();
        String expectedResult = "Thanh Xuan Bac";
        hamlet.setHamlet("Thanh Xuan Bac");
        String actual = hamlet.getHamlet();
        Assert.assertEquals(expectedResult, actual);
    }
}
