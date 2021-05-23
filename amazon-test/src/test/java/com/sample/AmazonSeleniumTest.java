package com.sample;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.page.Amazon;

class AmazonSeleniumTest {
	static WebDriver driver;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver","C:\\Manisha\\firefoxdriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.amazon.com/");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Thread.sleep(5*1000);
		driver.close();
	}

	@Test
	void test() {
		Amazon amazon = PageFactory.initElements(driver, Amazon.class);
		amazon.enterText("qa testing for beginners");
		amazon.searchItem();
		driver.findElement(By.linkText("Learn Testing in 1 Day: Definitive Guide to Learn Software Testing for Beginners")).click();
		String itemPrice = driver.findElement(By.id("newBuyBoxPrice")).getText();
		assertEquals("$12.99", itemPrice);
		driver.findElement(By.id("add-to-cart-button")).click();
		String subTotalPrice = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[1]/div/div/div[3]/div/div/div/div[1]/div[1]/span/span[2]")).getText();
		assertEquals("$12.99", subTotalPrice);
		driver.findElement(By.id("hlb-ptc-btn-native")).click();
	}

}
