package CO_ILP;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.*;
import Tests.ExecuteScripts;
/*import Test.CO_ILP.Need;
import Test.CO_ILP.scenario;*/
import Utilities.ExtentReports.Excel;
import bsh.This;
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;

import java.io.FileWriter;

import java.io.IOException;
import java.io.FileReader;
public class Executionbatchfile_CO_ILP {

public WebDriverWait wait;	
	WebDriver driver;
	String appUrl;

	static ExtentReports reports;
	ExtentTest test;

	@BeforeClass
	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();

		reports = new ExtentReports(System.getProperty("user.dir") +"/ExecutionReports/ILP/AA_ILP_Smoke Testing Scenarios_"+timestamp+".html", true);
		reports.addSystemInfo("Browser Version","IE 11.0");
	}


	/*	@BeforeTest
	public void setup() throws IOException {
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver","E:/Ncp_Workspace/Selenium/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
		//appUrl = "http://192.168.2.203/cc/demoIndex.do";
	}
*/
	
	@BeforeTest
	public void setup_Grid() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
	/*	String Node = "http://192.168.2.123:5555/wd/hub";
		  DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		 
		 
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
			
					driver = new RemoteWebDriver(new URL(Node), cap);*/
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
	 driver = new InternetExplorerDriver();		
	}
	
	//@BeforeTest
	public void Login (String username,String password,String storenumber) {

	      //JavascriptExecutor js = (JavascriptExecutor)driver;

		//Launch URL
		driver.get(appUrl);
		test.log(LogStatus.INFO, "CSR Application is launched");
		driver.manage().window().maximize();
		String usenameId = "loginRequestBean.userId";
		String passwordId = "loginRequestBean.password";
		String StoreId = "loginRequestBean.locNbr";
		String Login = "login";

	
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Application Login -----------");

		driver.findElement(By.name(usenameId)).clear();
		driver.findElement(By.name(usenameId)).sendKeys(username);
		test.log(LogStatus.PASS, "Username is entered: "+username);


		driver.findElement(By.name(passwordId)).clear();
		

		driver.findElement(By.name(passwordId)).sendKeys(password);
		test.log(LogStatus.PASS, "Password is entered: "+password);

		//writeText(By.name(StoreId), storenumber);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		
		driver.findElement(By.name(Login)).click();
		test.log(LogStatus.PASS, "Clicked on Submit button");
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



	public void NewLoan_ILP(String SSN,String FileName, String NegAmt) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String State =TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				System.out.println(ProductID);
				String UserName =TestData.getCellData(sheetName,"UserName",row);
				String Password =TestData.getCellData(sheetName,"Password",row);
				String ProductType =TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType=TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId =
				TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType =TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
				String ESign_CourtesyCallConsent =TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
				String AllowPromotion =TestData.getCellData(sheetName,"Allow Promotion",row);
				String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
				String ESign_Preference =TestData.getCellData(sheetName,"ESign_Preference",row);
				String ESign_Checks =TestData.getCellData(sheetName,"ESign_Checks",row);
				String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
				String ESign_CheckNbr =TestData.getCellData(sheetName,"ESign_CheckNbr",row);
				String last4cheknum=ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				String Parent_Window1 = driver.getWindowHandle();  
				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- NewLoan ILP -----------");

				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//*[@id="911100"]/a
				driver.findElement(By.cssSelector("li[id='911100']")).click();			
				test.log(LogStatus.PASS, "Clicked on New Loan");			
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



				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");

				//    Selection of Product based on the Name provided in
				//Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				Thread.sleep(4000);
				/*if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				{


					if(ProductName.equals("CO ILP"))
					{

						if(ESign_CollateralType.equals("ACH"))
						{
							//*[@id="termSel1"]

							driver.findElement(By.xpath("//*[@id='tableWid1']/tbody/tr[1]/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);

						}

						if(ESign_CollateralType.equals("CASH"))
						{
							//*[@id="termSel1"]
							//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
							//*[@id="tableWid1"]/tbody/tr[1]/td/b
							//*[@id="tableWid2"]/tbody/tr[1]/td/b
							driver.findElement(By.xpath("//*[@id='tableWid2']/tbody/tr[1]/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
					}
*/
					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("LoanButtonId")).click();
					//New Loan Screens

					if(ProductID.equals("ILP"))
					{


						//negLoanAmt buton

						driver.findElement(By.name("negLoanAmt")).click();
						test.log(LogStatus.PASS, "NegotiableAmmount Button clicked");
						for( String winHandle1 : driver.getWindowHandles())

						{
							if(!(winHandle1.equals(Parent_Window1)))
							{

								Thread.sleep(3000);
								driver.switchTo().window(winHandle1);
								Thread.sleep(1000);
								driver.manage().window().maximize();
								Thread.sleep(3000);
								Robot robot = new Robot();
								Thread.sleep(2000);
								robot.keyPress(KeyEvent.VK_F11);
								test.log(LogStatus.PASS, "F11 button clicked");
								System.out.println("Before");
								Thread.sleep(8000);
								//WebElement element = driver.findElement(By.name("requestBean.siilBean.advAmt"));                                       
								///Actions builder = new Actions(driver); 

								Thread.sleep(3000);

								WebElement element = driver.findElement(By.name("requestBean.siilBean.advAmt"));  
								if(element.isDisplayed()){


									Actions builder = new Actions(driver); 
									builder.doubleClick()
									/* .sendKeys(element, Keys.ARROW_RIGHT)
											   .sendKeys(element, Keys.ARROW_RIGHT)
											   .doubleClick()*/
									//.sendKeys(element, Keys.DELETE)
									//.sendKeys(element, Keys.ARROW_RIGHT)
									.sendKeys(element, Keys.DELETE)
									//.sendKeys(element, Keys.CLEAR)
									.sendKeys(element,Keys.BACK_SPACE)
									.sendKeys(element,Keys.BACK_SPACE)
									.sendKeys(element,Keys.BACK_SPACE)
									.sendKeys(element,Keys.BACK_SPACE)
									.sendKeys(element,Keys.BACK_SPACE)
									.sendKeys(element,Keys.BACK_SPACE)
									.sendKeys(element,Keys.BACK_SPACE)
									.build()
									.perform();
								}


								Thread.sleep(8000);

// name="requestBean.siilBean.advAmt"
								driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys(NegAmt);
								test.log(LogStatus.PASS, "Negotiable Amount Entered is::"+NegAmt);
								// name="requestBean.siilBean.advAmt"
								// reCalculate
								driver.findElement(By.name("reCalculate")).click();
								test.log(LogStatus.PASS, "ReCalculate button clicked");
								Thread.sleep(2000);
								// requestBean.siilBean.collateralType
								driver.findElement(By.name("requestBean.siilBean.collateralType")).sendKeys(ESign_CollateralType);
								test.log(LogStatus.PASS, "Collateral type is selected as ::"+ESign_CollateralType);
								Actions builder = new Actions(driver); 
								builder.doubleClick()
								.sendKeys(element, Keys.ARROW_DOWN)
								.sendKeys(element, Keys.ARROW_DOWN)
								.sendKeys(element, Keys.ARROW_DOWN)
								.sendKeys(element, Keys.ARROW_DOWN)

								.build()
								.perform();
								Thread.sleep(4000);

								//     /html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[3]/table/tbody/tr[15]/td/input
								//	/html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[5]/table/tbody/tr[15]/td
								//driver.findElement(By.xpath("/html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/table/tbody/tr[14]/td/input")).click();
							
								//                            /html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/table/tbody/tr[14]/td/input
								driver.findElement(By.xpath("/html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/table/tbody/tr[14]/td/input")).click();
								test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
								Thread.sleep(5000);
								robot.keyRelease(KeyEvent.VK_F11);
								test.log(LogStatus.PASS, "F11 button clicked");
							}




						}
						Thread.sleep(3000);
						driver.switchTo().window(Parent_Window);

						for( String winHandle1 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle1);

						}

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");	
						// name="requestBean.siilBean.advAmt"  negamt textfield
						// name="reCalculate" button
						//name="requestBean.siilBean.collateralType"  dropdown
						// /html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/table/tbody/tr[14]/td/input instbutton

					}
					String Instamt=driver.findElement(By.name("requestBean.siilBean.disbAmt")).getAttribute("value");
					System.out.println(Instamt);
					driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
					test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
					driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
					test.log(LogStatus.PASS, "Disb Type is entered as "+ESign_DisbType);
					driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
					test.log(LogStatus.PASS, "Disb Amt is entered as "+Instamt);
					driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
					test.log(LogStatus.PASS,"requestBean.siilBean.emailConsentFlag as "+ESign_CourtesyCallConsent);
					if(ESign_CourtesyCallConsent.equals("Yes"))
					{
						if(ESign_Preference.equals("Call"))
						{
							driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
							test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
						}
						if(ESign_Preference.equals("Mail"))
						{
							driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
							test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
						}
						if(ESign_Preference.equals("SMS"))
						{
							driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
							test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

							try {
								Alert alert = driver.switchTo().alert();
								alert.dismiss();
								//if alert present, accept and move on.

							}
							catch (NoAlertPresentException e) {
								//do what you normally would if you didn't have the alert.
							}
						}

					}
					if(AllowPromotion.equals("Yes"))
					{
						driver.findElement(By.name("allowPromotion")).click();
						test.log(LogStatus.PASS, "AllowPromotion is selected ");
						//String mwh=driver.getWindowHandle();
						driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
						test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
						//String winHandle =
						driver.getWindowHandle(); //Get current window handle.
					}
					WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
					String NumofInst=ele.getAttribute("value");
					/*
			//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
			                        System.out.println(NumofInst);
			                        int installments = Integer.parseInt(NumofInst);
			                        for(int i=0;i<installments;i++)
			                        {
			                            Random rand = new Random();
			                            int rand1 = rand.nextInt(100000);
			                            String chknum = Integer.toString(rand1);
			driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);

			                        }             */
					driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
					test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
					driver.findElement(By.name("finishLoan")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
					test.log(LogStatus.PASS, "click on Finish Loan button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(500);

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					Thread.sleep(5000);
					//

					//driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='OKBut' and @type='button' and @value='Yes']")));
							driver.findElement(By.xpath("//input[@id='OKBut' and @type='button' and @value='Yes']")).click();*/
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[  @value='Yes' and @type='button' ]")));
					//driver.findElement(By.xpath("//input[ @value='Yes' and @type='button' ]")).click();
					driver.findElement(By.xpath("//*[@id='OKBut']")).click();
					//*[@id="OKBut"]
					//driver.findElement(By.name("OKBut")).click();
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();

					test.log(LogStatus.PASS, "click on Yes button ");
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if(		driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).isDisplayed())
					{
						test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
						//driver.findElement(By.name("ok")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
					}
				}


			}

		}
	








	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException ex){
			return false;
		}
	}





	public void Void (String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- EPP Payment Void -----------");

				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
				if(ProductID.equals("ILP"))
				{
					driver.findElement(By.name("button")).click();
				}



				for( String winHandle1 : driver.getWindowHandles())
				{
					// driver.findElement(By.name("button")).click();
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/select")).sendKeys("cash");
					test.log(LogStatus.PASS, "Disb type is selected as "+ "Cash");	
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);	
					driver.findElement(By.name("Submit33")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Void Paymentplan Payment button ");


					Thread.sleep(2000);							






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
					Thread.sleep(2000);
					Thread.sleep(2000);
					if(driver.findElement(By.name("checkno")).isDisplayed())
					{

						driver.findElement(By.name("checkno")).click();
						test.log(LogStatus.INFO, " void Payment with-SSN: " +SSN +" :: is Successful");
					}
					else
					{
						test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
					}


				}

			}

		}
	}


	public void DrawerDeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Drawer DeAssign -----------");

				Thread.sleep(5000);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Deassign");	
				driver.switchTo().frame("main");		
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				//driver.findElement(By.name("drawerDeassignRequestBean.currentCashBalance")).sendKeys("0");
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Banker PIN# is enetered as"+Password);	
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Click on Finish De-assign Button");
				try{
					driver.close();
				}
				catch (Exception e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);				
				driver.findElement(By.name("drawerdeassign")).click();
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
				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
				{
					WebElement htmltable=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));	

					List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
					System.out.println("current row num "+rows.size());
					int count=0;							
					count=driver.findElements(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr")).size();				 				
					for(int rnum=1;rnum<rows.size();rnum++)
					{                      
						System.out.println("current row num "+rnum);						
						//List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));												

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
						Thread.sleep(5000);
					}
				}
				String DrawerOverShortAmount =driver.findElement(By.name("drawerRequestBean.drawerOverShort")).getAttribute("value");
				driver.findElement(By.name("drawerRequestBean.amount")).sendKeys(DrawerOverShortAmount);
				test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);
				driver.findElement(By.name("drawerRequestBean.primary")).sendKeys("Cash Handling");
				test.log(LogStatus.PASS, "Primary Reason is selected as Cash Handling");
				driver.findElement(By.name("drawerRequestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");	
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
				Thread.sleep(3000);
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
					test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
				}
			}
		}
	}

	public void StatementGeneration_EODProcessing(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Statement Generation EOD Processing -----------");

				Thread.sleep(5000);	    

				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Daily Processing");
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("eod")).click();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				Thread.sleep(4000);
				// driver.findElement(By.name("requestBean.comments")).click();
				driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
				// requestBean.comments
				Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");
				Thread.sleep(4000);
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}
				Thread.sleep(4000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS,"Clicked on Balance Safe");
				Thread.sleep(1000);
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

				//	String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
				//String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

				driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);

				/// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[7]/td[3]

				// driver.findElement(By.name("requestBean.amount")).sendKeys("SafeOverShortAmount");
				test.log(LogStatus.PASS,"Enter the Balance 50");
				driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
				test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
				driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");	
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
				Thread.sleep(5000);
				WebDriverWait wait = new WebDriverWait(driver, 10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")));
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")).click();
				// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input
				test.log(LogStatus.PASS, "Clicked on Next");


				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}



				Thread.sleep(1000);
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
				Thread.sleep(4000);

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
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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

				driver.get(AdminURL);
				Thread.sleep(1000);

				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Store Info -----------");


				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Store Setup");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				driver.findElement(By.linkText("Store Config")).click();
				//Store Config
				/*WebElement element= driver.findElement(By.cssSelector("li[id='101000']"));	
	Actions action = new Actions(driver);
	action.moveToElement(element).perform();
  WebElement subElement = driver.findElement(By.cssSelector("li[id='101020']"));			        
  action.moveToElement(subElement).perform();			 
  action.click();	*/
				driver.findElement(By.linkText("Edit Store")).click();
				// action.perform();
				//  driver.findElement(By.cssSelector("li[id='101020']")).click();
				test.log(LogStatus.PASS, "Clicked on Store Config");

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
				// test.log(LogStatus.PASS, "Store Info Status is Chenged: "+Storestaus);

				//locationBean.locStatusCd



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");													    	
				if(driver.findElement(By.name("submitButton")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Store Aging is Successfully ");
					driver.findElement(By.name("submitButton")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
				}
				//driver.close();
			}
		}
	}
	public void Safeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);		
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Safe Assign -----------");

				Thread.sleep(5000);
				/*driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on safe");	
				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				//driver.findElement(By.linkText("Drawer")).click();

				driver.findElement(By.linkText("Assign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");



				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


			//if(driver.findElement(By.name("previous")).isDisplayed())




				driver.findElement(By.name("previous")).click();

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.name("yes")).click(); */



				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");	
				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				//driver.findElement(By.linkText("Drawer")).click();
				driver.findElement(By.linkText("Assign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");

				//login.Login(UserName, Password, StoreId, driver, AppURL, test);
				Thread.sleep(5000);

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
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input
				///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).isDisplayed())
				if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
				{

					test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
					driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
					//driver.findElement(By.name("done")).click();
				}
				else
				{
					test.log(LogStatus.PASS,"Safe not assigned successfully with over/short.");
				}
			}
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

	public void Drawerassign(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);		
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);

				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Drawer Assign -----------");

				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//driver.findElement(By.cssSelector("li[id='911101']")).click();
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");
				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				//driver.findElement(By.linkText("Drawer")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
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

				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//|| driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font")).getCssValue("color")=="red"
				if(this.Field(driver) != null )
					//if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).isDisplayed())
				{                    		                   
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					//driver.findElement(By.cssSelector("li[id='911101']")).click();
					driver.findElement(By.linkText("Safe")).click();
					test.log(LogStatus.PASS, "Clicked on Safe");
					//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
					//driver.findElement(By.linkText("Drawer")).click();
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
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
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
						Thread.sleep(3000);
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
						Thread.sleep(2000);
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
							test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");
						}                            
					}
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					//driver.findElement(By.cssSelector("li[id='911101']")).click();
					driver.findElement(By.linkText("Safe")).click();
					test.log(LogStatus.PASS, "Clicked on Assign");
					//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
					//driver.findElement(By.linkText("Drawer")).click();
					driver.findElement(By.linkText("Assign")).click();
					test.log(LogStatus.PASS, "Clicked on Assign");

					//login.Login(UserName, Password, StoreId, driver, AppURL, test);
					Thread.sleep(5000);

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
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input
					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
					if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
					{

						test.log(LogStatus.PASS,"Safe assigned successfully.");
						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
						//driver.findElement(By.name("done")).click();
					}
					else
					{
						test.log(LogStatus.PASS,"Safe not assigned successfully.");
					}

					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.switchTo().frame("main");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					//driver.findElement(By.cssSelector("li[id='911101']")).click();
					driver.findElement(By.linkText("Drawer")).click();
					test.log(LogStatus.PASS, "Clicked on Drawer");
					//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
					//driver.findElement(By.linkText("Drawer")).click();
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.switchTo().frame("main");
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
					Thread.sleep(2000);
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
						test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
					}

				}                                        
				else
				{                    	
					// if(driver.findElement(By.name("done")).isDisplayed())
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
					{

						test.log(LogStatus.PASS,"Drawer Assigned successfully with over/short.");
						//driver.findElement(By.name("done")).click();
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
					}
					else
					{
						test.log(LogStatus.PASS,"Drawer not Assigned successfully with over/short.");
					}
				}


			}
		}
	}



	public void Payliance_OriginationFile(String SSN,String FileName,int Days,int instnum) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Payliance Origination -----------");


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



				if(ProductID.equals("ILP"))
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
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				//	DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+instnum+"]/td[2]")).getText();

				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
				driver.findElement(By.linkText("Payliance")).click();
				test.log(LogStatus.PASS, "Clicked on Payliance");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Payliance Origination File")).click();
				test.log(LogStatus.PASS, "Clicked on Payliance Origination File");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
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
				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				{									        								
					test.log(LogStatus.PASS, "Process NACHA file successfully.");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process NACHA is not updated successfully.");
				}




			}
		}
	}






	public void ACH_Deposit(String SSN,String FileName,int Days,int InstNum) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Process ILP Pre ACH Deposits -----------");


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



				if(ProductID.equals("ILP"))
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
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;


				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				// //*[@id="ContractScheduleTable"]/tbody/tr[3]/td[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();

				//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
				driver.findElement(By.linkText("Installment Loan")).click();
				test.log(LogStatus.PASS, "Clicked on Installment Loan");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process ILP Pre ACH Deposits")).click();
				test.log(LogStatus.PASS, "Clicked on Process ILP Pre ACH Deposits");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
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
				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				{									        								
					test.log(LogStatus.PASS, "LOC Pre ACH Deposit Process  successfully.");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process LOC Pre ACH Deposit is not updated successfully.");
				}




			}
		}
	}




	public void ACHReturnPosting(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				//String age_store = TestData.getCellData(sheetName, "AgeStore",row);
				//int Age_store = Integer.parseInt(age_store);
				//System.out.println(Age_store);
				//System.out.println("age_store:::"+age_store);
				//int Days= Integer.parseInt(age_store);

				System.out.println(AdminURL);


				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- ACH Return Posting ILP -----------");


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
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
				driver.findElement(By.linkText("Installment Loan")).click();
				test.log(LogStatus.PASS, "Clicked on Installment Loan");

				driver.findElement(By.linkText("ACH Return")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Return");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);					  	        			   
				//Click Login Button
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.name("requestBean.chkName")).click();
				test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					  	        			   
				//Click Login Button
				driver.findElement(By.name("requestBean.rtnReasonId")).sendKeys("R01-Insufficient Funds");
				test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");
				driver.findElement(By.name("CmdReturnPosting")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Return Posting button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					driver.findElement(By.name("Ok")).click();
					test.log(LogStatus.PASS, "ACH Return Posting Done Successfull");	
				}


			}
		}
	}






	public void Payment_Inst_Amount(String SSN,String FileName, int instnum) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Installment Amount payment -----------");

				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
				/* driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("ILP"))
					 {
						  driver.findElement(By.name("button")).click();
						 String Payment = null;
						 String InstAmt = null;
						 double Pymt;

						//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]
						 PastDueAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]")).getText();
						 InstAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[9]/td/span[2]")).getText();

						// double PDA = double.valueOf(PastDueAmt);

						 double PDA = Double.valueOf(PastDueAmt);

						 double InstAmount = Double.valueOf(InstAmt);

						 Pymt = PDA + InstAmount;
						 String Payment = String.valueOf(Pymt);
						 Payment = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+instnum+"]/td[5]")).getText();

					 driver.close();


					 driver = new InternetExplorerDriver();

					 driver.get(AppURL);

					// CSRLoginpage login = new CSRLoginpage();
				     login.Login(UserName, Password, StoreId, driver, AppURL, test);
					driver.switchTo().defaultContent();
					Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					Thread.sleep(1000);
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


					    if(ProductID.equals("ILP"))
						 {
					    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
					    //	 /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
						 }
					  //  driver.findElement(By.name("button")).click();
						test.log(LogStatus.PASS, "Click on GO Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");*/
				driver.findElement(By.name("transactionList")).sendKeys("Payment");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.findElement(By.name("button")).click();
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//*[@id='PD4']")).click();
					// name="instAmt" alue
					String Payment =driver.findElement(By.name("instAmt")).getAttribute("value");
					//CharSequence Payment;
					/*		 driver.findElement(By.name("requestBean.siilBean.payAmt")).clear();
							driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(Payment);*/
					test.log(LogStatus.PASS, "Payment Amount (past due +installment amount): "+Payment);
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
					test.log(LogStatus.PASS, "Tender Amount (past due +installment amount) "+Payment);						
					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);			
					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

					Thread.sleep(2000);							


					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}

					/*		try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														

									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}*/
					/*		

							 try { 
								    Alert alert = driver.switchTo().alert();
								    alert.accept();
								    //if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {



								    //do what you normally would if you didn't have the alert.
								}
					 */
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					Thread.sleep(2000);
					Thread.sleep(2000);
					if(driver.findElement(By.name("checkno")).isDisplayed())
					{

						driver.findElement(By.name("checkno")).click();
						test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
					}
					else
					{
						test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
					}


				}

			}

		}
	}






	public void AgeStore_ILP(String SSN,String FileName,int Days,int instnum) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{

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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Process AgeStore ILP -----------");

				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				//	driver.findElement(By.name("button")).click();

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame"); 
				driver.switchTo().frame("main");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//driver.findElement(By.name("transactionList")).sendKeys("History");
				driver.findElement(By.name("transactionList")).sendKeys("History");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  


				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+instnum+"]/td[2]")).getText();
				//*[@id="ContractScheduleTable"]/tbody/tr[3]/td[2]
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
				Actions actions1 = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);
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

	
	public void Check_Payment(String SSN,String FileName) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				Thread.sleep(5000);
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
				Thread.sleep(1000);

				//if(ProductID.equals("LOC"))
				//{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(1000);
				int n=driver.findElements(By.xpath("//select[@name='transactionList']/option")).size();

				for(int i=1;i<=n;i++)
				{
					String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getText();


					if(transactino_value.equalsIgnoreCase("Payment"))
					{
						test.log(LogStatus.PASS, "Payment option is available in the list");

					}
					else
					{
						test.log(LogStatus.PASS, "Payment option is checking with the list:: "+transactino_value);

					}


				}
				test.log(LogStatus.PASS, "Payment option is not available in the list" );
			}}
	}

	public void Loan_Balance_Status(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		

		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);


				System.out.println(AdminURL);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Loan Balance Status -----------");


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
				/*driver.findElement(By.name("button")).click();*/
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  


				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String BalanceStatus=null;
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[8]/td/span[2]
				BalanceStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[8]/td/span[2]")).getText();
				//BalanceStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				////*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]
				//BalanceStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				test.log(LogStatus.PASS,"Balance  status is ::"  +BalanceStatus);
				String LoanStatus=null;
				//                                           //*[@id="transactionHistoryTable"]/tbody/tr/td[2]/table/tbody/tr[1]/td/span[2]
				LoanStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[2]/table/tbody/tr[1]/td/span[2]")).getText();
				//LoanStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]
				//LoanStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				test.log(LogStatus.PASS,"Loan  status is ::"  +LoanStatus);
				Thread.sleep(10000);

			}
		}
	}

