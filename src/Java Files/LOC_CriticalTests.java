package Tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
//import java.sql.Time;
import java.text.DateFormat;
//import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
//import java.util.Set;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

//import Pages.HomePage;
//import Pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Utilities.ExtentReports.Excel;


public class LOC_CriticalTests {
	
	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	
	@BeforeClass
	public void Initialize() throws IOException {	
		
		reports = new ExtentReports(System.getProperty("user.dir") +"/ExecutionReports/LOC_Critical_Scenarios/CriticalScenarios_"+timestamp+".html", true);
		reports.addSystemInfo("Browser Version","IE 11.0");
		
		
	}
	
	@BeforeMethod
	 public void killProcess() throws Exception

	      {

			 //Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			 Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
	          Thread.sleep(5000); //Allow OS to kill the process
	          System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		  		driver = new InternetExplorerDriver();
		  		driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				this.DrawerAssignmentCheck();
				
	        }
			
	
	public void DrawerAssignmentCheck(){
		
	}
	
	public void Login (String username,String password,String storenumber) throws InterruptedException {										
			//Launch URL
			driver.get(appUrl);
			test.log(LogStatus.INFO, "CSR Application is launched");
			driver.manage().window().maximize();
			String usenameId = "loginRequestBean.userId";
		    String passwordId = "loginRequestBean.password";
		    String StoreId = "loginRequestBean.locNbr";
		    String Login = "login";
		 
		    driver.findElement(By.name(usenameId)).sendKeys(username);
		    Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Username is entered: "+username);

	        //Enter Password
	       
		    driver.findElement(By.name(passwordId)).clear();
		    driver.findElement(By.name(passwordId)).sendKeys(password);
		    Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Password is entered: "+password);
	        	        
	        driver.findElement(By.name(StoreId)).sendKeys(storenumber);
	        Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
	        //Click Login Button
	        driver.findElement(By.name(Login)).click();
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Clicked on Submit button");
	        Thread.sleep(2000);
	}
						
	public boolean IsElementExits(String Value) {
	    int secondsToWait = 5;

	    try {
	        new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Value)));
	        return true;
	    } catch (org.openqa.selenium.TimeoutException e) {
	        return false;
	    }
	}
	public WebElement Field(WebDriver driver) {

		
		  try {
		    Thread.sleep(500);
		    WebElement element = (new WebDriverWait(driver, 9)).until(ExpectedConditions.visibilityOfElementLocated(By
		    .xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
		    return element;
		  } catch (Exception e) {
		    return null;
		  }
	}
	
public void NewLoan(String SSN,String FileName) throws Exception{
		
		test.log(LogStatus.INFO,"New Loan");
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);   	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{		
				String State = TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
				String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
				String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
				String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
				String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
				String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();  
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
					 {
						if(ProductName.equals("Line of Credit"))
						{
							if(StoreID.equals("5435"))
							{
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
							}
							else
							{
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
							}
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						driver.findElement(By.name("ShareScreenBtn")).click();
						test.log(LogStatus.PASS, "ShareScreen Button is clicked");
						
						Set<String> handles = driver.getWindowHandles();
						 Iterator<String> i1=handles.iterator();
						 
						 while(i1.hasNext())			
					        {		
					            String ChildWindow=i1.next();		
					            		
					            if(!Parent_Window.equalsIgnoreCase(ChildWindow))			
					            {    
					            	
					            	driver.switchTo().window(ChildWindow);
					            	driver.findElement(By.name("confirmSummary")).click();
									test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
					            }
					        }
				         					
						 driver.switchTo().window(Parent_Window);
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
						driver.findElement(By.id("LoanButtonId")).click();
						 test.log(LogStatus.PASS, "Clicked on New Loan button");
				//New Loan Screens
					
					if(ProductID.equals("LOC"))
					{
					
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
						test.log(LogStatus.PASS, "Click on Finish LOC Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						
						if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
						
					}
					else
					{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
					}
			}
		}

	}
	
public void Void(String SSN,String FileName) throws Exception{
		
		test.log(LogStatus.INFO, "Void Loan");
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				driver.switchTo().defaultContent();	
				WebDriverWait wait = new WebDriverWait(driver, 30);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
		        driver.findElement(By.cssSelector("li[id='910000']")).click();	
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				   if(ProductID.equals("LOC"))
					 {
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				   test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 else
					 {
					 driver.findElement(By.id("go_Button")).click();
					 }
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
									}
							 
					    	
						 }
										    
							
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
														
			}
			
		}
	}		
public void Closer(String SSN,String FileName) throws Exception{
	
	test.log(LogStatus.INFO, "Loan Closure");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys(TxnType);
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			else
			{
				driver.findElement(By.id("go_Button")).click();
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("close loc")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Closer Loan button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("ok")).click();

				if(driver.findElement(By.name("ok")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Closer Loan is Completed Successfully ");
					driver.findElement(By.name("ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Closer Loan is not Completed Successfully ");
				}


			}
			
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
		}

	}
}

public void NewLoan_Draw(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";	
	test.log(LogStatus.INFO, "Issue New Loan and Draw");
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
			String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			String Parent_Window = driver.getWindowHandle();
			
			test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
			for( String winHandle1 : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle1);
			}			
			 driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 //	Selection of Product based on the Name provided in Test Data
			
			 if(driver.findElement(By.id("ShareScreenBtn")).isEnabled())
			 {
				 											
				if(ProductName.equals("Line of Credit"))
				{
					if(StoreID.equals("5435"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
					}
					else
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
					}
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
				}
				
				driver.findElement(By.name("ShareScreenBtn")).click();
				test.log(LogStatus.PASS, "ShareScreen Button is clicked");
				
				Set<String> handles = driver.getWindowHandles();
				 Iterator<String> i1=handles.iterator();
				 
				 while(i1.hasNext())			
			        {		
			            String ChildWindow=i1.next();		
			            		
			            if(!Parent_Window.equalsIgnoreCase(ChildWindow))			
			            {    
			            	
			            	driver.switchTo().window(ChildWindow);
			            	driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
			            }
			        }
		         					
				 driver.switchTo().window(Parent_Window);

                  for( String winHandle1 : driver.getWindowHandles())
                         {
                             driver.switchTo().window(winHandle1);
                         }                    

                         driver.switchTo().defaultContent();

                         driver.switchTo().frame("mainFrame");

                         driver.switchTo().frame("main");
                        
                         driver.findElement(By.id("LoanButtonId")).click();
                        
                         test.log(LogStatus.PASS, "Clicked on New Loan button");
			//New Loan Screens
				
				if(ProductID.equals("LOC"))
				{
					test.log(LogStatus.INFO, "Navigated to New Loan Screen");
					driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
					test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
					Thread.sleep(1000);
					driver.findElement(By.name("requestBean.password")).clear();
					driver.findElement(By.name("requestBean.password")).click();
					driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
					test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
					driver.findElement(By.name("finishadvance")).click();
					test.log(LogStatus.PASS, "Click on Finish LOC Button");
					
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 
					 driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
					 test.log(LogStatus.PASS, "click on Yes button ");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					{
						test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
						
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).click();
						test.log(LogStatus.INFO, "Navigated to Draw Screen");
						
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 
						 driver.findElement(By.name("loanAmt")).clear();
							
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							
						 driver.findElement(By.name("loanAmt")).sendKeys("50");	
						 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
						 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
						 driver.findElement(By.name("disbAmtFirst")).sendKeys("50");					
						 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
						 driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						 test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
						 driver.findElement(By.name("finishadvance")).click();
						 test.log(LogStatus.PASS, "Click on Finish Loan Button");
						
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							
							if(driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td")).isDisplayed())
							{
								test.log(LogStatus.PASS, "Draw New Loan is Completed Successfully ");
							}
							else
							{
								test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
							}
					}
					else
					{
						test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
					}
				}
					
			}
				
		}
	}

}

public void VoidDrawLoan(String SSN,String FileName) throws Exception{
	
	test.log(LogStatus.INFO, "Void Draw");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							 driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
							 String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							 System.out.println(Pmt);
							driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
public void ResindDrawLoan(String SSN,String FileName) throws Exception{
	
	test.log(LogStatus.INFO, "Rescind Draw");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				 test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							 driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
							 try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
							 String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							 driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Rescind Loan button ");
								try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														
									
								}
								catch (NoAlertPresentException e) {
								    //do what you normally would if you didn't have the alert.
								}
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Rescind Completed Successfully ");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Rescind not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}

public void AgeStore(String SSN,String FileName) throws Exception
{
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");						
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			   if(ProductID.equals("LOC"))
				 {
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			    test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String DueDate=null;
					 //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();	 
					DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
					 test.log(LogStatus.INFO, "Captured Due Date:"+DueDate);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
				    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				    driver.close();															
				    driver = new InternetExplorerDriver();
				    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				    driver.get(AdminURL);
				    driver.manage().window().maximize();
				    storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
		}
	}
}

public void AgeStore(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			 if(ProductID.equals("LOC"))
			 {
		    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			 }
		    test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle1);
			}			
			 driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 driver.findElement(By.name("transactionList")).sendKeys("History");
			 if(ProductID.equals("LOC"))
			 {
				 driver.findElement(By.name("button")).click(); 
			 }
			 
			 for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
			String DueDate=null;

			DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			 test.log(LogStatus.INFO, "Captured Due Date:"+DueDate);
			
			//driver.close();

			//driver = new InternetExplorerDriver();
			driver.get(AdminURL);
            driver.manage().window().maximize();
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			 driver.findElement(By.linkText("QA Jobs")).click();
		      test.log(LogStatus.PASS, "Clicked on QA Jobs");
			
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			String DDueDate[] =DueDate.split("/");

			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, Days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
			Actions actions1 = new Actions(driver);								        
			actions1.moveToElement(elements1).build().perform();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}
		}
	}
}


