package com.dangth.bhxh;

import com.dangth.bhxh.model.Address;
import com.dangth.bhxh.model.IdentityCard;
import com.dangth.bhxh.model.Worker;
import com.dangth.bhxh.repository.WorkerRepository;
import com.dangth.bhxh.utils.Calculator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SeleniumTests {
    private WebDriver driver;
    @Autowired
    private WorkerRepository workerRepository;

    @BeforeClass
    public static void setup() {
        WebDriverManager.firefoxdriver().setup();
        BhxhApplication.main(new String[]{});
    }
    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
    }
    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testHomePageNonLogin() {
        driver.get("http://localhost:8080/");
        String expectedTitle = "Trang chủ";
        String actualTitle;

        // launch Fire fox and direct it to the Base URL

        // get the actual value of the title
        actualTitle = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLoginPageHeadline() {
        driver.get("http://localhost:8080/login");

        String actual = driver.findElement(By.tagName("h2")).getText();
        String expectedResult = "Đăng nhập";
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testLoginPageInputUsername() {
        driver.get("http://localhost:8080/login");
        String actual = driver.findElement(By.id("inputName")).getTagName();
        String expectedResult = "input";
        Assert.assertEquals(expectedResult, actual);

    }
    @Test
    public void testLoginPageInputPassword() {
        driver.get("http://localhost:8080/login");
        String actual = driver.findElement(By.id("inputPassword")).getTagName();
        String expectedResult = "input";
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testLoginPageLoginButton() {
        driver.get("http://localhost:8080/login");
        String actual = driver.findElement(By.className("btn")).getTagName();
        String expectedResult = "button";
        Assert.assertEquals(expectedResult, actual);

    }
    @Test
    public void testLoginFail() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("1234");
        loginButton.submit();
        Thread.sleep(1000);
        String actual = driver.getCurrentUrl();
        String expectedResult = "http://localhost:8080/login?error";
        Assert.assertEquals(expectedResult, actual);

    }


    @Test
    public void testLoginSuccess() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        String actual = driver.findElement(By.className("card-title")).getText();
        String expectedResult = "Đăng kí thông tin tham gia bảo hiểm";
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testLogout() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(1000);
        driver.findElement(By.id("logoutForm")).submit();
        Thread.sleep(1000);
        String expectedResult = "Đăng xuất thành công";
        String actual = driver.findElement(By.className("alert-info")).getText();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testInfoPageInput() {
        driver.get("http://localhost:8080/info");
        String actual = driver.findElement(By.id("search")).getTagName();
        String expectedResult = "input";
        Assert.assertEquals(expectedResult, actual);

    }
    @Test
    public void testInfoPageHeadline() {
        driver.get("http://localhost:8080/info");
        String actual = driver.findElement(By.tagName("h1")).getText();
        String expectedResult = "Thông tin đăng kí";
        Assert.assertEquals(expectedResult, actual);

    }
    @Test
    public void testInfoPageSearchButton() {
        driver.get("http://localhost:8080/info");
        String actual = driver.findElement(By.id("action")).getText();
        String expectedResult = "Tìm";
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testInfoPageFind() throws InterruptedException {
        driver.get("http://localhost:8080/info");
        WebElement inputSearch = driver.findElement(By.id("search"));
        WebElement searchButton = driver.findElement(By.id("action"));
        inputSearch.sendKeys("071038685");
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(1000);
        String expectedResult = "Trần Hải Đăng";
        String actual = driver.findElement(By.tagName("td")).getText();
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testInfoPageInputEmptyCall() throws InterruptedException {
        driver.get("http://localhost:8080/info");
        WebElement inputSearch = driver.findElement(By.id("search"));
        WebElement searchButton = driver.findElement(By.id("action"));
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(1000);
        String expectedResult = "Vui lòng nhập MSBH hoặc CMT";
        String actual = driver.switchTo().alert().getText();
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testInfoPageNotFound() throws InterruptedException {
        driver.get("http://localhost:8080/info");
        WebElement inputSearch = driver.findElement(By.id("search"));
        WebElement searchButton = driver.findElement(By.id("action"));
        inputSearch.sendKeys("123123123232  gk");
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(1000);
        String expectedResult = "Không tìm thấy thông tin";
        String actual = driver.switchTo().alert().getText();
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testRegistrationHeadline() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/registration");
        String actual = driver.findElement(By.tagName("h2")).getText();
        String expectedResult = "Đăng kí mới hồ sơ";
        Assert.assertEquals(expectedResult, actual);

    }

    @Test
    public void testRegistrationForm() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/registration");
        List<String> actual = new ArrayList<>();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("select");
        expectedResult.add("select");
        expectedResult.add("select");
        expectedResult.add("button");
        expectedResult.add("a");

        String radioTypeNormal = driver.findElement(By.id("radioTypeNormal")).getTagName();
        actual.add(radioTypeNormal);
        String radioTypeVolunteer = driver.findElement(By.id("radioTypeVolunteer")).getTagName();
        actual.add(radioTypeVolunteer);
        String inputNameType = driver.findElement(By.id("inputName")).getTagName();
        actual.add(inputNameType);
        String inputBirthDateType = driver.findElement(By.id("inputDateBirth")).getTagName();
        actual.add(inputBirthDateType);
        String inputGenderType = driver.findElement(By.id("inputGender")).getTagName();
        actual.add(inputGenderType);
        String inputIdNumberType = driver.findElement(By.id("inputCMND")).getTagName();
        actual.add(inputIdNumberType);
        String inputPlaceCMND = driver.findElement(By.id("inputPlaceCMND")).getTagName();
        actual.add(inputPlaceCMND);
        String inputDateCMND = driver.findElement(By.id("inputDateCMND")).getTagName();
        actual.add(inputDateCMND);
        String inputPhone = driver.findElement(By.id("inputPhone")).getTagName();
        actual.add(inputPhone);
        String inputEmail = driver.findElement(By.id("inputEmail")).getTagName();
        actual.add(inputEmail);
        String inputWorkplace = driver.findElement(By.id("inputWorkplace")).getTagName();
        actual.add(inputWorkplace);
        String inputZone = driver.findElement(By.id("inputZone")).getTagName();
        actual.add(inputZone);
        String inputSalary = driver.findElement(By.id("inputSalary")).getTagName();
        actual.add(inputSalary);
        String inputPC = driver.findElement(By.id("inputPC")).getTagName();
        actual.add(inputPC);
        String inputHT = driver.findElement(By.id("inputHT")).getTagName();
        actual.add(inputHT);
        String inputCity = driver.findElement(By.id("inputCity")).getTagName();
        actual.add(inputCity);
        String inputProvince = driver.findElement(By.id("inputProvince")).getTagName();
        actual.add(inputProvince);
        String inputPX = driver.findElement(By.id("inputPX")).getTagName();
        actual.add(inputPX);
        String inputTX = driver.findElement(By.id("inputTX")).getTagName();
        actual.add(inputTX);
        String submitButton = driver.findElement(By.className("btn-primary")).getTagName();
        actual.add(submitButton);
        String cancelButton = driver.findElement(By.className("btn-danger")).getTagName();
        actual.add(cancelButton);
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testRegisterNewWorker() throws InterruptedException {
//        Worker expectedResult = new Worker();
//        expectedResult.setFullName("Trần Hải Đăng");
//        expectedResult.setEmail("tranhaidang2320@live.com");
//        expectedResult.setBirthDate(java.sql.Date.valueOf("1997-01-20"));
//        expectedResult.setGender(1);
//        expectedResult.setPhoneNumber("0377289069");
//        expectedResult.setMsbh("1556527910971");
//        expectedResult.setType(Calculator.NORMAL_TYPE);
//        expectedResult.setSalary((double) 15030000);
//        expectedResult.setPc((double) 1070000);
//        expectedResult.setHt((double) 5000000);
//        expectedResult.setZone(2);
//        expectedResult.setWorkplace("VCCorp");
//        expectedResult.setAddress(new Address(0,"92TTT", "916HH", "31117", "00026131"));
//        expectedResult.setIdentityCard(new IdentityCard(null, "071038686", "92TTT",java.sql.Date.valueOf("2019-03-14")));

        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/registration");

        driver.findElement(By.id("radioTypeNormal")).click();
        driver.findElement(By.id("radioTypeVolunteer"));
        driver.findElement(By.id("inputDateCMND")).sendKeys("14/03/2019");
        driver.findElement(By.id("inputDateBirth")).sendKeys("29/01/1997");
        WebElement nameElement = driver.findElement(By.id("inputName"));
        nameElement.sendKeys("Trần Hải Đăng");
        nameElement.click();
        new Select(driver.findElement(By.id("inputGender"))).selectByIndex(1);
        driver.findElement(By.id("inputCMND")).sendKeys("071038686");
        new Select(driver.findElement(By.id("inputPlaceCMND"))).selectByIndex(1);
        driver.findElement(By.id("inputPhone")).sendKeys("0377289069");
        driver.findElement(By.id("inputEmail")).sendKeys("tranhaidang2320@live.com");
        driver.findElement(By.id("inputWorkplace")).sendKeys("VCCorp");
        new Select(driver.findElement(By.id("inputZone"))).selectByIndex(2);
        driver.findElement(By.id("inputSalary")).sendKeys("15030000");
        driver.findElement(By.id("inputPC")).sendKeys("1070000");
        driver.findElement(By.id("inputHT")).sendKeys("5000000");
        new Select(driver.findElement(By.id("inputCity"))).selectByIndex(1);
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("inputProvince"))).selectByIndex(1);
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("inputPX"))).selectByIndex(1);
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("inputTX"))).selectByIndex(1);
        driver.findElement(By.className("btn-primary")).submit();

        Thread.sleep(1500);
        String expectedResult = "Đăng kí thành công";
        Worker worker = workerRepository.findByIdentityCard_Number("071038686");
        System.out.println(worker);
        workerRepository.deleteById(worker.getId());
        String actual = driver.findElement(By.className("alert-info")).getText();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testRegisterNewWorkerDuplicateIdentityNumber() throws InterruptedException {

        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/registration");

        driver.findElement(By.id("radioTypeNormal")).click();
        driver.findElement(By.id("radioTypeVolunteer"));
        driver.findElement(By.id("inputDateCMND")).sendKeys("14/03/2019");
        driver.findElement(By.id("inputDateBirth")).sendKeys("29/01/1997");
        WebElement nameElement = driver.findElement(By.id("inputName"));
        nameElement.sendKeys("Trần Hải Đăng");
        nameElement.click();
        new Select(driver.findElement(By.id("inputGender"))).selectByIndex(1);
        driver.findElement(By.id("inputCMND")).sendKeys("071038685");
        new Select(driver.findElement(By.id("inputPlaceCMND"))).selectByIndex(1);
        driver.findElement(By.id("inputPhone")).sendKeys("0377289069");
        driver.findElement(By.id("inputEmail")).sendKeys("tranhaidang2320@live.com");
        driver.findElement(By.id("inputWorkplace")).sendKeys("VCCorp");
        new Select(driver.findElement(By.id("inputZone"))).selectByIndex(2);
        driver.findElement(By.id("inputSalary")).sendKeys("15030000");
        driver.findElement(By.id("inputPC")).sendKeys("1070000");
        driver.findElement(By.id("inputHT")).sendKeys("5000000");
        new Select(driver.findElement(By.id("inputCity"))).selectByIndex(1);
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("inputProvince"))).selectByIndex(1);
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("inputPX"))).selectByIndex(1);
        Thread.sleep(1500);
        new Select(driver.findElement(By.id("inputTX"))).selectByIndex(1);
        driver.findElement(By.className("btn-primary")).submit();

        Thread.sleep(1500);
        String expectedResult = "Số CMND đã tồn tại";
        String actual = driver.findElement(By.className("alert-danger")).getText();
        Assert.assertEquals(expectedResult, actual);
    }
    @Test
    public void testUpdateForm() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/qlinfo/071038685");
        List<String> actual = new ArrayList<>();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("input");
        expectedResult.add("select");
        expectedResult.add("select");
        expectedResult.add("select");
        expectedResult.add("select");
        expectedResult.add("button");
        expectedResult.add("a");

        String radioTypeNormal = driver.findElement(By.id("radioTypeNormal")).getTagName();
        actual.add(radioTypeNormal);
        String radioTypeVolunteer = driver.findElement(By.id("radioTypeVolunteer")).getTagName();
        actual.add(radioTypeVolunteer);
        String inputNameType = driver.findElement(By.id("inputName")).getTagName();
        actual.add(inputNameType);
        String inputBirthDateType = driver.findElement(By.id("inputDateBirth")).getTagName();
        actual.add(inputBirthDateType);
        String inputGenderType = driver.findElement(By.id("inputGender")).getTagName();
        actual.add(inputGenderType);
        String inputIdNumberType = driver.findElement(By.id("inputCMND")).getTagName();
        actual.add(inputIdNumberType);
        String inputPlaceCMND = driver.findElement(By.id("inputPlaceCMND")).getTagName();
        actual.add(inputPlaceCMND);
        String inputDateCMND = driver.findElement(By.id("inputDateCMND")).getTagName();
        actual.add(inputDateCMND);
        String inputPhone = driver.findElement(By.id("inputPhone")).getTagName();
        actual.add(inputPhone);
        String inputEmail = driver.findElement(By.id("inputEmail")).getTagName();
        actual.add(inputEmail);
        String inputWorkplace = driver.findElement(By.id("inputWorkplace")).getTagName();
        actual.add(inputWorkplace);
        String inputZone = driver.findElement(By.id("inputZone")).getTagName();
        actual.add(inputZone);
        String inputSalary = driver.findElement(By.id("inputSalary")).getTagName();
        actual.add(inputSalary);
        String inputPC = driver.findElement(By.id("inputPC")).getTagName();
        actual.add(inputPC);
        String inputHT = driver.findElement(By.id("inputHT")).getTagName();
        actual.add(inputHT);
        String inputCity = driver.findElement(By.id("inputCity")).getTagName();
        actual.add(inputCity);
        String inputProvince = driver.findElement(By.id("inputProvince")).getTagName();
        actual.add(inputProvince);
        String inputPX = driver.findElement(By.id("inputPX")).getTagName();
        actual.add(inputPX);
        String inputTX = driver.findElement(By.id("inputTX")).getTagName();
        actual.add(inputTX);
        String submitButton = driver.findElement(By.className("btn-primary")).getTagName();
        actual.add(submitButton);
        String cancelButton = driver.findElement(By.className("btn-danger")).getTagName();
        actual.add(cancelButton);
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testUpdateFormNotFoundWorker() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/qlinfo/");
        driver.findElement(By.id("inputDefault")).sendKeys("12312312323");
        driver.findElement(By.id("btnFind")).click();
        Thread.sleep(1000);
        String expectedResult = "Không tìm thấy người người lao động";
        String actual = driver.findElement(By.className("alert-danger")).getText();
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testUpdateFormDuplicateIdentityNumber() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Worker worker = workerRepository.findByIdentityCard_Number("0710386844");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/qlinfo/");
        WebElement input = driver.findElement(By.id("inputDefault"));
        input.sendKeys("");
        input.sendKeys("0710386844");
        driver.findElement(By.id("btnFind")).click();
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("inputCMND"));
        element.clear();
        element.sendKeys("071038684412");
        driver.findElement(By.className("btn-primary")).submit();
        Thread.sleep(1000);
        String expectedResult = "Số chứng minh thư bị trùng";
        String actual = driver.findElement(By.className("alert-danger")).getText();
        workerRepository.save(worker);
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void testUpdateFormSuccess() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Worker worker = workerRepository.findByIdentityCard_Number("071038685");
        WebElement loginButton = driver.findElement(By.className("btn"));
        WebElement inputName = driver.findElement(By.id("inputName"));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputName.sendKeys("dangth@gmail.com");
        inputPassword.sendKeys("123");
        loginButton.submit();
        Thread.sleep(2000);
        driver.get("http://localhost:8080/qlinfo/");
        WebElement input = driver.findElement(By.id("inputDefault"));
        input.sendKeys("");
        input.sendKeys("071038685");
        driver.findElement(By.id("btnFind")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("radioTypeVolunteer")).click();
        driver.findElement(By.className("btn-primary")).submit();
        Thread.sleep(1000);
        String expectedResult = "Cập nhật thành công";
        String actual = driver.findElement(By.className("alert-info")).getText();
        workerRepository.save(worker);
        Assert.assertEquals(expectedResult, actual);
    }

}
