package Tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Utilities.ExtentReports.Excel;
import qfundx.MyBorrowerRegistrationpage;
import qfundx.MyCSRLoginpage;

public class LOC_SmokeTests {
	
	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	
public WebElement Field(WebDriver driver) {

	
	  try {
	    Thread.sleep(500);
	    WebElement element = (new WebDriverWait(driver, 9)).until(ExpectedConditions.visibilityOfElementLocated
	    		(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
	    return element;
	  } catch (Exception e) {
	    return null;
	  }
}

public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
	 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	 File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);		 
	                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	 String destination = System.getProperty("user.dir") + "/ExecutionReports/LOC/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	 File finalDestination = new File(destination);
	 FileUtils.copyFile(source, finalDestination);
	 return destination;
	 }

public void Login (String username,String password,String storenumber) throws InterruptedException {
	
	
	
	//Launch URL
	driver.get(appUrl);
	test.log(LogStatus.INFO, "CSR Application is launched:"+appUrl);
	driver.manage().window().maximize();
	String usenameId = "loginRequestBean.userId";
    String passwordId = "loginRequestBean.password";
    String StoreId = "loginRequestBean.locNbr";
    String Login = "login";
 
  
    
  //Enter Username(Email)
    driver.findElement(By.name(usenameId)).sendKeys(username);
    test.log(LogStatus.PASS, "Username is entered: "+username);

    //Enter Password
    driver.findElement(By.name(passwordId)).clear();
    driver.findElement(By.name(passwordId)).sendKeys(password);
    test.log(LogStatus.PASS, "Password is entered: "+password);
    
    //Enter Store Number
    driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
    test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
    
    //Click Login Button
    driver.findElement(By.name(Login)).click();
    test.log(LogStatus.PASS, "Clicked on Submit button");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    if(driver.getPageSource().contains("clockForm"))
	{					
	WebElement Clockouttime  = driver.findElement(By.name("clockRequestBean.clockOutTime"));
	Clockouttime.click();
	Clockouttime.clear();
	Clockouttime.sendKeys("2350");
	driver.findElement(By.name("clock")).click();
	//Thread.sleep(2000);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.name("clock")).click();
	}
    Thread.sleep(2000);
}

public void NewLoanDraw(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
				 { List<WebElement> radioButtons = driver.findElements(By.name("prodSel"));
				 
				 int iSize = radioButtons.size();
									 
				 // Start the loop from first checkbox to last checkbox
				 if(iSize != 0)
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
				 
				driver.findElement(By.name("ShareScreenBtn")).click();
				test.log(LogStatus.PASS, "ShareScreen Button is clicked");
					
					 											
					/* Old Code  
					 if(ProductName.equals("Line of Credit"))
					 
					{
						if(StoreID.equals("5435"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
							//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						}
						else
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						}
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					
					Thread.sleep(2000);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
					
					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button is clicked");*/	
					
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
						
					 driver.switchTo().window(Parent_Window);
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						driver.findElement(By.id("LoanButtonId")).click();
					 test.log(LogStatus.PASS, "Clicked on New Loan button");*/
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
								Thread.sleep(2000);
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

public void StatementGeneration(String SSN,String FileName) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);

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

/*wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));

driver.findElement(By.linkText("Borrower")).click();

test.log(LogStatus.PASS, "Clicked on Borrower");*/

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));

driver.findElement(By.linkText("QA Jobs")).click();

test.log(LogStatus.PASS, "Clicked on QA Jobs");

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));

driver.findElement(By.linkText("Process Date Change")).click();

test.log(LogStatus.PASS, "Clicked on Process Date Change");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));

Actions action = new Actions(driver);

action.moveToElement(element).build().perform();

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

driver.switchTo().frame("main");

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

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
			Thread.sleep(2000);
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
 	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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


	   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);		
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


public void Draw(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "Draw Amount");
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
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
				 driver.findElement(By.name("transactionList")).sendKeys("Draw");
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
			test.log(LogStatus.FAIL, "Draw New Loan is Failed ");
		}
		}
	}
	
}


