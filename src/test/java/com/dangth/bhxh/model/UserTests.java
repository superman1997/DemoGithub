package com.dangth.bhxh.model;

import com.dangth.bhxh.model.admin.Role;
import com.dangth.bhxh.model.admin.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UserTests {

    @Test
    public void testGetSetId() {
        User user = new User();
        int expectedResult = 1;
        user.setId(1);
        int actual = user.getId();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetEmail() {
        User user = new User();
        String expectedResult = "dangth@gmail.com";
        user.setEmail("dangth@gmail.com");
        String actual = user.getEmail();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetPassword() {
        User user = new User();
        String expectedResult = "12345678";
        user.setPassword("12345678");
        String actual = user.getPassword();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetRole() {
        User user = new User();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1, "ADMIN"));
        roleSet.add(new Role(2, "MEMBER"));
        Set<Role> expectedResult = new HashSet<>();
        expectedResult.add(new Role(1, "ADMIN"));
        expectedResult.add(new Role(2, "MEMBER"));
        user.setRoles(roleSet);
        Set<Role> actual = user.getRoles();
        Assert.assertEquals(expectedResult, actual);
    }

}