public void PendingBNK(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
				String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);

			

				 ////////////////////////
				 
					this.Login(UserName,Password,StoreID);
					 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Pending Bankruptsy -----------");

					Thread.sleep(5000);
					Thread.sleep(1000);
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


					if(ProductID.equals("ILP"))
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
					driver.switchTo().frame("main");
					driver.findElement(By.name("transactionList")).sendKeys("History");
					if(ProductID.equals("ILP"))
					{

						driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
						//driver.findElement(By.id("go_Button")).click();  
					}

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					String LoanNumber=driver.findElement(By.xpath(" //*[@id='transactionHistoryTable']/tbody/tr/td[1]/table/tbody/tr[3]/td/span[2]")).getText();;
					test.log(LogStatus.PASS, "Loan Number captured is ::"+LoanNumber);

					driver.close();

					driver = new InternetExplorerDriver();
					driver.get(AdminURL);
					test.log(LogStatus.INFO, "Admin portal is launched");


				 
				 ///////////////////////
			
				driver.manage().window().maximize();
				Thread.sleep(1000);



				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);					   
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				} 


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");	
				
				driver.findElement(By.name("requestBean.dealNbr")).sendKeys(LoanNumber);
				test.log(LogStatus.PASS, "Loan Number entered  is : "+LoanNumber);
/*				driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store Number entered is entered: "+SSN1);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);*/
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);			

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");  



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		

				test.log(LogStatus.PASS,"Click on Go button");		 

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Pending");
				test.log(LogStatus.PASS, "select status as :Pending" );

				driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
				test.log(LogStatus.PASS, "Entered Attorny Name");


				driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
				test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
				test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
				Thread.sleep(500);			
				driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
				test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);

				driver.findElement(By.name("bt_AddBankruptcy")).click();			
				test.log(LogStatus.PASS, "<FONT color=green style=Arial> Status BNKPending is Saved");


			}
		}		 




	}

	public void PendingBNK_Void(String SSN,String FileName) throws Exception
	{
		Excel TestData = new
				Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType =
						TestData.getCellData(sheetName,"TenderType",row);
				String
				ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName =
						TestData.getCellData(sheetName,"UserName",row);
				String Password =
						TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String
				BNKstatus1=TestData.getCellData(sheetName,"BNKstatus1",row);
				String AttorneyPhone =
						TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);

				
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Pending Bankruptsy Void -----------");

				 ///////////////////////////////////////
				 
				 this.Login(UserName,Password,StoreID);

				Thread.sleep(5000);
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
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
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String LoanNumber=driver.findElement(By.xpath(" //*[@id='transactionHistoryTable']/tbody/tr/td[1]/table/tbody/tr[3]/td/span[2]")).getText();;
				test.log(LogStatus.PASS, "Loan Number captured is ::"+LoanNumber);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");


				 
				 //////////////////////////////////////
			
				driver.manage().window().maximize();
				Thread.sleep(1000);



				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.dealNbr")).sendKeys(LoanNumber);
				test.log(LogStatus.PASS, "Loan Number entered  is : "+LoanNumber);
/*				driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store Number entered is entered: "+SSN1);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);*/
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();

				test.log(LogStatus.PASS,"Click on Go button");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void Pendiing");
				test.log(LogStatus.PASS, "select status as :Void Pendiing");

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, "Status BNKPending is Saved");


			}
		}



		/*if(driver.findElement(By.name("submitButton")).isDisplayed())
        {
         test.log(LogStatus.PASS, "Store Aging is Successfully ");
driver.findElement(By.name("submitButton")).click();
        }
     else
        {
            test.log(LogStatus.FAIL, "Store Aging is not
Successfully ");
        }*/
		//driver.close();
	}

	public void Bankrupt(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
				String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);
				///////////////////////////////////////
				this.Login(UserName,Password,StoreID);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> -----------Bankruptsy-----------");

				Thread.sleep(5000);
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
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
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				// //*[@id="ContractScheduleTable"]/tbody/tr[4]/td[2]
				String LoanNumber=driver.findElement(By.xpath(" //*[@id='transactionHistoryTable']/tbody/tr/td[1]/table/tbody/tr[3]/td/span[2]")).getText();;
				test.log(LogStatus.PASS, "Loan Number captured is ::"+LoanNumber);
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

				//DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();
				//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
				test.log(LogStatus.PASS, "Capture DueDate::"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, -2);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				////////////////////////////////////
				/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
				driver.manage().window().maximize();
				Thread.sleep(1000);



				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);					   
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				} 


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//LoanNumber
				driver.findElement(By.name("requestBean.dealNbr")).sendKeys(LoanNumber);
				test.log(LogStatus.PASS, "Loan Number entered  is : "+LoanNumber);
				// requestBean.bnklocnbr
			/*	driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store Number entered is entered: "+SSN1);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);*/
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);			

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");  



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		

				test.log(LogStatus.PASS,"Click on Go button");		 

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(3000);
				if( driver.findElement(By.name("loanCode")).isDisplayed())
				{
					driver.findElement(By.name("loanCode")).click();
					test.log(LogStatus.PASS, "Selecting Check box for loan");
				}
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Bankrupt");
				test.log(LogStatus.PASS, "select status as :Bankrupt" );






				driver.findElement(By.name("bankruptcyFilingDate1")).sendKeys(DueDate1.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("bankruptcyFilingDate2")).sendKeys(DueDate2.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
				Thread.sleep(500);			
				driver.findElement(By.name("bankruptcyFilingDate3")).sendKeys(DueDate3.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			

				driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
				driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
				test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");


				driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
				test.log(LogStatus.PASS, "Entered Attorny Name");


				driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
				test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
				test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
				Thread.sleep(500);			
				driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
				test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);

				driver.findElement(By.name("bt_AddBankruptcy")).click();			
				test.log(LogStatus.PASS, "Status BNKPending is Saved");

				Thread.sleep(50000);
				//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
				if( driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
				{
					test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
				}
				driver.close();

				driver = new InternetExplorerDriver();

			}

		}		 



		/*if(driver.findElement(By.name("submitButton")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "Store Aging is Successfully ");
			driver.findElement(By.name("submitButton")).click();
		}
	 else
		{
			test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
		}*/
		//driver.close();
	}

	public void Writoff_RecoveryFull_ILP(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String ESign_CheckNbr =TestData.getCellData(sheetName,"ESign_CheckNbr",row);

				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
				System.out.println(AdminURL);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- WriteOff Recovery Full ILP -----------");


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



				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
				driver.findElement(By.name("transactionList")).sendKeys("WO Recovery");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.name("button")).click(); 


				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//String PaymentAmount=null;
				if(TenderType.equals("Cash"))
				{
					PaymentAMT = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);



					driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
					driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(PaymentAMT);
					test.log(LogStatus.PASS, "Enter the Payment Amount");

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Select the Tender Type::");

					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PaymentAMT);
					test.log(LogStatus.PASS, "Enter the Tender Amount");

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Enter the Password");

					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
					Thread.sleep(3000);
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
				}

				if(TenderType.equals("Cashiers Check"))
				{
					PaymentAMT = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);


					driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();

					driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(PaymentAMT);
					test.log(LogStatus.PASS, "Enter the Payment Amount");

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Select the Tender Type::");

					driver.findElement(By.name("transactionDataBean.ccmoNbrFirst")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Banking Checking number entered is::"+ESign_CheckNbr);

					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PaymentAMT);
					test.log(LogStatus.PASS, "Enter the Tender Amount");

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Enter the Password");

					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
				}

				if(TenderType.equals("Money Order"))
				{
					PaymentAMT = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);


					driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();

					driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(PaymentAMT);
					test.log(LogStatus.PASS, "Enter the Payment Amount");

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Select the Tender Type::");

					driver.findElement(By.name("transactionDataBean.ccmoNbrFirst")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Banking Checking number entered is::"+ESign_CheckNbr);

					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PaymentAMT);
					test.log(LogStatus.PASS, "Enter the Tender Amount");

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Enter the Password");

					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
				}
				/* if(driver.findElement(By.name("Ok")).isDisplayed())
			 {*/
				test.log(LogStatus.PASS, "Write off Recovery completed Sucessfully");
				/* driver.findElement(By.name("Ok")).click();
			 }
				 */ 

				/*	 else
				 {

			test.log(LogStatus.PASS, "Write off Recovery not completed Sucessfully");
				 }
				 */


			}
		}

	}

	public void Dismissed_AfterBANKRUPT(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
				String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);
				String Bankstatus = null;
				///////////////////////////////////////
				this.Login(UserName,Password,StoreID);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Dismissed After Bankrupt -----------");

				Thread.sleep(5000);
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
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
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

				String LoanNumber=driver.findElement(By.xpath(" //*[@id='transactionHistoryTable']/tbody/tr/td[1]/table/tbody/tr[3]/td/span[2]")).getText();;
				test.log(LogStatus.PASS, "Loan Number captured is ::"+LoanNumber);
				
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 10);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				////////////////////////////////////
				/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
				driver.manage().window().maximize();
				Thread.sleep(1000);



				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);					   
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				} 
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				
				driver.findElement(By.name("requestBean.dealNbr")).sendKeys(LoanNumber);
				test.log(LogStatus.PASS, "Loan Number entered  is : "+LoanNumber);
				
		/*		driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store Number entered is entered: "+SSN1);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);*/
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(3000);			

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");  
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();


				test.log(LogStatus.PASS,"Click on Go button");	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("menu")).sendKeys("Edit");
				driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if( driver.findElement(By.name("loanCode")).isDisplayed())
				{
					driver.findElement(By.name("loanCode")).click();
					test.log(LogStatus.PASS, "Selecting Check box for loan");
				}
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Dismissed");
				test.log(LogStatus.PASS, "select status as  Dismissed"); 
				driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
				test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
				test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
				Thread.sleep(500);			
				driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
				test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);			



				driver.findElement(By.name("bt_AddBankruptcy")).click();			
				test.log(LogStatus.PASS, "Status Dismissed is Saved");

				Thread.sleep(50000);
				//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
				Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();

				test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Deceased");

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}		 

	}

	////*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]
	public void EOD_BatchProcess_DueDate_DFLT(String SSN,String FileName,int days,int InstNum) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- EOD BatchProcess Default Date -----------");


				String SSN1 = SSN.substring(0, 3);

				String SSN2 = SSN.substring(3,5);

				String SSN3 = SSN.substring(5,9);

				Thread.sleep(5000);

				Thread.sleep(1000);

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

				//driver.findElement(By.name("button")).click();
				//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");

				for(String winHandle : driver.getWindowHandles()){

					driver.switchTo().window(winHandle);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


				//driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				/*if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
				 */
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String DueDate=null;

				/*List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();*/


				/*
for(int cnt=2; cnt<=InstNum; cnt++)
{
				 */


				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]")).getText();
				//DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();

				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



				/*}
				 */
				Thread.sleep(1000);
				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

				test.log(LogStatus.PASS, "DueDate:" + DueDate);


				//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);

				//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on QA Jobs");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(5000);

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

				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

				{

					test.log(LogStatus.PASS, "Process Date updated successfully");

				}

				else

				{

					test.log(LogStatus.FAIL, "Process Date updated successfully.");

				}



				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("EOD Batch Process")).click();
				test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				test.log(LogStatus.INFO, "EOD Batch Process Completed");


			}

		}

	}


	public void EOD_BatchProcess_DueDate(String SSN,String FileName,int days,int InstNum) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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

				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- EOD Batch Process DueDate -----------");


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

				//driver.findElement(By.name("button")).click();
				//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");

				for(String winHandle : driver.getWindowHandles()){

					driver.switchTo().window(winHandle);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


				//driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				/*if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
				 */
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String DueDate=null;

				/*List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();*/


				/*
for(int cnt=2; cnt<=InstNum; cnt++)
{
				 */


				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
			
				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();

				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



				/*}
				 */
				Thread.sleep(1000);
				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

				test.log(LogStatus.PASS, "DueDate:" + DueDate);


				//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);

				//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on QA Jobs");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(5000);

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

				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

				{

					test.log(LogStatus.PASS, "Process Date updated successfully");

				}

				else

				{

					test.log(LogStatus.FAIL, "Process Date updated successfully.");

				}



				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("EOD Batch Process")).click();
				test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				test.log(LogStatus.INFO, "EOD Batch Process Completed");


			}

		}

	}


	public void Active_Military_Start(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Active Military Start -----------");


				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				driver=new InternetExplorerDriver();
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("li[id='900000']")).click();				
				test.log(LogStatus.PASS, "Clicked on Borrower");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(1000);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='988190443']")));
				driver.findElement(By.cssSelector("li[id='988190443']")).click();			
				test.log(LogStatus.PASS, "Clicked on Active Military");	
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//driver.findElement(By.xpath("//*[contains(text(),'Go')]")).click();			
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//Thread.sleep(1000);
				//driver.findElement(By.name("menu1")).sendKeys("Active Military");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("//html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input]")).click();
				///html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input
				test.log(LogStatus.PASS, "Click on GO Button");

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				test.log(LogStatus.PASS, "Accept the Alert");				
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				Thread.sleep(2000);
				driver.findElement(By.name("requestBean.activeMilitaryType")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("finishBank")).click();

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

				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@name='ok' and @type='button']")).click();
				//driver.findElement(By.xpath("///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input]")).click();
				///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input
				test.log(LogStatus.PASS, "Active Military Transaction Completed Successfully.");
			}
		}
	}



	public void Deceased_AfterBANKRUPT(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
				String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);
				String Bankstatus = null;
				///////////////////////////////////////
				this.Login(UserName,Password,StoreID);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Deceased after Bankrupt -----------");

				Thread.sleep(5000);
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
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
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

/*				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();
*/				String LoanNumber=driver.findElement(By.xpath(" //*[@id='transactionHistoryTable']/tbody/tr/td[1]/table/tbody/tr[3]/td/span[2]")).getText();;
				test.log(LogStatus.PASS, "Loan Number captured is ::"+LoanNumber);
				//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 10);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				////////////////////////////////////
				/*		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");*/
				driver.manage().window().maximize();
				Thread.sleep(1000);



				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);					   
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				} 
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.dealNbr")).sendKeys(LoanNumber);
				test.log(LogStatus.PASS, "Loan Number entered  is : "+LoanNumber);
