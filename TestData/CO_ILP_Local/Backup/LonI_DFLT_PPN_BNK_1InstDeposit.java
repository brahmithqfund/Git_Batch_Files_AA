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

public class LonI_DFLT_PPN_BNK_1InstDeposit {

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


		String filename="AA_COILP_RegressionScenarios_Scenario.No_100_"+timestamp+".html";

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


	
/*

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

 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();

}

//String winHandleBefore = driver.getWindowHandle();

for(String winHandle : driver.getWindowHandles()){

driver.switchTo().window(winHandle);

}



// driver.findElement(By.xpath("//*[@id='home']")).click();

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



*/
	

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
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
														//*[@id="ContractScheduleTable"]/tbody/tr[2]/td[15]
			
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

public void History(String SSN,String FileName) throws Exception
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
			BalanceStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
			test.log(LogStatus.PASS,"Balance  status is ::"  +BalanceStatus);
			String LoanStatus=null;
			LoanStatus =driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
			test.log(LogStatus.PASS,"Loan  status is ::"  +LoanStatus);
			String TotalPendMHC = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]")).getText();
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
			test.log(LogStatus.INFO, "Total MHC Fee Pending to date ::"+TotalPendMHC);
			   double TotalMHC = Double.parseDouble(TotalPendMHC); 
			String TotalPendORigiFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[24]/td/span[2]")).getText();
			test.log(LogStatus.INFO, "Total Origination Fee Pending to date ::"+TotalPendORigiFee);
			
			String TotalPaidMHCFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[32]/td/span[2]")).getText();
			test.log(LogStatus.INFO, "Total MHC Fee Paid to date ::"+TotalPaidMHCFee);
			
			String TotalPaidORigiFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[33]/td/span[2]")).getText();
			test.log(LogStatus.INFO, "Total Origination Fee Paid to date ::"+TotalPaidORigiFee);
			
		    String PrncAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[17]/td/span[2]")).getText();
			double PrincipleAmt = Double.parseDouble(PrncAmt); 
			double ExpectedOrigAmt = (PrincipleAmt*0.1);
			String OrigiFeeAmt = Double.toString(ExpectedOrigAmt);
			
			String TotInst = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[10]/td/span[2]")).getText();

			if(PrincipleAmt<=500 && PrincipleAmt>300)
			{
				test.log(LogStatus.INFO, "MHC Calculated  Per Month Should be $15");	
			}

			if(PrincipleAmt<=300 && PrincipleAmt>100)
			{
				test.log(LogStatus.INFO, "MHC Calculated  Per Month Should be $12.50");	
			}
		 
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[4]/table/tbody/tr[10]/td/span[2] total Inst
			List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
			int schsize = options.size();
        
			for(i=2; i<=schsize; i++)
			{
                 int j= i-1;
				String days = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[3]")).getText();
				test.log(LogStatus.PASS, "Days for  Schedule::"+j+":: is ::"+days);
				int totdays =Integer.valueOf(days);
				 totdays1 = totdays+totdays1; 
				String OriginFee = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[8]")).getText();
				test.log(LogStatus.PASS, "Origination Fee for  Schedule::"+j+":: is ::"+OriginFee);
				double totalorigfee = Double.parseDouble(OriginFee);
				totalorigfee1 = totalorigfee+totalorigfee1;
				String MHCFee = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[7]")).getText();
				test.log(LogStatus.PASS, "MHC Fee for  Schedule::"+j+":: is ::"+MHCFee);
			
				double totalMHCFee = Double.parseDouble(MHCFee);
				totalMHCFee1 = totalMHCFee+totalMHCFee1;
				if(totalMHCFee!=0)
				{
					test.log(LogStatus.INFO, "MHC Calculated  Per Month Should be ::"+totalMHCFee);	
					String Date = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+i+"]/td[2]")).getText();
					// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
					test.log(LogStatus.INFO, "MHC Calculated for this date::"+Date+" From last Accural Date / MHC Date is ::"+totalMHCFee);	
				
				}
				
			}
		    test.log(LogStatus.INFO, "Total Days is::"+totdays1);
		    String totdays2  = Integer.toString(totdays1);
		    double totdays3 = Double.parseDouble(totdays2); 
		    test.log(LogStatus.INFO, "Total Origination Fee is::"+totalorigfee1);
		    test.log(LogStatus.INFO, "Total MHC Fee  is::"+totalMHCFee1);
		    
			String OriginationAmt =  driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[24]/td/span[2]")).getText();
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
			test.log(LogStatus.INFO, "Total Origination Fee  ::"+OriginationAmt);

			if(OrigiFeeAmt.equals(OriginationAmt))
			{
				test.log(LogStatus.INFO, "Origination Fee Calculated is as Expected");
	
			}
			double MHCPerDay = TotalMHC/totdays3;
			test.log(LogStatus.INFO, "MHC Fee day ::"+MHCPerDay);
/*			double MHCPerMon = MHCPerDay*30;
			test.log(LogStatus.INFO, "MHC Fee per Month  ::"+MHCPerMon);*/
/*			if(PrincipleAmt<=500 && PrincipleAmt>300)
			{
				test.log(LogStatus.INFO, "MHC Calculated  Per Month Should be $15");	
					double TotInst1 = Double.parseDouble(TotInst); 
					//String TotInstmts = Double.toString(ExpectedOrigAmt);
					double CalMHCFee = TotInst1*15;
			}

			if(PrincipleAmt<=300 && PrincipleAmt>100)
			{
				test.log(LogStatus.INFO, "MHC Calculated  Per Month Should be $12.50");	
				double TotInst1 = Double.parseDouble(TotInst); 
				//String TotInstmts = Double.toString(ExpectedOrigAmt);
				double CalMHCFee = TotInst1*12.50;
			}*/
		    
		    		//Double.parseDouble(InterestAmt);
	
			
		
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]   MHC
					//(300*24.0*a/100)/365;
/*			test.log(LogStatus.PASS, "Expected  IntFee :: "+IntFee);
			String ReqAmt = Double.toString(PerAmt);*/
		    
		    // //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[17]/td/span[2]   principle
		     
		    
	/*	    double RegAmt = Double.parseDouble(InterestAmt);
			double IntFee = (300*24.0*a/100)/365;
			test.log(LogStatus.PASS, "Expected  IntFee :: "+IntFee);
			String ReqAmt = Double.toString(PerAmt);*/
			
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

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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



public void Payment_2ndEODbatchprocess(String SSN,String FileName) throws Exception{
	
	
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
						 String PastDueAmt = null;
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
			
			/*String OrgFeepaid = null;
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
              */

		}
	}
}




public void Bankrupt(String SSN,String FileName) throws Exception
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

    //    DueDate =
driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

         DueDate =
driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[2]/td[2]")).getText();
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
/*        driver.get(AdminURL);
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
driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Bankrupt");
     test.log(LogStatus.PASS, "select status as :: Bankrupt");






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
    ///html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
         if(
driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
         {
         test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
         }
            driver.close();

            driver = new InternetExplorerDriver();

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
	    }
	}




public void NACHA_ILP(String SSN,String FileName,int Days) throws Exception
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
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			                                          
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
		/*Thread.sleep(5000);
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
		}*/
	}
}
}




    
	private Object Field(WebDriver driver2) {
	// TODO Auto-generated method stub
	return null;
}

	@Test (priority=0) //Run this scenario with store date need to check again for the date

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
				this.NewLoan_ILP(SSN,FileName);				
				this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
				this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
				this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);				
				this.History(SSN, FileName);
				this.RCC_Revoke(SSN, FileName);
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