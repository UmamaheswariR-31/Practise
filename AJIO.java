package TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class AJIO {

	public static void main(String[] args) throws InterruptedException {
//		    1)go to https://www.ajio.com/shop/sale
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options =new ChromeOptions();
	    options.addArguments("disable-notifications");
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.ajio.com/shop/sale");
		//Maximize
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//WebDriverWait wait = new WebDriverWait(driver,60);

		
//			2) Enter Bags in the Search field and Select Bags in Women Handbags  
		driver.findElementByName("searchVal").sendKeys("bags");
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Bags in ']/following-sibling::span").click();
	Thread.sleep(3000);
//			3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class='five-grid']").click();
		Thread.sleep(2000);
		 WebElement WebEle = driver.findElementByXPath("//Select");
		
		Select sortBy= new Select(WebEle);
		sortBy.selectByVisibleText("What's New");
		Thread.sleep(2000);
//			4) Enter Price Range Min as 2000 and Max as 5000  
		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementById("minPrice").sendKeys("2500");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		Thread.sleep(5000);
//			5) Click on the product "Puma Ferrari LS Shoulder Bag"  
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Thread.sleep(5000);
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList=new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
//			6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon 
		String text=	driver.findElementByXPath("//div[text()='Extra Upto 28% Off on 2690 and Above ']").getText();
		String pPrice = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		int conpPrice = Integer.parseInt(pPrice.replaceAll("\\D", ""));
		System.out.println(conpPrice);
		
		if(text.contains("2690 and Above")) {
			String text2 = driver.findElementByXPath("//div[text()='EPIC']").getText();
			System.out.println(text2);
		}
		String dpPrice = driver.findElementByXPath("//div[text()='Get it for ']/span").getText();
		int condpPrice = Integer.parseInt(dpPrice.replaceAll("\\D", ""));
		int disAmtInPrd= conpPrice-condpPrice;
		System.out.println("Discounted Under Product is " +disAmtInPrd);
//			7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available  
		driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']").click();
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("682001");
		driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
		String Expdate = driver.findElementByXPath("//span[@class='edd-message-success-details-highlighted']").getText();
		System.out.println("The Expected Delivery date is"+Expdate);
//			8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email  
		driver.findElementByXPath("//div[text()='Other information']").click();
		Thread.sleep(2000);
		String CustCareInfo = driver.findElementByXPath("(//span[text()='Customer Care Address']/following-sibling::span)[2]").getText();
		System.out.println("Customer Care Address: "+CustCareInfo);
//			9) Click on ADD TO BAG and then GO TO BAG  
		driver.findElementByXPath("//span[text()='ADD TO BAG']/ancestor::div[1]").click();
		Thread.sleep(10000);
		driver.findElementByXPath("//span[text()='GO TO BAG']/ancestor::div[1]").click();
		Thread.sleep(5000);
//			10) Check the Order Total before apply coupon  
	       String ordrTotal = driver.findElementByXPath("//span[text()='Order Total']/following-sibling::span").getText();
	       System.out.println("Order total before apply coupon "+ordrTotal);
	       Thread.sleep(5000);
//			11) Enter Coupon Code and Click Apply  
	       driver.findElementById("couponCodeInput").sendKeys("EPIC");
	       driver.findElementByXPath("//button[text()='Apply']").click();
//			12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details  
	       String couponSavAmt=driver.findElementByXPath("//span[text()='Coupon savings']/following-sibling::span").getText();
	       String coupnSavAmtNum = couponSavAmt.replaceAll("[,a-zA-Z ]", "");
	       String[] split = coupnSavAmtNum.split(".",2);
	       System.out.println(split);
	       System.out.println(split[1]);
			System.out.println(split[0]);
			
			System.out.println("CoupnSavAmt is " +split[1]);
			
			double d = Double.parseDouble(split[1]);
			int roundoffamt= (int) Math.round(d);
			System.out.println("Round off of Coupon Sav amt id " +roundoffamt);
			
			if(disAmtInPrd==roundoffamt)
			{
				System.out.println("Coupon Savings amount under Order Summary matches with  the amount calculated in Product details");
			}
			else
			{
				System.out.println("Coupon Savings amount under Order Summary does not matches with  the amount calculated in Product details");
			
			}
//			13) Click on Delete and Delete the item from Bag  
			driver.findElementByXPath("//div[@class='product-delete']/div[text()='Delete']").click();
			driver.findElementByXPath("//div[@class='card-delete-button']/div[2]").click();
			
//			14) Close all the browsers

	}

}