/*				driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store Number entered is entered: "+SSN1);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				Thread.sleep(5000);*/
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(3000);			

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");  
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();


				test.log(LogStatus.PASS,"Click on Go button");	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("menu")).sendKeys("Edit");
				driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if( driver.findElement(By.name("loanCode")).isDisplayed())
				{
					driver.findElement(By.name("loanCode")).click();
					test.log(LogStatus.PASS, "Selecting Check box for loan");
				}
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
				test.log(LogStatus.PASS, "select status as  Deceased"); 
				driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
				test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
				test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
				Thread.sleep(500);			
				driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
				test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);			



				driver.findElement(By.name("bt_AddBankruptcy")).click();			
				test.log(LogStatus.PASS, "Status Deceased is Saved");

				Thread.sleep(50000);
				//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
				Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();

				test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Deceased");

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}		 

	}



	public void Payment_LessthanAmount (String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Payment Less than Installment Amount-----------");

				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
				/* driver.findElement(By.name("transactionList")).sendKeys("History");
						 if(ProductID.equals("ILP"))
						 {
							  driver.findElement(By.name("button")).click();
							// String PastDueAmt = null;
							 String InstAmt = null;
							 double Pymt;

							//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]

							// PastDueAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]")).getText();
//							 InstAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[9]/td/span[2]")).getText();
							 InstAmt = driver.findElement(By.name("instAmt")).getText();

							 // instAmt
							// double PDA = double.valueOf(PastDueAmt);

							 //double PDA = Double.valueOf(PastDueAmt);

							 double InstAmount = Double.valueOf(InstAmt);

							 Pymt =  InstAmount-10.0;
							 String Payment = String.valueOf(Pymt);

						 driver.close();


						 driver = new InternetExplorerDriver();

						 driver.get(AppURL);

						// CSRLoginpage login = new CSRLoginpage();
					     login.Login(UserName, Password, StoreId, driver, AppURL, test);
						driver.switchTo().defaultContent();
						Thread.sleep(1000);
						driver.switchTo().frame("topFrame");
						test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
						driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
						test.log(LogStatus.PASS, "Clicked on Loan Transactions");
						Thread.sleep(1000);
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


						    if(ProductID.equals("ILP"))
							 {
						    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
						    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
						    //	 /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
							 }
						  //  driver.findElement(By.name("button")).click();
							test.log(LogStatus.PASS, "Click on GO Button");
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");*/
				driver.findElement(By.name("transactionList")).sendKeys("Payment");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.findElement(By.name("button")).click();
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("//*[@id='PD5']")).click();
					String InstAmt = driver.findElement(By.name("instAmt")).getAttribute("value");

					// instAmt
					// double PDA = double.valueOf(PastDueAmt);

					//double PDA = Double.valueOf(PastDueAmt);

					double InstAmount = Double.valueOf(InstAmt);


					double Pymt =  InstAmount-10.0;
					String Payment = String.valueOf(Pymt);
					//CharSequence Payment;
					driver.findElement(By.name("requestBean.siilBean.payAmt")).clear();
					driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(Payment);
					test.log(LogStatus.PASS, "Payment Amount (past due +installment amount): "+Payment);
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
					test.log(LogStatus.PASS, "Tender Amount (past due +installment amount) "+Payment);						
					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);			
					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

					Thread.sleep(2000);							


					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}

					/*		try { 
										    Alert alert = driver.switchTo().alert();
										    alert.accept();
										    //if alert present, accept and move on.														

										}
										catch (NoAlertPresentException e) {
										    //do what you normally would if you didn't have the alert.
										}*/
					/*		

								 try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														

									}
									catch (NoAlertPresentException e) {



									    //do what you normally would if you didn't have the alert.
									}
					 */
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					Thread.sleep(2000);
					Thread.sleep(2000);
					if(driver.findElement(By.name("checkno")).isDisplayed())
					{

						driver.findElement(By.name("checkno")).click();
						test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
					}
					else
					{
						test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
					}


				}

			}

		}
	}


	public void Payment_PercentofDFLTAmt (String SSN,String FileName,double amt ) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Default percentage Payment -----------");

				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
				driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
				if(ProductID.equals("ILP"))
				{
					driver.findElement(By.name("button")).click();
					// String PastDueAmt = null;
					String Defaultpymt = null;
					// double DFLTPymt;

					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]

					// PastDueAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]")).getText();
					Defaultpymt = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
					// transactionDataBean.paymentBalAmt


					double Percent = Double.valueOf(amt);

					double DFLTPymt = Double.valueOf(Defaultpymt);

					DFLTPymt =  Percent*DFLTPymt;

					test.log(LogStatus.PASS, "Default Payment:" +DFLTPymt);

					String Payment = String.valueOf(DFLTPymt);

					test.log(LogStatus.PASS, "Default Payment after" +amt + "%:" +Payment);

					driver.close();


					driver = new InternetExplorerDriver();

					driver.get(AppURL);

					// CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					driver.switchTo().defaultContent();
					Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					Thread.sleep(1000);
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


					if(ProductID.equals("ILP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
						driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
						//	 /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
					driver.findElement(By.name("transactionList")).sendKeys("Default Payment");

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.findElement(By.name("button")).click();
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if(ProductID.equals("ILP"))
					{

						//driver.findElement(By.xpath("//*[@id='PD5']")).click();

						//CharSequence Payment;
						driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
						//driver.findElement(By.name("transactionDataBean.paymentBalAmt")).sendKeys(Payment);
						//test.log(LogStatus.PASS, "Payment Amount (past due +installment amount): "+Payment);
						driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(Payment);							
						driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
						driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Payment);
						test.log(LogStatus.PASS, "Tender Amount (past due +installment amount) "+Payment);						
						driver.findElement(By.name("password")).sendKeys(Password);
						test.log(LogStatus.PASS, "Password is selected as "+Password);			
						driver.findElement(By.name("Submit22")).click();
						test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

						Thread.sleep(2000);							


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
						Thread.sleep(2000);
						Thread.sleep(2000);
						if(driver.findElement(By.name("checkno")).isDisplayed())
						{

							driver.findElement(By.name("checkno")).click();
							test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
						}
						else
						{
							test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
						}


					}

				}

			}
		}
	}
	
	public void EPP_ILP(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- RPP ILP -----------");

				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);



				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

				driver.findElement(By.name("transactionList")).sendKeys("Payment Plan");
				test.log(LogStatus.PASS,"Payment Plan Selected From Transaction List");
				driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
				Thread.sleep(5000);

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}


				Thread.sleep(5000);
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(5000);
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

				driver.findElement(By.name("collateralTypeId")).sendKeys("ACH");
				test.log(LogStatus.PASS,"Collateral Type is Selected as ACH");

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
	
	public void AgeStore_RPP_ILP(String SSN,String FileName,int Days,int instnum) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);


				System.out.println(AdminURL);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- AgeStore RPP ILP -----------");


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
				//driver.findElement(By.name("button")).click();

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");




				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");


				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  


				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				// //*[@id="PPNScheduleHistoryTable"]/tbody/tr[2]/td[2]
				// //*[@id="PPNScheduleHistoryTable"]/tbody/tr[3]/td[2]
				DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+instnum+"]/td[2]")).getText();
				//*[@id="ContractScheduleTable"]/tbody/tr[3]/td[2]
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
				Actions actions1 = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);
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


	public void RPP_Payment_ILP(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String Password = TestData.getCellData(sheetName,"Password",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String State = TestData.getCellData(sheetName,"StateID",row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
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
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				this.Login(UserName,Password,StoreID);	
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- RPP Payment ILP -----------");

				Thread.sleep(3000);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
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
				//  driver.findElement(By.name("button")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");



				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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
				driver.findElement(By.name("transactionList")).sendKeys("RPP Payment");

				//	 driver.findElement(By.name("button")).click(); 

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				// name="transactionDataBean.paymentAmt"
				String RPPAmt = driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");
				test.log(LogStatus.PASS, "Payment Amount is ::"+RPPAmt);
				// name="transactionDataBean.tenderTypeFirst"
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "Tender Type selected is ::Cash");
				// name="transactionDataBean.tenderAmtFirst"
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(RPPAmt);
				test.log(LogStatus.PASS, "Tender Amount selected is ::"+RPPAmt);
				// name="password"
				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password entered is ::"+Password);
				// name="Submit22"
				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Clicked on FinishPaymentPlan button ");



				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}

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


				if((driver.findElement(By.name("checkyes"))).isDisplayed())
				{
					test.log(LogStatus.INFO, "PaymentPlan Transaction with-SSN: " +SSN +" :: is Successful");
				}



				else
				{
					test.log(LogStatus.PASS, "PaymentPlan Transaction is not Completed Successfully ");
				}

			}

		}

	}

	public void EPP_EOD_BatchProcess_DueDate(String SSN,String FileName,int days,int InstNum) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- EPP_EOD_Batch Process DueDate -----------");


				String SSN1 = SSN.substring(0, 3);

				String SSN2 = SSN.substring(3,5);

				String SSN3 = SSN.substring(5,9);

				Thread.sleep(5000);

				Thread.sleep(1000);

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

				//driver.findElement(By.name("button")).click();
				//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");

				for(String winHandle : driver.getWindowHandles()){

					driver.switchTo().window(winHandle);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


				//driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				/*if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
				 */
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String DueDate=null;

				/*List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();*/


				/*
for(int cnt=2; cnt<=InstNum; cnt++)
{
				 */


				//DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+InstNum+"]/td[2]")).getText();
				//*[@id="PPNScheduleHistoryTable"]/tbody/tr[2]/td[2]
				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



				/*}
				 */
				Thread.sleep(1000);
				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

				test.log(LogStatus.PASS, "DueDate:" + DueDate);


				//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);

				//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on QA Jobs");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(5000);

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

				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

				{

					test.log(LogStatus.PASS, "Process Date updated successfully");

				}

				else

				{

					test.log(LogStatus.FAIL, "Process Date updated successfully.");

				}



				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("EOD Batch Process")).click();
				test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				test.log(LogStatus.INFO, "EOD Batch Process Completed");


			}

		}

	}

	
	public void EPP_AfterDFLT_WO_EOD_BatchProcess_DueDate(String SSN,String FileName,int days,int InstNum) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- EPP_AfterDFLT_WO_EOD_BatchProcess_DueDate -----------");


				String SSN1 = SSN.substring(0, 3);

				String SSN2 = SSN.substring(3,5);

				String SSN3 = SSN.substring(5,9);

				Thread.sleep(5000);

				Thread.sleep(1000);

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

				//driver.findElement(By.name("button")).click();
				//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");

				for(String winHandle : driver.getWindowHandles()){

					driver.switchTo().window(winHandle);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


				//driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				/*if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
				 */
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String DueDate=null;

				/*List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();*/


				/*
for(int cnt=2; cnt<=InstNum; cnt++)
{
				 */


				// //*[@id="transactionDetailsTable"]/tbody/tr[10]/td[4]/font
				//DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();

				//                                     //*[@id="transactionDetailsTable"]/tbody/tr[10]/td[4]/font
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]")).getText();

				//DueDate = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[4]/font")).getText();
				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]  

				//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]")).getText();

				//*[@id="PPNScheduleHistoryTable"]/tbody/tr[2]/td[2]
				// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
				test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



				/*}
				 */
				Thread.sleep(1000);
				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

				test.log(LogStatus.PASS, "DueDate:" + DueDate);


				//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);

				//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				String DDueDate[] =DueDate.split(" ");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];


				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on QA Jobs");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(5000);

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

				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

				{

					test.log(LogStatus.PASS, "Process Date updated successfully");

				}

				else

				{

					test.log(LogStatus.FAIL, "Process Date updated successfully.");

				}



				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("EOD Batch Process")).click();
				test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				test.log(LogStatus.INFO, "EOD Batch Process Completed");


			}

		}

	}
	
	public void Void_PaymentPlanPayment(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Void Payment plan Payment -----------");

				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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


				if(ProductID.equals("ILP"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
				if(ProductID.equals("ILP"))
				{
					driver.findElement(By.name("button")).click();
				}



				for( String winHandle1 : driver.getWindowHandles())
				{
					// driver.findElement(By.name("button")).click();
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("ILP"))
				{

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/select")).sendKeys("cash");
					test.log(LogStatus.PASS, "Disb type is selected as "+ "Cash");	
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);	
					driver.findElement(By.name("Submit33")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Void Paymentplan Payment button ");


					Thread.sleep(2000);							






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
					Thread.sleep(2000);
					Thread.sleep(2000);
					if(driver.findElement(By.name("checkno")).isDisplayed())
					{

						driver.findElement(By.name("checkno")).click();
						test.log(LogStatus.INFO, " void Payment with-SSN: " +SSN +" :: is Successful");
					}
					else
					{
						test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
					}


				}

			}

		}
	}

	public void Customer_Status(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				//test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> ----------- Customer Status -----------");

				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
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


				if(ProductID.equals("ILP"))
				{
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
					//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				}
				//  driver.findElement(By.name("button")).click();
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");

				if(ProductID.equals("ILP"))
				{
					//driver.findElement(By.name("button")).click(); 
					driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
					//*[@id="go_Button"]
				}
				test.log(LogStatus.PASS, "Click on GO Button");




				String BalanceStatus = null;
				String LoanStatus = null;

				BalanceStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				//*[@id="CustGrid"]/tbody/tr[2]/td[4]
				//*[@id="CustGrid"]/tbody/tr[2]/td[4]



				test.log(LogStatus.PASS, "Balance Status:" +BalanceStatus);
				test.log(LogStatus.PASS, "Loan Status:" +LoanStatus);

				Thread.sleep(10000);

			}
		}
	}

	
	public void AgeStore_Regular(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{

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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

				Date date = new Date();



				Calendar cal = Calendar.getInstance();

				cal.setTime(date);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1= cal.getTime();

				String date1 =df.format(DDueDate1);

				String DueDate0[] =date1.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];



				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
				Actions actions1 = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);
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



	public void CurePaymentStatus(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus = null;

				

				 
				// *[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();

				// *[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
				test.log(LogStatus.PASS, "Payment status is Cure." + CheckStaus);
				// DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				System.out.print(CheckStaus);
				// driver.close();

			}
		}
		}
	

	public void EncryptionKey_Void(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				this.Login(UserName, Password, StoreId);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String Eankey = null;
				Thread.sleep(4000);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
				driver.findElement(By.cssSelector("li[id='910000']")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

					// driver.findElement(By.xpath("
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Void");
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				// driver.findElement(By.id("go_Button")).click();
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String TranID = driver
						.findElement(By
								.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td"))
						.getText();
				test.log(LogStatus.PASS, "TranId captured:" + TranID);
				String TranID0[] = TranID.split(":");
				String TranID1 = TranID0[0];
				String TranID2 = TranID0[1];
				Thread.sleep(3000);
				// driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
				driver.findElement(By
						.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]"))
						.click();
				// /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]
				// driver.findElement(By.xpath("//input[@name='NO' and
				// @type='button']")).click();
				test.log(LogStatus.PASS, "No button is clicked ");
				// name="NO"
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Employee')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Encryption Key")).click();
				test.log(LogStatus.PASS, "Clicked on Encryption Key");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("Encryption")).click();
				test.log(LogStatus.PASS, "Clicked on Encryption");
				Thread.sleep(5000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreId);
				test.log(LogStatus.PASS, "Store number Entered");

				driver.findElement(By.name("requestBean.tranNbr")).sendKeys(TranID2);
				test.log(LogStatus.PASS, "Tran number Entered");

				driver.findElement(By.name("trancd")).sendKeys("Revolving Credit Buy-RCBUY");
				test.log(LogStatus.PASS, "Trancd selected");

				driver.findElement(By.name("GetKey")).click();
				test.log(LogStatus.PASS, "GetKey clicked");

				Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
				test.log(LogStatus.PASS, "GetKey clicked:" + Eankey);

				driver.close();
				driver = new InternetExplorerDriver();
				this.Login(UserName, Password, StoreId);
				Thread.sleep(4000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

					// driver.findElement(By.xpath("
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Void");
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				// driver.findElement(By.id("go_Button")).click();
				Thread.sleep(5000);

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				// driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
				driver.findElement(By
						.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[1]"))
						.click();
				// driver.findElement(By.xpath("//input[@name='YES' and
				// @type='button']")).click();
				test.log(LogStatus.PASS, "Yes Button clicked");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as " + TenderType);
					// String Pmt= driver.findElement(By.xpath("
					// /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();
					// System.out.println(Pmt);
					// driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					// test.log(LogStatus.PASS, "Tender Amt is entered as
					// "+Pmt);

					driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					test.log(LogStatus.PASS, "Encryption key is entered as " + Eankey);

				}

				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("password")).sendKeys(Password);
					// Robot robot = new Robot();
					// Thread.sleep(2000);
					// robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("Submit22")).click();
					// robot.keyPress(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
				}

				Thread.sleep(500);

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
				
			/*	 * driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame");
				 * driver.switchTo().frame("main"); if(ProductID.equals("LOC"))
				 * {
				 * 
				 * if(driver.findElement(By.name("checkyes")).isDisplayed()) {
				 * test.log(LogStatus.PASS,
				 * "Void Loan is Completed Successfully ");
				 * driver.findElement(By.name("checkyes")).click(); } else {
				 * test.log(LogStatus.FAIL,
				 * "Void Payment is not Completed Successfully ");
				 */
				 }
				 }
				 
			}
		
	



	public void BankruptStatus(String SSN, String FileName) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
					// driver.findElement(By.name("button")).click();
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				test.log(LogStatus.PASS, "History Selected in DropDown");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
					// driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String PaymentStatus = null;
				PaymentStatus = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Payment status is ::" + PaymentStatus);
				String LineStatus = null;
				LineStatus = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Line status is ::" + LineStatus);
				String WOReason = null;
				WOReason = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Reason for Customer got WriteOff ::" + WOReason);

			}
		}
	}

	public void Bankrupt_Void(String SSN, String FileName) throws Exception {
	
		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String BNKstatus = TestData.getCellData(sheetName, "BNKstatus", row);
				String AttorneyPhone = TestData.getCellData(sheetName, "AttorneyPhone", row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				System.out.println(AdminURL);
				///////////////////////////////////////

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");

				////////////////////////////////////
				
/*				 * driver.get(AdminURL); test.log(LogStatus.INFO,
				 * "Admin portal is launched");*/
				 
				driver.manage().window().maximize();
				Thread.sleep(1000);

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: " + UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200, 100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("menu")).sendKeys("Edit");
				driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input"))
						.click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(3000);
				if (driver.findElement(By.name("loanCode")).isDisplayed()) {
					driver.findElement(By.name("loanCode")).click();
					test.log(LogStatus.PASS, "Selecting Check box for loan");
				}
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
				test.log(LogStatus.PASS, "select status as Void");

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, " Clicked on Saved");

				Thread.sleep(5000);

				String ele = driver
						.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]"))
						.getText();
				if (ele.contains("Void")) {
					test.log(LogStatus.PASS, " Bankrupt void complted Sucessfully ");

				} else {
					test.log(LogStatus.PASS, " Bankrupt void not complted Sucessfully ");
				}

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}

		
	/*	 * if(driver.findElement(By.name("submitButton")).isDisplayed()) {
		 * test.log(LogStatus.PASS, "Store Aging is Successfully ");
		 * driver.findElement(By.name("submitButton")).click(); } else {
		 * test.log(LogStatus.FAIL, "Store Aging is not Successfully "); }*/
		 
		// driver.close();
	}

	public void Void_Deceased(String SSN, String FileName) throws Exception {
		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String BNKstatus = TestData.getCellData(sheetName, "BNKstatus", row);
				String AttorneyPhone = TestData.getCellData(sheetName, "AttorneyPhone", row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				System.out.println(AdminURL);
				///////////////////////////////////////

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");

				////////////////////////////////////
			/*	
				 * driver.get(AdminURL); test.log(LogStatus.INFO,
				 * "Admin portal is launched");*/
				 
				driver.manage().window().maximize();
				Thread.sleep(1000);

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: " + UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				Thread.sleep(8000);
				driver.switchTo().frame("topFrame");
				WebDriverWait wait = new WebDriverWait(driver, 10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
				test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200, 100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("menu")).sendKeys("Edit");
				driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input"))
						.click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(3000);
				
			/*	 * if( driver.findElement(By.name("loanCode")).isDisplayed()) {
				 * driver.findElement(By.name("loanCode")).click();
				 * test.log(LogStatus.PASS, "Selecting Check box for loan"); }*/
				 
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
				test.log(LogStatus.PASS, "select status as Void");

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, " Clicked on Saved");

				Thread.sleep(5000);

				String ele = driver
						.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]"))
						.getText();
				if (ele.contains("Void")) {
					test.log(LogStatus.PASS, " Bankrupt void complted Sucessfully ");

				} else {
					test.log(LogStatus.PASS, " Bankrupt void not complted Sucessfully ");
				}

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}

		

	}


	public void Payoff(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String DisbType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String TenderType = TestData.getCellData(sheetName, "Tender_Type", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(4000);
				String Payoffbalance = null;
				this.Login(UserName, Password, StoreId);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Closure Transaction with-SSN: " + SSN + " :: is Starts");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
				driver.findElement(By.cssSelector("li[id='910000']")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(1000);
				if (ProductID.equals("ILP")) {
					                              
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.name("button")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				} else {
					driver.findElement(By.id("go_Button")).click();
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP"))

				{

					driver.findElement(By.name("requestBean.paymentType")).click();
					test.log(LogStatus.PASS, "Pay off the balance option is selected ");

					Payoffbalance = driver.findElement(By.name("payOff")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payoff balance " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(DisbType);
					test.log(LogStatus.PASS, "Tender Type is selected " + TenderType);

					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payoffbalance);
					test.log(LogStatus.PASS, "Tender Amount is Entered " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as " + Password);

					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						// if alert present, accept and move on.

					} catch (NoAlertPresentException e) {
						// do what you normally would if you didn't have the
						// alert.
					}
					for (String winHandle1 : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					// driver.findElement(By.name("ok")).click();

					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					if (driver.findElement(By.name("checkyes")).isDisplayed()) {
						test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " + SSN + " :: is Successful");
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
						driver.findElement(By.name("checkyes")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
					}

				}
			}

		}
	}
	

	public void Payoff_Return(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String DisbType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String TenderType = TestData.getCellData(sheetName, "Tender_Type", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(4000);
				String Payoffbalance = null;
				this.Login(UserName, Password, StoreId);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Closure Transaction with-SSN: " + SSN + " :: is Starts");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
				driver.findElement(By.cssSelector("li[id='910000']")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(1000);
				if (ProductID.equals("ILP")) {
					                              
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.name("button")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				} else {
					driver.findElement(By.id("go_Button")).click();
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP"))

				{
					
                    driver.findElement(By.xpath("//*[@id='PD3']")).click();
                    test.log(LogStatus.PASS, "Pay off the balance option is selected ");
				//	driver.findElement(By.name("requestBean.paymentType")).click();
				//	test.log(LogStatus.PASS, "Pay off the balance option is selected ");

					Payoffbalance = driver.findElement(By.name("requestBean.siilBean.payAmt")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payoff balance " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(DisbType);
					test.log(LogStatus.PASS, "Tender Type is selected " + TenderType);

					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payoffbalance);
					test.log(LogStatus.PASS, "Tender Amount is Entered " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as " + Password);

					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						// if alert present, accept and move on.

					} catch (NoAlertPresentException e) {
						// do what you normally would if you didn't have the
						// alert.
					}
					for (String winHandle1 : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					// driver.findElement(By.name("ok")).click();

					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					if (driver.findElement(By.name("checkyes")).isDisplayed()) {
						test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " + SSN + " :: is Successful");
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
						driver.findElement(By.name("checkyes")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
					}

				}
			}

		}
	}
	

	public void Payoff_Installments(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String DisbType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String TenderType = TestData.getCellData(sheetName, "Tender_Type", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(4000);
				String Payoffbalance = null;
				this.Login(UserName, Password, StoreId);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Closure Transaction with-SSN: " + SSN + " :: is Starts");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
				driver.findElement(By.cssSelector("li[id='910000']")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			/*	if (ProductID.equals("ILP")) {
					                            
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
					// driver.findElement(By.name("button")).click();
				}*/
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				} else {
					driver.findElement(By.id("go_Button")).click();
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP"))

				{

					driver.findElement(By.name("requestBean.paymentType")).click();
					test.log(LogStatus.PASS, "Pay off the balance option is selected ");

					Payoffbalance = driver.findElement(By.name("payOff")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payoff balance " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(DisbType);
					test.log(LogStatus.PASS, "Tender Type is selected " + TenderType);

					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payoffbalance);
					test.log(LogStatus.PASS, "Tender Amount is Entered " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as " + Password);

					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						// if alert present, accept and move on.

					} catch (NoAlertPresentException e) {
						// do what you normally would if you didn't have the
						// alert.
					}
					for (String winHandle1 : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					// driver.findElement(By.name("ok")).click();

					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					if (driver.findElement(By.name("checkyes")).isDisplayed()) {
						test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " + SSN + " :: is Successful");
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
						driver.findElement(By.name("checkyes")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
					}

				}
			}

		}
	}

	public void AgeStore(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				
				  driver.manage().timeouts().implicitlyWait(120,
				  TimeUnit.SECONDS); driver.switchTo().defaultContent();
				  driver.switchTo().frame("mainFrame"); WebElement elements1 = driver.findElement(By.linkText("QA Jobs")); Actions
				  actions1 = new Actions(driver);
				  actions1.moveToElement(elements1).build().perform();
				  driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
				 

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	

	public void History_Paymentcaluculation(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		int Schedules_count = 0;
		int i;
		double totalorigfee1 = 0;
		int totdays1 = 0;
		double totalMHCFee1 = 0;
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
				String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				 //driver.findElement(By.name("button")).click(); 
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				// driver.findElement(By.id("go_Button")).click();

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				
				
			   String Linestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Payment status is ." + Linestatus);
				String BalanceStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Payment status is ." + BalanceStaus);
               
				String TaotalPaidAMT = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[21]/td/span[2]")).getText();
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
				test.log(LogStatus.INFO, "Total paid AMTF ee  ::" + TaotalPaidAMT);

				String PrincipalAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[17]/td/span[2]")).getText();
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
				test.log(LogStatus.INFO, "Principal AMT ::" + PrincipalAmt);

				String TotalPaidMHCFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]")).getText();
				test.log(LogStatus.INFO, "Total MHC Fee ::" + TotalPaidMHCFee);

				String ORigiFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[24]/td/span[2]")).getText();
				test.log(LogStatus.INFO, "Total Origination Fee Paid to date ::" + ORigiFee);
				
				//String Balancestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();  
				//test.log(LogStatus.INFO, "Balance status is::"+Balancestatus);
				
				String Latefee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[20]/td/span[2]")).getText();  
				test.log(LogStatus.INFO, "Late fee is::"+Latefee);
			
				String NSFFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[10]/font")).getText();  
				test.log(LogStatus.INFO, "NSFFee fee is::"+NSFFee);
				
				
				if(CollateralType.equals("CASH")){
				double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee);
				
				String caluculationAMT1 = String.valueOf(caluculationAMT);			     
				double b = Double.parseDouble(TaotalPaidAMT);
				String b1 = String.valueOf(b);
				
				if (caluculationAMT1.contains(b1)) {
					test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}else {
					test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}
				}
				
				if(CollateralType.equals("ACH")){
					double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee)+Double.parseDouble(NSFFee);
					
					String caluculationAMT1 = String.valueOf(caluculationAMT);			     
					double b = Double.parseDouble(TaotalPaidAMT);
					String b1 = String.valueOf(b);
					
					if (caluculationAMT1.contains(b1)) {
						test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
					}else {
						test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
					}
					}
				
				
			}
		}
	}
	

	public void AgeStore_1stInstallment(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[2]/td[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				
				  /*driver.manage().timeouts().implicitlyWait(120,  TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 // driver.switchTo().frame("mainFrame"); WebElement elements1 =  driver.findElement(By.linkText("QA Jobs"));
				 Actions actions1 = new Actions(driver);
				 actions1.moveToElement(elements1).build().perform();
				  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);*/
				 

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	
	
	public void AgeStore_2ndInstallment(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[3]/td[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				
				  driver.manage().timeouts().implicitlyWait(120,
				  TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame"); WebElement elements1 =  driver.findElement(By.linkText("QA Jobs")); Actions
				  actions1 = new Actions(driver);
				  actions1.moveToElement(elements1).build().perform();
				  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				 

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	
	public void AgeStore_3rdInstallment(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				
				  driver.manage().timeouts().implicitlyWait(120,
				  TimeUnit.SECONDS); driver.switchTo().defaultContent();
				  driver.switchTo().frame("mainFrame"); WebElement elements1 =  driver.findElement(By.linkText("QA Jobs")); Actions
				  actions1 = new Actions(driver);
				  actions1.moveToElement(elements1).build().perform();
				  driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				 

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	
	



public void Void_Payment (String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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
				    
				 
				    if(ProductID.equals("ILP"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				    	// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
					 if(ProductID.equals("ILP"))
					 {
						  driver.findElement(By.name("button")).click();
					 }
						  
				
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						// driver.findElement(By.name("button")).click();
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("ILP"))
						 {
							 
							 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[6]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[3]/td[2]/select")).sendKeys("cash");
							 test.log(LogStatus.PASS, "Disb type is selected as "+ "Cash");	
							 driver.findElement(By.name("requestBean.password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Password is selected as "+Password);			
							 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/table/tbody/tr/td[3]/input")).click();
								test.log(LogStatus.PASS, "Clicked on Void Payment button ");
							 
							 Thread.sleep(2000);							
								 
								 
									try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														
										
									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}
									
									try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														
										
									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}
								
							 
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
									Thread.sleep(2000);
									 Thread.sleep(2000);
								 if(driver.findElement(By.name("checkno")).isDisplayed())
									{
									 
									 driver.findElement(By.name("checkno")).click();
									 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
									}
							
					    	
						 }
					
			}
			
		}
		}




public void ACH_Deposit(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			                                          
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
			driver.findElement(By.linkText("Installment Loan")).click();
			test.log(LogStatus.PASS, "Clicked on Installment Loan");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process ILP Pre ACH Deposits")).click();
			test.log(LogStatus.PASS, "Clicked on Process ILP Pre ACH Deposits");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");*/
			Thread.sleep(5000);

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
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "LOC Pre ACH Deposit Process  successfully.");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process LOC Pre ACH Deposit is not updated successfully.");
			}




		}
		//driver.quit();
	}
}



public void EODProcessing_with_recordsChecking(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			Thread.sleep(5000);	    

			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Daily Processing");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("eod")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			Thread.sleep(4000);
			// driver.findElement(By.name("requestBean.comments")).click();
			driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
			// requestBean.comments
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			Thread.sleep(4000);
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}
			Thread.sleep(4000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			//String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
			String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

			driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);


			test.log(LogStatus.PASS,"Enter the Balance 50");
			driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
			test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
			driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
			test.log(LogStatus.PASS, "Notes Entered ");	
			driver.findElement(By.name("bt_AddDrawer")).click();
			test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
			Thread.sleep(5000);

			WebDriverWait wait = new WebDriverWait(driver, 10000);

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



			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			Thread.sleep(5000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")));
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();

			test.log(LogStatus.PASS, "Clicked on Next");

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			Thread.sleep(5000);

			try { 

				if( driver.findElement(By.name("achDepRecLoc")).isDisplayed())
					//if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[6]")).isDisplayed())
				{									        								

					//driver.findElement(By.name("achDepRecLoc")).isDisplayed();
					test.log(LogStatus.PASS, "LOC ACH  Deposit Available");

				}
				else{
					test.log(LogStatus.PASS, "LOC ACH  Deposit Not Available.");
				}

			}
			catch (Exception e) {
				//do what you normally would if you didn't have the alert.





				test.log(LogStatus.PASS, "LOC ACH  Deposit record Not Available.");
			}


			Thread.sleep(5000);

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
			test.log(LogStatus.PASS, "Bag number is provided as 34");
			driver.findElement(By.name("finishdeposit")).click();
			test.log(LogStatus.PASS, "Clicked on Finish Deposit");
			test.log(LogStatus.PASS, "StatmentGeneration EOD Processing Completed");
			Thread.sleep(4000);

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


public void Payment_ILP(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Payment_ILP Transaction with-SSN: " +SSN +" :: is Starts");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		//		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("ILP"))
				
			{
				
				
				///driver.findElement(By.name("requestBean.paymentType")).click();
				// name="requestBean.paymentType"  name="requestBean.paymentType"  name="requestBean.paymentType"
				//  value="PD4" payment    value="PD3" payoff  value="PD5" payanyotherAmt
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input[2]  payoff value
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]  payment value
				driver.findElement(By.xpath("//*[@id='PD4']")).click();
				test.log(LogStatus.PASS, "Payment option is selected ");
				
			     Payment=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");				     
			     
				test.log(LogStatus.PASS, "Capture Payment "+Payment);
				
				Thread.sleep(500);
				// name="requestBean.siilBean.tenderTypeFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Tender Type is selected "+TenderType);
				
				
				// name="requestBean.siilBean.tenderAmtFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+Payment);
				
				Thread.sleep(500);
				// name="requestBean.password"
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				// name="finish"
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();

// name="checkyes"
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
				}


			
		
			}
		}

	}
}


public void PaymentLess_ILP(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Payment_ILP Transaction with-SSN: " +SSN +" :: is Starts");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		//		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("ILP"))
				
			{
				
	
				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, "Payment option is selected ");
				
			     Payment=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");				     
			     
				test.log(LogStatus.PASS, "Capture Payment "+Payment);
				
			double Payment1 =	Double.parseDouble(Payment);
			double LessPMT = Payment1 - 10.00;
			String LessPMT1 = String.valueOf(LessPMT);
				
				driver.findElement(By.name("requestBean.siilBean.payAmt")).clear();
				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(LessPMT1);
				test.log(LogStatus.PASS, "Eneter the cure amount  "+LessPMT);
				
				Thread.sleep(500);
				// name="requestBean.siilBean.tenderTypeFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Tender Type is selected "+TenderType);
				
				
				// name="requestBean.siilBean.tenderAmtFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+Payment);
				
				Thread.sleep(500);
				// name="requestBean.password"
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				// name="finish"
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();

// name="checkyes"
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
				}


			
		
			}
		}

	}
}





