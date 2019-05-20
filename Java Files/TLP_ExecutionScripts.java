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
import org.openqa.selenium.Keys;
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
import qfundx.MyCSRLoginpage;


public class TLP_ExecutionScripts {
	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	
	@BeforeClass
	public void Initialize() throws IOException {	
		
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		String filename="AA_TLP_Scenarios_"+timestamp+".html";
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/TLP_Scenarios/"+filename, true);
		reports.addSystemInfo("Browser Version","IE 11.0");
	}
	
	@BeforeMethod
	 public void killProcess() throws Exception

	      {

	         Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
	          Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
	          Thread.sleep(5000); //Allow OS to kill the process
	          System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		  		driver = new InternetExplorerDriver();
		  		driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
    
    
    driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
    Assert.assertTrue(true);
    test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
    //Click Login Button
    driver.findElement(By.name(Login)).click();
    Assert.assertTrue(true);
    test.log(LogStatus.PASS, "Clicked on Submit button");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    if(driver.getPageSource().contains("clockForm"))
	{					
	WebElement Clockouttime  = driver.findElement(By.name("clockRequestBean.clockOutTime"));
	Clockouttime.click();
	Clockouttime.clear();
	Clockouttime.sendKeys("2350");
	driver.findElement(By.name("clock")).click();
	Thread.sleep(2000);
	driver.findElement(By.name("clock")).click();
	}

	Thread.sleep(2000);
}
public void NewLoan(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Initiating New Loan");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);  	
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
			String ABA_Number = TestData.getCellData(sheetName,"ABARoutingNbr",row);		
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
				
				if(ProductID.equals("TLP"))							
				{					
					
					//Thread.sleep(2000);
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

					//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
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
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);

					}
					if(StoreID.equals("1343"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}

				}

				driver.findElement(By.name("ShareScreenBtn")).click();
				test.log(LogStatus.PASS, "ShareScreen Button clicked");
				
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
				//New Loan Screen

				if(ProductID.equals("TLP"))
				{	
					String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
					String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
					String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
					String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
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
					driver.findElement(By.name("button1")).click();
					test.log(LogStatus.PASS, "click on Update 1 button ");
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
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
					Thread.sleep(2000);
					driver.findElement(By.name("button2")).click();	
					test.log(LogStatus.PASS, "click on Update 2 button ");
					Thread.sleep(2000);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
					driver.findElement(By.name("process")).click();
					test.log(LogStatus.PASS, "click on process Loan button ");
					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
					driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
					test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
					String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
					driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
					test.log(LogStatus.PASS, "Collateral Type is enterted as ::"+ESign_DisbType);	
					driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
					test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);

					if(ESign_DisbType.equals("ACH"))	
					{

						driver.findElement(By.name("newAbaNbr")).sendKeys(ABA_Number);
						test.log(LogStatus.PASS, "ABA Number entered");
						driver.findElement(By.name("newCkngAccntNbr")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Newchecking number Enetered");
					}

					driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
					test.log(LogStatus.PASS, "Electronic Documents Only? is selected as "+ESign_CourtesyCallConsent);
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
						driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
						test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
													
					}

					if(ESign_CollateralType.equals("CHECK"))
					{
						driver.findElement(By.name("requestBean.checkNbrs")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Check number Entered");
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
			}
		}
	}
}  

public void Biweek_due_RegistrationPage(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Biweek_due_RegistrationPage");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			Date Misc_IDExpDt = df.parse(Misc_IDExpDate);

			String IDExpDate0 =df.format(Misc_IDExpDt);

			String IDExpDate[] =IDExpDate0.split("/");

			String IDExpD1 = IDExpDate[0];

			String IDExpD2 = IDExpDate[1];

			String IDExpD3 = IDExpDate[2];

			String DOB[] =BorrDOB.split("/");

			String DOB1 = DOB[0];

			String DOB2 = DOB[1];

			String DOB3 = DOB[2];

			appUrl = AppURL;

			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

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

			driver.findElement(By.name("customerBean.mailingAddressLn1")).sendKeys(AddressLine);
			test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
			driver.findElement(By.name("customerBean.mailingCity")).sendKeys(City);
			test.log(LogStatus.PASS, "City is entered: "+City);
			driver.findElement(By.name("customerBean.mailingStateCd")).sendKeys(State);
			test.log(LogStatus.PASS, "State is entered: "+State);
			driver.findElement(By.name("customerBean.mailingPostalCd")).sendKeys(ZipCode);
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
			driver.findElement(By.name("customerBean.eyeColor")).sendKeys("Black");
			test.log(LogStatus.PASS, "Eye Color is entered: Black");
			driver.findElement(By.name("customerBean.feet")).sendKeys("5");
			test.log(LogStatus.PASS, "Height is entered: 5");
			driver.findElement(By.name("customerBean.inches")).sendKeys("5");
			test.log(LogStatus.PASS, "Inches is entered: 5");



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

			String Parent_Window = driver.getWindowHandle();

			if(Income_PayFrequency.equals("Monthly"))

			{

				driver.findElement(By.name("customerBean.monthlyDate")).sendKeys("1st");

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

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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





				if(driver.findElement(By.id("ShareScreenBtn")).isEnabled())

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

public void NewLoanWithVIN(String SSN,String FileName,String NewVIN) throws Exception{
	test.log(LogStatus.INFO, "New Loan With VIN");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);     	
	int lastrow=TestData.getLastRow("NewLoan");
	
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"Esign_DisbType2",row);
			String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);	
			String ABA_Number = TestData.getCellData(sheetName,"ABARoutingNbr",row);
			String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String Parent_Window = driver.getWindowHandle();
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			String SSN1 = SSN.substring(0,3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
		
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
			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);	
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				if(ProductID.equals("TLP"))							
				{					
					driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
					driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
					driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();	

				}												
			if(ProductName.equals("TNPAYDAY"))
			{
				driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("TNPDL all coll"))
			{	driver.findElement(By.name("prodSel")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("Tennessee"))
			{
				//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
				driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("Line of Credit"))
			{
				if(StoreID.equals("4322"))
				{
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
				}
				if(StoreID.equals("4353"))
				{
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);

				}
				if(StoreID.equals("1343"))
				{
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
				}

			}
				
			driver.findElement(By.name("ShareScreenBtn")).click();
			test.log(LogStatus.PASS, "ShareScreen Button clicked");
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

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
				test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
				if(!(ESign_LoanAmt.isEmpty()))
				{
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
					test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
				}
				driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
				test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					

				String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");

				driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type1 is enterted as "+ESign_DisbType);

				driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys("215");					
				test.log(LogStatus.PASS, "Disb Amt1 is enterted as ::" +"215");
		
				driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(ESign_DisbType2);
				test.log(LogStatus.PASS, "Disb Type2 is selected as ::Cash");

				driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("210");
				test.log(LogStatus.PASS, "Disb Amt2 is enterted as 210");
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
				WebDriverWait wait = new WebDriverWait(driver, 30);	
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
				driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
				test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
				driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
				test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
				driver.findElement(By.name("finishadvance")).click();
				test.log(LogStatus.PASS, "click on Finish Loan button ");
				
				try { 
					Alert alert = driver.switchTo().alert();
					String Var1 = alert.getText();
					test.log(LogStatus.PASS, "ALert Displayed is :: "+Var1);

					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				
				try { 
					Alert alert = driver.switchTo().alert();
					String Var = alert.getText();
					test.log(LogStatus.PASS, "ALert Displayed is :: "+Var);
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
								
				driver.findElement(By.xpath("//*[@id='OKBut']")).click();				
				test.log(LogStatus.PASS, "click on Yes button ");
				test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("bdyLoad");
				
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
				driver.findElement(By.name("requestBean.titleNumber")).clear();
				driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
				driver.findElement(By.name("requestBean.appraisalVal")).clear();
				driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
				driver.findElement(By.name("button1")).click();
				test.log(LogStatus.PASS, "click on Update 1 button ");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
				driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
				driver.findElement(By.name("requestBean.licensePltNbr")).clear();
				driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
				driver.findElement(By.name("requestBean.licensePltExpire")).clear();
				driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
				driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
				driver.findElement(By.name("iPhoneNbr1")).clear();
				driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
				driver.findElement(By.name("iPhoneNbr2")).clear();
				driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
				driver.findElement(By.name("iPhoneNbr3")).clear();
				driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
				driver.findElement(By.name("requestBean.companyName")).clear();
				driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
				driver.findElement(By.name("iexpiry1")).clear();
				driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
				driver.findElement(By.name("iexpiry2")).clear();
				driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
				driver.findElement(By.name("iexpiry3")).clear();
				driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
				driver.findElement(By.name("requestBean.polocyNbr")).clear();
				driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
				driver.findElement(By.name("button2")).click();		
				Thread.sleep(2000);
				driver.findElement(By.name("button2")).click();	
				test.log(LogStatus.PASS, "click on Update 2 button ");
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
				driver.findElement(By.name("process")).click();
				test.log(LogStatus.PASS, "click on process Loan button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
				test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);*/
				//Thread.sleep(1000);
				driver.findElement(By.name("negLoanAmt")).click();
				
				Thread.sleep(3000);
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));
				driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				
				WebElement element = driver.findElement(By.name("requestBean.siilBean.advAmt"));                                       
				Actions builder = new Actions(driver); 
				builder.doubleClick()
				.sendKeys(element, Keys.DELETE)
				.sendKeys(element,Keys.BACK_SPACE)
				.sendKeys(element,Keys.BACK_SPACE)
				.sendKeys(element,Keys.BACK_SPACE)
				.sendKeys(element,Keys.BACK_SPACE)
				.sendKeys(element,Keys.BACK_SPACE)
				.sendKeys(element,Keys.BACK_SPACE)
				.sendKeys(element,Keys.BACK_SPACE)
				.build()
				.perform();


				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}

				driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys("1250");
				driver.findElement(By.name("negSel")).click();
				driver.findElement(By.name("reCalculate")).click();
				driver.findElement(By.name("negSel")).click();
				driver.switchTo().window(Parent_Window);

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}                    

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");
				driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
				test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
				driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
				String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");

				driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys("1250");
				test.log(LogStatus.PASS, "Disb Amt is enterted as "+"1250");

				if(ESign_DisbType.equals("ACH"))	
				{

					driver.findElement(By.name("newAbaNbr")).sendKeys(ABA_Number);
					test.log(LogStatus.PASS, "ABA Number entered");
					driver.findElement(By.name("newCkngAccntNbr")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Newchecking number Enetered");

				}

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
					
					driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
					test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
											
				}

				if(ESign_CollateralType.equals("CHECK"))
				{
					driver.findElement(By.name("requestBean.checkNbrs")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Check number Entered");
				}
				driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
				test.log(LogStatus.PASS, "enter the Passwored");
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
		}
	}
}

