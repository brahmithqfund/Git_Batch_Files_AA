package Tests;

	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Test;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;

	import java.io.File;
	import java.io.IOException;
	
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
	public class TLP_SmokeExecution 
	{
		public WebDriverWait wait;	
		WebDriver driver;
		String appUrl;
		static ExtentReports reports;
		ExtentTest test;
		 
@BeforeClass
		
		public synchronized void initialize()
		{
			String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
			String filename="AA_TLP_SmokeTesting_Scenarios_"+timestamp+".html";
			reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/TLP_Smoke/"+filename, true);
			}
		
@BeforeMethod
		 public void killProcess() throws Exception
		      {
		          Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		          Thread.sleep(5000); //Allow OS to kill the process
		          System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
			  		driver = new InternetExplorerDriver();
			  		driver.manage().window().maximize();
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		        }
public void IETaskKiller() throws IOException, InterruptedException {

	Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
	Thread.sleep(2000); //Allow OS to kill the process
	System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
	driver = new InternetExplorerDriver();		
}

 public void Login (String username,String password,String storenumber) throws Exception
		{
			driver.get(appUrl);
			test.log(LogStatus.INFO, "CSR Application is launched with ::"+appUrl);
			driver.manage().window().maximize();
			String usenameId = "loginRequestBean.userId";
			String passwordId = "loginRequestBean.password";
			String StoreId = "loginRequestBean.locNbr";
			String Login = "login";
			driver.findElement(By.name(usenameId)).click();
			driver.findElement(By.name(usenameId)).clear();
			driver.findElement(By.name(usenameId)).sendKeys(username);
			test.log(LogStatus.PASS, "Username is entered: "+username);
			driver.findElement(By.name(passwordId)).clear();
			driver.findElement(By.name(passwordId)).sendKeys(password);
			test.log(LogStatus.PASS, "Password is entered: "+password);
			driver.findElement(By.name(StoreId)).clear();
			driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
			test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
			driver.findElement(By.name(Login)).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(4000);
		}

public void RegistrationPage(String SSN,String FileName) throws Exception
{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);   		
			int lastrow=TestData.getLastRow("Borrower_Registration");
			String sheetName="Borrower_Registration";		
			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{	
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					String ProductID = TestData.getCellData(sheetName,"ProductID",row);       
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
					if(ProductID.equals("TLP"))
					{
						driver.findElement(By.name("customerBean.eyeColor")).sendKeys("Black");
						test.log(LogStatus.PASS, "Eye Color is entered: Black");
						driver.findElement(By.name("customerBean.feet")).sendKeys("5");
						test.log(LogStatus.PASS, "Height is entered: 5");
						driver.findElement(By.name("customerBean.inches")).sendKeys("5");
						test.log(LogStatus.PASS, "Inches is entered: 5");
					}
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
					try
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();												
					}
					catch (NoAlertPresentException e)
					{
						//do what you normally would if you didn't have the alert.
					}
					for(String winHandle : driver.getWindowHandles())
					{
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
					try 
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();												

					}
					catch (NoAlertPresentException e) 
					{
						
					}
				}
			}
		}

public boolean isAlertPresent()
{
			try
			{
				driver.switchTo().alert();
				return true;
			}
			catch(NoAlertPresentException ex)
			{
				return false;
			}
		}
		
public void NewLoan(String SSN,String FileName) throws Exception
{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);   	
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
						if(ProductID.equals("TLP"))							
						{												
							driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
							driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();
							Thread.sleep(2000);
						}	
						if(ProductName.equals("Tennessee"))
						{
							//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						//---------Added by Srikanth
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
							Thread.sleep(2000);
							test.log(LogStatus.PASS, "click on Update 2 button ");
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
							driver.findElement(By.name("process")).click();
							test.log(LogStatus.PASS, "click on process Loan button ");
							try { 
								Alert alert = driver.switchTo().alert();
								alert.accept();	
							}
							catch (NoAlertPresentException e) 
							{
							
							}
							Thread.sleep(2000);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
							driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
							test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
							driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
							System.out.println(Instamt);
							driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
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
									}
									catch (NoAlertPresentException e)
									{
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
		
public void NewLoan1(String SSN,String FileName) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);      	
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
					String ESign_DisbType2 = TestData.getCellData(sheetName,"Esign_DisbType2",row);
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
					if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
					{
						if(ProductID.equals("TLP"))							
						{					
							System.out.println("IN TLP");
							driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
							driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
							//Thread.sleep(3000);
							driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();	
							//Thread.sleep(20000);
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
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
								test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
							}
							if(StoreID.equals("1343"))
							{
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
								test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
							}

						}
						//-------------Added by Srikanth
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
						 //-----------Till here
						/*driver.findElement(By.name("ShareScreenBtn")).click();

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

						driver.switchTo().window(Parent_Window);*/

						/*driver.findElement(By.name("ShareScreenBtn")).click();
						test.log(LogStatus.PASS, "ShareScreen Button clicked");
						for( String winHandle1 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle1);

						}
						Thread.sleep(3000);
						driver.findElement(By.name("confirmSummary")).click();
						test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						Thread.sleep(3000);
						driver.switchTo().window(Parent_Window);*/

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
									try 
									{ 
										Alert alert = driver.switchTo().alert();
										alert.dismiss();
									}
									catch (NoAlertPresentException e)
									{
										
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
							try
							{ 
								Alert alert = driver.switchTo().alert();
								String Var1 = alert.getText();
								test.log(LogStatus.PASS, "ALert Displayed is :: "+Var1);
								alert.accept();												
							}
							catch (NoAlertPresentException e) 
							{
							}
							try 
							{ 
								Alert alert = driver.switchTo().alert();
								String Var = alert.getText();
								test.log(LogStatus.PASS, "ALert Displayed is :: "+Var);
								alert.accept();
							}
							catch (NoAlertPresentException e)
							{
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
									}
									catch (NoAlertPresentException e) 
									{
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
							WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
							String NumofInst=ele.getAttribute("value");
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
							try
							{ 
								Alert alert = driver.switchTo().alert();
								alert.accept();					
							}
							catch (NoAlertPresentException e)
							{
							}		
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
							driver.findElement(By.name("OKBut")).click();
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
							driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
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
							driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);							//requestBean.licensePltNbr
							driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);							//requestBean.licensePltExpire
							driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);							//requestBean.paintCondition
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
							Thread.sleep(2000);
							test.log(LogStatus.PASS, "click on Update 2 button ");						
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
							driver.findElement(By.name("process")).click();
							test.log(LogStatus.PASS, "click on process Loan button ");
							try 
							{ 
								Alert alert = driver.switchTo().alert();
								alert.accept();												
							}
							catch (NoAlertPresentException e)
							{
							}
							Thread.sleep(2000);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
							driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
							test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
							driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType2);
							test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType2);
							String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");
							System.out.println(Instamt);
							driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys("300");
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+"200");
							driver.findElement(By.name("requestBean.siilBean.disbTypeSecond")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type2 is selected as ::"+ESign_DisbType);
							try { 
								Alert alert = driver.switchTo().alert();
								alert.accept();														
							}
							catch (NoAlertPresentException e)
							{
							}
							driver.findElement(By.name("requestBean.siilBean.disbAmtSecond")).sendKeys("260");
							test.log(LogStatus.PASS, "Disb Amt2 is enterted as 260");
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
									try 
									{ 
										Alert alert = driver.switchTo().alert();
										alert.dismiss();													
									}
									catch (NoAlertPresentException e) 
									{
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
							try 
							{ 
								Alert alert = driver.switchTo().alert();
								alert.dismiss();												

							}
							catch (NoAlertPresentException e)
							{
							}
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
							driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
							driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
					}
					
				}
			}



		public void Void(String SSN,String FileName) throws Exception{


			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);    	
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
					String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
					String ESign_DisbType2 = null;
					//TestData.getCellData(sheetName,"ESign_DisbType2",row);
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					driver.switchTo().defaultContent();	
					WebDriverWait wait = new WebDriverWait(driver, 30);
					//Thread.sleep(4000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
					driver.switchTo().frame("topFrame");
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
					driver.findElement(By.cssSelector("li[id='910000']")).click();	
					driver.switchTo().defaultContent();				
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					//Thread.sleep(1000);
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
					//Thread.sleep(5000);					
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
					if(ProductID.equals("TLP"))
					{
						System.out.println("under TLP for button Go");
						driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
						System.out.println("under TLP after button Go");
					}
					else
					{
						driver.findElement(By.id("go_Button")).click();
					}
					try
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														
					}
					catch (NoAlertPresentException e) 
					{
						//do what you normally would if you didn't have the alert.
					}					
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
				/*	
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
					}*/

				/*	
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");*/

					if(ProductID.equals("TLP"))
					{
						// name="tenderType"
						// tenderType
						// driver.findElement(By.name("tenderType")).sendKeys(TenderType);
						//name="tenderType"
						//html/body/form[1]/table/tbody/tr/td/table/tbody[2]/tr[5]/td/table/tbody/tr[11]/td[2]/select
						//driver.findElement(By.name("tenderType")).click();
					//	Thread.sleep(4000);
						System.out.println("under TLP for selected overdue");
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody[2]/tr[5]/td/table/tbody/tr[11]/td[2]/select")).sendKeys("Original Tender");
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
						// name="requestBean.password"
						// name="finish"  name="finish"
						// name="requestBean.password"
						driver.findElement(By.name("requestBean.password")).sendKeys(Password);
						driver.findElement(By.name("finish")).click();
						test.log(LogStatus.PASS, "Password is selected as "+Password);																					
						test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
					}		


					try 
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();
					}
					catch (NoAlertPresentException e)
					{
						
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
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
						}
					}
				}

			}
		}


