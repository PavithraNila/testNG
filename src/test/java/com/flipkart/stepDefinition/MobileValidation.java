package com.flipkart.stepDefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileValidation {
	static WebDriver driver;
	static long startTime;
	static String name1;
	@BeforeClass(groups="default")
	public static void BeforeClass() {
		  WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		  
		  driver.get("https://www.flipkart.com/");
		  driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@AfterClass(groups="default")
	public  static void AfterClass() {
	 driver.quit();
		
	}
	@BeforeMethod
	public void BeforeTest() {
		startTime=System.currentTimeMillis();
	}
	@AfterMethod
	public void AfterTest() {
		long endTime=System.currentTimeMillis();
		System.out.println("Time is Taken:" + (endTime - startTime));
	}
	
	@Test(priority = 1,groups="sanity")
	public void close() {
		try {
			WebElement close=driver.findElement(By.xpath("//button[text()='✕']"));
			close.click();
		}
		catch(Exception e){
			//handle exception
		}
	}
	@Test(priority = 2, groups="sanity")
	public void search() {
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("realme",Keys.ENTER);
	}
	@Test(priority = 3)
	public void name(){
		WebElement mobileName1=driver.findElement(By.xpath("(//div[contains(text(),'realme')])[2]"));
		name1=mobileName1.getText();
		System.out.println(name1);
	}
	@Test(priority = 4)
	public void window() {
		String parent= driver.getWindowHandle();
		Set<String> children=driver.getWindowHandles();
		for(String x:children) {
			if(!parent.equals(x)) {
				driver.switchTo().window(x);
			}
		}
	}
	@Test(priority = 5, enabled=false)
	public void validation() {
		WebElement mobileName2=driver.findElement(By.xpath("//div[text()='₹7,499']"));
		String name2=mobileName2.getText();
		System.out.println(name2);
		
		Assert.assertTrue(mobileName2.isDisplayed());
		Assert.assertEquals(name1,name2);
	}
	@Test(priority = 6,invocationCount = 5)
	public void Success() {
		System.out.println("Success method");
	}
}