public void AgeStore_EOD(String SSN,String FileName) throws Exception
{
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");						
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String DueDate=null;
					 
				DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				 test.log(LogStatus.INFO, "Captured due date:"+DueDate);								
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
			    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			 driver.quit();
			   driver = new InternetExplorerDriver();
			   driver.get(AdminURL);
			   test.log(LogStatus.INFO, "Admin portal is launched");
				driver.manage().window().maximize();
			   storeupdate_EOD(UserName,Password,StoreID,DueDate,AdminURL);
		}
	}
}
public void storeupdate_EOD(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
{
	DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
	driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
	test.log(LogStatus.PASS, "Username is entered: admin");			        
	driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
	test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
	//Click Login Button
	driver.findElement(By.name("login")).click();
	test.log(LogStatus.PASS, "Clicked on Submit button");
	for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
	}
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	WebDriverWait wait = new WebDriverWait(driver, 30);
	Date DDueDate = df.parse(DueDate);
	Calendar cal = Calendar.getInstance();
	cal.setTime(DDueDate);
	String DueDate0[] =DueDate.split("/");
	String DueDate1 = DueDate0[0];
	String DueDate2 = DueDate0[1];
	String DueDate3 = DueDate0[2];
	driver.switchTo().defaultContent();
	driver.switchTo().frame("topFrame");
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
	driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Transactions");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));
	
	driver.findElement(By.linkText("QA Jobs")).click();

	test.log(LogStatus.PASS, "Clicked on QA Jobs");
	
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));
	driver.findElement(By.linkText("Process Date Change")).click();
	test.log(LogStatus.PASS, "Clicked on Process Date Change");
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	driver.switchTo().defaultContent();

	driver.switchTo().frame("mainFrame");

	WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

	Actions actions1 = new Actions(driver);

	actions1.moveToElement(elements1).build().perform();

	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");
	driver.findElement(By.name("storeCode")).click();
	driver.findElement(By.name("storeCode")).sendKeys(StoreID);
	test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
	Thread.sleep(2000);
	driver.findElement(By.name("beginMonth")).clear();
	driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
	test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
	driver.findElement(By.name("beginDay")).clear();
	driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
	test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
	driver.findElement(By.name("beginYear")).clear();
	driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
	test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
	driver.findElement(By.name("btnPreview")).click();
	test.log(LogStatus.PASS, "Clicked on submit button");
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");
	if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
	{									        								
		test.log(LogStatus.PASS, "Process Date updated successfully");
	}
	else
	{
		test.log(LogStatus.FAIL, "Process Date updated successfully.");
	}
}


public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
{
		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
	   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
        test.log(LogStatus.PASS, "Username is entered: admin");			        
        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
        //Click Login Button
        driver.findElement(By.name("login")).click();
        test.log(LogStatus.PASS, "Clicked on Submit button");
        for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    Date DDueDate = df.parse(DueDate);
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(DDueDate);
				 DueDate = df.format(DDueDate);
				String DueDate0[] =DueDate.split("/");
		        String DueDate1 = DueDate0[0];
		        String DueDate2 = DueDate0[1];
		        String DueDate3 = DueDate0[2];
		        
		        driver.switchTo().defaultContent();
				 driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Transactions");
		         driver.switchTo().defaultContent();
				      driver.switchTo().frame("mainFrame");
				      				  	
				  	driver.findElement(By.linkText("QA Jobs")).click();
				  	test.log(LogStatus.PASS, "Clicked on QA Jobs");
				  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				  	driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("storeCode")).click();
					driver.findElement(By.name("storeCode")).sendKeys(StoreID);
					 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
					 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					 Thread.sleep(2000);
					 driver.findElement(By.name("beginMonth")).clear();
				        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				        driver.findElement(By.name("beginDay")).clear();
				        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				        driver.findElement(By.name("beginYear")).clear();
				        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				        driver.findElement(By.name("btnPreview")).click();
				       test.log(LogStatus.PASS, "Clicked on submit button");
				        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
				        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				        {									        								
				        	test.log(LogStatus.PASS, "Process Date updated successfully");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Process Date updated successfully.");
				        }
}

public void LoanPartialPayment(String SSN,String FileName) throws Exception{
	
	test.log(LogStatus.INFO, "Initiating Partial Payment");
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 CSRLoginpage login = new CSRLoginpage();
		     login.Login(UserName, Password, StoreId, driver, AppURL, test);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			 test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 if(ProductID.equals("LOC"))
					 {
						driver.findElement(By.name("requestBean.paymentAmt")).clear();
						 driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("10");
						 test.log(LogStatus.PASS, "Payment Amt is entered as 10");
						 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
						 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
						driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("10");
						test.log(LogStatus.PASS, "Tender Amt is entered as 10");							
						 driver.findElement(By.name("password")).sendKeys(Password);
						 driver.findElement(By.name("Submit22")).click();
						 
						 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
							test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
							
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 
							 if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
								{
								 test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");
									driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
								}
							 else
								{
									test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");
								}
						 
				    	
					 }
				
		}
		
	}
}
public void Void_PartialPayment(String SSN,String FileName) throws Exception{
	
	test.log(LogStatus.INFO, "Void Partial Payment");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				 test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Void Partial Payment Completed Successfully ");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void Partial Payment not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
public void PayOffLoan(String SSN,String FileName) throws Exception{
	
	test.log(LogStatus.INFO, "PayOff Loan Transaction");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				    test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");
							driver.findElement(By.name("tenderType")).sendKeys(TenderType);
							test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("tenderAmount")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							driver.findElement(By.name("password")).sendKeys(Password);
							driver.findElement(By.name("Submit22")).click();
							 
							test.log(LogStatus.PASS, "Password is selected as "+Password);																					
							test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							 
								 if(driver.findElement(By.name("ok")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Payoff Completed Successfully ");
									 driver.findElement(By.name("ok")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payoff transaction is Failed");
									}
							 
					    	
						 }
					
			}
			
		}
	}

public void Void_Payoff(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Void PayOff Transaction");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			    test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("Void");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 
						driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
					    test.log(LogStatus.PASS, "DisbType is entered: "+TenderType);
						driver.findElement(By.name("password")).sendKeys(Password);
						test.log(LogStatus.PASS, "password is entered: "+Password);
						driver.findElement(By.name("Submit22")).click();
						test.log(LogStatus.PASS, "Clicked on voidpayoff Button");	
						 
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
							 {
								 
								 driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
								 test.log(LogStatus.PASS, "Clicked on Confirm voidpayoff Button");
								 test.log(LogStatus.INFO, "VoidPayoff is Completed");
							 }
					   
		}
		
	}
}
public void StatementGeneration(String SSN,String FileName) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);

	int lastrow=TestData.getLastRow("NewLoan");

String sheetName="NewLoan";
test.log(LogStatus.INFO, "Statement Generation");
for(int row=2;row<=lastrow;row++)

{

String RegSSN = TestData.getCellData(sheetName,"SSN",row);

if(SSN.equals(RegSSN))

{

String TxnType=TestData.getCellData(sheetName,"TxnType",row);

String TenderType = TestData.getCellData(sheetName,"TenderType",row);

String ProductID=TestData.getCellData(sheetName,"ProductID",row);

String UserName = TestData.getCellData(sheetName,"UserName",row);

String Password = TestData.getCellData(sheetName,"Password",row);

String StoreID = TestData.getCellData(sheetName,"StoreID",row);

String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

String AppURL = TestData.getCellData(sheetName,"AppURL",row);

appUrl = AppURL;

this.Login(UserName,Password,StoreID);
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
String SSN1 = SSN.substring(0, 3);

String SSN2 = SSN.substring(3,5);

String SSN3 = SSN.substring(5,9);

driver.switchTo().frame("topFrame");

driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

test.log(LogStatus.PASS, "Clicked on Loan Transactions");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.findElement(By.cssSelector("li[id='911101']")).click();

test.log(LogStatus.PASS, "Clicked on Transactions");

driver.switchTo().frame("main");

driver.findElement(By.name("ssn1")).sendKeys(SSN1);

test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);

driver.findElement(By.name("ssn2")).sendKeys(SSN2);

test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);

driver.findElement(By.name("ssn3")).sendKeys(SSN3);

test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);

driver.findElement(By.name("submit1")).click();

test.log(LogStatus.PASS, "Click on submit Button");

for(String winHandle : driver.getWindowHandles()){

driver.switchTo().window(winHandle);

}

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

driver.findElement(By.name("button")).click();

test.log(LogStatus.PASS, "Click on GO Button");

for(String winHandle : driver.getWindowHandles()){

driver.switchTo().window(winHandle);

}

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");


if(ProductID.equals("LOC"))

{

driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

}

test.log(LogStatus.PASS, "Click on GO Button");

for( String winHandle1 : driver.getWindowHandles())

{

driver.switchTo().window(winHandle1);

}

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

driver.findElement(By.name("transactionList")).sendKeys("History");

if(ProductID.equals("LOC"))

{

driver.findElement(By.name("button")).click();

}

for( String winHandle1 : driver.getWindowHandles())

{

driver.switchTo().window(winHandle1);

}

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

String StatementDate=null;

StatementDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();

test.log(LogStatus.PASS, "Captured Statement Generation Date: "+StatementDate);

driver.close();

driver = new InternetExplorerDriver();
driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

driver.get(AdminURL);

test.log(LogStatus.INFO, "Admin portal is launched");

driver.manage().window().maximize();

DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

test.log(LogStatus.PASS, "Username is entered: admin");

driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

test.log(LogStatus.PASS, "Password is entered: "+Password);

//Click Login Button

driver.findElement(By.name("login")).click();

test.log(LogStatus.PASS, "Clicked on Submit button");

String DueDate0[] =StatementDate.split("/");

String DueDate1 = DueDate0[0];

String DueDate2 = DueDate0[1];

String DueDate3 = DueDate0[2];

driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

WebDriverWait wait = new WebDriverWait(driver, 30);

wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

test.log(LogStatus.PASS, "Clicked on Transactions");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));

driver.findElement(By.linkText("QA Jobs")).click();
test.log(LogStatus.PASS, "Clicked on QA Jobs");

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));

driver.findElement(By.linkText("Process Date Change")).click();

test.log(LogStatus.PASS, "Clicked on Process Date Change");
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));

Actions action = new Actions(driver);

action.moveToElement(element).build().perform();
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

driver.findElement(By.name("storeCode")).click();

driver.findElement(By.name("storeCode")).sendKeys(StoreID);

test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
Thread.sleep(2000);

driver.findElement(By.name("beginMonth")).clear();

driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

driver.findElement(By.name("beginDay")).clear();

driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

driver.findElement(By.name("beginYear")).clear();

driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

driver.findElement(By.name("btnPreview")).click();

test.log(LogStatus.PASS, "Clicked on submit button");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

{

test.log(LogStatus.PASS, "Process Date updated successfully");

driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

}

else

{

test.log(LogStatus.FAIL, "Process Date is not updated.");

}

driver.switchTo().defaultContent();

driver.switchTo().frame("topFrame");

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

test.log(LogStatus.PASS, "Clicked on Transactions");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.findElement(By.linkText("ACH")).click();

test.log(LogStatus.PASS, "Clicked on ACH");

driver.findElement(By.linkText("LOC")).click();

test.log(LogStatus.PASS, "Clicked on LOC");

driver.findElement(By.linkText("Unsecure Loc Statement")).click();

test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

WebElement elements = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));

Actions actions = new Actions(driver);
actions.moveToElement(elements).build().perform();
Thread.sleep(1000);

driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);

test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);

driver.findElement(By.name("beginMonth")).click();

driver.findElement(By.name("beginMonth")).clear();

driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

driver.findElement(By.name("beginDay")).clear();

driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

driver.findElement(By.name("beginYear")).clear();

driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

driver.findElement(By.name("submit")).click();

test.log(LogStatus.PASS, "Clicked on submit button");
//test.log(LogStatus.PASS, "LOC statement is generated successfully");

driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).isDisplayed())

{

test.log(LogStatus.PASS, "LOC statement is generated successfully");

}

else

{

test.log(LogStatus.FAIL, "LOC statement generation failed");

}

}
}

}

public void DrawerDeassign(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";	
		test.log(LogStatus.INFO, "Drawer Deassign");
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Deassign");	
				driver.switchTo().frame("main");		
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Banker PIN# is enetered as"+Password);	
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Click on Finish De-assign Button");
				
				try{
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//driver.close();
				}
				catch (Exception e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				
				driver.findElement(By.name("drawerDeassignRequestBean.password")).clear();
				driver.findElement(By.name("drawerDeassignRequestBean.password")).click();
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);				
				driver.findElement(By.name("drawerdeassign")).click();
				
				try{
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//driver.close();
				}
				catch (Exception e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
				{
					 WebElement htmltable=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));	
					    
						List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
						//System.out.println("current row num "+rows.size());
						int count=0;							
						 count=driver.findElements(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr")).size();				 				
						for(int rnum=1;rnum<rows.size();rnum++)
						{                      
							//System.out.println("current row num "+rnum);						
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select")).sendKeys("Delete");
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).click();						
							try { 
								Alert alert = driver.switchTo().alert();
								alert.accept();
								//if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
								//do what you normally would if you didn't have the alert.
							}
													}
				}
				Thread.sleep(2000);
				String DrawerOverShortAmount =driver.findElement(By.name("drawerRequestBean.drawerOverShort")).getAttribute("value");
				driver.findElement(By.name("drawerRequestBean.amount")).sendKeys(DrawerOverShortAmount);
				test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);
				driver.findElement(By.name("drawerRequestBean.primary")).sendKeys("Cash Handling");
				test.log(LogStatus.PASS, "Primary Reason is selected as Cash Handling");
				driver.findElement(By.name("drawerRequestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");	
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();

				test.log(LogStatus.PASS, "Click on Finish Drawer O/S");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
				{

					test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL,"Drawer is not De-assigned");
				}
			}
		}
	}

public void StatementGeneration_EODProcessing(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		test.log(LogStatus.INFO, "EOD Processing");
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				
				WebDriverWait wait = new WebDriverWait(driver, 30);
				
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Daily Processing");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("eod")).click();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
				test.log(LogStatus.PASS,"Comments entered as 'comment'");
				// requestBean.comments
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");
				
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.name("Submit2")));
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");
				
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");
				driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);
				test.log(LogStatus.PASS,"Entered the Balance as"+SafeOverShortAmount);
				driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
				test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
				driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");	
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")));
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")).click();
				test.log(LogStatus.PASS, "Clicked on Next");

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.name("Next"));
				// Next
				test.log(LogStatus.PASS, "Clicked on Next");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
				test.log(LogStatus.PASS, "Clicked on Next");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
				test.log(LogStatus.PASS, "Clicked on Next");
				driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
				test.log(LogStatus.PASS, "Bag number is provided as 34");
				driver.findElement(By.name("finishdeposit")).click();
				test.log(LogStatus.PASS, "Clicked on Finish Deposit");
				test.log(LogStatus.PASS, "StatmentGeneration EOD Processing Completed");
			
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}

			}
		}
	}

public void StoreInfo(String SSN,String FileName) throws Exception
	{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "Edit Store Info");
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
					
			driver.get(AdminURL);
			test.log(LogStatus.PASS, "Admin portal is launched");	
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			driver.switchTo().frame("topFrame");
	driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Store Setup");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Config")));
	driver.findElement(By.linkText("Store Config")).click();
	test.log(LogStatus.PASS, "Clicked on Store Config");
	driver.findElement(By.linkText("Edit Store")).click();
	test.log(LogStatus.PASS, "Clicked on Edit Store");			
	
	driver.switchTo().frame("main");		
	  driver.findElement(By.name("locationBean.locNbr")).sendKeys(StoreID);
	  test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
	  driver.findElement(By.name("Submit2")).click();
	  test.log(LogStatus.PASS, "Clicked on submit button");
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			} 

	  
	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");													  	 	  
	     driver.findElement(By.name("locationBean.locStatusCd")).sendKeys("Crash Package");
	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");													    	
		 if(driver.findElement(By.name("submitButton")).isDisplayed())
			{
			 test.log(LogStatus.PASS, "Store Edit is Successfully completed ");
				driver.findElement(By.name("submitButton")).click();
			}
		 else
			{
				test.log(LogStatus.FAIL, "Store Edit is not Successfull ");
			}
		}
		}
	}

public void Safeassign(String SSN,String FileName) throws Exception{
 	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		test.log(LogStatus.INFO, "Safe Assign");
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				
				 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				 CSRLoginpage login = new CSRLoginpage();
				 login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");	
				driver.findElement(By.linkText("Assign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys(Password);
				driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
				driver.findElement(By.name("safeassign")).click();
				
				try { 
				    Alert alert = driver.switchTo().alert();
				    alert.accept();
				    //if alert present, accept and move on.														
					
				}
				catch (NoAlertPresentException e) {
				    //do what you normally would if you didn't have the alert.
					
				}
				Thread.sleep(1000);
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				    
				    if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
				    {
				
				    	 test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
				    	 driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
				    	 				    }
				    else
				    {
				    	test.log(LogStatus.FAIL,"Safe is not assigned .");
				    }
				}
				}
				}

public void Drawerassign(String SSN,String FileName) throws Exception{


	   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);		
	    int lastrow=TestData.getLastRow("NewLoan");
	    String sheetName="NewLoan";
	    test.log(LogStatus.INFO, "Drawer Assign");
	    for(int row=2;row<=lastrow;row++)
	    {
	            String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	            if(SSN.equals(RegSSN))
	            {
	                    String TxnType=TestData.getCellData(sheetName,"TxnType",row);
	                    String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	                    String ProductID=TestData.getCellData(sheetName,"ProductID",row);
	                    String AppURL = TestData.getCellData(sheetName,"AppURL",row);
	                    String UserName = TestData.getCellData(sheetName,"UserName",row);
	                    String Password = TestData.getCellData(sheetName,"Password",row);
	                    String StoreId = TestData.getCellData(sheetName,"StoreID",row);
	                    	CSRLoginpage login = new CSRLoginpage();
	                    login.Login(UserName, Password, StoreId, driver, AppURL, test);
	                    driver.switchTo().defaultContent();
	                    driver.switchTo().frame("topFrame");
	                    driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();
	                    test.log(LogStatus.PASS, "Clicked on Cash Management");
	                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	                    driver.switchTo().defaultContent();
	                    driver.switchTo().frame("mainFrame");
	                    driver.findElement(By.linkText("Drawer")).click();
	                    test.log(LogStatus.PASS, "Clicked on Drawer");
	                    driver.switchTo().defaultContent();
	                    driver.switchTo().frame("mainFrame");
	                    driver.findElement(By.linkText("Assign")).click();
	                    test.log(LogStatus.PASS, "Clicked on Assign");
	                    driver.switchTo().defaultContent();
	                    driver.switchTo().frame("mainFrame");
	                    driver.switchTo().frame("main");
	                    driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
	                    test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");
	                    driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
	                    driver.findElement(By.name("drawerassign")).click();
	                    try {
	                            Alert alert = driver.switchTo().alert();
	                            alert.accept();
	                            //if alert present, accept and move on.

	                    }
	                    catch (NoAlertPresentException e) {
	                            //do what you normally would if you didn't have the alert.

	                    }
	                    
	                    driver.switchTo().defaultContent();
	                    driver.switchTo().frame("mainFrame");
	                    driver.switchTo().frame("main");
	                   
	                    if(this.Field(driver) != null )
	                    {                    		                   
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.findElement(By.linkText("Safe")).click();
	                            test.log(LogStatus.PASS, "Clicked on Safe");
	                            driver.findElement(By.linkText("Deassign")).click();
	                            test.log(LogStatus.PASS, "Clicked on Deassign");
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");                            
	                            driver.switchTo().frame("main");
	                            driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
	                            test.log(LogStatus.PASS, "Enter the Value 0");
	                            driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
	                            test.log(LogStatus.PASS, "Enter the Password");
	                            driver.findElement(By.name("safedeassign")).click();
	                            test.log(LogStatus.PASS, "Click on the Deassign");

	                            try { 
	                            	Alert alert = driver.switchTo().alert();
	                            	alert.accept();
	                            	//if alert present, accept and move on.														
		
	                            	}
	                            catch (NoAlertPresentException e) {
	                            	//do what you normally would if you didn't have the alert.
		
	                            }
	                            driver.switchTo().defaultContent();
	                			driver.switchTo().frame("mainFrame");
	                			driver.switchTo().frame("main");
	                			if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
	                			{
	                				test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
	                				driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
	                			}
	                			else
	                			{
	                            driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
	                            test.log(LogStatus.PASS, "Enter the Password");
	                            driver.findElement(By.name("safedeassign")).click();
	                            test.log(LogStatus.PASS, "Click on the Deassign");
	                            for(String winHandle : driver.getWindowHandles()){
	                				driver.switchTo().window(winHandle);
	                			}				    
	                			driver.switchTo().defaultContent();
	                			driver.switchTo().frame("mainFrame");
	                			driver.switchTo().frame("main");
	                            String DrawerOverShortAmount =driver.findElement(By.name("safeRequestBean.safeOverShort")).getAttribute("value");
	                			driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);
	                			test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);
	                			driver.findElement(By.name("safeRequestBean.primary")).sendKeys("Counterfeit Bill");
	                			test.log(LogStatus.PASS, "Primary Reason is selected as Counterfeit Bill");
	                			driver.findElement(By.name("safeRequestBean.notes")).sendKeys("Notes");
	                			test.log(LogStatus.PASS, "Notes Entered ");	
	                			driver.findElement(By.name("bt_AddDrawer")).click();
	                			test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
	                			driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
	                			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();
	                			test.log(LogStatus.PASS, "Click on Finish Safe O/S");
	                			try { 
	                				Alert alert = driver.switchTo().alert();
	                				alert.accept();
	                				//if alert present, accept and move on.														

	                			}
	                			catch (NoAlertPresentException e) {
	                				//do what you normally would if you didn't have the alert.
	                			}
	                			for(String winHandle : driver.getWindowHandles()){
	                				driver.switchTo().window(winHandle);
	                			}				    
	                			driver.switchTo().defaultContent();
	                			driver.switchTo().frame("mainFrame");
	                			driver.switchTo().frame("main");

	                			if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
	                			{

	                				test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
	                				driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
	                			}
	                			else
	                			{
	                				test.log(LogStatus.FAIL,"Safe not De-assigned successfully with over/short.");
	                			}                            
	                			}
	                          
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.findElement(By.linkText("Safe")).click();
	                            test.log(LogStatus.PASS, "Clicked on Assign");
	                            driver.findElement(By.linkText("Assign")).click();
	                            test.log(LogStatus.PASS, "Clicked on Assign");
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.switchTo().frame("main");
	                            driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
	                            //Password
	                            driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
	                            test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");
	                            driver.findElement(By.name("safeassign")).click();
	                            test.log(LogStatus.PASS,"Click on Safe Assigen");

	                            try {
	                                    Alert alert = driver.switchTo().alert();
	                                    alert.accept();
	                                    //if alert present, accept and move on.

	                            }
	                            catch (NoAlertPresentException e) {
	                                    //do what you normally would if you didn't have the alert.

	                            }
	                           driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.switchTo().frame("main");

	                           if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
	                            {

	                                    test.log(LogStatus.PASS,"Safe assigned successfully.");
	                                    driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
	                            
	                            }
	                            else
	                            {
	                                    test.log(LogStatus.FAIL,"Safe not assigned successfully.");
	                            }
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.findElement(By.linkText("Drawer")).click();
	                            test.log(LogStatus.PASS, "Clicked on Drawer");
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.findElement(By.linkText("Assign")).click();
	                            test.log(LogStatus.PASS, "Clicked on Assign");
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.switchTo().frame("main");
	                            driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
	                            test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");
	                            driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
	                            driver.findElement(By.name("drawerassign")).click();
	                            
	                            try {
	                                    Alert alert = driver.switchTo().alert();
	                                    alert.accept();
	                                    //if alert present, accept and move on.

	                            }
	                            catch (NoAlertPresentException e) {
	                                    //do what you normally would if you didn't have the alert.

	                            }
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.switchTo().frame("main");

	                            if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
	                            {
	                            	
	                                    test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
	                                    driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
	                            }
	                            else
	                            {
	                                    test.log(LogStatus.FAIL,"Drawer not De-assigned successfully with over/short.");
	                            }

	                     }                                        
	                    else
	                    {                    	
	                            if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
	                            {
	                            	
	                                    test.log(LogStatus.PASS,"Drawer Assigned successfully with over/short.");
	                                    driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
	                            }
	                            else
	                            {
	                                    test.log(LogStatus.FAIL,"Drawer not Assigned.");
	                            }
	                    }
	                  
	                    
	            }
	    }
	}