public void SafeDeassign(String SSN,String FileName) throws Exception
{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					driver.findElement(By.name("safedeassign")).click();
					try 
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();
					}
					catch (NoAlertPresentException e) 
					{
					}

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");
					driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");
					driver.findElement(By.name("safedeassign")).click();
					try 
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();													
					}
					catch (NoAlertPresentException e) 
					{
						
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.name("safeDeassignRequestBean.comments")).sendKeys("chenna");
					driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");

					driver.findElement(By.name("safedeassign")).click();

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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.name("yes")).click();
					driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");
					driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
					driver.findElement(By.name("drawerassign")).click();
					try
					{ 
						Alert alert = driver.switchTo().alert();
						alert.accept();													
					}
					catch (NoAlertPresentException e) 
					{
					
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

public void DrawerDeassign(String SSN,String FileName) throws Exception{
			//-------------done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
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
						}
						catch (Exception e)
						{
						}
						Thread.sleep(2000);
						
						driver.findElement(By.name("drawerDeassignRequestBean.password")).clear();
						driver.findElement(By.name("drawerDeassignRequestBean.password")).click();
						driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);				
						driver.findElement(By.name("drawerdeassign")).click();
						
						try{
							Alert alert = driver.switchTo().alert();
							alert.accept();
						}
						catch (Exception e)
						{
						}
						Thread.sleep(2000);
						
						for(String winHandle : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
						{
							 WebElement htmltable=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));	
							    
								List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
								int count=0;							
								 count=driver.findElements(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr")).size();				 				
								for(int rnum=1;rnum<rows.size();rnum++)
								{ 				
									driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select")).sendKeys("Delete");
									driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).click();						
									try { 
										Alert alert = driver.switchTo().alert();
										alert.accept();													
									}
									catch (NoAlertPresentException e) 
									{
										
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
						}
						catch (NoAlertPresentException e) 
						{
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

			//-------------done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
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
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						Thread.sleep(2000);
						driver.findElement(By.name("Submit2")).click();
						test.log(LogStatus.PASS,"Clicked on Balance Safe");
						
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
						}
						catch (NoAlertPresentException e)
						{

						}
						Thread.sleep(5000);
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
						Thread.sleep(3000);
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
						test.log(LogStatus.PASS, "Clicked on Next");
						Thread.sleep(20000);
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
						Thread.sleep(20000);
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
			//-------------done updating
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
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
				
					driver.findElement(By.name("login")).click();
					test.log(LogStatus.PASS, "Clicked on Submit button");
					
					Thread.sleep(4000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					
			driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Store Setup");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			
			/*WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Config")));*/
			driver.findElement(By.linkText("Store Config")).click();
			test.log(LogStatus.PASS, "Clicked on Store Config");
			driver.findElement(By.linkText("Edit Store")).click();
			test.log(LogStatus.PASS, "Clicked on Edit Store");			
			
			driver.switchTo().frame("main");		
			  driver.findElement(By.name("locationBean.locNbr")).sendKeys(StoreID);
			  test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			  driver.findElement(By.name("Submit2")).click();
			  test.log(LogStatus.PASS, "Clicked on submit button");
			  for(String winHandle : driver.getWindowHandles())
			  {
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

public void Safeassign(String SSN,String FileName) throws Exception
{
			//-------------done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					
						WebDriverWait wait = new WebDriverWait(driver, 30);
						driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Safe")));
						driver.findElement(By.linkText("Safe")).click();
						test.log(LogStatus.PASS, "Clicked on Assign");	
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Assign")));
						driver.findElement(By.linkText("Assign")).click();
						test.log(LogStatus.PASS, "Clicked on Assign");
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				      //  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("safeassign")));
						driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys(Password);
						driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
						driver.findElement(By.name("safeassign")).click();
						
						try 
						{ 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();	
						}
						catch (NoAlertPresentException e) 
						{
						}
						Thread.sleep(2000);
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

/*public void Drawerassign(String SSN,String FileName) throws Exception{

			//-------------done updating
			   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 		
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
			                    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
			                			}
			                			catch (NoAlertPresentException e)
			                			{
			                			}
			                			for(String winHandle : driver.getWindowHandles())
			                			{
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
			                            Thread.sleep(2000);
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
			                			}
			                			catch (NoAlertPresentException e)
			                			{
			                			}
			                			for(String winHandle : driver.getWindowHandles())
			                			{
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
			                            Thread.sleep(2000);
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
			                			}
			                			catch (NoAlertPresentException e)
			                			{
			                			}
			                			for(String winHandle : driver.getWindowHandles())
			                			{
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
			                            Thread.sleep(2000);
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
			                			}
			                			catch (NoAlertPresentException e)
			                			{
			                			}
			                			for(String winHandle : driver.getWindowHandles())
			                			{
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
			                            Thread.sleep(2000);
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
*/



/*public void Drawerassign(String SSN, String FileName) throws Exception {

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan " + lastrow);
	String sheetName = "NewLoan";
	for (int row = 2; row <= lastrow; row++) {
		String RegSSN = TestData.getCellData(sheetName, "SSN", row);
		if (SSN.equals(RegSSN)) {
			String TxnType = TestData.getCellData(sheetName, "TxnType", row);
			String TenderType = TestData.getCellData(sheetName, "TenderType", row);
			String ProductID = TestData.getCellData(sheetName, "ProductID", row);
			String AppURL = TestData.getCellData(sheetName, "AppURL", row);
			String UserName = TestData.getCellData(sheetName, "UserName", row);
			String Password = TestData.getCellData(sheetName, "Password", row);
			String StoreId = TestData.getCellData(sheetName, "StoreID", row);

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
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
			test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

			driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Passwored is Entered");
			driver.findElement(By.name("drawerassign")).click();
			test.log(LogStatus.PASS, "Clicked on Drawer Assigen Button");
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();

			} catch (NoAlertPresentException e) {

			}

			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if (this.Field(driver) != null) {
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
				test.log(LogStatus.PASS, "Click on the Safe Deassign Button");

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {

				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {
					test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
					driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
				} else {
					driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Enter the Password");
					driver.findElement(By.name("safedeassign")).click();
					test.log(LogStatus.PASS, "Click on the Deassign");
					for (String winHandle : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DrawerOverShortAmount = driver.findElement(By.name("safeRequestBean.safeOverShort"))
							.getAttribute("value");
					driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);
					test.log(LogStatus.PASS, "Amount entered as " + DrawerOverShortAmount);
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

					} catch (NoAlertPresentException e) {
					}
					Thread.sleep(2000);
					for (String winHandle : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if (driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {

						test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
					} else {
						test.log(LogStatus.PASS, "Safe not De-assigned successfully with over/short.");
					}
				}
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");
				driver.findElement(By.linkText("Assign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");
				Thread.sleep(5000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
				test.log(LogStatus.PASS, "Passwored is Entered");

				driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("safeassign")).click();
				test.log(LogStatus.PASS, "Click on Safe Assigen");

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {

				}
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {

					test.log(LogStatus.PASS, "Safe assigned successfully.");
					driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
				} else {
					test.log(LogStatus.PASS, "Safe not assigned successfully.");
				}

				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Passwored is Entered");
				driver.findElement(By.name("drawerassign")).click();
				test.log(LogStatus.PASS, "Click on drawer Assigen Button");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {

				}
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed()) {

					test.log(LogStatus.PASS, "Drawer De-assigned successfully with over/short.");
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
				} else {
					test.log(LogStatus.PASS, "Drawer not De-assigned successfully with over/short.");
				}

			} else {
				if (driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed()) {

					test.log(LogStatus.PASS, "Drawer Assigned successfully with over/short.");
					// driver.findElement(By.name("done")).click();
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
				} else {
					test.log(LogStatus.PASS, "Drawer not Assigned successfully with over/short.");
				}
			}

		}
	}
	// driver.quit();
	// driver.close();
}
*/


public WebElement Field(WebDriver driver)
{

    try {
        Thread.sleep(500);
        WebElement element = (new WebDriverWait(driver, 
9)).until(ExpectedConditions 
.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
        return element;
    } catch (Exception e) {
        return null;
    }
}


public void Drawerassign(String SSN, String FileName) throws 
Exception {

    Excel TestData = new 
//Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/" + FileName);
    		Excel("E:/QfundTesting/TestData/TLP_Smoke/" + FileName);
    		//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
    int lastrow = TestData.getLastRow("NewLoan");
    System.out.println("NewLoan " + lastrow);
    String sheetName = "NewLoan";
    for (int row = 2; row <= lastrow; row++) {
        String RegSSN = TestData.getCellData(sheetName, "SSN", row);
        if (SSN.equals(RegSSN)) {
            String TxnType = TestData.getCellData(sheetName, 
"TxnType", row);
            String TenderType = TestData.getCellData(sheetName, 
"TenderType", row);
            String ProductID = TestData.getCellData(sheetName, 
"ProductID", row);
            String AppURL = TestData.getCellData(sheetName, 
"AppURL", row);
            String UserName = TestData.getCellData(sheetName, 
"UserName", row);
            String Password = TestData.getCellData(sheetName, 
"Password", row);
            String StoreId = TestData.getCellData(sheetName, 
"StoreID", row);

            Thread.sleep(5000);

            CSRLoginpage login = new CSRLoginpage();
            login.Login(UserName, Password, StoreId, driver, 
AppURL, test);
            Thread.sleep(5000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("topFrame");
driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();
            test.log(LogStatus.PASS, "Clicked on Cash Management");
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(120, 
TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.manage().timeouts().implicitlyWait(60, 
TimeUnit.SECONDS);
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
            test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
            test.log(LogStatus.PASS, "Passwored is Entered");
driver.findElement(By.name("drawerassign")).click();
            test.log(LogStatus.PASS, "Clicked on Drawer Assigen Button");
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();

            } catch (NoAlertPresentException e) {

            }

            Thread.sleep(2000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame("mainFrame");
            driver.switchTo().frame("main");
            if (this.Field(driver) != null) {
                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(120, 
TimeUnit.SECONDS);
                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.manage().timeouts().implicitlyWait(60, 
TimeUnit.SECONDS);
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
                test.log(LogStatus.PASS, "Click on the Safe Deassign Button");

                try {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();

                } catch (NoAlertPresentException e) {

                }
                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.switchTo().frame("main");
                if 
(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {
                    test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
                } 
                else 
                {
driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
                    test.log(LogStatus.PASS, "Enter the Password");
driver.findElement(By.name("safedeassign")).click();
                    test.log(LogStatus.PASS, "Click on the Deassign");
                    for (String winHandle : 
driver.getWindowHandles()) {
                        driver.switchTo().window(winHandle);
                    }
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame("mainFrame");
                    driver.switchTo().frame("main");
                    String DrawerOverShortAmount = 
driver.findElement(By.name("safeRequestBean.safeOverShort"))
                            .getAttribute("value");
driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);
                    test.log(LogStatus.PASS, "Amount entered as " + 
DrawerOverShortAmount);
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

                    } catch (NoAlertPresentException e) {
                    }
                    Thread.sleep(2000);
                    for (String winHandle : 
driver.getWindowHandles()) {
                        driver.switchTo().window(winHandle);
                    }
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame("mainFrame");
                    driver.switchTo().frame("main");

                    if 
(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {

                        test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
                    } else {
                        test.log(LogStatus.PASS, "Safe not De-assigned successfully with over/short.");
                    }
                }
                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(120, 
TimeUnit.SECONDS);
                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.manage().timeouts().implicitlyWait(60, 
TimeUnit.SECONDS);
driver.findElement(By.linkText("Safe")).click();
                test.log(LogStatus.PASS, "Clicked on Assign");
driver.findElement(By.linkText("Assign")).click();
                test.log(LogStatus.PASS, "Clicked on Assign");
                Thread.sleep(5000);

                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.switchTo().frame("main");

driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
                test.log(LogStatus.PASS, "Passwored is Entered");

driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
                test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

driver.findElement(By.name("safeassign")).click();
                test.log(LogStatus.PASS, "Click on Safe Assigen");

                try {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();

                } catch (NoAlertPresentException e) {

                }
                Thread.sleep(5000);
                driver.manage().timeouts().implicitlyWait(120, 
TimeUnit.SECONDS);

                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.switchTo().frame("main");

                if 
(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {

                    test.log(LogStatus.PASS, "Safe assigned successfully.");
driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
                } else {
                    test.log(LogStatus.PASS, "Safe not assigned successfully.");
                }

                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(120, 
TimeUnit.SECONDS);
                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.manage().timeouts().implicitlyWait(60, 
TimeUnit.SECONDS);
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
                test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
                test.log(LogStatus.PASS, "Passwored is Entered");
driver.findElement(By.name("drawerassign")).click();
                test.log(LogStatus.PASS, "Click on drawer Assigen Button");
                try {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();

                } catch (NoAlertPresentException e) {

                }
                Thread.sleep(2000);
                driver.switchTo().defaultContent();
                driver.switchTo().frame("mainFrame");
                driver.switchTo().frame("main");

                if 
(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed()) 
{

                    test.log(LogStatus.PASS, "Drawer De-assigned successfully with over/short.");
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
                } else {
                    test.log(LogStatus.PASS, "Drawer not De-assigned successfully with over/short.");
                }

            } else {
                if 
(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed()) 
{

                    test.log(LogStatus.PASS, "Drawer Assigned successfully with over/short.");
                    // driver.findElement(By.name("done")).click();
driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
                } else {
                    test.log(LogStatus.PASS, "Drawer not Assigned successfully with over/short.");
                }
            }

        }
    }
    // driver.quit();
    // driver.close();
}


		public void EODProcessing(String SSN,String FileName) throws Exception{

//------------done
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
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
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.findElement(By.name("Submit2")).click();
					test.log(LogStatus.PASS,"Clicked on Balance Safe");
					//Thread.sleep(1000);
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

		
						
		

		public void AgeStore_1stLoan_1ndInst(String SSN,String FileName,int days) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	


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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
							}

							 //String winHandleBefore = driver.getWindowHandle();
							 for(String winHandle : driver.getWindowHandles()){
								    driver.switchTo().window(winHandle);
								}
							 Thread.sleep(8000);
							  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]
					DueDate=driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
					//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]	
					System.out.print(DueDate);	
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
					storeupdate(UserName,Password,StoreID,DueDate,AdminURL,days);
				}
			}
		}


		public void AgeStore_1stLoan_2ndInst(String SSN,String FileName,int days) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	


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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
							}

							 //String winHandleBefore = driver.getWindowHandle();
							 for(String winHandle : driver.getWindowHandles()){
								    driver.switchTo().window(winHandle);
								}
							 Thread.sleep(8000);
							  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]
					DueDate=driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();
					//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]	
					System.out.print(DueDate);	
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
					storeupdate(UserName,Password,StoreID,DueDate,AdminURL,days);
				}
			}
		}


		public void AgeStore_2ndLoan_1stInst(String SSN,String FileName,int days) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	


						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
							}

							 //String winHandleBefore = driver.getWindowHandle();
							 for(String winHandle : driver.getWindowHandles()){
								    driver.switchTo().window(winHandle);
								}
							 Thread.sleep(8000);
							  // driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DueDate=driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
					//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]	
					System.out.print(DueDate);	
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
					storeupdate(UserName,Password,StoreID,DueDate,AdminURL,days);
				}
			}
		}

		public void AgeStore_2ndLoan_2stInst(String SSN,String FileName,int days) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
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
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	


						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
							}

							 //String winHandleBefore = driver.getWindowHandle();
							 for(String winHandle : driver.getWindowHandles()){
								    driver.switchTo().window(winHandle);
								}
							 Thread.sleep(8000);
							  // driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DueDate=driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();
					//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]	
					System.out.print(DueDate);	
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
					// days = days;
					storeupdate(UserName,Password,StoreID,DueDate,AdminURL, days);
				}
			}
		}


		public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL,int days) throws Exception
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
			cal.add(Calendar.DATE, days);
			Date DDueDateminus1= cal.getTime();

			String DueDateminus1 =df.format(DDueDateminus1);
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
			//Thread.sleep(5000);
		/*	driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);*/
			
			driver.findElement(By.linkText("QA Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on QA Jobs");
			
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
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
			Thread.sleep(2000);
			//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			Thread.sleep(2000);
			//Thread.sleep(8000);
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


			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			// driver.switchTo().defaultContent();
			//  driver.switchTo().frame("mainFrame");


			// for(String winHandle : driver.getWindowHandles()){
			//  driver.switchTo().window(winHandle);
			//}
			// driver.switchTo().defaultContent();
			// driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			// Date DueDate = df.parse(DueDate);
			//Calendar cal = Calendar.getInstance();
			//cal.setTime(DDueDate);
			//cal.add(Calendar.DATE, -1);
			//Date DDueDateminus1= cal.getTime();

			//String DueDateminus1 =df.format(DDueDateminus1);
			//    String DDueDate0[] =DueDate.split("/");
			//    String DDueDate1 = DDueDate0[0];
			//    String DDueDate2 = DDueDate0[1];
			//    String DDueDate3 = DDueDate0[2];


			Thread.sleep(5000);
			// driver.switchTo().defaultContent();
			//driver.switchTo().frame("topFrame");
			//driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			//test.log(LogStatus.PASS, "Clicked on Transactions");
			/*		 driver.switchTo().defaultContent();
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
										        } */
			//driver.close();
		}


		public void DefaultPaymentStatus1(String SSN,String FileName) throws Exception
		{
//------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 	
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	


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
					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]
					CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

					test.log(LogStatus.PASS,"Payment status is Default."+CheckStaus);
					System.out.print(CheckStaus);	
					//driver.close();

				}
			}
		}

		public void DefaultPaymentStatus2(String SSN,String FileName) throws Exception
		{
//-----done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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


						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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
					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]
					CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

					test.log(LogStatus.PASS,"Payment status is Default."+CheckStaus);
					System.out.print(CheckStaus);	
					driver.close();
					driver = new InternetExplorerDriver();

				}
			}
		}

		public void BatchProcess(String SSN,String FileName,int days) throws Exception
		{
//------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					/*Thread.sleep(5000);
					Thread.sleep(1000);*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
							}

							 //String winHandleBefore = driver.getWindowHandle();
							 for(String winHandle : driver.getWindowHandles()){
								    driver.switchTo().window(winHandle);
								}
							 Thread.sleep(8000);
							  // driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
					System.out.print(DueDate);	
					driver.close();

					driver = new InternetExplorerDriver();
					driver.get(AdminURL);
					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

driver.manage().window().maximize();
					DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
					test.log(LogStatus.PASS, "Username is entered: admin");			        
					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
					//Click Login Button
					driver.findElement(By.name("login")).click();
					test.log(LogStatus.PASS, "Clicked on Submit button");
					Thread.sleep(3000);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Transactions");
				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					Date DDueDate = df.parse(DueDate);
					Calendar cal = Calendar.getInstance();
					cal.setTime(DDueDate);
					cal.add(Calendar.DATE,days);
					Date DDueDateminus1= cal.getTime();


					String DueDateminus1 =df.format(DDueDateminus1);
					//String DueDate0[] =DueDateminus1.split("/");
					String DueDate0[] =DueDate.split("/");

					String DueDate1 = DueDate0[0];
					String DueDate2 = DueDate0[1];
					String DueDate3 = DueDate0[2];

					/* driver.findElement(By.linkText("ACH")).click();
									 test.log(LogStatus.PASS, "Clicked on ACH");


									//Thread.sleep(5000);
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
											driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
											test.log(LogStatus.PASS, "Clicked on Transactions");


								             driver.switchTo().defaultContent();
										      driver.switchTo().frame("mainFrame");*/
					//Thread.sleep(5000);
					/*   driver.findElement(By.linkText("Borrower")).click();
										   test.log(LogStatus.PASS, "Clicked on Borrower");
										   Thread.sleep(5000);
											driver.findElement(By.linkText("Process Date Change")).click();
											test.log(LogStatus.PASS, "Clicked on Process Date Change");
											driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
										        Thread.sleep(2000);
										        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
										        	test.log(LogStatus.FAIL, "Process Date updated successfully.");
										        }
					 */
					// EOD Batch Process

					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Transactions");
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


				}
			}
		}


		public void AgeStore_1stInst(String SSN,String FileName,int days) throws Exception

		{
//-------updation done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

				//	Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

						//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

				}

				//String winHandleBefore = driver.getWindowHandle();

				for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

				}

				Thread.sleep(8000);

				// driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]  
					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();
                    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					driver.get(AdminURL);
					driver.manage().window().maximize();

					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

					test.log(LogStatus.PASS, "Username is entered: admin");

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Password is entered: "+Password);

					//Click Login Button

					driver.findElement(By.name("login")).click();

					test.log(LogStatus.PASS, "Clicked on Submit button");

				//	Thread.sleep(8000);

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

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
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					Thread.sleep(5000);*/

					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");
