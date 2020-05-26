package com.dangth.bhxh.model;

import com.dangth.bhxh.model.location.Province;
import org.junit.Assert;
import org.junit.Test;

public class ProvinceTests {
    @Test
    public void testGetSetId() {
        Province province = new Province();
        String expectedResult = "CT1";
        province.setIdprovince("CT1");
        String actual = province.getIdprovince();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetIdCity() {
        Province province = new Province();
        String expectedResult = "CT1";
        province.setIdcity("CT1");
        String actual = province.getIdcity();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetProvince() {
        Province province = new Province();
        String expectedResult = "Thanh Xuan";
        province.setName("Thanh Xuan");
        String actual = province.getName();
        Assert.assertEquals(expectedResult, actual);
    }
}
