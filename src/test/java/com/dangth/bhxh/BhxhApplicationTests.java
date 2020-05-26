package com.dangth.bhxh;

import com.dangth.bhxh.dto.DTOUtils;
import com.dangth.bhxh.dto.WorkerDTO;
import com.dangth.bhxh.model.Address;
import com.dangth.bhxh.model.IdentityCard;
import com.dangth.bhxh.model.Worker;
import com.dangth.bhxh.model.admin.Role;
import com.dangth.bhxh.model.admin.User;
import com.dangth.bhxh.repository.UserRepository;
import com.dangth.bhxh.repository.WorkerRepository;
import com.dangth.bhxh.utils.Calculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BhxhApplicationTests {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    UserRepository userRepository;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testFindWorkerByMsbh() {
        Worker expectedResult = new Worker();
        expectedResult.setId(5L);
        expectedResult.setFullName("Trần Hải Đăng");
        expectedResult.setEmail("tranhaidang2320@live.com");
        expectedResult.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        expectedResult.setGender(1);
        expectedResult.setType(Calculator.NORMAL_TYPE);
        expectedResult.setPhoneNumber("0377289069");
        expectedResult.setMsbh("1556527910970");
        expectedResult.setSalary((double) 15030000);
        expectedResult.setPc((double) 1070000);
        expectedResult.setHt((double) 5000000);
        expectedResult.setZone(2);
        expectedResult.setWorkplace("VCCorp");
        expectedResult.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        expectedResult.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        Worker actual = workerRepository.findByMsbh("1556527910970");
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testFindWorkerByIdentityCardNumber() {
        Worker expectedResult = new Worker();
        expectedResult.setId(5L);
        expectedResult.setFullName("Trần Hải Đăng");
        expectedResult.setEmail("tranhaidang2320@live.com");
        expectedResult.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        expectedResult.setGender(1);
        expectedResult.setPhoneNumber("0377289069");
        expectedResult.setMsbh("1556527910970");
        expectedResult.setSalary((double) 15030000);
        expectedResult.setPc((double) 1070000);
        expectedResult.setHt((double) 5000000);
        expectedResult.setZone(2);
        expectedResult.setType(Calculator.NORMAL_TYPE);
        expectedResult.setWorkplace("VCCorp");
        expectedResult.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        expectedResult.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        Worker actual = workerRepository.findByIdentityCard_Number("071038685");
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    @Transactional
    public void testFindUserByEmail() {
        User actual = userRepository.findByEmail("dangth@gmail.com");
        User expectedResult = new User();
        expectedResult.setId(1);
        expectedResult.setEmail("dangth@gmail.com");
        expectedResult.setPassword("$2a$10$tfrsFQv//azBkhsCq22VD.pj/AcJJZZubmpI530.vF/vJJUji8JiW");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1, "ROLE_ADMIN"));
        expectedResult.setRoles(new HashSet<>(roles));
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testWorkerToWorkerDTO() {
        WorkerDTO expectedResult = new WorkerDTO();
        expectedResult.setId(5L);
        expectedResult.setFullName("Trần Hải Đăng");
        expectedResult.setEmail("tranhaidang2320@live.com");
        expectedResult.setBirthDate("20/01/1997");
        expectedResult.setGender(1);
        expectedResult.setPhoneNumber("0377289069");
        expectedResult.setMsbh("1556527910970");
        expectedResult.setSalary("15030000");
        expectedResult.setPc("1070000");
        expectedResult.setHt("5000000");
        expectedResult.setZone(2);
        expectedResult.setType("1");
        expectedResult.setWorkplace("VCCorp");
        expectedResult.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        expectedResult.setIdentityId(7L);
        expectedResult.setIdentityNumber("071038685");
        expectedResult.setValidFrom("14/03/2019");
        expectedResult.setPlace("08TTT");
        Worker worker = new Worker();
        worker.setId(5L);
        worker.setFullName("Trần Hải Đăng");
        worker.setEmail("tranhaidang2320@live.com");
        worker.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        worker.setGender(1);
        worker.setType(Calculator.NORMAL_TYPE);
        worker.setPhoneNumber("0377289069");
        worker.setMsbh("1556527910970");
        worker.setSalary((double) 15030000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(2);
        worker.setWorkplace("VCCorp");
        worker.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        worker.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        WorkerDTO actual = DTOUtils.toWorkerDTO(worker);
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testWorkerDTOToWorker() {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(5L);
        workerDTO.setFullName("Trần Hải Đăng");
        workerDTO.setEmail("tranhaidang2320@live.com");
        workerDTO.setBirthDate("20/01/1997");
        workerDTO.setGender(1);
        workerDTO.setPhoneNumber("0377289069");
        workerDTO.setMsbh("1556527910970");
        workerDTO.setSalary("15030000");
        workerDTO.setPc("1070000");
        workerDTO.setHt("5000000");
        workerDTO.setZone(2);
        workerDTO.setType("1");
        workerDTO.setWorkplace("VCCorp");
        workerDTO.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        workerDTO.setIdentityId(7L);
        workerDTO.setIdentityNumber("071038685");
        workerDTO.setValidFrom("14/03/2019");
        workerDTO.setPlace("08TTT");
        Worker expectedResult = new Worker();
        expectedResult.setId(5L);
        expectedResult.setFullName("Trần Hải Đăng");
        expectedResult.setEmail("tranhaidang2320@live.com");
        expectedResult.setBirthDate(new GregorianCalendar(1997, Calendar.JANUARY, 20).getTime());
        expectedResult.setGender(1);
        expectedResult.setPhoneNumber("0377289069");
        expectedResult.setMsbh("1556527910970");
        expectedResult.setSalary((double) 15030000);
        expectedResult.setPc((double) 1070000);
        expectedResult.setHt((double) 5000000);
        expectedResult.setZone(2);
        expectedResult.setType(Calculator.NORMAL_TYPE);
        expectedResult.setWorkplace("VCCorp");
        expectedResult.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        expectedResult.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",new GregorianCalendar(2019, Calendar.MARCH, 14).getTime()));
        Worker actual = DTOUtils.toWorker(workerDTO, false);
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testEncryptPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Assert.assertTrue(encoder.matches("123", "$2a$10$tfrsFQv//azBkhsCq22VD.pj/AcJJZZubmpI530.vF/vJJUji8JiW"));
    }

    @Test
    public void testEncryptPasswordFail() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Assert.assertFalse(encoder.matches("12323", "$2a$10$tfrsFQv//azBkhsCq22VD.pj/AcJJZZubmpI530.vF/vJJUji8JiW"));
    }

    @Test
    public void testCalculateFeeNormal() {
        Worker worker = new Worker();
        worker.setId(5L);
        worker.setFullName("Trần Hải Đăng");
        worker.setEmail("tranhaidang2320@live.com");
        worker.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        worker.setGender(1);
        worker.setPhoneNumber("0377289069");
        worker.setMsbh("1556527910970");
        worker.setSalary((double) 15030000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(2);
        worker.setType(Calculator.NORMAL_TYPE);
        worker.setWorkplace("VCCorp");
        worker.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        worker.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        double expectedResult = 1288000;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double)expectedResult, (Double)actual);
    }

    @Test
    public void testCalculateFeeVolunteer() {
        Worker worker = new Worker();
        worker.setId(5L);
        worker.setFullName("Trần Hải Đăng");
        worker.setEmail("tranhaidang2320@live.com");
        worker.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        worker.setGender(1);
        worker.setPhoneNumber("0377289069");
        worker.setMsbh("1556527910970");
        worker.setSalary((double) 15030000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(2);
        worker.setType(Calculator.VOLUNTEER_TYPE);
        worker.setWorkplace("VCCorp");
        worker.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        worker.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        double expectedResult = 3542000;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double)expectedResult, (Double)actual);
    }

    @Test
    public void testCalculateFeeNormalZone1True() {
        Worker worker = new Worker();
        worker.setSalary((double) 15030000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(1);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 1288000;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone1Fail() {
        Worker worker = new Worker();
        worker.setSalary((double) 1000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(1);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 0;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone2True() {
        Worker worker = new Worker();
        worker.setSalary((double) 10000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(2);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 885600;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone2Fail() {
        Worker worker = new Worker();
        worker.setSalary((double) 1000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(2);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 0;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone3True() {
        Worker worker = new Worker();
        worker.setSalary((double) 10000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(3);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 885600;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone3Fail() {
        Worker worker = new Worker();
        worker.setSalary((double) 1000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(3);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 0;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone4True() {
        Worker worker = new Worker();
        worker.setSalary((double) 10000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(4);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 885600;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeNormalZone4Fail() {
        Worker worker = new Worker();
        worker.setSalary((double) 1000000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(3);
        worker.setType(Calculator.NORMAL_TYPE);

        double expectedResult = 0;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double) expectedResult, (Double) actual);

    }

    @Test
    public void testCalculateFeeVolunteerSmallerThanMinSalary() {
        Worker worker = new Worker();
        worker.setSalary((double) 100000);
        worker.setPc((double) 100000);
        worker.setHt((double) 5000000);
        worker.setType(Calculator.VOLUNTEER_TYPE);
        double expectedResult = 0;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double)expectedResult, (Double)actual);
    }

    @Test
    public void testCalculateFeeVolunteerBiggerThanMaxSalary() {
        Worker worker = new Worker();
        worker.setSalary((double) 30000000);
        worker.setPc((double) 100000);
        worker.setHt((double) 5000000);
        worker.setType(Calculator.VOLUNTEER_TYPE);
        double expectedResult = 6116000;
        double actual = Calculator.calculate(worker);
        Assert.assertEquals((Double)expectedResult, (Double)actual);
    }

    @Test
    @Transactional
    @Rollback
    public void testWriteNewWorker() {
        Worker worker = new Worker();
        worker.setFullName("Trần Hải Đăng");
        worker.setEmail("tranhaidang2320@live.com");
        worker.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        worker.setGender(1);
        worker.setPhoneNumber("0377289069");
        worker.setMsbh("1556527910971");
        worker.setType(Calculator.NORMAL_TYPE);
        worker.setSalary((double) 15030000);
        worker.setPc((double) 1070000);
        worker.setHt((double) 5000000);
        worker.setZone(2);
        worker.setWorkplace("VCCorp");
        worker.setAddress(new Address(0,"01TTT", "004HH", "00124", "00117710"));
        worker.setIdentityCard(new IdentityCard(null, "071038686", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        Worker expectedResult = workerRepository.save(worker);
        Worker actual = workerRepository.findByMsbh("1556527910971");
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateWorker() {
        Worker expectedResult = new Worker();
        expectedResult.setId(5L);
        expectedResult.setFullName("Trần Hải Đăng");
        expectedResult.setEmail("tranhaidang2320@live.com");
        expectedResult.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
        expectedResult.setGender(1);
        expectedResult.setPhoneNumber("0377289069");
        expectedResult.setMsbh("1556527910970");
        expectedResult.setSalary((double) 15030000);
        expectedResult.setPc((double) 1070000);
        expectedResult.setHt((double) 5000000);
        expectedResult.setZone(3);
        expectedResult.setWorkplace("VCCorp");
        expectedResult.setAddress(new Address(7L, "01TTT", "004HH", "00124", "00117710"));
        expectedResult.setIdentityCard(new IdentityCard(7L, "071038685", "08TTT",java.sql.Date.valueOf("2019-03-14")));
        Worker actual = workerRepository.save(expectedResult);
        Assert.assertEquals(expectedResult, actual);
    }

}