*/
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();*/

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
Thread.sleep(2000);
					driver.findElement(By.name("beginDay")).clear();

					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

					driver.findElement(By.name("beginYear")).clear();

					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

				//	Thread.sleep(2000);

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				//	Thread.sleep(1000);

				//	Thread.sleep(5000);

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



		public void AgeStore_2ndInst(String SSN,String FileName,int days) throws Exception

		{
//---------------------updation done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);

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

				//	Thread.sleep(5000);

					Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

						//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

			}

			//String winHandleBefore = driver.getWindowHandle();

			for(String winHandle : driver.getWindowHandles()){

			driver.switchTo().window(winHandle);

			}

			Thread.sleep(8000);

			// driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]  
					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();
					test.log(LogStatus.PASS, "2nd instalment duedate :"+DueDate);

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();

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

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

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
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					Thread.sleep(5000);*/
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");

					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");*/

					/*driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/

					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();*/

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

				//	Thread.sleep(2000);

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				//	Thread.sleep(1000);

				//	Thread.sleep(5000);

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
//-------------------------done
			//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
			
			
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

					//Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

						//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

			}

			//String winHandleBefore = driver.getWindowHandle();

			for(String winHandle : driver.getWindowHandles()){

			driver.switchTo().window(winHandle);

			}

			Thread.sleep(8000);

			// driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]  
					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[4]/td[2]")).getText();
					test.log(LogStatus.PASS, "2nd instalment duedate :"+DueDate);

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();

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

				//	Thread.sleep(8000);

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

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
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					Thread.sleep(5000);*/
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");

					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");*/

					/*driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
*/
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("storeCode")).click();

					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

					driver.findElement(By.name("storeCode")).sendKeys(StoreID);

					test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

					Thread.sleep(3000);

					driver.findElement(By.name("beginMonth")).clear();

					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

					driver.findElement(By.name("beginDay")).clear();

					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

					driver.findElement(By.name("beginYear")).clear();

					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

				//	Thread.sleep(2000);

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				//	Thread.sleep(1000);

				//	Thread.sleep(5000);

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


		public void PrincipalReduction_4thInst(String SSN,String FileName) throws Exception

		{
//------------------done
			//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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
/*
					Thread.sleep(5000);

					Thread.sleep(1000);*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

						//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					//System.out.print(DueDate);


				}

			}

		}


		public void DefaultPaymentStatus1_C(String SSN,String FileName) throws Exception
		{
//------------done
			//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
				//	Thread.sleep(5000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
					
				FirstLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[7]")).getText();
					
				test.log(LogStatus.PASS,"First Loan Status::  "+FirstLoanStatus);
				
				SecondLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[6]")).getText();
				
				test.log(LogStatus.PASS,"Second Loan Status::  "+SecondLoanStatus);


										//	driver.close();

				}
			}
		}



			public void AgeStore1_1BatchProcess_C(String SSN,String FileName,int days) throws Exception

				{
//---------done
				//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);
				Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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
Thread.sleep(3000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Loan Transactions");

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
Thread.sleep(4000);
				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

			//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

					 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
			    // 			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();

					              //driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

				/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

				}

				//String winHandleBefore = driver.getWindowHandle();

				for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

				}

				Thread.sleep(8000);

				// driver.findElement(By.xpath("//*[@id='home']")).click();*/

				DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture Default date:"+DueDate);


				driver.close();

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);
				driver.manage().window().maximize();
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				//Thread.sleep(8000);

				//for(String winHandle : driver.getWindowHandles()){

				// driver.switchTo().window(winHandle);

				//}

				// driver.switchTo().defaultContent();

				// driver.switchTo().frame("mainFrame");

				//driver.switchTo().frame("main");

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

				/*driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("Borrower")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				Thread.sleep(5000);*/
				Thread.sleep(4000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
			
				/*driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");*/

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(3000);

				driver.findElement(By.name("beginMonth")).clear();

				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				
				Thread.sleep(2000);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

			//	Thread.sleep(2000);

				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				//Thread.sleep(1000);

				//Thread.sleep(5000);

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

				
				
			//	Thread.sleep(5000);
			       driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Transactions");
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
					//	Thread.sleep(5000);
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
				
		

		public void AgeStore1_2(String SSN,String FileName,int days) throws Exception

		{
//-----done
			//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					/*Thread.sleep(5000);

					Thread.sleep(1000);
*/	driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(2000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

						//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

				}

				//String winHandleBefore = driver.getWindowHandle();

				for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

				}

				Thread.sleep(8000);

				// driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();

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

					//Thread.sleep(8000);

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

					Date DDueDate = df.parse(DueDate);

					Calendar cal = Calendar.getInstance();

					cal.setTime(DDueDate);

					cal.add(Calendar.DATE, days);

					Date DDueDateminus1= cal.getTime();

					// String DueDateminus1 =df.format(DDueDateminus1);

					String DueDate0[] =DueDate.split("/");

					String DueDate1 = DueDate0[0];

					String DueDate2 = DueDate0[1];

					String DueDate3 = DueDate0[2];
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					Thread.sleep(5000);
*/
					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");*/
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();*/

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("storeCode")).click();

					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

					driver.findElement(By.name("storeCode")).sendKeys(StoreID);

					test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

					Thread.sleep(3000);

					driver.findElement(By.name("beginMonth")).clear();

					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

					driver.findElement(By.name("beginDay")).clear();

					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

					driver.findElement(By.name("beginYear")).clear();

					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

					//Thread.sleep(2000);

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					//Thread.sleep(1000);

				//	Thread.sleep(5000);

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

		public void AgeStore2_1(String SSN,String FileName,int days) throws Exception

		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	


						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

				}

				//String winHandleBefore = driver.getWindowHandle();

				for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

				}

				Thread.sleep(8000);

				// driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();

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

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

					Date DDueDate = df.parse(DueDate);

					Calendar cal = Calendar.getInstance();

					cal.setTime(DDueDate);

					cal.add(Calendar.DATE, days);

					Date DDueDateminus1= cal.getTime();

					// String DueDateminus1 =df.format(DDueDateminus1);

					String DueDate0[] =DueDate.split("/");

					String DueDate1 = DueDate0[0];

					String DueDate2 = DueDate0[1];

					String DueDate3 = DueDate0[2];
					Thread.sleep(5000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		/*			driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");*/

					//Thread.sleep(5000);
					
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");

					driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		public void AgeStore2_2(String SSN,String FileName,int days) throws Exception

		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					if(ProductID.equals("TLP"))
					{
						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

						//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

				}

				//String winHandleBefore = driver.getWindowHandle();

				for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

				}

				Thread.sleep(8000);

				// driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();

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

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

					Date DDueDate = df.parse(DueDate);

					Calendar cal = Calendar.getInstance();

					cal.setTime(DDueDate);

					cal.add(Calendar.DATE, days);

					Date DDueDateminus1= cal.getTime();

					// String DueDateminus1 =df.format(DDueDateminus1);

					String DueDate0[] =DueDate.split("/");

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

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

/*					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					Thread.sleep(5000);*/
					
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");

					driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		public void AgeStore1_1(String SSN,String FileName,int days) throws Exception

		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

		 if(ProductID.equals("TLP"))
		 {
	    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

			 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
	    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

		/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

		}

		//String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){

		driver.switchTo().window(winHandle);

		}

		Thread.sleep(8000);

		// driver.findElement(By.xpath("//*[@id='home']")).click();*/

		DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

		//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

		System.out.print(DueDate);

		driver.close();

		driver = new InternetExplorerDriver();

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

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

		Date DDueDate = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDate);

		cal.add(Calendar.DATE, days);

		Date DDueDateminus1= cal.getTime();

		// String DueDateminus1 =df.format(DDueDateminus1);

		String DueDate0[] =DueDate.split("/");

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

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

/*		driver.findElement(By.linkText("Borrower")).click();

		test.log(LogStatus.PASS, "Clicked on Borrower");

		Thread.sleep(5000);*/

		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		
		driver.findElement(By.linkText("Process Date Change")).click();

		test.log(LogStatus.PASS, "Clicked on Process Date Change");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
		

		
		public void Payment (String SSN,String FileName) throws Exception{
//----------done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
			

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

					String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

					String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);

					String StoreID = TestData.getCellData(sheetName,"StoreID",row);

					String AppURL = TestData.getCellData(sheetName,"AppURL",row);

					String UserName = TestData.getCellData(sheetName,"UserName",row);

					//String Password = TestData.getCellData(sheetName,"Password",row);

					//String StoreID = TestData.getCellData(sheetName,"StoreID",row);

					String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

					appUrl = AppURL;

					this.Login(UserName,Password,StoreID);

					String SSN1 = SSN.substring(0, 3);

					String SSN2 = SSN.substring(3,5);

					String SSN3 = SSN.substring(5,9);

					//Thread.sleep(4000);

					driver.switchTo().defaultContent();

					WebDriverWait wait = new WebDriverWait(driver, 30);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));

					driver.switchTo().frame("topFrame");

					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));

					driver.findElement(By.cssSelector("li[id='910000']")).click();

					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click(); */

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					//Thread.sleep(1000);

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

				//	Thread.sleep(5000);

					if(ProductID.equals("LOC"))

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

					driver.findElement(By.name("transactionList")).sendKeys("Payment");

					test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

					/* if(ProductID.equals("LOC"))

					{

					driver.findElement(By.name("button")).click();

					}

					else

					{

					driver.findElement(By.id("go_Button")).click();

					}*/

					if(ProductID.equals("TLP"))

					{

						driver.findElement(By.xpath("//*[@id='go_Button']")).click();

					}

					else

					{

						driver.findElement(By.id("go_Button")).click();

					}

					//*[@id="go_Button"]

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

						// name="tenderType"

						// tenderType

						// driver.findElement(By.name("tenderType")).sendKeys(TenderType);

						//*[@id="PD4"]

						//*[@id="PD5"]


						//driver.findElement(By.xpath("//*[@id='PD5']")).click();

						//*[@id="PD4"]
						driver.findElement(By.xpath("//*[@id='PD4']")).click();
						test.log(LogStatus.PASS, " Click payment amt Type ");

						//driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys("300");

						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");

						test.log(LogStatus.PASS, "tenderType as Cash ");
						//name="instAmt"
						String pmtamt = driver.findElement(By.name("instAmt")).getAttribute("value");

						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(pmtamt);

					}

			

					if(ProductID.equals("TLP"))

					{

						// name="requestBean.password"

						// name="finish"

						driver.findElement(By.name("requestBean.password")).sendKeys(Password);

						driver.findElement(By.name("finish")).click();

						test.log(LogStatus.PASS, "Password is selected as "+Password);

					}

					//Thread.sleep(2000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
					//*[@id="renew-confirm"]/table/tbody/tr[4]/td/input[1]
					driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[1]")).click();

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

		public void After_Payment_InstNO (String SSN,String FileName) throws Exception{	
			//----done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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
					this.Login(UserName, Password, StoreID);

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

					//Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click(); 

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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



						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

					String InstNO=null;


					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

					InstNO = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[18]/td[1]")).getText();


					test.log(LogStatus.PASS, "After_Payment_InstNO"+InstNO);

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText(); 

					System.out.print(InstNO); 

					driver.close();
					driver = new InternetExplorerDriver();

				}
			}

		}


		public void AgeStore(String SSN,String FileName,int Days) throws Exception

		{
//--------------done updating

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					//Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click(); 

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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



						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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


					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();


					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText(); 

					System.out.print(DueDate); 

					driver.close();


					driver = new InternetExplorerDriver();

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

					//Thread.sleep(8000);

					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					//wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();*/

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);




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

				//	Thread.sleep(2000);

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					//Thread.sleep(1000);

					//Thread.sleep(5000);

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

		public void Before_Payment_InstNO (String SSN,String FileName) throws Exception{	
			//-------done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					this.Login(UserName, Password, StoreID);

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

					//Thread.sleep(5000);

					//Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click(); 

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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



						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

					String InstNO=null;


					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

					InstNO = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[24]/td[1]")).getText();


					test.log(LogStatus.PASS, "Before_Payment_InstNO"+InstNO);




					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText(); 

					//System.out.print(InstNo); 



				}
			}

		}


		public void Loandate_AgeStore(String SSN,String FileName,int Days) throws Exception

		{
//-------------done updating

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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
				//	driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click(); 

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					Thread.sleep(2000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

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
					test.log(LogStatus.PASS, "Capture Loan Date"+DueDate);
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
					driver.findElement(By.name("login")).click();
					test.log(LogStatus.PASS, "Clicked on Submit button");
					Thread.sleep(5000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					//driver.findElement(By.linkText("Process Date Change")).click();
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
										
	
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
					Thread.sleep(2000);
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
		public void PartialPayment (String SSN,String FileName) throws Exception{		

			//-------------done updating
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
			int lastrow=TestData.getLastRow("NewLoan");
			String sheetName="NewLoan";
			for(int row=2;row<=lastrow;row++)
			{
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					String TxnType=TestData.getCellData(sheetName,"TxnType",row);
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
		public void AgeStore_1stInst_DueDate10DaysBefore(String SSN,String FileName,int Days) throws Exception

		{

//--------------done updating--------------------
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(3000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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
					
					//Thread.sleep(2000);

					Thread.sleep(5000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

	
					
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

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
		public void Renewal_Status(String SSN,String FileName) throws Exception{
			//-------------done updating
			//String FileName= "AA_TwoLoans_OneVIN_OneDefault_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

					//button
					String CheckStatus=null;
					CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[18]/td/span[2]")).getText();
					if(CheckStatus.equals("Y")){
						test.log(LogStatus.PASS,"Is Loan Renewal status : "+CheckStatus);
						test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
						

					}
					else
					{
						test.log(LogStatus.PASS,"Is Loan in Renewal : "+CheckStatus);
						test.log(LogStatus.INFO,"Renewal Transaction is faild");
						
					}


				}       

			}


		}

		public void AgeStore_1stInst_DueDate(String SSN,String FileName,int Days) throws Exception

		{
			//-------------done updating

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(2000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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


					//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

					// //*[@id="myTable1"]/tbody[2]/tr[2]/td[2]

					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);

					driver.close();


					driver = new InternetExplorerDriver();
				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.get(AdminURL);

					
					DateFormat df=new SimpleDateFormat("MM/dd/yyyy"); 

					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

					test.log(LogStatus.PASS, "Username is entered: admin"); 

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Password is entered: "+Password); 

					//Click Login Button

					driver.findElement(By.name("login")).click();

					test.log(LogStatus.PASS, "Clicked on Submit button");

					Thread.sleep(5000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
					

					
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

Thread.sleep(3000);
					driver.findElement(By.name("storeCode")).click();

					driver.findElement(By.name("storeCode")).sendKeys(StoreID);

					test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
					Thread.sleep(3000);
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
		public void AgeStore2ndInst_Duedate(String SSN,String FileName,int Days) throws Exception

		{
//--------done updating

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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


					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();


					test.log(LogStatus.PASS, "Capture 2nd Installment DueDate"+DueDate);

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
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					
					
					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click(); 

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");*/

					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					
					driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");

					
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

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
		public void Inst_Payoff (String SSN,String FileName) throws Exception{		
//----------------------done updating

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					String UserName = TestData.getCellData(sheetName,"UserName",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
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
					
					

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if(ProductID.equals("TLP"))
					{
						
						driver.findElement(By.xpath("//*[@id='PD3']")).click();
						test.log(LogStatus.PASS, " Click Pay Off the balance ");
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");
						test.log(LogStatus.PASS, "tenderType  as Cash ");
						String Pmt= driver.findElement(By.name("requestBean.siilBean.payAmt")).getAttribute("value");

						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Pmt);
						test.log(LogStatus.PASS, " Click pay any other amt Type ");
						
					}
					
					if(ProductID.equals("TLP"))
					{
						
						driver.findElement(By.name("requestBean.password")).sendKeys(Password);
						driver.findElement(By.name("finish")).click();
						test.log(LogStatus.PASS, "Password is selected as "+Password);
					} 

					{

						if(driver.findElement(By.name("Ok")).isDisplayed())

						{
							test.log(LogStatus.PASS, "Payoff Completed Successfully ");
							driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "Payoff is not Completed Successfully ");
						}

					}
				}
			}
		}

		public void Renewal_StatusEnd(String SSN,String FileName) throws Exception{

			//String FileName= "AA_TwoLoans_OneVIN_OneDefault_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					test.log(LogStatus.INFO, "Renewal Status(Starts)");

					System.out.println(ProductID);	
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					appUrl = AppURL;
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreID, driver, AppURL, test);
					//this.Login(UserName,Password,StoreID);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					/*Thread.sleep(5000);

					Thread.sleep(5000);
					Thread.sleep(1000);*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					test.log(LogStatus.INFO,"Transaqction Selection Go Button Click");

					//button
					String CheckStatus=null;
					CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[18]/td/span[2]")).getText();
					if(CheckStatus.equals("Y")){
						test.log(LogStatus.PASS,"Is Loan Renewal status : "+CheckStatus);
						test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
						System.out.print(CheckStatus);	

					}
					else
					{
						test.log(LogStatus.PASS,"Is Loan in Renewal : "+CheckStatus);
						test.log(LogStatus.INFO,"Renewal Transaction is faild");
						System.out.print(CheckStatus);
					}

					driver.close();
					driver = new InternetExplorerDriver();
				}	

			}


		}
		public void AgeStore_2BatchProcess(String SSN,String FileName,int days) throws Exception

		{
//---------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

				//	Thread.sleep(5000);

				//	Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

						//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

				}

				//String winHandleBefore = driver.getWindowHandle();

				for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

				}

				Thread.sleep(8000);

				// driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="myTable1"]/tbody[2]/tr[3]/td[2]
					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();

					driver = new InternetExplorerDriver();
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
driver.manage().window().maximize();
					driver.get(AdminURL);
					driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);

					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

					test.log(LogStatus.PASS, "Username is entered: admin");

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Password is entered: "+Password);

					//Click Login Button

					driver.findElement(By.name("login")).click();

					test.log(LogStatus.PASS, "Clicked on Submit button");

					//Thread.sleep(8000);

					//for(String winHandle : driver.getWindowHandles()){

					// driver.switchTo().window(winHandle);

					//}

					// driver.switchTo().defaultContent();

					// driver.switchTo().frame("mainFrame");

					//driver.switchTo().frame("main");

					Date DDueDate = df.parse(DueDate);

					Calendar cal = Calendar.getInstance();

					cal.setTime(DDueDate);

					cal.add(Calendar.DATE, days);

					Date DDueDateminus1= cal.getTime();

					// String DueDateminus1 =df.format(DDueDateminus1);

					String DueDate0[] =DueDate.split("/");

					String DueDate1 = DueDate0[0];

					String DueDate2 = DueDate0[1];

					String DueDate3 = DueDate0[2];
					Thread.sleep(4000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					/*driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");*/

					//Thread.sleep(5000);

					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");*/

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					/*WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();
*/
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

					//Thread.sleep(2000);

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					//Thread.sleep(1000);

					//Thread.sleep(5000);

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



				//	Thread.sleep(5000);
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
		public void Loan_StatusEnd(String SSN,String FileName) throws Exception{
//-------------done
			//String FileName= "AA_TwoLoans_OneVIN_OneDefault_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); ;	
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
					test.log(LogStatus.INFO, "Renewal Status(Starts)");

					System.out.println(ProductID);	
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					appUrl = AppURL;
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreID, driver, AppURL, test);
					//this.Login(UserName,Password,StoreID);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
				//	Thread.sleep(5000);

				//	Thread.sleep(5000);
				//	Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					test.log(LogStatus.INFO,"Transaqction Selection Go Button Click");

					//button
					String CheckStatus=null;
					//                                          //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]
					CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
					if(CheckStatus.equals("Current")){
						test.log(LogStatus.PASS,"Is Loan Renewal status : "+CheckStatus);
						test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
						System.out.print(CheckStatus);	

					}
					else
					{
						test.log(LogStatus.FAIL,"Is Loan in Renewal : "+CheckStatus);
						test.log(LogStatus.INFO,"Renewal Transaction is faild");
						System.out.print(CheckStatus);
					}

					driver.close();
					driver = new InternetExplorerDriver();
				}	

			}


		}
		public void Loan_StatusEndDEF(String SSN,String FileName) throws Exception{
			//-------------done
						//String FileName= "AA_TwoLoans_OneVIN_OneDefault_Txn_Testdata.xls";
						Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); ;	
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
								test.log(LogStatus.INFO, "Renewal Status(Starts)");

								System.out.println(ProductID);	
								String AppURL = TestData.getCellData(sheetName,"AppURL",row);
								appUrl = AppURL;
								CSRLoginpage login = new CSRLoginpage();
								login.Login(UserName, Password, StoreID, driver, AppURL, test);
								//this.Login(UserName,Password,StoreID);
								String SSN1 = SSN.substring(0, 3);
								String SSN2 = SSN.substring(3,5);
								String SSN3 = SSN.substring(5,9);
							//	Thread.sleep(5000);

							//	Thread.sleep(5000);
							//	Thread.sleep(1000);
								driver.switchTo().defaultContent();
								driver.switchTo().frame("topFrame");
								driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
								test.log(LogStatus.PASS, "Clicked on Loan Transactions");
								//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
								test.log(LogStatus.INFO,"Transaqction Selection Go Button Click");

								//button
								String CheckStatus=null;
								//                                          //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]
								CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
								if(CheckStatus.equals("Default")){
									test.log(LogStatus.PASS,"Is Loan Renewal status : "+CheckStatus);
									test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
									System.out.print(CheckStatus);	

								}
								else
								{
									test.log(LogStatus.FAIL,"Is Loan in Renewal : "+CheckStatus);
									test.log(LogStatus.INFO,"Renewal Transaction is faild");
									System.out.print(CheckStatus);
								}

								driver.close();
								driver = new InternetExplorerDriver();
							}	

						}


					}
		public void Loan_Status(String SSN,String FileName) throws Exception{
//---------done
			//String FileName= "AA_TwoLoans_OneVIN_OneDefault_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					test.log(LogStatus.INFO, "Renewal Status(Starts)");

					System.out.println(ProductID);	
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					appUrl = AppURL;
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreID, driver, AppURL, test);
					//this.Login(UserName,Password,StoreID);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					//Thread.sleep(5000);

					//Thread.sleep(5000);
					//Thread.sleep(1000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					test.log(LogStatus.INFO,"Navigated To Loan Transaction Screen");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
					test.log(LogStatus.INFO,"Transaqction Selection Go Button Click");

					//button
					String CheckStatus=null;
					//      

					CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();

					test.log(LogStatus.PASS,"Loan status : "+CheckStatus);
					test.log(LogStatus.INFO,"Default Transaction Done Successfully");

					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]
					/*	CheckStatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
							if(CheckStatus.equals("Y")){
							test.log(LogStatus.PASS,"Is Loan Renewal status : "+CheckStatus);
							test.log(LogStatus.INFO,"Renewal Transaction Done Successfully");
							System.out.print(CheckStatus);	

							}
							else
							{
								test.log(LogStatus.PASS,"Is Loan in Renewal : "+CheckStatus);
								test.log(LogStatus.INFO,"Renewal Transaction is Done");
								System.out.print(CheckStatus);
							}
					 */
					//driver.close();
				}	

			}


		}




		public void AgeStore_CureDate(String SSN,String FileName,int Days) throws Exception

		{

///----------------------updated

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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



						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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



					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]     1st ins date 2nd loan
					//*[@id="myTable1"]/tbody[2]/tr[3]/td[2]
					//*[@id="myTable1"]/tbody[2]/tr[3]/td[2]
					DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();



					test.log(LogStatus.PASS, "Capture DueDate"+DueDate);

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();



					driver = new InternetExplorerDriver();

					driver.get(AdminURL);
					
