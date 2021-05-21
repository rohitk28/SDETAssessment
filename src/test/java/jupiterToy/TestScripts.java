package jupiterToy;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeSuite;

public class TestScripts {

	WebDriver driver = null;
	String Url = "https://jupiter.cloud.planittesting.com/#/";

	@BeforeSuite
	public void SetupDriverPath() {
		// configurations - ChromeDriver Path Setting

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	}

	@BeforeMethod
	public void CallingChormeDriver() {
		// Calling ChromeDriver,
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	@Test // To find total number of products in the shopping. public void
	public void TestCase_01() {

		driver.get(Url);

		driver.findElement(By.xpath("//a[text()='Start Shopping »']")).click();

		int xpathCount = driver.findElements(By.xpath("//a[contains (text() ,'Buy')]")).size();

		int expected = 8;

		if (xpathCount == expected) {

			System.out.println("Total number of products: " + xpathCount);

		} else
			System.out.println("Test case is unsucessfull");

	}

	@Test // This test for the adding products to the cart. public void
	public void TestCase_02() {

		driver.get(Url);

		driver.findElement(By.xpath("//a[text()='Start Shopping »']")).click();

		int xpathCount = 8;

		driver.findElement(By.xpath("//*[@id=\"product-1\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-2\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-3\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-4\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-5\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-6\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-7\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-8\"]/div/p/a")).click();

		int CartTotal = driver.findElements(By.xpath("//*[@id=\"nav-cart\"]/a/span")).size();

		if (xpathCount == CartTotal) {

			System.out.println("Test is sucessful as total producst = " + CartTotal);

		} else {
			System.out.println("Test is failed as total products  not eaual to" + CartTotal);
		}
	}

	
	@Test
	// The complete empty cart
	public void TestCase_03() {
		driver.get(Url);

		driver.findElement(By.xpath("//a[text()='Start Shopping »']")).click();
		driver.findElement(By.xpath("//*[@id=\"product-1\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-2\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-3\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-4\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-5\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-6\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-7\"]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"product-8\"]/div/p/a")).click();

		driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[2]/td/ng-confirm/a")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[3]/a[1]")).click();

		String actual_msg = driver.findElement(By.xpath("//a[text()='Start Shopping »']")).getText();

		System.out.println("the message is " + actual_msg);

		String expect = "Start Shopping »";

		if (actual_msg == expect) {

			System.out.println("Test pass as expected message shown ");

		} else {
			System.out.println("Test fail as expected message not shown");
		}
	}


	@Test
	// to remove item from cart
	public void TestCase_04() {

		driver.get(Url);

		driver.findElement(By.xpath("//a[text()='Start Shopping »']")).click();

		driver.findElement(By.xpath("//*[@id=\"product-1\"]/div/p/a")).click();

		driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();

		driver.findElement(By.className("remove-item btn btn-mini btn-danger")).click();

		driver.findElement(By.className("btn btn-success")).click();

		String actual_msg = driver.findElement(By.xpath("/html/body/div[2]/div/div/strong")).getText();

		String expect = "Your cart is empty";

		if (actual_msg == expect) {

			System.out.println("Test pass as expected message shown ");

		} else {
			System.out.println("Test fail as expected message not shown");
		}
	}
	@Test
	// checking checkout filed without data to mandatory fields
	public void Test_case_05() {

		driver.get(Url);

		driver.findElement(By.xpath("//a[text()='Start Shopping »']")).click();

		driver.findElement(By.xpath("//*[@id=\"product-1\"]/div/p/a")).click();

		driver.findElement(By.xpath("//a[text() = 'Check Out']")).click();

		driver.findElement(By.id("forename")).sendKeys(" ");

		driver.findElement(By.id("surname")).sendKeys(" ");

		driver.findElement(By.id("email")).sendKeys(" ");

		driver.findElement(By.id("address")).sendKeys(" ");

		Select CardType = new Select(driver.findElement(By.id("cardType")));

		CardType.selectByVisibleText("Visa");

		driver.findElement(By.id("card")).sendKeys("1223 1586 4986 8852");

		driver.findElement(By.id("checkout-submit-btn")).click();

		String actual_msg = driver.findElement(By.xpath("//*[@id=\"header-message\"]/div")).getText();

		String expect = "Almost there";

		if (actual_msg == expect) {

			System.out.println("Test pass as expected message shown ");

		} else {
			System.out.println("Test fail as expected message not shown");
		}

	}
	@AfterMethod
	public void Quiting_the_browser() {
		driver.quit();
	}

}
