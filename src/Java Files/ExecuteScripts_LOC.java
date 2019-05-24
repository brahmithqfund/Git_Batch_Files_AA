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
//import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;

public class ExecuteScripts_LOC {
	
	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	//ExtentTest logger;

	/*@BeforeClass

	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();
				
		String filename="BorrowerRegistration_NewLoan_"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/BorrowerRegistration_NewLoan/"+filename, true);
		
	}*/

	
	//@BeforeTest
	@BeforeClass
	public void setup() throws IOException {	
		
		reports = new ExtentReports(System.getProperty("user.dir") +"/ExecutionReports/LOC/CriticalScenarios_"+timestamp+".html", true);
		reports.addSystemInfo("Browser Version","IE 11.0");
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		
	}
	
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 //TakesScreenshot ts = (TakesScreenshot) driver;
		 //File source = ts.getScreenshotAs(OutputType.FILE);	
		 
		 File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);		 
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/ExecutionReports/LOC/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }

	//@BeforeTest
	public void Login (String username,String password,String storenumber) {										
			//Launch URL
			driver.get(appUrl);
			test.log(LogStatus.INFO, "CSR Application is launched");
			driver.manage().window().maximize();
			String usenameId = "loginRequestBean.userId";
		    String passwordId = "loginRequestBean.password";
		    String StoreId = "loginRequestBean.locNbr";
		    String Login = "login";
		 
		   // String username= "CSR353";
		   // String password= "1234";
		   // String storenumber= "353";
		    
		  //Enter Username(Email)
	        //writeText(By.name(usenameId),username);
		    driver.findElement(By.name(usenameId)).sendKeys(username);
		    Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Username is entered: "+username);

	        //Enter Password
	        //writeText(By.name(passwordId), password);
		    driver.findElement(By.name(passwordId)).clear();
		    driver.findElement(By.name(passwordId)).sendKeys(password);
		    Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Password is entered: "+password);
	        
	        //writeText(By.name(StoreId), storenumber);
	        driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
	        Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
	        //Click Login Button
	        driver.findElement(By.name(Login)).click();
	        Assert.assertTrue(true);
	        test.log(LogStatus.PASS, "Clicked on Submit button");
	}
		//if(driver.findElement()
	
	public void RegistrationPage(String SSN,String FileName) throws Exception{
				
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   		
		int lastrow=TestData.getLastRow("Borrower_Registration");
		
		String sheetName="Borrower_Registration";		
		for(int row=2;row<=lastrow;row++)
		{		
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{	
				
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			//String UserName = TestData.getCellData(sheetName,"UserName",row);
			//String Password = TestData.getCellData(sheetName,"Password",row);
	       // System.out.println(Password);
	      //  String StoreId = TestData.getCellData(sheetName,"StoreID",row);
	       // String ProductID = TestData.getCellData(sheetName,"ProductID",row);
	       // String StateID = TestData.getCellData(sheetName,"StateID",row);
	       // String SSN = TestData.getCellData(sheetName,"SSN",row);	
	        //String Header = StateID+ "_" + ProductID;	      
	       // test = reports.startTest("BorrowerRegistration_"+Header, "Register a Borrower");	          
	       String LastName = TestData.getCellData(sheetName,"LastName",row);
	       String FirstName = TestData.getCellData(sheetName,"FirstName",row);
	       String AddressLine = TestData.getCellData(sheetName,"AddressLine",row);
	       String City = TestData.getCellData(sheetName,"City",row);
	       String State = TestData.getCellData(sheetName,"State",row);	      
	       String ZipCode = TestData.getCellData(sheetName,"ZipCode",row);
	       String MonthsAtAddress = TestData.getCellData(sheetName,"MonthsAtAddress",row);	     
	       String Bank_ABARoutingNbr = TestData.getCellData(sheetName,"Bank_ABARoutingNbr",row);
	       String Bank_ChkgAcctNbr = TestData.getCellData(sheetName,"Bank_ChkgAcctNbr",row);	       
	       String Ctc_PrimaryPhone = TestData.getCellData(sheetName,"Ctc_PrimaryPhone",row);
	       String Ctc_PhoneType = TestData.getCellData(sheetName,"Ctc_PhoneType",row);
	       String Misc_PhotoIDNbr = TestData.getCellData(sheetName,"Misc_PhotoIDNbr",row);
	       String Misc_IDExpDate = TestData.getCellData(sheetName,"Misc_IDExpDate",row);	   
	       String Misc_PhotoIDType = TestData.getCellData(sheetName,"Misc_PhotoIDType",row);
	       String BorrDOB = TestData.getCellData(sheetName,"Misc_DOB",row);
	       String Income_IncomeType = TestData.getCellData(sheetName,"Income_IncomeType",row);
	       String Income_Employer = TestData.getCellData(sheetName,"Income_Employer",row);
	       String Income_WorkPhone = TestData.getCellData(sheetName,"Income_WorkPhone",row);
	       String Income_NetIncomeAmt = TestData.getCellData(sheetName,"Income_NetIncomeAmt",row);
	       String Income_GrossIncome = TestData.getCellData(sheetName,"Income_GrossIncome",row);
	       String Income_PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
	       String Income_HireDt = TestData.getCellData(sheetName,"Income_HireDt",row);
	       String Income_DirectDeposit=TestData.getCellData(sheetName,"Income_DirectDeposit",row);	
	       String ProductType=TestData.getCellData(sheetName,"ProductType",row);
	      /* String PrimaryRef_LastName = TestData.getCellData(sheetName,"PrimaryRef_LastName",row);
	       String PrimaryRef_FirstName = TestData.getCellData(sheetName,"PrimaryRef_FirstName",row);
	       String PrimaryRef_Relationship = TestData.getCellData(sheetName,"PrimaryRef_Relationship",row);
	       String PrimaryRef_PhoneNbr=TestData.getCellData(sheetName,"PrimaryRef_PhoneNbr",row);
	       String Ref_LastName = TestData.getCellData(sheetName,"Ref_LastName",row);
	       String Ref_FirstName = TestData.getCellData(sheetName,"Ref_FirstName",row);
	       String Ref_Relationship = TestData.getCellData(sheetName,"Ref_Relationship",row);
	       String Ref_PhoneNbr=TestData.getCellData(sheetName,"Ref_PhoneNbr",row);*/	       
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
			      /*  String PrimaryRef_PhoneNbr1 = PrimaryRef_PhoneNbr.substring(0, 3);
			        String PrimaryRef_PhoneNbr2 = PrimaryRef_PhoneNbr.substring(3, 6);
			        String PrimaryRef_PhoneNbr3 = PrimaryRef_PhoneNbr.substring(6, 10);
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
			        appUrl = AppURL;
			      //  this.Login(UserName, Password, StoreId);
			        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			        Thread.sleep(5000);
			   // driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
			driver.findElement(By.name("customerBean.addressLn")).sendKeys(AddressLine);
			test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
			driver.findElement(By.name("customerBean.city")).sendKeys(City);
			test.log(LogStatus.PASS, "City is entered: "+City);
			driver.findElement(By.name("customerBean.stateCd")).sendKeys(State);
			test.log(LogStatus.PASS, "State is entered: "+State);
			driver.findElement(By.name("customerBean.postalCd")).sendKeys(ZipCode);
			test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
			driver.findElement(By.name("customerBean.sameMailAddress")).click();
			test.log(LogStatus.PASS, "Mailing address is selected as same as above");
			driver.findElement(By.name("customerBean.monthsAtAddress")).sendKeys(MonthsAtAddress);
			test.log(LogStatus.PASS, "MonthsAtAddress is entered: "+MonthsAtAddress);			
			driver.findElement(By.name("customerBean.rentOwnFlg")).sendKeys("Yes");
			test.log(LogStatus.PASS, "Own Residence?* is entered: Yes");
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
			test.log(LogStatus.PASS, "Phone Type is selected as: "+Ctc_PhoneType);
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
			test.log(LogStatus.PASS, "Secondary Phone Type is selected as: "+Ctc_PhoneType);
			driver.findElement(By.name("customerBean.isCustomerEmailQuest")).click();
			test.log(LogStatus.PASS, "Does not have e-mail selected");
			driver.findElement(By.name("customerBean.driversLicNbr")).sendKeys(Misc_PhotoIDNbr);
			test.log(LogStatus.PASS, "PhotoIDNbr is entered: "+Misc_PhotoIDNbr);
			driver.findElement(By.name("customerBean.driversLicSt")).sendKeys(State);
			test.log(LogStatus.PASS, "ID State is entered: "+State);
			driver.findElement(By.name("dlexpiry1")).sendKeys(IDExpD1);
			test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD1);
			driver.findElement(By.name("dlexpiry2")).sendKeys(IDExpD2);
			test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD2);
			driver.findElement(By.name("dlexpiry3")).sendKeys(IDExpD3);
			test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD3);
			driver.findElement(By.name("customerBean.photoIdType")).sendKeys(Misc_PhotoIDType);
			test.log(LogStatus.PASS, "PhotoIDType is entered: "+Misc_PhotoIDType);
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
			test.log(LogStatus.PASS, "IncomeType is entered: "+Income_IncomeType);
			driver.findElement(By.name("customerBean.empNmDisp")).sendKeys(Income_Employer);
			test.log(LogStatus.PASS, "Employer is entered: "+Income_Employer);
			driver.findElement(By.name("workPhoneNbrDisp1")).sendKeys(IncomeP1);
			test.log(LogStatus.PASS, "PP1 is entered: "+IncomeP1);
			driver.findElement(By.name("workPhoneNbrDisp2")).sendKeys(IncomeP2);
			test.log(LogStatus.PASS, "PP2 is entered: "+IncomeP2);
			driver.findElement(By.name("workPhoneNbrDisp3")).sendKeys(IncomeP3);
			test.log(LogStatus.PASS, "PP3 is entered: "+IncomeP3);
			driver.findElement(By.name("customerBean.incomeAmtDisp")).sendKeys(Income_NetIncomeAmt);
			test.log(LogStatus.PASS, "Income_NetIncomeAmt is entered: "+Income_NetIncomeAmt);
			driver.findElement(By.name("customerBean.grossAmtDisp")).sendKeys(Income_GrossIncome);
			test.log(LogStatus.PASS, "Income_GrossIncome is entered: "+Income_GrossIncome);
			driver.findElement(By.name("customerBean.payFreqCdDisp")).sendKeys(Income_PayFrequency);
			test.log(LogStatus.PASS, "Income_PayFrequency is entered: "+Income_PayFrequency);
			if(Income_PayFrequency.equals("Semi-Monthly"))
			{
				driver.findElement(By.id("rad_semi1")).click();
				test.log(LogStatus.PASS, "The 1st and 16th day of each month is selected");
			}
			if(Income_PayFrequency.equals("Bi-Weekly"))
			{
				driver.findElement(By.id("rad_wk4")).click();
				test.log(LogStatus.PASS, "Wednesday is selected");
				driver.findElement(By.id("biwksndid")).click();
				test.log(LogStatus.PASS, "Which day is your next Pay date? is selected as last date radio button");				
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("bottom");
			 String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
			 String Busdate[]=BusinessDt.split(":");
			 String date = Busdate[1];			
			 Date d1 = df.parse(date);
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(d1);
			 cal.add(Calendar.DATE, -20);
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
				test.log(LogStatus.PASS, "PayStubReviewed1 is entered: "+PayStubReviewedDate2);
				driver.findElement(By.name("payStubReviewed2")).sendKeys(PayStubReviewedDate3);
				test.log(LogStatus.PASS, "PayStubReviewed2 is entered: "+PayStubReviewedDate3);
				driver.findElement(By.name("payStubReviewed3")).sendKeys(PayStubReviewedDate4);
				test.log(LogStatus.PASS, "PayStubReviewed3 is entered: "+PayStubReviewedDate4);				
				cal.add(Calendar.DATE, -30);
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
					test.log(LogStatus.PASS, "payStubDate1 is entered: "+PayStubDate2);
					driver.findElement(By.name("payStubDate2")).sendKeys(PayStubDate3);
					test.log(LogStatus.PASS, "payStubDate2 is entered: "+PayStubDate3);
					driver.findElement(By.name("payStubDate3")).sendKeys(PayStubDate4);
					test.log(LogStatus.PASS, "payStubDate3 is entered: "+PayStubDate4);
					
					String Income_HireDt0[] =Income_HireDt.split("/");
			        String Income_HireDt1 = Income_HireDt0[0];
			        String Income_HireDt2 = Income_HireDt0[1];
			        String Income_HireDt3 = Income_HireDt0[2];
			        
			        driver.findElement(By.name("hireDate1")).sendKeys(Income_HireDt1);
					test.log(LogStatus.PASS, "hireDate1 is entered: "+Income_HireDt1);
					driver.findElement(By.name("hireDate2")).sendKeys(Income_HireDt2);
					test.log(LogStatus.PASS, "hireDate2 is entered: "+Income_HireDt2);
					driver.findElement(By.name("hireDate3")).sendKeys(Income_HireDt3);
					test.log(LogStatus.PASS, "hireDate3 is entered: "+Income_HireDt3);
		
			driver.findElement(By.name("customerBean.directDeposit")).sendKeys(Income_DirectDeposit);
			test.log(LogStatus.PASS, "DirectDeposit is entered: "+Income_DirectDeposit);
			cal.add(Calendar.DATE, -60);
			 Date Bank_AcctVerificationDt0= cal.getTime();
			 
			 String Bank_AcctVerificationDt =df.format(Bank_AcctVerificationDt0);
			 String Bank_AcctVerificationDt1[] =Bank_AcctVerificationDt.split("/");
		        String Bank_AcctVerificationDt2 = Bank_AcctVerificationDt1[0];
		        String Bank_AcctVerificationDt3 = Bank_AcctVerificationDt1[1];
		        String Bank_AcctVerificationDt4 = Bank_AcctVerificationDt1[2];
			driver.findElement(By.name("statementEndDtDisp1")).sendKeys(Bank_AcctVerificationDt2);
			test.log(LogStatus.PASS, "Bank_AcctVerificationDt1 is entered: "+Bank_AcctVerificationDt2);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("statementEndDtDisp2")).sendKeys(Bank_AcctVerificationDt3);
			test.log(LogStatus.PASS, "Bank_AcctVerificationDt2 is entered: "+Bank_AcctVerificationDt3);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("statementEndDtDisp3")).sendKeys(Bank_AcctVerificationDt4);
			test.log(LogStatus.PASS, "Bank_AcctVerificationDt3 is entered: "+Bank_AcctVerificationDt4);
			
			 //driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
			//driver.findElement(By.name("phoneNbr3"))(PP3);
			//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
			driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
			test.log(LogStatus.PASS, "Bank_ABARoutingNbr is entered: "+Bank_ABARoutingNbr);
			driver.findElement(By.name("checkAbaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
			test.log(LogStatus.PASS, "Confirm ABA/Routing Nbr is entered: "+Bank_ABARoutingNbr);
			driver.findElement(By.name("customerBean.accountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
			test.log(LogStatus.PASS, "Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
			driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
			test.log(LogStatus.PASS, "Confirm Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
			//driver.findElement(By.name("customerBean.drivingZipcode")).sendKeys(Bank_ChkgAcctNbr);
			//test.log(LogStatus.PASS, "drivingZipcode is entered: "+MiscZipCode);
			
			/*//Primary Reference Details
			
			
				//driver.findElement(By.name("customerBean.contName")).sendKeys(PrimaryRef_LastName);
				//test.log(LogStatus.PASS, "PRLast Name is entered: "+PrimaryRef_LastName);
			//	driver.findElement(By.name("customerBean.contactFirstName")).sendKeys(PrimaryRef_FirstName);
			//	test.log(LogStatus.PASS, "PRFirst Name is entered: "+PrimaryRef_FirstName);
			
			//driver.findElement(By.name("customerBean.contName")).sendKeys(PrimaryRef_LastName);
				driver.findElement(By.name("customerBean.contactrelationDisp")).sendKeys(PrimaryRef_Relationship);
				test.log(LogStatus.PASS, "Contactrelation is entered: "+PrimaryRef_Relationship);
				driver.findElement(By.name("cphoneNbrDisp1")).sendKeys(PrimaryRef_PhoneNbr1);
				test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is entered: "+PrimaryRef_PhoneNbr1);
				driver.findElement(By.name("cphoneNbrDisp2")).sendKeys(PrimaryRef_PhoneNbr2);
				test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is entered: "+PrimaryRef_PhoneNbr2);
				driver.findElement(By.name("cphoneNbrDisp3")).sendKeys(PrimaryRef_PhoneNbr3);
				test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is entered: "+PrimaryRef_PhoneNbr3);
			
			// Reference Details
				
				
				driver.findElement(By.name("customerBean.nameDispSummary")).sendKeys(Ref_LastName);
				test.log(LogStatus.PASS, "RLast Name is entered: "+Ref_LastName);
				driver.findElement(By.name("customerBean.referenceFirstNameSummary")).sendKeys(Ref_FirstName);
				test.log(LogStatus.PASS, "RFirst Name is entered: "+Ref_FirstName);
				
				//driver.findElement(By.name("customerBean.contName")).sendKeys(Ref_LastName);
				//test.log(LogStatus.PASS, "PRLast Name is entered: "+Ref_LastName);
				 driver.findElement(By.name("customerBean.relationDispSummary")).sendKeys(Ref_Relationship);
				 test.log(LogStatus.PASS, "reference relation is entered: "+Ref_Relationship);
				 driver.findElement(By.name("refPhoneNbr1")).sendKeys(Ref_PhoneNbr1);
				 test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered: "+Ref_PhoneNbr1);
				 driver.findElement(By.name("refPhoneNbr2")).sendKeys(Ref_PhoneNbr2);
				 test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered: "+Ref_PhoneNbr2);
				 driver.findElement(By.name("refPhoneNbr3")).sendKeys(Ref_PhoneNbr3);
				 test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered: "+Ref_PhoneNbr3);
				 driver.findElement(By.name("bt_Reference")).click();			
				 test.log(LogStatus.PASS, "Clicked on ADD Reference");
			*/
					 driver.findElement(By.name("customerBean.bankrupty")).sendKeys(Bankruptcy);
					 test.log(LogStatus.PASS, "Bankrupty is selected as: "+Bankruptcy);
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
					 
				
					 	if(driver.findElement(By.id("LoanButtonId")).isDisplayed())
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
						    //do what you normally would if you didn't have the alert.
						}
				}
		
		}	
		  
					//driver.switchTo().alert().accept();
					
				/*	WebDriver driver = new FirefoxDriver();
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("document.getElementById('elementid').focus();");*/
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
	public void NewLoan(String SSN,String FileName) throws Exception{
		
			
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{		
				String State = TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				System.out.println(ProductID);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
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
				/*this.Login(UserName,Password,StoreId);
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
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");*/
				 String Parent_Window = driver.getWindowHandle();  
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 //	Selection of Product based on the Name provided in Test Data
					 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
					 {
						 
						 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
					//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
						if(ProductID.equals("TLP"))							
						{					
						System.out.println("IN TLP");
						driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
						driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
						driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
						driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
						}												
						if(ProductName.equals("TNPAYDAY"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("TNPDL all coll"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("Tennessee"))
						{
							driver.findElement(By.xpath("//*[@id='termSel1']")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						if(ProductName.equals("Line of Credit"))
						{
							
							if(StoreID.equals("4330"))
							{
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							}
							if(StoreID.equals("4353"))
							{
								//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
								
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
							
							}
							if(StoreID.equals("1343"))
							{
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
								test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
							}
						}
						//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
					   /* WebElement htmltable=driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table"));
					    													//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table
					    
						List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
						
						int count=0;							
						 count=driver.findElements(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr")).size();	
						 									//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[1]
						 System.out.println("current row num "+count);	
						 System.out.println(" rows num "+ rows.size());
						for(int rnum=1;rnum<=count;rnum++)
						{
							System.out.println("current row num "+rnum);						
						List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
						
						System.out.println("columns Count "+columns.size());
							
						for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
						{					
							String product_name=columns.get(cnum).getText();						
							System.out.println(product_name);	
								
							if(product_name.equals(stateProduct))
							{
									
								if(ProductID.equals("PDL"))
								{					
									rnum=rnum+1;														
									driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[2]/input")).click();								
									
								}
							}
							if(stateProduct.equals("MO PDL"))
							{
									
								if(ProductID.equals("PDL"))
								{					
									rnum=rnum+1;														
									driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input")).click();														
								}
							}
							
								if(ProductID.equals("ILP")||ProductID.equals("TLP"))							
								{	
									
									System.out.println("IN ILP/TLP");
									String Pname=driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[2]")).getText();
									System.out.println("current row of table"+Pname);
									if(Pname.equals(stateProductType))
									{
										if(Term.equals("Term1"))
										{
										driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[5]/table/tbody/tr/td[2]/table[1]/tbody/tr[1]/td/b/input")).click();								
										}
										if(Term.equals("Term2"))
										{
											driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[5]/table/tbody/tr/td[2]/table[2]/tbody/tr[1]/td/b/input")).click();									
										}
									}																
									
								}																														
						}							 			
						}
						if(ProductID.equals("PDL"))
						{
							test.log(LogStatus.PASS, "Product selected as "+stateProduct);
						}
						if(ProductID.equals("ILP")||ProductID.equals("TLP"))
						{
							test.log(LogStatus.PASS, "Product selected as "+stateProductType+" Term Selected as "+Term);
						}*/
						driver.findElement(By.name("ShareScreenBtn")).click();						
						 test.log(LogStatus.PASS, "Clicked on Share Customer Summary");
					
						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}
						
						 driver.switchTo().defaultContent();	
						 Thread.sleep(3000);
						 WebDriverWait wait2 = new WebDriverWait(driver, 5000);	
						 wait2.until(ExpectedConditions.elementToBeClickable(By.name("confirmSummary")));
						 driver.findElement(By.name("confirmSummary")).click();
						 test.log(LogStatus.PASS, "Clicked on Yes Share Customer Summary screen");
						/* for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}
						 Thread.sleep(1000);*/
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
					if(ProductID.equals("PDL"))
					{	
					
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						if(!(ESign_LoanAmt.isEmpty()))
						{
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
						}
						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
						test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
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
							driver.findElement(By.xpath("//*[@id='allowCoupons']/td[3]/input")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							driver.findElement(By.xpath("//*[@id='coupon']/td[3]/div[1]/input")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
						}
						driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
						WebDriverWait wait = new WebDriverWait(driver, 1000);	
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
						driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
						driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
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
					for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						// driver.findElement(By.name("OKBut")).click();					
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("bdyLoad");
						if(driver.findElement(By.name("Ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("ILP"))
					{	
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_CourtesyCallConsent);
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
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
						}
						 WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
				 		String NumofInst=ele.getAttribute("value");
				 		//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
				 		System.out.println(NumofInst);
				 		int installments = Integer.parseInt(NumofInst);
				 		for(int i=0;i<installments;i++)
				 		{
				 			 Random rand = new Random();
				 			 int rand1 = rand.nextInt(100000);	
				 			 String chknum = Integer.toString(rand1);
				 			driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);
				 			
				 		}			 					 			
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
						//for( String winHandle1 : driver.getWindowHandles())
						//{
						   // driver.switchTo().window(winHandle1);
						//}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						 driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
						
					if(ProductID.equals("TLP"))
					{	
						String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
						String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
						String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
						String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
						//String VehicleGrade=TestData.getCellData(sheetName,"Vehicle Grade",row);
						String LicensePlateExp=TestData.getCellData(sheetName,"License Plate Expiry",row);
						String InsuranceCoverage=TestData.getCellData(sheetName,"Insurance Coverage",row);
						String PhoneNbr=TestData.getCellData(sheetName,"Phone Nbr",row);
						 String PhoneNbr1 = PhoneNbr.substring(0, 3);
				        String PhoneNbr2 = PhoneNbr.substring(3, 6);
				        String PhoneNbr3 = PhoneNbr.substring(6, 10);
				        String InsuranceCompany =TestData.getCellData(sheetName,"Insurance Company",row);
				        String InsuranceExpiryDate=TestData.getCellData(sheetName,"Insurance Expiry Date",row);
				        String PolicyNumber=TestData.getCellData(sheetName,"Policy Number",row);
				        String InsuranceExpiryDate0[] =InsuranceExpiryDate.split("/");
				        String InsuranceExpiryDate1 = InsuranceExpiryDate0[0];
				        String InsuranceExpiryDate2 = InsuranceExpiryDate0[1];
				        String InsuranceExpiryDate3 = InsuranceExpiryDate0[2];
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
					//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
					//	for( String winHandle1 : driver.getWindowHandles())
					//	{
					//	    driver.switchTo().window(winHandle1);
					//	}			
						// driver.switchTo().defaultContent();
						// driver.switchTo().frame("mainFrame");
						// driver.switchTo().frame("main");
						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
						driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
						driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
						driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
						driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
						driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
						driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
						driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
						driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
						driver.findElement(By.name("button2")).click();			
						driver.findElement(By.name("button2")).click();	
						//driver.findElement(By.name("button2")).click();	
						//driver.findElement(By.name("button2")).click();	
						test.log(LogStatus.PASS, "click on Update 2 button ");
						Thread.sleep(8000);
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						/*for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");*/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						driver.findElement(By.name("process")).click();
						//driver.findElement(By.name("process")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on process Loan button ");
						try { 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();
						    //if alert present, accept and move on.														
							
						}
						catch (NoAlertPresentException e) {
						    //do what you normally would if you didn't have the alert.
						}
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
						}
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						test.log(LogStatus.PASS, "Click on Finish Loan Button");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
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
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("LOC"))
					{
					
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
					//	driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
					//	test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
						
					//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
				 	}
					else
					{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
					}
			}
		}

	}
	
public void Void(String SSN,String FileName) throws Exception{
		
		
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 	
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
				Thread.sleep(4000);
				driver.switchTo().defaultContent();	
				WebDriverWait wait = new WebDriverWait(driver, 100);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
		        driver.findElement(By.cssSelector("li[id='910000']")).click();	
				/*driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();	*/		
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
				    
				    if(ProductID.equals("PDL"))
					 {
				    driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				    if(ProductID.equals("TLP"))
					 {
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
					 }
				    
				    Thread.sleep(5000);
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
						 if(ProductID.equals("PDL"))
						 {
						 driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
						 String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
						 System.out.println(Pmt);
							driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
						 }
						 if(ProductID.equals("TLP"))
						 {
						 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
						 }
						 if(ProductID.equals("PDL"))
						 {
							 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
							 driver.findElement(By.name("Submit23")).click();
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
							 test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
						 }
					    if(ProductID.equals("TLP"))
						 {
					    	driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					    	driver.findElement(By.name("finish")).click();
					    	test.log(LogStatus.PASS, "Password is selected as "+Password);																					
							test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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
							 if(ProductID.equals("TLP"))
							 {
						    	
							 if(driver.findElement(By.name("Ok")).isDisplayed())
								{
								 test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
									driver.findElement(By.name("Ok")).click();
								}
							 else
								{
									test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
								}
							 }
							 if(ProductID.equals("PDL"))
							 {
						    	
							if(driver.findElement(By.name("checkyes")).isDisplayed())
							{
								test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
								driver.findElement(By.name("checkyes")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
							}
						}
			}
			
		}
	}		
public void Closer(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
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

			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
			}

			Thread.sleep(1000);
			if(ProductID.equals("LOC"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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


				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("ok")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Closer Loan is Completed Successfully ");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Closer Loan is not Completed Successfully ");
				}


			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
				System.out.println(Pmt);
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("tenderType")).sendKeys(TenderType);
			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.name("Submit23")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("finish")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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
			if(ProductID.equals("TLP"))
			{


				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Closer Loan is Completed Successfully ");
					driver.findElement(By.name("Ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
				}
			}
			if(ProductID.equals("PDL"))
			{

				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
				}
			}
		}

	}
}
public void NewLoan_Draw(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			System.out.println(ProductID);
			//String UserName = TestData.getCellData(sheetName,"UserName",row);
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
			/*this.Login(UserName,Password,StoreId);
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
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");*/
			test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
			 String Parent_Window = driver.getWindowHandle();  
			for( String winHandle1 : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle1);
			}			
			 driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 //	Selection of Product based on the Name provided in Test Data
			 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
			 {
				 
				 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
			//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
				if(ProductID.equals("TLP"))							
				{					
				System.out.println("IN TLP");
				driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
				driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
				driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
				driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
				driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
				driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
				driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
				}												
				if(ProductName.equals("TNPAYDAY"))
				{
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
				}
				if(ProductName.equals("TNPDL all coll"))
				{
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
				}
				if(ProductName.equals("Tennessee"))
				{
					driver.findElement(By.xpath("//*[@id='termSel1']")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
				}
				if(ProductName.equals("Line of Credit"))
				{
					
					if(StoreID.equals("4353"))
					{
						//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					
					}
					
				}
				
				driver.findElement(By.name("ShareScreenBtn")).click();

				test.log(LogStatus.PASS, "ShareScreen Button clicked");

				for( String winHandle1 : driver.getWindowHandles())

				{

				if(!(winHandle1.equals(Parent_Window)))

				{

				driver.switchTo().window(winHandle1);

				Thread.sleep(2000);

				driver.findElement(By.name("confirmSummary")).click();

				test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");

				}

				}
				
				Thread.sleep(2000);
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
					Thread.sleep(5000);
				//	driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
				//	test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
					driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
					test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
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
						 
						 /*//String Instamt=driver.findElement(By.name("availableBalance")).getAttribute("value");
						//String amt=Instamt.substring(0, 1);
						//System.out.println(amt);
						int lamount=Integer.parseInt(amt);
						System.out.println(lamount);
						 int Loanamout=100*lamount;
						 System.out.println(Loanamout);
						 String amount=Integer.toString(Loanamout);
						 //String lamout =  
*/							 driver.findElement(By.name("loanAmt")).clear();
							
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
					
				//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
			 	}
				else
				{
				test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
				}
		}
	}

}
public void VoidDrawLoan(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
							 System.out.println(Pmt);
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
	
public void AgeStore(String SSN,String FileName,int i) throws Exception
{
	
	//Excel TestData = new Excel("LOC_Critical/TestData/"+FileName);
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			 Thread.sleep(2000);
			 //Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			    	 
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
					 String DueDate=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					
					DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(DueDate);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			    //driver.quit();//need to change to close
			    driver.quit();//Uncomment 
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
		      //Thread.sleep(8000);
		      Thread.sleep(2000);
			         //for(String winHandle : driver.getWindowHandles()){
						  //  driver.switchTo().window(winHandle);
							//}
						  //  driver.switchTo().defaultContent();
						  //  driver.switchTo().frame("mainFrame");
						    
						    
						    
						     //driver.switchTo().frame("main");
						     Date DDueDate = df.parse(DueDate);
							 Calendar cal = Calendar.getInstance();
							 cal.setTime(DDueDate);
							 cal.add(Calendar.DATE, i);
							  Date DDueDateminus1= cal.getTime();
							 
							// String DueDateminus1 =df.format(DDueDateminus1);
						    String DueDate0[] =DueDate.split("/");
					        String DueDate1 = DueDate0[0];
					        String DueDate2 = DueDate0[1];
					        String DueDate3 = DueDate0[2];

			    
					        WebDriverWait wait = new WebDriverWait(driver, 10);
			      				        
			         driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
				     driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					 test.log(LogStatus.PASS, "Clicked on Transactions");
					 //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					 Thread.sleep(2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					/*driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
					driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");
					Thread.sleep(2000);*/
						/*	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);							
							//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");*/
							/*WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
							Actions actions1 = new Actions(driver);
							actions1.moveToElement(elements1).build().perform();	*/	
				         	driver.findElement(By.linkText("QA Jobs")).click();
				         	test.log(LogStatus.PASS, "Clicked on QA Jobs");
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							driver.findElement(By.linkText("Process Date Change")).click();
							test.log(LogStatus.PASS, "Clicked on Process Date Change");
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");							 
							 driver.findElement(By.name("storeCode")).click();
							 Thread.sleep(2000);
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
						        Thread.sleep(2000);
						        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						        //Thread.sleep(1000);
						        Thread.sleep(2000);
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
	}
}