driver.manage().window().maximize();
					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);


					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

					test.log(LogStatus.PASS, "Username is entered: admin");

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Password is entered: "+Password);

					//Click Login Button

					driver.findElement(By.name("login")).click();

					test.log(LogStatus.PASS, "Clicked on Submit button");
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
				/*//	Thread.sleep(8000);




					driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					//Thread.sleep(5000);
*/
					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");*/

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



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

					//Thread.sleep(2000);

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					/*Thread.sleep(1000);

					Thread.sleep(5000);*/

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




		/*public void CurePayment (String SSN,String FileName) throws Exception{
//--------------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

			int lastrow=TestData.getLastRow("NewLoan");

			System.out.println("NewLoan "+lastrow);

			String sheetName="NewLoan";

			for(int row=2;row<=lastrow;row++)

			{

				String RegSSN = TestData.getCellData(sheetName,"SSN",row);

				if(SSN.equals(RegSSN))

				{

					String UserName = TestData.getCellData(sheetName,"UserName",row);

					String Password = TestData.getCellData(sheetName,"Password",row);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					// System.out.println(Password);

					String StoreId = TestData.getCellData(sheetName,"StoreID",row);

					String TxnType=TestData.getCellData(sheetName,"TxnType",row);

					String TenderType = TestData.getCellData(sheetName,"TenderType",row);

					String ProductID=TestData.getCellData(sheetName,"ProductID",row);

					//	String Password = TestData.getCellData(sheetName,"Password",row);

					String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

					String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);

					String SSN1 = SSN.substring(0, 3);

					String SSN2 = SSN.substring(3,5);

					String SSN3 = SSN.substring(5,9);

					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
				//	Thread.sleep(4000);

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

				//	Thread.sleep(1000);

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

				//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

				//	Thread.sleep(5000);

					if(ProductID.equals("LOC"))

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


					driver.findElement(By.name("transactionList")).sendKeys("Payment");
					//driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

					 if(ProductID.equals("LOC"))

					{

					driver.findElement(By.name("button")).click();

					}

					else

					{

					driver.findElement(By.id("go_Button")).click();

					}

					if(ProductID.equals("TLP"))

					{

						driver.findElement(By.xpath("//*[@id='go_Button']")).click();

					}

					else

					{

						driver.findElement(By.id("go_Button")).click();

					}

					//*[@id="go_Button"]

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("PD5")).sendKeys(Password);//pradeep

					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);

					test.log(LogStatus.PASS, "Clicked on Finish Loan button ");


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

						String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();

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

						// name="tenderType"

						// tenderType

						// driver.findElement(By.name("tenderType")).sendKeys(TenderType);

						//*[@id="PD4"]

						//*[@id="PD5"]


						//driver.findElement(By.xpath("//*[@id='PD5']")).click();

						//*[@id="PD4"]

						// //*[@id="minpaymentAmt"]     Curepayment Checkbox

						//   name="transactionDataBean.tenderTypeFirst"

						// name="transactionDataBean.tenderAmtFirst"

						//  //*[@id="minPaymentAmountToCurrent"]/b     textelement


						//  name="password"    


						//name="Submit22"   
						
						driver.findElement(By.xpath("//*[@id='minpaymentAmt']")).click();
						test.log(LogStatus.PASS, " Click on Cure payment amt Type ");

						//driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys("300");

						driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");

						test.log(LogStatus.PASS, "tenderType as Cash ");
						//name="instAmt"
						String pmtamt = driver.findElement(By.name("transactionDataBean.renPastDue")).getAttribute("value");

						driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(pmtamt);

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

						// name="requestBean.password"

						// name="finish"

						driver.findElement(By.name("password")).sendKeys(Password);
						test.log(LogStatus.PASS, "Password is selected as "+Password);
						Thread.sleep(2000);
						//driver.findElement(By.name("Submit22")).click();
						// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr[9]/td/table/tbody/tr[5]/td[2]/input
						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr[9]/td/table/tbody/tr[5]/td[2]/input")).click();
						test.log(LogStatus.PASS, "Clicked on Finish button");


					}
				//	Thread.sleep(2000);

					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					//Thread.sleep(2000);
					for( String winHandle1 : driver.getWindowHandles())

					{

					driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
					//Thread.sleep(3000);
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
					//*[@id="renew-confirm"]/table/tbody/tr[4]/td/input[1]
					driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[1]")).click();

					{
						//name="Ok"
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

		}		*/
		
		public void CurePayment (String SSN,String FileName) throws Exception{
			//--------------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);

						int lastrow=TestData.getLastRow("NewLoan");

						System.out.println("NewLoan "+lastrow);

						String sheetName="NewLoan";

						for(int row=2;row<=lastrow;row++)

						{

							String RegSSN = TestData.getCellData(sheetName,"SSN",row);

							if(SSN.equals(RegSSN))

							{

								String UserName = TestData.getCellData(sheetName,"UserName",row);

								String Password = TestData.getCellData(sheetName,"Password",row);
								String AppURL = TestData.getCellData(sheetName,"AppURL",row);
								// System.out.println(Password);

								String StoreId = TestData.getCellData(sheetName,"StoreID",row);

								String TxnType=TestData.getCellData(sheetName,"TxnType",row);

								String TenderType = TestData.getCellData(sheetName,"TenderType",row);

								String ProductID=TestData.getCellData(sheetName,"ProductID",row);

								//	String Password = TestData.getCellData(sheetName,"Password",row);

								String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);

								String ESign_DisbType2 = TestData.getCellData(sheetName,"ESign_DisbType1",row);

								String SSN1 = SSN.substring(0, 3);

								String SSN2 = SSN.substring(3,5);

								String SSN3 = SSN.substring(5,9);

								CSRLoginpage login = new CSRLoginpage();
								login.Login(UserName, Password, StoreId, driver, AppURL, test);
							//	Thread.sleep(4000);

								driver.switchTo().defaultContent();

								WebDriverWait wait = new WebDriverWait(driver, 30);

								wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));

								driver.switchTo().frame("topFrame");

								wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));

								driver.findElement(By.cssSelector("li[id='910000']")).click();

								/*driver.switchTo().defaultContent();

								driver.switchTo().frame("topFrame");

								driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click(); */

								test.log(LogStatus.PASS, "Clicked on Loan Transactions");

							//	Thread.sleep(1000);

								//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

							//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

							//	Thread.sleep(5000);

								if(ProductID.equals("LOC"))

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


								driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
								//driver.findElement(By.name("transactionList")).sendKeys(TxnType);
								test.log(LogStatus.PASS, "Transaction Type is selected as Payment");

								/* if(ProductID.equals("LOC"))

								{

								driver.findElement(By.name("button")).click();

								}

								else

								{

								driver.findElement(By.id("go_Button")).click();

								}*/

								if(ProductID.equals("TLP"))

								{

									driver.findElement(By.xpath("//*[@id='go_Button']")).click();

								}

								else

								{

									driver.findElement(By.id("go_Button")).click();

								}

								//*[@id="go_Button"]

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

									String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();

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

									// name="tenderType"

									// tenderType

									// driver.findElement(By.name("tenderType")).sendKeys(TenderType);

									//*[@id="PD4"]

									//*[@id="PD5"]


									//driver.findElement(By.xpath("//*[@id='PD5']")).click();

									//*[@id="PD4"]

									// //*[@id="minpaymentAmt"]     Curepayment Checkbox

									//   name="transactionDataBean.tenderTypeFirst"

									// name="transactionDataBean.tenderAmtFirst"

									//  //*[@id="minPaymentAmountToCurrent"]/b     textelement


									//  name="password"    


									//name="Submit22"   
									driver.findElement(By.xpath("//*[@id='minpaymentAmt']")).click();
									test.log(LogStatus.PASS, " Click on Cure payment amt Type ");

									//driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys("300");

									driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");

									test.log(LogStatus.PASS, "tenderType as Cash ");
									//name="instAmt"
									String pmtamt = driver.findElement(By.name("transactionDataBean.renPastDue")).getAttribute("value");

									driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(pmtamt);

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

									// name="requestBean.password"

									// name="finish"

									driver.findElement(By.name("password")).sendKeys(Password);
									test.log(LogStatus.PASS, "Password is selected as "+Password);
									Thread.sleep(2000);
									//driver.findElement(By.name("Submit22")).click();
									// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr[9]/td/table/tbody/tr[5]/td[2]/input
									driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr[9]/td/table/tbody/tr[5]/td[2]/input")).click();
									test.log(LogStatus.PASS, "Clicked on Finish button");


								}
							//	Thread.sleep(2000);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.accept();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
								//Thread.sleep(2000);
								/*for( String winHandle1 : driver.getWindowHandles())

								{

								driver.switchTo().window(winHandle1);

								}

								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

								driver.switchTo().frame("main");*/
								//Thread.sleep(3000);
								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

								driver.switchTo().frame("main");
								//*[@id="renew-confirm"]/table/tbody/tr[4]/td/input[1]
								driver.findElement(By.xpath("//*[@id='renew-confirm']/table/tbody/tr[4]/td/input[1]")).click();

								{
									//name="Ok"
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



		public void NewLoanWithVIN(String SSN,String FileName,String NewVIN) throws Exception{

//-----------------------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);      	
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
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					System.out.println(ESign_CollateralType);
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
					System.out.println(last4cheknum);
					System.out.println(stateProductType);
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
					if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
						if(ProductID.equals("TLP"))							
						{					
							System.out.println("IN TLP");
							driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
							driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();
							Thread.sleep(2000);
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
					
					
					
					//------------added by srikanth
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
				            	Thread.sleep(1000);
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

								}
								catch (NoAlertPresentException e) {
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

						}
						catch (NoAlertPresentException e) {
						}
					//	Thread.sleep(2000);
						try { 
							Alert alert = driver.switchTo().alert();
							String Var = alert.getText();
							test.log(LogStatus.PASS, "ALert Displayed is :: "+Var);
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
						test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("bdyLoad");
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
						Thread.sleep(2000);
						test.log(LogStatus.PASS, "click on Update 2 button ");
						//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						Thread.sleep(3000);
						driver.findElement(By.name("process")).click();
						test.log(LogStatus.PASS, "click on process Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
						}
						catch (NoAlertPresentException e) {
						}
						Thread.sleep(3000);
						
						driver.findElement(By.name("negLoanAmt")).click();
						Thread.sleep(5000);
						for( String winHandle1 : driver.getWindowHandles())

						{

						if(!(winHandle1.equals(Parent_Window)))

						{

						driver.switchTo().window(winHandle1);
						Thread.sleep(2000);
					
				
					
Thread.sleep(2000);
						//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));
					//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));
						driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
						Thread.sleep(3000);

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();												

						}
						catch (NoAlertPresentException e) {
						}
						Thread.sleep(3000);

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

						//Thread.sleep(6000);
						driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys("1250");
						driver.findElement(By.name("negSel")).click();
						driver.findElement(By.name("reCalculate")).click();
						driver.findElement(By.name("negSel")).click();
						//Thread.sleep(3000);
						driver.switchTo().window(Parent_Window);

						for( String winHandle5 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle5);

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


						//	Thread.sleep(5000);
							
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
							for( String winHandle2 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle2);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							driver.findElement(By.xpath("//*[@id='OKBut']")).click();
							test.log(LogStatus.PASS, "click on Yes button ");
							for( String winHandle3 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle3);
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
		}


		public void OutToRepo(String SSN,String FileName,int days) throws Exception

		{
//---------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					String DefaultDate=null;
					//String SecondLoanStatus=null;
					
					DefaultDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[3]")).getText();
					
				test.log(LogStatus.PASS,"DefaultDate::  "+DefaultDate);
				
				//SecondLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[6]")).getText();
				
				//test.log(LogStatus.PASS,"Second Loan Status::  "+SecondLoanStatus);


			driver.close();


		driver = new InternetExplorerDriver();

		driver.get(AdminURL);
		driver.manage().window().maximize();
driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

		test.log(LogStatus.PASS, "Username is entered: admin");

		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password is entered: "+Password);

		//Click Login Button

		driver.findElement(By.name("login")).click();

		test.log(LogStatus.PASS, "Clicked on Submit button");

		//Thread.sleep(8000);

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

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
		Thread.sleep(3000);
		driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
Thread.sleep(4000);
		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Title Loan")).click();

		test.log(LogStatus.PASS, "Clicked on Title Loan");

		Thread.sleep(3000);

		driver.findElement(By.linkText("Post Default Transactions")).click();

		test.log(LogStatus.PASS, "Clicked on Post Default Transactions");

	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		//driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

		//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

		driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

		Thread.sleep(3000);

		driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
		test.log(LogStatus.PASS, "SSN number is Entered: ");

		driver.findElement(By.name("requestBean.statusType")).sendKeys("Out To Repo");

		test.log(LogStatus.PASS, "Out To Repo is entered: ");

		driver.findElement(By.name("submit")).click();

		test.log(LogStatus.PASS, "Clicked on submitt Button: ");
		
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();
		test.log(LogStatus.PASS, "Clicked on Go Button: ");	
		Thread.sleep(1000);
		driver.findElement(By.name("solvageCompany")).sendKeys("Consolidated Asset Recovery Services (CARS)");

		test.log(LogStatus.PASS, "Solvage Company is selected As Consolidated Asset Recovery Services (CARS) ");

		
		Thread.sleep(1000);

		driver.findElement(By.name("password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password Entered");
		
		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Click on submitt Button");

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/input")).isDisplayed())

		{
			                              ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td/input
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

		
		public void Repossession(String SSN,String FileName,int days) throws Exception

		{
//----------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(3000);
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
				/*Start	
				 	driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DefaultDate=null;
					//String SecondLoanStatus=null;
					
					DefaultDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[3]")).getText();
					
				test.log(LogStatus.PASS,"DefaultDate::  "+DefaultDate);
				
				//SecondLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[6]")).getText();
				
				//test.log(LogStatus.PASS,"Second Loan Status::  "+SecondLoanStatus);


			driver.close(); END*/
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					 if(ProductID.equals("TLP"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

						 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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
						 driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
					 }

					for( String winHandle1 : driver.getWindowHandles())

					{

					driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					String DefaultDate=null;

					/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

					}

					//String winHandleBefore = driver.getWindowHandle();

					for(String winHandle : driver.getWindowHandles()){

					driver.switchTo().window(winHandle);

					}

					Thread.sleep(8000);

					// driver.findElement(By.xpath("//*[@id='home']")).click();*/

					DefaultDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
					test.log(LogStatus.PASS, "Capture Default date:"+DefaultDate);



		driver = new InternetExplorerDriver();

		driver.get(AdminURL);
		driver.manage().window().maximize();
		// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

		test.log(LogStatus.PASS, "Username is entered: admin");

		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password is entered: "+Password);

		//Click Login Button

		driver.findElement(By.name("login")).click();

		test.log(LogStatus.PASS, "Clicked on Submit button");

		//Thread.sleep(8000);

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

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
		Thread.sleep(3000);
		driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Title Loan")).click();

		test.log(LogStatus.PASS, "Clicked on Title Loan");

		Thread.sleep(3000);

		driver.findElement(By.linkText("Post Default Transactions")).click();

		test.log(LogStatus.PASS, "Clicked on Post Default Transactions");

	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		//driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

		//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

		driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

	//	Thread.sleep(5000);

		driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
		test.log(LogStatus.PASS, "SSN number is Entered: ");

		driver.findElement(By.name("requestBean.statusType")).sendKeys("Repossession");

		test.log(LogStatus.PASS, "Out To Repo is entered: ");

		driver.findElement(By.name("submit")).click();

		test.log(LogStatus.PASS, "Clicked on submitt Button: ");
		Thread.sleep(2000);
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

		
		Thread.sleep(2000);

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

		
		
	/*	public void Auction1(String SSN,String FileName,int days) throws Exception

		{
//-----------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					String DefaultDate=null;
					//String SecondLoanStatus=null;
					
					DefaultDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[3]")).getText();
					
				test.log(LogStatus.PASS,"DefaultDate::  "+DefaultDate);
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					 if(ProductID.equals("TLP"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

						 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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
						 driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
					 }

					for( String winHandle1 : driver.getWindowHandles())

					{

					driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					String DefaultDate=null;

					 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

					}

					//String winHandleBefore = driver.getWindowHandle();

					for(String winHandle : driver.getWindowHandles()){

					driver.switchTo().window(winHandle);

					}

					Thread.sleep(8000);

					// driver.findElement(By.xpath("//*[@id='home']")).click();

					DefaultDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
					test.log(LogStatus.PASS, "Capture Default date:"+DefaultDate);

					 CSRLoginpage login = new CSRLoginpage();
				        login.Login(UserName, Password, StoreId, driver, AppURL, test);
				        Thread.sleep(5000);
						driver.switchTo().defaultContent();
						driver.switchTo().frame("topFrame");
						driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
						test.log(LogStatus.PASS, "Clicked on Loan Transactions");
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
						
						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");

						 if(ProductID.equals("TLP"))
						 {
					    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

							 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
					    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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
						 driver.findElement(By.name("transactionList")).sendKeys("Auction");
						 if(ProductID.equals("TLP"))
						 {
							 driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
						 }
				//SecondLoanStatus = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[6]")).getText();
				
				//test.log(LogStatus.PASS,"Second Loan Status::  "+SecondLoanStatus);


			driver.close();


		driver = new InternetExplorerDriver();

		driver.get(AdminURL);
		driver.manage().window().maximize();
		// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

		test.log(LogStatus.PASS, "Username is entered: admin");

		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password is entered: "+Password);

		//Click Login Button

		driver.findElement(By.name("login")).click();

		test.log(LogStatus.PASS, "Clicked on Submit button");

	//	Thread.sleep(8000);

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");
					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
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
		Thread.sleep(3000);
		driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

	//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Title Loan")).click();

		test.log(LogStatus.PASS, "Clicked on Title Loan");

	Thread.sleep(3000);

		driver.findElement(By.linkText("Post Default Transactions")).click();

		test.log(LogStatus.PASS, "Clicked on Post Default Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		//driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

		//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

		driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);

		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

		//Thread.sleep(5000);

		driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
		test.log(LogStatus.PASS, "SSN number is Entered: ");

		driver.findElement(By.name("requestBean.statusType")).sendKeys("Auction");

		test.log(LogStatus.PASS, "Auction is entered: ");

		driver.findElement(By.name("submit")).click();

		test.log(LogStatus.PASS, "Clicked on submitt Button: ");
		
		

		driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();
		test.log(LogStatus.PASS, "Clicked on Go Button: ");	

		driver.findElement(By.name("solvageCompany")).sendKeys("CARS");

		test.log(LogStatus.PASS, "Solvage Company is selected CARS ");
		
		driver.findElement(By.name("rpossesdate1")).sendKeys(DueDate1);
		test.log(LogStatus.PASS, "Month is Entered");
		driver.findElement(By.name("rpossesdate2")).sendKeys(DueDate2);
		test.log(LogStatus.PASS, "Day is Entered");
		driver.findElement(By.name("rpossesdate3")).sendKeys(DueDate3);
		test.log(LogStatus.PASS, "Year is Entered");

		
	//	Thread.sleep(5000);

		driver.findElement(By.name("requestBean.password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password Entered");
		
		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Click on submitt Button");

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())

		{
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
		test.log(LogStatus.PASS, "OK Button is Clicked");
		test.log(LogStatus.PASS, "Auction updated successfully.");
		}

		else

		{

		test.log(LogStatus.FAIL, "Auction Not updated successfully.");

		}

		
		
		}

		}

		}*/
		
public void Auction(String SSN,String FileName,int days) throws Exception

		{

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					Thread.sleep(7000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.cssSelector("li[id='911101']")).click();
					Thread.sleep(2000);
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
					
					String CsrLoan=	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[2]")).getText();
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
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("TLP"))
					 {
						 driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
					 }
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					 String DefaultDate=null;
					DefaultDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();
					test.log(LogStatus.PASS, "Capture Default date:"+DefaultDate);
			driver.close();
		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		String Parent_Window = driver.getWindowHandle();
		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
		test.log(LogStatus.PASS, "Username is entered: admin");
		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Password is entered: "+Password);
		driver.findElement(By.name("login")).click();
		test.log(LogStatus.PASS, "Clicked on Submit button");
		Thread.sleep(2000);
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
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
		test.log(LogStatus.PASS, "Clicked on Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Title Loan")).click();
		test.log(LogStatus.PASS, "Clicked on Title Loan");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Post Default Transactions")).click();
		test.log(LogStatus.PASS, "Clicked on Post Default Transactions");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);
		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
		Thread.sleep(1000);
		driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
		test.log(LogStatus.PASS, "SSN number is Entered: ");
		driver.findElement(By.name("requestBean.statusType")).sendKeys("Auction");
		test.log(LogStatus.PASS, "Auction is entered: ");
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Clicked on submitt Button: ");
		//--------------updated new code
		driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();
		test.log(LogStatus.PASS, "Clicked on Go Button: ");
		driver.findElement(By.name("history")).click();
		Thread.sleep(4000);
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}
		Thread.sleep(4000);
		driver.manage().window().maximize();
		String Loan1 = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[4]/td/span[2]")).getText();
		test.log(LogStatus.PASS, "Loan number --"+Loan1);
			Set<String> windows= driver.getWindowHandles();
			for(String winHandle1:windows)			
		{
			if(!Parent_Window.equalsIgnoreCase(winHandle1))
					driver.switchTo().window(winHandle1).close();
		}
		
	driver.switchTo().window(Parent_Window);
			if(CsrLoan.equals(Loan1))
			{			
			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");
		driver.findElement(By.name("solvageCompany")).sendKeys("CARS");
		test.log(LogStatus.PASS, "Solvage Company is selected CARS ");
		driver.findElement(By.name("rpossesdate1")).sendKeys(DueDate1);
		test.log(LogStatus.PASS, "Month is Entered");
		driver.findElement(By.name("rpossesdate2")).sendKeys(DueDate2);
		test.log(LogStatus.PASS, "Day is Entered");
		driver.findElement(By.name("rpossesdate3")).sendKeys(DueDate3);
		test.log(LogStatus.PASS, "Year is Entered");
		Thread.sleep(5000);
		driver.findElement(By.name("requestBean.password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Password Entered");
		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Click on submitt Button");
			}
		else
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("cancelLoan")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(1000);
			driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
			test.log(LogStatus.PASS, "SSN number is Entered: ");
			driver.findElement(By.name("requestBean.statusType")).sendKeys("Auction");
			test.log(LogStatus.PASS, "Auction is entered: ");
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Clicked on submit Button: ");
			driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[3]/td[6]/input")).click();
			test.log(LogStatus.PASS, "Clicked on Go Button: ");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
		driver.findElement(By.name("solvageCompany")).sendKeys("CARS");
		test.log(LogStatus.PASS, "Solvage Company is selected CARS ");
		driver.findElement(By.name("rpossesdate1")).sendKeys(DueDate1);
		test.log(LogStatus.PASS, "Month is Entered");
		driver.findElement(By.name("rpossesdate2")).sendKeys(DueDate2);
		test.log(LogStatus.PASS, "Day is Entered");
		driver.findElement(By.name("rpossesdate3")).sendKeys(DueDate3);
		test.log(LogStatus.PASS, "Year is Entered");
		Thread.sleep(3000);
		driver.findElement(By.name("requestBean.password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Password Entered");
		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Click on submitt Button");
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
		{
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
		test.log(LogStatus.PASS, "OK Button is Clicked");
		test.log(LogStatus.PASS, "Auction updated successfully.");
		}
		else
		{
		test.log(LogStatus.FAIL, "Auction Not updated successfully.");
		}
		}
		}
		}
		
public void Sale (String SSN,String FileName,int days) throws Exception
		{
//-------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					 if(ProductID.equals("TLP"))
					 {
				    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
					 }
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
					String DefaultDate=null;
					DefaultDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();					
					test.log(LogStatus.PASS, "Capture Default date:"+DefaultDate);
			driver.close();
		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		driver.manage().window().maximize();
		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
		test.log(LogStatus.PASS, "Username is entered: admin");
		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Password is entered: "+Password);
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
Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
		test.log(LogStatus.PASS, "Clicked on Transactions");
Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.findElement(By.linkText("Title Loan")).click();
		test.log(LogStatus.PASS, "Clicked on Title Loan");
		Thread.sleep(3000);
		driver.findElement(By.linkText("Post Default Transactions")).click();
		test.log(LogStatus.PASS, "Clicked on Post Default Transactions");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("requestBean.storeId")).sendKeys(StoreID);
		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
		driver.findElement(By.name("requestBean.ssn")).sendKeys(SSN);
		test.log(LogStatus.PASS, "SSN number is Entered: ");
		driver.findElement(By.name("requestBean.statusType")).sendKeys("Sale");
		test.log(LogStatus.PASS, "Auction is entered: ");
		driver.findElement(By.name("submit")).click();
		test.log(LogStatus.PASS, "Clicked on submitt Button: ");
		driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input")).click();
		test.log(LogStatus.PASS, "Clicked on Go Button: ");	
		driver.findElement(By.name("rpossesdate1")).sendKeys(DueDate1);
		test.log(LogStatus.PASS, "Month is Entered");
		driver.findElement(By.name("rpossesdate2")).sendKeys(DueDate2);
		test.log(LogStatus.PASS, "Day is Entered");
		driver.findElement(By.name("rpossesdate3")).sendKeys(DueDate3);
		test.log(LogStatus.PASS, "Year is Entered");
		driver.findElement(By.name("requestBean.saleValue")).clear();
		driver.findElement(By.name("requestBean.saleValue")).sendKeys("5000");
		test.log(LogStatus.PASS, "SalValue Entered");
		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Click on submitt Button");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
		{
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
		test.log(LogStatus.PASS, "OK Button is Clicked");
		test.log(LogStatus.PASS, "Sale completed successfully.");
		}
		else
		{
		test.log(LogStatus.FAIL, "Sale not completed.");
		}
		}
		}
		}

public void CustomerRefund(String SSN,String FileName) throws Exception
		{
//--------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
		for(String winHandle : driver.getWindowHandles())
		{
		driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button");
		for(String winHandle : driver.getWindowHandles())
		{
		driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		driver.findElement(By.xpath("//*[@id='showMore-2']")).click();
		String Loannbr1= driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[2]")).getText(); 
		int Loan1 = Integer.parseInt(Loannbr1);
		String Loannbr2=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[2]")).getText();
		int Loan2 = Integer.parseInt(Loannbr2);
		WebElement Go1 = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input"));
		WebElement Go2 = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[13]/input"));
		 if(ProductID.equals("TLP"))
		 {	
			 if(Loan1<Loan2)
				{
				Go1.click();
			}
			else 
			{
				Go2.click();
			}
		 }
	  //  driver.findElement(By.name("button")).click();
		//test.log(LogStatus.PASS, "Click on GO Button");
		for( String winHandle1 : driver.getWindowHandles())
		{
		    driver.switchTo().window(winHandle1);
		}			
		 driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 driver.findElement(By.name("transactionList")).sendKeys("CustomerRefund");
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
		driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
		test.log(LogStatus.PASS, "Enter the Disb Type Cash");
		driver.findElement(By.name("password")).sendKeys(Password);
		test.log(LogStatus.PASS, "Enter the Password");
		driver.findElement(By.name("finish")).click();
		test.log(LogStatus.PASS, "Click on Finish Refund Button");
	Thread.sleep(2000);
		if(driver.findElement(By.name("checkno")).isDisplayed())
		{
			driver.findElement(By.name("checkno")).click();
			test.log(LogStatus.PASS, "NO Button is Clicked");
		test.log(LogStatus.PASS, "CustomerRefund updated successfully.");
		}
		else
		{
			test.log(LogStatus.FAIL, "CustomerRefund Not updated successfully.");
			} 
		}
		}
		}
		
public void LoanDate_Agestore(String SSN,String FileName, int days) throws Exception
		{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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

		 if(ProductID.equals("TLP"))
		 {
	    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

			 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

		/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

		}

		//String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){

		driver.switchTo().window(winHandle);

		}

		Thread.sleep(8000);

		// driver.findElement(By.xpath("//*[@id='home']")).click();*/

		LoanDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[11]/td/span[2]")).getText();

		//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

		System.out.print(LoanDate);

		driver.close();

		driver = new InternetExplorerDriver();

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

		Thread.sleep(4000);

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

	 	Date DDueDate = df.parse(LoanDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDate);

		cal.add(Calendar.DATE, days);

		Date DDueDateminus1= cal.getTime();

		 String DueDateminus1 =df.format(DDueDateminus1);

		String DueDate0[] =DueDateminus1.split("/"); 
		
	/*	 Date DDueDate = df.parse(LoanDate);
	     Calendar cal = Calendar.getInstance();
	     cal.setTime(DDueDate);
	     cal.add(Calendar.DATE, days);
	      Date DDueDateminus1= cal.getTime();

	      String DueDateminus1 =df.format(DDueDateminus1);
	     String DueDate0[] =LoanDate.split("/"); */
		
		
		
		

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

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		/*driver.findElement(By.linkText("Borrower")).click();

		test.log(LogStatus.PASS, "Clicked on Borrower");

		Thread.sleep(5000);*/

		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		
		driver.findElement(By.linkText("Process Date Change")).click();

		test.log(LogStatus.PASS, "Clicked on Process Date Change");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
		
		
		
		public void NewLoanWithVIN2(String SSN,String FileName,String NewVIN2) throws Exception{
//------------------done

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);      	
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
					//String Term = TestData.getCellData(sheetName,"Term",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					// String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
					//System.out.println(Term);
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					//String stateProduct=State+" "+ProductID;
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					System.out.println(ESign_CollateralType);
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
					System.out.println(last4cheknum);
					System.out.println(stateProductType);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					//String UserName = TestData.getCellData(sheetName,"UserName",row);
					//String Password = TestData.getCellData(sheetName,"Password",row);
					// System.out.println(Password);
					//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					//String ProductID = TestData.getCellData(sheetName,"ProductID",row);
					String StateID = TestData.getCellData(sheetName,"StateID",row);
					//String SSN = TestData.getCellData(sheetName,"SSN",row);	
					String Parent_Window = driver.getWindowHandle();
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					//*[@id="911100"]/a
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					for(String winHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle);	
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input      Loan2
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input      Loan1
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					//	Selection of Product based on the Name provided in Test Data

					if(driver.findElement(By.id("LoanButtonId")).isEnabled())
					//if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
						if(ProductID.equals("TLP"))							
						{					
							System.out.println("IN TLP");
							// name="vehicleChk"   //*[@id="veh1"]
							////*[@id="vehicleType_dd"]
							//*[@id="vehicleType_dd"]
							driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
							// //*[@id="vinDD"]
							driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
							// //*[@id="vinPop"]/div/table[1]/tbody/tr[1]/td[2]/input
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN2);	
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN2);
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();	
							Thread.sleep(2000);
						}												
					if(ProductName.equals("TNPAYDAY"))
					{
						////							*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{								////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.name("prodSel")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						//driver.findElement(By.xpath("//*[@id="termSel1"]")).click();
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
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
					/*driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");
					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}
					Thread.sleep(1000);
					driver.findElement(By.name("confirmSummary")).click();
					test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");*/
					driver.findElement(By.id("LoanButtonId")).click();
					//driver.findElement(By.id("LoanButtonId")).click();

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
						//test.log(LogStatus.PASS, "Disb Amt is enterted as 210");
						///driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
						/*				try { 
							    Alert alert = driver.switchTo().alert();
							    String Var1 = alert.getText();
							    test.log(LogStatus.PASS, "ALert Displayed is :: "+Var1);

							    alert.accept();
							    //if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							Thread.sleep(5000);
							driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(ESign_DisbType2);
							driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).clear();
							driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("210");
							driver.findElement(By.name("finishadvance")).click();
							//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
							test.log(LogStatus.PASS, "click on Finish Loan button ");*/

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
						//Thread.sleep(2000);
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
						/*if(driver.findElement(By.name("Ok")).isDisplayed())
							{
								//driver.findElement(By.name("Ok")).click();
								test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
								//driver.findElement(By.name("Ok")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
							}*/
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
						//name="requestBean.titleNumber"
						driver.findElement(By.name("requestBean.titleNumber")).clear();
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						//name="requestBean.appraisalVal"
						driver.findElement(By.name("requestBean.appraisalVal")).clear();
						driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
						//driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
						//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
						//	for( String winHandle1 : driver.getWindowHandles())
						//	{
						//	    driver.switchTo().window(winHandle1);
						//	}			
						// driver.switchTo().defaultContent();
						// driver.switchTo().frame("mainFrame");
						// driver.switchTo().frame("main");
						//requestBean.extClr
						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						//requestBean.licensePltNbr
						driver.findElement(By.name("requestBean.licensePltNbr")).clear();
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						//requestBean.licensePltExpire
						driver.findElement(By.name("requestBean.licensePltExpire")).clear();
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						//requestBean.paintCondition
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
						Thread.sleep(2000);
						//driver.findElement(By.name("button2")).click();	
						//driver.findElement(By.name("button2")).click();	
						test.log(LogStatus.PASS, "click on Update 2 button ");
						//Thread.sleep(8000);
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						/*for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");*/
						//process
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
						Thread.sleep(2000);
						
					/*	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						//element
						driver.findElement(By.name("negLoanAmt")).click();
						for( String winHandle1 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle1);

						}

						Thread.sleep(1000);


						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);

						//requestBean.siilBean.disbType
						//requestBean.siilBean.advAmt
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).click();
						driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
						Thread.sleep(3000);

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						Thread.sleep(3000);
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
						 .sendKeys(element, Keys.ARROW_RIGHT)
							   .sendKeys(element, Keys.ARROW_RIGHT)
							   .doubleClick()
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



								 //WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
							  Actions action = new Actions(driver);	

							  action.moveToElement(element).doubleClick().sendKeys("415");
														  Keys.ENTER;
							  Keys.BACKSPACE;
							   driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
							   Actions builder = new Actions(driver); 
							   builder.moveToElement(element)
							    .clickAndHold()
							    .sendKeys("415");

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}

						Thread.sleep(6000);
						driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys("1250");
						driver.findElement(By.name("negSel")).click();
						driver.findElement(By.name("reCalculate")).click();
						driver.findElement(By.name("negSel")).click();
						Thread.sleep(3000);
						driver.switchTo().window(Parent_Window);

						for( String winHandle1 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle1);

						}                    

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");*/
						//negLoanAmt   negotiateamount button
						//requestBean.siilBean.advAmt       ChangedLoanAmountField
						//reCalculate   Recalculate
						//negSel  Negotiation Complete


						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						//requestBean.siilBean.disbType
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						//requestBean.siilBean.advAmt
						String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");
						//String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						/*	int Instamt1 = Integer.parseInt(Instamt);
						      int inst2		
									String s=Integer.toString(i);
							System.out.println(Instamt)*/;

							//requestBean.siilBean.disbAmtFirst
							//
							driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);


							Thread.sleep(5000);
							//requestBean.siilBean.disbTypeSecond
							/*								driver.findElement(By.name("requestBean.siilBean.disbTypeSecond")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type2 is selected as ::"+ESign_DisbType);
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							Thread.sleep(5000);

								//requestBean.siilBean.disbAmtSecond
							driver.findElement(By.name("requestBean.siilBean.disbAmtSecond")).sendKeys("260");
							test.log(LogStatus.PASS, "Disb Amt2 is enterted as 260");
							//requestBean.siilBean.emailConsentFlag
							 */								
							/*driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
							driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);*/
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
							//OKBut
							if(driver.findElement(By.name("ok")).isDisplayed())
								//if(driver.findElement(By.name("OKBut")).isDisplayed())
							{
								test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
								//driver.findElement(By.name("ok")).click();
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
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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

			}
		}

		public void NewLoanWithVIN3(String SSN,String FileName,String NewVIN2) throws Exception{

//------------------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);      	
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
					//String Term = TestData.getCellData(sheetName,"Term",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					// String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
					//System.out.println(Term);
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					//String stateProduct=State+" "+ProductID;
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					System.out.println(ESign_CollateralType);
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
					System.out.println(last4cheknum);
					System.out.println(stateProductType);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					//String UserName = TestData.getCellData(sheetName,"UserName",row);
					//String Password = TestData.getCellData(sheetName,"Password",row);
					// System.out.println(Password);
					//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					//String ProductID = TestData.getCellData(sheetName,"ProductID",row);
					String StateID = TestData.getCellData(sheetName,"StateID",row);
					//String SSN = TestData.getCellData(sheetName,"SSN",row);	
					String Parent_Window = driver.getWindowHandle();
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					//*[@id="911100"]/a
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
					for(String winHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle);	
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input      Loan2
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input      Loan1
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
						if(ProductID.equals("TLP"))							
						{					
							System.out.println("IN TLP");
							// name="vehicleChk"   //*[@id="veh1"]
							////*[@id="vehicleType_dd"]
							//*[@id="vehicleType_dd"]
							driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
							// //*[@id="vinDD"]
							driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
							// //*[@id="vinPop"]/div/table[1]/tbody/tr[1]/td[2]/input
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN2);	
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN2);
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();	
							Thread.sleep(2000);
						}												
					if(ProductName.equals("TNPAYDAY"))
					{
						////							*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{								////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.name("prodSel")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					Thread.sleep(1000);
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
					
					//------------Added by Srikanth
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
					//driver.findElement(By.id("LoanButtonId")).click();

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
						//Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type1 is enterted as "+ESign_DisbType);
						//Thread.sleep(2000);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys("215");					
						test.log(LogStatus.PASS, "Disb Amt1 is enterted as ::" +"215");
						//Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(ESign_DisbType2);
						test.log(LogStatus.PASS, "Disb Type2 is selected as ::Cash");



						driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("210");
						test.log(LogStatus.PASS, "Disb Amt2 is enterted as 210");
						//test.log(LogStatus.PASS, "Disb Amt is enterted as 210");
						///driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
						/*				try { 
							    Alert alert = driver.switchTo().alert();
							    String Var1 = alert.getText();
							    test.log(LogStatus.PASS, "ALert Displayed is :: "+Var1);

							    alert.accept();
							    //if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							Thread.sleep(5000);
							driver.findElement(By.name("advanceRequestBean.disbursementTypeSecond")).sendKeys(ESign_DisbType2);
							driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).clear();
							driver.findElement(By.name("advanceRequestBean.disbAmtSecond")).sendKeys("210");
							driver.findElement(By.name("finishadvance")).click();
							//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
							test.log(LogStatus.PASS, "click on Finish Loan button ");*/

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
						//Thread.sleep(2000);
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
						/*if(driver.findElement(By.name("Ok")).isDisplayed())
							{
								//driver.findElement(By.name("Ok")).click();
								test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
								//driver.findElement(By.name("Ok")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
							}*/
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
						//name="requestBean.titleNumber"
						driver.findElement(By.name("requestBean.titleNumber")).clear();
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						//name="requestBean.appraisalVal"
						driver.findElement(By.name("requestBean.appraisalVal")).clear();
						driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
						//driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
						//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
						//	for( String winHandle1 : driver.getWindowHandles())
						//	{
						//	    driver.switchTo().window(winHandle1);
						//	}			
						// driver.switchTo().defaultContent();
						// driver.switchTo().frame("mainFrame");
						// driver.switchTo().frame("main");
						//requestBean.extClr
					//	driver.findElement(By.name("requestBean.extClr")).clear();
						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						//requestBean.licensePltNbr
						driver.findElement(By.name("requestBean.licensePltNbr")).clear();
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						//requestBean.licensePltExpire
						driver.findElement(By.name("requestBean.licensePltExpire")).clear();
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						//requestBean.paintCondition
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
						Thread.sleep(2000);
						//driver.findElement(By.name("button2")).click();	
						//driver.findElement(By.name("button2")).click();	
						test.log(LogStatus.PASS, "click on Update 2 button ");
						//Thread.sleep(8000);
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						/*for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");*/
						//process
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
						Thread.sleep(2000);
						
					/*	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						//element
						driver.findElement(By.name("negLoanAmt")).click();
						for( String winHandle1 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle1);

						}

						Thread.sleep(1000);


						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);

						//requestBean.siilBean.disbType
						//requestBean.siilBean.advAmt
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).click();
						driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
						Thread.sleep(3000);

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						Thread.sleep(3000);
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
						 .sendKeys(element, Keys.ARROW_RIGHT)
							   .sendKeys(element, Keys.ARROW_RIGHT)
							   .doubleClick()
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



								 //WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
							  Actions action = new Actions(driver);	

							  action.moveToElement(element).doubleClick().sendKeys("415");
														  Keys.ENTER;
							  Keys.BACKSPACE;
							   driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
							   Actions builder = new Actions(driver); 
							   builder.moveToElement(element)
							    .clickAndHold()
							    .sendKeys("415");

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}

						Thread.sleep(6000);
						driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys("1250");
						driver.findElement(By.name("negSel")).click();
						driver.findElement(By.name("reCalculate")).click();
						driver.findElement(By.name("negSel")).click();
						Thread.sleep(3000);
						driver.switchTo().window(Parent_Window);

						for( String winHandle1 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle1);

						}                    

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");*/
						//negLoanAmt   negotiateamount button
						//requestBean.siilBean.advAmt       ChangedLoanAmountField
						//reCalculate   Recalculate
						//negSel  Negotiation Complete


						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						//requestBean.siilBean.disbType
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						//requestBean.siilBean.advAmt
						String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");
						//String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						/*	int Instamt1 = Integer.parseInt(Instamt);
						      int inst2		
									String s=Integer.toString(i);
							System.out.println(Instamt)*/;

							//requestBean.siilBean.disbAmtFirst
							//
							driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);


						//	Thread.sleep(5000);
							//requestBean.siilBean.disbTypeSecond
							/*								driver.findElement(By.name("requestBean.siilBean.disbTypeSecond")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type2 is selected as ::"+ESign_DisbType);
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							Thread.sleep(5000);

								//requestBean.siilBean.disbAmtSecond
							driver.findElement(By.name("requestBean.siilBean.disbAmtSecond")).sendKeys("260");
							test.log(LogStatus.PASS, "Disb Amt2 is enterted as 260");
							//requestBean.siilBean.emailConsentFlag
							 */								
							/*driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
							driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);*/
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
							//OKBut
							if(driver.findElement(By.name("ok")).isDisplayed())
								//if(driver.findElement(By.name("OKBut")).isDisplayed())
							{
								test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
								//driver.findElement(By.name("ok")).click();
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
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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

			}
			}
		}

		public void AgeStore1_1_C(String SSN,String FileName,int days) throws Exception

		{
//----------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

		/*Thread.sleep(5000);

		Thread.sleep(1000);
*/driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Loan Transactions");

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

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

		 if(ProductID.equals("TLP"))
		 {
	    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

			 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
		    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();

			 //driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
			 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
		 }
	  //  driver.findElement(By.name("button")).click();
		test.log(LogStatus.PASS, "Click on GO Button"); //1st Loan Go Button
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

		/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

		}

		//String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){

		driver.switchTo().window(winHandle);

		}

		Thread.sleep(8000);

		// driver.findElement(By.xpath("//*[@id='home']")).click();*/

		DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();

		//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
		test.log(LogStatus.PASS, "Captured Due date:"+DueDate);
		//System.out.print(DueDate);

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

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

		Date DDueDate = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDate);

		cal.add(Calendar.DATE, days);

		Date DDueDateminus1= cal.getTime();

		// String DueDateminus1 =df.format(DDueDateminus1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
		test.log(LogStatus.PASS, "Clicked on Transactions");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
	
		/*driver.findElement(By.linkText("Borrower")).click();
		test.log(LogStatus.PASS, "Clicked on Borrower");*/
		/*driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Borrower")).click();

		test.log(LogStatus.PASS, "Clicked on Borrower");

		Thread.sleep(5000);*/

		/*driver.findElement(By.linkText("Process Date Change")).click();

		test.log(LogStatus.PASS, "Clicked on Process Date Change");
		
*/
		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		driver.findElement(By.linkText("Process Date Change")).click();
		test.log(LogStatus.PASS, "Clicked on Process Date Change");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		//Thread.sleep(2000);

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Thread.sleep(1000);

		//Thread.sleep(5000);

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
	
		
		public void EOD_BatchProcess_1_1_C(String SSN,String FileName,int days) throws Exception

		 {

		 	//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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


		 		//	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();

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

		 			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
		 			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
		 			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

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

		
		public void EOD_BatchProcess_2_1_C(String SSN,String FileName,int days) throws Exception

		 {

		 	//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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
			    	//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
			    	//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();

		 			//driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

		 			test.log(LogStatus.PASS, "Click on GO Button");

		 			for(String winHandle : driver.getWindowHandles()){

		 				driver.switchTo().window(winHandle);

		 			}

		 			driver.switchTo().defaultContent();

		 			driver.switchTo().frame("mainFrame");

		 			driver.switchTo().frame("main");


		 		//	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();

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

		 			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
		 			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
		 			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

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




		public void NewLoanWithVIN2_1(String SSN,String FileName,String NewVIN2) throws Exception{

//------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);      	
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
					//String Term = TestData.getCellData(sheetName,"Term",row);
					String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
					// String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
					//System.out.println(Term);
					String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					String StoreID = TestData.getCellData(sheetName,"StoreID",row);
					//String stateProduct=State+" "+ProductID;
					String stateProductType=State+" "+ProductType;
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					System.out.println(ESign_CollateralType);
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
					System.out.println(last4cheknum);
					System.out.println(stateProductType);
					String AppURL = TestData.getCellData(sheetName,"AppURL",row);
					//String UserName = TestData.getCellData(sheetName,"UserName",row);
					//String Password = TestData.getCellData(sheetName,"Password",row);
					// System.out.println(Password);
					//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
					//String ProductID = TestData.getCellData(sheetName,"ProductID",row);
					String StateID = TestData.getCellData(sheetName,"StateID",row);
					//String SSN = TestData.getCellData(sheetName,"SSN",row);	
					String Parent_Window = driver.getWindowHandle();
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					//*[@id="911100"]/a
					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
					for(String winHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle);	
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input      Loan2
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input      Loan1
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					//	Selection of Product based on the Name provided in Test Data

					if(driver.findElement(By.id("LoanButtonId")).isEnabled())
					//if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
						if(ProductID.equals("TLP"))							
						{					
							System.out.println("IN TLP");
							// name="vehicleChk"   //*[@id="veh1"]
							////*[@id="vehicleType_dd"]
							//*[@id="vehicleType_dd"]
							driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
							// //*[@id="vinDD"]
							driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
							// //*[@id="vinPop"]/div/table[1]/tbody/tr[1]/td[2]/input
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN2);	
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN2);
							driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).clear();
							driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
							driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
						}												
					if(ProductName.equals("TNPAYDAY"))
					{
						////							*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{								////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.name("prodSel")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						//driver.findElement(By.xpath("//*[@id="termSel1"]")).click();
						//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
				
					driver.findElement(By.id("LoanButtonId")).click();
					//driver.findElement(By.id("LoanButtonId")).click();

					test.log(LogStatus.PASS, "Clicked on New Loan button");
					//New Loan Screens
					
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
						//name="requestBean.titleNumber"
						driver.findElement(By.name("requestBean.titleNumber")).clear();
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						//name="requestBean.appraisalVal"
						driver.findElement(By.name("requestBean.appraisalVal")).clear();
						driver.findElement(By.name("requestBean.appraisalVal")).sendKeys(AppraisalValue);
						//driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
						//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));
						
						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						//requestBean.licensePltNbr
						driver.findElement(By.name("requestBean.licensePltNbr")).clear();
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						//requestBean.licensePltExpire
						driver.findElement(By.name("requestBean.licensePltExpire")).clear();
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						//requestBean.paintCondition
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
						Thread.sleep(2000);
						//driver.findElement(By.name("button2")).click();	
						//driver.findElement(By.name("button2")).click();	
						test.log(LogStatus.PASS, "click on Update 2 button ");
					//	Thread.sleep(8000);
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						/*for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");*/
						//process
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
						Thread.sleep(2000);
						
						/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);*/
						//element
						driver.findElement(By.name("negLoanAmt")).click();
						for( String winHandle1 : driver.getWindowHandles())

						{

						if(!(winHandle1.equals(Parent_Window)))

						{

						driver.switchTo().window(winHandle1);
						Thread.sleep(2000);
				
						Thread.sleep(5000);


						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);

						//requestBean.siilBean.disbType
						//requestBean.siilBean.advAmt
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.siilBean.advAmt")));
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).
						//driver.findElement(By.name("requestBean.siilBean.advAmt")).click();
						Thread.sleep(1000);
						driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
						

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						//Thread.sleep(3000);
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
						 .sendKeys(element, Keys.ARROW_RIGHT)
							   .sendKeys(element, Keys.ARROW_RIGHT)
							   .doubleClick()
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



								 //WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
							 /* Actions action = new Actions(driver);	

							  action.moveToElement(element).doubleClick().sendKeys("415");
														  Keys.ENTER;
							  Keys.BACKSPACE;
							   driver.findElement(By.name("requestBean.siilBean.advAmt")).clear();
							   Actions builder = new Actions(driver); 
							   builder.moveToElement(element)
							    .clickAndHold()
							    .sendKeys("415");*/

						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}

						//Thread.sleep(6000);
						driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys("1250");
						driver.findElement(By.name("negSel")).click();
						driver.findElement(By.name("reCalculate")).click();
						driver.findElement(By.name("negSel")).click();
					//	Thread.sleep(3000);
						driver.switchTo().window(Parent_Window);

						for( String winHandle2 : driver.getWindowHandles())

						{

							driver.switchTo().window(winHandle2);

						}                    

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");
						//negLoanAmt   negotiateamount button
						//requestBean.siilBean.advAmt       ChangedLoanAmountField
						//reCalculate   Recalculate
						//negSel  Negotiation Complete


						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						//requestBean.siilBean.disbType
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						//requestBean.siilBean.advAmt
						String Instamt=driver.findElement(By.name("requestBean.siilBean.advAmt")).getAttribute("value");
						//String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						/*	int Instamt1 = Integer.parseInt(Instamt);
						      int inst2		
									String s=Integer.toString(i);
							System.out.println(Instamt)*/;

							//requestBean.siilBean.disbAmtFirst
							//
							driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys("1250");
							test.log(LogStatus.PASS, "Disb Amt is enterted as "+"1250");


							//Thread.sleep(5000);
							//requestBean.siilBean.disbTypeSecond
							/*								driver.findElement(By.name("requestBean.siilBean.disbTypeSecond")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type2 is selected as ::"+ESign_DisbType);
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							Thread.sleep(5000);

								//requestBean.siilBean.disbAmtSecond
							driver.findElement(By.name("requestBean.siilBean.disbAmtSecond")).sendKeys("260");
							test.log(LogStatus.PASS, "Disb Amt2 is enterted as 260");
							//requestBean.siilBean.emailConsentFlag
							 */								
							/*driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
							driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
							test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);*/
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
							for( String winHandle3 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle3);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
							//driver.findElement(By.name("OKBut")).click();
							//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
							test.log(LogStatus.PASS, "click on Yes button ");
							for( String winHandle4 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle4);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							//OKBut
							if(driver.findElement(By.name("ok")).isDisplayed())
								//if(driver.findElement(By.name("OKBut")).isDisplayed())
							{
								test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
								//driver.findElement(By.name("ok")).click();
								driver.findElement(By.name("ok")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
							}
					}
					
					}

					//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
				}
				}
			}
		}

		
		public void AgeStore2_1_C(String SSN,String FileName,int days) throws Exception

		{
//-------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Loan Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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


				//driver.findElement(By.xpath("//input[starts-with(@onclick,'locSel')]")).click();
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

		/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

		}

		//String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){

		driver.switchTo().window(winHandle);

		}

		Thread.sleep(8000);

		// driver.findElement(By.xpath("//*[@id='home']")).click();*/

		DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[2]/td[2]")).getText();
		
		 test.log(LogStatus.PASS, "Captured Due date:"+DueDate);

		//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

		System.out.print(DueDate);

		driver.close();

		driver = new InternetExplorerDriver();

		driver.get(AdminURL);
		driver.manage().window().maximize();
		// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

		test.log(LogStatus.PASS, "Username is entered: admin");

		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password is entered: "+Password);

		//Click Login Button

		driver.findElement(By.name("login")).click();

		test.log(LogStatus.PASS, "Clicked on Submit button");

		//Thread.sleep(8000);

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

		Date DDueDate = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDate);

		cal.add(Calendar.DATE, days);

		Date DDueDateminus1= cal.getTime();

		// String DueDateminus1 =df.format(DDueDateminus1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
		test.log(LogStatus.PASS, "Clicked on Transactions");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
	
		/*driver.findElement(By.linkText("Borrower")).click();
		test.log(LogStatus.PASS, "Clicked on Borrower");*/
		/*driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Borrower")).click();

		test.log(LogStatus.PASS, "Clicked on Borrower");

		Thread.sleep(5000);*/
		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		driver.findElement(By.linkText("Process Date Change")).click();
		test.log(LogStatus.PASS, "Clicked on Process Date Change");

		/*driver.findElement(By.linkText("Process Date Change")).click();

		test.log(LogStatus.PASS, "Clicked on Process Date Change");
*/
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		//Thread.sleep(2000);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Thread.sleep(1000);

		//Thread.sleep(5000);

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

		
		public void AgeStore1_1BatchProcess(String SSN,String FileName,int days) throws Exception

		{
//-----------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Loan Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

			 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
	    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
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

		/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

		}

		//String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){

		driver.switchTo().window(winHandle);

		}

		Thread.sleep(8000);

		// driver.findElement(By.xpath("//*[@id='home']")).click();*/

		DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

		//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

		System.out.print(DueDate);

		driver.close();

		driver = new InternetExplorerDriver();

		driver.get(AdminURL);