public void PaymentcureAmount_ILP(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Payment_ILP Transaction with-SSN: " +SSN +" :: is Starts");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		//		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			String cureAmount = driver.findElement(By.xpath("//*[@id='CustGrid']/tbody/tr[2]/td[7]")).getText();
			test.log(LogStatus.PASS, "cure amount captured "+cureAmount);
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("ILP"))
				
			{
				
				
				///driver.findElement(By.name("requestBean.paymentType")).click();
				// name="requestBean.paymentType"  name="requestBean.paymentType"  name="requestBean.paymentType"
				//  value="PD4" payment    value="PD3" payoff  value="PD5" payanyotherAmt
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input[2]  payoff value
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]  payment value
				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, "pay any another Amount option is selected ");
				
			  ///   Payment=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");				     
				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(cureAmount);
				test.log(LogStatus.PASS, "Eneter the cure amount  "+cureAmount);
				
				Thread.sleep(500);
				// name="requestBean.siilBean.tenderTypeFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Tender Type is selected "+TenderType);
				
				
				// name="requestBean.siilBean.tenderAmtFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(cureAmount);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+cureAmount);
				
				Thread.sleep(500);
				// name="requestBean.password"
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				// name="finish"
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();

// name="checkyes"
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "cure payment completed  Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "cure payment completed  is not Completed Successfully ");
				}


			
		
			}
		}

	}
}




public void RegistrationPage_NewLoan_ILP(WebDriver driver,ExtentTest test,String AppURL,String SSN,String FileName) throws Exception{

	//Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
	//Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);
	//Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);

	int lastrow=TestData.getLastRow("Borrower_Registration");

	String sheetName="Borrower_Registration";
	for(int row=2;row<=lastrow;row++)
	{
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

			//String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			//String UserName = TestData.getCellData(sheetName,"UserName",row);
			//String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			//  String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			// String ProductID =TestData.getCellData(sheetName,"ProductID",row);
			// String StateID = TestData.getCellData(sheetName,"StateID",row);
			// String SSN = TestData.getCellData(sheetName,"SSN",row);
			//String Header = StateID+ "_" + ProductID;
			// test = reports.startTest("BorrowerRegistration_"+Header,"Register a Borrower");
			String LastName = TestData.getCellData(sheetName,"LastName",row);
			String FirstName = TestData.getCellData(sheetName,"FirstName",row);
			String AddressLine =TestData.getCellData(sheetName,"AddressLine",row);
			String City = TestData.getCellData(sheetName,"City",row);
			String State = TestData.getCellData(sheetName,"State",row);
			String ZipCode = TestData.getCellData(sheetName,"ZipCode",row);
			String MonthsAtAddress =TestData.getCellData(sheetName,"MonthsAtAddress",row);
			String Bank_ABARoutingNbr =TestData.getCellData(sheetName,"Bank_ABARoutingNbr",row);
			String Bank_ChkgAcctNbr =TestData.getCellData(sheetName,"Bank_ChkgAcctNbr",row);
			String Ctc_PrimaryPhone =TestData.getCellData(sheetName,"Ctc_PrimaryPhone",row);
			String Ctc_PhoneType =TestData.getCellData(sheetName,"Ctc_PhoneType",row);
			String Misc_PhotoIDNbr =TestData.getCellData(sheetName,"Misc_PhotoIDNbr",row);
			String Misc_IDExpDate =TestData.getCellData(sheetName,"Misc_IDExpDate",row);
			String Misc_PhotoIDType =TestData.getCellData(sheetName,"Misc_PhotoIDType",row);
			String BorrDOB = TestData.getCellData(sheetName,"Misc_DOB",row);
			String Income_IncomeType =TestData.getCellData(sheetName,"Income_IncomeType",row);
			String Income_Employer =TestData.getCellData(sheetName,"Income_Employer",row);
			String Income_WorkPhone =TestData.getCellData(sheetName,"Income_WorkPhone",row);
			String Income_NetIncomeAmt =TestData.getCellData(sheetName,"Income_NetIncomeAmt",row);
			String Income_GrossIncome =TestData.getCellData(sheetName,"Income_GrossIncome",row);
			String Income_PayFrequency =TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String Income_HireDt =TestData.getCellData(sheetName,"Income_HireDt",row);
			String Income_DirectDeposit=TestData.getCellData(sheetName,"Income_DirectDeposit",row);

			String ProductType=TestData.getCellData(sheetName,"ProductType",row);
			/* String PrimaryRef_LastName =
TestData.getCellData(sheetName,"PrimaryRef_LastName",row);
    String PrimaryRef_FirstName =
TestData.getCellData(sheetName,"PrimaryRef_FirstName",row);
    String PrimaryRef_Relationship =
TestData.getCellData(sheetName,"PrimaryRef_Relationship",row);
    String
PrimaryRef_PhoneNbr=TestData.getCellData(sheetName,"PrimaryRef_PhoneNbr",row);
    String Ref_LastName =
TestData.getCellData(sheetName,"Ref_LastName",row);
    String Ref_FirstName =
TestData.getCellData(sheetName,"Ref_FirstName",row);
    String Ref_Relationship =
TestData.getCellData(sheetName,"Ref_Relationship",row);
    String
Ref_PhoneNbr=TestData.getCellData(sheetName,"Ref_PhoneNbr",row);*/
			String Bankruptcy=TestData.getCellData(sheetName,"Bankruptcy",row);
			test.log(LogStatus.INFO, "Borrower Registration-SSN: " +SSN);
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String PP1 = Ctc_PrimaryPhone.substring(0, 3);
			String PP2 = Ctc_PrimaryPhone.substring(3, 6);
			String PP3 = Ctc_PrimaryPhone.substring(6, 10);
			String IncomeP1 = Income_WorkPhone.substring(0, 3);
			String IncomeP2 = Income_WorkPhone.substring(3, 6);
			String IncomeP3 = Income_WorkPhone.substring(6, 10);
			/*  String PrimaryRef_PhoneNbr1 =
PrimaryRef_PhoneNbr.substring(0, 3);
             String PrimaryRef_PhoneNbr2 =
PrimaryRef_PhoneNbr.substring(3, 6);
             String PrimaryRef_PhoneNbr3 =
PrimaryRef_PhoneNbr.substring(6, 10);
             String Ref_PhoneNbr1 = Ref_PhoneNbr.substring(0, 3);
             String Ref_PhoneNbr2 = Ref_PhoneNbr.substring(3, 6);
             String Ref_PhoneNbr3 = Ref_PhoneNbr.substring(6, 10);*/
			System.out.println(Misc_IDExpDate);
			Date Misc_IDExpDt = df.parse(Misc_IDExpDate);
			String IDExpDate0 =df.format(Misc_IDExpDt);
			System.out.println(IDExpDate0);
			String IDExpDate[] =IDExpDate0.split("/");
			String IDExpD1 = IDExpDate[0];
			String IDExpD2 = IDExpDate[1];
			String IDExpD3 = IDExpDate[2];
			String DOB[] =BorrDOB.split("/");
			String DOB1 = DOB[0];
			String DOB2 = DOB[1];
			String DOB3 = DOB[2];
			// appUrl = AppURL;
			//  this.Login(UserName, Password, StoreId);
			//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			Thread.sleep(5000);
			// driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 1000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			//WebElement  ele = driver.findElement(By.name("topFrame"));
			//new Actions(driver).moveToElement(ele).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
			driver.findElement(By.cssSelector("li[id='900000']")).click();
			//driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='901000']")));
			driver.findElement(By.cssSelector("li[id='901000']")).click();
			test.log(LogStatus.PASS, "Clicked on Registration");
			driver.switchTo().frame("main");
			driver.findElement(By.name("customerBean.custProdType")).sendKeys(ProductType);
			test.log(LogStatus.PASS, "ProductType is entered: "+ProductType);
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("ssn4")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "Confirm SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn5")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "Confirm SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn6")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "Confirm SSN3 is entered: "+SSN3);
			driver.findElement(By.name("customerBean.lastNm")).sendKeys(LastName);
			test.log(LogStatus.PASS, "LastName is entered: "+LastName);

			driver.findElement(By.name("customerBean.firstNm")).sendKeys(FirstName);
			test.log(LogStatus.PASS, "FirstName is entered: "+FirstName);
			driver.findElement(By.name("customerBean.mailingAddressLn1")).sendKeys(AddressLine);
			test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
			driver.findElement(By.name("customerBean.mailingCity")).sendKeys(City);
			test.log(LogStatus.PASS, "City is entered: "+City);
			driver.findElement(By.name("customerBean.mailingStateCd")).sendKeys(State);
			test.log(LogStatus.PASS, "State is entered: "+State);
			driver.findElement(By.name("customerBean.mailingPostalCd")).sendKeys(ZipCode);
			test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
			driver.findElement(By.name("customerBean.sameMailAddress")).click();
			test.log(LogStatus.PASS, "Mailing address is selected as sameas above");
			driver.findElement(By.name("customerBean.monthsAtAddress")).sendKeys(MonthsAtAddress);
			test.log(LogStatus.PASS, "MonthsAtAddress is entered:"+MonthsAtAddress);
			driver.findElement(By.name("customerBean.rentOwnFlg")).sendKeys("Yes");
			/*driver.findElement(By.name("customerBean.firstNm")).sendKeys(FirstName);
     test.log(LogStatus.PASS, "FirstName is entered: "+FirstName);
driver.findElement(By.name("customerBean.addressLn")).sendKeys(AddressLine);
     test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
driver.findElement(By.name("customerBean.city")).sendKeys(City);
     test.log(LogStatus.PASS, "City is entered: "+City);
driver.findElement(By.name("customerBean.stateCd")).sendKeys(State);
     test.log(LogStatus.PASS, "State is entered: "+State);
driver.findElement(By.name("customerBean.postalCd")).sendKeys(ZipCode);
     test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
driver.findElement(By.name("customerBean.sameMailAddress")).click();
     test.log(LogStatus.PASS, "Mailing address is selected as same
as above");
driver.findElement(By.name("customerBean.monthsAtAddress")).sendKeys(MonthsAtAddress);
     test.log(LogStatus.PASS, "MonthsAtAddress is entered:
"+MonthsAtAddress);
driver.findElement(By.name("customerBean.rentOwnFlg")).sendKeys("Yes");
     test.log(LogStatus.PASS, "Own Residence?* is entered: Yes");*/
			driver.findElement(By.name("phoneNbr1")).sendKeys(PP1);
			test.log(LogStatus.PASS, "PP1 is entered: "+PP1);
			driver.findElement(By.name("phoneNbr2")).sendKeys(PP2);
			test.log(LogStatus.PASS, "PP2 is entered: "+PP2);
			driver.findElement(By.name("phoneNbr3")).sendKeys(PP3);
			test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
			//driver.findElement(By.name("phoneNbr3"))(PP3);
			//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
			Select PhoneType  = new Select(driver.findElement(By.name("customerBean.phoneCd")));
			PhoneType.selectByVisibleText(Ctc_PhoneType);
			test.log(LogStatus.PASS, "Phone Type is selected as:"+Ctc_PhoneType);
			driver.findElement(By.name("sphoneNbr1")).sendKeys(PP1);
			test.log(LogStatus.PASS, "SPP1 is entered: "+PP1);
			driver.findElement(By.name("sphoneNbr2")).sendKeys(PP1);
			test.log(LogStatus.PASS, "SPP2 is entered: "+PP1);
			driver.findElement(By.name("sphoneNbr3")).sendKeys(PP3);
			test.log(LogStatus.PASS, "SPP3 is entered: "+PP3);
			//driver.findElement(By.name("phoneNbr3"))(PP3);
			//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
			Select SubPhoneType  = new Select(driver.findElement(By.name("customerBean.cphoneCd")));
			SubPhoneType.selectByVisibleText(Ctc_PhoneType);
			test.log(LogStatus.PASS, "Secondary Phone Type is selected as:"+Ctc_PhoneType);
			driver.findElement(By.name("customerBean.isCustomerEmailQuest")).click();
			test.log(LogStatus.PASS, "Does not have e-mail selected");
			driver.findElement(By.name("customerBean.driversLicNbr")).sendKeys(Misc_PhotoIDNbr);
			test.log(LogStatus.PASS, "PhotoIDNbr is entered:"+Misc_PhotoIDNbr);
			driver.findElement(By.name("customerBean.driversLicSt")).sendKeys(State);
			test.log(LogStatus.PASS, "ID State is entered: "+State);
			driver.findElement(By.name("dlexpiry1")).sendKeys(IDExpD1);
			test.log(LogStatus.PASS, "ID Expiration Date1 is entered:"+IDExpD1);
			driver.findElement(By.name("dlexpiry2")).sendKeys(IDExpD2);
			test.log(LogStatus.PASS, "ID Expiration Date1 is entered:"+IDExpD2);
			driver.findElement(By.name("dlexpiry3")).sendKeys(IDExpD3);
			test.log(LogStatus.PASS, "ID Expiration Date1 is entered:"+IDExpD3);
			driver.findElement(By.name("customerBean.photoIdType")).sendKeys(Misc_PhotoIDType);
			test.log(LogStatus.PASS, "PhotoIDType is entered:"+Misc_PhotoIDType);
			driver.findElement(By.name("customerBean.drivingZipcode")).sendKeys(ZipCode);
			test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
			driver.findElement(By.name("dob1")).sendKeys(DOB1);
			test.log(LogStatus.PASS, "DOB1 Date1 is entered: "+DOB1);
			driver.findElement(By.name("dob2")).sendKeys(DOB2);
			test.log(LogStatus.PASS, "DOB3 is entered: "+DOB2);
			driver.findElement(By.name("dob3")).sendKeys(DOB3);
			test.log(LogStatus.PASS, "DOB3 is entered: "+DOB3);
			//driver.findElement(By.name("PhoneNbr2")).sendKeys(PP3);
			driver.findElement(By.name("customerBean.incomeCdDisp")).sendKeys(Income_IncomeType);
			test.log(LogStatus.PASS, "IncomeType is entered:"+Income_IncomeType);
			driver.findElement(By.name("customerBean.empNmDisp")).sendKeys(Income_Employer);
			test.log(LogStatus.PASS, "Employer is entered: "+Income_Employer);
			driver.findElement(By.name("workPhoneNbrDisp1")).sendKeys(IncomeP1);
			test.log(LogStatus.PASS, "PP1 is entered: "+IncomeP1);
			driver.findElement(By.name("workPhoneNbrDisp2")).sendKeys(IncomeP2);
			test.log(LogStatus.PASS, "PP2 is entered: "+IncomeP2);
			driver.findElement(By.name("workPhoneNbrDisp3")).sendKeys(IncomeP3);
			test.log(LogStatus.PASS, "PP3 is entered: "+IncomeP3);
			driver.findElement(By.name("customerBean.incomeAmtDisp")).sendKeys(Income_NetIncomeAmt);
			test.log(LogStatus.PASS, "Income_NetIncomeAmt is entered:"+Income_NetIncomeAmt);
			driver.findElement(By.name("customerBean.grossAmtDisp")).sendKeys(Income_GrossIncome);
			test.log(LogStatus.PASS, "Income_GrossIncome is entered:"+Income_GrossIncome);
			driver.findElement(By.name("customerBean.payFreqCdDisp")).sendKeys(Income_PayFrequency);
			test.log(LogStatus.PASS, "Income_PayFrequency is entered:"+Income_PayFrequency);
			Thread.sleep(2000);
	/*		if(Income_PayFrequency.equals("Semi-Monthly"))
			{
				driver.findElement(By.id("rad_semi1")).click();
				test.log(LogStatus.PASS, "The 1st and 16th day of each month is selected");
			}
			if(Income_PayFrequency.equals("Bi-Weekly"))
			{
				driver.findElement(By.id("rad_wk4")).click();
				test.log(LogStatus.PASS, "Wednesday is selected");
				driver.findElement(By.id("biwksndid")).click();
				test.log(LogStatus.PASS, "Which day is your next Pay date?is selected as last date radio button");
			}*/
			
			//////////////

	          if(Income_PayFrequency.equals("Monthly"))
	          
	          {

	           driver.findElement(By.name("customerBean.monthlyDate")).sendKeys("10th");

	              test.log(LogStatus.PASS, "Monthly PayFrequency Selected");
	          }



	          if (Income_PayFrequency.equals("Weekly"))
	          
	          {
	           driver.findElement(By.id("rad_wk2")).click();

	          test.log(LogStatus.PASS, "Weekly PayFrequency Selected");
	          
	          }                               
	          

	          if(Income_PayFrequency.equals("Semi-Monthly"))

	          {

	                  driver.findElement(By.id("rad_semi1")).click();

	                  test.log(LogStatus.PASS, "SemiMonthly PayFrequency Selected");

	          }


	          if(Income_PayFrequency.equals("Bi-Weekly"))

	          {

	                  driver.findElement(By.id("rad_wk4")).click();

	                  test.log(LogStatus.PASS, "Wednesday is selected");

	                  driver.findElement(By.id("biwksndid")).click();

	                  test.log(LogStatus.PASS, "BiWeekly PayFrequency Selected");

	          }
			
			/////////////
			driver.switchTo().defaultContent();
			driver.switchTo().frame("bottom");
			String  BusinessDt=
					driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
			String Busdate[]=BusinessDt.split(":");
			String date = Busdate[1];

			Date d1 = df.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			cal.add(Calendar.DATE, -10);
			Date PayStubReviewedDate1= cal.getTime();

			String PayStubReviewedDate =df.format(PayStubReviewedDate1);
			//Date D=Add(date1,7);
			//System.out.println(date);

			//System.out.println(PayStubReviewedDate);

			String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");
			String PayStubReviewedDate2 = PayStubReviewedDate0[0];
			String PayStubReviewedDate3 = PayStubReviewedDate0[1];
			String PayStubReviewedDate4 = PayStubReviewedDate0[2];
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("payStubReviewed1")).sendKeys(PayStubReviewedDate2);
			test.log(LogStatus.PASS, "PayStubReviewed1 is entered:"+PayStubReviewedDate2);
			driver.findElement(By.name("payStubReviewed2")).sendKeys(PayStubReviewedDate3);
			test.log(LogStatus.PASS, "PayStubReviewed2 is entered:"+PayStubReviewedDate3);
			driver.findElement(By.name("payStubReviewed3")).sendKeys(PayStubReviewedDate4);
			test.log(LogStatus.PASS, "PayStubReviewed3 is entered:"+PayStubReviewedDate4);

			cal.add(Calendar.DATE, -10);
			Date PayStubDate1= cal.getTime();

			String PayStubDate =df.format(PayStubDate1);
			//Date D=Add(date1,7);
			//System.out.println(date);

			//System.out.println(PayStubReviewedDate);

			String PayStubDate0[] =PayStubDate.split("/");
			String PayStubDate2 = PayStubDate0[0];
			String PayStubDate3 = PayStubDate0[1];
			String PayStubDate4 = PayStubDate0[2];
			driver.findElement(By.name("payStubDate1")).sendKeys(PayStubDate2);
			test.log(LogStatus.PASS, "payStubDate1 is entered:"+PayStubDate2);
			driver.findElement(By.name("payStubDate2")).sendKeys(PayStubDate3);
			test.log(LogStatus.PASS, "payStubDate2 is entered:"+PayStubDate3);
			driver.findElement(By.name("payStubDate3")).sendKeys(PayStubDate4);
			test.log(LogStatus.PASS, "payStubDate3 is entered:"+PayStubDate4);

			String Income_HireDt0[] =Income_HireDt.split("/");
			String Income_HireDt1 = Income_HireDt0[0];
			String Income_HireDt2 = Income_HireDt0[1];
			String Income_HireDt3 = Income_HireDt0[2];

			driver.findElement(By.name("hireDate1")).sendKeys(Income_HireDt1);
			test.log(LogStatus.PASS, "hireDate1 is entered:"+Income_HireDt1);
			driver.findElement(By.name("hireDate2")).sendKeys(Income_HireDt2);
			test.log(LogStatus.PASS, "hireDate2 is entered:"+Income_HireDt2);
			driver.findElement(By.name("hireDate3")).sendKeys(Income_HireDt3);
			test.log(LogStatus.PASS, "hireDate3 is entered:"+Income_HireDt3);

			driver.findElement(By.name("customerBean.directDeposit")).sendKeys(Income_DirectDeposit);
			test.log(LogStatus.PASS, "DirectDeposit is entered:"+Income_DirectDeposit);
			cal.add(Calendar.DATE, -10);
			Date Bank_AcctVerificationDt0= cal.getTime();

			String Bank_AcctVerificationDt=df.format(Bank_AcctVerificationDt0);
			String Bank_AcctVerificationDt1[]=Bank_AcctVerificationDt.split("/");
			String Bank_AcctVerificationDt2 = Bank_AcctVerificationDt1[0];
			String Bank_AcctVerificationDt3 = Bank_AcctVerificationDt1[1];
			String Bank_AcctVerificationDt4 = Bank_AcctVerificationDt1[2];
			driver.findElement(By.name("statementEndDtDisp1")).sendKeys(Bank_AcctVerificationDt2);
			test.log(LogStatus.PASS, "Bank_AcctVerificationDt1 is entered:"+Bank_AcctVerificationDt2);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("statementEndDtDisp2")).sendKeys(Bank_AcctVerificationDt3);
			test.log(LogStatus.PASS, "Bank_AcctVerificationDt2 is entered:"+Bank_AcctVerificationDt3);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("statementEndDtDisp3")).sendKeys(Bank_AcctVerificationDt4);
			test.log(LogStatus.PASS, "Bank_AcctVerificationDt3 is entered:"+Bank_AcctVerificationDt4);

			//driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
			//driver.findElement(By.name("phoneNbr3"))(PP3);
			//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
			driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
			test.log(LogStatus.PASS, "Bank_ABARoutingNbr is entered:"+Bank_ABARoutingNbr);
			driver.findElement(By.name("checkAbaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
			test.log(LogStatus.PASS, "Confirm ABA/Routing Nbr is entered:"+Bank_ABARoutingNbr);
			driver.findElement(By.name("customerBean.accountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
			test.log(LogStatus.PASS, "Chkg Acct Nbr is entered:"+Bank_ChkgAcctNbr);
			driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
			test.log(LogStatus.PASS, "Confirm Chkg Acct Nbr is entered:"+Bank_ChkgAcctNbr);
			//driver.findElement(By.name("customerBean.drivingZipcode")).sendKeys(Bank_ChkgAcctNbr);
			//test.log(LogStatus.PASS, "drivingZipcode is entered:"+MiscZipCode);

			/*//Primary Reference Details


//driver.findElement(By.name("customerBean.contName")).sendKeys(PrimaryRef_LastName);
         //test.log(LogStatus.PASS, "PRLast Name is entered:
"+PrimaryRef_LastName);
     //
driver.findElement(By.name("customerBean.contactFirstName")).sendKeys(PrimaryRef_FirstName);
     //    test.log(LogStatus.PASS, "PRFirst Name is entered:
"+PrimaryRef_FirstName);

//driver.findElement(By.name("customerBean.contName")).sendKeys(PrimaryRef_LastName);
driver.findElement(By.name("customerBean.contactrelationDisp")).sendKeys(PrimaryRef_Relationship);
         test.log(LogStatus.PASS, "Contactrelation is entered:
"+PrimaryRef_Relationship);
driver.findElement(By.name("cphoneNbrDisp1")).sendKeys(PrimaryRef_PhoneNbr1);
         test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is
entered: "+PrimaryRef_PhoneNbr1);
driver.findElement(By.name("cphoneNbrDisp2")).sendKeys(PrimaryRef_PhoneNbr2);
         test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is
entered: "+PrimaryRef_PhoneNbr2);
driver.findElement(By.name("cphoneNbrDisp3")).sendKeys(PrimaryRef_PhoneNbr3);
         test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is
entered: "+PrimaryRef_PhoneNbr3);

     // Reference Details


driver.findElement(By.name("customerBean.nameDispSummary")).sendKeys(Ref_LastName);
         test.log(LogStatus.PASS, "RLast Name is entered:
"+Ref_LastName);
driver.findElement(By.name("customerBean.referenceFirstNameSummary")).sendKeys(Ref_FirstName);
         test.log(LogStatus.PASS, "RFirst Name is entered:
"+Ref_FirstName);

//driver.findElement(By.name("customerBean.contName")).sendKeys(Ref_LastName);
         //test.log(LogStatus.PASS, "PRLast Name is entered:
"+Ref_LastName);
driver.findElement(By.name("customerBean.relationDispSummary")).sendKeys(Ref_Relationship);
          test.log(LogStatus.PASS, "reference relation is entered:
"+Ref_Relationship);
driver.findElement(By.name("refPhoneNbr1")).sendKeys(Ref_PhoneNbr1);
          test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered:
"+Ref_PhoneNbr1);
driver.findElement(By.name("refPhoneNbr2")).sendKeys(Ref_PhoneNbr2);
          test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered:
"+Ref_PhoneNbr2);
driver.findElement(By.name("refPhoneNbr3")).sendKeys(Ref_PhoneNbr3);
          test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered:
"+Ref_PhoneNbr3);
driver.findElement(By.name("bt_Reference")).click();
          test.log(LogStatus.PASS, "Clicked on ADD Reference");
			 */
			driver.findElement(By.name("customerBean.bankrupty")).sendKeys(Bankruptcy);
			test.log(LogStatus.PASS, "Bankrupty is selected as:"+Bankruptcy);
			driver.findElement(By.name("SLoan")).click();
			test.log(LogStatus.PASS, "Clicked on Save&Loan");
			Thread.sleep(5000);

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

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(5000);

				if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				{
					test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);
				}
				else
				{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
				}


			}
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("topFrame");
			//driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			//*[@id="icons"]/li[7]/a
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't havethe alert.
			}
		}

	}

	//driver.switchTo().alert().accept();

	/*    WebDriver driver = new FirefoxDriver();
             JavascriptExecutor jse = (JavascriptExecutor) driver;
jse.executeScript("document.getElementById('elementid').focus();");*/
}




	


