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

public class SeleniumLoginTest{
	
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
	public void testLogin() throws InterruptedException {
		
		LoginSucess();
		
        // Chờ một chút để trang web xử lý
        Thread.sleep(1000);

        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        WebElement loggedContent = null;
        try {
            loggedContent = driver.findElement(By.id("loggedContent"));
        } catch (Exception e) {
            // Nếu không tìm thấy phần tử, in ra lỗi và fail test
            System.out.println("Element loggedContent not found");
            e.printStackTrace();
        }
        
        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        assertEquals(true, loggedContent.isDisplayed());
	}
	
	@Test
	public void testLoginEmptyUsername() throws InterruptedException {

		
		this.driver.get("http://localhost:8080/home");
		
		WebElement txtUsername = this.driver.findElement(By.id("username"));
		WebElement txtPassword = this.driver.findElement(By.id("password"));
		WebElement btnLogin = this.driver.findElement(By.id("login-button"));
		
		txtUsername.sendKeys("");
		Thread.sleep(500);
		txtPassword.sendKeys("12");
		Thread.sleep(500);
		btnLogin.click();
		
        // Chờ một chút để trang web xử lý
        Thread.sleep(1000);
        
        // Tạo đối tượng WebDriverWait với timeout là 10 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Chờ cho alert xuất hiện và lấy đối tượng Alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertString = alert.getText();

        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        WebElement loggedContent = null;
        try {
            loggedContent = driver.findElement(By.id("loggedContent"));
        } catch (Exception e) {
            // Nếu không tìm thấy phần tử, in ra lỗi và fail test
            System.out.println("Element loggedContent not found");
        }
        
        
        
        this.driver.get("http://localhost:8080/logout");
        
        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        assertEquals(null, loggedContent);
        assertEquals("Vui lòng nhập tên tài khoản", alertString);
	}

	@Test
	public void testLoginEmptyPassword() throws InterruptedException {

		
		this.driver.get("http://localhost:8080/home");
		
		WebElement txtUsername = this.driver.findElement(By.id("username"));
		WebElement txtPassword = this.driver.findElement(By.id("password"));
		WebElement btnLogin = this.driver.findElement(By.id("login-button"));
		
		txtUsername.sendKeys("a");
		Thread.sleep(500);
		txtPassword.sendKeys("");
		Thread.sleep(500);
		btnLogin.click();
		
        // Chờ một chút để trang web xử lý
        Thread.sleep(1000);
        
        // Tạo đối tượng WebDriverWait với timeout là 10 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Chờ cho alert xuất hiện và lấy đối tượng Alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertString = alert.getText();

        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        WebElement loggedContent = null;
        try {
            loggedContent = driver.findElement(By.id("loggedContent"));
        } catch (Exception e) {
            // Nếu không tìm thấy phần tử, in ra lỗi và fail test
            System.out.println("Element loggedContent not found");
        }
        
        
        
        this.driver.get("http://localhost:8080/logout");
        
        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        assertEquals(null, loggedContent);
        assertEquals("Vui lòng nhập mật khẩu", alertString);
	}
	
	@Test
	public void testLoginWrongUnPw() throws InterruptedException {

		
		this.driver.get("http://localhost:8080/home");
		
		WebElement txtUsername = this.driver.findElement(By.id("username"));
		WebElement txtPassword = this.driver.findElement(By.id("password"));
		WebElement btnLogin = this.driver.findElement(By.id("login-button"));
		
		txtUsername.sendKeys("B20DCCN070");
		Thread.sleep(500);
		txtPassword.sendKeys("12");
		Thread.sleep(500);
		btnLogin.click();
		
        // Chờ một chút để trang web xử lý
        Thread.sleep(1000);
        
        // Tạo đối tượng WebDriverWait với timeout là 10 giây
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Chờ cho alert xuất hiện và lấy đối tượng Alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertString = alert.getText();

        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        WebElement loggedContent = null;
        try {
            loggedContent = driver.findElement(By.id("loggedContent"));
        } catch (Exception e) {
            // Nếu không tìm thấy phần tử, in ra lỗi và fail test
            System.out.println("Element loggedContent not found");
        }
        
        
        
        this.driver.get("http://localhost:8080/logout");
        
        // Kiểm tra xem phần tử loggedContent có hiển thị hay không
        assertEquals(null, loggedContent);
        assertEquals("Tên người dùng hoặc mật khẩu không chính xác", alertString);

	}

}