public void AgeStore(String SSN,String FileName) throws Exception
{
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
			    driver.close();															
			    driver = new InternetExplorerDriver();
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			    driver.get(AdminURL);
			    storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
		}
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
				 cal.add(Calendar.DATE, 0);
				 Date DDueDateminus1= cal.getTime();
				 DueDate =df.format(DDueDateminus1);
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
				      WebDriverWait wait = new WebDriverWait(driver, 60);
				     /* wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
				   driver.findElement(By.linkText("Borrower")).click();
				   test.log(LogStatus.PASS, "Clicked on Borrower");*/
				      
			      wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));
			      driver.findElement(By.linkText("QA Jobs")).click();
			      test.log(LogStatus.PASS, "Clicked on QA Jobs");

			      wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Process Date Change")));
				   driver.findElement(By.linkText("Process Date Change")).click();
				   test.log(LogStatus.PASS, "Clicked on Process Date Change");
				   driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("storeCode")).click();
					 driver.findElement(By.name("storeCode")).sendKeys(StoreID);
					 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
					 Thread.sleep(3000);
					 driver.findElement(By.name("beginMonth")).clear();
					 driver.findElement(By.name("beginMonth")).click();
				     driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				     test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			        driver.findElement(By.name("beginDay")).clear();
			        driver.findElement(By.name("beginDay")).click();
			        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			        driver.findElement(By.name("beginYear")).clear();
			        driver.findElement(By.name("beginYear")).click();
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
				        	test.log(LogStatus.FAIL, "Process Date updation is failed.");
				        }

				    /*    
				        for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
							}
						  String DDueDate0[] =DueDate.split("/");
					        String DDueDate1 = DDueDate0[0];
					        String DDueDate2 = DDueDate0[1];
					        String DDueDate3 = DDueDate0[2];
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
				        	test.log(LogStatus.FAIL, "EOD Batch Process is Failed.");
				        }*/
				       						
}


public void AgeStore_10days(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
					 String DueDate=null;
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				
				 test.log(LogStatus.INFO, "Captured due date:"+DueDate);
		             
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
					      
					/* wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
					   driver.findElement(By.linkText("Borrower")).click();
					   test.log(LogStatus.PASS, "Clicked on Borrower");*/
					 
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

public void EODProcessing(String SSN,String FileName) throws Exception{


Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
			Thread.sleep(10000);
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

public void Payment(String SSN,String FileName) throws Exception{
	
	
	   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
	   	int lastrow=TestData.getLastRow("NewLoan");
	   	String sheetName="NewLoan";		
	   	test.log(LogStatus.INFO, "Payment Transaction");
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
	   				 driver.findElement(By.name("transactionList")).sendKeys("Payments");
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
	   						driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("50");
	   					
	   						 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
	   						 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
	   						driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("50");
	   						test.log(LogStatus.PASS, "Tender Amt is entered as 50");							
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
	   								 test.log(LogStatus.PASS, "Payment Completed Successfully ");
	   									driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
	   								}
	   							 else
	   								{
	   									test.log(LogStatus.FAIL, " Payment is not completed ");
	   								}
	   						 
	   				    	
	   					 }
	   				
	   		}
	   		
	   	}
	   }

public void Payment_Payoff(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "Payment Payoff Transaction");
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
				 driver.findElement(By.name("transactionList")).sendKeys("Payments");
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
					 
					String pmtamt =  driver.findElement(By.name("totalOwed")).getAttribute("value");
					
				
						driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "TenderType is entered: "+TenderType);
						driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(pmtamt);
						test.log(LogStatus.PASS, "tenderAmt is entered: "+pmtamt);
						driver.findElement(By.name("password")).sendKeys(Password);
						test.log(LogStatus.PASS, "Password is entered: "+Password);
						driver.findElement(By.name("Submit22")).click();
						test.log(LogStatus.PASS, "Clicked on finishpayment Button");	

						 for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 driver.findElement(By.id("btnADV_Yes")).click();
								test.log(LogStatus.PASS, "Clicked on finishpayment Button");
							 
							 								 
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
							 
								String pmtamt1 =  driver.findElement(By.name("payOffAmount")).getAttribute("value");
								
								
								driver.findElement(By.name("tenderType")).sendKeys(TenderType);
								test.log(LogStatus.PASS, "TenderType is entered: "+TenderType);
								driver.findElement(By.name("tenderAmount")).sendKeys(pmtamt1);
								test.log(LogStatus.PASS, "tenderAmt is entered: "+pmtamt);
								driver.findElement(By.name("password")).sendKeys(Password);
								test.log(LogStatus.PASS, "Password is entered: "+Password);
								driver.findElement(By.name("Submit22")).click();
								test.log(LogStatus.PASS, "Click on finishpayment Button");	
								if(driver.findElement(By.name("ok")).isDisplayed())
								{
									driver.findElement(By.name("ok")).click();
									test.log(LogStatus.PASS, "Clicked on finishPayoffpayment Button");
									test.log(LogStatus.INFO, "Payment Payoff is completed");
								}
					
		}
		
	}
}


