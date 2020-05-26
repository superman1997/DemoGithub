package com.dangth.bhxh.model;

import org.junit.Assert;
import org.junit.Test;

public class IdentityCardTests {
    @Test
    public void testGetSetId() {
        IdentityCard identityCard = new IdentityCard();
        long expectedResult = 1;
        identityCard.setId(1L);
        long actual = identityCard.getId();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetNumber() {
        IdentityCard identityCard = new IdentityCard();
        String expectedResult = "071038685";
        identityCard.setNumber("071038685");
        String actual = identityCard.getNumber();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetPlace() {
        IdentityCard identityCard = new IdentityCard();
        String expectedResult = "Tuyen Quang";
        identityCard.setPlace("Tuyen Quang");
        String actual = identityCard.getPlace();
        Assert.assertEquals(expectedResult, actual);
    }
}
