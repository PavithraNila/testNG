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

public class SamsungMobileValidation {
	static WebDriver driver;
	static long startTime;
	static String name1;
	@BeforeClass
	public static void BeforeClass() {
		  WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		  
		  driver.get("https://www.flipkart.com/");
		  driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@AfterClass
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
	
	@Test(priority =1)
	public void close1() {
		try {
			WebElement close=driver.findElement(By.xpath("//button[text()='✕']"));
			close.click();
		}
		catch(Exception e){
			//handle exception
		}
	}
	@Test(priority =2)
	public void search2() {
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("samsung",Keys.ENTER);
	}
	@Test(priority =3)
	public void names() {
		WebElement mobileName1=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]"));
		name1=mobileName1.getText();
		System.out.println(name1);
	}
	@Test(priority =4,enabled=false)
	public void window1() {
		String parent1= driver.getWindowHandle();
		Set<String> children1=driver.getWindowHandles();
		for(String x1:children1) {
			if(!parent1.equals(x1)) {
				driver.switchTo().window(x1);
			}
		}
	}
	@Test(priority =5,enabled=false)
	public void validation1() {
		WebElement mobileName2=driver.findElement(By.xpath("//div[text()='₹1,04,999']"));
		String name2=mobileName2.getText();
		System.out.println(name2);
		
		Assert.assertTrue(mobileName2.isDisplayed());
		Assert.assertEquals(name1,name2);
	}
	
}