public void AgeStore_EOD(String SSN,String FileName) throws Exception
{
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");						
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 String DueDate=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					
							 DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
			   										
			   System.out.print(DueDate);	
			   // driver.close();
			   // Thread.sleep(5000);
			    /*for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}*/
			    driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
			    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			   //driver.quit();//--need to change to quit
			    //driver.quit();//Uncomment 
			  // driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
			  // driver.switchTo().defaultContent();
				//driver.switchTo().frame("topFrame");
				//driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			 //*[@id="icons"]/li[7]/a
				Thread.sleep(5000);
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
	Thread.sleep(8000);
	Thread.sleep(1000);
	for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
	}
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	WebDriverWait wait = new WebDriverWait(driver, 10000);
	// driver.switchTo().frame("main");
	Date DDueDate = df.parse(DueDate);
	Calendar cal = Calendar.getInstance();
	cal.setTime(DDueDate);
	//cal.add(Calendar.DATE, -1);
	// Date DDueDateminus1= cal.getTime();
	// String DueDateminus1 =df.format(DDueDateminus1);
	String DueDate0[] =DueDate.split("/");
	String DueDate1 = DueDate0[0];
	String DueDate2 = DueDate0[1];
	String DueDate3 = DueDate0[2];
	driver.switchTo().defaultContent();
	driver.switchTo().frame("topFrame");
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
	driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Transactions");
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(5000);
	//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
	driver.findElement(By.linkText("Borrower")).click();
	test.log(LogStatus.PASS, "Clicked on Borrower");
	Thread.sleep(5000);
	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));
	driver.findElement(By.linkText("Process Date Change")).click();
	test.log(LogStatus.PASS, "Clicked on Process Date Change");
	
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	driver.switchTo().defaultContent();

	driver.switchTo().frame("mainFrame");

	WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

	Actions actions1 = new Actions(driver);

	actions1.moveToElement(elements1).build().perform();

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
	driver.findElement(By.name("btnPreview")).click();
	Thread.sleep(2000);
	//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	Thread.sleep(2000);
	Thread.sleep(8000);
	test.log(LogStatus.PASS, "Clicked on submit button");
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

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


