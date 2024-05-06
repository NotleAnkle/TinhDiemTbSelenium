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

public class SeleniumViewScoreTest{
	
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
	public void testGetScore() throws InterruptedException{
		
		LoginSucess();
		
		Thread.sleep(1000);
		
		WebElement linkScores = driver.findElement(By.id("linkScores"));
		linkScores.click();
		
		assertTrue(driver.getCurrentUrl().endsWith("/user/score/1"));
		
	}

}
