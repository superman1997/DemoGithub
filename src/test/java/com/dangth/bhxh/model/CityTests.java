package com.dangth.bhxh.model;

import com.dangth.bhxh.model.admin.Role;
import com.dangth.bhxh.model.location.City;
import org.junit.Assert;
import org.junit.Test;

public class CityTests {
    @Test
    public void testGetSetId() {
        City city = new City();
        String expectedResult = "CT1";
        city.setIdcity("CT1");
        String actual = city.getIdcity();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetCity() {
        City city = new City();
        String expectedResult = "Ha Noi";
        city.setCity("Ha Noi");
        String actual = city.getCity();
        Assert.assertEquals(expectedResult, actual);
    }
}