/*	for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
	}


	//String DueDateminus1 =df.format(DDueDateminus1);
	String DDueDate0[] =DueDate.split("/");
	String DDueDate1 = DDueDate0[0];
	String DDueDate2 = DDueDate0[1];
	String DDueDate3 = DDueDate0[2];


	Thread.sleep(5000);

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
	driver.findElement(By.name("beginMonth")).sendKeys(DDueDate1); 
	test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
	driver.findElement(By.name("beginDay")).clear();
	driver.findElement(By.name("beginDay")).sendKeys(DDueDate2);
	test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
	driver.findElement(By.name("beginYear")).clear();
	driver.findElement(By.name("beginYear")).sendKeys(DDueDate3);
	test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
	driver.findElement(By.name("submit")).click();
	test.log(LogStatus.PASS, "Clicked on submit button");
	if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
	{
		test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
	}
	else
	{
		test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
	}*/
	//driver.close();
}
/*public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
		{
								
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");				        
		        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
		        test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
		        //Click Login Button
		        driver.findElement(By.name("login")).click();
		        test.log(LogStatus.PASS, "Clicked on Submit button");
		        Thread.sleep(10000);
		        Thread.sleep(8000);
		        WebDriverWait wait = new WebDriverWait(driver, 10000);	
		        driver.switchTo().frame("topFrame");
		        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='100000']")));
		        driver.findElement(By.cssSelector("li[id='100000']")).click();			        
				driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Store Setup");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				 driver.findElement(By.linkText("Store Config")).click();
				//Store Config
				WebElement element= driver.findElement(By.cssSelector("li[id='101000']"));	
				Actions action = new Actions(driver);
				action.moveToElement(element).perform();
		        WebElement subElement = driver.findElement(By.cssSelector("li[id='101020']"));			        
		        action.moveToElement(subElement).perform();			 
		        action.click();	
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
					    String DueDate0[] =DueDate.split("/");
				        String DueDate1 = DueDate0[0];
				        String DueDate2 = DueDate0[1];
				        String DueDate3 = DueDate0[2];
				        driver.findElement(By.name("procDt1")).clear();
				        driver.findElement(By.name("procDt1")).sendKeys(DueDate1); 
				        test.log(LogStatus.PASS, "DueDate1 is entered: "+DueDate1);
				        driver.findElement(By.name("procDt2")).clear();
				        driver.findElement(By.name("procDt2")).sendKeys(DueDate2);
				        test.log(LogStatus.PASS, "DueDate2 is entered: "+DueDate2);
				        driver.findElement(By.name("procDt3")).clear();
				        driver.findElement(By.name("procDt3")).sendKeys(DueDate3);
				        test.log(LogStatus.PASS, "DueDate3 is entered: "+DueDate3);
				        Thread.sleep(5000);
				        driver.findElement(By.name("submitButton")).click();
				        test.log(LogStatus.PASS, "Clicked on submitted button");
				       // driver.close();		
				        driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");													    	
						 if(driver.findElement(By.name("Submit")).isDisplayed())
							{
							 test.log(LogStatus.PASS, "Store Aging is Successfully ");
								driver.findElement(By.name("Submit")).click();
							}
						 else
							{
								test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
							}
						//driver.close();
}*/
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
        //cal.add(Calendar.DATE, -1);
        // Date DDueDateminus1= cal.getTime();

        // String DueDateminus1 =df.format(DDueDateminus1);
        String DueDate0[] =DueDate.split("/");
        String DueDate1 = DueDate0[0];
        String DueDate2 = DueDate0[1];
        String DueDate3 = DueDate0[2];
        driver.switchTo().defaultContent();
        driver.switchTo().frame("topFrame");
        WebDriverWait wait = new WebDriverWait(driver, 10000);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
        test.log(LogStatus.PASS, "Clicked on Transactions");


        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        Thread.sleep(5000);
        driver.findElement(By.linkText("Borrower")).click();
        test.log(LogStatus.PASS, "Clicked on Borrower");
        Thread.sleep(5000);
        driver.findElement(By.linkText("Process Date Change")).click();
        test.log(LogStatus.PASS, "Clicked on Process Date Change");
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

		Actions actions1 = new Actions(driver);

		actions1.moveToElement(elements1).build().perform();

		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

    /*   driver.switchTo().defaultContent();
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("main");
        WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
         Actions action = new Actions(driver);
         action.moveToElement(element).build().perform();  */


        Thread.sleep(6000);


        //driver.findElement(By.name("storeCode")).click();
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
        driver.findElement(By.name("btnPreview")).click();
        Thread.sleep(2000);
        //driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
        Thread.sleep(2000);
        //Thread.sleep(8000);
        test.log(LogStatus.PASS, "Clicked on submit button");
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

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
	
	
Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 if(ProductID.equals("LOC"))
					 {
						// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
						
						 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
						// System.out.println(Pmt);
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
							// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
							//*[@id="btnADV_Yes"]
							//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
							
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
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String TxnType=TestData.getCellData(sheetName,"TxnType",row);
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
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							// System.out.println(Pmt);
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
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
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
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
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
							// System.out.println(Pmt);
							// driver.findElement(By.name("requestBean.paymentAmt")).clear();
							// driver.findElement(By.name("tenderType")).sendKeys("10");
							 //test.log(LogStatus.PASS, "tenderType");
							 String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");
							 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("tenderAmount")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								//for( String winHandle1 : driver.getWindowHandles())
								//{
								    //driver.switchTo().window(winHandle1);
								//}			
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
										test.log(LogStatus.FAIL, "Payoff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
public void Void_Payoff(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String TxnType=TestData.getCellData(sheetName,"TxnType",row);
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
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							// System.out.println(Pmt);
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish VoidPayOff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{
									 test.log(LogStatus.PASS, "Void PayOff Payment Completed Successfully ");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void PayOff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
	
/*public void StatementGeneration(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 String DueDate=null;
					 
		    driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();
					
					DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(DueDate);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			   driver.quit();//Need to change to close
			    //driver.quit();//Uncomment 
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
			        Thread.sleep(10000);
			        Thread.sleep(8000);
			        WebDriverWait wait = new WebDriverWait(driver, 10000);						
			       
			        
			         driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
				     driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					 test.log(LogStatus.PASS, "Clicked on Transactions");
					 driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					 driver.findElement(By.linkText("ACH")).click();
					 test.log(LogStatus.PASS, "Clicked on ACH");
					 
					 
					Thread.sleep(5000);
					 driver.findElement(By.linkText("LOC")).click();
					 test.log(LogStatus.PASS, "Clicked on LOC");
					 
					 //driver.switchTo().defaultContent();
					 //driver.switchTo().frame("mainFrame");
					  Thread.sleep(5000);
					 driver.findElement(By.linkText("Unsecure Loc Statement")).click();
					 test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");
					 
					 
					 driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    
					 driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
					 test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
					 
					 
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
					    String DueDate0[] =DueDate.split("/");
						 
				        String DueDate1 = DueDate0[0];
				        String DueDate2 = DueDate0[1];
				        String DueDate3 = DueDate0[2];
					   
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
				        
				      ///Process Date  
				        driver.switchTo().defaultContent();
						 driver.switchTo().frame("topFrame");
						 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
							driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
							test.log(LogStatus.PASS, "Clicked on Transactions");
				        
				     
				             driver.switchTo().defaultContent();
						      driver.switchTo().frame("mainFrame");
						Thread.sleep(5000);
						   driver.findElement(By.linkText("Borrower")).click();
						   test.log(LogStatus.PASS, "Clicked on Borrower");
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
							 driver.findElement(By.name("beginMonth")).clear();
						        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
						        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
						        driver.findElement(By.name("beginDay")).clear();
						        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
						        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
						        driver.findElement(By.name("beginYear")).clear();
						        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
						        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
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
		        
				       // EOD Batch Process
				        
				        Thread.sleep(5000);
				       // driver.switchTo().defaultContent();
						// driver.switchTo().frame("topFrame");
							//driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
							//test.log(LogStatus.PASS, "Clicked on Transactions");
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
			    
		}
	}
}*/
public void StatementGeneration(String SSN,String FileName) throws Exception
{

        //Excel TestData = new Excel("E:/LOC_Critical/TestData/"+FileName);
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
                        String SSN1 = SSN.substring(0,3);
                        String SSN2 = SSN.substring(3,5);
                        String SSN3 = SSN.substring(5,9);
                        Thread.sleep(2000);
                        //Thread.sleep(1000);
                        driver.switchTo().frame("topFrame");
                        driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Loan Transactions");
                       //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        Thread.sleep(2000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
                        Thread.sleep(1000);
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
                                //driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
                                driver.findElement(By.xpath("html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
                              
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
                        String DueDate=null;

                        //DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                        DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                        test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);
                        //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
                        System.out.print("Captured Statement Generation Date" +DueDate);
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
                        	//Thread.sleep(8000);
                        String DueDate0[] =DueDate.split("/");
                        String DueDate1 = DueDate0[0];
                        String DueDate2 = DueDate0[1];
                        String DueDate3 = DueDate0[2];
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("topFrame");
                        Thread.sleep(2000);
                    	//Thread.sleep(8000);
                        WebDriverWait wait = new WebDriverWait(driver, 10);
   					    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
                        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Transactions");
                        //Thread.sleep(2000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        
                        /*//wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Borrower")).click();
                        test.log(LogStatus.PASS, "Clicked on Borrower");
                        Thread.sleep(2000);
                        //wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Process Date Change")).click();
                        test.log(LogStatus.PASS, "Clicked on Process Date Change");*/                                                
                        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        
                        WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
    					Actions actions1 = new Actions(driver); 
    					actions1.moveToElement(elements1).build().perform(); 
    					Thread.sleep(1000);
    					driver.findElement(By.linkText("Process Date Change")).click();
    					test.log(LogStatus.PASS, "Clicked on Process Date Change");
                        //Thread.sleep(2000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("storeCode")).click();
                        Thread.sleep(1000);
                        //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
                        driver.findElement(By.name("storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
                        Thread.sleep(1000);
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
                        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        Thread.sleep(2000);
                        //Thread.sleep(8000);
                        test.log(LogStatus.PASS, "Clicked on submit button");
                        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
                        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        driver.findElement(By.linkText("ACH")).click();
                        test.log(LogStatus.PASS, "Clicked on ACH");

                        Thread.sleep(2000);
                        driver.findElement(By.linkText("LOC")).click();
                        test.log(LogStatus.PASS, "Clicked on LOC");

                        //driver.switchTo().defaultContent();
                        //driver.switchTo().frame("mainFrame");
                        Thread.sleep(2000);
                        driver.findElement(By.linkText("Unsecure Loc Statement")).click();
                        test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

                        ///Thread.sleep(6000);  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        // /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        WebElement elements = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
                        Actions actions = new Actions(driver);
                        actions.moveToElement(elements).build().perform();
                        
                        Thread.sleep(2000);
                        driver.findElement(By.name("requestBean.storeCode")).click();
                        driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);
                        
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
                        driver.findElement(By.name("submit")).click();
                        test.log(LogStatus.PASS, "Clicked on submit button");
                        test.log(LogStatus.PASS, "Statement Generated");
                }
        }
}


public void DrawerDeassign(String SSN,String FileName) throws Exception{

	//Excel TestData = new Excel("E:/LOC_Critical/TestData/"+FileName);
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);
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
			Thread.sleep(2000);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Cash Management");
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.findElement(By.cssSelector("li[id='911101']")).click();	
			driver.findElement(By.linkText("Drawer")).click();
			Thread.sleep(1000);
			test.log(LogStatus.PASS, "Clicked on Drawer");	
			driver.findElement(By.linkText("Deassign")).click();
			test.log(LogStatus.PASS, "Clicked on Deassign");	
			driver.switchTo().frame("main");				
			driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
			//driver.findElement(By.name("drawerDeassignRequestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS, "Current Cash Balance is provided as 2000");	
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
			//driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
			driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
			test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
			//Thread.sleep(2000);
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
						//Thread.sleep(2000);
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
			//Thread.sleep(2000);
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
				//driver.findElement(By.name("ok")).click();
			}
			else
			{
				test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
			}
		}
	}
}
public void StatementGeneration_EODProcessing(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
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
 Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Store Setup')]")));
	driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Store Setup");
	Thread.sleep(10000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(10000);
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Config")));
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
 	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

				
					   	
/*public void Drawerassign(String SSN,String FileName) throws Exception{


Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
//CSRLoginpage login = new CSRLoginpage();
//login.Login(UserName, Password, StoreId, driver, AppURL, test);
Thread.sleep(5000);
//driver.switchTo().defaultContent();				
//driver.switchTo().frame("topFrame");
//driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
//test.log(LogStatus.PASS, "Clicked on Cash Management");
//Thread.sleep(1000);
//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//driver.switchTo().defaultContent();
//driver.switchTo().frame("mainFrame");
//driver.switchTo().frame("main");
//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//driver.findElement(By.cssSelector("li[id='911101']")).click();	
//driver.findElement(By.linkText("Drawer")).click();
//test.log(LogStatus.PASS, "Clicked on Drawer");	
//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
//driver.findElement(By.linkText("Drawer")).click();

//driver.findElement(By.linkText("Assign")).click();
//test.log(LogStatus.PASS, "Clicked on Assign");

//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//driver.switchTo().defaultContent();
//driver.switchTo().frame("mainFrame");
//driver.switchTo().frame("main");

//driver.findElement(By.name("previous")).click();

//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//driver.switchTo().defaultContent();
//driver.switchTo().frame("mainFrame");
//driver.switchTo().frame("main");


//driver.findElement(By.name("yes")).click();

//driver.switchTo().defaultContent();
//driver.switchTo().frame("mainFrame");
//driver.switchTo().frame("main");

//driver.close();
CSRLoginpage login = new CSRLoginpage();
login.Login(UserName, Password, StoreId, driver, AppURL, test);
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


driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

if(driver.findElement(By.name("done")).isDisplayed())
{

 test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
 driver.findElement(By.name("done")).click();
}
else
{
test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
}

					    
}

}
}*/
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


    Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);
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


public void EODProcessing(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			//Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");



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
			test.log(LogStatus.PASS, "EOD Processing Completed");
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

public void DeliquentPaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
					 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
					 test.log(LogStatus.PASS,"Payment status is Deliquent."+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			  //driver.close();//need to change to close
			  driver.quit();//Uncomment 
			  driver = new InternetExplorerDriver();

		}
	}
}

public void AgeStore_10days(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 String DueDate=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					
					DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(DueDate);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			    //driver.quit();//need to change to close
			    driver.quit();//Uncomment 
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
			         //for(String winHandle : driver.getWindowHandles()){
						  //  driver.switchTo().window(winHandle);
							//}
						  //  driver.switchTo().defaultContent();
						  //  driver.switchTo().frame("mainFrame");
						    
						    
						    
						     //driver.switchTo().frame("main");
						     Date DDueDate = df.parse(DueDate);
							 Calendar cal = Calendar.getInstance();
							 cal.setTime(DDueDate);
							 cal.add(Calendar.DATE, +10);
							  Date DDueDateminus1= cal.getTime();
							 
							// String DueDateminus1 =df.format(DDueDateminus1);
						    String DueDate0[] =DueDate.split("/");
					        String DueDate1 = DueDate0[0];
					        String DueDate2 = DueDate0[1];
					        String DueDate3 = DueDate0[2];

			    
					        WebDriverWait wait = new WebDriverWait(driver, 10000);
			      				        
			         driver.switchTo().defaultContent();
					 driver.switchTo().frame("topFrame");
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
				     driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					 test.log(LogStatus.PASS, "Clicked on Transactions");
					 driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
						   driver.findElement(By.linkText("Borrower")).click();
						   test.log(LogStatus.PASS, "Clicked on Borrower");
						   Thread.sleep(5000);
							driver.findElement(By.linkText("Process Date Change")).click();
							test.log(LogStatus.PASS, "Clicked on Process Date Change");
							//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.switchTo().defaultContent();

							driver.switchTo().frame("mainFrame");

							WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

							Actions actions1 = new Actions(driver);

							actions1.moveToElement(elements1).build().perform();

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
		        
				      
				        
				       				    
		}
	}
}
public void CurePaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
					 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
					 
					//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
					 test.log(LogStatus.PASS,"Payment status is Cure."+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			 //   driver.close();//need to change to close
			 driver.quit();//Uncomment 
			    driver = new InternetExplorerDriver();

		}
	}
}

