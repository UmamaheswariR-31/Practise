package TestCases;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Azure {

	public static ChromeDriver driver;
	public static void main(String[] args) throws InterruptedException {
//		07/05/2020
//		==========
		
       System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
       System.setProperty("webdriver.chrome.silentOutput", "true");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--disable-notifications");

		//DesiredCapabilities cap = new DesiredCapabilities();
		//cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		//options.merge(cap);
		
		//ChromeOptions options =new ChromeOptions();
		//options.addArguments("disable-notifications");
		 driver=new ChromeDriver();
//		1) Go to https://azure.microsoft.com/en-in/
		driver.get("https://azure.microsoft.com/en-in/");
		
		//Maximize
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//Actions build=new Actions(driver);
				WebDriverWait wait=new WebDriverWait(driver,30);
				JavascriptExecutor js = (JavascriptExecutor)driver;
	
//		2) Click on Pricing
		driver.findElementByLinkText("Pricing").click();
		
//		3) Click on Pricing Calculator
		driver.findElementByXPath("(//ul[@class='linkList initial-list']/li)[2]").click();
		Thread.sleep(2000);
//		4) Click on Containers
		driver.findElementByXPath("//div[@class='category-container']/button[text()='Containers']").click();
//		5) Select Container Instances
		driver.findElementByXPath("(//span[@class='service-heading' and text()='Container Instances'])[2]").click();

//		6) Click on Container Instance Added View
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='Container Instances added.']//a[text()='View']")));
		driver.findElementByXPath("//div[text()='Container Instances added.']//a[text()='View']").click();
//		7) Select Region as "South India"
		WebElement regionDD = driver.findElementByXPath("//select[@name='region']");
		Select region=new Select(regionDD);
		region.selectByVisibleText("South India");
		//		8) Set the Duration as 180000 seconds
		//keys.chord(Keys.CONTROL,"a"),"seonds value"
		//Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END)
		driver.findElementByXPath("//input[@name='seconds']").sendKeys(Keys.chord(Keys.CONTROL,"a"),"180000");
//		9) Select the Memory as 4GB
		WebElement memory = driver.findElementByXPath("//select[@name='memory']");
		Select memoryDD =new Select(memory);
		memoryDD.selectByVisibleText("4 GB");
		Thread.sleep(3000);
//		10) Enable SHOW DEV/TEST PRICING
		driver.findElementById("devtest-toggler").click();
      
//		wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("notification-copy")));
//		String pricingPopup = driver.findElementByClassName("notification-copy").getText();
//		System.out.println("The notification content is "+pricingPopup);
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElementByClassName("notification-copy")));



//		11) Select Indian Rupee  as currency
		WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currencyDD =new Select(currency);
		currencyDD.selectByValue("INR");
//		12) Print the Estimated monthly price
		String monthlyEstimatedPrice = driver.findElementByXPath("//h3[text()='Estimated monthly cost']/ancestor::div[1]/following-sibling::div/div[2]//span/span").getText();
		System.out.println("Monthly Estimated price: "+monthlyEstimatedPrice);	
//		13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[contains(@class,'export-button')]").click();
		Thread.sleep(3000);


//		14) Verify the downloded file in the local folder
		
		File export =new File("C:\\Users\\global\\Downloads","ExportedEstimate.xlsx");
		if (export.exists())
		{
			System.out.println("File downloaded successfully");
		}else
		{ 
			System.out.println("File not found");
		
		}
		export.delete();
//		15) Navigate to Example Scenarios and Select CI/CD for Containers
		js.executeScript("window.scrollTo(0, document.body.scrollTop)");
		driver.findElementByLinkText("Example Scenarios").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click(); 
		Thread.sleep(2000);

//		16) Click Add to Estimate
		WebElement addToEstimate = driver.findElementByXPath("//button[text()='Add to estimate']");
		js.executeScript("arguments[0].click()", addToEstimate);
		Thread.sleep(5000);
		
//		17) Change the Currency as Indian Rupee
		js.executeScript("window.scrollBy(0, 900)"); 
		WebElement currency2 = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currency2DD=new Select(currency2);
		currency2DD.selectByValue("INR");
		
//		18) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
//		19) Export the Estimate
		driver.findElementByXPath("//button[text()='Export']").click(); 
		Thread.sleep(5000);
//		20) Verify the downloded file in the local folder
		if (export.exists())
		{
			System.out.println("File downloaded successfully");
		}else
		{ 
			System.out.println("File not found");
		
		}
	}

}
