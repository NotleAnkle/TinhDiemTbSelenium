package com.example.BTL.Selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumInputScoreTest{
	
	WebDriver driver;
	
	@BeforeEach
	public void Starting() {
		// Thiết lập vị trí file edge driver
		System.setProperty("webdriver.edge.driver", "D:\\WebDriver\\Edge-124\\msedgedriver.exe");
		//Thiết lập cơ chế HTTP cho web driver (Do gặp lỗi phát sinh)
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		//Thiết lập option xử lý aleart
		EdgeOptions options = new EdgeOptions();
		options.setCapability("unexpectedAlertBehaviour", "accept");
		
		//Khởi tạo EdgeDriver mới với option
		this.driver = new EdgeDriver(options);
		//Đặt full màn hình
		this.driver.manage().window().maximize();
		
	}
	@AfterEach
	public void Finish() {
		//Logout khỏi hệ thống sau khi Test
        this.driver.get("http://localhost:8080/logout");
        //Đóng trình duyệt
		driver.quit();
	}
	
	private void LoginSucess() throws InterruptedException {
		this.driver.get("http://localhost:8080/home");
		
		Thread.sleep(500);
		
		WebElement txtUsername = this.driver.findElement(By.id("username"));
		WebElement txtPassword = this.driver.findElement(By.id("password"));
		WebElement btnLogin = this.driver.findElement(By.id("login-button"));
		
		txtUsername.sendKeys("B20DCCN070");
		Thread.sleep(500);
		txtPassword.sendKeys("123");
		Thread.sleep(500);
		btnLogin.click();
	}
	
	@Test
	public void testGetScoreInput() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkInput = driver.findElement(By.id("linkInput"));
		linkInput.click();
		
		assertTrue(driver.getCurrentUrl().endsWith("score/user/1/term/1"));
		
	}
	
	@Test
	public void testGetScoreInputTermSelect() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkInput = driver.findElement(By.id("linkInput"));
		linkInput.click();
		
        WebElement dropdownMenu = driver.findElement(By.id("terms"));

        // Khởi tạo đối tượng Select với dropdown menu
        Select select = new Select(dropdownMenu);

        // Lựa chọn phần tử trong dropdown menu bằng giá trị của thuộc tính "value"
        select.selectByValue("2");
		
		assertTrue(driver.getCurrentUrl().endsWith("score/user/1/term/2"));
		
	}
	
	@Test
	public void testTryScore() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkInput = driver.findElement(By.id("linkInput"));
		linkInput.click();
		
		String[] values = {"1", "1", "1", "1", "1",
                "2", "2", "2", "2", "2",
                "1", "1", "1", "1", "1",
                "1", "1", "1", "1", "1",
                "1", "1", "1", "1", "1",
                "6", "6", "6", "6", "6"};
		
		List<WebElement> inputElements = driver.findElements(By.xpath("//input[@class='numberInput']"));
		for (int i = 0; i < values.length; i++) {
		    WebElement inputElement = inputElements.get(i);
		    inputElement.clear(); // Xóa giá trị cũ (nếu có)
		    inputElement.sendKeys(values[i]); // Gửi giá trị mới
		}
		
        WebElement element = driver.findElement(By.id("btnTry"));
        
        element.click();
		
        Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().endsWith("score/user/1/term/1"));
		
	}
	
	@Test
	public void testSelectTermTryScore() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkInput = driver.findElement(By.id("linkInput"));
 		linkInput.click();
		
	    WebElement dropdownMenu = driver.findElement(By.id("terms"));
	
	    // Khởi tạo đối tượng Select với dropdown menu
	    Select select = new Select(dropdownMenu);
	
	    // Lựa chọn phần tử trong dropdown menu bằng giá trị của thuộc tính "value"
	    select.selectByValue("3");
		
		String[] values = {"1", "1", "1", "1", "1",
	            "2", "2", "2", "2", "2",
	            "6", "6", "6", "6", "6"};
		
		List<WebElement> inputElements = driver.findElements(By.xpath("//input[@class='numberInput']"));
		for (int i = 0; i < values.length; i++) {
		    WebElement inputElement = inputElements.get(i);
		    inputElement.clear(); // Xóa giá trị cũ (nếu có)
		    inputElement.sendKeys(values[i]); // Gửi giá trị mới
		}
		
	    WebElement element = driver.findElement(By.id("btnTry"));
	    
	    element.click();
		
	    Thread.sleep(1000);
		
		assertTrue(driver.getCurrentUrl().endsWith("score/user/1/term/3"));
		
	}
	
	@Test
	public void testSaveScoreInput() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkInput = driver.findElement(By.id("linkInput"));
		linkInput.click();
		
		WebElement btnSave = driver.findElement(By.id("btnSave"));
		btnSave.click();
		
		Thread.sleep(1000);
		
		assertTrue(driver.getCurrentUrl().endsWith("/user/score/1"));
		
	}
	
	@Test
	public void testSelectTermSaveScoreInput() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkInput = driver.findElement(By.id("linkInput"));
		linkInput.click();
		
	    WebElement dropdownMenu = driver.findElement(By.id("terms"));
	
	    // Khởi tạo đối tượng Select với dropdown menu
	    Select select = new Select(dropdownMenu);
	
	    // Lựa chọn phần tử trong dropdown menu bằng giá trị của thuộc tính "value"
	    select.selectByValue("3");
		
	    Thread.sleep(1000);
	    
		WebElement btnSave = driver.findElement(By.id("btnSave"));
		btnSave.click();
		
		Thread.sleep(1000);
		
		assertTrue(driver.getCurrentUrl().endsWith("/user/score/1"));
		
	}
}