public void NewLoanWithVIN1(String SSN,String FileName,String NewVIN) throws Exception{
	test.log(LogStatus.INFO, "New Loan With VIN1");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);     	
	int lastrow=TestData.getLastRow("NewLoan");
	
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"Esign_DisbType2",row);
			String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
			String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String Parent_Window = driver.getWindowHandle();
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
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
			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);	
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
			{
				if(ProductID.equals("TLP"))							
				{					

					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
					driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
					driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();

				}												

				if(ProductName.equals("Tennessee"))
				{

					//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
				}


				driver.findElement(By.name("ShareScreenBtn")).click();
				test.log(LogStatus.PASS, "ShareScreen Button clicked");
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


				if(ProductID.equals("TLP"))
				{	
					String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
					String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
					String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
					String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
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
					driver.findElement(By.name("requestBean.titleNumber")).clear();
					driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
					driver.findElement(By.name("requestBean.appraisalVal")).clear();
					driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
					driver.findElement(By.name("button1")).click();
					test.log(LogStatus.PASS, "click on Update 1 button ");
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));

					driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
					driver.findElement(By.name("requestBean.licensePltNbr")).clear();
					driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
					driver.findElement(By.name("requestBean.licensePltExpire")).clear();
					driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
					driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
					driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
					driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
					driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
					driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
					driver.findElement(By.name("iPhoneNbr1")).clear();
					driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
					driver.findElement(By.name("iPhoneNbr2")).clear();
					driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);

					driver.findElement(By.name("iPhoneNbr3")).clear();
					driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
					driver.findElement(By.name("requestBean.companyName")).clear();
					driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
					driver.findElement(By.name("iexpiry1")).clear();
					driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
					driver.findElement(By.name("iexpiry2")).clear();
					driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
					driver.findElement(By.name("iexpiry3")).clear();
					driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
					driver.findElement(By.name("requestBean.polocyNbr")).clear();
					driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
					
					driver.findElement(By.name("button2")).click();	
					Thread.sleep(2000);
					driver.findElement(By.name("button2")).click();	
					test.log(LogStatus.PASS, "click on Update 2 button ");
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
					driver.findElement(By.name("process")).click();
					test.log(LogStatus.PASS, "click on process Loan button ");
					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();

					}
					catch (NoAlertPresentException e) {
					}
					Thread.sleep(2000);
					/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
					test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
					Thread.sleep(1000);*/
					driver.findElement(By.name("negLoanAmt")).click();
					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}
					Thread.sleep(5000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));

					driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
					

					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();

					}
					catch (NoAlertPresentException e) {
					}
					
					WebElement element = driver.findElement(By.name("requestBean.siilBean.advAmt"));                                       
					Actions builder = new Actions(driver); 
					builder.doubleClick()

					.sendKeys(element, Keys.DELETE)

					.sendKeys(element,Keys.BACK_SPACE)
					.sendKeys(element,Keys.BACK_SPACE)
					.sendKeys(element,Keys.BACK_SPACE)
					.sendKeys(element,Keys.BACK_SPACE)
					.sendKeys(element,Keys.BACK_SPACE)
					.sendKeys(element,Keys.BACK_SPACE)
					.sendKeys(element,Keys.BACK_SPACE)
					.build()
					.perform();



					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();


					}
					catch (NoAlertPresentException e) {

					}

					driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys("1250");
					driver.findElement(By.name("negSel")).click();
					driver.findElement(By.name("reCalculate")).click();
					driver.findElement(By.name("negSel")).click();
					
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
					test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
					driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
					test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
					String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");
					driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys("1250");
					test.log(LogStatus.PASS, "Disb Amt is enterted as "+"1250");

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

							}
							catch (NoAlertPresentException e) {
							}
						}

					}
					if(AllowPromotion.equals("Yes"))
					{
						driver.findElement(By.name("allowPromotion")).click();
						test.log(LogStatus.PASS, "AllowPromotion is selected ");
						driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
						test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
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

			}

		}
	}
}


public void Loan_Status_Loan1(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Loan Status Loan1");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	
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


			if(ProductID.equals("TLP"))
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			String CheckStaus=null;
			CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
			test.log(LogStatus.PASS,"Loan No 1 Status is :: "+CheckStaus);
			driver.close();
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
}

public void Loan_Status_Loan2(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Loan Status of Loan2");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	
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

			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();

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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			String CheckStaus=null;
			CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

			test.log(LogStatus.PASS,"Loan No 2 Status is :: "+CheckStaus);
			
			driver.close();
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
	}
}


