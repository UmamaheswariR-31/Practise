package TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC002_Nykaa {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		//ChromeOptions options =new ChromeOptions();
		//options.addArguments("disable-notifications");
		ChromeDriver driver= new ChromeDriver();
		//'15/04/2020
				//==========
//		1) Go to https://www.nykaa.com/
			driver.get("https://www.nykaa.com/");
			//Maximize
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//2) Mouseover on Brands and Mouseover on Popular
		WebElement brannds = driver.findElementByXPath("//li[@class='menu-dropdown-icon']/a");
		Actions builder =new Actions(driver);
		builder.moveToElement(brannds).perform();
		builder.moveToElement(driver.findElementByXPath("//div[@class='BrandsCategoryHeading']/a")).perform();
		//3) Click L'Oreal Paris
		driver.findElementByXPath("(//a[@target='_blank']/img)[5]").click();
//		4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> winset = driver.getWindowHandles();
		List<String> winList= new ArrayList<String>(winset);
		String SecWin = winList.get(1);
		driver.switchTo().window(SecWin);
		String title = driver.getTitle();
		System.out.println(title);
		//5) Click sort By and select customer top rated
		driver.findElementByXPath("//span[@class='pull-right']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		Thread.sleep(3000);
		
        //6) Click Category and click Shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//label[@for='chk_Shampoo_undefined']/div").click();
		Thread.sleep(3000);
//		7) check whether the Filter is applied with Shampoo
		String textSham = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if(textSham.contains("Shampoo"))
		{
			System.out.println("Shampoo is selected");
		}else {
			System.out.println("Shampoo is not selected");
		}
//		8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[contains (text(), 'Oreal Paris Colour Protect Shampoo')]").click();
//		9) GO to the new window and select size as 175ml
		Set<String> winSet2 = driver.getWindowHandles();
		List<String> winList2=new ArrayList<String>(winSet2);
		int size = winList2.size();
		System.out.println(size);
		driver.switchTo().window(winList2.get(2));
		Thread.sleep(5000);
		driver.findElementByXPath("//span[text()='175ml']").click();
		Thread.sleep(2000);
        //10) Print the MRP of the product
		String mrp = driver.findElementByXPath("//span[@class='post-card__content-price-offer']").getText();
		System.out.println(mrp);
//		11) Click on ADD to BAG
		driver.findElementByXPath("//button[text()='ADD TO BAG']").click();
//		12) Go to Shopping Bag 
		driver.findElementByClassName("AddToBagbox").click();
//		13) Print the Grand Total amount
		String grandTotal = driver.findElementByXPath("(//div[@class='value'])[4]").getText();
		System.out.println(grandTotal);
//		14) Click Proceed
		driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click(); 
		 Thread.sleep(3000);
//		15) Click on Continue as Guest
		 driver.findElementByXPath("//button[@class='btn full big']").click();
//		16) Print the warning message (delay in shipment)
		 System.out.println(driver.findElementByXPath("//div[text()='Please expect delay in shipments because of the national lockdown']").getText());
	     
//		17) Close all windows
		 driver.quit();

	}

}