public void CustomerDefault(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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


			if(ProductID.equals("LOC"))
			{


				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
			String DueDate=null;

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture Cure End Dtae"+DueDate);
			System.out.print(DueDate);			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.quit();	//need to change to close
			//System.out.print(DueDate);	
			// driver.close();

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
		/*	Date DDueDate = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDate);
			cal.add(Calendar.DATE, 0);
			Date DDueDateminus1= cal.getTime();
		    String DueDateminus1 =df.format(DDueDateminus1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];  */
			
			
			      Date DDueDate = df.parse(DueDate);
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(DDueDate);
				 cal.add(Calendar.DATE, 0);
				  Date DDueDateminus1= cal.getTime();
				 
			 String DueDateminus1 =df.format(DDueDateminus1);
			    String DueDate0[] =DueDate.split("/");
		        String DueDate1 = DueDate0[0];
		        String DueDate2 = DueDate0[1];
		        String DueDate3 = DueDate0[2];

 
		        WebDriverWait wait = new WebDriverWait(driver, 10000);
			
			
			//WebDriverWait wait = new WebDriverWait(driver, 10000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			
			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
			Actions actions1 = new Actions(driver);
			actions1.moveToElement(elements1).build().perform();							
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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



			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");


			Thread.sleep(5000);
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");

			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Default Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Default Loc");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			//Thread.sleep(6000);
			/* WebElement element = driver.findElement(By.name("cancel"));
							        Actions action = new Actions(driver);								        
							        action.moveToElement(element).build().perform();*/

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
			 Thread.sleep(6000);
			 WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
			  Actions action = new Actions(driver);								        
			  action.moveToElement(element).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
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


			// driver.findElement(By.linkText("iPads")).click();
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			Thread.sleep(6000);


		}
	}
}
public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

			test.log(LogStatus.PASS,"Payment status is Default."+CheckStaus);
			System.out.print(CheckStaus);	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.quit();//need to change to close
			 driver = new InternetExplorerDriver();

		}
	}
}