public void NewLoanWithVIN2(String SSN,String FileName,String NewVIN2) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);     	
	int lastrow=TestData.getLastRow("NewLoan");
	
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"Esign_DisbType2",row);
			String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);	
			String ABA_Number = TestData.getCellData(sheetName,"ABARoutingNbr",row);
			String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			
			String Parent_Window = driver.getWindowHandle();
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
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
			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);	
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(!driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				if(ProductID.equals("TLP"))							
				{
					Thread.sleep(2000);										
					driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
					driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN2);	
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN2);
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
					driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
				}												
			if(ProductName.equals("TNPAYDAY"))
			{
				driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("TNPDL all coll"))
			{	
				driver.findElement(By.name("prodSel")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("Tennessee"))
			{
				//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
				driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}
			if(ProductName.equals("Line of Credit"))
			{

				if(StoreID.equals("4322"))
				{
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
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

			driver.findElement(By.name("ShareScreenBtn")).click();
			test.log(LogStatus.PASS, "ShareScreen Button clicked");
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

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
				test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
				if(!(ESign_LoanAmt.isEmpty()))
				{
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
					test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
				}
				driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
				test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
				Thread.sleep(5000);
				String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
				System.out.println(Instamt);
				driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type1 is enterted as "+ESign_DisbType);
				Thread.sleep(2000);
				driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys("215");					
				test.log(LogStatus.PASS, "Disb Amt1 is enterted as ::" +"215");
				Thread.sleep(5000);
				driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(ESign_DisbType2);
				test.log(LogStatus.PASS, "Disb Type2 is selected as ::Cash");



				driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("210");
				test.log(LogStatus.PASS, "Disb Amt2 is enterted as 210");
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
				WebDriverWait wait = new WebDriverWait(driver, 30);	
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
					String Var1 = alert.getText();
					test.log(LogStatus.PASS, "ALert Displayed is :: "+Var1);

					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				try { 
					Alert alert = driver.switchTo().alert();
					String Var = alert.getText();
					test.log(LogStatus.PASS, "ALert Displayed is :: "+Var);
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
				test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("bdyLoad");

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
				driver.findElement(By.name("requestBean.titleNumber")).clear();
				driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
				driver.findElement(By.name("requestBean.appraisalVal")).clear();
				driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
				driver.findElement(By.name("button1")).click();
				test.log(LogStatus.PASS, "click on Update 1 button ");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
				driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
				driver.findElement(By.name("requestBean.licensePltNbr")).clear();
				driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
				driver.findElement(By.name("requestBean.licensePltExpire")).clear();
				driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
				driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
				driver.findElement(By.name("iPhoneNbr1")).clear();
				driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
				driver.findElement(By.name("iPhoneNbr2")).clear();
				driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
				driver.findElement(By.name("iPhoneNbr3")).clear();
				driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
				driver.findElement(By.name("requestBean.companyName")).clear();
				driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
				driver.findElement(By.name("iexpiry1")).clear();
				driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
				driver.findElement(By.name("iexpiry2")).clear();
				driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
				driver.findElement(By.name("iexpiry3")).clear();
				driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
				driver.findElement(By.name("requestBean.polocyNbr")).clear();
				driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
				driver.findElement(By.name("button2")).click();	
				Thread.sleep(2000);
				driver.findElement(By.name("button2")).click();		
				test.log(LogStatus.PASS, "click on Update 2 button ");
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
				driver.findElement(By.name("process")).click();
				test.log(LogStatus.PASS, "click on process Loan button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
				test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
				
				driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
				
				String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");
				driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
				test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);

				if(ESign_DisbType.equals("ACH"))	
				{

					driver.findElement(By.name("newAbaNbr")).sendKeys(ABA_Number);
					test.log(LogStatus.PASS, "ABA Number entered");
					driver.findElement(By.name("newCkngAccntNbr")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Newchecking number Enetered");

				}
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
					driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
					test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
										
				}

				if(ESign_CollateralType.equals("CHECK"))
				{
					driver.findElement(By.name("requestBean.checkNbrs")).sendKeys(ESign_CheckNbr);
					test.log(LogStatus.PASS, "Check number Entered");
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
		}

	}
}

public void NewLoanWithVIN3(String SSN,String FileName,String NewVIN2) throws Exception{

	test.log(LogStatus.INFO, "NewLoanWithVIN3");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);     	
	int lastrow=TestData.getLastRow("NewLoan");
	
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"Esign_DisbType2",row);
			String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
			String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String Parent_Window = driver.getWindowHandle();
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
		
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
			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);	
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				if(ProductID.equals("TLP"))							
				{					
					System.out.println("IN TLP");
					driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
					driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN2);	
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN2);
					driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
					driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
					driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
				}												

			if(ProductName.equals("Tennessee"))
			{
				//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
				driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
				test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
			}

			driver.findElement(By.name("ShareScreenBtn")).click();
			test.log(LogStatus.PASS, "ShareScreen Button clicked");
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

			if(ProductID.equals("TLP"))
			{	
				String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
				String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
				String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
				String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
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
				driver.findElement(By.name("requestBean.titleNumber")).clear();
				driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
				driver.findElement(By.name("requestBean.appraisalVal")).clear();
				driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
				driver.findElement(By.name("button1")).click();
				test.log(LogStatus.PASS, "click on Update 1 button ");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
				driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
				driver.findElement(By.name("requestBean.licensePltNbr")).clear();
				driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
				driver.findElement(By.name("requestBean.licensePltExpire")).clear();
				driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
				
				driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
				driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
				driver.findElement(By.name("iPhoneNbr1")).clear();
				driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
				driver.findElement(By.name("iPhoneNbr2")).clear();
				driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);

				driver.findElement(By.name("iPhoneNbr3")).clear();
				driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
				driver.findElement(By.name("requestBean.companyName")).clear();
				driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
				driver.findElement(By.name("iexpiry1")).clear();
				driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
				driver.findElement(By.name("iexpiry2")).clear();
				driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
				driver.findElement(By.name("iexpiry3")).clear();
				driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
				driver.findElement(By.name("requestBean.polocyNbr")).clear();
				driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
				driver.findElement(By.name("button2")).click();			
				Thread.sleep(2000);
				driver.findElement(By.name("button2")).click();	
				test.log(LogStatus.PASS, "click on Update 2 button ");
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
				driver.findElement(By.name("process")).click();
				test.log(LogStatus.PASS, "click on process Loan button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {
				}
				Thread.sleep(2000);
				driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
				test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
				driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
				String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");

				driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
				test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);

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

						}
						catch (NoAlertPresentException e) {
						}
					}

				}
				if(AllowPromotion.equals("Yes"))
				{
					driver.findElement(By.name("allowPromotion")).click();
					test.log(LogStatus.PASS, "AllowPromotion is selected ");
					driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
					test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
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

		}

	}
}


public void Void_Loan(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Void Loan");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   	
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
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String Eankey=null;
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();		
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

			driver.findElement(By.id("go_Button")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");			 	

			driver.findElement(By.name("tenderType")).sendKeys("Cash"); //updated element
			driver.findElement(By.name("requestBean.password")).sendKeys("1234");
			driver.findElement(By.name("finish")).click();
			test.log(LogStatus.PASS, "Password is selected as ");																					
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
				test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
				driver.findElement(By.name("Ok")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
			}
		}

	}
}

public void Rescind(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Rescind Loan");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   	
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
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();	
			driver.switchTo().defaultContent();				
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
				driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
			}

			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Rescind");
			test.log(LogStatus.PASS, "Transaction Type is selected as: Rescind");	
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
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("finish")).click();
			}						 						 
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
			if(ProductID.equals("TLP"))
			{

				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Rescind Loan is Completed Successfully ");
					driver.findElement(By.name("Ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Rescind Loan is not Completed Successfully ");
				}
			}
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

public void AgeStore_1stInst_DueDate10DaysBefore(String SSN,String FileName,int Days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore - 10 Days Before Duedate");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName); 
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


			if(ProductID.equals("TLP"))

			{

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

			if(ProductID.equals("TLP"))

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
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);
			driver.manage().window().maximize();
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
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
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
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();

			}

			else

			{
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");

			}
		}

	}
}

public void AgeStore_1stInst_ONDueDate(String SSN,String FileName,int Days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore- 1st Installment DueDate");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName); 
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

			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

			if(ProductID.equals("TLP"))
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
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy"); 

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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}
		}
	}
}

public void AgeStore_1stInst(String SSN,String FileName,int Days) throws Exception
{
	test.log(LogStatus.INFO, "AgeStore 1stInst "+Days+"Days");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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


			if(ProductID.equals("TLP"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("TLP"))
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

			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			
			driver.close();

			driver = new InternetExplorerDriver();
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
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			 driver.findElement(By.linkText("QA Jobs")).click();
		      test.log(LogStatus.PASS, "Clicked on QA Jobs");
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			
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
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}
		}
	}
}

public void AgeStore_1stInst_DueDate(String SSN,String FileName,int Days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore 1stInst DueDate"+Days+"Days");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName); 

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


			if(ProductID.equals("TLP"))

			{

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

			if(ProductID.equals("TLP"))

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
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);

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

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

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

				test.log(LogStatus.FAIL, "Process Date not updated successfully.");

			}
		}

	}
}

public void AgeStore_2ndInst(String SSN,String FileName,int days) throws Exception

{
	test.log(LogStatus.INFO, "AgeStore 2ndInst "+days+"Days");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			
			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();
			test.log(LogStatus.PASS, "2nd instalment duedate :"+DueDate);

			System.out.print(DueDate);

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(8000);

			Date DDueDate = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDate);

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

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

	}

}

public void AgeStore_3rdInst(String SSN,String FileName,int days) throws Exception

{
	test.log(LogStatus.INFO, "AgeStore 3rdInst "+days+"Days");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			
			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[4]/td[2]")).getText();
			test.log(LogStatus.PASS, "2nd instalment duedate :"+DueDate);

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

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

	}

}

public void Loandate_AgeStore(String SSN,String FileName,int Days) throws Exception
{
	test.log(LogStatus.INFO, "AgeStore - LoanDate +"+Days);

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
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

			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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

			if(ProductID.equals("TLP"))
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

			DueDate  =  driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

			test.log(LogStatus.PASS, "Captured Loan Date"+DueDate);

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy"); 

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

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}
		}
	}
}