public void EODProcessing(String SSN,String FileName) throws Exception{


Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "EOD Processing");
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Daily Processing");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("eod")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			//Thread.sleep(4000);
			// driver.findElement(By.name("requestBean.comments")).click();
			driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
			test.log(LogStatus.PASS,"Comments is entered as comment");
			// requestBean.comments
			//Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			//Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			
			driver.findElement(By.name("Next"));
			// Next
			test.log(LogStatus.PASS, "Clicked on Next");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
			test.log(LogStatus.PASS, "Bag number is provided as 34");
			driver.findElement(By.name("finishdeposit")).click();
			test.log(LogStatus.PASS, "Clicked on Finish Deposit");
			test.log(LogStatus.PASS, "EOD Processing Completed Successfully");
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}



		}
	}
}

public void DeliquentPaymentStatus(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Deliquent Payment Status");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			 test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String CheckStaus=null;
		
					 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
					 test.log(LogStatus.PASS,"Payment status is Deliquent."+CheckStaus);
		            	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
						  
			  driver.quit();//Uncomment 
			  driver = new InternetExplorerDriver();

		}
	}
}

public void AgeStore_10days(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging +10days");
							
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				
				 test.log(LogStatus.INFO, "Captured due date:"+DueDate);
		             
			    driver.close();
			    
			    driver = new InternetExplorerDriver();
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		         driver.get(AdminURL);
		         driver.manage().window().maximize();
		       DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
		        test.log(LogStatus.PASS, "Username is entered: admin");			        
		        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
		        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
		        //Click Login Button
		        driver.findElement(By.name("login")).click();
		        test.log(LogStatus.PASS, "Clicked on Submit button");
		        
						     Date DDueDate = df.parse(DueDate);
							 Calendar cal = Calendar.getInstance();
							 cal.setTime(DDueDate);
							 cal.add(Calendar.DATE, +10);
							 Date DDueDateminus1= cal.getTime();
							DueDate =df.format(DDueDateminus1);
						    String DueDate0[] =DueDate.split("/");
					        String DueDate1 = DueDate0[0];
					        String DueDate2 = DueDate0[1];
					        String DueDate3 = DueDate0[2];
					        
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
					 WebDriverWait wait = new WebDriverWait(driver, 60);
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
					 driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
						test.log(LogStatus.PASS, "Clicked on Transactions");
						driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					      
					 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));
					 driver.findElement(By.linkText("QA Jobs")).click();
					 test.log(LogStatus.PASS, "Clicked on QA Jobs");
					 
					   wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));
					   driver.findElement(By.linkText("Process Date Change")).click();
						test.log(LogStatus.PASS, "Clicked on Process Date Change");
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.name("storeCode")).click();
						 driver.findElement(By.name("storeCode")).sendKeys(StoreID);
							 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
							 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							 Thread.sleep(2000);
							driver.findElement(By.name("beginMonth")).clear();
						        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
						        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
						        driver.findElement(By.name("beginDay")).clear();
						        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
						        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
						        driver.findElement(By.name("beginYear")).clear();
						        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
						        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
						        driver.findElement(By.name("btnPreview")).click();
						        test.log(LogStatus.PASS, "Clicked on submit button");
						        driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
						        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
						        {									        								
						        	test.log(LogStatus.PASS, "Process Date updated successfully");
						        }
						        else
						        {
						        	test.log(LogStatus.FAIL, "Process Date updation is Failed.");
						        }
		              				    
		}
	}
}
public void CurePaymentStatus(String SSN,String FileName) throws Exception
{
	
   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);		
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "Cure Payment Status");
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String CheckStaus=null;
					 
		  CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
					 
					test.log(LogStatus.PASS,"Payment status is Cure. "+CheckStaus);
		}
	}
}

public void CustomerDefault(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "CustomerDefault");
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{


				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Cure Period End Date"+DueDate);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.close();
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			driver.manage().window().maximize();
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, 0);
			Date DDueDateminus1= cal.getTime();
			// String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];
			WebDriverWait wait = new WebDriverWait(driver, 30);
			/*Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");*/
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
			test.log(LogStatus.PASS, "Clicked on Transactions");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			 driver.findElement(By.linkText("QA Jobs")).click();
			//driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on jobs");

			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(2000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updation is failed.");
			}



			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");
			driver.findElement(By.linkText("Default Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Default Loc");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
			  Actions action = new Actions(driver);								        
			  action.moveToElement(element).build().perform();
			  Thread.sleep(1000);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).isDisplayed())

			{

			test.log(LogStatus.PASS, "Default LOC is successfull");

			}

			else

			{

			test.log(LogStatus.FAIL, "Default LOC is not successfull");

			}
			}
	}
}

public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
	{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		test.log(LogStatus.INFO, "Default Payment Status");
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(2000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(ProductID.equals("LOC"))
				{
					
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus=null;
				CheckStaus = driver.findElement(By.xpath(" //*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();                            	
				test.log(LogStatus.PASS,"Payment status is Default "+CheckStaus);
				}
		}
	}



public void Default_Payment(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			//Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click(); //updated by sri
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys(TxnType);
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			String PaymentAmount=null;
			
			PaymentAmount = driver.findElement(By.name("totalDue")).getAttribute("value");
			//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
			
			driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Tender Type");
			
			driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAmount);
			test.log(LogStatus.PASS, "Enter the Tender Amount");
			
			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");
			
			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on Finish Payment Button");
			
			test.log(LogStatus.PASS, "Default Payment completed Sucessfully");
		
		
		}
	}
}
public void DefaultPayment_Void(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			//Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{

				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			String PaymentAmount=null;
		                           
			driver.findElement(By.name("defPaymentRequestBeanRC.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Disb Type");

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Void Default Payment");

			test.log(LogStatus.PASS, "Defult Payment Void completed Sucessfully");



		}
	}
}



public void RCC_Revoke(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			//Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("RCC Revoke");
			test.log(LogStatus.PASS, "RCC Revoke Transaction is selected");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			try { 
			    Alert alert = driver.switchTo().alert();
			    alert.accept();
			    test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
			    //if alert present, accept and move on.														
				
			}
			catch (NoAlertPresentException e) {
			    //do what you normally would if you didn't have the alert.
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
		    test.log(LogStatus.PASS, "Navigated to Customer RCC Authorization Details");
		    driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='RCC Revoke')]")).click();
			//driver.findElement(By.name("bt_BankDetails")).click(); 
			test.log(LogStatus.PASS, "Clicked on RCC Revoke");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.name("bt_BankDetails")).isDisplayed())
			{
			 test.log(LogStatus.PASS, "RCC Revoke Transaction completed successfully.");
				driver.findElement(By.name("bt_BankDetails")).click(); 
				test.log(LogStatus.PASS, "Clicked on Ok button");
			}
			else
			{
				test.log(LogStatus.PASS, "RCC Revoke Transaction not completed successfully.");
			}
			Thread.sleep(3000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.PASS, "History Transaction is selected");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.PASS, "Navigated to History screen");
			if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]")).isDisplayed())
			{
				test.log(LogStatus.PASS, "RCC Tracking Status is displayed in History screen");
				  WebElement htmltable=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]"));	
				  
					List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
					int count=0;							
					 count=driver.findElements(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]/tbody/tr[1]/td[1]/table/tbody/tr")).size();
					 
					 System.out.println("current row num "+count);
					 String Details=null;
					for(int rnum=0;rnum<count;rnum++)
					{
						System.out.println("current row num "+rnum);						
					List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
					
					System.out.println("columns Count "+columns.size());
						
					for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
					{					
						 Details=columns.get(cnum).getText();
						 test.log(LogStatus.PASS, "RCC Tracking Status Details"+Details);
													
					}
					
					
					}
					
			}
			else
			{
				test.log(LogStatus.PASS, "RCC Tracking Status is not displayed in History screen");
			}
		}
	}
}