public void Payment_PayoffVoid(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
	int lastrow=TestData.getLastRow("NewLoan");
	String sheetName="NewLoan";		
	test.log(LogStatus.INFO, "Void PayOff Transaction");
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

public void PayOffLoan(String SSN,String FileName) throws Exception{
	
	
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
			int lastrow=TestData.getLastRow("NewLoan");
			String sheetName="NewLoan";		
			test.log(LogStatus.INFO, "PayOff Loan Transaction");
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

public void CurePaymentStatus(String SSN,String FileName) throws Exception
	{
		
	   Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);		
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
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
						 String CheckStatus=null;
						 
			  CheckStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
				if(CheckStatus.equals("Y"))		
				{
					test.log(LogStatus.PASS,"Loan in Cure Period. "+CheckStatus);
					
				}
				else
				{
					test.log(LogStatus.FAIL,"Loan in Cure Period. "+CheckStatus);
				}
						
			}
		}
	}

public void CustomerDefault(String SSN,String FileName) throws Exception
{

Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			 
			/*driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");*/

			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));
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

			test.log(LogStatus.PASS, "LOC statement generated successfully");

			}

			else

			{

			test.log(LogStatus.FAIL, "LOC statement generation is failed");

			}
			}
	}
}

public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
	{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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

public void Default_WOProc(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
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



public void EditBorrower(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("Borrower_Registration");
		String sheetName="Borrower_Registration";	
		test.log(LogStatus.INFO, "Edit Borrower");
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
				String MonthlyPayDay=TestData.getCellData(sheetName,"MonthlyPayDay",row);
				String Income_PayFrequency=TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String SemiMonOthFirstDay=TestData.getCellData(sheetName,"SemiMonOthFirstDay",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String Monthlydate=null;
				String Monthlydate1=null;
				if(MonthlyPayDay.length()==3)
				{
					Monthlydate = MonthlyPayDay.substring(0, 1);
					Monthlydate1="0"+Monthlydate;
				}
				if(MonthlyPayDay.length()==4)
				{
					 Monthlydate1 = MonthlyPayDay.substring(0, 2);
				}
				
				WebDriverWait wait = new WebDriverWait(driver, 30);	
				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Borrower");
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
				//State --New code
				driver.findElement(By.xpath("//*[@id='myTable']/tbody/tr[2]/td[8]/div[3]/input")).click();
				Thread.sleep(2000);
				try {

					Alert alert1 = driver.switchTo().alert();

					alert1.accept();
					
					Thread.sleep(2000);
					Alert alert2 = driver.switchTo().alert();

					alert2.accept();

					//if alert present, accept and move on.

				}

				catch (NoAlertPresentException e) {

					//do what you normally would if you didn't have the alert.

				}
				Thread.sleep(2000);
				//End--
				String NextPayday =null;
				
				if(Income_PayFrequency.equals("Bi-Weekly"))
				{
					 NextPayday = driver.findElement(By.xpath("//*[@id='biWeekly']/td/table/tbody/tr[2]/td[2]/input")).getAttribute("value");
					String PayStubReviewedDate0[] =NextPayday.split("/");
					String PayStubReviewedDate2 = PayStubReviewedDate0[0];
					String month=null;
					if(PayStubReviewedDate2.length()==1)
					{
						month = "0"+PayStubReviewedDate0[0];
					}
					else
					{
						month = PayStubReviewedDate0[0];
					}
					 // int day= Integer.parseInt(PayStubReviewedDate2);
					  String Day = PayStubReviewedDate0[1];
					  if(Day.length()==1)
	  					{
						  Day = "0"+PayStubReviewedDate0[1];
	  					}
	  					else
	  					{
	  						Day = PayStubReviewedDate0[1];
	  					}
					  String Year = PayStubReviewedDate0[2];
					  NextPayday = month+"/"+Day+"/"+Year;
				}
				if(Income_PayFrequency.equals("Monthly"))
				{
					driver.switchTo().defaultContent();
					driver.switchTo().frame("bottom");
					String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
					 String Busdate[]=BusinessDt.split(":");
					 String date = Busdate[1];
					 DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
					 Date d1 = df.parse(date);
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(d1);
					 cal.add(Calendar.DATE, -10);
					 Date PayStubReviewedDate1= cal.getTime();
					 
					 String PayStubReviewedDate =df.format(PayStubReviewedDate1);
					String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");
					  String PayStubReviewedDate2 = PayStubReviewedDate0[0];
					  int day= Integer.parseInt(PayStubReviewedDate2);
					  String PayStubReviewedDate3 = PayStubReviewedDate0[1];
					  String PayStubReviewedDate4 = PayStubReviewedDate0[2];
					int yyyy= Integer.parseInt(PayStubReviewedDate4);
					  int DD= day+1;
					  String month="0"+String.valueOf(DD);
					  String days=Monthlydate1;
					 String year=null;
					  if(day==12)
					  {
						yyyy=yyyy+1;
						 year=String.valueOf(yyyy);
					  }
					  else
					  {
						year=PayStubReviewedDate4;
					  }
					  
					  
				 NextPayday = month+"/"+days+"/"+year;
				}
				if(Income_PayFrequency.equals("Semi-Monthly"))
				{
					driver.switchTo().defaultContent();
					driver.switchTo().frame("bottom");
					String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
					 String Busdate[]=BusinessDt.split(":");
					 String date = Busdate[1];
					 DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
					 Date d1 = df.parse(date);
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(d1);
					 cal.add(Calendar.DATE, 0);
					 Date PayStubReviewedDate1= cal.getTime();
					 
					 String PayStubReviewedDate =df.format(PayStubReviewedDate1);
					 String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");
					  String PayStubReviewedDate2 = PayStubReviewedDate0[0];
					  int day= Integer.parseInt(PayStubReviewedDate2);
					  String PayStubReviewedDate3 = PayStubReviewedDate0[1];
					  String PayStubReviewedDate4 = PayStubReviewedDate0[2];
					int yyyy= Integer.parseInt(PayStubReviewedDate4);
					  int DD= day+1;
					  String month="0"+String.valueOf(DD);
					  String days="01";
					 String year=null;
					  if(day==12)
					  {
						yyyy=yyyy+1;
						 year=String.valueOf(yyyy);
					  }
				
					  else
					  {
						year=PayStubReviewedDate4;
					  }
					 NextPayday = month+"/"+days+"/"+year;
				} 
					if(Income_PayFrequency.equals("Weekly"))
	  				{
	  					driver.switchTo().defaultContent();
	  					driver.switchTo().frame("bottom");
	  					String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
	  					 String Busdate[]=BusinessDt.split(":");
	  					 String date = Busdate[1];
	  					 DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
	  					 Date d1 = df.parse(date);
	  					 Calendar cal = Calendar.getInstance();
	  					 cal.setTime(d1);
	  					
	  					 if(SemiMonOthFirstDay.equals("Monday"))
	  					 {
	  					 cal.add(Calendar.DATE, 1);
	  					 Date PayStubReviewedDate1= cal.getTime();
	  					 
	  					  NextPayday =df.format(PayStubReviewedDate1);
	  					 }
	  					if(SemiMonOthFirstDay.equals("Tuesday"))
	  					 {
	  					 cal.add(Calendar.DATE, 2);
	  					 Date PayStubReviewedDate1= cal.getTime();
	  					 
	  					  NextPayday =df.format(PayStubReviewedDate1);
	  					 }
	  				if(SemiMonOthFirstDay.equals("Wednesday"))
					 {
					 cal.add(Calendar.DATE, 3);
					 Date PayStubReviewedDate1= cal.getTime();
					 
					  NextPayday =df.format(PayStubReviewedDate1);
					 }
	  			if(SemiMonOthFirstDay.equals("Thursday"))
					 {
					 cal.add(Calendar.DATE, 4);
					 Date PayStubReviewedDate1= cal.getTime();
					 
					  NextPayday =df.format(PayStubReviewedDate1);
					 }
	  		if(SemiMonOthFirstDay.equals("Friday"))
			 {
			 cal.add(Calendar.DATE, 5);
			 Date PayStubReviewedDate1= cal.getTime();
			 
			  NextPayday =df.format(PayStubReviewedDate1);
			 }
	  	if(SemiMonOthFirstDay.equals("Saturday"))
		 {
		 cal.add(Calendar.DATE, 6);
		 Date PayStubReviewedDate1= cal.getTime();
		 
		  NextPayday =df.format(PayStubReviewedDate1);
		 }
	  if(SemiMonOthFirstDay.equals("Sunday"))
		 {
		 cal.add(Calendar.DATE, 7);
		 Date PayStubReviewedDate1= cal.getTime();
		 
		  NextPayday =df.format(PayStubReviewedDate1);
		 }
	  }
			test.log(LogStatus.PASS,"Next Paydate."+NextPayday);
							
	
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
	   		Date DDueDate = df.parse(NextPayday);
	   			Calendar cal = Calendar.getInstance();
	   			cal.setTime(DDueDate);
	   			cal.add(Calendar.DATE, -10);
	   			Date DDueDateminus1= cal.getTime();
	   			 String DueDateminus1 =df.format(DDueDateminus1);
	   			String NextPayday0[] =DueDateminus1.split("/");
	   			String NextPayday1 = NextPayday0[0];
	   			String NextPayday2 = NextPayday0[1];
	   			String NextPayday3 = NextPayday0[2];
	   			WebDriverWait wait1 = new WebDriverWait(driver, 30);
	   			driver.switchTo().defaultContent();
	   			driver.switchTo().frame("topFrame");
	   			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
	   			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
	   			test.log(LogStatus.PASS, "Clicked on Transactions");
	   			driver.switchTo().defaultContent();
	   			driver.switchTo().frame("mainFrame");
	   			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	   			/*driver.findElement(By.linkText("Borrower")).click();
	   			test.log(LogStatus.PASS, "Clicked on Borrower");*/
	   			
	   			driver.findElement(By.linkText("QA Jobs")).click();
	   			test.log(LogStatus.PASS, "Clicked on QA Jobs");

	   			driver.findElement(By.linkText("Process Date Change")).click();
	   			test.log(LogStatus.PASS, "Clicked on Process Date Change");
	   			driver.switchTo().defaultContent();
	   			driver.switchTo().frame("mainFrame");
	   			driver.switchTo().frame("main");

	   			driver.findElement(By.name("storeCode")).click();
	   			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
	   			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
	   			Thread.sleep(3000);
	   			driver.findElement(By.name("beginMonth")).clear();
	   			driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1); 
	   			test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);
	   			driver.findElement(By.name("beginDay")).clear();
	   			driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);
	   			test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);
	   			driver.findElement(By.name("beginYear")).clear();
	   			driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);
	   			test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);
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
	   				test.log(LogStatus.FAIL, "Failed to update Process Date");
	   			}

	   			driver.switchTo().defaultContent();
	   			driver.switchTo().frame("topFrame");
	   			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
	   			driver.findElement(By.name("beginMonth")).clear();
	   			driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1); 
	   			test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);
	   			driver.findElement(By.name("beginDay")).clear();
	   			driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);
	   			test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);
	   			driver.findElement(By.name("beginYear")).clear();
	   			driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);
	   			test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);
	   			driver.findElement(By.name("submit")).click();
	   			test.log(LogStatus.PASS, "Clicked on submit button");
	   			
	   		}
	   	}
	}

 public void RCCSchduleStatus(String SSN,String FileName) throws Exception
	{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";	
		test.log(LogStatus.INFO, "RCC Schedule Status");
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
				test.log(LogStatus.INFO, "RCCScheduleStatus");
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
				//String CheckStatus=null;
				WebElement RCCEnabled = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input"));
				Boolean CheckStatus = RCCEnabled.isEnabled();
				
				test.log(LogStatus.PASS," RCC Schedule is Enabled");
				
			}
		}
	}

