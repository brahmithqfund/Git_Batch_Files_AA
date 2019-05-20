package Test.CO_ILP;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Tests.ExecuteScripts;
import Utilities.ExtentReports.Excel;

public class LonI_IstInstPayment_ACTM_ACHRevoke_2ndInstDeposit_Verify_Record {

	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	String SSN;
	String FileName;

	@BeforeClass 
	public void setup() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}

	@BeforeClass

	public synchronized void initialize() {

		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());


		String filename="AA_COILP_RegressionScenarios_Scenario.No_105_"+timestamp+".html";

		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/COILP/"+filename, true);
	}

	public void Login (String username,String password,String storenumber) {										
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
	}


	public void NewLoan_ILP(String SSN,String FileName) throws Exception{


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
				if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
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

					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window1)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window1);

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


						String
						Instamt=driver.findElement(By.name("requestBean.siilBean.disbAmt")).getAttribute("value");
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
				else
				{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
				}
			}
		}

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
			
			/*List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
			//*[@id="ContractScheduleTable"]/tbody/tr[2]/td[16]
            int schsize = options.size();*/
											
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
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			
			String BalanceStatus = null;
			
			BalanceStatus = driver.findElement(By.xpath("//*[@id='CustGrid']/tbody/tr[2]/td[5]")).getText();
			test.log(LogStatus.PASS, "Balance Status" +BalanceStatus);
			
			
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
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("ILP"))
					 {
						  driver.findElement(By.name("button")).click();
						// String PastDueAmt = null;
						 String InstAmt = null;
						 double Pymt;
						
						//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]
					                                            
						// PastDueAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]")).getText();
						 InstAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[9]/td/span[2]")).getText();
						 
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
						 driver.switchTo().frame("main");
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
	}



public void AgeStore(String SSN,String FileName,int Days) throws Exception
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

			                                        //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			                                       
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

			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));
			Actions actions1 = new Actions(driver);								        
			actions1.moveToElement(elements1).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);*/

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
					 driver.findElement(By.name("transactionList")).sendKeys("History");
					 if(ProductID.equals("ILP"))
					 {
						  driver.findElement(By.name("button")).click();
						 String Payment = null;
						 String InstAmt = null;
						 double Pymt;
						 
					/*	//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]
						 PastDueAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[22]/td/span[2]")).getText();
						 InstAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[9]/td/span[2]")).getText();
						 
						// double PDA = double.valueOf(PastDueAmt);
						 
						 double PDA = Double.valueOf(PastDueAmt);
						 
						 double InstAmount = Double.valueOf(InstAmt);
						 
						 Pymt = PDA + InstAmount;
						 String Payment = String.valueOf(Pymt);*/
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
						 driver.switchTo().frame("main");
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
								/*for( String winHandle1 : driver.getWindowHandles())
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
									}*/
							
					    	
						 }
					
			}
			
		}
		}



public void EOD_BatchProcess_DueDate(String SSN,String FileName,int days) throws Exception

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
	 
	 //driver.findElement(By.xpath("//input[@type='button' and @value='GO']")).click();
	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	

	 							//html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
	                          //   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input	
	 
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



// driver.findElement(By.xpath("//*[@id='home']")).click();*/

Thread.sleep(1000);
//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

test.log(LogStatus.PASS, "DueDate:" + DueDate);


//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

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
			//driver.findElement(By.name("drawerDeassignRequestBean.empPwd")).sendKeys("1234");
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
			//driver.findElement(By.name("drawerDeassignRequestBean.empPwd")).sendKeys("1234");
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
			Thread.sleep(2000);
			//driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			Thread.sleep(2000);
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
			Thread.sleep(2000);
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

			Thread.sleep(4000);
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
		 
		 

driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
//Click Login Button
driver.findElement(By.name("login")).click();
test.log(LogStatus.PASS, "Clicked on Submit button");
Thread.sleep(5000);
driver.switchTo().frame("topFrame");
driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
test.log(LogStatus.PASS, "Clicked on Store Setup");
Thread.sleep(5000);
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
	                    
	    }    }



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
            Thread.sleep(5000);
            // /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]
          /*  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
	        driver.findElement(By.cssSelector("li[id='900000']")).click();	*/
            driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]")).click();
            //wait.until(ExpectedConditions.elementToBeClickable(By.name("bt_BankDetails")));
            //driver.findElement(By.name("bt_BankDetails")).click();
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


public void EODProcessing_with_recordsChecking(String SSN,String FileName) throws Exception{


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

	


	private Object Field(WebDriver driver2) {
	// TODO Auto-generated method stub
	return null;
}

	@Test (priority=0) //Run this scenario with store date need to check again for the date

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
				this.NewLoan_ILP(SSN,FileName);	
				this.AgeStore_ILP(SSN, FileName, 0, 2);			
				this.Payment_Inst_Amount(SSN, FileName, 2);
				this.Apportions_List(SSN, FileName, 2);
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
		reports.flush();

	}			
	@AfterTest

	public void endReport(){
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