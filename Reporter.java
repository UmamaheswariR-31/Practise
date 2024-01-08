package utils;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;


public abstract class Reporter {
	
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public ExtentTest test, node;
	
	public String testCaseName, testDescription, nodes, authors,category;
	public String excelFileName;
	
	@BeforeSuite
	public void startReport() {
		reporter = new ExtentHtmlReporter("./report/result.html");
		reporter.setAppendExisting(true); 
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		//logger obj
		//jdbc connections
	}
	
    @BeforeClass
	public void report() throws IOException {
		test = extent.createTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);  
	}
    
    public abstract long takeSnap();
    
    public void reportStep(String desc, String status) {
    	MediaEntityModelProvider img = null;
		if(!status.equalsIgnoreCase("PASS")){

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						("./../reports/images/"+snapNumber+".jpg").build();
			} catch (IOException e) {
				
			}
		}
    	if(status.equalsIgnoreCase("pass")) {
    		node.pass(desc, img);
    	} else if(status.equalsIgnoreCase("fail")) {
    		node.fail(desc, img); 
    	}
	}

    @AfterSuite
    public void stopReport() {
    	extent.flush();
    }
}