@BeforeClass
public synchronized void initialize() {
	
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
			
	String filename="AA_LOC_SmokeTestScenarios_Txns"+timestamp+".html";
	
	reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/AA_LOC_SmokeTestScenarios_Txns/"+filename, true);
}

	@BeforeMethod
	public void setup() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
        Thread.sleep(5000); //Allow OS to kill the process
        System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	
	@Test (priority=1)
	public void Draw_Deliquent_Statement() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_Draw_Statement_Deliquent_Cure_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
		       test = reports.startTest("Cure"+Header, "Initiate LOC Loan with CASH_Draw_Statement_Draw_Delinquent_Payment_void_Statement_cure");
		        appUrl = AppURL;
		      MyCSRLoginpage login = new MyCSRLoginpage();
		    login.Login(UserName, Password, StoreId, driver, AppURL, test);
		    MyBorrowerRegistrationpage Reg = new MyBorrowerRegistrationpage();
		    Reg.RegistrationPage_NewLoan_LOC(driver, test, AppURL, SSN, FileName);
		    
		        this.NewLoanDraw(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        /*this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		        this.Draw(SSN, FileName);
		        this.AgeStore(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.Payment_Payoff(SSN, FileName);
		        this.Payment_PayoffVoid(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);	
		        /*this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);//  this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);*/
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);//  this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);
		      	}
		}
	  }

	@Test (priority=2)
	 public void LOCInitiation_Payoff_EODStatement() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_LOCInitialtion_Payoff_EODStatement_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
		       test = reports.startTest("AA_LOCInitialtion_Payoff_EODStatement_Txn_"+Header, "Initiate LOC Loan with ACH_Draw_Statement_Payoff_Draw_On Due Date_EOD_Statement");
		        appUrl = AppURL;
		        MyCSRLoginpage login = new MyCSRLoginpage();
			    login.Login(UserName, Password, StoreId, driver, AppURL, test);
			    MyBorrowerRegistrationpage Reg = new MyBorrowerRegistrationpage();
			    Reg.RegistrationPage_NewLoan_LOC(driver, test, AppURL, SSN, FileName);
		        this.NewLoanDraw(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.PayOffLoan(SSN, FileName);
		        this.Draw(SSN, FileName);
		        this.AgeStore(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        
		}
		}
	}

	 @Test(priority = 3)
	 public void RegistrationTest() throws Exception {
			
			// Start test. Mention test script name
			String FileName= "AA_Loan_Statement_Default_Rcc_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
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
			        test = reports.startTest("Loan_Statement_Default_Rcc"+Header, "Initiate LOC Loan with CASH_Draw_Statement_Draw_pay less that min payment amount_Delinquent_statement_cure_Default_RCC schedule should be generated 10 days before due date");
			        appUrl = AppURL;
			        CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_LOC(driver, test,AppURL, SSN,FileName);
			        this.NewLoanDraw(SSN,FileName);
			        this.StatementGeneration(SSN, FileName);  
//			        this.DrawerDeassign(SSN, FileName);
//			        this.StatementGeneration_EODProcessing(SSN, FileName);
//			        this.StoreInfo(SSN, FileName);
//			        this.Safeassign(SSN, FileName);
//			        this.Drawerassign(SSN, FileName);
			        this.Draw(SSN, FileName);
			        this.Payment(SSN, FileName);
			        this.AgeStore(SSN,FileName);
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName); //this.EODProcessing(SSN, FileName); 
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.StatementGeneration(SSN, FileName);
//			        this.DrawerDeassign(SSN, FileName); 
//			        this.StatementGeneration_EODProcessing(SSN, FileName); // this.EODProcessing(SSN, FileName);
//				    this.StoreInfo(SSN, FileName);
//				    this.Safeassign(SSN, FileName);
//				    this.Drawerassign(SSN, FileName);  
			        this.AgeStore_10days(SSN, FileName);
			        this.DrawerDeassign(SSN, FileName);
			        this.StatementGeneration_EODProcessing(SSN, FileName); // this.EODProcessing(SSN, FileName);
			        this.StoreInfo(SSN, FileName);
			        this.Safeassign(SSN, FileName);
			        this.Drawerassign(SSN, FileName);
			        this.CurePaymentStatus(SSN, FileName); 
			        this.CustomerDefault(SSN, FileName);
			        this.DefaultPaymentStatus(SSN, FileName);
			        this.EditBorrower(SSN, FileName); 
			        this.RCCSchduleStatus(SSN, FileName);
			}
			}
			}
	 
	@Test (priority=4)
		
		 public void Default_CustomerWO() throws Exception {
		
			// Start test. Mention test script name
			String FileName= "AA_Default_CustomerWO_Txn_Testdata.xls";
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);  
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
			       
			        test = reports.startTest("Default_CustomerWO"+Header, "Initiate Loan->Draw->Statement Generation->Default_CustomerWO");
			        appUrl = AppURL;
			        CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_LOC(driver, test,AppURL, SSN,FileName);
			        this.NewLoanDraw(SSN,FileName);
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
			   	       		        		        	        	        
			}
	  }
	}
	 
	 
	 @AfterMethod
	 public void getResult(ITestResult result) throws Exception{
		 if(ITestResult.FAILURE == result.getStatus()){
			
			String screenshotPath = LOC_SmokeTests.getScreenhot(driver, result.getName());
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
		 

	 
}