public void Default_Payment(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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
			
			test.log(LogStatus.PASS, "Defult Payment completed Sucessfully");
		
		
		}
	}
}
public void DefaultPayment_Void(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

			driver.findElement(By.name("transactionList")).sendKeys("Payment Plan Payment");
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
			Thread.sleep(500);
			//test.log(LogStatus.INFO,"RPP(Ends)"); 


		}
	}
}


public void RPP_Status(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			Thread.sleep(500);
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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


			if(ProductID.equals("LOC"))
			{
			//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
			    //driver.findElement(By.name("button")).click();
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
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture PWO  Dtae"+DueDate);
			System.out.print(DueDate);			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			driver.close();	
			//System.out.print(DueDate);	
			// driver.close();

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


			WebDriverWait wait = new WebDriverWait(driver, 10000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
         	test.log(LogStatus.PASS, "Clicked on QA Jobs");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			/*driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

			Actions actions1 = new Actions(driver);

			actions1.moveToElement(elements1).build().perform();

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
*/
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
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");

			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Writeoff Loc")).click();
			test.log(LogStatus.PASS, "Clicked on Writeoff Loc");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			Thread.sleep(6000);
			/* WebElement element = driver.findElement(By.name("cancel"));
							        Actions action = new Actions(driver);								        
							        action.moveToElement(element).build().perform();*/

			//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
			//Thread.sleep(6000);
			//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			
			
			//try { 
				//Alert alert = driver.switchTo().alert();
				//alert.dismiss();
				//if alert present, accept and move on.														

			//}
			//catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
		//}
			
			
			 Thread.sleep(6000);
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
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


			// driver.findElement(By.linkText("iPads")).click();
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			Thread.sleep(6000);


		}
	}
}