public void AgeStore1_1_C(String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore1 1 C");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{
				driver.switchTo().window(winHandle1);
			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
			
			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

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

				test.log(LogStatus.FAIL, "Process Date is not updated successfully.");

			}

		}

	}

}

public void AgeStore1_2_C(String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore1_2_C");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

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

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.name("storeCode")).click();
			
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			driver.findElement(By.name("beginMonth")).click();

			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

			driver.findElement(By.name("beginDay")).click();

			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

			driver.findElement(By.name("beginYear")).click();

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

				test.log(LogStatus.FAIL, "Process Date is not updated successfully.");

			}

		}

	}

}

public void AgeStore2_1_C(String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore2_1_C "+days);
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Date DDueDate = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDate);

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

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

				test.log(LogStatus.FAIL, "Process Date is not updated successfully.");

			}

		}

	}

}

public void LoanDate_Agestore_C(String SSN,String FileName, int days) throws Exception{
	test.log(LogStatus.INFO, "LoanDate_Agestore_C"+days);
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String LoanDate=null;

			
			LoanDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[11]/td/span[2]")).getText();

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Date DDueDate = df.parse(LoanDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDate);

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/"); 

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

				test.log(LogStatus.FAIL, "Process Date is not updated successfully.");

			}

		}

	}

}

public void AgeStore1_1BatchProcess_C(String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore1_1BatchProcess_C +"+days);
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);
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

			if(ProductID.equals("TLP"))
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

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

				test.log(LogStatus.FAIL, "Process Date is not updated successfully.");

			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("EOD Batch Process")).click();
			test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			test.log(LogStatus.INFO, "EOD Batch Process Completed");
		}

	}

}

public void DrawerDeassign(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

public void EODProcessing(String SSN,String FileName) throws Exception{

Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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

public void StoreInfo(String SSN,String FileName) throws Exception
	{
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
 	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);
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
			
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


	   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);		
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

public void Renewal_Status(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Checking Renewal Status");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);     
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
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreID, driver, AppURL, test);
			//this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();                      
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");
		
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

			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}                                   
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
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
			test.log(LogStatus.INFO,"Transaqction Selection Go Button Click");

			String CheckStatus=null;
			CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[18]/td/span[2]")).getText();
			if(CheckStatus.equals("Y")){
				test.log(LogStatus.PASS,"Loan Renewal status : "+CheckStatus);
				test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
				
			}
			else
			{
				test.log(LogStatus.FAIL," Loan Renewal Status : "+CheckStatus);
				test.log(LogStatus.INFO,"Renewal Transaction is failed");
			}
		}       
	}
}

public void Renewal_Status1(String SSN,String FileName) throws Exception{

	test.log(LogStatus.INFO, "Renewal Status1");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);     
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
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			test.log(LogStatus.INFO, "RPP(Starts)");
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

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

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

			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}                                   
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
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
			test.log(LogStatus.INFO,"Transaqction Selection Go Button Click");

			String CheckStatus=null;                       
			CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[18]/td/span[2]")).getText();
			if(CheckStatus.equals("N")){
				test.log(LogStatus.PASS,"Loan Renewal status : "+CheckStatus);
				test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
				
			}
			else
			{
				test.log(LogStatus.FAIL,"Loan Renewal Status : "+CheckStatus);
				test.log(LogStatus.INFO,"Renewal Transaction is faild");
			}
		}       
	}
}

public void Payment (String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Loan Payment");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);

			String StoreID = TestData.getCellData(sheetName,"StoreID",row);

			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String UserName = TestData.getCellData(sheetName,"UserName",row);

			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

			appUrl = AppURL;

			this.Login(UserName,Password,StoreID);

			String SSN1 = SSN.substring(0, 3);

			String SSN2 = SSN.substring(3,5);

			String SSN3 = SSN.substring(5,9);

			driver.switchTo().defaultContent();

		WebDriverWait wait = new WebDriverWait(driver, 30);

			/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));*/
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().frame("topFrame");

			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));

			driver.findElement(By.cssSelector("li[id='910000']")).click();

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

			driver.findElement(By.name("transactionList")).sendKeys("Payment");

			test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();

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

				test.log(LogStatus.PASS, "Clicked on Finish Loan button ");

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

					test.log(LogStatus.PASS, "Payment is Completed Successfully ");

					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();

				}

				else

				{

					test.log(LogStatus.FAIL, "Payment Loan is not Completed Successfully ");

				}



			}

			if(ProductID.equals("PDL"))

			{

				if(ESign_DisbType.equals("Check")){

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);

				}

				//driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);

				if(ESign_DisbType.equals("Cash")){

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");

				}

				if((ESign_DisbType.equals("Cash")||(ESign_DisbType.equals("Check")))&&((ESign_DisbType2.equals("Check"))||(ESign_DisbType.equals("Cash")) ))

				{

					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("TenderType");

				}

				String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();

				System.out.println(Pmt);

				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);

				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

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

				
				driver.findElement(By.xpath("//*[@id='PD4']")).click();
				test.log(LogStatus.PASS, " Click payment amt Type ");

				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");

				test.log(LogStatus.PASS, "tenderType as Cash ");
				
				String pmtamt = driver.findElement(By.name("instAmt")).getAttribute("value");

				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(pmtamt);

			}

			if(ProductID.equals("PDL"))

			{

				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);

				driver.findElement(By.name("Submit23")).click();

				test.log(LogStatus.PASS, "Password is selected as "+Password);

				test.log(LogStatus.PASS, "Clicked on Finish Payament Loan button ");

			}

			if(ProductID.equals("TLP"))

			{

				driver.findElement(By.name("requestBean.password")).sendKeys(Password);

				driver.findElement(By.name("finish")).click();

				test.log(LogStatus.PASS, "Password is selected as "+Password);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");
			
			driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[1]")).click();

			{

				if(driver.findElement(By.name("Ok")).isDisplayed())

				{

					test.log(LogStatus.PASS, "Payment is Completed Successfully ");

					driver.findElement(By.name("Ok")).click();

				}

				else

				{

					test.log(LogStatus.FAIL, "Payment is not Completed Successfully ");

				}

			}

		}

	}
}	

public void Payment_No (String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Payment No");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);

			String StoreID = TestData.getCellData(sheetName,"StoreID",row);

			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String UserName = TestData.getCellData(sheetName,"UserName",row);

			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

			appUrl = AppURL;

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

			driver.findElement(By.name("transactionList")).sendKeys("Payment");

			test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

			if(ProductID.equals("TLP"))

			{

				driver.findElement(By.xpath("//*[@id='go_Button']")).click();

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

			if(ProductID.equals("TLP"))

			{


				driver.findElement(By.xpath("//*[@id='PD4']")).click();
				test.log(LogStatus.PASS, " Click payment amt Type ");

				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");

				test.log(LogStatus.PASS, "tenderType as Cash ");
				
				String pmtamt = driver.findElement(By.name("instAmt")).getAttribute("value");

				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(pmtamt);

			}


			if(ProductID.equals("TLP"))

			{



				driver.findElement(By.name("requestBean.password")).sendKeys(Password);

				driver.findElement(By.name("finish")).click();

				test.log(LogStatus.PASS, "Password is selected as "+Password);

				driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[2]")).click();
				test.log(LogStatus.PASS, "Payment is  No Renewed ");
			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");
			
			{

				if(driver.findElement(By.name("Ok")).isDisplayed())

				{

					test.log(LogStatus.PASS, " Payment is Completed Successfully ");

					driver.findElement(By.name("Ok")).click();

				}

				else

				{

					test.log(LogStatus.FAIL, "Partial Payment is not Completed Successfully ");

				}

			}

		}

	}

}		

public void PartialPayment (String SSN,String FileName) throws Exception{		
	
	test.log(LogStatus.INFO, "Partial Payment");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";
	for(int row=2;row<=lastrow;row++)
	{
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			
			driver.switchTo().defaultContent();
			this.Login(UserName, Password, StoreID);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();
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

			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
			}

			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
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


			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, " Click pay any other amt Type ");
				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys("50");
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "tenderType  as Cash ");
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys("50");

			}

			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("finish")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
			} 
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[2]")).click();
			test.log(LogStatus.PASS, "ReNewButton No is Selected ");

			if(driver.findElement(By.name("Ok")).isDisplayed())

			{
				test.log(LogStatus.PASS, "Partial Payment is Completed Successfully ");
				driver.findElement(By.name("Ok")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Partial Payment is not Completed Successfully ");
			}
		}
	}

}

