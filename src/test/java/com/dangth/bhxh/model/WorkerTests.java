package com.dangth.bhxh.model;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkerTests {
    @Test
    public void testGetSetId() {
        Worker worker = new Worker();
        long expectedResult = 1;
        worker.setId(1L);
        long actual = worker.getId();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetNumber() {
        Worker worker = new Worker();
        String expectedResult = "Tran Hai Dang";
        worker.setFullName("Tran Hai Dang");
        String actual = worker.getFullName();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetBirthDate() {
        Worker worker = new Worker();
        java.util.Date expectedResult = Date.valueOf("1997-01-20");
        worker.setBirthDate(Date.valueOf("1997-01-20"));
        java.util.Date actual = worker.getBirthDate();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetGender() {
        Worker worker = new Worker();
        Integer expectedResult = 1;
        worker.setGender(1);
        Integer actual = worker.getGender();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetPhoneNumber() {
        Worker worker = new Worker();
        String expectedResult = "0377289069";
        worker.setPhoneNumber("0377289069");
        String actual = worker.getPhoneNumber();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetEmail() {
        Worker worker = new Worker();
        String expectedResult = "tranhaidang2320@gmail.com";
        worker.setEmail("tranhaidang2320@gmail.com");
        String actual = worker.getEmail();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetMsbh() {
        Worker worker = new Worker();
        String expectedResult = "1556527910970";
        worker.setMsbh("1556527910970");
        String actual = worker.getMsbh();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetSalary() {
        Worker worker = new Worker();
        Double expectedResult = 1000000D;
        worker.setSalary(1000000D);
        Double actual = worker.getSalary();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetPc() {
        Worker worker = new Worker();
        Double expectedResult = 1000000D;
        worker.setPc(1000000D);
        Double actual = worker.getPc();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetHt() {
        Worker worker = new Worker();
        Double expectedResult = 1000000D;
        worker.setHt(1000000D);
        Double actual = worker.getHt();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetZone() {
        Worker worker = new Worker();
        Integer expectedResult = 1;
        worker.setZone(1);
        Integer actual = worker.getZone();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testGetSetWorkplace() {
        Worker worker = new Worker();
        String expectedResult = "VCCorp";
        worker.setWorkplace("VCCorp");
        String actual = worker.getWorkplace();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetAddress() {
        Worker worker = new Worker();
        Address expectedResult = new Address(1, "Ha Noi", "Thanh Xuan", "TXB", "To 15");
        worker.setAddress(new Address(1, "Ha Noi", "Thanh Xuan", "TXB", "To 15"));
        Address actual = worker.getAddress();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testGetSetIdentityCard() {
        Worker worker = new Worker();
        IdentityCard expectedResult = new IdentityCard(1L, "071038685", "Thanh Xuan", new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime());
        worker.setIdentityCard(new IdentityCard(1L, "071038685", "Thanh Xuan", new GregorianCalendar(2019, Calendar.JANUARY, 3).getTime()));
        IdentityCard actual = worker.getIdentityCard();
        Assert.assertEquals(expectedResult, actual);
    }

}