public void WOPaymentStatus(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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


			if(ProductID.equals("LOC"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		   driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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

			System.out.print(CheckStaus);	
			//driver.close();

		}
	}
}

public void Writoff_Recovery(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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


				if(ProductID.equals("LOC"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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

				//String PaymentAmount=null;
				
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Void Write off Recovery Payment ");

			//test.log(LogStatus.PASS, "Void Write off Recovery Payment completed Sucessfully");

		    if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
				{
			// /html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input[2]
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
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
					DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
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
					 WebDriverWait wait = new WebDriverWait(driver, 10000);
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
						
						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

						Actions actions1 = new Actions(driver);

						actions1.moveToElement(elements1).build().perform();

						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

						
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
					        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					        Thread.sleep(1000);
					        Thread.sleep(8000);
					        test.log(LogStatus.PASS, "Clicked on submit button");
					        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					        
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
								driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
				        test.log(LogStatus.PASS, "Completed ACH Deposit");
				        
				        
		}
	}
}


public void Deposit_Clear(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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


			if(ProductID.equals("LOC"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
			String DueDate=null;

			/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}

			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/

			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
			test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);	
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

			
		 //driver.switchTo().defaultContent();
		    //driver.switchTo().frame("mainFrame");
		    //driver.switchTo().frame("main");
		    Date DDueDate = df.parse(DueDate);
		    Calendar cal = Calendar.getInstance();
			 cal.setTime(DDueDate);
			 cal.add(Calendar.DATE,5);
		      Date DDueDateminus1= cal.getTime();
			 
			 
			String DueDateminus1 =df.format(DDueDateminus1);
		    String DueDate0[] =DueDateminus1.split("/");
		    //String DueDate0[] =DueDate.split("/");
			 
	        String DueDate1 = DueDate0[0];
	        String DueDate2 = DueDate0[1];
	        String DueDate3 = DueDate0[2];
			//String DueDate0[] =DueDate.split("/");

			//String DueDate1 = DueDate0[0];
			//String DueDate2 = DueDate0[1];
			//String DueDate3 = DueDate0[2];

	        WebDriverWait wait = new WebDriverWait(driver, 10000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");


			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("QA Jobs")).click();
         	test.log(LogStatus.PASS, "Clicked on QA Jobs");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			
			
			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

			Actions actions1 = new Actions(driver);

			actions1.moveToElement(elements1).build().perform();*/

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			//Thread.sleep(2000);
		/*	driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));                                       
			//WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
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
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(8000);
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

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
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");


			Thread.sleep(5000);
			driver.findElement(By.linkText("RCPO")).click();
			test.log(LogStatus.PASS, "Clicked on RCPO");

			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			Thread.sleep(5000);
			driver.findElement(By.linkText("ACH Clear")).click();
			test.log(LogStatus.PASS, "Clicked on ACH Clear");
			
			//driver.switchTo().defaultContent();
			//driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			                                             /// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td
			     WebElement element1 = driver.findElement(By.linkText("Collateral Checks"));
			  // WebElement element1 = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[1]/td[2]/input"));
			  Actions action1 = new Actions(driver);								        
			  action1.moveToElement(element1).build().perform();
			  Thread.sleep(6000);

			///Thread.sleep(6000);  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			

			//Thread.sleep(6000);

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
			//driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			//driver.findElement(By.name("beginYear")).clear();
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
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					
					//DueDate=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[7]/td[2]/table/tbody/tr[2]/td[4]")).getText();
				DueDate=driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr[2]/td[2]")).getText();
					
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
						 WebDriverWait wait = new WebDriverWait(driver, 10000);
						 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
							driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
							test.log(LogStatus.PASS, "Clicked on Transactions");
				        
				     
			             driver.switchTo().defaultContent();
						      driver.switchTo().frame("mainFrame");
						Thread.sleep(5000);
						driver.findElement(By.linkText("QA Jobs")).click();
			         	test.log(LogStatus.PASS, "Clicked on QA Jobs");
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.linkText("Process Date Change")).click();
						test.log(LogStatus.PASS, "Clicked on Process Date Change");
							
							
							/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.switchTo().defaultContent();

							driver.switchTo().frame("mainFrame");

							WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

							Actions actions1 = new Actions(driver);

							actions1.moveToElement(elements1).build().perform();
*/
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							//Thread.sleep(2000);
						/*	 driver.switchTo().defaultContent();
							    driver.switchTo().frame("mainFrame");
							    driver.switchTo().frame("main");
							   WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));                                       
							 //WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
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
						        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
						        Thread.sleep(1000);
						        Thread.sleep(8000);
						        test.log(LogStatus.PASS, "Clicked on submit button");
						        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
						        
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
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
					
					//DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[7]/td[2]/table/tbody/tr[2]/td[4]")).getText();
				      DueDate=driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr[2]/td[2]")).getText();
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
					 WebDriverWait wait = new WebDriverWait(driver, 10000);
					 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
						
						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

						/*driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));

						Actions actions1 = new Actions(driver);

						actions1.moveToElement(elements1).build().perform();

						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
*/
						
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
					        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					        Thread.sleep(1000);
					        Thread.sleep(8000);
					        test.log(LogStatus.PASS, "Clicked on submit button");
					        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					        
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
								driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

public void ACH_Clear_Firtinst(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");

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

			//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			DueDate=driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr[2]/td[2]")).getText();


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
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");
			Thread.sleep(5000);			
			driver.findElement(By.linkText("ACH Clear")).click();
			test.log(LogStatus.PASS, "Clicked on ACH Clear");
			Thread.sleep(5000);	

			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
			Actions actions1 = new Actions(driver);								        
			actions1.moveToElement(elements1).build().perform();
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

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

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
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);			
			driver.findElement(By.name("submit")).click();


			test.log(LogStatus.PASS, "Clicked on submit button");

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed();

			test.log(LogStatus.PASS, "ACH Clear successfully Completed.");

		}




	}
}



