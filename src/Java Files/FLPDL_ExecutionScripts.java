package Tests;


import java.awt.Robot;
import java.awt.event.KeyEvent;
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
//import Tests.Executionscripts_PDL_New;
import Utilities.ExtentReports.Excel;
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;



public class FLPDL_ExecutionScripts {

	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	//ExtentTest logger;

	@BeforeClass
	public synchronized void initialize() {
		
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
				
		String filename="AA_FL_PDL_Scenarios"+timestamp+".html";
		
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/FLPDL_Regresion/"+filename, true);
	}
	
	@BeforeMethod
	 public void killProcess() throws Exception{		
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
        Thread.sleep(5000); //Allow OS to kill the process
        System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	
	//@BeforeTest
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
		driver.findElement(By.name(passwordId)).clear();
		driver.findElement(By.name(passwordId)).sendKeys(password);
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Password is entered: "+password);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		driver.findElement(By.name(Login)).click();
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Clicked on Submit button");
		Thread.sleep(2000);
	}

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
				Thread.sleep(5000);
				WebDriverWait wait = new WebDriverWait(driver, 1000);	
				driver.switchTo().frame("topFrame");     	        
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
				driver.findElement(By.cssSelector("li[id='900000']")).click();			
				test.log(LogStatus.PASS, "Clicked on Borrower");
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
				Select PhoneType  = new Select(driver.findElement(By.name("customerBean.phoneCd")));
				PhoneType.selectByVisibleText(Ctc_PhoneType);
				test.log(LogStatus.PASS, "Phone Type is selected as: "+Ctc_PhoneType);
				driver.findElement(By.name("sphoneNbr1")).sendKeys(PP1);
				test.log(LogStatus.PASS, "SPP1 is entered: "+PP1);
				driver.findElement(By.name("sphoneNbr2")).sendKeys(PP1);
				test.log(LogStatus.PASS, "SPP2 is entered: "+PP1);
				driver.findElement(By.name("sphoneNbr3")).sendKeys(PP3);
				test.log(LogStatus.PASS, "SPP3 is entered: "+PP3);
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

				driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
				test.log(LogStatus.PASS, "Bank_ABARoutingNbr is entered: "+Bank_ABARoutingNbr);
				driver.findElement(By.name("checkAbaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
				test.log(LogStatus.PASS, "Confirm ABA/Routing Nbr is entered: "+Bank_ABARoutingNbr);
				driver.findElement(By.name("customerBean.accountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
				test.log(LogStatus.PASS, "Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
				driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
				test.log(LogStatus.PASS, "Confirm Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
				driver.findElement(By.name("customerBean.bankrupty")).sendKeys(Bankruptcy);
				test.log(LogStatus.PASS, "Bankrupty is selected as: "+Bankruptcy);
				driver.findElement(By.name("SLoan")).click();							
				test.log(LogStatus.PASS, "Clicked on Save&Loan");
				Thread.sleep(5000);

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();													

				}
				catch (NoAlertPresentException e) {
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
	public boolean IsElementExits(String Value) {
		int secondsToWait = 5;

		try {
			new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Value)));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public void NewLoan_CustomerIncome(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);     	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		String LoanAmount = null;
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
				String Parent_Window = driver.getWindowHandle();
				
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 //	Selection of Product based on the Name provided in Test Data
				
				 //if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				 {
					 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
				//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
															
					if(ProductName.equals("TNPAYDAY"))
					{
						////							*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{								////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.name("prodSel")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					
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
                             //driver.findElement(By.id("LoanButtonId")).click();

                             test.log(LogStatus.PASS, "Clicked on New Loan button");

                             LoanAmount = driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
                             test.log(LogStatus.PASS, "LoanAmount is  "+LoanAmount);
					
				 }
			}
		}
	}

	public void NewLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
			test.log(LogStatus.INFO, "Issue New Loan");
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
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
					String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
					String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
					String Electronic_Documents = TestData.getCellData(sheetName,"Electronic_Documents",row);
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
					/*	 List<WebElement> radioButtons = driver.findElements(By.name("prodSel"));
						 
						 int iSize = radioButtons.size();
											 
						 // Start the loop from first checkbox to last checkbox
						 if(iSize > 0)
						
						 {
							 
						 for(int i=0; i <= iSize ; i++ ){
						 
						 // Store the checkbox name to the string variable, using 'Value' attribute
							 
						 String sValue = radioButtons.get(i).getAttribute("onclick");
						
						 // Select the checkbox it the value of the checkbox is same what you are looking for
						 
						 if (sValue.contains(ProductName)) {
						 
						 radioButtons.get(i).click();
						 
						 // This will take the execution out of for loop
						 
						 break;
						 
						 }
						}
						 } 
						 else*/
						 {
							 driver.findElement(By.name("ShareScreenBtn")).click();
								test.log(LogStatus.PASS, "ShareScreen Button is clicked");
						 }
						
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
	                          
						if(ProductID.equals("PDL"))
						{
							test.log(LogStatus.INFO, "Navigated to New Loan Screen");
							driver.findElement(By.name("advanceRequestBean.collateralTypeId")).sendKeys(ESign_CollateralType);
							test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
							Thread.sleep(1000);
							driver.findElement(By.name("advanceRequestBean.disbursementType")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "CollateralType is selected as Cash");
							Thread.sleep(1000);
							String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
		                    test.log(LogStatus.PASS, "LoanAmount is  "+Instamt);
							driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
							Thread.sleep(2000);
							driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(Electronic_Documents);
							test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+Electronic_Documents);
							
							 if(ESign_CollateralType.equals("CHECK")||ESign_CollateralType.equals("Check"))
	                         {
	                         driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
	                         test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
	                         Thread.sleep(2000);
	                         driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
	                         test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
	                         }
							 
							driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).clear();
							driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).click();
							driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
							test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
							driver.findElement(By.name("finishadvance")).click();
							test.log(LogStatus.PASS, "Click on Finish Loan Button");
							