driver.manage().window().maximize();
		// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

		DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

		test.log(LogStatus.PASS, "Username is entered: admin");

		driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

		test.log(LogStatus.PASS, "Password is entered: "+Password);

		//Click Login Button

		driver.findElement(By.name("login")).click();

		test.log(LogStatus.PASS, "Clicked on Submit button");

		//Thread.sleep(8000);

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

		Date DDueDate = df.parse(DueDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDate);

		cal.add(Calendar.DATE, days);

		Date DDueDateminus1= cal.getTime();

		// String DueDateminus1 =df.format(DDueDateminus1);

		String DueDate0[] =DueDate.split("/");

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];

		/*driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Borrower")).click();

		test.log(LogStatus.PASS, "Clicked on Borrower");

		Thread.sleep(5000);*/
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
		test.log(LogStatus.PASS, "Clicked on Transactions");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
	
		/*driver.findElement(By.linkText("Borrower")).click();
		test.log(LogStatus.PASS, "Clicked on Borrower");*/
		
		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		
		driver.findElement(By.linkText("Process Date Change")).click();

		test.log(LogStatus.PASS, "Clicked on Process Date Change");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		driver.findElement(By.name("storeCode")).click();

		//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

		driver.findElement(By.name("storeCode")).sendKeys(StoreID);

		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

		//Thread.sleep(5000);

		driver.findElement(By.name("beginMonth")).clear();

		driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

		test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

		driver.findElement(By.name("beginDay")).clear();

		driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

		test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

		driver.findElement(By.name("beginYear")).clear();

		driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

		test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

		//Thread.sleep(2000);

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Thread.sleep(1000);

		//Thread.sleep(5000);

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

		
		
		//Thread.sleep(5000);
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
		

		public void AgeStore_WriteOff60Days(String SSN,String FileName,int Days) throws Exception

		{



			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					/*Thread.sleep(5000);

					Thread.sleep(1000);*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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



					if(ProductID.equals("TLP"))

					{

						///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]



						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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



					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					//*[@id="myTable1"]/tbody[2]/tr[2]/td[2]     1st ins date 2nd loan
					//*[@id="myTable1"]/tbody[2]/tr[3]/td[2]
				//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]")).getText(); /// pradeep
					//                                      //*[@id="transactionHistoryTable"]/tbody/tr/td[4]/table/tbody/tr[6]/td/span[2]
					// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					// //*[@id="transactionHistoryTable"]/tbody/tr/td[4]/table/tbody/tr[6]/td/span[2]
					//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[7]/td/span[2]

					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText(); /// pradeep
					// 										//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

					test.log(LogStatus.PASS, "Capture DefaultDate"+DueDate);

					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();



					driver = new InternetExplorerDriver();

					driver.get(AdminURL);

					// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
					driver.manage().window().maximize();																			

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

					test.log(LogStatus.PASS, "Username is entered: admin");

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Password is entered: "+Password);

					//Click Login Button

					driver.findElement(By.name("login")).click();

					test.log(LogStatus.PASS, "Clicked on Submit button");
					Thread.sleep(3000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
					test.log(LogStatus.PASS, "Clicked on Transactions");
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
				
					/*driver.findElement(By.linkText("Borrower")).click();
					test.log(LogStatus.PASS, "Clicked on Borrower");*/
					/*Thread.sleep(8000);




					driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

					driver.findElement(By.linkText("Borrower")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					Thread.sleep(5000);
*/
					/*driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process Date Change");*/

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.findElement(By.linkText("QA Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on QA Jobs");
					driver.findElement(By.linkText("Process Date Change")).click();
					test.log(LogStatus.PASS, "Clicked on Process Date Change");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));

					Actions actions1 = new Actions(driver);

					actions1.moveToElement(elements1).build().perform();

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



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

				Thread.sleep(3000);

					driver.findElement(By.name("beginMonth")).clear();

					driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

					test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

					driver.findElement(By.name("beginDay")).clear();

					driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

					test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

					driver.findElement(By.name("beginYear")).clear();

					driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

					test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

					/*Thread.sleep(2000);

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					Thread.sleep(1000);

					Thread.sleep(5000);*/

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

					/*driver.close();

	driver = new InternetExplorerDriver();

	driver.get(AdminURL)*/;
	//Thread.sleep(5000);
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
		
		
		
		public void LoanDate_Agestore_C(String SSN,String FileName, int days) throws Exception

		{
//-----------------done
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

		//Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Loan Transactions");

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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

			 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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

		/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

		}

		//String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){

		driver.switchTo().window(winHandle);

		}

		Thread.sleep(8000);

		// driver.findElement(By.xpath("//*[@id='home']")).click();*/

		LoanDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[11]/td/span[2]")).getText();

		//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

		System.out.print(LoanDate);

		driver.close();

		driver = new InternetExplorerDriver();

		driver.get(AdminURL);