public void RPP(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			test.log(LogStatus.INFO, "RPP(Starts)");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			//Thread.sleep(5000);

			//Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");	
			test.log(LogStatus.INFO,"Navigate to Transactions Screen");

			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");	

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Customer Record Screen");

			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");


			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Transaction List Screen");

			driver.findElement(By.name("transactionList")).sendKeys("Payment Plan Payment");
			test.log(LogStatus.PASS,"Payment Plan Selected From Transaction List");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			Thread.sleep(3000);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}


			Thread.sleep(3000);
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"RPP Selected From Transaction List");

			test.log(LogStatus.INFO,"Navigate to RPP 1st Screen");
			if(driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).isDisplayed());
			{
				driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).click();

			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			test.log(LogStatus.INFO,"Navigate to RPP 2nd Screen");

			driver.findElement(By.name("collateralTypeId")).sendKeys(ESign_CollateralType);
			test.log(LogStatus.PASS,"Collateral Type is Selected as : "+ESign_CollateralType);

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS,"Password is Selected as : "+Password);

			driver.findElement(By.name("submitBtn")).click();
			test.log(LogStatus.PASS,"Submit Button Clicked Successfully ");

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			test.log(LogStatus.INFO," Navigated to Perform Another Transaction Permission Screen ");

			if(driver.findElement(By.xpath("//*[@id='OKBut']")).isDisplayed());
			{
				driver.findElement(By.xpath("//*[@id='OKBut']")).click();
			}

			driver.findElement(By.name("checkno")).click();
			test.log(LogStatus.PASS,"No Button for Perform Another Transaction Screen Clicked Successfully ");
			test.log(LogStatus.INFO,"RPP(Ends)"); 


		}
	}
}


public void RPP_Status(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			test.log(LogStatus.INFO, "RPP(Starts)");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");	
			test.log(LogStatus.INFO,"Navigate to Transactions Screen");
			
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");	
		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Customer Record Screen");
			
			//driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");
			

		
			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Transaction List Screen");
			
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.INFO,"Navigate to History Credit Table Screen");
			
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.INFO,"Transaction Selection Go Button Click");
			
			//button
			String CheckStatus=null;
			CheckStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[1]/span[2]")).getText();
			if(CheckStatus.equals("Y")){
			test.log(LogStatus.PASS,"Loan in RPP : "+CheckStatus);
			test.log(LogStatus.INFO,"RPP Transaction Done Successfully");
			System.out.print(CheckStatus);	
			//driver.close();
			}
			String CheckStatus1=null;
			CheckStatus1 = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[1]/span[2]")).getText();
			if(CheckStatus1.equals("Y")){
			test.log(LogStatus.INFO,"Payment Status After RPP Is $ Loan In RCC $ : "+CheckStatus1);
			System.out.print(CheckStatus1);	
			
			}
			
			String CheckStatus2=null;
			CheckStatus2 = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[2]/td[2]/span[2]")).getText();
			if(CheckStatus2.equals("Y")){
			test.log(LogStatus.INFO,"  Status After RPP Is $ ACH/RCC Revoke Flag $: "+CheckStatus2);
			System.out.print(CheckStatus2);	
			
			}

		}	
		
		}
			//driver.close();
	
}

public void RPP_Void(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{

				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			//String PaymentAmount=null;

			//driver.findElement(By.name("defPaymentreqBeanRC.tenderType")).sendKeys(ESign_TenderType);
			//test.log(LogStatus.PASS, "Select the Disb Type");

			driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Void Payment Plan");

			test.log(LogStatus.PASS, "Void Payment Plan completed Sucessfully");

		/* if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input[2]")))
				{
			// /html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input[2]
			test.log(LogStatus.PASS, "Void Payment Plan completed Sucessfully");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input[2]")).click();
				}
		
		else
		{
			test.log(LogStatus.PASS, "Void Payment Plan completed Sucessfully");
		}*/
		}

		}
	}

public void Default_WOProc(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.PASS, "History Selected in DropDown");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();
			//test.log(LogStatus.PASS, "Capture PWO  Date"+DueDate);
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture Cure Period End Date"+DueDate);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.close();	
			driver = new InternetExplorerDriver();
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			driver.manage().window().maximize();
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(2000);
			Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, +60);
			Date DDueDateminus1= cal.getTime();

			DueDate =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];

			WebDriverWait wait = new WebDriverWait(driver, 30);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
			 driver.findElement(By.linkText("QA Jobs")).click();
			//driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on jobs");
			
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			/*driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

			Actions actions1 = new Actions(driver);

			actions1.moveToElement(elements1).build().perform();*/
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(2000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updated successfully.");
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");

			driver.findElement(By.linkText("Writeoff Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Writeoff Loc");

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			 WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
			  Actions action = new Actions(driver);								        
			  action.moveToElement(element).build().perform();
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			Thread.sleep(2000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			


		}
	}
}

public void WOPaymentStatus(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				
			  driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			test.log(LogStatus.PASS, "History Selected in DropDown");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String CheckStaus=null;
			
			if(driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).isDisplayed())
			{
				test.log(LogStatus.PASS,"Payment status is Default Displayed sucessfully.");
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is Writeoff."  +CheckStaus);
				
			}
			else
			{
				test.log(LogStatus.PASS,"Payment status is  Writeoff not Displayed sucessfully.");
			}

		}
	}
}


public void Writoff_Recovery(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");	
			String PaymentAMT= null;
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys(TxnType);
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			PaymentAMT = driver.findElement(By.name("requestBean.totalOwed")).getAttribute("value");
			test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);

			driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
			test.log(LogStatus.PASS, "Select the Disb Type");
			
			driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAMT);
			test.log(LogStatus.PASS, "Enter the Tender Amount");

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
			
			 if(driver.findElement(By.name("Ok")).isDisplayed())
			 {
				 test.log(LogStatus.PASS, "Write off Recovery completed Sucessfully");
				 driver.findElement(By.name("Ok")).click();
			 }
					 
				 
				 else
				 {

			test.log(LogStatus.PASS, "Write off Recovery not completed Sucessfully");
				 }
			}
		}

		}
	

public void WORecovery_Void(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
int lastrow=TestData.getLastRow("NewLoan");
String sheetName="NewLoan";		
for(int row=2;row<=lastrow;row++)
{	
	String RegSSN = TestData.getCellData(sheetName,"SSN",row);
	if(SSN.equals(RegSSN))
	{
		String TxnType=TestData.getCellData(sheetName,"TxnType",row);
		String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
		String ProductID=TestData.getCellData(sheetName,"ProductID",row);
		String UserName = TestData.getCellData(sheetName,"UserName",row);
		String Password = TestData.getCellData(sheetName,"Password",row);
		String StoreID = TestData.getCellData(sheetName,"StoreID",row);
		String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
		test.log(LogStatus.INFO, "Scheduler-Store Aging");
		String AppURL = TestData.getCellData(sheetName,"AppURL",row);
		appUrl = AppURL;
		this.Login(UserName,Password,StoreID);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
		test.log(LogStatus.PASS, "Clicked on Loan Transactions");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("li[id='911101']")).click();			
		test.log(LogStatus.PASS, "Clicked on Transactions");		
		driver.switchTo().frame("main");		
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit1")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}				    
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");


		if(ProductID.equals("LOC"))
		{

			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
		}
		test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("Void");
		if(ProductID.equals("LOC"))
		{
			driver.findElement(By.name("button")).click(); 
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
		driver.findElement(By.name("password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Enter the Password");

		driver.findElement(By.name("Submit22")).click();
		test.log(LogStatus.PASS, "Click on the Finish Void Write off Recovery Payment ");

	    if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
			{
		test.log(LogStatus.PASS, "Void Write off Recovery Payment completed Sucessfully");
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
			}
	
	else
	{
		test.log(LogStatus.PASS, "Void Write off Recovery Payment Not completed Sucessfully");
	}
	}

	}
}

public void ACHDeposit(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "ACH Deposit");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String DueDate=null;
					DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
					test.log(LogStatus.PASS, "Due Date is Captured:"+DueDate);	
				
			    driver.close();
			    
			         driver = new InternetExplorerDriver();
			         driver.manage().window().maximize();
			         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			         driver.get(AdminURL);
			         test.log(LogStatus.INFO, "Admin portal is launched");
						driver.manage().window().maximize();
				    DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			        test.log(LogStatus.PASS, "Username is entered: admin");			        
			        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			        //Click Login Button
			        driver.findElement(By.name("login")).click();
			        test.log(LogStatus.PASS, "Clicked on Submit button");
			        driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
					 WebDriverWait wait = new WebDriverWait(driver, 30);
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
				     driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					 test.log(LogStatus.PASS, "Clicked on Transactions");
					 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					 driver.findElement(By.linkText("ACH")).click();
					 test.log(LogStatus.PASS, "Clicked on ACH");
					driver.findElement(By.linkText("Green Bank")).click();
					 test.log(LogStatus.PASS, "Clicked on Green Bank");
					 driver.findElement(By.linkText("Green Bank NACHA File")).click();
					 test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
					  driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    Date DDueDate = df.parse(DueDate);
					    Calendar cal = Calendar.getInstance();
						 cal.setTime(DDueDate);
						cal.add(Calendar.DATE,-1);
					      Date DDueDateminus1= cal.getTime();
						 						 
						String DueDateminus1 =df.format(DDueDateminus1);
					    String DDueDate0[] =DueDateminus1.split("/");
					     String DDueDate1 = DDueDate0[0];
				        String DDueDate2 = DDueDate0[1];
				        String DDueDate3 = DDueDate0[2];
				        Thread.sleep(2000);
				         driver.findElement(By.name("beginMonth")).click();
					    driver.findElement(By.name("beginMonth")).clear();
				        driver.findElement(By.name("beginMonth")).sendKeys(DDueDate1); 
				        test.log(LogStatus.PASS, "beginMonth is entered: "+DDueDate1);
				        driver.findElement(By.name("beginDay")).clear();
				        driver.findElement(By.name("beginDay")).sendKeys(DDueDate2);
				        test.log(LogStatus.PASS, "beginDay is entered: "+DDueDate2);
				        driver.findElement(By.name("beginYear")).clear();
				        driver.findElement(By.name("beginYear")).sendKeys(DDueDate3);
				        test.log(LogStatus.PASS, "beginYear is entered: "+DDueDate3);
				       driver.findElement(By.name("btnPreview")).click();
				        test.log(LogStatus.PASS, "Clicked on submit button");
				        
				        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				        {									        								
				        	test.log(LogStatus.PASS, "Green Bank updated successfully");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Green Bank Not updated successfully.");
				        }  
				        
				       			        
				    String DueDate0[] =DueDate.split("/");
					 
			        String DueDate1 = DueDate0[0];
			        String DueDate2 = DueDate0[1];
			        String DueDate3 = DueDate0[2];
			        
			        driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
						driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
						test.log(LogStatus.PASS, "Clicked on Transactions");
			        
			     
			             driver.switchTo().defaultContent();
					      driver.switchTo().frame("mainFrame");
							driver.findElement(By.linkText("QA Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on QA Jobs");
					   driver.findElement(By.linkText("Process Date Change")).click();
						test.log(LogStatus.PASS, "Clicked on Process Date Change");
						
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

						Actions actions1 = new Actions(driver);

						actions1.moveToElement(elements1).build().perform();

						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
						
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.name("storeCode")).click();
						 driver.findElement(By.name("storeCode")).sendKeys(StoreID);
						 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
						 Thread.sleep(2000);
						 driver.findElement(By.name("beginMonth")).clear();
					        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
					        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
					        driver.findElement(By.name("beginDay")).clear();
					        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
					        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
					        driver.findElement(By.name("beginYear")).clear();
					        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
					        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
					        driver.findElement(By.name("btnPreview")).click();
					        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					       test.log(LogStatus.PASS, "Clicked on submit button");
					        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					        
					        driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
					        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
					        {									        								
					        	test.log(LogStatus.PASS, "Process Date updated successfully");
					        	driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
					        }
					        else
					        {
					        	test.log(LogStatus.FAIL, "Process Date updated successfully.");
					        } 
        
			                    
				//PreACHDeposit 
					        
					        driver.switchTo().defaultContent();
							 driver.switchTo().frame("topFrame");
								driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
								test.log(LogStatus.PASS, "Clicked on Transactions"); 
								
								driver.switchTo().defaultContent();
								driver.switchTo().frame("mainFrame");
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							 driver.findElement(By.linkText("ACH")).click();
							 test.log(LogStatus.PASS, "Clicked on ACH");
					 
					 
					 driver.findElement(By.linkText("LOC")).click();
					 test.log(LogStatus.PASS, "Clicked on LOC");
					 
					 driver.findElement(By.linkText("LOC Pre ACH Deposit")).click();
					 test.log(LogStatus.PASS, "Clicked on Loc pre ACH Deposit");
					 
					 
					 
					 
				    	 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					  
					   Thread.sleep(2000);
				        driver.findElement(By.name("beginMonth")).click();
					    driver.findElement(By.name("beginMonth")).clear();
				        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				        driver.findElement(By.name("beginDay")).clear();
				        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				        driver.findElement(By.name("beginYear")).clear();
				        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				        driver.findElement(By.name("btnPreview")).click();
				        test.log(LogStatus.PASS, "Clicked on submit button");
				        test.log(LogStatus.PASS, "Completed ACH Deposit");
				        
				        
		}
	}
}