public void NACHA(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
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
			DueDate=driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr[2]/td[2]")).getText();

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
			driver.findElement(By.linkText("Green Bank")).click();
			test.log(LogStatus.PASS, "Clicked on Green Bank");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Green Bank NACHA File")).click();
			test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			/*driver.switchTo().defaultContent();
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

public void ACH_Deposit(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
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
			DueDate=driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr[2]/td[2]")).getText();

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
			driver.findElement(By.linkText("LOC")).click();
			test.log(LogStatus.PASS, "Clicked on LOC");
			Thread.sleep(5000);
			driver.findElement(By.linkText("LOC Pre ACH Deposit")).click();
			test.log(LogStatus.PASS, "Clicked on LOC Pre ACH Deposit");
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


public void StatenentGenerationStatus(String SSN,String FileName) throws Exception
	{
		
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
						 
			   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}
				 
				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
						//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
						 NextstatementGeneration = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
						//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]
						 test.log(LogStatus.PASS,"Secuessfully generated SecondstatementGeneration Date."+NextstatementGeneration);
			             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				   System.out.print(NextstatementGeneration);	
				   driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
					Thread.sleep(1000);
				  //driver.close();//need to change to close
				  driver.quit();//Uncomment 
				  driver = new InternetExplorerDriver();
	
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
	//	@Test (priority=0)
	
	 public void RegistrationTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_BorrowerRegistration_NewLoan_Txn_Testdata.xls";
		//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);		        
		       test = reports.startTest("BorrowerRegistration_NewLoan_"+Header, "New Loan");
		      // Assert.assertTrue(true);
		        appUrl = AppURL;
		       
		        this.Login(UserName,Password,StoreId);			        
		        this.RegistrationPage(SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        //WebDriverWait wait = new WebDriverWait(driver, 10);		        		        		        	       
		        //wait(100);
		       // this.RegistrationPage(SSN);
		}
		}
		//this.Login("CSR353","1234","353");
	
			}
//	@Test (priority=1)
	
	 public void VoidTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_VoidLoan_Txn_Testdata.xls";
		//Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);		       
		        test = reports.startTest("VoidLoan_"+Header, "Void after New Loan");
		       // Assert.assertTrue(true);
		        appUrl = AppURL;		       
		        this.Login(UserName,Password,StoreId);			        
		        this.RegistrationPage(SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.Void(SSN,FileName);
		        //WebDriverWait wait = new WebDriverWait(driver, 10);		        		        		        	       
		        //wait(100);
		       // this.RegistrationPage(SSN);
		}
		}
		//this.Login("CSR353","1234","353");
	
			}
	//@Test (priority=2)
	
	 public void CloserTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_CloserLoan_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);	
		 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("CloserLoan_"+Header, "Closer Loan");
		        Assert.assertTrue(true);
		        appUrl = AppURL;		       
		        this.Login(UserName,Password,StoreId);			        
		        this.RegistrationPage(SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.Closer(SSN,FileName);
		        //WebDriverWait wait = new WebDriverWait(driver, 10);		        		        		        	       
		        //wait(100);
		       // this.RegistrationPage(SSN);
		}
		}
		//this.Login("CSR353","1234","353");
	
			}
	//@Test (priority=3)
	
	 public void NewLoanDraw() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_NewLoan_Draw_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("NewLoan_Draw"+Header, "New Loan");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        //this.Login(UserName,Password,StoreId);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        //WebDriverWait wait = new WebDriverWait(driver, 10);		        		        		        	       
		        //wait(100);
		       // this.RegistrationPage(SSN);
		}
		}
		//this.Login("CSR353","1234","353");
	
			}
//	@Test (priority=4)
	
	 public void VoidDraw() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_NewLoan_Draw_Void_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Void Draw"+Header, "Void Draw");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        //this.Login(UserName,Password,StoreId);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.VoidDrawLoan(SSN,FileName);
		        
		}
		}
		
	
			}
	//@Test (priority=5)
	
	 public void RescindDraw() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_NewLoan_Draw_Rescind_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Rescind_Draw"+Header, "Rescind Draw");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        //this.Login(UserName,Password,StoreId);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.ResindDrawLoan(SSN,FileName);
		        
		}
		}			
			}
	//@Test (priority=6)//--ULOC Initiation --> Draw -->Age the store till Statement Date -->Perform EOD --> Statement Generated
	 public void EOD_StatementGenerated() throws Exception {
			
			// Start test. Mention test script name
			String FileName= "QC_EOD_StatementGeneration_Txn_Testdata.xls";
			Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
			        String Header = StateID+ "_" + ProductID;
			        //System.out.println(SSN);
			        test = reports.startTest("StatementGeneration"+Header, "Loan StatementGeneration");
			        appUrl = AppURL;
			        //this.StatenentGenerationStatus(SSN, FileName);
			       // this.StatementGeneration(SSN, FileName);
			        //this.AgeStore(SSN,FileName);         
			         CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
			        this.NewLoan_Draw(SSN,FileName);
			        /*this.StatementGeneration_AgeStore(SSN,FileName);
			        this.LoanDeposit(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        //this.SafeDeassign(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.StatementGeneration(SSN, FileName); */ 
			        this.StatementGeneration(SSN, FileName); 
			        this.AgeStore(SSN, FileName, 0); 
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.StatenentGenerationStatus(SSN, FileName);
			       
			        
			       		        		        	        	        
			}
			}
			
		
				}
	//@Test (priority=7)
	
	 public void DrwaPartialpayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_NewLoan_Draw_Partialpayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("PartialPayment"+Header, "Loan PartialPayment");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        //this.Login(UserName,Password,StoreId);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN, FileName, 0);
		        this.LoanPartialPayment(SSN,FileName);
		        
		}
		}
		
	
			}