public void Apportions_List(String SSN,String FileName,int i) throws Exception

{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			//test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

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
			//driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("ILP"))
			{

				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
		
			if(ProductID.equals("ILP"))
			{
				//driver.findElement(By.name("button")).click(); 
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
				//*[@id="go_Button"]
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String LateFee=null;
			String OrgFeepaid = null;
			String Principle = null;
			String MonthlyMaintainance = null;
		
			
			LateFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[20]/td/span[2]")).getText();
			OrgFeepaid = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[33]/td/span[2]")).getText();
			
			test.log(LogStatus.PASS," LateFee ::"+LateFee);
			test.log(LogStatus.PASS," OrgFeepaid ::"+OrgFeepaid);
			
			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
			//*[@id="ContractScheduleTable"]/tbody/tr[2]/td[16]
            int schsize = options.size();
											
              for(int cnt=2; cnt<=i; cnt++)
                {   
            	  
            	  Principle = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[16]")).getText();
            	  MonthlyMaintainance = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[7]")).getText();
            	  String insamt = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[9]")).getText();
            	
            	  
                    test.log(LogStatus.PASS, "Principle:" +Principle);
                    test.log(LogStatus.PASS, "Monthly Maintainance:" +MonthlyMaintainance  );
                    test.log(LogStatus.PASS,  "insamt:"+insamt  );
                   // Schedules_count = Schedules_count+1;
                    
                }
             // test.log(LogStatus.PASS,  "Schedules Count:"+Schedules_count );
              

		}
	}
}



public void Check_TransactionRecords(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	int Schedules_count =0;
    int i;
    double totalorigfee1=0;
    int totdays1=0;
    double totalMHCFee1 =0;
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
		      String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);

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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String BalanceStatus=null;
			BalanceStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
			test.log(LogStatus.PASS,"Balance  status is ::"  +BalanceStatus);
			String LoanStatus=null;
			LoanStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
			test.log(LogStatus.PASS,"Loan  status is ::"  +LoanStatus);
			
			
		
		 
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[4]/table/tbody/tr[10]/td/span[2] total Inst
			List<WebElement> options = driver.findElements(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr"));
			int schsize = options.size();

			for(i=2; i<=schsize; i++)
			{
				List<WebElement> options1 = driver.findElements(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr["+i+"]/td"));
				int schsize1 = options1.size();
				for(int j=2; j<=schsize1; j++)
				{
				String field =	driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr["+i+"]/td["+j+"]")).getText();
					test.log(LogStatus.INFO, "Field is ::"+field);	
					//*[@id="transactionDetailsTable"]/tbody/tr[1]/td[1]
			
				}
				
			}
		  
			
		}
	}
}



public void installmentPayment(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";
	
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"Tender_Type",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Starts");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
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

	

			Thread.sleep(1000);
			if(ProductID.equals("ILP"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
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
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
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
			if(ProductID.equals("ILP"))
				
			{
				
				
				///driver.findElement(By.name("requestBean.paymentType")).click();
				driver.findElement(By.xpath("//*[@id='PD4']")).click();
				test.log(LogStatus.PASS, "Payment option is selected ");
				
			     Payment=driver.findElement(By.name("requestBean.siilBean.payAmt")).getAttribute("value");				     
			     
				test.log(LogStatus.PASS, "Capture Payment "+Payment);
				
				Thread.sleep(500);
				
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "Tender Type is selected Cash ");
				
				
				
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+Payment);
				
				Thread.sleep(500);
				
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();


				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
				}


			
		
			}
		}

	}
}


public void Bankrupt_VV(String SSN,String FileName) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		String Bankstatus = null;
		///////////////////////////////////////
		this.Login(UserName,Password,StoreID);

		Thread.sleep(5000);
		Thread.sleep(1000);
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


		if(ProductID.equals("ILP"))
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
		driver.switchTo().frame("main");	
	driver.findElement(By.name("transactionList")).sendKeys("History");
		
	if(ProductID.equals("ILP"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.id("go_Button")).click();  
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String DueDate=null;

	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[8]/td/span[2]")).getText();
		//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
		test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
		System.out.print(DueDate);	
		driver.close();

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
		String DDueDate[] =DueDate.split("/");


		Date DDueDateminus1 = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDateminus1);

		cal.add(Calendar.DATE, 10);

		Date DDueDate1= cal.getTime();

		DueDate =df.format(DDueDate1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		

		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(3000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 
	 
	 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		
	 
	 test.log(LogStatus.PASS,"Click on Go button");		 

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Discharge");
	 test.log(LogStatus.PASS, "select status as :" + BNKstatus); 
	 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status BNKPending is Saved");
	 
		 Thread.sleep(50000);
	
		 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
	 
	 driver.findElement(By.xpath("//*[@id='menu0']")).click();
	 test.log(LogStatus.PASS, "Select EDIT");
	 Thread.sleep(50000);
	 
	 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();
	 
	 test.log(LogStatus.PASS, "Click on GO");	 

	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
	 test.log(LogStatus.PASS, "select status as :" + BNKstatus); 
	 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status Discharged is Saved");
	 
		 Thread.sleep(50000); 
	 
		 
		
		 
		 
			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
}



public void EOD_BatchProcess_Default(String SSN,String FileName) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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

			//driver.findElement(By.name("button")).click();
			//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");

			for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


			//driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
			 
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();


			
int InstNum=0;
for(int cnt=2; cnt<=InstNum; cnt++)
{
			 


		//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[14]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]")).getText();
	
	
			// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
			test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



			}
			 
			Thread.sleep(1000);
			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

			test.log(LogStatus.PASS, "DueDate:" + DueDate);


			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

			System.out.print(DueDate);

			driver.close();

			driver = new InternetExplorerDriver();

			driver.get(AdminURL);

			//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(4000);

			//Thread.sleep(8000);

			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, 0);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];


			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.findElement(By.linkText("QA Jobs")).click();

			test.log(LogStatus.PASS, "Clicked on QA Jobs");

			Thread.sleep(5000);

			driver.findElement(By.linkText("Process Date Change")).click();

			test.log(LogStatus.PASS, "Clicked on Process Date Change");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

			driver.findElement(By.name("storeCode")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			Thread.sleep(5000);

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

			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

			{

				test.log(LogStatus.PASS, "Process Date updated successfully");

			}

			else

			{

				test.log(LogStatus.FAIL, "Process Date updated successfully.");

			}



			Thread.sleep(5000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("EOD Batch Process")).click();
			test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.INFO, "EOD Batch Process Completed");


		}

	}

}



public void Default_Payment_ILP(String SSN,String FileName,double PerAmt) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			 test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			
			//driver.findElement(By.name("button")).click();
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
			
				//driver.findElement(By.name("button")).click(); 
			

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			String PaymentAmount=null;
			
			PaymentAmount = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
			//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
			Thread.sleep(2000);
			
			double pmtamt1 = Double.parseDouble(PaymentAmount);
			double pmtamt2 = pmtamt1*PerAmt;
			test.log(LogStatus.PASS,pmtamt2+ ": is :"+PerAmt+": of Total Default Amount");
			
		String pmtamt3 =String.valueOf(pmtamt2);
			
		
		driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(ESign_TenderType);
		test.log(LogStatus.PASS, "Select the Tender Type");
		
		
		driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
		
		
			driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(pmtamt3);
			test.log(LogStatus.PASS, "Enter the Tender Amount");
			
	
			
			driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(pmtamt3);
			test.log(LogStatus.PASS, "Enter the Tender Amount");
			
			
			
			
			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");
			
			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on Finish Payment Button");
			Thread.sleep(5000);
			
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
			
			test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Successful");
		
		
		}
	}

}



public void Void_Default_Payment_ILP(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			String VoidTendertype = TestData.getCellData(sheetName,"VoidTendertype",row);			
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
			System.out.println(AdminURL);
			 test.log(LogStatus.INFO, "Default_Void_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			
			//driver.findElement(By.name("button")).click();
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
			//	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			
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
			
				//driver.findElement(By.name("button")).click(); 
			

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			Thread.sleep(5000);
			
			/*if(VoidTendertype.equals("Money Order"))
			{
				driver.findElement(By.name("requestBean.siilBean.ccmoNbrFirst")).sendKeys("2546");				
				test.log(LogStatus.PASS, "Selected Tendertype is Money Order");
			}
			
			if(VoidTendertype.equals("Cashiers Check"))
				
			{
				driver.findElement(By.name("requestBean.siilBean.ccmoNbrFirst")).sendKeys("2546");
				test.log(LogStatus.PASS, "Selected Tendertype is Cashiers Check ");			
							
			}	*/
					
			
			driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");
			
			driver.findElement(By.name("Submit33")).click();
			test.log(LogStatus.PASS, "Click on  Payment Void Button");
			Thread.sleep(5000);
			
			
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			
			driver.findElement(By.name("checkno")).click();
			test.log(LogStatus.PASS, "Click on  Payment Void Complted");		
			
		
		
		}
	}

}




public void AgeStore_ILP_DEF(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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
			//driver.findElement(By.name("button")).click();

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


		

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

		//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		
			//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]")).getText();
			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]
			                                       //*[@id="ContractScheduleTable"]/tbody/tr[3]/td[2]
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture Defdate"+DueDate);
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
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



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

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
			Actions actions1 = new Actions(driver);								        
			actions1.moveToElement(elements1).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
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




public void Default_Payment_EncryptionKey_Void(String SSN,String FileName)throws Exception{

    Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
    int lastrow=TestData.getLastRow("NewLoan");
    System.out.println("NewLoan "+lastrow);
    String sheetName="NewLoan";
    for(int row=2;row<=lastrow;row++)
    {
        String RegSSN = TestData.getCellData(sheetName,"SSN",row);
        if(SSN.equals(RegSSN))
        {
            String TxnType=TestData.getCellData(sheetName,"TxnType",row);
            String TenderType =TestData.getCellData(sheetName,"TenderType",row);
            String ProductID=TestData.getCellData(sheetName,"ProductID",row);
            String Password =TestData.getCellData(sheetName,"Password",row);
            String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
            String UserName =TestData.getCellData(sheetName,"UserName",row);
            String StoreId =TestData.getCellData(sheetName,"StoreID",row);
            this.Login(UserName,Password,StoreId);
            String SSN1 = SSN.substring(0, 3);
            String SSN2 = SSN.substring(3,5);
            String SSN3 = SSN.substring(5,9);
            String Eankey=null;
            Thread.sleep(4000);
            driver.switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
            driver.switchTo().frame("topFrame");
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
            driver.findElement(By.cssSelector("li[id='910000']")).click();
            test.log(LogStatus.PASS, "Clicked on Loan Transactions");
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
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

            if(ProductID.equals("ILP"))
            {
            	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

                //driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
            test.log(LogStatus.PASS, "Transaction Type is selected as Void");
            driver.findElement(By.xpath("//input[@value='Go' and  @type='button']")).click();

            //driver.findElement(By.id("go_Button")).click();
            for( String winHandle1 : driver.getWindowHandles())
            {
                driver.switchTo().window(winHandle1);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");

            String TranID =
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td")).getText();
            test.log(LogStatus.PASS, "TranId captured:"+TranID);
            String TranID0[] =TranID.split(":");
            String TranID1 = TranID0[0];
            String TranID2 =  TranID0[1];
            Thread.sleep(3000);
//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]")).click();
           ///html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]
            //driver.findElement(By.xpath("//input[@name='NO' and @type='button']")).click();
            test.log(LogStatus.PASS, "No button is clicked ");
//name="NO"
            driver.close();

            driver = new InternetExplorerDriver();
            driver.get(AdminURL);


            DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
            driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
            test.log(LogStatus.PASS, "Username is entered: admin");
            driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
            test.log(LogStatus.PASS, "Password is entered:"+Password);
            //Click Login Button
            driver.findElement(By.name("login")).click();
            test.log(LogStatus.PASS, "Clicked on Submit button");
            Thread.sleep(8000);

            driver.switchTo().defaultContent();
            driver.switchTo().frame("topFrame");
            driver.findElement(By.xpath("//*[contains(text(),'Employee')]")).click();
            test.log(LogStatus.PASS, "Clicked on Transactions");
            driver.manage().timeouts().implicitlyWait(120,
TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.manage().timeouts().implicitlyWait(60,
TimeUnit.SECONDS);
            driver.findElement(By.linkText("Encryption Key")).click();
            test.log(LogStatus.PASS, "Clicked on Encryption Key");
            driver.manage().timeouts().implicitlyWait(120,
TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.findElement(By.linkText("Encryption")).click();
            test.log(LogStatus.PASS, "Clicked on Encryption");
            Thread.sleep(5000);

            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");
            driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreId);
            test.log(LogStatus.PASS, "Store number Entered");

            driver.findElement(By.name("requestBean.tranNbr")).sendKeys(TranID2);
            test.log(LogStatus.PASS, "Tran number Entered");

     driver.findElement(By.name("trancd")).sendKeys("Advance-ADV");
            test.log(LogStatus.PASS, "Trancd selected");

         driver.findElement(By.name("trancd")).sendKeys("Early pay off-EPAY");
            test.log(LogStatus.PASS, "Trancd selected");

        
driver.findElement(By.name("trancd")).sendKeys("Payment-PAYIL");
            test.log(LogStatus.PASS, "Trancd selected");

            driver.findElement(By.name("trancd")).sendKeys("Default Payment-DFP");
            test.log(LogStatus.PASS, "Trancd selected");

            driver.findElement(By.name("GetKey")).click();
            test.log(LogStatus.PASS, "GetKey clicked");

            Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
            test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

            driver.close();
            driver = new InternetExplorerDriver();
            this.Login(UserName,Password,StoreId);
            Thread.sleep(4000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("topFrame");
            driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
            test.log(LogStatus.PASS, "Clicked on Loan Transactions");
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
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

            if(ProductID.equals("ILP"))
            {
            	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

                //driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
            test.log(LogStatus.PASS, "Transaction Type is selected as Void");
            driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

            //driver.findElement(By.id("go_Button")).click();
            Thread.sleep(5000);

            for( String winHandle1 : driver.getWindowHandles())
            {
                driver.switchTo().window(winHandle1);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");

//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
             driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[1]")).click();
            //driver.findElement(By.xpath("//input[@name='YES' and @type='button']")).click();
            test.log(LogStatus.PASS, "Yes Button clicked");

            if(ProductID.equals("ILP"))
            {
                //driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys("Cash");
                //test.log(LogStatus.PASS, "DisbType Type is entered as Cash");
                String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();

                System.out.println(Pmt);
                driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
                test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);


            	driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
                test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



            }

            if(ProductID.equals("ILP"))
            {
            	driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
                // Robot robot = new Robot();
                //    Thread.sleep(2000);
                //    robot.keyPress(KeyEvent.VK_F11);
                driver.findElement(By.name("Submit33")).click();
            //    robot.keyPress(KeyEvent.VK_F11);
                test.log(LogStatus.PASS, "Password is selected as "+Password);
                test.log(LogStatus.PASS, "Clicked on Finish Void DEF PAYMENT button ");
            }


            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();

            }
            catch (NoAlertPresentException e) {
            }
            for( String winHandle1 : driver.getWindowHandles())
            {
                driver.switchTo().window(winHandle1);
            }
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");
            if(ProductID.equals("ILP"))
            {

                if(driver.findElement(By.name("checkno")).isDisplayed())
                {
                    test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
                    driver.findElement(By.name("checkno")).click();
//driver.findElement(By.name("checkyes")).click();
                }
                else
                {
                    test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
                }
            }
        }
    }
    }





public void DefaultPmt_Void_ILP(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		

	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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
			//driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
/*///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			//driver.findElement(By.name("transactionList")).sendKeys("Void");
			
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
*/			
			if(ProductID.equals("ILP"))
            {
	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
	// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
Thread.sleep(5000);
driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys("Cash");
			test.log(LogStatus.PASS, "Disbursement type selected as Cash");
			driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password entered");
			driver.findElement(By.name("Submit33")).click();
			test.log(LogStatus.PASS, "Click on finish void button");
		}
	}
}



public void EOD_BatchProcess_WO(String SSN,String FileName,int days) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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

			//driver.findElement(By.name("button")).click();
			//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");

			for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


			//driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
			 
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();


			int InstNum = 2;
for(int cnt=2; cnt<=InstNum; cnt++)
{
			 


			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[14]/td/span[2]")).getText();

			// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
			test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



			}
			 
			Thread.sleep(1000);
			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

			test.log(LogStatus.PASS, "DueDate:" + DueDate);


			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

			System.out.print(DueDate);

			driver.close();

			driver = new InternetExplorerDriver();

			driver.get(AdminURL);

			//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(8000);

			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];


			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.findElement(By.linkText("QA Jobs")).click();

			test.log(LogStatus.PASS, "Clicked on QA Jobs");

			Thread.sleep(5000);

			driver.findElement(By.linkText("Process Date Change")).click();

			test.log(LogStatus.PASS, "Clicked on Process Date Change");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

			driver.findElement(By.name("storeCode")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			Thread.sleep(5000);

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

			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

			{

				test.log(LogStatus.PASS, "Process Date updated successfully");

			}

			else

			{

				test.log(LogStatus.FAIL, "Process Date updated successfully.");

			}



			Thread.sleep(5000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("EOD Batch Process")).click();
			test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.INFO, "EOD Batch Process Completed");


		}

	}
}