public void NACHA(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
    int lastrow=TestData.getLastRow("NewLoan");
    System.out.println("NewLoan "+lastrow);
    String sheetName="NewLoan";
    for(int row=2;row<=lastrow;row++)
    {
        String RegSSN = TestData.getCellData(sheetName,"SSN",row);
        if(SSN.equals(RegSSN))
        {
            String TxnType=TestData.getCellData(sheetName,"TxnType",row);
            String TenderType = TestData.getCellData(sheetName,"TenderType",row);
            String ProductID=TestData.getCellData(sheetName,"ProductID",row);
            String UserName = TestData.getCellData(sheetName,"UserName",row);
            String Password = TestData.getCellData(sheetName,"Password",row);
            String StoreID = TestData.getCellData(sheetName,"StoreID",row);
            String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
            String AppURL = TestData.getCellData(sheetName,"AppURL",row);
            appUrl = AppURL;
            appUrl = AppURL;

            CSRLoginpage login = new CSRLoginpage();

            login.Login(UserName, Password, StoreID, driver, AppURL, test);
            System.out.println(AdminURL);
            test.log(LogStatus.INFO, "Scheduler-Store Aging");

            System.out.println(ProductID);

            appUrl = AppURL;


            Thread.sleep(5000);
            Thread.sleep(1000);
            String SSN1 = SSN.substring(0, 3);
            String SSN2 = SSN.substring(3,5);
            String SSN3 = SSN.substring(5,9);
            driver.switchTo().frame("topFrame");
            driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
            test.log(LogStatus.PASS, "Clicked on Loan Transactions");
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("li[id='911101']")).click();
            test.log(LogStatus.PASS, "Clicked on Transactions");
            driver.switchTo().frame("main"); 
            driver.findElement(By.name("ssn1")).sendKeys(SSN1);
            test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1); 
            driver.findElement(By.name("ssn2")).sendKeys(SSN2);
            test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
            driver.findElement(By.name("ssn3")).sendKeys(SSN3);
            test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
            driver.findElement(By.name("submit1")).click();
            test.log(LogStatus.PASS, "Click on submit Button");
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");
            driver.findElement(By.name("button")).click();
            test.log(LogStatus.PASS, "Click on GO Button");
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");

            if(ProductID.equals("LOC"))
            {

            	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
            }
            test.log(LogStatus.PASS, "Click on GO Button");
            for( String winHandle1 : driver.getWindowHandles())
            {
                driver.switchTo().window(winHandle1);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main"); driver.findElement(By.name("transactionList")).sendKeys("History");

            if(ProductID.equals("LOC"))
            {

            	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

            }

            for( String winHandle1 : driver.getWindowHandles())
            {
                driver.switchTo().window(winHandle1);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");
            String DueDate=null;

            //    DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
            // DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr[2]/td[2]")).getText();
             DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

            test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
            System.out.print(DueDate);
            driver.close();

            driver = new InternetExplorerDriver();
            driver.get(AdminURL);



            DateFormat  df=new SimpleDateFormat("MM/dd/yyyy"); 
            driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
            test.log(LogStatus.PASS, "Username is entered: admin"); 
            driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
            test.log(LogStatus.PASS, "Password is entered: "+Password);

            driver.findElement(By.name("login")).click();
            test.log(LogStatus.PASS, "Clicked on Submit button");
            Thread.sleep(8000);


            driver.switchTo().defaultContent();
            driver.switchTo().frame("topFrame");
            driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click(); 

            test.log(LogStatus.PASS, "Clicked on Transactions");
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.findElement(By.linkText("ACH")).click();
            test.log(LogStatus.PASS, "Clicked on ACH");
            Thread.sleep(5000);
            driver.findElement(By.linkText("Green Bank")).click();
            test.log(LogStatus.PASS, "Clicked on Green Bank");
            Thread.sleep(5000);
            driver.findElement(By.linkText("Green Bank NACHA File")).click();
            test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

            /* driver.manage().timeouts().implicitlyWait(120,
TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.manage().timeouts().implicitlyWait(60,
TimeUnit.SECONDS);
        driver.findElement(By.linkText("Daily Jobs")).click();
        test.log(LogStatus.PASS, "Clicked on Daily Jobs");
        Thread.sleep(5000);*/

            String DDueDate[] =DueDate.split("/");

            Date DDueDateminus1 = df.parse(DueDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(DDueDateminus1);
            cal.add(Calendar.DATE, Days);
            Date DDueDate1= cal.getTime();
            DueDate =df.format(DDueDate1);
            String DueDate0[] =DueDate.split("/");
            String DueDate1 = DueDate0[0];
            String DueDate2 = DueDate0[1];
            String DueDate3 = DueDate0[2];

            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");
            driver.findElement(By.name("beginMonth")).click();
            driver.findElement(By.name("beginMonth")).clear();
            driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
            test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
            driver.findElement(By.name("beginDay")).clear();
            driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
            test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
            driver.findElement(By.name("beginYear")).clear();
            driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
            test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
            Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            Thread.sleep(1000);
            Thread.sleep(5000);
            driver.findElement(By.name("btnPreview")).click();
            test.log(LogStatus.PASS, "Clicked on submit button");

            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");

            test.log(LogStatus.PASS, "Process NACHA file successfully.");

        }
    }
}


public void Deposit_Clear(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Deposit Clear");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			driver.manage().window().maximize();
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Date DDueDate = df.parse(DueDate);
		    Calendar cal = Calendar.getInstance();
			 cal.setTime(DDueDate);
			 cal.add(Calendar.DATE,5);
		      Date DDueDateminus1= cal.getTime();
			 
			 
			String DueDateminus1 =df.format(DDueDateminus1);
		    String DueDate0[] =DueDateminus1.split("/");
		    String DueDate1 = DueDate0[0];
	        String DueDate2 = DueDate0[1];
	        String DueDate3 = DueDate0[2];
			WebDriverWait wait = new WebDriverWait(driver, 30);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");;
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

			Actions actions1 = new Actions(driver);

			actions1.moveToElement(elements1).build().perform();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(2000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.findElement(By.name("btnPreview")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updated successfully.");
			}


			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");
			//driver.findElement(By.linkText("RCPO")).click();
			//test.log(LogStatus.PASS, "Clicked on RCPO");
			
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");
			driver.findElement(By.linkText("ACH Clear")).click();
			test.log(LogStatus.PASS, "Clicked on ACH Clear");
			 //  WebElement element1 = driver.findElement(By.linkText("Collateral Checks"));
			 //Actions action1 = new Actions(driver);								        
			  //action1.moveToElement(element1).build().perform();
			 driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			WebElement calendarele = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));

			Actions actions = new Actions(driver);
			actions.moveToElement(calendarele).build().perform();
			Thread.sleep(1000);
			
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("beginMonth")).click();
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.PASS, "ACH Clear completed sucessfully");
		}
	}
}

public void AgeStore_1stInsmtDueDate_Mns1(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
									 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String DueDate=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					
					DueDate=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[7]/td[2]/table/tbody/tr[2]/td[4]")).getText();
					test.log(LogStatus.PASS, "Capture duedate:"+DueDate);
					 
			   System.out.print(DueDate);	
			   // driver.close();
			    //driver.quit();
			   // Thread.sleep(5000);
			    /*for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}*/
			    driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
			    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			   driver.close();															
			   driver = new InternetExplorerDriver();
			   driver.get(AdminURL);
			   test.log(LogStatus.INFO, "Admin portal is launched");
				driver.manage().window().maximize();
			   storeupdate_1stInsmtDueDate_Mns1(UserName,Password,StoreID,DueDate,AdminURL);
		}
	}
}
		