driver.manage().window().maximize();
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

		//for(String winHandle : driver.getWindowHandles()){

		// driver.switchTo().window(winHandle);

		//}

		// driver.switchTo().defaultContent();

		// driver.switchTo().frame("mainFrame");

		//driver.switchTo().frame("main");

	 	Date DDueDate = df.parse(LoanDate);

		Calendar cal = Calendar.getInstance();

		cal.setTime(DDueDate);

		cal.add(Calendar.DATE, days);

		Date DDueDateminus1= cal.getTime();

		 String DueDateminus1 =df.format(DDueDateminus1);

		String DueDate0[] =DueDateminus1.split("/"); 
		
	/*	 Date DDueDate = df.parse(LoanDate);
	     Calendar cal = Calendar.getInstance();
	     cal.setTime(DDueDate);
	     cal.add(Calendar.DATE, days);
	      Date DDueDateminus1= cal.getTime();

	      String DueDateminus1 =df.format(DDueDateminus1);
	     String DueDate0[] =LoanDate.split("/"); */
		
		
		
		

		String DueDate1 = DueDate0[0];

		String DueDate2 = DueDate0[1];

		String DueDate3 = DueDate0[2];
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
		test.log(LogStatus.PASS, "Clicked on Transactions");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
	
		/*driver.findElement(By.linkText("Borrower")).click();
		test.log(LogStatus.PASS, "Clicked on Borrower");*/
		
		driver.findElement(By.linkText("QA Jobs")).click();
		test.log(LogStatus.PASS, "Clicked on QA Jobs");
		/*driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

		test.log(LogStatus.PASS, "Clicked on Transactions");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Borrower")).click();

		test.log(LogStatus.PASS, "Clicked on Borrower");

		Thread.sleep(5000);*/

		driver.findElement(By.linkText("Process Date Change")).click();

		test.log(LogStatus.PASS, "Clicked on Process Date Change");

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("mainFrame");

		driver.switchTo().frame("main");

		driver.findElement(By.name("storeCode")).click();

		//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

		driver.findElement(By.name("storeCode")).sendKeys(StoreID);

		test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

		Thread.sleep(3000);

		driver.findElement(By.name("beginMonth")).clear();

		driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);

		test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);

		driver.findElement(By.name("beginDay")).clear();

		driver.findElement(By.name("beginDay")).sendKeys(DueDate2);

		test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);

		driver.findElement(By.name("beginYear")).clear();

		driver.findElement(By.name("beginYear")).sendKeys(DueDate3);

		test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);

		//Thread.sleep(2000);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Thread.sleep(1000);

		//Thread.sleep(5000);

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
	
		
		
		
		public void EOD_BatchProcess_DueDate(String SSN,String FileName,int days, int InstNum) throws Exception

		 {

		 	//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

// //*[@id="myTable1"]/tbody[2]/tr[2]/td[2]
		 			

		 			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
	            	 int schsize = options.size();


		 			
				
		 			
		 			DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr["+InstNum+"]/td[2]")).getText();
		 			                                       //*[@id="myTable1"]/tbody[2]/tr[3]/td[2]

		 		//	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

		 			// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
		 			test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	

		 

		 			/*}
		 			 */
		 			Thread.sleep(1000);
		 			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

		 	/*		test.log(LogStatus.PASS, "DueDate:" + DueDate);*/


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


		


		public void WriteOff_Status(String SSN,String FileName) throws Exception

		{



			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					/*Thread.sleep(5000);

					Thread.sleep(1000);
*/
					driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

			//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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



						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();

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

					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[8]/td/span[2]")).getText();
					
					test.log(LogStatus.PASS, "WriteOff Date"+DueDate);
					test.log(LogStatus.PASS, "Loan Status is WriteOff");


					//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

					System.out.print(DueDate);

					driver.close();
					driver = new InternetExplorerDriver();
				}
			}
		}

		//@Test(priority=0)

		public void RegistrationTest() throws Exception 
  {

			// Start test. Mention test script name
			String FileName= "AA_BorrowerRegistration_NewLoan_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);    
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
					test = reports.startTest("AA_BorrowerRegistration_NewLoan_Txn_"+Header, "Login_Home screen_Borrower_Registration_New loan");
					appUrl = AppURL;
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
					this.NewLoan(SSN, FileName);
					this.IETaskKiller();
				}
			}
		}
		
	//@Test  (priority=1)

			public void BorrowerRegistration_NewLoan_PartialPayment() throws Exception 
		{
				String FileName= "AA_BorrowerRegistration_NewLoan_PartialPaymentTxn_Testdata.xls";
				//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
				Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
				int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";
				for(int row=2;row<=lastrow;row++)
				{
					String RunFlag = TestData.getCellData(sheetName,"Run",row);
					if(RunFlag.equals("Y"))
					{	String AppURL = TestData.getCellData(sheetName,"AppURL",row);
						String UserName = TestData.getCellData(sheetName,"UserName",row);
						String Password = TestData.getCellData(sheetName,"Password",row);
						String StoreId = TestData.getCellData(sheetName,"StoreID",row);
						String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						String StateID = TestData.getCellData(sheetName,"StateID",row);
						String SSN = TestData.getCellData(sheetName,"SSN",row);	
						String Header = StateID+ "_" + ProductID;
						test = reports.startTest("AA_BorrowerRegistration_NewLoan_PartialPaymentTxn_"+Header, "TitleLoan_partialpaymentandstoptherenewal(beforeRenewalLettergenerate)_tryingtogenerateRenewalLetter10daysbeforeduedate_shouldnotgeneratetheRenewalLetter_RunEODon1stinstallmentDuedate_TitleLoanshouldnotberenew_PayofftheTitleLoanOn2ndinsDueate_payoffamountshouldbalanceprinciple,1stinstremaininginterest,feesonly");
						appUrl = AppURL;

						CSRLoginpage login = new CSRLoginpage();
						login.Login(UserName, Password, StoreId, driver, AppURL, test);
						BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
						this.NewLoan(SSN, FileName);
						this.Loandate_AgeStore(SSN, FileName, +5);
						this.PartialPayment(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 2);
						this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Renewal_Status(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Renewal_Status(SSN, FileName);
						this.AgeStore2ndInst_Duedate(SSN, FileName, 0);
						this.Inst_Payoff(SSN, FileName);
						this.IETaskKiller();
					}
				}
			}

		//@Test (priority=2)

			public void BorrowerRegistration_NewLoan_MakePayment() throws Exception 
			{
				String FileName= "AA_BorrowerRegistration_NewLoan_MakePayment_Txn_Testdata.xls";
				Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);    
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
						
						test = reports.startTest("AA_BorrowerRegistration_NewLoan_MakePaymentTxn_"+Header, "TitleLoan_ generateletter10daysbeforeDuedate_makepayment_to_apportinate_principle_then_TitleLoan_shouldbeRescheduled");
						appUrl = AppURL;
						CSRLoginpage login = new CSRLoginpage();
						login.Login(UserName, Password, StoreId, driver, AppURL, test);
						BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
						this.NewLoan(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 2);
						this.AgeStore(SSN, FileName, -10);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Before_Payment_InstNO(SSN, FileName);
						this.Payment(SSN, FileName);
						this.After_Payment_InstNO(SSN, FileName);
						this.IETaskKiller();
					}
				}
			}


	//@Test (priority=3)

			public void BorrowerRegistration_NewLoan_Miss1stInst_Default_WriteOff() throws Exception 
			{
				String FileName= "AA_BorrowerRegistration_NewLoan_Miss1stInst_Default_WriteOff_Txn_Testdata.xls";
				Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
						test = reports.startTest("AA_BorrowerRegistration_NewLoan_Miss1stInst_Default_WriteOffTxn_"+Header, "TitleLoan_ generateletter10daysbeforeDuedate_Missedthe1stInstallmentpayment_RunEODon1stinstallmentDuedate_Renewthe2ndinst_RunDefaultProcedureon2ndInstallmentDuedate_LoangetsDefault(MissedInst_PLUS_30 days)_RunWriteOffProcedureon60daysfromDefaultdate_LoangetsWriteOff");
						appUrl = AppURL;

						CSRLoginpage login = new CSRLoginpage();
						login.Login(UserName, Password, StoreId, driver, AppURL, test);
						BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
						this.NewLoan(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 2);
						this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Renewal_Status(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Renewal_Status(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 3);
						this.AgeStore_2BatchProcess(SSN, FileName, 0);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Loan_Status(SSN, FileName);
						this.AgeStore_WriteOff60Days(SSN, FileName, +60);
						this.WriteOff_Status(SSN, FileName);
						this.IETaskKiller();
					}
				}
				
		}
			
			
  // @Test (priority=4)

		public void NewLoanMultiDIsb_Void() throws Exception {

			// Start test. Mention test script name
			String FileName= "AA_NewLoanMultiDIsb_Void_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					test = reports.startTest("AA_NewLoanMultiDIsb_Void"+Header, "loanwithmultipledisbursement(cash&check)_voidwithOriginalcheck");
					appUrl = AppURL;

					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
					this.NewLoan1(SSN, FileName);
					this.Void(SSN, FileName);
					this.IETaskKiller();
				}
			}
			
		}
		
	// @Test (priority=5)

		public void partialPayment_StopRenewal_1stinstpmton2ndinstpmt() throws Exception 
		{

			String FileName= "AA_partialPayment_StopRenewal_1stinstpmton2ndinstpmt_Txn_Testdata.xls";

			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 

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

					test = reports.startTest("AA_partialPayment_StopRenewal_1stinstpmton2ndinstpmtTxn_"+Header, "Title Loan _ partial payment and stop the renewal(before letter generate) _ trying to generate letter 10 days before due date _ should not generate the letter _ Run EOD on 1st inst due date _ Title Loan should not be renew _ make 1st installment payment on 2nd installment duration and cancel the stop renewal_ Renewal should be posted and payment should apply.");

					appUrl = AppURL;

					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
					this.NewLoan(SSN, FileName);
					this.Loandate_AgeStore(SSN, FileName, +5);
					this.PartialPayment(SSN, FileName);
					//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 3);
					this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
					this.DrawerDeassign(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);
					this.Renewal_Status(SSN, FileName);
					this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
					this.Renewal_Status(SSN, FileName);
					this.AgeStore1_2(SSN, FileName, -5);
					this.Payment(SSN, FileName);
					this.Renewal_StatusEnd(SSN, FileName);
					this.IETaskKiller();
				}
			}
		}

	
	