public void Payment_PartialAmount (String SSN,String FileName) throws Exception{		


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);
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
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName, Password, StoreId);
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
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
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
				test.log(LogStatus.PASS, "Clicked on Finish  Loan button ");
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
					test.log(LogStatus.PASS, "Payment is Completed Successfully ");
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payment Loan is not Completed Successfully ");
				}


			}
			if(ProductID.equals("PDL"))
			{
				if(ESign_DisbType.equals("Check")){
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				}
				if(ESign_DisbType.equals("Cash")){
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				}
				if((ESign_DisbType.equals("Cash")||(ESign_DisbType.equals("Check")))&&((ESign_DisbType2.equals("Check"))||(ESign_DisbType.equals("Cash")) ))
				{
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("TenderType");
				}
				String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();
				
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
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

				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, " Click pay any other amt Type ");

				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys("50");
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "tenderType  as Cash ");
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys("50");

			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.name("Submit23")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				test.log(LogStatus.PASS, "Clicked on Finish Payament Loan button ");
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("finish")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
			} 
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[2]")).click();

			test.log(LogStatus.PASS, "Clicked On NO Button in Renew PopUp ");

			{

				if(driver.findElement(By.name("Ok")).isDisplayed())

				{
					test.log(LogStatus.PASS, "Partial Payment is Completed Successfully ");
					driver.findElement(By.name("Ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Partial Payment is not Completed Successfully ");
				}

			}
		}
	}
}

public void Payment_PartialAmount_NO (String SSN,String FileName) throws Exception{		

	test.log(LogStatus.INFO, "Payment Partial Amount NO");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);
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
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName, Password, StoreId);
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
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			test.log(LogStatus.PASS, "Transaction Type is selected as Payment");


			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
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
				test.log(LogStatus.PASS, "Clicked on Finish  Loan button ");
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
					test.log(LogStatus.PASS, "Payment is Completed Successfully ");
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payment Loan is not Completed Successfully ");
				}


			}

			if(ProductID.equals("PDL"))
			{
				if(ESign_DisbType.equals("Check")){
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				}
				if(ESign_DisbType.equals("Cash")){
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				}
				if((ESign_DisbType.equals("Cash")||(ESign_DisbType.equals("Check")))&&((ESign_DisbType2.equals("Check"))||(ESign_DisbType.equals("Cash")) ))
				{
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("TenderType");
				}
				String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();
				
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
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
				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, " Click pay any other amt Type ");

				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys("50");
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "tenderType  as Cash ");
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys("50");

			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.name("Submit23")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				test.log(LogStatus.PASS, "Clicked on Finish Payament Loan button ");
			}
			if(ProductID.equals("TLP"))
			{
				
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("finish")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
			} 
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[2]")).click();

			test.log(LogStatus.PASS, "Clicked On No Button in Renew PopUp ");
			{
				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Partial Payment is Completed Successfully ");
					driver.findElement(By.name("Ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Partial Payment is not Completed Successfully ");
				}
			}
		}
	}
}

public void Payment_1stInterestAmt_on2ndinstduration(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Payment 1stInterestAmt on2ndinstduration");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

	int lastrow=TestData.getLastRow("NewLoan");

	String sheetName="NewLoan";

	for(int row=2;row<=lastrow;row++)

	{
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))

		{
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);

			String Password = TestData.getCellData(sheetName,"Password",row);

			String StoreID = TestData.getCellData(sheetName,"StoreID",row);

			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String UserName = TestData.getCellData(sheetName,"UserName",row);

			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

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


			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

			if(ProductID.equals("TLP"))

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

			String PrincipleAmt=null;

			PrincipleAmt = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[4]")).getText();

			test.log(LogStatus.PASS, "Interest Amount is:: "+PrincipleAmt);
		
			appUrl = AppURL;

			this.Login(UserName,Password,StoreID);

			driver.switchTo().defaultContent();

			WebDriverWait wait = new WebDriverWait(driver, 30);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));

			driver.switchTo().frame("topFrame");

			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));

			driver.findElement(By.cssSelector("li[id='910000']")).click();

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

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
			}

			test.log(LogStatus.PASS, "Click on GO Button");

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("transactionList")).sendKeys("Payment");

			test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
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


			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, " Click pay Other amt Type ");

				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(PrincipleAmt);
				test.log(LogStatus.PASS, "Payment Amount is:: "+PrincipleAmt);
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");

				test.log(LogStatus.PASS, "tenderType as Cash ");

				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(PrincipleAmt);
				test.log(LogStatus.PASS, "Tender Amount is:: "+PrincipleAmt);
			}


			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);

				driver.findElement(By.name("finish")).click();

				test.log(LogStatus.PASS, "Password is selected as "+Password);

				driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[2]")).click();
				test.log(LogStatus.PASS, "Click on NO for Renewal Amount ");

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			{
				if(driver.findElement(By.name("Ok")).isDisplayed())

				{

					test.log(LogStatus.PASS, " Payment is Completed Successfully ");

					driver.findElement(By.name("Ok")).click();
				}

				else

				{

					test.log(LogStatus.FAIL, " Payment is not Completed Successfully ");

				}
			}
		}
	}
}

public void Loan_1stinstallment_status(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Loan 1st installment status");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			test.log(LogStatus.INFO, "Renewal Status(Starts)");

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
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

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

			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			
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
			String payment=null;
			String Interest=null;
			payment = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[4]/td[5]/font")).getText();
			test.log(LogStatus.PASS, " 1st Instment Payment::"+payment);
			Interest = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[5]/td[5]/font")).getText();
			test.log(LogStatus.PASS, "1st Instment  Interest::"+Interest);
			test.log(LogStatus.PASS, "1st Instment  Payment is Completed Sucessfully");
		}
	}
}

public void DefaultPayment (String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Default Payment");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

	int lastrow=TestData.getLastRow("NewLoan");

	String sheetName="NewLoan";

	for(int row=2;row<=lastrow;row++)

	{

		String RegSSN = TestData.getCellData(sheetName,"SSN",row);

		if(SSN.equals(RegSSN))

		{

			String TenderType = TestData.getCellData(sheetName,"TenderType",row);

			String ProductID=TestData.getCellData(sheetName,"ProductID",row);

			String Password = TestData.getCellData(sheetName,"Password",row);

			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);

			String StoreID = TestData.getCellData(sheetName,"StoreID",row);

			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String UserName = TestData.getCellData(sheetName,"UserName",row);

			appUrl = AppURL;

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

			if(ProductID.equals("TLP"))

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

			driver.findElement(By.name("transactionList")).sendKeys("Default Payment");

			test.log(LogStatus.PASS, "Transaction Type is selected as Default Payment");

			if(ProductID.equals("TLP"))

			{

				driver.findElement(By.xpath("//*[@id='go_Button']")).click();

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


			if(ProductID.equals("TLP"))

			{
				String PmtAmt = driver.findElement(By.name("transactionDataBean.paymentBalAmt")).getAttribute("value");
				test.log(LogStatus.PASS, " Default  payment amt is :: "+PmtAmt );
				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();
				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys(PmtAmt);
				test.log(LogStatus.PASS, " Default  payment amt entered Is :: "+PmtAmt );
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, " TenderType is Selected  as :: Cash " );

				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PmtAmt);
				test.log(LogStatus.PASS, " TenderAmount entered is :: "+PmtAmt );

				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, " password entered " );
				driver.findElement(By.xpath("//input[@name='Submit22' and @value='Finish' and @type='Button']")).click();
				
				test.log(LogStatus.PASS, " Clicked on Finish Default Payment " );

			}

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			catch (NoAlertPresentException e) {

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			{

				if(driver.findElement(By.name("Ok")).isDisplayed())

				{

					test.log(LogStatus.PASS, "Default Payment is Completed Successfully ");

					driver.findElement(By.name("Ok")).click();

				}

				else

				{

					test.log(LogStatus.FAIL, "Default Payment is not Completed Successfully ");

				}

			}

		}

	}

}		

public void DefaultPaymentStatus1_C(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
			String FirstLoanStatus=null;
			String SecondLoanStatus=null;
			String FirstBalanceStatus=null;
			String SecondBalanceStatus=null;
			String FirstVehicleStatus=null;
			String SecondVehicleStatus=null;

			FirstLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[7]")).getText();

			test.log(LogStatus.PASS,"First Loan Status::  "+FirstLoanStatus);

			SecondLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[6]")).getText();

			test.log(LogStatus.PASS,"Second Loan Status::  "+SecondLoanStatus);

			FirstBalanceStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[6]")).getText();

			test.log(LogStatus.PASS,"First Balance Status::  "+FirstBalanceStatus);

			SecondBalanceStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[6]")).getText();

			test.log(LogStatus.PASS,"Second Balance Status::  "+SecondBalanceStatus);

			FirstVehicleStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[7]")).getText();

			test.log(LogStatus.PASS,"First Vehicle Status::  "+FirstVehicleStatus);

			SecondVehicleStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[7]")).getText();

			test.log(LogStatus.PASS,"Second Vehicle Status::  "+SecondVehicleStatus);

		}
	}
}