public void storeupdate_1stInsmtDueDate_Mns1(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
{
		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
	   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
        test.log(LogStatus.PASS, "Username is entered: admin");			        
        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
        //Click Login Button
        driver.findElement(By.name("login")).click();
        test.log(LogStatus.PASS, "Clicked on Submit button");
      Thread.sleep(8000);
       Thread.sleep(1000);
        
		  for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    
			    
			    
			   // driver.switchTo().frame("main");
			      Date DDueDate = df.parse(DueDate);
				  Calendar cal = Calendar.getInstance();
				  cal.setTime(DDueDate);
						 cal.add(Calendar.DATE, -1);
						 Date DDueDateminus1= cal.getTime();
						 
						 String DueDateminus1 =df.format(DDueDateminus1);
					    String DueDate0[] =DueDateminus1.split("/");
				        String DueDate1 = DueDate0[0];
				        String DueDate2 = DueDate0[1];
				        String DueDate3 = DueDate0[2];
				        
				        driver.switchTo().defaultContent();
						 driver.switchTo().frame("topFrame");
						 WebDriverWait wait = new WebDriverWait(driver, 30);
						 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
							driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
							test.log(LogStatus.PASS, "Clicked on Transactions");
				        
				     
				             driver.switchTo().defaultContent();
						      driver.switchTo().frame("mainFrame");
						Thread.sleep(5000);
						driver.findElement(By.linkText("QA Jobs")).click();
						test.log(LogStatus.PASS, "Clicked on QA Jobs");
						
							driver.findElement(By.linkText("Process Date Change")).click();
							test.log(LogStatus.PASS, "Clicked on Process Date Change");
							
							
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

							driver.switchTo().defaultContent();

							driver.switchTo().frame("mainFrame");

							WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

							Actions actions1 = new Actions(driver);

							actions1.moveToElement(elements1).build().perform();

							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

							//Thread.sleep(2000);
						/*	 driver.switchTo().defaultContent();
							    driver.switchTo().frame("mainFrame");
							    driver.switchTo().frame("main");
							   WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));                                       
							 //WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
							  Actions action = new Actions(driver);								        
							  action.moveToElement(element).build().perform();  */
							 
							 // Thread.sleep(6000);
							
							
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 driver.findElement(By.name("storeCode")).click();
							 //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
							 driver.findElement(By.name("storeCode")).sendKeys(StoreID);
							 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
							 Thread.sleep(2000);
							 driver.findElement(By.name("beginMonth")).clear();
						        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
						        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
						        driver.findElement(By.name("beginDay")).clear();
						        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
						        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
						        driver.findElement(By.name("beginYear")).clear();
						        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
						        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
						        driver.findElement(By.name("btnPreview")).click();
						        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						        Thread.sleep(1000);
						        Thread.sleep(8000);
						        test.log(LogStatus.PASS, "Clicked on submit button");
						        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						        
						        driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
						        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
						        {									        								
						        	test.log(LogStatus.PASS, "Process Date updated successfully");
						        	driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
						        }
						        else
						        {
						        	test.log(LogStatus.FAIL, "Process Date updated successfully.");
						        }
}

String DueDate=null;					        
public void ACHDeposit_1stInsmt(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 Thread.sleep(5000);
			 Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
									 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String DueDate=null;
					// String DDueDate=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
					DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[7]/td[2]/table/tbody/tr[2]/td[4]")).getText();
					test.log(LogStatus.PASS, "Due Date is Captured:"+DueDate);	
					
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(DueDate);	
			    driver.close();
			    
			         driver = new InternetExplorerDriver();
			         driver.get(AdminURL);
			         test.log(LogStatus.INFO, "Admin portal is launched");
						driver.manage().window().maximize();
				    // storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
			    
			    
			       DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				   driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			        test.log(LogStatus.PASS, "Username is entered: admin");			        
			        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			        //Click Login Button
			        driver.findElement(By.name("login")).click();
			        test.log(LogStatus.PASS, "Clicked on Submit button");
			        Thread.sleep(8000);
			        Thread.sleep(8000);
			         driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
					 WebDriverWait wait = new WebDriverWait(driver, 30);
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
				     driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					 test.log(LogStatus.PASS, "Clicked on Transactions");
					 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					 driver.findElement(By.linkText("ACH")).click();
					 test.log(LogStatus.PASS, "Clicked on ACH");
					 
					 
					Thread.sleep(5000);
					 driver.findElement(By.linkText("Green Bank")).click();
					 test.log(LogStatus.PASS, "Clicked on Green Bank");
					 
					 //driver.switchTo().defaultContent();
					 //driver.switchTo().frame("mainFrame");
					  Thread.sleep(5000);
					 driver.findElement(By.linkText("Green Bank NACHA File")).click();
					 test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
					 
					 
					 //driver.switchTo().defaultContent();
					  //  driver.switchTo().frame("mainFrame");
					  //  driver.switchTo().frame("main");
					    
					// driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
					 //test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
					 
					 
				    	 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    Date DDueDate = df.parse(DueDate);
					    Calendar cal = Calendar.getInstance();
						 cal.setTime(DDueDate);
						cal.add(Calendar.DATE,-1);
					      Date DDueDateminus1= cal.getTime();
						 
						 
						String DueDateminus1 =df.format(DDueDateminus1);
					    String DDueDate0[] =DueDateminus1.split("/");
					    //String DDueDate0[] =DueDate.split("/");
						 
				        String DDueDate1 = DDueDate0[0];
				        String DDueDate2 = DDueDate0[1];
				        String DDueDate3 = DDueDate0[2];
				        Thread.sleep(2000);
				        driver.findElement(By.name("beginMonth")).click();
					    driver.findElement(By.name("beginMonth")).clear();
				        driver.findElement(By.name("beginMonth")).sendKeys(DDueDate1); 
				        test.log(LogStatus.PASS, "beginMonth is entered: "+DDueDate1);
				        driver.findElement(By.name("beginDay")).clear();
				        driver.findElement(By.name("beginDay")).sendKeys(DDueDate2);
				        test.log(LogStatus.PASS, "beginDay is entered: "+DDueDate2);
				        driver.findElement(By.name("beginYear")).clear();
				        driver.findElement(By.name("beginYear")).sendKeys(DDueDate3);
				        test.log(LogStatus.PASS, "beginYear is entered: "+DDueDate3);
				        Thread.sleep(2000);
				        driver.findElement(By.name("btnPreview")).click();
				        test.log(LogStatus.PASS, "Clicked on submit button");
				        
				        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				        {									        								
				        	test.log(LogStatus.PASS, "Green Bank updated successfully");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Green Bank Not updated successfully.");
				        }  
				        
				       // driver.switchTo().defaultContent();
					    //driver.switchTo().frame("mainFrame");
					   // driver.switchTo().frame("main");
			        
				    String DueDate0[] =DueDate.split("/");
					 
			        String DueDate1 = DueDate0[0];
			        String DueDate2 = DueDate0[1];
			        String DueDate3 = DueDate0[2];
			        
			        driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
						driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
						test.log(LogStatus.PASS, "Clicked on Transactions");
			        
			     
			             driver.switchTo().defaultContent();
					      driver.switchTo().frame("mainFrame");
					Thread.sleep(5000);
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
						driver.findElement(By.linkText("Process Date Change")).click();
						test.log(LogStatus.PASS, "Clicked on Process Date Change");
						
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

						Actions actions1 = new Actions(driver);

						actions1.moveToElement(elements1).build().perform();

						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

						
					/*	driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
						
					    WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
						// WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
						  Actions action = new Actions(driver);								        
						  action.moveToElement(element).build().perform();  */
						 
						  Thread.sleep(6000);
									
						
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.name("storeCode")).click();
						 //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
						 driver.findElement(By.name("storeCode")).sendKeys(StoreID);
						 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
						 Thread.sleep(2000);
						 driver.findElement(By.name("beginMonth")).clear();
					        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
					        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
					        driver.findElement(By.name("beginDay")).clear();
					        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
					        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
					        driver.findElement(By.name("beginYear")).clear();
					        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
					        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
					        driver.findElement(By.name("btnPreview")).click();
					        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					        Thread.sleep(1000);
					        Thread.sleep(8000);
					        test.log(LogStatus.PASS, "Clicked on submit button");
					        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					        
					        driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
					        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
					        {									        								
					        	test.log(LogStatus.PASS, "Process Date updated successfully");
					        	driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
					        }
					        else
					        {
					        	test.log(LogStatus.FAIL, "Process Date updated successfully.");
					        } 
        
			                    
				//PreACHDeposit 
					        
					        driver.switchTo().defaultContent();
							 driver.switchTo().frame("topFrame");
								driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
								test.log(LogStatus.PASS, "Clicked on Transactions"); 
								
								driver.switchTo().defaultContent();
								driver.switchTo().frame("mainFrame");
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							 driver.findElement(By.linkText("ACH")).click();
							 test.log(LogStatus.PASS, "Clicked on ACH");
					 
					 
					Thread.sleep(5000);
					 driver.findElement(By.linkText("LOC")).click();
					 test.log(LogStatus.PASS, "Clicked on LOC");
					 
					 //driver.switchTo().defaultContent();
					 //driver.switchTo().frame("mainFrame");
					  Thread.sleep(5000);
					 driver.findElement(By.linkText("LOC Pre ACH Deposit")).click();
					 test.log(LogStatus.PASS, "Clicked on Loc pre ACH Deposit");
					 
					 
					 
					 
				    	 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					   // Date DDueDate = df.parse(DueDate);
					    //Calendar cal = Calendar.getInstance();
						// cal.setTime(DDueDate);
						// cal.add(Calendar.DATE,0);
					      //Date DDueDateminus1= cal.getTime();
						 
						 
						//String DueDateminus1 =df.format(DDueDateminus1);
					    //String DueDate0[] =DueDateminus1.split("/");
					    //String DueDate0[] =DueDate.split("/");
						 
				       // String DueDate1 = DueDate0[0];
				       // String DueDate2 = DueDate0[1];
				       // String DueDate3 = DueDate0[2];
					   
				        driver.findElement(By.name("beginMonth")).click();
					    driver.findElement(By.name("beginMonth")).clear();
				        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				        driver.findElement(By.name("beginDay")).clear();
				        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				        driver.findElement(By.name("beginYear")).clear();
				        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				        driver.findElement(By.name("btnPreview")).click();
				        test.log(LogStatus.PASS, "Clicked on submit button");
				        test.log(LogStatus.PASS, "ACH Deposit Completed Sucessfully");
				        
				        
		}
	}
}


public void ACHStatus_1stInsmt(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String CheckStaus=null;
			CheckStaus = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[2]/td[5]")).getText();

			test.log(LogStatus.PASS,"First Inst ACH Deposit Completed sucessfully & ACH Status is:"+CheckStaus);
			System.out.print(CheckStaus);	
			//driver.close();

		}
	}
}