public void Writoff_RecoveryPartPmt_ILP(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String Bank_ChkgAcctNbr =TestData.getCellData(sheetName,"Bank_ChkgAcctNbr",row);
				String ESign_CheckNbr =TestData.getCellData(sheetName,"ESign_CheckNbr",row);

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


			
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
				driver.findElement(By.name("transactionList")).sendKeys("WO Recovery");
				
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.name("button")).click(); 
				

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//String PaymentAmount=null;
				if(TenderType.equals("Cash"))
				{
				PaymentAMT = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
				test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);

				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
				
				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("20");
				test.log(LogStatus.PASS, "Enter the Payment Amount");
				
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Select the Tender Type::");

				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("20");
				test.log(LogStatus.PASS, "Enter the Tender Amount");
				
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				}
				if(TenderType.equals("Cashiers Check"))
				{
				PaymentAMT = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
				test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);


				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
				
				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("20");
				test.log(LogStatus.PASS, "Enter the Payment Amount");
				
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Select the Tender Type::");

				driver.findElement(By.name("transactionDataBean.ccmoNbrFirst")).sendKeys(ESign_CheckNbr);
				test.log(LogStatus.PASS, "Banking Checking number entered is::"+ESign_CheckNbr);
				
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("20");
				test.log(LogStatus.PASS, "Enter the Tender Amount");
				
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				}
				
				if(TenderType.equals("Money Order"))
				{
				PaymentAMT = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
				test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);


				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
				
				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("20");
				test.log(LogStatus.PASS, "Enter the Payment Amount");
				
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Select the Tender Type::");

				driver.findElement(By.name("transactionDataBean.ccmoNbrFirst")).sendKeys(ESign_CheckNbr);
				test.log(LogStatus.PASS, "Banking Checking number entered is::"+ESign_CheckNbr);
				
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("20");
				test.log(LogStatus.PASS, "Enter the Tender Amount");
				
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				}
				test.log(LogStatus.PASS, "Write off Recovery completed Sucessfully");
				
				/* if(driver.findElement(By.name("Ok")).isDisplayed())
				 {
					 test.log(LogStatus.PASS, "Write off Recovery completed Sucessfully");
					 driver.findElement(By.name("Ok")).click();
				 }
						 
					 
					 else
					 {

				test.log(LogStatus.PASS, "Write off Recovery not completed Sucessfully");
					 }*/
			

			 
			}
			}
	}
			



public void Void_WO(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		

	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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
		//	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys("Cash");
			test.log(LogStatus.PASS, "Disbursement type entered as ::OriginalTender");
			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password entered");
			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on Void WO Recovery Payment Button");
			
		}
	}
}




public void AgeStore_AfterWO(String SSN,String FileName,int days) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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

			//driver.findElement(By.name("button")).click();
			//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");

			for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


			//driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
			 
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();

int InstNum =2 ;
			
for(int cnt=2; cnt<=InstNum; cnt++)
{
			 


		//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[14]/td/span[2]")).getText();
	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[11]/td/span[2]")).getText();

			// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
			test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



			}
			 
			Thread.sleep(1000);
			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

			test.log(LogStatus.PASS, "DueDate:" + DueDate);


			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

			System.out.print(DueDate);

			driver.close();

			driver = new InternetExplorerDriver();

			driver.get(AdminURL);

			//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(8000);

			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];


			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.findElement(By.linkText("QA Jobs")).click();

			test.log(LogStatus.PASS, "Clicked on QA Jobs");

			Thread.sleep(5000);

			driver.findElement(By.linkText("Process Date Change")).click();

			test.log(LogStatus.PASS, "Clicked on Process Date Change");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

			driver.findElement(By.name("storeCode")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			Thread.sleep(5000);

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

			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

			{

				test.log(LogStatus.PASS, "Process Date updated successfully");

			}

			else

			{

				test.log(LogStatus.FAIL, "Process Date not updated successfully.");

			}



			Thread.sleep(5000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("EOD Batch Process")).click();
			test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.INFO, "EOD Batch Process Completed");


		}

	}

}





public void EncryptionKey_Void_WORec(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);   	
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
			String Password = TestData.getCellData(sheetName,"Password",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String Eankey=null;
			Thread.sleep(4000);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
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

			
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.id("go_Button")).click();
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			String TranID = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td")).getText();
			test.log(LogStatus.PASS, "TranId captured:"+TranID);	
			String TranID0[] =TranID.split(":");
			String TranID1 = TranID0[0];
			String TranID2 =  TranID0[1]; 
            Thread.sleep(3000);
			//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
            driver.findElement(By.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]")).click();
           //                              /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]
            //driver.findElement(By.xpath("//input[@name='NO' and @type='button']")).click();
			test.log(LogStatus.PASS, "No button is clicked ");		
//name="NO"
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Employee')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Encryption Key")).click();
			test.log(LogStatus.PASS, "Clicked on Encryption Key");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("Encryption")).click();
			test.log(LogStatus.PASS, "Clicked on Encryption");
			Thread.sleep(5000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreId);
			test.log(LogStatus.PASS, "Store number Entered");	

			driver.findElement(By.name("requestBean.tranNbr")).sendKeys(TranID2);
			test.log(LogStatus.PASS, "Tran number Entered");	

/*			driver.findElement(By.name("trancd")).sendKeys("Advance-ADV");
			test.log(LogStatus.PASS, "Trancd selected");
			
			driver.findElement(By.name("trancd")).sendKeys("Early pay off-EPAY");
			test.log(LogStatus.PASS, "Trancd selected");

			driver.findElement(By.name("trancd")).sendKeys("Payment-PAYIL");
			test.log(LogStatus.PASS, "Trancd selected");*/
			
			driver.findElement(By.name("trancd")).sendKeys("Write Off Recovery-WOR");
			test.log(LogStatus.PASS, "Trancd selected");
			
			driver.findElement(By.name("GetKey")).click();
			test.log(LogStatus.PASS, "GetKey clicked");

			Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
			test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

			driver.close();
			driver = new InternetExplorerDriver();
			this.Login(UserName,Password,StoreId);	
			Thread.sleep(4000);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();		
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
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

		
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			
			
			test.log(LogStatus.PASS, "Click on GO Button"); 
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");					
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.id("go_Button")).click();
			Thread.sleep(5000);

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
			 driver.findElement(By.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[1]")).click();
			//driver.findElement(By.xpath("//input[@name='YES' and @type='button']")).click();
			test.log(LogStatus.PASS, "Yes Button clicked");

		
				driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
		/*		String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
				System.out.println(Pmt);
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);*/

				driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
				test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



			
			
			
				driver.findElement(By.name("password")).sendKeys(Password);
				// Robot robot = new Robot();
				//	Thread.sleep(2000);
				//	robot.keyPress(KeyEvent.VK_F11);
				driver.findElement(By.name("Submit22")).click();
			//	robot.keyPress(KeyEvent.VK_F11);
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
				


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();

			}
			catch (NoAlertPresentException e) {
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
		
				if(	driver.findElement(By.xpath("//input[ @type='button']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "WriteOff Recovery Void  is Completed Successfully ");
					driver.findElement(By.xpath("//input[@type='button']")).click();
					//driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "WriteOff Recovery Voidt is not Completed Successfully ");
				}
			
		}
	}
}
	




public void Payliance_OriginationFile_PPN(String SSN,String FileName,int Days,int instnum) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
	


	if(ProductID.equals("ILP"))
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
	driver.switchTo().frame("main");
	driver.findElement(By.name("transactionList")).sendKeys("History");
	if(ProductID.equals("ILP"))
	{

		driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		//driver.findElement(By.id("go_Button")).click();  
	}

	for( String winHandle1 : driver.getWindowHandles())
	{
		driver.switchTo().window(winHandle1);
	}			
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");
	String DueDate=null;

//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
	DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+instnum+"]/td[2]")).getText();
	                                          
	//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
	driver.findElement(By.linkText("Payliance")).click();
	test.log(LogStatus.PASS, "Clicked on Payliance");
	Thread.sleep(5000);
	driver.findElement(By.linkText("Payliance Origination File")).click();
	test.log(LogStatus.PASS, "Clicked on Payliance Origination File");
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
	driver.findElement(By.linkText("QA Jobs")).click();
	test.log(LogStatus.PASS, "Clicked on QA Jobs");
	Thread.sleep(5000);

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
	if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
	{									        								
		test.log(LogStatus.PASS, "Process NACHA file successfully.");
		driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
	}
	else
	{
		test.log(LogStatus.FAIL, "Process NACHA is not updated successfully.");
	}




}
}
}





public void ACH_Deposit_RPP_ILP(String SSN,String FileName,int Days,int instnum) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			//                                    //*[@id="PPNScheduleHistoryTable"]/tbody/tr[3]/td[2]
			DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+instnum+"]/td[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			                                          
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
			driver.findElement(By.linkText("Installment Loan")).click();
			test.log(LogStatus.PASS, "Clicked on Installment Loan");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process ILP Pre EPP ACH Deposits")).click();
			test.log(LogStatus.PASS, "Clicked on Process ILP Pre ACH Deposits");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);

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
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "LOC Pre ACH  RPP Deposit Process  successfully.");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process LOC Pre ACH RPP Deposit is not updated successfully.");
			}




		}
	}
}





public void EOD_BatchProcess_EPPDueDate(String SSN,String FileName,int days,int instnum) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);

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

			//driver.findElement(By.name("button")).click();
			//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");

			for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


			//driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
			 
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();

int InstNum =2;
			
for(int cnt=2; cnt<=InstNum; cnt++)
{
			 

			//                                    //*[@id="PPNScheduleHistoryTable"]/tbody/tr[3]/td[2]
 			DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+instnum+"]/td[2]")).getText();

//	DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();

			// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
			test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



			}
			 
			Thread.sleep(1000);
			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

			test.log(LogStatus.PASS, "DueDate:" + DueDate);


			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

			System.out.print(DueDate);

			driver.close();

			driver = new InternetExplorerDriver();

			driver.get(AdminURL);

			//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(8000);

			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];


			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.findElement(By.linkText("QA Jobs")).click();

			test.log(LogStatus.PASS, "Clicked on QA Jobs");

			Thread.sleep(5000);

			driver.findElement(By.linkText("Process Date Change")).click();

			test.log(LogStatus.PASS, "Clicked on Process Date Change");

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

			driver.findElement(By.name("storeCode")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			Thread.sleep(5000);

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

			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

			{

				test.log(LogStatus.PASS, "Process Date updated successfully");

			}

			else

			{

				test.log(LogStatus.FAIL, "Process Date updated successfully.");

			}



			Thread.sleep(5000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("EOD Batch Process")).click();
			test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.INFO, "EOD Batch Process Completed");


		}

	}

}



public void RPP_Payment_Void(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String Password = TestData.getCellData(sheetName,"Password",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String State = TestData.getCellData(sheetName,"StateID",row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
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
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				this.Login(UserName,Password,StoreID);	

				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
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
				    
				
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
					
						 driver.findElement(By.name("button")).click(); 
					 
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");

						 driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys("Cash");
						 test.log(LogStatus.PASS, "DisbursementType selected is ::Cash");

						 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
						 test.log(LogStatus.PASS, "Password entered is ::"+Password);
						 driver.findElement(By.name("Submit22")).click();
						 test.log(LogStatus.PASS, "Clicked on FinishPaymentPlan button ");
						 
						 
							
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
						 
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
							
							
							 if((driver.findElement(By.xpath("//*[@type='button' and @value='OK']"))).isDisplayed())
							 {
									test.log(LogStatus.INFO, "PaymentPlan Transaction with-SSN: " +SSN +" :: is Successful");
							 }
							
					
						
							else
							{
								test.log(LogStatus.PASS, "PaymentPlan Transaction is not Completed Successfully ");
							}
					    	
						 }
					
			}
			
		}



public void RCC_Revoke(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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


		if(ProductID.equals("ILP"))
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
		if(ProductID.equals("ILP"))
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
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
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
			//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
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

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

			driver.findElement(By.name("transactionList")).sendKeys("Payment Plan");
			test.log(LogStatus.PASS,"Payment Plan Selected From Transaction List");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			Thread.sleep(5000);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}


			Thread.sleep(5000);
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

			driver.findElement(By.name("collateralTypeId")).sendKeys("ACH");
			test.log(LogStatus.PASS,"Collateral Type is Selected as ACH");

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




public void AgeStore_EPP(String SSN,String FileName,int Days, int i) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

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


			if(ProductID.equals("ILP"))
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+i+"]/td[2]")).getText();
			//*[@id="PPNScheduleHistoryTable"]/tbody/tr[2]/td[2]
			//	DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[+i+]/td[4]")).getText();
			//*[@id="achHistoryTable"]/tbody/tr[2]/td[4]
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("mm/dd/yyyy");		
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
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



			//String DDueDate[] =DueDate.split("/");


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

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
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


public void Void_Afterallinstallment  (String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);

				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
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
				    
				 
				    if(ProductID.equals("ILP"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				    	// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
					 if(ProductID.equals("ILP"))
					 {
						  driver.findElement(By.name("button")).click();
					 }
						  
				
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						// driver.findElement(By.name("button")).click();
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("ILP"))
						 {
							 
							 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/select")).sendKeys("cash");
							 test.log(LogStatus.PASS, "Disb type is selected as "+ "Cash");	
							 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Password is selected as "+Password);	
							 driver.findElement(By.name("Submit33")).click();
							 test.log(LogStatus.PASS, "Clicked on Finish Void Paymentplan Payment button ");
							
							 
							 Thread.sleep(2000);							
								 
								 
								
									

							 
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
									Thread.sleep(2000);
									 Thread.sleep(2000);
								 if(driver.findElement(By.name("checkno")).isDisplayed())
									{
									 
									 driver.findElement(By.name("checkno")).click();
									 test.log(LogStatus.INFO, " void Payment with-SSN: " +SSN +" :: is Successful");
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
									}
							
					    	
						 }
					
			}
			
		}
		}





public void Discharge_AfterBANKRUPT(String SSN,String FileName,int instnum) throws Exception
{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);	
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
		String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
		String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
		String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		System.out.println(AdminURL);
		String Bankstatus = null;
		///////////////////////////////////////
		this.Login(UserName,Password,StoreID);

		Thread.sleep(5000);
		Thread.sleep(1000);
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


		if(ProductID.equals("ILP"))
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
		driver.switchTo().frame("main");
		driver.findElement(By.name("transactionList")).sendKeys("History");
		if(ProductID.equals("ILP"))
		{

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			//driver.findElement(By.id("go_Button")).click();  
		}

		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		String DueDate=null;
/*		DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+instnum+"]/td[2]")).getText();
*/
		DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
	//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
		//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
		test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
		System.out.print(DueDate);	
		driver.close();

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
		String DDueDate[] =DueDate.split("/");


		Date DDueDateminus1 = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDateminus1);

		cal.add(Calendar.DATE, 10);

		Date DDueDate1= cal.getTime();

		DueDate =df.format(DDueDate1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		
		////////////////////////////////////
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		driver.manage().window().maximize();
		 Thread.sleep(1000);
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);					   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");
 
 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

  for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
		} 
       driver.switchTo().defaultContent();
	    driver.switchTo().frame("mainFrame");
	    driver.switchTo().frame("main");		 
	    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveByOffset(200,100).perform();
		Thread.sleep(10000);
		action.click();
		Thread.sleep(3000);			
		
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 
	 
	 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();
	 
	 
	 test.log(LogStatus.PASS,"Click on Go button");	
	    driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 driver.findElement(By.name("menu")).sendKeys("Edit");
		 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	   driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Discharged");
	    test.log(LogStatus.PASS, "select status as  Discharged"); 
	 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);			
		

		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, "Status Discharged is Saved");
	 
		 Thread.sleep(50000);
	//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
		 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
		 
		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Discharged");
		 
			driver.close();

			driver = new InternetExplorerDriver();
			
		}

	}		 
	 
}



public void Deceased(String SSN,String FileName) throws Exception
{
Excel TestData = new
Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
int lastrow=TestData.getLastRow("NewLoan");
System.out.println("NewLoan "+lastrow);
String sheetName="NewLoan";
for(int row=2;row<=lastrow;row++)
{
    String RegSSN = TestData.getCellData(sheetName,"SSN",row);
    if(SSN.equals(RegSSN))
    {
        String TxnType=TestData.getCellData(sheetName,"TxnType",row);
        String TenderType =
TestData.getCellData(sheetName,"TenderType",row);
        String
ProductID=TestData.getCellData(sheetName,"ProductID",row);
        String UserName =
TestData.getCellData(sheetName,"UserName",row);
        String Password =
TestData.getCellData(sheetName,"Password",row);
        String StoreID = TestData.getCellData(sheetName,"StoreID",row);
        String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
        String
BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
        String AttorneyPhone =
TestData.getCellData(sheetName,"AttorneyPhone",row);
        String AttorneyP1 = AttorneyPhone.substring(0, 3);
        String AttorneyP2 = AttorneyPhone.substring(3, 6);
        String AttorneyP3 = AttorneyPhone.substring(6, 10);
        String SSN1 = SSN.substring(0, 3);
        String SSN2 = SSN.substring(3,5);
        String SSN3 = SSN.substring(5,9);
        System.out.println(AdminURL);
        ///////////////////////////////////////
        this.Login(UserName,Password,StoreID);

        Thread.sleep(5000);
        Thread.sleep(1000);
        driver.switchTo().frame("topFrame");
        driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
        test.log(LogStatus.PASS, "Clicked on Loan Transactions");
        driver.manage().timeouts().implicitlyWait(120,
TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.manage().timeouts().implicitlyWait(60,
TimeUnit.SECONDS);
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
        driver.findElement(By.xpath("//input[@value='Go' and @name='button']")).click();

        // driver.findElement(By.name("button")).click();
        test.log(LogStatus.PASS, "Click on GO Button");
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("main");
 


            driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

        test.log(LogStatus.PASS, "Click on GO Button");
        for( String winHandle1 : driver.getWindowHandles())
        {
            driver.switchTo().window(winHandle1);
        }
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("main");
driver.findElement(By.name("transactionList")).sendKeys("History");


            driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
            //driver.findElement(By.id("go_Button")).click();


        for( String winHandle1 : driver.getWindowHandles())
        {
            driver.switchTo().window(winHandle1);
        }
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("main");
        String DueDate=null;

/*    //    DueDate =
driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

         DueDate =
driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[2]/td[2]")).getText();*/
		DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();
	//	DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
        test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
        System.out.print(DueDate);
        driver.close();

        driver = new InternetExplorerDriver();
        driver.get(AdminURL);
        test.log(LogStatus.INFO, "Admin portal is launched");


        DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
        String DDueDate[] =DueDate.split("/");


        Date DDueDateminus1 = df.parse(DueDate);

        Calendar cal = Calendar.getInstance();

        cal.setTime(DDueDateminus1);

        cal.add(Calendar.DATE, -2);

        Date DDueDate1= cal.getTime();

        DueDate =df.format(DDueDate1);

        String DueDate0[] =DueDate.split("/");

        String DueDate1 = DueDate0[0];

        String DueDate2 = DueDate0[1];

        String DueDate3 = DueDate0[2];


        ////////////////////////////////////
        driver.get(AdminURL);
        test.log(LogStatus.INFO, "Admin portal is launched");
        driver.manage().window().maximize();
         Thread.sleep(1000);



driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(10000);
Thread.sleep(8000);
driver.switchTo().frame("topFrame");
WebDriverWait wait = new WebDriverWait(driver, 10000);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(10000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
Thread.sleep(10000);
wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Borrower")).click();
 test.log(LogStatus.PASS, "Clicked on Borrower");

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");

  for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
        }


       driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("main");
        driver.findElement(By.name("ssn1")).sendKeys(SSN1);
        test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
        driver.findElement(By.name("ssn2")).sendKeys(SSN2);
        test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
        driver.findElement(By.name("ssn3")).sendKeys(SSN3);
        test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
        Thread.sleep(5000);
        Actions action = new Actions(driver);
        action.moveByOffset(200,100).perform();
        Thread.sleep(10000);
        action.click();
        Thread.sleep(5000);

        driver.findElement(By.name("submit")).click();
        test.log(LogStatus.PASS, "Click on submit Button");



     driver.switchTo().defaultContent();
     driver.switchTo().frame("mainFrame");
     driver.switchTo().frame("main");


driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();

     test.log(LogStatus.PASS,"Click on Go button");

     driver.switchTo().defaultContent();
     driver.switchTo().frame("mainFrame");
     driver.switchTo().frame("main");
     Thread.sleep(3000);
     if( driver.findElement(By.name("loanCode")).isDisplayed())
     {
     driver.findElement(By.name("loanCode")).click();
     test.log(LogStatus.PASS, "Selecting Check box for loan");
     }
driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
     test.log(LogStatus.PASS, "select status as :: Deceased");

driver.findElement(By.name("bankruptcyFilingDate1")).sendKeys(DueDate1.trim());
        test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);
        Thread.sleep(500);
driver.findElement(By.name("bankruptcyFilingDate2")).sendKeys(DueDate2.trim());

        test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);
        Thread.sleep(500);
driver.findElement(By.name("bankruptcyFilingDate3")).sendKeys(DueDate3.trim());

        test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);

driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
         test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);

driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
         test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");


driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
     test.log(LogStatus.PASS, "Entered Attorny Name");


driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());
        test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);
        Thread.sleep(500);
driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());

        test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);
        Thread.sleep(500);
driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());

        test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);
        driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
		Thread.sleep(500);		    
		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
		Thread.sleep(500);			
		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
		test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);	

driver.findElement(By.name("bt_AddBankruptcy")).click();
         test.log(LogStatus.PASS, "Status Deceased is Saved");

         Thread.sleep(50000);
    ///html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
       /*  if(driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
         {
         test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Deceased");
         }
      */  /*    driver.close();

            driver = new InternetExplorerDriver();*/

        }

    }



if(driver.findElement(By.name("submitButton")).isDisplayed())
        {
         test.log(LogStatus.PASS, "Store Aging is Successfully ");
driver.findElement(By.name("submitButton")).click();
        }
     else
        {
            test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
        }
    //driver.close();
}





