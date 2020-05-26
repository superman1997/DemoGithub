package com.dangth.bhxh.model;

import com.dangth.bhxh.model.location.Commune;
import org.junit.Assert;
import org.junit.Test;

public class CommuneTests {
    @Test
    public void testGetSetId() {
        Commune commune = new Commune();
        String expectedResult = "HTT1";
        commune.setIdcommune("HTT1");
        String actual = commune.getIdcommune();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetIdProvince() {
        Commune commune = new Commune();
        String expectedResult = "HTT1";
        commune.setIdprovince("HTT1");
        String actual = commune.getIdprovince();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetCommune() {
        Commune commune = new Commune();
        String expectedResult = "Thanh Xuan Bac";
        commune.setCommune("Thanh Xuan Bac");
        String actual = commune.getCommune();
        Assert.assertEquals(expectedResult, actual);
    }
}