							 try { 
	                             Alert alert = driver.switchTo().alert();
	                             alert.accept();
	                         }
	                         catch (NoAlertPresentException e) {
	                             
	                         }	
							Thread.sleep(1000);
							try { 
	                            Alert alert = driver.switchTo().alert();
	                            alert.accept();
	                        }
	                        catch (NoAlertPresentException e) {
	                            
	                        }	
							Thread.sleep(1000);
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
	                         driver.switchTo().frame("bdyLoad");
	                         if(driver.findElement(By.name("Ok")).isDisplayed())
	                         {
	                                 driver.findElement(By.name("Ok")).click();
	                                 test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
	                                 
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


	public void NewLoanRule(String SSN,String FileName) throws Exception{

		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);     	
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
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String Password = TestData.getCellData(sheetName,"Password",row);
					String ProductType = TestData.getCellData(sheetName,"ProductType",row);
					String ProductName = TestData.getCellData(sheetName,"ProductName",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
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
					String Parent_Window = driver.getWindowHandle();
					System.out.println(ProductID);	
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					appUrl = AppURL;
					this.Login(UserName, Password, StoreID);
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
					 driver.findElement(By.xpath("//*[@id='911100']/a")).click();
					 test.log(LogStatus.PASS, "Clicked on NewLoan");		
					 driver.switchTo().frame("main");		
					 driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					 test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					 driver.findElement(By.name("ssn2")).sendKeys(SSN2);
					 test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					 driver.findElement(By.name("ssn3")).sendKeys(SSN3);
					 test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					 driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[2]")).click();
					 test.log(LogStatus.PASS, "Click on submit Button");		
					 for(String winHandle : driver.getWindowHandles()){
						 driver.switchTo().window(winHandle);
					 }
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
					 test.log(LogStatus.PASS, "Click on GO Button");
					 for(String winHandle : driver.getWindowHandles()){
						 driver.switchTo().window(winHandle);
					 }				    
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 int count = 1;
					 for(int i=1;i<=count;i++)
						 
						 if(driver.findElement(By.xpath("//*[@id='errMsg']/ul/li")).isDisplayed())
						 {
							 Thread.sleep(3000);
							 String message = driver.findElement(By.xpath("//*[@id='errMsg']/ul/li")).getText();
							 test.log(LogStatus.PASS, "Maximum loans given and the message displayed is:"+ message);
							 int LoanCount = i ;
							 test.log(LogStatus.PASS, "Maximum loans given is :"+ LoanCount);
						 }
						 else
						 {
							 this.NewLoan(SSN, FileName);
						 }

				}
			}
		}


	}



	public void Biweekly_duedate (String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan "+lastrow);

		String sheetName="NewLoan";

		for(int row=2;row<=lastrow;row++)

		{

			String RegSSN = TestData.getCellData(sheetName,"SSN",row);

			if(SSN.equals(RegSSN))

			{

				String TxnType =TestData.getCellData(sheetName,"TxnType",row);                          

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);

				String Password = TestData.getCellData(sheetName,"Password",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);                  

				test.log(LogStatus.INFO, "Scheduler-Store Aging"); 

				System.out.println(ProductID);

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





				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

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

				String DueDate=null;                          


				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				

				test.log(LogStatus.PASS, "Biweekly Duedate is :"+DueDate);                      

				System.out.print(DueDate);


			}

		}

	}



	public void Monthly_duedate (String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan "+lastrow);

		String sheetName="NewLoan";

		for(int row=2;row<=lastrow;row++)

		{

			String RegSSN = TestData.getCellData(sheetName,"SSN",row);

			if(SSN.equals(RegSSN))

			{

				String TxnType =TestData.getCellData(sheetName,"TxnType",row);                          

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);

				String Password = TestData.getCellData(sheetName,"Password",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);                  

				test.log(LogStatus.INFO, "Scheduler-Store Aging"); 

				System.out.println(ProductID);

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

				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

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

				String DueDate=null;                          

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				
				test.log(LogStatus.PASS, "Monthly Duedate is :"+DueDate);                      

				System.out.print(DueDate);


			}

		}

	}



	public void semimonthly_duedate (String SSN,String FileName) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan "+lastrow);

		String sheetName="NewLoan";

		for(int row=2;row<=lastrow;row++)

		{

			String RegSSN = TestData.getCellData(sheetName,"SSN",row);

			if(SSN.equals(RegSSN))

			{

				String TxnType =TestData.getCellData(sheetName,"TxnType",row);                         

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);

				String Password = TestData.getCellData(sheetName,"Password",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);                  

				test.log(LogStatus.INFO, "Scheduler-Store Aging"); 

				System.out.println(ProductID);

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


				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

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

				String DueDate=null;                          

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				
				test.log(LogStatus.PASS, "Semimonthly Duedate is :"+DueDate);                      

				System.out.print(DueDate);

			}

		}

	}


	public void Weekly_duedate (String SSN,String FileName) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan "+lastrow);

		String sheetName="NewLoan";

		for(int row=2;row<=lastrow;row++)

		{

			String RegSSN = TestData.getCellData(sheetName,"SSN",row);

			if(SSN.equals(RegSSN))

			{

				String TxnType =TestData.getCellData(sheetName,"TxnType",row);                         

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);

				String Password = TestData.getCellData(sheetName,"Password",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);                  

				test.log(LogStatus.INFO, "Scheduler-Store Aging"); 

				System.out.println(ProductID);

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

				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

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

				String DueDate=null;                          

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			
				test.log(LogStatus.PASS, "Weekly Duedate is :"+DueDate);                      

				System.out.print(DueDate);

			}

		}

	}


	public void NewLoan_MultipulDisbTypes(String SSN,String FileName) throws Exception{



		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 

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

				String ProductType = TestData.getCellData(sheetName,"ProductType",row);

				String ProductName = TestData.getCellData(sheetName,"ProductName",row);

				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);

				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);

				String stateProductType=State+" "+ProductType;

				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);

				System.out.println(ESign_CollateralType);

				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);

				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);

				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

				String Esign_DisbType1 = TestData.getCellData(sheetName,"Esign_DisbType1",row);

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
				String LoanAmount = null;
				String DisbAmount1 = null;
				String DisbAmount2 = null;

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


					if(ProductName.equals("TNPAYDAY"))

					{

						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();

						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);

					}

					if(ProductName.equals("TNPDL all coll"))

						driver.findElement(By.name("prodSel")).click();

						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);

					}

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


					test.log(LogStatus.PASS, "Clicked on New Loan button");

					if(ProductID.equals("PDL"))

					{ 


						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);

						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						
						//LoanAmount = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).getAttribute("value");
						
						//DisbAmount1 = LoanAmount/2;
						//DisbAmount2 = LoanAmount-DisbAmount1;

						if(!(ESign_LoanAmt.isEmpty()))

						{

							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);

							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);

						}

						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);

						test.log(LogStatus.PASS, " Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);




						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);

						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);

						Thread.sleep(5000);

						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys("215"); 

						test.log(LogStatus.PASS, "Disb Amt is enterted as 215 ");


						driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(Esign_DisbType1);

						test.log(LogStatus.PASS, "Disb Type2 is selected as ::Check");

						driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("210");

						test.log(LogStatus.PASS, "Disb Amt1 is enterted as 210");

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
                  if(ESign_CollateralType.equals("CHECK")||ESign_CollateralType.equals("Check"))
                           { 
						driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);

						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);

						WebDriverWait wait = new WebDriverWait(driver, 1000); 

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));

						driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);

						test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
                           }

						driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);

						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);

						driver.findElement(By.name("finishadvance")).click();

						test.log(LogStatus.PASS, "click on Finish Loan button ");

						try { 

							Alert alert = driver.switchTo().alert();

							alert.accept();


						}

						catch (NoAlertPresentException e) {


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

							driver.findElement(By.name("Ok")).click();

							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");

						}

						else

						{

							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");

						}

					}
			}
		}
	}




	public void Void(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");	
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
				
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("Submit23")).click();
					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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


	public void LoanDate_AgeStore(String SSN,String FileName,int Days) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
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
				String LoanDate=null;

				LoanDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[10]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+LoanDate);
				System.out.print(LoanDate);	
				driver.close();  

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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
				Thread.sleep(8000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =LoanDate.split("/");
				Date DDueDateminus1 = df.parse(LoanDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDateminus1);
				cal.add(Calendar.DATE, Days);
				Date DDueDate1= cal.getTime();
				LoanDate =df.format(DDueDate1);
				String DueDate0[] =LoanDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];



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





	public void EncryptionKey_Void(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				driver.findElement(By.id("go_Button")).click();
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

				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
				test.log(LogStatus.PASS, "No button is clicked ");		

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

				driver.findElement(By.name("trancd")).sendKeys("Advance-ADV");
				test.log(LogStatus.PASS, "Trancd selected");

				driver.findElement(By.name("GetKey")).click();
				test.log(LogStatus.PASS, "GetKey clicked");

				Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
				test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

				driver.close();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				driver.findElement(By.id("go_Button")).click();
				Thread.sleep(5000);

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
				test.log(LogStatus.PASS, "Yes Button clicked");

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
					String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
					System.out.println(Pmt);
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



				}
				
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					// Robot robot = new Robot();
					//	Thread.sleep(2000);
					//	robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("Submit23")).click();
				//	robot.keyPress(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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

	public void Rescind(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);    	
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
				Thread.sleep(5000);
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
				
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				test.log(LogStatus.PASS, "Transaction Type is selected as: "+TxnType);	
				driver.findElement(By.id("go_Button")).click();
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
					System.out.println(Pmt);
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
				}
			
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("Submit23")).click();
				}
									 						 
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
				
				if(ProductID.equals("PDL"))
				{

					if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Rescind Loan is Completed Successfully ");
						driver.findElement(By.name("checkyes")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Rescind Loan is not Completed Successfully ");
					}
				}
			}

		}
	}

	public void AgeStore(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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

				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				
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
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
				 driver.findElement(By.linkText("QA Jobs")).click();
			      test.log(LogStatus.PASS, "Clicked on QA Jobs");
				
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}
			}
		}
	}

	public void LoanPartialPayment(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
					driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("20");
					test.log(LogStatus.PASS, "Payment Amt is entered as 20");
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("20");
					test.log(LogStatus.PASS, "Tender Amt is entered as 20");							
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("finish")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
					Thread.sleep(1000);

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
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");
					}


				}

			}

		}
	}

	public void Partialpayment_Void(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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
				
				if(ProductID.equals("PDL"))
				{  


					driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type Selected "+TenderType);

					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);
					Thread.sleep(1000);
					 Robot robot = new Robot();
						Thread.sleep(2000);
						robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Button");
					robot.keyPress(KeyEvent.VK_F11);

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


	public void PartialPaymentVoidEncryptionKey(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				driver.findElement(By.id("go_Button")).click();
		
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

				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
				test.log(LogStatus.PASS, "No button is clicked ");		

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

				driver.findElement(By.name("trancd")).sendKeys("Partial Payment-PAY");
				test.log(LogStatus.PASS, "Trancd selected");

				driver.findElement(By.name("GetKey")).click();
				test.log(LogStatus.PASS, "GetKey clicked");

				Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
				test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

				driver.close();


				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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

				driver.findElement(By.id("go_Button")).click();
	
				Thread.sleep(5000);

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
				test.log(LogStatus.PASS, "Yes Button clicked");

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type Selected "+TenderType);

					driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);

					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);
					Thread.sleep(1000);
					Robot robot = new Robot();
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Button");
					robot.keyPress(KeyEvent.VK_F11);

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


	public void Buyback (String SSN,String FileName) throws Exception{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);    	
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
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				this.Login(UserName,Password,StoreId);	
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
				Thread.sleep(5000);
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

				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(5000);
				driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				test.log(LogStatus.PASS, "Transaction Type is selected as: "+TxnType);	
				driver.findElement(By.id("go_Button")).click();
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("PDL"))
				{

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					String Pmt= driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");						 
					System.out.println(Pmt);						 
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("finish")).click();
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
					if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
						driver.findElement(By.name("checkyes")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
					}
				}
											
			}

		}
	}


	public void AgeStore_Buyback_encryptionKeyVoid(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

	public void Byback_AgestoreVoid(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();

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

				driver.findElement(By.id("go_Button")).click();
				
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

				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
				test.log(LogStatus.PASS, "No button is clicked ");		

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

				driver.findElement(By.name("trancd")).sendKeys("Buyback-BUY");
				test.log(LogStatus.PASS, "Trancd selected");

				driver.findElement(By.name("GetKey")).click();
				test.log(LogStatus.PASS, "GetKey clicked");

				Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
				test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

				driver.close();


				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
				driver.findElement(By.id("go_Button")).click();
				
				Thread.sleep(5000);

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
				test.log(LogStatus.PASS, "Yes Button clicked");

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type Selected "+TenderType);

					driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);

					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);
					Thread.sleep(1000);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Button");

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

	public void BuybackChange (String SSN,String FileName) throws Exception{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);    	
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
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);

				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

				System.out.println(AdminURL);

				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);

				String AppURL = TestData.getCellData(sheetName,"AppURL",row);

				appUrl = AppURL;

				this.Login(UserName,Password,StoreID);
				driver.switchTo().defaultContent();		
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
				Thread.sleep(5000);
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
				
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(5000);
				driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				test.log(LogStatus.PASS, "Transaction Type is selected as: "+TxnType);	
				driver.findElement(By.id("go_Button")).click();
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("PDL"))
				{
					

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					String Pmt = driver.findElement(By.name("payAmt")).getAttribute("value");
					System.out.println(Pmt);
					float pmt1=Float.parseFloat(Pmt);
					float pmt2 = pmt1+30;
					test.log(LogStatus.INFO, "Given Total Amount "+pmt2);
					String pmtv=Float.toString(pmt2);
					test.log(LogStatus.PASS, "Total Amount is as "+pmtv);
					System.out.println(pmtv);

					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(pmtv);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+pmtv);
					String ChangeAmount1 = driver.findElement(By.name("transactionDataBean.change")).getCssValue("value");
					test.log(LogStatus.INFO, "Change Amount is :: "+  "30");
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("finish")).click();
					Thread.sleep(3000);

					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						String Var3 = alert.getText();

					}
					catch (NoAlertPresentException e) {
					}


					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						String Var3 = alert.getText();

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
					if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
						driver.findElement(By.name("checkyes")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
					}
				}
											
			}

		}
	}			
	public void BuybackVoid(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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
				String ESign_Disb_Type2 = TestData.getCellData(sheetName,"Esign_Disb_Type2",row);
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				}
				if(ProductID.equals("TLP"))
				{
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				}

				Thread.sleep(5000);
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
					driver.findElement(By.id("go_Button")).click();
				
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(ESign_Disb_Type2);
					test.log(LogStatus.PASS, "disbursementType "+ESign_Disb_Type2);

				}
				
				if(ProductID.equals("PDL"))
				{

					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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


	public void PrenoteDeposit_6DaysBeforeDuedate(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				System.out.println(AdminURL);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
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


				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
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
				driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
				test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				String DDueDate[] =DueDate.split("/");
				Date DDueDateminus1 = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDateminus1);
				cal.add(Calendar.DATE, -7);
				Date DDueDate1= cal.getTime();
				DueDate =df.format(DDueDate1);
				String DueDate0[] =DueDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.locNbrCsr")).click();
				driver.findElement(By.name("requestBean.locNbrCsr")).sendKeys(StoreID);
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
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				{									        								
					test.log(LogStatus.PASS, "PDL Pre Note Deposit Process updated successfully");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "PDL Pre Note Deposit Process not updated successfully.");
				}
			}
		}
	}

	public void Prenote_deposit_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String Prepayment=null;

				String Prenotedeposit = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[5]")).getText();


				if((Prenotedeposit).contains("Prenote Deposit"))
				{
					test.log(LogStatus.PASS,"Prenote deposit Record is  Displayed " );
				}
				else
				{
					test.log(LogStatus.PASS,"Prenote deposit Record is not Displayed.");
				}
				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
			}
		}
	}


	public void PrenoteClear_BeforeDuedate(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  			
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
				test.log(LogStatus.PASS, "transactionList Selected as History");
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.id("go_Button")).click(); 
					test.log(LogStatus.PASS, "Click on GO Button");
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

				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");

				String DDueDate[] =DueDate.split("/");
				Date DDueDateminus1 = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDateminus1);
				cal.add(Calendar.DATE, -1);
				Date DDueDate1= cal.getTime();
				DueDate =df.format(DDueDate1);
				String DueDate0[] =DueDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
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
				if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
				{
					test.log(LogStatus.PASS, "OK Button as Enabled");
					test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");

				}
				else
				{
					test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
				}
			}

		}
	}

	public void EODProcessing(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 	
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
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.name("Submit2")).click();
					test.log(LogStatus.PASS,"Clicked on Balance Safe");
					Thread.sleep(10000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
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

	public void DrawerDeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 	
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
					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Cash Management");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					//-----------------updated 03/01/2019-----//
					try{
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//driver.close();
					}
					catch (Exception e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(1000);
					//driver.findElement(By.name("drawerdeassign")).click();
					//----------------//
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


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 	
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
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 	
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
				Thread.sleep(2000);		
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
			 Thread.sleep(1000);
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
	 	
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 
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
					 CSRLoginpage login = new CSRLoginpage();
					 login.Login(UserName, Password, StoreId, driver, AppURL, test);
					 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


		   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName); 		
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

	public WebElement Field(WebDriver driver) {


		try {
			Thread.sleep(500);
			WebElement element = (new WebDriverWait(driver, 9)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
			return element;
		} catch (Exception e) {
			return null;
		}
	}	    		
	
	public void SafeDeassign(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");

				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS,"Passwored is entered");

				driver.findElement(By.name("safedeassign")).click();
				test.log(LogStatus.PASS,"Click on Safe Deassigen Button");

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {

				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");

				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");
				test.log(LogStatus.PASS,"Passwored is Entered");

				driver.findElement(By.name("safedeassign")).click();
				test.log(LogStatus.PASS,"Click on Safe Deassign Button");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {

				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("safeDeassignRequestBean.comments")).sendKeys("chenna");
				test.log(LogStatus.PASS,"Comments are Eneterd");
				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");
				test.log(LogStatus.PASS,"Passwored is Entered");

				driver.findElement(By.name("safedeassign")).click();
				test.log(LogStatus.PASS,"Click on safe Deassign Button");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if(driver.findElement(By.name("done")).isDisplayed())
				{

					test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
					driver.findElement(By.name("done")).click();
				}
				else
				{
					test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");
				}


				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("yes")).click();


				driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
				driver.findElement(By.name("drawerassign")).click();
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {

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

			}}}

	public void AgeStore_ACHEffectiveDate(String SSN,String FileName,int days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

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

				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[2]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);


				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);


				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

				driver.findElement(By.linkText("Borrower")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE,days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

				}

				else

				{

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}

	public void ACHEffectivedate_6DaysBeforeDuedate(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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


				if(ProductID.equals("PDL"))
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
				test.log(LogStatus.PASS, "Transaction List  Selected is: History ");
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.id("go_Button")).click(); 
					test.log(LogStatus.PASS, "Click on GO Button");
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[2]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture ACH Effective Date:" +DueDate);
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
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
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
				test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				String DDueDate[] =DueDate.split("/");
				//String date = DDueDate[1];
				Date DDueDateminus1 = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDateminus1);
				cal.add(Calendar.DATE, -7);
				Date DDueDate1= cal.getTime();
				DueDate =df.format(DDueDate1);
				String DueDate0[] =DueDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.locNbrCsr")).click();
				driver.findElement(By.name("requestBean.locNbrCsr")).sendKeys(StoreID);
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
					test.log(LogStatus.PASS, "OK Button is Enabled");

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
					test.log(LogStatus.PASS, "PDL Pre Note Deposit Process updated successfully");


				}
				else
				{
					test.log(LogStatus.FAIL, "PDL Pre Note Deposit Process not updated successfully.");
				}
			}
		}
	}		

	public void NACHA(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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

				String DueDate=null;

				DueDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				//driver.close();

				//driver = new InternetExplorerDriver();
				driver.get(AdminURL);
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
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");
				driver.findElement(By.linkText("Green Bank")).click();
				test.log(LogStatus.PASS, "Clicked on Green Bank");
				driver.findElement(By.linkText("Green Bank NACHA File")).click();
				test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
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

	public void Payliance(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				String AdminPassword = TestData.getCellData(sheetName,"AdminPassword",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();

				login.Login(UserName, Password, StoreID, driver, AppURL, test);
								
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


				String DueDate=null;

				DueDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				driver.close();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(AdminURL);
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
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");
				
				driver.findElement(By.linkText("Payliance")).click();
				test.log(LogStatus.PASS, "Clicked on Payliance");
				
				driver.findElement(By.linkText("Payliance Origination File")).click();
				test.log(LogStatus.PASS, "Clicked on Payliance Origination File");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(1000);

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
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
				{									        								
					test.log(LogStatus.PASS, "Process Payliance file successfully.");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Payliance is not updated successfully.");
				}
			}
		}
	}

	public void ACHReturnPosting(String SSN,String FileName) throws Exception
	{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
			String AdminPassword = TestData.getCellData(sheetName,"AdminPassword",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			CSRLoginpage login = new CSRLoginpage();

			login.Login(UserName, Password, StoreID, driver, AppURL, test);
							
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
			driver.findElement(By.xpath("//*[@id='go_Button']")).click();
			test.log(LogStatus.PASS, "Click on Go for History");
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			String LoanNumber = driver.findElement(By.xpath("//input[@name='dealNbr']")).getAttribute("value");
			//String LoanNumber = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[2]")).getText();
						
			test.log(LogStatus.PASS, "Captured Loan Number"+LoanNumber);
			//driver = new InternetExplorerDriver();
			driver.get(AdminURL);
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
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("ACH")).click();
			test.log(LogStatus.PASS, "Clicked on ACH");
			driver.findElement(By.linkText("Payday Loan")).click();
			test.log(LogStatus.PASS, "Clicked on PayDayLoan");
			
			driver.findElement(By.linkText("ACH Return")).click();
			test.log(LogStatus.PASS, "Clicked on ACH Return");
			
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);					  	        			   
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");

				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
			

				 int CheckBoxsize= driver.findElements(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr")).size();
				 
				for (int i =3; i <= CheckBoxsize;i++)
				{
										 
					String Value = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[1]/input[2]")).getAttribute("value");
					 
					 if (Value.contains(LoanNumber)) {
					 
						 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[1]/input[2]")).click();
						 test.log(LogStatus.PASS, "Customer Record CheckBox Selected");
						 
						 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[10]/select")).sendKeys("R01-Insufficient Funds");
						 test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");		
					 
					 break;
					 
					 }
					}
				 } 
					
				
				 /*driver.findElement(By.name("requestBean.chkName")).click();
						test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					  	        			   
						driver.findElement(By.name("rtnReasonId")).sendKeys("R01-Insufficient Funds");
						test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");*/
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
	

	public void ACHRevoke(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

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

				driver.findElement(By.name("transactionList")).sendKeys("ACH Revoke");
				test.log(LogStatus.PASS, "ACH Revoke Transaction Selected");

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

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
				Thread.sleep(5000);
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Revoke Button");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {
				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");
				driver.findElement(By.name("checkyes")).click();
				test.log(LogStatus.PASS, "ACH Revoke Transaction is Completed");


			}
		}
	}

	public void CheckStatus(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  
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



				if(ProductID.equals("PDL"))

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

				test.log(LogStatus.INFO, "transactionList as Selected : History " );

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

					test.log(LogStatus.PASS, "Click on GO Button");

				}

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2] ")).getText(); 
				test.log(LogStatus.PASS, " $$ Check Status $$ After ACH Deposit  without Doing ACH Authorization after ACH Revoke is :: " + CheckStatus);	


			}
		}
	}

	public void ACHAuthorization(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				String ABARoutingNbr = TestData.getCellData(sheetName,"ABARoutingNbr1",row);

				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr1",row);	       


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



				if(ProductID.equals("PDL"))

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

				driver.findElement(By.name("transactionList")).sendKeys("ACH Authorization");

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

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

				driver.findElement(By.name("requestBean.abaCode")).sendKeys("111111118");
				test.log(LogStatus.PASS, "SSN1 is entered: 111111118 ");
				driver.findElement(By.name("checkAbaNbrDisp")).sendKeys("111111118");
				test.log(LogStatus.PASS, "SSN1 is entered: 111111118");
				driver.findElement(By.name("requestBean.ckActNo")).sendKeys("441022");
				test.log(LogStatus.PASS, "SSN1 is entered: 441022");
				driver.findElement(By.name("checkAccountNbrDisp")).sendKeys("441022");
				test.log(LogStatus.PASS, "SSN1 is entered: 441022");
				Thread.sleep(5000);
				driver.findElement(By.name("requestBean.loanValue")).click();
				test.log(LogStatus.PASS, "Clicked on Details for Current Loan Tranactions only");
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);

				driver.findElement(By.name("bt_BankDetails")).click();
				test.log(LogStatus.PASS, "clicked on ACH Authorizatin Button");


				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {
				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("checkyes")).click();
				test.log(LogStatus.PASS, "ACH Authorization Completed");


			}
		}
	}
	public void CustomerEodS_Recoredtatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus=null;
				CheckStaus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[5]")).getText();
				if((CheckStaus).contains("Prenote"))
				{
					test.log(LogStatus.PASS,"Deposit Record is  Displayed ");
				}
				else
				{
					test.log(LogStatus.PASS,"Deposit Record is not Displayed.");
				}

			}
		}
	}
	public void  DepositStatus(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  
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



				if(ProductID.equals("PDL"))

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

				test.log(LogStatus.INFO, "transactionList as Selected : History " );

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

					test.log(LogStatus.PASS, "Click on GO Button");

				}

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String ele = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[5] ")).getText(); 

				if(ele.equals("ACH Deposit"))
				{
					test.log(LogStatus.PASS, "Deposit completed successfully " );						
				}
				else
				{
					test.log(LogStatus.FAIL, "Deposit not completed successfully " );
				}



			}
		}
	}

	public void  Check_DepositAmount(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  
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



				if(ProductID.equals("PDL"))

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

				test.log(LogStatus.INFO, "transactionList as Selected : History " );

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

					test.log(LogStatus.PASS, "Click on GO Button");

				}

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String ele = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[7] ")).getText(); 

				test.log(LogStatus.PASS, "ACH Deposit Amount is: " +ele);




			}
		}
	}

	public void Customer_CheckStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus=null;
				CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				
				test.log(LogStatus.PASS,"CheckStatus is  Displayed as::"+CheckStaus);
				

			}
		}
	}

	public void PreACH_Deposit(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;

			//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
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
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Payday Loan")).click();
				test.log(LogStatus.PASS, "Clicked on PaydayLoan");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Pre ACH Deposit")).click();
				test.log(LogStatus.PASS, "Clicked on Process Pre ACH Deposit");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);

				String DDueDate[] =DueDate.split("/");
				//String date = DDueDate[1];
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
					test.log(LogStatus.PASS, "Process Pre ACH Deposite is updated successfully.");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Pre ACH Deposite is not updated successfully.");
				}




			}
		}
	}
	public void ACH_Deposit_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String AchDeposit=null;
				String AchDepositedAmount=null;


				AchDepositedAmount=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[7]")).getText();

				test.log(LogStatus.PASS, "Ach Deposit Amount as::"+AchDepositedAmount);

				AchDeposit = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();

				if((AchDeposit).contains("ACH Deposit"))
				{
					test.log(LogStatus.PASS,"ACHDeposit Record is  Displayed " );
				}
				else
				{
					test.log(LogStatus.PASS,"ACHDeposit Record is not Displayed.");
				}

			}
		}
	}
	public void EditBorrower_Inactive(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				String Monthlydate=null;
				String Monthlydate1=null;
				WebDriverWait wait = new WebDriverWait(driver, 1000);	

				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Borrower");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");			 
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='902000']")));
				driver.findElement(By.cssSelector("li[id='902000']")).click();

    			test.log(LogStatus.PASS, "Clicked on Edit");
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
				driver.findElement(By.name("customerBean.activeFlgDisp")).sendKeys("Inactive");
				test.log(LogStatus.PASS,"Acount status is chend to Inactive");

				driver.findElement(By.name("Save")).click();							
				test.log(LogStatus.PASS, "Clicked on Save&Exit");
				Thread.sleep(1000);
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
	public void EditBorrower_Active(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				String Monthlydate=null;
				String Monthlydate1=null;



				WebDriverWait wait = new WebDriverWait(driver, 1000);	

				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Borrower");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");			 
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='902000']")));
				driver.findElement(By.cssSelector("li[id='902000']")).click();

				//driver.findElement(By.cssSelector("//*[@id='902000']/a")).click();			
				test.log(LogStatus.PASS, "Clicked on Edit");





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
				//String NextPayday =null;
				driver.findElement(By.name("customerBean.activeFlgDisp")).sendKeys("Active");
				test.log(LogStatus.PASS,"Acount status is chend to Inactive");

				driver.findElement(By.name("Save")).click();							
				test.log(LogStatus.PASS, "Clicked on Save&Exit");
				Thread.sleep(1000);
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}



				//driver.quit();	//need to change to c


			}
		}





	}
	public void CheckPartialPayment(String SSN,String FileName) throws Exception{



		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

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
				String TenderType1 = TestData.getCellData(sheetName,"TenderType1",row);

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);

				String AppURL = TestData.getCellData(sheetName,"AppURL",row);

				String UserName = TestData.getCellData(sheetName,"UserName",row);

				String Password = TestData.getCellData(sheetName,"Password",row);

				String StoreId = TestData.getCellData(sheetName,"StoreID",row);

				String SSN1 = SSN.substring(0, 3);

				String SSN2 = SSN.substring(3,5);

				String SSN3 = SSN.substring(5,9);


				this.Login(UserName,Password,StoreId);

				/*	CSRLoginpage login = new CSRLoginpage();

	login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 */
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys(TxnType);

				test.log(LogStatus.PASS, "TxnType is selected as "+TxnType);

				//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");

				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

				{

					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					//String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");

					// System.out.println(Pmt);

					if(driver.findElement(By.name("transactionDataBean.paymentAmt")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Partial Payment Option is available in TransactionList after the DueDate Completion");
					}
					else
					{
						test.log(LogStatus.PASS, "Partial Payment Option is  not available in TransactionList after the DueDate Completion");
					}
					/*
	driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();

	driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("100");

	test.log(LogStatus.PASS, "Payment Amt is entered as 100");

	driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);

	test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);

	driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("100");

	test.log(LogStatus.PASS, "Tender Amt is entered as 100");

	driver.findElement(By.name("transactionDataBean.tenderTypeSecond")).sendKeys(TenderType1);

	test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType1);

	driver.findElement(By.name("transactionDataBean.ccmoNbrSecond")).sendKeys(TenderType1);
	transactionDataBean.ccmoNbrSecond
	transactionDataBean.tenderAmtSecond
	Finish Partial Payment

	driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);

	driver.findElement(By.name("finish")).click();

	test.log(LogStatus.PASS, "Password is selected as "+Password);

	test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

	Thread.sleep(1000);

	try {

	Alert alert = driver.switchTo().alert();

	alert.accept();

	//if alert present, accept and move on.

	}

	catch (NoAlertPresentException e) {

	//do what you normally would if you didn't have the alert.

	}



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

	// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]

	if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).isDisplayed())

	{

	test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");

	driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).click();

	}

	else

	{

	test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");

	}*/



				}

			}

		}

	}

	public void LoanPartialPayment_MultiTender(String SSN,String FileName) throws Exception{



		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

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

				String TenderType1 = TestData.getCellData(sheetName,"TenderType1",row);

				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);	

				String ProductID=TestData.getCellData(sheetName,"ProductID",row);

				String AppURL = TestData.getCellData(sheetName,"AppURL",row);

				String UserName = TestData.getCellData(sheetName,"UserName",row);

				String Password = TestData.getCellData(sheetName,"Password",row);

				String StoreId = TestData.getCellData(sheetName,"StoreID",row);

				String SSN1 = SSN.substring(0, 3);

				String SSN2 = SSN.substring(3,5);

				String SSN3 = SSN.substring(5,9);


				this.Login(UserName,Password,StoreId);

				/*	CSRLoginpage login = new CSRLoginpage();

	login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 */
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys(TxnType);

				test.log(LogStatus.PASS, "TxnType is selected as "+TxnType);

				//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");

				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

				{

					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					//String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");

					// System.out.println(Pmt);

					driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();

					driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("200");

					test.log(LogStatus.PASS, "Payment Amt is entered as 200");

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);

					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);

					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("100");

					test.log(LogStatus.PASS, "Tender Amt is entered as 100");

					driver.findElement(By.name("transactionDataBean.tenderTypeSecond")).sendKeys(TenderType1);

					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType1);

					driver.findElement(By.name("transactionDataBean.ccmoNbrSecond")).sendKeys(ESign_CheckNbr);

					test.log(LogStatus.PASS, "Check Number as "+ESign_CheckNbr);

					driver.findElement(By.name("transactionDataBean.tenderAmtSecond")).sendKeys("100");

					test.log(LogStatus.PASS, "Tender2 Amt is entered as 100");


					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);

					driver.findElement(By.name("finish")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);

					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

					Thread.sleep(1000);

					try {

						Alert alert = driver.switchTo().alert();

						alert.accept();

						//if alert present, accept and move on.

					}

					catch (NoAlertPresentException e) {

						//do what you normally would if you didn't have the alert.

					}



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

					// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]

					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).isDisplayed())

					{

						test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).click();

					}

					else

					{

						test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");

					}



				}

			}

		}

	}

	public void  check_updates (String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				test.log(LogStatus.INFO, "transactionList as Selected : History " );

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

					test.log(LogStatus.PASS, "Click on GO Button");

				}

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				String ele = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[6] ")).getText(); 
				if(ele.isEmpty()){
					test.log(LogStatus.FAIL, "Multiple TenderTypes Selected For Partial Payment are not selected");

				}
				else{

					test.log(LogStatus.PASS, "Multiple TenderTypes Selected For Partial Payment are ::"+ele );						

				}

			}
		}
	}

	public void ACH_Deposit_Status(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				/*	if(ProductID.equals("LOC"))
		{*/
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

				String AchDeposit=null;

				AchDeposit = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[5]")).getText();

				if((AchDeposit).contains("ACH Deposit"))
				{
					test.log(LogStatus.PASS,"ACHDeposit Record is  Displayed " );
				}
				else
				{
					test.log(LogStatus.PASS,"ACHDeposit Record is not Displayed.");
				}

			}
		}
	}	
	public void ACH_History_Status(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				/*	if(ProductID.equals("LOC"))
		{*/
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

				String RefundRecord=null;

				RefundRecord = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[6]/td[5]")).getText();

				if((RefundRecord).contains("Refund"))
				{
					test.log(LogStatus.PASS,"Refund transaction is  Displayed " );
				}
				else
				{
					test.log(LogStatus.FAIL,"Refund transaction is not Displayed.");
				}
				String CollateralType = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[11]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Collateral type is ::"+CollateralType);
				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[4]/table/tbody/tr[11]/td/span[2]
			}
		}
	}
	public void ACH_Clear_NA(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.name("transactionList")).sendKeys("ACH Clear");
				test.log(LogStatus.PASS, "Transacationlist selected as::ACH Clear");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.chkName")).click();
				test.log(LogStatus.PASS, "Select checkbbox of Customer record to Clear");
				driver.findElement(By.name("CmdReturnPosting")).click();
				test.log(LogStatus.PASS, "Clicked on Finish ACH Clear Button");

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

				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]")).isDisplayed())
				{
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
				}		
			}
		}
	}	

	public void ACH_Clear(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(AdminURL);


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
				 driver.findElement(By.linkText("QA Jobs")).click();
			      test.log(LogStatus.PASS, "Clicked on QA Jobs");
				
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				
				String DDueDate[] =DueDate.split("/");
				Date DDueDateminus1 = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDateminus1);
				cal.add(Calendar.DATE, 8);
				Date DDueDate1= cal.getTime();
				DueDate =df.format(DDueDate1);
				String DueDate0[] =DueDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

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
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");	
								
				//New line of code
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
				
				driver.findElement(By.linkText("Payday Loan")).click();
				test.log(LogStatus.PASS, "Clicked on Payday Loan");
				driver.findElement(By.linkText("ACH Clear")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Clear");
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreID);
					test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);					  	        			   
					driver.findElement(By.name("submit")).click();
					test.log(LogStatus.PASS, "Clicked on Submit button");

					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 
					 driver.findElement(By.name("requestBean.chkName")).click();;
					 test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					
					 driver.findElement(By.name("CmdReturnPosting")).click();
						test.log(LogStatus.PASS, "Clicked on ACH Clear button");
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
		test.log(LogStatus.PASS, "ACH Clear Done Successfully");	
	}
					 
					 
				/*driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
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
				if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
				{
					test.log(LogStatus.PASS, "OK Button as Enabled");
					test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");

				}
				else
				{
					test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
				}*/
			}
		}
	}	

	
	public void ACH_ReFund(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.name("transactionList")).sendKeys("CustomerRefund");
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}	
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
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "TenderType Select as ::Cash");

				driver.findElement(By.name("finish")).click();
				test.log(LogStatus.PASS, "Clicked on Finish ACH ReFund Button");

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

				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					driver.findElement(By.name("checkyes")).click();
					test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
				}		
			}
		}
	}	


	public void ACH_Clear_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				/*	if(ProductID.equals("LOC"))
		{*/
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

				String RefundRecord=null;

				String CheckStatus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);


			}
		}
	}
	public void ACHReturn_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				/*	if(ProductID.equals("LOC"))
		{*/
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

				/*	String RefundRecord=null;

		RefundRecord = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[6]/td[5]")).getText();

		if((RefundRecord).contains("Refund"))
		{
			test.log(LogStatus.PASS,"Refund Record is  Displayed " );
		}
		else
		{
			test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
		}*/
				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
				test.log(LogStatus.PASS,"ACH Clear Transaction Completed Successfully");

			}
		}
	}	
	public void ACH_PrePayment(String SSN,String FileName) throws Exception
	{
		  Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("ACH Pre-Payment");
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
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

				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "TenderType Select as ::Cash");

				String PmtAmt = driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");

				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PmtAmt);
				test.log(LogStatus.PASS, "TenderAmount Entered is::"+PmtAmt);
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Passwor Entered as::"+Password);
				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Clicked on Finish ACH PrePayment Button");

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

				if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).isDisplayed())
				{
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
				}		
			}
		}
	}
	
	public void ACH_Prepayment_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				/*	if(ProductID.equals("LOC"))
		{*/
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

				String Prepayment=null;

				Prepayment = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();


				if((Prepayment).contains("ACH Pre-Payment"))
				{
					test.log(LogStatus.PASS,"Prepayment Record is  Displayed " );
				}
				else
				{
					test.log(LogStatus.PASS,"Prepayment Record is not Displayed.");
				}
				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
			}
		}
	}
	public void Void_PrePayment(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


				this.Login(UserName,Password,StoreId);

				/*	CSRLoginpage login = new CSRLoginpage();

	login.Login(UserName, Password, StoreId, driver, AppURL, test);
				 */
				driver.switchTo().defaultContent();

				Thread.sleep(3000);

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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					//     /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
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

				driver.findElement(By.name("transactionList")).sendKeys("void");

				test.log(LogStatus.PASS, "VOID is selected as ");

				driver.findElement(By.id("go_Button")).click();

				//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");
				if(ProductID.equals("PDL"))
				{
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					//String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
					// System.out.println(Pmt);

					driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);
					//Thread.sleep(3000);
					// Robot robot = new Robot();
					//Thread.sleep(2000);
					//robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "PIN# is entered as"+Password);
					driver.findElement(By.name("Submit22")).click();

					//test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
					// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
					//*[@id="btnADV_Yes"]
					//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

					/*	for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
			                        WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input"));*/

					//  Actions action = new Actions(driver);

					//action.moveToElement(element);

					//action.sendKeys(Keys.PAGE_DOWN).build().perform();

					// action.sendKeys(Keys.PAGE_DOWN).build().perform();

					//  action.sendKeys(Keys.PAGE_DOWN).build().perform();

					Thread.sleep(2000); 
					//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[2]
					if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[2]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Void Pre-Payment Completed Successfully");
						driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[2]")).click();
						//robot.keyPress(KeyEvent.VK_F11);
					}
					else
					{
						test.log(LogStatus.FAIL, "Void Partial Payment not Completed Successfully ");
					}


				}

			}

		}
	}
	public void Check_Amount_History1(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
				String Transaction  = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[5]/td[5]")).getText();

				String PrepaymentAmt=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[5]/td[7]")).getText();

				if((Transaction).contains("ACH Pre-Payment"))
				{
					test.log(LogStatus.PASS,"Transaction is displayed as :" +Transaction);
					test.log(LogStatus.PASS,"Transaction amount is displayed as :" +PrepaymentAmt );
				}
			}
		}
	}
	
	public void ACHReturnPostingWithoutR01R09(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				//driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				
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
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
			
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");
				driver.findElement(By.linkText("Payday Loan")).click();
				test.log(LogStatus.PASS, "Clicked on PayDayLoan");

				driver.findElement(By.linkText("ACH Return")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Return");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(1000);
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


				driver.findElement(By.name("requestBean.chkName")).click();;
				test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					  	        			   
				//Click Login Button
				driver.findElement(By.name("rtnReasonId")).sendKeys("R02-Account Closed");
				test.log(LogStatus.PASS, "Return Reason Selected as ::  R02-Account Closed");
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
	
	public void ACHEffectiveDate_AgeStore(String SSN,String FileName,int days) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  
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

				this.Login(UserName,Password,StoreID);

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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				test.log(LogStatus.INFO, "transactionList as Selected : History " );

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

					test.log(LogStatus.PASS, "Click on GO Button");

				}

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='achHistoryTable']/tbody/tr[2]/td[4]")).getText();
				test.log(LogStatus.PASS, "Captured ACH Effective Date"+DueDate);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

				driver.get(AdminURL);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
				 driver.findElement(By.linkText("QA Jobs")).click();
			      test.log(LogStatus.PASS, "Clicked on QA Jobs");
				
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				
				String DDueDate[] =DueDate.split("/");

				//String date = DDueDate[1];

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

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				test.log(LogStatus.PASS, "Click on Store Edit Box");


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

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}
	
	public void FutureDeposit_RecoredSataus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus=null;
				CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				test.log(LogStatus.PASS,"Check status is."+CheckStaus);
				test.log(LogStatus.PASS,"ACH Deposit Record is not Displayed.");
			}
		}
	}

	public void ACHClear_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
				test.log(LogStatus.PASS,"ACH Clear Transaction Completed Successfully");

			}
		}
	}

	public void  Clear_Status (String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);  
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

				test.log(LogStatus.INFO, "transactionList as Selected : History " );

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.id("go_Button")).click();

					test.log(LogStatus.PASS, "Click on GO Button");

				}

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				String Clear = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5] ")).getText(); 

				test.log(LogStatus.PASS, "ACH  Clear Record is Displayed as :: "+Clear);

			}
		}
	}

	public void AgeStore_ACH(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(AdminURL);


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
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
				 driver.findElement(By.linkText("QA Jobs")).click();
			      test.log(LogStatus.PASS, "Clicked on QA Jobs");
				
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
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
	
	public void Perform_EPP (String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);	
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				this.Login(UserName,Password,StoreId);	
				//driver.switchTo().defaultContent();		
				//Thread.sleep(5000);				
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
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				if(ProductID.equals("TLP"))
				{
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
				Thread.sleep(5000);
				driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				driver.findElement(By.id("go_Button")).click();
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
				driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).click();
				test.log(LogStatus.PASS, "Click on Next");
				if(ProductID.equals("PDL"))
				{

					driver.findElement(By.name("chkNbr")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Chek number is entered as "+ESign_CheckNbr);
					driver.findElement(By.name("chkgAcctNbr")).sendKeys(last4cheknum);
					test.log(LogStatus.PASS, "Checking Account Nbr(Last 4 digits Only) is entered as "+last4cheknum);

					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("submitBtn")).click();
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
					if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
						driver.findElement(By.name("checkyes")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
					}
				}

				if(ProductID.equals("TLP"))
				{
					driver.findElement(By.xpath("//*[@id='PD3']")).click();
					test.log(LogStatus.PASS, " Pay Off the balance is selected ");
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					String Pmt= driver.findElement(By.name("payOff")).getAttribute("value");						 
					System.out.println(Pmt);						 
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Pmt);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					driver.findElement(By.name("finish")).click();											 						 
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

					if(driver.findElement(By.name("Ok")).isDisplayed())
					{
						test.log(LogStatus.PASS, "EPP Loan is Completed Successfully ");
						driver.findElement(By.name("Ok")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "EPP Loan is not Completed Successfully ");
					}
				}												
			}

		}
	}

	public void AgeStore1stinst(String SSN,String FileName,int days) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				if(ProductID.equals("PDL"))

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

				String DueDate1=null;
				//String LoanAmount = null;

				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				//DueDate1 = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate1 = driver.findElement(By.xpath("//*[@id='payPlanHistoryTable']/tbody/tr[3]/td[3]")).getText();


				//*[@id="payPlanHistoryTable"]/tbody/tr[3]/td[3]

				//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture 1stinstdueDate"+DueDate1);

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate1);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

				driver.get(AdminURL);

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =DueDate1.split("/");

				//String date = DDueDate[1];

				Date DDueDateminus1 = df.parse(DueDate1);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE,days);

				Date DDueDate1= cal.getTime();

				DueDate1 =df.format(DDueDate1);

				String DueDate0[] =DueDate1.split("/");

				String DueDate12 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

				}

				else

				{

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}

	public void AgeStore2ndinst(String SSN,String FileName,int days) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				if(ProductID.equals("PDL"))

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

				String DueDate2=null;
				//String LoanAmount = null;

				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				//DueDate2 = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate2 = driver.findElement(By.xpath("//*[@id='payPlanHistoryTable']/tbody/tr[4]/td[3]")).getText();

				//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture 2ndinstDueDate"+DueDate2);

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate2);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

				driver.get(AdminURL);

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

				driver.findElement(By.linkText("Borrower")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =DueDate2.split("/");

				//String date = DDueDate[1];

				Date DDueDateminus1 = df.parse(DueDate2);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE,days);

				Date DDueDate1= cal.getTime();

				DueDate2 =df.format(DDueDate1);

				String DueDate0[] =DueDate2.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate13 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

				}

				else

				{

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}
	
	public void CheckPartialPaymentinDropdown(String SSN,String FileName) throws Exception{

		

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

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
		
		String TenderType1 = TestData.getCellData(sheetName,"TenderType1",row);
		
		String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);	

		String ProductID=TestData.getCellData(sheetName,"ProductID",row);

		String AppURL = TestData.getCellData(sheetName,"AppURL",row);

		String UserName = TestData.getCellData(sheetName,"UserName",row);

		String Password = TestData.getCellData(sheetName,"Password",row);

		String StoreId = TestData.getCellData(sheetName,"StoreID",row);

		String SSN1 = SSN.substring(0, 3);

		String SSN2 = SSN.substring(3,5);

		String SSN3 = SSN.substring(5,9);

		
		this.Login(UserName,Password,StoreId);
		
	/*	CSRLoginpage login = new CSRLoginpage();

		login.Login(UserName, Password, StoreId, driver, AppURL, test);
	*/
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

		

		if(ProductID.equals("PDL"))

		{

		///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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
		

		//driver.findElement(By.name("transactionList")).sendKeys(TxnType);
		
		
		int ele= driver.findElements(By.name("transactionList")).size();
		
		for(int i=1;i<=ele;i++)
		{
			String trans_name=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getText();
			if(trans_name.equalsIgnoreCase("Partial Payment"))
			{
				test.log(LogStatus.PASS, "Partial Payment Transaction Is Displayed in Transaction List");	

			}
			else 
			{
				test.log(LogStatus.PASS, "Partial Payment Transaction Is not Displayed in Transaction List");	

			}
		}
		
		
	

		}

		}

		}
	

	public void AgeStore3rdinst(String SSN,String FileName,int days) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				if(ProductID.equals("PDL"))

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

				String DueDate2=null;
				//String LoanAmount = null;

				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				//DueDate2 = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate2 = driver.findElement(By.xpath("//*//*[@id='payPlanHistoryTable']/tbody/tr[5]/td[3]")).getText();

				//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture 2ndinstDueDate"+DueDate2);

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate2);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

				driver.get(AdminURL);

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

				driver.findElement(By.linkText("Borrower")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =DueDate2.split("/");

				//String date = DDueDate[1];

				Date DDueDateminus1 = df.parse(DueDate2);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE,days);

				Date DDueDate1= cal.getTime();

				DueDate2 =df.format(DDueDate1);

				String DueDate0[] =DueDate2.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate13 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

				}

				else

				{

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}



	public void AgeStore4rthinst(String SSN,String FileName,int days) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				if(ProductID.equals("PDL"))

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

				String DueDate=null;
				//String LoanAmount = null;

				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='payPlanHistoryTable']/tbody/tr[6]/td[3]")).getText();
				//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture 4rh inst DueDate"+DueDate);

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

				driver.get(AdminURL);

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

				driver.findElement(By.linkText("Borrower")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =DueDate.split("/");

				//String date = DDueDate[1];

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE,days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

				}

				else

				{

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}

	public void AgeStore5thinst(String SSN,String FileName,int days) throws Exception

	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys("History");

				if(ProductID.equals("PDL"))

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

				String DueDate=null;
				//String LoanAmount = null;

				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='payPlanHistoryTable']/tbody/tr[7]/td[3]")).getText();
				//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture 5th inst DueDate"+DueDate);

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

				System.out.print(DueDate);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();

				driver.get(AdminURL);

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

				driver.findElement(By.linkText("Borrower")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				String DDueDate[] =DueDate.split("/");

				//String date = DDueDate[1];

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE,days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

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

					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

				}

				else

				{

					test.log(LogStatus.PASS, "Process Date not updated successfully");

				}



			}
		}
	}


	public void RPPPayment (String SSN,String FileName) throws Exception{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);    	
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
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String TenderType1 = TestData.getCellData(sheetName,"TenderType1",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);

				this.Login(UserName,Password,StoreId);	
				driver.switchTo().defaultContent();		
				//Thread.sleep(5000);
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
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//driver.findElement(By.xpath("//input[@name='button'][@value='Go']")).click();
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				if(ProductID.equals("TLP"))
				{
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
				Thread.sleep(5000);
				driver.findElement(By.name("transactionList")).sendKeys("RPP Payment");
				test.log(LogStatus.PASS, "Transaction Type is selected as RPP Payment" );	
				driver.findElement(By.id("go_Button")).click();
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("PDL"))
				{

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					String Pmt= driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");						 
					System.out.println(Pmt);						 
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					driver.findElement(By.name("transactionDataBean.chkNbr")).sendKeys("123123");

					//driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
					driver.findElement(By.name("transactionDataBean.chkgAcctNbr")).sendKeys(last4cheknum);							 


					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

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
					if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, " 1st inst RPPPayment is Completed Successfully ");
						driver.findElement(By.name("checkyes")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "1st inst RPPPayment is not Completed Successfully ");
					}
				}

				if(ProductID.equals("TLP"))
				{
					driver.findElement(By.xpath("//*[@id='PD3']")).click();
					test.log(LogStatus.PASS, " Pay Off the balance is selected ");
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					String Pmt= driver.findElement(By.name("payOff")).getAttribute("value");						 
					System.out.println(Pmt);						 
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Pmt);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					driver.findElement(By.name("finish")).click();											 						 
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

					if(driver.findElement(By.name("Ok")).isDisplayed())
					{
						test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
						driver.findElement(By.name("Ok")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
					}
				}												
			}

		}
	}
	public void RPPPerform (String SSN,String FileName) throws Exception{



		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);

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


				this.Login(UserName,Password,StoreId);

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



				if(ProductID.equals("PDL"))

				{

					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();

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

				driver.findElement(By.name("transactionList")).sendKeys(TxnType);

				test.log(LogStatus.PASS, "TxnType is selected as "+TxnType);

				if(ProductID.equals("PDL"))

				{

					driver.findElement(By.name("button")).click();

				}


				try {

					Alert alert = driver.switchTo().alert();

					alert.accept();

					//if alert present, accept and move on.

				}

				catch (NoAlertPresentException e) {

					//do what you normally would if you didn't have the alert.

				}
				//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");



				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				if(ProductID.equals("PDL"))

				{


					driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked on Next");

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");


					driver.findElement(By.name("password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);


					driver.findElement(By.name("submitBtn")).click();				
					test.log(LogStatus.PASS, "Clicked on Finish RPP");

					Thread.sleep(1000);			


					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]

					if (driver.findElement(By.xpath("//*[@id='confirmEsign']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[4]/td/b")).isDisplayed())
						//*[@id="confirmEsign"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[4]/td/b
					{

						test.log(LogStatus.PASS, "RPP Completed Successfully ");

						driver.findElement(By.xpath("//*[@id='OKBut']")).click();
						//*[@id="OKBut"]
					}

					else

					{

						test.log(LogStatus.FAIL, "RPP not Completed");

					}



				}

			}

		}

	}
	public void ACH_ReFund_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String RefundRecord=null;

				RefundRecord = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[6]/td[5]")).getText();

				if((RefundRecord).contains("Refund"))
				{
					test.log(LogStatus.PASS,"Transaction Status is  Displayed as::"+RefundRecord );
				}
				else
				{
					test.log(LogStatus.FAIL,"CustomerRefund Record is not Displayed.");
				}
				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
			}
		}
	}	
	public void ACH_PartialPrePayment(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("ACH Pre-Payment");
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
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
				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("100");
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "TenderType Select as ::Cash");
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("100");
				test.log(LogStatus.PASS, "TenderAmount Entered is::100");
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Passwor Entered as::"+Password);
				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Clicked on Finish ACH PrePayment Button");

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

				if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).isDisplayed())
				{
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
				}		
			}
		}
	}
	
	
	public void writeoff_Process(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;

				DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture Returndate"+DueDate);

				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(AdminURL);

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
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
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

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
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
				if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
				{
					test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
				}
				else
				{
					test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
				}
				//driver.close();
			}
		}
	}

	public void Refund_Status(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				driver.findElement(By.name("transactionList")).sendKeys("Refund");
				test.log(LogStatus.PASS, "Refund Transaction is Not Available");			
			}
		}
	}

	public void LoanStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				
				test.log(LogStatus.PASS,"Loan Status is :"+LoanStatus);
			}
		}
	}	

	public void WriteoffBatchProcess(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;

				//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

				DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[5]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture Returndate"+DueDate);
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(AdminURL);
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


				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
			test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS); */

				String DDueDate[] =DueDate.split("/");
				//String date = DDueDate[1];
				Date DDueDateminus1 = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDateminus1);
				cal.add(Calendar.DATE, -1);
				Date DDueDate1= cal.getTime();
				DueDate =df.format(DDueDate1);
				String DueDate0[] =DueDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];



				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");



				Thread.sleep(5000);
				// driver.switchTo().defaultContent();
				//driver.switchTo().frame("topFrame");
				//driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				//test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
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
				if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
				{
					test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
				}
				else
				{
					test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
				}
				//driver.close();
			}



		}




	}
	public void ACHPayment(String SSN,String FileName,double d) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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
				driver.findElement(By.name("transactionList")).sendKeys("ACH Payment");
				if(ProductID.equals("PDL"))
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
				String TotAmt =driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");
				double RegAmt = Double.parseDouble(TotAmt);
				double PerAmt = RegAmt*(d);
				String ReqAmt = Double.toString(PerAmt);
				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(ReqAmt);
				test.log(LogStatus.PASS, "Percentage Amount Selected is ::"+d);
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "TenderType is Selected is ::Cash");
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(ReqAmt);
				test.log(LogStatus.PASS, "TenderAmount is Selected is ::"+ReqAmt);
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is Selected is ::"+Password);
				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Clicked on Finish ACH Payment Button");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				driver.findElement(By.name("checkyes")).click();
				test.log(LogStatus.PASS, "ACH Payment Completed");


			}
		}
	}
	public void WriteOff(String SSN,String FileName,int Days) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				if(ProductID.equals("PDL"))
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
				String DueDate=null;


				DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[4]")).getText();
				test.log(LogStatus.PASS, "Capture Returndate"+DueDate);

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(AdminURL);
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


				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
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
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);

				String DDueDate[] =DueDate.split("/");
				//String date = DDueDate[1];
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
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		Thread.sleep(5000);
		driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
		test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS); */


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");



				Thread.sleep(5000);
				// driver.switchTo().defaultContent();
				//driver.switchTo().frame("topFrame");
				//driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				//test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
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
				if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
				{
					test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
				}
				else
				{
					test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
				}
				//driver.close();
			}



		}


	}
	public void WriteOff_History(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
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
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

				test.log(LogStatus.PASS,"Check Status is ::"+CheckStatus);

				String LoanStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				if(LoanStatus.contains("Write Off"))
				{
					test.log(LogStatus.PASS,"Loan Status is ::"+LoanStatus);
					test.log(LogStatus.PASS,"WriteOff Transaction Completed Successfully");

				}


			}
		}
	}
	public void NSF_Void(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   	
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
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
				driver.findElement(By.cssSelector("li[id='910000']")).click();	
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

				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				if(ProductID.equals("TLP"))
				{
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				}
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
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");	
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
					driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
				}
				if(ProductID.equals("TLP"))
				{
					driver.findElement(By.name("tenderType")).sendKeys(TenderType);
				}
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();
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
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[2]")).click();

					test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
				}
			}
		}

	}

	public void CustomerEOD_PreNoteRecord(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
				/*	if(ProductID.equals("LOC"))
			{*/
				//*[@id="go_Button"]
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/*int CheckStausTable=0;
		//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
			CheckStausTable = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]")).
			//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
			if((CheckStausTable).e)
			{
				test.log(LogStatus.PASS,"Deposit Record is  Displayed ");
			}*/

				test.log(LogStatus.PASS,"Deposit Record is not Displayed.");


			}
		}
	}

	public void Prepaymentfull (String SSN,String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);    	
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
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				this.Login(UserName,Password,StoreId);	
				driver.switchTo().defaultContent();		
				//Thread.sleep(5000);
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
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//driver.findElement(By.xpath("//input[@name='button'][@value='Go']")).click();
				if(ProductID.equals("PDL"))
				{
					driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				if(ProductID.equals("TLP"))
				{
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
				Thread.sleep(5000);
				driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				test.log(LogStatus.PASS, "Transaction Type is selected as: "+TxnType);	
				driver.findElement(By.id("go_Button")).click();
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("PDL"))
				{

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					////String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						 
					String Pmt= driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");						 
					System.out.println(Pmt);						 
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();
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

					//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]


					if( driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).isDisplayed())
					{									        								

						driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).click();

						test.log(LogStatus.PASS, "ACHFullPrePayment is Completed Successfully ");

					}
					else
					{
						test.log(LogStatus.FAIL, "ACHFullPrePayment is not Completed Successfully.");
					}


				}

				if(ProductID.equals("TLP"))
				{
					driver.findElement(By.xpath("//*[@id='PD3']")).click();
					test.log(LogStatus.PASS, " Pay Off the balance is selected ");
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is selected as "+TenderType);
					Thread.sleep(5000);
					String Pmt= driver.findElement(By.name("payOff")).getAttribute("value");						 
					System.out.println(Pmt);						 
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Pmt);						 
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					driver.findElement(By.name("finish")).click();											 						 
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

					if(driver.findElement(By.name("Ok")).isDisplayed())
					{
						test.log(LogStatus.PASS, "BuyBack Loan is Completed Successfully ");
						driver.findElement(By.name("Ok")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "BuyBack Loan is not Completed Successfully ");
					}
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
	
	@Test (priority=52)

	public void PartialPayment_ACHDeposit_ACHPrePayment() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_PartialPayment_ACHDeposit_ACHPrePayment_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("AA_PartialPayment_ACHDeposit_ACHPrePayment"+Header, "PartialPayment_ACHDeposit_ACHPrePayment_Txn");
				appUrl = AppURL;
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,-2);
				this.LoanPartialPayment(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.ACH_PrePayment(SSN, FileName);
				this.Check_Amount_History1(SSN, FileName);
			}
		}
	}

	@Test (priority=53)

	public void Nacha_ACHDepositProcess_ACHReturn() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Nacha_DepositProcess_ReturnPosting_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:53"+"_"+PayFrequency+"_"+CollateralType,"Loan (ACH)_Nacha(EOD)_Ach Deposit process_Ach Return posting");
				test = reports.startTest("AA_DepositProcess_PaylianceProcess_ReturnPosting"+Header, "Loan (ACH)_EOD_Ach Deposit process_Ach Return posting");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName); 
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName); 
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPosting(SSN, FileName);

			}
		}
	}

	@Test (priority=54)

	public void ConvertCheckToACHNacha_ACHDepositProcess_ACHReturn() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_ConvertCheckToACHNacha_DepositProcess_ReturnPosting_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:54"+"_"+PayFrequency+"_"+CollateralType,"Loan (Check)_(Convert to ACH) Ach Deposit process_Ach Return Posting");
				test = reports.startTest("AA_ConvertCheckToACH_DepositProcess_Payliance_ReturnPosting"+Header, "Loan (Check)_(Convert to ACH) Ach Deposit process_Ach Return Posting");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName); 
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName); 
				this.Safeassign(SSN, FileName); 
				this.Drawerassign(SSN, FileName); 
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPosting(SSN, FileName);
		
			}
		}
	}
	
	@Test (priority=55)

	public void Loan_Deposit_PrePayment_Return() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_DepositProcess_PrePayment_ReturnPosting_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:55"+"_"+PayFrequency+"_"+CollateralType,"New Loan_Age the store upto duedate_perform deposit_perform ACH Prepayment_ACH Return");
				test = reports.startTest("AA_DepositProcess_PrePayment_ReturnPosting"+Header, "New Loan_Age the store upto duedate_perform deposit_perform ACH Prepayment_ACH Return");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName); 
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName); 
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName); 
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_PrePayment(SSN, FileName);
				this.ACHReturnPosting(SSN, FileName);
				}
		}
	}
	
	@Test (priority=56)

	public void LoanwithCheck_Deposit_ReturnwithoutR01R09_FutureDepositNotposted() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_NewLoan(check)_Deposit_ReturnPostingwithout(R01R09)_FetureDepositnotPost_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:56"+"_"+PayFrequency+"_"+CollateralType,"loan with check->deposit_return with return reason id other than R01,R09 then should not allow for future deposit");
				test = reports.startTest("AA_NewLoan(check)_Deposit_ReturnPostingwithout(R01R09)_FutureDepositnotPost"+Header, "loan with check->deposit_return with return reason id other than R01,R09 then should not allow for future deposit");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName); 
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName); 
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName); 
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPostingWithoutR01R09(SSN, FileName); 
				this.ACHEffectiveDate_AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);  
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.FutureDeposit_RecoredSataus(SSN, FileName);

			}
		}

	}

	@Test (priority=57)

	public void LoanwithACH_ACHDepProc_ProcessClear() throws Exception {

		// Start test. Mention test script name
		String FileName= "LoanwithACH_ACHDepProc_ACHClear_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:57"+"_"+PayFrequency+"_"+CollateralType, "LoanwithACH_ACHDepositProcess__ACHClear");
				test = reports.startTest("AA_LoanwithACH_ACHDepProc_PaylianceProcess_ProcessClear"+Header, "LoanwithACH_ACHDepositProcess__ACHClear");
				appUrl = AppURL;
				
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_Clear(SSN, FileName);
				this.ACHClear_History(SSN, FileName);

			}
		}
		
	}
	
	@Test (priority=58)

	public void NewLoan_PreNoteDeposit_PreNoteClear() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_NewLoan_PreNoteDeposit_PreNoteClearTestData.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:58"+"_"+PayFrequency+"_"+CollateralType, "Loan(ACH/Check)_PreNoteDeposit_PreNoteClear");
				test = reports.startTest("AA_NewLoan_PreNoteDeposit_PreNoteClear"+Header, "Loan(ACH/Check)_PreNoteDeposit_PreNoteClear");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, -7);
				this.PrenoteDeposit_6DaysBeforeDuedate(SSN, FileName, -7);
				this.AgeStore(SSN, FileName, -1);
				this.PrenoteClear_BeforeDuedate(SSN, FileName, -1);   
				this.Clear_Status(SSN, FileName);
				}
		}
	}
	
	@Test (priority=59)

	public void LoanwithCheck_ConverttoACH_ACHDepproc_ProcClear() throws Exception {

		// Start test. Mention test script name
		String FileName= "LoanwithCheck_ConverttoACH_ACHDepproc_ProcClear_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		//int lastrow=TestData.getLastRow("Borrower");
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:59"+"_"+PayFrequency+"_"+CollateralType, "LoanWithCheck__ConverttoACH_AchDepositprocess_ProcessClear");
				test = reports.startTest("AA_LoanwithCheck_ConvertToACH_ACHDepproc_ProcClear"+Header, "LoanWithCheck__ConverttoACH_AchDepositprocess_ProcessClear");
				appUrl = AppURL; 

				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_Clear(SSN, FileName);
				this.ACHClear_History(SSN, FileName);
			}
		}
	}

	@Test (priority=60)

	public void Loan_Agestoreduedate_performdepositPrepayment_agestore_ProcessClear() throws Exception {

		// Start test. Mention test script name
		String FileName= "Loan_AgeStoreduedate_Performdeposit_ACHPrepayment_ProcessClear_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:60"+"_"+PayFrequency+"_"+CollateralType, "Loan_AgeStoreduedate_Performdeposit_ACHPrepayment_ProcessClear_Txn");
				test = reports.startTest("AA_NewLoan_AgeStoreduedate_Performdeposit_ACHPrepayment_ProcessClear"+Header, "Loan_AgeStoreduedate_Performdeposit_ACHPrepayment_ProcessClear_Txn");
				appUrl = AppURL; 
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_PrePayment(SSN, FileName);
				this.AgeStore_ACH(SSN, FileName, 9);
				this.ACH_Clear(SSN, FileName);
				this.ACHClear_History(SSN, FileName);
			}
		}
	}

	//--@Test (priority=61) //EPP not applicable for FLPDL

	public void NewLoan_Perform_EPP() throws Exception {

		// Start test. Mention test script name
		String FileName= "NewLoan_Perform_EPP_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//test = reports.startTest(Header+"_S.No:61"+"_"+PayFrequency+"_"+CollateralType, "NewLoan_Perform_EPP");
				test = reports.startTest("NewLoan_Perform_EPP"+Header, "NewLoan_Perform_EPP");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,-5);
				this.Perform_EPP(SSN, FileName);			        
		}
		}
	}

	//--@Test (priority=62)

	public void RPP_MissRPPPayment_EOD_Checkbox() throws Exception {

		// Start test. Mention test script name
		String FileName= "RPP_MissRPPPayment_EOD_Checkbox_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				test = reports.startTest("RPP_MissRPPPayment_EOD_Checkbox_Txn_"+Header, "RPP_MissRPPPayment_EOD_Checkbox_Txn");
				//test = reports.startTest(Header+"_S.No:62"+"_"+PayFrequency+"_"+CollateralType, "RPP_MissRPPPayment_EOD_Checkbox_Txn");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,-2);
				this.RPPPerform(SSN, FileName);				           
				this.AgeStore1stinst(SSN, FileName, 3);
				this.DrawerDeassign(SSN, FileName);				          
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
			}
		}
	}

	//--@Test (priority=63)

	public void RPP_MissRPPPayment_EOD_Forcefully() throws Exception {

		// Start test. Mention test script name
		String FileName= "RPP_MissRPPPayment_EOD_Forcefully_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("EPP_MissRPPPayment_EOD_Forcefully_Txn_"+Header, "Loan-RPP- Miss the EPP Payment --> Age the store to 14 days from RPP due date --> Perform EOD --> Customer record should display in EOD process and should comes out forcefully from RPP.");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,-2);
				this.RPPPerform(SSN, FileName);				           
				this.AgeStore2ndinst(SSN, FileName, -14);
				this.DrawerDeassign(SSN, FileName);				          
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
			}
		}
	}
	
	//--@Test (priority=64)

	public void Newloan_PartialPayment_Deposit_Clear_Refund() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Newloan_PartialPayment_Deposit_Clear_Refund.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("AA_Newloan_PartialPayment_Deposit_Clear_Refund"+Header, "Loan_Partial Payment_Deposit_Clear_Refund");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,-6);
				this.LoanPartialPayment(SSN, FileName);
				this.AgeStore(SSN, FileName,0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_Clear(SSN, FileName);
				this.ACH_ReFund(SSN, FileName);
				this.ACH_ReFund_History(SSN, FileName);
			}
		}
	}

	@Test (priority=65)

	public void Loan_Deposit_FullPrePayment_Clear_Refund() throws Exception {

		// Start test. Mention test script name
		String FileName= "Loan_Deposit_FullPrePayment_Clear_Refund_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		//int lastrow=TestData.getLastRow("Borrower");
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
				test = reports.startTest("Loan_Deposit_FullPrePayment_Clear_Refund"+Header, "Loan_Deposit_Pre Payment full _Clear_Refund	");
				appUrl = AppURL; 

				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_PrePayment(SSN, FileName);
				this.ACH_Clear(SSN, FileName);
				this.ACH_ReFund(SSN, FileName);
				this.ACH_ReFund_History(SSN, FileName);
			}
		}
	}

	@Test (priority=66)

	public void LoanWithCheck_ConvertAch_AchPrePayment_AchClr_Refund() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LoanWithCheck_ConvertAch_AchPrePayment_AchClr_Refund_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("LoanWithCheck_ConvertAch_AchPrePayment_AchClr_Refund"+Header, "Loan With Check_Deposit (Convert to ACH)_ACH Pre Payment Partially_ACH CLR_Refund");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACH_PartialPrePayment(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.ACH_Clear(SSN, FileName);
				this.ACH_ReFund(SSN, FileName);
				this.ACH_History_Status(SSN, FileName);
			}
		}
	}

	@Test (priority=67)

	public void NewLoan_ReturnPosting_60days_Writeoff() throws Exception {

		// Start test. Mention test script name
		String FileName= "NewLoan_ReturnPosting_60days_Writeoff_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("NewLoan_ReturnPosting_60days_Writeoff_Txn"+Header, "Loan -> Return Posting -> WO Check whether Write off is done 60days from return date.");
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);						     
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPosting(SSN, FileName);
				this.writeoff_Process(SSN, FileName, 60);					      
				this.LoanStatus(SSN, FileName);

			}
		}
	}

	@Test (priority=68)

	public void Loan_ReturnPosting_15PerofTotalDue__CheckWriteOff120daystoretdate() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_ReturnPosting_15PerofTotalDue__CheckWriteOff120daystoretdate_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("Loan_ReturnPosting_15PerofTotalDue__CheckWriteOff120daystoretdate"+Header, "Loan__ReturnPosting___NSFPayment15PercentofTotalDue__WOCheckwhetherWriteoffisdone120daysfromreturndate");
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPosting(SSN, FileName);
				this.ACHPayment(SSN, FileName, 0.15);
				//this.WriteOff(SSN, FileName, 121);
				this.writeoff_Process(SSN, FileName, 121);	
				this.WriteOff_History(SSN, FileName);
			}
		}
	}

		@Test (priority=69)

	public void Loan_ReturnPosting_NSF15_Void_WO60days_Returndate() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_ReturnPosting_NSF15_Void_WO60days_Returndate_TestData.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("Loan_ReturnPosting_NSF15_Void_WO60days_Returndate_TestData"+Header, "Loan->Return Posting_NSF Payment 15% of Total Due_Void_WO Check whether Write off is done 60 days from return date.");
				appUrl = AppURL;
				this.Login(UserName,Password,StoreId);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPosting(SSN, FileName);
				this.ACHPayment(SSN, FileName, 0.15);
				this.NSF_Void(SSN, FileName);
				//this.WriteOff(SSN, FileName, 60);
				this.writeoff_Process(SSN, FileName, 60);	
				this.WriteOff_History(SSN, FileName);
			}
		}
	}

	@Test (priority=70)

	public void Loan_ReturnPosting_30PerofTotalDue__CheckWriteOff180daystoretdate() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_ReturnPosting_30PerofTotalDue__CheckWriteOff180daystoretdate_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("Loan_ReturnPosting_30PerofTotalDue__CheckWriteOff180daystoretdate"+Header, "Loan__ReturnPosting___NSFPayment30PercentofTotalDue__WOCheckwhetherWriteoffisdone180daysfromreturndate");
				appUrl = AppURL;
				this.Login(UserName,Password,StoreId);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				this.ACHReturnPosting(SSN, FileName);
				this.ACHPayment(SSN, FileName, 0.3);
				//this.WriteOff(SSN, FileName, 181);
				this.writeoff_Process(SSN, FileName, 180);
				this.WriteOff_History(SSN, FileName);
			}
		}
	}

	//--@Test (priority=71)

	public void NewLoan_RPP_AgeInstwise_PPPayment() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_NewLoan_Instwise_PerformRPP_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("AA_NewLoan_Instwise_PerformRPP_Txn_"+Header, "AA_NewLoan_Instwise_PerformRPP_Txn");
				appUrl = AppURL;
				this.Login(UserName,Password,StoreId);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,-2);
				this.RPPPerform(SSN, FileName);				           
				this.AgeStore1stinst(SSN, FileName, 0);
				this.RPPPayment(SSN, FileName);
				this.AgeStore2ndinst(SSN, FileName, 0);
				this.RPPPayment(SSN, FileName);
				this.AgeStore3rdinst(SSN, FileName, 0);
				this.RPPPayment(SSN, FileName);
				this.AgeStore4rthinst(SSN, FileName, 0);
				this.RPPPayment(SSN, FileName);
				this.AgeStore5thinst(SSN, FileName, 0);
				this.RPPPayment(SSN, FileName);  
			}
		}
	}

	//--@Test (priority=72) -- Not applicable
	public void NewLoan_DueDate_Holiday_Verification() throws Exception {

		// Start test. Mention test script name

		String FileName= "AA_NewLoan_Duedate_Holiday_Verification_Txn_Testdata.xls";

		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   

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
				this.Login(UserName,Password,StoreId);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);

			}

		}


	}

	@Test (priority=73)

	public void Loan_Deposit_PrePaymentfull_Return_RefundnotDisplay() throws Exception {

		// Start test. Mention test script name
		String FileName= "Loan_Deposit_PrePaymentfull_Return_RefundnotDisplay_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/FL_PDL_Regression/"+FileName);   
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
				test = reports.startTest("Loan_Deposit_PrePaymentfull_Return_RefundnotDisplay_Txn"+Header, "Loan_Deposit_PrePaymentfull_Return_RefundnotDisplay_Txn");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_PDL(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);						     
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				//this.NACHA(SSN, FileName, 0);
				this.Payliance(SSN, FileName, 0);
				//this.Prepaymentfull(SSN, FileName);
				this.ACH_PrePayment(SSN, FileName);
				this.ACHReturnPosting(SSN, FileName);						   
				this.Refund_Status(SSN, FileName);
			}
		}
	}

	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		 if(ITestResult.FAILURE == result.getStatus()){
			
			String screenshotPath = FLPDL_ExecutionScripts.getScreenhot(driver, result.getName());
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
	     driver.close();
		 }
	
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);		 
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/ExecutionReports/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }

	 
	@AfterClass
	 
public void closeBrowser() throws Exception{
		    
	    driver.quit();
	    
	}
}