public void Check_NewLoan_ILP(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";
	for(int row=2;row<=lastrow;row++)
	{
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String State =TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);

			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			System.out.println(ProductID);
			String UserName =TestData.getCellData(sheetName,"UserName",row);
			String Password =TestData.getCellData(sheetName,"Password",row);
			String ProductType =TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			//String Term = TestData.getCellData(sheetName,"Term",row);
			String VehicleType=TestData.getCellData(sheetName,"VehicleType",row);
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			//System.out.println(Term);
			//String StoreId =
			TestData.getCellData(sheetName,"StoreID",row);
			//String stateProduct=State+" "+ProductID;
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType =TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_CourtesyCallConsent =TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion =TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference =TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks =TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr =TestData.getCellData(sheetName,"ESign_CheckNbr",row);
			String last4cheknum=ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			String Parent_Window = driver.getWindowHandle();
			System.out.println(last4cheknum);
			System.out.println(stateProductType);
			 String Parent_Window1 = driver.getWindowHandle();  
				System.out.println(ProductID);	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
		
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//*[@id="911100"]/a
				driver.findElement(By.cssSelector("li[id='911100']")).click();			
				test.log(LogStatus.PASS, "Clicked on New Loan");			
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



			test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
		
			//    Selection of Product based on the Name provided in
			//Test Data
			// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
		if(driver.findElement(By.xpath("//*[@id='errMsg']/ul/li")).getText().contains("Active"))
		{
			test.log(LogStatus.PASS, "Loan is unable to process");
		}
		}
	}

}

	


public void Active_Military_End(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
		driver=new InternetExplorerDriver();
		this.Login(UserName,Password,StoreID);
		String SSN1 = SSN.substring(0, 3);
		String SSN2 = SSN.substring(3,5);
		String SSN3 = SSN.substring(5,9);
		Thread.sleep(5000);
		driver.switchTo().frame("topFrame");
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
		Thread.sleep(1000);
        driver.findElement(By.cssSelector("li[id='900000']")).click();				
		test.log(LogStatus.PASS, "Clicked on Borrower");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='988190443']")));
		driver.findElement(By.cssSelector("li[id='988190443']")).click();			
		test.log(LogStatus.PASS, "Clicked on Active Military");	
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().frame("main");
		driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
		driver.findElement(By.name("ssn1")).sendKeys(SSN1);
		test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
		driver.findElement(By.name("ssn2")).sendKeys(SSN2);
		test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
		driver.findElement(By.name("ssn3")).sendKeys(SSN3);
		test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Click on submit Button");		
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//*[contains(text(),'Go')]")).click();			
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
	
		//Thread.sleep(1000);
		//driver.findElement(By.name("menu1")).sendKeys("Active Military");
		driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
		//driver.findElement(By.xpath("//html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input]")).click();
		///html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input
		test.log(LogStatus.PASS, "Click on GO Button");
		
		try { 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    //if alert present, accept and move on.														
			
		}
		catch (NoAlertPresentException e) {
		    //do what you normally would if you didn't have the alert.
		}
		test.log(LogStatus.PASS, "Accept the Alert");				
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
	
		Thread.sleep(2000);
		driver.findElement(By.name("finishBank")).click();
		
					
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='ok' and @type='button']")).click();
		//driver.findElement(By.xpath("///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input]")).click();
		///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input
		test.log(LogStatus.PASS, "Active Military Transaction Completed Successfully.");
	}
}
}




public void ACH_Deposit_ILP(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("ILP"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			                                          
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
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
			driver.findElement(By.linkText("Installment Loan")).click();
			test.log(LogStatus.PASS, "Clicked on Installment Loan");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process ILP Pre ACH Deposits")).click();
			test.log(LogStatus.PASS, "Clicked on Process ILP Pre ACH Deposits");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");*/
			Thread.sleep(5000);

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
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "LOC Pre ACH Deposit Process  successfully.");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process LOC Pre ACH Deposit is not updated successfully.");
			}




		}
	}
}


public void Deposit_Record_Verification(String SSN,String FileName) throws Exception

{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			//test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

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
			//driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("ILP"))
			{

				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
		
			if(ProductID.equals("ILP"))
			{
				//driver.findElement(By.name("button")).click(); 
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
				//*[@id="go_Button"]
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String Depositrec;
			
			Depositrec = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[5]/td[4]/font")).getText();
			
			if(Depositrec.contains("Deposit"))
			{
				test.log(LogStatus.PASS,"Deposit Record is Displayed" );
				
			}
			else
				test.log(LogStatus.PASS,"Deposit Record is not Displayed" );
			
			String OrgFeepaid = null;
			String Principle = null;
			String MonthlyMaintainance = null;
		
			
			String LateFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[20]/td/span[2]")).getText();
			OrgFeepaid = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[33]/td/span[2]")).getText();
			
			test.log(LogStatus.PASS," LateFee ::"+LateFee);
			test.log(LogStatus.PASS," OrgFeepaid ::"+OrgFeepaid);
			
			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
			//*[@id="ContractScheduleTable"]/tbody/tr[2]/td[16]
            int schsize = options.size();
            int i =2;
											
              for(int cnt=2; cnt<=i; cnt++)
                {   
            	  
            	  Principle = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[16]")).getText();
            	  MonthlyMaintainance = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[7]")).getText();
            	  String insamt = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[9]")).getText();
            	
            	  
                    test.log(LogStatus.PASS, "Principle:" +Principle);
                    test.log(LogStatus.PASS, "Monthly Maintainance:" +MonthlyMaintainance  );
                    test.log(LogStatus.PASS,  "insamt:"+insamt  );
                   // Schedules_count = Schedules_count+1;
                    
                }
             // test.log(LogStatus.PASS,  "Schedules Count:"+Schedules_count );
              

		}
	}
}




public void ACHRevoke(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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

			

			if(ProductID.equals("ILP"))

			{

			///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}

			// driver.findElement(By.name("button")).click();

			test.log(LogStatus.PASS, "Click on GO Button");

			for( String winHandle1 : driver.getWindowHandles())

			{

			driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("transactionList")).sendKeys("ACH Revoke");
			test.log(LogStatus.PASS, "ACH Revoke Transaction Selected");

			if(ProductID.equals("ILP"))

			{

			driver.findElement(By.id("go_Button")).click();

			}


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
         //   Thread.sleep(5000);
            // /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]
  /*          wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
	        driver.findElement(By.cssSelector("li[id='900000']")).click();	*/
            driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]")).click();
            //wait.until(ExpectedConditions.elementToBeClickable(By.name("bt_BankDetails")));
            driver.findElement(By.name("bt_BankDetails")).click();
            test.log(LogStatus.PASS, "Clicked on ACH Revoke Button");
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
////*[@id="totPart"]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table[2]/tbody/tr/td/input[1]
			driver.findElement(By.name("checkyes")).click();
			test.log(LogStatus.PASS, "ACH Revoke Transaction is Completed");
			

		}
	}
}














	public  void proc(String ReturnFormat) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		 
		 // Object of Statement. It is used to create a Statement to execute the query
		 Statement stmt = null;
		 
		 //Object of ResultSet => 'It maintains a cursor that points to the current row in the result set'
		ResultSet resultSet = null;
		 
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 System.out.println("before conn");
		
		 // Open a connection
		 try {

			 conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.242:1521:QFUNDUAT2","QFUNDX_LOCAL_AUTOMATION","QFUNDX_LOCAL_AUTOMATION");


	        } catch (SQLException e1) {

	            System.out.println("Connection Failed! Check output console"+e1);
	            e1.printStackTrace();
	        }
		 
		 // Execute a query
		 stmt = conn.createStatement();
		 System.out.println("after conn");
		 
		 try{
			 String A = null;
			//resultSet = stmt.executeQuery("select St_CODE,SAFE_code from ST_CM_SAFE");
			 
			 //resultSet = stmt.executeQuery("create or replace PROCEDURE PRC_QC_SSN_THREE_ILP  Is Begin            Delete  From   Bo_Phone Where Bo_Code In (Select Bo_Code From Bo_Master Where Ssn IN  ( select ssn from QC_SSN_THREE_ILP )); Delete  From   Bo_Other_Info Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP) );             Delete  From   Bo_Income Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN (select ssn from QC_SSN_THREE_ILP) );             Delete  From   Bo_Address Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP) );             Delete  From   Bo_Bank_Acnt Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP) );             Delete  From   Bo_Reference Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));             Delete  From   Bo_Notes Where Bo_Code In (Select Bo_Code  From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));             Delete  From   ACH_STAGING Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));              Delete  From   st_il_master Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));              Delete  From   bo_card_details Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));             Delete  From   REPAY_DEPOSIT_SCHEDULE Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));            Delete  From   REPAY_DATA_LOG Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));             Delete  From   Bo_Master Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP));              Delete FROM    st_daily_summary where trunc(business_date)>TO_DATE('05-DEC-2017','dd-mon-yy') and st_code in (1709); Delete FROM    ca_closing_history where trunc(business_date)>TO_DATE('05-DEC-2017','dd-mon-yy') and st_code in (1709);       Commit; End");
			 //resultSet = stmt.executeQuery("create or replace PROCEDURE PRC_QC_SSN_THREE_ILP  Is Begin            Delete  From   Bo_Phone Where Bo_Code In (Select Bo_Code From Bo_Master Where Ssn IN  ( select ssn from QC_SSN_THREE_ILP )) Delete  From   Bo_Other_Info Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP) )             Delete  From   Bo_Income Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN (select ssn from QC_SSN_THREE_ILP) )             Delete  From   Bo_Address Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP) )             Delete  From   Bo_Bank_Acnt Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP) )             Delete  From   Bo_Reference Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))             Delete  From   Bo_Notes Where Bo_Code In (Select Bo_Code  From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))             Delete  From   ACH_STAGING Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))              Delete  From   st_il_master Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))              Delete  From   bo_card_details Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))             Delete  From   REPAY_DEPOSIT_SCHEDULE Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))            Delete  From   REPAY_DATA_LOG Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))             Delete  From   Bo_Master Where Bo_Code In (Select Bo_Code   From Bo_Master Where Ssn IN  (select ssn from QC_SSN_THREE_ILP))              Delete FROM    st_daily_summary where trunc(business_date)>TO_DATE('05-DEC-2017','dd-mon-yy') and st_code in (1709) Delete FROM    ca_closing_history where trunc(business_date)>TO_DATE('05-DEC-2017','dd-mon-yy') and st_code in (1709)       Commit End");
			resultSet = stmt.executeQuery("select ACH_CODE,TRAN_ID from bank_ach_staging where Ach_CODE = 1893051");   
			 while (resultSet .next()) {
			 System.out.println(resultSet .getString(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) + "  "
			 + resultSet.getString(4) + "  " + resultSet.getString(5));
			 A = resultSet.getString(1);
			 System.out.println(A);
			 }
			 
			
			 
			
			// FileWriter fw = null;
			// String output=resultSet .getString(1);
			  // String content = "ert";
			 // String timestamp = null; 
			//String contents=content+timestamp+" - Login Pass";

              //fw = "E:\\AdminLogin.txt";

            //  fw =System.getProperty("user.dir")+"/AdminLogin.txt";

            //  appendUsingFileWriter(fw,content);
              
			
			//BufferedWriter bw = null;
			//FileWriter fw = null;
			 String content;

			String fw = "E:\\AdminLogin.txt";
			//String content = "\r\n";
			
			//content=A;
			//fw = "E:\\AdminLogin.txt";
			
			appendUsingFileWriter(fw,ReturnFormat);
			//appendUsingFileWriter(fw,"\r\n");
			//appendUsingFileWriter(fw,content);
			
			 //String str = content.readLine();
			
			 
fw.replaceAll("[0-9,a-z,A-Z]{17,21}", A);


			//fw.close();
			 
			 
			 System.out.println("after query");
			 
			 if (resultSet != null) {
			 try {
			 resultSet.close();
			 } catch (Exception e) {
			 }
			 }
			 
			 if (stmt != null) {
			 try {
			 stmt.close();
			 } catch (Exception e) {
			 }
			 }
			 
			 if (conn != null) {
			 try {
			 conn.close();
			 } catch (Exception e) {
			 }
			 }
		 }
		 catch (Exception e2) {

	            System.out.println(" console"+e2);
	            e2.printStackTrace();
	        }
		 
		
	
	}


	 private static void appendUsingFileWriter(String filePath, String text) {
	        File file = new File(filePath);
	        FileWriter fr = null;
	        try {
	            //Below constructor argument decides whether to append or override
	            fr = new FileWriter(file);
	            fr.write(text);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }


				// @Test (priority=59) //Leela - Completed

				 public void Loan_DFLT_WO_WORecPartPmtWithAllTenderTypes_() throws Exception {

				 	// Start test. Mention test script name
				 	String FileName= "AA_Loan_DFLT_WO_WORecPartPmtWithAllTenderTypes_TestData.xls";
				 	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
				 	int lastrow=TestData.getLastRow("NewLoan");
				 	String sheetName="NewLoan";   
				 	//int lastrow=TestData.getLastRow("Borrower");
				 	System.out.println(lastrow);
				 	for(int row=2;row<=lastrow;row++)
				 	{
				 		String RunFlag = TestData.getCellData(sheetName,"Run",row);
				 		//System.out.println(RunFlag);
				 	if(RunFlag.equals("Y"))
				 	{	
				 		//driver.get(appUrl);
				 		//test.log(LogStatus.INFO, "Application is launched");
				 		//driver.manage().window().maximize();
				 			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				 			String UserName = TestData.getCellData(sheetName,"UserName",row);
				 			String Password = TestData.getCellData(sheetName,"Password",row);
				 	       // System.out.println(Password);
				 	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				 	        String StateID = TestData.getCellData(sheetName,"StateID",row);
				 	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				 	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				 			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				 	        String Header = StateID+ "_" + ProductID;
				 	        //System.out.println(SSN);
				 	        test = reports.startTest(Header+"_S.No:62"+"_"+PayFrequency+"_"+CollateralType, "Laon_default_write off_writeoff recovery(partial amount)(with all possible tender types)");
				 	        appUrl = AppURL;

				 	       this.Login(UserName,Password,StoreId);			        
							BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
							Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
				 	       this.NewLoan_ILP(SSN, FileName, "100");

				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 0,2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				 	   
				 	
				 	        this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	    
				 	      //  this.History(SSN, FileName);
				 	       this.EPP_AfterDFLT_WO_EOD_BatchProcess_DueDate(SSN, FileName, 60, 2);
				 	     //   this.EOD_BatchProcess_WO(SSN, FileName, 60);
				 	       // this.History(SSN, FileName);

				 	        this.Writoff_RecoveryPartPmt_ILP(SSN, FileName);

				 	       // this.History(SSN, FileName);
				 	       	        
				 	       
				 	       		        		        	        	        
				 	}
				 	}
				 	

				}		 
				 
				// @Test (priority=60) //Leela - Completed

				 public void Loan_DFLT_WO_WORecFull_VoidWithAllTenderTypes() throws Exception {

				 	// Start test. Mention test script name
				 	String FileName= "AA_Loan_DFLT_WO_WORecFull_VoidWithAllTenderTypes_TestData.xls";
				 	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
				 	int lastrow=TestData.getLastRow("NewLoan");
				 	String sheetName="NewLoan";   
				 	//int lastrow=TestData.getLastRow("Borrower");
				 	System.out.println(lastrow);
				 	for(int row=2;row<=lastrow;row++)
				 	{
				 		String RunFlag = TestData.getCellData(sheetName,"Run",row);
				 		//System.out.println(RunFlag);
				 	if(RunFlag.equals("Y"))
				 	{	
				 		//driver.get(appUrl);
				 		//test.log(LogStatus.INFO, "Application is launched");
				 		//driver.manage().window().maximize();
				 			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				 			String UserName = TestData.getCellData(sheetName,"UserName",row);
				 			String Password = TestData.getCellData(sheetName,"Password",row);
				 	       // System.out.println(Password);
				 	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				 	        String StateID = TestData.getCellData(sheetName,"StateID",row);
				 	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				 	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				 			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				 	        String Header = StateID+ "_" + ProductID;
				 	        //System.out.println(SSN);
				 	        test = reports.startTest(Header+"_S.No:63"+"_"+PayFrequency+"_"+CollateralType, "Laon_default_write off_writeoff recovery(full amount)_Loan_DFLT_WO_WORecFull_VoidWithAllTenderTypes_Void(with all possible tender types)");
				 	        appUrl = AppURL;

				 	       this.Login(UserName,Password,StoreId);			        
							BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
							Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
				 	       this.NewLoan_ILP(SSN, FileName, "100");

				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 0,2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				 	   
				 	
				 	        this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	    
				 	      //  this.History(SSN, FileName);
				 	       this.EPP_AfterDFLT_WO_EOD_BatchProcess_DueDate(SSN, FileName, 60, 2);

				 	       // this.History(SSN, FileName);
				 	        this.Writoff_RecoveryFull_ILP(SSN, FileName);
				 	

				 	        this.Void_WO(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	       // this.History(SSN, FileName);
				 	       	        
				 	       
				 	       		        		        	        	        
				 	}
				 	}
				 	

				 		}
				 
				// @Test (priority=61)

				 public void Loan_DFLT_WO_WORecPartPmt_VoidNextDay() throws Exception {

				 	// Start test. Mention test script name
				 	String FileName= "AA_Loan_DFLT_WO_WORecPartPmt_VoidNextDay_TestData.xls";
				 	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
				 	int lastrow=TestData.getLastRow("NewLoan");
				 	String sheetName="NewLoan";   
				 	//int lastrow=TestData.getLastRow("Borrower");
				 	System.out.println(lastrow);
				 	for(int row=2;row<=lastrow;row++)
				 	{
				 		String RunFlag = TestData.getCellData(sheetName,"Run",row);
				 		//System.out.println(RunFlag);
				 	if(RunFlag.equals("Y"))
				 	{	
				 		//driver.get(appUrl);
				 		//test.log(LogStatus.INFO, "Application is launched");
				 		//driver.manage().window().maximize();
				 			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				 			String UserName = TestData.getCellData(sheetName,"UserName",row);
				 			String Password = TestData.getCellData(sheetName,"Password",row);
				 	       // System.out.println(Password);
				 	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				 	        String StateID = TestData.getCellData(sheetName,"StateID",row);
				 	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				 	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				 			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				 	        String Header = StateID+ "_" + ProductID;
				 	        //System.out.println(SSN);
				 	        test = reports.startTest(Header+"_S.No:64"+"_"+PayFrequency+"_"+CollateralType, "Laon_default_write off_writeoff recovery(partial amount)_void on next day(with all possible tender types)");
				 	        appUrl = AppURL;

				 	       this.Login(UserName,Password,StoreId);			        
							BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
							Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
				 	       this.NewLoan_ILP(SSN, FileName, "100");

				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 0,2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				 	   
				 	
				 	        this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	    
				 	      //  this.History(SSN, FileName);
				 	       this.EPP_AfterDFLT_WO_EOD_BatchProcess_DueDate(SSN, FileName, 60, 2);
				 	      //  this.History(SSN, FileName);

				 	        this.Writoff_RecoveryPartPmt_ILP(SSN, FileName);
				 	        this.AgeStore_AfterWO(SSN, FileName, 61);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	      //  this.History(SSN, FileName);
				 	       
				 	        this.EncryptionKey_Void_WORec(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);       
				 	       
				 	       		        		        	        	        
				 	}
				 	}
				 	

				 		}

		 
				// @Test (priority=62) //Leela

				 public void DFLTLoan_EPPConvert_PpayAllInst_() throws Exception {

				 	// Start test. Mention test script name
				 	String FileName= "AA_DFLTLoan_EPPConvert_PpayAllInst_TestData.xls";
				 	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
				 	int lastrow=TestData.getLastRow("NewLoan");
				 	String sheetName="NewLoan";   
				 	//int lastrow=TestData.getLastRow("Borrower");
				 	System.out.println(lastrow);
				 	for(int row=2;row<=lastrow;row++)
				 	{
				 		String RunFlag = TestData.getCellData(sheetName,"Run",row);
				 		//System.out.println(RunFlag);
				 	if(RunFlag.equals("Y"))
				 	{	
				 		//driver.get(appUrl);
				 		//test.log(LogStatus.INFO, "Application is launched");
				 		//driver.manage().window().maximize();
				 			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				 			String UserName = TestData.getCellData(sheetName,"UserName",row);
				 			String Password = TestData.getCellData(sheetName,"Password",row);
				 	       // System.out.println(Password);
				 	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				 	        String StateID = TestData.getCellData(sheetName,"StateID",row);
				 	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				 	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				 			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				 	        String Header = StateID+ "_" + ProductID;
				 	        //System.out.println(SSN);
				 	        test = reports.startTest(Header+"_S.No:65"+"_"+PayFrequency+"_"+CollateralType, "Pick anILP Loan which is default status _ Convert to EPP _ PPAY all installments accordingly before due date");
				 	        appUrl = AppURL;

				 	       this.Login(UserName,Password,StoreId);			        
							BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
							Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
				 	        this.NewLoan_ILP(SSN, FileName, "100");
				             this.EOD_BatchProcess_DueDate(SSN, FileName, 0,2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				 	        this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EPP_ILP(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 2);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 3);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 4);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 5);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 6);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);

				 	       		        		        	        	        
				 	}
				 	}

				}
				 
		 
				// @Test (priority=63)

				 public void DFLTLoan_EPPConvert_DepositAll_ClearAll_() throws Exception {

				 	// Start test. Mention test script name
				 	String FileName= "AA_DFLTLoan_EPPConvert_DepositAll_ClearAll_TestData.xls";
				 	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
				 	int lastrow=TestData.getLastRow("NewLoan");
				 	String sheetName="NewLoan";   
				 	//int lastrow=TestData.getLastRow("Borrower");
				 	System.out.println(lastrow);
				 	for(int row=2;row<=lastrow;row++)
				 	{
				 		String RunFlag = TestData.getCellData(sheetName,"Run",row);
				 		//System.out.println(RunFlag);
				 	if(RunFlag.equals("Y"))
				 	{	
				 		//driver.get(appUrl);
				 		//test.log(LogStatus.INFO, "Application is launched");
				 		//driver.manage().window().maximize();
				 			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				 			String UserName = TestData.getCellData(sheetName,"UserName",row);
				 			String Password = TestData.getCellData(sheetName,"Password",row);
				 	       // System.out.println(Password);
				 	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				 	        String StateID = TestData.getCellData(sheetName,"StateID",row);
				 	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				 	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				 			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				 	        String Header = StateID+ "_" + ProductID;
				 	        //System.out.println(SSN);
				 	        test = reports.startTest(Header+"_S.No:66"+"_"+PayFrequency+"_"+CollateralType, "Pick an ILP Loan which is default status _ Convert to EPP _ Deposit all installments on due date accordingly and clear them");
				 	        appUrl = AppURL;
				 	       this.Login(UserName,Password,StoreId);			        
							BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
							Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
				 	        this.NewLoan_ILP(SSN, FileName, "100");
				 	        
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 0,2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				 	        this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EPP_ILP(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);

				 	        this.AgeStore_RPP_ILP(SSN, FileName, -1, 2);     			
				 	        this.DrawerDeassign(SSN, FileName);
				 	        this.StatementGeneration_EODProcessing(SSN, FileName);
				 	        this.StoreInfo(SSN, FileName);
				 	        this.Safeassign(SSN, FileName);
				 	        this.Drawerassign(SSN, FileName);
				 	        this.Payliance_OriginationFile_PPN(SSN, FileName, -1,2);
				 	        this.ACH_Deposit_RPP_ILP(SSN, FileName, 0,2);
				 	        this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 8, 2);
				 	       // this.Check_TransactionRecords(SSN, FileName);

				 	        this.AgeStore_RPP_ILP(SSN, FileName, -1, 3);			
				 	        this.DrawerDeassign(SSN, FileName);
				 	        this.StatementGeneration_EODProcessing(SSN, FileName);
				 	        this.StoreInfo(SSN, FileName);
				 	        this.Safeassign(SSN, FileName);
				 	        this.Drawerassign(SSN, FileName);
				 	        this.Payliance_OriginationFile_PPN(SSN, FileName, -1,3);
				 	        this.ACH_Deposit_RPP_ILP(SSN, FileName, 0,3);
				 	        this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 8, 3);
				 	     //   this.Check_TransactionRecords(SSN, FileName);
				 	        
				 	        
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -1, 4); 			
				 	        this.DrawerDeassign(SSN, FileName);
				 	        this.StatementGeneration_EODProcessing(SSN, FileName);
				 	        this.StoreInfo(SSN, FileName);
				 	        this.Safeassign(SSN, FileName);
				 	        this.Drawerassign(SSN, FileName);
				 	        this.Payliance_OriginationFile_PPN(SSN, FileName, -1,4);
				 	        this.ACH_Deposit_RPP_ILP(SSN, FileName, 0,4);
				 	        this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 8, 4);
				 	       // this.Check_TransactionRecords(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	}
				 	}
				 	

				}

			//	 @Test (priority=64) //Leela - Last

				 public void DFLTLoan_EPPConvert_PpayAllInst_VoidLastInst_() throws Exception {

				 	// Start test. Mention test script name
				 	String FileName= "AA_DFLTLoan_EPPConvert_PpayAllInst_VoidLastInst_TestData.xls";
				 	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
				 	int lastrow=TestData.getLastRow("NewLoan");
				 	String sheetName="NewLoan";   
				 	//int lastrow=TestData.getLastRow("Borrower");
				 	System.out.println(lastrow);
				 	for(int row=2;row<=lastrow;row++)
				 	{
				 		String RunFlag = TestData.getCellData(sheetName,"Run",row);
				 		//System.out.println(RunFlag);
				 	if(RunFlag.equals("Y"))
				 	{	
				 		//driver.get(appUrl);
				 		//test.log(LogStatus.INFO, "Application is launched");
				 		//driver.manage().window().maximize();
				 			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				 			String UserName = TestData.getCellData(sheetName,"UserName",row);
				 			String Password = TestData.getCellData(sheetName,"Password",row);
				 	       // System.out.println(Password);
				 	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				 	        String StateID = TestData.getCellData(sheetName,"StateID",row);
				 	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				 	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				 			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				 	        String Header = StateID+ "_" + ProductID;
				 	        //System.out.println(SSN);
				 	        test = reports.startTest(Header+"_S.No:67"+"_"+PayFrequency+"_"+CollateralType, "Pick an ILP Loan which is default status_ Convert to EPP _ PPAY all installments accordingly before due date _void last installment payment");
				 	        appUrl = AppURL;

				 	       this.Login(UserName,Password,StoreId);			        
							BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
							Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
				 	        this.NewLoan_ILP(SSN, FileName, "100");
				             this.EOD_BatchProcess_DueDate(SSN, FileName, 0,2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				 	        this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.EPP_ILP(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 2);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        this.AgeStore_RPP_ILP(SSN, FileName, -4, 3);
				 	        this.RPP_Payment_ILP(SSN, FileName);
				 	        /*this.AgeStore_RPP_ILP(SSN, FileName, -4, 4);*/
				 	        /*this.RPP_Payment_ILP(SSN, FileName);*/
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	        this.RPP_Payment_Void(SSN, FileName);
				 	        this.Loan_Balance_Status(SSN, FileName);
				 	       		        		        	        	        
				 	}
				 	}
			}
				 
				 //Scenarios 68 to 75 were blocked
				
	
@Test (priority=67)

public void Pick_ILP_WriteOff_ConvertEpp_DepositAllinstOnduedate_Clear() throws Exception {
	
	// Start test. Mention test script name
	String FileName= "AA_Pick_ILP_WriteOff_ConvertEpp_DepositAllinstOnduedate_Clear_Testdata.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);  	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";

	//int lastrow=TestData.getLastRow("Borrower");
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
	if(RunFlag.equals("Y"))
	{	
		//driver.get(appUrl);
		//test.log(LogStatus.INFO, "Application is launched");
		//driver.manage().window().maximize();
			
		
		    String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
	        String StateID = TestData.getCellData(sheetName,"StateID",row);
	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
	        String Header = StateID+ "_" + ProductID;
	        //System.out.println(SSN);
	        test = reports.startTest(Header+"_S.No:80"+"_"+PayFrequency+"_"+CollateralType,"Pick anILP Loan which is writeoff status_Convert to EPP_PPAY all installments accordingly before due datePick an ILP Loan which is writeoff status_Convert to EPP_Deposit all installments on due date accordingly and clear them");
	        appUrl = AppURL;
	        this.Login(UserName,Password,StoreId);
	        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
	       Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
	        this.NewLoan_ILP(SSN, FileName, "100");
	        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
			this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
			this.Loan_Balance_Status(SSN, FileName);
		       this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 0, 2);
	 	        this.Loan_Balance_Status(SSN, FileName);
	 	       this.EOD_BatchProcess_DueDate_DFLT(SSN, FileName, 60, 2);
	 	      //  this.History(SSN, FileName);

            this.Loan_Balance_Status(SSN, FileName);
            this.EPP_ILP(SSN, FileName);
            this.Loan_Balance_Status(SSN, FileName);
            
            this.AgeStore_RPP_ILP(SSN, FileName, -1, 2);
            this.DrawerDeassign(SSN, FileName);
            this.StatementGeneration_EODProcessing(SSN, FileName);
            this.StoreInfo(SSN, FileName);
            this.Safeassign(SSN, FileName);
            this.Drawerassign(SSN, FileName);
            this.Payliance_OriginationFile_PPN(SSN, FileName, -1, 2);
            this.ACH_Deposit_RPP_ILP(SSN, FileName, 0, 2);
            this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 8, 2);
            this.Check_TransactionRecords(SSN, FileName);
            
            this.AgeStore_RPP_ILP(SSN, FileName, -1, 3);
            this.DrawerDeassign(SSN, FileName);
            this.StatementGeneration_EODProcessing(SSN, FileName);
            this.StoreInfo(SSN, FileName);
            this.Safeassign(SSN, FileName);
            this.Drawerassign(SSN, FileName);
            this.Payliance_OriginationFile_PPN(SSN, FileName, -1, 3);
            this.ACH_Deposit_RPP_ILP(SSN, FileName, 0, 3);
            this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 8, 3);
            this.Check_TransactionRecords(SSN, FileName);
            
            this.AgeStore_RPP_ILP(SSN, FileName, -1, 4);
            this.DrawerDeassign(SSN, FileName);
            this.StatementGeneration_EODProcessing(SSN, FileName);
            this.StoreInfo(SSN, FileName);
            this.Safeassign(SSN, FileName);
            this.Drawerassign(SSN, FileName);
            this.Payliance_OriginationFile_PPN(SSN, FileName, -1, 4);
            this.ACH_Deposit_RPP_ILP(SSN, FileName, 0, 4);
            this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 8, 4);
            this.Check_TransactionRecords(SSN, FileName);
           
            this.Loan_Balance_Status(SSN, FileName);
           
	}       
	}
	

}



