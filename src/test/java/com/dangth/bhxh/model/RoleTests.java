package com.dangth.bhxh.model;

import com.dangth.bhxh.model.admin.Role;
import org.junit.Assert;
import org.junit.Test;

public class RoleTests {
    @Test
    public void testGetSetId() {
        Role role = new Role();
        int expectedResult = 1;
        role.setId(1);
        int actual = role.getId();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetRole() {
        Role role = new Role();
        String expectedResult = "ADMIN";
        role.setRole("ADMIN");
        String actual = role.getRole();
        Assert.assertEquals(expectedResult, actual);
    }
}