public void Loan_Status(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Loan Status");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
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

			if(ProductID.equals("TLP"))
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String CheckStaus=null;
			CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();

			test.log(LogStatus.PASS,"Loan Status is :: "+CheckStaus);
			
		}
	}
}

public void Loan_Status_Inst(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "Loan Status Inst");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			
			test.log(LogStatus.INFO, "Renewal Status(Starts)");
	
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
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");
			
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
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			test.log(LogStatus.INFO,"Navigate to Line Of Credit History Screen");
			driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
			
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

			String Fee=null;
			String Interest=null;
			String Principle=null;

			Fee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[4]/td[11]/font")).getText();
			test.log(LogStatus.PASS, "Fee Paid for 1st Inst::"+Fee);

			Interest = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[4]/td[10]/font")).getText();
			test.log(LogStatus.PASS, "1st Instment Interst Payment::"+Interest);

			Principle =	driver.findElement(By.xpath(" //*[@id='transactionDetailsTable']/tbody/tr[3]/td[9]/font")).getText();
			test.log(LogStatus.PASS, " Principle Ammount Addded::"+Principle);		

		}
	}
}

public void OutToRepo(String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "Out To Repo");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
			String DefaultDate=null;
			DefaultDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[3]")).getText();

			test.log(LogStatus.PASS,"DefaultDate::  "+DefaultDate);

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Date DDueDate = df.parse(DefaultDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDate);

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.linkText("Title Loan")).click();

			test.log(LogStatus.PASS, "Clicked on Title Loan");

			driver.findElement(By.linkText("Post Default Transactions")).click();

			test.log(LogStatus.PASS, "Clicked on Post Default Transactions");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
			test.log(LogStatus.PASS, "SSN number is Entered: ");

			driver.findElement(By.name("requestBean.statusType")).sendKeys("Out To Repo");

			test.log(LogStatus.PASS, "Out To Repo is entered: ");

			driver.findElement(By.name("submit")).click();

			test.log(LogStatus.PASS, "Clicked on submitt Button: ");

			driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();
			test.log(LogStatus.PASS, "Clicked on Go Button: ");	

			driver.findElement(By.name("solvageCompany")).sendKeys("Consolidated Asset Recovery Services (CARS)");

			test.log(LogStatus.PASS, "Solvage Company is selected As Consolidated Asset Recovery Services (CARS) ");

			driver.findElement(By.name("password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password Entered");

			driver.findElement(By.name("finish")).click();
			test.log(LogStatus.PASS, "Click on submitt Button");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/input")).isDisplayed())

			{
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/input")).click();
				test.log(LogStatus.PASS, "OK Button is Clicked");
				test.log(LogStatus.PASS, "Out To Repo updated successfully.");
			}

			else

			{

				test.log(LogStatus.FAIL, "Out To Repo Not updated successfully.");

			}



		}

	}

}

public void Repossession(String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "Repossession");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
			String DefaultDate=null;
			DefaultDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[3]")).getText();

			test.log(LogStatus.PASS,"DefaultDate::  "+DefaultDate);
			driver.close();
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Date DDueDate = df.parse(DefaultDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDate);

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.findElement(By.linkText("Title Loan")).click();

			test.log(LogStatus.PASS, "Clicked on Title Loan");

			driver.findElement(By.linkText("Post Default Transactions")).click();

			test.log(LogStatus.PASS, "Clicked on Post Default Transactions");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
			test.log(LogStatus.PASS, "SSN number is Entered: ");

			driver.findElement(By.name("requestBean.statusType")).sendKeys("Repossession");

			test.log(LogStatus.PASS, "Out To Repo is entered: ");

			driver.findElement(By.name("submit")).click();

			test.log(LogStatus.PASS, "Clicked on submitt Button: ");

			driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();
			test.log(LogStatus.PASS, "Clicked on Go Button: ");	

			driver.findElement(By.name("requestBean.companyName")).sendKeys("Consolidated Asset Recovery Services (CARS)");

			test.log(LogStatus.PASS, "Solvage Company is selected As Consolidated Asset Recovery Services (CARS) ");

			driver.findElement(By.name("rpossesdate1")).sendKeys(DueDate1);
			test.log(LogStatus.PASS, "Month is Entered");
			driver.findElement(By.name("rpossesdate2")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "Day is Entered");
			driver.findElement(By.name("rpossesdate3")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "Year is Entered");

			driver.findElement(By.name("requestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password Entered");

			driver.findElement(By.name("finish")).click();
			test.log(LogStatus.PASS, "Click on submitt Button");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			if( driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/input")).isDisplayed())

			{
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/input")).click();
				test.log(LogStatus.PASS, "OK Button is Clicked");
				test.log(LogStatus.PASS, "Repossession updated successfully.");
			}

			else

			{

				test.log(LogStatus.FAIL, "Repossession Not updated successfully.");

			}



		}

	}

}

public void LESS_Salvage (String SSN,String FileName,int days) throws Exception{
	test.log(LogStatus.INFO, "LESS Salvage");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
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
			String DefaultDate=null;
			
			DefaultDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[3]")).getText();

			test.log(LogStatus.PASS,"DefaultDate::  "+DefaultDate);

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);
			
			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(8000);

			Date DDueDate = df.parse(DefaultDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDate);

			cal.add(Calendar.DATE, days);

			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);

			String DueDate0[] =DueDateminus1.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

			test.log(LogStatus.PASS, "Clicked on Transactions");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.findElement(By.linkText("Title Loan")).click();

			test.log(LogStatus.PASS, "Clicked on Title Loan");

			driver.findElement(By.linkText("Post Default Transactions")).click();

			test.log(LogStatus.PASS, "Clicked on Post Default Transactions");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

			driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
			test.log(LogStatus.PASS, "SSN number is Entered: ");

			driver.findElement(By.name("requestBean.statusType")).sendKeys("Salvage");

			test.log(LogStatus.PASS, "Salvage is entered: ");

			driver.findElement(By.name("submit")).click();

			test.log(LogStatus.PASS, "Clicked on submitt Button: ");

			driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();

			test.log(LogStatus.PASS, "Clicked on Go Button: ");	

			WebElement SalvageCmp = driver.findElement(By.xpath("//*[@id='solvageCompany']"));

			SalvageCmp.sendKeys("Salvage Company220");

			test.log(LogStatus.PASS, "Salvage Company220 is selected from dropdown");	

			driver.findElement(By.name("rpossesdate1")).sendKeys(DueDate1);
			test.log(LogStatus.PASS, "Month is Entered");
			driver.findElement(By.name("rpossesdate2")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "Day is Entered");
			driver.findElement(By.name("rpossesdate3")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "Year is Entered");

			driver.findElement(By.name("requestBean.salvageValue")).clear();
			driver.findElement(By.name("requestBean.salvageValue")).sendKeys("400");
			test.log(LogStatus.PASS, "Salvage value is Entered");

			driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[3]/tbody/tr[5]/td[3]/textarea")).sendKeys("Comments");

			test.log(LogStatus.PASS, "Comments are Entered");

			driver.findElement(By.name("finish")).click();
			test.log(LogStatus.PASS, "Click on submit Button");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())

			{
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
				test.log(LogStatus.PASS, "OK Button is Clicked");
				test.log(LogStatus.PASS, "Salvage is completed successfully.");
			}

			else

			{

				test.log(LogStatus.FAIL, "Salvage is not completed successfully.");

			}



		}

	}

}

public void WAVE_Stastus1(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "WAVE_Stastus1");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
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

			if(ProductID.equals("TLP"))
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String WaveStatus=null;

			WaveStatus = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[7]/td[5]/font")).getText();

			test.log(LogStatus.PASS,"Waived of Amount:: "+WaveStatus);
			driver.close();
			driver = new InternetExplorerDriver();

		}
	}
}

public void WAVE_Stastus2(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "WAVE_Stastus2");
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	
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


			if(ProductID.equals("TLP"))
			{
		
				driver.findElement(By.xpath("//*[@id='showMore-2']")).click();
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[13]/input")).click();
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String WaveStatus=null;

			WaveStatus = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[7]/td[5]/font")).getText();

			test.log(LogStatus.PASS,"Waived of Amount:: "+WaveStatus);
			System.out.print(WaveStatus);	

			driver.close();
			driver = new InternetExplorerDriver();

		}
	}
}