//@Test (priority=68)

	public void Pick_ILP_WriteoffLoan_ConvertEPP_PPayAllInstallementBeforeDueDate_Void() throws Exception {
		
		// Start test. Mention test script name
		String FileName= "AA_Pick_ILP_WriteoffLoan_ConvertEPP_PPayAllInstallementBeforeDueDate_Void_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);  	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";

		//int lastrow=TestData.getLastRow("Borrower");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
				
			
			    String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       // System.out.println(Password);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"_S.No:81"+"_"+PayFrequency+"_"+CollateralType,"Pick anILP Loan which is writeoff status_Convert to EPP_PPAY all installments accordingly before due date");
		        appUrl = AppURL;
		        this.Login(UserName,Password,StoreId);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
		        this.NewLoan_ILP(SSN, FileName, "100");
		        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
				this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);				
	            this.EOD_BatchProcess_DueDate(SSN, FileName, 151, 2);
	            this.EPP_ILP(SSN, FileName);
	            this.AgeStore_RPP_ILP(SSN, FileName, -4, 2);
	            this.RPP_Payment_ILP(SSN, FileName);
	            this.AgeStore_RPP_ILP(SSN, FileName, -4, 3);
	            this.RPP_Payment_ILP(SSN, FileName);
	            this.AgeStore_RPP_ILP(SSN, FileName, -4, 4);
	            this.RPP_Payment_ILP(SSN, FileName);
	            this.AgeStore_RPP_ILP(SSN, FileName, -4, 5);
	            this.RPP_Payment_ILP(SSN, FileName);
	            this.Void_Afterallinstallment(SSN, FileName);
	            this.Loan_Balance_Status(SSN, FileName);
	            
		}       
		}
		

	}

//Scenarios Blocked from 82 to 89

//@Test (priority=69) //Leela - Completed

public void LonI_WO_PPN_1stInstpymt_VoidPymt_RunEODbatch35daysfromduedate() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_WO_PPN_1stInstpymt_VoidPymt_RunEODbatch35daysfromduedate_Testdata.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:90"+"_"+PayFrequency+"_"+CollateralType,"Pick an LOC Loan which is writeoff status_PPN=>1# paymnet _void _Run EOD batch process on 35 days from the due dateNOTE:In between 1#  due date to 35  days if 2# is there then that  amount should be missed payment))");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");		
			this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
			this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
			this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);
			this.EOD_BatchProcess_DueDate(SSN, FileName, 151, 2);
			//this.History(SSN, FileName);	
			//this.RCC_Revoke(SSN, FileName);
			this.RPP(SSN, FileName);
			this.AgeStore_EPP(SSN, FileName, 0, 2);
			this.RPP_Payment_ILP(SSN, FileName);
			this.Void_PaymentPlanPayment(SSN, FileName);
			this.AgeStore_EPP(SSN, FileName, 35, 2);
			this.EOD_BatchProcess_EPPDueDate(SSN, FileName, 0, 2);
			this.Loan_Balance_Status(SSN, FileName);

	      

		}
	}

}

//@Test (priority=70)

public void Loan_Bankrupt_Void_() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_Loan_Bankrupt_Void_TestData.xls";
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";   
	//int lastrow=TestData.getLastRow("Borrower");
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
	if(RunFlag.equals("Y"))
	{	
		//driver.get(appUrl);
		//test.log(LogStatus.INFO, "Application is launched");
		//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
	        String StateID = TestData.getCellData(sheetName,"StateID",row);
	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
	        String Header = StateID+ "_" + ProductID;
	        //System.out.println(SSN);
	        test = reports.startTest(Header+"_S.No:91"+"_"+PayFrequency+"_"+CollateralType, "Loan_Select Void Status from Status dropdown in Bankruptcy Deceased Suite pageEnter all mandatory fields and click on Save");
	        appUrl = AppURL;

	        this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
	        this.NewLoan_ILP(SSN, FileName, "100");
	        this.Bankrupt(SSN, FileName);
	        this.Bankrupt_Void(SSN, FileName);
	      // this.BankruptStatus(SSN, FileName);
	     
	       // this.History(SSN, FileName);
	       		        		        	        	        
	}
	}
	

		}



@Test (priority=73)//leela -Pending

		public void CO_ILP_Loan_first_payment_BNK_Second_deposit() throws Exception {
		
		
			// Start test. Mention test script name
			String FileName= "AA_CO_ILP_Loan_first_payment_BNK_Second_deposit_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName); 
			int lastrow=TestData.getLastRow("NewLoan");
			String sheetName="NewLoan";
			
			System.out.println(lastrow);
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
			        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
					String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

					test = reports.startTest(Header+"_S.No:95"+"_"+PayFrequency+"_"+CollateralType,"Loan>1 payment > mark customer to BNK>2 deposit");

			         Assert.assertTrue(true);
			         appUrl = AppURL;
			         this.Login(UserName,Password,StoreId);			        
						BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			         this.NewLoan_ILP(SSN, FileName, "100");
			         this.AgeStore(SSN, FileName, -4);
			         this.installmentPayment(SSN, FileName);
			         this.Bankrupt(SSN, FileName);
			        this.AgeStore_2ndInstallment(SSN, FileName,0);
			         this.DrawerDeassign(SSN, FileName);
			         this.StatementGeneration_EODProcessing(SSN, FileName);	
			         this.StoreInfo(SSN, FileName);
			         this.Safeassign(SSN, FileName);
			         this.Drawerassign(SSN, FileName);			         
			        
			 }
			
			}	
		}


@Test (priority=74) //Leela - Completed

public void LonI_Bnkrupt_Dismissed() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_Bnkrupt_Dismissed_Txn_TestData.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:96"+"_"+PayFrequency+"_"+CollateralType,"Loan>Select 'Bankruptcy' Status from Status dropdown in Bankruptcy/Deceased Suite page Enter all mandatory fields and click on Save. 2) Go to Edit bankrupt information >> Click on Go Button and select dismissed from drop down >> enter manditory fields and Clickon save.");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");
			this.Bankrupt(SSN, FileName);
			this.Loan_Balance_Status(SSN, FileName);
		//	this.Customer_Status(SSN, FileName);
			this.Dismissed_AfterBANKRUPT(SSN, FileName);
			this.Loan_Balance_Status(SSN, FileName);
			//this.Customer_Status(SSN, FileName);

		}
	}

}


@Test (priority=75)  //Leela - Completed

public void LonI_Bnkrupt_Deceased() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_Bnkrupt_Deceased_Txn_TestData.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:97"+"_"+PayFrequency+"_"+CollateralType,"1)Select 'Bankruptcy' Status from Status dropdown in Bankruptcy/Deceased Suite pageEnter all manatory fields and click on Save.2) Go to Edit bankrupt information >> Click on Go Button and select deaceased from drop down >> enter manditory fields andClick on save.");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");
			this.Bankrupt(SSN, FileName);
			this.Loan_Balance_Status(SSN, FileName);
			//this.Customer_Status(SSN, FileName);
			this.Deceased_AfterBANKRUPT(SSN, FileName);
			this.Loan_Balance_Status(SSN, FileName);
			//this.Customer_Status(SSN, FileName);

		}
	}

}

@Test (priority=76)  

public void LonI_Bnkrupt_Discharge() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_Bnkrupt_Discharge_Txn_TestData.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:98"+"_"+PayFrequency+"_"+CollateralType,"1)Select 'Bankruptcy' Status from Status dropdown in Bankruptcy/Deceased Suite page Enter all mandatory fields and click on Save.2) Go to Edit bankrupt information >> Click on Go Button and select Discharge from drop down >> enter manditory fields and Clickon save.");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");
			this.Bankrupt(SSN, FileName);
			this.Discharge_AfterBANKRUPT(SSN, FileName,3);
			this.Loan_Balance_Status(SSN, FileName);
			//this.Customer_Status(SSN, FileName);

		}
	}

}

@Test (priority=77) 

public void LonI_Deceased() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_Deceased_Txn_TestData.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:99"+"_"+PayFrequency+"_"+CollateralType,"Select 'deceased' Status from Status dropdown in Bankruptcy/Deceased Suite page Enter all mandatory fields and click on Save");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");
			this.Deceased(SSN, FileName);
			this.Loan_Balance_Status(SSN, FileName);
			//this.Customer_Status(SSN, FileName);
		}
	}

}

@Test (priority=78) //Leela - Completed

public void LonI_DFLT_PPN_BNK_1InstDeposit() throws Exception {

	// Start test. Mention test script name
	String FileName= "LonI_DFLT_PPN_BNK_1InstDeposit.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:100"+"_"+PayFrequency+"_"+CollateralType,"Loan>default>payment(15% of default payment)Run EOD batch process(default date+120 days)");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");			
			this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
			this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
			this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);				
			//this.History(SSN, FileName);
		//	this.RCC_Revoke(SSN, FileName);
			this.RPP(SSN, FileName);				
			this.Bankrupt(SSN, FileName);
			this.AgeStore_EPP(SSN, FileName, -1, 2);				
			this.DrawerDeassign(SSN, FileName);
			this.EODProcessing_with_recordsChecking(SSN, FileName);
	        this.StoreInfo(SSN, FileName);
	        this.Safeassign(SSN, FileName);
	        this.Drawerassign(SSN, FileName);
	      

		}
	}

}


@Test (priority=79) //Leela - Completed

public void Loan_1stpayment_activemilitary_2ndpayment_() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_Loan_1stpayment_activemilitary_2ndpayment_TestData.xls";
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";   
	//int lastrow=TestData.getLastRow("Borrower");
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
	if(RunFlag.equals("Y"))
	{	
		//driver.get(appUrl);
		//test.log(LogStatus.INFO, "Application is launched");
		//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
	        String StateID = TestData.getCellData(sheetName,"StateID",row);
	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
	        String Header = StateID+ "_" + ProductID;
	        //System.out.println(SSN);
	        test = reports.startTest(Header+"_S.No:101"+"_"+PayFrequency+"_"+CollateralType, "Loan_1st Inst payment_active military_2nd Inst payment");
	        appUrl = AppURL;

	        this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
	        this.NewLoan_ILP(SSN, FileName, "100");
	        this.AgeStore_ILP(SSN, FileName, -3, 2);
	        this.Payment_ILP(SSN, FileName);
	        this.Active_Military_Start(SSN, FileName);
	        
	        this.AgeStore_ILP(SSN, FileName, -3, 3);
	        this.Payment_ILP(SSN, FileName);
	       
	       // this.History(SSN, FileName, 0); 	        
	}
	}
	

		}

@Test (priority=80) //Leela - Completed

public void BorrowerRegistration_ActiveMilitary_NewLoanNotProcessed_() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_BorrowerRegistration_ActiveMilitary_NewLoanNotProcessed_TestData.xls";
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);  
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";   
	//int lastrow=TestData.getLastRow("Borrower");
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
	if(RunFlag.equals("Y"))
	{	
		//driver.get(appUrl);
		//test.log(LogStatus.INFO, "Application is launched");
		//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
	        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
	        String StateID = TestData.getCellData(sheetName,"StateID",row);
	        String SSN = TestData.getCellData(sheetName,"SSN",row);	
	        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
	        String Header = StateID+ "_" + ProductID;
	        //System.out.println(SSN);
	        test = reports.startTest(Header+"_S.No:102"+"_"+PayFrequency+"_"+CollateralType, "Loan_1st Inst payment_active military_2nd Inst payment");
	        appUrl = AppURL;

	        this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
	        this.Active_Military_Start(SSN, FileName);
	        this.Check_NewLoan_ILP(SSN, FileName);        
	}
	}
	

		}


//@Test (priority=0) //Leela - Completed (priority=82) 

public void LonI_IstInstPayment_ACTM_2ndInstDeposit_Verify_Record() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_IstInstPayment_ACTM_2ndInstDeposit_Verify_Record_Txn_TestData.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:104"+"_"+PayFrequency+"_"+CollateralType,"Loan_age the store date to after rescind period_select payment option from CSR dropdown_Payment screen should show the follwing options  1.Pay Off the balance 2.Pay Installment Am t 3.Pay any other Amt>select pay any other amt_enter Payment amount less than 1# amount");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");
			this.AgeStore_ILP(SSN, FileName, 0, 2);			
			this.Payment_Inst_Amount(SSN, FileName, 2);
			//this.Apportions_List(SSN, FileName, 2);
			this.Active_Military_Start(SSN, FileName);
			this.AgeStore_ILP(SSN, FileName, 0, 3);	
			this.DrawerDeassign(SSN, FileName);
	        this.StatementGeneration_EODProcessing(SSN, FileName);
	        this.StoreInfo(SSN, FileName);
	        this.Safeassign(SSN, FileName);
	        this.Drawerassign(SSN, FileName);
	        this.Payliance_OriginationFile(SSN, FileName, -1, 3);
	        this.ACH_Deposit_ILP(SSN, FileName, 0);				
			
		//	this.Deposit_Record_Verification(SSN, FileName);


		}
	}

}

@Test (priority=83) // last

public void LonI_IstInstPayment_ACTM_ACHRevoke_2ndInstDeposit_Verify_Record() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LonI_IstInstPayment_ACTM_ACHRevoke_2ndInstDeposit_Verify_Record_Txn_TestData.xls";		 
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CO_ILP/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";
	System.out.println(lastrow);
	for(int row=2;row<=lastrow;row++)
	{
		String RunFlag = TestData.getCellData(sheetName,"Run",row);
		//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			// System.out.println(Password);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String Header = StateID+ "_" + ProductID;      		        
			test = reports.startTest(Header+"_S.No:105"+"_"+PayFrequency+"_"+CollateralType,"Loan_age the store date to after rescind period_select payment option from CSR dropdown_Payment screen should show the follwing options  1.Pay Off the balance 2.Pay Installment Am t 3.Pay any other Amt>select pay any other amt_enter Payment amount less than 1# amount");
			appUrl = AppURL;

			this.Login(UserName,Password,StoreId);			        
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
			this.NewLoan_ILP(SSN, FileName, "100");
			this.AgeStore_ILP(SSN, FileName, 0, 2);			
			this.Payment_Inst_Amount(SSN, FileName, 2);
			//this.Apportions_List(SSN, FileName, 2);
			this.Active_Military_Start(SSN, FileName);
			this.ACHRevoke(SSN, FileName);
			this.AgeStore_ILP(SSN, FileName, 0, 3);	
			this.DrawerDeassign(SSN, FileName);
	        this.EODProcessing_with_recordsChecking(SSN, FileName);
	        this.StoreInfo(SSN, FileName);
	        this.Safeassign(SSN, FileName);
	        this.Drawerassign(SSN, FileName);		       			
			
		


		}
	}

}





@AfterMethod
public void getResult(ITestResult result) throws Exception{
	if(result.getStatus() == ITestResult.FAILURE){
		test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
		//We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 
		String screenshotPath = ExecuteScripts.getScreenhot(driver, result.getName());
		//To add it in the extent report 
		test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
	}else if(result.getStatus() == ITestResult.SKIP){
		test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	}else if(result.getStatus() == ITestResult.SUCCESS){
		test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}
	// reports.endTest(test);
	reports.flush();
	//Call close() at the very end of your session to clear all resources. 
	//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
	//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
	//Once this method is called, calling any Extent method will throw an error.
	//close() - To close all the operation
	//driver.quit();
	// ending test
	//endTest(logger) : It ends the current test and prepares to create HTML report
	// reports.endTest(test);
}	
@AfterTest
public void tearDown() {
	// Ending Test
	reports.endTest(test);

	// writing everything into HTML report
	reports.flush();
}

@AfterClass
public void quit() {
	// Closing browser
	driver.quit();

}

public void takeScreenShot(WebDriver driver, String filePath) {
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(scrFile, new File(filePath));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}

// *[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span