package com.dangth.bhxh;

import com.dangth.bhxh.model.location.City;
import com.dangth.bhxh.model.location.Commune;
import com.dangth.bhxh.model.location.Hamlet;
import com.dangth.bhxh.model.location.Province;
import com.dangth.bhxh.repository.CityRepository;
import com.dangth.bhxh.repository.CommuneRepository;
import com.dangth.bhxh.repository.HamletRepository;
import com.dangth.bhxh.repository.ProvinceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationAPITests {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    HamletRepository hamletRepository;

    @Test
    public void testFindCityByName() {
        City actual = cityRepository.findByCity("Tỉnh Tuyên Quang");
        City expectedResult = new City();
        expectedResult.setCity("Tỉnh Tuyên Quang");
        expectedResult.setIdcity("08TTT");
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testFindCityByNameFail() {
        City actual = cityRepository.findByCity("zzz");
        Assert.assertNull(actual);
    }
    @Test
    public void testFindCityById() {
        City actual = cityRepository.findById("08TTT").orElse(null);
        City expectedResult = new City();
        expectedResult.setCity("Tỉnh Tuyên Quang");
        expectedResult.setIdcity("08TTT");
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testFindProvinceByCityId() {
        List<Province> actual = provinceRepository.findAllByIdcity("08TTT");
        List<Province> expectedResult = new ArrayList<>();
        expectedResult.add(new Province("070HH","08TTT","Thành phố Tuyên Quang"));
        expectedResult.add(new Province("071HH","08TTT","Huyện Lâm Bình"));
        expectedResult.add(new Province("072HH","08TTT","Huyện Na Hang"));
        expectedResult.add(new Province("073HH","08TTT","Huyện Chiêm Hóa"));
        expectedResult.add(new Province("074HH","08TTT","Huyện Hàm Yên"));
        expectedResult.add(new Province("075HH","08TTT","Huyện Yên Sơn"));
        expectedResult.add(new Province("076HH","08TTT","Huyện Sơn Dương"));

        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testFindProvinceByCityIdFail() {
        List<Province> actual = provinceRepository.findAllByIdcity("121z");
        List<Province> expectedResult = new ArrayList<>();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testFindCommuneByIdProvince() {
        List<Commune> actual = communeRepository.findAllByIdprovince("071HH");
        List<Commune> expectedResult = new ArrayList<>();
        expectedResult.add(new Commune("02233","071HH","Xã Phúc Yên"));
        expectedResult.add(new Commune("02242","071HH","Xã Xuân Lập"));
        expectedResult.add(new Commune("02251","071HH","Xã Khuôn Hà"));
        expectedResult.add(new Commune("02266","071HH","Xã Lăng Can"));
        expectedResult.add(new Commune("02269","071HH","Xã Thượng Lâm"));
        expectedResult.add(new Commune("02290","071HH","Xã Bình An"));
        expectedResult.add(new Commune("02293","071HH","Xã Hồng Quang"));
        expectedResult.add(new Commune("02296","071HH","Xã Thổ Bình"));
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testFindCommuneByIdProvinceFail() {
        List<Commune> actual = communeRepository.findAllByIdprovince("071zHH");
        List<Commune> expectedResult = new ArrayList<>();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testFindHamletByIdCommune() {
        List<Hamlet> actual = hamletRepository.findAllByIdcommune("02233");
        List<Hamlet> expectedResult = new ArrayList<>();
        expectedResult.add(new Hamlet("00036580", "02233", "Bản Bon"));
        expectedResult.add(new Hamlet("00044334", "02233", "Nà Khậu"));
        expectedResult.add(new Hamlet("00044336", "02233", "Khau Cau"));
        expectedResult.add(new Hamlet("00044824", "02233", "Bản Tấng"));
        expectedResult.add(new Hamlet("00048035", "02233", "Bản Thàng"));
        expectedResult.add(new Hamlet("00051045", "02233", "Phiêng Mơ"));
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testFindHamletByIdCommuneFail() {
        List<Hamlet> actual = hamletRepository.findAllByIdcommune("022z33");
        List<Hamlet> expectedResult = new ArrayList<>();
        Assert.assertEquals(expectedResult, actual);
    }

}