public void AgeStore_Default_WriteOff60Days(String SSN,String FileName,int Days) throws Exception{
	test.log(LogStatus.INFO, "AgeStore Default WriteOff60Days");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))

			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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

			if(ProductID.equals("TLP"))

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

			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]")).getText();

			test.log(LogStatus.PASS, "Capture DefaultDate"+DueDate);

			driver.close();

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

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

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

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
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}

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
			test.log(LogStatus.INFO, "EOD Batch Process Completed");
		}
	}
}

public void WO_Recovery (String SSN,String FileName) throws Exception{		
	test.log(LogStatus.INFO, "WO Recovery");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);
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
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName, Password, StoreId);
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
			driver.findElement(By.name("transactionList")).sendKeys("WO Recovery");
			test.log(LogStatus.PASS, "Transaction Type is selected as WO Recovery ");

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
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

			if(ProductID.equals("TLP"))
			{
				String woamt=null;
				woamt=driver.findElement(By.name("transactionDataBean.amtOwed")).getAttribute("value");
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
				test.log(LogStatus.PASS, "tenderType  as Cash ");
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(woamt);
				test.log(LogStatus.PASS, "tender Amount as::"+woamt);

			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.name("Submit23")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				test.log(LogStatus.PASS, "Clicked on Finish Payament Loan button ");
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				test.log(LogStatus.PASS, "Clicked on Finish Wo Recovery button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				catch (NoAlertPresentException e) {
				}

			/*	if(driver.findElement(By.name("Ok")).isDisplayed())

				{
					test.log(LogStatus.PASS, " Wo Recovery is Completed Successfully ");
					driver.findElement(By.name("Ok")).click();
				}*/
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Wo Recovery is Completed Successfully");
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, " Wo Recovery is not Completed Successfully ");
				}
			}
		}
	}
}

public void WO_Void(String SSN,String FileName) throws Exception{
	test.log(LogStatus.INFO, "WriteOff Void");

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	
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
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
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
			driver.switchTo().defaultContent();				
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.xpath("//*[@name='button' and @value='Go']")).click();
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
				driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys("Cash");
				test.log(LogStatus.PASS, "disbursementType :: Cash");

			}
			if(ProductID.equals("TLP"))
			{

				driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys("Cash");
			}
			if(ProductID.equals("PDL"))
			{

				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
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
			if(ProductID.equals("TLP"))
			{

				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					test.log(LogStatus.PASS, "WriteOff Void is Completed Successfully ");
					driver.findElement(By.name("Ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "WriteOff Void is not Completed Successfully ");
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

public void PrincipalReduction_4thInst(String SSN,String FileName) throws Exception
{
	test.log(LogStatus.INFO, "Principal Reduction 4thInst");
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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

			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
			}

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String LoanAMT=null;
			String PrincipalAMT=null;

			LoanAMT = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
			test.log(LogStatus.PASS, "Loan Amount ::"+LoanAMT);

			PrincipalAMT = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[5]/td[14]")).getText();
			test.log(LogStatus.PASS, "5% of Loan Amount's,Principal Amount ::"+PrincipalAMT);

		}
	}
}


@Test (priority=31)

public void Newloan_void() throws Exception {

	// Start test. Mention test script name
	String FileName= "BorrowerRegistration_NewLoan_Void_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("AA_Scenario_No_05_"+Header, "Loan_Void on same day");
			appUrl = AppURL;
			 MyCSRLoginpage login = new MyCSRLoginpage();
			 login.Login(UserName, Password, StoreId, driver, AppURL, test);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			//this.Void_Loan(SSN, FileName);
		}
	}
}

//@Test (priority=32)

public void NewLoan_Rescind() throws Exception {

	// Start test. Mention test script name
	String FileName= "BorrowerRegistration_NewLoan_Rescind_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("AA_Scenario_No_07_"+Header, "Loan_Rescind on same day");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Rescind(SSN, FileName);

		}
	}
}

//@Test (priority=33)

public void NewLoan_EODon10DaysBeforeInstallmentDuedate_RenewalLaterShouldBePrinted() throws Exception {

	// Start test. Mention test script name
	String FileName= "NewLoan_EODon10DaysBeforeInstallmentDuedate_RenewalLaterShouldBePrinted_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_15"+Header, "Loan_EOD on 10 days before Installment due date_Renewal Letter should be printed.");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Renewal_Status(SSN, FileName);
		}
	}
	//this.Login("CSR353","1234","353");

}

//@Test (priority=34)

public void NewLoan_EODon10DaysBeforeInstallmentDuedate_RenewalLaterShouldBePrinted_OnDueDateEOD_Renewed() throws Exception {

	// Start test. Mention test script name
	String FileName= "NewLoan_EODon10DaysBeforeInstallmentDuedate_RenewalLaterShouldBePrinted_OnDueDateEOD_Renewed_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_16"+Header, "Loan_EOD on 10 days before Installment due date_Renewal Letter should be printed_EOD On Due date_Loan should Renewed.");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore_1stInst_ONDueDate(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Renewal_Status(SSN, FileName);
		}
	}
}

//@Test (priority=35)

public void NewLoan_PartialPayment_GenerateRenewLater10DaysBeforeDueDate_ShouldnotgenerateTheRenewalLater() throws Exception {

	// Start test. Mention test script name
	String FileName= "NewLoan_PartialPayment_GenerateRenewLater10DaysBeforeDueDate_ShouldnotgenerateTheRenewalLater_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_21"+Header, "Title Loan_partial payment and stop the renewal(before letter generate)_trying to generate Renewal Letter 10 days before due date_Should not generate the Renewal Letter");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Loandate_AgeStore(SSN, FileName,+5);
			this.Payment_PartialAmount(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Renewal_Status1(SSN, FileName);
		}
	}
}

//@Test (priority=36)

public void NewLoan_PartialPayment_GenerateRenewLater10DaysBeforeDueDate_ShouldnotgenerateTheRenewalLater_PartialPayment() throws Exception {

	// Start test. Mention test script name
	String FileName= "NewLoan_PartialPayment_GenerateRenewLater10DaysBeforeDueDate_ShouldnotgenerateTheRenewalLater_PartialPayment_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_23"+Header, "Title Loan_partial payment and stop the renewal(before letter generate)_trying to generate letter 10 days before due date_Should not generate the Renewal Letter_partial payment (Stp Rnwl = N)_Renewal Letter Should Print after Payment");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Loandate_AgeStore(SSN, FileName,+5);
			this.Payment_PartialAmount(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Payment_PartialAmount(SSN, FileName);
			this.Renewal_Status1(SSN, FileName);
		}
	}
}



//@Test (priority=37)

public void NewLoan_PartialPayment_GenerateRenewLater10DaysBeforeDueDate_ShouldnotgenerateTheRenewalLater_1stand2ndinstallmentPayment_on2ndinstallmentdurationdate() throws Exception {

	// Start test. Mention test script name
	String FileName= "NewLoan_PartialPayment_GenerateRenewLater10DaysBeforeDueDate_ShouldnotgenerateTheRenewalLater_1stand2ndinstallmentPayment_on2ndinstallmentdurationdate_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_27"+Header, "Title Loan_partial payment and stop the renewal(before letter generate)_trying to generate letter 10 days before due date_Should not generate the letter_Run EOD on 1st inst due date_Title Loan should not be renew_Make 1st installment payment on 2nd installment duration and again stop renewal_payment should be applied 1st installment only.");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Loandate_AgeStore(SSN, FileName,+5);
			this.Payment_PartialAmount(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName,-10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName,0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			//this.AgeStore_2ndInst_DueDate(SSN, FileName,0);
			this.Payment_1stInterestAmt_on2ndinstduration(SSN, FileName);
			this.Loan_1stinstallment_status(SSN, FileName);
		}
	}
}

//@Test (priority=38)
public void Loan_GenerateLetter_EOD1stInstDate_EOD2ndInstDate_Default_DefaultPmt_LoanShouldbeClosed() throws Exception {

	String FileName= "Loan_GenerateLetter_EOD1stInstDate_EOD2ndInstDate_Default_DefaultPmt_LoanShouldbeClosed_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);  
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
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);	
			String Header = StateID+ "_" + ProductID;
			test = reports.startTest("AA_Scenario_No_34"+Header, "Title Loan_Generate letter 10 days before due date_Run EOD on Due date of 1st Installment_Run EOD on 2nd Installment date and customer status changed to default_age the store_Perform the Default payment full_Loan should be closed.");
			appUrl = AppURL;

			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.AgeStore1_1_C(SSN, FileName, -10); 
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore1_1_C(SSN, FileName, 0); 
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore1_2_C(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore1_1BatchProcess_C(SSN, FileName, 0);
			this.AgeStore1_2_C(SSN, FileName, 1);
			this.DefaultPayment(SSN, FileName);
			this.Loan_Status(SSN, FileName);
		}
	}
}