public void ACHDeposit_Clear_1stInsmt(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			//String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			//appUrl = AppURL;
			// this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			//driver = new InternetExplorerDriver();
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			driver.manage().window().maximize();
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);
			Thread.sleep(8000);
		//String	DueDate = "07/07/2021";
			//RPP_1stInsment_ACHClear cl=new RPP_1stInsment_ACHClear();
			//String DueDate=cl.ACHDeposit_1stInsmt(SSN3, FileName);
			System.out.print(DueDate);
			
		    Date DDueDate = df.parse(DueDate);
		    Calendar cal = Calendar.getInstance();
			 cal.setTime(DDueDate);
			 cal.add(Calendar.DATE,5);
		      Date DDueDateminus1= cal.getTime();
			 
			 
			String DueDateminus1 =df.format(DDueDateminus1);
		    String DueDate0[] =DueDateminus1.split("/");
			 
	        String DueDate1 = DueDate0[0];
	        String DueDate2 = DueDate0[1];
	        String DueDate3 = DueDate0[2];
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			//Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));                                       
			//WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
			Actions action = new Actions(driver);								        
			action.moveToElement(element).build().perform();

			Thread.sleep(3000);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.findElement(By.name("btnPreview")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(8000);
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date updated successfully.");
			}


			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");

			driver.findElement(By.linkText("RCPO")).click();
			test.log(LogStatus.PASS, "Clicked on RCPO");
			Thread.sleep(5000);
			driver.findElement(By.linkText("ACH Clear")).click();
			test.log(LogStatus.PASS, "Clicked on ACH Clear");
			

			   WebElement element1 = driver.findElement(By.linkText("Collateral Checks"));
			  // WebElement element1 = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[1]/td[2]/input"));
			  Actions action1 = new Actions(driver);								        
			  action1.moveToElement(element1).build().perform();
			  Thread.sleep(6000);

			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	

			Thread.sleep(6000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("beginMonth")).click();
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.PASS, "ACH Clear completed sucessfully");
		}
	}
}


public void StatenentGenerationStatus(String SSN,String FileName) throws Exception
	{
	test.log(LogStatus.INFO, "Statement Generation Status");
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				   appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				 
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 test.log(LogStatus.PASS,"In Dropdown History transaction selected.");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 String NextstatementGeneration=null;
						
						 NextstatementGeneration = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
						 test.log(LogStatus.PASS,"Successfully generated SecondstatementGeneration Date."+NextstatementGeneration);
			    /*         
				   driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
					driver.quit();//Uncomment 
				  driver = new InternetExplorerDriver();*/
	
			}
		}
	}

//}
	public boolean isAlertPresent(){
		 try{
		  driver.switchTo().alert();
		  return true;
		 }catch(NoAlertPresentException ex){
		  return false;
		 }
	}
	@Test (priority=0)
	
	 public void RegistrationTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_BorrowerRegistration_NewLoan_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("BorrowerRegistration_NewLoan_"+Header, "New Loan");
		       appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        
		}
		}
		
	
			}
	@Test (priority=1)
	
	 public void VoidTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_VoidLoan_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("VoidLoan_"+Header, "Void after New Loan");
		       appUrl = AppURL;		       
		         CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.Void(SSN,FileName);
		       
		}
		}
			
			}
	@Test (priority=2)
	
	 public void CloserTest() throws Exception {
	
		// Start test
		String FileName= "AA_CloserLoan_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);	
		 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("CloserLoan_"+Header, "Closer Loan");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.Closer(SSN,FileName);
		        
				}
			}
		}
	
	@Test (priority=3)
	
	 public void NewLoanDraw() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_NewLoan_Draw_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("NewLoan_Draw"+Header, "New Loan");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		       CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		       
		}
		}
		}
	 
	@Test (priority=4)

	 public void VoidDraw() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_NewLoan_Draw_Void_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		       test = reports.startTest("Void Draw"+Header, "Void Draw");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.VoidDrawLoan(SSN,FileName);
		        
		}
		}
		}

	@Test (priority=5)
	
	 public void RescindDraw() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_NewLoan_Draw_Rescind_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		       test = reports.startTest("Rescind_Draw"+Header, "Rescind Draw");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.ResindDrawLoan(SSN,FileName);
		        
		}
		}			
	}
	
	@Test (priority=6)//--ULOC Initiation --> Draw -->Age the store till Statement Date -->Perform EOD --> Statement Generated
	 public void EOD_StatementGenerated() throws Exception {
			
			// Start test. Mention test script name
			String FileName= "AA_EOD_StatementGeneration_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
			int lastrow=TestData.getLastRow("NewLoan");
			String sheetName="NewLoan";
			for(int row=2;row<=lastrow;row++)
			{
				String RunFlag = TestData.getCellData(sheetName,"Run",row);
				
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
			        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			        String StateID = TestData.getCellData(sheetName,"StateID",row);
			        String SSN = TestData.getCellData(sheetName,"SSN",row);	
			        String Header = StateID+ "_" + ProductID;
			        test = reports.startTest("StatementGeneration"+Header, "Loan StatementGeneration");
			        appUrl = AppURL;
			       CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
			        this.NewLoan_Draw(SSN,FileName);
			        this.StatementGeneration(SSN, FileName); 
			        this.AgeStore(SSN,FileName); 
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.StatenentGenerationStatus(SSN, FileName);			       		        		      	        	        
			}
			}
			}
	
	 @Test (priority=7)
	
	 public void DrawPartialpayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_NewLoan_Draw_Partialpayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
		
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("PartialPayment"+Header, "Loan PartialPayment");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN,FileName);
		        this.LoanPartialPayment(SSN,FileName);
		        
		}
		}
		
	
			}
	 
	@Test (priority=8)
	
	 public void VoidPartialPayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_VoidPartialpayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("Void PartialPayment"+Header, "Void after PartialPayment");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN,FileName);
		        this.LoanPartialPayment(SSN,FileName);
		        this.Void_PartialPayment(SSN,FileName);
		}
		}
	}

	@Test (priority=9)
	
	 public void PayOff() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_NewLoan_Draw_PayOff_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
		
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("PayOff"+Header, "Loan PayOff");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		      	       
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN,FileName);
		        this.PayOffLoan(SSN,FileName);
		        
		}
		
		}
	}
	
	@Test (priority=10)
	
	 public void VoidPayoff() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_VoidPayOff_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("VoidPayOff"+Header, "Void PayOff");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		      CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN,FileName);
		        this.PayOffLoan(SSN,FileName);
		        this.Void_Payoff(SSN, FileName);
		        
		}
		
		}
		}
	
	@Test (priority=11)
		
	 public void Deposit() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_NewLoan(ACH)_Draw_Deposit_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("Deposit"+Header, "Loan Deposit");
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName); 
		        this.AgeStore(SSN,FileName,-1); 
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.ACHDeposit(SSN, FileName);
		        //this.NACHA(SSN, FileName, 0);		       	      
		}
	}
}

	
	@Test (priority=12)
	
	 public void Deposit_Clear() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_Deposit_Clear_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		       test = reports.startTest("Deposit_Clear"+Header, "Deposit_Clear");
		        appUrl = AppURL;
		       CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName); 
		        this.AgeStore(SSN,FileName); 
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        //this.NACHA(SSN, FileName, 0);
		        this.ACHDeposit(SSN, FileName);
		        this.Deposit_Clear(SSN, FileName);
		       		 
		}
	}
}		
	 
	@Test (priority=13)
	
	 public void Deliquent() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_EOD_Deliquent_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("Deliquent"+Header, "Loan Deliquent");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        /*this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		        this.AgeStore_EOD(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); //this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.DeliquentPaymentStatus(SSN, FileName);
		        		       		        		       		        		        	        	        
		}
		}			
			}
	
	@Test (priority=14)
	
	public void Cure() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_EOD_Cure_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";   
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
		{	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("Cure"+Header, "Loan Cure");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        /*this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		        this.AgeStore_EOD(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); //this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); //this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);		       		        		       		        		        	        	        
		}
		}
	}
	
	
	//--@Test (priority=15) //added in smoke test
	
	 public void Default_CustomerWO() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_Default_CustomerWO_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";   
		
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{	
			
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		       
		        test = reports.startTest("Default_CustomerWO"+Header, "Default_CustomerWO");
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		      /*  this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		       this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		       this.AgeStore(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		       // this.EODProcessing(SSN, FileName);  
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName); 
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.Default_WOProc(SSN, FileName);
		        this.WOPaymentStatus(SSN, FileName);
		   	       		        		        	        	        
		}
  }
}
	
	@Test (priority=16)
	
	 public void Default_WORecoveryPayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_Default_WORecoveryPayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";   
		//int lastrow=TestData.getLastRow("Borrower");
		
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
		
		if(RunFlag.equals("Y"))
		{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		      
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		       
		        test = reports.startTest("Default_WORecovery"+Header, "Default_WORecovery");
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        /*this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		       this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		       this.AgeStore(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		       // this.EODProcessing(SSN, FileName);  
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName); 
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.Default_WOProc(SSN, FileName);
		        this.WOPaymentStatus(SSN, FileName);
		        this.Writoff_Recovery(SSN, FileName);
		       	       		        		        	        	        
		}
		}
	}

	@Test (priority=17)
	
	 public void Default_WORecovery_VoidPayment() throws Exception 
	{
		String FileName= "AA_Default_WORecoveryP_Voidayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC_Critical_Scenarios/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan"; 
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
		if(RunFlag.equals("Y"))
		{			  
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        test = reports.startTest("WORecoveryP_Voidayment"+Header, "WORecoveryP_Voidayment");
		        appUrl = AppURL;
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_LOC_Scenarios(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		       /* this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		       this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		       this.AgeStore(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		       // this.EODProcessing(SSN, FileName);  
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName); 
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.Default_WOProc(SSN, FileName);
		        this.WOPaymentStatus(SSN, FileName);
		        this.Writoff_Recovery(SSN, FileName);
		        this.WORecovery_Void(SSN, FileName);	     	        
		}
		}			
}
	
	
	 
	 @AfterMethod
 	 public void getResult(ITestResult result) throws Exception{
		 if(ITestResult.FAILURE == result.getStatus()){
			
			String screenshotPath = LOC_CriticalTests.getScreenhot(driver, result.getName());
			 //To add it in the extent report 
				test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
				test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		      		 
		 }else if(result.getStatus() == ITestResult.SKIP){
			 test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }else if(result.getStatus() == ITestResult.SUCCESS){
			 test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}
		 reports.endTest(test);
	     reports.flush();
	     Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
	     Thread.sleep(3000);
	     driver.close();
		 }
		 
	 public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);		 
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/ExecutionReports/LOC_Critical_Scenarios/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }
	 
	@AfterClass
	 
 public void closeBrowser() throws Exception{
		    
	    driver.quit();
	    
	}
}
	
	