//	@Test (priority=6)

		public void Miss1stInst_RunDefaulton2ndInst() throws Exception 
	{
			String FileName= "AA_Miss1stInst_RunDefaulton2ndInst_Txn_Testdata.xls";//AA_Miss1stInst_RunDefaulton2ndInst_Txn_Testdata
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					test = reports.startTest("AA_Miss1stInst_RunDefaulton2ndInstTxn_"+Header, "Title Loan _ generate letter 10 days before due date _ Missed the 1st Installment payment _ Run EOD on 1st installment due date _ Renew the 2nd inst._ Run Default Procedure on 2nd Installment due date -_ Loan gets Default(Missed Inst + 30 days)");
					appUrl = AppURL;

					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
					this.NewLoan(SSN, FileName);
					//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 2);
					this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
					this.DrawerDeassign(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);
					this.Renewal_Status(SSN, FileName);
				//	this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
					this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
					this.DrawerDeassign(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);
					this.Renewal_Status(SSN, FileName);
				//	this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 3);
					this.AgeStore_2BatchProcess(SSN, FileName, 0);
					this.DrawerDeassign(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);
					this.Loan_StatusEndDEF(SSN, FileName);
					this.IETaskKiller();
				}
			}
		}



		//@Test (priority=7)

		public void Default_CurePayment() throws Exception 
		{
			String FileName= "AA_Default_CurePayment_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					test = reports.startTest("AA_Default_CurePayment_Txn_"+Header, "TitleLoan_Generateletter10daysbeforeduedate_RunEODonDuedateof1stInstallment_RunEODon2ndInstallmentdateandcustomerstatuschangedtodefault_agethestore_PerformDefaultPaymentWithCureAmountOnly_LoanStatuschangedtoCurrent");

					appUrl = AppURL;
					CSRLoginpage login = new CSRLoginpage();
					login.Login(UserName, Password, StoreId, driver, AppURL, test);
					BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
					this.NewLoan(SSN, FileName);
					//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 2);
					this.AgeStore_1stInst_DueDate10DaysBefore(SSN, FileName, -10);
					this.DrawerDeassign(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);
					this.Renewal_Status(SSN, FileName);
				//	this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
					this.AgeStore_1stInst_DueDate(SSN, FileName, 0);
					this.DrawerDeassign(SSN, FileName);
					//this.EODProcessing(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);  				
					this.Renewal_Status(SSN, FileName);
					//this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 3);
					this.AgeStore_2BatchProcess(SSN, FileName, 0);
					this.DrawerDeassign(SSN, FileName);
					this.StatementGeneration_EODProcessing(SSN, FileName);
					this.StoreInfo(SSN, FileName);
					this.Safeassign(SSN, FileName);
					this.Drawerassign(SSN, FileName);
					this.Loan_Status(SSN, FileName);
					this.AgeStore_CureDate(SSN, FileName, 3);
					this.CurePayment(SSN, FileName);
					this.Loan_StatusEnd(SSN, FileName);
					this.IETaskKiller();
				}
			}
		}

   @Test (priority=8)
			public void Newloan_1stinstTo3rdinstPayment_check4thinstpayment() throws Exception 
			{
				String FileName= "AA_Newloan_1stinstTo3rdinstPayment_check4thinstpayment_Txn_Testdata.xls";
				Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);  
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
						test = reports.startTest("AA_Loan_1stTo3rdInstPayment_checkthe4thinstAmount"+Header, "Title Loan _ generate letter 10 days before due date _ customer made 1st installment payment_ Run EOD on due date _ Loan should Renewed._ same way pay upto 3rd installment_ Run EOD on 3rd inst due date _ Renew the Loan _ at the time of 4th installment renewal, Principle reduction should be apply(5% of Title Loan amount).");
						appUrl = AppURL;
						
						CSRLoginpage login = new CSRLoginpage();
						login.Login(UserName, Password, StoreId, driver, AppURL, test);
						BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
						this.NewLoan(SSN, FileName); 
						//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 2);
						this.AgeStore_1stInst(SSN, FileName, -10);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.Payment(SSN, FileName);
						this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						this.AgeStore_1stInst(SSN, FileName, 0);
						this.DrawerDeassign(SSN, FileName); 
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName); 
						//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 3);
						this.AgeStore_2ndInst(SSN, FileName, -10);
						this.DrawerDeassign(SSN, FileName);
						//this.EODProcessing(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName); 
						this.Payment(SSN, FileName);
					//	this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 3);
						this.AgeStore_2ndInst(SSN, FileName, 0);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName); 
						//this.EOD_BatchProcess_DueDate(SSN, FileName, -10, 4);
						this.AgeStore_3rdInst(SSN, FileName, -10);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName); 
						this.Payment(SSN, FileName);
						//this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 4);
						this.AgeStore_3rdInst(SSN, FileName, 0);
						this.DrawerDeassign(SSN, FileName);
						this.StatementGeneration_EODProcessing(SSN, FileName);
						this.StoreInfo(SSN, FileName);
						this.Safeassign(SSN, FileName);
						this.Drawerassign(SSN, FileName);
						this.PrincipalReduction_4thInst(SSN, FileName);
						this.IETaskKiller();
					}
				}
			}

		@Test (priority=9)
	
  		public void OneCustomer_TwoVINs_FourLoans() throws Exception
  	{
  			String FileName= "AA_OneCustomer_TwoVINs_FourLoans_Txn_Testdata.xls";
  			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);  
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
  					test = reports.startTest("AA_OneCustomer_TwoVINs_FourLoans"+Header, "Register customer _ Originate two loans on one VIN _ Originate another two Loans on Second VIN _ Four loans should be originated for the customer.");
  					appUrl = AppURL;
  					CSRLoginpage login = new CSRLoginpage();
  					login.Login(UserName, Password, StoreId, driver, AppURL, test);
  					BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
  					Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
  					this.NewLoanWithVIN(SSN, FileName,NewVIN);
  					this.NewLoanWithVIN2(SSN, FileName,NewVIN);
  					this.NewLoanWithVIN2_1(SSN, FileName,NewVIN2);
  					this.NewLoanWithVIN2(SSN, FileName,NewVIN2);
  					this.IETaskKiller();
  				}
  			}
  		}

	@Test (priority=10)
	
		 public void TwoLoans_OneVIN_OneDefault() throws Exception 
		 {
			String FileName= "AA_TwoLoans_OneVIN_OneDefault_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName); 
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
					String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
					String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
			        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			        String StateID = TestData.getCellData(sheetName,"StateID",row);
			        String SSN = TestData.getCellData(sheetName,"SSN",row);	
			        String Header = StateID+ "_" + ProductID;
			        test = reports.startTest("TwoLoans_OneVIN_OneDefault"+Header, "Originate two Loans on Same VIN_make one loan to Default_Other loan should not become Default.");
			        appUrl = AppURL;
			        CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
			        this.NewLoanWithVIN(SSN, FileName,NewVIN);
					this.LoanDate_Agestore_C(SSN, FileName,5);
			        this.NewLoanWithVIN3(SSN, FileName,NewVIN);
			       // this.EOD_BatchProcess_1_1_C(SSN, FileName, 0);
			        this.AgeStore1_1_C(SSN, FileName, 0); 
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			      //  this.EOD_BatchProcess_2_1_C(SSN, FileName, 0);
			        this.AgeStore2_1_C(SSN, FileName, 0);
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.AgeStore1_1BatchProcess_C(SSN, FileName, 0);// Pradeep
			        this.DefaultPaymentStatus1(SSN, FileName);
			        this.IETaskKiller();
			}
		}
	}
		
		
		@Test (priority=11)
		
		 public void TwoLoans_OneVIN_OneDefault_Repossession() throws Exception 
		{
			String FileName= "AA_TwoLoans_OneVIN_OneDefault_Repossession_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/TLP_Smoke/"+FileName);  
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
					String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
					String NewVIN2= TestData.getCellData(sheetName,"NewVIN2",row);
			        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
			        String StateID = TestData.getCellData(sheetName,"StateID",row);
			        String SSN = TestData.getCellData(sheetName,"SSN",row);	
			        String Header = StateID+ "_" + ProductID;
			        test = reports.startTest("AA_TwoLoans_OneVIN_OneDefault_Repossession"+Header, "Originate two Loans on Same VIN_make one loan to Default_Other loan should not become Default_Make Repossession for one loan_Repossession should post for Other Loan also_Make Auction for the Loan_Make Sale for the Loan with amout just more than two loans Total Due_Two loans shld be closed_Remaining amount should be Refunded to Custmr through  first loan.");
			        appUrl = AppURL;
			        
			        CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			       // Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
			        Reg.RegistrationPage_NewLoan_TLP_Smoke(driver, test, AppURL, SSN, FileName);
			        this.NewLoanWithVIN(SSN, FileName,NewVIN); //1st loan issue
				    this.LoanDate_Agestore_C(SSN, FileName,5); //update store date to 5 days from loan date
			        this.NewLoanWithVIN3(SSN, FileName,NewVIN); //2nd loan issue
			       // this.EOD_BatchProcess_1_1_C(SSN, FileName, 0);
			        this.AgeStore1_1_C(SSN, FileName, 0); //1st loan due date update
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			       // this.EOD_BatchProcess_2_1_C(SSN, FileName, 0);
			        this.AgeStore2_1_C(SSN, FileName, 0); //2nd loan due date update
			        this.DrawerDeassign(SSN, FileName);
			      //this.EODProcessing(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName); 
			        this.AgeStore1_1BatchProcess_C(SSN, FileName, 0);//1st Loan default date batch process //pradeep
			        this.DefaultPaymentStatus1_C(SSN, FileName);	//Fetching Loan Status of 2 loans
		            this.OutToRepo(SSN, FileName, 1); //Out to Repo submission
			        this.Repossession(SSN, FileName, 0); //repossession date = default date
			        this.DefaultPaymentStatus1_C(SSN, FileName); //Fetching Loan Status of 2 loans
			        this.AgeStore1_1BatchProcess_C(SSN, FileName, 21);//age store to 21 days from default date
                    this.Auction(SSN, FileName, 21);
			        this.Sale(SSN, FileName, 21);
				    this.CustomerRefund(SSN, FileName);
				    this.IETaskKiller();
			}
			}
		}


		@AfterMethod
		 public void getResult(ITestResult result) throws Exception{
		 if(ITestResult.FAILURE == result.getStatus())
		 {
			
			 String screenshotPath = TLP_SmokeExecution.getScreenhot(driver, result.getName());
				test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
				test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		 }
		 else if(result.getStatus() == ITestResult.SKIP)
		 {
			 test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }
		 else if(result.getStatus() == ITestResult.SUCCESS)
		 {
			 test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");
			 }
		 reports.endTest(test);
	    reports.flush();
	    Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
	    Thread.sleep(3000);
	    driver.close();
		 }
		 
		 public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception 
		 {
			 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			 File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);
			 String destination = System.getProperty("user.dir") + "/ExecutionReports/FailedTestsScreenshots/TLP_Smoke/"+screenshotName+dateName+".png";
			 File finalDestination = new File(destination);
			 FileUtils.copyFile(source, finalDestination);
			 return destination;
			 }
		 
		@AfterTest
		public void tearDown() 
		{
			reports.endTest(test);
			reports.flush();
		}

		@AfterClass
		public void quit()
		{
			//driver.quit();
		}
	}