//@Test (priority=39)
public void TwoLoans_singleVIN_OneDFLT_Other_ShouldNotDFLT2_Surrender_Salvage_Waive1() throws Exception {

	String FileName= "TwoLoans_singleVIN_OneDFLT_Other_ShouldNotDFLT2_Surrender_Salvage_Waive_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);  
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
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);	
			String Header = StateID+ "_" + ProductID;
			test = reports.startTest("AA_Scenario_No_49_"+Header, "Originate two Loans on Same VIN_make one loan to Default_Other loan should not become Default_Make Repossession for one loan_Repossession should post for Other Loan also_Make salvage for the Loan with less amount_Remaining amount should be waived_Other Loan should also waived");
			appUrl = AppURL;

			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoanWithVIN(SSN, FileName,NewVIN);
			this.LoanDate_Agestore_C(SSN, FileName,5);
			this.NewLoanWithVIN3(SSN, FileName,NewVIN);
			this.AgeStore1_1_C(SSN, FileName, 0); 
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore2_1_C(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName); 
			this.AgeStore1_1BatchProcess_C(SSN, FileName, 0);
			this.DefaultPaymentStatus1_C(SSN, FileName);	
			this.OutToRepo(SSN, FileName, 1);				
			this.Repossession(SSN, FileName, 1);
			this.DefaultPaymentStatus1_C(SSN, FileName);	
			this.LESS_Salvage(SSN, FileName, 21);
			this.WAVE_Stastus1(SSN, FileName);
			this.WAVE_Stastus2(SSN, FileName);
		}
	}
}

//@Test (priority=40)

public void Newloan_Default_Writeoff_WriteOffRecovery_LoanShouldClosed() throws Exception {
	//Start test. Mention test script name
	String FileName= "Newloan_Default_Writeoff_WriteOffRecovery_LoanShouldClosed_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			
			test = reports.startTest("Scenario_No_59"+Header, "Title Loan_Default_Write Off_Write Off Recovery payment_Loan should close.");
			appUrl = AppURL;
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.AgeStore1_1_C(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore1_1BatchProcess_C(SSN, FileName, 0);
			this.AgeStore_Default_WriteOff60Days(SSN, FileName, +60);
			this.WO_Recovery(SSN, FileName);
			this.Loan_Status(SSN, FileName);
		}
	}
}

//@Test (priority=41)

public void Newloan_Default_Writeoff_WriteOffRecovery_void_LoanOpened() throws Exception {

	// Start test. Mention test script name
	String FileName= "Newloan_Default_Writeoff_WriteOffRecovery_void_LoanOpened_TestData.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_60"+Header, " Title Loan_Default_Write Off_Write Off Recovery payment_Loan should close_Void WOR_Loan should Open.");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.AgeStore1_1_C(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.AgeStore1_1BatchProcess_C(SSN, FileName, 0);
			this.AgeStore_Default_WriteOff60Days(SSN, FileName, +60);
			this.WO_Recovery(SSN, FileName);
			this.Loan_Status(SSN, FileName);
			this.WO_Void(SSN, FileName);
			this.Loan_Status(SSN, FileName);
		}
	}
}

//Anji
//@Test (priority=42)

public void Newloan_1stinstTo3rdinstPayment_check4thinstpayment() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_Newloan_1stinstTo3rdinstPayment_check4thinstpayment_Txn_Testdata.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);  
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
			test = reports.startTest("AA_Loan_1stTo3rdInstPayment_checkthe4thinstAmount"+Header, "Checkthe4thinstprincipalAmount");
			appUrl = AppURL;

			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			Reg.RegistrationPage_NewLoan_TLP(driver, test, AppURL, SSN, FileName);
			this.NewLoan(SSN, FileName); 
			this.AgeStore_1stInst(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Payment(SSN, FileName);
			this.AgeStore_1stInst(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName); 
			this.StatementGeneration_EODProcessing(SSN, FileName);			
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName); 
			this.AgeStore_2ndInst(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName); 
			this.Payment(SSN, FileName);
			this.AgeStore_2ndInst(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName); 
			this.AgeStore_3rdInst(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);//this.EODProcessing(SSN, FileName);   
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName); 
			this.Payment(SSN, FileName);
			this.AgeStore_3rdInst(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.PrincipalReduction_4thInst(SSN, FileName);


		}
	}
}

//@Test (priority=43)

public void AA_NewLoan_PartialPayment_StopRenew_EODDuedate () throws Exception {

	String FileName= "AA_NewLoan_PartialPayment_StopRenew_EODDuedate_Txn.xls";

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);

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
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);

			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);

			test = reports.startTest("Scenario_No_25 "+Header, "Title Loan --> partial payment and stop the renewal(before letter generate) --> trying to generate letter 10 days before due date --> should not generate the letter--> Run EOD on 1st inst due date --> Title Loan should not be renewed");

			appUrl = AppURL;

			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			this.Biweek_due_RegistrationPage(SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Loandate_AgeStore(SSN, FileName, +5);
			this.PartialPayment(SSN, FileName);
			this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);		

		}

	}



}

//@Test (priority=44)

public void AA_NewLoan_PPayment_StpY_2inst_FeePriciple_PPayment () throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_NewLoan_PPayment_StpY_2inst_FeePriciple_PPayment_Txn.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_28"+Header, "Title Loan –> Partial Payment ( Stp Rnwl = Y)--> Auto Renewal not posted – 2nd Installment bucket : Payment with first Installment amount(stop Renewal = Y) –> should effect to principal & 1st Inst Fee amt –> prtl pmnt(Stp Rnwl = N) –> Missed Renewal letter & record should post with latest values.");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			this.Biweek_due_RegistrationPage(SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Loandate_AgeStore(SSN, FileName,+5);
			this.Payment_PartialAmount(SSN, FileName);
			this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Payment(SSN, FileName);
			this.Payment_PartialAmount_NO(SSN, FileName);
			this.Loan_Status_Inst(SSN, FileName);
		}
	}
}

//@Test (priority=45)

public void AA_NewLoan_PPayment_StpY_2ndInstDuration_Payment_StpN () throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_NewLoan_PPayment_StpY_2ndInstDuration_Payment_StpN_Txn.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);   
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
			test = reports.startTest("Scenario_No_29"+Header, "Title Loan ---> Partial Payment(Stop Renewal = Y) --> 2 Bckt : After renewal Letter Date make Payment with first installment amt(Stp Rnwl = N) --> Two Renewal Letters for 1 & 2 post with Payment date.");
			appUrl = AppURL;
			this.Login(UserName,Password,StoreId);
			this.Biweek_due_RegistrationPage(SSN, FileName);
			this.NewLoan(SSN, FileName);
			this.Loandate_AgeStore(SSN, FileName,+5);
			this.Payment_PartialAmount(SSN, FileName);
			this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
			this.DrawerDeassign(SSN, FileName);
			this.StatementGeneration_EODProcessing(SSN, FileName);
			this.StoreInfo(SSN, FileName);
			this.Safeassign(SSN, FileName);
			this.Drawerassign(SSN, FileName);
			this.Payment_No(SSN, FileName);
		}
	}
}

//@Test (priority=46)

public void AA_OneCustomer_OneVIN_TwoLoans_Writoff() throws Exception {

	String FileName= "AA_OneCustomer_OneVIN_TwoLoans_Writoff_Txn.xls";
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Critical/"+FileName);  
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
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			String StateID = TestData.getCellData(sheetName,"StateID",row);
			String SSN = TestData.getCellData(sheetName,"SSN",row);	
			String Header = StateID+ "_" + ProductID;
			test = reports.startTest("Scenario_No_42"+Header, "Originate two Loans on Same VIN –> make one loan to Write Off – Other loan should also become Write Off");
			appUrl = AppURL;
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			
			this.Biweek_due_RegistrationPage(SSN, FileName);
			this.NewLoanWithVIN1(SSN, FileName,NewVIN);
			this.NewLoanWithVIN2(SSN, FileName,NewVIN);
			this.AgeStore1_1BatchProcess_C(SSN, FileName, +90);				
			this.AgeStore_Default_WriteOff60Days(SSN, FileName, +60);
			this.Loan_Status_Loan1(SSN, FileName);
			this.Loan_Status_Loan2(SSN, FileName);

		}

	}

}
	
	
@AfterMethod
 public void getResult(ITestResult result) throws Exception{
if(ITestResult.FAILURE == result.getStatus()){
	
	String screenshotPath = TLP_ExecutionScripts.getScreenshot(driver, result.getName());
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

public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
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

//driver.quit();	
}

}