//	@Test (priority=8)
	
	 public void VoidPartialPayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_VoidPartialpayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Void PartialPayment"+Header, "Void after PartialPayment");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        //this.Login(UserName,Password,StoreId);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN, FileName, 0);
		        this.LoanPartialPayment(SSN,FileName);
		        this.Void_PartialPayment(SSN,FileName);
		}
		}
		
	
			}
	//@Test (priority=9)
	
	 public void PayOff() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_NewLoan_Draw_PayOff_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("PayOff"+Header, "Loan PayOff");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		      //this.Login(UserName,Password,StoreId);
		       //this.AgeStore(SSN,FileName);
		       //this.PayOffLoan(SSN,FileName);
		       
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN, FileName, 0);
		       // Thread.sleep(5000);
		        this.PayOffLoan(SSN,FileName);
		        
		}
		
		}
		
	
			}
	//@Test (priority=10)
	
	 public void VoidPayoff() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_VoidPayOff_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName); 
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("VoidPayOff"+Header, "Void PayOff");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		      //this.Login(UserName,Password,StoreId);
		       //this.AgeStore(SSN,FileName);
		       //this.PayOffLoan(SSN,FileName);
		       
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.AgeStore(SSN, FileName, 0);
		       // Thread.sleep(5000);
		        this.PayOffLoan(SSN,FileName);
		        this.Void_Payoff(SSN, FileName);
		        
		}
		
		}
		
	
			}
	//@Test (priority=11)
		
	 public void Deposit() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_NewLoan(ACH)_Draw_Deposit_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Deposit"+Header, "Loan Deposit");
		        appUrl = AppURL;
		        
		       
		        //this.ACHDeposit(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName); 
		        this.AgeStore(SSN, FileName, 0); 
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.ACHDeposit(SSN, FileName);
		       
		      
		}
	}
		
	
}

	//@Test (priority=12)
	
	 public void Deposit_Clear() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_Deposit_Clear_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Deposit_Clear"+Header, "Deposit_Clear");
		        appUrl = AppURL;
		        
		        //this.Deposit_Claer(SSN, FileName);
		        //this.ACHDeposit(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName); 
		        this.AgeStore(SSN, FileName, 0); 
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.ACHDeposit(SSN, FileName);
		        this.Deposit_Clear(SSN, FileName);
		        
		 
		}
	}
		
	
}
	//@Test (priority=13)
	
	 public void Deliquent() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_EOD_Deliquent_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Deliquent"+Header, "Loan Deliquent");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		        //this.DrawerDeassign(SSN, FileName);
		      // this.Drawerassign(SSN, FileName);
		        //this.StatementGeneration(SSN, FileName);
		        //this.AgeStore(SSN,FileName);         
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        //this.LoanDeposit(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_EOD(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		       // this.LoanDeposit(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.DeliquentPaymentStatus(SSN, FileName);
		       // this.PaymentStatus(SSN, FileName);		        		       		        		       		        		        	        	        
		}
		}			
			}
	//@Test (priority=14)
	
	 public void Cure() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_EOD_Cure_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Cure"+Header, "Loan Cure");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		       
		         CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_EOD(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);		       		        		       		        		        	        	        
		}
		}
		
//--------------------
			}
	//@Test (priority=15)
	
	 public void Default() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_Default_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        String Header = StateID+ "_" + ProductID;
		       
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_17"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default");
		        Assert.assertTrue(true);
		        appUrl = AppURL;		        
		       // this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName); 
		        this.AgeStore(SSN, FileName, 0);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore(SSN, FileName, 10);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName); 
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);   
		       	       		        		        	        	        
		}
		}
		
	
			}
	//@Test (priority=16)
	 public void DefaultPayment() throws Exception {
			
			// Start test. Mention test script name
			String FileName= "QC_DefaultPayment_Txn_Testdata.xls";
			Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
			        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
			        String Header = StateID+ "_" + ProductID;
			        //System.out.println(SSN);
			        test = reports.startTest(Header+"Critical_Scenario.No_18"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default --> Default Payment");
			        Assert.assertTrue(true);
			        appUrl = AppURL;
			       
			        //this.SetCurrentDate(SSN, FileName);
			        CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
			        this.NewLoan_Draw(SSN,FileName);
			        this.StatementGeneration(SSN, FileName);
			        this.AgeStore(SSN, FileName, 0);
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.AgeStore(SSN, FileName, 10);
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName); 
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName); 
			        this.CustomerDefault(SSN, FileName);
			        this.DefaultPaymentStatus(SSN, FileName);
			        this.Default_Payment(SSN, FileName);
			       	       		        		        	        	        
			}
			}
			
		
				}
	//@Test (priority=17)
		
	 public void DefaultPayment_Void() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_DefaultPayment_Void_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_19"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default --> Default Payment --> Void Default Payment");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		       // this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN, FileName, 0);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore(SSN, FileName, 10);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName); 
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.Default_Payment(SSN, FileName);  
		        this.DefaultPayment_Void(SSN, FileName);
		       	       		        		        	        	        
		}
		}			
			}
	
	
	//@Test (priority=18)
	
	 public void RCCRevoke() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "Default_RCCRevoke_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_20"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default --> Perform  RCC Revoke --> customer bank details revoked");
		        appUrl = AppURL;
		       // this.Drawerassign(SSN, FileName);
		      //  this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN,FileName,0);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore(SSN,FileName,10);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.RCC_Revoke(SSN, FileName);
		       	       		        		        	        	        
		}
		}
		
	
			}
	
	
	//@Test (priority=19)
	
	 public void RCCRevoke_RPP() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "Default_RCCRevoke_RPP_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;		        
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_22"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default --> Perform  RCC Revoke --> RPP shown in Transaction Drop Down --> Perfom RPP");
		        appUrl = AppURL;
		       // this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN,FileName,0);
		        this.DrawerDeassign(SSN, FileName); 
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);  
		        this.AgeStore(SSN,FileName,10);
		        this.DrawerDeassign(SSN, FileName);
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.RCC_Revoke(SSN, FileName); 
		        this.RPP(SSN, FileName);
		        //this.RPP_Status(SSN, FileName);
		       // this.RPP_Void(SSN, FileName);
		       	       		        		        	        	        
		}
		}
		
	
			}
	
	
	//@Test (priority=20)
	
	 public void RCCRevoke_RPP_Void() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "RCCRevoke_RPP_Void_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_23"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default --> Perform  RCC Revoke --> RPP shown in Transaction Drop Down --> Perfom RPP --> Void RPP");
		        appUrl = AppURL;
		        //this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN,FileName,0);
		        this.DrawerDeassign(SSN, FileName); 
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);  
		        this.AgeStore(SSN,FileName,10);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.RCC_Revoke(SSN, FileName);
		        this.RPP(SSN, FileName);
		        this.RPP_Status(SSN, FileName);
		        this.RPP_Void(SSN, FileName);
		       	       		        		        	        	        
		}
		}
		
	
			}
	@Test (priority=21)
	
	 public void RPP_1stInstallment_PerformDeposit() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "RPP_1stInsmtDeposit_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_24"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default-->Perform RCC Revoke -->RPP shown in Transaction Drop Down --> Perfom RPP --> Age the store upto 1st installment due date --> Perform Deposit");
		        appUrl = AppURL;
		        
		        //this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN,FileName,0);
		        this.DrawerDeassign(SSN, FileName); 
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName); 
		        this.AgeStore(SSN,FileName,10);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.RCC_Revoke(SSN, FileName);
		        this.RPP(SSN, FileName);
		       // this.RPP_Status(SSN, FileName);
		        this.AgeStore_1stInsmtDueDate_Mns1(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.NACHA(SSN, FileName, -1); 
		        this.ACH_Deposit(SSN, FileName, 0);
		        this.ACHStatus_1stInsmt(SSN, FileName);	
		        
		}
		}			
			}
	
	@Test (priority=22)
	
	 public void RPP_1stInstallment_ACHClear() throws Exception {
		String FileName= "RPP_1stInsmtACHClear_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
		        String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"Critical_Scenario.No_25"+"_"+PayFrequency+"_"+CollateralType, "ULOC Initiation with NOC --> Draw --> Age the store upto due date --> Perform EOD --> Customer Deliquent --> Due Date + 10 Days --> Perform EOD ---> Customer Cure --> Cure Date +21 Days --> Run DEF proc --> Customer Default --> Perform  RCC Revoke --> RPP shown in Transaction Drop Down --> Perfom RPP --> Age the store upto 1st installment due date --> Perform Deposit --> Age the Store --> Clear Deposit");
		        appUrl = AppURL;
		        
		        //this.SetCurrentDate(SSN, FileName);
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN,FileName,0);
		        this.DrawerDeassign(SSN, FileName); 
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName); 
		        this.AgeStore(SSN,FileName,10); 
		        this.DrawerDeassign(SSN, FileName);
		        //this.EODProcessing(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName); 
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName);
		        this.RCC_Revoke(SSN, FileName);
		        this.RPP(SSN, FileName);
		       // this.RPP_Status(SSN, FileName); 
		        this.AgeStore_1stInsmtDueDate_Mns1(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        //this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);  
		        this.NACHA(SSN, FileName, -1); 
		        this.ACH_Deposit(SSN, FileName, 0);
		        this.ACH_Clear_Firtinst(SSN, FileName, 7);
		}
		}
		
	
			}

	
	//@Test (priority=23)
	
	 public void Default_CustomerWO() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_Default_CustomerWO_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Default_CustomerWO"+Header, "Default_CustomerWO");
		        appUrl = AppURL;
		       
		        //this.WOPaymentStatus(SSN, FileName);
		        //this.Writoff_Recovery(SSN, FileName);
		         CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN, FileName, 0);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore(SSN,FileName,10);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);  
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
	
	///@Test (priority=24)
	
	 public void Default_WORecoveryPayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_Default_WORecoveryPayment_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("Default_WORecovery"+Header, "Default_WORecovery");
		        appUrl = AppURL;
		       
		        //this.CustomerDefault(SSN, FileName);
		         CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore(SSN, FileName, 0); 
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);  
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

	///@Test (priority=25)
	
	 public void Default_WORecovery_VoidPayment() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "QC_Default_WORecoveryP_Voidayment_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
		        String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest("WORecoveryP_Voidayment"+Header, "WORecoveryP_Voidayment");
		        appUrl = AppURL;
		       
		        
		        //this.Writoff_Recovery(SSN, FileName);
		         CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan_Draw(SSN,FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore(SSN, FileName, 0);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);  
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
	 
	 public void endReport(){
	 // writing everything to document
	 //flush() - to write or update test information to your report.
		 reports.endTest(test);
	                reports.flush();
	                //Call close() at the very end of your session to clear all resources. 
	                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
	                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
	                //Once this method is called, calling any Extent method will throw an error.
	                //close() - To close all the operation
	           //driver.quit();
	         
	               
	    }
 @AfterClass
	 
 public void closeBrowser() throws Exception{
	    
	    driver.quit();
	    
	}
}
	
	


