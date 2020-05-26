package com.dangth.bhxh.model;

import org.junit.Assert;
import org.junit.Test;

public class AddressTests {

    @Test
    public void testGetSetId() {
        Address address = new Address();
        long expectedResult = 1;
        address.setId(expectedResult);
        long actual = address.getId();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetCity() {
        Address address = new Address();
        String expectedResult = "Tuyen Quang";
        address.setTinh("Tuyen Quang");
        String actual = address.getTinh();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetProvince() {
        Address address = new Address();
        String expectedResult = "Ham Yen";
        address.setHuyen("Ham Yen");
        String actual = address.getHuyen();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetCommune() {
        Address address = new Address();
        String expectedResult = "Tan Tien";
        address.setPhuong("Tan Tien");
        String actual = address.getPhuong();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetHamlet() {
        Address address = new Address();
        String expectedResult = "13";
        address.setXa("13");
        String actual = address.getXa();
        Assert.assertEquals(expectedResult, actual);
    }
}
